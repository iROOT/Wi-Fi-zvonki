package com.fgmicrotec.mobile.android.fgvoip;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiManager.WifiLock;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.os.SystemClock;
import android.provider.ContactsContract.Contacts;
import android.provider.Settings.System;
import android.provider.Telephony.Sms;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.ActionBar;
import android.telephony.PhoneNumberUtils;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;
import com.fgmicrotec.mobile.android.fgmag.VoIP;
import com.fgmicrotec.mobile.android.fgvoipcommon.CallService;
import com.mavenir.android.activity.ActivationActivity;
import com.mavenir.android.activity.ExceptionDialogActivity;
import com.mavenir.android.activity.InCallScreenActivity;
import com.mavenir.android.activity.PreferenceMainActivity;
import com.mavenir.android.common.BluetoothService;
import com.mavenir.android.common.CallManager;
import com.mavenir.android.common.aa;
import com.mavenir.android.common.j;
import com.mavenir.android.common.l;
import com.mavenir.android.common.q;
import com.mavenir.android.common.s;
import com.mavenir.android.common.v;
import com.mavenir.android.settings.c.h;
import com.mavenir.android.settings.c.i;
import com.mavenir.android.settings.c.k;
import com.mavenir.android.settings.c.o;
import com.mavenir.android.settings.c.p;
import com.mavenir.android.settings.c.z;
import java.io.File;
import java.lang.reflect.Field;
import java.net.InetAddress;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FgVoIP extends Application {
    private static int K = 3000;
    private static int L = VoIP.REASON_CODE_SERVER_INTERNAL_ERROR;
    private static int M = 5000;
    public static boolean a = false;
    public static int b = 100;
    public static File c = (Environment.getExternalStorageState().equals("mounted") ? Environment.getExternalStorageDirectory() : null);
    public static String d = "fgAdapter.txt";
    public static File e;
    public static String f = "spiritAndroid.log";
    public static File g;
    public static final byte[] h = new byte[]{(byte) -77, (byte) -98, (byte) 68, (byte) 104, (byte) -56, (byte) -62, (byte) 61, (byte) 58, (byte) -62, (byte) 83, (byte) 22, (byte) -82, (byte) 27, (byte) 61, (byte) 117, (byte) -9, (byte) 85, (byte) 69, (byte) -82, (byte) 111};
    private static boolean i = false;
    private static FgVoIP l;
    private static a m;
    private static long n;
    private static boolean p = false;
    private static BluetoothService q = null;
    private static boolean r = false;
    private static boolean s = false;
    private static long t = 0;
    private static boolean u = false;
    private static long x = 0;
    private String A = null;
    private String B = null;
    private PowerManager C;
    private WakeLock D;
    private WakeLock E = null;
    private WakeLock F = null;
    private WakeLock G;
    private g H = g.SLEEP;
    private WifiManager I;
    private WifiLock J = null;
    private String N;
    private long O;
    private boolean P = false;
    private Runnable Q = new Runnable(this) {
        final /* synthetic */ FgVoIP a;

        {
            this.a = r1;
        }

        public void run() {
            WifiManager wifiManager = (WifiManager) this.a.getSystemService("wifi");
            wifiManager.enableNetwork(wifiManager.getConnectionInfo().getNetworkId(), false);
        }
    };
    private ServiceConnection R = new ServiceConnection(this) {
        final /* synthetic */ FgVoIP a;

        {
            this.a = r1;
        }

        public void onServiceDisconnected(ComponentName componentName) {
            if ("com.mavenir.android.common.BluetoothService".equals(componentName.getPackageName() + componentName.getClassName())) {
                q.a("FgVoIP", "ServiceConnection: onServiceDisconnected(): custom BlueTooth service disconnected");
                FgVoIP.q = null;
            }
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            if ("com.mavenir.android.common.BluetoothService".equals(componentName.getClassName())) {
                q.a("FgVoIP", "ServiceConnection: onServiceConnected(): custom BlueTooth service connected");
                try {
                    FgVoIP.q = ((com.mavenir.android.common.BluetoothService.b) iBinder).a();
                } catch (Exception e) {
                    q.d("FgVoIP", "onServiceConnected(): " + e);
                }
            }
        }
    };
    private b j;
    private com.mavenir.android.common.a k;
    private boolean o = false;
    private Handler v;
    private boolean w = false;
    private final int y = 32;
    private final int z = 3;

    public enum a {
        VOIP,
        VOIP_WITH_SMS,
        VToW,
        RCSE,
        FULL_RCS
    }

    private class b extends BroadcastReceiver {
        final /* synthetic */ FgVoIP a;

        private b(FgVoIP fgVoIP) {
            this.a = fgVoIP;
        }

        /* synthetic */ b(FgVoIP fgVoIP, AnonymousClass1 anonymousClass1) {
            this(fgVoIP);
        }

        public void onReceive(Context context, Intent intent) {
            if (intent != null && intent.getAction() != null) {
                q.b("FgVoIP", "FgVoIP onReceive(): " + intent.getAction());
                int intExtra;
                int intExtra2;
                if ("com.fgmicrotec.mobile.android.voip.LoginToServerCnf".equals(intent.getAction())) {
                    if (intent.hasExtra("extra_error_code")) {
                        intExtra = intent.getIntExtra("extra_error_code", 1);
                        intExtra2 = intent.getIntExtra("extra_reason_code", 0);
                        if (intExtra == 0) {
                            this.a.aP();
                            if (this.a.ai() && k.u()) {
                                this.a.a("com.mavenir.android.messaging.activity.ConversationActivity.sendSms", true);
                            }
                        }
                        this.a.a(intExtra, intExtra2);
                    }
                } else if ("com.fgmicrotec.mobile.android.voip.LogoutFromServerCnf".equals(intent.getAction()) || "com.fgmicrotec.mobile.android.voip.LogoutFromServerInd".equals(intent.getAction())) {
                    if (intent.hasExtra("extra_error_code")) {
                        intExtra = intent.getIntExtra("extra_error_code", 1);
                        intExtra2 = intent.getIntExtra("extra_reason_code", 0);
                        if (intExtra == 0 || "com.fgmicrotec.mobile.android.voip.LogoutFromServerInd".equals(intent.getAction())) {
                            this.a.aR();
                            if (this.a.ai()) {
                                this.a.a("com.mavenir.android.messaging.activity.ConversationActivity.sendSms", false);
                            }
                        } else {
                            this.a.a(intExtra, intExtra2);
                            q.d("FgVoIP", g.a(intExtra));
                        }
                        this.a.aQ();
                    }
                } else if ("com.fgmicrotec.mobile.android.voip.IncomingCallInd".equals(intent.getAction())) {
                    this.a.a(true, true, false);
                    FgVoIP.t = System.currentTimeMillis();
                    this.a.v.post(new e(this.a, intent.getStringExtra("extra_uri_to_call"), intent.getStringExtra("extra_display_name"), intent.getIntExtra("extra_session_id", 0), intent.getIntExtra("extra_is_video_call", 0)));
                } else if ("com.fgmicrotec.mobile.android.voip.IncomingCallCanceledInd".equals(intent.getAction())) {
                    if (System.currentTimeMillis() - FgVoIP.t < 500) {
                        this.a.v.postDelayed(new d(this.a, intent.getIntExtra("extra_session_id", 0)), 1000);
                    } else {
                        this.a.v.post(new d(this.a, intent.getIntExtra("extra_session_id", 0)));
                    }
                } else if ("InternalIntents.RequestWakeStateSleep".equals(intent.getAction())) {
                    this.a.a(g.SLEEP);
                } else if ("InternalIntents.RequestWakeStatePartial".equals(intent.getAction())) {
                    this.a.a(g.PARTIAL);
                } else if ("InternalIntents.RequestWakeStateFull".equals(intent.getAction())) {
                    this.a.a(g.FULL);
                } else if ("com.mavenir.android.action.initiateCallExternal".equals(intent.getAction())) {
                    String stringExtra = intent.getStringExtra("ExtraNumberExternal");
                    if (stringExtra != null && h.i()) {
                        this.a.j(stringExtra);
                    }
                } else if ("com.mavenir.android.action.initiateCsCall".equals(intent.getAction())) {
                    Object stringExtra2 = intent.getStringExtra("ExtraNumberExternal");
                    if (!TextUtils.isEmpty(stringExtra2)) {
                        this.a.m(stringExtra2);
                    }
                } else if ("com.mavenir.android.activation.action_user_activated".equals(intent.getAction())) {
                    this.a.g(intent.getBooleanExtra("com.mavenir.android.activation.extra_user_enabled", false));
                } else if (!"MainTabActions.ActionStartAfterReboot".equals(intent.getAction()) || !k.h()) {
                } else {
                    if (this.a.aj()) {
                        Intent intent2 = new Intent("com.mavenir.action.LAUNCH_MAIN_TAB");
                        intent2.setFlags(268435456);
                        this.a.startActivity(intent2);
                        return;
                    }
                    this.a.c();
                }
            }
        }
    }

    private class c implements Runnable {
        final /* synthetic */ FgVoIP a;
        private String b;

        c(FgVoIP fgVoIP, String str) {
            this.a = fgVoIP;
            this.b = str;
        }

        public void run() {
            q.b("NumberPrefixLog", "HandleAskUserDialog(): number: " + this.b);
            Intent intent = new Intent(this.a, OutgoingCallAskUser.class);
            intent.addFlags(1073741824);
            intent.addFlags(8912896);
            intent.addFlags(268435456);
            intent.putExtra("OutgoingCallAskUser.InternalIntents.ExtraNumberToCall", this.b);
            this.a.startActivity(intent);
        }
    }

    private class d implements Runnable {
        final /* synthetic */ FgVoIP a;
        private int b;

        d(FgVoIP fgVoIP, int i) {
            this.a = fgVoIP;
            this.b = i;
        }

        public void run() {
            com.mavenir.android.common.g.a().e();
            Intent intent = new Intent();
            intent.setAction("CallManager.IncomingCallCanceledInd");
            intent.putExtra("extra_session_id", this.b);
            this.a.sendBroadcast(intent);
        }
    }

    private class e implements Runnable {
        final /* synthetic */ FgVoIP a;
        private String b;
        private String c;
        private int d;
        private int e;

        e(FgVoIP fgVoIP, String str, String str2, int i, int i2) {
            this.a = fgVoIP;
            this.b = str;
            this.c = str2;
            this.d = i;
            this.e = i2;
        }

        public void run() {
            Intent intent;
            if (((TelephonyManager) this.a.getSystemService("phone")).getCallState() != 0) {
                intent = new Intent();
                intent.setAction("com.fgmicrotec.mobile.android.voip.RejectInvitationReq");
                intent.putExtra("extra_session_id", this.d);
                this.a.sendBroadcast(intent);
                com.mavenir.android.common.g.a().a(true);
                com.mavenir.android.common.e.b(this.c, com.mavenir.android.common.t.f.f(this.b), 3, System.currentTimeMillis(), null);
            } else if (VERSION.SDK_INT < 9 || CallManager.a()) {
                intent = new Intent(this.a, this.a.aH());
                intent.addFlags(268435456);
                if (this.e == 1) {
                    intent.setAction("InCallActions.ActionIncomingVideoCallInd");
                } else {
                    intent.setAction("InCallActions.ActionIncomingCallInd");
                }
                intent.putExtra("extra_uri_to_call", this.b);
                intent.putExtra("extra_display_name", this.c);
                intent.putExtra("extra_session_id", this.d);
                this.a.startActivity(intent);
            } else {
                e.a().a(this.c, this.b, this.d, true, this.e);
                com.mavenir.android.common.g.a().a(com.mavenir.android.common.t.f.f(this.b));
                com.mavenir.android.common.g.a().a(2, 2);
            }
        }
    }

    private class f implements Runnable {
        final /* synthetic */ FgVoIP a;
        private String b;

        f(FgVoIP fgVoIP, String str) {
            this.a = fgVoIP;
            this.b = str;
        }

        public void run() {
            this.a.j(this.b);
        }
    }

    private enum g {
        SLEEP,
        PARTIAL,
        FULL
    }

    static {
        File file;
        File file2 = null;
        if (c != null) {
            file = new File(c, d);
        } else {
            file = null;
        }
        e = file;
        if (c != null) {
            file2 = new File(c, f);
        }
        g = file2;
    }

    public void onCreate() {
        l = this;
        r = false;
        n = System.currentTimeMillis();
        m = aN();
        q.a("FgVoIP", "FgVoIP app created!");
        this.k = new com.mavenir.android.common.a();
        registerActivityLifecycleCallbacks(this.k);
        c(true);
        this.v = new Handler();
        this.C = (PowerManager) getSystemService("power");
        this.E = this.C.newWakeLock(1, "FgVoIP");
        this.F = this.C.newWakeLock(1, "FgVoIPTimeouted");
        this.G = this.C.newWakeLock(32, "FgVoIP");
        this.I = (WifiManager) getSystemService("wifi");
        this.J = this.I.createWifiLock(1, "VoipWifiLock");
        com.b.a.b.d.a().a(new com.b.a.b.e.a(this).a(new j(this)).a());
        com.b.a.c.c.a();
        if (aj() || ai()) {
            com.fgmicrotec.mobile.android.fgvoipcommon.e eVar = new com.fgmicrotec.mobile.android.fgvoipcommon.e(this);
        }
        this.j = new b();
        IntentFilter intentFilter = new IntentFilter("com.fgmicrotec.mobile.android.voip.LoginToServerCnf");
        intentFilter.addAction("com.fgmicrotec.mobile.android.voip.LogoutFromServerCnf");
        intentFilter.addAction("com.fgmicrotec.mobile.android.voip.CallServiceReleasedInd");
        intentFilter.addAction("com.fgmicrotec.mobile.android.voip.LoginStartedInd");
        intentFilter.addAction("com.fgmicrotec.mobile.android.voip.LogoutFromServerInd");
        intentFilter.addAction("com.fgmicrotec.mobile.android.voip.IncomingCallInd");
        intentFilter.addAction("com.fgmicrotec.mobile.android.voip.IncomingCallCanceledInd");
        intentFilter.addAction("android.bluetooth.device.action.ACL_CONNECTED");
        intentFilter.addAction("android.bluetooth.device.action.ACL_DISCONNECTED");
        intentFilter.addAction("InternalIntents.RequestWakeStateSleep");
        intentFilter.addAction("InternalIntents.RequestWakeStatePartial");
        intentFilter.addAction("InternalIntents.RequestWakeStateFull");
        intentFilter.addAction("com.mavenir.android.action.initiateCallExternal");
        intentFilter.addAction("com.mavenir.android.action.initiateCsCall");
        intentFilter.addAction("com.mavenir.android.activation.action_user_activated");
        intentFilter.addAction("MainTabActions.ActionStartAfterReboot");
        registerReceiver(this.j, intentFilter);
        if (!ai() && ay()) {
            aD();
        }
        aO();
    }

    public void onTerminate() {
        q.b("FgVoIP", "onTerminate()");
        this.v.removeCallbacksAndMessages(e.class);
        this.v.removeCallbacksAndMessages(f.class);
        this.v.removeCallbacksAndMessages(c.class);
        this.v.removeCallbacks(this.Q);
        unregisterReceiver(this.j);
        l = null;
        n = 0;
        f();
        super.onTerminate();
    }

    public void onLowMemory() {
        super.onLowMemory();
        q.c("FgVoIP", "onLowMemory()");
    }

    @TargetApi(14)
    public void onTrimMemory(int i) {
        String str;
        if (VERSION.SDK_INT >= 14) {
            super.onTrimMemory(i);
        }
        if (i == 5) {
            str = "RUNNING_MODERATE";
        } else if (i == 10) {
            str = "RUNNING_LOW";
        } else if (i == 15) {
            str = "RUNNING_CRITICAL";
        } else if (i == 20) {
            str = "UI_HIDDEN";
        } else if (i == 40) {
            str = "BACKGROUND";
        } else if (i == 60) {
            str = "MODERATE";
        } else if (i == 80) {
            str = "COMPLETE";
        } else {
            str = "UNKNOWN";
        }
        q.c("FgVoIP", "onTrimMemory(" + i + "): " + str);
    }

    public void a() {
        U().aG();
        d();
        com.mavenir.android.applog.a.a(getApplicationContext()).b();
        a(false);
    }

    public void b() {
        if (this.w && System.currentTimeMillis() - x > 60000) {
            x = System.currentTimeMillis();
            a((Context) this, 12000);
        }
        this.w = false;
    }

    public static void a(Context context, int i) {
        if (i == 0) {
            i = 1;
        }
        ((AlarmManager) context.getSystemService("alarm")).set(1, System.currentTimeMillis() + ((long) i), PendingIntent.getActivity(context, 0, context.getPackageManager().getLaunchIntentForPackage(context.getPackageName()), 67108864));
        System.exit(2);
    }

    public void c() {
        q.a("FgVoIP", "startServices()");
        if (m() && ag()) {
            q.a("FgVoIP", "startServices(): Unable to start services");
        } else if (CallService.k() && CallService.l()) {
            q.a("FgVoIP", "startServices(): services are stopping, delaying start...");
            this.v.postDelayed(new Runnable(this) {
                final /* synthetic */ FgVoIP a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.c();
                }
            }, 500);
        } else {
            e.a((Context) this);
            if (!CallService.k()) {
                k(true);
                startService(new Intent(this, CallService.class));
                aF();
            }
            if (ad() != a.VOIP && ad() != a.VToW) {
                a("com.fgmicrotec.mobile.android.fgvoipcommon.MessagingService", "IntentActions.StartServiceReq");
            } else if (ad() == a.VToW) {
                if (ax() && k.u()) {
                    a("com.mavenir.android.messaging.service.MessagingService", "IntentActions.StartServiceReq");
                } else {
                    q.a("FgVoIP", "startServices(): messaging disabled - stopping messaging service");
                    a("com.mavenir.android.messaging.service.MessagingService", "IntentActions.StopServiceReq");
                }
            }
            if (ad() == a.RCSE || ad() == a.FULL_RCS) {
                a("com.mavenir.android.rcs.cd.CapabilityDiscoveryService", null);
                a("com.mavenir.android.rcs.im.InstantMessagingService", "IntentActions.StartServiceReq");
                a("com.mavenir.android.rcs.presence.PresenceService", "IntentActions.StartServiceReq");
            }
            if (U().N()) {
                a("com.mavenir.android.modules.ModulesService", "IntentActions.StartServiceReq");
            }
            e();
        }
    }

    public void d() {
        q.b("FgVoIP", "stopServices() - stopping services due to app exit");
        if (ad() == a.RCSE || ad() == a.FULL_RCS) {
            a("com.mavenir.android.rcs.im.InstantMessagingService", "IntentActions.StopServiceReq");
            a("com.mavenir.android.rcs.presence.PresenceService", "IntentActions.StopServiceReq");
            a("com.mavenir.android.rcs.cd.CapabilityDiscoveryService", "IntentActions.StopServiceReq");
        }
        if (ad() != a.VOIP && ad() != a.VToW) {
            a("com.fgmicrotec.mobile.android.fgvoipcommon.MessagingService", "IntentActions.StopServiceReq");
        } else if (ad() == a.VToW) {
            a("com.mavenir.android.messaging.service.MessagingService", "IntentActions.StopServiceReq");
            a("com.mavenir.android.settings.BackupService", "IntentActions.StopServiceReq");
        }
        f();
        Intent intent = new Intent(this, CallService.class);
        intent.setAction("IntentActions.StopServiceReq");
        startService(intent);
        k(false);
    }

    public void a(String str) {
        Intent intent = new Intent(this, CallService.class);
        if (!(str == null || str.equals("IntentActions.ActionNone"))) {
            intent.setAction(str);
        }
        startService(intent);
    }

    public void a(String str, String str2) {
        Intent intent = new Intent();
        intent.setClassName(this, str);
        if (str2 != null) {
            intent.setAction(str2);
        }
        startService(intent);
    }

    public void e() {
        if (BluetoothAdapter.getDefaultAdapter() != null) {
            bindService(new Intent(this, BluetoothService.class), this.R, 1);
            p = true;
        }
    }

    public void f() {
        if (p) {
            unbindService(this.R);
            p = false;
        }
    }

    public void a(List<com.mavenir.android.modules.a> list) {
    }

    public void g() {
        q.b("FgVoIP", "releaseAfterUnexpectedStart() - app not running, clean up services");
        aG();
        d();
    }

    public void h() {
        com.mavenir.android.applog.a.a(getApplicationContext()).a();
        com.mavenir.android.applog.a.a(getApplicationContext()).f();
        com.mavenir.android.applog.a.a(getApplicationContext()).g();
        com.mavenir.android.applog.a.a(getApplicationContext()).h();
        com.mavenir.android.applog.a.a(getApplicationContext()).e();
        com.mavenir.android.applog.a.a(getApplicationContext()).k();
    }

    public void i() {
        com.mavenir.android.applog.a.a(getApplicationContext()).e();
    }

    private void k(boolean z) {
        getPackageManager().setComponentEnabledSetting(new ComponentName(this, NewOutgoingCallReceiver.class), z ? 1 : 2, 1);
        q.a("FgVoIP", "enableOrDisableReceivers: " + (z ? "enabled" : "disabled"));
    }

    private void a(String str, boolean z) {
        getPackageManager().setComponentEnabledSetting(new ComponentName(this, str), z ? 1 : 2, 1);
        q.a("FgVoIP", "enableOrDisableComponent: " + str + " = " + (z ? "enabled" : "disabled"));
    }

    public void j() {
    }

    public boolean k() {
        return false;
    }

    public boolean l() {
        return false;
    }

    public boolean m() {
        return false;
    }

    public boolean n() {
        return false;
    }

    public boolean o() {
        return true;
    }

    public boolean p() {
        return false;
    }

    public boolean q() {
        return true;
    }

    public boolean r() {
        return true;
    }

    public boolean s() {
        return false;
    }

    public boolean t() {
        return true;
    }

    public boolean u() {
        return true;
    }

    public boolean v() {
        return true;
    }

    public boolean w() {
        return false;
    }

    public boolean x() {
        return false;
    }

    public boolean y() {
        return false;
    }

    public boolean z() {
        return true;
    }

    public boolean A() {
        return true;
    }

    public boolean B() {
        return false;
    }

    public boolean C() {
        return false;
    }

    public boolean D() {
        return true;
    }

    public boolean E() {
        return false;
    }

    public boolean F() {
        return false;
    }

    public boolean G() {
        return true;
    }

    public boolean H() {
        return false;
    }

    public boolean I() {
        return false;
    }

    public boolean J() {
        return true;
    }

    public boolean K() {
        return true;
    }

    public boolean L() {
        return false;
    }

    public boolean M() {
        return false;
    }

    public boolean N() {
        return false;
    }

    public boolean O() {
        return false;
    }

    public boolean P() {
        return false;
    }

    public boolean Q() {
        return true;
    }

    public boolean R() {
        return false;
    }

    public boolean S() {
        return true;
    }

    public boolean T() {
        return false;
    }

    public static FgVoIP U() {
        return l;
    }

    public static long V() {
        return n;
    }

    public boolean W() {
        return this.o;
    }

    public void X() {
        this.o = false;
    }

    public Intent Y() {
        return new Intent(this, ActivationActivity.class);
    }

    public BluetoothService Z() {
        return q;
    }

    public Intent aa() {
        Intent intent = new Intent();
        intent.setClassName(this, "com.mavenir.android.activity.ContactsSelectionActivity");
        return intent;
    }

    public String ab() {
        return getString(f.k.app_hockey_id);
    }

    public String ac() {
        if (aj()) {
            return getString(f.k.app_loggly_id);
        }
        return getString(f.k.app_hockey_id);
    }

    public a ad() {
        return m;
    }

    private a aN() {
        String string = getString(f.k.app_type);
        if (string.equals(getString(f.k.app_type_full_rcs))) {
            return a.FULL_RCS;
        }
        if (string.equals(getString(f.k.app_type_rcse))) {
            return a.RCSE;
        }
        if (string.equals(getString(f.k.app_type_vtow))) {
            return a.VToW;
        }
        if (string.equals(getString(f.k.app_type_vowifi_sms))) {
            return a.VOIP_WITH_SMS;
        }
        return a.VOIP;
    }

    public String a(int i) {
        return getString(i);
    }

    public int b(int i) {
        return Integer.parseInt(getString(i));
    }

    public boolean c(int i) {
        return Boolean.parseBoolean(getString(i));
    }

    public String[] d(int i) {
        return getResources().getStringArray(i);
    }

    public File ae() {
        return getDir("nativecrash", 0);
    }

    public byte[] b(String str) {
        if (str != null) {
            try {
                return MessageDigest.getInstance("SHA-1").digest(str.getBytes(net.hockeyapp.android.e.d.DEFAULT_CHARSET));
            } catch (Throwable e) {
                q.c("FgVoIP", "getHashedCode()", e);
            } catch (Throwable e2) {
                q.c("FgVoIP", "getHashedCode()", e2);
            }
        }
        return null;
    }

    public String c(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        return Base64.encodeToString(b(str), 0);
    }

    public void a(Activity activity) {
    }

    public void b(Activity activity) {
    }

    public boolean af() {
        if (VERSION.SDK_INT < 19) {
            return false;
        }
        String defaultSmsPackage = Sms.getDefaultSmsPackage(U());
        String packageName = U().getPackageName();
        if (defaultSmsPackage == null || !defaultSmsPackage.equals(packageName)) {
            return false;
        }
        return true;
    }

    public boolean d(String str) {
        return Arrays.equals(h, b(str));
    }

    public boolean ag() {
        Boolean w = k.w();
        long v = k.v();
        long currentTimeMillis = System.currentTimeMillis();
        if (!CallManager.a() && currentTimeMillis - v >= 300000) {
            k.b(currentTimeMillis);
            boolean a = v.a();
            if (w != null && w.booleanValue() == a) {
                return a;
            }
            k.l(a);
            return a;
        } else if (w == null) {
            return false;
        } else {
            return w.booleanValue();
        }
    }

    public void a(boolean z) {
        boolean z2 = o() || z;
        this.P = z2;
    }

    public boolean ah() {
        return this.P;
    }

    public boolean ai() {
        return ad() == a.VToW;
    }

    public boolean aj() {
        return ad() == a.RCSE || ad() == a.FULL_RCS;
    }

    public boolean e(String str) {
        List b = i.b();
        if (str == null) {
            q.a("FgVoIP", "isEmergencyNumber(): number not provided");
            return false;
        } else if (b.size() > 0) {
            return b.contains(str);
        } else {
            q.a("FgVoIP", "isEmergencyNumber(): emergency number list is empty");
            return false;
        }
    }

    public boolean f(String str) {
        List<String> a = o.a();
        if (str == null) {
            q.a("FgVoIP", "isNativeNumber(): number not provided");
            return false;
        }
        for (String compare : a) {
            if (PhoneNumberUtils.compare(compare, str)) {
                return true;
            }
        }
        return false;
    }

    public boolean ak() {
        boolean z = true;
        String p = l.a((Context) this).p();
        String r = l.a((Context) this).r();
        String n = p.n();
        if (!TextUtils.isEmpty(r)) {
            q.b("FgVoIP", "Second sim imsi" + r);
            if (p.equals(n) || r.equals(n)) {
                z = false;
            }
        } else if (p.equals(n)) {
            z = false;
        }
        q.b("FgVoIP", "isSIMchanged(): " + z + " [ currentIMSI=" + p + ", provisioningIMSI=" + n + " ]");
        return z;
    }

    public boolean al() {
        boolean z = true;
        if (n()) {
            Object p = l.a((Context) this).p();
            if (TextUtils.isEmpty(p)) {
                z = false;
            }
            q.b("FgVoIP", "isSIMInserted(): " + z + "[ currentIMSI=" + p + " ]");
        }
        return z;
    }

    public boolean am() {
        return CallService.k();
    }

    public boolean an() {
        boolean D = l.a((Context) this).D();
        if (n() && !D && (ak() || !al())) {
            q.a("FgVoIP", "isLoginAttemptAllowed(): false (sim check)");
            return false;
        } else if (!ai()) {
            D = l.a((Context) this).C();
            boolean z = l.a((Context) this).z();
            if (I() && (aB().isEmpty() || aB().equals("sip:"))) {
                return false;
            }
            if ((D && ao()) || (z && ap())) {
                q.a("FgVoIP", "isLoginAttemptAllowed: true");
                return true;
            }
            q.a("FgVoIP", "isLoginAttemptAllowed: false");
            return false;
        } else if (D) {
            q.a("FgVoIP", "isLoginAttemptAllowed(): false (Airplane Mode ON)");
            return false;
        } else if (l.a((Context) this).C() && x() && u) {
            q.a("FgVoIP", "isLoginAttemptAllowed(): true (emergency call)");
            return true;
        } else if (ar()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean ao() {
        int b = z.b();
        if (b == 0 || b == 1) {
            return true;
        }
        return false;
    }

    public boolean ap() {
        int b = z.b();
        if (b == 2 || b == 1) {
            return true;
        }
        return false;
    }

    public boolean aq() {
        boolean d = com.mavenir.android.settings.c.l.d();
        if (!d) {
            return false;
        }
        String str;
        boolean A = l.a((Context) this).A();
        int v = l.a((Context) this).v();
        boolean z = v >= com.mavenir.android.settings.c.l.j() && v <= com.mavenir.android.settings.c.l.k();
        int x = l.a((Context) this).x();
        boolean z2 = x == com.mavenir.android.settings.c.l.h();
        int w = l.a((Context) this).w();
        boolean z3 = w == com.mavenir.android.settings.c.l.i();
        boolean au = U().au();
        boolean B = l.a((Context) this).B();
        boolean a = CallManager.a();
        boolean z4 = d && A && !B && z && z2 && z3 && !au;
        if (a) {
            z4 = d && (A || B);
        }
        String str2 = "FgVoIP";
        StringBuilder append = new StringBuilder().append("isLoginAttemptAllowed (LTE): ").append(z4).append(" [ ");
        if (a) {
            str = "inCall: " + a + " (HIPRI:" + B + "), ";
        } else {
            str = "";
        }
        q.b(str2, append.append(str).append("lteConnected=").append(A).append(", lteNetworkEnabled=").append(d).append(", TAC=").append(v).append("(matching=").append(z).append("), MNC=").append(x).append("(matching=").append(z2).append("), MCC=").append(w).append("(matching=").append(z3).append("), manuallyUnregistred: ").append(au).append(" ]").toString());
        return z4;
    }

    public boolean ar() {
        String stringBuilder;
        boolean C = l.a((Context) this).C();
        boolean d = aa.a((Context) this).d();
        boolean e = aa.a((Context) this).e();
        boolean z = k.d() > 0;
        String P = l.a((Context) this).P();
        String Q = l.a((Context) this).Q();
        boolean z2 = (TextUtils.isEmpty(Q) || Q.equals("00:00:00:00:00:00")) ? false : true;
        boolean au = U().au();
        boolean a = CallManager.a();
        boolean z3 = z2 && d && !e && z && !au && as() != null;
        String str = "FgVoIP";
        StringBuilder append = new StringBuilder().append("isLoginAttemptAllowed (Wi-Fi): ").append(z3).append(" [ ").append(a ? "inCall: " + a + ", " : "").append("wifiConnected=").append(C);
        if (C || z2) {
            StringBuilder append2 = new StringBuilder().append(" (ssid=").append(P).append(", bssid=").append(Q).append("), wifiWhitelisted: ");
            a = d && !e;
            stringBuilder = append2.append(a).toString();
        } else {
            stringBuilder = "";
        }
        q.b(str, append.append(stringBuilder).append(", bssidValid: ").append(z2).append(", activated: ").append(z).append(", manuallyUnregistered: ").append(au).append(" ]").toString());
        return z3;
    }

    public InetAddress as() {
        return l.a((Context) this).R();
    }

    public boolean at() {
        return CallService.m();
    }

    public boolean au() {
        return r;
    }

    public boolean av() {
        return VERSION.CODENAME.equals("MNC") || VERSION.SDK_INT > 22;
    }

    public void b(boolean z) {
        r = z;
    }

    public void c(boolean z) {
        try {
            ViewConfiguration viewConfiguration = ViewConfiguration.get(this);
            Field declaredField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
            if (declaredField != null) {
                declaredField.setAccessible(z);
                declaredField.setBoolean(viewConfiguration, false);
            }
        } catch (Exception e) {
            q.c("FgVoIP", "forceOverflowMenuButton(): " + e.getLocalizedMessage(), e.getCause());
        }
    }

    public void d(boolean z) {
        s = z;
    }

    public void e(boolean z) {
        u = z;
    }

    public boolean aw() {
        if (e == null || !e.delete()) {
            q.b("FgVoIP", "Failed to delete trace file: " + e);
            return false;
        }
        q.b("FgVoIP", "Trace file deleted: " + e);
        return true;
    }

    private void aO() {
        b.a();
        if (ai()) {
            b.a(new com.fgmicrotec.mobile.android.fgvoip.b.a(com.fgmicrotec.mobile.android.fgvoip.b.b.ABOUT.ordinal(), getString(f.k.help_topic_about), getString(f.k.help_topic_about)));
            b.a(new com.fgmicrotec.mobile.android.fgvoip.b.a(com.fgmicrotec.mobile.android.fgvoip.b.b.HELP.ordinal(), getString(f.k.help), getString(f.k.help)));
            return;
        }
        b.a(new com.fgmicrotec.mobile.android.fgvoip.b.a(com.fgmicrotec.mobile.android.fgvoip.b.b.ABOUT.ordinal(), getString(f.k.help_topic_about), getString(f.k.help_topic_about)));
        b.a(new com.fgmicrotec.mobile.android.fgvoip.b.a(com.fgmicrotec.mobile.android.fgvoip.b.b.ACTIVATION.ordinal(), getString(f.k.help_topic_activation), getString(f.k.help_text_activation)));
    }

    public void a(ActionBar actionBar, int i, String str) {
        if (str == "") {
            CharSequence aA = U().aA();
            if (aA.indexOf("@") != -1) {
                aA = aA.substring(0, aA.indexOf("@"));
            }
            actionBar.setTitle(getString(f.k.app_name));
            actionBar.setSubtitle(aA);
            return;
        }
        actionBar.setTitle(getString(f.k.app_name));
        actionBar.setSubtitle((CharSequence) str);
    }

    public boolean ax() {
        return k.d() > 0;
    }

    public boolean ay() {
        boolean z;
        boolean z2 = k.d() > 0;
        if (k.f() != -1) {
            z = true;
        } else {
            z = false;
        }
        boolean D = l.a((Context) this).D();
        int d = k.d();
        if (D) {
            if (!z2 || z) {
                q.c("FgVoIP", "activationNeedsUpdate(): true [ airplane mode=ON, vers=" + d + " ]");
                return true;
            }
        } else if (!z2) {
            q.c("FgVoIP", "activationNeedsUpdate(): true [ vers=" + d + " ]");
            return true;
        } else if (n() && ak()) {
            q.c("FgVoIP", "activationNeedsUpdate(): true [ SIM changed ]");
            return true;
        }
        q.b("FgVoIP", "activationNeedsUpdate(): false [ vers=" + d + ", isDeactivated=" + z + ", isAirplaneModeOn=" + D + ", isSimChanged=" + ak() + " ]");
        return false;
    }

    public void az() {
    }

    public boolean f(boolean z) {
        boolean z2;
        boolean C = l.a((Context) this).C();
        Object Q = l.a((Context) this).Q();
        boolean z3 = TextUtils.isEmpty(Q) || Q.equals("00:00:00:00:00:00");
        int M = l.a((Context) this).M();
        boolean z4;
        if (M < com.mavenir.android.settings.c.q.d()) {
            z4 = true;
        } else {
            z4 = false;
        }
        boolean z5 = l.a((Context) this).z();
        boolean I = l.a((Context) this).I();
        String str = "FgVoIP";
        StringBuilder append = new StringBuilder().append("checkConnectionConditions(): initial: ").append(z).append(", wifiConnected: ").append(C).append(", wifiConnValid: ");
        if (z3) {
            z2 = false;
        } else {
            z2 = true;
        }
        q.a(str, append.append(z2).append(", wifiSignalStrength: ").append(M).append(", mobileDataConnected: ").append(z5).append(", networkRoaming: ").append(I).toString());
        if (z || ((C && !z3 && !z4) || z5)) {
            return true;
        }
        q.c("FgVoIP", "checkConnectionConditions(): No network connection, request will be sent when network connection is restored");
        return false;
    }

    public void g(boolean z) {
    }

    private String p(String str) {
        String str2 = "";
        if (str != null && str.length() > 0) {
            int indexOf = str.indexOf(":");
            int indexOf2 = str.indexOf("@");
            if (indexOf2 > 0 && indexOf2 < str.length() && indexOf < indexOf2) {
                str2 = str.substring(indexOf > -1 ? indexOf + 1 : 0, indexOf2);
            }
        }
        q.a("FgVoIP", "extractUserId(): UserID: " + str2);
        return str2;
    }

    public String aA() {
        return p(aB());
    }

    public String aB() {
        return p.f();
    }

    public void aC() {
        this.B = aB();
        this.A = p(this.B);
    }

    public void aD() {
        if (ai()) {
            com.mavenir.android.settings.c.g.b();
            s.a().a(this);
            q.a("FgVoIP", "Database reset, account deactivated");
        }
        a(false);
        Intent intent = new Intent();
        intent.setAction("InternalIntents.UpdateSmsNotification");
        sendBroadcast(intent);
    }

    public boolean aE() {
        return i;
    }

    public void h(boolean z) {
        i = z;
    }

    public void i(boolean z) {
        i = true;
        a("com.fgmicrotec.mobile.android.voip.ActionUpdateMessaging");
        if (z) {
            a("com.mavenir.android.messaging.service.MessagingService", "IntentActions.StartServiceReq");
        } else {
            a("com.mavenir.android.messaging.service.MessagingService", "IntentActions.StopServiceReq");
        }
    }

    private void a(g gVar) {
        synchronized (this) {
            switch (gVar) {
                case PARTIAL:
                    q.a("FgVoIP", "requestWakeState(): partial");
                    this.E.acquire();
                    if (this.D.isHeld()) {
                        this.D.release();
                        break;
                    }
                    break;
                case FULL:
                    q.a("FgVoIP", "requestWakeState(): full");
                    this.D.acquire(60000);
                    if (this.E.isHeld()) {
                        this.E.release();
                        break;
                    }
                    break;
                default:
                    q.a("FgVoIP", "requestWakeState(): sleep");
                    if (this.D.isHeld()) {
                        this.D.release();
                    }
                    if (this.E.isHeld()) {
                        this.E.release();
                        break;
                    }
                    break;
            }
            this.H = gVar;
        }
    }

    public void a(int i, boolean z) {
        q.b("FgVoIP", "updateProximitySensorMode() callState = " + i);
        Object obj = null;
        if (Build.MANUFACTURER.equals("HTC") && Build.MODEL.equals("HTC Desire")) {
            obj = 1;
            q.d("FgVoIP", "Proximity wake lock disabled to prevent audio loss!!!");
        }
        synchronized (this.G) {
            if (i == 2 && !z && obj == null) {
                if (q == null || !q.a()) {
                    if (!this.G.isHeld()) {
                        this.G.acquire();
                    }
                }
            }
            if (this.G.isHeld()) {
                this.G.release();
            }
        }
    }

    public void aF() {
        if (VERSION.SDK_INT < 17) {
            System.putInt(getContentResolver(), "wifi_sleep_policy", 2);
        }
        if (!(this.J == null || this.J.isHeld())) {
            this.J.acquire();
            q.b("FgVoIP", "WAKE LOCKS mWifiLock.acquire() ");
        }
        q.b("FgVoIP", "WAKE LOCKS acquired ");
    }

    public void aG() {
        if (VERSION.SDK_INT < 17) {
            System.putInt(getContentResolver(), "wifi_sleep_policy", 0);
        }
        if (this.J != null && this.J.isHeld()) {
            q.a("FgVoIP", "WIFI WAKE LOCK released");
            this.J.release();
        }
        if (this.G != null && this.G.isHeld()) {
            q.a("FgVoIP", "PROXIMITY WAKE LOCK released");
            this.G.release();
        }
        if (this.D != null && this.D.isHeld()) {
            q.a("FgVoIP", "WAKE LOCK released");
            this.D.release();
        }
        if (this.E != null && this.E.isHeld()) {
            q.a("FgVoIP", "PARTIAL WAKE LOCK released");
            this.E.release();
        }
        if (this.F != null && this.F.isHeld()) {
            q.a("FgVoIP", "PARTIAL TIMEOUTED WAKE LOCK released");
            this.F.release();
        }
        q.b("FgVoIP", "WAKE LOCKS released ");
    }

    public void j(boolean z) {
        q.b("FgVoIP", "switchWifiWakeLocks isInCall = " + z);
        if (this.J != null && this.J.isHeld()) {
            q.a("FgVoIP", "switchWifiWakeLocks(): WIFI WAKE LOCK released");
            this.J.release();
        }
        if (z) {
            this.J = this.I.createWifiLock(3, "VoipWifiLock");
        } else {
            this.J = this.I.createWifiLock(1, "VoipWifiLock");
        }
        this.J.acquire();
    }

    public void a(boolean z, boolean z2, boolean z3) {
        q.b("FgVoIP", "switchProcessorWakeLock activate = " + z + " isFromCall = " + z2 + " isFromSockets = " + z3);
        if (z) {
            if (z3) {
                if (this.F != null) {
                    this.F.acquire(5000);
                    q.b("FgVoIP", "WAKE LOCKS mPartialTimeoutedWakeLock.acquire() ");
                }
            } else if (this.E != null && !this.E.isHeld()) {
                this.E.acquire();
                q.b("FgVoIP", "WAKE LOCKS mPartialWakeLock.acquire() ");
            }
        } else if (z3) {
            if (this.F != null && this.F.isHeld()) {
                this.F.release();
                q.b("FgVoIP", "WAKE LOCKS mPartialTimeoutedWakeLock.release() ");
            }
        } else if (((!k.t() && z2) || !z2) && this.E != null && this.E.isHeld()) {
            this.E.release();
            q.b("FgVoIP", "WAKE LOCKS mPartialWakeLock.release() ");
        }
    }

    public void c(Activity activity) {
        Intent parentActivityIntent = NavUtils.getParentActivityIntent(activity);
        if (NavUtils.shouldUpRecreateTask(activity, parentActivityIntent)) {
            TaskStackBuilder.create(activity).addNextIntentWithParentStack(parentActivityIntent).startActivities();
            activity.finish();
            return;
        }
        parentActivityIntent.addFlags(67108864);
        activity.startActivity(parentActivityIntent);
        activity.finish();
    }

    public Class<? extends Activity> aH() {
        return InCallScreenActivity.class;
    }

    public Intent aI() {
        Intent intent = new Intent(l, aH());
        intent.setAction("InCallActions.UserPressedNotification");
        intent.setFlags(277086208);
        return intent;
    }

    public Intent a(String str, String str2, int i, boolean z, int i2) {
        Intent intent = new Intent(l, aH());
        intent.addFlags(268435456);
        if (z) {
            if (i2 == 1) {
                intent.setAction("InCallActions.ActionIncomingCallAcceptVideoReq");
            } else {
                intent.setAction("InCallActions.ActionIncomingCallAcceptReq");
            }
        } else if (i2 == 1) {
            intent.setAction("InCallActions.ActionIncomingVideoCallInd");
        } else {
            intent.setAction("InCallActions.ActionIncomingCallInd");
        }
        intent.putExtra("extra_uri_to_call", str);
        intent.putExtra("extra_display_name", str2);
        intent.putExtra("extra_session_id", i);
        return intent;
    }

    public static Intent a(String str, String str2, int i, int i2) {
        Intent intent = new Intent("com.fgmicrotec.mobile.android.voip.RejectInvitationReq");
        intent.putExtra("extra_uri_to_call", str);
        intent.putExtra("extra_display_name", str2);
        intent.putExtra("extra_session_id", i);
        intent.putExtra("extra_call_log_type", i2);
        return intent;
    }

    public static Intent aJ() {
        Intent intent = new Intent("com.mavenir.action.LAUNCH_MAIN_TAB");
        intent.putExtra("MainTabExtras.ExtraSelectedTabIndex", 1);
        return intent;
    }

    public static Intent aK() {
        Intent intent = new Intent(l, ClearNotificationActivity.class);
        intent.addFlags(268435456);
        return intent;
    }

    public void g(String str) {
        Intent intent = new Intent("android.intent.action.INSERT", Contacts.CONTENT_URI);
        intent.putExtra("finishActivityOnSaveCompleted", true);
        if (str != null) {
            intent.putExtra("phone", str);
        }
        intent.addFlags(268435456);
        startActivity(intent);
    }

    public void h(String str) {
        Intent intent = new Intent("android.intent.action.INSERT_OR_EDIT");
        intent.setType("vnd.android.cursor.item/contact");
        intent.putExtra("finishActivityOnSaveCompleted", true);
        if (str != null) {
            intent.putExtra("phone", str);
        }
        intent.addFlags(268435456);
        startActivity(intent);
    }

    public void a(FragmentActivity fragmentActivity, com.mavenir.android.fragments.f.a aVar, int i) {
        a(fragmentActivity, aVar, i, 0);
    }

    public void a(FragmentActivity fragmentActivity, com.mavenir.android.fragments.f.a aVar, int i, int i2) {
        a(fragmentActivity, aVar, i, i2, null);
    }

    public void a(FragmentActivity fragmentActivity, com.mavenir.android.fragments.f.a aVar, int i, int i2, String str) {
        a(fragmentActivity, aVar, i, i2, str, false);
    }

    public void a(FragmentActivity fragmentActivity, com.mavenir.android.fragments.f.a aVar, int i, int i2, String str, boolean z) {
        com.mavenir.android.fragments.f.a(aVar, i, i2, str, z).show(fragmentActivity.getSupportFragmentManager(), "ExceptionDialog");
    }

    public void a(com.mavenir.android.fragments.f.a aVar, int i) {
        a(aVar, i, 0, null);
    }

    public void a(com.mavenir.android.fragments.f.a aVar, String str) {
        a(aVar, -1, 0, str);
    }

    public void a(com.mavenir.android.fragments.f.a aVar, int i, int i2, String str) {
        Intent intent = new Intent(this, ExceptionDialogActivity.class);
        intent.putExtra("ExceptionDialogFragment.ExtraExceptionType", aVar);
        intent.putExtra("ExceptionDialogFragment.ExtraExceptionID", i);
        intent.putExtra("ExceptionDialogFragment.SipErrorCode", i2);
        intent.putExtra("ExceptionDialogFragment.ExtraErrorMessage", str);
        intent.addFlags(268435456);
        intent.addFlags(65536);
        startActivity(intent);
    }

    public void showSoftKeyboard(View view) {
        ((InputMethodManager) getSystemService("input_method")).showSoftInput(view, 1);
    }

    public void hideSoftKeyboard(View view) {
        ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 2);
    }

    public void a(Context context, String str) {
        if (ai()) {
            Intent intent = new Intent();
            intent.setClassName(context, "com.mavenir.android.settings.BackupService");
            intent.setAction(str);
            context.startService(intent);
        }
    }

    public void a(Context context, String str, int i) {
        if (ai()) {
            Intent intent = new Intent();
            intent.setClassName(context, "com.mavenir.android.settings.BackupService");
            intent.setAction(str);
            intent.putExtra("com.mavenir.android.extra_backup_reason", i);
            context.startService(intent);
        }
    }

    private void aP() {
        Intent intent = new Intent();
        intent.setAction("com.mavenir.android.rcs.CapabilityServiceQueryCapabilitiesReq");
        sendBroadcast(intent);
    }

    private void aQ() {
        Intent intent = new Intent();
        intent.setAction("com.mavenir.android.rcs.CapabilityServiceStopCapabilityDiscoveryReq");
        sendBroadcast(intent);
    }

    private void aR() {
        aC();
        Intent intent = new Intent();
        intent.setAction("MainTabActions.LoginStatusChanged");
        sendBroadcast(intent);
    }

    private void a(int i, int i2) {
        aC();
        Intent intent = new Intent();
        intent.setAction("MainTabActions.LoginStatusChanged");
        intent.putExtra("extra_error_code", i);
        intent.putExtra("extra_reason_code", i2);
        sendBroadcast(intent);
    }

    public boolean i(String str) {
        q.b("FgVoIP", "processNewIncomingCallIntent(): " + str);
        if (ad() != a.VToW && CallManager.a()) {
            Intent intent = new Intent(this, aH());
            intent.setAction("InCallActions.SupplementaryScreenCompleted");
            intent.setFlags(268435456);
            intent.putExtra("InCallExtras.ExtraSupplementaryNumber", str);
            startActivity(intent);
            return true;
        } else if (ad() == a.VToW && (!at() || as() == null)) {
            return false;
        } else {
            int j = k.j();
            if (at() && (j == 0 || ad() == a.VToW)) {
                if (this.N != null && this.N.equals(str) && SystemClock.elapsedRealtime() - this.O < ((long) K)) {
                    return true;
                }
                this.O = SystemClock.elapsedRealtime();
                this.N = str;
                this.v.postDelayed(new f(this, PhoneNumberUtils.stripSeparators(PhoneNumberUtils.convertKeypadLettersToDigits(str))), (long) L);
                return true;
            } else if (!at() || j != 2) {
                return false;
            } else {
                this.v.postDelayed(new c(this, str), (long) L);
                return true;
            }
        }
    }

    public void j(String str) {
        Intent intent;
        if (d(str)) {
            a(true);
            intent = new Intent(this, PreferenceMainActivity.class);
            Activity a = this.k.a();
            if (a != null) {
                q.a("FgVoIP", "starting engineering mode from current task");
                a.startActivity(intent);
                return;
            }
            q.a("FgVoIP", "starting engineering mode from new task");
            intent.setFlags(268435456);
            startActivity(intent);
        } else if (!T() || !com.mavenir.android.common.g.a().f()) {
            if (!CallManager.a() && CallService.t() != -1) {
                Toast.makeText(getApplicationContext(), "Not Allowed.", 0).show();
            } else if (str.length() > 0) {
                String b;
                q.b("FgVoIP", "initiateTheCall(): number: " + str);
                if (getString(f.k.app_name).startsWith("Three")) {
                    b = com.mavenir.android.common.t.f.b(str);
                } else {
                    b = com.mavenir.android.common.t.f.c(str);
                }
                b = com.mavenir.android.common.t.f.a(b, com.mavenir.android.settings.c.c.b(), com.mavenir.android.settings.c.v.g());
                q.b("FgVoIP", "initiateTheCall(): calling URI: " + b);
                String f = com.mavenir.android.common.t.f.f(b);
                if (f.length() == 0) {
                    Toast.makeText(this, getString(f.k.call_invalid_number), 0).show();
                } else if (PhoneNumberUtils.compare(f, aA())) {
                    Toast.makeText(this, getString(f.k.call_own_number), 0).show();
                } else if (q() && CallManager.a()) {
                    sendBroadcast(new Intent("MainTabActions.ActionStopActivityIncomingCall"));
                    intent = new Intent(this, aH());
                    intent.setAction("InCallActions.SupplementaryScreenCompleted");
                    intent.addFlags(268435456);
                    intent.putExtra("InCallExtras.ExtraSupplementaryNumber", str);
                    intent.putExtra("InCallExtras.ExtraSupplementaryType", 0);
                    startActivity(intent);
                } else if (ad() == a.VToW) {
                    b(com.mavenir.android.common.t.f.f(b), b);
                } else if (U().aj() && com.mavenir.android.common.t.f.f(b).startsWith("*")) {
                    o(b);
                } else {
                    l(b);
                }
            }
        }
    }

    public void k(String str) {
        if (str.length() > 0) {
            String a = com.mavenir.android.common.t.f.a(com.mavenir.android.common.t.f.a(str), com.mavenir.android.settings.c.c.b(), com.mavenir.android.settings.c.v.g());
            if (q() && CallManager.a()) {
                sendBroadcast(new Intent("MainTabActions.ActionStopActivityIncomingCall"));
                Intent intent = new Intent(this, aH());
                intent.setAction("InCallActions.SupplementaryScreenCompleted");
                intent.addFlags(268435456);
                intent.putExtra("InCallExtras.ExtraSupplementaryNumber", str);
                intent.putExtra("InCallExtras.ExtraSupplementaryType", 1);
                startActivity(intent);
                return;
            }
            Intent intent2 = new Intent(this, aH());
            intent2.setAction("InCallActions.ActionOutgoingVideoCall");
            intent2.addFlags(268435456);
            intent2.putExtra(com.fgmicrotec.mobile.android.fgvoip.a.a.b, a);
            startActivity(intent2);
        }
    }

    public void a(ArrayList<String> arrayList) {
        Intent intent = new Intent(this, aH());
        intent.setAction("InCallActions.ActionConferenceCall");
        intent.addFlags(268435456);
        intent.putStringArrayListExtra("InCallExtras.ExtraConferenceParticipants", arrayList);
        startActivity(intent);
    }

    public void b(String str, String str2) {
        if (k() && str.startsWith("*")) {
            if (com.mavenir.android.settings.c.c.g()) {
                o(str2);
            } else {
                m(str);
            }
        } else if (e(str)) {
            if (!x() || l.a((Context) this).F()) {
                n(str);
            } else if (CallService.s()) {
                q.a("FgVoIP", "initiateCallVToW(): native call in progress, aborting VoIP call");
            } else {
                q.a("FgVoIP", "initiateCallVToW(): starting emergency voip call: " + str2);
                u = true;
                l(str2);
            }
        } else if (!at() || f(str) || as() == null) {
            m(str);
        } else {
            l(str2);
        }
    }

    public void l(String str) {
        Intent intent = new Intent(this, aH());
        intent.setAction("InCallActions.ActionOutgoingCall");
        intent.addFlags(268435456);
        intent.putExtra(com.fgmicrotec.mobile.android.fgvoip.a.a.b, str);
        startActivity(intent);
    }

    public void m(String str) {
        this.o = true;
        Uri parse = Uri.parse("tel:" + Uri.encode(str));
        q.a("FgVoIP", "initiateTheCall(): routing call to CS: " + parse.toString());
        Intent intent = new Intent("android.intent.action.CALL");
        intent.setData(parse);
        intent.addFlags(268435456);
        startActivity(intent);
    }

    public void n(String str) {
        this.o = true;
        Uri parse = Uri.parse("tel:" + Uri.encode(str));
        q.a("FgVoIP", "initiateTheCall(): routing call to CS: " + parse.toString());
        Intent intent = new Intent("android.intent.action.DIAL");
        intent.setData(parse);
        intent.addFlags(268435456);
        startActivity(intent);
    }

    public void o(String str) {
        Intent intent = new Intent();
        intent.setAction("com.fgmicrotec.mobile.android.voip.CallInviteReq");
        intent.putExtra("extra_uri_to_call", str);
        sendBroadcast(intent);
    }

    public boolean aL() {
        if (g == null || !g.delete()) {
            q.b("FgVoIP", "Failed to delete trace file: " + g);
            return false;
        }
        q.b("FgVoIP", "Trace file deleted: " + g);
        return true;
    }
}
