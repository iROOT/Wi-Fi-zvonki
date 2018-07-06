package com.mavenir.android.activity;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceCategory;
import android.preference.PreferenceScreen;
import android.view.MenuItem;
import com.fgmicrotec.mobile.android.fgvoip.FgVoIP;
import com.fgmicrotec.mobile.android.fgvoip.f.k;
import com.mavenir.android.common.l;
import com.mavenir.android.settings.c;
import com.mavenir.android.settings.c.p;
import com.mavenir.android.settings.c.q;

public class PreferenceAccountActivity extends PreferenceActivity implements OnPreferenceChangeListener {
    private PreferenceScreen a;
    private PreferenceCategory b;
    private EditTextPreference c;
    private EditTextPreference d;
    private EditTextPreference e;
    private EditTextPreference f;
    private EditTextPreference g;
    private EditTextPreference h;
    private EditTextPreference i;
    private boolean j = false;

    @TargetApi(11)
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (VERSION.SDK_INT >= 11) {
            ActionBar actionBar = getActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(k.preference_account_title);
            actionBar.setSubtitle(getString(k.preference_current_profile) + p.d());
        }
        a();
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        if (preference instanceof EditTextPreference) {
            String str = (String) obj;
            EditTextPreference editTextPreference = (EditTextPreference) preference;
            editTextPreference.setSummary(str);
            if (editTextPreference.getKey() == "profile_name") {
                p.a(str);
                return true;
            } else if (editTextPreference.getKey() == "display_name") {
                p.b(str);
                return true;
            } else if (editTextPreference.getKey() == "msisdn") {
                p.h(str);
                this.j = true;
                return true;
            } else if (editTextPreference.getKey() == "impu") {
                p.c(str);
                if (FgVoIP.U().ai() && c.k.d() == -3) {
                    c.k.a(l.a((Context) this).p());
                    p.k(l.a((Context) this).p());
                    c.k.b(-1);
                    q.b(System.currentTimeMillis());
                    c.k.a(1);
                }
                this.j = true;
                return true;
            } else if (editTextPreference.getKey() == "impi") {
                p.d(str);
                this.j = true;
                return true;
            } else if (editTextPreference.getKey() == "password") {
                p.e(str);
                editTextPreference.setSummary("");
                return true;
            } else if (editTextPreference.getKey() == "network_imei") {
                p.l(str);
                editTextPreference.setSummary("");
                return true;
            }
        }
        return false;
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.j) {
            FgVoIP.U().i();
            this.j = false;
        }
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
        this.b.setTitle(k.preference_account_category);
        this.a.addPreference(this.b);
        this.c = new EditTextPreference(this);
        this.c.setKey("profile_name");
        this.c.setTitle(k.preference_account_profile_name);
        this.c.setPersistent(false);
        this.c.setSummary(p.d());
        this.c.setDefaultValue(p.d());
        this.c.setOnPreferenceChangeListener(this);
        this.b.addPreference(this.c);
        this.d = new EditTextPreference(this);
        this.d.setKey("display_name");
        this.d.setTitle(k.preference_account_display_name);
        this.d.setPersistent(false);
        this.d.setSummary(p.e());
        this.d.setDefaultValue(p.e());
        this.d.setOnPreferenceChangeListener(this);
        this.b.addPreference(this.d);
        this.e = new EditTextPreference(this);
        this.e.setKey("msisdn");
        this.e.setTitle(k.preference_account_msisdn);
        this.e.setPersistent(false);
        this.e.setSummary(p.k());
        this.e.setDefaultValue(p.k());
        this.e.setOnPreferenceChangeListener(this);
        this.f = new EditTextPreference(this);
        this.f.setKey("impu");
        this.f.setTitle(k.preference_account_impu);
        this.f.setPersistent(false);
        this.f.setSummary(p.f());
        this.f.setDefaultValue(p.f());
        this.f.setOnPreferenceChangeListener(this);
        this.b.addPreference(this.f);
        this.g = new EditTextPreference(this);
        this.g.setKey("impi");
        this.g.setTitle(k.preference_account_impi);
        this.g.setPersistent(false);
        this.g.setSummary(p.g());
        this.g.setDefaultValue(p.g());
        this.g.setOnPreferenceChangeListener(this);
        this.b.addPreference(this.g);
        this.h = new EditTextPreference(this);
        this.h.setKey("password");
        this.h.setTitle(k.preference_account_password);
        this.h.setPersistent(false);
        this.h.setDefaultValue(p.h());
        this.h.getEditText().setInputType(129);
        this.h.setOnPreferenceChangeListener(this);
        this.b.addPreference(this.h);
        this.i = new EditTextPreference(this);
        this.i.setKey("network_imei");
        this.i.setTitle(k.preference_account_imei);
        this.i.setPersistent(false);
        this.i.setSummary(p.o());
        this.i.setDefaultValue(p.o());
        this.i.setOnPreferenceChangeListener(this);
        this.b.addPreference(this.i);
        setPreferenceScreen(this.a);
    }
}
