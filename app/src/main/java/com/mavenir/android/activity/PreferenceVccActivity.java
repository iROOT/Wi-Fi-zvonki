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
import android.preference.PreferenceActivity;
import android.preference.PreferenceCategory;
import android.preference.PreferenceScreen;
import android.view.MenuItem;
import android.widget.Toast;
import com.fgmicrotec.mobile.android.fgvoip.FgVoIP;
import com.fgmicrotec.mobile.android.fgvoip.f.k;
import com.mavenir.android.common.q;
import com.mavenir.android.common.t;
import com.mavenir.android.settings.c.p;
import com.mavenir.android.settings.c.x;
import net.hockeyapp.android.views.UpdateView;

public class PreferenceVccActivity extends PreferenceActivity implements OnPreferenceChangeListener {
    private PreferenceScreen a;
    private PreferenceCategory b;
    private CheckBoxPreference c;
    private EditTextPreference d;
    private EditTextPreference e;

    @TargetApi(11)
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (VERSION.SDK_INT >= 11) {
            ActionBar actionBar = getActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(k.preference_vcc_title);
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
                    q.c("PreferenceVccActivity", e.getLocalizedMessage(), e.getCause());
                    Toast.makeText(this, getString(k.invalid_value), 0).show();
                    return false;
                }
            }
            editTextPreference.setSummary(str);
            if (editTextPreference.getKey() == "cs_threshold") {
                x.a(Integer.valueOf(str).intValue());
                return true;
            } else if (editTextPreference.getKey() != "cs_number") {
                return false;
            } else {
                x.a(str);
                return true;
            }
        } else if (!(preference instanceof CheckBoxPreference)) {
            return false;
        } else {
            CheckBoxPreference checkBoxPreference = (CheckBoxPreference) preference;
            boolean booleanValue = ((Boolean) obj).booleanValue();
            if (checkBoxPreference.getKey() != "cs_enable") {
                return false;
            }
            x.a(booleanValue);
            return true;
        }
    }

    private void a() {
        this.a = getPreferenceManager().createPreferenceScreen(this);
        setPreferenceScreen(this.a);
        this.b = new PreferenceCategory(this);
        this.b.setTitle(k.preference_vcc_cs_category);
        this.a.addPreference(this.b);
        this.c = new CheckBoxPreference(this);
        this.c.setKey("cs_enable");
        this.c.setTitle(k.preference_vcc_cs_enable);
        this.c.setPersistent(false);
        this.c.setChecked(x.b());
        this.c.setOnPreferenceChangeListener(this);
        this.b.addPreference(this.c);
        this.d = new EditTextPreference(this);
        this.d.setKey("cs_threshold");
        this.d.setDialogTitle(k.preference_vcc_cs_threshold);
        this.d.setTitle(k.preference_vcc_cs_threshold);
        this.d.setPersistent(false);
        t.a(this.d, 4);
        this.d.setSummary(String.valueOf(x.c()));
        this.d.setDefaultValue(String.valueOf(x.c()));
        this.d.setOnPreferenceChangeListener(this);
        this.d.getEditText().setInputType(UpdateView.NAME_LABEL_ID);
        this.b.addPreference(this.d);
        this.e = new EditTextPreference(this);
        this.e.setKey("cs_number");
        this.e.setDialogTitle(k.preference_vcc_cs_number);
        this.e.setTitle(k.preference_vcc_cs_number);
        this.e.setPersistent(false);
        t.a(this.e, 15);
        this.e.setSummary(x.d());
        this.e.setDefaultValue(x.d());
        this.e.setOnPreferenceChangeListener(this);
        this.e.getEditText().setInputType(3);
        this.b.addPreference(this.e);
        this.d.setDependency("cs_enable");
        this.e.setDependency("cs_enable");
    }
}
