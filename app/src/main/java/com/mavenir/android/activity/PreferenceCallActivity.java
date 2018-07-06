package com.mavenir.android.activity;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceCategory;
import android.preference.PreferenceScreen;
import android.view.MenuItem;
import com.fgmicrotec.mobile.android.fgvoip.FgVoIP;
import com.fgmicrotec.mobile.android.fgvoip.f.h;
import com.fgmicrotec.mobile.android.fgvoip.f.k;
import com.mavenir.android.settings.c.c;
import com.mavenir.android.settings.c.p;

public class PreferenceCallActivity extends PreferenceActivity implements OnPreferenceChangeListener, OnPreferenceClickListener {
    private PreferenceScreen a;
    private PreferenceCategory b;
    private EditTextPreference c;
    private EditTextPreference d;
    private EditTextPreference e;
    private CheckBoxPreference f;

    @TargetApi(11)
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (VERSION.SDK_INT >= 11) {
            ActionBar actionBar = getActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(k.preference_call_title);
            actionBar.setSubtitle(getString(k.preference_current_profile) + p.d());
        }
        a();
    }

    public boolean onPreferenceClick(Preference preference) {
        if (preference instanceof CheckBoxPreference) {
            boolean isChecked = ((CheckBoxPreference) preference).isChecked();
            if (preference.getKey() == "call_enable_ussd") {
                c.a(isChecked);
                return true;
            }
        }
        return false;
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        if (preference instanceof EditTextPreference) {
            String str = (String) obj;
            EditTextPreference editTextPreference = (EditTextPreference) preference;
            editTextPreference.setSummary(str);
            if (editTextPreference.getKey() == "call_uri_template") {
                c.b(str);
                return true;
            } else if (editTextPreference.getKey() == "call_pickup_template") {
                c.d(str);
                return true;
            } else if (editTextPreference.getKey() == "call_conference_factory_uri") {
                c.e(str);
                return true;
            }
        }
        return false;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case 16908332:
                FgVoIP.U().c((Activity) this);
                break;
        }
        return true;
    }

    private void a() {
        this.a = getPreferenceManager().createPreferenceScreen(this);
        this.b = new PreferenceCategory(this);
        this.b.setTitle(k.preference_call_category);
        this.a.addPreference(this.b);
        this.c = new EditTextPreference(this);
        this.c.setKey("call_uri_template");
        this.c.setTitle(k.preference_call_uri_template);
        this.c.setPersistent(false);
        this.c.setSummary(c.b());
        this.c.setDefaultValue(c.b());
        this.c.setOnPreferenceChangeListener(this);
        this.b.addPreference(this.c);
        this.d = new EditTextPreference(this);
        this.d.setKey("call_pickup_template");
        this.d.setTitle(k.preference_call_pickup_template);
        this.d.setPersistent(false);
        this.d.setSummary(c.c());
        this.d.setDefaultValue(c.c());
        this.d.setOnPreferenceChangeListener(this);
        this.b.addPreference(this.d);
        this.e = new EditTextPreference(this);
        this.e.setKey("call_conference_factory_uri");
        this.e.setTitle(k.preference_call_conference_factory_uri);
        this.e.setPersistent(false);
        this.e.setSummary(c.d());
        this.e.setDefaultValue(c.d());
        this.e.setOnPreferenceChangeListener(this);
        this.b.addPreference(this.e);
        if (FgVoIP.U().l()) {
            this.f = new CheckBoxPreference(this);
            this.f.setLayoutResource(h.custom_checkbox_preference);
            this.f.setKey("call_enable_ussd");
            this.f.setTitle(k.preference_call_enable_ussd);
            this.f.setPersistent(false);
            this.f.setChecked(c.g());
            this.f.setSummaryOff(k.value_off);
            this.f.setSummaryOn(k.value_on);
            this.f.setOnPreferenceClickListener(this);
            this.b.addPreference(this.f);
        }
        setPreferenceScreen(this.a);
    }
}
