package com.mavenir.android.activity;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceCategory;
import android.preference.PreferenceScreen;
import android.view.MenuItem;
import android.widget.Toast;
import com.fgmicrotec.mobile.android.fgvoip.FgVoIP;
import com.fgmicrotec.mobile.android.fgvoip.f.b;
import com.fgmicrotec.mobile.android.fgvoip.f.k;
import com.mavenir.android.common.q;
import com.mavenir.android.common.t;
import com.mavenir.android.settings.c;
import com.mavenir.android.settings.c.p;
import com.mavenir.android.settings.c.z;
import net.hockeyapp.android.views.UpdateView;

public class PreferenceWifiActivity extends PreferenceActivity implements OnPreferenceChangeListener, OnPreferenceClickListener {
    private PreferenceScreen a;
    private PreferenceCategory b;
    private ListPreference c;
    private CheckBoxPreference d;
    private PreferenceCategory e;
    private EditTextPreference f;
    private EditTextPreference g;
    private EditTextPreference h;
    private PreferenceCategory i;
    private EditTextPreference j;
    private EditTextPreference k;
    private EditTextPreference l;
    private boolean m = false;

    @TargetApi(11)
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (VERSION.SDK_INT >= 11) {
            ActionBar actionBar = getActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(k.preference_wifi_title);
            actionBar.setSubtitle(getString(k.preference_current_profile) + p.d());
        }
        a();
    }

    public boolean onPreferenceClick(Preference preference) {
        if (preference instanceof CheckBoxPreference) {
            boolean isChecked = ((CheckBoxPreference) preference).isChecked();
            if (preference.getKey() == "wifi_call_manual_selection") {
                z.a(isChecked);
                return true;
            }
        }
        return false;
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        String str;
        if (preference instanceof ListPreference) {
            str = (String) obj;
            ListPreference listPreference = (ListPreference) preference;
            int findIndexOfValue = listPreference.findIndexOfValue(str);
            listPreference.setValueIndex(findIndexOfValue);
            listPreference.setSummary(str);
            if (listPreference.getKey() == "connection_preference") {
                z.a(findIndexOfValue);
                return true;
            }
        } else if (preference instanceof EditTextPreference) {
            str = (String) obj;
            EditTextPreference editTextPreference = (EditTextPreference) preference;
            if (editTextPreference.getEditText().getInputType() == 2 || editTextPreference.getEditText().getInputType() == UpdateView.NAME_LABEL_ID) {
                try {
                    Integer.valueOf(str);
                    editTextPreference.setSummary(String.valueOf(Integer.valueOf(str)));
                } catch (NumberFormatException e) {
                    q.c("PreferenceWifiActivity", e.getLocalizedMessage(), e.getCause());
                    Toast.makeText(this, getString(k.invalid_value), 0).show();
                    return false;
                }
            }
            editTextPreference.setSummary(str);
            if (editTextPreference.getKey() == "wifi_weak_upper_threshold") {
                z.c(Integer.valueOf(str).intValue());
                return true;
            } else if (editTextPreference.getKey() == "wifi_weak_lower_threshold") {
                z.b(Integer.valueOf(str).intValue());
                return true;
            } else if (editTextPreference.getKey() == "wifi_weak_timeout") {
                z.d(Integer.valueOf(str).intValue());
                return true;
            } else if (editTextPreference.getKey() == "hs_lost_timer") {
                c.q.e(Integer.valueOf(str).intValue());
                return true;
            } else if (editTextPreference.getKey() == "wifi_hysteresis_timer") {
                c.q.c(Integer.valueOf(str).intValue());
                return true;
            } else if (editTextPreference.getKey() == "min_wifi_threshold") {
                c.q.b(Integer.valueOf(str).intValue());
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
        this.m = FgVoIP.U().aj();
        if (this.m) {
            this.b = new PreferenceCategory(this);
            this.b.setTitle(k.preference_wifi_category);
            this.a.addPreference(this.b);
            this.c = new ListPreference(this);
            this.c.setKey("connection_preference");
            this.c.setDialogTitle(k.preference_wifi_connection_preference);
            this.c.setTitle(k.preference_wifi_connection_preference);
            this.c.setEntries(b.connection_preference);
            this.c.setEntryValues(b.connection_preference);
            this.c.setPersistent(false);
            this.c.setValueIndex(z.b());
            this.c.setSummary(this.c.getEntry());
            this.c.setOnPreferenceChangeListener(this);
            this.b.addPreference(this.c);
            this.d = new CheckBoxPreference(this);
            this.d.setKey("wifi_call_manual_selection");
            this.d.setTitle(k.preference_wifi_manual_calling);
            this.d.setPersistent(false);
            this.d.setChecked(z.c());
            this.d.setSummaryOff(k.value_off);
            this.d.setSummaryOn(k.value_on);
            this.d.setOnPreferenceClickListener(this);
            this.b.addPreference(this.d);
        }
        if (FgVoIP.U().aj()) {
            this.e = new PreferenceCategory(this);
            this.e.setTitle(k.preference_weak_wifi_category);
            this.a.addPreference(this.e);
            this.f = new EditTextPreference(this);
            this.f.setKey("wifi_weak_upper_threshold");
            this.f.setDialogTitle(k.preference_wifi_upper_threshold);
            this.f.setTitle(k.preference_wifi_upper_threshold);
            this.f.setPersistent(false);
            t.a(this.f, 4);
            this.f.setSummary(Integer.toString(z.e()));
            this.f.setDefaultValue(Integer.toString(z.e()));
            this.f.setOnPreferenceChangeListener(this);
            this.f.getEditText().setInputType(UpdateView.NAME_LABEL_ID);
            this.e.addPreference(this.f);
            this.g = new EditTextPreference(this);
            this.g.setKey("wifi_weak_lower_threshold");
            this.g.setDialogTitle(k.preference_wifi_lower_threshold);
            this.g.setTitle(k.preference_wifi_lower_threshold);
            this.g.setPersistent(false);
            t.a(this.g, 4);
            this.g.setSummary(Integer.toString(z.d()));
            this.g.setDefaultValue(Integer.toString(z.d()));
            this.g.setOnPreferenceChangeListener(this);
            this.g.getEditText().setInputType(UpdateView.NAME_LABEL_ID);
            this.e.addPreference(this.g);
            this.h = new EditTextPreference(this);
            this.h.setKey("wifi_weak_timeout");
            this.h.setDialogTitle(k.preference_weak_wifi_timeout);
            this.h.setTitle(k.preference_weak_wifi_timeout);
            this.h.setPersistent(false);
            t.a(this.h, 4);
            this.h.setSummary(Integer.toString(z.f()));
            this.h.setDefaultValue(Integer.toString(z.f()));
            this.h.setOnPreferenceChangeListener(this);
            this.h.getEditText().setInputType(2);
            this.e.addPreference(this.h);
        }
        if (FgVoIP.U().ai()) {
            this.i = new PreferenceCategory(this);
            this.i.setTitle(k.preference_wifi_thresholds_category);
            this.a.addPreference(this.i);
            this.j = new EditTextPreference(this);
            this.j.setKey("hs_lost_timer");
            this.j.setDialogTitle(k.preference_wifi_hs_lost_timer);
            this.j.setTitle(k.preference_wifi_hs_lost_timer);
            this.j.setPersistent(false);
            t.a(this.j, 5);
            this.j.setSummary(Integer.toString(c.q.g()));
            this.j.setDefaultValue(Integer.toString(c.q.g()));
            this.j.setOnPreferenceChangeListener(this);
            this.j.getEditText().setInputType(UpdateView.NAME_LABEL_ID);
            this.i.addPreference(this.j);
            this.k = new EditTextPreference(this);
            this.k.setKey("wifi_hysteresis_timer");
            this.k.setDialogTitle(k.preference_wifi_hysteresis_timer);
            this.k.setTitle(k.preference_wifi_hysteresis_timer);
            this.k.setPersistent(false);
            t.a(this.k, 5);
            this.k.setSummary(Integer.toString(c.q.e()));
            this.k.setDefaultValue(Integer.toString(c.q.e()));
            this.k.setOnPreferenceChangeListener(this);
            this.k.getEditText().setInputType(UpdateView.NAME_LABEL_ID);
            this.i.addPreference(this.k);
            this.l = new EditTextPreference(this);
            this.l.setKey("min_wifi_threshold");
            this.l.setDialogTitle(k.preference_wifi_min_threshold);
            this.l.setTitle(k.preference_wifi_min_threshold);
            this.l.setPersistent(false);
            t.a(this.l, 5);
            this.l.setSummary(Integer.toString(c.q.d()));
            this.l.setDefaultValue(Integer.toString(c.q.d()));
            this.l.setOnPreferenceChangeListener(this);
            this.l.getEditText().setInputType(UpdateView.NAME_LABEL_ID);
            this.i.addPreference(this.l);
        }
        setPreferenceScreen(this.a);
    }
}
