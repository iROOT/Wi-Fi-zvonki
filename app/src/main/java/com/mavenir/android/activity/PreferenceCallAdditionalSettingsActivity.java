package com.mavenir.android.activity;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.IBinder;
import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;
import android.widget.Toast;
import com.fgmicrotec.mobile.android.fgvoip.f.h;
import com.fgmicrotec.mobile.android.fgvoip.f.k;
import com.mavenir.android.common.q;
import com.mavenir.android.common.t;
import com.mavenir.android.services.SupplementaryService;
import com.mavenir.android.services.SupplementaryServiceRule;
import com.mavenir.android.services.SupplementaryServicesAdapter;
import com.mavenir.android.services.SupplementaryServicesAdapter.d;
import com.mavenir.android.services.SupplementaryServicesService;
import com.mavenir.android.services.SupplementaryServicesService.a;
import com.mavenir.android.services.SupplementaryServicesService.b;
import com.mavenir.android.settings.c.c;
import com.mavenir.android.settings.c.p;

public class PreferenceCallAdditionalSettingsActivity extends PreferenceActivity implements OnCancelListener, OnPreferenceChangeListener, OnPreferenceClickListener, b {
    private PreferenceScreen a;
    private EditTextPreference b;
    private EditTextPreference c;
    private PreferenceScreen d;
    private PreferenceScreen e;
    private PreferenceScreen f;
    private CheckBoxPreference g;
    private SupplementaryServicesService h;
    private SupplementaryService i;
    private boolean j = false;
    private ProgressDialog k;
    private boolean l = false;
    private boolean m = false;
    private ServiceConnection n = new ServiceConnection(this) {
        final /* synthetic */ PreferenceCallAdditionalSettingsActivity a;

        {
            this.a = r1;
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            q.a("PreferenceUtActivity", "onServiceConnected()");
            this.a.h = ((a) iBinder).a();
            this.a.h.a(this.a);
            this.a.j = true;
            this.a.h.a();
            this.a.h.b();
        }

        public void onServiceDisconnected(ComponentName componentName) {
            q.a("PreferenceUtActivity", "onServiceDisconnected()");
            this.a.j = false;
        }
    };

