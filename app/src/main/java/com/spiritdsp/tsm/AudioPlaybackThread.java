package com.spiritdsp.tsm;

import android.media.AudioTrack;
import java.nio.ByteBuffer;
import java.util.Arrays;

class AudioPlaybackThread extends AudioThreadBase {
    private static final boolean ENABLE_DEBUG_PRINT = true;
    private static final int TIMEOUT_DURATION_MS = 3;
    private int BufferedPlaybackSamples;
    private final ByteBuffer PlaybackBuffer;
    private int PlaybackBufferLen;
    private int PlaybackPos;
    private AudioTrack mAudioTrack;
    private volatile int mCurPos;
    private volatile boolean mForceWrite;
    private volatile int mNumBytes;
    private volatile int mNumSamples;
    private volatile int mSequentialSleepsCount;

    AudioPlaybackThread(ByteBuffer buffer) {
        this.mSequentialSleepsCount = 0;
        this.mForceWrite = false;
        this.BufferedPlaybackSamples = 0;
        this.PlaybackBufferLen = 0;
        this.PlaybackPos = 0;
        this.mAudioTrack = null;
        this.threadName = "AudioPlaybackThread";
        this.PlaybackBuffer = buffer;
    }

    void Init(AudioTrack audioTrack, int playbackSamplingRate, int playbackBufferLen) {
        this.mAudioTrack = audioTrack;
        this.mNumSamples = playbackSamplingRate / 100;
        this.LoggingRateInSamples = playbackSamplingRate;
        this.PlaybackBufferLen = playbackBufferLen;
    }

    protected void setup() {
        if (this.aBuffer == null) {
            this.aBuffer = new byte[960];
        } else {
            Arrays.fill(this.aBuffer, (byte) 0);
        }
        this.BufferedPlaybackSamples = 0;
        this.mNumBytes = this.mNumSamples * 2;
        this.BufferedPlaybackSamples = 0;
        this.PlaybackPos = 0;
        this.mSequentialSleepsCount = 0;
        this.mForceWrite = false;
        this.mCurPos = 0;
        int k = 0;
        while (k < this.PlaybackBufferLen * 100 && !this.shouldExit) {
            int written = this.mAudioTrack.write(this.aBuffer, 0, 100);
            this.BufferedPlaybackSamples += written >> 1;
            k += written >> 1;
            this.mCurPos = this.mAudioTrack.getPlaybackHeadPosition();
            if (this.mCurPos < this.PlaybackPos) {
                this.PlaybackPos = 0;
            }
            this.BufferedPlaybackSamples -= this.mCurPos - this.PlaybackPos;
            this.PlaybackPos = this.mCurPos;
            if (this.mCurPos > 0) {
                break;
            } else if (k >= this.PlaybackBufferLen) {
                try {
                    Thread.sleep(3);
                } catch (InterruptedException e) {
                }
            }
        }
        this.NativeInterop.SetBufferedPlaybackSamples(this.BufferedPlaybackSamples);
        if (this.mCurPos == 0) {
            Logging.LogDebugPrint(true, "PlaybackThread: AudioTrack doesn't run!", new Object[0]);
        }
    }

    protected void iteration() throws InterruptedException {
        this.mCurPos = this.mAudioTrack.getPlaybackHeadPosition();
        if (this.mCurPos < this.PlaybackPos) {
            this.PlaybackPos = 0;
        }
        this.BufferedPlaybackSamples -= this.mCurPos - this.PlaybackPos;
        this.PlaybackPos = this.mCurPos;
        if (this.BufferedPlaybackSamples < this.PlaybackBufferLen - this.mNumSamples || this.mForceWrite) {
            this.NativeInterop.ObtainPlaybackBuffer(this.mNumSamples);
            this.PlaybackBuffer.rewind();
            this.PlaybackBuffer.get(this.aBuffer, 0, this.mNumBytes);
            if (this.AudioTuner != null) {
                this.AudioTuner.Process(this.aBuffer, this.mNumBytes);
            }
            LogAudioLevel(this.mNumBytes);
            int written = this.mAudioTrack.write(this.aBuffer, 0, this.mNumBytes);
            if (written != this.mNumBytes) {
                Logging.LogNativePrint("SIO: Warning! PlaybackThread: audioTrack.write() returned " + written + " instead of " + this.mNumBytes, new Object[0]);
            }
            if (written > 0) {
                this.BufferedPlaybackSamples += written >> 1;
            }
            this.mCurPos = this.mAudioTrack.getPlaybackHeadPosition();
            if (this.mCurPos < this.PlaybackPos) {
                this.PlaybackPos = 0;
            }
            if (!this.mForceWrite) {
                this.BufferedPlaybackSamples -= this.mCurPos - this.PlaybackPos;
            }
            this.NativeInterop.SetBufferedPlaybackSamples(this.BufferedPlaybackSamples);
            this.PlaybackPos = this.mCurPos;
            this.mSequentialSleepsCount = 0;
            this.mForceWrite = false;
            return;
        }
        Thread.sleep(3);
        this.mSequentialSleepsCount++;
        if (this.mSequentialSleepsCount > 300) {
            this.mForceWrite = true;
        }
    }
}
