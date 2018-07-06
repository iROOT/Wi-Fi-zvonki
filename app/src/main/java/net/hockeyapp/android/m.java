package net.hockeyapp.android;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import net.hockeyapp.android.d.e;
import net.hockeyapp.android.d.f;
import net.hockeyapp.android.e.a;
import net.hockeyapp.android.e.j;
import net.hockeyapp.android.views.UpdateView;
import org.json.JSONArray;
import org.json.JSONException;

public class m extends DialogFragment implements OnClickListener, n {
    private e downloadTask;
    private String urlString;
    private j versionHelper;
    private JSONArray versionInfo;

    public static m newInstance(JSONArray jSONArray, String str) {
        Bundle bundle = new Bundle();
        bundle.putString("url", str);
        bundle.putString("versionInfo", jSONArray.toString());
        m mVar = new m();
        mVar.setArguments(bundle);
        return mVar;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        try {
            this.urlString = getArguments().getString("url");
            this.versionInfo = new JSONArray(getArguments().getString("versionInfo"));
            setStyle(1, 16973939);
        } catch (JSONException e) {
            dismiss();
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View layoutView = getLayoutView();
        this.versionHelper = new j(getActivity(), this.versionInfo.toString(), this);
        ((TextView) layoutView.findViewById(UpdateView.NAME_LABEL_ID)).setText(getAppName());
        final TextView textView = (TextView) layoutView.findViewById(4099);
        final String str = "Version " + this.versionHelper.getVersionString();
        final String fileDateString = this.versionHelper.getFileDateString();
        String str2 = "Unknown size";
        if (this.versionHelper.getFileSizeBytes() >= 0) {
            str2 = String.format("%.2f", new Object[]{Float.valueOf(((float) r4) / 1048576.0f)}) + " MB";
        } else {
            a.execute(new f(getActivity(), this.urlString, new net.hockeyapp.android.b.a() {
                public void downloadSuccessful(e eVar) {
                    if (eVar instanceof f) {
                        long size = ((f) eVar).getSize();
                        textView.setText(str + "\n" + fileDateString + " - " + (String.format("%.2f", new Object[]{Float.valueOf(((float) size) / 1048576.0f)}) + " MB"));
                    }
                }
            }));
        }
        textView.setText(str + "\n" + fileDateString + " - " + str2);
        ((Button) layoutView.findViewById(UpdateView.UPDATE_BUTTON_ID)).setOnClickListener(this);
        WebView webView = (WebView) layoutView.findViewById(UpdateView.WEB_VIEW_ID);
        webView.clearCache(true);
        webView.destroyDrawingCache();
        webView.loadDataWithBaseURL(a.BASE_URL, this.versionHelper.getReleaseNotes(false), "text/html", "utf-8", null);
        return layoutView;
    }

    public void onClick(View view) {
        prepareDownload();
    }

    public void prepareDownload() {
        if (VERSION.SDK_INT < 23 || getActivity().checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == 0) {
            startDownloadTask(getActivity());
            dismiss();
            return;
        }
        requestPermissions(new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 1);
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (strArr.length != 0 && iArr.length != 0 && i == 1) {
            if (iArr[0] == 0) {
                startDownloadTask(getActivity());
                return;
            }
            Log.w("HockeyApp", "User denied write permission, can't continue with updater task.");
            p lastListener = o.getLastListener();
            if (lastListener != null) {
                lastListener.onUpdatePermissionsNotGranted();
            } else {
                new Builder(getActivity()).setTitle(k.get(k.PERMISSION_UPDATE_TITLE_ID)).setMessage(k.get(k.PERMISSION_UPDATE_MESSAGE_ID)).setNegativeButton(k.get(k.PERMISSION_DIALOG_NEGATIVE_BUTTON_ID), null).setPositiveButton(k.get(k.PERMISSION_DIALOG_POSITIVE_BUTTON_ID), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        this.prepareDownload();
                    }
                }).create().show();
            }
        }
    }

    private void startDownloadTask(final Activity activity) {
        this.downloadTask = new e(activity, this.urlString, new net.hockeyapp.android.b.a() {
            public void downloadFailed(e eVar, Boolean bool) {
                if (bool.booleanValue()) {
                    m.this.startDownloadTask(activity);
                }
            }

            public void downloadSuccessful(e eVar) {
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

    public int getCurrentVersionCode() {
        int i = -1;
        try {
            return getActivity().getPackageManager().getPackageInfo(getActivity().getPackageName(), NotificationCompat.FLAG_HIGH_PRIORITY).versionCode;
        } catch (NameNotFoundException e) {
            return i;
        } catch (NullPointerException e2) {
            return i;
        }
    }

    public String getAppName() {
        Activity activity = getActivity();
        try {
            PackageManager packageManager = activity.getPackageManager();
            return packageManager.getApplicationLabel(packageManager.getApplicationInfo(activity.getPackageName(), 0)).toString();
        } catch (NameNotFoundException e) {
            return "";
        }
    }

    public View getLayoutView() {
        return new UpdateView(getActivity(), false, true);
    }
}
