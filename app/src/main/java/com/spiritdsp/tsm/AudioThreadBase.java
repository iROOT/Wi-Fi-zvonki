package com.spiritdsp.tsm;

import android.os.Process;

abstract class AudioThreadBase implements Runnable {
    private static final boolean bEnableDebugPrint = true;
    IAudioTuner AudioTuner;
    private volatile int LoggingCallsCounter = 0;
    int LoggingRateInSamples = 0;
    private volatile int LoggingSamplesCounter = 0;
    private volatile double LoggingSamplesSum = 0.0d;
    INativeInterop NativeInterop;
    byte[] aBuffer;
    private volatile boolean onPause = false;
    private volatile boolean paused = false;
    volatile boolean shouldExit = false;
    private Thread thread;
    String threadName;

    public interface IAudioTuner {
        void Process(byte[] bArr, int i);
    }

    public interface INativeInterop {
        void DeliverCaptureBuffer(int i);

        void ObtainPlaybackBuffer(int i);

        void SetBufferedPlaybackSamples(int i);
    }

    protected abstract void iteration() throws InterruptedException;

    protected abstract void setup();

    AudioThreadBase() {
    }

    public void start() {
        if (this.thread == null) {
            this.thread = new Thread(this, this.threadName);
            this.thread.setPriority(8);
            this.thread.start();
        }
    }

    public void stop() {
        if (this.thread != null) {
            this.shouldExit = true;
            try {
                this.thread.join(1000);
            } catch (InterruptedException e) {
            }
            this.thread = null;
        }
    }

    public void suspend() {
        if (!this.onPause) {
            this.onPause = true;
            try {
                synchronized (this) {
                    while (!this.paused) {
                        wait(500);
                    }
                }
            } catch (InterruptedException e) {
            }
        }
    }

    public void resume() {
        if (this.onPause) {
            setup();
            this.onPause = false;
            this.paused = false;
        }
    }

    public void run() {
        Logging.LogDebugPrint(true, this.threadName + " started", new Object[0]);
        try {
            Process.setThreadPriority(-19);
        } catch (Exception e) {
            Logging.LogNativePrintErr("setThreadPriority() failed: " + e.toString(), new Object[0]);
        }
        long setup_start = System.nanoTime();
        setup();
        if (((long) (((double) (System.nanoTime() - setup_start)) / 1000000.0d)) > 10) {
            Logging.LogDebugPrint(true, String.format("%s:setup:WARN: took too long (%d ms)", new Object[]{this.threadName, Long.valueOf((long) (((double) (System.nanoTime() - setup_start)) / 1000000.0d))}), new Object[0]);
        }
        while (!this.shouldExit) {
            try {
                if (this.onPause) {
                    if (!this.paused) {
                        this.paused = true;
                        synchronized (this) {
                            notifyAll();
                        }
                    }
                    Thread.sleep(15);
                } else {
                    long iteration_start = System.nanoTime();
                    iteration();
                    if (((long) (((double) (System.nanoTime() - iteration_start)) / 1000000.0d)) > 20) {
                        Logging.LogDebugPrint(true, String.format("%s:iteration:WARN: took too long (%d ms)", new Object[]{this.threadName, Long.valueOf((long) (((double) (System.nanoTime() - iteration_start)) / 1000000.0d))}), new Object[0]);
                    }
                }
            } catch (Throwable ex) {
                Logging.LogNativePrintErr(this.threadName + ": exception: " + ex.toString(), new Object[0]);
            }
        }
        Logging.LogDebugPrint(true, this.threadName + " exit", new Object[0]);
    }

    void LogAudioLevel(int bytesNum) {
        long lal_start = System.nanoTime();
        for (int i = 0; i < bytesNum; i += 2) {
            short sample = (short) ((this.aBuffer[i] & 255) | (this.aBuffer[i + 1] << 8));
            if (sample < (short) 0) {
                this.LoggingSamplesSum -= (double) sample;
            } else {
                this.LoggingSamplesSum += (double) sample;
            }
        }
        this.LoggingSamplesCounter += bytesNum / 2;
        this.LoggingCallsCounter++;
        if (this.LoggingSamplesCounter >= this.LoggingRateInSamples) {
            Logging.LogDebugPrint(true, this.threadName + " audio level avg=%d samples=%d calls=%d", Integer.valueOf((int) (this.LoggingSamplesSum / ((double) this.LoggingSamplesCounter))), Integer.valueOf(this.LoggingSamplesCounter), Integer.valueOf(this.LoggingCallsCounter));
            this.LoggingSamplesSum = 0.0d;
            this.LoggingSamplesCounter = 0;
            this.LoggingCallsCounter = 0;
        }
        if (((long) (((double) (System.nanoTime() - lal_start)) / 1000000.0d)) > 10) {
            Logging.LogDebugPrint(true, String.format("%s:LogAudioLevel:WARN: too too long (%d ms)", new Object[]{this.threadName, Long.valueOf((long) (((double) (System.nanoTime() - lal_start)) / 1000000.0d))}), new Object[0]);
        }
    }

    public void SetEnvironment(INativeInterop interop, IAudioTuner tuner) {
        this.AudioTuner = tuner;
        this.NativeInterop = interop;
    }
}
