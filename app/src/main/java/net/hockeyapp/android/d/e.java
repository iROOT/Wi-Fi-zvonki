package net.hockeyapp.android.d;

import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Environment;
import com.mavenir.android.vtow.activation.ActivationAdapter;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.UUID;
import net.hockeyapp.android.b.a;
import net.hockeyapp.android.k;

public class e extends AsyncTask<Void, Integer, Long> {
    protected static final int MAX_REDIRECTS = 6;
    protected Context context;
    private String downloadErrorMessage;
    protected String filePath = (Environment.getExternalStorageDirectory().getAbsolutePath() + "/Download");
    protected String filename = (UUID.randomUUID() + ".apk");
    protected a notifier;
    protected ProgressDialog progressDialog;
    protected String urlString;

    public e(Context context, String str, a aVar) {
        this.context = context;
        this.urlString = str;
        this.notifier = aVar;
        this.downloadErrorMessage = null;
    }

    public void attach(Context context) {
        this.context = context;
    }

    public void detach() {
        this.context = null;
        this.progressDialog = null;
    }

    protected Long doInBackground(Void... voidArr) {
        try {
            URLConnection createConnection = createConnection(new URL(getURLString()), 6);
            createConnection.connect();
            int contentLength = createConnection.getContentLength();
            String contentType = createConnection.getContentType();
            if (contentType == null || !contentType.contains("text")) {
                File file = new File(this.filePath);
                if (file.mkdirs() || file.exists()) {
                    File file2 = new File(file, this.filename);
                    InputStream bufferedInputStream = new BufferedInputStream(createConnection.getInputStream());
                    OutputStream fileOutputStream = new FileOutputStream(file2);
                    byte[] bArr = new byte[k.FEEDBACK_FAILED_TITLE_ID];
                    long j = 0;
                    while (true) {
                        int read = bufferedInputStream.read(bArr);
                        if (read != -1) {
                            j += (long) read;
                            publishProgress(new Integer[]{Integer.valueOf(Math.round((((float) j) * 100.0f) / ((float) contentLength)))});
                            fileOutputStream.write(bArr, 0, read);
                        } else {
                            fileOutputStream.flush();
                            fileOutputStream.close();
                            bufferedInputStream.close();
                            return Long.valueOf(j);
                        }
                    }
                }
                throw new IOException("Could not create the dir(s):" + file.getAbsolutePath());
            }
            this.downloadErrorMessage = "The requested download does not appear to be a file.";
            return Long.valueOf(0);
        } catch (Exception e) {
            e.printStackTrace();
            return Long.valueOf(0);
        }
    }

    protected void setConnectionProperties(HttpURLConnection httpURLConnection) {
        httpURLConnection.addRequestProperty("User-Agent", "HockeySDK/Android");
        httpURLConnection.setInstanceFollowRedirects(true);
        if (VERSION.SDK_INT <= 9) {
            httpURLConnection.setRequestProperty("connection", "close");
        }
    }

    protected URLConnection createConnection(URL url, int i) {
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        setConnectionProperties(httpURLConnection);
        int responseCode = httpURLConnection.getResponseCode();
        if ((responseCode != ActivationAdapter.REASON_NO_NEW_CONFIG_DATA && responseCode != ActivationAdapter.REASON_DEVICE_BLOCKED && responseCode != ActivationAdapter.REASON_DEVICE_BLOCKED_ROOTED) || i == 0) {
            return httpURLConnection;
        }
        URL url2 = new URL(httpURLConnection.getHeaderField("Location"));
        if (url.getProtocol().equals(url2.getProtocol())) {
            return httpURLConnection;
        }
        httpURLConnection.disconnect();
        return createConnection(url2, i - 1);
    }

    protected void onProgressUpdate(Integer... numArr) {
        try {
            if (this.progressDialog == null) {
                this.progressDialog = new ProgressDialog(this.context);
                this.progressDialog.setProgressStyle(1);
                this.progressDialog.setMessage("Loading...");
                this.progressDialog.setCancelable(false);
                this.progressDialog.show();
            }
            this.progressDialog.setProgress(numArr[0].intValue());
        } catch (Exception e) {
        }
    }

    protected void onPostExecute(Long l) {
        if (this.progressDialog != null) {
            try {
                this.progressDialog.dismiss();
            } catch (Exception e) {
            }
        }
        if (l.longValue() > 0) {
            this.notifier.downloadSuccessful(this);
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setDataAndType(Uri.fromFile(new File(this.filePath, this.filename)), "application/vnd.android.package-archive");
            intent.setFlags(268435456);
            this.context.startActivity(intent);
            return;
        }
        try {
            CharSequence charSequence;
            Builder builder = new Builder(this.context);
            builder.setTitle(k.get(this.notifier, 256));
            if (this.downloadErrorMessage == null) {
                charSequence = k.get(this.notifier, k.DOWNLOAD_FAILED_DIALOG_MESSAGE_ID);
            } else {
                charSequence = this.downloadErrorMessage;
            }
            builder.setMessage(charSequence);
            builder.setNegativeButton(k.get(this.notifier, k.DOWNLOAD_FAILED_DIALOG_NEGATIVE_BUTTON_ID), new OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    e.this.notifier.downloadFailed(e.this, Boolean.valueOf(false));
                }
            });
            builder.setPositiveButton(k.get(this.notifier, k.DOWNLOAD_FAILED_DIALOG_POSITIVE_BUTTON_ID), new OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    e.this.notifier.downloadFailed(e.this, Boolean.valueOf(true));
                }
            });
            builder.create().show();
        } catch (Exception e2) {
        }
    }

    protected String getURLString() {
        return this.urlString + "&type=apk";
    }
}
