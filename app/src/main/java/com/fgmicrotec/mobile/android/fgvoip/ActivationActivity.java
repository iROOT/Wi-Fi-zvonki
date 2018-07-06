package com.fgmicrotec.mobile.android.fgvoip;

import android.annotation.SuppressLint;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import com.fgmicrotec.mobile.android.fgvoip.f.g;
import com.fgmicrotec.mobile.android.fgvoip.f.h;
import com.fgmicrotec.mobile.android.fgvoip.f.k;
import com.fgmicrotec.mobile.android.fgvoipcommon.CallService;
import com.mavenir.android.common.l;

public class ActivationActivity extends ActionBarActivity implements OnClickListener, OnItemClickListener {
    private ListView a;
    private Button b;
    private Button c;
    private View d;
    private TextView e;
    private TextView f;
    private TextView g;
    private EditText h;
    private ProgressDialog i;
    private a j;
    private Handler k;
    private String l;
    private b m = b.INITIAL;

    private class a extends BroadcastReceiver {
        final /* synthetic */ ActivationActivity a;

        private a(ActivationActivity activationActivity) {
            this.a = activationActivity;
        }

        /* synthetic */ a(ActivationActivity activationActivity, AnonymousClass1 anonymousClass1) {
            this(activationActivity);
        }

        public void onReceive(Context context, Intent intent) {
            this.a.h();
            if (intent == null) {
                return;
            }
            if ("ActivationActions.ActionProvisioningRequestPin".equals(intent.getAction())) {
                this.a.k.postDelayed(new c(this.a, b.REQUEST_PIN, intent.getExtras()), 100);
            } else if (!"ActivationActions.ActionStartProvisioningProgress".equals(intent.getAction()) && !"ActivationActions.ActionStopProvisioningProgress".equals(intent.getAction())) {
                if ("ActivationActions.ActionProvisioningSuccess".equals(intent.getAction())) {
                    if (intent.getIntExtra("ActivationExtras.ExtraVersion", 0) == 0 && intent.getStringExtra("ActivationExtras.ExtraPrivateURI") != null) {
                        this.a.k.postDelayed(new c(this.a, b.ACTIVATION_SUCCESS, intent.getExtras()), 100);
                    }
                } else if ("ActivationActions.ActionProvisioningFailed".equals(intent.getAction())) {
                    this.a.k.postDelayed(new c(this.a, b.ACTIVATION_FAILURE, intent.getExtras()), 100);
                }
            }
        }
    }

    private enum b {
        INITIAL,
        REQUEST_NUMBER,
        PROGRESS_DIALOG,
        REQUEST_PIN,
        REQUEST_WIFI,
        ACTIVATION_SUCCESS,
        ACTIVATION_FAILURE
    }

    private class c implements Runnable {
        final /* synthetic */ ActivationActivity a;
        private b b;
        private Bundle c;

        public c(ActivationActivity activationActivity, b bVar) {
            this.a = activationActivity;
            this.b = bVar;
            this.c = new Bundle();
        }

        public c(ActivationActivity activationActivity, b bVar, Bundle bundle) {
            this.a = activationActivity;
            this.b = bVar;
            this.c = bundle;
        }

        public void run() {
            if (this.b == b.INITIAL) {
                this.a.c();
            } else if (this.b == b.REQUEST_NUMBER) {
                this.a.d();
            } else if (this.b == b.PROGRESS_DIALOG) {
                this.a.e();
            } else if (this.b == b.REQUEST_PIN) {
                this.a.a(this.c);
            } else if (this.b == b.REQUEST_WIFI) {
                this.a.f();
            } else if (this.b == b.ACTIVATION_SUCCESS) {
                this.a.b(this.c);
            } else if (this.b == b.ACTIVATION_FAILURE) {
                this.a.c(this.c);
            }
        }
    }

