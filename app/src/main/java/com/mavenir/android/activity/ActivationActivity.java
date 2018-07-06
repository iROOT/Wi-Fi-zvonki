package com.mavenir.android.activity;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.o;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.fgmicrotec.mobile.android.fgvoip.FgVoIP;
import com.fgmicrotec.mobile.android.fgvoip.HelpTopicDetailActivity;
import com.fgmicrotec.mobile.android.fgvoip.b.b;
import com.fgmicrotec.mobile.android.fgvoip.f.g;
import com.fgmicrotec.mobile.android.fgvoip.f.h;
import com.fgmicrotec.mobile.android.fgvoip.f.k;
import com.fgmicrotec.mobile.android.fgvoipcommon.CallService;
import com.mavenir.android.common.l;
import com.mavenir.android.common.q;
import com.mavenir.android.common.t.f;
import com.mavenir.android.settings.c;

public class ActivationActivity extends ActionBarActivity implements OnClickListener, OnItemClickListener {
    private a a;
    private ListView b;
    private Button c;
    private ProgressBar d;
    private TextView e;
    private TextView f;
    private boolean g = false;

    private class a extends BroadcastReceiver {
        final /* synthetic */ ActivationActivity a;

        private a(ActivationActivity activationActivity) {
            this.a = activationActivity;
        }

