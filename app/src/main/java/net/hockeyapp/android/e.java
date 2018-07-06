package net.hockeyapp.android;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.media.MediaScannerConnection;
import android.media.MediaScannerConnection.MediaScannerConnectionClient;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import net.hockeyapp.android.c.h;
import net.hockeyapp.android.d.i;
import net.hockeyapp.android.e.f;

public class e {
    private static final String BROADCAST_ACTION = "net.hockeyapp.android.SCREENSHOT";
    private static final int BROADCAST_REQUEST_CODE = 1;
    private static final int SCREENSHOT_NOTIFICATION_ID = 1;
    private static Activity currentActivity;
    private static String identifier = null;
    private static f lastListener = null;
    private static boolean notificationActive = false;
    private static BroadcastReceiver receiver = null;
    private static h requireUserEmail;
    private static h requireUserName;
    private static String urlString = null;

    private static class a implements MediaScannerConnectionClient {
        private MediaScannerConnection connection;
        private String path;

        /* synthetic */ a(String str, AnonymousClass1 anonymousClass1) {
            this(str);
        }

        private a(String str) {
            this.connection = null;
            this.path = str;
        }

        public void setConnection(MediaScannerConnection mediaScannerConnection) {
            this.connection = mediaScannerConnection;
        }

        public void onMediaScannerConnected() {
            if (this.connection != null) {
                this.connection.scanFile(this.path, null);
            }
        }

        public void onScanCompleted(String str, Uri uri) {
            Log.i("HockeyApp", String.format("Scanned path %s -> URI = %s", new Object[]{str, uri.toString()}));
            this.connection.disconnect();
        }
    }

    public static void register(Context context, String str) {
        register(context, str, null);
    }

    public static void register(Context context, String str, f fVar) {
        register(context, a.BASE_URL, str, fVar);
    }

    public static void register(Context context, String str, String str2, f fVar) {
        if (context != null) {
            identifier = net.hockeyapp.android.e.h.sanitizeAppIdentifier(str2);
            urlString = str;
            lastListener = fVar;
            a.loadFromContext(context);
        }
    }

    public static void unregister() {
        lastListener = null;
    }

    public static void showFeedbackActivity(Context context, Uri... uriArr) {
        showFeedbackActivity(context, null, uriArr);
    }

    public static void showFeedbackActivity(Context context, Bundle bundle, Uri... uriArr) {
        if (context != null) {
            Class cls = null;
            if (lastListener != null) {
                cls = lastListener.getFeedbackActivityClass();
            }
            if (cls == null) {
                cls = FeedbackActivity.class;
            }
            Intent intent = new Intent();
            if (!(bundle == null || bundle.isEmpty())) {
                intent.putExtras(bundle);
            }
            intent.setFlags(268435456);
            intent.setClass(context, cls);
            intent.putExtra("url", getURLString(context));
            intent.putExtra("initialAttachments", uriArr);
            context.startActivity(intent);
        }
    }

    public static void checkForAnswersAndNotify(final Context context) {
        String feedbackTokenFromPrefs = f.getInstance().getFeedbackTokenFromPrefs(context);
        if (feedbackTokenFromPrefs != null) {
            int i = context.getSharedPreferences(net.hockeyapp.android.d.h.PREFERENCES_NAME, 0).getInt(net.hockeyapp.android.d.h.ID_LAST_MESSAGE_SEND, -1);
            AsyncTask iVar = new i(context, getURLString(context), null, null, null, null, null, feedbackTokenFromPrefs, new Handler() {
                public void handleMessage(Message message) {
                    String string = message.getData().getString("feedback_response");
                    if (string != null) {
                        AsyncTask hVar = new net.hockeyapp.android.d.h(context, string, null, "fetch");
                        hVar.setUrlString(e.getURLString(context));
                        net.hockeyapp.android.e.a.execute(hVar);
                    }
                }
            }, true);
            iVar.setShowProgressDialog(false);
            iVar.setLastMessageId(i);
            net.hockeyapp.android.e.a.execute(iVar);
        }
    }

    public static f getLastListener() {
        return lastListener;
    }

    private static String getURLString(Context context) {
        return urlString + "api/2/apps/" + identifier + "/feedback/";
    }

    public static h getRequireUserName() {
        return requireUserName;
    }

    public static void setRequireUserName(h hVar) {
        requireUserName = hVar;
    }

    public static h getRequireUserEmail() {
        return requireUserEmail;
    }

    public static void setRequireUserEmail(h hVar) {
        requireUserEmail = hVar;
    }

    public static void setActivityForScreenshot(Activity activity) {
        currentActivity = activity;
        if (!notificationActive) {
            startNotification();
        }
    }

    public static void unsetCurrentActivityForScreenshot(Activity activity) {
        if (currentActivity != null && currentActivity == activity) {
            endNotification();
            currentActivity = null;
        }
    }

    public static void takeScreenshot(final Context context) {
        View decorView = currentActivity.getWindow().getDecorView();
        decorView.setDrawingCacheEnabled(true);
        final Bitmap drawingCache = decorView.getDrawingCache();
        String localClassName = currentActivity.getLocalClassName();
        File hockeyAppStorageDir = a.getHockeyAppStorageDir();
        File file = new File(hockeyAppStorageDir, localClassName + ".jpg");
        int i = 1;
        while (file.exists()) {
            file = new File(hockeyAppStorageDir, localClassName + "_" + i + ".jpg");
            i++;
        }
        new AsyncTask<File, Void, Boolean>() {
            protected Boolean doInBackground(File... fileArr) {
                try {
                    OutputStream fileOutputStream = new FileOutputStream(fileArr[0]);
                    drawingCache.compress(CompressFormat.JPEG, 100, fileOutputStream);
                    fileOutputStream.close();
                    return Boolean.valueOf(true);
                } catch (Throwable e) {
                    Log.e("HockeyApp", "Could not save screenshot.", e);
                    return Boolean.valueOf(false);
                }
            }

            protected void onPostExecute(Boolean bool) {
                if (!bool.booleanValue()) {
                    Toast.makeText(context, "Screenshot could not be created. Sorry.", 2000).show();
                }
            }
        }.execute(new File[]{file});
        Object aVar = new a(file.getAbsolutePath(), null);
        MediaScannerConnection mediaScannerConnection = new MediaScannerConnection(currentActivity, aVar);
        aVar.setConnection(mediaScannerConnection);
        mediaScannerConnection.connect();
        Toast.makeText(context, "Screenshot '" + file.getName() + "' is available in gallery.", 2000).show();
    }

    private static void startNotification() {
        notificationActive = true;
        NotificationManager notificationManager = (NotificationManager) currentActivity.getSystemService("notification");
        int identifier = currentActivity.getResources().getIdentifier("ic_menu_camera", "drawable", "android");
        Intent intent = new Intent();
        intent.setAction(BROADCAST_ACTION);
        notificationManager.notify(1, net.hockeyapp.android.e.h.createNotification(currentActivity, PendingIntent.getBroadcast(currentActivity, 1, intent, 1073741824), "HockeyApp Feedback", "Take a screenshot for your feedback.", identifier));
        if (receiver == null) {
            receiver = new BroadcastReceiver() {
                public void onReceive(Context context, Intent intent) {
                    e.takeScreenshot(context);
                }
            };
        }
        currentActivity.registerReceiver(receiver, new IntentFilter(BROADCAST_ACTION));
    }

    private static void endNotification() {
        notificationActive = false;
        currentActivity.unregisterReceiver(receiver);
        ((NotificationManager) currentActivity.getSystemService("notification")).cancel(1);
    }
}
