package com.mavenir.android.activity;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;
import android.text.TextUtils;
import android.widget.Toast;
import com.fgmicrotec.mobile.android.fgvoip.f.k;
import com.mavenir.android.common.q;
import com.mavenir.android.common.t.f;
import com.mavenir.android.services.SupplementaryService;
import com.mavenir.android.services.SupplementaryServiceRule;
import com.mavenir.android.services.SupplementaryServicesAdapter;
import com.mavenir.android.services.SupplementaryServicesAdapter.d;
import com.mavenir.android.services.SupplementaryServicesService;
import com.mavenir.android.services.SupplementaryServicesService.a;
import com.mavenir.android.services.SupplementaryServicesService.b;
import com.mavenir.android.settings.c.c;
import com.mavenir.android.view.SupplementaryEditTextPreference;
import java.util.ArrayList;
import java.util.Iterator;

public class PreferenceCallForwardingActivity extends PreferenceActivity implements OnCancelListener, OnPreferenceChangeListener, OnPreferenceClickListener, b {
    private PreferenceScreen a;
    private SupplementaryEditTextPreference b;
    private SupplementaryEditTextPreference c;
    private SupplementaryEditTextPreference d;
    private SupplementaryEditTextPreference e;
    private SupplementaryEditTextPreference f;
    private SupplementaryServicesService g;
    private SupplementaryService h;
    private SupplementaryServiceRule i;
    private SupplementaryServiceRule j;
    private boolean k = false;
    private ProgressDialog l;
    private boolean m = false;
    private Handler n;
    private ServiceConnection o = new ServiceConnection(this) {
        final /* synthetic */ PreferenceCallForwardingActivity a;

        {
            this.a = r1;
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            q.a("PreferenceCallForwardingActivity", "onServiceConnected()");
            this.a.g = ((a) iBinder).a();
            this.a.g.a(this.a);
            this.a.k = true;
            this.a.c();
        }

        public void onServiceDisconnected(ComponentName componentName) {
            q.a("PreferenceCallForwardingActivity", "onServiceDisconnected()");
            this.a.k = false;
        }
    };