    @SuppressLint({"NewApi"})
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.setContentView(h.activation_activity);
        if (VERSION.SDK_INT > 11) {
            super.setFinishOnTouchOutside(false);
        }
        this.d = findViewById(g.dialogAuthFormView);
        this.a = (ListView) findViewById(g.activationListView);
        this.e = (TextView) findViewById(g.dialogMessageTextView);
        this.f = (TextView) findViewById(g.messageTextView);
        this.h = (EditText) findViewById(g.numberInputEditText);
        this.g = (TextView) findViewById(g.errorTextView);
        this.b = (Button) findViewById(g.dialogNegativeButton);
        this.c = (Button) findViewById(g.dialogPositiveButton);
        this.a.setOnItemClickListener(this);
        this.b.setOnClickListener(this);
        this.c.setOnClickListener(this);
        this.h.setOnFocusChangeListener(new OnFocusChangeListener(this) {
            final /* synthetic */ ActivationActivity a;

            {
                this.a = r1;
            }

            public void onFocusChange(View view, boolean z) {
                InputMethodManager inputMethodManager = (InputMethodManager) this.a.getSystemService("input_method");
                if (!z) {
                    inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
            }
        });
        this.k = new Handler();
        this.j = new a();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("ActivationActions.ActionStartProvisioningProgress");
        intentFilter.addAction("ActivationActions.ActionStopProvisioningProgress");
        intentFilter.addAction("ActivationActions.ActionProvisioningSuccess");
        intentFilter.addAction("ActivationActions.ActionProvisioningFailed");
        intentFilter.addAction("ActivationActions.ActionRefreshAfterProvisioning");
        intentFilter.addAction("ActivationActions.ActionProvisioningRequestPin");
        registerReceiver(this.j, intentFilter);
        FgVoIP.U().aD();
    }

    protected void onSaveInstanceState(Bundle bundle) {
        bundle.putInt("ActivationExtras.ExtraActivationStep", this.m.ordinal());
        super.onSaveInstanceState(bundle);
    }

    protected void onRestoreInstanceState(Bundle bundle) {
        if (getIntent().hasExtra("ActivationExtras.ExtraActivationStep")) {
            this.m = b.values()[getIntent().getExtras().getInt("ActivationExtras.ExtraActivationStep")];
        }
        super.onRestoreInstanceState(bundle);
    }

    public void onPause() {
        super.onPause();
    }

