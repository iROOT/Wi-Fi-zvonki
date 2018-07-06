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
import com.fgmicrotec.mobile.android.fgvoip.FgVoIP.a;
import com.fgmicrotec.mobile.android.fgvoip.f.b;
import com.fgmicrotec.mobile.android.fgvoip.f.k;
import com.mavenir.android.common.q;
import com.mavenir.android.common.t;
import com.mavenir.android.settings.c.p;
import com.mavenir.android.settings.c.s;

public class PreferenceRtpActivity extends PreferenceActivity implements OnPreferenceChangeListener, OnPreferenceClickListener {
    private PreferenceScreen a;
    private PreferenceCategory b;
    private EditTextPreference c;
    private EditTextPreference d;
    private CheckBoxPreference e;
    private PreferenceCategory f;
    private CheckBoxPreference g;
    private ListPreference h;
    private ListPreference i;

    @TargetApi(11)
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (VERSION.SDK_INT >= 11) {
            ActionBar actionBar = getActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(k.preference_rtp_title);
            actionBar.setSubtitle(getString(k.preference_current_profile) + p.d());
        }
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
        String str;
        if (preference instanceof EditTextPreference) {
            str = (String) obj;
            EditTextPreference editTextPreference = (EditTextPreference) preference;
            if (editTextPreference.getEditText().getInputType() == 2) {
                try {
                    Integer.valueOf(str);
                    editTextPreference.setSummary(String.valueOf(Integer.valueOf(str)));
                } catch (NumberFormatException e) {
                    q.c("PreferenceRtpActivity", e.getLocalizedMessage(), e.getCause());
                    Toast.makeText(this, getString(k.invalid_value), 0).show();
                    return false;
                }
            }
            editTextPreference.setSummary(str);
            if (editTextPreference.getKey() == "rtp_local_port") {
                s.a(Integer.valueOf(str).intValue());
                return true;
            } else if (editTextPreference.getKey() == "rtp_decoder_timeout") {
                s.b(Integer.valueOf(str).intValue());
                return true;
            }
        } else if (preference instanceof ListPreference) {
            str = (String) obj;
            ListPreference listPreference = (ListPreference) preference;
            int findIndexOfValue = listPreference.findIndexOfValue(str);
            listPreference.setValueIndex(findIndexOfValue);
            listPreference.setSummary(str);
            if (listPreference.getKey() == "srtp_encryption_type") {
                s.c(findIndexOfValue);
                return true;
            } else if (listPreference.getKey() == "srtp_auth_type") {
                s.d(findIndexOfValue);
                return true;
            }
        } else if (preference instanceof CheckBoxPreference) {
            boolean isChecked = ((CheckBoxPreference) preference).isChecked();
            if (preference.getKey() == "rtp_use_rtcp") {
                s.a(isChecked);
            } else if (preference.getKey() == "srtp_mode") {
                s.b(isChecked);
            }
        }
        return false;
    }

    public boolean onPreferenceClick(Preference preference) {
        if (preference instanceof CheckBoxPreference) {
            boolean isChecked = ((CheckBoxPreference) preference).isChecked();
            if (preference.getKey() == "rtp_use_rtcp") {
                s.a(isChecked);
            } else if (preference.getKey() == "srtp_mode") {
                s.b(isChecked);
                this.h.setEnabled(s.f());
                this.i.setEnabled(s.f());
            }
        }
        return false;
    }

    private void a() {
        this.a = getPreferenceManager().createPreferenceScreen(this);
        this.b = new PreferenceCategory(this);
        this.b.setTitle(k.preference_rtp_category);
        this.a.addPreference(this.b);
        this.c = new EditTextPreference(this);
        this.c.setKey("rtp_local_port");
        this.c.setDialogTitle(k.preference_rtp_local_port);
        this.c.setTitle(k.preference_rtp_local_port);
        this.c.setPersistent(false);
        t.a(this.c, 5);
        this.c.setSummary(Integer.toString(s.b()));
        this.c.setDefaultValue(Integer.toString(s.b()));
        this.c.setOnPreferenceChangeListener(this);
        this.c.getEditText().setInputType(2);
        this.b.addPreference(this.c);
        this.d = new EditTextPreference(this);
        this.d.setKey("rtp_decoder_timeout");
        this.d.setDialogTitle(k.preference_rtp_decoder_timeout);
        this.d.setTitle(k.preference_rtp_decoder_timeout);
        this.d.setPersistent(false);
        t.a(this.d, 5);
        this.d.setSummary(Integer.toString(s.c()));
        this.d.setDefaultValue(Integer.toString(s.c()));
        this.d.setOnPreferenceChangeListener(this);
        this.d.getEditText().setInputType(2);
        this.b.addPreference(this.d);
        if (FgVoIP.U().ad() != a.VToW) {
            this.e = new CheckBoxPreference(this);
            this.e.setKey("rtp_use_rtcp");
            this.e.setTitle(k.preference_rtp_use_rtcp);
            this.e.setPersistent(false);
            this.e.setChecked(s.d());
            this.e.setSummaryOff(k.value_off);
            this.e.setSummaryOn(k.value_on);
            this.e.setOnPreferenceClickListener(this);
            this.b.addPreference(this.e);
        }
        this.f = new PreferenceCategory(this);
        this.f.setTitle(k.preference_srtp_category);
        this.a.addPreference(this.f);
        this.g = new CheckBoxPreference(this);
        this.g.setKey("srtp_mode");
        this.g.setTitle(k.preference_srtp_mode);
        this.g.setPersistent(false);
        this.g.setChecked(s.f());
        this.g.setSummaryOff(k.preference_srtp_mode_disabled);
        this.g.setSummaryOn(k.preference_srtp_mode_enabled);
        this.g.setOnPreferenceClickListener(this);
        this.f.addPreference(this.g);
        this.h = new ListPreference(this);
        this.h.setKey("srtp_encryption_type");
        this.h.setDialogTitle(k.preference_srtp_encryption_type);
        this.h.setTitle(k.preference_srtp_encryption_type);
        this.h.setEntries(b.srtp_encryption_type);
        this.h.setEntryValues(b.srtp_encryption_type);
        this.h.setPersistent(false);
        this.h.setEnabled(this.g.isChecked());
        this.h.setValueIndex(s.g());
        this.h.setSummary(this.h.getEntry());
        this.h.setOnPreferenceChangeListener(this);
        this.f.addPreference(this.h);
        this.i = new ListPreference(this);
        this.i.setKey("srtp_auth_type");
        this.i.setDialogTitle(k.preference_srtp_auth_type);
        this.i.setTitle(k.preference_srtp_auth_type);
        this.i.setEntries(b.srtp_auth_type);
        this.i.setEntryValues(b.srtp_auth_type);
        this.i.setPersistent(false);
        this.i.setEnabled(this.g.isChecked());
        this.i.setValueIndex(s.h());
        this.i.setSummary(this.i.getEntry());
        this.i.setOnPreferenceChangeListener(this);
        this.f.addPreference(this.i);
        setPreferenceScreen(this.a);
    }
}
