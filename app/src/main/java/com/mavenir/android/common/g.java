package com.mavenir.android.common;

import android.content.res.AssetFileDescriptor;
import android.database.ContentObserver;
import android.media.AudioManager;
import android.media.AudioManager.OnAudioFocusChangeListener;
import android.media.MediaPlayer;
import android.media.ToneGenerator;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Vibrator;
import android.provider.Settings.System;
import android.text.TextUtils;
import com.fgmicrotec.mobile.android.a.b;
import com.fgmicrotec.mobile.android.fgmag.VoIP;
import com.fgmicrotec.mobile.android.fgvoip.FgVoIP;
import com.fgmicrotec.mobile.android.fgvoip.f.k;
import com.mavenir.android.settings.c.m;
import com.mavenir.android.vtow.activation.ActivationAdapter;

public class g implements OnAudioFocusChangeListener {
    private static g a;
    private AudioManager b;
    private Handler c;
    private a d;
    private ToneGenerator e;
    private ToneGenerator f;
    private ToneGenerator g;
    private MediaPlayer h;
    private MediaPlayer i;
    private MediaPlayer j;
    private MediaPlayer k;
    private Vibrator l;
    private boolean m;
    private boolean n;
    private boolean o;
    private boolean p;
    private boolean q;
    private boolean r;
    private int s;
    private String t;
    private int u;
    private Runnable v;
    private Runnable w;

    private class a extends ContentObserver {
        final /* synthetic */ g a;
        private int b;

        public a(g gVar, Handler handler) {
            this.a = gVar;
            super(handler);
            this.b = gVar.b.getStreamVolume(2);
            q.a("CallNotifier", "SettingsContentObserver(): initial volume: " + this.b);
        }

        public void onChange(boolean z) {
            super.onChange(z);
            int streamVolume = this.a.b.getStreamVolume(3);
            q.a("CallNotifier", "onChange(): new volume: " + streamVolume);
            if (this.b - streamVolume != 0) {
                this.a.e();
            }
        }
    }

    public static g a() {
        if (a == null) {
            a = new g();
        }
        return a;
    }

    private g() {
        this.b = null;
        this.h = null;
        this.i = null;
        this.j = null;
        this.k = null;
        this.l = null;
        this.m = false;
        this.n = false;
        this.o = false;
        this.p = false;
        this.q = false;
        this.r = false;
        this.s = 0;
        this.t = "";
        this.u = 0;
        this.v = new Runnable(this) {
            final /* synthetic */ g a;

            {
                this.a = r1;
            }

            public void run() {
                if (this.a.s < 3) {
                    this.a.s = this.a.s + 1;
                    this.a.c.postDelayed(this.a.v, 750);
                    this.a.f.startTone(30, 250);
                    return;
                }
                this.a.s = 0;
            }
        };
        this.w = new Runnable(this) {
            final /* synthetic */ g a;

            {
                this.a = r1;
            }

            public void run() {
                q.a("CallNotifier", "abandonAudioFocus(): result: " + (this.a.b.abandonAudioFocus(this.a) == 1 ? "GRANTED" : "DENIED"));
            }
        };
        this.b = (AudioManager) FgVoIP.U().getSystemService("audio");
        this.c = new Handler();
    }

    public void onAudioFocusChange(int i) {
        switch (i) {
            case ActivationAdapter.VERS_INITIAL /*-3*/:
                q.a("CallNotifier", "onAudioFocusChange(): AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK");
                return;
            case -2:
                q.a("CallNotifier", "onAudioFocusChange(): AUDIOFOCUS_LOSS_TRANSIENT");
                return;
            case -1:
                q.a("CallNotifier", "onAudioFocusChange(): AUDIOFOCUS_LOSS");
                return;
            case 1:
                q.a("CallNotifier", "onAudioFocusChange(): AUDIOFOCUS_GAIN");
                return;
            default:
                q.a("CallNotifier", "onAudioFocusChange(): App has requested: " + i);
                return;
        }
    }

