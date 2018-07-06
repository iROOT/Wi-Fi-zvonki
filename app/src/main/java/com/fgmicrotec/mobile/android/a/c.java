package com.fgmicrotec.mobile.android.a;

import android.annotation.SuppressLint;
import android.media.AudioRecord;
import android.media.audiofx.AcousticEchoCanceler;
import android.media.audiofx.AutomaticGainControl;
import android.media.audiofx.NoiseSuppressor;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Process;
import com.fgmicrotec.mobile.android.fgmag.SimpleCodecAL;
import com.fgmicrotec.mobile.android.fgvoip.FgVoIP;
import com.fgmicrotec.mobile.android.fgvoip.f;
import com.mavenir.android.common.q;
import com.mavenir.android.fragments.f.a;
import com.mavenir.android.fragments.f.b;
import net.hockeyapp.android.k;

@SuppressLint({"NewApi"})
public class c extends Thread {
    private static boolean g = false;
    private int a;
    private int b = k.FEEDBACK_FAILED_TITLE_ID;
    private int c;
    private long d;
    private int e;
    private int f = 0;
    private boolean h = false;
    private boolean i = false;
    private long j = 0;
    private boolean k = false;
    private boolean l = false;
    private int[] m;
    private int n = 0;
    private int o = 12;
    private AudioRecord p;
    private AcousticEchoCanceler q = null;
    private AutomaticGainControl r = null;
    private NoiseSuppressor s = null;
    private int t = 0;
    private long u = 0;

    public c(int i, int i2, int i3, int i4) {
        this.a = i;
        this.c = i2;
        this.b = i3;
        this.t = i4;
        this.m = new int[10];
    }

    public void a(boolean z) {
        this.h = !this.h;
        this.i = z;
        if (this.h) {
            q.b("fgMedia", "RtpStreamSender.muteAudio() - Audio is muted");
            this.j = System.currentTimeMillis() + 1000;
            return;
        }
        q.b("fgMedia", "RtpStreamSender.muteAudio() - Audio is NOT muted");
    }

    public boolean a() {
        return this.h;
    }

    public void a(int i) {
        if (!this.h) {
            if (this.n < 9) {
                this.m[this.n] = i;
                this.n++;
            }
            if (!this.k) {
                this.k = true;
                this.o = 12;
                q.b("fgMedia", "Initiated sending of the DTMF character: " + this.m[0]);
            }
        }
    }

    public void b() {
        int i = 0;
        if (g) {
            g = false;
            q.b("fgMedia", "Recorder: RtpStreamSender.stopAudioRecorder()");
            q.b("fgMedia", "Recorder: RtpStreamSender not running");
            try {
                if (VERSION.SDK_INT >= 16) {
                    a(this.q);
                    a(this.r);
                    a(this.s);
                }
                this.p.stop();
                q.a("fgMedia", "AudioRecord stoped");
            } catch (Exception e) {
                q.d("fgMedia", "AudioRecord.stop() failed: " + e.getMessage());
                i = 1;
            }
            if (this.p != null) {
                this.p.release();
            }
            q.a("fgMedia", "AudioRecord released");
            SimpleCodecAL.stopRecorderCnf(i);
            return;
        }
        SimpleCodecAL.stopRecorderCnf(0);
    }

    public static void c() {
        g = false;
    }

