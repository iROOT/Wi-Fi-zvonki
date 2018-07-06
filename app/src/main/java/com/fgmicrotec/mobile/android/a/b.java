package com.fgmicrotec.mobile.android.a;

import android.content.Context;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.media.ToneGenerator;
import android.os.Process;
import com.fgmicrotec.mobile.android.fgmag.SimpleCodecAL;
import com.mavenir.android.common.q;
import com.mavenir.android.vtow.activation.ActivationAdapter;
import java.util.HashMap;
import net.hockeyapp.android.k;

public class b extends Thread {
    private static final HashMap<Character, Integer> B = new HashMap();
    static ToneGenerator b = null;
    static int c = -1;
    private static boolean k = false;
    private static Context v = null;
    private long A = 0;
    boolean a = false;
    private long d = 0;
    private int e = 8000;
    private int f = 2;
    private int g = 2;
    private int h = -1;
    private int i;
    private AudioTrack j = null;
    private boolean l = false;
    private int m = k.FEEDBACK_FAILED_TITLE_ID;
    private short[] n = new short[k.FEEDBACK_FAILED_TITLE_ID];
    private int o = 0;
    private int p = 0;
    private int q = 0;
    private boolean r = true;
    private int s = 0;
    private int t = 1;
    private short[] u = new short[k.FEEDBACK_FAILED_TITLE_ID];
    private ToneGenerator w = null;
    private long x = 0;
    private long y = 0;
    private int z = 320;

    static {
        B.put(Character.valueOf('1'), Integer.valueOf(1));
        B.put(Character.valueOf('2'), Integer.valueOf(2));
        B.put(Character.valueOf('3'), Integer.valueOf(3));
        B.put(Character.valueOf('4'), Integer.valueOf(4));
        B.put(Character.valueOf('5'), Integer.valueOf(5));
        B.put(Character.valueOf('6'), Integer.valueOf(6));
        B.put(Character.valueOf('7'), Integer.valueOf(7));
        B.put(Character.valueOf('8'), Integer.valueOf(8));
        B.put(Character.valueOf('9'), Integer.valueOf(9));
        B.put(Character.valueOf('0'), Integer.valueOf(0));
        B.put(Character.valueOf('#'), Integer.valueOf(11));
        B.put(Character.valueOf('*'), Integer.valueOf(10));
    }

    public b(Context context, int i) {
        if (context != null) {
            v = context;
        }
        this.i = i;
        c();
        this.e = 8000;
        this.m = 160;
        this.z = 320;
        if (this.i == 102) {
            this.e = 16000;
            this.m = 320;
            this.z = 320;
        } else if (this.i == 120) {
            this.e = 48000;
            this.m = 960;
            this.z = 960;
        }
    }

    private void c() {
        try {
            this.w = new ToneGenerator(0, 80);
        } catch (Exception e) {
            this.w = null;
        }
    }

