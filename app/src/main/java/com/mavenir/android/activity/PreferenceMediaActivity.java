package com.mavenir.android.activity;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceCategory;
import android.preference.PreferenceScreen;
import android.view.MenuItem;
import android.widget.Toast;
import com.fgmicrotec.mobile.android.fgvoip.FgVoIP;
import com.fgmicrotec.mobile.android.fgvoip.f.b;
import com.fgmicrotec.mobile.android.fgvoip.f.k;
import com.mavenir.android.common.q;
import com.mavenir.android.common.t;
import com.mavenir.android.settings.c.e;
import com.mavenir.android.settings.c.m;
import com.mavenir.android.settings.c.p;

public class PreferenceMediaActivity extends PreferenceActivity implements OnPreferenceChangeListener, OnPreferenceClickListener {
    private PreferenceScreen a;
    private PreferenceCategory b;
    private ListPreference c;
    private ListPreference d;
    private CheckBoxPreference e;
    private e[] f;
    private PreferenceCategory g;
    private EditTextPreference h;
    private EditTextPreference i;
    private EditTextPreference j;
    private PreferenceCategory k;
    private ListPreference l;
    private ListPreference m;
    private PreferenceCategory n;
    private ListPreference o;
    private boolean p = false;
    private boolean q = false;
    private boolean r = true;
    private PreferenceCategory s;
    private ListPreference t;

    @TargetApi(11)
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (VERSION.SDK_INT >= 11) {
            ActionBar actionBar = getActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(k.preference_media_title);
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
        boolean z = false;
        String str;
        if (preference instanceof EditTextPreference) {
            str = (String) obj;
            EditTextPreference editTextPreference = (EditTextPreference) preference;
            if (editTextPreference.getEditText().getInputType() == 2) {
                try {
                    Integer.valueOf(str);
                    editTextPreference.setSummary(String.valueOf(Integer.valueOf(str)));
                } catch (NumberFormatException e) {
                    q.c("PreferenceMediaActivity", e.getLocalizedMessage(), e.getCause());
                    Toast.makeText(this, getString(k.invalid_value), 0).show();
                    return false;
                }
            }
            editTextPreference.setSummary(str);
            if (editTextPreference.getKey() == "media_overflow_mark") {
                m.c(Integer.valueOf(str).intValue());
                return true;
            } else if (editTextPreference.getKey() == "media_high_watermark") {
                m.d(Integer.valueOf(str).intValue());
                return true;
            } else if (editTextPreference.getKey() == "media_low_watermark") {
                m.e(Integer.valueOf(str).intValue());
                return true;
            }
        } else if (preference instanceof ListPreference) {
            str = (String) obj;
            ListPreference listPreference = (ListPreference) preference;
            int findIndexOfValue = listPreference.findIndexOfValue(str);
            listPreference.setValueIndex(findIndexOfValue);
            listPreference.setSummary(str);
            if (listPreference.getKey() == "media_codec_type") {
                e eVar = this.f[findIndexOfValue];
                m.a(eVar.a());
                if (this.q) {
                    int[] c = eVar.c();
                    CharSequence[] d = eVar.d();
                    if (c == null || d == null) {
                        this.d.setEnabled(false);
                        this.d.setEntries(null);
                        this.d.setEntryValues(null);
                    } else {
                        this.d.setEnabled(true);
                        this.d.setEntries(d);
                        this.d.setEntryValues(d);
                        m.b(c[c.length - 1]);
                        this.d.setValueIndex(m.e());
                        this.d.setSummary(this.d.getEntry());
                    }
                }
                if (!this.p) {
                    return true;
                }
                CheckBoxPreference checkBoxPreference = this.e;
                if (eVar.e() != null) {
                    z = true;
                }
                checkBoxPreference.setEnabled(z);
                return true;
            } else if (listPreference.getKey() == "media_codec_mode") {
                m.b(findIndexOfValue);
                return true;
            } else if (listPreference.getKey() == "media_dtmf_signalization") {
                m.f(findIndexOfValue);
                return true;
            } else if (listPreference.getKey() == "media_alert_volume") {
                m.g(findIndexOfValue);
                return true;
            } else if (listPreference.getKey() == "media_video_codec_type") {
                m.h(findIndexOfValue);
                if (findIndexOfValue == 0) {
                    this.m.setEntries(b.list_of_video_sizes_h263);
                    this.m.setEntryValues(b.list_of_video_sizes_h263);
                } else {
                    this.m.setEntries(b.list_of_video_sizes);
                    this.m.setEntryValues(b.list_of_video_sizes);
                }
                this.m.setValueIndex(m.m());
                this.m.setSummary(this.m.getEntry());
                return true;
            } else if (listPreference.getKey() == "media_video_size_index") {
                m.i(findIndexOfValue);
                return true;
            }
        }
        return false;
    }