    private void c(boolean z) {
        int k = m.k();
        if (k != 0) {
            float f;
            if (k == 1) {
                f = 0.3f;
            } else if (k == 2) {
                f = 0.5f;
            } else {
                f = 0.8f;
            }
            try {
                if (this.h == null) {
                    this.h = new MediaPlayer();
                } else {
                    this.h.reset();
                }
                AssetFileDescriptor openFd = FgVoIP.U().getAssets().openFd(FgVoIP.U().getString(k.DEF_QOS_TONE));
                this.h.setDataSource(openFd.getFileDescriptor(), openFd.getStartOffset(), openFd.getLength());
                this.h.setAudioStreamType(0);
                this.h.setVolume(f, f);
                this.h.prepare();
                this.h.setLooping(z);
                q.a("CallNotifier", "initializeAlertTonePlayer(): initialized");
            } catch (Exception e) {
                q.d("CallNotifier", "initializeAlertTonePlayer(): unable to initialize: " + e);
            }
        }
    }

    private boolean d(boolean z) {
        boolean z2 = false;
        if (this.e == null || z) {
            try {
                int i;
                int k = m.k();
                if (k == 0) {
                    z2 = true;
                    i = false;
                } else if (k == 1) {
                    i = 30;
                } else if (k == 2) {
                    i = 50;
                } else {
                    i = 80;
                }
                this.e = new ToneGenerator(0, i);
                q.a("CallNotifier", "initializeAlertToneGenerator(): initialized! volume=" + i);
            } catch (RuntimeException e) {
                q.d("CallNotifier", "initializeAlertToneGenerator(): unable to initialize: " + e);
                this.e = null;
            }
        }
        return z2;
    }

    private void a(String str, boolean z) {
        try {
            Uri d = k.d(str);
            if (this.k == null) {
                this.k = new MediaPlayer();
            } else {
                this.k.reset();
            }
            if (this.k != null) {
                this.k.setDataSource(FgVoIP.U(), d);
                this.k.setAudioStreamType(2);
                this.k.prepare();
                this.k.setLooping(z);
                q.a("CallNotifier", "initializeRingtonePlayer(): initialized");
            }
            t();
        } catch (Exception e) {
            q.d("CallNotifier", "initializeRingtonePlayer(): Unable to initialize: " + e);
            throw e;
        }
    }

    private void e(boolean z) {
        try {
            if (this.i == null) {
                this.i = new MediaPlayer();
            } else {
                this.i.reset();
            }
            AssetFileDescriptor openFd = FgVoIP.U().getAssets().openFd(FgVoIP.U().getString(k.DEF_CALL_WAITING_TONE));
            this.i.setDataSource(openFd.getFileDescriptor(), openFd.getStartOffset(), openFd.getLength());
            this.i.setAudioStreamType(0);
            this.i.prepare();
            this.i.setLooping(!z);
            q.a("CallNotifier", "initializeCallWaitingPlayer(): initialized!");
            this.i.start();
        } catch (Exception e) {
            q.d("CallNotifier", "initializeCallWaitingPlayer(): unable to initialize: " + e.getLocalizedMessage());
            throw e;
        }
    }

    private void f(boolean z) {
        try {
            if (this.j == null) {
                this.j = new MediaPlayer();
            } else {
                this.j.reset();
            }
            AssetFileDescriptor openFd = FgVoIP.U().getAssets().openFd(FgVoIP.U().getString(k.DEF_RINGBACK_TONE));
            this.j.setDataSource(openFd.getFileDescriptor(), openFd.getStartOffset(), openFd.getLength());
            this.j.setAudioStreamType(0);
            this.j.prepare();
            this.j.setLooping(z);
            q.a("CallNotifier", "initializeRingbackPlayer(): initialized!");
        } catch (Throwable e) {
            q.c("CallNotifier", "initializeRingbackPlayer(): unable to initialize: ", e);
        }
    }