    public void run() {
        a(false);
        Process.setThreadPriority(-19);
        this.h = AudioTrack.getMinBufferSize(this.e, this.g, this.f);
        q.b("fgMedia", "AudioTrack minBufSize = " + this.h);
        try {
            this.j = new AudioTrack(0, this.e, this.g, this.f, this.h * 2, 1);
        } catch (IllegalArgumentException e) {
            q.d("fgMedia", "new AudioTrack failed: " + e.getMessage());
        }
        if (this.j == null) {
            SimpleCodecAL.startPlayerCnf(1);
            return;
        }
        int i;
        for (i = 0; i < this.m; i++) {
            this.u[i] = (short) 0;
        }
        System.gc();
        SimpleCodecAL.startPlayerCnf(0);
        byte b = (byte) -1;
        short[] sArr = new short[]{(short) 0};
        try {
            System.currentTimeMillis();
            this.j.play();
            q.b("fgMedia", "AudioTrack INITIAL mAudioTrack.play()");
            System.currentTimeMillis();
        } catch (IllegalStateException e2) {
            q.d("fgMedia", "AudioTrack FIRST mAudioTrack.play() EXCEPTION: " + e2.getLocalizedMessage());
            try {
                sleep(20);
                this.j.play();
            } catch (IllegalStateException e22) {
                q.d("fgMedia", "AudioTrack SECOND mAudioTrack.play() EXCEPTION: " + e22.getLocalizedMessage());
                return;
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        }
        System.currentTimeMillis();
        this.A = System.currentTimeMillis();
        i = 0;
        while (b != (byte) 0) {
            d();
            b = SimpleCodecAL.CallbackDecoder2((byte) this.i, sArr, this.n);
            i++;
        }
        q.b("fgMedia", "AudioTrack FIRST RTP packet received.");
        this.l = true;
        System.currentTimeMillis();
        k = true;
        System.currentTimeMillis();
        if (!(this.j == null || this.j.getState() == 0 || this.j.getPlayState() != 3)) {
            i = this.j.write(this.n, 0, sArr[0]);
            if (i < 0) {
                q.d("fgMedia", "run() - AudioTrack first write returned: " + i);
            }
            this.o = i + this.o;
            this.A = System.currentTimeMillis();
        }
        while (k) {
            if (!this.r) {
                d();
            }
            a(this.j);
        }
    }

    private void d() {
        try {
            sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void a(AudioTrack audioTrack) {
        boolean z = true;
        if (System.currentTimeMillis() - this.y > 1000) {
            this.y = System.currentTimeMillis();
        }
        int i;
        if (k && audioTrack != null && audioTrack.getPlayState() == 3) {
            short[] sArr = new short[]{(short) 0};
            if (audioTrack != null) {
                try {
                    this.p = audioTrack.getPlaybackHeadPosition();
                } catch (Exception e) {
                }
            }
            i = this.o - this.p;
            if (i < 0) {
                if (audioTrack != null) {
                    this.o = audioTrack.getPlaybackHeadPosition();
                }
                i = 0;
            }
            if (SimpleCodecAL.CallbackDecoder2((byte) this.i, sArr, this.n) != (byte) 0) {
                if (i < this.z) {
                    this.s++;
                    if (this.t == 1) {
                        if (!(audioTrack == null || audioTrack.getState() == 0 || audioTrack.getPlayState() != 3)) {
                            i = audioTrack.write(a(this.n, this.m), 0, this.m);
                            if (i < 0) {
                                q.d("fgMedia", "track.write ATTENUATED FRAME, returned negative value (" + i + ", offset: 0, size: " + this.m + "), breaking loop...");
                            }
                            this.A = System.currentTimeMillis();
                        }
                        i = 0;
                    } else {
                        if (this.t == 0 && audioTrack != null && audioTrack.getState() != 0 && audioTrack.getPlayState() == 3) {
                            i = audioTrack.write(this.u, 0, this.m);
                            if (i < 0) {
                                q.d("fgMedia", "track.write ZERO FRAME returned negative value (" + i + ", offset: 0, size: " + this.m + "), breaking loop...");
                            }
                            this.A = System.currentTimeMillis();
                        }
                        i = 0;
                    }
                    this.q++;
                    sArr[0] = (short) 0;
                } else {
                    this.q = 0;
                    i = 0;
                }
                this.r = false;
            } else if (sArr[0] == (short) 0) {
                this.q = 0;
                q.b("fgMedia", "AudioTrack LOOP  onPeriodicNotification() ERROR FRAME");
                i = 0;
            } else {
                this.q = 0;
                boolean z2 = false;
                boolean z3 = false;
                int i2 = 0;
                while (i2 < sArr[0] && k && !z2) {
                    if (!(audioTrack == null || audioTrack.getState() == 0 || audioTrack.getPlayState() != 3)) {
                        int write = audioTrack.write(this.n, i2, sArr[0] - i2);
                        if (write <= 0) {
                            q.d("fgMedia", "track.write returned negative value (" + write + ", offset: " + i2 + ", size: " + (sArr[0] - i2) + "), breaking loop...");
                            break;
                        } else {
                            i2 += write;
                            this.A = System.currentTimeMillis();
                        }
                    }
                    if (z3 == i2 && i2 < sArr[0]) {
                        q.b("fgMedia", "AudioTrack LOOP escaping possibly stuck on track.write(), numWritten = " + i2);
                        z2 = true;
                    }
                    z3 = i2;
                }
                if (this.r) {
                    z = false;
                }
                this.r = z;
                i = i2;
            }
            if (i >= 0) {
                this.o = i + this.o;
            } else {
                q.b("fgMedia", "AudioTrack LOOP  onPeriodicNotification() ERROR WRITING numWritten = " + i);
            }
        } else if (System.currentTimeMillis() - this.x > 200) {
            if (!k) {
                q.d("fgMedia", "AudioTrack LOOP ERROR mPlayerIsRunning = false !!!");
            }
            if (audioTrack == null) {
                q.d("fgMedia", "AudioTrack LOOP ERROR track is null !!!");
            } else if (audioTrack.getPlayState() != 3) {
                String str;
                i = audioTrack.getPlayState();
                if (i == 2) {
                    str = "AudioTrack.PLAYSTATE_PAUSED";
                } else if (i == 3) {
                    str = "AudioTrack.PLAYSTATE_PLAYING";
                } else {
                    str = "AudioTrack.PLAYSTATE_STOPPED";
                }
                q.d("fgMedia", "AudioTrack LOOP ERROR (track.getPlayState() != AudioTrack.PLAYSTATE_PLAYING !!! track.getPlayState() = " + str);
            }
            this.x = System.currentTimeMillis();
        }
    }

    private short[] a(short[] sArr, int i) {
        short[] sArr2 = new short[k.FEEDBACK_FAILED_TITLE_ID];
        for (int i2 = 0; i2 < i; i2++) {
            sArr2[i2] = (short) (sArr[i2] >> 1);
            sArr[i2] = sArr2[i2];
        }
        return sArr2;
    }

    public void a() {
        int i;
        q.b("fgMedia", "AudioTrack STOPPED  mCallStatisticsDelayFramesInserted = " + this.s);
        try {
            if (this.j != null) {
                this.j.pause();
                this.j.flush();
                this.j.stop();
            }
            k = false;
            i = 0;
        } catch (IllegalStateException e) {
            q.d("fgMedia", "AudioTrack.stop() failed: " + e.getMessage());
            i = 1;
        }
        if (this.j != null) {
            this.j.release();
            q.b("fgMedia", "AudioTrack RELEASED");
        }
        SimpleCodecAL.stopPlayerCnf(i);
        this.l = false;
    }

    public static void b() {
        k = false;
    }

    public static synchronized void a(Context context, boolean z) {
        synchronized (b.class) {
            v = context;
            a(z);
        }
    }

    public static synchronized void a(boolean z) {
        synchronized (b.class) {
            if (v == null) {
                q.d("fgMedia", "Ringback context is null");
            } else {
                if (z) {
                    if (b == null) {
                        AudioManager audioManager = (AudioManager) v.getSystemService("audio");
                        c = audioManager.getStreamVolume(0);
                        audioManager.setStreamVolume(0, audioManager.getStreamMaxVolume(0), 0);
                        b = new ToneGenerator(0, 80);
                        b.startTone(23);
                    }
                }
                if (!z) {
                    if (b != null) {
                        b.stopTone();
                        b.release();
                        b = null;
                        ((AudioManager) v.getSystemService("audio")).setStreamVolume(0, c, 0);
                        c = -1;
                    }
                }
            }
        }
    }

    public synchronized void a(int i) {
        q.b("fgMedia", "Playing DTMF tone: " + i);
        char c = (char) i;
        if (this.w == null) {
            c();
        }
        if (this.w != null) {
            this.w.startTone(((Integer) B.get(Character.valueOf(c))).intValue(), ActivationAdapter.OP_CONFIGURATION_INITIAL);
        }
    }
}
