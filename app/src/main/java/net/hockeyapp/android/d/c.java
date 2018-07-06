package net.hockeyapp.android.d;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;
import java.lang.ref.WeakReference;
import net.hockeyapp.android.UpdateActivity;
import net.hockeyapp.android.e.h;
import net.hockeyapp.android.e.i;
import net.hockeyapp.android.k;
import net.hockeyapp.android.m;
import net.hockeyapp.android.p;
import org.json.JSONArray;

public class c extends b {
    private Activity activity = null;
    private AlertDialog dialog = null;
    protected boolean isDialogRequired = false;

    public c(WeakReference<Activity> weakReference, String str, String str2, p pVar, boolean z) {
        super(weakReference, str, str2, pVar);
        if (weakReference != null) {
            this.activity = (Activity) weakReference.get();
        }
        this.isDialogRequired = z;
    }

    public void detach() {
        super.detach();
        this.activity = null;
        if (this.dialog != null) {
            this.dialog.dismiss();
            this.dialog = null;
        }
    }

    protected void onPostExecute(JSONArray jSONArray) {
        super.onPostExecute(jSONArray);
        if (jSONArray != null && this.isDialogRequired) {
            showDialog(jSONArray);
        }
    }

    @TargetApi(11)
    private void showDialog(final JSONArray jSONArray) {
        if (getCachingEnabled()) {
            i.setVersionInfo(this.activity, jSONArray.toString());
        }
        if (this.activity != null && !this.activity.isFinishing()) {
            Builder builder = new Builder(this.activity);
            builder.setTitle(k.get(this.listener, k.UPDATE_DIALOG_TITLE_ID));
            if (this.mandatory.booleanValue()) {
                Toast.makeText(this.activity, k.get(this.listener, 512), 1).show();
                startUpdateIntent(jSONArray, Boolean.valueOf(true));
                return;
            }
            builder.setMessage(k.get(this.listener, k.UPDATE_DIALOG_MESSAGE_ID));
            builder.setNegativeButton(k.get(this.listener, k.UPDATE_DIALOG_NEGATIVE_BUTTON_ID), new OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    c.this.cleanUp();
                    if (c.this.listener != null) {
                        c.this.listener.onCancel();
                    }
                }
            });
            builder.setPositiveButton(k.get(this.listener, k.UPDATE_DIALOG_POSITIVE_BUTTON_ID), new OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (c.this.getCachingEnabled()) {
                        i.setVersionInfo(c.this.activity, "[]");
                    }
                    WeakReference weakReference = new WeakReference(c.this.activity);
                    if (h.fragmentsSupported().booleanValue() && h.runsOnTablet(weakReference).booleanValue()) {
                        c.this.showUpdateFragment(jSONArray);
                    } else {
                        c.this.startUpdateIntent(jSONArray, Boolean.valueOf(false));
                    }
                }
            });
            this.dialog = builder.create();
            this.dialog.show();
        }
    }

    @TargetApi(11)
    private void showUpdateFragment(JSONArray jSONArray) {
        if (this.activity != null) {
            FragmentTransaction beginTransaction = this.activity.getFragmentManager().beginTransaction();
            beginTransaction.setTransition(4097);
            Fragment findFragmentByTag = this.activity.getFragmentManager().findFragmentByTag("hockey_update_dialog");
            if (findFragmentByTag != null) {
                beginTransaction.remove(findFragmentByTag);
            }
            beginTransaction.addToBackStack(null);
            Class cls = m.class;
            if (this.listener != null) {
                cls = this.listener.getUpdateFragmentClass();
            }
            try {
                ((DialogFragment) cls.getMethod("newInstance", new Class[]{JSONArray.class, String.class}).invoke(null, new Object[]{jSONArray, getURLString("apk")})).show(beginTransaction, "hockey_update_dialog");
            } catch (Exception e) {
                Log.d("HockeyApp", "An exception happened while showing the update fragment:");
                e.printStackTrace();
                Log.d("HockeyApp", "Showing update activity instead.");
                startUpdateIntent(jSONArray, Boolean.valueOf(false));
            }
        }
    }

    @TargetApi(11)
    private void startUpdateIntent(JSONArray jSONArray, Boolean bool) {
        Class cls = null;
        if (this.listener != null) {
            cls = this.listener.getUpdateActivityClass();
        }
        if (cls == null) {
            cls = UpdateActivity.class;
        }
        if (this.activity != null) {
            Intent intent = new Intent();
            intent.setClass(this.activity, cls);
            intent.putExtra("json", jSONArray.toString());
            intent.putExtra("url", getURLString("apk"));
            this.activity.startActivity(intent);
            if (bool.booleanValue()) {
                this.activity.finish();
            }
        }
        cleanUp();
    }

    protected void cleanUp() {
        super.cleanUp();
        this.activity = null;
        this.dialog = null;
    }
}
