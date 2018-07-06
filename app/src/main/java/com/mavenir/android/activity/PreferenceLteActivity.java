package com.mavenir.android.activity;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceCategory;
import android.preference.PreferenceScreen;
import android.widget.Toast;
import com.fgmicrotec.mobile.android.fgvoip.f.k;
import com.mavenir.android.common.q;
import com.mavenir.android.common.t;
import com.mavenir.android.settings.c.l;
import com.mavenir.android.settings.c.p;

public class PreferenceLteActivity extends PreferenceActivity implements OnPreferenceChangeListener {
    private PreferenceScreen a;
    private PreferenceCategory b;
    private EditTextPreference c;
    private EditTextPreference d;
    private EditTextPreference e;
    private EditTextPreference f;
    private EditTextPreference g;

    @TargetApi(11)
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (VERSION.SDK_INT >= 11) {
            ActionBar actionBar = getActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(k.preference_lte_title);
            actionBar.setSubtitle(getString(k.preference_current_profile) + p.d());
        }
        a();
    }

    private void a() {
        this.a = getPreferenceManager().createPreferenceScreen(this);
        setPreferenceScreen(this.a);
        this.b = new PreferenceCategory(this);
        this.b.setTitle(k.preference_lte_category);
        this.a.addPreference(this.b);
        Preference createPreferenceScreen = getPreferenceManager().createPreferenceScreen(this);
        createPreferenceScreen.setIntent(new Intent(this, PreferenceSbcLteListActivity.class));
        createPreferenceScreen.setTitle(k.preference_lte_sbc_category);
        this.b.addPreference(createPreferenceScreen);
        this.c = new EditTextPreference(this);
        this.c.setKey("sip_port");
        this.c.setDialogTitle(k.preference_lte_sip_port);
        this.c.setTitle(k.preference_lte_sip_port);
        this.c.setPersistent(false);
        t.a(this.c, 5);
        this.c.setSummary(Integer.toString(l.g()));
        this.c.setDefaultValue(Integer.toString(l.g()));
        this.c.setOnPreferenceChangeListener(this);
        this.c.getEditText().setInputType(2);
        this.b.addPreference(this.c);
        this.d = new EditTextPreference(this);
        this.d.setKey("mnc");
        this.d.setDialogTitle(k.preference_lte_mnc);
        this.d.setTitle(k.preference_lte_mnc);
        this.d.setPersistent(false);
        t.a(this.d, 5);
        this.d.setSummary(Integer.toString(l.h()));
        this.d.setDefaultValue(Integer.toString(l.h()));
        this.d.setOnPreferenceChangeListener(this);
        this.d.getEditText().setInputType(2);
        this.b.addPreference(this.d);
        this.e = new EditTextPreference(this);
        this.e.setKey("mcc");
        this.e.setDialogTitle(k.preference_lte_mcc);
        this.e.setTitle(k.preference_lte_mcc);
        this.e.setPersistent(false);
        t.a(this.e, 5);
        this.e.setSummary(Integer.toString(l.i()));
        this.e.setDefaultValue(Integer.toString(l.i()));
        this.e.setOnPreferenceChangeListener(this);
        this.e.getEditText().setInputType(2);
        this.b.addPreference(this.e);
        this.f = new EditTextPreference(this);
        this.f.setKey("tac_min");
        this.f.setDialogTitle(k.preference_lte_tac_min);
        this.f.setTitle(k.preference_lte_tac_min);
        this.f.setPersistent(false);
        t.a(this.f, 5);
        this.f.setSummary(Integer.toString(l.j()));
        this.f.setDefaultValue(Integer.toString(l.j()));
        this.f.setOnPreferenceChangeListener(this);
        this.f.getEditText().setInputType(2);
        this.b.addPreference(this.f);
        this.g = new EditTextPreference(this);
        this.g.setKey("tac_max");
        this.g.setDialogTitle(k.preference_lte_tac_max);
        this.g.setTitle(k.preference_lte_tac_max);
        this.g.setPersistent(false);
        t.a(this.g, 5);
        this.g.setSummary(Integer.toString(l.k()));
        this.g.setDefaultValue(Integer.toString(l.k()));
        this.g.setOnPreferenceChangeListener(this);
        this.g.getEditText().setInputType(2);
        this.b.addPreference(this.g);
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        if (!(preference instanceof EditTextPreference)) {
            return false;
        }
        String str = (String) obj;
        EditTextPreference editTextPreference = (EditTextPreference) preference;
        if (editTextPreference.getEditText().getInputType() == 2) {
            try {
                editTextPreference.setSummary(String.valueOf(Integer.valueOf(str).intValue()));
            } catch (NumberFormatException e) {
                q.c("PreferenceLteActivity", e.getLocalizedMessage(), e.getCause());
                Toast.makeText(this, getString(k.invalid_value), 0).show();
                return false;
            }
        }
        editTextPreference.setSummary(str);
        if (editTextPreference.getKey() == "mnc") {
            l.b(Integer.valueOf(str).intValue());
            return true;
        } else if (editTextPreference.getKey() == "mcc") {
            l.c(Integer.valueOf(str).intValue());
            return true;
        } else if (editTextPreference.getKey() == "tac_min") {
            l.d(Integer.valueOf(str).intValue());
            return true;
        } else if (editTextPreference.getKey() == "tac_max") {
            l.e(Integer.valueOf(str).intValue());
            return true;
        } else if (editTextPreference.getKey() != "sip_port") {
            return false;
        } else {
            l.a(Integer.valueOf(str).intValue());
            return true;
        }
    }
}