    @TargetApi(11)
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (VERSION.SDK_INT >= 11) {
            ActionBar actionBar = getActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(k.preference_call_forwarding_title);
        }
        a();
        bindService(new Intent(this, SupplementaryServicesService.class), this.o, 1);
        this.n = new Handler();
        a(false);
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.k) {
            q.a("PreferenceCallForwardingActivity", "onDestroy(): unbinding service");
            unbindService(this.o);
            this.k = false;
        }
    }

    public boolean onPreferenceClick(Preference preference) {
        if (preference instanceof SupplementaryEditTextPreference) {
            this.f = (SupplementaryEditTextPreference) preference;
        }
        return false;
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        if (preference instanceof SupplementaryEditTextPreference) {
            String str = (String) obj;
            SupplementaryEditTextPreference supplementaryEditTextPreference = (SupplementaryEditTextPreference) preference;
            if (str.length() > 0) {
                supplementaryEditTextPreference.a(true);
                supplementaryEditTextPreference.setSummary(getString(k.preference_call_forwarding_to, new Object[]{str}));
            } else {
                supplementaryEditTextPreference.a(false);
                supplementaryEditTextPreference.setSummary(k.preference_call_forward_off);
            }
            this.h = this.g.a(d.SS_SERVICE_CD);
            if (this.h != null) {
                if (supplementaryEditTextPreference.getKey().equals(getString(k.preference_call_forward_always))) {
                    a(SupplementaryServicesAdapter.b.SS_RULE_CFU, str);
                    a(this.h, this.j);
                    return true;
                } else if (supplementaryEditTextPreference.getKey().equals(getString(k.preference_call_forward_busy))) {
                    a(SupplementaryServicesAdapter.b.SS_RULE_CFB, str);
                    a(this.h, this.j);
                    return true;
                } else if (supplementaryEditTextPreference.getKey().equals(getString(k.preference_call_forward_unanswered))) {
                    a(SupplementaryServicesAdapter.b.SS_RULE_CFNR, str);
                    a(this.h, this.j);
                    return true;
                } else if (supplementaryEditTextPreference.getKey().equals(getString(k.preference_call_forward_unreachable))) {
                    a(SupplementaryServicesAdapter.b.SS_RULE_CFNRC, str);
                    a(this.h, this.j);
                    return true;
                }
            }
        }
        return false;
    }

    public void onCancel(DialogInterface dialogInterface) {
        if (!this.m) {
            finish();
        }
    }

    public void a(SupplementaryServicesAdapter.a aVar) {
    }

    public void b(final SupplementaryServicesAdapter.a aVar) {
        q.a("PreferenceCallForwardingActivity", "onSetServiceCnf(): errorType: " + aVar.name());
        b();
        runOnUiThread(new Runnable(this) {
            final /* synthetic */ PreferenceCallForwardingActivity b;

            public void run() {
                boolean z = true;
                if (aVar != SupplementaryServicesAdapter.a.SS_ERROR_OK) {
                    this.b.a(k.preference_call_additional_error_update);
                    if (this.b.f != null) {
                        this.b.a(this.b.h, this.b.i, this.b.f);
                    }
                } else if (this.b.f != null) {
                    if (this.b.j.getRuleType() == SupplementaryServicesAdapter.b.SS_RULE_CFU) {
                        boolean z2;
                        this.b.a(this.b.c, !this.b.j.isActive());
                        PreferenceCallForwardingActivity preferenceCallForwardingActivity = this.b;
                        SupplementaryEditTextPreference d = this.b.d;
                        if (this.b.j.isActive()) {
                            z2 = false;
                        } else {
                            z2 = true;
                        }
                        preferenceCallForwardingActivity.a(d, z2);
                        PreferenceCallForwardingActivity preferenceCallForwardingActivity2 = this.b;
                        SupplementaryEditTextPreference e = this.b.e;
                        if (this.b.j.isActive()) {
                            z = false;
                        }
                        preferenceCallForwardingActivity2.a(e, z);
                    }
                    this.b.g.b(this.b.h, this.b.j);
                    this.b.a(this.b.h, this.b.j, this.b.f);
                    com.mavenir.android.settings.d.b(this.b, com.mavenir.android.settings.d.b, System.currentTimeMillis());
                }
            }
        });
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1 && intent != null) {
            Uri data = intent.getData();
            if (data != null) {
                Object a = com.mavenir.android.common.k.a(data);
                if (this.f != null && this.f.getEditText() != null) {
                    this.f.getEditText().setText(a);
                    this.f.getEditText().setSelection(a.length());
                }
            }
        }
    }

    private void a() {
        this.a = getPreferenceManager().createPreferenceScreen(this);
        setPreferenceScreen(this.a);
        this.b = new SupplementaryEditTextPreference(this);
        this.b.setKey(getString(k.preference_call_forward_always));
        this.b.setTitle(k.preference_call_forward_always);
        this.b.setPersistent(false);
        this.b.setDialogMessage(k.preference_call_forward_always_number);
        this.b.setSummary(k.preference_call_forward_off);
        this.b.setDefaultValue("");
        this.b.setOnPreferenceChangeListener(this);
        this.b.setOnPreferenceClickListener(this);
        this.b.setEnabled(false);
        this.c = new SupplementaryEditTextPreference(this);
        this.c.setKey(getString(k.preference_call_forward_busy));
        this.c.setTitle(k.preference_call_forward_busy);
        this.c.setPersistent(false);
        this.c.setDialogMessage(k.preference_call_forward_busy_number);
        this.c.setSummary(k.preference_call_forward_off);
        this.c.setDefaultValue("");
        this.c.setOnPreferenceChangeListener(this);
        this.c.setOnPreferenceClickListener(this);
        this.c.setEnabled(false);
        this.d = new SupplementaryEditTextPreference(this);
        this.d.setKey(getString(k.preference_call_forward_unanswered));
        this.d.setTitle(k.preference_call_forward_unanswered);
        this.d.setPersistent(false);
        this.d.setDialogMessage(k.preference_call_forward_unanswered_number);
        this.d.setSummary(k.preference_call_forward_off);
        this.d.setDefaultValue("");
        this.d.setOnPreferenceChangeListener(this);
        this.d.setOnPreferenceClickListener(this);
        this.d.setEnabled(false);
        this.e = new SupplementaryEditTextPreference(this);
        this.e.setKey(getString(k.preference_call_forward_unreachable));
        this.e.setTitle(k.preference_call_forward_unreachable);
        this.e.setPersistent(false);
        this.e.setDialogMessage(k.preference_call_forward_unreachable_number);
        this.e.setSummary(k.preference_call_forward_off);
        this.e.setDefaultValue("");
        this.e.setOnPreferenceChangeListener(this);
        this.e.setOnPreferenceClickListener(this);
        this.e.setEnabled(false);
    }

    private void a(boolean z) {
        CharSequence string;
        boolean z2 = false;
        this.l = new ProgressDialog(this);
        this.l.setCanceledOnTouchOutside(false);
        ProgressDialog progressDialog = this.l;
        if (!z) {
            z2 = true;
        }
        progressDialog.setCancelable(z2);
        this.l.setTitle(null);
        progressDialog = this.l;
        if (z) {
            string = getString(k.preference_call_additional_progress_update);
        } else {
            string = getString(k.preference_call_additional_progress);
        }
        progressDialog.setMessage(string);
        this.l.setIndeterminate(true);
        this.l.setOnCancelListener(this);
        this.l.show();
    }

    private void b() {
        this.m = true;
        if (this.l != null && this.l.isShowing()) {
            this.l.dismiss();
        }
    }

    private void c() {
        if (this.g != null) {
            ArrayList c = this.g.c();
            if (c != null) {
                Iterator it = c.iterator();
                while (it.hasNext()) {
                    SupplementaryService supplementaryService = (SupplementaryService) it.next();
                    if (supplementaryService.getServiceType() == d.SS_SERVICE_CD) {
                        a(supplementaryService);
                    }
                }
            }
        }
        b();
    }

    private void a(final SupplementaryService supplementaryService, final SupplementaryServiceRule supplementaryServiceRule) {
        a(true);
        long currentTimeMillis = System.currentTimeMillis() - com.mavenir.android.settings.d.a((Context) this, com.mavenir.android.settings.d.b, -1);
        if (currentTimeMillis > 2000) {
            this.g.a(supplementaryService, supplementaryServiceRule);
        } else {
            this.n.postDelayed(new Runnable(this) {
                final /* synthetic */ PreferenceCallForwardingActivity c;

                public void run() {
                    this.c.g.a(supplementaryService, supplementaryServiceRule);
                }
            }, currentTimeMillis);
        }
    }

    private void a(SupplementaryService supplementaryService) {
        for (SupplementaryServiceRule supplementaryServiceRule : supplementaryService.getRules()) {
            if (supplementaryServiceRule.getRuleType() == SupplementaryServicesAdapter.b.SS_RULE_CFU) {
                this.a.addPreference(this.b);
                a(supplementaryService, supplementaryServiceRule, this.b);
            } else if (supplementaryServiceRule.getRuleType() == SupplementaryServicesAdapter.b.SS_RULE_CFB) {
                this.a.addPreference(this.c);
                a(supplementaryService, supplementaryServiceRule, this.c);
            } else if (supplementaryServiceRule.getRuleType() == SupplementaryServicesAdapter.b.SS_RULE_CFNR) {
                this.a.addPreference(this.d);
                a(supplementaryService, supplementaryServiceRule, this.d);
            } else if (supplementaryServiceRule.getRuleType() == SupplementaryServicesAdapter.b.SS_RULE_CFNRC) {
                this.a.addPreference(this.e);
                a(supplementaryService, supplementaryServiceRule, this.e);
            }
        }
    }

    private void a(SupplementaryServicesAdapter.b bVar, String str) {
        this.i = this.g.a(this.h, bVar);
        this.j = new SupplementaryServiceRule();
        if (this.i != null) {
            this.j.setRuleId(this.i.getRuleId());
            this.j.setRuleType(this.i.getRuleType());
            this.j.setRuleValueType(this.i.getRuleValueType());
            this.j.setIsActive(!TextUtils.isEmpty(str));
            this.j.setNoReplyTimerValue(this.i.getNoReplyTimerValue());
            this.j.setActionAllowed(this.i.isActionAllowed());
            this.j.setForwardingValue(f.a(str, c.b(), -1));
        }
    }

    private void a(SupplementaryService supplementaryService, SupplementaryServiceRule supplementaryServiceRule, SupplementaryEditTextPreference supplementaryEditTextPreference) {
        if (supplementaryServiceRule != null) {
            boolean z;
            String f;
            supplementaryEditTextPreference.setOnPreferenceChangeListener(null);
            if (supplementaryServiceRule.getRuleType() != SupplementaryServicesAdapter.b.SS_RULE_CFU) {
                SupplementaryServiceRule ruleByType = supplementaryService.getRuleByType(SupplementaryServicesAdapter.b.SS_RULE_CFU);
                if (ruleByType != null && ruleByType.isActive()) {
                    z = true;
                    if (supplementaryService.isActive()) {
                        supplementaryEditTextPreference.setSummary(getString(k.preference_call_forward_off));
                    } else if (supplementaryServiceRule.isActive()) {
                        supplementaryEditTextPreference.setSummary(getString(k.preference_call_forward_off));
                    } else if (supplementaryServiceRule.getRuleValueType() == SupplementaryServicesAdapter.c.SS_RULE_TYPE_FORWARDING) {
                        f = f.f(supplementaryServiceRule.getForwardingValue());
                        supplementaryEditTextPreference.setSummary(getString(k.preference_call_forwarding_to, new Object[]{f}));
                        supplementaryEditTextPreference.setText(f);
                        supplementaryEditTextPreference.a(supplementaryServiceRule.isActive());
                    }
                    if (z) {
                        supplementaryEditTextPreference.setEnabled(true);
                    } else {
                        supplementaryEditTextPreference.setEnabled(false);
                    }
                    supplementaryEditTextPreference.setOnPreferenceChangeListener(this);
                }
            }
            z = false;
            if (supplementaryService.isActive()) {
                supplementaryEditTextPreference.setSummary(getString(k.preference_call_forward_off));
            } else if (supplementaryServiceRule.isActive()) {
                supplementaryEditTextPreference.setSummary(getString(k.preference_call_forward_off));
            } else if (supplementaryServiceRule.getRuleValueType() == SupplementaryServicesAdapter.c.SS_RULE_TYPE_FORWARDING) {
                f = f.f(supplementaryServiceRule.getForwardingValue());
                supplementaryEditTextPreference.setSummary(getString(k.preference_call_forwarding_to, new Object[]{f}));
                supplementaryEditTextPreference.setText(f);
                supplementaryEditTextPreference.a(supplementaryServiceRule.isActive());
            }
            if (z) {
                supplementaryEditTextPreference.setEnabled(false);
            } else {
                supplementaryEditTextPreference.setEnabled(true);
            }
            supplementaryEditTextPreference.setOnPreferenceChangeListener(this);
        }
    }

    private void a(SupplementaryEditTextPreference supplementaryEditTextPreference, boolean z) {
        supplementaryEditTextPreference.setOnPreferenceChangeListener(null);
        supplementaryEditTextPreference.setEnabled(z);
        supplementaryEditTextPreference.setOnPreferenceChangeListener(this);
    }

    private void a(final int i) {
        runOnUiThread(new Runnable(this) {
            final /* synthetic */ PreferenceCallForwardingActivity b;

            public void run() {
                Toast.makeText(this.b.getApplicationContext(), i, 0).show();
            }
        });
    }
}
