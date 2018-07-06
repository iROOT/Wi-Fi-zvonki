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
import com.fgmicrotec.mobile.android.fgvoipcommon.e;
import com.mavenir.android.common.q;

public class PreferenceSpiritTestActivity extends PreferenceActivity implements OnPreferenceChangeListener, OnPreferenceClickListener {
    private PreferenceScreen a;
    private e b;
    private PreferenceCategory c;
    private CheckBoxPreference d;
    private CheckBoxPreference e;
    private CheckBoxPreference f;

    @TargetApi(11)
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (VERSION.SDK_INT >= 11) {
            ActionBar actionBar = getActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("SPIRIT Test Setup");
        }
        this.b = new e(this);
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
                    q.c("PreferenceSpiritTestActivity", e.getLocalizedMessage(), e.getCause());
                    Toast.makeText(this, getString(k.invalid_value), 0).show();
                    return false;
                }
            }
            editTextPreference.setSummary(str);
            if (preference.getKey() == "spirit_video_packetization") {
                e.a(Integer.valueOf(str).intValue());
                return true;
            }
        }
        return false;
    }

    public boolean onPreferenceClick(Preference preference) {
        if (preference instanceof CheckBoxPreference) {
            boolean isChecked = ((CheckBoxPreference) preference).isChecked();
            if (preference.getKey() == "spirit_audio_use") {
                e.a(isChecked);
                return true;
            } else if (preference.getKey() == "spirit_audio_ars") {
                e.b(isChecked);
                return true;
            } else if (preference.getKey() == "spirit_video_ars") {
                e.c(isChecked);
                return true;
            } else if (preference.getKey() == "spirit_logs") {
                if (!isChecked) {
                    FgVoIP.U().aL();
                }
                e.d(isChecked);
                return true;
            }
        }
        return false;
    }

    private void a() {
        this.a = getPreferenceManager().createPreferenceScreen(this);
        this.c = new PreferenceCategory(this);
        this.c.setTitle("SPIRIT Config");
        this.a.addPreference(this.c);
        this.d = new CheckBoxPreference(this);
        this.d.setKey("spirit_audio_use");
        this.d.setTitle("SPIRIT Audio Use");
        this.d.setPersistent(false);
        this.d.setChecked(e.a());
        this.d.setEnabled(true);
        this.d.setSummaryOff("Spirit Audio Off");
        this.d.setSummaryOn("Spirit Audio On");
        this.d.setOnPreferenceClickListener(this);
        this.c.addPreference(this.d);
        this.e = new CheckBoxPreference(this);
        this.e.setKey("spirit_audio_ars");
        this.e.setTitle("SPIRIT Audio ARS");
        this.e.setPersistent(false);
        this.e.setChecked(e.b());
        this.e.setEnabled(false);
        this.e.setSummaryOff("Audio ARS Off");
        this.e.setSummaryOn("Audio ARS On");
        this.e.setOnPreferenceClickListener(this);
        this.c.addPreference(this.e);
        this.f = new CheckBoxPreference(this);
        this.f.setKey("spirit_logs");
        this.f.setTitle("SPIRIT Logs");
        this.f.setPersistent(false);
        this.f.setChecked(e.e());
        this.f.setEnabled(true);
        this.f.setSummaryOff("Spirit Logs Off");
        this.f.setSummaryOn("Spirit Logs On");
        this.f.setOnPreferenceClickListener(this);
        this.c.addPreference(this.f);
        setPreferenceScreen(this.a);
    }
}
