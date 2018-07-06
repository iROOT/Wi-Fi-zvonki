package com.mavenir.android.vtow.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.content.o;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.fgmicrotec.mobile.android.fgvoip.FgVoIP;
import com.fgmicrotec.mobile.android.fgvoip.f;
import com.fgmicrotec.mobile.android.fgvoip.f.g;
import com.fgmicrotec.mobile.android.fgvoip.f.h;
import com.mavenir.android.activity.PreferenceMainActivity;
import com.mavenir.android.common.l;
import com.mavenir.android.common.q;
import com.mavenir.android.settings.c.k;
import com.mavenir.android.vtow.activation.ActivationAdapter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ActivationInitialActivity extends ActionBarActivity implements OnClickListener, OnTouchListener, com.mavenir.android.vtow.activation.b.a {
    private Handler a;
    private CheckBox b;
    private TextView c;
    private Button d;
    private Button e;
    private View f;
    private ProgressBar g;
    private TextView h;
    private b i;
    private a j = null;
    private List<Runnable> k = new LinkedList();
    private boolean l = false;
    private Runnable m = new Runnable(this) {
        final /* synthetic */ ActivationInitialActivity a;

        {
            this.a = r1;
        }

        public void run() {
            this.a.startActivity(new Intent(this.a, MainTabActivity.class));
            this.a.finish();
        }
    };
    private Runnable n = new Runnable(this) {
        final /* synthetic */ ActivationInitialActivity a;

        {
            this.a = r1;
        }

        public void run() {
            this.a.g();
        }
    };
    private Runnable o = new Runnable(this) {
        final /* synthetic */ ActivationInitialActivity a;

        {
            this.a = r1;
        }

        public void run() {
            this.a.h();
        }
    };
    private Runnable p = new Runnable(this) {
        final /* synthetic */ ActivationInitialActivity a;

        {
            this.a = r1;
        }

        public void run() {
            this.a.j();
        }
    };
    private Runnable q = new Runnable(this) {
        final /* synthetic */ ActivationInitialActivity a;

        {
            this.a = r1;
        }

        public void run() {
            this.a.n();
        }
    };

    private class a extends BroadcastReceiver {
        final /* synthetic */ ActivationInitialActivity a;

        private a(ActivationInitialActivity activationInitialActivity) {
            this.a = activationInitialActivity;
        }

        /* synthetic */ a(ActivationInitialActivity activationInitialActivity, AnonymousClass1 anonymousClass1) {
            this(activationInitialActivity);
        }

        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                return;
            }
            if (intent.getAction().equals("com.mavenir.android.activation.action_provisioning_cnf")) {
                this.a.a(intent.getExtras());
            } else if (intent.getAction().equals("com.mavenir.android.activation.action_configuration_cnf")) {
                this.a.b(intent.getExtras());
            } else if (intent.getAction().equals("com.mavenir.android.activation.action_restoration_cnf")) {
                this.a.l();
            } else if (intent.getAction().equals("com.mavenir.android.ActionConectivityChange")) {
                this.a.m();
            }
        }
    }

    private enum b {
        INITIAL,
        PERMISSION,
        PROGRESS_PROVISIONING,
        PROGRESS_CONFIGURATION,
        PROGRESS_RESTORATION,
        SUCCESS,
        FAILURE
    }

    private static class c implements Runnable {
        private ActionBarActivity a;
        private com.mavenir.android.fragments.f.a b;
        private int c;
        private String d;

        c(ActionBarActivity actionBarActivity, com.mavenir.android.fragments.f.a aVar, int i, String str) {
            this.a = actionBarActivity;
            this.b = aVar;
            this.c = i;
            this.d = str;
        }

        public void run() {
            FgVoIP.U().a(this.a, this.b, this.c, -1, this.d);
        }
    }

    private void a(Runnable runnable) {
        if (this.l) {
            runnable.run();
        } else {
            this.k.add(runnable);
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.a = new Handler();
        int d = k.d();
        boolean booleanExtra = getIntent().getBooleanExtra("com.mavenir.android.vtow.activity.ActivationInitialActivity.ExtraAutoProvisioning", false);
        boolean booleanExtra2 = getIntent().getBooleanExtra("com.mavenir.android.activation.extra_force_activation", false);
        if (booleanExtra || (booleanExtra2 && d > 0)) {
            q.a("ActivationInitialActivity", "onCreate(): auto provisioning");
            b();
        } else if (d == -1) {
            q.a("ActivationInitialActivity", "onCreate(): vers=-1, starting configuration");
            g();
        } else if (d > 0) {
            q.a("ActivationInitialActivity", "onCreate(): vers>0, starting configuration");
            g();
        } else {
            q.a("ActivationInitialActivity", "onCreate(): vers=-3, starting provisioning");
            d();
        }
        a();
    }

    protected void onResumeFragments() {
        super.onResumeFragments();
        this.l = true;
        for (Runnable post : this.k) {
            this.a.post(post);
        }
        this.k.clear();
        FgVoIP.U().a((Activity) this);
    }

    protected void onPause() {
        this.l = false;
        super.onPause();
    }

    protected void onDestroy() {
        super.onDestroy();
        o.a((Context) this).a(this.j);
    }

    public void onBackPressed() {
        q.a("ActivationInitialActivity", "onBackPressed(): User requested app exit");
        FgVoIP.U().d();
        super.onBackPressed();
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == g.activationActivateButton) {
            q.a("ActivationInitialActivity", "onClick(): user pressed Activate button");
            if (k() && e()) {
                b();
            }
        } else if (id == g.activationRetryButton) {
            q.a("ActivationInitialActivity", "onClick(): user pressed Retry button");
            if (k.d() >= -1) {
                g();
            } else {
                b();
            }
        } else if (id == g.whitelistOkButton) {
            this.a.postDelayed(this.m, 1000);
        }
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (view.getId() == g.activationEngineeringMode) {
            switch (motionEvent.getActionMasked()) {
                case 0:
                    this.a.postDelayed(this.q, 4000);
                    return true;
                case 1:
                case 3:
                case 4:
                    this.a.removeCallbacks(this.q);
                    return true;
            }
        }
        return false;
    }

    public void a(final com.mavenir.android.c.a.b bVar) {
        runOnUiThread(new Runnable(this) {
            final /* synthetic */ ActivationInitialActivity b;

            public void run() {
                q.b("ActivationInitialActivity", "connectionInitializationComplete(): HIPRI state: " + bVar.name());
                int d = k.d();
                if (bVar == com.mavenir.android.c.a.b.CONNECTED) {
                    if (d == -3) {
                        this.b.f();
                    }
                } else if (bVar != com.mavenir.android.c.a.b.CONNECTING && bVar != com.mavenir.android.c.a.b.DISCONNECTING && d < -1) {
                    if (l.a(this.b).z()) {
                        this.b.d();
                        if (!l.a(this.b).z()) {
                            q.c("ActivationInitialActivity", "handleActivation(): mobile data disconnected, cannot proceed...");
                            this.b.a(new c(this.b, com.mavenir.android.fragments.f.a.SYSTEM, com.mavenir.android.fragments.f.b.ENABLE_DATA.ordinal(), null));
                            return;
                        }
                        return;
                    }
                    q.a("ActivationInitialActivity", "connectionInitializationComplete(): starting new provisioning");
                    this.b.f();
                }
            }
        });
    }

    private void a() {
        this.j = new a();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.mavenir.android.activation.action_provisioning_cnf");
        intentFilter.addAction("com.mavenir.android.activation.action_configuration_cnf");
        intentFilter.addAction("com.mavenir.android.activation.action_restoration_cnf");
        intentFilter.addAction("com.mavenir.android.ActionConectivityChange");
        o.a((Context) this).a(this.j, intentFilter);
    }

    private void b() {
        if (!FgVoIP.U().al()) {
            q.c("ActivationInitialActivity", "startActivation(): SIM not present, cannot proceed...");
            FgVoIP.U().a((FragmentActivity) this, com.mavenir.android.fragments.f.a.H3G_ACTIVATION, 0, 0, getString(f.k.exception_sim_no_sim));
        } else if (l.a((Context) this).D()) {
            q.c("ActivationInitialActivity", "startActivation(): airplane mode is on, cannot proceed...");
            FgVoIP.U().a((FragmentActivity) this, com.mavenir.android.fragments.f.a.SYSTEM, com.mavenir.android.fragments.f.b.DISABLE_AIRPLANE_MODE.ordinal());
        } else {
            c();
        }
    }

    private void c() {
        a(b.PROGRESS_PROVISIONING);
        q.a("ActivationInitialActivity", "setupInitializationScreen(): starting HIPRI initialization");
        this.h.setText(f.k.activation_progress_initializing);
        com.mavenir.android.common.t.a.a(this.h.getText().toString());
        CharSequence u = com.mavenir.android.settings.c.q.u();
        CharSequence v = com.mavenir.android.settings.c.q.v();
        List arrayList = new ArrayList();
        if (!TextUtils.isEmpty(u)) {
            arrayList.add(u);
        }
        if (!TextUtils.isEmpty(v)) {
            arrayList.add(v);
        }
        com.mavenir.android.vtow.activation.b.a((Context) this).a((com.mavenir.android.vtow.activation.b.a) this);
    }

    private void d() {
        super.setContentView(h.activation_initial_activity);
        this.b = (CheckBox) findViewById(g.activationTCCheckBox);
        this.c = (TextView) findViewById(g.activationTCTextview);
        this.d = (Button) findViewById(g.activationActivateButton);
        this.d.setOnClickListener(this);
        this.c.setMovementMethod(LinkMovementMethod.getInstance());
        this.f = findViewById(g.activationEngineeringMode);
        this.f.setOnTouchListener(this);
        this.i = b.INITIAL;
        e();
    }

    private boolean e() {
        if (!FgVoIP.U().av() || com.mavenir.android.settings.c.a() != null) {
            return true;
        }
        FgVoIP.U().a((FragmentActivity) this, com.mavenir.android.fragments.f.a.SYSTEM, com.mavenir.android.fragments.f.b.ENABLE_PERMISSION.ordinal(), -10, getString(f.k.exception_permission_message_phone));
        return false;
    }

    private void a(b bVar) {
        super.setContentView(h.activation_progress_activity);
        this.g = (ProgressBar) findViewById(g.activationProgressBar);
        this.e = (Button) findViewById(g.activationRetryButton);
        this.e.setOnClickListener(this);
        this.e.setText(f.k.activation_initial_reactivate_button);
        this.h = (TextView) findViewById(g.activationProgressTextView);
        this.f = findViewById(g.activationEngineeringMode);
        this.f.setOnTouchListener(this);
        this.i = bVar;
    }

    private void f() {
        a(b.PROGRESS_PROVISIONING);
        this.h.setText(f.k.activation_progress_provisioning);
        com.mavenir.android.common.t.a.a(this.h.getText().toString());
        String stringExtra = getIntent().getStringExtra("com.mavenir.android.vtow.activity.ActivationInitialActivity.ExtraImsi");
        String stringExtra2 = getIntent().getStringExtra("com.mavenir.android.vtow.activity.ActivationInitialActivity.ExtraMsisdn");
        String[] stringArrayExtra = getIntent().getStringArrayExtra("com.mavenir.android.vtow.activity.ActivationInitialActivity.ExtraFqdns");
        if (!TextUtils.isEmpty(stringExtra) && !TextUtils.isEmpty(stringExtra2)) {
            q.a("ActivationInitialActivity", "setupProvisioningScreen(): starting extended provisioning");
            com.mavenir.android.vtow.activation.b.a((Context) this).a(stringExtra, stringExtra2, stringArrayExtra);
        } else if (FgVoIP.U().H()) {
            q.a("ActivationInitialActivity", "setupProvisioningScreen(): starting initial provisioning");
            com.mavenir.android.vtow.activation.b.a((Context) this).b(100);
        } else {
            q.a("ActivationInitialActivity", "setupProvisioningScreen(): starting initial provisioning (non LTE)");
            com.mavenir.android.vtow.activation.b.a((Context) this).b(0);
        }
    }

    private void g() {
        a(b.PROGRESS_CONFIGURATION);
        this.h.setText(f.k.activation_progress_configuring);
        com.mavenir.android.common.t.a.a(this.h.getText().toString());
        if (FgVoIP.U().H()) {
            q.a("ActivationInitialActivity", "setupConfigurationScreen(): starting initial configuration");
            com.mavenir.android.vtow.activation.b.a((Context) this).a((int) ActivationAdapter.OP_CONFIGURATION_INITIAL, true);
            return;
        }
        q.a("ActivationInitialActivity", "setupConfigurationScreen(): starting initial configuration (non LTE)");
        com.mavenir.android.vtow.activation.b.a((Context) this).a(0, true);
    }

    private void h() {
        q.a("ActivationInitialActivity", "setupRestorationScreen(): starting restoration");
        a(b.PROGRESS_RESTORATION);
        this.h.setText(f.k.activation_progress_restoring);
        com.mavenir.android.common.t.a.a(this.h.getText().toString());
        FgVoIP.U().a((Context) this, "com.mavenir.android.action_restore_data");
    }

    private void i() {
        a(b.PROGRESS_PROVISIONING);
        q.b("ActivationInitialActivity", "setupProvisioningSuccessScreen(): proceeding to configuration...");
        this.h.setText(f.k.activation_provisioning_success);
        com.mavenir.android.common.t.a.a(this.h.getText().toString());
        this.a.removeCallbacks(this.n);
        this.a.postDelayed(this.n, 1000);
    }

    private void a(int i, String str) {
        String str2;
        a(b.PROGRESS_PROVISIONING);
        if (str != null) {
            str2 = str;
        } else {
            str2 = com.mavenir.android.vtow.activation.ActivationAdapter.b.values()[i].toString();
        }
        q.b("ActivationInitialActivity", "setupProvisioningFailedScreen(): error(" + i + "): " + str2);
        this.h.setText(f.k.activation_provisioning_failed);
        com.mavenir.android.common.t.a.a(this.h.getText().toString());
        this.i = b.FAILURE;
        a(new c(this, com.mavenir.android.fragments.f.a.H3G_ACTIVATION, i, str));
        this.a.removeCallbacks(this.p);
        this.a.postDelayed(this.p, 1000);
    }

    private void b(int i, String str) {
        a(b.PROGRESS_CONFIGURATION);
        q.b("ActivationInitialActivity", "setupConfigurationSuccessScreen(): proceeding to restoration...");
        this.h.setText(f.k.activation_configuration_success);
        com.mavenir.android.common.t.a.a(this.h.getText().toString());
        this.i = b.SUCCESS;
        if (!TextUtils.isEmpty(str)) {
            a(new c(this, com.mavenir.android.fragments.f.a.H3G_ACTIVATION, i, str));
        }
        this.a.removeCallbacks(this.o);
        this.a.postDelayed(this.o, 1000);
    }

    private void c(int i, String str) {
        String str2;
        a(b.PROGRESS_CONFIGURATION);
        if (i >= ActivationAdapter.REASON_NEW_CONFIG_DATA) {
            str2 = str;
        } else if (str != null) {
            str2 = str;
        } else {
            str2 = com.mavenir.android.vtow.activation.ActivationAdapter.b.values()[i].toString();
        }
        q.b("ActivationInitialActivity", "setupConfigurationFailedScreen(): error(" + i + "): " + str2);
        this.h.setText(f.k.activation_configuration_failed);
        com.mavenir.android.common.t.a.a(this.h.getText().toString());
        this.i = b.FAILURE;
        if (str2 != null) {
            a(new c(this, com.mavenir.android.fragments.f.a.H3G_ACTIVATION, i, str));
        }
        this.a.removeCallbacks(this.p);
        this.a.postDelayed(this.p, 1000);
    }

    private void j() {
        if (this.i == b.FAILURE) {
            this.g.setVisibility(8);
            this.e.setVisibility(0);
        }
    }

    private boolean k() {
        if (this.b.isChecked()) {
            return true;
        }
        Toast.makeText(this, getString(f.k.activation_initial_terms_accept), 0).show();
        return false;
    }

    private void a(Bundle bundle) {
        if (bundle != null) {
            int d = k.d();
            int i = bundle.getInt("com.mavenir.android.activation.extra_error_code", -1);
            String string = bundle.getString("com.mavenir.android.activation.error_message");
            if (i == com.mavenir.android.vtow.activation.ActivationAdapter.b.PROVISIONING_OK.ordinal() || d == -1) {
                i();
                return;
            }
            com.mavenir.android.vtow.activation.b.a((Context) this).g();
            a(i, string);
        }
    }

    private void b(Bundle bundle) {
        if (bundle != null) {
            int i = bundle.getInt("com.mavenir.android.activation.extra_error_code", -1);
            if (bundle.getInt("com.mavenir.android.activation.extra_op_code", 0) == 0) {
                String string = bundle.getString("com.mavenir.android.activation.error_message");
                if (i == com.mavenir.android.vtow.activation.ActivationAdapter.b.PROVISIONING_OK.ordinal()) {
                    b(i, "");
                } else {
                    c(i, string);
                }
            } else {
                c(bundle);
            }
        }
        com.mavenir.android.vtow.activation.b.a((Context) this).g();
    }

    private void c(Bundle bundle) {
        int i = bundle.getInt("com.mavenir.android.activation.extra_error_code", -1);
        int i2 = bundle.getInt("com.mavenir.android.activation.extra_reason_code", -1);
        String string = bundle.getString("com.mavenir.android.activation.error_message");
        switch (i2) {
            case ActivationAdapter.REASON_NEW_CONFIG_DATA /*300*/:
            case ActivationAdapter.REASON_APP_USER_FRIENDLY_MESSAGE /*305*/:
            case ActivationAdapter.REASON_USER_PROVISIONING_ERROR /*306*/:
            case ActivationAdapter.REASON_NETWORK_PROVISIONING_ERROR /*307*/:
                b(i, string);
                return;
            case ActivationAdapter.REASON_DEVICE_BLOCKED /*302*/:
            case ActivationAdapter.REASON_DEVICE_BLOCKED_ROOTED /*303*/:
            case ActivationAdapter.REASON_APP_VERSION_BLOCKED /*304*/:
                c(i2, string);
                return;
            case ActivationAdapter.REASON_MALFORMED_PARAMS /*308*/:
            case ActivationAdapter.REASON_INVALID_URL_REQ /*309*/:
            case ActivationAdapter.REASON_UNKNOWN_CMS_ERROR /*310*/:
            case ActivationAdapter.REASON_MSISDN_HASH_MISMATCH /*314*/:
                c(i2, string);
                return;
            default:
                q.d("ActivationInitialActivity", "configurationLteCnf(): wrong reason code: " + i2);
                if (k.d() > 0) {
                    b(i2, getString(f.k.activation_exception_lte_unkown));
                    return;
                } else {
                    c(i2, getString(f.k.activation_exception_lte_unkown));
                    return;
                }
        }
    }

    private void l() {
        q.b("ActivationInitialActivity", "restorationCnf(): restoration complete, starting app...");
        this.a.postDelayed(this.m, 1000);
    }

    private void m() {
        boolean z = l.a((Context) this).z();
        boolean C = l.a((Context) this).C();
        if (!z && !C) {
            if (this.i == b.PROGRESS_PROVISIONING) {
                a(com.mavenir.android.vtow.activation.ActivationAdapter.b.PROVISIONING_NO_RESPONSE.ordinal(), null);
            } else if (this.i == b.PROGRESS_CONFIGURATION) {
                c(com.mavenir.android.vtow.activation.ActivationAdapter.b.PROVISIONING_NO_RESPONSE.ordinal(), null);
            }
        }
    }

    private void n() {
        final View editText = new EditText(this);
        editText.setInputType(3);
        editText.setSingleLine();
        AlertDialog create = new Builder(this).setTitle(f.k.activation_enter_engineering_code).setView(editText).setPositiveButton(f.k.dialog_ok, new DialogInterface.OnClickListener(this) {
            final /* synthetic */ ActivationInitialActivity b;

            public void onClick(DialogInterface dialogInterface, int i) {
                this.b.a(editText.getText().toString());
            }
        }).setNegativeButton(f.k.dialog_cancel, new DialogInterface.OnClickListener(this) {
            final /* synthetic */ ActivationInitialActivity a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
            }
        }).create();
        create.show();
        create.getWindow().clearFlags(131080);
        create.getWindow().setSoftInputMode(5);
    }

    private void a(String str) {
        FgVoIP U = FgVoIP.U();
        if (U.d(str)) {
            U.a(true);
            TaskStackBuilder.create(this).addNextIntentWithParentStack(new Intent(this, PreferenceMainActivity.class)).startActivities();
            finish();
        }
    }
}
