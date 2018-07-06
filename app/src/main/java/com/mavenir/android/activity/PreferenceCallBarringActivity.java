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
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;
import android.widget.Toast;
import com.fgmicrotec.mobile.android.fgvoip.f.h;
import com.fgmicrotec.mobile.android.fgvoip.f.k;
import com.mavenir.android.common.q;
import com.mavenir.android.services.SupplementaryService;
import com.mavenir.android.services.SupplementaryServiceRule;
import com.mavenir.android.services.SupplementaryServicesAdapter;
import com.mavenir.android.services.SupplementaryServicesAdapter.d;
import com.mavenir.android.services.SupplementaryServicesService;
import com.mavenir.android.services.SupplementaryServicesService.a;
import com.mavenir.android.services.SupplementaryServicesService.b;
import java.util.ArrayList;
import java.util.Iterator;

public class PreferenceCallBarringActivity extends PreferenceActivity implements OnCancelListener, OnPreferenceClickListener, b {
    private PreferenceScreen a;
    private CheckBoxPreference b;
    private CheckBoxPreference c;
    private CheckBoxPreference d;
    private CheckBoxPreference e;
    private CheckBoxPreference f;
    private SupplementaryServicesService g;
    private CheckBoxPreference h;
    private boolean i = false;
    private ProgressDialog j;
    private boolean k = false;
    private ServiceConnection l = new ServiceConnection(this) {
        final /* synthetic */ PreferenceCallBarringActivity a;

        {
            this.a = r1;
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            q.a("PreferenceCallBarringActivity", "onServiceConnected()");
            this.a.g = ((a) iBinder).a();
            this.a.g.a(this.a);
            this.a.i = true;
            this.a.c();
        }

        public void onServiceDisconnected(ComponentName componentName) {
            q.a("PreferenceCallBarringActivity", "onServiceDisconnected()");
            this.a.i = false;
        }
    };

