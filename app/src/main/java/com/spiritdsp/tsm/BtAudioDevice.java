package com.spiritdsp.tsm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.os.Build.VERSION;
import android.os.Handler;
import java.lang.reflect.Method;

class BtAudioDevice {
    private static final String SCO_AUDIO_STATE = "android.media.extra.SCO_AUDIO_STATE";
    private static final String SCO_AUDIO_STATE_CHANGED = "android.media.SCO_AUDIO_STATE_CHANGED";
    private static final String SCO_AUDIO_STATE_UPDATED = "android.media.SCO_AUDIO_STATE_UPDATED";
    private static final int SCO_START_CHECK_TIMEOUT = 2000;
    private static final int SCO_STATE_CONNECTED = 1;
    private static final int SCO_STATE_DISCONNECTED = 0;
    private static final boolean bEnableDebugPrint = true;
    private AudioManager m_AudioManager = null;
    private final BroadcastReceiver m_BroadcastReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            int i = 2;
            boolean bScoCallback = false;
            int state = intent.getIntExtra(BtAudioDevice.SCO_AUDIO_STATE, -1);
            Logging.LogDebugPrint(true, "BtAudioDevice: onReceive: sco state = %d", Integer.valueOf(state));
            Logging.LogNativePrint("BtAudioDevice: onReceive: sco state = %d", Integer.valueOf(state));
            synchronized (BtAudioDevice.this.m_Sync) {
                if (BtAudioDevice.this.m_ScoState == eScoState.STARTING) {
                    if (state == 1) {
                        Logging.LogDebugPrint(true, "BtAudioDevice: onReceive: m_ScoState = %d->%d", Integer.valueOf(BtAudioDevice.this.m_ScoState.ordinal()), Integer.valueOf(eScoState.STARTED.ordinal()));
                        Logging.LogNativePrint("BtAudioDevice: onReceive: m_ScoState = %d->%d", Integer.valueOf(BtAudioDevice.this.m_ScoState.ordinal()), Integer.valueOf(eScoState.STARTED.ordinal()));
                        AudioManager access$300 = BtAudioDevice.this.m_AudioManager;
                        if (VERSION.SDK_INT >= 14) {
                            i = 3;
                        }
                        access$300.setMode(i);
                        BtAudioDevice.this.m_ScoState = eScoState.STARTED;
                        bScoCallback = true;
                    } else {
                        new Handler().postDelayed(new ScoCheckStartRunnable(BtAudioDevice.this, null), 2000);
                    }
                } else if (BtAudioDevice.this.m_ScoState == eScoState.STARTED && state == 0) {
                    BtAudioDevice.stopScoService(BtAudioDevice.this.m_AudioManager);
                    BtAudioDevice.this.m_AudioManager.setMode(0);
                    BtAudioDevice.this.m_Context.unregisterReceiver(BtAudioDevice.this.m_BroadcastReceiver);
                    Logging.LogDebugPrint(true, "BtAudioDevice: onReceive: m_ScoState = %d->%d", Integer.valueOf(BtAudioDevice.this.m_ScoState.ordinal()), Integer.valueOf(eScoState.STOPPED.ordinal()));
                    Logging.LogNativePrint("BtAudioDevice: onReceive: m_ScoState = %d->%d", Integer.valueOf(BtAudioDevice.this.m_ScoState.ordinal()), Integer.valueOf(eScoState.STOPPED.ordinal()));
                    BtAudioDevice.this.m_ScoState = eScoState.STOPPED;
                    bScoCallback = true;
                }
                eScoState cb_state = BtAudioDevice.this.m_ScoState;
            }
            if (bScoCallback && BtAudioDevice.this.m_BtStateReceiver != null) {
                BtAudioDevice.this.m_BtStateReceiver.OnScoService(cb_state);
            }
        }
    };
    private final BtStateReceiver m_BtStateReceiver;
    private Context m_Context = null;
    private volatile eScoState m_ScoState = eScoState.STOPPED;
    private final Object m_Sync = new Object();

    interface BtStateReceiver {
        void OnScoService(eScoState escostate);
    }

    private class ScoCheckStartRunnable implements Runnable {
        private ScoCheckStartRunnable() {
        }

        /* synthetic */ ScoCheckStartRunnable(BtAudioDevice x0, AnonymousClass1 x1) {
            this();
        }

        public void run() {
            boolean bScoCallback = false;
            synchronized (BtAudioDevice.this.m_Sync) {
                if (BtAudioDevice.this.m_ScoState == eScoState.STARTING) {
                    BtAudioDevice.stopScoService(BtAudioDevice.this.m_AudioManager);
                    BtAudioDevice.this.m_Context.unregisterReceiver(BtAudioDevice.this.m_BroadcastReceiver);
                    Logging.LogDebugPrint(true, "BtAudioDevice: ScoCheckStartRunnable: m_ScoState = %d->%d", Integer.valueOf(BtAudioDevice.this.m_ScoState.ordinal()), Integer.valueOf(eScoState.STOPPED.ordinal()));
                    Logging.LogNativePrint("BtAudioDevice: ScoCheckStartRunnable: m_ScoState = %d->%d", Integer.valueOf(BtAudioDevice.this.m_ScoState.ordinal()), Integer.valueOf(eScoState.STOPPED.ordinal()));
                    BtAudioDevice.this.m_ScoState = eScoState.STOPPED;
                    bScoCallback = true;
                }
            }
            if (bScoCallback && BtAudioDevice.this.m_BtStateReceiver != null) {
                BtAudioDevice.this.m_BtStateReceiver.OnScoService(eScoState.STOPPED);
            }
        }
    }

    enum eScoState {
        STOPPED,
        STARTING,
        STARTED
    }

    BtAudioDevice(Context context, AudioManager audioManager, BtStateReceiver btStateReceiver) {
        this.m_Context = context;
        this.m_AudioManager = audioManager;
        this.m_BtStateReceiver = btStateReceiver;
    }

    boolean Start() {
        if (checkBluetoothScoAvailableOffCall(this.m_AudioManager)) {
            synchronized (this.m_Sync) {
                if (this.m_ScoState != eScoState.STOPPED) {
                    return false;
                }
                Handler checkStartService;
                Logging.LogDebugPrint(true, "BtAudioDevice: Start: m_ScoState = %d->%d", Integer.valueOf(this.m_ScoState.ordinal()), Integer.valueOf(eScoState.STARTING.ordinal()));
                Logging.LogNativePrint("BtAudioDevice: Start: m_ScoState = %d->%d", Integer.valueOf(this.m_ScoState.ordinal()), Integer.valueOf(eScoState.STARTING.ordinal()));
                this.m_ScoState = eScoState.STARTING;
                this.m_Context.registerReceiver(this.m_BroadcastReceiver, new IntentFilter(SCO_AUDIO_STATE_UPDATED));
                this.m_Context.registerReceiver(this.m_BroadcastReceiver, new IntentFilter(SCO_AUDIO_STATE_CHANGED));
                this.m_AudioManager.setSpeakerphoneOn(false);
                startScoService(this.m_AudioManager);
                if (this.m_Context != null) {
                    checkStartService = new Handler(this.m_Context.getMainLooper());
                } else {
                    checkStartService = new Handler();
                }
                checkStartService.postDelayed(new ScoCheckStartRunnable(), 3000);
                return true;
            }
        }
        Logging.LogDebugPrint(true, "WARN: BT SCO is not availabe off call", new Object[0]);
        return false;
    }

    public eScoState GetState() {
        return this.m_ScoState;
    }

    void Stop() {
        synchronized (this.m_Sync) {
            Logging.LogDebugPrint(true, "BtAudioDevice: Stop %d -> %d", Integer.valueOf(this.m_ScoState.ordinal()), Integer.valueOf(eScoState.STOPPED.ordinal()));
            Logging.LogNativePrint("BtAudioDevice: Stop %d -> %d", Integer.valueOf(this.m_ScoState.ordinal()), Integer.valueOf(eScoState.STOPPED.ordinal()));
            if (this.m_ScoState != eScoState.STARTED) {
                return;
            }
            this.m_ScoState = eScoState.STOPPED;
            this.m_Context.unregisterReceiver(this.m_BroadcastReceiver);
            stopScoService(this.m_AudioManager);
            this.m_AudioManager.setMode(0);
            Logging.LogDebugPrint(true, "BtAudioDevice: Stopped", new Object[0]);
            Logging.LogNativePrint("BtAudioDevice: Stopped", new Object[0]);
        }
    }

    private static boolean checkBluetoothScoAvailableOffCall(AudioManager audioManager) {
        try {
            Method methCheck = audioManager.getClass().getMethod("isBluetoothScoAvailableOffCall", new Class[0]);
            if (methCheck != null) {
                return ((Boolean) methCheck.invoke(audioManager, new Object[0])).booleanValue();
            }
        } catch (Exception e) {
            Logging.LogDebugPrint(true, "checkBluetoothScoAvailableOffCall: %s", e.getMessage());
        }
        return false;
    }

    private static void startScoService(AudioManager audioManager) {
        try {
            Method methStart = audioManager.getClass().getMethod("startBluetoothSco", new Class[0]);
            if (methStart != null) {
                audioManager.setBluetoothScoOn(true);
                methStart.invoke(audioManager, new Object[0]);
            }
        } catch (Exception e) {
            Logging.LogDebugPrint(true, "startScoService: %s", e.getMessage());
        }
    }

    private static void stopScoService(AudioManager audioManager) {
        try {
            Method methStop = audioManager.getClass().getMethod("stopBluetoothSco", new Class[0]);
            if (methStop != null) {
                methStop.invoke(audioManager, new Object[0]);
                audioManager.setBluetoothScoOn(false);
            }
        } catch (Exception e) {
            Logging.LogDebugPrint(true, "stopScoService: %s", e.getMessage());
        }
    }
}
