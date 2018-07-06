package com.spiritdsp.tsm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.os.Build.VERSION;
import android.os.Handler;
import java.lang.reflect.Method;

public class a {
    private static boolean b = true;
    private BroadcastReceiver a = new BroadcastReceiver(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public void onReceive(Context context, Intent intent) {
            int i = 1;
            int intExtra = intent.getIntExtra("android.media.extra.SCO_AUDIO_STATE", -1);
            Logging.LogDebugPrint(a.b, "BtAudioDevice: onReceive: sco state = %d", Integer.valueOf(intExtra));
            Logging.LogNativePrint("BtAudioDevice: onReceive: sco state = %d", Integer.valueOf(intExtra));
            synchronized (this.a.e) {
                if (this.a.f == c.STARTING) {
                    if (intExtra == 1) {
                        Logging.LogDebugPrint(a.b, "BtAudioDevice: onReceive: m_ScoState = %s -> %s", this.a.f.name(), c.STARTED.name());
                        Logging.LogNativePrint("BtAudioDevice: onReceive: m_ScoState = %s -> %s", this.a.f.name(), c.STARTED.name());
                        this.a.d.setMode(VERSION.SDK_INT < 14 ? 2 : 3);
                        this.a.f = c.STARTED;
                    } else {
                        new Handler().postDelayed(new b(this.a, null), 2000);
                        i = 0;
                    }
                } else if (this.a.f == c.STARTED && intExtra == 0) {
                    a.d(this.a.d);
                    this.a.d.setMode(0);
                    this.a.c.unregisterReceiver(this.a.a);
                    Logging.LogDebugPrint(a.b, "BtAudioDevice: onReceive: m_ScoState = %s -> %s", this.a.f.name(), c.STOPPED.name());
                    Logging.LogNativePrint("BtAudioDevice: onReceive: m_ScoState = %s -> %s", this.a.f.name(), c.STOPPED.name());
                    this.a.f = c.STOPPED;
                } else {
                    i = 0;
                }
                c b = this.a.f;
            }
            if (i != 0 && this.a.g != null) {
                this.a.g.a(b);
            }
        }
    };
    private Context c = null;
    private AudioManager d = null;
    private Object e = new Object();
    private volatile c f = c.STOPPED;
    private a g;

    public interface a {
        void a(c cVar);
    }

    private class b implements Runnable {
        final /* synthetic */ a a;

        private b(a aVar) {
            this.a = aVar;
        }

        /* synthetic */ b(a aVar, AnonymousClass1 anonymousClass1) {
            this(aVar);
        }

        public void run() {
            Object obj = 1;
            synchronized (this.a.e) {
                if (this.a.f == c.STARTING) {
                    a.d(this.a.d);
                    this.a.c.unregisterReceiver(this.a.a);
                    Logging.LogDebugPrint(a.b, "BtAudioDevice: ScoCheckStartRunnable: m_ScoState = %s->%s", this.a.f.name(), c.STOPPED.name());
                    Logging.LogNativePrint("BtAudioDevice: ScoCheckStartRunnable: m_ScoState = %s->%s", this.a.f.name(), c.STOPPED.name());
                    this.a.f = c.STOPPED;
                } else {
                    obj = null;
                }
            }
            if (obj != null && this.a.g != null) {
                this.a.g.a(c.STOPPED);
            }
        }
    }

    public enum c {
        STOPPED,
        STARTING,
        STARTED
    }

    public a(Context context, AudioManager audioManager, a aVar) {
        this.c = context;
        this.d = audioManager;
        this.g = aVar;
    }

    public boolean a() {
        if (b(this.d)) {
            synchronized (this.e) {
                if (this.f != c.STOPPED) {
                    return false;
                }
                Handler handler;
                Logging.LogDebugPrint(b, "BtAudioDevice: Start: m_ScoState = %s->%s", this.f.name(), c.STARTING.name());
                Logging.LogNativePrint("BtAudioDevice: Start: m_ScoState = %s->%s", this.f.name(), c.STARTING.name());
                this.f = c.STARTING;
                this.c.registerReceiver(this.a, new IntentFilter("android.media.SCO_AUDIO_STATE_UPDATED"));
                this.c.registerReceiver(this.a, new IntentFilter("android.media.SCO_AUDIO_STATE_CHANGED"));
                this.d.setSpeakerphoneOn(false);
                c(this.d);
                if (this.c != null) {
                    handler = new Handler(this.c.getMainLooper());
                } else {
                    handler = new Handler();
                }
                handler.postDelayed(new b(), 3000);
                return true;
            }
        }
        Logging.LogDebugPrint(b, "WARN: BT SCO is not availabe off call", new Object[0]);
        return false;
    }

    public void b() {
        synchronized (this.e) {
            Logging.LogDebugPrint(b, "BtAudioDevice: Stop %s -> %s", this.f.name(), c.STOPPED.name());
            Logging.LogNativePrint("BtAudioDevice: Stop %s -> %s", this.f.name(), c.STOPPED.name());
            if (this.f != c.STARTED) {
                return;
            }
            this.f = c.STOPPED;
            this.c.unregisterReceiver(this.a);
            d(this.d);
            this.d.setMode(0);
            Logging.LogDebugPrint(b, "BtAudioDevice: Stopped", new Object[0]);
            Logging.LogNativePrint("BtAudioDevice: Stopped", new Object[0]);
        }
    }

    private static boolean b(AudioManager audioManager) {
        try {
            Method method = audioManager.getClass().getMethod("isBluetoothScoAvailableOffCall", new Class[0]);
            if (method != null) {
                return ((Boolean) method.invoke(audioManager, new Object[0])).booleanValue();
            }
        } catch (Exception e) {
            Logging.LogDebugPrint(b, "checkBluetoothScoAvailableOffCall: %s", e.getMessage());
        }
        return false;
    }

    private static void c(AudioManager audioManager) {
        try {
            Method method = audioManager.getClass().getMethod("startBluetoothSco", new Class[0]);
            if (method != null) {
                audioManager.setBluetoothScoOn(true);
                method.invoke(audioManager, new Object[0]);
            }
        } catch (Exception e) {
            Logging.LogDebugPrint(b, "startScoService: %s", e.getMessage());
        }
    }

    private static void d(AudioManager audioManager) {
        try {
            Method method = audioManager.getClass().getMethod("stopBluetoothSco", new Class[0]);
            if (method != null) {
                method.invoke(audioManager, new Object[0]);
                audioManager.setBluetoothScoOn(false);
            }
        } catch (Exception e) {
            Logging.LogDebugPrint(b, "stopScoService: %s", e.getMessage());
        }
    }
}
