package com.spiritdsp.tsm;

import android.media.AudioRecord;
import java.nio.ByteBuffer;
import java.util.Arrays;

class AudioCaptureThread extends AudioThreadBase {
    private static final boolean ENABLE_DEBUG_PRINT = true;
    private final ByteBuffer CaptureBuffer;
    private AudioRecord mAudioRecord;
    private int mNumBytes;

    AudioCaptureThread(ByteBuffer buffer) {
        this.mAudioRecord = null;
        this.threadName = "AudioCaptureThread";
        this.CaptureBuffer = buffer;
    }

    void Init(AudioRecord audioRecord, int captureSamplingRate) {
        this.mAudioRecord = audioRecord;
        this.mNumBytes = (captureSamplingRate / 100) * 2;
        this.LoggingRateInSamples = captureSamplingRate;
    }

    protected void setup() {
        if (this.aBuffer == null) {
            this.aBuffer = new byte[960];
        } else {
            Arrays.fill(this.aBuffer, (byte) 0);
        }
    }

    protected void iteration() throws InterruptedException {
        int read = this.mAudioRecord.read(this.aBuffer, 0, this.mNumBytes);
        if (read != this.mNumBytes) {
            Logging.LogNativePrint("Warning! CaptureThread: audioRecord.read() returned " + read + " instead of " + this.mNumBytes, new Object[0]);
        }
        if (read <= 0) {
            Thread.sleep(5);
            return;
        }
        if (this.AudioTuner != null) {
            this.AudioTuner.Process(this.aBuffer, this.mNumBytes);
        }
        LogAudioLevel(read);
        this.CaptureBuffer.rewind();
        this.CaptureBuffer.put(this.aBuffer, 0, read);
        this.NativeInterop.DeliverCaptureBuffer(read >> 1);
    }
}
