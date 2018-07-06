package com.spiritdsp.tsm;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.database.ContentObserver;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.media.audiofx.AcousticEchoCanceler;
import android.media.audiofx.AutomaticGainControl;
import android.media.audiofx.NoiseSuppressor;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import android.provider.Settings.System;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import com.mavenir.android.vtow.activation.ActivationAdapter;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class Audio {
    private static final int AUDIO_PATH_BT_HANDSET = 5;
    private static final int AUDIO_PATH_DISABLED = 0;
    private static final int AUDIO_PATH_HANDSET = 2;
    private static final int AUDIO_PATH_HANDSFREE = 1;
    private static final int BT_HS_AUDIO_CAP_RATE = 8000;
    private static final int BT_HS_AUDIO_PB_RATE = 16000;
    private static boolean bEnableDebugPrint = true;
    private static Context mContext;
    private static boolean mEnableAudioVolumeObservation = false;
    private static boolean mUpdateContextAM = true;
    private static boolean mUpdateContextBT = true;
    private static boolean mUpdateContextObs = true;
    private final int BUFFER_SIZE = 960;
    private int BufferedPlaybackSamples = 0;
    private boolean CaptureActive = false;
    private ByteBuffer CaptureBuffer = ByteBuffer.allocateDirect(960);
    private int CaptureSamplingRate = 0;
    private boolean ENABLE_SR_SELECTION_WITH_MIN_BUFSIZE = true;
    private boolean PlaybackActive = false;
    private ByteBuffer PlaybackBuffer = ByteBuffer.allocateDirect(960);
    private int PlaybackBufferLen = 0;
    private int PlaybackPos = 0;
    private int PlaybackSamplingRate = 0;
    private int SEND_TEST_SIGNAL = 0;
    private AudioManager audioManager = null;
    private AudioRecord audioRecord = null;
    private AudioTrack audioTrack;
    private a btAudioDevice = null;
    private a captureThread;
    private int contextPtr;
    private AcousticEchoCanceler mAec = null;
    private AutomaticGainControl mAgc = null;
    private int mAudioPath = 2;
    private int mAudioSource = -1;
    private boolean mHardwareAecEnable = true;
    private boolean mHardwareAecPresent = false;
    private boolean mIsSpeakerphoneOn = false;
    private NoiseSuppressor mNs = null;
    private int mOutputVolume = -1;
    private int mPlaybackSource;
    private boolean mStateOfFLAG_KEEP_SCREEN_ON = false;
    private boolean mUseBtAudioDevice = false;
    private boolean mWakeLocked = false;
    private c minBufferSizeCache = new c(this);
    private d playbackThread;
    private long test_sign_abs_ind = 0;
    private int[] test_signal = new int[]{0, 10126, 19261, 26510, 31164, 32768, 31164, 26510, 19261, 10126, 0, -10126, -19261, -26510, -31164, -32768, -31164, -26510, -19261, -10126};
    private long test_signal_delay = 220500;
    private int test_signal_ind = 0;
    private int test_signal_period = 2;
    private byte[] tmp_buffer = new byte[40000];

    abstract class b implements Runnable {
        private volatile boolean a = false;
        public Thread d;
        volatile boolean e = false;
        volatile boolean f = false;
        volatile double g = 0.0d;
        volatile int h = 0;
        volatile int i = 0;
        int j = 0;
        protected byte[] k;
        protected String l;
        final /* synthetic */ Audio m;

        protected abstract void a();

        protected abstract void b();

        b(Audio audio) {
            this.m = audio;
        }

        public void c() {
            if (this.d == null) {
                this.d = new Thread(this, this.l);
                this.d.setPriority(8);
                this.d.start();
            }
        }

        public void d() {
            if (this.d != null) {
                this.f = true;
                try {
                    this.d.join(1000);
                } catch (InterruptedException e) {
                }
                this.d = null;
            }
        }

        public void e() {
            if (!this.e) {
                this.e = true;
                try {
                    synchronized (this) {
                        while (!this.a) {
                            wait(500);
                        }
                    }
                } catch (InterruptedException e) {
                }
            }
        }

        public void f() {
            if (this.e) {
                a();
                this.e = false;
                this.a = false;
            }
        }

        public void run() {
            Logging.LogDebugPrint(Audio.bEnableDebugPrint, this.l + " started", new Object[0]);
            try {
                Process.setThreadPriority(-19);
            } catch (Exception e) {
                Logging.LogNativePrintErr("setThreadPriority() failed: " + e.toString(), new Object[0]);
            }
            long nanoTime = System.nanoTime();
            a();
            if (((long) (((double) (System.nanoTime() - nanoTime)) / 1000000.0d)) > 10) {
                Logging.LogDebugPrint(Audio.bEnableDebugPrint, String.format("%s:setup:WARN: took too long (%d ms)", new Object[]{this.l, Long.valueOf(nanoTime)}), new Object[0]);
            }
            while (!this.f) {
                try {
                    if (this.e) {
                        if (!this.a) {
                            this.a = true;
                            synchronized (this) {
                                notifyAll();
                            }
                        }
                        Thread.sleep(15);
                    } else {
                        nanoTime = System.nanoTime();
                        b();
                        if (((long) (((double) (System.nanoTime() - nanoTime)) / 1000000.0d)) > 20) {
                            Logging.LogDebugPrint(Audio.bEnableDebugPrint, String.format("%s:iteration:WARN: took too long (%d ms)", new Object[]{this.l, Long.valueOf(nanoTime)}), new Object[0]);
                        }
                    }
                } catch (Throwable th) {
                    Logging.LogNativePrintErr(this.l + ": exception: " + th.toString(), new Object[0]);
                }
            }
            Logging.LogDebugPrint(Audio.bEnableDebugPrint, this.l + " exit", new Object[0]);
        }

        void a(int i) {
            long nanoTime = System.nanoTime();
            for (int i2 = 0; i2 < i; i2 += 2) {
                short s = (short) ((this.k[i2] & 255) | (this.k[i2 + 1] << 8));
                if (s < (short) 0) {
                    this.g -= (double) s;
                } else {
                    this.g = ((double) s) + this.g;
                }
            }
            this.h += i / 2;
            this.i++;
            if (this.h >= this.j) {
                Logging.LogDebugPrint(Audio.bEnableDebugPrint, this.l + " audio level avg=%d samples=%d calls=%d", Integer.valueOf((int) (this.g / ((double) this.h))), Integer.valueOf(this.h), Integer.valueOf(this.i));
                this.g = 0.0d;
                this.h = 0;
                this.i = 0;
            }
            if (((long) (((double) (System.nanoTime() - nanoTime)) / 1000000.0d)) > 10) {
                Logging.LogDebugPrint(Audio.bEnableDebugPrint, String.format("%s:LogAudioLevel:WARN: too too long (%d ms)", new Object[]{this.l, Long.valueOf(nanoTime)}), new Object[0]);
            }
        }
    }

    class a extends b {
        int a;
        final int b;
        final /* synthetic */ Audio c;

        a(Audio audio) {
            this.c = audio;
            super(audio);
            this.b = 25;
            this.l = "CaptureThread";
        }

        protected void a() {
            if (this.k == null) {
                this.k = new byte[960];
            } else {
                Arrays.fill(this.k, (byte) 0);
            }
            this.a = (this.c.CaptureSamplingRate / 100) * 2;
            this.j = this.c.CaptureSamplingRate;
        }

        protected void b() {
            int i = 0;
            int read = this.c.audioRecord.read(this.k, 0, this.a);
            if (read != this.a) {
                Logging.LogNativePrint("Warning! CaptureThread: audioRecord.read() returned " + read + " instead of " + this.a, new Object[0]);
            }
            if (read <= 0) {
                Thread.sleep(5);
                return;
            }
            int i2;
            if (TSM_impl.mIsMI_4) {
                for (i2 = 0; i2 < read; i2 += 2) {
                    short s = (short) ((((short) ((this.k[i2] & 255) | (this.k[i2 + 1] << 8))) * 28500) / 32768);
                    this.k[i2] = (byte) (s & 255);
                    this.k[i2 + 1] = (byte) (s >> 8);
                }
            }
            if (TSM_impl.mIsMI_3W) {
                for (i2 = 0; i2 < read; i2 += 2) {
                    short s2 = (short) ((((short) ((this.k[i2] & 255) | (this.k[i2 + 1] << 8))) * 12000) / 32768);
                    this.k[i2] = (byte) (s2 & 255);
                    this.k[i2 + 1] = (byte) (s2 >> 8);
                }
            }
            if (Audio.bEnableDebugPrint) {
                a(read);
            }
            this.c.CaptureBuffer.rewind();
            this.c.CaptureBuffer.put(this.k, 0, read);
            boolean access$1700 = this.c.PlaybackActive;
            int access$400 = this.c.PlaybackSamplingRate;
            int access$500 = this.c.BufferedPlaybackSamples;
            if (access$1700 && access$400 != 0) {
                i = ((access$500 * 1000) / access$400) + 25;
            }
            this.c.DeliverCaptureData(this.c.contextPtr, read >> 1, i);
        }
    }

    class c {
        static final /* synthetic */ boolean a = (!Audio.class.desiredAssertionStatus());
        final /* synthetic */ Audio b;
        private final int[] c = new int[]{44100, Audio.BT_HS_AUDIO_PB_RATE, 48000, Audio.BT_HS_AUDIO_CAP_RATE};
        private final int d = this.c.length;
        private final int[] e = new int[this.d];
        private int f = 0;
        private int g = 0;

        @SuppressLint({"InlinedApi"})
        c(Audio audio) {
            int i = 0;
            this.b = audio;
            while (i < this.d) {
                this.e[i] = AudioTrack.getMinBufferSize(this.c[i], 4, 2);
                i++;
            }
        }

        public int a(int i) {
            for (int i2 = 0; i2 < this.d; i2++) {
                if (i == this.c[i2]) {
                    return this.e[i2];
                }
            }
            if (a) {
                return -1;
            }
            throw new AssertionError();
        }

        public int b(int i) {
            return this.c[i];
        }

        public int a() {
            return this.d;
        }

        public boolean a(int i, int i2) {
            if (i == this.f && i2 == this.g) {
                return true;
            }
            return false;
        }

        public void b(int i, int i2) {
            this.f = i;
            this.g = i2;
            if (!a && !a(i, i2)) {
                throw new AssertionError();
            }
        }
    }

    class d extends b {
        volatile int a;
        volatile int b;
        volatile int c;
        volatile boolean n;
        volatile int o;
        final /* synthetic */ Audio p;

        d(Audio audio) {
            this.p = audio;
            super(audio);
            this.c = 0;
            this.n = false;
            this.l = "PlaybackThread";
        }

        protected void a() {
            if (this.k == null) {
                this.k = new byte[960];
            } else {
                Arrays.fill(this.k, (byte) 0);
            }
            this.a = this.p.PlaybackSamplingRate / 100;
            this.j = this.p.PlaybackSamplingRate;
            this.b = this.a * 2;
            this.p.BufferedPlaybackSamples = 0;
            this.p.PlaybackPos = 0;
            this.c = 0;
            this.n = false;
            this.o = 0;
            int i = 0;
            while (i < this.p.PlaybackBufferLen * 100 && !this.f) {
                int write = this.p.audioTrack.write(this.k, 0, 100);
                this.p.BufferedPlaybackSamples = this.p.BufferedPlaybackSamples + (write >> 1);
                i += write >> 1;
                this.o = this.p.audioTrack.getPlaybackHeadPosition();
                if (this.o < this.p.PlaybackPos) {
                    this.p.PlaybackPos = 0;
                }
                this.p.BufferedPlaybackSamples = this.p.BufferedPlaybackSamples - (this.o - this.p.PlaybackPos);
                this.p.PlaybackPos = this.o;
                if (this.o > 0) {
                    break;
                } else if (i >= this.p.PlaybackBufferLen) {
                    try {
                        Thread.sleep(3);
                    } catch (InterruptedException e) {
                    }
                }
            }
            if (this.o == 0) {
                Logging.LogDebugPrint(Audio.bEnableDebugPrint, "PlaybackThread: AudioTrack doesn't run!", new Object[0]);
            }
        }

        protected void b() {
            this.o = this.p.audioTrack.getPlaybackHeadPosition();
            if (this.o < this.p.PlaybackPos) {
                this.p.PlaybackPos = 0;
            }
            this.p.BufferedPlaybackSamples = this.p.BufferedPlaybackSamples - (this.o - this.p.PlaybackPos);
            this.p.PlaybackPos = this.o;
            if (this.p.BufferedPlaybackSamples < this.p.PlaybackBufferLen - this.a || this.n) {
                int i;
                this.p.GetPlaybackData(this.p.contextPtr, this.a);
                this.p.PlaybackBuffer.rewind();
                this.p.PlaybackBuffer.get(this.k, 0, this.b);
                if (TSM_impl.mIsMI_3W || TSM_impl.mIsMI_4) {
                    i = 10000;
                    if (TSM_impl.mIsMI_4 && this.p.getAudioManager() != null) {
                        i = this.p.getAudioManager().isWiredHeadsetOn() ? 5850 : this.p.GetSpeakerphone() ? 10922 : 9925;
                    }
                    for (int i2 = 0; i2 < this.b; i2 += 2) {
                        short s = (short) ((((short) ((this.k[i2] & 255) | (this.k[i2 + 1] << 8))) * i) / 32768);
                        this.k[i2] = (byte) (s & 255);
                        this.k[i2 + 1] = (byte) (s >> 8);
                    }
                }
                if (Audio.bEnableDebugPrint) {
                    a(this.b);
                }
                i = this.p.audioTrack.write(this.k, 0, this.b);
                if (i != this.b) {
                    Logging.LogNativePrint("SIO: Warning! PlaybackThread: audioTrack.write() returned " + i + " instead of " + this.b, new Object[0]);
                }
                if (i > 0) {
                    this.p.BufferedPlaybackSamples = (i >> 1) + this.p.BufferedPlaybackSamples;
                }
                this.o = this.p.audioTrack.getPlaybackHeadPosition();
                if (this.o < this.p.PlaybackPos) {
                    this.p.PlaybackPos = 0;
                }
                if (!this.n) {
                    this.p.BufferedPlaybackSamples = this.p.BufferedPlaybackSamples - (this.o - this.p.PlaybackPos);
                }
                this.p.PlaybackPos = this.o;
                this.c = 0;
                this.n = false;
                return;
            }
            Thread.sleep(3);
            this.c++;
            if (this.c > ActivationAdapter.REASON_NEW_CONFIG_DATA) {
                this.n = true;
            }
        }
    }

    private class e extends ContentObserver {
        final /* synthetic */ Audio a;

        e(Audio audio) {
            this.a = audio;
            super(new Handler(Looper.getMainLooper()));
        }

        public boolean deliverSelfNotifications() {
            return false;
        }

        public void onChange(boolean z) {
            this.a.OnVolumeChangedExternally();
        }
    }

    private native void DeliverCaptureData(int i, int i2, int i3);

    private native void GetPlaybackData(int i, int i2);

    private native void HandleExternallyVolumeChanging(int i, int i2);

    private native void LogPrint(int i, String str);

    private native void LogPrintErr(int i, String str);

    private native boolean SetCaptureSamplingRate(int i, int i2, int i3);

    private native void SetHardwareAecPresentStatus(int i, int i2);

    private native boolean SetPlaybackSamplingRate(int i, int i2, int i3);

    static void setContext(Activity activity) {
        Logging.LogDebugPrint(bEnableDebugPrint, "A: setContext: %h", activity);
        mContext = activity;
        mUpdateContextAM = true;
        mUpdateContextBT = true;
        mUpdateContextObs = true;
    }

    static void enableAudioVolumeObservation(boolean z) {
        mEnableAudioVolumeObservation = z;
    }

    private AudioManager getAudioManager() {
        if ((mUpdateContextAM || this.audioManager == null) && mContext != null) {
            synchronized (this) {
                this.audioManager = (AudioManager) mContext.getSystemService("audio");
                mUpdateContextAM = false;
            }
        }
        if (this.audioManager == null) {
            Logging.LogDebugPrint(bEnableDebugPrint, "getAudioManager: NULL!", new Object[0]);
        }
        return this.audioManager;
    }

    private a getBtAudioDevice() {
        if ((mUpdateContextBT || this.btAudioDevice == null) && mContext != null) {
            synchronized (this) {
                this.btAudioDevice = new a(mContext, getAudioManager(), new com.spiritdsp.tsm.a.a(this) {
                    final /* synthetic */ Audio a;

                    {
                        this.a = r1;
                    }

                    public void a(com.spiritdsp.tsm.a.c cVar) {
                        Logging.LogDebugPrint(Audio.bEnableDebugPrint, "BtAudioDevice: SCO state %s", cVar.name());
                        if (cVar != com.spiritdsp.tsm.a.c.STARTED) {
                            this.a.mUseBtAudioDevice = false;
                            Logging.LogDebugPrint(Audio.bEnableDebugPrint, "mUseBtAudioDevice = %s", String.valueOf(this.a.mUseBtAudioDevice));
                        }
                    }
                });
                mUpdateContextBT = false;
            }
        }
        Logging.LogDebugPrint(bEnableDebugPrint, "BtAudioDevice: %s", String.valueOf(this.btAudioDevice));
        return this.btAudioDevice;
    }

    public Audio(int i) {
        this.contextPtr = i;
        setDefaultPlaybackSource();
    }

    private synchronized boolean InitPlayback() {
        boolean z = false;
        synchronized (this) {
            long nanoTime = System.nanoTime();
            Logging.LogDebugPrint(bEnableDebugPrint, "InitPlayback", new Object[0]);
            setDefaultPlaybackSource();
            this.PlaybackSamplingRate = SelectSamplingRate(true);
            if (this.PlaybackSamplingRate == 0) {
                Logging.LogDebugPrint(bEnableDebugPrint, "SelectSamplingRate for playback failed", new Object[0]);
                Logging.LogNativePrintErr("SelectSamplingRate for playback failed", new Object[0]);
            } else {
                int[] iArr = new int[2];
                if (InitAudioTrack(this.PlaybackSamplingRate, iArr, false)) {
                    Logging.LogNativePrint("AudioTrack initialized for sr=" + this.PlaybackSamplingRate, new Object[0]);
                    Logging.LogNativePrint("AudioTrack bufSize=" + iArr[0] + "bytes (" + (((iArr[0] / 2) * 1000) / this.PlaybackSamplingRate) + "ms)", new Object[0]);
                    Logging.LogNativePrint("AudioTrack minBufSize=" + iArr[1] + "bytes (" + (((iArr[1] / 2) * 1000) / this.PlaybackSamplingRate) + "ms)", new Object[0]);
                    this.PlaybackBufferLen = iArr[0] >> 1;
                    if (((long) (((double) (System.nanoTime() - nanoTime)) / 1000000.0d)) > 100) {
                        Logging.LogDebugPrint(bEnableDebugPrint, "InitPlayback: WARN: took too long (%d ms)", Long.valueOf((long) (((double) (System.nanoTime() - nanoTime)) / 1000000.0d)));
                    }
                    z = true;
                } else {
                    Logging.LogDebugPrint(bEnableDebugPrint, "InitAudioTrack(" + this.PlaybackSamplingRate + ") failed", new Object[0]);
                    Logging.LogNativePrintErr("InitAudioTrack(" + this.PlaybackSamplingRate + ") failed", new Object[0]);
                }
            }
        }
        return z;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized boolean StartPlayback_impl(boolean r10, boolean r11) {
        /*
        r9 = this;
        r0 = 1;
        r1 = 0;
        monitor-enter(r9);
        r4 = java.lang.System.nanoTime();	 Catch:{ all -> 0x0097 }
        r3 = bEnableDebugPrint;	 Catch:{ all -> 0x0097 }
        r6 = "StartPlayback %s";
        r2 = 1;
        r7 = new java.lang.Object[r2];	 Catch:{ all -> 0x0097 }
        r8 = 0;
        if (r11 == 0) goto L_0x001e;
    L_0x0011:
        r2 = "reset";
    L_0x0013:
        r7[r8] = r2;	 Catch:{ all -> 0x0097 }
        com.spiritdsp.tsm.Logging.LogDebugPrint(r3, r6, r7);	 Catch:{ all -> 0x0097 }
        r2 = r9.PlaybackActive;	 Catch:{ all -> 0x0097 }
        if (r2 == 0) goto L_0x0021;
    L_0x001c:
        monitor-exit(r9);
        return r0;
    L_0x001e:
        r2 = "resume";
        goto L_0x0013;
    L_0x0021:
        r2 = r9.audioTrack;	 Catch:{ all -> 0x0097 }
        if (r2 != 0) goto L_0x0027;
    L_0x0025:
        r0 = r1;
        goto L_0x001c;
    L_0x0027:
        r2 = r9.audioTrack;	 Catch:{ all -> 0x0097 }
        r2 = r2.getState();	 Catch:{ all -> 0x0097 }
        if (r2 != 0) goto L_0x0031;
    L_0x002f:
        r0 = r1;
        goto L_0x001c;
    L_0x0031:
        r2 = 0;
        r9.BufferedPlaybackSamples = r2;	 Catch:{ all -> 0x0097 }
        r2 = 0;
        r9.PlaybackPos = r2;	 Catch:{ all -> 0x0097 }
        r2 = r9.audioTrack;	 Catch:{ IllegalStateException -> 0x009a }
        r2.flush();	 Catch:{ IllegalStateException -> 0x009a }
        r2 = r9.audioTrack;	 Catch:{ IllegalStateException -> 0x009a }
        r2.play();	 Catch:{ IllegalStateException -> 0x009a }
        if (r10 != 0) goto L_0x0047;
    L_0x0043:
        r1 = r9.playbackThread;	 Catch:{ all -> 0x0097 }
        if (r1 != 0) goto L_0x0053;
    L_0x0047:
        r1 = new com.spiritdsp.tsm.Audio$d;	 Catch:{ all -> 0x0097 }
        r1.<init>(r9);	 Catch:{ all -> 0x0097 }
        r9.playbackThread = r1;	 Catch:{ all -> 0x0097 }
        r1 = r9.playbackThread;	 Catch:{ all -> 0x0097 }
        r1.c();	 Catch:{ all -> 0x0097 }
    L_0x0053:
        r1 = r9.playbackThread;	 Catch:{ all -> 0x0097 }
        r1.f();	 Catch:{ all -> 0x0097 }
        r1 = 1;
        r9.PlaybackActive = r1;	 Catch:{ all -> 0x0097 }
        r1 = com.spiritdsp.tsm.TSM_impl.mIsHTCdesireSV;	 Catch:{ all -> 0x0097 }
        if (r1 != 0) goto L_0x006f;
    L_0x005f:
        r1 = com.spiritdsp.tsm.TSM_impl.mIsHTCOneA9;	 Catch:{ all -> 0x0097 }
        if (r1 != 0) goto L_0x006f;
    L_0x0063:
        r1 = com.spiritdsp.tsm.TSM_impl.mIsHTCOneM9;	 Catch:{ all -> 0x0097 }
        if (r1 != 0) goto L_0x006f;
    L_0x0067:
        r1 = com.spiritdsp.tsm.TSM_impl.mIsHTCD628;	 Catch:{ all -> 0x0097 }
        if (r1 != 0) goto L_0x006f;
    L_0x006b:
        r1 = com.spiritdsp.tsm.TSM_impl.mIsHTCD626;	 Catch:{ all -> 0x0097 }
        if (r1 == 0) goto L_0x0072;
    L_0x006f:
        r9.wake_lock();	 Catch:{ all -> 0x0097 }
    L_0x0072:
        r2 = java.lang.System.nanoTime();	 Catch:{ all -> 0x0097 }
        r2 = r2 - r4;
        r2 = (double) r2;	 Catch:{ all -> 0x0097 }
        r4 = 4696837146684686336; // 0x412e848000000000 float:0.0 double:1000000.0;
        r2 = r2 / r4;
        r2 = (long) r2;	 Catch:{ all -> 0x0097 }
        r4 = 100;
        r1 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r1 <= 0) goto L_0x001c;
    L_0x0085:
        r1 = bEnableDebugPrint;	 Catch:{ all -> 0x0097 }
        r4 = "StartPlayback: WARN: took too long (%d ms)";
        r5 = 1;
        r5 = new java.lang.Object[r5];	 Catch:{ all -> 0x0097 }
        r6 = 0;
        r2 = java.lang.Long.valueOf(r2);	 Catch:{ all -> 0x0097 }
        r5[r6] = r2;	 Catch:{ all -> 0x0097 }
        com.spiritdsp.tsm.Logging.LogDebugPrint(r1, r4, r5);	 Catch:{ all -> 0x0097 }
        goto L_0x001c;
    L_0x0097:
        r0 = move-exception;
        monitor-exit(r9);
        throw r0;
    L_0x009a:
        r0 = move-exception;
        r0 = "audioTrack.play() failed";
        r2 = 0;
        r2 = new java.lang.Object[r2];	 Catch:{ all -> 0x0097 }
        com.spiritdsp.tsm.Logging.LogNativePrintErr(r0, r2);	 Catch:{ all -> 0x0097 }
        r0 = r1;
        goto L_0x001c;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.spiritdsp.tsm.Audio.StartPlayback_impl(boolean, boolean):boolean");
    }

    private boolean StartPlaybackSLES() {
        Logging.LogDebugPrint(bEnableDebugPrint, "StartPlaybackSLES", new Object[0]);
        return true;
    }

    private boolean StartPlayback() {
        return StartPlayback_impl(true, true);
    }

    private synchronized boolean StopPlayback_impl(boolean z, boolean z2) {
        String str;
        long nanoTime = System.nanoTime();
        boolean z3 = bEnableDebugPrint;
        String str2 = "StopPlayback %s:%s";
        Object[] objArr = new Object[2];
        objArr[0] = z ? "restart" : "keep";
        if (z2) {
            str = "reset";
        } else {
            str = "resume";
        }
        objArr[1] = str;
        Logging.LogDebugPrint(z3, str2, objArr);
        wake_unlock();
        if (this.playbackThread != null) {
            this.playbackThread.e();
            if (z) {
                this.playbackThread.d();
                this.playbackThread = null;
            }
        }
        if (this.audioTrack != null) {
            try {
                if (this.PlaybackActive && this.audioTrack.getState() != 0) {
                    this.audioTrack.stop();
                    this.audioTrack.flush();
                }
                if (z2) {
                    this.audioTrack.release();
                    this.audioTrack = null;
                    System.gc();
                }
            } catch (IllegalStateException e) {
                Logging.LogNativePrintErr("audioTrack.stop: exception " + e.toString(), new Object[0]);
            }
        }
        if (mContext != null && (mContext instanceof Activity)) {
            ((Activity) mContext).setVolumeControlStream(Integer.MIN_VALUE);
        }
        this.PlaybackActive = false;
        if (((long) (((double) (System.nanoTime() - nanoTime)) / 1000000.0d)) > 100) {
            Logging.LogDebugPrint(bEnableDebugPrint, "StopPlayback: WARN: took too long (%d ms)", Long.valueOf((long) (((double) (System.nanoTime() - nanoTime)) / 1000000.0d)));
        }
        return true;
    }

    private boolean StopPlaybackSLES() {
        Logging.LogDebugPrint(bEnableDebugPrint, "StopPlaybackSLES", new Object[0]);
        return true;
    }

    private boolean StopPlayback() {
        return StopPlayback_impl(true, true);
    }

    @SuppressLint({"InlinedApi"})
    private void wake_lock() {
        if (!this.mWakeLocked) {
            if (mContext == null) {
                Logging.LogNativePrintErr("audioTrack.wake_lock without context", new Object[0]);
                return;
            }
            Logging.LogNativePrint("audioTrack.wake_lock", new Object[0]);
            if (mContext instanceof Activity) {
                final Activity activity = (Activity) mContext;
                activity.runOnUiThread(new Runnable(this) {
                    final /* synthetic */ Audio b;

                    public void run() {
                        this.b.mStateOfFLAG_KEEP_SCREEN_ON = (activity.getWindow().getAttributes().flags & NotificationCompat.FLAG_HIGH_PRIORITY) != 0;
                        if (VERSION.SDK_INT >= 5) {
                            activity.getWindow().addFlags(4194304);
                            activity.getWindow().addFlags(2097152);
                        }
                        activity.getWindow().addFlags(NotificationCompat.FLAG_HIGH_PRIORITY);
                    }
                });
                this.mWakeLocked = true;
            }
        }
    }

    @SuppressLint({"InlinedApi"})
    private void wake_unlock() {
        if (this.mWakeLocked) {
            this.mWakeLocked = false;
            if (mContext == null) {
                Logging.LogNativePrintErr("audioTrack.wake_unlock without context", new Object[0]);
                return;
            }
            Logging.LogNativePrint("audioTrack.wake_unlock", new Object[0]);
            if (mContext instanceof Activity) {
                final Activity activity = (Activity) mContext;
                activity.runOnUiThread(new Runnable(this) {
                    final /* synthetic */ Audio b;

                    public void run() {
                        if (!this.b.mStateOfFLAG_KEEP_SCREEN_ON) {
                            activity.getWindow().clearFlags(NotificationCompat.FLAG_HIGH_PRIORITY);
                        }
                        if (VERSION.SDK_INT >= 5) {
                            activity.getWindow().clearFlags(4194304);
                            activity.getWindow().clearFlags(2097152);
                        }
                    }
                });
            }
            this.mStateOfFLAG_KEEP_SCREEN_ON = false;
        }
    }

    private void InitAudioLevelObserver() {
        if (mUpdateContextObs && mEnableAudioVolumeObservation && mContext != null) {
            mUpdateContextObs = false;
            mContext.getContentResolver().registerContentObserver(System.CONTENT_URI, true, new e(this));
        }
    }

    @SuppressLint({"InlinedApi"})
    private boolean InitAudioTrack(int i, int[] iArr, boolean z) {
        long nanoTime = System.nanoTime();
        boolean z2 = bEnableDebugPrint;
        String str = "InitAudioTrack: %s";
        Object[] objArr = new Object[1];
        objArr[0] = z ? "bufsize" : "init";
        Logging.LogDebugPrint(z2, str, objArr);
        InitAudioLevelObserver();
        int a = this.minBufferSizeCache.a(i);
        if (a <= 0) {
            return false;
        }
        int i2;
        int i3;
        if (iArr != null) {
            iArr[1] = a;
        }
        if (((a / 2) * 1000) / i < 100) {
            i2 = (i * ActivationAdapter.OP_CONFIGURATION_INITIAL) / 1000;
        } else {
            i2 = a;
        }
        if (TSM_impl.mIsKarbonnTAFone || (this.mUseBtAudioDevice && TSM_impl.mIsNexus7)) {
            i3 = a;
        } else {
            i3 = i2;
        }
        if (iArr != null) {
            iArr[0] = i3;
        }
        if (z) {
            return true;
        }
        if (this.audioTrack == null || !this.minBufferSizeCache.a(i, this.mPlaybackSource)) {
            if (this.audioTrack != null) {
                this.audioTrack.release();
                this.audioTrack = null;
                System.gc();
            }
            Logging.LogDebugPrint(bEnableDebugPrint, "InitAudioTrack:%d:%d:%d", Integer.valueOf(this.mPlaybackSource), Integer.valueOf(i), Integer.valueOf(i3));
            if (mContext != null && (mContext instanceof Activity)) {
                ((Activity) mContext).setVolumeControlStream(this.mPlaybackSource);
            }
            try {
                this.audioTrack = new AudioTrack(this.mPlaybackSource, i, 4, 2, i3, 1);
                this.minBufferSizeCache.b(i, this.mPlaybackSource);
                if (this.audioTrack.getState() != 1) {
                    this.audioTrack.release();
                    this.audioTrack = null;
                    System.gc();
                    return false;
                }
                if (((long) (((double) (System.nanoTime() - nanoTime)) / 1000000.0d)) > 100) {
                    Logging.LogDebugPrint(bEnableDebugPrint, "InitAudioTrack: WARN: took too long (%d ms)", Long.valueOf((long) (((double) (System.nanoTime() - nanoTime)) / 1000000.0d)));
                }
                return true;
            } catch (IllegalArgumentException e) {
                Logging.LogNativePrintErr(String.format("failed to create AudioTrack instance: %s", new Object[]{e.getMessage()}), new Object[0]);
                return false;
            }
        }
        Logging.LogDebugPrint(bEnableDebugPrint, "InitAudioTrack: no changes in cfg. using same instance", new Object[0]);
        return true;
    }

    private synchronized boolean InitCapture() {
        boolean z = false;
        synchronized (this) {
            long nanoTime = System.nanoTime();
            Logging.LogDebugPrint(bEnableDebugPrint, "InitCapture", new Object[0]);
            if (this.mAudioSource == -1) {
                setDefaultAudioSource();
            }
            this.CaptureSamplingRate = SelectSamplingRate(false);
            if (this.CaptureSamplingRate == 0) {
                Logging.LogDebugPrint(bEnableDebugPrint, "SelectSamplingRate for capture failed", new Object[0]);
                Logging.LogNativePrintErr("SelectSamplingRate for capture failed", new Object[0]);
            } else {
                int[] iArr = new int[2];
                if (InitAudioRecord(this.CaptureSamplingRate, iArr, false)) {
                    Logging.LogNativePrint("AudioRecord initialized for sr=" + this.CaptureSamplingRate, new Object[0]);
                    Logging.LogNativePrint("AudioRecord bufSize=" + iArr[0] + "bytes (" + (((iArr[0] / 2) * 1000) / this.CaptureSamplingRate) + "ms)", new Object[0]);
                    Logging.LogNativePrint("AudioRecord minBufSize=" + iArr[1] + "bytes (" + (((iArr[1] / 2) * 1000) / this.CaptureSamplingRate) + "ms)", new Object[0]);
                    if (((long) (((double) (System.nanoTime() - nanoTime)) / 1000000.0d)) > 100) {
                        Logging.LogDebugPrint(bEnableDebugPrint, "InitCapture: WARN: took too long (%d ms)", Long.valueOf((long) (((double) (System.nanoTime() - nanoTime)) / 1000000.0d)));
                    }
                    z = true;
                } else {
                    Logging.LogDebugPrint(bEnableDebugPrint, "InitAudioRecord(" + this.CaptureSamplingRate + ") failed", new Object[0]);
                    Logging.LogNativePrintErr("InitAudioRecord(" + this.CaptureSamplingRate + ") failed", new Object[0]);
                }
            }
        }
        return z;
    }

    private synchronized boolean StartCapture_impl(boolean z, boolean z2) {
        boolean z3 = true;
        synchronized (this) {
            long nanoTime = System.nanoTime();
            boolean z4 = bEnableDebugPrint;
            String str = "StartCapture %s";
            Object[] objArr = new Object[1];
            objArr[0] = z2 ? "reset" : "resume";
            Logging.LogDebugPrint(z4, str, objArr);
            if (!this.CaptureActive) {
                if (this.audioRecord == null) {
                    z3 = false;
                } else if (this.audioRecord.getState() == 0) {
                    z3 = false;
                } else {
                    if (z || this.captureThread == null) {
                        this.captureThread = new a(this);
                        this.captureThread.c();
                    }
                    this.captureThread.f();
                    try {
                        this.audioRecord.startRecording();
                        this.CaptureActive = StartCaptureBT(true);
                        if (((long) (((double) (System.nanoTime() - nanoTime)) / 1000000.0d)) > 100) {
                            Logging.LogDebugPrint(bEnableDebugPrint, "StartCapture: WARN: took too long (%d ms)", Long.valueOf((long) (((double) (System.nanoTime() - nanoTime)) / 1000000.0d)));
                        }
                        z3 = this.CaptureActive;
                    } catch (IllegalStateException e) {
                        Logging.LogNativePrintErr("audioRecord.startRecording() failed", new Object[0]);
                        z3 = false;
                    }
                }
            }
        }
        return z3;
    }

    private boolean StartCaptureSLES() {
        Logging.LogDebugPrint(bEnableDebugPrint, "StartCaptureSLES", new Object[0]);
        if (this.CaptureActive) {
            return true;
        }
        boolean StartCaptureBT = StartCaptureBT(false);
        this.CaptureActive = StartCaptureBT;
        return StartCaptureBT;
    }

    private synchronized boolean StartCaptureBT(boolean z) {
        boolean z2 = true;
        synchronized (this) {
            Logging.LogDebugPrint(bEnableDebugPrint, "StartCaptureBT:mUseBtAudioDevice=%s", String.valueOf(this.mUseBtAudioDevice));
            if (this.mUseBtAudioDevice) {
                Logging.LogDebugPrint(bEnableDebugPrint, "StartCaptureBT", new Object[0]);
                a btAudioDevice = getBtAudioDevice();
                if (btAudioDevice != null) {
                    btAudioDevice.a();
                } else {
                    Logging.LogNativePrintErr("FAILED to get BT device", new Object[0]);
                    z2 = false;
                }
            }
        }
        return z2;
    }

    private boolean StartCapture() {
        return StartCapture_impl(true, true);
    }

    @SuppressLint({"NewApi"})
    private void StopHardwareAec() {
        if (canUseHwAec() && this.mHardwareAecEnable && this.mHardwareAecPresent) {
            synchronized (this) {
                Logging.LogDebugPrint(bEnableDebugPrint, "StopEC", new Object[0]);
                if (this.mAec != null) {
                    this.mAec.release();
                }
                if (this.mAgc != null) {
                    this.mAgc.release();
                }
                if (this.mNs != null) {
                    this.mNs.release();
                }
                this.mAec = null;
                this.mAgc = null;
                this.mNs = null;
            }
        }
    }

    private synchronized boolean StopCapture_impl(boolean z, boolean z2) {
        long nanoTime = System.nanoTime();
        boolean z3 = bEnableDebugPrint;
        String str = "StopCapture %s:%s";
        Object[] objArr = new Object[2];
        objArr[0] = z ? "restart" : "keep";
        objArr[1] = z2 ? "reset" : "resume";
        Logging.LogDebugPrint(z3, str, objArr);
        if (this.CaptureActive) {
            if (this.captureThread != null) {
                this.captureThread.e();
                if (z) {
                    this.captureThread.d();
                    this.captureThread = null;
                }
            }
            StopCaptureBT(true);
            if (z2) {
                StopHardwareAec();
            }
            if (this.audioRecord != null) {
                try {
                    if (this.audioRecord.getState() != 0) {
                        if (this.CaptureActive) {
                            this.audioRecord.stop();
                        }
                        if (z2) {
                            this.audioRecord.release();
                            this.audioRecord = null;
                            System.gc();
                        }
                    }
                } catch (IllegalStateException e) {
                    Logging.LogNativePrintErr("audioRecord.stop: exception " + e.toString(), new Object[0]);
                }
            }
            this.CaptureActive = false;
            if (((long) (((double) (System.nanoTime() - nanoTime)) / 1000000.0d)) > 100) {
                Logging.LogDebugPrint(bEnableDebugPrint, "StopCapture: WARN: took too long (%d ms)", Long.valueOf((long) (((double) (System.nanoTime() - nanoTime)) / 1000000.0d)));
            }
        }
        return true;
    }

    private boolean StopCaptureSLES() {
        Logging.LogDebugPrint(bEnableDebugPrint, "StopCaptureSLES", new Object[0]);
        if (this.CaptureActive) {
            StopCaptureBT(false);
            this.CaptureActive = false;
        }
        return true;
    }

    private synchronized boolean StopCaptureBT(boolean z) {
        if (this.CaptureActive) {
            Logging.LogDebugPrint(bEnableDebugPrint, "StopCaptureBT:mUseBtAudioDevice=%s", String.valueOf(this.mUseBtAudioDevice));
            if (this.mUseBtAudioDevice) {
                Logging.LogDebugPrint(bEnableDebugPrint, "StopCaptureBT", new Object[0]);
                a btAudioDevice = getBtAudioDevice();
                if (btAudioDevice != null) {
                    btAudioDevice.b();
                } else {
                    Logging.LogNativePrintErr("FAILED to get BT device", new Object[0]);
                }
            }
        }
        return true;
    }

    private boolean StopCapture() {
        return StopCapture_impl(true, true);
    }

    private static boolean canUseHwAec() {
        return TSM_impl.mIsGalaxyS3 || TSM_impl.mIsGalaxyS4 || TSM_impl.mIsSGS5 || TSM_impl.mIsSGS7 || TSM_impl.mIsGalaxyNote || TSM_impl.mIsNexus7 || TSM_impl.mIsNexus5 || TSM_impl.mIsNexus5X || TSM_impl.mIsNexus6 || TSM_impl.mIsNexus6P || TSM_impl.mIsTele2Mini;
    }

    private static boolean enableSLES() {
        return (canUseHwAec() || TSM_impl.mIsSGS6 || TSM_impl.mIsSGS8P || TSM_impl.mIsHTCOneS || TSM_impl.mIsHTCOne || TSM_impl.mIsHTCD628 || TSM_impl.mIsHTCD626 || TSM_impl.mIsHTCOneA9 || TSM_impl.mIsHTCOneM7 || TSM_impl.mIsHTCOneM8 || TSM_impl.mIsHTCOneM9 || TSM_impl.mIsHTCOneM10 || TSM_impl.mIsAndromax || TSM_impl.mIsAlcatel7045A || TSM_impl.mIsAlcatel7045Y || TSM_impl.mIsAlcatelPixi35 || TSM_impl.mIsSonyEricssonXArc || TSM_impl.mIsSonyXperiaS || TSM_impl.mIsSonyEricssonXMiro || TSM_impl.mIsSonyXperiaJ || TSM_impl.mIsSonyXperiaP || TSM_impl.mIsSonyXperiaZU || TSM_impl.mIsSonyXperiaZ1 || TSM_impl.mIsSonyXperiaZ3 || TSM_impl.mIsSonyXperiaZ5 || TSM_impl.mIsSonyXperiaM2 || TSM_impl.mIsTele2Midi) ? false : true;
    }

    @SuppressLint({"NewApi"})
    private boolean InitAudioRecord(int i, int[] iArr, boolean z) {
        long nanoTime = System.nanoTime();
        boolean z2 = bEnableDebugPrint;
        String str = "InitAudioRecord: %s";
        Object[] objArr = new Object[1];
        objArr[0] = z ? "bufsize" : "init";
        Logging.LogDebugPrint(z2, str, objArr);
        if (!(z || this.audioRecord == null)) {
            this.audioRecord.release();
            this.audioRecord = null;
            System.gc();
        }
        if (!z) {
            StopHardwareAec();
        }
        int minBufferSize = AudioRecord.getMinBufferSize(i, 16, 2);
        if (minBufferSize <= 0) {
            Logging.LogDebugPrint(bEnableDebugPrint, "AudioRecord - cannot get minBufferSize", new Object[0]);
            Logging.LogNativePrintErr("AudioRecord - cannot get minBufferSize", new Object[0]);
            return false;
        }
        if (iArr != null) {
            iArr[1] = minBufferSize;
        }
        int i2 = minBufferSize * 100;
        if (!TSM_impl.mIsKarbonnTAFone) {
            minBufferSize = i2;
        }
        if (iArr != null) {
            iArr[0] = minBufferSize;
        }
        if (!z) {
            boolean GetSpeakerphone = GetSpeakerphone();
            SetSpeakerphone(false);
            Logging.LogDebugPrint(bEnableDebugPrint, "InitAudioRecord:%d:%d:%d", Integer.valueOf(this.mAudioSource), Integer.valueOf(i), Integer.valueOf(minBufferSize));
            try {
                this.audioRecord = new AudioRecord(this.mAudioSource, i, 16, 2, minBufferSize);
                if (this.audioRecord == null) {
                    Logging.LogDebugPrint(bEnableDebugPrint, "AudioRecord creation ERROR", new Object[0]);
                    Logging.LogNativePrintErr("AudioRecord creation ERROR", new Object[0]);
                    return false;
                } else if (this.audioRecord.getState() != 1) {
                    Logging.LogDebugPrint(bEnableDebugPrint, "AudioRecord - wrong state - ", Integer.valueOf(this.audioRecord.getState()));
                    Logging.LogNativePrintErr("AudioRecord - wrong state - " + this.audioRecord.getState(), new Object[0]);
                    this.audioRecord.release();
                    this.audioRecord = null;
                    System.gc();
                    return false;
                } else {
                    if (TSM_impl.mIsMI_4) {
                        Logging.LogNativePrint("MI 4", new Object[0]);
                    }
                    if (canUseHwAec() && VERSION.SDK_INT >= 16) {
                        try {
                            if (AcousticEchoCanceler.isAvailable()) {
                                Logging.LogNativePrint("AudioRecord apr", new Object[0]);
                                this.mAec = AcousticEchoCanceler.create(this.audioRecord.getAudioSessionId());
                                if (this.mAec == null || this.mAec.setEnabled(this.mHardwareAecEnable) != 0) {
                                    Logging.LogNativePrint("AudioRecord AFL", new Object[0]);
                                } else {
                                    this.mHardwareAecPresent = true;
                                    Logging.LogNativePrint("AudioRecord aok", new Object[0]);
                                }
                            } else {
                                Logging.LogNativePrint("AudioRecord anpr", new Object[0]);
                            }
                            SetHardwareAecPresentStatus(this.contextPtr, this.mHardwareAecPresent ? 1 : 0);
                            if (!(TSM_impl.mIsAndromaxQ || TSM_impl.mIsAndromaxE3 || TSM_impl.mIsAndromaxR || TSM_impl.mIsAndromaxE2P || TSM_impl.mIsAndromaxR2 || TSM_impl.mIsNexus5X || TSM_impl.mIsNexus6 || TSM_impl.mIsNexus6P || TSM_impl.mIsHTCOneM8)) {
                                try {
                                    if (AutomaticGainControl.isAvailable()) {
                                        Logging.LogNativePrint("AudioRecord gpr", new Object[0]);
                                        this.mAgc = AutomaticGainControl.create(this.audioRecord.getAudioSessionId());
                                        if (this.mAgc == null || this.mAgc.setEnabled(true) != 0) {
                                            Logging.LogNativePrint("AudioRecord GFL", new Object[0]);
                                        } else {
                                            Logging.LogNativePrint("AudioRecord gok", new Object[0]);
                                        }
                                    }
                                } catch (Exception e) {
                                    Logging.LogNativePrint("AudioRecord GEXC", new Object[0]);
                                }
                            }
                            if (!(TSM_impl.mIsHTCD628 || TSM_impl.mIsHTCD626 || TSM_impl.mIsAndromaxQ || TSM_impl.mIsAndromaxE3 || TSM_impl.mIsAndromaxR || TSM_impl.mIsAndromaxE2P || TSM_impl.mIsAndromaxR2 || TSM_impl.mIsNexus5X || TSM_impl.mIsNexus6 || TSM_impl.mIsNexus6P || TSM_impl.mIsHTCOneM8)) {
                                try {
                                    if (NoiseSuppressor.isAvailable()) {
                                        Logging.LogNativePrint("AudioRecord npr", new Object[0]);
                                        this.mNs = NoiseSuppressor.create(this.audioRecord.getAudioSessionId());
                                        if (this.mNs == null || this.mNs.setEnabled(true) != 0) {
                                            Logging.LogNativePrint("AudioRecord NFL", new Object[0]);
                                        } else {
                                            Logging.LogNativePrint("AudioRecord nok", new Object[0]);
                                        }
                                    }
                                } catch (Exception e2) {
                                    Logging.LogNativePrint("AudioRecord NEXC", new Object[0]);
                                }
                            }
                        } catch (Exception e3) {
                            Logging.LogNativePrint("AudioRecord AEXC", new Object[0]);
                            return false;
                        }
                    }
                    Logging.LogDebugPrint(bEnableDebugPrint, "InitAudioRecord:mUseBtAudioDevice=%s", String.valueOf(this.mUseBtAudioDevice));
                    if (!this.mUseBtAudioDevice) {
                        SetSpeakerphone(GetSpeakerphone);
                    }
                }
            } catch (Exception e4) {
                e4.printStackTrace();
                Logging.LogNativePrintErr(String.format("failed to create AudioRecord instance: %s", new Object[]{e4.getMessage()}), new Object[0]);
                return false;
            }
        }
        if (((long) (((double) (System.nanoTime() - nanoTime)) / 1000000.0d)) > 100) {
            Logging.LogDebugPrint(bEnableDebugPrint, "InitAudioRecord: WARN: took too long (%d ms)", Long.valueOf((long) (((double) (System.nanoTime() - nanoTime)) / 1000000.0d)));
        }
        return true;
    }

    @SuppressLint({"InlinedApi", "NewApi"})
    private synchronized boolean EnableHardwareAec(int i) {
        boolean z = true;
        boolean z2 = false;
        synchronized (this) {
            if (canUseHwAec()) {
                Logging.LogDebugPrint(bEnableDebugPrint, "EnableEC %d", Integer.valueOf(i));
                boolean z3 = i != 0;
                Logging.LogNativePrint("AudioRecord ena" + i, new Object[0]);
                if (this.mAec == null) {
                    this.mHardwareAecEnable = z3;
                    z2 = true;
                } else if (VERSION.SDK_INT >= 16) {
                    Logging.LogNativePrint("AudioRecord ena7", new Object[0]);
                    if (this.mAec.setEnabled(z3) != 0) {
                        z = false;
                    }
                    z2 = z;
                }
            }
        }
        return z2;
    }

    private void setDefaultPlaybackSource() {
        this.mPlaybackSource = 0;
    }

    @SuppressLint({"InlinedApi"})
    private void setDefaultAudioSource() {
        if (TSM_impl.mIsLgOptimusG || TSM_impl.mIsKoreanGalaxyS2 || TSM_impl.mIsHuaweiAscend || TSM_impl.mIsQuantaAL7 || TSM_impl.mIsHTCdesireX || TSM_impl.mIsGalaxyS2Plus || TSM_impl.mIsHTCOne || TSM_impl.mIsHTCOneS || TSM_impl.mIsHTCOneA9 || TSM_impl.mIsHTCOneM7 || TSM_impl.mIsHTCOneM9 || TSM_impl.mIsSonyXperiaJ || TSM_impl.mIsSonyXperiaP || TSM_impl.mIsSonyXperiaM2 || TSM_impl.mIsHTCD626) {
            this.mAudioSource = 0;
        } else if (TSM_impl.mIsKarbonnTAFone) {
            this.mAudioSource = 1;
        } else if (TSM_impl.mIsGalaxyS3mini || TSM_impl.mIsLG_G2 || TSM_impl.mIsMI_3W || TSM_impl.mIsMI_4) {
            this.mAudioSource = 6;
        } else if (VERSION.SDK_INT >= 11) {
            this.mAudioSource = 7;
        } else {
            this.mAudioSource = 0;
        }
        Logging.LogNativePrint("Default AudioSource = " + this.mAudioSource, new Object[0]);
    }

    private boolean SetAudioSource(int i) {
        Logging.LogNativePrint("SetAudioSource: newSource  = " + String.valueOf(i), new Object[0]);
        if (this.mAudioSource != i) {
            this.mAudioSource = i;
            Logging.LogNativePrint("AudioSource = " + String.valueOf(this.mAudioSource), new Object[0]);
        }
        return true;
    }

    private int SelectSamplingRate(boolean z) {
        int a = this.minBufferSizeCache.a();
        int[] iArr = new int[a];
        if (this.mUseBtAudioDevice) {
            if (z) {
                return BT_HS_AUDIO_PB_RATE;
            }
            return BT_HS_AUDIO_CAP_RATE;
        } else if (TSM_impl.mIsMI_3W) {
            return BT_HS_AUDIO_PB_RATE;
        } else {
            if (TSM_impl.mIsKarbonnTAFone) {
                return 44100;
            }
            if (TSM_impl.mIsHSL695 || TSM_impl.mIsHSL671) {
                return 44100;
            }
            if (TSM_impl.mIsAndromax) {
                return 44100;
            }
            if (TSM_impl.mIsHTCOne || TSM_impl.mIsHTCOneA9 || TSM_impl.mIsHTCOneM7 || TSM_impl.mIsHTCOneM9) {
                return 44100;
            }
            if (TSM_impl.mIsSonyEricssonXMiro || TSM_impl.mIsLG_G2 || TSM_impl.mIsUnknownDevice) {
                return BT_HS_AUDIO_PB_RATE;
            }
            if ((TSM_impl.mIsSamsungGalaxyAce || TSM_impl.mIsSonyXperiaP) && !z) {
                return BT_HS_AUDIO_PB_RATE;
            }
            if (TSM_impl.mIsSGS5 || TSM_impl.mIsSGS6 || TSM_impl.mIsSGS7) {
                return 44100;
            }
            if (TSM_impl.mIsAcerE380) {
                return 44100;
            }
            if (TSM_impl.mIsTele2Mini) {
                return 44100;
            }
            if (TSM_impl.mIsSonyXperiaZU || TSM_impl.mIsSonyXperiaZ3 || TSM_impl.mIsSonyXperiaZ5) {
                return 44100;
            }
            if (TSM_impl.mIsAlcatel7045A || TSM_impl.mIsAlcatel7045Y) {
                return 44100;
            }
            int i;
            for (i = 0; i < a; i++) {
                boolean InitAudioTrack;
                int b = this.minBufferSizeCache.b(i);
                int[] iArr2 = new int[2];
                if (z) {
                    InitAudioTrack = InitAudioTrack(b, iArr2, true);
                } else {
                    InitAudioTrack = InitAudioRecord(b, iArr2, true);
                }
                if (!InitAudioTrack) {
                    iArr[i] = 0;
                } else if (!this.ENABLE_SR_SELECTION_WITH_MIN_BUFSIZE) {
                    return b;
                } else {
                    iArr[i] = iArr2[1];
                }
            }
            if (this.ENABLE_SR_SELECTION_WITH_MIN_BUFSIZE) {
                String str = z ? "Playback" : "Capture";
                int i2 = Integer.MAX_VALUE;
                i = 0;
                int i3 = -1;
                while (i < a) {
                    int b2 = this.minBufferSizeCache.b(i);
                    int i4 = ((iArr[i] / 2) * 1000) / b2;
                    Logging.LogNativePrint("SelectSamplingRate: " + str + "[" + b2 + "]=" + iArr[i] + " (" + i4 + "ms)", new Object[0]);
                    if (i4 == 0 || i4 >= i2 || i2 - i4 <= 20) {
                        i4 = i2;
                    } else {
                        i3 = i;
                    }
                    i++;
                    i2 = i4;
                }
                if (i3 != -1) {
                    return this.minBufferSizeCache.b(i3);
                }
            }
            return 0;
        }
    }

    private int GetAudioPath() {
        return this.mAudioPath;
    }

    @SuppressLint({"NewApi"})
    private boolean IsSLESModeEnabled() {
        if (enableSLES()) {
            return true;
        }
        Logging.LogDebugPrint(bEnableDebugPrint, "CheckPresentEC", new Object[0]);
        if (VERSION.SDK_INT < 16 || AcousticEchoCanceler.isAvailable()) {
            return false;
        }
        return true;
    }

    @SuppressLint({"NewApi"})
    private int GetPrppertyOutputSampleRate() {
        Logging.LogDebugPrint(bEnableDebugPrint, "GetPrppertyOutputSampleRate", new Object[0]);
        if (VERSION.SDK_INT < 17 || getAudioManager() == null) {
            return -1;
        }
        int parseInt = Integer.parseInt(getAudioManager().getProperty("android.media.property.OUTPUT_SAMPLE_RATE"));
        Log.d("AudioManager", "PROPERTY_OUTPUT_SAMPLE_RATE=" + parseInt);
        return parseInt;
    }

    @SuppressLint({"NewApi"})
    private int GetPrppertyOutputFramesPerBuffer() {
        Logging.LogDebugPrint(bEnableDebugPrint, "GetPrppertyOutputFramesPerBuffer", new Object[0]);
        if (VERSION.SDK_INT < 17 || getAudioManager() == null) {
            return -1;
        }
        int parseInt = Integer.parseInt(getAudioManager().getProperty("android.media.property.OUTPUT_FRAMES_PER_BUFFER"));
        Log.d("AudioManager", "PROPERTY_OUTPUT_FRAMES_PER_BUFFER=" + parseInt);
        return parseInt;
    }

    private int SetAudioPathSLES(int i) {
        return SetAudioPath_impl(i, false);
    }

    private int SetAudioPath(int i) {
        return SetAudioPath_impl(i, true);
    }

    private synchronized int SetAudioPath_impl(int i, boolean z) {
        boolean z2 = true;
        int i2 = 0;
        synchronized (this) {
            long nanoTime = System.nanoTime();
            if (i != this.mAudioPath) {
                String str;
                this.mOutputVolume = -1;
                boolean z3 = bEnableDebugPrint;
                String str2 = "SetAudioPath_impl: Mode=%s Path:%d->%d PlaybackActive=%s CaptureActive=%s OutputVolume=%d AudioManager=%h Context=%h spk=%s";
                Object[] objArr = new Object[9];
                objArr[0] = z ? "Java" : "SLES";
                objArr[1] = Integer.valueOf(this.mAudioPath);
                objArr[2] = Integer.valueOf(i);
                objArr[3] = this.PlaybackActive ? "true" : "false";
                objArr[4] = this.CaptureActive ? "true" : "false";
                objArr[5] = Integer.valueOf(GetOutputVolume());
                objArr[6] = getAudioManager();
                objArr[7] = mContext;
                if (GetSpeakerphone()) {
                    str = "on";
                } else {
                    str = "off";
                }
                objArr[8] = str;
                Logging.LogDebugPrint(z3, str2, objArr);
                if (getAudioManager() != null) {
                    z3 = getAudioManager().isBluetoothA2dpOn();
                } else {
                    z3 = false;
                }
                if (i == 2 || i == 1 || i == 5 || i == 0) {
                    boolean z4;
                    if (this.PlaybackActive || this.CaptureActive) {
                        z4 = true;
                    } else {
                        z4 = false;
                    }
                    if (z4) {
                        if (z) {
                            StopCapture_impl(false, z4);
                            StopPlayback_impl(false, z4);
                        } else {
                            StopCaptureSLES();
                            StopPlaybackSLES();
                        }
                    }
                    switch (i) {
                        case 1:
                            if (z3) {
                                getAudioManager().setMode(0);
                                getAudioManager().setBluetoothScoOn(false);
                                getAudioManager().stopBluetoothSco();
                            }
                            this.mUseBtAudioDevice = false;
                            SetSpeakerphone(true);
                            break;
                        case 5:
                            SetSpeakerphone(false);
                            if (!z3) {
                                AudioManager audioManager = getAudioManager();
                                if (audioManager == null || audioManager.isBluetoothScoOn() || !audioManager.isBluetoothScoAvailableOffCall()) {
                                    z2 = false;
                                }
                                BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
                                if (defaultAdapter != null) {
                                    z2 = defaultAdapter.isEnabled();
                                }
                                this.mUseBtAudioDevice = z2;
                                break;
                            }
                            getAudioManager().setMode(0);
                            getAudioManager().startBluetoothSco();
                            getAudioManager().setBluetoothScoOn(true);
                            break;
                        default:
                            if (z3) {
                                getAudioManager().setMode(3);
                                getAudioManager().setBluetoothScoOn(false);
                                getAudioManager().stopBluetoothSco();
                            }
                            this.mUseBtAudioDevice = false;
                            SetSpeakerphone(false);
                            break;
                    }
                    Logging.LogDebugPrint(bEnableDebugPrint, "SetAudioPath_impl: hasA2DPEnabled=%s mUseBtAudioDevice=%s", String.valueOf(z3), String.valueOf(this.mUseBtAudioDevice));
                    this.mAudioPath = i;
                    if (z4) {
                        if (z) {
                            if (z4 && (!InitPlayback() || !InitCapture() || !SetPlaybackSamplingRate(this.contextPtr, this.PlaybackSamplingRate, 1) || !SetCaptureSamplingRate(this.contextPtr, this.CaptureSamplingRate, 1))) {
                                i2 = -5;
                            } else if (!(StartPlayback_impl(false, z4) && StartCapture_impl(false, z4))) {
                                i2 = -5;
                            }
                        } else if (!(StartPlaybackSLES() && StartCaptureSLES())) {
                            i2 = -5;
                        }
                    }
                    Logging.LogDebugPrint(bEnableDebugPrint, "SetAudioPath_impl: current volume %d", Integer.valueOf(GetOutputVolume()));
                    if (((long) (((double) (System.nanoTime() - nanoTime)) / 1000000.0d)) > 100) {
                        Logging.LogDebugPrint(bEnableDebugPrint, "SetAudioPath_impl: WARN: switch took too long (%d ms)", Long.valueOf((long) (((double) (System.nanoTime() - nanoTime)) / 1000000.0d)));
                    }
                } else {
                    Logging.LogDebugPrint(bEnableDebugPrint, "SetAudioPath_impl: Invalid mode", new Object[0]);
                    i2 = -2;
                }
            }
        }
        return i2;
    }

    private synchronized void SetSpeakerphone(boolean z) {
        boolean z2 = bEnableDebugPrint;
        String str = "SetSpeakerphone: %s";
        Object[] objArr = new Object[1];
        objArr[0] = z ? "on" : "off";
        Logging.LogDebugPrint(z2, str, objArr);
        if (getAudioManager() != null) {
            if (getAudioManager().isSpeakerphoneOn() == z) {
                Logging.LogDebugPrint(bEnableDebugPrint, "SetSpeakerphone: The same value", new Object[0]);
            } else {
                getAudioManager().setSpeakerphoneOn(z);
                this.mIsSpeakerphoneOn = z;
            }
        }
    }

    private boolean GetSpeakerphone() {
        if (getAudioManager() == null) {
            return false;
        }
        return getAudioManager().isSpeakerphoneOn();
    }

    private synchronized void SetOutputVolume(int i) {
        int i2 = 32768;
        synchronized (this) {
            if (getAudioManager() == null) {
                Logging.LogDebugPrint(bEnableDebugPrint, "SetOutputVolume: AudioManager is NULL!", new Object[0]);
            } else {
                int i3;
                if (!TSM_impl.mIsSonyEricssonXArc || i <= 32768) {
                    i3 = i;
                } else {
                    i3 = 32768;
                }
                if (TSM_impl.mIsMilestone1 && r1 > 32768) {
                    i3 = 32768;
                }
                if (!(this.mAudioPath == 1 && TSM_impl.mIsGalaxyS2Plus && i3 > 32768)) {
                    i2 = i3;
                }
                this.mOutputVolume = i2;
                Logging.LogDebugPrint(bEnableDebugPrint, "SetOutputVolume: %d", Integer.valueOf(i2));
                Logging.LogDebugPrint(bEnableDebugPrint, "SetOutputVolume internal: %d", Integer.valueOf((i2 * getAudioManager().getStreamMaxVolume(this.mPlaybackSource)) / 65535));
                getAudioManager().setStreamVolume(this.mPlaybackSource, i2, 0);
            }
        }
    }

    private int getSystemPlaybackVolume() {
        if (getAudioManager() == null) {
            return 65535;
        }
        Logging.LogDebugPrint(bEnableDebugPrint, "getSystemPlaybackVolume: %d", Integer.valueOf(getAudioManager().getStreamVolume(this.mPlaybackSource)));
        return (65535 * getAudioManager().getStreamVolume(this.mPlaybackSource)) / getAudioManager().getStreamMaxVolume(this.mPlaybackSource);
    }

    private int GetOutputVolume() {
        if (this.mOutputVolume >= 0) {
            return this.mOutputVolume;
        }
        int systemPlaybackVolume = getSystemPlaybackVolume();
        Logging.LogDebugPrint(bEnableDebugPrint, "GetOutputVolume: %d", Integer.valueOf(systemPlaybackVolume));
        this.mOutputVolume = systemPlaybackVolume;
        return systemPlaybackVolume;
    }

    private void OnVolumeChangedExternally() {
        this.mOutputVolume = getSystemPlaybackVolume();
        Logging.LogDebugPrint(bEnableDebugPrint, "OnVolumeChangedExternally: %d", Integer.valueOf(this.mOutputVolume));
    }
}