    private void p() {
        if (this.f == null) {
            try {
                this.f = new ToneGenerator(0, 80);
                q.a("CallNotifier", "initializeBusyToneGenerator(): initialized");
            } catch (RuntimeException e) {
                q.d("CallNotifier", "initializeBusyToneGenerator(): unable to initialize: " + e);
                this.f = null;
            }
        }
    }

    private void q() {
        if (this.g == null) {
            try {
                this.g = new ToneGenerator(0, 80);
                q.a("CallNotifier", "initializeIncomingCallToneGenerator(): initialized");
            } catch (RuntimeException e) {
                q.d("CallNotifier", "initializeIncomingCallToneGenerator(): unable to initialize: " + e);
                this.g = null;
            }
        }
    }

    public boolean b() {
        int ringerMode = this.b.getRingerMode();
        if (ringerMode == 2 || ringerMode == 1) {
            return true;
        }
        return false;
    }

    public void c() {
        try {
            if (FgVoIP.U().ai()) {
                c(true);
                this.h.start();
                q.a("CallNotifier", "startPlayingAlertTone(): alert tone player started");
            } else if (d(false)) {
                this.e.startTone(24, ActivationAdapter.OP_CONFIGURATION_INITIAL);
                q.a("CallNotifier", "startPlayingAlertTone(): alert tone started");
            }
        } catch (Exception e) {
            q.d("CallNotifier", "startPlayingAlertTone(): unable to start: " + e);
        }
    }

    public void d() {
        if (FgVoIP.U().ai()) {
            r();
        } else {
            s();
        }
    }

    private void r() {
        try {
            if (this.h != null && this.h.isPlaying()) {
                this.h.stop();
                q.a("CallNotifier", "stopPlayingAlertTonePlayer(): stopped");
            }
        } catch (Exception e) {
            q.d("CallNotifier", "stopPlayingAlertTonePlayer(): unable to stop: " + e);
        }
    }

    private void s() {
        try {
            if (this.e != null) {
                this.e.stopTone();
                q.d("CallNotifier", "stopPlayingAlertToneGenerator(): stopped");
            }
        } catch (Exception e) {
            q.d("CallNotifier", "stopPlayingAlertToneGenerator(): unable to stop: " + e);
        }
    }

    public void a(String str) {
        try {
            this.t = str;
            if (!this.p) {
                a(str, true);
                this.u = this.b.getMode();
                this.k.start();
                this.p = true;
                q.a("CallNotifier", "startPlayingRingtone(): ringtone started");
            }
        } catch (Exception e) {
            this.p = false;
            q.d("CallNotifier", "startPlayingRingtone(): Unable to start ringtone: " + e);
        }
        n();
    }

    public void e() {
        if (this.p && this.k != null) {
            try {
                this.k.stop();
                q.a("CallNotifier", "stopPlayingRingtone(): ringtone stopped");
                u();
                try {
                    if (this.k != null) {
                        this.k.release();
                        this.k = null;
                    }
                } catch (Exception e) {
                    q.d("CallNotifier", "stopPlayingRingtone(): unable to release resources: " + e);
                }
            } catch (IllegalStateException e2) {
                q.d("CallNotifier", "Unable to stop playing ringtone - " + e2);
                try {
                    if (this.k != null) {
                        this.k.release();
                        this.k = null;
                    }
                } catch (Exception e3) {
                    q.d("CallNotifier", "stopPlayingRingtone(): unable to release resources: " + e3);
                }
            } catch (Throwable th) {
                try {
                    if (this.k != null) {
                        this.k.release();
                        this.k = null;
                    }
                } catch (Exception e4) {
                    q.d("CallNotifier", "stopPlayingRingtone(): unable to release resources: " + e4);
                }
            }
            this.p = false;
        }
        o();
    }

    public boolean f() {
        return this.p;
    }

    public String g() {
        return this.t;
    }

