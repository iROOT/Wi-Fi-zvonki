package com.mavenir.android.activity;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;
import com.fgmicrotec.mobile.android.fgmag.VoIP.b;
import com.fgmicrotec.mobile.android.fgvoip.FgVoIP;
import com.fgmicrotec.mobile.android.fgvoip.f.k;
import com.mavenir.android.common.t;
import com.mavenir.android.settings.c.q;

public class PreferenceActivationActivity extends PreferenceActivity implements OnPreferenceChangeListener, OnPreferenceClickListener {
    private PreferenceScreen a;
    private EditTextPreference b;
    private EditTextPreference c;
    private Preference d;
    private Preference e;

    @TargetApi(11)
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (VERSION.SDK_INT >= 11) {
            ActionBar actionBar = getActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(k.preference_dev_activation_title);
        }
        a();
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        if (preference instanceof EditTextPreference) {
            String str = (String) obj;
            EditTextPreference editTextPreference = (EditTextPreference) preference;
            editTextPreference.setSummary(str);
            if (editTextPreference.getKey() == "provisioning_fqdn") {
                q.c(str);
                return true;
            } else if (editTextPreference.getKey() == "provisioning_fqdn_2") {
                q.d(str);
                return true;
            }
        }
        return false;
    }

    public boolean onPreferenceClick(Preference preference) {
        if (preference.getKey().equals("RESET_BUTTON")) {
            com.mavenir.android.common.q.a("PreferenceActivationActivity", "onPreferenceClick(): User pressed deactivate button");
            FgVoIP.U().a("com.fgmicrotec.mobile.android.voip.LogoutFromServerReq");
            FgVoIP.U().a((Context) this, "com.mavenir.android.action_backup_deactivated", b.FGVOIPCPROXY_DEACTIVATION_MUST_REPROVISION_CREDENTIALS.ordinal());
            this.b.setText("");
            this.b.setSummary("");
            this.b.setDefaultValue("");
            this.c.setText("");
            this.c.setSummary("");
            this.c.setDefaultValue("");
        } else if (preference.getKey().equals("START_BUTTON")) {
            com.mavenir.android.common.q.a("PreferenceActivationActivity", "onPreferenceClick(): User pressed start button");
            Intent intent = new Intent();
            intent.setClassName(this, "com.mavenir.android.vtow.activity.ActivationInitialActivity");
            intent.putExtra("com.mavenir.android.activation.extra_force_activation", true);
            intent.addFlags(268435456);
            intent.addFlags(32768);
            startActivity(intent);
        }
        return false;
    }

    private void a() {
        this.a = getPreferenceManager().createPreferenceScreen(this);
        this.b = new EditTextPreference(this);
        this.b.setKey("provisioning_fqdn");
        this.b.setDialogTitle(k.preference_activation_fqdn_1);
        this.b.setTitle(k.preference_activation_fqdn_1);
        this.b.setPersistent(false);
        t.a(this.b, 100);
        this.b.setSummary(q.u());
        this.b.setDefaultValue(q.u());
        this.b.setOnPreferenceChangeListener(this);
        this.a.addPreference(this.b);
        this.c = new EditTextPreference(this);
        this.c.setKey("provisioning_fqdn_2");
        this.c.setDialogTitle(k.preference_activation_fqdn_2);
        this.c.setTitle(k.preference_activation_fqdn_2);
        this.c.setPersistent(false);
        t.a(this.c, 100);
        this.c.setSummary(q.v());
        this.c.setDefaultValue(q.v());
        this.c.setOnPreferenceChangeListener(this);
        this.a.addPreference(this.c);
        this.d = new Preference(this);
        this.d.setKey("RESET_BUTTON");
        this.d.setTitle(k.preference_activation_reset);
        this.d.setSummary(k.preference_activation_reset_summary);
        this.d.setPersistent(false);
        this.d.setOnPreferenceClickListener(this);
        this.a.addPreference(this.d);
        this.e = new Preference(this);
        this.e.setKey("START_BUTTON");
        this.e.setTitle(k.preference_activation_start);
        this.e.setSummary(k.preference_activation_start_summary);
        this.e.setPersistent(false);
        this.e.setOnPreferenceClickListener(this);
        this.a.addPreference(this.e);
        setPreferenceScreen(this.a);
    }
}
