package com.mavenir.android.activity;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.content.Intent;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;
import android.text.TextUtils;
import android.view.MenuItem;
import com.fgmicrotec.mobile.android.fgvoip.FgVoIP;
import com.fgmicrotec.mobile.android.fgvoip.f.k;
import com.mavenir.android.common.q;
import com.mavenir.android.settings.c.h;
import java.io.File;

public class PreferenceEngineeringActivity extends PreferenceActivity implements OnPreferenceChangeListener, OnPreferenceClickListener {
    private PreferenceScreen a;
    private CheckBoxPreference b;
    private PreferenceScreen c;
    private CheckBoxPreference d;
    private CheckBoxPreference e;
    private PreferenceScreen f;
    private PreferenceScreen g;
    private PreferenceScreen h;
    private Preference i;
    private PreferenceScreen j;

    @TargetApi(11)
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (VERSION.SDK_INT >= 11) {
            ActionBar actionBar = getActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(k.preference_dev_title);
        }
        a();
    }

    protected void onResume() {
        super.onResume();
        b();
    }

    public boolean onPreferenceClick(Preference preference) {
        int i = 2;
        if (!(preference instanceof CheckBoxPreference)) {
            return false;
        }
        boolean isChecked = ((CheckBoxPreference) preference).isChecked();
        if (preference.getKey() == "trace_enabled") {
            int i2;
            h.b(isChecked);
            h.a(isChecked ? 2 : 0);
            if (isChecked) {
                i2 = 2;
            } else {
                i2 = 0;
            }
            h.b(i2);
            if (isChecked) {
                i2 = 2;
            } else {
                i2 = 0;
            }
            h.c(i2);
            if (isChecked) {
                i2 = 2;
            } else {
                i2 = 0;
            }
            h.d(i2);
            if (!isChecked) {
                i = 0;
            }
            h.e(i);
            h.a(true);
            if (!isChecked) {
                FgVoIP.U().aw();
            }
            q.b();
            c();
            return true;
        } else if (preference.getKey() == "enable_qos_test_ui") {
            h.c(isChecked);
            return true;
        } else if (preference.getKey() != "enable_adb_hooks") {
            return false;
        } else {
            h.d(isChecked);
            return true;
        }
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
            }
        }
        return false;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case 16908332:
                finish();
                break;
        }
        return true;
    }

    private void a() {
        this.a = getPreferenceManager().createPreferenceScreen(this);
        this.b = new CheckBoxPreference(this);
        this.b.setKey("trace_enabled");
        this.b.setTitle(k.preference_dev_trace_enable);
        this.b.setPersistent(false);
        this.b.setSummaryOff(k.value_off);
        this.b.setSummaryOn(k.value_on);
        this.b.setOnPreferenceClickListener(this);
        this.a.addPreference(this.b);
        this.c = getPreferenceManager().createPreferenceScreen(this);
        this.c.setIntent(null);
        this.c.setTitle(k.preference_dev_share_trace);
        this.c.setSummary(k.preference_dev_share_trace_summary);
        this.c.setEnabled(false);
        this.a.addPreference(this.c);
        String externalStorageState = Environment.getExternalStorageState();
        if ("mounted".equals(externalStorageState) || "mounted_ro".equals(externalStorageState)) {
            File file = new File(Environment.getExternalStorageDirectory(), "fgAdapter.txt");
            if (file.exists() && file.isFile()) {
                Parcelable fromFile = Uri.fromFile(file);
                Intent intent = new Intent();
                intent.setAction("android.intent.action.SEND");
                intent.putExtra("android.intent.extra.STREAM", fromFile);
                intent.setType("text/plain");
                this.c.setIntent(intent);
                this.c.setEnabled(true);
            }
        }
        this.d = new CheckBoxPreference(this);
        this.d.setKey("enable_qos_test_ui");
        this.d.setTitle(k.preference_dev_test_enable_ui);
        this.d.setPersistent(false);
        this.d.setSummaryOff(k.value_disabled);
        this.d.setSummaryOn(k.value_enabled);
        this.d.setOnPreferenceClickListener(this);
        this.a.addPreference(this.d);
        this.e = new CheckBoxPreference(this);
        this.e.setKey("enable_adb_hooks");
        this.e.setTitle(k.preference_dev_test_adb_hooks);
        this.e.setPersistent(false);
        this.e.setSummaryOff(k.value_disabled);
        this.e.setSummaryOn(k.value_enabled);
        this.e.setOnPreferenceClickListener(this);
        this.a.addPreference(this.e);
        this.f = getPreferenceManager().createPreferenceScreen(this);
        this.f.setIntent(new Intent(this, PreferenceTestUiQosActivity.class));
        this.f.setTitle(k.preference_dev_test_ui_qos_title);
        this.f.setSummary(k.preference_dev_test_ui_qos_summary);
        this.a.addPreference(this.f);
        if (FgVoIP.U().M()) {
            this.g = getPreferenceManager().createPreferenceScreen(this);
            this.g.setIntent(new Intent(this, PreferenceConnectionManagerActivity.class));
            this.g.setTitle(k.preference_dev_test_connection_manager_title);
            this.g.setSummary(k.preference_dev_test_connection_manager_summary);
            this.a.addPreference(this.g);
        }
        this.h = getPreferenceManager().createPreferenceScreen(this);
        this.h.setIntent(new Intent(this, PreferenceAppLogActivity.class));
        this.h.setTitle(k.preference_dev_app_log_title);
        this.h.setSummary(k.preference_dev_app_log_summary);
        this.a.addPreference(this.h);
        if (!FgVoIP.U().ai()) {
            this.i = new Preference(this);
            Intent intent2 = new Intent(this, ActivationActivity.class);
            intent2.putExtra("com.mavenir.android.activity.ActivationActivity.ExtraLaunchActivity", false);
            this.i.setIntent(intent2);
            this.i.setTitle(k.preference_dev_activation_old_title);
            this.i.setSummary(k.preference_dev_activation_old_summary);
            this.a.addPreference(this.i);
        }
        if (FgVoIP.U().ai()) {
            this.j = getPreferenceManager().createPreferenceScreen(this);
            this.j.setIntent(new Intent(this, PreferenceActivationActivity.class));
            this.j.setTitle(k.preference_dev_activation_title);
            this.j.setSummary(k.preference_dev_activation_summary);
            this.a.addPreference(this.j);
        }
        if (!TextUtils.isEmpty(FgVoIP.U().a(k.DEF_OAUTH_CLIENT_ID))) {
            Preference createPreferenceScreen = getPreferenceManager().createPreferenceScreen(this);
            createPreferenceScreen.setIntent(new Intent(this, PreferenceAuthActivity.class));
            createPreferenceScreen.setTitle(k.preference_auth_title);
            createPreferenceScreen.setSummary(k.preference_auth_summary);
            this.a.addPreference(createPreferenceScreen);
        }
        setPreferenceScreen(this.a);
    }

    private void b() {
        this.b.setChecked(h.c());
        this.d.setChecked(h.i());
        this.e.setChecked(h.j());
    }

    private void c() {
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
