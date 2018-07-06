package com.mavenir.android.activity;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceCategory;
import android.preference.PreferenceGroup;
import android.preference.PreferenceScreen;
import android.preference.RingtonePreference;
import android.support.v4.content.o;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.Toast;
import com.fgmicrotec.mobile.android.fgvoip.FgVoIP;
import com.fgmicrotec.mobile.android.fgvoip.e;
import com.fgmicrotec.mobile.android.fgvoip.f.b;
import com.fgmicrotec.mobile.android.fgvoip.f.h;
import com.fgmicrotec.mobile.android.fgvoip.f.k;
import com.mavenir.android.common.l;
import com.mavenir.android.common.q;
import com.mavenir.android.common.t;
import com.mavenir.android.common.y;
import com.mavenir.android.settings.c;
import com.mavenir.android.settings.c.f;
import com.mavenir.android.settings.c.m;
import com.mavenir.android.vtow.activation.ActivationAdapter;

public class PreferenceGeneralActivity extends PreferenceActivity implements OnPreferenceChangeListener, OnPreferenceClickListener {
    private boolean A = false;
    private boolean B = false;
    private a C;
    private ProgressDialog D;
    private Handler E;
    private Runnable F = new Runnable(this) {
        final /* synthetic */ PreferenceGeneralActivity a;

        {
            this.a = r1;
        }

        public void run() {
            boolean z = true;
            this.a.B = true;
            boolean isChecked = this.a.n.isChecked();
            this.a.n.setOnPreferenceClickListener(null);
            CheckBoxPreference a = this.a.n;
            if (isChecked) {
                z = false;
            }
            a.setChecked(z);
            this.a.n.setOnPreferenceClickListener(this.a);
            this.a.b();
        }
    };
    private PreferenceScreen a;
    private PreferenceCategory b;
    private CheckBoxPreference c;
    private CheckBoxPreference d;
    private CheckBoxPreference e;
    private PreferenceCategory f;
    private ListPreference g;
    private PreferenceCategory h;
    private CheckBoxPreference i;
    private CheckBoxPreference j;
    private CheckBoxPreference k;
    private EditTextPreference l;
    private CheckBoxPreference m;
    private CheckBoxPreference n;
    private RingtonePreference o;
    private CheckBoxPreference p;
    private CheckBoxPreference q;
    private CheckBoxPreference r;
    private CheckBoxPreference s;
    private PreferenceGroup t;
    private CheckBoxPreference u;
    private ListPreference v;
    private PreferenceCategory w;
    private ListPreference x;
    private ListPreference y;
    private boolean z = false;

    private class a extends BroadcastReceiver {
        final /* synthetic */ PreferenceGeneralActivity a;

        private a(PreferenceGeneralActivity preferenceGeneralActivity) {
            this.a = preferenceGeneralActivity;
        }

        /* synthetic */ a(PreferenceGeneralActivity preferenceGeneralActivity, AnonymousClass1 anonymousClass1) {
            this(preferenceGeneralActivity);
        }

        public void onReceive(Context context, Intent intent) {
            if (intent != null && intent.getAction() != null) {
                String action = intent.getAction();
                if (!this.a.B) {
                    if (action.equals("com.mavenir.android.activation.action_lte_activation_cnf")) {
                        this.a.a(intent.getIntExtra("com.mavenir.android.activation.extra_error_code", -1), intent.getIntExtra("com.mavenir.android.activation.extra_reason_code", -1), intent.getStringExtra("com.mavenir.android.activation.error_message"));
                    } else if (action.equals("com.mavenir.android.activation.action_lte_deactivation_cnf")) {
                        this.a.b(intent.getIntExtra("com.mavenir.android.activation.extra_error_code", -1), intent.getIntExtra("com.mavenir.android.activation.extra_reason_code", -1), intent.getStringExtra("com.mavenir.android.activation.error_message"));
                    }
                }
            }
        }
    }

