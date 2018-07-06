package com.fgmicrotec.mobile.android.fgmag;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.net.rtp.AudioCodec;
import android.net.rtp.AudioGroup;
import android.net.rtp.AudioStream;
import android.os.Build.VERSION;
import com.fgmicrotec.mobile.android.a.c;
import com.fgmicrotec.mobile.android.fgvoip.FgVoIP;
import com.mavenir.android.common.q;
import com.mavenir.android.common.w;
import com.mavenir.android.settings.c.e;
import com.mavenir.android.settings.c.m;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Timer;

@SuppressLint({"NewApi"})
public class SimpleCodecAL {
    private static boolean mIsPlayStarting = false;
    private static boolean mIsPlayStopping = false;
    private static boolean mIsRecStarting = false;
    private static boolean mIsRecStopping = false;
    private static byte[] mMediaEngine = null;
    private static String[] mMediaFmtp = null;
    private static String[] mMediaName = null;
    private static byte[] mMediaType = null;
    private static boolean mPendingPlayStop = false;
    private static boolean mPendingRecStop = false;
    private static a mPlayParams = null;
    private static long mPlayStoppingTimestamp = 0;
    private static b mRecParams = null;
    private static long mRecStoppingTimestamp = 0;
    private static boolean m_bIsInit = false;
    private static SimpleCodecAL s_instance;
    private AudioGroup mAudioGroup = null;
    private AudioStream mAudioStream = null;
    private InetAddress mLocalInetAddress = null;
    private int mLocalPort = 0;
    private boolean mNativeAudioIsMuted = false;
    private InetAddress mRemoteInetAddress = null;
    private int mRemotePort = 0;
    private Timer mTimer = null;
    private int mUSedMediaType = 0;
    private boolean mUseNativeAndroidAudio = false;
    private Context m_ctx = null;
    private com.fgmicrotec.mobile.android.a.b m_receiver = null;
    private c m_sender = null;

    private class a {
        public int a = 0;
        public int b = 0;
        final /* synthetic */ SimpleCodecAL c;

        public a(SimpleCodecAL simpleCodecAL, int i, int i2) {
            this.c = simpleCodecAL;
            this.a = i;
            this.b = i2;
        }
    }

    private class b {
        public int a = 0;
        public int b = 0;
        public int c = 0;
        final /* synthetic */ SimpleCodecAL d;

        public b(SimpleCodecAL simpleCodecAL, int i, int i2, int i3) {
            this.d = simpleCodecAL;
            this.a = i;
            this.b = i2;
            this.c = i3;
        }
    }

    private native byte CallbackDecoderImpl(byte b, short[] sArr, byte[] bArr);

    private native byte CallbackDecoderImpl2(byte b, short[] sArr, short[] sArr2);

    private native byte CallbackEncoderImpl(byte b, short s, byte[] bArr);

    private native byte CallbackEncoderImpl2(byte b, short s, short[] sArr);

    private native void initCnf();

    private static native void printTofgAdapter(String str);

    public static native void sendSilence(int i);

    private static native void setAudioCodecs(String[] strArr, byte[] bArr, String[] strArr2, byte[] bArr2);

    private static native void startPlayerCnfImpl(int i);

    private static native void startRecorderCnfImpl(int i);

    private static native void stopPlayerCnfImpl(int i);

    private static native void stopRecorderCnfImpl(int i);

    private SimpleCodecAL() {
    }

    public static SimpleCodecAL getInstance() {
        if (s_instance == null) {
            s_instance = new SimpleCodecAL();
        }
        return s_instance;
    }

    public void setContext(Context context) {
        this.m_ctx = context;
    }

    public static byte CallbackEncoder(byte b, short s, byte[] bArr) {
        if (!m_bIsInit) {
            return (byte) -100;
        }
        if (bArr == null) {
            return (byte) -101;
        }
        return getInstance().CallbackEncoderImpl(b, s, bArr);
    }

    public static byte CallbackDecoder(byte b, short[] sArr, byte[] bArr) {
        if (!m_bIsInit) {
            return (byte) -100;
        }
        if (sArr == null || sArr.length == 0 || bArr == null) {
            return (byte) -101;
        }
        return getInstance().CallbackDecoderImpl(b, sArr, bArr);
    }

