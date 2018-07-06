package com.fgmicrotec.mobile.android.fgvoipcommon;

import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.database.Cursor;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.SystemClock;
import android.provider.CallLog.Calls;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.o;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.fgmicrotec.mobile.android.fgmag.DataConnectionManager;
import com.fgmicrotec.mobile.android.fgmag.FgSDKLoader;
import com.fgmicrotec.mobile.android.fgmag.SimpleCodecAL;
import com.fgmicrotec.mobile.android.fgmag.VoIP;
import com.fgmicrotec.mobile.android.fgvoip.FgVoIP;
import com.mavenir.android.common.CallManager;
import com.mavenir.android.common.DeviceStateReceiver;
import com.mavenir.android.common.aa;
import com.mavenir.android.common.l;
import com.mavenir.android.common.m;
import com.mavenir.android.common.q;
import com.mavenir.android.common.w;
import com.mavenir.android.common.y;
import com.mavenir.android.settings.c.n;
import com.mavenir.android.settings.c.p;
import com.mavenir.android.settings.c.r;
import com.mavenir.android.settings.c.s;
import com.mavenir.android.settings.c.v;
import com.mavenir.android.settings.c.x;
import com.mavenir.android.vtow.activation.ActivationAdapter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.security.cert.Certificate;
import java.util.Vector;

public class CallService extends Service implements com.fgmicrotec.mobile.android.fgmag.b {
    private static boolean A = false;
    private static boolean B = false;
    private static boolean C = false;
    private static boolean D = false;
    private static com.fgmicrotec.mobile.android.fgmag.DataConnectionManager.a E = com.fgmicrotec.mobile.android.fgmag.DataConnectionManager.a.ANY;
    private static String F = "";
    private static TelephonyManager G = null;
    private static CallServiceBroadcastReceiver H = null;
    private static CallServiceBroadcastReceiver I = null;
    private static PackageChangeBroadcastReceiver J = null;
    private static DeviceStateReceiver K = null;
    private static m L = null;
    private static boolean N = false;
    public static long a = -1;
    private static boolean aA = false;
    private static int ar = -1;
    public static boolean b = false;
    static final String[] c = new String[]{"number", "display_name"};
    private static boolean y = false;
    private static boolean z = false;
    private b M = null;
    private boolean O = false;
    private boolean P = false;
    private boolean Q = false;
    private FgSDKLoader R = null;
    private VoIP S = null;
    private Handler T = null;
    private Handler U = null;
    private Handler V = null;
    private Handler W = null;
    private Handler X = null;
    private Handler Y = null;
    private int Z = 0;
    private boolean aB = false;
    private long aC;
    private final IBinder aD = new h(this);
    private Runnable aE = new Runnable(this) {
        final /* synthetic */ CallService a;

        {
            this.a = r1;
        }

        public void run() {
            this.a.a(System.currentTimeMillis(), l.a(this.a).M());
        }
    };
    private Runnable aF = new Runnable(this) {
        final /* synthetic */ CallService a;

        {
            this.a = r1;
        }

        public void run() {
            if (CallService.ar > 0) {
                q.a("CallService", "incomingCallInvitationTimeoutRunnable(): session=" + CallService.ar + ", CallerURI=" + this.a.as + ", CallerName=" + this.a.at);
                Intent intent = new Intent();
                intent.setAction("com.fgmicrotec.mobile.android.voip.RejectInvitationReq");
                intent.putExtra("extra_session_id", CallService.ar);
                intent.putExtra("extra_uri_to_call", this.a.as);
                intent.putExtra("extra_display_name", this.a.at);
                intent.putExtra("extra_call_log_type", 3);
                this.a.sendBroadcast(intent);
            }
        }
    };
    private Runnable aG = new Runnable(this) {
        final /* synthetic */ CallService a;

        {
            this.a = r1;
        }

        public void run() {
            this.a.R();
        }
    };
    private Runnable aH = new Runnable(this) {
        final /* synthetic */ CallService a;

        {
            this.a = r1;
        }

        public void run() {
            q.b("CallService", "CallService - stopSelf()");
            FgVoIP.U().b();
            this.a.stopSelf();
        }
    };
    private Runnable aI = new Runnable(this) {
        final /* synthetic */ CallService a;

        {
            this.a = r1;
        }

        public void run() {
            this.a.T.removeCallbacks(this.a.aI);
            if (CallService.A) {
                this.a.af = 0;
                return;
            }
            this.a.af = this.a.af * 2;
            this.a.T.postDelayed(this.a.aI, (long) this.a.af);
            this.a.W();
            q.b("CallService", "handleRecoveryLoginAttempt mRecoveryLoginCurrentTimeout = " + this.a.af);
        }
    };
    private Runnable aJ = new Runnable(this) {
        final /* synthetic */ CallService a;

        {
            this.a = r1;
        }

        public void run() {
            this.a.T.removeCallbacks(this.a.aJ);
            if (CallService.A) {
                this.a.X();
                this.a.T.removeCallbacks(this.a.aI);
                this.a.T.postDelayed(this.a.aI, 5000);
            }
        }
    };
    private Runnable aK = new Runnable(this) {
        final /* synthetic */ CallService a;

        {
            this.a = r1;
        }

        public void run() {
            int ordinal = com.fgmicrotec.mobile.android.fgmag.VoIP.d.FGVOIPCPROXY_POPUP_LOCATION_ISSUE.ordinal();
            this.a.T.removeCallbacks(this.a.aK);
            this.a.av = true;
            q.b("CallService", "handleNotifyRegTerminatedInd: DeRegistering... Retry Login Timer (milliseconds) = " + 30000);
            this.a.T.postDelayed(this.a.aL, (long) 30000);
            if (CallService.A) {
                this.a.X();
                FgVoIP.U().a(com.mavenir.android.fragments.f.a.VOIPC_PROXY, ordinal, 0, com.mavenir.android.settings.c.j.a(ordinal));
            }
        }
    };
    private Runnable aL = new Runnable(this) {
        final /* synthetic */ CallService a;

        {
            this.a = r1;
        }

        public void run() {
            q.b("CallService", "handleLoginAfterNotifyTerminatedTimeout");
            this.a.T.removeCallbacks(this.a.aL);
            com.mavenir.android.fragments.f.a();
            this.a.av = false;
            if (!CallService.A) {
                this.a.W();
            }
        }
    };
    private Runnable aM = new Runnable(this) {
        final /* synthetic */ CallService a;

        {
            this.a = r1;
        }

        public void run() {
            boolean z = true;
            int i = FgVoIP.U().an() ? 1 : 0;
            com.fgmicrotec.mobile.android.fgmag.DataConnectionManager.a H = l.a(this.a).H();
            boolean z2;
            if (CallService.E == com.fgmicrotec.mobile.android.fgmag.DataConnectionManager.a.LTE && H == com.fgmicrotec.mobile.android.fgmag.DataConnectionManager.a.WLAN) {
                z2 = true;
            } else {
                z2 = false;
            }
            boolean z3;
            if (CallService.E != H) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (CallService.ar > 0 && (z2 || z3)) {
                i = 0;
            }
            String str = "CallService";
            StringBuilder append = new StringBuilder().append("handleLoginAllowedQueryInd: ").append(i).append(", bearer: ").append(H.name()).append(" (registered bearer: ").append(CallService.E.name()).append("), call: ");
            if (CallService.ar <= 0) {
                z = false;
            }
            q.b(str, append.append(z).toString());
            if (!CallService.l()) {
                this.a.S.loginAllowedQueryRes(i, H.ordinal());
            }
        }
    };
    private Runnable aN = new Runnable(this) {
        final /* synthetic */ CallService a;

        {
            this.a = r1;
        }

        public void run() {
            if (p.d() != null) {
                String q = p.q();
                int r = p.r();
                q.a("CallService", "setUserInfoReq(): UUID: " + q + ", regID: " + r);
                this.a.S.setUserInfoReq(p.e(), p.f(), p.g(), p.h(), q, r, p.o());
                q.b("CallService", "handleSetUserInfo: getNetworkIMEI: " + p.o());
                return;
            }
            q.d("CallService", "handleSetUserInfo: No active profile!");
            this.a.S.setUserInfoReq(" ", " ", " ", " ", " ", 0, " ");
        }
    };
    private Runnable aO = new Runnable(this) {
        final /* synthetic */ CallService a;

        {
            this.a = r1;
        }

        public void run() {
            if (this.a.aa) {
                this.a.aa = false;
                this.a.b(true, this.a.ab);
            }
        }
    };
    private Runnable aP = new Runnable(this) {
        final /* synthetic */ CallService a;

        {
            this.a = r1;
        }

        public void run() {
            int ordinal = com.fgmicrotec.mobile.android.fgmag.VoIP.c.FGVOIPCPROXY_NOT_AVAILABLE.ordinal();
            if (!FgVoIP.U().an() && this.a.ad) {
                ordinal = com.fgmicrotec.mobile.android.fgmag.VoIP.c.FGVOIPCPROXY_OK.ordinal();
            }
            this.a.S.unregisterConnNeededRes(ordinal, l.a(this.a).H().ordinal());
        }
    };
    private Runnable aQ = new Runnable(this) {
        final /* synthetic */ CallService a;

        {
            this.a = r1;
        }

        public void run() {
            if (com.mavenir.android.settings.c.k.t() && FgVoIP.U().ai()) {
                FgVoIP.U().a(true, false, false);
            }
            this.a.S.loginReq(l.a(this.a).H().ordinal());
        }
    };
    private Runnable aR = new Runnable(this) {
        final /* synthetic */ CallService a;

        {
            this.a = r1;
        }

        public void run() {
            q.b("CallService", "handlePeriodicLoginCheck  mLoggedInToTheServer = " + CallService.A);
            if (!CallService.A && FgVoIP.U().aj()) {
                this.a.W();
            }
            this.a.T.postDelayed(this.a.aR, 300000);
        }
    };
    private Runnable aS = new Runnable(this) {
        final /* synthetic */ CallService a;

        {
            this.a = r1;
        }

        public void run() {
            Intent intent = new Intent("com.mavenir.action.LAUNCH_MAIN_TAB");
            intent.setFlags(268435456);
            this.a.startActivity(intent);
        }
    };
    private Runnable aT = new Runnable(this) {
        final /* synthetic */ CallService a;

        {
            this.a = r1;
        }

        public void run() {
            int i;
            int i2 = 144;
            Display defaultDisplay = ((WindowManager) this.a.getSystemService("window")).getDefaultDisplay();
            int width = defaultDisplay.getWidth();
            int height = defaultDisplay.getHeight();
            DisplayMetrics displayMetrics = new DisplayMetrics();
            defaultDisplay.getMetrics(displayMetrics);
            q.b("CallService", "Obtained screen densities: xdpi = " + displayMetrics.xdpi + " ydpi = " + displayMetrics.ydpi);
            q.b("CallService", "Obtained screen dimensions: width = " + width + " height = " + height);
            int i3 = -1;
            if (com.mavenir.android.settings.c.k.k()) {
                i3 = com.mavenir.android.settings.c.m.l();
            }
            if (com.mavenir.android.settings.c.m.m() == 0) {
                if (i3 == 0) {
                    i = NotificationCompat.FLAG_HIGH_PRIORITY;
                    i2 = 96;
                } else {
                    i = 160;
                    i2 = 120;
                }
            } else if (com.mavenir.android.settings.c.m.m() == 1) {
                i = 176;
            } else if (com.mavenir.android.settings.c.m.m() == 2) {
                if (i3 == 0) {
                    i2 = 288;
                    i = 352;
                } else {
                    i = 320;
                    i2 = 240;
                }
            } else if (com.mavenir.android.settings.c.m.m() == 3) {
                if (i3 == 0) {
                    i = 704;
                    i2 = 576;
                } else {
                    i2 = 288;
                    i = 352;
                }
            } else if (com.mavenir.android.settings.c.m.m() != 4) {
                i = 176;
            } else if (i3 == 0) {
                i = 1408;
                i2 = 1152;
            } else {
                i = 640;
                i2 = VoIP.REASON_CODE_CALLEE_TEMP_UNAVAILABLE;
            }
            q.b("CallService", "Selected video codec = " + i3);
            q.b("CallService", "Selected video size  nSelectedVideoWidth = " + i + " nSelectedVideoHeight " + i2);
            this.a.T.removeCallbacks(this.a.aT);
            this.a.S.setVideoCallPreferencesReq(i3, width, height, i, i2);
        }
    };
    private Runnable aU = new Runnable(this) {
        final /* synthetic */ CallService a;

        {
            this.a = r1;
        }

        public void run() {
            if (CallManager.a()) {
                this.a.S.qosPaceForExternalMediaEngine();
                this.a.T.postDelayed(this.a.aU, 5500);
                return;
            }
            this.a.T.removeCallbacks(this.a.aU);
        }
    };
    private Runnable aV = new Runnable(this) {
        final /* synthetic */ CallService a;

        {
            this.a = r1;
        }

        public void run() {
            this.a.sendBroadcast(new Intent("com.fgmicrotec.mobile.android.voip.ShouldDisplayVideoViewInd"));
        }
    };
    private Runnable aW = new Runnable(this) {
        final /* synthetic */ CallService a;

        {
            this.a = r1;
        }

        public void run() {
            if (FgVoIP.U().ad() != com.fgmicrotec.mobile.android.fgvoip.FgVoIP.a.VToW) {
                this.a.W.removeCallbacks(this.a.aW);
                this.a.W.postDelayed(this.a.aW, 86400000);
                this.a.ap();
            }
        }
    };
    private Runnable aX = new Runnable(this) {
        final /* synthetic */ CallService a;

        {
            this.a = r1;
        }

        public void run() {
            this.a.al();
        }
    };
    private boolean aa;
    private int ab;
    private boolean ac = false;
    private boolean ad;
    private int ae;
    private int af;
    private i ag;
    private a ah;
    private boolean ai;
    private int aj;
    private final int ak = 5000;
    private final int al = 5000;
    private final int am = 5000;
    private Vector<k> an;
    private final int ao = 10;
    private long ap;
    private boolean aq = true;
    private String as = null;
    private String at = null;
    private boolean au = false;
    private boolean av = false;
    private j aw = j.VPN_DISCONNECTED;
    private boolean ax;
    private boolean ay;
    private long az;
    private final int d = 2000;
    private final int e = 5000;
    private final String f = "*62";
    private final int g = 5000;
    private final int h = 5000;
    private final int i = 120000;
    private final int j = 300000;
    private final String k = "http://app.mavenir.com/Android/android2.txt";
    private final String l = "vowVer.txt";
    private final String m = "http://app.mavenir.com/install/index.php";
    private final int n = 86400000;
    private final int o = 15000;
    private final int p = 3000;
    private final String q = "app.mavenir.com/provisioning-server/server2.php";
    private final String r = "com.mavenir.android.action.qosRinging";
    private final String s = "com.mavenir.android.action.qosStatsDeterioted";
    private final String t = "com.mavenir.android.action.qosStatsImproved";
    private final String u = "com.mavenir.android.action.qosCallEstablished";
    private final String v = "com.mavenir.android.action.qosCallEnded";
    private String w;
    private String x;