    public void run() {
        if (g) {
            SimpleCodecAL.startRecorderCnf(0);
            return;
        }
        int i;
        short[] sArr;
        int i2;
        int minBufferSize = AudioRecord.getMinBufferSize(this.c, 16, 2);
        q.b("fgMedia", "AudioRecorder minBufSize = " + minBufferSize);
        Process.setThreadPriority(-19);
        if (VERSION.SDK_INT >= 11) {
            if (d()) {
                q.c("fgMedia", "Using AudioSource.MIC");
            } else {
                i = 7;
                this.p = new AudioRecord(i, this.c, 16, 2, minBufferSize * 20);
                if (FgVoIP.U().av() && this.p.getState() == 0) {
                    FgVoIP.U().a(a.SYSTEM, b.ENABLE_PERMISSION.ordinal(), -1, FgVoIP.U().getString(f.k.exception_permission_message_mic));
                }
                if (VERSION.SDK_INT >= 16) {
                    this.q = e();
                    this.r = f();
                    this.s = g();
                }
                this.p.startRecording();
                g = true;
                SimpleCodecAL.startRecorderCnf(0);
                b(20);
                sArr = new short[this.b];
                if (this.p == null && this.p.getRecordingState() == 3 && this.p.getState() == 1) {
                    i2 = 0;
                    while (i2 >= 0 && i2 < this.b) {
                        q.b("fgMedia", "INIT mRecorder.read before possible stuck");
                        int read = this.p.read(sArr, i2, this.b - i2);
                        q.b("fgMedia", "INIT mRecorder.read after possible stuck tempSamples = " + read);
                        if (read <= 0) {
                            break;
                        }
                        i2 += read;
                    }
                } else {
                    i2 = 0;
                }
                this.d = System.currentTimeMillis();
                this.e = 0;
                q.b("fgMedia", "INIT mRecorder.read = " + i2);
                this.f = i2 + this.f;
                while (g) {
                    a(this.p);
                }
            }
        }
        i = 1;
        try {
            this.p = new AudioRecord(i, this.c, 16, 2, minBufferSize * 20);
        } catch (Exception e) {
            q.d("fgMedia", "run(): AudioRecord init: " + e);
        }
        FgVoIP.U().a(a.SYSTEM, b.ENABLE_PERMISSION.ordinal(), -1, FgVoIP.U().getString(f.k.exception_permission_message_mic));
        if (VERSION.SDK_INT >= 16) {
            this.q = e();
            this.r = f();
            this.s = g();
        }
        try {
            this.p.startRecording();
            g = true;
        } catch (IllegalStateException e2) {
            q.d("fgMedia", "AudioRecord.startRecording() failed: " + e2.getMessage());
            q.d("fgMedia", "Temp: Ignoring!");
            g = false;
        }
        SimpleCodecAL.startRecorderCnf(0);
        b(20);
        sArr = new short[this.b];
        if (this.p == null) {
        }
        i2 = 0;
        this.d = System.currentTimeMillis();
        this.e = 0;
        q.b("fgMedia", "INIT mRecorder.read = " + i2);
        this.f = i2 + this.f;
        while (g) {
            a(this.p);
        }
    }

    private boolean d() {
        for (Object equals : FgVoIP.U().getResources().getStringArray(f.b.audioExceptionModels)) {
            if (Build.MODEL.equals(equals)) {
                q.c("fgMedia", "isAudioExceptionDevice(): model: " + Build.MODEL + " detected");
                return true;
            }
        }
        return false;
    }

    private AcousticEchoCanceler e() {
        if (AcousticEchoCanceler.isAvailable()) {
            AcousticEchoCanceler create;
            try {
                create = AcousticEchoCanceler.create(this.p.getAudioSessionId());
            } catch (Throwable th) {
                create = null;
            }
            if (create != null) {
                int i = 0;
                if (create.getEnabled()) {
                    q.b("fgMedia", "activate_AEC(): AcousticEchoCanceler already ENABLED");
                } else {
                    String str;
                    int enabled = create.setEnabled(true);
                    String str2 = "fgMedia";
                    StringBuilder append = new StringBuilder().append("activate_AEC(): enabling AcousticEchoCanceler");
                    if (enabled == 0) {
                        str = " is SUCCESSFULL";
                    } else {
                        str = " has FAILED - " + (enabled == -5 ? " invalid operation" : " dead object");
                    }
                    q.b(str2, append.append(str).toString());
                    i = enabled;
                }
                if (i == 0) {
                    if (create.hasControl()) {
                        q.b("fgMedia", "activate_AEC(): AcousticEchoCanceler is CONTROLLED!");
                    } else {
                        q.b("fgMedia", "activate_AEC(): AcousticEchoCanceler is NOT CONTROLLED!");
                    }
                    if (create.getEnabled()) {
                        q.b("fgMedia", "activate_AEC(): AcousticEchoCanceler is ENABLED!");
                    } else {
                        q.b("fgMedia", "activate_AEC(): AcousticEchoCanceler is NOT ENABLED!");
                    }
                }
            } else {
                q.b("fgMedia", "activate_AEC():AcousticEchoCanceler is NULL!");
            }
            return create;
        }
        q.b("fgMedia", "activate_AEC(): AcousticEchoCanceler is NOT AVAILABLE!");
        return null;
    }