    @TargetApi(11)
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (VERSION.SDK_INT >= 11) {
            ActionBar actionBar = getActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);
            if (FgVoIP.U().ai() && FgVoIP.U().ah()) {
                actionBar.setTitle(k.preference_general_category);
            } else {
                actionBar.setTitle(k.preference_general_title);
            }
        }
        a();
        this.E = new Handler();
        this.C = new a();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.mavenir.android.activation.action_lte_activation_cnf");
        intentFilter.addAction("com.mavenir.android.activation.action_lte_deactivation_cnf");
        o.a((Context) this).a(this.C, intentFilter);
    }

    protected void onDestroy() {
        super.onDestroy();
        q.a("PreferenceGeneralActivity", "onDestroy()");
        this.B = false;
        o.a((Context) this).a(this.C);
        if (this.z) {
            FgVoIP.U().a("com.fgmicrotec.mobile.android.voip.LoginToServerReq");
            this.z = false;
        }
        if (this.A) {
            FgVoIP.U().a((Context) this, "com.mavenir.android.action_backup_user_prefs");
            this.A = false;
        }
    }

    public boolean onPreferenceClick(Preference preference) {
        if (preference instanceof CheckBoxPreference) {
            boolean isChecked = ((CheckBoxPreference) preference).isChecked();
            this.A = true;
            if (preference.getKey() == "save_user_data") {
                c.k.a(isChecked);
                if (!isChecked) {
                    FgVoIP.U().a((Context) this, "com.mavenir.android.action_backup_disabled");
                }
            } else if (preference.getKey() == "start_after_phone_boot") {
                c.k.b(isChecked);
            } else if (preference.getKey() == "enable_status_icon") {
                c.k.c(isChecked);
                e.a().a(FgVoIP.U().at(), l.a((Context) this).M(), true);
            } else if (preference.getKey() == "sms_request_delivery_reports") {
                c.k.d(isChecked);
                return true;
            } else if (preference.getKey() == "msg_request_display_reports") {
                c.k.h(isChecked);
                return true;
            } else if (preference.getKey() == "msg_allow_display_reports") {
                c.k.i(isChecked);
                return true;
            } else if (preference.getKey() == "mms_auto_download_messages") {
                c.k.e(isChecked);
                return true;
            } else if (preference.getKey() == "sms_delete_old_messages") {
                c.k.f(isChecked);
                this.l.setEnabled(isChecked);
                return true;
            } else if (preference.getKey() == "sms_notify_incoming") {
                c.k.g(isChecked);
                return true;
            } else if (preference.getKey() == "enable_tcp_keep_alive") {
                c.k.j(isChecked);
                if (this.z) {
                    return true;
                }
                q.a("PreferenceGeneralActivity", "clearWhitelist(): logging out due to TCP keep alive flag change");
                FgVoIP.U().a("com.fgmicrotec.mobile.android.voip.LogoutFromServerReq");
                this.z = true;
                return true;
            } else if (preference.getKey() == "enable_messaging") {
                c.k.k(isChecked);
                if (!this.z) {
                    q.a("PreferenceGeneralActivity", "clearWhitelist(): logging out due to enable messaging flag change");
                    FgVoIP.U().a("com.fgmicrotec.mobile.android.voip.LogoutFromServerReq");
                    this.z = true;
                }
                FgVoIP.U().i(isChecked);
                return true;
            } else if (preference.getKey() == "network_performance") {
                f.a(isChecked);
                r2 = new Intent("com.mavenir.android.action_enable_net_performance");
                r2.putExtra("com.mavenir.android.extra_value", isChecked);
                o.a((Context) this).a(r2);
                return true;
            } else if (preference.getKey() == "lte_800_user_activated") {
                if (FgVoIP.U().f(false)) {
                    r2 = new Intent("com.mavenir.android.activation.action_user_activated");
                    r2.putExtra("com.mavenir.android.activation.extra_user_enabled", isChecked);
                    sendBroadcast(r2);
                    a(isChecked);
                    return true;
                }
                FgVoIP.U().a(com.mavenir.android.fragments.f.a.H3G_ACTIVATION, 0, 0, getString(k.activation_exception_lte_no_connection));
                this.n.setOnPreferenceClickListener(null);
                CheckBoxPreference checkBoxPreference = this.n;
                if (isChecked) {
                    isChecked = false;
                } else {
                    isChecked = true;
                }
                checkBoxPreference.setChecked(isChecked);
                this.n.setOnPreferenceClickListener(this);
                return true;
            }
        }
        return false;
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        String str;
        int findIndexOfValue;
        if (preference instanceof ListPreference) {
            str = (String) obj;
            ListPreference listPreference = (ListPreference) preference;
            findIndexOfValue = listPreference.findIndexOfValue(str);
            listPreference.setValueIndex(findIndexOfValue);
            listPreference.setSummary(str);
            this.A = true;
            if (listPreference.getKey() == "outgoing_calls_preference") {
                c.k.c(findIndexOfValue);
                return true;
            } else if (listPreference.getKey() == "media_alert_volume") {
                m.g(findIndexOfValue);
                return true;
            } else if (listPreference.getKey() == "contacts_display_order") {
                c.k.e(findIndexOfValue);
                return true;
            } else if (listPreference.getKey() == "contacts_sort_order") {
                c.k.f(findIndexOfValue);
                return true;
            }
        } else if (preference instanceof EditTextPreference) {
            str = (String) obj;
            EditTextPreference editTextPreference = (EditTextPreference) preference;
            this.A = true;
            if (editTextPreference.getKey() == "sms_messages_per_conversation_limit") {
                try {
                    findIndexOfValue = Integer.valueOf(str).intValue();
                    if (findIndexOfValue < 10) {
                        Toast.makeText(this, getString(k.preference_sms_limit_per_thread_warning), 0).show();
                        return false;
                    }
                    c.k.d(findIndexOfValue);
                    editTextPreference.setSummary(String.valueOf(Integer.valueOf(str)));
                    return true;
                } catch (NumberFormatException e) {
                    q.c("PreferenceGeneralActivity", e.getLocalizedMessage(), e.getCause());
                    Toast.makeText(this, getString(k.invalid_value), 0).show();
                }
            }
        } else if (preference instanceof RingtonePreference) {
            str = (String) obj;
            RingtonePreference ringtonePreference = (RingtonePreference) preference;
            ringtonePreference.setSummary(a(str));
            this.A = true;
            if (ringtonePreference.getKey() == "sms_notifications_ringtone") {
                c.k.b(str);
                return true;
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
        this.t = this.a;
        FgVoIP U = FgVoIP.U();
        if (!U.ai()) {
            this.b = new PreferenceCategory(this);
            this.b.setTitle(k.preference_general_startup_title);
            this.a.addPreference(this.b);
            this.t = this.b;
        }
        if (U.ai()) {
            this.c = new CheckBoxPreference(this);
            this.c.setLayoutResource(h.custom_checkbox_preference);
            this.c.setKey("save_user_data");
            this.c.setTitle(k.preference_general_save_user_data);
            this.c.setPersistent(false);
            this.c.setChecked(c.k.g());
            this.c.setSummaryOff(k.value_off);
            this.c.setSummaryOn(k.value_on);
            this.c.setOnPreferenceClickListener(this);
            this.t.addPreference(this.c);
        }
        this.d = new CheckBoxPreference(this);
        this.d.setLayoutResource(h.custom_checkbox_preference);
        this.d.setKey("start_after_phone_boot");
        this.d.setTitle(k.preference_general_boot_start);
        this.d.setPersistent(false);
        this.d.setChecked(c.k.h());
        this.d.setSummaryOff(k.value_off);
        this.d.setSummaryOn(k.value_on);
        this.d.setOnPreferenceClickListener(this);
        this.t.addPreference(this.d);
        if (FgVoIP.U().F()) {
            this.e = new CheckBoxPreference(this);
            this.e.setLayoutResource(h.custom_checkbox_preference);
            this.e.setKey("enable_status_icon");
            this.e.setTitle(k.preference_general_service_status_icon);
            this.e.setPersistent(false);
            this.e.setChecked(c.k.i());
            this.e.setSummaryOff(k.value_off);
            this.e.setSummaryOn(k.value_on);
            this.e.setOnPreferenceClickListener(this);
            this.t.addPreference(this.e);
        }
        if (FgVoIP.U().r()) {
            Preference createPreferenceScreen = getPreferenceManager().createPreferenceScreen(this);
            Intent intent = new Intent();
            intent.setClassName(this, "com.mavenir.android.activity.PreferenceCallAdditionalSettingsActivity");
            intent.putExtra("showAdvanced", false);
            createPreferenceScreen.setIntent(intent);
            createPreferenceScreen.setTitle(k.preference_ut_title);
            this.a.addPreference(createPreferenceScreen);
        }
        if (!U.ai()) {
            this.f = new PreferenceCategory(this);
            this.f.setTitle(k.preference_call_category);
            this.a.addPreference(this.f);
            this.g = new ListPreference(this);
            this.g.setKey("outgoing_calls_preference");
            this.g.setDialogTitle(k.preference_call_outgoing);
            this.g.setTitle(k.preference_call_outgoing);
            this.g.setEntries(b.preference_outgoing_calls);
            this.g.setEntryValues(b.preference_outgoing_calls);
            this.g.setPersistent(false);
            this.g.setValueIndex(c.k.j());
            this.g.setSummary(this.g.getEntry());
            this.g.setOnPreferenceChangeListener(this);
            this.f.addPreference(this.g);
        }
        this.t = this.a;
        if (!U.ai()) {
            this.h = new PreferenceCategory(this);
            this.h.setTitle(k.preference_sms_category);
            this.a.addPreference(this.h);
            this.t = this.h;
        }
        if (FgVoIP.U().G()) {
            this.s = new CheckBoxPreference(this);
            this.s.setLayoutResource(h.custom_checkbox_preference);
            this.s.setKey("enable_messaging");
            this.s.setTitle(k.preference_general_enable_messaging);
            this.s.setPersistent(false);
            this.s.setChecked(c.k.u());
            this.s.setSummaryOff(k.preference_general_enable_messaging_summary);
            this.s.setSummaryOn(k.preference_general_enable_messaging_summary);
            this.s.setOnPreferenceClickListener(this);
            this.t.addPreference(this.s);
        }
        if (U.aj()) {
            this.p = new CheckBoxPreference(this);
            this.p.setLayoutResource(h.custom_checkbox_preference);
            this.p.setKey("msg_request_display_reports");
            this.p.setTitle(k.preference_msg_display_reports);
            this.p.setPersistent(false);
            this.p.setChecked(c.k.r());
            this.p.setSummaryOff(k.preference_msg_display_reports_detailed);
            this.p.setSummaryOn(k.preference_msg_display_reports_detailed);
            this.p.setOnPreferenceClickListener(this);
            this.t.addPreference(this.p);
            this.q = new CheckBoxPreference(this);
            this.q.setLayoutResource(h.custom_checkbox_preference);
            this.q.setKey("msg_allow_display_reports");
            this.q.setTitle(k.preference_msg_display_reports_allow);
            this.q.setPersistent(false);
            this.q.setChecked(c.k.s());
            this.q.setSummaryOff(k.preference_msg_display_reports_allow_detailed);
            this.q.setSummaryOn(k.preference_msg_display_reports_allow_detailed);
            this.q.setOnPreferenceClickListener(this);
            this.t.addPreference(this.q);
        } else if (FgVoIP.U().K()) {
            this.i = new CheckBoxPreference(this);
            this.i.setLayoutResource(h.custom_checkbox_preference);
            this.i.setKey("sms_request_delivery_reports");
            this.i.setTitle(k.preference_sms_delivery);
            this.i.setPersistent(false);
            this.i.setChecked(c.k.l());
            this.i.setSummaryOff(k.preference_sms_delivery_detailed);
            this.i.setSummaryOn(k.preference_sms_delivery_detailed);
            this.i.setOnPreferenceClickListener(this);
            this.t.addPreference(this.i);
        }
        if (!U.ai()) {
            this.j = new CheckBoxPreference(this);
            this.j.setLayoutResource(h.custom_checkbox_preference);
            this.j.setKey("mms_auto_download_messages");
            this.j.setTitle(k.preference_mms_auto_download);
            this.j.setPersistent(false);
            this.j.setChecked(c.k.m());
            this.j.setSummaryOff(k.preference_mms_auto_download_detailed);
            this.j.setSummaryOn(k.preference_mms_auto_download_detailed);
            this.j.setOnPreferenceClickListener(this);
            this.t.addPreference(this.j);
        }
        if (!U.ai()) {
            this.k = new CheckBoxPreference(this);
            this.k.setLayoutResource(h.custom_checkbox_preference);
            this.k.setKey("sms_delete_old_messages");
            this.k.setTitle(k.preference_sms_delete_old);
            this.k.setPersistent(false);
            this.k.setChecked(c.k.n());
            this.k.setSummaryOff(k.preference_sms_delete_old_detailed);
            this.k.setSummaryOn(k.preference_sms_delete_old_detailed);
            this.k.setOnPreferenceClickListener(this);
            this.t.addPreference(this.k);
        }
        if (!U.ai()) {
            this.l = new EditTextPreference(this);
            this.l.setKey("sms_messages_per_conversation_limit");
            this.l.setDialogTitle(k.preference_sms_limit_per_thread);
            this.l.setTitle(k.preference_sms_limit_per_thread);
            this.l.setPersistent(false);
            t.a(this.l, 4);
            this.l.setSummary(String.valueOf(c.k.o()));
            this.l.setDefaultValue(String.valueOf(c.k.o()));
            this.l.setOnPreferenceChangeListener(this);
            this.l.getEditText().setInputType(2);
            this.t.addPreference(this.l);
            this.l.setEnabled(c.k.n());
        }
        this.m = new CheckBoxPreference(this);
        this.m.setLayoutResource(h.custom_checkbox_preference);
        this.m.setKey("sms_notify_incoming");
        this.m.setTitle(k.preference_sms_notify_incoming);
        this.m.setPersistent(false);
        this.m.setChecked(c.k.p());
        this.m.setSummaryOff(k.preference_sms_notify_incoming_detailed);
        this.m.setSummaryOn(k.preference_sms_notify_incoming_detailed);
        this.m.setOnPreferenceClickListener(this);
        this.t.addPreference(this.m);
        if (c.l.b()) {
            this.n = new CheckBoxPreference(this);
            this.n.setLayoutResource(h.custom_checkbox_preference);
            this.n.setKey("lte_800_user_activated");
            this.n.setTitle(k.preference_general_lte_enable);
            this.n.setPersistent(false);
            this.n.setChecked(c.l.c());
            this.n.setSummaryOff(k.preference_general_lte_enable_summary);
            this.n.setSummaryOn(k.preference_general_lte_enable_summary);
            this.n.setOnPreferenceClickListener(this);
            this.t.addPreference(this.n);
        }
        if (!U.ai()) {
            this.o = new RingtonePreference(this, this) {
                final /* synthetic */ PreferenceGeneralActivity a;

                protected Uri onRestoreRingtone() {
                    Object q = c.k.q();
                    return !TextUtils.isEmpty(q) ? Uri.parse(q) : null;
                }
            };
            this.o.setKey("sms_notifications_ringtone");
            this.o.setTitle(k.preference_sms_notify_ringtone);
            this.o.setPersistent(false);
            this.o.setRingtoneType(2);
            this.o.setShowDefault(true);
            this.o.setShowSilent(true);
            this.o.setSummary(a(c.k.q()));
            this.o.setOnPreferenceChangeListener(this);
            this.t.addPreference(this.o);
        }
        this.t = this.a;
        if (U.ai() && FgVoIP.U().Q()) {
            this.v = new ListPreference(this);
            this.v.setKey("media_alert_volume");
            this.v.setDialogTitle(k.preference_alert_tone_volume);
            this.v.setTitle(k.preference_alert_tone_volume);
            this.v.setEntries(b.alert_tone_volume_list);
            this.v.setEntryValues(b.alert_tone_volume_list);
            this.v.setPersistent(false);
            this.v.setValueIndex(m.k());
            this.v.setSummary(this.v.getEntry());
            this.v.setOnPreferenceChangeListener(this);
            this.t.addPreference(this.v);
        }
        this.r = new CheckBoxPreference(this);
        this.r.setLayoutResource(h.custom_checkbox_preference);
        this.r.setKey("enable_tcp_keep_alive");
        this.r.setTitle(k.preference_general_tcp_keep_alive);
        this.r.setPersistent(false);
        this.r.setChecked(c.k.t());
        this.r.setSummaryOff(k.preference_general_tcp_keep_alive_summary);
        this.r.setSummaryOn(k.preference_general_tcp_keep_alive_summary);
        this.r.setOnPreferenceClickListener(this);
        this.t.addPreference(this.r);
        if (U.w()) {
            this.u = new CheckBoxPreference(this);
            this.u.setLayoutResource(h.custom_checkbox_preference);
            this.u.setKey("network_performance");
            this.u.setTitle(k.preference_general_enable_net_performance);
            this.u.setPersistent(false);
            this.u.setChecked(f.b());
            this.u.setSummaryOff(k.value_off);
            this.u.setSummaryOn(k.value_on);
            this.u.setOnPreferenceClickListener(this);
            this.t.addPreference(this.u);
            createPreferenceScreen = new Preference(this);
            createPreferenceScreen.setKey("preference_general_change_pin");
            createPreferenceScreen.setIntent(new Intent(getString(k.action_change_pin)));
            createPreferenceScreen.setTitle(k.preference_general_change_pin);
            this.t.addPreference(createPreferenceScreen);
        }
        Intent a = y.a().a(this);
        if (a != null) {
            Preference createPreferenceScreen2 = getPreferenceManager().createPreferenceScreen(this);
            createPreferenceScreen2.setIntent(a);
            createPreferenceScreen2.setTitle("IPSec Setup");
            this.t.addPreference(createPreferenceScreen2);
        }
        if (U.ai()) {
            this.w = new PreferenceCategory(this);
            this.w.setTitle(k.preference_general_contacts_category);
            this.t.addPreference(this.w);
            CharSequence[] charSequenceArr = new String[]{getResources().getString(k.preference_general_contacts_display_order_first_name_first), getResources().getString(k.preference_general_contacts_display_order_last_name_first)};
            this.x = new ThemedListPreference(this);
            this.x.setKey("contacts_display_order");
            this.x.setDialogTitle(k.preference_general_contacts_display_order);
            this.x.setTitle(k.preference_general_contacts_display_order);
            this.x.setEntries(charSequenceArr);
            this.x.setEntryValues(charSequenceArr);
            this.x.setPersistent(false);
            this.x.setValueIndex(c.k.y());
            this.x.setSummary(this.x.getEntry());
            this.x.setOnPreferenceChangeListener(this);
            this.w.addPreference(this.x);
            charSequenceArr = new String[]{getResources().getString(k.preference_general_contacts_sort_by_first_name), getResources().getString(k.preference_general_contacts_sort_by_last_name)};
            this.y = new ThemedListPreference(this);
            this.y.setKey("contacts_sort_order");
            this.y.setDialogTitle(k.preference_general_contacts_sort_order);
            this.y.setTitle(k.preference_general_contacts_sort_order);
            this.y.setEntries(charSequenceArr);
            this.y.setEntryValues(charSequenceArr);
            this.y.setPersistent(false);
            this.y.setValueIndex(c.k.z());
            this.y.setSummary(this.y.getEntry());
            this.y.setOnPreferenceChangeListener(this);
            this.w.addPreference(this.y);
        }
        setPreferenceScreen(this.a);
    }

    private String a(String str) {
        Ringtone ringtone = null;
        if (str != null && (str.equals("Default") || str.equals(com.mavenir.android.settings.b.z))) {
            return getResources().getString(k.default_ringtone);
        }
        Uri parse = TextUtils.isEmpty(str) ? null : Uri.parse(str);
        if (parse != null) {
            ringtone = RingtoneManager.getRingtone(this, parse);
        }
        return ringtone != null ? ringtone.getTitle(this) : getResources().getString(k.silent_ringtone);
    }

    private void a(boolean z) {
        CharSequence string;
        this.D = new ProgressDialog(this);
        this.D.setCanceledOnTouchOutside(false);
        this.D.setCancelable(false);
        this.D.setTitle(null);
        ProgressDialog progressDialog = this.D;
        if (z) {
            string = getString(k.preference_general_lte_activate_progress);
        } else {
            string = getString(k.preference_general_lte_deactivate_progress);
        }
        progressDialog.setMessage(string);
        this.D.setIndeterminate(true);
        this.D.show();
        this.E.postDelayed(this.F, 30000);
    }

    private void b() {
        if (this.D != null && this.D.isShowing()) {
            this.D.dismiss();
        }
    }

    private void a(int i, int i2, String str) {
        b();
        this.E.removeCallbacks(this.F);
        q.d("PreferenceGeneralActivity", "serviceActivationCnf(): errCode: " + i + ", reasonCode: " + i2 + ", msg: " + str);
        if (c.l.b()) {
            this.t.addPreference(this.n);
        }
        this.n.setOnPreferenceClickListener(null);
        if (i2 == ActivationAdapter.REASON_SERVICE_ACTIVATION_OK) {
            this.n.setChecked(true);
            c.l.b(true);
        } else {
            this.n.setChecked(false);
            c.l.b(false);
        }
        this.n.setOnPreferenceClickListener(this);
        if (!TextUtils.isEmpty(str)) {
            FgVoIP.U().a(com.mavenir.android.fragments.f.a.H3G_ACTIVATION, i, 0, str);
        }
        FgVoIP.U().a("com.mavenir.android.ActionConectivityChange");
    }

    private void b(int i, int i2, String str) {
        b();
        this.E.removeCallbacks(this.F);
        q.d("PreferenceGeneralActivity", "serviceDectivationCnf(): errCode: " + i + ", reasonCode: " + i2 + ", msg: " + str);
        this.n.setOnPreferenceClickListener(null);
        if (i2 == ActivationAdapter.REASON_SERVICE_DEACTIVATION_OK) {
            this.n.setChecked(false);
            c.l.b(false);
        } else {
            this.n.setChecked(true);
            c.l.b(true);
        }
        this.n.setOnPreferenceClickListener(this);
        if (!TextUtils.isEmpty(str)) {
            FgVoIP.U().a(com.mavenir.android.fragments.f.a.H3G_ACTIVATION, i, 0, str);
        }
        FgVoIP.U().a("com.mavenir.android.ActionConectivityChange");
    }
}
