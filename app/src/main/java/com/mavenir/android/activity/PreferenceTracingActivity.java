package com.mavenir.android.activity;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceCategory;
import android.preference.PreferenceScreen;
import android.view.MenuItem;
import com.fgmicrotec.mobile.android.fgvoip.FgVoIP;
import com.fgmicrotec.mobile.android.fgvoip.f.b;
import com.fgmicrotec.mobile.android.fgvoip.f.k;
import com.mavenir.android.settings.c.h;

public class PreferenceTracingActivity extends PreferenceActivity implements OnPreferenceChangeListener, OnPreferenceClickListener {
    private PreferenceScreen a;
    private PreferenceCategory b;
    private CheckBoxPreference c;
    private PreferenceCategory d;
    private ListPreference e;
    private ListPreference f;
    private ListPreference g;
    private ListPreference h;
    private ListPreference i;

    @TargetApi(11)
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (VERSION.SDK_INT >= 11) {
            ActionBar actionBar = getActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(k.preference_dev_trace_title);
        }
        a();
        b();
    }

    public boolean onPreferenceClick(Preference preference) {
        if (preference instanceof CheckBoxPreference) {
            boolean isChecked = ((CheckBoxPreference) preference).isChecked();
            if (preference.getKey() == "trace_write_mode") {
                h.a(isChecked);
                return true;
            } else if (preference.getKey() == "enable_qos_test_ui") {
                h.c(isChecked);
                return true;
            }
        }
        return false;
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        if (preference instanceof ListPreference) {
            String str = (String) obj;
            ListPreference listPreference = (ListPreference) preference;
            int findIndexOfValue = listPreference.findIndexOfValue(str);
            listPreference.setValueIndex(findIndexOfValue);
            listPreference.setSummary(str);
            if (listPreference.getKey() == "trace_level_data") {
                h.a(findIndexOfValue);
                return true;
            } else if (listPreference.getKey() == "trace_level_external") {
                h.b(findIndexOfValue);
                return true;
            } else if (listPreference.getKey() == "trace_level_gui") {
                h.c(findIndexOfValue);
                return true;
            } else if (listPreference.getKey() == "trace_level_media") {
                h.d(findIndexOfValue);
                return true;
            } else if (listPreference.getKey() == "trace_level_sip") {
                h.e(findIndexOfValue);
                return true;
            } else {
                c();
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
        this.b.setTitle(k.preference_dev_trace_category);
        this.a.addPreference(this.b);
        this.c = new CheckBoxPreference(this);
        this.c.setKey("trace_write_mode");
        this.c.setTitle(k.preference_dev_trace_append_file);
        this.c.setPersistent(false);
        this.c.setSummaryOff(k.value_off);
        this.c.setSummaryOn(k.value_on);
        this.c.setOnPreferenceClickListener(this);
        this.b.addPreference(this.c);
        this.d = new PreferenceCategory(this);
        this.d.setTitle(k.preference_dev_trace_levels_category);
        this.a.addPreference(this.d);
        this.e = new ListPreference(this);
        this.e.setKey("trace_level_data");
        this.e.setDialogTitle(k.preference_dev_trace_data);
        this.e.setTitle(k.preference_dev_trace_data);
        this.e.setEntries(b.preference_tracing_levels);
        this.e.setEntryValues(b.preference_tracing_levels);
        this.e.setPersistent(false);
        this.e.setOnPreferenceChangeListener(this);
        this.d.addPreference(this.e);
        this.f = new ListPreference(this);
        this.f.setKey("trace_level_external");
        this.f.setDialogTitle(k.preference_dev_trace_external);
        this.f.setTitle(k.preference_dev_trace_external);
        this.f.setEntries(b.preference_tracing_levels);
        this.f.setEntryValues(b.preference_tracing_levels);
        this.f.setPersistent(false);
        this.f.setOnPreferenceChangeListener(this);
        this.d.addPreference(this.f);
        this.g = new ListPreference(this);
        this.g.setKey("trace_level_gui");
        this.g.setDialogTitle(k.preference_dev_trace_gui);
        this.g.setTitle(k.preference_dev_trace_gui);
        this.g.setEntries(b.preference_tracing_levels);
        this.g.setEntryValues(b.preference_tracing_levels);
        this.g.setPersistent(false);
        this.g.setOnPreferenceChangeListener(this);
        this.d.addPreference(this.g);
        this.h = new ListPreference(this);
        this.h.setKey("trace_level_media");
        this.h.setDialogTitle(k.preference_dev_trace_media);
        this.h.setTitle(k.preference_dev_trace_media);
        this.h.setEntries(b.preference_tracing_levels);
        this.h.setEntryValues(b.preference_tracing_levels);
        this.h.setPersistent(false);
        this.h.setOnPreferenceChangeListener(this);
        this.d.addPreference(this.h);
        this.i = new ListPreference(this);
        this.i.setKey("trace_level_sip");
        this.i.setDialogTitle(k.preference_dev_trace_sip);
        this.i.setTitle(k.preference_dev_trace_sip);
        this.i.setEntries(b.preference_tracing_levels);
        this.i.setEntryValues(b.preference_tracing_levels);
        this.i.setPersistent(false);
        this.i.setOnPreferenceChangeListener(this);
        this.d.addPreference(this.i);
        setPreferenceScreen(this.a);
    }

    private void b() {
        this.c.setChecked(h.b());
        this.e.setValueIndex(h.d());
        this.e.setSummary(this.e.getEntry());
        this.f.setValueIndex(h.e());
        this.f.setSummary(this.f.getEntry());
        this.g.setValueIndex(h.f());
        this.g.setSummary(this.g.getEntry());
        this.h.setValueIndex(h.g());
        this.h.setSummary(this.h.getEntry());
        this.i.setValueIndex(h.h());
        this.i.setSummary(this.i.getEntry());
    }

    private void c() {
        if (h.d() == 0 && h.e() == 0 && h.f() == 0 && h.g() == 0 && h.h() == 0) {
            h.b(false);
        } else {
            h.b(true);
        }
        d();
    }

    private void d() {
        Intent intent = new Intent("com.fgmicrotec.mobile.android.voip.SetTraceLevel");
        intent.putExtra("extra_trace_group", 0);
        intent.putExtra("extra_trace_level", h.d());
        sendBroadcast(intent);
        intent.putExtra("extra_trace_group", 1);
        intent.putExtra("extra_trace_level", h.e());
        sendBroadcast(intent);
        intent.putExtra("extra_trace_group", 2);
        intent.putExtra("extra_trace_level", h.f());
        sendBroadcast(intent);
        intent.putExtra("extra_trace_group", 3);
        intent.putExtra("extra_trace_level", h.g());
        sendBroadcast(intent);
        intent.putExtra("extra_trace_group", 4);
        intent.putExtra("extra_trace_level", h.h());
        sendBroadcast(intent);
    }
}