    public static class CallServiceBroadcastReceiver extends BroadcastReceiver {
        public void onReceive(Context context, Intent intent) {
            Intent intent2 = new Intent(context, CallService.class);
            intent2.setAction(intent.getAction());
            if (intent.getExtras() != null) {
                intent2.putExtras(intent.getExtras());
            }
            context.startService(intent2);
        }
    }

    public static class PackageChangeBroadcastReceiver extends BroadcastReceiver {
        public void onReceive(Context context, Intent intent) {
            if (intent != null && intent.getAction().equals("android.intent.action.PACKAGE_CHANGED") && VERSION.SDK_INT < 16) {
                q.a("CallService", "Received ACTION_PACKAGE_CHANGED, resetting Wifi notification...");
                com.fgmicrotec.mobile.android.fgvoip.e.a().a(CallService.A, l.a(context).M(), true);
            }
        }
    }

    public enum a {
        TYPE_SMS,
        TYPE_PIN,
        TYPE_REQUEST_PIN
    }

    private class b extends BroadcastReceiver {
        final /* synthetic */ CallService a;

        private b(CallService callService) {
            this.a = callService;
        }

        /* synthetic */ b(CallService callService, AnonymousClass1 anonymousClass1) {
            this(callService);
        }

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (!CallService.z) {
                if (action.equals("android.net.conn.CONNECTIVITY_CHANGE")) {
                    this.a.ad = !intent.getBooleanExtra("noConnectivity", false);
                    NetworkInfo networkInfo = (NetworkInfo) intent.getParcelableExtra("networkInfo");
                    int C = this.a.ae;
                    if (networkInfo != null) {
                        this.a.ae = networkInfo.getType();
                    }
                    if (FgVoIP.U().ai() && com.mavenir.android.settings.c.k.d() > 0) {
                        FgVoIP.U().d(this.a.ad);
                        boolean I = l.a(context).I();
                        if (this.a.ad) {
                            com.mavenir.android.applog.a.a(context).c(false);
                        } else if (I) {
                            com.mavenir.android.applog.a.a(context).c(true);
                        }
                        Intent intent2 = new Intent();
                        intent2.setAction("com.mavenir.android.ActionConectivityChange");
                        String str = "noConnectivity";
                        if (this.a.ad) {
                            I = false;
                        } else {
                            I = true;
                        }
                        intent2.putExtra(str, I);
                        context.sendBroadcast(intent2);
                    }
                    CallService.B = false;
                    if (this.a.ad) {
                        q.b("CallService", "Received connectivity action indication. CONNECTED! mCurrentConnectionBearer " + l.a(context).b(C) + " changes to " + l.a(context).b(this.a.ae));
                        CallService.b(false);
                        if (com.fgmicrotec.mobile.android.fgvoip.e.a() != null) {
                            com.fgmicrotec.mobile.android.fgvoip.e.a().a(CallService.m(), l.a(context).M());
                        }
                        if (C != 0 && this.a.ae == 0) {
                            DataConnectionManager.clearAllConnectionsData();
                        }
                        if (this.a.ae == 1) {
                            InetAddress localIpAddress = DataConnectionManager.getLocalIpAddress();
                            if (localIpAddress != null) {
                                action = localIpAddress.getHostAddress();
                                q.b("CallService", "Received connectivity action indication. CONNECTED! Local IP = " + action);
                                this.a.d(action);
                            }
                        }
                        FgVoIP.U().az();
                        if (this.a.ae == 1) {
                            com.mavenir.android.applog.a.a(this.a.getApplicationContext()).g();
                        }
                        if (!FgVoIP.U().an()) {
                            return;
                        }
                        if (com.mavenir.android.settings.c.k.d() > 0) {
                            if (!CallService.A && (this.a.af == 0 || this.a.af > 10000)) {
                                this.a.af = 5000;
                                this.a.T.removeCallbacks(this.a.aI);
                                this.a.T.postDelayed(this.a.aI, 5000);
                            }
                            if (!this.a.ai) {
                                this.a.W.postDelayed(this.a.aW, 15000);
                                return;
                            }
                            return;
                        }
                        q.b("CallService", "Received connectivity action indication. CONNECTED! but  General.getProvisioningVers() = " + com.mavenir.android.settings.c.k.d());
                        return;
                    }
                    if (FgVoIP.U().ai()) {
                        CallService.b(true);
                    }
                    if (this.a.ae == 0 || this.a.ae == 1) {
                        this.a.af = 0;
                        this.a.T.removeCallbacks(this.a.aI);
                        q.b("CallService", "Received connectivity action indication. DISCONNECTED! ");
                        if (y.a().d() && y.a().e()) {
                            q.a("CallService", "ConnectionStatusChangeReceiver: mLoggedInToTheServer = " + CallService.A);
                        } else {
                            q.a("CallService", "ConnectionStatusChangeReceiver: mLoggedInToTheServer = " + CallService.A);
                        }
                    }
                    this.a.S();
                } else if (action.equals("android.net.wifi.RSSI_CHANGED")) {
                    if (!CallService.z && CallService.E != com.fgmicrotec.mobile.android.fgmag.DataConnectionManager.a.LTE) {
                        int intExtra = intent.getIntExtra("newRssi", 0);
                        if (!(CallService.p() || this.a.getResources().getString(com.fgmicrotec.mobile.android.fgvoip.f.k.app_name).equals("Three inTouch") || com.fgmicrotec.mobile.android.fgvoip.e.a() == null)) {
                            com.fgmicrotec.mobile.android.fgvoip.e.a().a(CallService.m(), intExtra);
                        }
                        this.a.S.setWifiSignalStrengthInd(intExtra);
                        this.a.a(System.currentTimeMillis(), intExtra);
                    }
                } else if (intent.getAction().equals("android.intent.action.TIMEZONE_CHANGED")) {
                    q.a("CallService", "ConnectionStatusChangeReceiver: Time zone changed: " + l.a(context).L());
                    com.mavenir.android.applog.a.a(context).f();
                }
            }
        }
    }

    private class c implements Runnable {
        final /* synthetic */ CallService a;
        private int b;
        private String c;

        c(CallService callService, int i, String str) {
            this.a = callService;
            this.b = i;
            this.c = str;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
            r8 = this;
            r0 = r8.a;
            r1 = 1;
            r0.ai = r1;
            r0 = r8.b;
            if (r0 != 0) goto L_0x008d;
        L_0x000a:
            r2 = new java.io.File;
            r0 = r8.c;
            r2.<init>(r0);
            if (r2 == 0) goto L_0x008d;
        L_0x0013:
            r0 = r2.exists();
            if (r0 == 0) goto L_0x008d;
        L_0x0019:
            r1 = 0;
            r0 = new java.io.FileInputStream;	 Catch:{ Exception -> 0x00a8, all -> 0x00ca }
            r0.<init>(r2);	 Catch:{ Exception -> 0x00a8, all -> 0x00ca }
            r1 = r0.available();	 Catch:{ Exception -> 0x00f0, all -> 0x00eb }
            r1 = new byte[r1];	 Catch:{ Exception -> 0x00f0, all -> 0x00eb }
            r0.read(r1);	 Catch:{ Exception -> 0x00f0, all -> 0x00eb }
            r3 = new java.lang.String;	 Catch:{ Exception -> 0x00f2, all -> 0x00eb }
            r4 = "ISO-8859-1";
            r3.<init>(r1, r4);	 Catch:{ Exception -> 0x00f2, all -> 0x00eb }
            r1 = "CallService";
            r4 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00f2, all -> 0x00eb }
            r4.<init>();	 Catch:{ Exception -> 0x00f2, all -> 0x00eb }
            r5 = "Update available - version = ";
            r4 = r4.append(r5);	 Catch:{ Exception -> 0x00f2, all -> 0x00eb }
            r5 = r3.toString();	 Catch:{ Exception -> 0x00f2, all -> 0x00eb }
            r4 = r4.append(r5);	 Catch:{ Exception -> 0x00f2, all -> 0x00eb }
            r4 = r4.toString();	 Catch:{ Exception -> 0x00f2, all -> 0x00eb }
            com.mavenir.android.common.q.b(r1, r4);	 Catch:{ Exception -> 0x00f2, all -> 0x00eb }
            r1 = r8.a;	 Catch:{ Exception -> 0x00f2, all -> 0x00eb }
            r1 = com.mavenir.android.common.l.a(r1);	 Catch:{ Exception -> 0x00f2, all -> 0x00eb }
            r4 = 0;
            r1 = r1.a(r4);	 Catch:{ Exception -> 0x00f2, all -> 0x00eb }
            r4 = "CallService";
            r5 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00f2, all -> 0x00eb }
            r5.<init>();	 Catch:{ Exception -> 0x00f2, all -> 0x00eb }
            r6 = "installedVersion = ";
            r5 = r5.append(r6);	 Catch:{ Exception -> 0x00f2, all -> 0x00eb }
            r5 = r5.append(r1);	 Catch:{ Exception -> 0x00f2, all -> 0x00eb }
            r5 = r5.toString();	 Catch:{ Exception -> 0x00f2, all -> 0x00eb }
            com.mavenir.android.common.q.b(r4, r5);	 Catch:{ Exception -> 0x00f2, all -> 0x00eb }
            r4 = r3.length();	 Catch:{ Exception -> 0x00f2, all -> 0x00eb }
            r5 = 10;
            if (r4 >= r5) goto L_0x0085;
        L_0x0076:
            r3 = r3.toString();	 Catch:{ Exception -> 0x00f2, all -> 0x00eb }
            r1 = r1.equals(r3);	 Catch:{ Exception -> 0x00f2, all -> 0x00eb }
            if (r1 != 0) goto L_0x0085;
        L_0x0080:
            r1 = r8.a;	 Catch:{ Exception -> 0x00f2, all -> 0x00eb }
            r1.aq();	 Catch:{ Exception -> 0x00f2, all -> 0x00eb }
        L_0x0085:
            if (r0 == 0) goto L_0x008a;
        L_0x0087:
            r0.close();	 Catch:{ IOException -> 0x008e }
        L_0x008a:
            r2.delete();
        L_0x008d:
            return;
        L_0x008e:
            r0 = move-exception;
            r1 = "CallService";
            r3 = new java.lang.StringBuilder;
            r3.<init>();
            r4 = "HandleHttpDownloadCnf: run(): unable to close stream: ";
            r3 = r3.append(r4);
            r0 = r3.append(r0);
            r0 = r0.toString();
            com.mavenir.android.common.q.d(r1, r0);
            goto L_0x008a;
        L_0x00a8:
            r0 = move-exception;
            r0 = r1;
        L_0x00aa:
            if (r0 == 0) goto L_0x008a;
        L_0x00ac:
            r0.close();	 Catch:{ IOException -> 0x00b0 }
            goto L_0x008a;
        L_0x00b0:
            r0 = move-exception;
            r1 = "CallService";
            r3 = new java.lang.StringBuilder;
            r3.<init>();
            r4 = "HandleHttpDownloadCnf: run(): unable to close stream: ";
            r3 = r3.append(r4);
            r0 = r3.append(r0);
            r0 = r0.toString();
            com.mavenir.android.common.q.d(r1, r0);
            goto L_0x008a;
        L_0x00ca:
            r0 = move-exception;
        L_0x00cb:
            if (r1 == 0) goto L_0x00d0;
        L_0x00cd:
            r1.close();	 Catch:{ IOException -> 0x00d1 }
        L_0x00d0:
            throw r0;
        L_0x00d1:
            r1 = move-exception;
            r2 = "CallService";
            r3 = new java.lang.StringBuilder;
            r3.<init>();
            r4 = "HandleHttpDownloadCnf: run(): unable to close stream: ";
            r3 = r3.append(r4);
            r1 = r3.append(r1);
            r1 = r1.toString();
            com.mavenir.android.common.q.d(r2, r1);
            goto L_0x00d0;
        L_0x00eb:
            r1 = move-exception;
            r7 = r1;
            r1 = r0;
            r0 = r7;
            goto L_0x00cb;
        L_0x00f0:
            r1 = move-exception;
            goto L_0x00aa;
        L_0x00f2:
            r1 = move-exception;
            goto L_0x0085;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.fgmicrotec.mobile.android.fgvoipcommon.CallService.c.run():void");
        }
    }

    private class d implements Runnable {
        final /* synthetic */ CallService a;
        private int b;
        private String c;

        public d(CallService callService, int i, String str) {
            this.a = callService;
            this.b = i;
            this.c = str;
        }

        public void run() {
            q.a("CallService", "sent provisioningAuthRes(): " + (this.b == a.TYPE_REQUEST_PIN.ordinal() ? "TYPE_PIN" : "TYPE_SMS"));
            if (this.b != a.TYPE_REQUEST_PIN.ordinal()) {
                Intent intent = new Intent();
                intent.setAction("ActivationActions.ActionStartProvisioningProgress");
                this.a.sendBroadcast(intent);
                this.a.S.provisioningAuthRes(this.b, this.c, this.a.x);
                return;
            }
            intent = new Intent();
            intent.setAction("ActivationActions.ActionProvisioningRequestPin");
            intent.putExtra("ActivationExtras.ExtraPrivateURI", this.a.w);
            intent.putExtra("ActivationExtras.ExtraAuthType", this.b);
            intent.putExtra("ActivationExtras.ExtraTAN", this.c);
            this.a.sendBroadcast(intent);
        }
    }

    private class e implements Runnable {
        final /* synthetic */ CallService a;
        private byte[] b;
        private byte[][] c;
        private boolean d;

        public e(CallService callService, byte[] bArr, byte[][] bArr2, boolean z) {
            this.a = callService;
            this.b = bArr;
            this.c = bArr2;
            this.d = z;
        }

        public void run() {
            new Thread(new Runnable(this) {
                final /* synthetic */ e a;

                {
                    this.a = r1;
                }

                public void run() {
                    if (!CallService.l()) {
                        com.fgmicrotec.mobile.android.fgmag.VoIP.e eVar = com.fgmicrotec.mobile.android.fgmag.VoIP.e.FGTLS_ERROR_NOT_TRUSTED;
                        try {
                            Certificate[] a = com.mavenir.android.security.b.a().a(this.a.c);
                            if (a != null) {
                                eVar = com.mavenir.android.security.b.a().a(a, this.a.d);
                                if (FgVoIP.U().aj() && eVar == com.fgmicrotec.mobile.android.fgmag.VoIP.e.FGTLS_ERROR_SELF_SIGNED) {
                                    q.b("CallService", "HandleTlsVerifyCertCnf: allowing self signed certificate (changing code to OK)");
                                    eVar = com.fgmicrotec.mobile.android.fgmag.VoIP.e.FGTLS_OK;
                                }
                            }
                        } catch (Throwable th) {
                            q.c("CallService", "HandleTlsVerifyCertCnf: " + th.getLocalizedMessage(), th);
                        }
                        this.a.a.S.tlsVerifyCertCnf(this.a.b, eVar.ordinal());
                    }
                }
            }).start();
        }
    }

    private class f implements Runnable {
        int a;
        int b;
        final /* synthetic */ CallService c;
        private String d;

        public f(CallService callService, int i, String str, int i2) {
            this.c = callService;
            this.a = i;
            this.d = str;
            this.b = i2;
        }

        public void run() {
            Intent intent = new Intent();
            intent.setAction("USSIDialogActions.StringReceivedInd");
            intent.putExtra("USSIDialogExtras.ExtraUSSIResultCode", this.a);
            intent.putExtra("USSIDialogExtras.ExtraUSSIString", this.d);
            intent.putExtra("USSIDialogExtras.ExtraUSSIResponseExpected", this.b);
            intent.addFlags(268435456);
            this.c.startActivity(intent);
        }
    }

    private class g implements Runnable {
        final /* synthetic */ CallService a;
        private String b;
        private String c;
        private int d;
        private int e;

        public g(CallService callService, String str, String str2, int i, int i2) {
            this.a = callService;
            this.b = str;
            this.c = str2;
            this.d = i;
            this.e = i2;
        }

        public void run() {
            this.a.S.userCallQualityRatingRes(this.e, this.d, this.b, this.c);
        }
    }

    public class h extends Binder {
        final /* synthetic */ CallService a;

        public h(CallService callService) {
            this.a = callService;
        }
    }

    private class i implements b {
        final /* synthetic */ CallService a;

        private i(CallService callService) {
            this.a = callService;
        }

        /* synthetic */ i(CallService callService, AnonymousClass1 anonymousClass1) {
            this(callService);
        }

        public void a(int i, int i2, String str) {
            this.a.W.post(new c(this.a, i, str));
        }

        public void a(int i, int i2) {
        }
    }

    public enum j {
        VPN_DISCONNECTED,
        VPN_CONNECTING,
        VPN_CONNECTED,
        VPN_CONNECTION_ERROR
    }

    private class k {
        final /* synthetic */ CallService a;
        private long b;
        private int c;

        public k(CallService callService, long j, int i) {
            this.a = callService;
            this.b = j;
            this.c = i;
        }

        public long a() {
            return this.b;
        }

        public int b() {
            return this.c;
        }
    }

    static {
        if (FgVoIP.U().aj() || FgVoIP.U().ai()) {
            try {
                System.loadLibrary("media_manager_gcc.armv7n");
            } catch (UnsatisfiedLinkError e) {
            }
            try {
                System.loadLibrary("stlport_shared");
            } catch (UnsatisfiedLinkError e2) {
            }
            System.loadLibrary("osip2");
            System.loadLibrary("fgVoIP");
        }
    }

    public IBinder onBind(Intent intent) {
        return this.aD;
    }

    public static boolean k() {
        return y;
    }

    public static boolean l() {
        return z;
    }

    public static boolean m() {
        return A;
    }

    public static boolean n() {
        return C;
    }

    public static com.fgmicrotec.mobile.android.fgmag.DataConnectionManager.a o() {
        return E;
    }

    public static boolean p() {
        return b;
    }

    public static void b(boolean z) {
        b = z;
    }

    public static boolean q() {
        return N;
    }

    public static void r() {
        N = false;
    }

    public static boolean s() {
        return D;
    }

    public static int t() {
        return ar;
    }

    public static String u() {
        return F;
    }

    public void onCreate() {
        super.onCreate();
        q.b("CallService", "CallService onCreate()");
        y = true;
        this.T = new Handler();
        this.V = new Handler();
        this.U = new Handler();
        this.W = new Handler();
        this.X = new Handler();
        this.Y = new Handler();
        this.O = false;
        A = false;
        this.P = false;
        this.aa = false;
        this.ab = 0;
        this.ai = false;
        this.aj = 0;
        this.ax = false;
        this.ay = false;
        this.an = new Vector();
        this.ap = 0;
        this.az = 0;
        this.ae = -1;
        E = com.fgmicrotec.mobile.android.fgmag.DataConnectionManager.a.ANY;
        this.ad = false;
        if (l.a((Context) this).C()) {
            this.ae = 1;
            this.ad = true;
        } else if (l.a((Context) this).z()) {
            this.ae = 0;
            this.ad = true;
        }
        this.af = 0;
        this.M = new b();
        K();
        K = new DeviceStateReceiver();
        M();
        if (com.fgmicrotec.mobile.android.fgvoip.e.a() == null) {
            com.fgmicrotec.mobile.android.fgvoip.e.a((Context) this);
        }
        startForeground(3, com.fgmicrotec.mobile.android.fgvoip.e.a().b());
        G = (TelephonyManager) getSystemService("phone");
        L = new m(this);
        v();
        H = new CallServiceBroadcastReceiver();
        H();
        O();
        FgVoIP.U().h();
        A();
        ao();
        SimpleCodecAL.stopPlayerAndRecorderLoops();
        if (FgVoIP.U().aj()) {
            this.T.postDelayed(this.aR, 60000);
        }
    }

    private void G() {
        m(0, com.mavenir.android.settings.c.h.d());
        m(1, com.mavenir.android.settings.c.h.e());
        m(2, com.mavenir.android.settings.c.h.f());
        m(3, com.mavenir.android.settings.c.h.g());
        m(4, com.mavenir.android.settings.c.h.h());
    }

    public void onDestroy() {
        q.b("CallService", "CallService onDestroy() ");
        if (this.P) {
            Intent intent = new Intent();
            intent.setAction("com.fgmicrotec.mobile.android.voip.CallServiceReleasedInd");
            sendBroadcast(intent);
        }
        this.T.removeCallbacks(this.aN);
        this.T.removeCallbacks(this.aQ);
        this.T.removeCallbacks(this.aR);
        this.U.removeCallbacks(this.aG);
        this.U.removeCallbacks(this.aH);
        this.V.removeCallbacks(this.aO);
        this.T.removeCallbacks(this.aI);
        this.W.removeCallbacks(this.aW);
        this.W.removeCallbacksAndMessages(c.class);
        this.T.removeCallbacks(this.aX);
        this.T.removeCallbacks(this.aJ);
        this.X.removeCallbacksAndMessages(d.class);
        this.Y.removeCallbacksAndMessages(e.class);
        this.T.removeCallbacks(this.aM);
        this.T.removeCallbacks(this.aE);
        this.T.removeCallbacks(this.aT);
        this.T.removeCallbacks(this.aV);
        this.T.removeCallbacksAndMessages(f.class);
        this.T.removeCallbacksAndMessages(g.class);
        this.T.removeCallbacks(this.aU);
        this.T.removeCallbacks(this.aS);
        this.T.removeCallbacks(this.aK);
        this.T.removeCallbacks(this.aL);
        I();
        L();
        w();
        N();
        a = -1;
        w.a().b();
        y.a().c();
        this.aw = j.VPN_DISCONNECTED;
        com.fgmicrotec.mobile.android.fgvoip.e.a().i();
        y = false;
        z = false;
        super.onDestroy();
    }

    private void H() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("IntentActions.StopServiceReq");
        intentFilter.addAction("com.fgmicrotec.mobile.android.voip.CallServicereleaseReq");
        intentFilter.addAction("com.fgmicrotec.mobile.android.voip.LoginToServerReq");
        intentFilter.addAction("com.fgmicrotec.mobile.android.voip.LogoutFromServerReq");
        intentFilter.addAction("com.fgmicrotec.mobile.android.voip.CallInviteReq");
        intentFilter.addAction("com.fgmicrotec.mobile.android.voip.VideoCallInviteReq");
        intentFilter.addAction("com.fgmicrotec.mobile.android.voip.CallTerminateReq");
        intentFilter.addAction("com.fgmicrotec.mobile.android.voip.AcceptInvitationReq");
        intentFilter.addAction("com.fgmicrotec.mobile.android.voip.RejectInvitationReq");
        intentFilter.addAction("com.fgmicrotec.mobile.android.voip.CancelIncomingCallInvitationTimeoutReq");
        intentFilter.addAction("com.fgmicrotec.mobile.android.voip.InviteCancelReq");
        intentFilter.addAction("com.fgmicrotec.mobile.android.voip.SendGenericDTMFReq");
        intentFilter.addAction("com.fgmicrotec.mobile.android.voip.StopSendingGenericDTMFReq");
        intentFilter.addAction("com.fgmicrotec.mobile.android.voip.MuteAudioReq");
        intentFilter.addAction("com.fgmicrotec.mobile.android.voip.UnmuteAudioReq");
        intentFilter.addAction("com.fgmicrotec.mobile.android.voip.UpdateAudioMode");
        intentFilter.addAction("com.fgmicrotec.mobile.android.voip.SetTraceLevel");
        intentFilter.addAction("com.fgmicrotec.mobile.android.voip.DNDToggleReq");
        intentFilter.addAction("com.fgmicrotec.mobile.android.voip.CallHoldReq");
        intentFilter.addAction("com.fgmicrotec.mobile.android.voip.CallUnholdReq");
        intentFilter.addAction("com.fgmicrotec.mobile.android.voip.CallConsultationReq");
        intentFilter.addAction("com.fgmicrotec.mobile.android.voip.CallVideoConsultationReq");
        intentFilter.addAction("com.fgmicrotec.mobile.android.voip.CallTransferReq");
        intentFilter.addAction("com.fgmicrotec.mobile.android.voip.CallToggleReq");
        intentFilter.addAction("com.fgmicrotec.mobile.android.voip.Call3WayReq");
        intentFilter.addAction("com.fgmicrotec.mobile.android.voip.CallConferenceMergeReq");
        intentFilter.addAction("com.fgmicrotec.mobile.android.voip.CallConferenceAddParticipantReq");
        intentFilter.addAction("com.fgmicrotec.mobile.android.voip.CallConferenceCreateReq");
        intentFilter.addAction("com.fgmicrotec.mobile.android.voip.CallParkReq");
        intentFilter.addAction("com.fgmicrotec.mobile.android.voip.VolumeChangeReq");
        intentFilter.addAction("com.fgmicrotec.mobile.android.voip.MainScreenWasClosedInd");
        intentFilter.addAction("com.fgmicrotec.mobile.android.voip.QosReportReq");
        intentFilter.addAction("com.fgmicrotec.mobile.android.voip.QosMarkReportReq");
        intentFilter.addAction("com.fgmicrotec.mobile.android.voip.WifiSignalStrengthInd");
        intentFilter.addAction("com.fgmicrotec.mobile.android.voip.ActionUpdateMessaging");
        intentFilter.addAction("com.fgmicrotec.mobile.android.voip.ShouldDisplayVideoViewRes");
        intentFilter.addAction("com.fgmicrotec.mobile.android.voip.AddVideoReq");
        intentFilter.addAction("com.fgmicrotec.mobile.android.voip.RemoveVideoReq");
        intentFilter.addAction("android.intent.action.HEADSET_PLUG");
        intentFilter.addAction("android.intent.action.PHONE_STATE");
        intentFilter.addAction("com.mavenir.android.ActionConectivityChange");
        intentFilter.addAction("android.intent.action.ACTION_SHUTDOWN");
        intentFilter.addAction("com.mavenir.android.ActionVpnStateChanged");
        intentFilter.addAction("USSIDialogActions.StringReceivedRes");
        intentFilter.addAction("UserCallRatingDialogActions.CallRatingRes");
        intentFilter.addAction("android.intent.action.QUICKBOOT_POWEROFF");
        intentFilter.addAction("com.fgmicrotec.mobile.android.voip.VolumeChangeReq");
        registerReceiver(H, intentFilter);
        if (FgVoIP.U().n()) {
            q.a("CallService", "Registering SIM change receiver");
            I = new CallServiceBroadcastReceiver();
            intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.SIM_STATE_CHANGED");
            registerReceiver(I, intentFilter);
        }
        if (VERSION.SDK_INT < 16) {
            J = new PackageChangeBroadcastReceiver();
            intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.PACKAGE_CHANGED");
            intentFilter.addDataScheme("package");
            registerReceiver(J, intentFilter);
        }
    }

    private void I() {
        J();
        unregisterReceiver(H);
        if (VERSION.SDK_INT < 16) {
            unregisterReceiver(J);
        }
    }

    private void J() {
        if (I != null) {
            q.a("CallService", "Unregistering SIM change receiver");
            unregisterReceiver(I);
            I = null;
        }
    }

    private synchronized void K() {
        if (!this.O) {
            this.O = true;
            IntentFilter intentFilter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
            intentFilter.addAction("android.net.wifi.RSSI_CHANGED");
            intentFilter.addAction("android.intent.action.TIME_SET");
            registerReceiver(this.M, intentFilter);
        }
    }

    private synchronized void L() {
        if (this.O) {
            this.O = false;
            unregisterReceiver(this.M);
        }
    }

    private void M() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.TIME_SET");
        registerReceiver(K, intentFilter);
    }

    private void N() {
        unregisterReceiver(K);
    }

    public void v() {
        if (L != null) {
            q.a("CallService", "startPhoneStateListener()");
            int i = 289;
            try {
                if (com.mavenir.android.settings.c.l.d()) {
                    i = ActivationAdapter.REASON_APP_USER_FRIENDLY_MESSAGE;
                }
                G.listen(L, i);
            } catch (Exception e) {
                q.d("CallService", "startPhoneStateListener(): " + e);
            }
        }
    }

    public void w() {
        if (L != null) {
            q.a("CallService", "stopping phone state listeners...");
            try {
                G.listen(L, 0);
            } catch (Exception e) {
                q.d("CallService", "stopPhoneStateListener(): " + e);
            }
        }
    }

    private int O() {
        if (this.R == null) {
            if (!FgVoIP.U().aj()) {
                System.loadLibrary("osip2");
                System.loadLibrary("fgVoIP");
            }
            DataConnectionManager.setContext(this);
            this.R = new FgSDKLoader();
        }
        boolean c = com.mavenir.android.settings.c.h.c();
        boolean b = com.mavenir.android.settings.c.h.b();
        String file = FgVoIP.U().ae().toString();
        String absolutePath = FgVoIP.e != null ? FgVoIP.e.getAbsolutePath() : null;
        String absolutePath2 = getDir("storage", 0).getAbsolutePath();
        q.a("CallService", "initSDK(): traces enabled: " + c + ", write mode: " + b + ", traceFile: " + absolutePath + ", nativeCrashDir: " + file + ", storageRoot: " + absolutePath2);
        this.R.fgInit(c, b, absolutePath, file, absolutePath2);
        q.b("CallService", "Lib ver: " + this.R.fgGetAppVerStr() + "; " + this.R.fgGetAppBuildDateStr());
        this.R.fgSetTraceLevel(0, 0);
        G();
        FgVoIP.U().aL();
        this.S = new VoIP(this);
        this.S.init();
        if (l.a((Context) this).j().equals("SM-G920P")) {
            P();
        }
        return 0;
    }

    private void P() {
        String str;
        Object obj = null;
        String str2 = null;
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("logcat -d").getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();
            String str3 = "";
            Object obj2 = null;
            do {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                if (readLine.contains("-----BEGIN BREAKPAD MICRODUMP-----")) {
                    obj2 = 1;
                }
                if (readLine.contains("-----END BREAKPAD MICRODUMP-----")) {
                    obj = 1;
                }
                if (obj2 != null) {
                    stringBuilder.append(readLine);
                    stringBuilder.append("\n");
                    continue;
                }
            } while (obj == null);
            str2 = stringBuilder.toString();
            str = str2;
        } catch (IOException e) {
            str = null;
        }
        if (str != null) {
            str2 = Environment.getExternalStorageDirectory().toString();
            str2 = str2 + "/" + ("Crash_" + System.currentTimeMillis() + ".dmp");
            try {
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(str2));
                bufferedWriter.write(str);
                bufferedWriter.flush();
                bufferedWriter.close();
                q.b("CallService", "getBreakpadDumpFromLogCat() - path = " + str2);
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            com.mavenir.android.applog.a.a((Context) this).a(com.mavenir.android.applog.AppLogAdapter.b.FGAPPLOG_EVENT_GROUP_NATIVE_CRASH, com.mavenir.android.applog.AppLogAdapter.d.FGAPPLOG_EVENT_TYPE_NONE, com.mavenir.android.applog.AppLogAdapter.c.FGAPPLOG_EVENT_REASON_NONE, str);
        }
    }

    private void Q() {
        q.b("CallService", "exitSDK() - stop voip");
        this.S.exit();
        if (this.R != null) {
            q.b("CallService", "exitSDK() - stop core");
            this.R.fgExit();
            this.R = null;
        }
        q.b("CallService", "exitSDK() - stop conn.man.");
        DataConnectionManager.releaseFromContext();
        q.b("CallService", "exitSDK() - done.");
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        String str = null;
        int i3 = 0;
        super.onStartCommand(intent, i, i2);
        if (intent != null) {
            String action = intent.getAction();
            q.a("CallService", "onStartCommand() " + (action != null ? " : " + action : ""));
            if (action != null) {
                q.b("CallService", "CallService Requested action is " + action);
                if (action.equals("com.fgmicrotec.mobile.android.voip.CallServicereleaseReq") || action.equals("IntentActions.StopServiceReq")) {
                    if (!this.P) {
                        z = true;
                        a = SystemClock.elapsedRealtime();
                        R();
                    }
                } else if (action.equals("ActivationActions.ActionStartInitialProvisioning")) {
                    if (intent.hasExtra("ActivationExtras.ExtraProvisioningNumber")) {
                        this.w = intent.getStringExtra("ActivationExtras.ExtraProvisioningNumber");
                    } else {
                        this.w = null;
                    }
                    this.T.postDelayed(this.aX, 5000);
                } else if (action.equals("InternalIntents.ProvisioningAuthResponse")) {
                    r0 = intent.getStringExtra("ActivationExtras.ExtraProvisioningTAN");
                    this.x = intent.getStringExtra("ActivationExtras.ExtraProvisioningPIN");
                    this.X.post(new d(this, a.TYPE_PIN.ordinal(), r0));
                } else if (action.equals("com.fgmicrotec.mobile.android.voip.LoginToServerReq")) {
                    this.af = 5000;
                    W();
                } else if (action.equals("com.fgmicrotec.mobile.android.voip.LogoutFromServerReq")) {
                    d(intent.getBooleanExtra("extra_login", false));
                } else if (action.equals("com.fgmicrotec.mobile.android.voip.CallInviteReq")) {
                    if (intent.getIntExtra("extra_is_call_pickup", 0) != 0) {
                        r0 = com.mavenir.android.settings.c.c.c();
                        q.b("CallService", "CallService CALL_INVITE_REQ (pickup) " + r0);
                        e(r0);
                    } else if (intent.hasExtra("extra_uri_to_call")) {
                        r0 = intent.getStringExtra("extra_uri_to_call");
                        q.b("CallService", "CallService CALL_INVITE_REQ " + r0);
                        e(r0);
                    }
                } else if (action.equals("com.fgmicrotec.mobile.android.voip.VideoCallInviteReq")) {
                    if (intent.hasExtra("extra_uri_to_call")) {
                        r0 = intent.getStringExtra("extra_uri_to_call");
                        q.b("CallService", "CallService VIDEO_CALL_INVITE_REQ " + r0);
                        f(r0);
                    }
                } else if (action.equals("com.fgmicrotec.mobile.android.voip.AcceptInvitationReq")) {
                    ak();
                    Y();
                    this.S.acceptInvitationReq(intent.getIntExtra("extra_session_id", this.Z));
                } else if (action.equals("com.fgmicrotec.mobile.android.voip.RejectInvitationReq")) {
                    ak();
                    r0 = intent.getIntExtra("extra_call_log_type", -1);
                    if (r0 > -1) {
                        com.mavenir.android.common.e.a(intent.getStringExtra("extra_display_name"), com.mavenir.android.common.t.f.f(intent.getStringExtra("extra_uri_to_call")), r0, System.currentTimeMillis());
                        com.fgmicrotec.mobile.android.fgvoip.e.a().g();
                        com.mavenir.android.common.g.a().e();
                    }
                    com.mavenir.android.common.g.a().j();
                    this.S.rejectInvitationReq(intent.getIntExtra("extra_session_id", this.Z));
                    ar = -1;
                    this.as = null;
                    this.at = null;
                } else if (action.equals("com.fgmicrotec.mobile.android.voip.CancelIncomingCallInvitationTimeoutReq")) {
                    ak();
                } else if (action.equals("com.fgmicrotec.mobile.android.voip.InviteCancelReq")) {
                    if (intent.hasExtra("extra_uri_to_call")) {
                        this.S.inviteCancelReq(intent.getStringExtra("extra_uri_to_call"), intent.getIntExtra("extra_session_id", this.Z));
                    }
                } else if (action.equals("com.fgmicrotec.mobile.android.voip.CallTerminateReq")) {
                    b(true, intent.getIntExtra("extra_session_id", intent.getIntExtra("extra_session_id", this.Z)));
                } else if (action.equals("com.fgmicrotec.mobile.android.voip.MuteAudioReq")) {
                    this.S.muteAudioReq(true);
                } else if (action.equals("com.fgmicrotec.mobile.android.voip.UnmuteAudioReq")) {
                    this.S.muteAudioReq(false);
                } else if (action.equals("com.fgmicrotec.mobile.android.voip.UpdateAudioMode")) {
                    int i4;
                    int i5;
                    int i6;
                    r1 = intent.getBooleanExtra(VoIP.AUDIO_BT_MODE, false) ? 1 : 0;
                    if (intent.getBooleanExtra(VoIP.AUDIO_SPEAKER_MODE, false)) {
                        i4 = 1;
                    } else {
                        i4 = 0;
                    }
                    if (intent.getBooleanExtra(VoIP.AUDIO_HEASDET_MODE, false)) {
                        i5 = 1;
                    } else {
                        i5 = 0;
                    }
                    if (intent.getBooleanExtra(VoIP.AUDIO_HANDSET_MODE, false)) {
                        i6 = 1;
                    } else {
                        i6 = 0;
                    }
                    if (intent.getBooleanExtra(VoIP.AUDIO_HEADPHONES_MODE, false)) {
                        i3 = 1;
                    }
                    this.S.updateAudioMode(r1, i4, i5, i6, i3);
                } else if (action.equals("com.fgmicrotec.mobile.android.voip.SendGenericDTMFReq")) {
                    if (intent.hasExtra("extra_dtmf_string")) {
                        r0 = intent.getStringExtra("extra_dtmf_string");
                        this.S.genericSendDTMF(r0);
                        q.b("CallService", "genericSendDTMF() " + r0 + " length = " + r0.length());
                    }
                } else if (action.equals("com.fgmicrotec.mobile.android.voip.StopSendingGenericDTMFReq")) {
                    this.S.genericStopSendingDTMF();
                    q.b("CallService", "genericStopSendingDTMF()");
                } else if (action.equals("com.fgmicrotec.mobile.android.voip.DNDToggleReq")) {
                    if (!this.aa) {
                        this.aa = true;
                        r0 = g("*62");
                        e(r0);
                        q.b("CallService", "toggleDND - invite() " + r0);
                    }
                } else if (action.equals("com.fgmicrotec.mobile.android.voip.CallHoldReq")) {
                    this.S.supplementaryServiceReq(null, intent.getIntExtra("extra_session_id", 0), 0);
                } else if (action.equals("com.fgmicrotec.mobile.android.voip.CallUnholdReq")) {
                    this.S.supplementaryServiceReq(null, intent.getIntExtra("extra_session_id", 0), 1);
                } else if (action.equals("com.fgmicrotec.mobile.android.voip.CallConsultationReq")) {
                    this.S.supplementaryServiceReq(intent.getStringExtra("extra_uri_to_call"), 0, 4);
                } else if (action.equals("com.fgmicrotec.mobile.android.voip.CallVideoConsultationReq")) {
                    this.S.supplementaryServiceReq(intent.getStringExtra("extra_uri_to_call"), 0, 13);
                } else if (action.equals("com.fgmicrotec.mobile.android.voip.CallTransferReq")) {
                    this.ac = false;
                    if (intent.hasExtra("extra_uri_to_call")) {
                        r0 = intent.getStringExtra("extra_uri_to_call");
                    } else {
                        r0 = null;
                    }
                    this.S.supplementaryServiceReq(r0, intent.getIntExtra("extra_session_id", 0), 7);
                } else if (action.equals("com.fgmicrotec.mobile.android.voip.CallToggleReq")) {
                    this.S.supplementaryServiceReq(null, 0, 6);
                } else if (action.equals("com.fgmicrotec.mobile.android.voip.Call3WayReq")) {
                    if (intent.hasExtra("extra_uri_to_call")) {
                        str = intent.getStringExtra("extra_uri_to_call");
                    }
                    this.S.supplementaryServiceReq(str, 0, 12);
                } else if (action.equals("com.fgmicrotec.mobile.android.voip.CallConferenceMergeReq")) {
                    a(intent.getStringExtra("extra_conf_held_call_uri"), intent.getIntExtra("extra_conf_held_session_id", 0), intent.getStringExtra("extra_conf_active_call_uri"), intent.getIntExtra("extra_conf_active_session_id", 0), intent.getBooleanExtra("extra_video_call", false));
                } else if (action.equals("com.fgmicrotec.mobile.android.voip.CallConferenceAddParticipantReq")) {
                    a(intent.getStringExtra("extra_conf_active_call_uri"), intent.getIntExtra("extra_conf_active_session_id", 0), intent.getStringExtra("extra_conf_participant"), intent.getBooleanExtra("extra_video_call", false));
                } else if (action.equals("com.fgmicrotec.mobile.android.voip.CallConferenceCreateReq")) {
                    a(intent.getStringArrayExtra("extra_conf_participants"));
                } else if (action.equals("com.fgmicrotec.mobile.android.voip.CallParkReq")) {
                    if (intent.hasExtra("extra_uri_to_call")) {
                        r0 = intent.getStringExtra("extra_uri_to_call");
                    } else {
                        r0 = g("P700");
                    }
                    this.ac = true;
                    this.S.supplementaryServiceReq(r0, intent.getIntExtra("extra_session_id", 0), 7);
                } else if (action.equals("com.fgmicrotec.mobile.android.voip.VolumeChangeReq")) {
                    q.b("CallService", "Change in volume by direction:" + intent.getIntExtra("extra_volume_change_direction", 0) + " (0-reduce/1-increase)");
                    this.S.setVolumeChange(intent.getIntExtra("extra_volume_change_direction", 1));
                } else if (action.equals("CallServiceActions.ActionNavigateToApplicationUpdatePage")) {
                    r0 = new Intent("android.intent.action.VIEW", Uri.parse("http://app.mavenir.com/install/index.php"));
                    r0.addFlags(268435456);
                    startActivity(r0);
                } else if (action.equals("CallServiceActions.ActionReconfigureVideoEnabled")) {
                    this.T.post(this.aT);
                } else if (action.equals("com.fgmicrotec.mobile.android.voip.SetTraceLevel")) {
                    if (intent.hasExtra("extra_trace_group")) {
                        r0 = intent.getIntExtra("extra_trace_group", 0);
                        if (intent.hasExtra("extra_trace_level")) {
                            r1 = intent.getIntExtra("extra_trace_level", 0);
                            m(r0, r1);
                            if (r0 == 2 && r1 == 2) {
                                q.a("CallService", "DeviceInfoRecord: IMEI: " + l.a(FgVoIP.U()).k() + ", appVer: " + l.a(FgVoIP.U()).c() + ", OS: " + l.a(FgVoIP.U()).g() + ", OSver: " + l.a(FgVoIP.U()).h() + ", manufacturer: " + l.a(FgVoIP.U()).i() + ", model:" + l.a(FgVoIP.U()).j());
                            }
                        }
                    }
                } else if (action.equals("android.intent.action.PHONE_STATE")) {
                    str = intent.getStringExtra("state");
                    action = intent.getStringExtra("incoming_number");
                    D = !str.equals(TelephonyManager.EXTRA_STATE_IDLE);
                    q.a("CallService", "onStartCommand(): ACTION_PHONE_STATE_CHANGED: new state: " + str + ", incoming number: " + action);
                    q.a("CallService", "onStartCommand(): ACTION_PHONE_STATE_CHANGED: mNativeCallInProgress: " + D);
                    if (str.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
                        q.a("CallService", "onStartCommand(): ACTION_PHONE_STATE_CHANGED: new state: " + str + ", incoming number: " + action);
                    } else {
                        q.a("CallService", "onStartCommand(): ACTION_PHONE_STATE_CHANGED: new state: " + str);
                    }
                    q.a("CallLog", "TelephonyManager. uses VCC  mCScallinitiated " + this.aB);
                    if (this.aB && FgVoIP.U().p()) {
                        if (str.equals(TelephonyManager.EXTRA_STATE_OFFHOOK)) {
                            q.a("CallLog", "TelephonyManager.EXTRA_STATE_OFFHOOK: ");
                            aA = true;
                        } else if (str.equals(TelephonyManager.EXTRA_STATE_IDLE)) {
                            q.a("CallLog", "TelephonyManager.EXTRA_STATE_IDLE: mIsNativeCallstarted " + aA);
                            if (aA) {
                                aA = false;
                                this.aB = false;
                                new Handler().postDelayed(new Runnable(this) {
                                    final /* synthetic */ CallService b;

                                    public void run() {
                                        q.a("CallLog", "Last outgoingno:  number " + action);
                                        this.b.b(null);
                                    }
                                }, 2000);
                            }
                        }
                    }
                } else if (action.equals("com.fgmicrotec.mobile.android.voip.QosReportReq")) {
                    am();
                } else if (action.equals("com.fgmicrotec.mobile.android.voip.QosMarkReportReq")) {
                    an();
                } else if (action.equals("com.fgmicrotec.mobile.android.voip.WifiSignalStrengthInd")) {
                    r0 = intent.getIntExtra("extra_wifi_signal_strength", 0);
                    if (!A) {
                        if (com.fgmicrotec.mobile.android.fgvoip.e.a() == null) {
                            com.fgmicrotec.mobile.android.fgvoip.e.a((Context) this);
                        }
                        if (com.fgmicrotec.mobile.android.fgvoip.e.a() != null) {
                            com.fgmicrotec.mobile.android.fgvoip.e.a().a(A, r0);
                        }
                    }
                    if (!(z || E == com.fgmicrotec.mobile.android.fgmag.DataConnectionManager.a.LTE)) {
                        this.S.setWifiSignalStrengthInd(r0);
                        a(System.currentTimeMillis(), r0);
                    }
                } else if (action.equals("com.mavenir.android.ActionConectivityChange")) {
                    a(intent);
                } else if (action.equals("com.mavenir.android.ActionVpnStateChanged")) {
                    this.aw = (j) intent.getSerializableExtra("IntentActivationExtras.VpnState");
                    if (this.aw == j.VPN_CONNECTED) {
                        W();
                    } else if (this.aw == j.VPN_DISCONNECTED && A) {
                        r0 = new Intent();
                        r0.setAction("DataConnectionManagerIntents.ActionVPNDisconnected");
                        sendBroadcast(r0);
                    } else if (this.aw == j.VPN_CONNECTION_ERROR) {
                        this.aw = j.VPN_DISCONNECTED;
                    }
                } else if (action.equals("USSIDialogActions.StringReceivedRes")) {
                    this.S.ussdStringReceivedRes(intent.getStringExtra("USSIDialogExtras.ExtraUSSIString"));
                } else if (action.equals("UserCallRatingDialogActions.CallRatingRes")) {
                    this.T.postDelayed(new g(this, intent.getStringExtra("UserCallRatingDialogExtras.ExtraRatingPhoneNumber"), intent.getStringExtra("UserCallRatingDialogExtras.ExtraRatingNetworkBearer"), intent.getIntExtra("UserCallRatingDialogExtras.ExtraRatingCallDuration", 0), intent.getIntExtra("UserCallRatingDialogExtras.ExtraRatingStars", -1)), 1000);
                } else if (action.equals("android.intent.action.SIM_STATE_CHANGED") && FgVoIP.U().n()) {
                    r0 = l.a(getApplicationContext()).p();
                    q.a("CallService", "SIM_STATE_CHANGED: phoneName=" + intent.getStringExtra("phoneName") + ", ss=" + intent.getStringExtra("ss") + ", reason=" + intent.getStringExtra("reason") + ", SIM=" + r0);
                    if (this.aq) {
                        q.a("CallService", "SIM_STATE_CHANGED: skipping first event");
                        this.aq = false;
                    } else if (l.a((Context) this).D()) {
                        q.a("CallService", "SIM_STATE_CHANGED: airplain mode is on - skipping event");
                    } else if ("ABSENT".equals(intent.getStringExtra("ss")) && r0 == null) {
                        q.a("CallService", "SIM removed - performing onSimRemoved() ");
                        V();
                    } else if ("LOADED".equals(intent.getStringExtra("ss"))) {
                        q.a("CallService", "SIM is back - doing login");
                        W();
                    }
                } else if (action.equals("android.intent.action.QUICKBOOT_POWEROFF") || action.equals("android.intent.action.ACTION_SHUTDOWN")) {
                    J();
                } else if (action.equals("com.fgmicrotec.mobile.android.voip.ActionUpdateMessaging")) {
                    A();
                } else if (action.equals("com.fgmicrotec.mobile.android.voip.ShouldDisplayVideoViewRes")) {
                    r0 = intent.getIntExtra("extra_video_view_width", 0);
                    r1 = intent.getIntExtra("extra_video_view_height", 0);
                    q.b("CallService", "SHOULD_DISPLAY_VIDEO_VIEW_RES availableWidth = " + r0 + " availableHeight = " + r1);
                    o(r0, r1);
                } else if (action.equals("com.fgmicrotec.mobile.android.voip.AddVideoReq")) {
                    this.S.addVideoReq();
                } else if (action.equals("com.fgmicrotec.mobile.android.voip.RemoveVideoReq")) {
                    this.S.removeVideoReq();
                }
            }
        }
        return 1;
    }

    private void m(int i, int i2) {
        int i3 = 0;
        if (i2 != 0) {
            if (i2 == 1) {
                i3 = 3;
            } else if (i2 == 2) {
                i3 = 15;
            }
        }
        if (this.R == null) {
            return;
        }
        if (i == 0) {
            this.R.fgSetTraceLevel(3, i3);
            this.R.fgSetTraceLevel(10, i3);
            this.R.fgSetTraceLevel(60, i3);
            this.R.fgSetTraceLevel(4, i3);
            this.R.fgSetTraceLevel(15, i3);
            this.R.fgSetTraceLevel(35, i3);
            this.R.fgSetTraceLevel(51, i3);
            this.R.fgSetTraceLevel(43, i3);
            this.R.fgSetTraceLevel(49, i3);
            this.R.fgSetTraceLevel(52, i3);
            this.R.fgSetTraceLevel(53, i3);
            this.R.fgSetTraceLevel(54, i3);
            this.R.fgSetTraceLevel(55, i3);
            this.R.fgSetTraceLevel(56, i3);
            this.R.fgSetTraceLevel(57, i3);
            this.R.fgSetTraceLevel(58, i3);
            this.R.fgSetTraceLevel(20, i3);
            this.R.fgSetTraceLevel(12, i3);
            this.R.fgSetTraceLevel(29, i3);
        } else if (i == 1) {
            this.R.fgSetTraceLevel(1, i3);
            this.R.fgSetTraceLevel(44, i3);
            this.R.fgSetTraceLevel(17, i3);
        } else if (i == 2) {
            this.R.fgSetTraceLevel(21, i3);
        } else if (i == 3) {
            this.R.fgSetTraceLevel(41, i3);
            this.R.fgSetTraceLevel(13, i3);
            this.R.fgSetTraceLevel(6, i3);
        } else if (i == 4) {
            this.R.fgSetTraceLevel(14, i3);
        }
    }

    private void R() {
        this.P = true;
        this.U.postDelayed(this.aH, 5000);
        if (A) {
            if (!CallManager.a()) {
                if (ar != -1) {
                    com.mavenir.android.common.e.a(null, com.mavenir.android.common.t.f.f(this.as), 3, System.currentTimeMillis());
                }
                com.fgmicrotec.mobile.android.fgvoip.e.a().g();
                com.mavenir.android.common.g.a().e();
            }
            X();
            A = false;
        }
        this.U.postDelayed(new Runnable(this) {
            final /* synthetic */ CallService a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.U.removeCallbacks(this.a.aG);
                q.b("CallService", "CallService releaseSDKAndExitService() - calling exitSDK()");
                new AsyncTask<Void, Void, Void>(this) {
                    final /* synthetic */ AnonymousClass12 a;

                    {
                        this.a = r1;
                    }

                    protected /* synthetic */ Object doInBackground(Object[] objArr) {
                        return a((Void[]) objArr);
                    }

                    protected Void a(Void... voidArr) {
                        this.a.a.Q();
                        return null;
                    }
                }.execute(new Void[0]);
            }
        }, 2000);
    }

    private void a(Intent intent) {
        if (FgVoIP.U().an()) {
            W();
            if (this.S != null && !z && E != com.fgmicrotec.mobile.android.fgmag.DataConnectionManager.a.LTE) {
                this.S.setWifiSignalStrengthInd(l.a((Context) this).M());
                return;
            }
            return;
        }
        if (A) {
            X();
        }
        sendBroadcast(new Intent("com.mavenir.android.ActionWifiWhitelist"));
    }

    private void a(long j, int i) {
        boolean z = false;
        if (this.an != null) {
            this.an.add(new k(this, j, i));
            q.b("CallService", "CallService saveWiFiMeasurement() rssi = " + i);
            if (this.an.size() > 10) {
                this.an.remove(0);
            }
            if (FgVoIP.U().ai() && i < com.mavenir.android.settings.c.q.d() && i > -99 && A && !CallManager.a() && U()) {
                X();
            }
            int callState = G.getCallState();
            if (FgVoIP.U().p() && callState == 0) {
                boolean b = x.b();
                int c = x.c();
                String d = x.d();
                String c2 = CallManager.c();
                String str = "CallService";
                StringBuilder append = new StringBuilder().append("start_CS_Call: checking conditions: number: ").append(d).append(", checking treshold ").append(c).append(" vs ").append(i).append(", CS call state: ").append(callState).append(", wifi call exists: ");
                if (c2 != null) {
                    z = true;
                }
                q.a(str, append.append(z).toString());
                int E = l.a((Context) this).E();
                boolean J = l.a((Context) this).J() | l.a((Context) this).I();
                q.a("CallService", " isNetworkRoaming " + J);
                if (E == 0) {
                    q.a("CallService", "State : Service_in ");
                } else {
                    q.a("CallService", "Service: not STATE_IN_SERVICE : STATE_POWER_OFF =3 | STATE_OUT_OF_SERVICE=  1 : service state =" + E);
                }
                if (b && c != 0 && i < c && c2 != null && callState == 0 && E == 0 && !J) {
                    Intent intent = new Intent("com.mavenir.android.action.initiateCsCall");
                    intent.putExtra("ExtraNumberExternal", d);
                    sendBroadcast(intent);
                    this.aB = true;
                    a(System.currentTimeMillis());
                }
            }
        }
    }

    private void S() {
        this.an.clear();
    }

    private int b(long j, int i) {
        int i2 = 0;
        int size = this.an.size() - 1;
        while (size >= 0) {
            int i3;
            if (j - ((k) this.an.get(size)).a() < ((long) (i + VoIP.REASON_CODE_SERVER_INTERNAL_ERROR))) {
                i3 = i2 + 1;
            } else {
                i3 = i2;
            }
            size--;
            i2 = i3;
        }
        return i2;
    }

    private int c(long j, int i) {
        int i2 = 0;
        int size = this.an.size() - 1;
        int i3 = 0;
        while (size >= 0) {
            if (j - ((k) this.an.get(size)).a() < ((long) (i + VoIP.REASON_CODE_SERVER_INTERNAL_ERROR))) {
                i3 += ((k) this.an.get(size)).b();
                i2++;
                q.b("CallService", "CallService getMeasurementsAverageOverWindow() measurement(" + size + ") = " + ((k) this.an.get(size)).b() + " old " + (j - ((k) this.an.get(size)).a()) + " ms");
            }
            int i4 = i2;
            size--;
            i3 = i3;
            i2 = i4;
        }
        return i3 / i2;
    }

    private int T() {
        long currentTimeMillis = System.currentTimeMillis();
        int i = 1;
        if (this.ap > 0 && currentTimeMillis - this.ap > 12000) {
            i = 0;
        }
        a(currentTimeMillis, l.a((Context) this).M());
        int e = com.mavenir.android.settings.c.q.e() * 1000;
        if (e == 0 || e > 10000) {
            e = 3000;
        }
        int b = b(currentTimeMillis, e);
        if (b <= i || currentTimeMillis - ((k) this.an.get(0)).a() <= ((long) (e - 500))) {
            this.ap = System.currentTimeMillis();
            if (b == 0) {
                return e / 3;
            }
            return (int) (((long) e) - (currentTimeMillis - ((k) this.an.get(this.an.size() - b)).a()));
        }
        i = c(currentTimeMillis, e);
        q.b("CallService", "CallService getAveragedWiFiSignalStrengthOrDelay() averaged = " + i);
        return i;
    }

    private boolean U() {
        if (FgVoIP.U().aq()) {
            return false;
        }
        int e = com.mavenir.android.settings.c.q.e() * 1000;
        if (e < 5000) {
            e = 5000;
        }
        this.T.postDelayed(this.aE, (long) e);
        long currentTimeMillis = System.currentTimeMillis();
        if (b(currentTimeMillis, e) > 1 && currentTimeMillis - ((k) this.an.get(0)).a() > ((long) (e - 500))) {
            int c = c(currentTimeMillis, e);
            q.b("CallService", "CallService logoutDueToPoorWiFiConditionMatched() averaged = " + c);
            if (c < com.mavenir.android.settings.c.q.d()) {
                return true;
            }
        }
        return false;
    }

    private void V() {
        if (FgVoIP.U().ax()) {
            q.b("CallService", "SIM removed, deactivating account");
            if (A) {
                X();
            }
            FgVoIP.U().a((Context) this, "com.mavenir.android.action_backup_sim_removed");
        }
    }

    private boolean W() {
        this.au = false;
        if (l()) {
            q.a("CallService", "Prevent login while CallService is Stoping");
            return false;
        } else if (p.d() == null) {
            q.d("CallService", "login: no active profile!");
            a(15, 0, "", 0, "");
            return false;
        } else if (this.av) {
            q.c("CallService", "login(): login attempt blocked - Deregistered by server!");
            return false;
        } else if (!FgVoIP.U().an()) {
            q.c("CallService", "login(): Failed - no valid connection!");
            return false;
        } else if (A && E == l.a((Context) this).H()) {
            q.c("CallService", "login(): already registered (Bearer:" + E.name() + ")");
            return false;
        } else if (this.P) {
            q.c("CallService", "login(): Failed - releasing CallService!");
            return false;
        } else if (B) {
            q.c("CallService", "login(): login attempt blocked due to SIP error 600");
            return false;
        } else if (y.a().d() && this.aw == j.VPN_DISCONNECTED) {
            y.a().b();
            this.aw = j.VPN_CONNECTING;
            return false;
        } else if (y.a().d() && this.aw == j.VPN_CONNECTING) {
            return false;
        } else {
            this.Q = false;
            if (FgVoIP.U().ai() && l.a((Context) this).C()) {
                q.b("CallService", "login(): collecting Wi-Fi measurements");
                int T = T();
                if (T > 0) {
                    this.af = 5000;
                    this.T.postDelayed(this.aI, (long) T);
                    q.b("CallService", "login(): delayed to collect measurements by " + T);
                    return false;
                }
                q.b("CallService", "login(): averagedValueOrDelay=" + T + ", minWifiThreshold=" + com.mavenir.android.settings.c.q.d());
                if (T >= com.mavenir.android.settings.c.q.d() || T == -200 || T == -9999) {
                    this.T.removeCallbacks(this.aI);
                    this.af = 0;
                } else {
                    this.af = 5000;
                    this.T.postDelayed(this.aI, (long) this.af);
                    return false;
                }
            }
            if (Math.abs(System.currentTimeMillis() - this.az) < 5000) {
                q.b("CallService", "login(): Previous configuration and login attempt are still pending!");
                return true;
            }
            q.b("CallService", "login(): starting login procedure");
            C = true;
            this.az = System.currentTimeMillis();
            com.fgmicrotec.mobile.android.fgvoip.e.a().a(A, l.a((Context) this).M());
            z();
            if (!CallManager.a()) {
                x();
                Z();
            }
            aa();
            if (!CallManager.a()) {
                ab();
                ac();
                ad();
                ae();
                af();
                ag();
                ah();
                ai();
                y();
            } else if (FgVoIP.U().B() && e.a()) {
                ae();
            }
            return true;
        }
    }

    private boolean d(boolean z) {
        q.a("CallService", "logout(): login after logout: " + z);
        this.au = z;
        C = false;
        this.S.logoutReq();
        return true;
    }

    private boolean X() {
        return d(false);
    }

    private void d(String str) {
        if (FgVoIP.U().B() && e.a()) {
            this.S.renewSpiritLocalIPAddress(str);
        }
    }

    private void e(String str) {
        String f = com.mavenir.android.common.t.f.f(str);
        q.a("CallService", "invite(): " + f);
        if (f.startsWith("*")) {
            Intent intent = new Intent();
            intent.setAction("USSIDialogActions.StringReceivedInd");
            intent.putExtra("USSIDialogExtras.ExtraSENT", true);
            intent.addFlags(268435456);
            startActivity(intent);
        }
        this.S.inviteReq(str, 0, false, 0, 0);
    }

    private void f(String str) {
        int c = com.mavenir.android.settings.c.m.c();
        int e = com.mavenir.android.settings.c.m.e();
        this.S.setMediaInfoReq(c, com.mavenir.android.settings.c.m.g(), com.mavenir.android.settings.c.m.h(), com.mavenir.android.settings.c.m.i(), e, e, com.mavenir.android.settings.c.m.j(), false, false, false, false, c, com.mavenir.android.settings.c.m.f() ? 1 : 0, true, 1);
        this.T.post(this.aT);
        this.S.inviteReq(str, 0, false, 0, 1);
    }

    private void b(boolean z, int i) {
        this.ac = false;
        if (z) {
            this.S.disconnectSessionReq(i);
        }
        if (this.aa) {
            this.aa = false;
            this.V.removeCallbacks(this.aO);
            Intent intent = new Intent();
            intent.setAction("com.fgmicrotec.mobile.android.voip.SessionEndedInd");
            intent.putExtra("extra_session_id", this.ab);
            intent.putExtra("extra_reason_code", 0);
            sendBroadcast(intent);
        }
        i("com.mavenir.android.action.qosCallEnded");
    }

    private void n(int i, int i2) {
        sendBroadcast(a("com.fgmicrotec.mobile.android.voip.CallInviteCnf", i, i2));
    }

    private void Y() {
        i("com.mavenir.android.action.qosCallEstablished");
    }

    private Intent a(String str, int i, int i2) {
        Intent intent = new Intent();
        intent.setAction(str);
        intent.putExtra("extra_error_code", i);
        intent.putExtra("extra_session_id", i2);
        return intent;
    }

    public void sendBroadcast(Intent intent) {
        q.b("CallService", "CallService:sendBroadcast(): " + intent.toString());
        super.sendBroadcast(intent);
    }

    public void x() {
        if (FgVoIP.U().ai()) {
            q.b("CallService", "proxyConfigurationReq()");
            int g = com.mavenir.android.settings.c.q.g();
            String[] c = v.c();
            int b = v.b();
            String[] f = com.mavenir.android.settings.c.l.f();
            int g2 = com.mavenir.android.settings.c.l.g();
            int f2 = com.mavenir.android.settings.c.q.f();
            int[] s = com.mavenir.android.settings.c.q.s();
            int o = com.mavenir.android.settings.c.q.o();
            int p = com.mavenir.android.settings.c.q.p();
            this.S.proxyConfigurationReq(s.length, s, o, f2, c.length, c, b, f, g2, g, com.mavenir.android.settings.c.q.q(), p);
        }
    }

    public void k(int i) {
        q.b("CallService", "proxyConfigurationCnf(): errorCode: " + i);
    }

    public void f() {
        q.b("CallService", "unregisterConnNeededInd()");
        if (this.ad) {
            this.Q = false;
            this.T.post(this.aP);
            return;
        }
        this.Q = true;
    }

    private void Z() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(FgVoIP.U().getString(com.fgmicrotec.mobile.android.fgvoip.f.k.app_name_short)).append(" ");
        stringBuffer.append(FgVoIP.U().getString(com.fgmicrotec.mobile.android.fgvoip.f.k.app_country)).append(" ");
        stringBuffer.append("Android").append(" v");
        stringBuffer.append(l.a((Context) this).a(false)).append(" ");
        stringBuffer.append(l.a((Context) this).f());
        this.S.setUserAgentReq(stringBuffer.toString(), false);
    }

    public void j(int i) {
        q.b("CallService", "setUserAgentCnf(): err =" + i);
    }

    private void aa() {
        int f = n.f();
        int g = n.g();
        int h = n.h();
        if (!com.mavenir.android.settings.c.k.t() || n.f() <= 0 || n.g() <= 0 || n.h() <= 0) {
            f = 0;
            g = 0;
            h = 0;
        }
        this.S.setConnectionInfoReq(v.d(), v.b(), v.e(), s.b(), false, v.h(), f, g, h, n.e(), s.c(), v.f(), 0, 0, v.i(), s.d(), null, 0);
    }

    public void a(int i) {
        if (i == 0) {
            this.T.post(this.aN);
            return;
        }
        q.d("CallService", "setConnectionInfoCnf(): err" + i);
        this.T.postDelayed(this.aI, 120000);
    }

    private void ab() {
        int b = n.b();
        if (FgVoIP.U().ad() == com.fgmicrotec.mobile.android.fgvoip.FgVoIP.a.VToW && b == 1) {
            b = 2;
        }
        this.S.setConnectionTypeReq(b);
    }

    public void c(int i) {
        q.b("CallService", "setConnectionTypeCnf(): err=" + i);
    }

    private void ac() {
        this.S.setQoSThresholdsReq(com.mavenir.android.settings.c.q.h(), com.mavenir.android.settings.c.q.i(), com.mavenir.android.settings.c.q.j(), com.mavenir.android.settings.c.q.k(), com.mavenir.android.settings.c.q.l());
    }

    public void b(int i) {
        if (i != 0) {
            q.d("CallService", "setQoSThresholdsCnf(): err" + i);
        }
    }

    private void ad() {
        int i = 0;
        int i2 = s.f() ? 1 : 0;
        int g = s.g() + 1;
        int h = s.h() + 1;
        if (i2 == 0) {
            h = 0;
        } else {
            i = h;
            h = g;
        }
        q.b("CallService", "setSRTPModeReq(): srtpMode: " + i2 + ", encryption: " + h + ", authentication: " + i);
        this.S.setSRTPModeReq(i2, i2, h, i);
    }

    public void h(int i) {
        if (i == 0) {
            q.b("CallService", "setSRTPModeCnf(): errCode=" + i);
        } else {
            q.d("CallService", "setSRTPModeCnf(): errCode=" + i);
        }
    }

    private void ae() {
        int c = com.mavenir.android.settings.c.m.c();
        int e = com.mavenir.android.settings.c.m.e();
        this.S.setMediaInfoReq(c, com.mavenir.android.settings.c.m.g(), com.mavenir.android.settings.c.m.h(), com.mavenir.android.settings.c.m.i(), e, e, com.mavenir.android.settings.c.m.j(), false, false, false, false, e, com.mavenir.android.settings.c.m.f() ? 1 : 0, true, 1);
        if (FgVoIP.U().aj() || FgVoIP.U().ai()) {
            int i;
            SimpleCodecAL.configCodecsEngine();
            if (FgVoIP.U().z()) {
                this.T.postDelayed(this.aT, 3000);
            }
            VoIP voIP = this.S;
            c = e.a() ? 1 : 0;
            int i2 = e.b() ? 1 : 0;
            int i3 = e.c() ? 1 : 0;
            int d = e.d();
            e = com.mavenir.android.settings.c.m.i() * 20;
            int h = com.mavenir.android.settings.c.m.h() * 20;
            int g = com.mavenir.android.settings.c.m.g() * 20;
            if (e.e()) {
                i = 1;
            } else {
                i = 0;
            }
            voIP.setSpiritTestPreferencesReq(c, i2, i3, d, e, h, g, i);
            InetAddress localIpAddress = DataConnectionManager.getLocalIpAddress();
            if (localIpAddress != null) {
                d(localIpAddress.getHostAddress());
            }
        }
    }

    public void e(int i) {
        q.a("CallService", "setMediaInfoCnf(): errCode: " + i);
    }

    private void af() {
        this.S.setSessionInfoReq(v.j(), v.k(), v.l());
    }

    public void f(int i) {
        q.b("CallService", "setSessionInfoCnf err=" + i);
    }

    private void ag() {
        this.S.setSTUNInfoReq(n.c(), n.d());
    }

    public void g(int i) {
        q.b("CallService", "setSTUNInfoCnf err=" + i);
    }

    private void ah() {
        if (s.e() > 0) {
            this.S.setRTCPInterval(s.e() * 1000);
        }
    }

    private void ai() {
        this.S.setATGInfoReq(com.mavenir.android.settings.c.a.b(), com.mavenir.android.settings.c.a.c() ? 1 : 0);
    }

    public void d(int i) {
        if (i == 0) {
            this.T.post(this.aQ);
            return;
        }
        q.d("CallService", "setUserInfoCnf err" + i);
        this.T.postDelayed(this.aI, 120000);
    }

    public void a(int i, int i2, String str, int i3, String str2) {
        boolean z = true;
        q.b("CallService", "loginVoIPCnf(): err=" + i + "; reason=" + i2 + "; UUID: " + str + "; regID: " + i3 + "; SIP server: " + str2);
        C = false;
        if (i != 2 || !A) {
            if (i != 0) {
                F = "";
                A = false;
                FgVoIP.U().a(false, false, false);
                if (FgVoIP.U().ai() && i == 13 && i2 == VoIP.REASON_CODE_BUSY_EVERYWHERE) {
                    B = true;
                }
            } else if (FgVoIP.U().an()) {
                if (!TextUtils.isEmpty(str)) {
                    p.n(str);
                    p.a(i3);
                }
                if (FgVoIP.U().ai()) {
                    aa.a((Context) this).c();
                }
                F = str2;
                A = true;
                E = l.a((Context) this).H();
                this.af = 0;
                this.T.removeCallbacks(this.aI);
            } else {
                this.T.post(new Runnable(this) {
                    final /* synthetic */ CallService a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.X();
                    }
                });
                return;
            }
            String str3 = "CallService";
            StringBuilder append = new StringBuilder().append("loginVoIPCnf(): mLoggedInToTheServer = ").append(A).append(", mIsRegisteredOverLTE = ");
            if (E != com.fgmicrotec.mobile.android.fgmag.DataConnectionManager.a.LTE) {
                z = false;
            }
            q.a(str3, append.append(z).toString());
            b(false);
            com.fgmicrotec.mobile.android.fgvoip.e.a().a(A, l.a((Context) this).M());
            Intent intent = new Intent();
            intent.setAction("com.fgmicrotec.mobile.android.voip.LoginToServerCnf");
            intent.putExtra("extra_error_code", i);
            intent.putExtra("extra_reason_code", i2);
            sendBroadcast(intent);
            if (y.a().d() && i == 20) {
                this.aw = j.VPN_DISCONNECTED;
                y.a().c();
                this.T.postDelayed(this.aI, 5000);
            } else if (!this.ax && !FgVoIP.U().ai() && com.mavenir.android.settings.c.k.d() != 1000) {
                this.T.postDelayed(this.aX, 5000);
            }
        }
    }

    public void a(int i, int i2) {
        boolean z = true;
        q.b("CallService", "logoutVoIPCnf err=" + i + "; reason=" + i2);
        E = com.fgmicrotec.mobile.android.fgmag.DataConnectionManager.a.ANY;
        A = false;
        q.a("CallService", "logoutVoIPCnf(): mLoggedInToTheServer = " + A);
        b(false);
        FgVoIP.U().a(false, false, false);
        com.fgmicrotec.mobile.android.fgvoip.e.a().a(A, l.a((Context) this).M());
        Intent intent = new Intent();
        intent.setAction("com.fgmicrotec.mobile.android.voip.LogoutFromServerCnf");
        intent.putExtra("extra_error_code", i);
        intent.putExtra("extra_reason_code", i2);
        sendBroadcast(intent);
        if (!this.P && FgVoIP.U().ai() && !FgVoIP.U().au() && this.ad) {
            boolean z2 = this.ae == 1 && FgVoIP.U().ao();
            if (!((this.ae == 0 || this.ae == 5) && FgVoIP.U().aq())) {
                z = false;
            }
            if (z2 || z) {
                this.af = 5000;
                this.T.postDelayed(this.aI, (long) this.af);
            }
        }
        if (this.au) {
            this.au = false;
            this.T.post(new Runnable(this) {
                final /* synthetic */ CallService a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.W();
                }
            });
        }
    }

    public void b(int i, int i2) {
        boolean z = true;
        q.b("CallService", "logoutVoIPInd err=" + i + "; reason=" + i2);
        A = false;
        q.a("CallService", "logoutVoIPInd(): mLoggedInToTheServer: " + A);
        b(false);
        FgVoIP.U().a(false, false, false);
        com.fgmicrotec.mobile.android.fgvoip.e.a().a(A, l.a((Context) this).M());
        Intent intent = new Intent();
        intent.setAction("com.fgmicrotec.mobile.android.voip.LogoutFromServerInd");
        intent.putExtra("extra_error_code", i);
        intent.putExtra("extra_reason_code", i2);
        sendBroadcast(intent);
        if (y.a().d() && (i == 0 || i == 20)) {
            q.a("CallService", "logoutVoIPInd(): VPN timed out, initiating login recovery");
            this.aw = j.VPN_DISCONNECTED;
            y.a().c();
            this.T.postDelayed(this.aI, 5000);
        } else if (this.ad) {
            q.a("CallService", "logoutVoIPInd(): Client has connectivity, initiating login recovery");
            if (FgVoIP.U().ai()) {
                boolean z2 = this.ae == 1 && FgVoIP.U().ao();
                if (!((this.ae == 0 || this.ae == 5) && FgVoIP.U().aq())) {
                    z = false;
                }
                if (z2 || z) {
                    if (this.af == 0 || this.af > 10000) {
                        this.af = 5000;
                    }
                    this.T.post(this.aI);
                    return;
                }
                return;
            }
            this.af = 5000;
            this.T.postDelayed(this.aI, 1000);
        } else {
            q.a("CallService", "logoutVoIPInd(): Client has no connectivity");
        }
    }

    public void b() {
        Intent intent = new Intent();
        intent.setAction("com.fgmicrotec.mobile.android.voip.LoginToServerCnf");
        intent.putExtra("extra_error_code", 0);
        sendBroadcast(intent);
    }

    public void a() {
        C = true;
        com.fgmicrotec.mobile.android.fgvoip.e.a().a(A, l.a((Context) this).M());
        Intent intent = new Intent();
        intent.setAction("com.fgmicrotec.mobile.android.voip.LoginStartedInd");
        sendBroadcast(intent);
    }

    public void c() {
        this.T.postDelayed(this.aM, 100);
    }

    public void c(int i, int i2) {
        n(i, i2);
        if (this.aa) {
            this.ab = i2;
        }
    }

    public void g(int i, int i2) {
        ak();
        ar = -1;
        this.as = null;
        this.at = null;
        sendBroadcast(a("com.fgmicrotec.mobile.android.voip.CallTerminateCnf", i, i2));
    }

    public void e(int i, int i2) {
        sendBroadcast(a("com.fgmicrotec.mobile.android.voip.AcceptInvitationCnf", i, i2));
    }

    public void a(String str, String str2, String str3, String str4, int i, int i2, int i3) {
        try {
            Intent intent = new Intent();
            intent.setAction("com.fgmicrotec.mobile.android.voip.IncomingCallInd");
            intent.putExtra("extra_uri_to_call", str);
            intent.putExtra("extra_display_name", str2);
            intent.putExtra("extra_session_id", i);
            if (i2 == com.fgmicrotec.mobile.android.fgmag.VoIP.g.SESSION_IP_VIDEO_CALL.ordinal()) {
                intent.putExtra("extra_is_video_call", 1);
            } else {
                intent.putExtra("extra_is_video_call", 0);
            }
            sendBroadcast(intent);
            ar = i;
            this.as = str;
            this.at = str2;
            aj();
            i("com.mavenir.android.action.qosRinging");
        } catch (Exception e) {
            q.d("CallService", "No activity found to handle VoipServiceIntents.INCOMING_CALL_IND");
        }
    }

    private void aj() {
        q.a("CallService", "setRejectIncomingCallInvitationTimeout()");
        this.V.postDelayed(this.aF, 30000);
    }

    private void ak() {
        q.a("CallService", "cancelRejectIncomingCallInvitationTimeout()");
        this.V.removeCallbacks(this.aF);
    }

    public void a(String str, String str2, int i, int i2, boolean z) {
        Intent intent = new Intent();
        intent.setAction("com.fgmicrotec.mobile.android.voip.InvitationRingingInd");
        intent.putExtra("extra_call_waiting", i2);
        intent.putExtra("extra_media_about_to_start", i);
        intent.putExtra("extra_stop_ringback", z);
        sendBroadcast(intent);
    }

    public void a(int i, String str, int i2) {
        Intent a = a("com.fgmicrotec.mobile.android.voip.InviteCancelCnf", i, i2);
        a.putExtra("extra_uri_to_call", str);
        sendBroadcast(a);
    }

    public void f(int i, int i2) {
        sendBroadcast(a("com.fgmicrotec.mobile.android.voip.RejectInvitationCnf", i, i2));
    }

    public void a(String str, String str2, int i) {
        Intent intent = new Intent();
        intent.setAction("com.fgmicrotec.mobile.android.voip.InvitationSessionProgressInd");
        intent.putExtra("extra_uri_to_call", str);
        intent.putExtra("extra_display_name", str2);
        intent.putExtra("extra_media_about_to_start", i);
        sendBroadcast(intent);
    }

    public void i(int i) {
        ak();
        Intent intent = new Intent();
        intent.setAction("com.fgmicrotec.mobile.android.voip.IncomingCallCanceledInd");
        intent.putExtra("extra_session_id", i);
        sendBroadcast(intent);
        if (!CallManager.a()) {
            if (ar > 0) {
                com.mavenir.android.common.e.a(null, com.mavenir.android.common.t.f.f(this.as), 3, System.currentTimeMillis());
            }
            com.fgmicrotec.mobile.android.fgvoip.e.a().g();
            com.mavenir.android.common.g.a().e();
        }
        ar = -1;
        this.as = null;
        this.at = null;
    }

    public void b(String str, String str2, int i) {
        q.a("CallService", "missedCallInd(): displayName: " + str2 + ", numberOrUri: " + str + ", callType: " + i);
        com.mavenir.android.common.e.a(null, com.mavenir.android.common.t.f.f(str), 3, System.currentTimeMillis());
        com.fgmicrotec.mobile.android.fgvoip.e.a().a(str2, str, null, System.currentTimeMillis());
    }

    public void h(int i, int i2) {
        b(false, i);
        ak();
        ar = -1;
        this.as = null;
        this.at = null;
        Intent intent = new Intent();
        intent.setAction("com.fgmicrotec.mobile.android.voip.SessionEndedInd");
        intent.putExtra("extra_session_id", i);
        intent.putExtra("extra_reason_code", i2);
        sendBroadcast(intent);
    }

    public void a(String str, String str2, String str3, String str4, int i) {
        if (this.aa) {
            this.V.postDelayed(this.aO, 5000);
            Intent intent = new Intent();
            intent.setAction("com.fgmicrotec.mobile.android.voip.DNDToggleCnf");
            intent.putExtra("extra_error_code", f.a);
            sendBroadcast(intent);
            return;
        }
        Y();
        intent = new Intent();
        intent.setAction("com.fgmicrotec.mobile.android.voip.InvitationAcceptedInd");
        intent.putExtra("extra_session_id", i);
        intent.putExtra("extra_uri_to_call", str);
        intent.putExtra("extra_display_name", str2);
        sendBroadcast(intent);
    }

    public void a(String str, int i, String str2, int i2, boolean z, int i3) {
        if (this.aa) {
            this.V.postDelayed(this.aO, 5000);
            Intent intent = new Intent();
            intent.setAction("com.fgmicrotec.mobile.android.voip.DNDToggleCnf");
            intent.putExtra("extra_error_code", -1);
            sendBroadcast(intent);
            return;
        }
        intent = new Intent();
        intent.setAction("com.fgmicrotec.mobile.android.voip.InvitationRejectedInd");
        intent.putExtra("extra_session_id", i2);
        intent.putExtra("extra_uri_to_call", str);
        intent.putExtra("extra_reason_code", i);
        intent.putExtra("extra_reason_phrase", str2);
        intent.putExtra("extra_is_invited", z);
        intent.putExtra("extra_busy_tone_type", i3);
        sendBroadcast(intent);
    }

    public void d(int i, int i2) {
        Intent intent = new Intent();
        intent.setAction("com.fgmicrotec.mobile.android.voip.SessionIdChangedInd");
        intent.putExtra("extra_original_session_id", i);
        intent.putExtra("extra_changed_session_id", i2);
        sendBroadcast(intent);
    }

    public void a(int i, int i2, int i3) {
        Intent intent = new Intent();
        intent.putExtra("extra_session_id", i);
        intent.putExtra("extra_error_code", i2);
        switch (i3) {
            case 0:
                intent.setAction("com.fgmicrotec.mobile.android.voip.CallHoldCnf");
                break;
            case 1:
                intent.setAction("com.fgmicrotec.mobile.android.voip.CallUnholdCnf");
                break;
            case 4:
                q.b("CallService", "supplementaryServiceCnf(VOIP_SUPPL_CONSULTATION_START): 2nd call initiating: sessionID=" + i);
                break;
            case 5:
                q.b("CallService", "supplementaryServiceCnf(VOIP_SUPPL_CONSULTATION_END): 2nd call ended: session ID=" + i);
                intent.setAction("com.fgmicrotec.mobile.android.voip.SessionEndedInd");
                break;
            case 6:
                intent.setAction("com.fgmicrotec.mobile.android.voip.CallToggleCnf");
                break;
            case 7:
                intent.setAction(this.ac ? "com.fgmicrotec.mobile.android.voip.CallParkCnf" : "com.fgmicrotec.mobile.android.voip.CallTransferCnf");
                this.ac = false;
                break;
            case 12:
                intent.setAction("com.fgmicrotec.mobile.android.voip.Call3WayCnf");
                break;
            default:
                q.d("CallService", "supplementaryServiceCnf(): unknown service ID = " + i3);
                break;
        }
        if (intent.getAction() != null) {
            sendBroadcast(intent);
        }
    }

    private void a(String str, int i, String str2, int i2, boolean z) {
        String d = com.mavenir.android.settings.c.c.d();
        q.b("CallService", "callConferenceMergeCallsReq(): conference factory URI: " + d + ", active session ID: " + i2 + ", active number: " + com.mavenir.android.common.t.f.f(str2) + ", held session ID: " + i + ", held number: " + com.mavenir.android.common.t.f.f(str));
        this.S.callConferenceMergeCallsReq(d, str, i, str2, i2, z);
    }

    public void i(int i, int i2) {
        if (i == f.a) {
            q.b("CallService", "callConferenceMergeCallsCnf(): errCode: " + i + ", nSessionID: " + i2);
        } else {
            q.d("CallService", "callConferenceMergeCallsCnf(): errCode: " + i);
        }
        Intent intent = new Intent("com.fgmicrotec.mobile.android.voip.CallConferenceCreateCnf");
        intent.putExtra("extra_error_code", i);
        intent.putExtra("extra_session_id", i2);
        sendBroadcast(intent);
    }

    private void a(String str, int i, String str2, boolean z) {
        String d = com.mavenir.android.settings.c.c.d();
        q.b("CallService", "callConferenceAddParticipantReq(): conference factory URI: " + d + ", active session ID: " + i + ", active uri: " + str + ", add uri: " + str2);
        this.S.callConferenceAddParticipantReq(d, str, i, str2, z);
    }

    public void j(int i, int i2) {
        if (i == f.a) {
            q.b("CallService", "callConferenceAddParticipantCnf(): errCode: " + i + ", nSessionID: " + i2);
        } else {
            q.d("CallService", "callConferenceAddParticipantCnf(): errCode: " + i);
        }
        Intent intent = new Intent("com.fgmicrotec.mobile.android.voip.CallConferenceCreateCnf");
        intent.putExtra("extra_error_code", i);
        intent.putExtra("extra_session_id", i2);
        sendBroadcast(intent);
    }

    private void a(String[] strArr) {
        q.b("CallService", "callConferenceCreateReq(): participants: " + com.mavenir.android.common.t.b.b(strArr, ","));
        this.S.callConferenceCreateReq(com.mavenir.android.settings.c.c.d(), strArr, strArr.length);
    }

    public void k(int i, int i2) {
        if (i == f.a) {
            q.b("CallService", "callConferenceCreateCnf(): errCode: " + com.fgmicrotec.mobile.android.fgmag.VoIP.f.values()[i].name() + ", nSessionID: " + i2);
        } else {
            q.d("CallService", "callConferenceCreateCnf(): errCode: " + com.fgmicrotec.mobile.android.fgmag.VoIP.f.values()[i].name());
        }
        Intent intent = new Intent("com.fgmicrotec.mobile.android.voip.CallConferenceCreateCnf");
        intent.putExtra("extra_error_code", i);
        intent.putExtra("extra_session_id", i2);
        sendBroadcast(intent);
    }

    public void a(boolean z) {
        q.b("CallService", "callParkInd: call " + (!z ? "NOT " : "") + "available");
        Intent intent = new Intent();
        if (z) {
            intent.setAction("com.fgmicrotec.mobile.android.voip.CallParkInd");
            intent.putExtra("extra_uri_to_call", g("P701"));
        } else {
            intent.setAction("com.fgmicrotec.mobile.android.voip.CallUnparkInd");
        }
        sendBroadcast(intent);
    }

    public void a(boolean z, int i, int i2, int i3, int i4) {
        q.b("CallService", "rtcpRRInd: totalLostPackets = " + i);
        q.b("CallService", "rtcpRRInd: jitter = " + i2);
        q.b("CallService", "rtcpRRInd: fractionalLoss = " + i3);
        q.b("CallService", "rtcpRRInd: roundTripDelay = " + i4);
        if (FgVoIP.U().M()) {
            Intent intent = new Intent("com.mitel.mobile.android.voip.ActionConnectionManagerHandoverRequest");
            intent.putExtra("extra_wifi_rssi", l.a((Context) this).M());
            intent.putExtra("extra_rtcp_info_fractional_packet_loss", i3);
            intent.putExtra("extra_rtcp_info_cumulative_packet_loss", i);
            intent.putExtra("extra_rtcp_info_jitter", i2);
            intent.putExtra("extra_rtcp_info_round_trip_delay", i4);
            sendBroadcast(intent);
        }
    }

    public void a(boolean z, int i) {
    }

    private String g(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("sip:");
        String b = com.mavenir.android.settings.c.c.b();
        stringBuffer.append(str);
        if (b.length() > 4) {
            stringBuffer.append(b.substring(4, b.length()));
        }
        return stringBuffer.toString();
    }

    public void a(String str, String str2) {
        q.b("CallService", "callHoldedInd: strUserURIs = " + str);
        Intent intent = new Intent("com.fgmicrotec.mobile.android.voip.CallHoldedInd");
        intent.putExtra("extra_display_name", str2);
        intent.putExtra("extra_uri_to_call", str);
        sendBroadcast(intent);
    }

    public void b(String str, String str2) {
        q.b("CallService", "callUnholdedInd: strUserURIs = " + str);
        Intent intent = new Intent("com.fgmicrotec.mobile.android.voip.CallUnholdedInd");
        intent.putExtra("extra_display_name", str2);
        intent.putExtra("extra_uri_to_call", str);
        sendBroadcast(intent);
    }

    private void al() {
        if (!this.ay && !FgVoIP.U().I()) {
            this.ay = true;
            int d = com.mavenir.android.settings.c.k.d();
            if (d == -3) {
                com.mavenir.android.settings.c.k.a(0);
                d = 0;
            }
            q.b("CallService", "provisioningReq() ver = " + d);
            String p = l.a((Context) this).p();
            String k = l.a((Context) this).k();
            if (k.length() == 0) {
                k = l.a((Context) this).l();
            }
            this.S.provisioningReq("app.mavenir.com/provisioning-server/server2.php", d, p, k, l.a((Context) this).b(), l.a((Context) this).a(false), l.a((Context) this).i(), l.a((Context) this).j(), l.a((Context) this).g(), l.a((Context) this).h(), ar());
        }
    }

    public void a(int i, int i2, int i3, String str, int i4, String str2, String str3, String str4, String str5, String str6) {
        q.b("CallService", "provisioningCnf() nErrorCode = " + i + ", nVer = " + i2);
        this.ay = false;
        if (p.d() == null) {
            com.mavenir.android.settings.c.g.a();
        }
        Intent intent;
        if (i == 0) {
            if (str3 == null || str3.length() <= 0) {
                q.b("CallService", "provisioningCnf(): Provisioning caused NO settings update!");
                this.ax = true;
                return;
            }
            String h;
            q.b("CallService", "provisioningCnf(): Provisioning - updating settings!");
            p.d(str3);
            if (i3 == 0 || i3 == 1 || i3 == 2) {
                v.e(i3);
            }
            if (str != null && str.length() > 0) {
                v.a(new String[]{str});
            }
            if (i4 > 0) {
                v.a(i4);
            }
            if (str2 != null && str2.length() > 0) {
                p.c(str2);
                p.h(com.mavenir.android.common.t.f.f(str2));
                h = h(str2);
                if (h != null) {
                    com.mavenir.android.settings.c.c.b(h);
                    com.mavenir.android.settings.c.c.d("sip:pickup" + h.substring(h.indexOf("@")));
                }
            }
            if (str4 != null && str4.length() > 0) {
                p.e(str4);
            }
            h = l.a((Context) this).p();
            com.mavenir.android.settings.c.k.a(i2);
            com.mavenir.android.settings.c.k.a(h);
            p.k(h);
            intent = new Intent();
            intent.setAction("ActivationActions.ActionProvisioningSuccess");
            intent.putExtra("ActivationExtras.ExtraPublicURI", str2);
            intent.putExtra("ActivationExtras.ExtraVersion", com.mavenir.android.settings.c.k.d());
            if (str5 != null) {
                intent.putExtra("ActivationExtras.ExtraError", str5);
            }
            if (str6 != null) {
                intent.putExtra("ActivationExtras.ExtraPin", str6);
            }
            o.a((Context) this).a(intent);
            if (A) {
                this.T.postDelayed(this.aJ, 5000);
            } else {
                this.af = 5000;
                this.T.post(this.aI);
            }
            this.W.post(new Runnable(this) {
                final /* synthetic */ CallService a;

                {
                    this.a = r1;
                }

                public void run() {
                    FgVoIP.U().i();
                }
            });
            if (FgVoIP.U().ai()) {
                aa.a((Context) this).a();
            }
        } else if (com.mavenir.android.settings.c.k.d() == 0) {
            q.d("CallService", "provisioningCnf(): error code: " + i);
            if (str5 != null) {
                q.d("CallService", "provisioningCnf: error message: " + str5);
            }
            if (!com.mavenir.android.settings.c.k.b()) {
                if (!this.ad && str5 == null) {
                    str5 = getString(com.fgmicrotec.mobile.android.fgvoip.f.k.registration_status_no_wifi_data);
                }
                intent = new Intent();
                intent.setAction("ActivationActions.ActionProvisioningFailed");
                intent.putExtra("ActivationExtras.ExtraError", str5);
                o.a((Context) this).a(intent);
            } else {
                return;
            }
        } else {
            return;
        }
        if (com.mavenir.android.settings.c.k.d() != 0) {
            this.ax = true;
        }
    }

    public void a(int i, String str, String str2) {
        q.a("CallService", "provisioningAuthInd(): " + (i == a.TYPE_REQUEST_PIN.ordinal() ? "TYPE_PIN" : "TYPE_SMS") + ", TAN=" + str + ", SMS=" + str2);
        if (i == a.TYPE_SMS.ordinal()) {
            c(str, str2);
        } else if (i == a.TYPE_PIN.ordinal()) {
            i = a.TYPE_REQUEST_PIN.ordinal();
        }
        this.X.post(new d(this, i, str));
    }

    public void a(byte[] bArr, byte[][] bArr2, boolean z) {
        this.Y.post(new e(this, bArr, bArr2, z));
    }

    public void y() {
        this.S.tlsSetTimersReq(com.mavenir.android.settings.c.q.m(), com.mavenir.android.settings.c.q.n());
    }

    public void d() {
        q.a("CallService", "playAlertToneInd()");
        sendBroadcast(new Intent("com.fgmicrotec.mobile.android.voip.PlayAlertToneInd"));
        i("com.mavenir.android.action.qosStatsDeterioted");
    }

    public void e() {
        q.a("CallService", "qosStatsImprovedInd()");
        sendBroadcast(new Intent("com.fgmicrotec.mobile.android.voip.StopAlertToneInd"));
        i("com.mavenir.android.action.qosStatsImproved");
    }

    private void am() {
        this.S.qosReportReq();
        if ((FgVoIP.U().aj() || FgVoIP.U().ai()) && e.a()) {
            this.T.postDelayed(this.aU, 5500);
        }
    }

    private void an() {
        this.S.qosReportMarkEntryReq();
    }

    public void a(int i, int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4, int[] iArr5, int[] iArr6) {
        q.a("CallService", "qosReportCnf(): Received " + i + " entries");
        final int i2 = i;
        final int[] iArr7 = iArr;
        final int[] iArr8 = iArr3;
        final int[] iArr9 = iArr2;
        final int[] iArr10 = iArr4;
        final int[] iArr11 = iArr5;
        final int[] iArr12 = iArr6;
        new Thread(new Runnable(this) {
            final /* synthetic */ CallService h;

            public void run() {
                long currentTimeMillis = System.currentTimeMillis();
                r.a();
                for (int i = 0; i < i2; i++) {
                    int i2 = iArr7[i];
                    if (FgVoIP.U().aq()) {
                        i2 = -99999;
                    }
                    r.a(i2, iArr8[i], iArr9[i], iArr10[i], iArr11[i], iArr12[i]);
                }
                q.a("CallService", "qosReportCnf(): written " + i2 + " entries in " + (System.currentTimeMillis() - currentTimeMillis) + " ms");
            }
        }).start();
    }

    public void l(int i, int i2) {
        FgVoIP.U().a(com.mavenir.android.fragments.f.a.VOIPC_PROXY, i, i2, null);
        if (i == com.fgmicrotec.mobile.android.fgmag.VoIP.d.FGVOIPCPROXY_POPUP_RESTARTING_SERVICE.ordinal() || i == com.fgmicrotec.mobile.android.fgmag.VoIP.d.FGVOIPCPROXY_POPUP_NO_SRTP_AGREEMENT.ordinal() || i == com.fgmicrotec.mobile.android.fgmag.VoIP.d.FGVOIPCPROXY_POPUP_INVITE_SIP_ERROR.ordinal()) {
            sendBroadcast(new Intent("CallManager.ActionBlockClosing"));
        }
    }

    public void a(String str) {
        q.b("CallService", "stnReceivedInd(): STN = " + str);
        if (!TextUtils.isEmpty(str) && !str.toLowerCase().equals("none")) {
            x.a(str);
        }
    }

    public void b(int i, String str, int i2) {
        q.b("CallService", "ussdStringReceivedInd(): Server String = " + str);
        this.T.post(new f(this, i, str, i2));
    }

    public void g() {
        q.b("CallService", "videoViewShouldBeDisplayedInd()");
        this.T.post(this.aV);
    }

    public void h() {
        q.b("CallService", "unrecoverableSpiritErrorOccuredInd()");
        N = true;
        this.T.postDelayed(this.aS, 1000);
    }

    public void m(int i) {
        q.b("CallService", "addVideoCnf()");
        Intent intent = new Intent("com.fgmicrotec.mobile.android.voip.AddVideoCnf");
        intent.putExtra("extra_error_code", i);
        sendBroadcast(intent);
    }

    public void n(int i) {
        q.b("CallService", "removeVideoCnf()");
        Intent intent = new Intent("com.fgmicrotec.mobile.android.voip.RemoveVideoCnf");
        intent.putExtra("extra_error_code", i);
        sendBroadcast(intent);
    }

    public void i() {
        q.b("CallService", "videoAddedInd()");
        sendBroadcast(new Intent("com.fgmicrotec.mobile.android.voip.VideoAddedInd"));
    }

    public void j() {
        q.b("CallService", "videoRemovedInd()");
        sendBroadcast(new Intent("com.fgmicrotec.mobile.android.voip.VideoRemovedInd"));
    }

    public void a(String str, int i, String str2) {
        q.b("CallService", "notifyRegStateInd(): IMPU=" + str + " State=" + i + " Event=" + str2);
        if (i == 0) {
            this.T.post(this.aK);
        }
    }

    private void o(int i, int i2) {
        this.S.videoViewShouldBeDisplayedRes(i, i2);
    }

    public void z() {
        if (FgVoIP.U().p()) {
            this.S.setCallContinuityReq(x.b());
        }
    }

    public void l(int i) {
        q.a("CallService", "setCallContinuityCnf(): errorCode: " + i);
    }

    public void A() {
        boolean u = com.mavenir.android.settings.c.k.u();
        q.a("CallService", "updateMessagingFeatureReq(): enabled: " + (u ? "yes" : "no"));
        this.S.updateMessagingFeatureReq(u);
    }

    private String h(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        if (str.length() <= 10) {
            return null;
        }
        String str2 = "sip:";
        if (str2.compareTo(str.substring(0, 4)) != 0) {
            return null;
        }
        stringBuffer.append(str2);
        int indexOf = str.indexOf(64);
        if (indexOf <= 3 || indexOf >= str.length()) {
            return null;
        }
        stringBuffer.append(str.substring(indexOf, str.length()));
        return stringBuffer.toString();
    }

    private void ao() {
        this.ag = new i();
        this.ah = new a(this.ag);
        this.ah.a();
        this.ah.a(getFilesDir());
    }

    private void ap() {
        try {
            this.aj++;
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            this.ah.a(this.aj, "http://app.mavenir.com/Android/android2.txt".replace("android2", "version" + packageInfo.versionCode), "vowVer.txt");
            q.b("CallService", "http://app.mavenir.com/Android/android2.txt".replace("android2", "version" + packageInfo.versionCode));
        } catch (Exception e) {
            q.d("CallService", "Update get failed!");
        }
    }

    private void aq() {
        com.fgmicrotec.mobile.android.fgvoip.e.a().f();
        com.fgmicrotec.mobile.android.fgvoip.e.a().d();
    }

    private String ar() {
        return this.w;
    }

    private void c(String str, String str2) {
        SmsManager smsManager = SmsManager.getDefault();
        PendingIntent broadcast = PendingIntent.getBroadcast(this, 0, new Intent("CallServiceActions.ActionSmsSent"), 0);
        String str3 = "" + "TAN=" + str + "; MSISDN=" + this.w + ";";
        smsManager.sendTextMessage(str2, null, str3, broadcast, null);
        q.a("CallService", "Send sms '" + str3 + "' to " + str2);
    }

    private void i(String str) {
        if (com.mavenir.android.settings.c.h.j()) {
            q.b("CallService", "sendQoSBroadcastEvent(): action=" + str);
            sendBroadcast(new Intent(str));
        }
    }

    public void b(String str) {
        try {
            q.a("CallService", "removefromCallHistory");
            Cursor query = getContentResolver().query(Calls.CONTENT_URI, null, "type = 2", null, "date DESC  LIMIT 2");
            String d = x.d();
            if (query.getCount() > 0) {
                q.a("CallService", "has values c.getCount()" + query.getCount());
                query.moveToFirst();
                do {
                    String string = query.getString(query.getColumnIndex("number"));
                    q.a("CallService", "time date mills" + query.getLong(query.getColumnIndex("date")));
                    q.a("CallService", "CallLog.Calls._ID " + query.getInt(query.getColumnIndex("_id")));
                    q.a("CallService", "has values phoneNo" + string);
                    if (string.equals(d)) {
                        q.a("CallService", "has values phoneNo Vcc no" + string);
                        q.a("CallService", "CallLog.Calls._ID VCC id" + query.getInt(query.getColumnIndex("_id")));
                        c(string);
                    }
                } while (query.moveToNext());
            }
            query.close();
        } catch (Exception e) {
            q.d("CallService", "Exception, unable to remove # from call log: " + e.toString());
        }
    }

    public void c(String str) {
        try {
            q.a("CallService", "removefromCallHistory");
            Cursor query = getContentResolver().query(Calls.CONTENT_URI, null, "number = ? ", new String[]{str}, "date DESC");
            if (query.moveToFirst()) {
                q.a("CallService", " idOfRowToDelete  " + query.getInt(query.getColumnIndex("_id")));
                q.a("CallService", "deleted logs : rows effected" + getContentResolver().delete(Calls.CONTENT_URI, "_id = ? ", new String[]{String.valueOf(r0)}));
            }
        } catch (Exception e) {
            q.d("CallService", "Exception, unable to remove # from call log: " + e.toString());
        }
    }

    public void a(long j) {
        this.aC = j;
    }

    public static boolean B() {
        return aA;
    }
}
