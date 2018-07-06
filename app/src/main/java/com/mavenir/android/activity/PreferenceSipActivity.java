package com.mavenir.android.activity;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
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
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.Toast;
import com.fgmicrotec.mobile.android.fgvoip.FgVoIP;
import com.fgmicrotec.mobile.android.fgvoip.f.b;
import com.fgmicrotec.mobile.android.fgvoip.f.k;
import com.mavenir.android.common.q;
import com.mavenir.android.common.t;
import com.mavenir.android.settings.c.p;
import com.mavenir.android.settings.c.v;

public class PreferenceSipActivity extends PreferenceActivity implements OnPreferenceChangeListener, OnPreferenceClickListener {
    private PreferenceScreen a;
    private PreferenceCategory b;
    private EditTextPreference c;
    private EditTextPreference d;
    private EditTextPreference e;
    private EditTextPreference f;
    private EditTextPreference g;
    private PreferenceCategory h;
    private EditTextPreference i;
    private EditTextPreference j;
    private EditTextPreference k;
    private ListPreference l;
    private PreferenceCategory m;
    private ListPreference n;

    @TargetApi(11)
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (VERSION.SDK_INT >= 11) {
            ActionBar actionBar = getActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(k.preference_sip_title);
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
        if (preference instanceof EditTextPreference) {
            String str = (String) obj;
            EditTextPreference editTextPreference = (EditTextPreference) preference;
            if (editTextPreference.getEditText().getInputType() == 2) {
                try {
                    Integer.valueOf(str);
                    editTextPreference.setSummary(String.valueOf(Integer.valueOf(str)));
                } catch (NumberFormatException e) {
                    q.c("PreferenceSipActivity", e.getLocalizedMessage(), e.getCause());
                    Toast.makeText(this, getString(k.invalid_value), 0).show();
                    return false;
                }
            }
            editTextPreference.setSummary(str);
            if (editTextPreference.getKey() == "sip_proxy1") {
                String str2 = null;
                if (FgVoIP.U().ai()) {
                    str2 = this.d.getSummary().toString();
                }
                a(str, str2);
                return true;
            } else if (editTextPreference.getKey() == "sip_proxy2") {
                a(this.c.getSummary().toString(), str);
                return true;
            } else if (editTextPreference.getKey() == "sip_port") {
                v.a(Integer.valueOf(str).intValue());
                return true;
            } else if (editTextPreference.getKey() == "sip_local_port") {
                v.b(Integer.valueOf(str).intValue());
                return true;
            } else if (editTextPreference.getKey() == "sip_reg_exp_time") {
                v.c(Integer.valueOf(str).intValue());
                return true;
            } else if (editTextPreference.getKey() == "session_expires_time") {
                v.f(Integer.valueOf(str).intValue());
                return true;
            } else if (editTextPreference.getKey() == "min_session_expires_time") {
                v.g(Integer.valueOf(str).intValue());
                return true;
            } else if (editTextPreference.getKey() == "session_refresher") {
                v.h(Integer.valueOf(str).intValue());
                return true;
            }
        }
        if (preference instanceof ListPreference) {
            String str3 = (String) obj;
            ListPreference listPreference = (ListPreference) preference;
            int findIndexOfValue = listPreference.findIndexOfValue(str3);
            listPreference.setValueIndex(findIndexOfValue);
            listPreference.setSummary(str3);
            if (listPreference.getKey() == "sip_uri_format") {
                v.d(findIndexOfValue);
                return true;
            } else if (listPreference.getKey() == "sip_transport") {
                v.e(findIndexOfValue);
                return true;
            }
        }
        return false;
    }

    public boolean onPreferenceClick(Preference preference) {
        if (preference instanceof CheckBoxPreference) {
            boolean isChecked = ((CheckBoxPreference) preference).isChecked();
            if (preference.getKey() == "sip_route") {
                v.a(isChecked);
            }
        }
        return false;
    }

