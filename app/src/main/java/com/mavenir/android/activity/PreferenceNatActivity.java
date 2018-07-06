package com.mavenir.android.activity;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
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
import com.mavenir.android.common.q;
import com.mavenir.android.common.t;
import com.mavenir.android.settings.c.n;
import com.mavenir.android.settings.c.p;

public class PreferenceNatActivity extends PreferenceActivity implements OnPreferenceChangeListener {
    private PreferenceScreen a;
    private PreferenceCategory b;
    private ListPreference c;
    private PreferenceCategory d;
    private EditTextPreference e;
    private PreferenceCategory f;
    private EditTextPreference g;
    private EditTextPreference h;
    private EditTextPreference i;

    @TargetApi(11)
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (VERSION.SDK_INT >= 11) {
            ActionBar actionBar = getActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(k.preference_nat_title);
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
                    q.c("PreferenceNatActivity", e.getLocalizedMessage(), e.getCause());
                    Toast.makeText(this, getString(k.invalid_value), 0).show();
                    return false;
                }
            }
            editTextPreference.setSummary(str);
            if (editTextPreference.getKey() == "nat_stun_server") {
                n.a(str);
                return true;
            } else if (editTextPreference.getKey() == "nat_stun_port") {
                n.b(Integer.valueOf(str).intValue());
                return true;
            } else if (editTextPreference.getKey() == "nat_sip_keep_alive") {
                n.d(Integer.valueOf(str).intValue());
                return true;
            } else if (editTextPreference.getKey() == "tcp_keep_alive_idle_time") {
                n.e(Integer.valueOf(str).intValue());
                return true;
            } else if (editTextPreference.getKey() == "tcp_keep_alive_probe_time") {
                n.f(Integer.valueOf(str).intValue());
                return true;
            } else if (editTextPreference.getKey() != "tcp_keep_alive_probes_count") {
                return false;
            } else {
                n.g(Integer.valueOf(str).intValue());
                return true;
            }
        } else if (!(preference instanceof ListPreference)) {
            return false;
        } else {
            str = (String) obj;
            ListPreference listPreference = (ListPreference) preference;
            int findIndexOfValue = listPreference.findIndexOfValue(str);
            listPreference.setValueIndex(findIndexOfValue);
            listPreference.setSummary(str);
            if (listPreference.getKey() == "nat_traversal_mode") {
                n.a(findIndexOfValue);
                return true;
            } else if (listPreference.getKey() != "nat_stun_query_mode") {
                return false;
            } else {
                n.c(findIndexOfValue);
                return true;
            }
        }
    }

    private void a() {
        this.a = getPreferenceManager().createPreferenceScreen(this);
        this.b = new PreferenceCategory(this);
        this.b.setTitle(k.preference_nat_traversal_category);
        this.a.addPreference(this.b);
        this.c = new ListPreference(this);
        this.c.setKey("nat_traversal_mode");
        this.c.setDialogTitle(k.preference_nat_traversal_mode);
        this.c.setTitle(k.preference_nat_traversal_mode);
        this.c.setEntries(b.nat_traversal_mode);
        this.c.setEntryValues(b.nat_traversal_mode);
        this.c.setPersistent(false);
        this.c.setValueIndex(n.b());
        this.c.setSummary(this.c.getEntry());
        this.c.setOnPreferenceChangeListener(this);
        this.b.addPreference(this.c);
        this.d = new PreferenceCategory(this);
        this.d.setTitle(k.preference_sip_keep_alive_category);
        this.a.addPreference(this.d);
        this.e = new EditTextPreference(this);
        this.e.setKey("nat_sip_keep_alive");
        this.e.setDialogTitle(k.preference_nat_sip_keep_alive);
        this.e.setTitle(k.preference_nat_sip_keep_alive);
        this.e.setPersistent(false);
        t.a(this.e, 6);
        this.e.setSummary(Integer.toString(n.e()));
        this.e.setDefaultValue(Integer.toString(n.e()));
        this.e.setOnPreferenceChangeListener(this);
        this.e.getEditText().setInputType(2);
        this.d.addPreference(this.e);
        this.f = new PreferenceCategory(this);
        this.f.setTitle(k.preference_tcp_keep_alive_category);
        this.a.addPreference(this.f);
        this.g = new EditTextPreference(this);
        this.g.setKey("tcp_keep_alive_idle_time");
        this.g.setDialogTitle(k.preference_tcp_keep_alive_idle_time);
        this.g.setTitle(k.preference_tcp_keep_alive_idle_time);
        this.g.setPersistent(false);
        t.a(this.g, 5);
        this.g.setSummary(Integer.toString(n.f()));
        this.g.setDefaultValue(Integer.toString(n.f()));
        this.g.setOnPreferenceChangeListener(this);
        this.g.getEditText().setInputType(2);
        this.f.addPreference(this.g);
        this.h = new EditTextPreference(this);
        this.h.setKey("tcp_keep_alive_probe_time");
        this.h.setDialogTitle(k.preference_tcp_keep_alive_probe_time);
        this.h.setTitle(k.preference_tcp_keep_alive_probe_time);
        this.h.setPersistent(false);
        t.a(this.h, 5);
        this.h.setSummary(Integer.toString(n.g()));
        this.h.setDefaultValue(Integer.toString(n.g()));
        this.h.setOnPreferenceChangeListener(this);
        this.h.getEditText().setInputType(2);
        this.f.addPreference(this.h);
        this.i = new EditTextPreference(this);
        this.i.setKey("tcp_keep_alive_probes_count");
        this.i.setDialogTitle(k.preference_tcp_keep_alive_probes_count);
        this.i.setTitle(k.preference_tcp_keep_alive_probes_count);
        this.i.setPersistent(false);
        t.a(this.i, 5);
        this.i.setSummary(Integer.toString(n.h()));
        this.i.setDefaultValue(Integer.toString(n.h()));
        this.i.setOnPreferenceChangeListener(this);
        this.i.getEditText().setInputType(2);
        this.f.addPreference(this.i);
        setPreferenceScreen(this.a);
    }
}
