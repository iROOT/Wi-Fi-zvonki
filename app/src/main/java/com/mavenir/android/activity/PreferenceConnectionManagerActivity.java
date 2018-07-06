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
import android.widget.Toast;
import com.fgmicrotec.mobile.android.fgvoip.FgVoIP;
import com.fgmicrotec.mobile.android.fgvoip.f.k;
import com.fgmicrotec.mobile.android.fgvoipcommon.d;
import com.mavenir.android.common.q;
import com.mavenir.android.common.t;

public class PreferenceConnectionManagerActivity extends PreferenceActivity implements OnPreferenceChangeListener, OnPreferenceClickListener {
    private PreferenceScreen a;
    private d b;
    private PreferenceCategory c;
    private CheckBoxPreference d;
    private EditTextPreference e;
    private EditTextPreference f;
    private EditTextPreference g;
    private EditTextPreference h;
    private EditTextPreference i;

    @TargetApi(11)
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (VERSION.SDK_INT >= 11) {
            ActionBar actionBar = getActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("ROKE Connection Manager Setup");
        }
        this.b = new d(this);
        a();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case 16908332:
                FgVoIP.U().c((Activity) this);
                break;
        }
        return true;
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        if (preference instanceof EditTextPreference) {
            String str = (String) obj;
            EditTextPreference editTextPreference = (EditTextPreference) preference;
            if (editTextPreference.getEditText().getInputType() == 2) {
                try {
                    Integer.valueOf(str);
                    editTextPreference.setSummary(String.valueOf(Integer.valueOf(str)));
                } catch (NumberFormatException e) {
                    q.c("PreferenceConnectionManagerActivity", e.getLocalizedMessage(), e.getCause());
                    Toast.makeText(this, getString(k.invalid_value), 0).show();
                    return false;
                }
            }
            editTextPreference.setSummary(str);
            if (preference.getKey() == "roke_wifi_rssi_threshold") {
                d.a(Integer.valueOf(str).intValue());
                return true;
            } else if (preference.getKey() == "roke_round_trip_delay_threshold") {
                d.b(Integer.valueOf(str).intValue());
                return true;
            } else if (preference.getKey() == "roke_jitter_threshold") {
                d.c(Integer.valueOf(str).intValue());
                return true;
            } else if (preference.getKey() == "roke_fractional_loss_threshold") {
                d.d(Integer.valueOf(str).intValue());
                return true;
            } else if (preference.getKey() == "roke_cumulative_loss_threshold") {
                d.e(Integer.valueOf(str).intValue());
                return true;
            }
        }
        return false;
    }

    private void a() {
        this.a = getPreferenceManager().createPreferenceScreen(this);
        this.c = new PreferenceCategory(this);
        this.c.setTitle("ROKE Connection Manager Config");
        this.a.addPreference(this.c);
        this.d = new CheckBoxPreference(this);
        this.d.setKey("roke_enabled");
        this.d.setTitle("Use ROKE Connection Manager");
        this.d.setPersistent(false);
        this.d.setChecked(d.a());
        this.d.setEnabled(true);
        this.d.setSummaryOff("Connection Manager Off");
        this.d.setSummaryOn("Connection Manager On");
        this.d.setOnPreferenceClickListener(this);
        this.c.addPreference(this.d);
        this.e = new EditTextPreference(this);
        this.e.setKey("roke_wifi_rssi_threshold");
        this.e.setDialogTitle("WiFi RSSI Threshold");
        this.e.setTitle("WiFi RSSI Threshold");
        this.e.setPersistent(false);
        t.a(this.e, 3);
        this.e.setSummary(Integer.toString(d.b()));
        this.e.setDefaultValue(Integer.toString(d.b()));
        this.e.setOnPreferenceChangeListener(this);
        this.e.getEditText().setInputType(2);
        this.c.addPreference(this.e);
        this.f = new EditTextPreference(this);
        this.f.setKey("roke_round_trip_delay_threshold");
        this.f.setDialogTitle("Round Trip Delay Threshold");
        this.f.setTitle("Round Trip Delay Threshold");
        this.f.setPersistent(false);
        t.a(this.f, 4);
        this.f.setSummary(Integer.toString(d.c()));
        this.f.setDefaultValue(Integer.toString(d.c()));
        this.f.setOnPreferenceChangeListener(this);
        this.f.getEditText().setInputType(2);
        this.c.addPreference(this.f);
        this.g = new EditTextPreference(this);
        this.g.setKey("roke_jitter_threshold");
        this.g.setDialogTitle("Jitter Threshold");
        this.g.setTitle("Jitter Threshold");
        this.g.setPersistent(false);
        t.a(this.g, 4);
        this.g.setSummary(Integer.toString(d.d()));
        this.g.setDefaultValue(Integer.toString(d.d()));
        this.g.setOnPreferenceChangeListener(this);
        this.g.getEditText().setInputType(2);
        this.c.addPreference(this.g);
        this.h = new EditTextPreference(this);
        this.h.setKey("roke_fractional_loss_threshold");
        this.h.setDialogTitle("Fractional Loss Threshold");
        this.h.setTitle("Fractional Loss Threshold");
        this.h.setPersistent(false);
        t.a(this.h, 2);
        this.h.setSummary(Integer.toString(d.e()));
        this.h.setDefaultValue(Integer.toString(d.e()));
        this.h.setOnPreferenceChangeListener(this);
        this.h.getEditText().setInputType(2);
        this.c.addPreference(this.h);
        this.i = new EditTextPreference(this);
        this.i.setKey("roke_cumulative_loss_threshold");
        this.i.setDialogTitle("Cumulative Loss Threshold");
        this.i.setTitle("Cumulative Loss Threshold");
        this.i.setPersistent(false);
        t.a(this.i, 5);
        this.i.setSummary(Integer.toString(d.f()));
        this.i.setDefaultValue(Integer.toString(d.f()));
        this.i.setOnPreferenceChangeListener(this);
        this.i.getEditText().setInputType(2);
        this.c.addPreference(this.i);
        setPreferenceScreen(this.a);
    }

    public boolean onPreferenceClick(Preference preference) {
        boolean isChecked = ((CheckBoxPreference) preference).isChecked();
        if (preference.getKey() != "roke_enabled") {
            return false;
        }
        d.a(isChecked);
        return true;
    }
}