    public static byte CallbackEncoder2(byte b, short s, short[] sArr) {
        if (!m_bIsInit) {
            return (byte) -100;
        }
        if (sArr == null) {
            return (byte) -101;
        }
        return getInstance().CallbackEncoderImpl2(b, s, sArr);
    }

    public static byte CallbackDecoder2(byte b, short[] sArr, short[] sArr2) {
        if (!m_bIsInit) {
            return (byte) -100;
        }
        if (sArr == null || sArr.length == 0 || sArr2 == null) {
            return (byte) -101;
        }
        return getInstance().CallbackDecoderImpl2(b, sArr, sArr2);
    }

    public static void stopPlayerAndRecorderLoops() {
        com.fgmicrotec.mobile.android.a.b.b();
        c.c();
    }

    private void sendText(String str) {
        if (this.m_ctx == null) {
        }
    }

    private static int initReq() {
        getInstance().initCnf();
        m_bIsInit = true;
        configCodecsEngine();
        return m_bIsInit ? 0 : -1;
    }

    public static void logToFgAdapter(String str) {
        printTofgAdapter(str + "\n");
    }

    public static int getFrameSignature(short[] sArr, int i) {
        int i2 = 0;
        int i3 = 0;
        while (i2 < i) {
            i3 += Math.abs(sArr[i2]);
            i2++;
        }
        return i3;
    }

    private static int exitReq() {
        return 0;
    }

    public static void configCodecsEngine() {
        byte b;
        byte b2 = (VERSION.SDK_INT >= 12 && FgVoIP.U().aj() && FgVoIP.U().A()) ? (byte) 1 : (byte) 0;
        if ((FgVoIP.U().aj() || FgVoIP.U().ai()) && w.a().c()) {
            b = (byte) 1;
        } else {
            b = (byte) 0;
        }
        e[] h = e.h();
        mMediaName = new String[h.length];
        mMediaFmtp = new String[h.length];
        mMediaType = new byte[h.length];
        mMediaEngine = new byte[h.length];
        for (int i = 0; i < h.length; i++) {
            e eVar = h[i];
            mMediaName[i] = null;
            mMediaFmtp[i] = null;
            mMediaType[i] = (byte) eVar.b();
            mMediaEngine[i] = (byte) 0;
            if (b2 != (byte) 0 && (eVar == e.PCMA || eVar == e.AMR)) {
                mMediaEngine[i] = (byte) 1;
            }
            if (b != (byte) 0) {
                mMediaEngine[i] = (byte) 1;
            }
        }
        setAudioCodecs(mMediaName, mMediaType, mMediaFmtp, mMediaEngine);
    }

    private void startRecorderReq(String str, int i, int i2, int i3, int i4, int i5, boolean z, boolean z2, boolean z3, boolean z4, boolean z5) {
        q.b("SimpleCodecAL", "startRecorderReq(): nRecorderMode = " + i5 + "; nMediaType = " + i + "; nCodecMode = " + i3);
        if (i == 102) {
            if (i3 == -1 || i3 == 65535) {
                i3 = 7;
            }
        } else if (i == 96 && (i3 == -1 || i3 == 65535)) {
            i3 = 7;
        }
        setupNativeAudioUsageBasedOnMediaTypeAndAndroidVersion(i);
        q.b("SimpleCodecAL", "startRecorderReq(): mUseNativeAndroidAudio = " + this.mUseNativeAndroidAudio);
        if (this.mUseNativeAndroidAudio) {
            q.b("SimpleCodecAL", "startRecorderReq() starting NATIVE audio ");
            mIsRecStarting = true;
            if (startNativeAudioStream(i, i2)) {
                startRecorderCnf(0);
                return;
            } else {
                startRecorderCnf(1);
                return;
            }
        }
        q.b("SimpleCodecAL", "startRecorderReq() starting FGMSP audio ");
        if (!mIsRecStopping || System.currentTimeMillis() - mRecStoppingTimestamp >= 5000) {
            if (this.m_ctx != null) {
                ((AudioManager) this.m_ctx.getSystemService("audio")).setMode(3);
            }
            mIsRecStopping = false;
            if (i == 102) {
                this.m_sender = new c(i, 16000, 320, i3);
            } else if (i == 120) {
                this.m_sender = new c(i, 48000, 960, i3);
            } else {
                this.m_sender = new c(i, 8000, 160, i3);
            }
            this.m_sender.start();
            mIsRecStarting = true;
            if (this.m_ctx != null) {
                String str2 = "G.711-u";
                if (i == 8) {
                    str2 = "G.711-a";
                } else if (i == 18) {
                    str2 = "G.729";
                }
                if (i5 == 2) {
                    str2 = str2 + "; ATI pass";
                } else if (i5 == 3) {
                    str2 = str2 + "; ATI tick";
                }
                sendText(str2);
                return;
            }
            return;
        }
        q.b("SimpleCodecAL", "startRecorderReq(): starting delayed since stopping in progress!");
        mRecParams = new b(this, i, i5, i3);
    }