    public void onResume() {
        super.onResume();
        if (getIntent().hasExtra("ActivationExtras.ExtraActivationStep")) {
            this.m = b.values()[getIntent().getExtras().getInt("ActivationExtras.ExtraActivationStep")];
        }
        this.k.post(new c(this, this.m));
    }

    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(this.j);
    }

    public void onBackPressed() {
    }

    public void onClick(View view) {
        this.h.clearFocus();
        int id = view.getId();
        if (id == g.dialogNegativeButton) {
            c();
        } else if (id == g.dialogPositiveButton) {
            b();
        }
    }

    private void a() {
        Intent intent = new Intent();
        intent.setAction("IntentActions.ActionExit");
        sendBroadcast(intent);
        finish();
    }

    private void b() {
        if (this.m == b.INITIAL) {
            this.k.postDelayed(new c(this, b.PROGRESS_DIALOG), 100);
        } else if (this.m == b.REQUEST_NUMBER) {
            if (this.h.getText().length() == 0) {
                this.g.setVisibility(0);
                this.g.setText(k.activation_error_number_empty);
                return;
            }
            this.g.setVisibility(8);
            r0 = new Intent(this, CallService.class);
            r0.setAction("ActivationActions.ActionStartInitialProvisioning");
            r0.putExtra("ActivationExtras.ExtraProvisioningNumber", this.h.getText().toString());
            startService(r0);
            this.k.postDelayed(new c(this, b.PROGRESS_DIALOG), 100);
        } else if (this.m == b.REQUEST_PIN) {
            if (this.h.getText().length() == 0) {
                this.g.setVisibility(0);
                this.g.setText(k.activation_error_pin_empty);
                return;
            }
            if (!l.a((Context) this).C()) {
                this.k.postDelayed(new c(this, b.REQUEST_WIFI), 100);
            }
            this.g.setVisibility(8);
            this.g.setText("");
            r0 = new Intent(this, CallService.class);
            r0.setAction("InternalIntents.ProvisioningAuthResponse");
            r0.putExtra("ActivationExtras.ExtraProvisioningTAN", this.l);
            r0.putExtra("ActivationExtras.ExtraProvisioningPIN", this.h.getText().toString().trim());
            startService(r0);
            this.k.postDelayed(new c(this, b.PROGRESS_DIALOG), 100);
        } else if (this.m == b.ACTIVATION_SUCCESS) {
            finish();
        } else if (this.m == b.ACTIVATION_FAILURE) {
            this.k.postDelayed(new c(this, b.INITIAL), 100);
        }
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        if (i >= 2 || l.a((Context) this).C() || l.a((Context) this).z()) {
            switch (i) {
                case 0:
                    d();
                    return;
                case 1:
                    g();
                    return;
                case 2:
                    a(com.fgmicrotec.mobile.android.fgvoip.b.b.ACTIVATION.ordinal());
                    return;
                case 3:
                    a();
                    return;
                default:
                    return;
            }
        }
        this.k.postDelayed(new c(this, b.REQUEST_WIFI), 100);
    }

    private void a(boolean z, boolean z2) {
        int i;
        int i2 = 0;
        Button button = this.b;
        if (z) {
            i = 0;
        } else {
            i = 8;
        }
        button.setVisibility(i);
        Button button2 = this.c;
        if (!z2) {
            i2 = 8;
        }
        button2.setVisibility(i2);
    }

    private void c() {
        setTitle(k.activation_title);
        this.m = b.INITIAL;
        this.a.setVisibility(0);
        this.d.setVisibility(8);
        this.e.setVisibility(8);
        a(false, false);
    }

    private void d() {
        setTitle(k.activation_title_number);
        this.m = b.REQUEST_NUMBER;
        this.a.setVisibility(8);
        this.d.setVisibility(0);
        this.e.setVisibility(8);
        this.f.setVisibility(8);
        this.g.setVisibility(8);
        this.h.setInputType(2);
        this.h.setHint(k.activation_number_hint);
        this.h.getEditableText().clear();
        this.h.setText(l.a((Context) this).o());
        a(true, true);
    }

    private void e() {
        this.m = b.PROGRESS_DIALOG;
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("");
        progressDialog.setCancelable(false);
        progressDialog.setMessage(getResources().getString(k.activation_progress_activating));
        progressDialog.show();
        this.i = progressDialog;
    }

    private void a(Bundle bundle) {
        setTitle(k.activation_title_pin);
        this.m = b.REQUEST_PIN;
        this.a.setVisibility(8);
        this.d.setVisibility(0);
        this.e.setVisibility(8);
        this.f.setVisibility(0);
        this.g.setVisibility(8);
        String string = bundle.getString("ActivationExtras.ExtraPrivateURI");
        this.h.setInputType(18);
        this.h.setHint(k.activation_pin_hint);
        this.h.getEditableText().clear();
        this.f.setText(String.format(getString(k.activation_enter_pin_message), new Object[]{string}));
        this.l = bundle.getString("ActivationExtras.ExtraTAN");
    }

    private void b(Bundle bundle) {
        this.a.setVisibility(8);
        this.d.setVisibility(8);
        this.e.setVisibility(0);
        a(false, true);
        String string = bundle.getString("ActivationExtras.ExtraPrivateURI");
        CharSequence string2 = bundle.getString("ActivationExtras.ExtraError");
        String string3 = bundle.getString("ActivationExtras.ExtraPin");
        if (string != null) {
            if (string.contains("@")) {
                string = string.substring(0, string.indexOf("@"));
            }
            if (string2 == null) {
                CharSequence format;
                setTitle(k.activation_title_success);
                this.m = b.ACTIVATION_SUCCESS;
                if (string3 == null) {
                    format = String.format(getString(k.activation_account_number), new Object[]{string});
                } else {
                    format = String.format(getString(k.activation_account_number_pin), new Object[]{string, string3});
                }
                this.e.setText(format);
                return;
            }
            setTitle(k.activation_title_failure);
            this.m = b.ACTIVATION_FAILURE;
            this.e.setText(string2);
        }
    }

    private void c(Bundle bundle) {
        setTitle(k.activation_title_failure);
        this.m = b.ACTIVATION_FAILURE;
        this.a.setVisibility(8);
        this.d.setVisibility(8);
        this.e.setVisibility(0);
        a(false, true);
        CharSequence string = bundle.getString("ActivationExtras.ExtraError");
        if (string == null) {
            string = getString(k.activation_account_not_valid_title);
        }
        this.e.setText(string);
    }

    private void f() {
        Builder builder = new Builder(this);
        builder.setTitle(k.activation_enable_wifi_title);
        builder.setMessage(k.activation_enable_wifi_message);
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

    private void g() {
        Intent intent = new Intent(this, CallService.class);
        intent.setAction("ActivationActions.ActionStartInitialProvisioning");
        startService(intent);
        this.k.postDelayed(new c(this, b.PROGRESS_DIALOG), 100);
    }

    private void a(int i) {
        Intent intent = new Intent(this, HelpTopicDetailActivity.class);
        intent.putExtra("topic_id", com.fgmicrotec.mobile.android.fgvoip.b.b.ACTIVATION.ordinal());
        startActivity(intent);
    }

    private void h() {
        if (this.i != null) {
            this.i.dismiss();
        }
    }
}