    private void a(AcousticEchoCanceler acousticEchoCanceler) {
        if (!AcousticEchoCanceler.isAvailable()) {
            q.b("fgMedia", "deactivate_AEC(): AcousticEchoCanceler is NOT AVAILABLE!");
        } else if (acousticEchoCanceler != null) {
            if (acousticEchoCanceler.getEnabled()) {
                String str;
                int enabled = acousticEchoCanceler.setEnabled(false);
                String str2 = "fgMedia";
                StringBuilder append = new StringBuilder().append("deactivate_AEC(): disabling AcousticEchoCanceler");
                if (enabled == 0) {
                    str = " is successful";
                } else {
                    str = " has failed - " + (enabled == -5 ? " invalid operation" : " dead object");
                }
                q.b(str2, append.append(str).toString());
            }
            acousticEchoCanceler.release();
            q.b("fgMedia", "deactivate_AEC(): releasing AcousticEchoCanceler");
        }
    }

    private AutomaticGainControl f() {
        if (AutomaticGainControl.isAvailable()) {
            AutomaticGainControl create;
            try {
                create = AutomaticGainControl.create(this.p.getAudioSessionId());
            } catch (Throwable th) {
                create = null;
            }
            if (create != null) {
                int i = 0;
                if (create.getEnabled()) {
                    q.b("fgMedia", "activate_AGC(): AutomaticGainControl already ENABLED");
                } else {
                    String str;
                    int enabled = create.setEnabled(true);
                    String str2 = "fgMedia";
                    StringBuilder append = new StringBuilder().append("activate_AGC(): enabling AutomaticGainControl");
                    if (enabled == 0) {
                        str = " is SUCCESSFULL";
                    } else {
                        str = " has FAILED - " + (enabled == -5 ? " invalid operation" : " dead object");
                    }
                    q.b(str2, append.append(str).toString());
                    i = enabled;
                }
                if (i == 0) {
                    if (create.hasControl()) {
                        q.b("fgMedia", "activate_AGC(): AutomaticGainControl is CONTROLLED!");
                    } else {
                        q.b("fgMedia", "activate_AGC(): AutomaticGainControl is NOT CONTROLLED!");
                    }
                    if (create.getEnabled()) {
                        q.b("fgMedia", "activate_AGC(): AutomaticGainControl is ENABLED!");
                    } else {
                        q.b("fgMedia", "activate_AGC(): AutomaticGainControl is NOT ENABLED!");
                    }
                }
            } else {
                q.b("fgMedia", "activate_AGC(): AutomaticGainControl is NULL!");
            }
            return create;
        }
        q.b("fgMedia", "activate_AGC(): AutomaticGainControl is NOT AVAILABLE!");
        return null;
    }

    private void a(AutomaticGainControl automaticGainControl) {
        if (!AutomaticGainControl.isAvailable()) {
            q.b("fgMedia", "deactivate_AGC(): AutomaticGainControl is NOT AVAILABLE!");
        } else if (automaticGainControl != null) {
            if (automaticGainControl.getEnabled()) {
                String str;
                int enabled = automaticGainControl.setEnabled(false);
                String str2 = "fgMedia";
                StringBuilder append = new StringBuilder().append("deactivate_AGC(): disabling AutomaticGainControl");
                if (enabled == 0) {
                    str = " is successful";
                } else {
                    str = " has failed - " + (enabled == -5 ? " invalid operation" : " dead object");
                }
                q.b(str2, append.append(str).toString());
            }
            automaticGainControl.release();
            q.b("fgMedia", "deactivate_AGC(): releasing AutomaticGainControl");
        }
    }

    private NoiseSuppressor g() {
        if (NoiseSuppressor.isAvailable()) {
            NoiseSuppressor create;
            try {
                create = NoiseSuppressor.create(this.p.getAudioSessionId());
            } catch (Throwable th) {
                create = null;
            }
            if (create != null) {
                int i = 0;
                if (create.getEnabled()) {
                    q.b("fgMedia", "activate_NS(): NoiseSuppressor already ENABLED");
                } else {
                    String str;
                    int enabled = create.setEnabled(true);
                    String str2 = "fgMedia";
                    StringBuilder append = new StringBuilder().append("activate_NS(): enabling NoiseSuppressor");
                    if (enabled == 0) {
                        str = " is SUCCESSFULL";
                    } else {
                        str = " has FAILED - " + (enabled == -5 ? " invalid operation" : " dead object");
                    }
                    q.b(str2, append.append(str).toString());
                    i = enabled;
                }
                if (i == 0) {
                    if (create.hasControl()) {
                        q.b("fgMedia", "activate_NS(): NoiseSuppressor is CONTROLLED!");
                    } else {
                        q.b("fgMedia", "activate_NS(): NoiseSuppressor is NOT CONTROLLED!");
                    }
                    if (create.getEnabled()) {
                        q.b("fgMedia", "activate_NS(): NoiseSuppressor is ENABLED!");
                    } else {
                        q.b("fgMedia", "activate_NS(): NoiseSuppressor is NOT ENABLED!");
                    }
                }
            } else {
                q.b("fgMedia", "activate_NS(): NoiseSuppressor is NULL!");
            }
            return create;
        }
        q.b("fgMedia", "activate_NS(): NoiseSuppressor is NOT AVAILABLE!");
        return null;
    }

