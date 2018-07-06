package com.spiritdsp.tsm;

import android.annotation.SuppressLint;
import android.app.Activity;
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
import android.provider.Settings.System;
import android.support.v4.internal.view.SupportMenu;
import android.support.v4.widget.ExploreByTouchHelper;
import android.util.Log;
import com.android.volley.toolbox.ImageRequest;
import com.spiritdsp.tsm.AudioThreadBase.IAudioTuner;
import com.spiritdsp.tsm.AudioThreadBase.INativeInterop;
import java.nio.ByteBuffer;

public class Audio {
    private static final int BT_HS_AUDIO_CAP_RATE = 8000;
    private static final int BT_HS_AUDIO_PB_RATE = 16000;
    static final int BUFFER_SIZE = 960;
    private static final boolean ENABLE_SR_SELECTION_WITH_MIN_BUFSIZE = true;
    private static final boolean bEnableDebugPrint = true;
    private static Context mContext;
    private static boolean mEnableAudioVolumeObservation = false;
    private static boolean mUpdateContextAM = true;
    private static boolean mUpdateContextObs = true;
    private boolean CaptureActive = false;
    private final ByteBuffer CaptureBuffer = ByteBuffer.allocateDirect(BUFFER_SIZE);
    private int CaptureSamplingRate = 0;
    private final INativeInterop NativeInterop = new INativeInterop() {
        private static final int MS_STATIC_CAPTURE_DELAY = 25;
        private int BufferedPlaybackSamples = 0;

        public void DeliverCaptureBuffer(int samplesNum) {
            int msDelay = 0;
            boolean locPBActive = Audio.this.PlaybackActive;
            int locPBSR = Audio.this.PlaybackSamplingRate;
            int locBufferedPBSamples = this.BufferedPlaybackSamples;
            if (locPBActive && locPBSR != 0) {
                msDelay = ((locBufferedPBSamples * ImageRequest.DEFAULT_IMAGE_TIMEOUT_MS) / locPBSR) + 25;
            }
            Audio.this.DeliverCaptureData(Audio.this.contextPtr, samplesNum, msDelay);
        }

        public void ObtainPlaybackBuffer(int samplesNum) {
            Audio.this.GetPlaybackData(Audio.this.contextPtr, samplesNum);
        }

        public void SetBufferedPlaybackSamples(int samplesNum) {
            this.BufferedPlaybackSamples = samplesNum;
        }
    };
    private boolean PlaybackActive = false;
    private final ByteBuffer PlaybackBuffer = ByteBuffer.allocateDirect(BUFFER_SIZE);
    private int PlaybackBufferLen = 0;
    private int PlaybackSamplingRate = 0;
    private AudioManager audioManager = null;
    private AudioRecord audioRecord = null;
    private AudioTrack audioTrack;
    private AudioCaptureThread captureThread;
    private final int contextPtr;
    private AcousticEchoCanceler mAec = null;
    private AutomaticGainControl mAgc = null;
    private int mAudioSource = -1;
    private boolean mHardwareAecEnable = true;
    private boolean mHardwareAecPresent = false;
    private NoiseSuppressor mNs = null;
    private int mOutputVolume = -1;
    private int mPlaybackSource;
    private final MinBufferSizeCache minBufferSizeCache = new MinBufferSizeCache();
    private AudioPlaybackThread playbackThread;

    private class MinBufferSizeCache {
        static final /* synthetic */ boolean $assertionsDisabled = (!Audio.class.desiredAssertionStatus());
        private final int[] SAMPLING_RATES = new int[]{44100, Audio.BT_HS_AUDIO_PB_RATE, 48000, Audio.BT_HS_AUDIO_CAP_RATE};
        private int currentPlaybackSource = 0;
        private int currentSamplingRate = 0;
        private final int length = this.SAMPLING_RATES.length;
        private final int[] minBufferSizes = new int[this.length];

        @SuppressLint({"InlinedApi"})
        MinBufferSizeCache() {
            for (int i = 0; i < this.length; i++) {
                this.minBufferSizes[i] = AudioTrack.getMinBufferSize(this.SAMPLING_RATES[i], 4, 2);
            }
        }

        int GetMinBufferSize(int rate) {
            for (int i = 0; i < this.length; i++) {
                if (rate == this.SAMPLING_RATES[i]) {
                    return this.minBufferSizes[i];
                }
            }
            if ($assertionsDisabled) {
                return -1;
            }
            throw new AssertionError();
        }

        int Rate(int index) {
            return this.SAMPLING_RATES[index];
        }

        int Length() {
            return this.length;
        }

        boolean NoChange(int samplingRate, int playbackSource) {
            return samplingRate == this.currentSamplingRate && playbackSource == this.currentPlaybackSource;
        }

        void Update(int samplingRate, int playbackSource) {
            this.currentSamplingRate = samplingRate;
            this.currentPlaybackSource = playbackSource;
            if (!$assertionsDisabled && !NoChange(samplingRate, playbackSource)) {
                throw new AssertionError();
            }
        }
    }

    private class VolumeContentObserver extends ContentObserver {
        VolumeContentObserver() {
            super(new Handler(Looper.getMainLooper()));
        }

        public boolean deliverSelfNotifications() {
            return false;
        }

        public void onChange(boolean selfChange) {
            Audio.this.OnVolumeChangedExternally();
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
        Logging.LogDebugPrint(true, "A: setContext: %h", activity);
        mContext = activity;
        mUpdateContextAM = true;
        AudioRouter.mUpdateContextBT = true;
        mUpdateContextObs = true;
    }

    static void enableAudioVolumeObservation(boolean enable) {
        mEnableAudioVolumeObservation = enable;
    }

