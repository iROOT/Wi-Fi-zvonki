package com.mavenir.android.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceGroup;
import android.preference.PreferenceScreen;
import android.view.MenuItem;
import com.fgmicrotec.mobile.android.fgvoip.FgVoIP;
import com.fgmicrotec.mobile.android.fgvoip.f.b;
import com.fgmicrotec.mobile.android.fgvoip.f.k;
import com.mavenir.android.settings.c.aa;

public class PreferenceVVMActivity extends PreferenceActivity implements OnPreferenceChangeListener {
    private PreferenceScreen a;

    @TargetApi(11)
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTitle(k.preference_vvm_title);
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
            if (editTextPreference.getKey() == "vvm_client_id") {
                aa.a(str);
                editTextPreference.setSummary(str);
                editTextPreference.setDefaultValue(str);
                return true;
            } else if (editTextPreference.getKey() == "vvm_client_passwd") {
                aa.b(str);
                editTextPreference.setSummary(str);
                editTextPreference.setDefaultValue(str);
                return true;
            } else if (editTextPreference.getKey() == "vvm_server_addr") {
                aa.c(str);
                editTextPreference.setSummary(str);
                editTextPreference.setDefaultValue(str);
                return true;
            } else if (editTextPreference.getKey() == "vvm_server_port") {
                aa.d(str);
                editTextPreference.setSummary(str);
                editTextPreference.setDefaultValue(str);
                return true;
            } else if (editTextPreference.getKey() == "vvm_tls_enabled") {
                aa.e(str);
                editTextPreference.setSummary(str);
                editTextPreference.setDefaultValue(str);
                return true;
            } else if (editTextPreference.getKey() == "vvm_folder_path") {
                aa.f(str);
                editTextPreference.setSummary(str);
                editTextPreference.setDefaultValue(str);
                return true;
            } else if (editTextPreference.getKey() == "vvm_sit_encode_enabled") {
                aa.g(str);
                editTextPreference.setSummary(str);
                editTextPreference.setDefaultValue(str);
                return true;
            }
        }
        if (preference instanceof ListPreference) {
            String str2 = (String) obj;
            ListPreference listPreference = (ListPreference) preference;
            int findIndexOfValue = listPreference.findIndexOfValue(str2);
            listPreference.setValueIndex(findIndexOfValue);
            listPreference.setSummary(str2);
            if (listPreference.getKey() == "vvm_download_mode") {
                aa.a(findIndexOfValue);
                return true;
            }
        }
        return false;
    }

    private void a() {
        this.a = getPreferenceManager().createPreferenceScreen(this);
        a("vvm_client_id", aa.b(), k.preference_vvm_client_login_id, 17, this.a);
        a("vvm_client_passwd", aa.c(), k.preference_vvm_client_login_pass, 17, this.a);
        a("vvm_server_addr", aa.d(), k.preference_vvm_client_mstore_addr, 17, this.a);
        a("vvm_server_port", Integer.toString(aa.e()), k.preference_vvm_client_mstore_port, 2, this.a);
        a("vvm_tls_enabled", Integer.toString(aa.f()), k.preference_vvm_client_tls_enabled, 2, this.a);
        a("vvm_folder_path", aa.g(), k.preference_vvm_client_file_location, 17, this.a);
        a("vvm_sit_encode_enabled", Integer.toString(aa.h()), k.preference_vvm_client_sit_encode_enabled, 2, this.a);
        a("vvm_download_mode", Integer.toString(aa.i()), k.preference_vvm_download, this.a);
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

    private void a(String str, String str2, int i, PreferenceGroup preferenceGroup) {
        Preference listPreference = new ListPreference(this);
        listPreference.setKey(str);
        listPreference.setDialogTitle(i);
        listPreference.setTitle(i);
        listPreference.setEntries(b.vvm_download_mode);
        listPreference.setEntryValues(b.vvm_download_mode);
        listPreference.setPersistent(false);
        listPreference.setValueIndex(aa.i());
        listPreference.setSummary(listPreference.getEntry());
        listPreference.setOnPreferenceChangeListener(this);
        preferenceGroup.addPreference(listPreference);
    }

    public void onStop() {
        FgVoIP.U().j();
        super.onStop();
    }
}