    private void a(NoiseSuppressor noiseSuppressor) {
        if (!NoiseSuppressor.isAvailable()) {
            q.b("fgMedia", "deactivate_NS(): NoiseSuppressor is NOT AVAILABLE!");
        } else if (noiseSuppressor != null) {
            if (noiseSuppressor.getEnabled()) {
                String str;
                int enabled = noiseSuppressor.setEnabled(false);
                String str2 = "fgMedia";
                StringBuilder append = new StringBuilder().append("deactivate_NS(): disabling NoiseSuppressor");
                if (enabled == 0) {
                    str = " is successful";
                } else {
                    str = " has failed - " + (enabled == -5 ? " invalid operation" : " dead object");
                }
                q.b(str2, append.append(str).toString());
            }
            noiseSuppressor.release();
            q.b("fgMedia", "deactivate_NS(): releasing NoiseSuppressor");
        }
    }

    private void b(int i) {
        try {
            sleep((long) i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void a(AudioRecord audioRecord) {
        if (g) {
            int i;
            Object obj = new short[this.b];
            if (audioRecord != null && audioRecord.getRecordingState() == 3 && audioRecord.getState() == 1) {
                i = 0;
                while (i >= 0 && i < this.b && g) {
                    int read = audioRecord.read(obj, i, this.b - i);
                    if (read >= 0) {
                        i += read;
                        this.f += i;
                        if (System.currentTimeMillis() - this.u > 1000) {
                            this.u = System.currentTimeMillis();
                        }
                    } else {
                        return;
                    }
                }
            }
            i = 0;
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - this.d < 19 && this.e < 25) {
                b((int) (19 - (currentTimeMillis - this.d)));
            }
            currentTimeMillis = System.currentTimeMillis();
            if (this.h && this.i) {
                if (currentTimeMillis > this.j) {
                    SimpleCodecAL.sendSilence(50);
                    this.j = 1000 + currentTimeMillis;
                }
            } else if (i > 0) {
                this.e = (int) (currentTimeMillis - this.d);
                this.d = System.currentTimeMillis();
                if (this.h) {
                    for (i = 0; i < obj.length; i++) {
                        obj[i] = null;
                    }
                }
                if (this.k) {
                    if (this.o == 12 || this.o == 11 || this.o == 2 || this.o == 1) {
                        for (i = 0; i < obj.length; i++) {
                            obj[i] = null;
                        }
                    } else {
                        if (this.o == 10) {
                            if (this.a == 102) {
                                a.a((char) this.m[0], 32000, true);
                            } else {
                                a.a((char) this.m[0], 32000, false);
                            }
                        }
                        if (this.a == 102) {
                            a.a(obj, 320);
                        } else {
                            a.a(obj, 160);
                        }
                    }
                    this.o--;
                    if (this.o <= 0) {
                        for (i = 0; i < this.m.length - 1; i++) {
                            this.m[i] = this.m[i + 1];
                        }
                        this.n--;
                        if (this.n < 0) {
                            this.n = 0;
                        }
                        this.k = false;
                        if (this.n > 0) {
                            this.k = true;
                            q.b("fgMedia", "Initiated sending of the next DTMF character: " + this.m[0]);
                        }
                    }
                }
                if (this.a == 18) {
                    Object obj2 = new short[80];
                    System.arraycopy(obj, 0, obj2, 0, obj2.length);
                    SimpleCodecAL.CallbackEncoder2((byte) 18, (short) 0, obj2);
                    System.arraycopy(obj, obj2.length, obj2, 0, obj2.length);
                    SimpleCodecAL.CallbackEncoder2((byte) 18, (short) 0, obj2);
                } else if (this.a == 96 || this.a == 102) {
                    SimpleCodecAL.CallbackEncoder2((byte) this.a, (short) this.t, obj);
                } else {
                    SimpleCodecAL.CallbackEncoder2((byte) this.a, (short) 0, obj);
                }
            } else {
                q.d("fgMedia", "record.read ret=" + i);
            }
        }
    }
}
