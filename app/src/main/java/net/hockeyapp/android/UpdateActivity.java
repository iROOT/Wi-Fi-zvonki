package net.hockeyapp.android;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.provider.Settings.Global;
import android.provider.Settings.Secure;
import android.provider.Settings.SettingNotFoundException;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import net.hockeyapp.android.c.c;
import net.hockeyapp.android.d.e;
import net.hockeyapp.android.d.f;
import net.hockeyapp.android.e.a;
import net.hockeyapp.android.e.j;
import net.hockeyapp.android.views.UpdateView;

public class UpdateActivity extends Activity implements OnClickListener, n {
    private final int DIALOG_ERROR_ID = 0;
    private Context context;
    protected e downloadTask;
    private c error;
    protected j versionHelper;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTitle("App Update");
        setContentView(getLayoutView());
        this.context = this;
        this.versionHelper = new j(this, getIntent().getStringExtra("json"), this);
        configureView();
        this.downloadTask = (e) getLastNonConfigurationInstance();
        if (this.downloadTask != null) {
            this.downloadTask.attach(this);
        }
    }

    protected void configureView() {
        ((TextView) findViewById(UpdateView.NAME_LABEL_ID)).setText(getAppName());
        final TextView textView = (TextView) findViewById(4099);
        final String str = "Version " + this.versionHelper.getVersionString();
        final String fileDateString = this.versionHelper.getFileDateString();
        String str2 = "Unknown size";
        if (this.versionHelper.getFileSizeBytes() >= 0) {
            str2 = String.format("%.2f", new Object[]{Float.valueOf(((float) r4) / 1048576.0f)}) + " MB";
        } else {
            a.execute(new f(this, getIntent().getStringExtra("url"), new net.hockeyapp.android.b.a() {
                public void downloadSuccessful(e eVar) {
                    if (eVar instanceof f) {
                        long size = ((f) eVar).getSize();
                        textView.setText(str + "\n" + fileDateString + " - " + (String.format("%.2f", new Object[]{Float.valueOf(((float) size) / 1048576.0f)}) + " MB"));
                    }
                }
            }));
        }
        textView.setText(str + "\n" + fileDateString + " - " + str2);
        ((Button) findViewById(UpdateView.UPDATE_BUTTON_ID)).setOnClickListener(this);
        WebView webView = (WebView) findViewById(UpdateView.WEB_VIEW_ID);
        webView.clearCache(true);
        webView.destroyDrawingCache();
        webView.loadDataWithBaseURL(a.BASE_URL, getReleaseNotes(), "text/html", "utf-8", null);
    }

    protected String getReleaseNotes() {
        return this.versionHelper.getReleaseNotes(false);
    }

    public Object onRetainNonConfigurationInstance() {
        if (this.downloadTask != null) {
            this.downloadTask.detach();
        }
        return this.downloadTask;
    }

    protected void startDownloadTask() {
        startDownloadTask(getIntent().getStringExtra("url"));
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        enableUpdateButton();
        if (strArr.length != 0 && iArr.length != 0 && i == 1) {
            if (iArr[0] == 0) {
                prepareDownload();
                return;
            }
            Log.w("HockeyApp", "User denied write permission, can't continue with updater task.");
            p lastListener = o.getLastListener();
            if (lastListener != null) {
                lastListener.onUpdatePermissionsNotGranted();
            } else {
                new Builder(this.context).setTitle(k.get(k.PERMISSION_UPDATE_TITLE_ID)).setMessage(k.get(k.PERMISSION_UPDATE_MESSAGE_ID)).setNegativeButton(k.get(k.PERMISSION_DIALOG_NEGATIVE_BUTTON_ID), null).setPositiveButton(k.get(k.PERMISSION_DIALOG_POSITIVE_BUTTON_ID), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        this.prepareDownload();
                    }
                }).create().show();
            }
        }
    }

    protected void startDownloadTask(String str) {
        createDownloadTask(str, new net.hockeyapp.android.b.a() {
            public void downloadSuccessful(e eVar) {
                UpdateActivity.this.enableUpdateButton();
            }

            public void downloadFailed(e eVar, Boolean bool) {
                if (bool.booleanValue()) {
                    UpdateActivity.this.startDownloadTask();
                } else {
                    UpdateActivity.this.enableUpdateButton();
                }
            }

            public String getStringForResource(int i) {
                p lastListener = o.getLastListener();
                if (lastListener != null) {
                    return lastListener.getStringForResource(i);
                }
                return null;
            }
        });
        a.execute(this.downloadTask);
    }

    protected void createDownloadTask(String str, net.hockeyapp.android.b.a aVar) {
        this.downloadTask = new e(this, str, aVar);
    }

    public void enableUpdateButton() {
        findViewById(UpdateView.UPDATE_BUTTON_ID).setEnabled(true);
    }

    public int getCurrentVersionCode() {
        int i = -1;
        try {
            return getPackageManager().getPackageInfo(getPackageName(), NotificationCompat.FLAG_HIGH_PRIORITY).versionCode;
        } catch (NameNotFoundException e) {
            return i;
        }
    }

    public ViewGroup getLayoutView() {
        return new UpdateView(this);
    }

    public String getAppName() {
        try {
            PackageManager packageManager = getPackageManager();
            return packageManager.getApplicationLabel(packageManager.getApplicationInfo(getPackageName(), 0)).toString();
        } catch (NameNotFoundException e) {
            return "";
        }
    }

    private boolean isWriteExternalStorageSet(Context context) {
        return context.checkCallingOrSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == 0;
    }

    private boolean isUnknownSourcesChecked() {
        try {
            if (VERSION.SDK_INT < 17 || VERSION.SDK_INT >= 21) {
                if (Secure.getInt(getContentResolver(), "install_non_market_apps") != 1) {
                    return false;
                }
                return true;
            } else if (Global.getInt(getContentResolver(), "install_non_market_apps") == 1) {
                return true;
            } else {
                return false;
            }
        } catch (SettingNotFoundException e) {
            return true;
        }
    }

    public void onClick(View view) {
        prepareDownload();
        view.setEnabled(false);
    }

    protected void prepareDownload() {
        if (isWriteExternalStorageSet(this.context)) {
            if (isUnknownSourcesChecked()) {
                startDownloadTask();
                return;
            }
            this.error = new c();
            this.error.setMessage("The installation from unknown sources is not enabled. Please check the device settings.");
            runOnUiThread(new Runnable() {
                public void run() {
                    UpdateActivity.this.showDialog(0);
                }
            });
        } else if (VERSION.SDK_INT >= 23) {
            requestPermissions(new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 1);
        } else {
            this.error = new c();
            this.error.setMessage("The permission to access the external storage permission is not set. Please contact the developer.");
            runOnUiThread(new Runnable() {
                public void run() {
                    UpdateActivity.this.showDialog(0);
                }
            });
        }
    }

    protected Dialog onCreateDialog(int i) {
        return onCreateDialog(i, null);
    }

    protected Dialog onCreateDialog(int i, Bundle bundle) {
        switch (i) {
            case 0:
                return new Builder(this).setMessage("An error has occured").setCancelable(false).setTitle("Error").setIcon(17301543).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        UpdateActivity.this.error = null;
                        dialogInterface.cancel();
                    }
                }).create();
            default:
                return null;
        }
    }

    protected void onPrepareDialog(int i, Dialog dialog) {
        switch (i) {
            case 0:
                AlertDialog alertDialog = (AlertDialog) dialog;
                if (this.error != null) {
                    alertDialog.setMessage(this.error.getMessage());
                    return;
                } else {
                    alertDialog.setMessage("An unknown error has occured.");
                    return;
                }
            default:
                return;
        }
    }
}