    public void a(boolean z) {
        try {
            if (TextUtils.isEmpty(FgVoIP.U().getString(k.DEF_CALL_WAITING_TONE))) {
                p();
                if (z) {
                    this.f.startTone(22, VoIP.REASON_CODE_SERVER_INTERNAL_ERROR);
                } else {
                    this.f.startTone(22);
                }
            } else {
                e(z);
            }
            q.a("CallNotifier", "startPlayingCallWaitingTone(): started!");
            this.m = true;
        } catch (Exception e) {
            this.m = false;
            e.printStackTrace();
        }
    }

    public void h() {
        if (this.m) {
            try {
                if (TextUtils.isEmpty(FgVoIP.U().getString(k.DEF_CALL_WAITING_TONE))) {
                    this.f.stopTone();
                } else {
                    this.i.stop();
                }
                try {
                    if (this.i != null) {
                        this.i.release();
                        this.i = null;
                    }
                } catch (Exception e) {
                    q.d("CallNotifier", "stopPlayingCallWaitingTone(): unable to release resources: " + e);
                }
            } catch (IllegalStateException e2) {
                q.d("CallNotifier", "stopPlayingCallWaitingTone(): " + e2);
                try {
                    if (this.i != null) {
                        this.i.release();
                        this.i = null;
                    }
                } catch (Exception e3) {
                    q.d("CallNotifier", "stopPlayingCallWaitingTone(): unable to release resources: " + e3);
                }
            } catch (Throwable th) {
                try {
                    if (this.i != null) {
                        this.i.release();
                        this.i = null;
                    }
                } catch (Exception e4) {
                    q.d("CallNotifier", "stopPlayingCallWaitingTone(): unable to release resources: " + e4);
                }
            }
            this.m = false;
        }
    }

    public void i() {
        try {
            q();
            this.g.startTone(22);
            q.a("CallNotifier", "startPlayingIncomingCallTone(): started");
            this.n = true;
        } catch (Exception e) {
            q.d("CallNotifier", "startPlayingIncomingCallTone(): unable to start: " + e);
            this.n = false;
        }
    }

    public void j() {
        if (this.n) {
            try {
                if (this.g != null) {
                    this.g.stopTone();
                    q.a("CallNotifier", "stopPlayingIncomingCallTone(): stopped");
                }
            } catch (Exception e) {
                q.d("CallNotifier", "stopPlayingIncomingCallTone(): unable to stop: " + e);
                this.g = null;
            }
            this.n = false;
        }
    }

    public void b(boolean z) {
        if (!this.o && !z) {
            try {
                if (TextUtils.isEmpty(FgVoIP.U().getString(k.DEF_RINGBACK_TONE))) {
                    b.a(FgVoIP.U(), true);
                } else {
                    f(true);
                    this.j.start();
                    this.o = true;
                }
                q.a("CallNotifier", "startPlayingRingback(): started");
            } catch (Exception e) {
                q.d("CallNotifier", "startPlayingRingback(): unable to start");
            }
            this.o = true;
        }
    }

    public boolean k() {
        try {
            if (TextUtils.isEmpty(FgVoIP.U().getString(k.DEF_RINGBACK_TONE))) {
                b.a(FgVoIP.U(), false);
                q.a("CallNotifier", "stopPlayingRingback(): stopped");
            } else if (this.j != null && this.j.isPlaying()) {
                this.j.stop();
                q.a("CallNotifier", "stopPlayingRingback(): stopped");
            }
            try {
                if (this.j != null) {
                    this.j.release();
                    this.j = null;
                }
            } catch (Exception e) {
                q.d("CallNotifier", "stopPlayingRingback(): unable to release resources: " + e);
            }
        } catch (Exception e2) {
            q.d("CallNotifier", "stopPlayingRingback(): unable to stop ringback: " + e2);
            try {
                if (this.j != null) {
                    this.j.release();
                    this.j = null;
                }
            } catch (Exception e22) {
                q.d("CallNotifier", "stopPlayingRingback(): unable to release resources: " + e22);
            }
        } catch (Throwable th) {
            try {
                if (this.j != null) {
                    this.j.release();
                    this.j = null;
                }
            } catch (Exception e3) {
                q.d("CallNotifier", "stopPlayingRingback(): unable to release resources: " + e3);
            }
        }
        this.o = false;
        return false;
    }

