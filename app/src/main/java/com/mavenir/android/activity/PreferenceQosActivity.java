package com.mavenir.android.activity;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceCategory;
import android.preference.PreferenceScreen;
import android.view.MenuItem;
import android.widget.Toast;
import com.fgmicrotec.mobile.android.fgvoip.f.k;
import com.mavenir.android.common.q;
import com.mavenir.android.common.t;
import com.mavenir.android.settings.c;
import com.mavenir.android.settings.c.p;
import net.hockeyapp.android.views.UpdateView;

public class PreferenceQosActivity extends PreferenceActivity implements OnPreferenceChangeListener {
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
            actionBar.setTitle(k.preference_qos_title);
            actionBar.setSubtitle(getString(k.preference_current_profile) + p.d());
        }
        a();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case 16908332:
                finish();
                break;
        }
        return true;
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        if (preference instanceof EditTextPreference) {
            String str = (String) obj;
            EditTextPreference editTextPreference = (EditTextPreference) preference;
            if (editTextPreference.getEditText().getInputType() == 2 || editTextPreference.getEditText().getInputType() == UpdateView.NAME_LABEL_ID) {
                try {
                    Integer.valueOf(str);
                    editTextPreference.setSummary(String.valueOf(Integer.valueOf(str)));
                } catch (NumberFormatException e) {
                    q.c("PreferenceQosActivity", e.getLocalizedMessage(), e.getCause());
                    Toast.makeText(this, getString(k.invalid_value), 0).show();
                    return false;
                }
            }
            editTextPreference.setSummary(str);
            if (editTextPreference.getKey() == "wifi_qos_threshold") {
                c.q.h(Integer.valueOf(str).intValue());
                return true;
            } else if (editTextPreference.getKey() == "max_jitter") {
                c.q.i(Integer.valueOf(str).intValue());
                return true;
            } else if (editTextPreference.getKey() == "max_round_trip_delay") {
                c.q.j(Integer.valueOf(str).intValue());
                return true;
            } else if (editTextPreference.getKey() == "rtp_max_cumulative_loss") {
                c.q.k(Integer.valueOf(str).intValue());
                return true;
            } else if (editTextPreference.getKey() == "rtp_max_fraction_loss") {
                c.q.l(Integer.valueOf(str).intValue());
                return true;
            }
        }
        return false;
    }

    private void a() {
        this.a = getPreferenceManager().createPreferenceScreen(this);
        this.b = new PreferenceCategory(this);
        this.b.setTitle(k.preference_qos_category);
        this.a.addPreference(this.b);
        this.c = new EditTextPreference(this);
        this.c.setKey("wifi_qos_threshold");
        this.c.setDialogTitle(k.preference_qos_wifi_threshold);
        this.c.setTitle(k.preference_qos_wifi_threshold);
        this.c.setPersistent(false);
        t.a(this.c, 4);
        this.c.setSummary(Integer.toString(c.q.h()));
        this.c.setDefaultValue(Integer.toString(c.q.h()));
        this.c.setOnPreferenceChangeListener(this);
        this.c.getEditText().setInputType(UpdateView.NAME_LABEL_ID);
        this.b.addPreference(this.c);
        this.d = new EditTextPreference(this);
        this.d.setKey("max_jitter");
        this.d.setDialogTitle(k.preference_qos_jitter_threshold);
        this.d.setTitle(k.preference_qos_jitter_threshold);
        this.d.setPersistent(false);
        t.a(this.d, 4);
        this.d.setSummary(Integer.toString(c.q.i()));
        this.d.setDefaultValue(Integer.toString(c.q.i()));
        this.d.setOnPreferenceChangeListener(this);
        this.d.getEditText().setInputType(2);
        this.b.addPreference(this.d);
        this.e = new EditTextPreference(this);
        this.e.setKey("max_round_trip_delay");
        this.e.setDialogTitle(k.preference_qos_roun_trip_delay_threshold);
        this.e.setTitle(k.preference_qos_roun_trip_delay_threshold);
        this.e.setPersistent(false);
        t.a(this.e, 4);
        this.e.setSummary(Integer.toString(c.q.j()));
        this.e.setDefaultValue(Integer.toString(c.q.j()));
        this.e.setOnPreferenceChangeListener(this);
        this.e.getEditText().setInputType(2);
        this.b.addPreference(this.e);
        this.f = new EditTextPreference(this);
        this.f.setKey("rtp_max_cumulative_loss");
        this.f.setDialogTitle(k.preference_qos_cumulative_loss_threshold);
        this.f.setTitle(k.preference_qos_cumulative_loss_threshold);
        this.f.setPersistent(false);
        t.a(this.f, 4);
        this.f.setSummary(Integer.toString(c.q.k()));
        this.f.setDefaultValue(Integer.toString(c.q.k()));
        this.f.setOnPreferenceChangeListener(this);
        this.f.getEditText().setInputType(2);
        this.b.addPreference(this.f);
        this.g = new EditTextPreference(this);
        this.g.setKey("rtp_max_fraction_loss");
        this.g.setDialogTitle(k.preference_qos_fractional_loss_threshold);
        this.g.setTitle(k.preference_qos_fractional_loss_threshold);
        this.g.setPersistent(false);
        t.a(this.g, 4);
        this.g.setSummary(Integer.toString(c.q.l()));
        this.g.setDefaultValue(Integer.toString(c.q.l()));
        this.g.setOnPreferenceChangeListener(this);
        this.g.getEditText().setInputType(2);
        this.b.addPreference(this.g);
        setPreferenceScreen(this.a);
    }
}