    private boolean startNativeAudioStream(int i, int i2) {
        boolean z;
        try {
            this.mAudioGroup = new AudioGroup();
            this.mAudioGroup.setMode(3);
            this.mAudioStream = new AudioStream(this.mLocalInetAddress);
            this.mUSedMediaType = i;
            if (i == 0) {
                this.mAudioStream.setCodec(AudioCodec.PCMU);
            } else if (i == 8) {
                this.mAudioStream.setCodec(AudioCodec.PCMA);
            } else if (i == 3) {
                this.mAudioStream.setCodec(AudioCodec.GSM);
            } else if (i == 96) {
                this.mAudioStream.setCodec(AudioCodec.getCodec(i2, "AMR/8000", "mode-set=0,1,2,3,4,5,6,7; octet-align=0"));
            } else if (i == 120) {
                this.mAudioStream.setCodec(AudioCodec.getCodec(120, "opus/48000", null));
            }
            this.mAudioStream.setMode(0);
            int j = m.j();
            q.a("SimpleCodecAL", "startNativeAudioStream() dmtf value INBAND = 0 and OUTBAND= 1 value =" + j);
            if (j != 0) {
                i2 = 103;
            } else if (i != 96) {
                i2 = i;
            }
            q.a("SimpleCodecAL", "startNativeAudioStream() dtmf_type" + i2);
            this.mAudioStream.setDtmfType(i2);
            this.mAudioStream.associate(this.mRemoteInetAddress, this.mRemotePort);
            this.mAudioStream.join(this.mAudioGroup);
            ((AudioManager) this.m_ctx.getSystemService("audio")).setMode(3);
            z = true;
        } catch (Throwable e) {
            q.c("SimpleCodecAL", "startNativeAudioStream()", e);
            z = false;
        } catch (Throwable e2) {
            q.c("SimpleCodecAL", "startNativeAudioStream()", e2);
            z = false;
        }
        q.b("SimpleCodecAL", "startNativeAudioStream() retVal = " + z);
        return z;
    }

    public static void startRecorderCnf(int i) {
        q.b("SimpleCodecAL", "startRecorderCnf(): error = " + i);
        mRecParams = null;
        mIsRecStarting = false;
        startRecorderCnfImpl(i);
        if (mPendingRecStop) {
            mPendingRecStop = false;
            q.b("SimpleCodecAL", "startRecorderCnf(): pending stop recorder!");
            getInstance().stopRecorderReq();
        }
        Intent intent = new Intent("CallManager.ActionEnableMute");
        intent.putExtra("use_spirit", false);
        FgVoIP.U().sendBroadcast(intent);
    }

    private void prepareResources(int i) {
    }

    public void startPlayerCnf() {
        q.a("SimpleCodecAL", "startPlayerCnf from Spirit");
        Intent intent = new Intent("CallManager.ActionEnableMute");
        intent.putExtra("use_spirit", true);
        FgVoIP.U().sendBroadcast(intent);
    }

