package com.mavenir.android.activity;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceCategory;
import android.preference.PreferenceScreen;
import android.view.MenuItem;
import android.widget.Toast;
import com.fgmicrotec.mobile.android.fgvoip.FgVoIP;
import com.fgmicrotec.mobile.android.fgvoip.f.b;
import com.fgmicrotec.mobile.android.fgvoip.f.k;
import com.mavenir.android.common.t;
import com.mavenir.android.settings.c.p;
import com.mavenir.android.settings.c.w;

public class PreferenceSmsActivity extends PreferenceActivity implements OnPreferenceChangeListener {
    private PreferenceScreen a;
    private PreferenceCategory b;
    private EditTextPreference c;
    private ListPreference d;
    private EditTextPreference e;
    private ListPreference f;
    private ListPreference g;

    @TargetApi(11)
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (VERSION.SDK_INT >= 11) {
            ActionBar actionBar = getActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(k.preference_sms_title);
            actionBar.setSubtitle(getString(k.preference_current_profile) + p.d());
        }
        b();
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        if (preference instanceof EditTextPreference) {
            String str = (String) obj;
            EditTextPreference editTextPreference = (EditTextPreference) preference;
            editTextPreference.setSummary(str);
            if (editTextPreference.getKey() == "sms_service_center") {
                w.a(str);
                a();
                return true;
            } else if (editTextPreference.getKey() == "sms_validity") {
                try {
                    w.c(Integer.parseInt(str));
                    a();
                } catch (Exception e) {
                    Toast.makeText(this, getString(k.invalid_value), 0).show();
                }
                return true;
            }
        }
        if (preference instanceof ListPreference) {
            String str2 = (String) obj;
            ListPreference listPreference = (ListPreference) preference;
            int findIndexOfValue = listPreference.findIndexOfValue(str2);
            listPreference.setValueIndex(findIndexOfValue);
            listPreference.setSummary(str2);
            if (listPreference.getKey() == "preferred_messaging_protocol") {
                w.a(findIndexOfValue);
                return true;
            } else if (listPreference.getKey() == "enc_nat_number") {
                w.d(findIndexOfValue);
                a();
                return true;
            } else if (listPreference.getKey() == "enc_intl_number") {
                w.e(findIndexOfValue);
                a();
                return true;
            }
        }
        return false;
    }

    private void a() {
        Intent intent = new Intent();
        intent.setAction("InternalIntents.ActionUpdateSmsSettings");
        sendBroadcast(intent);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case 16908332:
                FgVoIP.U().c((Activity) this);
                break;
        }
        return true;
    }

    private void b() {
        this.a = getPreferenceManager().createPreferenceScreen(this);
        this.b = new PreferenceCategory(this);
        this.b.setTitle(k.preference_sms_category);
        this.a.addPreference(this.b);
        this.c = new EditTextPreference(this);
        this.c.setKey("sms_service_center");
        this.c.setDialogTitle(k.preference_sms_smsc);
        this.c.setTitle(k.preference_sms_smsc);
        this.c.setPersistent(false);
        this.c.setSummary(w.b());
        this.c.setDefaultValue(w.b());
        this.c.setOnPreferenceChangeListener(this);
        this.c.getEditText().setInputType(1);
        this.b.addPreference(this.c);
        this.d = new ListPreference(this);
        this.d.setKey("preferred_messaging_protocol");
        this.d.setDialogTitle(k.preference_sms_default_protocol);
        this.d.setTitle(k.preference_sms_default_protocol);
        this.d.setEntries(b.messaging_protocol);
        this.d.setEntryValues(b.messaging_protocol);
        this.d.setPersistent(false);
        this.d.setValueIndex(w.c());
        this.d.setSummary(this.d.getEntry());
        this.d.setOnPreferenceChangeListener(this);
        this.b.addPreference(this.d);
        if (FgVoIP.U().t()) {
            this.e = new EditTextPreference(this);
            this.e.setKey("sms_validity");
            this.e.setDialogTitle(k.preference_sms_validity);
            this.e.setTitle(k.preference_sms_validity);
            this.e.setPersistent(false);
            t.a(this.e, 9);
            this.e.setSummary(String.valueOf(w.e()));
            this.e.setDefaultValue(String.valueOf(w.e()));
            this.e.setOnPreferenceChangeListener(this);
            this.e.getEditText().setInputType(2);
            this.b.addPreference(this.e);
            this.f = new ListPreference(this);
            this.f.setKey("enc_nat_number");
            this.f.setDialogTitle(k.preference_sms_nat_num_enc);
            this.f.setTitle(k.preference_sms_nat_num_enc);
            this.f.setEntries(b.sms_enc_nat_numbers);
            this.f.setEntryValues(b.sms_enc_nat_numbers);
            this.f.setPersistent(false);
            this.f.setValueIndex(w.f());
            this.f.setSummary(this.f.getEntry());
            this.f.setOnPreferenceChangeListener(this);
            this.b.addPreference(this.f);
            this.g = new ListPreference(this);
            this.g.setKey("enc_intl_number");
            this.g.setDialogTitle(k.preference_sms_intl_num_enc);
            this.g.setTitle(k.preference_sms_intl_num_enc);
            this.g.setEntries(b.sms_enc_intl_numbers);
            this.g.setEntryValues(b.sms_enc_intl_numbers);
            this.g.setPersistent(false);
            this.g.setValueIndex(w.g());
            this.g.setSummary(this.g.getEntry());
            this.g.setOnPreferenceChangeListener(this);
            this.b.addPreference(this.g);
        }
        setPreferenceScreen(this.a);
    }
}
