package com.mavenir.android.vtow.activity;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import com.fgmicrotec.mobile.android.fgvoip.FgVoIP;
import com.fgmicrotec.mobile.android.fgvoip.f.h;
import com.fgmicrotec.mobile.android.fgvoip.f.k;
import com.fgmicrotec.mobile.android.fgvoipcommon.CallService;
import com.mavenir.android.applog.AppLogAdapter;
import com.mavenir.android.applog.AppLogAdapter.d;
import com.mavenir.android.common.l;
import com.mavenir.android.common.q;
import com.mavenir.android.settings.c;
import com.mavenir.android.vtow.activation.b;

public class SplashScreenActivity extends FragmentActivity {
    private boolean a = false;
    private Handler b;
    private Runnable c = new Runnable(this) {
        final /* synthetic */ SplashScreenActivity a;

        {
            this.a = r1;
        }

        public void run() {
            FgVoIP U = FgVoIP.U();
            if (U.ai() && (this.a.a() || this.a.b())) {
                this.a.a = true;
                return;
            }
            if (!U.ay() || U.ah()) {
                if (U.o()) {
                    U.a(true);
                }
                if (U.ah()) {
                    q.a("SplashScreenActivity", "onCreate() - run(): We're in admin mode, show main screen.");
                } else {
                    q.a("SplashScreenActivity", "onCreate() - run(): We're already activated, show main screen.");
                }
                this.a.c();
            } else {
                this.a.startActivity(U.Y());
            }
            this.a.finish();
        }
    };

    public static class a extends DialogFragment {
        public static void a(FragmentManager fragmentManager) {
            new a().show(fragmentManager, "DeviceRootedDialogFragment");
        }

        public Dialog onCreateDialog(Bundle bundle) {
            Builder builder = new Builder(getActivity());
            builder.setTitle(k.dialog_error_title).setMessage(k.dialog_device_is_rooted).setPositiveButton(k.dialog_ok, null);
            return builder.create();
        }

        public void onDismiss(DialogInterface dialogInterface) {
            super.onDismiss(dialogInterface);
            Activity activity = getActivity();
            if (activity != null) {
                activity.finish();
            }
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.setContentView(h.splash_activity);
        this.b = new Handler();
    }

    protected void onStart() {
        super.onStart();
        this.a = false;
        this.b.post(new Runnable(this) {
            final /* synthetic */ SplashScreenActivity a;

            {
                this.a = r1;
            }

            public void run() {
                int i = 0;
                if (FgVoIP.U().m() && FgVoIP.U().ag()) {
                    q.d("SplashScreenActivity", "onStart(): Not allowed to continue");
                    a.a(this.a.getSupportFragmentManager());
                    return;
                }
                int i2 = 4000;
                if (this.a.getIntent() != null && this.a.getIntent().getAction() == "com.mavenir.action.LAUNCH_MAIN_TAB") {
                    i2 = 0;
                }
                if (!CallService.k() || CallService.l()) {
                    FgVoIP.U().c();
                    i = i2;
                }
                this.a.b.postDelayed(this.a.c, (long) i);
            }
        });
    }

    protected void onResume() {
        super.onResume();
        if (this.a) {
            finish();
        }
    }

    protected boolean a() {
        FgVoIP U = FgVoIP.U();
        if (l.a((Context) this).D()) {
            q.b("SplashScreenActivity", "handleSimError(): isAirPlaneMode on - skipping sim checks");
            return false;
        } else if (U.n() && !U.al()) {
            q.b("SplashScreenActivity", "handleSimError(): SIM removed, deactivating account");
            if (U.at()) {
                q.a("SplashScreenActivity", "handleSimError(): logging out due to SIM removal");
                U.a("com.fgmicrotec.mobile.android.voip.LogoutFromServerReq");
            }
            FgVoIP.U().a((Context) this, "com.mavenir.android.action_backup_no_sim");
            return true;
        } else if (!U.ax() || !U.n() || !U.ak()) {
            return false;
        } else {
            q.b("SplashScreenActivity", "handleSimError(): SIM changed, deactivating account");
            if (U.at()) {
                q.a("SplashScreenActivity", "handleSimError(): logging out due to SIM change");
                U.a("com.fgmicrotec.mobile.android.voip.LogoutFromServerReq");
            }
            FgVoIP.U().a((Context) this, "com.mavenir.android.action_backup_sim_swapped");
            return true;
        }
    }

    private boolean b() {
        if (c.k.d() <= -3 || b.a((Context) this).a()) {
            return false;
        }
        q.c("SplashScreenActivity", "handleWrongParams(): Wrong params, deactivating account");
        FgVoIP.U().a((Context) this, "com.mavenir.android.action_backup_deactivated", com.mavenir.android.vtow.activation.ActivationAdapter.a.FGVOIPCPROXY_DEACTIVATION_MUST_REPROVISION_CREDENTIALS.ordinal());
        com.mavenir.android.applog.a.a((Context) this).a(AppLogAdapter.b.FGAPPLOG_EVENT_GROUP_PROVISIONING_ERROR, d.FGAPPLOG_EVENT_TYPE_DEACTIVATED, AppLogAdapter.c.FGAPPLOG_EVENT_REASON_OTHER, getString(k.activation_provisioning_reason_wrong_parameters));
        return true;
    }

    private void c() {
        Intent intent = new Intent(this, MainTabActivity.class);
        if (!(getIntent() == null || getIntent().getExtras() == null)) {
            intent.putExtras(getIntent().getExtras());
        }
        startActivity(intent);
    }
}
