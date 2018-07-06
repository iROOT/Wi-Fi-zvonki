package net.hockeyapp.android.d;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedList;
import java.util.Queue;
import net.hockeyapp.android.c.e;
import net.hockeyapp.android.k;
import net.hockeyapp.android.views.AttachmentView;

public class a {
    private boolean downloadRunning;
    private Queue<b> queue;

    private static class a {
        public static final a INSTANCE = new a();

        private a() {
        }
    }

    private static class b {
        private final AttachmentView attachmentView;
        private final e feedbackAttachment;
        private int remainingRetries;
        private boolean success;

        /* synthetic */ b(e eVar, AttachmentView attachmentView, AnonymousClass1 anonymousClass1) {
            this(eVar, attachmentView);
        }

        private b(e eVar, AttachmentView attachmentView) {
            this.feedbackAttachment = eVar;
            this.attachmentView = attachmentView;
            this.success = false;
            this.remainingRetries = 2;
        }

        public e getFeedbackAttachment() {
            return this.feedbackAttachment;
        }

        public AttachmentView getAttachmentView() {
            return this.attachmentView;
        }

        public boolean isSuccess() {
            return this.success;
        }

        public void setSuccess(boolean z) {
            this.success = z;
        }

        public boolean hasRetry() {
            return this.remainingRetries > 0;
        }

        public boolean consumeRetry() {
            int i = this.remainingRetries - 1;
            this.remainingRetries = i;
            return i >= 0;
        }
    }

    private static class c extends AsyncTask<Void, Integer, Boolean> {
        private Bitmap bitmap = null;
        private int bitmapOrientation = 0;
        private final b downloadJob;
        private File dropFolder = net.hockeyapp.android.a.getHockeyAppStorageDir();
        private final Handler handler;

        public c(b bVar, Handler handler) {
            this.downloadJob = bVar;
            this.handler = handler;
        }

        protected void onPreExecute() {
        }

        protected Boolean doInBackground(Void... voidArr) {
            e feedbackAttachment = this.downloadJob.getFeedbackAttachment();
            if (feedbackAttachment.isAvailableInCache()) {
                Log.e("HockeyApp", "Cached...");
                loadImageThumbnail();
                return Boolean.valueOf(true);
            }
            Log.e("HockeyApp", "Downloading...");
            boolean downloadAttachment = downloadAttachment(feedbackAttachment.getUrl(), feedbackAttachment.getCacheId());
            if (downloadAttachment) {
                loadImageThumbnail();
            }
            return Boolean.valueOf(downloadAttachment);
        }

        protected void onProgressUpdate(Integer... numArr) {
        }

        protected void onPostExecute(Boolean bool) {
            AttachmentView attachmentView = this.downloadJob.getAttachmentView();
            this.downloadJob.setSuccess(bool.booleanValue());
            if (bool.booleanValue()) {
                attachmentView.setImage(this.bitmap, this.bitmapOrientation);
            } else if (!this.downloadJob.hasRetry()) {
                attachmentView.signalImageLoadingError();
            }
            this.handler.sendEmptyMessage(0);
        }

        private void loadImageThumbnail() {
            try {
                String cacheId = this.downloadJob.getFeedbackAttachment().getCacheId();
                AttachmentView attachmentView = this.downloadJob.getAttachmentView();
                this.bitmapOrientation = net.hockeyapp.android.e.e.determineOrientation(new File(this.dropFolder, cacheId));
                this.bitmap = net.hockeyapp.android.e.e.decodeSampledBitmap(new File(this.dropFolder, cacheId), this.bitmapOrientation == 1 ? attachmentView.getWidthLandscape() : attachmentView.getWidthPortrait(), this.bitmapOrientation == 1 ? attachmentView.getMaxHeightLandscape() : attachmentView.getMaxHeightPortrait());
            } catch (IOException e) {
                e.printStackTrace();
                this.bitmap = null;
            }
        }

        private boolean downloadAttachment(String str, String str2) {
            try {
                URLConnection createConnection = createConnection(new URL(str));
                createConnection.connect();
                int contentLength = createConnection.getContentLength();
                String headerField = createConnection.getHeaderField("Status");
                if (headerField != null && !headerField.startsWith("200")) {
                    return false;
                }
                File file = new File(this.dropFolder, str2);
                InputStream bufferedInputStream = new BufferedInputStream(createConnection.getInputStream());
                OutputStream fileOutputStream = new FileOutputStream(file);
                byte[] bArr = new byte[k.FEEDBACK_FAILED_TITLE_ID];
                long j = 0;
                while (true) {
                    int read = bufferedInputStream.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    j += (long) read;
                    publishProgress(new Integer[]{Integer.valueOf((int) ((100 * j) / ((long) contentLength)))});
                    fileOutputStream.write(bArr, 0, read);
                }
                fileOutputStream.flush();
                fileOutputStream.close();
                bufferedInputStream.close();
                return j > 0;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        private URLConnection createConnection(URL url) {
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.addRequestProperty("User-Agent", "HockeySDK/Android");
            httpURLConnection.setInstanceFollowRedirects(true);
            if (VERSION.SDK_INT <= 9) {
                httpURLConnection.setRequestProperty("connection", "close");
            }
            return httpURLConnection;
        }
    }

    /* synthetic */ a(AnonymousClass1 anonymousClass1) {
        this();
    }

    public static a getInstance() {
        return a.INSTANCE;
    }

    private a() {
        this.queue = new LinkedList();
        this.downloadRunning = false;
    }

    public void download(e eVar, AttachmentView attachmentView) {
        this.queue.add(new b(eVar, attachmentView, null));
        downloadNext();
    }

    private void downloadNext() {
        if (!this.downloadRunning) {
            b bVar = (b) this.queue.peek();
            if (bVar != null) {
                AsyncTask cVar = new c(bVar, new Handler() {
                    public void handleMessage(Message message) {
                        final b bVar = (b) a.this.queue.poll();
                        if (!bVar.isSuccess() && bVar.consumeRetry()) {
                            postDelayed(new Runnable() {
                                public void run() {
                                    a.this.queue.add(bVar);
                                    a.this.downloadNext();
                                }
                            }, 3000);
                        }
                        a.this.downloadRunning = false;
                        a.this.downloadNext();
                    }
                });
                this.downloadRunning = true;
                net.hockeyapp.android.e.a.execute(cVar);
            }
        }
    }
}