        /* synthetic */ a(ActivationActivity activationActivity, AnonymousClass1 anonymousClass1) {
            this(activationActivity);
        }

        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                String action = intent.getAction();
                q.a("ActivationActivity", "onReceive(): action: " + action);
                if ("ActivationActions.ActionProvisioningRequestPin".equals(action)) {
                    this.a.a(intent.getExtras());
                } else if ("ActivationActions.ActionProvisioningSuccess".equals(action)) {
                    this.a.b(intent.getExtras());
                } else if ("ActivationActions.ActionProvisioningFailed".equals(action)) {
                    this.a.c(intent.getExtras());
                }
            }
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        FgVoIP.U().aD();
        a();
        boolean z = false;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            z = extras.getBoolean("com.mavenir.android.activity.ActivationActivity.ExtraAutoProvisioning");
        }
        if (z) {
            d();
        } else {
            b();
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        o.a((Context) this).a(this.a);
    }

    private void a() {
        this.a = new a();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("ActivationActions.ActionRefreshAfterProvisioning");
        intentFilter.addAction("ActivationActions.ActionProvisioningSuccess");
        intentFilter.addAction("ActivationActions.ActionProvisioningFailed");
        intentFilter.addAction("ActivationActions.ActionProvisioningRequestPin");
        o.a((Context) this).a(this.a, intentFilter);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == g.activationRetryButton) {
            i();
        } else if (id == g.whitelistOkButton) {
            j();
        }
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        switch (i) {
            case 0:
                c();
                return;
            case 1:
                d();
                return;
            case 2:
                a(b.ACTIVATION.ordinal());
                return;
            case 3:
                e();
                return;
            default:
                return;
        }
    }

    private void b() {
        super.setContentView(h.activation_initial_rcs_activity);
        this.b = (ListView) findViewById(g.activationListView);
        if (this.b != null) {
            this.b.setOnItemClickListener(this);
        }
    }

    private void c() {
        if (g()) {
            CharSequence o = l.a((Context) this).o();
            final View editText = new EditText(this);
            editText.setInputType(3);
            editText.setHint(k.activation_number_hint);
            editText.setText(o);
            Builder builder = new Builder(this);
            builder.setTitle(k.activation_enter_number_title);
            builder.setMessage(k.activation_enter_number_message);
            builder.setView(editText);
            builder.setPositiveButton(k.dialog_ok, new DialogInterface.OnClickListener(this) {
                final /* synthetic */ ActivationActivity b;

                public void onClick(DialogInterface dialogInterface, int i) {
                    String obj = editText.getText().toString();
                    if (FgVoIP.U().d(obj)) {
                        c.k.a(1000);
                        this.b.j();
                        return;
                    }
                    this.b.a(obj);
                }
            });
            builder.setNegativeButton(k.dialog_cancel, new DialogInterface.OnClickListener(this) {
                final /* synthetic */ ActivationActivity a;

                {
                    this.a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                }
            });
            builder.show();
        }
    }

    private void a(Bundle bundle) {
        if (g()) {
            String f = f.f(bundle.getString("ActivationExtras.ExtraPrivateURI"));
            final String string = bundle.getString("ActivationExtras.ExtraTAN");
            CharSequence format = String.format(getString(k.activation_enter_pin_message), new Object[]{f});
            final View editText = new EditText(this);
            editText.setInputType(2);
            editText.setHint(k.activation_pin_hint);
            editText.getEditableText().clear();
            Builder builder = new Builder(this);
            builder.setTitle(k.activation_enter_pin_message);
            builder.setMessage(format);
            builder.setView(editText);
            builder.setPositiveButton(k.dialog_ok, null);
            builder.setNegativeButton(k.dialog_cancel, new DialogInterface.OnClickListener(this) {
                final /* synthetic */ ActivationActivity a;

                {
                    this.a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    this.a.b();
                }
            });
            final AlertDialog create = builder.create();
            create.getButton(-1).setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ ActivationActivity d;

                public void onClick(View view) {
                    String trim = editText.getText().toString().trim();
                    if (trim.length() != 0) {
                        this.d.a(trim, string);
                        create.dismiss();
                    }
                }
            });
        }
    }

    private void a(String str) {
        Intent intent = new Intent(this, CallService.class);
        intent.setAction("ActivationActions.ActionStartInitialProvisioning");
        intent.putExtra("ActivationExtras.ExtraProvisioningNumber", str);
        startService(intent);
        f();
    }

    private void d() {
        if (g()) {
            Intent intent = new Intent(this, CallService.class);
            intent.setAction("ActivationActions.ActionStartInitialProvisioning");
            startService(intent);
            f();
        }
    }

    private void a(String str, String str2) {
        Intent intent = new Intent(this, CallService.class);
        intent.setAction("InternalIntents.ProvisioningAuthResponse");
        intent.putExtra("ActivationExtras.ExtraProvisioningTAN", str2);
        intent.putExtra("ActivationExtras.ExtraProvisioningPIN", str);
        startService(intent);
    }

    private void a(int i) {
        Intent intent = new Intent(this, HelpTopicDetailActivity.class);
        intent.putExtra("topic_id", b.ACTIVATION.ordinal());
        startActivity(intent);
    }

    private void e() {
        q.b("ActivationActivity", "exitApplication() - user called exit from activation");
        FgVoIP.U().a();
        finish();
    }

    private void f() {
        super.setContentView(h.activation_progress_rcs_activity);
        this.d = (ProgressBar) findViewById(g.activationProgressBar);
        this.c = (Button) findViewById(g.activationRetryButton);
        this.e = (TextView) findViewById(g.activationProgressTextView);
        this.f = (TextView) findViewById(g.activationErrorTextView);
        if (this.c != null) {
            this.c.setOnClickListener(this);
            this.c.setText(k.activation_initial_reactivate_button);
        }
        if (this.e != null) {
            this.e.setText(k.activation_progress_activating);
        }
        if (this.f != null) {
            this.f.setVisibility(8);
        }
    }

    private void b(Bundle bundle) {
        if (this.e != null) {
            this.e.setText(k.activation_provisioning_success);
        }
        if (this.d != null) {
            this.d.setVisibility(8);
        }
        if (this.f != null) {
            this.f.setVisibility(0);
        }
        if (this.c != null) {
            this.c.setVisibility(0);
            this.c.setText(k.dialog_ok);
        }
        int i = bundle.getInt("ActivationExtras.ExtraVersion", 0);
        String f = f.f(bundle.getString("ActivationExtras.ExtraPublicURI"));
        CharSequence string = bundle.getString("ActivationExtras.ExtraError");
        String string2 = bundle.getString("ActivationExtras.ExtraPin");
        if (i == 0) {
            c(bundle);
            return;
        }
        this.g = true;
        if (this.f == null) {
            return;
        }
        if (string == null) {
            CharSequence format;
            if (string2 == null) {
                format = String.format(getString(k.activation_account_number), new Object[]{f});
            } else {
                format = String.format(getString(k.activation_account_number_pin), new Object[]{f, string2});
            }
            this.f.setText(format);
            return;
        }
        this.f.setText(string);
    }

    private void c(Bundle bundle) {
        if (this.e != null) {
            this.e.setText(k.activation_provisioning_failed);
        }
        if (this.d != null) {
            this.d.setVisibility(8);
        }
        if (this.f != null) {
            this.f.setVisibility(0);
        }
        if (this.c != null) {
            this.c.setVisibility(0);
            this.c.setText(k.activation_initial_retry_button);
        }
        CharSequence string = bundle.getString("ActivationExtras.ExtraError");
        this.g = false;
        if (string == null) {
            string = getString(k.activation_account_not_valid_title);
        }
        if (this.f != null) {
            this.f.setText(string);
        }
    }

    private boolean g() {
        if (l.a((Context) this).D()) {
            q.c("ActivationActivity", "handleActivation(): airplane mode is on, cannot proceed...");
            h();
            return false;
        } else if (FgVoIP.U().an() || !FgVoIP.U().ai() || l.a((Context) this).C()) {
            return true;
        } else {
            q.c("ActivationActivity", "handleActivation(): wifi not connected, cannot proceed...");
            a(true);
            return false;
        }
    }

    private void h() {
        Builder builder = new Builder(this);
        builder.setTitle(k.activation_disable_airplane_mode_title);
        builder.setMessage(k.activation_disable_airplane_mode_message);
        builder.setCancelable(false);
        builder.setPositiveButton(k.dialog_ok, new DialogInterface.OnClickListener(this) {
            final /* synthetic */ ActivationActivity a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                this.a.startActivity(new Intent("android.settings.AIRPLANE_MODE_SETTINGS"));
                dialogInterface.dismiss();
            }
        });
        builder.setNegativeButton(k.dialog_cancel, new DialogInterface.OnClickListener(this) {
            final /* synthetic */ ActivationActivity a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.create().show();
    }

    private void a(final boolean z) {
        Builder builder = new Builder(this);
        if (z) {
            builder.setTitle(k.activation_enable_wifi_title);
            builder.setMessage(k.configuration_enable_wifi_message);
        } else {
            builder.setTitle(k.activation_disable_wifi_title);
            builder.setMessage(k.activation_disable_wifi_message);
        }
        builder.setCancelable(false);
        builder.setPositiveButton(k.dialog_ok, new DialogInterface.OnClickListener(this) {
            final /* synthetic */ ActivationActivity a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                this.a.startActivity(new Intent("android.settings.WIFI_SETTINGS"));
                dialogInterface.dismiss();
            }
        });
        builder.setNegativeButton(k.dialog_cancel, new DialogInterface.OnClickListener(this) {
            final /* synthetic */ ActivationActivity b;

            public void onClick(DialogInterface dialogInterface, int i) {
                if (z) {
                    dialogInterface.dismiss();
                }
            }
        });
        builder.create().show();
    }

    private void i() {
        if (this.g) {
            j();
            return;
        }
        Intent Y = FgVoIP.U().Y();
        if (Y.getComponent().getShortClassName().contains(getClass().getSimpleName())) {
            b();
            return;
        }
        startActivity(Y);
        finish();
    }

    private void j() {
        if (getIntent().getBooleanExtra("com.mavenir.android.activity.ActivationActivity.ExtraLaunchActivity", true)) {
            if (FgVoIP.U().C()) {
                startActivity(new Intent("com.mavenir.action.ACTION_LAUNCH_MAIN_TAB_DEFAULT_MESSAGING_APP"));
            } else {
                startActivity(new Intent("com.mavenir.action.LAUNCH_MAIN_TAB"));
            }
        }
        finish();
    }
}