    public void a(int i) {
        try {
            int i2;
            if (i == com.fgmicrotec.mobile.android.fgmag.VoIP.a.BUSY_TONE_USER.ordinal()) {
                i2 = 17;
            } else if (i == com.fgmicrotec.mobile.android.fgmag.VoIP.a.BUSY_TONE_NETWORK.ordinal()) {
                i2 = 30;
            } else {
                return;
            }
            p();
            if (this.f != null) {
                if (i == com.fgmicrotec.mobile.android.fgmag.VoIP.a.BUSY_TONE_NETWORK.ordinal()) {
                    q.a("CallNotifier", "startPlayingBusyTone(): network busy tone started");
                    this.c.post(this.v);
                } else {
                    this.f.startTone(i2);
                    q.a("CallNotifier", "startPlayingBusyTone(): started");
                }
                this.q = true;
            }
        } catch (Exception e) {
            this.q = false;
            q.d("CallNotifier", "startPlayingBusyTone(): " + e);
        }
    }

    public void l() {
        if (this.q) {
            try {
                if (this.f != null) {
                    this.f.stopTone();
                    q.a("CallNotifier", "stopPlayingBusyTone(): stopped");
                    this.c.removeCallbacks(this.v);
                }
            } catch (Exception e) {
                q.d("CallNotifier", "stopPlayingBusyTone(): unable to stop: " + e);
            }
            this.q = false;
        }
    }

    public void m() {
        try {
            p();
            if (this.f != null) {
                this.f.startTone(30, VoIP.REASON_CODE_SERVER_INTERNAL_ERROR);
                q.a("CallNotifier", "startPlayingCallEndedTone(): started");
            }
        } catch (Exception e) {
            q.d("CallNotifier", "startPlayingCallEndedTone(): " + e);
        }
    }

    public void n() {
        if (b() && !this.r) {
            this.l = (Vibrator) FgVoIP.U().getSystemService("vibrator");
            if (this.l != null) {
                this.l.vibrate(new long[]{0, 1000, 1000}, 0);
                this.r = true;
                q.a("CallNotifier", "startVibrator(): started!");
                return;
            }
            this.r = false;
            q.d("CallNotifier", "startVibrator(): Unable to start vibrator!");
        }
    }

    public void o() {
        if (this.l != null && this.r) {
            this.l.cancel();
            this.r = false;
            q.a("CallNotifier", "stopVibrator(): stopped!");
        }
    }

    public boolean a(int i, int i2) {
        boolean z = true;
        if (this.b.requestAudioFocus(this, i, i2) != 1) {
            z = false;
        }
        q.a("CallNotifier", "requestAudioFocus(): focus " + (z ? "GRANTED" : "DENIED") + " on " + b(i));
        return z;
    }

    public String b(int i) {
        switch (i) {
            case 0:
                return "STREAM VOICE CALL";
            case 1:
                return "STREAM SYSTEM";
            case 2:
                return "STREAM RING";
            case 3:
                return "STREAM MUSIC";
            case 4:
                return "STREAM ALARM";
            case 5:
                return "STREAM NOTIFICATION";
            case 8:
                return "STREAM DTMF";
            default:
                return "STREAM UNKNOWN";
        }
    }

    private void t() {
        if (VERSION.SDK_INT > 19) {
            this.d = new a(this, this.c);
            FgVoIP.U().getContentResolver().registerContentObserver(System.CONTENT_URI, true, this.d);
            q.a("CallNotifier", "registerVolumeChangeObserver(): Listening for volume change");
        }
    }

    private void u() {
        if (VERSION.SDK_INT > 19 && this.d != null) {
            FgVoIP.U().getContentResolver().unregisterContentObserver(this.d);
            q.a("CallNotifier", "unregisterVolumeChangeObserver(): Stopped listening for volume change");
        }
    }
}
