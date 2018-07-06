package com.mavenir.android.common;

import android.annotation.TargetApi;
import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothHeadset;
import android.bluetooth.BluetoothProfile;
import android.bluetooth.BluetoothProfile.ServiceListener;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.os.Binder;
import android.os.IBinder;
import android.support.v4.content.o;
import com.fgmicrotec.mobile.android.fgvoipcommon.e;

@TargetApi(11)
public class BluetoothService extends Service {
    private BluetoothAdapter a = null;
    private BluetoothHeadset b = null;
    private BluetoothDevice c = null;
    private final IBinder d = new b(this);
    private a e;
    private boolean f = false;
    private boolean g = false;
    private boolean h = false;
    private AudioManager i = null;
    private ServiceListener j = new ServiceListener(this) {
        final /* synthetic */ BluetoothService a;

        {
            this.a = r1;
        }

        public void onServiceConnected(int i, BluetoothProfile bluetoothProfile) {
            this.a.b = (BluetoothHeadset) bluetoothProfile;
        }

        public void onServiceDisconnected(int i) {
            this.a.b = null;
        }
    };

    private class a extends BroadcastReceiver {
        final /* synthetic */ BluetoothService a;

        private a(BluetoothService bluetoothService) {
            this.a = bluetoothService;
        }

        /* synthetic */ a(BluetoothService bluetoothService, AnonymousClass1 anonymousClass1) {
            this(bluetoothService);
        }

        public void onReceive(Context context, Intent intent) {
            if (intent != null && intent.getAction() != null) {
                String action = intent.getAction();
                if (action.equals("android.bluetooth.adapter.action.STATE_CHANGED")) {
                    q.a("BluetoothService", "BluetoothBroadcastReceiver: onReceive(): BluetoothAdapter.ACTION_STATE_CHANGED: " + this.a.b(intent.getIntExtra("android.bluetooth.adapter.extra.PREVIOUS_STATE", -1)) + "->" + this.a.b(intent.getIntExtra("android.bluetooth.adapter.extra.STATE", -1)));
                } else if (action.equals("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED")) {
                    r1 = intent.getIntExtra("android.bluetooth.profile.extra.PREVIOUS_STATE", -1);
                    int intExtra = intent.getIntExtra("android.bluetooth.profile.extra.STATE", -1);
                    this.a.c = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                    q.a("BluetoothService", "BluetoothBroadcastReceiver: onReceive(): BluetoothHeadset.ACTION_CONNECTION_STATE_CHANGED: " + this.a.c(r1) + "->" + this.a.c(intExtra));
                    if (intExtra == 2) {
                        this.a.a("BluetoothService.ActionDeviceConnectionChanged", true);
                    } else {
                        this.a.a("BluetoothService.ActionDeviceConnectionChanged", false);
                    }
                } else if (action.equals("android.bluetooth.headset.action.VENDOR_SPECIFIC_HEADSET_EVENT")) {
                    action = intent.getStringExtra("android.bluetooth.headset.extra.VENDOR_SPECIFIC_HEADSET_EVENT_CMD");
                    q.a("BluetoothService", "BluetoothBroadcastReceiver: onReceive(): BluetoothHeadset.ACTION_VENDOR_SPECIFIC_HEADSET_EVENT: cmd: " + action + ", cmdType: " + intent.getIntExtra("android.bluetooth.headset.extra.VENDOR_SPECIFIC_HEADSET_EVENT_CMD_TYPE", -1));
                } else if (action.equals("android.media.ACTION_SCO_AUDIO_STATE_UPDATED")) {
                    int intExtra2 = intent.getIntExtra("android.media.extra.SCO_AUDIO_PREVIOUS_STATE", -1);
                    r1 = intent.getIntExtra("android.media.extra.SCO_AUDIO_STATE", -1);
                    q.a("BluetoothService", "BluetoothBroadcastReceiver: onReceive(): AudioManager.ACTION_SCO_AUDIO_STATE_UPDATED: " + this.a.d(intExtra2) + "->" + this.a.d(r1));
                    if (intExtra2 == 2 && r1 == 0 && !e.a()) {
                        this.a.i.stopBluetoothSco();
                    }
                    if (r1 == 1) {
                        this.a.a("BluetoothService.ActionScoAudioConnectionChanged", true);
                        this.a.g = true;
                        return;
                    }
                    this.a.a("BluetoothService.ActionScoAudioConnectionChanged", false);
                    this.a.g = false;
                }
            }
        }
    }

    public class b extends Binder {
        final /* synthetic */ BluetoothService a;

        public b(BluetoothService bluetoothService) {
            this.a = bluetoothService;
        }

        public BluetoothService a() {
            return this.a;
        }
    }

    public IBinder onBind(Intent intent) {
        return this.d;
    }

    public void onCreate() {
        super.onCreate();
        q.a("BluetoothService", "onCreate()");
        this.a = BluetoothAdapter.getDefaultAdapter();
        this.i = (AudioManager) getSystemService("audio");
        this.a.getProfileProxy(this, this.j, 1);
        d();
    }

    public void onDestroy() {
        super.onDestroy();
        q.a("BluetoothService", "onDestroy()");
        e();
    }

    private void d() {
        q.a("BluetoothService", "registerBroadcastReceiver()");
        this.e = new a();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
        intentFilter.addAction("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED");
        intentFilter.addAction("android.bluetooth.headset.action.VENDOR_SPECIFIC_HEADSET_EVENT");
        intentFilter.addAction("android.media.ACTION_SCO_AUDIO_STATE_UPDATED");
        registerReceiver(this.e, intentFilter);
    }

    private void e() {
        q.a("BluetoothService", "unregisterBroadcastReceiver()");
        unregisterReceiver(this.e);
    }

    private String b(int i) {
        if (i == 10) {
            return "STATE_OFF";
        }
        if (i == 11) {
            return "STATE_TURNING_ON";
        }
        if (i == 12) {
            return "STATE_ON";
        }
        if (i == 13) {
            return "STATE_TURNING_OFF";
        }
        return "STATE_UNKNOWN";
    }

    private String c(int i) {
        if (i == 0) {
            return "STATE_DISCONNECTED";
        }
        if (i == 1) {
            return "STATE_CONNECTING";
        }
        if (i == 2) {
            return "STATE_CONNECTED";
        }
        if (i == 3) {
            return "STATE_DISCONNECTING";
        }
        if (i == 10) {
            return "STATE_AUDIO_DISCONNECTED";
        }
        if (i == 11) {
            return "STATE_AUDIO_DISCONNECTED";
        }
        if (i == 12) {
            return "STATE_AUDIO_CONNECTED";
        }
        return "STATE_UNKNOWN";
    }

    private String d(int i) {
        if (i == 2) {
            return "SCO_AUDIO_STATE_CONNECTING";
        }
        if (i == 1) {
            return "SCO_AUDIO_STATE_CONNECTED";
        }
        if (i == 0) {
            return "SCO_AUDIO_STATE_DISCONNECTED";
        }
        if (i == -1) {
            return "SCO_AUDIO_STATE_ERROR";
        }
        return "STATE_UNKNOWN";
    }

    public boolean a() {
        return this.f;
    }

    public int a(int i) {
        if (this.a != null) {
            return this.a.getProfileConnectionState(i);
        }
        return 0;
    }

    public boolean b() {
        return a(1) == 2;
    }

    public boolean c() {
        return this.g;
    }

    private void a(String str, boolean z) {
        Intent intent = new Intent(str);
        intent.putExtra("BluetoothService.ExtraState", z);
        o.a((Context) this).a(intent);
    }
}
