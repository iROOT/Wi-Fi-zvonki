package com.spiritdsp.tsm;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Build.VERSION;

class WakeLocker {
    private static WakeLocker TheInstance;
    private boolean mStateOfFLAG_KEEP_SCREEN_ON = false;
    private boolean mWakeLocked = false;

    private WakeLocker() {
    }

    public static WakeLocker GetInstance() {
        if (TheInstance == null) {
            TheInstance = new WakeLocker();
        }
        return TheInstance;
    }

    @SuppressLint({"InlinedApi"})
    public void wake_lock(Context context) {
        if (!this.mWakeLocked) {
            if (context == null) {
                Logging.LogNativePrintErr("audioTrack.wake_lock without context", new Object[0]);
                return;
            }
            Logging.LogNativePrint("audioTrack.wake_lock", new Object[0]);
            if (context instanceof Activity) {
                final Activity act = (Activity) context;
                act.runOnUiThread(new Runnable() {
                    public void run() {
                        WakeLocker.this.mStateOfFLAG_KEEP_SCREEN_ON = (act.getWindow().getAttributes().flags & 128) != 0;
                        if (VERSION.SDK_INT >= 5) {
                            act.getWindow().addFlags(4194304);
                            act.getWindow().addFlags(2097152);
                        }
                        act.getWindow().addFlags(128);
                    }
                });
                this.mWakeLocked = true;
            }
        }
    }

    @SuppressLint({"InlinedApi"})
    public void wake_unlock(Context context) {
        if (this.mWakeLocked) {
            this.mWakeLocked = false;
            if (context == null) {
                Logging.LogNativePrintErr("audioTrack.wake_unlock without context", new Object[0]);
                return;
            }
            Logging.LogNativePrint("audioTrack.wake_unlock", new Object[0]);
            if (context instanceof Activity) {
                final Activity act = (Activity) context;
                act.runOnUiThread(new Runnable() {
                    public void run() {
                        if (!WakeLocker.this.mStateOfFLAG_KEEP_SCREEN_ON) {
                            act.getWindow().clearFlags(128);
                        }
                        if (VERSION.SDK_INT >= 5) {
                            act.getWindow().clearFlags(4194304);
                            act.getWindow().clearFlags(2097152);
                        }
                    }
                });
            }
            this.mStateOfFLAG_KEEP_SCREEN_ON = false;
        }
    }
}