    @TargetApi(11)
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (VERSION.SDK_INT >= 11) {
            ActionBar actionBar = getActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(k.preference_call_barring_title);
        }
        a();
        bindService(new Intent(this, SupplementaryServicesService.class), this.l, 1);
        a(false);
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.i) {
            q.a("PreferenceCallBarringActivity", "onDestroy(): unbinding service");
            unbindService(this.l);
            this.i = false;
        }
    }

    public void onCancel(DialogInterface dialogInterface) {
        if (!this.k) {
            finish();
        }
    }

    public void a(SupplementaryServicesAdapter.a aVar) {
    }

    public void b(SupplementaryServicesAdapter.a aVar) {
        q.a("PreferenceCallBarringActivity", "onSetServiceCnf(): errorType: " + aVar.name());
        b();
        if (aVar != SupplementaryServicesAdapter.a.SS_ERROR_OK) {
            a(k.preference_call_additional_error_update);
            runOnUiThread(new Runnable(this) {
                final /* synthetic */ PreferenceCallBarringActivity a;

                {
                    this.a = r1;
                }

                public void run() {
                    if (this.a.h != null) {
                        this.a.a(this.a.h, !this.a.h.isChecked());
                    }
                }
            });
        }
    }

    public boolean onPreferenceClick(Preference preference) {
        if (preference instanceof CheckBoxPreference) {
            CheckBoxPreference checkBoxPreference = (CheckBoxPreference) preference;
            SupplementaryService a;
            if (preference.getKey().equals(getString(k.preference_call_barring_outgoing_all))) {
                a = this.g.a(d.SS_SERVICE_OCB);
                a(checkBoxPreference, a, this.g.a(a, SupplementaryServicesAdapter.b.SS_RULE_BAOC));
            } else if (preference.getKey().equals(getString(k.preference_call_barring_outgoing_intl))) {
                a = this.g.a(d.SS_SERVICE_OCB);
                a(checkBoxPreference, a, this.g.a(a, SupplementaryServicesAdapter.b.SS_RULE_BOIC));
            } else if (preference.getKey().equals(getString(k.preference_call_barring_outgoing_intl_except))) {
                a = this.g.a(d.SS_SERVICE_OCB);
                a(checkBoxPreference, a, this.g.a(a, SupplementaryServicesAdapter.b.SS_RULE_BOICExHC));
            } else if (preference.getKey().equals(getString(k.preference_call_barring_incoming_all))) {
                a = this.g.a(d.SS_SERVICE_ICB);
                a(checkBoxPreference, a, this.g.a(a, SupplementaryServicesAdapter.b.SS_RULE_BAIC));
            } else if (preference.getKey().equals(getString(k.preference_call_barring_incoming_roam))) {
                a = this.g.a(d.SS_SERVICE_ICB);
                a(checkBoxPreference, a, this.g.a(a, SupplementaryServicesAdapter.b.SS_RULE_BICROAM));
            }
        }
        return false;
    }

    private void a() {
        this.a = getPreferenceManager().createPreferenceScreen(this);
        setPreferenceScreen(this.a);
        this.b = new CheckBoxPreference(this);
        this.b.setLayoutResource(h.custom_checkbox_preference);
        this.b.setKey(getString(k.preference_call_barring_outgoing_all));
        this.b.setTitle(k.preference_call_barring_outgoing_all);
        this.b.setPersistent(false);
        this.b.setChecked(false);
        this.b.setSummary(k.preference_call_barring_off);
        this.b.setOnPreferenceClickListener(this);
        this.b.setEnabled(false);
        this.c = new CheckBoxPreference(this);
        this.c.setLayoutResource(h.custom_checkbox_preference);
        this.c.setKey(getString(k.preference_call_barring_outgoing_intl));
        this.c.setTitle(k.preference_call_barring_outgoing_intl);
        this.c.setPersistent(false);
        this.c.setChecked(false);
        this.c.setSummary(k.preference_call_barring_off);
        this.c.setOnPreferenceClickListener(this);
        this.c.setEnabled(false);
        this.d = new CheckBoxPreference(this);
        this.d.setLayoutResource(h.custom_checkbox_preference);
        this.d.setKey(getString(k.preference_call_barring_outgoing_intl_except));
        this.d.setTitle(k.preference_call_barring_outgoing_intl_except);
        this.d.setPersistent(false);
        this.d.setChecked(false);
        this.d.setSummary(k.preference_call_barring_off);
        this.d.setOnPreferenceClickListener(this);
        this.d.setEnabled(false);
        this.e = new CheckBoxPreference(this);
        this.e.setLayoutResource(h.custom_checkbox_preference);
        this.e.setKey(getString(k.preference_call_barring_incoming_all));
        this.e.setTitle(k.preference_call_barring_incoming_all);
        this.e.setPersistent(false);
        this.e.setChecked(false);
        this.e.setSummary(k.preference_call_barring_off);
        this.e.setOnPreferenceClickListener(this);
        this.e.setEnabled(false);
        this.f = new CheckBoxPreference(this);
        this.f.setLayoutResource(h.custom_checkbox_preference);
        this.f.setKey(getString(k.preference_call_barring_incoming_roam));
        this.f.setTitle(k.preference_call_barring_incoming_roam);
        this.f.setPersistent(false);
        this.f.setChecked(false);
        this.f.setSummary(k.preference_call_barring_off);
        this.f.setOnPreferenceClickListener(this);
        this.f.setEnabled(false);
    }

    private void a(boolean z) {
        CharSequence string;
        boolean z2 = false;
        this.j = new ProgressDialog(this);
        this.j.setCanceledOnTouchOutside(false);
        ProgressDialog progressDialog = this.j;
        if (!z) {
            z2 = true;
        }
        progressDialog.setCancelable(z2);
        this.j.setTitle(null);
        progressDialog = this.j;
        if (z) {
            string = getString(k.preference_call_additional_progress_update);
        } else {
            string = getString(k.preference_call_additional_progress);
        }
        progressDialog.setMessage(string);
        this.j.setIndeterminate(true);
        this.j.setOnCancelListener(this);
        this.j.show();
    }

    private void b() {
        this.k = true;
        if (this.j != null && this.j.isShowing()) {
            this.j.dismiss();
        }
    }

    private void c() {
        if (this.g != null) {
            ArrayList c = this.g.c();
            if (c != null) {
                Iterator it = c.iterator();
                while (it.hasNext()) {
                    SupplementaryService supplementaryService = (SupplementaryService) it.next();
                    if (supplementaryService.getServiceType() == d.SS_SERVICE_ICB || supplementaryService.getServiceType() == d.SS_SERVICE_OCB) {
                        a(supplementaryService);
                    }
                }
            }
        }
        b();
    }

    private void a(SupplementaryService supplementaryService) {
        for (SupplementaryServiceRule supplementaryServiceRule : supplementaryService.getRules()) {
            if (supplementaryServiceRule.getRuleType() == SupplementaryServicesAdapter.b.SS_RULE_BAOC) {
                this.a.addPreference(this.b);
                a(supplementaryService, supplementaryServiceRule, this.b);
            } else if (supplementaryServiceRule.getRuleType() == SupplementaryServicesAdapter.b.SS_RULE_BOIC) {
                this.a.addPreference(this.c);
                a(supplementaryService, supplementaryServiceRule, this.c);
            } else if (supplementaryServiceRule.getRuleType() == SupplementaryServicesAdapter.b.SS_RULE_BOICExHC) {
                this.a.addPreference(this.d);
                a(supplementaryService, supplementaryServiceRule, this.d);
            } else if (supplementaryServiceRule.getRuleType() == SupplementaryServicesAdapter.b.SS_RULE_BAIC) {
                this.a.addPreference(this.e);
                a(supplementaryService, supplementaryServiceRule, this.e);
            } else if (supplementaryServiceRule.getRuleType() == SupplementaryServicesAdapter.b.SS_RULE_BICROAM) {
                this.a.addPreference(this.f);
                a(supplementaryService, supplementaryServiceRule, this.f);
            }
        }
    }

    private void a(SupplementaryService supplementaryService, SupplementaryServiceRule supplementaryServiceRule, CheckBoxPreference checkBoxPreference) {
        checkBoxPreference.setOnPreferenceClickListener(null);
        if (supplementaryService.isActive()) {
            checkBoxPreference.setSummary(supplementaryServiceRule.isActive() ? k.preference_call_barring_on : k.preference_call_barring_off);
            checkBoxPreference.setChecked(supplementaryServiceRule.isActive());
        } else {
            checkBoxPreference.setSummary(k.preference_call_barring_off);
            checkBoxPreference.setChecked(false);
        }
        checkBoxPreference.setEnabled(true);
        checkBoxPreference.setOnPreferenceClickListener(this);
    }

    private void a(CheckBoxPreference checkBoxPreference, SupplementaryService supplementaryService, SupplementaryServiceRule supplementaryServiceRule) {
        boolean z = false;
        boolean z2 = true;
        boolean isChecked = checkBoxPreference.isChecked();
        if (supplementaryService == null || supplementaryServiceRule == null) {
            if (isChecked) {
                z2 = false;
            }
            a(checkBoxPreference, z2);
            return;
        }
        supplementaryServiceRule.setIsActive(isChecked);
        if (!isChecked) {
            z = true;
        }
        supplementaryServiceRule.setActionAllowed(z);
        a(true);
        this.g.a(supplementaryService, supplementaryServiceRule);
        this.h = checkBoxPreference;
    }

    private void a(CheckBoxPreference checkBoxPreference, boolean z) {
        checkBoxPreference.setOnPreferenceClickListener(null);
        checkBoxPreference.setChecked(z);
        checkBoxPreference.setSummary(z ? k.preference_call_barring_on : k.preference_call_barring_off);
        checkBoxPreference.setEnabled(true);
        checkBoxPreference.setOnPreferenceClickListener(this);
    }

    private void a(final int i) {
        runOnUiThread(new Runnable(this) {
            final /* synthetic */ PreferenceCallBarringActivity b;

            public void run() {
                Toast.makeText(this.b.getApplicationContext(), i, 0).show();
            }
        });
    }
}