    private void startPlayerReq(int i, String str, int i2, int i3, int i4, int i5, int i6, boolean z) {
        q.b("SimpleCodecAL", "startPlayerReq(): nPlayerMode = " + i6 + "; nMediaType" + i2);
        if (com.fgmicrotec.mobile.android.fgvoipcommon.e.a()) {
            q.b("SimpleCodecAL", "startPlayerReq(): Spirit is enabled");
            Intent intent = new Intent("CallManager.ActionStopRingback");
            if (this.m_ctx != null) {
                this.m_ctx.sendBroadcast(intent);
                return;
            } else {
                q.d("SimpleCodecAL", "startPlayerReq(): context is null ");
                return;
            }
        }
        setupNativeAudioUsageBasedOnMediaTypeAndAndroidVersion(i2);
        if (this.mUseNativeAndroidAudio) {
            q.b("SimpleCodecAL", "startPlayerReq() starting NATIVE audio ");
            mIsPlayStarting = true;
            startPlayerCnf(0);
            return;
        }
        q.b("SimpleCodecAL", "startPlayerReq() starting FGMSP audio ");
        if (!mIsPlayStopping || System.currentTimeMillis() - mPlayStoppingTimestamp >= 5000) {
            mIsPlayStopping = false;
            if (this.m_ctx != null) {
                this.m_ctx.sendBroadcast(new Intent("CallManager.ActionStopRingback"));
                mIsPlayStarting = true;
                this.m_receiver = new com.fgmicrotec.mobile.android.a.b(this.m_ctx, i2);
                this.m_receiver.start();
                return;
            }
            q.d("SimpleCodecAL", "startPlayerReq(): m_ctx is null!");
            this.m_receiver = null;
            return;
        }
        q.b("SimpleCodecAL", "startPlayerReq(): starting delayed since stopping in progress!");
        mPlayParams = new a(this, i2, i6);
    }

    public static void startPlayerCnf(int i) {
        q.b("SimpleCodecAL", "startPlayerCnf(): error = " + i);
        mPlayParams = null;
        mIsPlayStarting = false;
        startPlayerCnfImpl(i);
        if (mPendingPlayStop) {
            mPendingPlayStop = false;
            q.b("SimpleCodecAL", "startPlayerCnf(): pending stop player!");
            getInstance().stopPlayerReq();
        }
    }

    private void stopRecorderReq() {
        q.b("SimpleCodecAL", "stopRecorderReq()");
        mRecStoppingTimestamp = System.currentTimeMillis();
        if (this.mUseNativeAndroidAudio) {
            releaseAudioStream();
        } else if (mIsRecStarting) {
            q.b("SimpleCodecAL", "stopRecorderReq(): stopping delayed since starting in progress!");
            mPendingRecStop = true;
            return;
        } else {
            mPendingRecStop = false;
            mIsRecStopping = true;
            if (this.m_sender != null) {
                this.m_sender.b();
            } else {
                mIsRecStopping = false;
            }
        }
        this.mUseNativeAndroidAudio = false;
    }

    public static void stopRecorderCnf(int i) {
        q.b("SimpleCodecAL", "stopRecorderCnf(): error = " + i);
        mIsRecStopping = false;
        stopRecorderCnfImpl(i);
        if (mRecParams != null) {
            q.b("SimpleCodecAL", "stopRecorderCnf(): pending start recorder!");
            getInstance().startRecorderReq(null, mRecParams.a, 0, mRecParams.c, 0, mRecParams.b, false, false, false, false, false);
        }
        mRecParams = null;
    }

    private void stopPlayerReq() {
        q.b("SimpleCodecAL", "stopPlayerReq() mUseNativeAndroidAudio = " + this.mUseNativeAndroidAudio);
        mPlayStoppingTimestamp = System.currentTimeMillis();
        if (mIsPlayStarting) {
            q.b("SimpleCodecAL", "stopPlayerReq(): stopping delayed since starting in progress!");
            mPendingPlayStop = true;
            return;
        }
        mPendingPlayStop = false;
        mIsPlayStopping = true;
        if (this.mUseNativeAndroidAudio) {
            stopPlayerCnf(0);
        } else if (this.m_receiver != null) {
            this.m_receiver.a();
        } else {
            mIsPlayStopping = false;
        }
    }

    public static void stopPlayerCnf(int i) {
        q.b("SimpleCodecAL", "stopPlayerCnf(): error = " + i);
        mIsPlayStopping = false;
        stopPlayerCnfImpl(i);
        if (mPlayParams != null) {
            q.b("SimpleCodecAL", "stopPlayerCnf(): pending start player!");
            getInstance().startPlayerReq(0, null, mPlayParams.a, 0, 0, 0, mPlayParams.b, false);
        }
        mPlayParams = null;
    }