    @TargetApi(11)
    public void onCreate(Bundle bundle) {
        q.a("PreferenceUtActivity", "onCreate()");
        super.onCreate(bundle);
        this.m = getIntent().getBooleanExtra("showAdvanced", false);
        if (VERSION.SDK_INT >= 11) {
            ActionBar actionBar = getActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(k.preference_ut_title);
            if (this.m) {
                actionBar.setSubtitle(getString(k.preference_current_profile) + p.d());
            }
        }
        a();
        if (!this.m) {
            bindService(new Intent(this, SupplementaryServicesService.class), this.n, 1);
            a(false);
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        q.a("PreferenceUtActivity", "onDestroy()");
        if (this.j) {
            q.a("PreferenceUtActivity", "onDestroy(): unbinding service");
            if (this.j) {
                unbindService(this.n);
                this.j = false;
            }
        }
    }

    protected void onResume() {
        super.onResume();
        if (this.j && this.h != null) {
            this.h.a((b) this);
        }
    }

    public void onCancel(DialogInterface dialogInterface) {
        if (!this.l) {
            finish();
        }
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        if (preference instanceof EditTextPreference) {
            String str = (String) obj;
            EditTextPreference editTextPreference = (EditTextPreference) preference;
            editTextPreference.setSummary(str);
            if (editTextPreference.getKey() == "call_ut_server_address") {
                c.f(str);
                return true;
            } else if (editTextPreference.getKey() == "call_ut_server_port") {
                try {
                    c.a(Integer.parseInt(str));
                    return true;
                } catch (NumberFormatException e) {
                    q.a("PreferenceUtActivity", "onPreferenceChange(): UT port invalid: " + str);
                }
            }
        }
        return false;
    }

    public boolean onPreferenceClick(Preference preference) {
        if (preference instanceof CheckBoxPreference) {
            boolean isChecked = ((CheckBoxPreference) preference).isChecked();
            if (preference.getKey().equals(getString(k.preference_call_waiting_title))) {
                SupplementaryService a = this.h.a(d.SS_SERVICE_CW);
                if (a != null) {
                    a.setIsActive(isChecked);
                    a(a, null);
                    this.i = a;
                } else {
                    this.g.setOnPreferenceClickListener(null);
                    CheckBoxPreference checkBoxPreference = this.g;
                    if (isChecked) {
                        isChecked = false;
                    } else {
                        isChecked = true;
                    }
                    checkBoxPreference.setChecked(isChecked);
                    this.g.setEnabled(true);
                    this.g.setOnPreferenceClickListener(this);
                }
            }
        }
        return false;
    }

    public void a(SupplementaryServicesAdapter.a aVar) {
        q.a("PreferenceUtActivity", "onGetServicesCnf(): errorType: " + aVar.name());
        b();
        if (aVar == SupplementaryServicesAdapter.a.SS_ERROR_OK) {
            c();
            return;
        }
        a(k.preference_call_additional_error);
        finish();
    }

    public void b(SupplementaryServicesAdapter.a aVar) {
        q.a("PreferenceUtActivity", "onSetServiceCnf(): errorType: " + aVar.name());
        b();
        if (aVar != SupplementaryServicesAdapter.a.SS_ERROR_OK) {
            a(k.preference_call_additional_error_update);
            if (this.i != null && this.i.getServiceType() == d.SS_SERVICE_CW) {
                this.i.setIsActive(!this.i.isActive());
            }
        }
        c();
    }

    private void a() {
        this.a = getPreferenceManager().createPreferenceScreen(this);
        setPreferenceScreen(this.a);
        if (this.m) {
            this.b = new EditTextPreference(this);
            this.b.setKey("call_ut_server_address");
            this.b.setDialogTitle(k.preference_call_additional_server_address);
            this.b.setTitle(k.preference_call_additional_server_address);
            this.b.setPersistent(false);
            this.b.setSummary(c.e());
            this.b.setDefaultValue(c.e());
            this.b.setOnPreferenceChangeListener(this);
            this.b.getEditText().setInputType(1);
            this.a.addPreference(this.b);
            this.c = new EditTextPreference(this);
            this.c.setKey("call_ut_server_port");
            this.c.setDialogTitle(k.preference_call_additional_server_port);
            this.c.setTitle(k.preference_call_additional_server_port);
            this.c.setPersistent(false);
            t.a(this.c, 5);
            this.c.setSummary(String.valueOf(c.f()));
            this.c.setDefaultValue(String.valueOf(c.f()));
            this.c.setOnPreferenceChangeListener(this);
            this.c.getEditText().setInputType(2);
            this.a.addPreference(this.c);
            return;
        }
        this.d = getPreferenceManager().createPreferenceScreen(this);
        this.d.setIntent(new Intent(this, PreferenceCallIdentityPresentationActivity.class));
        this.d.setTitle(k.preference_call_id_presentation_title);
        this.e = getPreferenceManager().createPreferenceScreen(this);
        this.e.setIntent(new Intent(this, PreferenceCallForwardingActivity.class));
        this.e.setTitle(k.preference_call_forwarding_title);
        this.f = getPreferenceManager().createPreferenceScreen(this);
        this.f.setIntent(new Intent(this, PreferenceCallBarringActivity.class));
        this.f.setTitle(k.preference_call_barring_title);
        this.g = new CheckBoxPreference(this);
        this.g.setLayoutResource(h.custom_checkbox_preference);
        this.g.setKey(getString(k.preference_call_waiting_title));
        this.g.setTitle(k.preference_call_waiting_title);
        this.g.setPersistent(false);
        this.g.setChecked(false);
        this.g.setSummary(k.preference_call_waiting_summary);
        this.g.setOnPreferenceClickListener(this);
        this.g.setEnabled(false);
    }

    private void a(boolean z) {
        CharSequence string;
        boolean z2 = false;
        this.k = new ProgressDialog(this);
        this.k.setCanceledOnTouchOutside(false);
        ProgressDialog progressDialog = this.k;
        if (!z) {
            z2 = true;
        }
        progressDialog.setCancelable(z2);
        this.k.setTitle(null);
        progressDialog = this.k;
        if (z) {
            string = getString(k.preference_call_additional_progress_update);
        } else {
            string = getString(k.preference_call_additional_progress);
        }
        progressDialog.setMessage(string);
        this.k.setIndeterminate(true);
        this.k.setOnCancelListener(this);
        this.k.show();
    }

    private void b() {
        this.l = true;
        if (this.k != null && this.k.isShowing()) {
            this.k.dismiss();
        }
    }

    private void c() {
        final SupplementaryService a = this.h.a(d.SS_SERVICE_CW);
        runOnUiThread(new Runnable(this) {
            final /* synthetic */ PreferenceCallAdditionalSettingsActivity b;

            public void run() {
                if (this.b.h.b(d.SS_SERVICE_OIP) || this.b.h.b(d.SS_SERVICE_OIPR) || this.b.h.b(d.SS_SERVICE_TIP) || this.b.h.b(d.SS_SERVICE_TIPR)) {
                    this.b.a.addPreference(this.b.d);
                }
                if (this.b.h.b(d.SS_SERVICE_CD)) {
                    this.b.a.addPreference(this.b.e);
                }
                if (this.b.h.b(d.SS_SERVICE_OCB) || this.b.h.b(d.SS_SERVICE_ICB)) {
                    this.b.a.addPreference(this.b.f);
                }
                if (this.b.h.b(d.SS_SERVICE_CW)) {
                    this.b.a.addPreference(this.b.g);
                    this.b.b(a.isActive());
                }
            }
        });
    }

    private void a(SupplementaryService supplementaryService, SupplementaryServiceRule supplementaryServiceRule) {
        a(true);
        this.h.a(supplementaryService, supplementaryServiceRule);
    }

    private void b(boolean z) {
        this.g.setOnPreferenceClickListener(null);
        this.g.setChecked(z);
        this.g.setEnabled(true);
        this.g.setOnPreferenceClickListener(this);
    }

    private void a(final int i) {
        runOnUiThread(new Runnable(this) {
            final /* synthetic */ PreferenceCallAdditionalSettingsActivity b;

            public void run() {
                Toast.makeText(this.b.getApplicationContext(), i, 0).show();
            }
        });
    }
}