    public boolean onPreferenceClick(Preference preference) {
        if (preference instanceof CheckBoxPreference) {
            boolean isChecked = ((CheckBoxPreference) preference).isChecked();
            if (preference.getKey() == "amr_format_octet_aligned") {
                m.a(isChecked);
                return true;
            }
        }
        return false;
    }

    private void a() {
        this.a = getPreferenceManager().createPreferenceScreen(this);
        this.b = new PreferenceCategory(this);
        this.b.setTitle(k.preference_codec_category);
        this.a.addPreference(this.b);
        this.f = e.g();
        CharSequence[] a = e.a(this.f);
        int b = e.b(this.f, m.b());
        if (b == -1) {
            b = 0;
        }
        e eVar = this.f[b];
        this.c = new ListPreference(this);
        this.c.setKey("media_codec_type");
        this.c.setDialogTitle(k.preference_media_codec_type);
        this.c.setTitle(k.preference_media_codec_type);
        this.c.setEntries(a);
        this.c.setEntryValues(a);
        this.c.setPersistent(false);
        this.c.setValueIndex(b);
        this.c.setSummary(this.c.getEntry());
        this.c.setOnPreferenceChangeListener(this);
        this.b.addPreference(this.c);
        if (this.q) {
            this.d = new ListPreference(this);
            this.d.setKey("media_codec_mode");
            this.d.setDialogTitle(k.preference_media_codec_mode);
            this.d.setTitle(k.preference_media_codec_mode);
            int[] c = eVar.c();
            a = eVar.d();
            if (c == null || a == null) {
                this.d.setEnabled(false);
                this.d.setEntries(null);
                this.d.setEntryValues(null);
            } else {
                this.d.setEnabled(true);
                this.d.setEntries(a);
                this.d.setEntryValues(a);
                this.d.setValueIndex(m.e());
            }
            this.d.setPersistent(false);
            this.d.setSummary(this.d.getEntry());
            this.d.setOnPreferenceChangeListener(this);
            this.b.addPreference(this.d);
        }
        if (this.p) {
            this.e = new CheckBoxPreference(this);
            this.e.setKey("amr_format_octet_aligned");
            this.e.setTitle(k.preference_amr_format_octet_aligned);
            this.e.setPersistent(false);
            this.e.setChecked(m.f());
            this.e.setEnabled(eVar.e() != null);
            this.e.setSummaryOff(k.preference_octet_aligned_disabled);
            this.e.setSummaryOn(k.preference_octet_aligned_enabled);
            this.e.setOnPreferenceClickListener(this);
            this.b.addPreference(this.e);
        }
        this.g = new PreferenceCategory(this);
        this.g.setTitle(k.preference_decoder_framer_category);
        this.a.addPreference(this.g);
        this.h = new EditTextPreference(this);
        this.h.setKey("media_overflow_mark");
        this.h.setDialogTitle(k.preference_media_overflow_mark);
        this.h.setTitle(k.preference_media_overflow_mark);
        this.h.setPersistent(false);
        t.a(this.h, 3);
        this.h.setSummary(Integer.toString(m.g()));
        this.h.setDefaultValue(Integer.toString(m.g()));
        this.h.setOnPreferenceChangeListener(this);
        this.h.getEditText().setInputType(2);
        this.g.addPreference(this.h);
        this.i = new EditTextPreference(this);
        this.i.setKey("media_high_watermark");
        this.i.setDialogTitle(k.preference_media_high_watermark);
        this.i.setTitle(k.preference_media_high_watermark);
        this.i.setPersistent(false);
        t.a(this.i, 3);
        this.i.setSummary(Integer.toString(m.h()));
        this.i.setDefaultValue(Integer.toString(m.h()));
        this.i.setOnPreferenceChangeListener(this);
        this.i.getEditText().setInputType(2);
        this.g.addPreference(this.i);
        this.j = new EditTextPreference(this);
        this.j.setKey("media_low_watermark");
        this.j.setDialogTitle(k.preference_media_low_watermark);
        this.j.setTitle(k.preference_media_low_watermark);
        this.j.setPersistent(false);
        t.a(this.j, 3);
        this.j.setSummary(Integer.toString(m.i()));
        this.j.setDefaultValue(Integer.toString(m.i()));
        this.j.setOnPreferenceChangeListener(this);
        this.j.getEditText().setInputType(2);
        this.g.addPreference(this.j);
        if (FgVoIP.U().aj()) {
            this.r = true;
        }
        if (this.r) {
            this.s = new PreferenceCategory(this);
            this.s.setTitle(k.preference_dtmf_category);
            this.a.addPreference(this.s);
            this.t = new ListPreference(this);
            this.t.setKey("media_dtmf_signalization");
            this.t.setDialogTitle(k.preference_dtmf_signalization);
            this.t.setTitle(k.preference_dtmf_signalization);
            this.t.setEntries(b.dtmf_signalization_list);
            this.t.setEntryValues(b.dtmf_signalization_list);
            this.t.setPersistent(false);
            this.t.setValueIndex(m.j());
            this.t.setSummary(this.t.getEntry());
            this.t.setOnPreferenceChangeListener(this);
            this.s.addPreference(this.t);
        }
        if (FgVoIP.U().aj()) {
            this.k = new PreferenceCategory(this);
            this.k.setTitle(k.preference_video_calls_category);
            this.a.addPreference(this.k);
            this.l = new ListPreference(this);
            this.l.setKey("media_video_codec_type");
            this.l.setDialogTitle(k.preference_media_video_codec_type);
            this.l.setTitle(k.preference_media_video_codec_type);
            this.l.setEntries(b.list_of_video_codecs);
            this.l.setEntryValues(b.list_of_video_codecs);
            this.l.setPersistent(false);
            this.l.setValueIndex(m.l());
            this.l.setSummary(this.l.getEntry());
            this.l.setOnPreferenceChangeListener(this);
            this.k.addPreference(this.l);
            this.m = new ListPreference(this);
            this.m.setKey("media_video_size_index");
            this.m.setDialogTitle(k.preference_media_video_size);
            this.m.setTitle(k.preference_media_video_size);
            if (m.l() == 0) {
                this.m.setEntries(b.list_of_video_sizes_h263);
                this.m.setEntryValues(b.list_of_video_sizes_h263);
            } else {
                this.m.setEntries(b.list_of_video_sizes);
                this.m.setEntryValues(b.list_of_video_sizes);
            }
            this.m.setPersistent(false);
            this.m.setValueIndex(m.m());
            this.m.setSummary(this.m.getEntry());
            this.m.setOnPreferenceChangeListener(this);
            this.k.addPreference(this.m);
        }
        if (!FgVoIP.U().ai()) {
            this.n = new PreferenceCategory(this);
            this.n.setTitle(k.preference_audio_category);
            this.a.addPreference(this.n);
            this.o = new ListPreference(this);
            this.o.setKey("media_alert_volume");
            this.o.setDialogTitle(k.preference_alert_tone_volume);
            this.o.setTitle(k.preference_alert_tone_volume);
            this.o.setEntries(b.alert_tone_volume_list);
            this.o.setEntryValues(b.alert_tone_volume_list);
            this.o.setPersistent(false);
            this.o.setValueIndex(m.k());
            this.o.setSummary(this.o.getEntry());
            this.o.setOnPreferenceChangeListener(this);
            this.n.addPreference(this.o);
        }
        setPreferenceScreen(this.a);
    }
}