    private void muteEncoder(boolean z, boolean z2) {
        q.b("SimpleCodecAL", "muteEncoder(): mute = " + z + " outOfBand = " + z2);
        if (this.mUseNativeAndroidAudio) {
            if (this.mNativeAudioIsMuted || !z) {
                if (this.mNativeAudioIsMuted && !z && this.mAudioGroup != null) {
                    this.mAudioGroup.setMode(3);
                    this.mNativeAudioIsMuted = false;
                }
            } else if (this.mAudioGroup != null) {
                this.mAudioGroup.setMode(1);
                this.mNativeAudioIsMuted = true;
            }
        } else if (this.m_sender != null && z != this.m_sender.a()) {
            this.m_sender.a(z2);
        }
    }

    private void encodeDTMF(int i) {
        if (this.mUseNativeAndroidAudio) {
            int i2;
            if (i >= 48 && i <= 57) {
                i2 = i - 48;
            } else if (i >= 65 && i <= 68) {
                i2 = (i - 65) + 12;
            } else if (i >= 97 && i <= 100) {
                i2 = (i - 65) + 12;
            } else if (i == 42) {
                i2 = 10;
            } else if (i == 35) {
                i2 = 11;
            } else {
                i2 = -1;
            }
            q.b("SimpleCodecAL", "NATIVE encodeDTMF(): characterCode = " + i + ", event = " + i2);
            if (i2 > -1) {
                this.mAudioGroup.sendDtmf(i2);
                return;
            }
            return;
        }
        q.b("SimpleCodecAL", "FGMSP encodeDTMF(): characterCode = " + i);
        if (this.m_sender != null) {
            this.m_sender.a(i);
        }
    }

    private void playDTMF(int i) {
        q.b("SimpleCodecAL", "FGMSP playDTMF(): keyTone = " + i);
        if (this.m_receiver != null) {
            this.m_receiver.a(i);
        }
    }

    private void rtpNegotiated(String str, int i, String str2, int i2) {
        q.b("SimpleCodecAL", "rtpNegotiated(): strRemoteAddr = " + str + " nRemotePort = " + i);
        q.b("SimpleCodecAL", "rtpNegotiated(): strLocalAddr = " + str2 + " nLocalPort = " + i2);
        this.mUseNativeAndroidAudio = false;
        try {
            this.mRemoteInetAddress = InetAddress.getAllByName(str)[0];
            this.mRemotePort = i;
            this.mLocalInetAddress = InetAddress.getAllByName(str2)[0];
            this.mLocalPort = i2;
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public synchronized void releaseAudioStream() {
        q.d("SimpleCodecAL", "releaseAudioStream() mUseNativeAndroidAudio = " + this.mUseNativeAndroidAudio);
        this.mUseNativeAndroidAudio = false;
        if (this.mAudioStream != null) {
            this.mAudioStream.join(null);
            q.d("SimpleCodecAL", "releaseAudioStream() mAudioStream.join(null)");
            if (!this.mAudioStream.isBusy()) {
                try {
                    this.mAudioStream.release();
                    q.d("SimpleCodecAL", "releaseAudioStream() mAudioStream.release()");
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                }
                this.mAudioStream = null;
            }
            stopRecorderCnf(0);
        }
    }

    private void setupNativeAudioUsageBasedOnMediaTypeAndAndroidVersion(int i) {
        if (FgVoIP.U().ai()) {
            this.mUseNativeAndroidAudio = false;
        } else if (!FgVoIP.U().A()) {
            this.mUseNativeAndroidAudio = false;
        } else if (VERSION.SDK_INT < 12) {
            this.mUseNativeAndroidAudio = false;
        } else if (i == 18 || i == 0 || i == 102) {
            this.mUseNativeAndroidAudio = false;
        } else {
            this.mUseNativeAndroidAudio = true;
        }
    }

    public void volumeChangeCnf(int i) {
        q.a("SimpleCodecAL", "Volume Change Confirmation received - volume changed to=" + i);
        Intent intent = new Intent("com.fgmicrotec.mobile.android.voip.VolumeChangeCnf");
        intent.putExtra("extra_volume_change_direction", i);
        FgVoIP.U().sendBroadcast(intent);
    }
}