    private void a() {
        this.a = getPreferenceManager().createPreferenceScreen(this);
        this.b = new PreferenceCategory(this);
        this.b.setTitle(k.preference_sip_connection_category);
        this.a.addPreference(this.b);
        if (FgVoIP.U().ai()) {
            Preference createPreferenceScreen = getPreferenceManager().createPreferenceScreen(this);
            createPreferenceScreen.setIntent(new Intent(this, PreferenceSbcListActivity.class));
            createPreferenceScreen.setTitle(k.preference_sip_sbc_category);
            this.b.addPreference(createPreferenceScreen);
        } else {
            this.c = new EditTextPreference(this);
            this.c.setKey("sip_proxy1");
            this.c.setDialogTitle(k.preference_sip_sbc1);
            this.c.setTitle(k.preference_sip_sbc1);
            this.c.setPersistent(false);
            CharSequence a = a(0);
            this.c.setSummary(a);
            this.c.setDefaultValue(a);
            this.c.setOnPreferenceChangeListener(this);
            this.c.getEditText().setInputType(1);
            this.b.addPreference(this.c);
        }
        this.e = new EditTextPreference(this);
        this.e.setKey("sip_port");
        this.e.setDialogTitle(k.preference_sip_port);
        this.e.setTitle(k.preference_sip_port);
        this.e.setPersistent(false);
        t.a(this.e, 5);
        this.e.setSummary(Integer.toString(v.b()));
        this.e.setDefaultValue(Integer.toString(v.b()));
        this.e.setOnPreferenceChangeListener(this);
        this.e.getEditText().setInputType(2);
        this.b.addPreference(this.e);
        this.f = new EditTextPreference(this);
        this.f.setKey("sip_local_port");
        this.f.setDialogTitle(k.preference_sip_local_port);
        this.f.setTitle(k.preference_sip_local_port);
        this.f.setPersistent(false);
        t.a(this.f, 5);
        this.f.setSummary(Integer.toString(v.e()));
        this.f.setDefaultValue(Integer.toString(v.e()));
        this.f.setOnPreferenceChangeListener(this);
        this.f.getEditText().setInputType(2);
        this.b.addPreference(this.f);
        this.g = new EditTextPreference(this);
        this.g.setKey("sip_reg_exp_time");
        this.g.setDialogTitle(k.preference_sip_reg_exp_time);
        this.g.setTitle(k.preference_sip_reg_exp_time);
        this.g.setPersistent(false);
        t.a(this.g, 5);
        this.g.setSummary(Integer.toString(v.f()));
        this.g.setDefaultValue(Integer.toString(v.f()));
        this.g.setOnPreferenceChangeListener(this);
        this.g.getEditText().setInputType(2);
        this.b.addPreference(this.g);
        this.l = new ListPreference(this);
        this.l.setKey("sip_uri_format");
        this.l.setDialogTitle(k.preference_sip_uri_format);
        this.l.setTitle(k.preference_sip_uri_format);
        this.l.setEntries(b.sip_uri_format);
        this.l.setEntryValues(b.sip_uri_format);
        this.l.setPersistent(false);
        this.l.setValueIndex(v.g());
        this.l.setSummary(this.l.getEntry());
        this.l.setOnPreferenceChangeListener(this);
        this.b.addPreference(this.l);
        this.h = new PreferenceCategory(this);
        this.h.setTitle(k.preference_sip_session_category);
        this.a.addPreference(this.h);
        this.i = new EditTextPreference(this);
        this.i.setKey("session_expires_time");
        this.i.setDialogTitle(k.preference_sip_session_exp_time);
        this.i.setTitle(k.preference_sip_session_exp_time);
        this.i.setPersistent(false);
        t.a(this.i, 5);
        this.i.setSummary(Integer.toString(v.j()));
        this.i.setDefaultValue(Integer.toString(v.j()));
        this.i.setOnPreferenceChangeListener(this);
        this.i.getEditText().setInputType(2);
        this.h.addPreference(this.i);
        this.j = new EditTextPreference(this);
        this.j.setKey("min_session_expires_time");
        this.j.setDialogTitle(k.preference_sip_session_min_exp_time);
        this.j.setTitle(k.preference_sip_session_min_exp_time);
        this.j.setPersistent(false);
        t.a(this.j, 5);
        this.j.setSummary(Integer.toString(v.k()));
        this.j.setDefaultValue(Integer.toString(v.k()));
        this.j.setOnPreferenceChangeListener(this);
        this.j.getEditText().setInputType(2);
        this.h.addPreference(this.j);
        this.k = new EditTextPreference(this);
        this.k.setKey("session_refresher");
        this.k.setDialogTitle(k.preference_sip_session_refresher);
        this.k.setTitle(k.preference_sip_session_refresher);
        this.k.setPersistent(false);
        t.a(this.k, 5);
        this.k.setSummary(Integer.toString(v.l()));
        this.k.setDefaultValue(Integer.toString(v.l()));
        this.k.setOnPreferenceChangeListener(this);
        this.k.getEditText().setInputType(2);
        this.h.addPreference(this.k);
        this.m = new PreferenceCategory(this);
        this.m.setTitle(k.preference_sip_transport_category);
        this.a.addPreference(this.m);
        this.n = new ListPreference(this);
        this.n.setKey("sip_transport");
        this.n.setDialogTitle(k.preference_sip_transport);
        this.n.setTitle(k.preference_sip_transport);
        this.n.setEntries(b.transport_protocol);
        this.n.setEntryValues(b.transport_protocol);
        this.n.setPersistent(false);
        this.n.setValueIndex(v.h());
        this.n.setSummary(this.n.getEntry());
        this.n.setOnPreferenceChangeListener(this);
        this.m.addPreference(this.n);
        setPreferenceScreen(this.a);
    }

    private void a(String str, String str2) {
        String[] strArr = TextUtils.isEmpty(str) ? new String[]{str2} : TextUtils.isEmpty(str2) ? new String[]{str} : new String[]{str, str2};
        v.a(strArr);
    }

    private String a(int i) {
        String[] c = v.c();
        if (c.length >= i + 1) {
            return c[i];
        }
        return "";
    }
}
