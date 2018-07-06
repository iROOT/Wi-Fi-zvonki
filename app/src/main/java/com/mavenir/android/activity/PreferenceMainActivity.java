package com.mavenir.android.activity;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;
import android.view.MenuItem;
import com.fgmicrotec.mobile.android.fgvoip.FgVoIP;
import com.fgmicrotec.mobile.android.fgvoip.f.k;

public class PreferenceMainActivity extends PreferenceActivity {
    private PreferenceScreen a;
    private PreferenceScreen b;
    private PreferenceScreen c;
    private PreferenceScreen d;
    private PreferenceScreen e;

    @TargetApi(11)
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (!FgVoIP.U().ai() || FgVoIP.U().ah()) {
            if (VERSION.SDK_INT >= 11) {
                ActionBar actionBar = getActionBar();
                actionBar.setDisplayHomeAsUpEnabled(true);
                actionBar.setTitle(k.preference_title);
            }
            this.a = getPreferenceManager().createPreferenceScreen(this);
            this.b = getPreferenceManager().createPreferenceScreen(this);
            this.b.setIntent(new Intent(this, PreferenceGeneralActivity.class));
            this.b.setTitle(k.preference_general_category);
            this.b.setSummary(k.preference_general_summary);
            this.a.addPreference(this.b);
            if (FgVoIP.U().D()) {
                this.c = getPreferenceManager().createPreferenceScreen(this);
                this.c.setIntent(new Intent(this, PreferenceQuickActivity.class));
                this.c.setTitle(k.preference_quick_category);
                this.c.setSummary(k.preference_quick_summary);
                this.a.addPreference(this.c);
                this.d = getPreferenceManager().createPreferenceScreen(this);
                this.d.setIntent(new Intent(this, PreferenceProfileListActivity.class));
                this.d.setTitle(k.preference_profiles_category);
                this.d.setSummary(k.preference_profiles_summary);
                this.a.addPreference(this.d);
            }
            this.e = getPreferenceManager().createPreferenceScreen(this);
            this.e.setIntent(new Intent(this, PreferenceEngineeringActivity.class));
            this.e.setTitle(k.preference_dev_category);
            this.e.setSummary(k.preference_dev_sumary);
            this.a.addPreference(this.e);
            setPreferenceScreen(this.a);
            return;
        }
        finish();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case 16908332:
                FgVoIP.U().c((Activity) this);
                break;
        }
        return true;
    }
}
