package com.mavenir.android.activity;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceCategory;
import android.preference.PreferenceScreen;
import android.view.MenuItem;
import com.fgmicrotec.mobile.android.fgvoip.FgVoIP;
import com.fgmicrotec.mobile.android.fgvoip.FgVoIP.a;
import com.fgmicrotec.mobile.android.fgvoip.f.k;
import com.mavenir.android.common.q;
import com.mavenir.android.settings.c.l;
import com.mavenir.android.settings.c.p;

public class PreferenceProfileAdvancedActivity extends PreferenceActivity implements OnPreferenceClickListener {
    private PreferenceScreen a;

    @TargetApi(11)
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (VERSION.SDK_INT >= 11) {
            ActionBar actionBar = getActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(k.preference_profile_main_title);
            actionBar.setSubtitle(getString(k.preference_current_profile) + p.d());
        }
        a();
    }

    public boolean onPreferenceClick(Preference preference) {
        return false;
    }

    protected void onDestroy() {
        super.onDestroy();
        q.a("PreferenceProfileAdvancedActivity", "onDestroy(): leaving advanced profile preferences...");
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
        Preference preferenceCategory = new PreferenceCategory(this);
        preferenceCategory.setTitle(k.preference_account_category);
        this.a.addPreference(preferenceCategory);
        Preference createPreferenceScreen = getPreferenceManager().createPreferenceScreen(this);
        createPreferenceScreen.setIntent(new Intent(this, PreferenceAccountActivity.class));
        createPreferenceScreen.setTitle(k.preference_account_category);
        preferenceCategory.addPreference(createPreferenceScreen);
        preferenceCategory = new PreferenceCategory(this);
        preferenceCategory.setTitle(k.preference_call_sms_category);
        this.a.addPreference(preferenceCategory);
        if (FgVoIP.U().q()) {
            createPreferenceScreen = getPreferenceManager().createPreferenceScreen(this);
            createPreferenceScreen.setIntent(new Intent(this, PreferenceCallActivity.class));
            createPreferenceScreen.setTitle(k.preference_call_category);
            preferenceCategory.addPreference(createPreferenceScreen);
        }
        if (FgVoIP.U().r()) {
            createPreferenceScreen = getPreferenceManager().createPreferenceScreen(this);
            Intent intent = new Intent();
            intent.setClassName(this, "com.mavenir.android.activity.PreferenceCallAdditionalSettingsActivity");
            intent.putExtra("showAdvanced", true);
            createPreferenceScreen.setIntent(intent);
            createPreferenceScreen.setTitle(k.preference_ut_title);
            preferenceCategory.addPreference(createPreferenceScreen);
        }
        if (FgVoIP.U().p()) {
            createPreferenceScreen = getPreferenceManager().createPreferenceScreen(this);
            createPreferenceScreen.setIntent(new Intent(this, PreferenceVccActivity.class));
            createPreferenceScreen.setTitle(k.preference_vcc_title);
            preferenceCategory.addPreference(createPreferenceScreen);
        }
        if (FgVoIP.U().ad() != a.VOIP) {
            createPreferenceScreen = getPreferenceManager().createPreferenceScreen(this);
            intent = new Intent();
            intent.setClassName(this, "com.mavenir.android.activity.PreferenceSmsActivity");
            createPreferenceScreen.setIntent(intent);
            createPreferenceScreen.setTitle(k.preference_sms_category);
            preferenceCategory.addPreference(createPreferenceScreen);
        }
        preferenceCategory = new PreferenceCategory(this);
        preferenceCategory.setTitle(k.preference_connection_category);
        this.a.addPreference(preferenceCategory);
        createPreferenceScreen = getPreferenceManager().createPreferenceScreen(this);
        createPreferenceScreen.setIntent(new Intent(this, PreferenceSipActivity.class));
        createPreferenceScreen.setTitle(k.preference_sip_category);
        preferenceCategory.addPreference(createPreferenceScreen);
        if (l.d() && !FgVoIP.U().aj()) {
            createPreferenceScreen = getPreferenceManager().createPreferenceScreen(this);
            createPreferenceScreen.setIntent(new Intent(this, PreferenceLteActivity.class));
            createPreferenceScreen.setTitle(k.preference_lte_category);
            preferenceCategory.addPreference(createPreferenceScreen);
        }
        createPreferenceScreen = getPreferenceManager().createPreferenceScreen(this);
        createPreferenceScreen.setIntent(new Intent(this, PreferenceRtpActivity.class));
        createPreferenceScreen.setTitle(k.preference_rtp_category);
        preferenceCategory.addPreference(createPreferenceScreen);
        createPreferenceScreen = getPreferenceManager().createPreferenceScreen(this);
        createPreferenceScreen.setIntent(new Intent(this, PreferenceNatActivity.class));
        createPreferenceScreen.setTitle(k.preference_nat_category);
        preferenceCategory.addPreference(createPreferenceScreen);
        createPreferenceScreen = getPreferenceManager().createPreferenceScreen(this);
        createPreferenceScreen.setIntent(new Intent(this, PreferenceWifiActivity.class));
        createPreferenceScreen.setTitle(k.preference_wifi_category);
        preferenceCategory.addPreference(createPreferenceScreen);
        if (FgVoIP.U().ai()) {
            createPreferenceScreen = getPreferenceManager().createPreferenceScreen(this);
            createPreferenceScreen.setIntent(new Intent(this, PreferenceQosActivity.class));
            createPreferenceScreen.setTitle(k.preference_qos_category);
            preferenceCategory.addPreference(createPreferenceScreen);
        }
        preferenceCategory = new PreferenceCategory(this);
        preferenceCategory.setTitle(k.preference_media_category);
        this.a.addPreference(preferenceCategory);
        createPreferenceScreen = getPreferenceManager().createPreferenceScreen(this);
        createPreferenceScreen.setIntent(new Intent(this, PreferenceMediaActivity.class));
        createPreferenceScreen.setTitle(k.preference_media_category);
        preferenceCategory.addPreference(createPreferenceScreen);
        if (FgVoIP.U().ai()) {
            preferenceCategory = new PreferenceCategory(this);
            preferenceCategory.setTitle(k.preference_audio_engine_setup);
            this.a.addPreference(preferenceCategory);
            createPreferenceScreen = getPreferenceManager().createPreferenceScreen(this);
            intent = new Intent();
            intent.setClassName(this, "com.mavenir.android.activity.PreferenceSpiritTestActivity");
            createPreferenceScreen.setIntent(intent);
            createPreferenceScreen.setTitle("SPIRIT Setup");
            preferenceCategory.addPreference(createPreferenceScreen);
        }
        if (FgVoIP.U().aj()) {
            if (FgVoIP.U().N()) {
                preferenceCategory = getPreferenceManager().createPreferenceScreen(this);
                preferenceCategory.setIntent(new Intent(this, PreferenceVVMActivity.class));
                preferenceCategory.setTitle(k.preference_vvm_title);
                preferenceCategory.setSummary(k.preference_vvm_summary);
                this.a.addPreference(preferenceCategory);
            }
            preferenceCategory = new PreferenceCategory(this);
            preferenceCategory.setTitle(k.preference_rcs_category);
            this.a.addPreference(preferenceCategory);
            createPreferenceScreen = getPreferenceManager().createPreferenceScreen(this);
            intent = new Intent();
            intent.setClassName(this, "com.mavenir.android.rcs.activities.PreferenceChatAndFTActivity");
            createPreferenceScreen.setIntent(intent);
            createPreferenceScreen.setTitle(k.preference_rcs_chat_and_ft_category);
            preferenceCategory.addPreference(createPreferenceScreen);
            createPreferenceScreen = getPreferenceManager().createPreferenceScreen(this);
            intent = new Intent();
            intent.setClassName(this, "com.mavenir.android.activity.PreferenceSpiritTestActivity");
            createPreferenceScreen.setIntent(intent);
            createPreferenceScreen.setTitle("SPIRIT Setup");
            preferenceCategory.addPreference(createPreferenceScreen);
            if (!FgVoIP.U().y()) {
                createPreferenceScreen = getPreferenceManager().createPreferenceScreen(this);
                intent = new Intent();
                intent.setClassName(this, "com.mavenir.android.rcs.activities.PreferenceCapabilitiesActivity");
                createPreferenceScreen.setIntent(intent);
                createPreferenceScreen.setTitle(k.preference_cd_category);
                preferenceCategory.addPreference(createPreferenceScreen);
                createPreferenceScreen = getPreferenceManager().createPreferenceScreen(this);
                intent = new Intent();
                intent.setClassName(this, "com.mavenir.android.rcs.activities.PreferenceRcsPresenceActivity");
                createPreferenceScreen.setIntent(intent);
                createPreferenceScreen.setTitle(k.preference_rcs_presence_category);
                preferenceCategory.addPreference(createPreferenceScreen);
            }
        }
        setPreferenceScreen(this.a);
    }
}