    private AudioManager getAudioManager() {
        if ((mUpdateContextAM || this.audioManager == null) && mContext != null) {
            synchronized (this) {
                this.audioManager = (AudioManager) mContext.getSystemService("audio");
                mUpdateContextAM = false;
            }
        }
        if (this.audioManager == null) {
            Logging.LogDebugPrint(true, "getAudioManager: NULL!", new Object[0]);
        }
        return this.audioManager;
    }

    public Audio(int ContextPtr) {
        this.contextPtr = ContextPtr;
        setDefaultPlaybackSource();
    }

    private void wake_lock() {
        if (mContext != null) {
            WakeLocker.GetInstance().wake_lock(mContext);
        }
    }

    private void wake_unlock() {
        if (mContext != null) {
            WakeLocker.GetInstance().wake_unlock(mContext);
        }
    }

    private synchronized boolean InitPlayback() {
        boolean z = false;
        synchronized (this) {
            long ip_start = System.nanoTime();
            Logging.LogDebugPrint(true, "InitPlayback", new Object[0]);
            setDefaultPlaybackSource();
            this.PlaybackSamplingRate = SelectSamplingRate(true);
            if (this.PlaybackSamplingRate == 0) {
                Logging.LogDebugPrint(true, "SelectSamplingRate for playback failed", new Object[0]);
                Logging.LogNativePrintErr("SelectSamplingRate for playback failed", new Object[0]);
            } else {
                int[] bufsizeinfo = new int[2];
                if (InitAudioTrack(this.PlaybackSamplingRate, bufsizeinfo, false)) {
                    Logging.LogNativePrint("AudioTrack initialized for sr=" + this.PlaybackSamplingRate, new Object[0]);
                    Logging.LogNativePrint("AudioTrack bufSize=" + bufsizeinfo[0] + "bytes (" + (((bufsizeinfo[0] / 2) * ImageRequest.DEFAULT_IMAGE_TIMEOUT_MS) / this.PlaybackSamplingRate) + "ms)", new Object[0]);
                    Logging.LogNativePrint("AudioTrack minBufSize=" + bufsizeinfo[1] + "bytes (" + (((bufsizeinfo[1] / 2) * ImageRequest.DEFAULT_IMAGE_TIMEOUT_MS) / this.PlaybackSamplingRate) + "ms)", new Object[0]);
                    this.PlaybackBufferLen = bufsizeinfo[0] >> 1;
                    if (((long) (((double) (System.nanoTime() - ip_start)) / 1000000.0d)) > 100) {
                        Logging.LogDebugPrint(true, "InitPlayback: WARN: took too long (%d ms)", Long.valueOf((long) (((double) (System.nanoTime() - ip_start)) / 1000000.0d)));
                    }
                    z = true;
                } else {
                    Logging.LogDebugPrint(true, "InitAudioTrack(" + this.PlaybackSamplingRate + ") failed", new Object[0]);
                    Logging.LogNativePrintErr("InitAudioTrack(" + this.PlaybackSamplingRate + ") failed", new Object[0]);
                }
            }
        }
        return z;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized boolean StartPlayback_impl(boolean r13, boolean r14) {
        /*
        r12 = this;
        monitor-enter(r12);
        r6 = java.lang.System.nanoTime();	 Catch:{ all -> 0x00a4 }
        r8 = 1;
        r9 = "StartPlayback %s";
        r1 = 1;
        r10 = new java.lang.Object[r1];	 Catch:{ all -> 0x00a4 }
        r11 = 0;
        if (r14 == 0) goto L_0x001c;
    L_0x000e:
        r1 = "reset";
    L_0x0010:
        r10[r11] = r1;	 Catch:{ all -> 0x00a4 }
        com.spiritdsp.tsm.Logging.LogDebugPrint(r8, r9, r10);	 Catch:{ all -> 0x00a4 }
        r1 = r12.PlaybackActive;	 Catch:{ all -> 0x00a4 }
        if (r1 == 0) goto L_0x001f;
    L_0x0019:
        r1 = 1;
    L_0x001a:
        monitor-exit(r12);
        return r1;
    L_0x001c:
        r1 = "resume";
        goto L_0x0010;
    L_0x001f:
        r1 = r12.audioTrack;	 Catch:{ all -> 0x00a4 }
        if (r1 != 0) goto L_0x0025;
    L_0x0023:
        r1 = 0;
        goto L_0x001a;
    L_0x0025:
        r1 = r12.audioTrack;	 Catch:{ all -> 0x00a4 }
        r1 = r1.getState();	 Catch:{ all -> 0x00a4 }
        if (r1 != 0) goto L_0x002f;
    L_0x002d:
        r1 = 0;
        goto L_0x001a;
    L_0x002f:
        r1 = r12.audioTrack;	 Catch:{ IllegalStateException -> 0x0098 }
        r1.flush();	 Catch:{ IllegalStateException -> 0x0098 }
        r1 = r12.audioTrack;	 Catch:{ IllegalStateException -> 0x0098 }
        r1.play();	 Catch:{ IllegalStateException -> 0x0098 }
        if (r13 != 0) goto L_0x003f;
    L_0x003b:
        r1 = r12.playbackThread;	 Catch:{ all -> 0x00a4 }
        if (r1 != 0) goto L_0x0042;
    L_0x003f:
        r12.CreateAudioPlaybackThread();	 Catch:{ all -> 0x00a4 }
    L_0x0042:
        r1 = r12.playbackThread;	 Catch:{ all -> 0x00a4 }
        r8 = r12.audioTrack;	 Catch:{ all -> 0x00a4 }
        r9 = r12.PlaybackSamplingRate;	 Catch:{ all -> 0x00a4 }
        r10 = r12.PlaybackBufferLen;	 Catch:{ all -> 0x00a4 }
        r1.Init(r8, r9, r10);	 Catch:{ all -> 0x00a4 }
        r1 = r12.playbackThread;	 Catch:{ all -> 0x00a4 }
        r1.resume();	 Catch:{ all -> 0x00a4 }
        r1 = 1;
        r12.PlaybackActive = r1;	 Catch:{ all -> 0x00a4 }
        r1 = com.spiritdsp.tsm.TSM_impl.mIsHTCdesireSV;	 Catch:{ all -> 0x00a4 }
        if (r1 != 0) goto L_0x0069;
    L_0x0059:
        r1 = com.spiritdsp.tsm.TSM_impl.mIsHTCOneA9;	 Catch:{ all -> 0x00a4 }
        if (r1 != 0) goto L_0x0069;
    L_0x005d:
        r1 = com.spiritdsp.tsm.TSM_impl.mIsHTCOneM9;	 Catch:{ all -> 0x00a4 }
        if (r1 != 0) goto L_0x0069;
    L_0x0061:
        r1 = com.spiritdsp.tsm.TSM_impl.mIsHTCD628;	 Catch:{ all -> 0x00a4 }
        if (r1 != 0) goto L_0x0069;
    L_0x0065:
        r1 = com.spiritdsp.tsm.TSM_impl.mIsHTCD626;	 Catch:{ all -> 0x00a4 }
        if (r1 == 0) goto L_0x0072;
    L_0x0069:
        r1 = com.spiritdsp.tsm.WakeLocker.GetInstance();	 Catch:{ all -> 0x00a4 }
        r8 = mContext;	 Catch:{ all -> 0x00a4 }
        r1.wake_lock(r8);	 Catch:{ all -> 0x00a4 }
    L_0x0072:
        r4 = java.lang.System.nanoTime();	 Catch:{ all -> 0x00a4 }
        r8 = r4 - r6;
        r8 = (double) r8;	 Catch:{ all -> 0x00a4 }
        r10 = 4696837146684686336; // 0x412e848000000000 float:0.0 double:1000000.0;
        r8 = r8 / r10;
        r2 = (long) r8;	 Catch:{ all -> 0x00a4 }
        r8 = 100;
        r1 = (r2 > r8 ? 1 : (r2 == r8 ? 0 : -1));
        if (r1 <= 0) goto L_0x0096;
    L_0x0086:
        r1 = 1;
        r8 = "StartPlayback: WARN: took too long (%d ms)";
        r9 = 1;
        r9 = new java.lang.Object[r9];	 Catch:{ all -> 0x00a4 }
        r10 = 0;
        r11 = java.lang.Long.valueOf(r2);	 Catch:{ all -> 0x00a4 }
        r9[r10] = r11;	 Catch:{ all -> 0x00a4 }
        com.spiritdsp.tsm.Logging.LogDebugPrint(r1, r8, r9);	 Catch:{ all -> 0x00a4 }
    L_0x0096:
        r1 = 1;
        goto L_0x001a;
    L_0x0098:
        r0 = move-exception;
        r1 = "audioTrack.play() failed";
        r8 = 0;
        r8 = new java.lang.Object[r8];	 Catch:{ all -> 0x00a4 }
        com.spiritdsp.tsm.Logging.LogNativePrintErr(r1, r8);	 Catch:{ all -> 0x00a4 }
        r1 = 0;
        goto L_0x001a;
    L_0x00a4:
        r1 = move-exception;
        monitor-exit(r12);
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.spiritdsp.tsm.Audio.StartPlayback_impl(boolean, boolean):boolean");
    }

    private void CreateAudioPlaybackThread() {
        this.playbackThread = new AudioPlaybackThread(this.PlaybackBuffer);
        IAudioTuner tuner = null;
        if (TSM_impl.mIsMI_3W || TSM_impl.mIsMI_4) {
            tuner = new IAudioTuner() {
                public void Process(byte[] samplesBuffer, int sizeInBytes) {
                    int gain = 10000;
                    if (TSM_impl.mIsMI_4 && Audio.this.getAudioManager() != null) {
                        gain = Audio.this.getAudioManager().isWiredHeadsetOn() ? 5850 : Audio.this.GetSpeakerphone() ? 10922 : 9925;
                    }
                    Audio.AdjustAudioLevel(samplesBuffer, sizeInBytes, gain);
                }
            };
        }
        this.playbackThread.SetEnvironment(this.NativeInterop, tuner);
        this.playbackThread.start();
    }

    private boolean StartPlaybackSLES() {
        Logging.LogDebugPrint(true, "StartPlaybackSLES", new Object[0]);
        return true;
    }

    private boolean StartPlayback() {
        return StartPlayback_impl(true, true);
    }

    private synchronized boolean StopPlayback_impl(boolean doRestart, boolean doReset) {
        String str;
        long pb_start = System.nanoTime();
        String str2 = "StopPlayback %s:%s";
        Object[] objArr = new Object[2];
        objArr[0] = doRestart ? "restart" : "keep";
        if (doReset) {
            str = "reset";
        } else {
            str = "resume";
        }
        objArr[1] = str;
        Logging.LogDebugPrint(true, str2, objArr);
        WakeLocker.GetInstance().wake_unlock(mContext);
        if (this.playbackThread != null) {
            this.playbackThread.suspend();
            if (doRestart) {
                this.playbackThread.stop();
                this.playbackThread = null;
            }
        }
        if (this.audioTrack != null) {
            try {
                if (this.PlaybackActive && this.audioTrack.getState() != 0) {
                    this.audioTrack.stop();
                    this.audioTrack.flush();
                }
                if (doReset) {
                    this.audioTrack.release();
                    this.audioTrack = null;
                    System.gc();
                }
            } catch (IllegalStateException ex) {
                Logging.LogNativePrintErr("audioTrack.stop: exception " + ex.toString(), new Object[0]);
            }
        }
        if (mContext != null && (mContext instanceof Activity)) {
            ((Activity) mContext).setVolumeControlStream(ExploreByTouchHelper.INVALID_ID);
        }
        this.PlaybackActive = false;
        if (((long) (((double) (System.nanoTime() - pb_start)) / 1000000.0d)) > 100) {
            Logging.LogDebugPrint(true, "StopPlayback: WARN: took too long (%d ms)", Long.valueOf((long) (((double) (System.nanoTime() - pb_start)) / 1000000.0d)));
        }
        return true;
    }

    private boolean StopPlaybackSLES() {
        Logging.LogDebugPrint(true, "StopPlaybackSLES", new Object[0]);
        return true;
    }

    private boolean StopPlayback() {
        return StopPlayback_impl(true, true);
    }

    private void InitAudioLevelObserver() {
        if (mUpdateContextObs && mEnableAudioVolumeObservation && mContext != null) {
            mUpdateContextObs = false;
            mContext.getContentResolver().registerContentObserver(System.CONTENT_URI, true, new VolumeContentObserver());
        }
    }

    @SuppressLint({"InlinedApi"})
    private boolean InitAudioTrack(int samplingRate, int[] _bufsizeinfo, boolean bOnlyGetBufferSize) {
        long iat_start = System.nanoTime();
        String str = "InitAudioTrack: %s";
        Object[] objArr = new Object[1];
        objArr[0] = bOnlyGetBufferSize ? "bufsize" : "init";
        Logging.LogDebugPrint(true, str, objArr);
        InitAudioLevelObserver();
        int minBufferSize = this.minBufferSizeCache.GetMinBufferSize(samplingRate);
        if (minBufferSize <= 0) {
            return false;
        }
        if (_bufsizeinfo != null) {
            _bufsizeinfo[1] = minBufferSize;
        }
        int BufferSize = minBufferSize;
        if (((BufferSize / 2) * ImageRequest.DEFAULT_IMAGE_TIMEOUT_MS) / samplingRate < 100) {
            BufferSize = (samplingRate * 200) / ImageRequest.DEFAULT_IMAGE_TIMEOUT_MS;
        }
        if (TSM_impl.mIsKarbonnTAFone || (AudioRouter.GetRouter(true).UseBtAudioDevice() && TSM_impl.mIsNexus7)) {
            BufferSize = minBufferSize;
        }
        if (_bufsizeinfo != null) {
            _bufsizeinfo[0] = BufferSize;
        }
        if (bOnlyGetBufferSize) {
            return true;
        }
        if (this.audioTrack != null) {
            if (this.minBufferSizeCache.NoChange(samplingRate, this.mPlaybackSource)) {
                Logging.LogDebugPrint(true, "InitAudioTrack: no changes in cfg. using same instance", new Object[0]);
                return true;
            }
        }
        if (this.audioTrack != null) {
            this.audioTrack.release();
            this.audioTrack = null;
            System.gc();
        }
        Logging.LogDebugPrint(true, "InitAudioTrack:%d:%d:%d", Integer.valueOf(this.mPlaybackSource), Integer.valueOf(samplingRate), Integer.valueOf(BufferSize));
        if (mContext != null && (mContext instanceof Activity)) {
            ((Activity) mContext).setVolumeControlStream(this.mPlaybackSource);
        }
        try {
            this.audioTrack = new AudioTrack(this.mPlaybackSource, samplingRate, 4, 2, BufferSize, 1);
            this.minBufferSizeCache.Update(samplingRate, this.mPlaybackSource);
            if (this.audioTrack.getState() != 1) {
                this.audioTrack.release();
                this.audioTrack = null;
                System.gc();
                return false;
            }
            AudioRouter.GetRouter(true).AfterAudioTrackCreation(getAudioManager(), this.audioTrack);
            if (((long) (((double) (System.nanoTime() - iat_start)) / 1000000.0d)) > 100) {
                Logging.LogDebugPrint(true, "InitAudioTrack: WARN: took too long (%d ms)", Long.valueOf((long) (((double) (System.nanoTime() - iat_start)) / 1000000.0d)));
            }
            return true;
        } catch (IllegalArgumentException e) {
            Logging.LogNativePrintErr(String.format("failed to create AudioTrack instance: %s", new Object[]{e.getMessage()}), new Object[0]);
            return false;
        }
    }

    private synchronized boolean InitCapture() {
        boolean z = false;
        synchronized (this) {
            long ic_start = System.nanoTime();
            Logging.LogDebugPrint(true, "InitCapture", new Object[0]);
            if (this.mAudioSource == -1) {
                setDefaultAudioSource();
            }
            this.CaptureSamplingRate = SelectSamplingRate(false);
            if (this.CaptureSamplingRate == 0) {
                Logging.LogDebugPrint(true, "SelectSamplingRate for capture failed", new Object[0]);
                Logging.LogNativePrintErr("SelectSamplingRate for capture failed", new Object[0]);
            } else {
                int[] bufsizeinfo = new int[2];
                if (InitAudioRecord(this.CaptureSamplingRate, bufsizeinfo, false)) {
                    Logging.LogNativePrint("AudioRecord initialized for sr=" + this.CaptureSamplingRate, new Object[0]);
                    Logging.LogNativePrint("AudioRecord bufSize=" + bufsizeinfo[0] + "bytes (" + (((bufsizeinfo[0] / 2) * ImageRequest.DEFAULT_IMAGE_TIMEOUT_MS) / this.CaptureSamplingRate) + "ms)", new Object[0]);
                    Logging.LogNativePrint("AudioRecord minBufSize=" + bufsizeinfo[1] + "bytes (" + (((bufsizeinfo[1] / 2) * ImageRequest.DEFAULT_IMAGE_TIMEOUT_MS) / this.CaptureSamplingRate) + "ms)", new Object[0]);
                    if (((long) (((double) (System.nanoTime() - ic_start)) / 1000000.0d)) > 100) {
                        Logging.LogDebugPrint(true, "InitCapture: WARN: took too long (%d ms)", Long.valueOf((long) (((double) (System.nanoTime() - ic_start)) / 1000000.0d)));
                    }
                    z = true;
                } else {
                    Logging.LogDebugPrint(true, "InitAudioRecord(" + this.CaptureSamplingRate + ") failed", new Object[0]);
                    Logging.LogNativePrintErr("InitAudioRecord(" + this.CaptureSamplingRate + ") failed", new Object[0]);
                }
            }
        }
        return z;
    }

    private synchronized boolean StartCapture_impl(boolean doRestart, boolean doReset) {
        boolean z;
        long cp_start = System.nanoTime();
        String str = "StartCapture %s";
        Object[] objArr = new Object[1];
        objArr[0] = doReset ? "reset" : "resume";
        Logging.LogDebugPrint(true, str, objArr);
        if (this.CaptureActive) {
            z = true;
        } else if (this.audioRecord == null) {
            z = false;
        } else if (this.audioRecord.getState() == 0) {
            z = false;
        } else {
            if (doRestart || this.captureThread == null) {
                CreateAudioCaptureThread();
            }
            this.captureThread.Init(this.audioRecord, this.CaptureSamplingRate);
            this.captureThread.resume();
            try {
                this.audioRecord.startRecording();
                this.CaptureActive = AudioRouter.GetRouter(true).StartCaptureBT(mContext, getAudioManager());
                if (((long) (((double) (System.nanoTime() - cp_start)) / 1000000.0d)) > 100) {
                    Logging.LogDebugPrint(true, "StartCapture: WARN: took too long (%d ms)", Long.valueOf((long) (((double) (System.nanoTime() - cp_start)) / 1000000.0d)));
                }
                z = this.CaptureActive;
            } catch (IllegalStateException e) {
                Logging.LogNativePrintErr("audioRecord.startRecording() failed", new Object[0]);
                z = false;
            }
        }
        return z;
    }

    private void CreateAudioCaptureThread() {
        this.captureThread = new AudioCaptureThread(this.CaptureBuffer);
        IAudioTuner tuner = null;
        if (TSM_impl.mIsMI_4 || TSM_impl.mIsMI_3W) {
            final int gain = TSM_impl.mIsMI_4 ? 28500 : 12000;
            tuner = new IAudioTuner() {
                public void Process(byte[] samplesBuffer, int sizeInBytes) {
                    Audio.AdjustAudioLevel(samplesBuffer, sizeInBytes, gain);
                }
            };
        }
        this.captureThread.SetEnvironment(this.NativeInterop, tuner);
        this.captureThread.start();
    }

    private boolean StartCaptureSLES() {
        Logging.LogDebugPrint(true, "StartCaptureSLES", new Object[0]);
        if (!this.CaptureActive) {
            boolean StartCaptureBT = AudioRouter.GetRouter(false).StartCaptureBT(mContext, getAudioManager());
            this.CaptureActive = StartCaptureBT;
            if (!StartCaptureBT) {
                return false;
            }
        }
        return true;
    }

    private boolean StartCapture() {
        return StartCapture_impl(true, true);
    }

    @SuppressLint({"NewApi"})
    private void StopHardwareAec() {
        if (canUseHwAec() && this.mHardwareAecEnable && this.mHardwareAecPresent) {
            synchronized (this) {
                Logging.LogDebugPrint(true, "StopEC", new Object[0]);
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

    private synchronized boolean StopCapture_impl(boolean doRestart, boolean doReset) {
        long cp_start = System.nanoTime();
        String str = "StopCapture %s:%s";
        Object[] objArr = new Object[2];
        objArr[0] = doRestart ? "restart" : "keep";
        objArr[1] = doReset ? "reset" : "resume";
        Logging.LogDebugPrint(true, str, objArr);
        if (this.CaptureActive) {
            if (this.captureThread != null) {
                this.captureThread.suspend();
                if (doRestart) {
                    this.captureThread.stop();
                    this.captureThread = null;
                }
            }
            StopCaptureBT(true);
            if (doReset) {
                StopHardwareAec();
            }
            if (this.audioRecord != null) {
                try {
                    if (this.audioRecord.getState() != 0) {
                        if (this.CaptureActive) {
                            this.audioRecord.stop();
                        }
                        if (doReset) {
                            this.audioRecord.release();
                            this.audioRecord = null;
                            System.gc();
                        }
                    }
                } catch (IllegalStateException ex) {
                    Logging.LogNativePrintErr("audioRecord.stop: exception " + ex.toString(), new Object[0]);
                }
            }
            this.CaptureActive = false;
            if (((long) (((double) (System.nanoTime() - cp_start)) / 1000000.0d)) > 100) {
                Logging.LogDebugPrint(true, "StopCapture: WARN: took too long (%d ms)", Long.valueOf((long) (((double) (System.nanoTime() - cp_start)) / 1000000.0d)));
            }
        }
        return true;
    }

    private boolean StopCaptureSLES() {
        Logging.LogDebugPrint(true, "StopCaptureSLES", new Object[0]);
        if (this.CaptureActive) {
            StopCaptureBT(false);
            this.CaptureActive = false;
        }
        return true;
    }

    private synchronized boolean StopCaptureBT(boolean isJavaIo) {
        boolean z;
        z = !this.CaptureActive || AudioRouter.GetRouter(isJavaIo).StopCaptureBT(mContext, getAudioManager());
        return z;
    }

    private boolean StopCapture() {
        return StopCapture_impl(true, true);
    }

    private static boolean canUseHwAec() {
        return TSM_impl.mIsGalaxyS3 || TSM_impl.mIsGalaxyS4 || TSM_impl.mIsSGS5 || TSM_impl.mIsGalaxyNote || TSM_impl.mIsNexus7 || TSM_impl.mIsNexus5 || TSM_impl.mIsNexus5X || TSM_impl.mIsNexus6 || TSM_impl.mIsNexus6P || TSM_impl.mIsTele2Mini;
    }

    private static boolean enableSLES() {
        return (canUseHwAec() || TSM_impl.mIsSGS6 || TSM_impl.mIsSGS8P || TSM_impl.mIsHTCOneS || TSM_impl.mIsHTCOne || TSM_impl.mIsHTCD628 || TSM_impl.mIsHTCD626 || TSM_impl.mIsHTCOneA9 || TSM_impl.mIsHTCOneM7 || TSM_impl.mIsHTCOneM8 || TSM_impl.mIsHTCOneM9 || TSM_impl.mIsHTCOneM10 || TSM_impl.mIsAndromax || TSM_impl.mIsAlcatel7045A || TSM_impl.mIsAlcatel7045Y || TSM_impl.mIsAlcatelPixi35 || TSM_impl.mIsSonyEricssonXArc || TSM_impl.mIsSonyXperiaS || TSM_impl.mIsSonyEricssonXMiro || TSM_impl.mIsSonyXperiaJ || TSM_impl.mIsSonyXperiaP || TSM_impl.mIsSonyXperiaZU || TSM_impl.mIsSonyXperiaZ1 || TSM_impl.mIsSonyXperiaZ3 || TSM_impl.mIsSonyXperiaZ5 || TSM_impl.mIsSonyXperiaM2 || TSM_impl.mIsTele2Midi) ? false : true;
    }

    @SuppressLint({"NewApi"})
    private boolean InitAudioRecord(int samplingRate, int[] _bufsizeinfo, boolean bOnlyGetBufferSize) {
        long iar_start = System.nanoTime();
        String str = "InitAudioRecord: %s";
        Object[] objArr = new Object[1];
        objArr[0] = bOnlyGetBufferSize ? "bufsize" : "init";
        Logging.LogDebugPrint(true, str, objArr);
        if (!(bOnlyGetBufferSize || this.audioRecord == null)) {
            this.audioRecord.release();
            this.audioRecord = null;
            System.gc();
        }
        if (!bOnlyGetBufferSize) {
            StopHardwareAec();
        }
        int minBufferSize = AudioRecord.getMinBufferSize(samplingRate, 16, 2);
        if (minBufferSize <= 0) {
            Logging.LogDebugPrint(true, "AudioRecord - cannot get minBufferSize", new Object[0]);
            Logging.LogNativePrintErr("AudioRecord - cannot get minBufferSize", new Object[0]);
            return false;
        }
        if (_bufsizeinfo != null) {
            _bufsizeinfo[1] = minBufferSize;
        }
        int BufferSize = minBufferSize * 100;
        if (TSM_impl.mIsKarbonnTAFone) {
            BufferSize = minBufferSize;
        }
        if (_bufsizeinfo != null) {
            _bufsizeinfo[0] = BufferSize;
        }
        if (!bOnlyGetBufferSize) {
            AudioRouter.GetRouter(true).BeforeAudioRecordCreation(getAudioManager());
            Logging.LogDebugPrint(true, "InitAudioRecord:%d:%d:%d", Integer.valueOf(this.mAudioSource), Integer.valueOf(samplingRate), Integer.valueOf(BufferSize));
            try {
                this.audioRecord = new AudioRecord(this.mAudioSource, samplingRate, 16, 2, BufferSize);
                if (this.audioRecord.getState() != 1) {
                    Logging.LogDebugPrint(true, "AudioRecord - wrong state - ", Integer.valueOf(this.audioRecord.getState()));
                    Logging.LogNativePrintErr("AudioRecord - wrong state - " + this.audioRecord.getState(), new Object[0]);
                    this.audioRecord.release();
                    this.audioRecord = null;
                    System.gc();
                    return false;
                }
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
                        if (!(TSM_impl.mIsAndromaxQ || TSM_impl.mIsAndromaxE3 || TSM_impl.mIsAndromaxR || TSM_impl.mIsAndromaxE2P || TSM_impl.mIsAndromaxR2 || TSM_impl.mIsNexus5X || TSM_impl.mIsNexus6P || TSM_impl.mIsHTCOneM8)) {
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
                        if (!(TSM_impl.mIsHTCD628 || TSM_impl.mIsHTCD626 || TSM_impl.mIsAndromaxQ || TSM_impl.mIsAndromaxE3 || TSM_impl.mIsAndromaxR || TSM_impl.mIsAndromaxE2P || TSM_impl.mIsAndromaxR2 || TSM_impl.mIsNexus5X || TSM_impl.mIsNexus6P || TSM_impl.mIsHTCOneM8)) {
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
                AudioRouter.GetRouter(true).AfterAudioRecordCreation(getAudioManager(), this.audioRecord);
            } catch (Exception e4) {
                e4.printStackTrace();
                Logging.LogDebugPrint(true, "AudioRecord creation ERROR", new Object[0]);
                Logging.LogNativePrintErr(String.format("failed to create AudioRecord instance: %s", new Object[]{e4.getMessage()}), new Object[0]);
                return false;
            }
        }
        if (((long) (((double) (System.nanoTime() - iar_start)) / 1000000.0d)) > 100) {
            Logging.LogDebugPrint(true, "InitAudioRecord: WARN: took too long (%d ms)", Long.valueOf((long) (((double) (System.nanoTime() - iar_start)) / 1000000.0d)));
        }
        return true;
    }

    @SuppressLint({"InlinedApi", "NewApi"})
    private synchronized boolean EnableHardwareAec(int enable) {
        boolean z = true;
        boolean z2 = false;
        synchronized (this) {
            if (canUseHwAec()) {
                boolean benable;
                Logging.LogDebugPrint(true, "EnableEC %d", Integer.valueOf(enable));
                if (enable != 0) {
                    benable = true;
                } else {
                    benable = false;
                }
                Logging.LogNativePrint("AudioRecord ena" + enable, new Object[0]);
                if (this.mAec == null) {
                    this.mHardwareAecEnable = benable;
                    z2 = true;
                } else if (VERSION.SDK_INT >= 16) {
                    Logging.LogNativePrint("AudioRecord ena7", new Object[0]);
                    if (this.mAec.setEnabled(benable) != 0) {
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

    private boolean SetAudioSource(int newSource) {
        Logging.LogNativePrint("SetAudioSource: newSource  = " + String.valueOf(newSource), new Object[0]);
        if (this.mAudioSource != newSource) {
            this.mAudioSource = newSource;
            Logging.LogNativePrint("AudioSource = " + String.valueOf(this.mAudioSource), new Object[0]);
        }
        return true;
    }

    private int SelectSamplingRate(boolean bPlayback) {
        if (AudioRouter.GetRouter(true).UseBtAudioDevice()) {
            if (bPlayback) {
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
            if ((TSM_impl.mIsSamsungGalaxyAce || TSM_impl.mIsSonyXperiaP) && !bPlayback) {
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
            int length = this.minBufferSizeCache.Length();
            int[] bufSizes = new int[length];
            for (i = 0; i < length; i++) {
                boolean res;
                int sr = this.minBufferSizeCache.Rate(i);
                int[] info = new int[2];
                if (bPlayback) {
                    res = InitAudioTrack(sr, info, true);
                } else {
                    res = InitAudioRecord(sr, info, true);
                }
                if (res) {
                    bufSizes[i] = info[1];
                } else {
                    bufSizes[i] = 0;
                }
            }
            String type = bPlayback ? "Playback" : "Capture";
            int minSizeMs = Integer.MAX_VALUE;
            int minSRIdx = -1;
            for (i = 0; i < length; i++) {
                int rate = this.minBufferSizeCache.Rate(i);
                int curBufSizeMs = ((bufSizes[i] / 2) * ImageRequest.DEFAULT_IMAGE_TIMEOUT_MS) / rate;
                Logging.LogNativePrint("SelectSamplingRate: " + type + "[" + rate + "]=" + bufSizes[i] + " (" + curBufSizeMs + "ms)", new Object[0]);
                if (curBufSizeMs != 0 && curBufSizeMs < minSizeMs && minSizeMs - curBufSizeMs > 20) {
                    minSizeMs = curBufSizeMs;
                    minSRIdx = i;
                }
            }
            if (minSRIdx != -1) {
                return this.minBufferSizeCache.Rate(minSRIdx);
            }
            return 0;
        }
    }

    private int GetAudioPath() {
        return AudioRouter.GetAudioPath();
    }

    @SuppressLint({"NewApi"})
    private boolean IsSLESModeEnabled() {
        if (enableSLES()) {
            return true;
        }
        Logging.LogDebugPrint(true, "CheckPresentEC", new Object[0]);
        if (VERSION.SDK_INT < 16 || AcousticEchoCanceler.isAvailable()) {
            return false;
        }
        return true;
    }

    @SuppressLint({"NewApi"})
    private int GetPrppertyOutputSampleRate() {
        Logging.LogDebugPrint(true, "GetPrppertyOutputSampleRate", new Object[0]);
        if (VERSION.SDK_INT < 17 || getAudioManager() == null) {
            return -1;
        }
        int sr = Integer.parseInt(getAudioManager().getProperty("android.media.property.OUTPUT_SAMPLE_RATE"));
        Log.d("AudioManager", "PROPERTY_OUTPUT_SAMPLE_RATE=" + sr);
        return sr;
    }

    @SuppressLint({"NewApi"})
    private int GetPrppertyOutputFramesPerBuffer() {
        Logging.LogDebugPrint(true, "GetPrppertyOutputFramesPerBuffer", new Object[0]);
        if (VERSION.SDK_INT < 17 || getAudioManager() == null) {
            return -1;
        }
        int bufsize = Integer.parseInt(getAudioManager().getProperty("android.media.property.OUTPUT_FRAMES_PER_BUFFER"));
        Log.d("AudioManager", "PROPERTY_OUTPUT_FRAMES_PER_BUFFER=" + bufsize);
        return bufsize;
    }

    private int SetAudioPathSLES(int audioPath) {
        return SetAudioPath_impl(audioPath, false);
    }

    private int SetAudioPath(int audioPath) {
        return SetAudioPath_impl(audioPath, true);
    }

    private synchronized int SetAudioPath_impl(int audioPath, boolean isJavaIO) {
        int i;
        long ap_switch_start = System.nanoTime();
        if (audioPath == AudioRouter.GetAudioPath()) {
            i = 0;
        } else {
            String str;
            this.mOutputVolume = -1;
            String str2 = "SetAudioPath_impl: Mode=%s Path:%d->%d PlaybackActive=%s CaptureActive=%s OutputVolume=%d AudioManager=%h Context=%h spk=%s";
            Object[] objArr = new Object[9];
            objArr[0] = isJavaIO ? "Java" : "SLES";
            objArr[1] = Integer.valueOf(AudioRouter.GetAudioPath());
            objArr[2] = Integer.valueOf(audioPath);
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
            Logging.LogDebugPrint(true, str2, objArr);
            boolean needSIOreset = false;
            if (AudioRouter.IsValidAudioPath(audioPath)) {
                IRouter audioRouter = AudioRouter.GetRouter(isJavaIO);
                if (audioRouter.IsAudioResetRequired(audioPath) && (this.PlaybackActive || this.CaptureActive)) {
                    needSIOreset = true;
                }
                if (null != null || needSIOreset) {
                    if (isJavaIO) {
                        StopCapture_impl(false, needSIOreset);
                        StopPlayback_impl(false, needSIOreset);
                    } else {
                        StopCaptureSLES();
                        StopPlaybackSLES();
                    }
                }
                Log.d("SwitchAudioPath", "" + audioPath);
                audioRouter.SwitchAudioPath(mContext, getAudioManager(), audioPath, this.audioTrack, this.audioRecord);
                if (null != null || needSIOreset) {
                    if (isJavaIO) {
                        if (needSIOreset && (!InitPlayback() || !InitCapture() || !SetPlaybackSamplingRate(this.contextPtr, this.PlaybackSamplingRate, 1) || !SetCaptureSamplingRate(this.contextPtr, this.CaptureSamplingRate, 1))) {
                            i = -5;
                        } else if (!(StartPlayback_impl(false, needSIOreset) && StartCapture_impl(false, needSIOreset))) {
                            i = -5;
                        }
                    } else if (!(StartPlaybackSLES() && StartCaptureSLES())) {
                        i = -5;
                    }
                }
                Logging.LogDebugPrint(true, "SetAudioPath_impl: current volume %d", Integer.valueOf(GetOutputVolume()));
                if (((long) (((double) (System.nanoTime() - ap_switch_start)) / 1000000.0d)) > 100) {
                    Logging.LogDebugPrint(true, "SetAudioPath_impl: WARN: switch took too long (%d ms)", Long.valueOf((long) (((double) (System.nanoTime() - ap_switch_start)) / 1000000.0d)));
                }
                i = 0;
            } else {
                Logging.LogDebugPrint(true, "SetAudioPath_impl: Invalid mode", new Object[0]);
                i = -2;
            }
        }
        return i;
    }

    private boolean GetSpeakerphone() {
        if (getAudioManager() == null) {
            return false;
        }
        return getAudioManager().isSpeakerphoneOn();
    }

    private synchronized void SetOutputVolume(int volume) {
        if (getAudioManager() == null) {
            Logging.LogDebugPrint(true, "SetOutputVolume: AudioManager is NULL!", new Object[0]);
        } else {
            if (TSM_impl.mIsSonyEricssonXArc && volume > 32768) {
                volume = 32768;
            }
            if (TSM_impl.mIsMilestone1 && volume > 32768) {
                volume = 32768;
            }
            if (AudioRouter.GetAudioPath() == 1) {
                if (TSM_impl.mIsGalaxyS4 && volume > 32768) {
                    volume = 32768;
                }
                if (TSM_impl.mIsGalaxyS2Plus && volume > 32768) {
                    volume = 32768;
                }
            }
            this.mOutputVolume = volume;
            Logging.LogDebugPrint(true, "SetOutputVolume: %d", Integer.valueOf(volume));
            Logging.LogDebugPrint(true, "SetOutputVolume internal: %d", Integer.valueOf((getAudioManager().getStreamMaxVolume(this.mPlaybackSource) * volume) / SupportMenu.USER_MASK));
            getAudioManager().setStreamVolume(this.mPlaybackSource, theVolume, 0);
        }
    }

    private int getSystemPlaybackVolume() {
        if (getAudioManager() == null) {
            return SupportMenu.USER_MASK;
        }
        Logging.LogDebugPrint(true, "getSystemPlaybackVolume: %d", Integer.valueOf(getAudioManager().getStreamVolume(this.mPlaybackSource)));
        return (SupportMenu.USER_MASK * getAudioManager().getStreamVolume(this.mPlaybackSource)) / getAudioManager().getStreamMaxVolume(this.mPlaybackSource);
    }

    private int GetOutputVolume() {
        if (this.mOutputVolume >= 0) {
            return this.mOutputVolume;
        }
        int theVolume = getSystemPlaybackVolume();
        Logging.LogDebugPrint(true, "GetOutputVolume: %d", Integer.valueOf(theVolume));
        this.mOutputVolume = theVolume;
        return theVolume;
    }

    private void OnVolumeChangedExternally() {
        this.mOutputVolume = getSystemPlaybackVolume();
        Logging.LogDebugPrint(true, "OnVolumeChangedExternally: %d", Integer.valueOf(this.mOutputVolume));
    }

    private static void AdjustAudioLevel(byte[] buffer, int bytesNum, int gain) {
        for (int i = 0; i < bytesNum; i += 2) {
            short tmp = (short) ((((short) ((buffer[i] & 255) | (buffer[i + 1] << 8))) * gain) / 32768);
            buffer[i] = (byte) (tmp & 255);
            buffer[i + 1] = (byte) (tmp >> 8);
        }
    }
}
