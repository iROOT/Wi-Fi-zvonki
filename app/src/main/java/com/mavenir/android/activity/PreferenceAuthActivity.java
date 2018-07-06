package com.mavenir.android.activity;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceCategory;
import android.preference.PreferenceGroup;
import android.preference.PreferenceScreen;
import android.view.MenuItem;
import com.fgmicrotec.mobile.android.fgvoip.FgVoIP;
import com.fgmicrotec.mobile.android.fgvoip.f.k;
import com.mavenir.android.settings.c.b;

public class PreferenceAuthActivity extends PreferenceActivity implements OnPreferenceChangeListener {
    private PreferenceScreen a;

    @TargetApi(11)
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (VERSION.SDK_INT >= 11) {
            ActionBar actionBar = getActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(k.preference_auth_title);
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
            if (editTextPreference.getKey() == "oauth_client_id") {
                b.a(str);
                editTextPreference.setSummary(str);
                editTextPreference.setDefaultValue(str);
                return true;
            } else if (editTextPreference.getKey() == "oauth_client_secret") {
                b.b(str);
                editTextPreference.setSummary(str);
                editTextPreference.setDefaultValue(str);
                return true;
            } else if (editTextPreference.getKey() == "oauth_scope") {
                b.c(str);
                editTextPreference.setSummary(str);
                editTextPreference.setDefaultValue(str);
                return true;
            } else if (editTextPreference.getKey() == "oauth_redirect_uri") {
                b.d(str);
                editTextPreference.setSummary(str);
                editTextPreference.setDefaultValue(str);
                return true;
            } else if (editTextPreference.getKey() == "oauth_auth_endpoint") {
                b.e(str);
                editTextPreference.setSummary(str);
                editTextPreference.setDefaultValue(str);
                return true;
            } else if (editTextPreference.getKey() == "oauth_token_endpoint") {
                b.f(str);
                editTextPreference.setSummary(str);
                editTextPreference.setDefaultValue(str);
                return true;
            } else if (editTextPreference.getKey() == "oauth_revoke_endpoint") {
                b.g(str);
                editTextPreference.setSummary(str);
                editTextPreference.setDefaultValue(str);
                return true;
            } else if (editTextPreference.getKey() == "oauth_device_agent") {
                b.h(str);
                editTextPreference.setSummary(str);
                editTextPreference.setDefaultValue(str);
                return true;
            } else if (editTextPreference.getKey() == "es_server_url") {
                b.i(str);
                editTextPreference.setSummary(str);
                editTextPreference.setDefaultValue(str);
                return true;
            } else if (editTextPreference.getKey() == "wsg_server_url") {
                b.j(str);
                editTextPreference.setSummary(str);
                editTextPreference.setDefaultValue(str);
                return true;
            } else if (editTextPreference.getKey() == "iam_server_url") {
                b.k(str);
                editTextPreference.setSummary(str);
                editTextPreference.setDefaultValue(str);
                return true;
            }
        }
        return false;
    }

    private void a() {
        this.a = getPreferenceManager().createPreferenceScreen(this);
        a("es_server_url", b.j(), k.preference_auth_es_server_url, 17, this.a);
        a("wsg_server_url", b.k(), k.preference_auth_wsg_server_url, 17, this.a);
        a("iam_server_url", b.l(), k.preference_auth_iam_server_url, 17, this.a);
        Preference preferenceCategory = new PreferenceCategory(this);
        preferenceCategory.setTitle(k.preference_auth_oauth_category);
        this.a.addPreference(preferenceCategory);
        a("oauth_client_id", b.b(), k.preference_auth_oauth_client_id, 1, preferenceCategory);
        a("oauth_client_secret", b.c(), k.preference_auth_oauth_client_secret, 1, preferenceCategory);
        a("oauth_scope", b.d(), k.preference_auth_oauth_scope, 1, preferenceCategory);
        a("oauth_redirect_uri", b.e(), k.preference_auth_oauth_redirect_uri, 17, preferenceCategory);
        a("oauth_auth_endpoint", b.f(), k.preference_auth_oauth_auth_endpoint, 17, preferenceCategory);
        a("oauth_token_endpoint", b.g(), k.preference_auth_oauth_token_endpoint, 17, preferenceCategory);
        a("oauth_revoke_endpoint", b.h(), k.preference_auth_oauth_revoke_endpoint, 17, preferenceCategory);
        a("oauth_device_agent", b.i(), k.preference_auth_oauth_device_agent, 1, preferenceCategory);
        setPreferenceScreen(this.a);
    }

    private void a(String str, String str2, int i, int i2, PreferenceGroup preferenceGroup) {
        Preference editTextPreference = new EditTextPreference(this);
        editTextPreference.setKey(str);
        editTextPreference.setDialogTitle(i);
        editTextPreference.setTitle(i);
        editTextPreference.setPersistent(false);
        editTextPreference.setSummary(str2);
        editTextPreference.setDefaultValue(str2);
        editTextPreference.setOnPreferenceChangeListener(this);
        editTextPreference.getEditText().setInputType(i2);
        preferenceGroup.addPreference(editTextPreference);
    }
}
