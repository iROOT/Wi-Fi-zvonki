package com.fgmicrotec.mobile.android.fgmag;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import com.fgmicrotec.mobile.android.fgvoip.FgVoIP;
import com.mavenir.android.common.l;
import com.mavenir.android.common.q;
import com.mavenir.android.common.y;
import com.mavenir.android.settings.c.k;
import java.net.InetAddress;

public class DataConnectionManager {
    private static final int ERR_GENERAL = 1;
    private static final int ERR_OK = 0;
    private static final int FGSOC_UNKNOWN_CID = 65536;
    private static final int FGSOC_UNKNOWN_LAC = 65536;
    private static final int FGSOC_UNKNOWN_MCC = 1000;
    private static final int FGSOC_UNKNOWN_MNC = 1000;
    private static final String TAG = "DataConnectionManager";
    private static final int UNKNOWN_CID = -1;
    private static final int UNKNOWN_HOST_ADDRESS = -1;
    private static boolean bReceiving;
    private static DataConnectionManager dataConnectionManager;
    private static a iConnectionHandler;
    private static Context iContext;
    private static b iReceiver;
    private static c iVPNReceiver;
    private State iConnectionState = State.UNKNOWN;

    public enum a {
        ANY,
        GPRS,
        EDGE,
        WCDMA,
        WLAN,
        VPN,
        LTE
    }

    private class b extends BroadcastReceiver {
        final /* synthetic */ DataConnectionManager a;

        private b(DataConnectionManager dataConnectionManager) {
            this.a = dataConnectionManager;
        }

        public void onReceive(Context context, Intent intent) {
            q.b(DataConnectionManager.TAG, "ConnectionChangeReceiver.onReceive(): " + intent);
            if (intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")) {
                boolean booleanExtra = intent.getBooleanExtra("noConnectivity", false);
                q.b(DataConnectionManager.TAG, "ConnectionChangeReceiver.onReceive(): noConnectivity = " + booleanExtra);
                NetworkInfo networkInfo = (NetworkInfo) intent.getParcelableExtra("networkInfo");
                q.b(DataConnectionManager.TAG, "ConnectionChangeReceiver.onReceive(): aReason = " + intent.getStringExtra("reason"));
                q.b(DataConnectionManager.TAG, "ConnectionChangeReceiver.onReceive(): aIsFailover = " + intent.getBooleanExtra("isFailover", false));
                if (booleanExtra) {
                    this.a.iConnectionState = State.DISCONNECTED;
                    int c;
                    if (networkInfo.getType() == 0) {
                        c = DataConnectionManager.iConnectionHandler.c(a.GPRS.ordinal());
                        if (c == -1) {
                            return;
                        }
                        if (!y.a().d() || !y.a().e()) {
                            DataConnectionManager.closeDataConnectionInd(c, 1);
                            DataConnectionManager.iConnectionHandler.b(c);
                            return;
                        }
                        return;
                    }
                    c = DataConnectionManager.iConnectionHandler.c(a.WLAN.ordinal());
                    if (c == -1) {
                        return;
                    }
                    if (!y.a().d() || !y.a().e()) {
                        DataConnectionManager.closeDataConnectionInd(c, 1);
                        DataConnectionManager.iConnectionHandler.b(c);
                        return;
                    }
                    return;
                }
                this.a.iConnectionState = State.CONNECTED;
                return;
            }
            q.b(DataConnectionManager.TAG, "ConnectionChangeReceiver.onReceive(): ... called with" + intent);
        }
    }

    private class c extends BroadcastReceiver {
        final /* synthetic */ DataConnectionManager a;

        private c(DataConnectionManager dataConnectionManager) {
            this.a = dataConnectionManager;
        }

        public void onReceive(Context context, Intent intent) {
            q.b(DataConnectionManager.TAG, "VPNConnectionChangeReceiver.onReceive(): ... called");
            if (intent.getAction().equals("DataConnectionManagerIntents.ActionVPNDisconnected")) {
                NetworkInfo activeNetworkInfo = ((ConnectivityManager) DataConnectionManager.iContext.getSystemService("connectivity")).getActiveNetworkInfo();
                this.a.iConnectionState = State.DISCONNECTED;
                int c;
                if (activeNetworkInfo.getType() == 0) {
                    c = DataConnectionManager.iConnectionHandler.c(a.GPRS.ordinal());
                    if (c == -1) {
                        return;
                    }
                    if (!y.a().d() || !y.a().e()) {
                        DataConnectionManager.closeDataConnectionInd(c, 1);
                        DataConnectionManager.iConnectionHandler.b(c);
                        return;
                    }
                    return;
                }
                c = DataConnectionManager.iConnectionHandler.c(a.WLAN.ordinal());
                if (c == -1) {
                    return;
                }
                if (!y.a().d() || !y.a().e()) {
                    DataConnectionManager.closeDataConnectionInd(c, 1);
                    DataConnectionManager.iConnectionHandler.b(c);
                    return;
                }
                return;
            }
            q.b(DataConnectionManager.TAG, "VPNConnectionChangeReceiver.onReceive(): ... called with" + intent);
        }
    }

    public static native void closeDataConnectionInd(int i, int i2);

    public native void closeDataConnectionCnf(int i, int i2);

    public native void getBearersCnf(int i, int i2, int[] iArr, int[] iArr2, int[] iArr3, String[] strArr);

    public native void openDataConnectionCnf(int i, int i2, int i3, int i4, int i5, int i6, String str, String str2, int i7, int i8, int i9, int i10);

    public native void setDefaultBearerCnf(int i, int i2, int i3);

    private DataConnectionManager() {
        iReceiver = new b();
        iVPNReceiver = new c();
        iConnectionHandler = new a();
    }

    public static void setContext(Context context) {
        iContext = context;
        startMonitoring();
        iConnectionHandler.a();
    }

    public static void releaseFromContext() {
        stopMonitoring();
        iConnectionHandler.a();
    }

    public static void clearAllConnectionsData() {
        iConnectionHandler.a();
    }

    public static void recoverAfterSilentIPChange() {
        int c;
        if (((ConnectivityManager) iContext.getSystemService("connectivity")).getActiveNetworkInfo().getType() == 0) {
            c = iConnectionHandler.c(a.LTE.ordinal());
            if (!(c == -1 || (y.a().d() && y.a().e()))) {
                closeDataConnectionInd(c, 1);
                iConnectionHandler.b(c);
            }
        } else {
            c = iConnectionHandler.c(a.WLAN.ordinal());
            if (!(c == -1 || (y.a().d() && y.a().e()))) {
                closeDataConnectionInd(c, 1);
                iConnectionHandler.b(c);
            }
        }
        iConnectionHandler.a();
    }

    public static InetAddress getLocalIpAddress() {
        return iConnectionHandler.b();
    }

    private static synchronized void startMonitoring() {
        synchronized (DataConnectionManager.class) {
            if (iContext != null) {
                if (!bReceiving) {
                    iContext.registerReceiver(iReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
                    iContext.registerReceiver(iVPNReceiver, new IntentFilter("DataConnectionManagerIntents.ActionVPNDisconnected"));
                    bReceiving = true;
                }
            }
        }
    }

    private static synchronized void stopMonitoring() {
        synchronized (DataConnectionManager.class) {
            if (iContext != null) {
                if (bReceiving) {
                    try {
                        iContext.unregisterReceiver(iReceiver);
                        iContext.unregisterReceiver(iVPNReceiver);
                    } catch (IllegalArgumentException e) {
                    }
                    bReceiving = false;
                }
            }
        }
    }

    private NetworkInfo getActiveNetworkInfo() {
        ConnectivityManager connectivityManager = (ConnectivityManager) iContext.getSystemService("connectivity");
        return connectivityManager == null ? null : connectivityManager.getActiveNetworkInfo();
    }

    private NetworkInfo getNetworkInfo(int i) {
        ConnectivityManager connectivityManager = (ConnectivityManager) iContext.getSystemService("connectivity");
        return connectivityManager == null ? null : connectivityManager.getNetworkInfo(i);
    }

    private void setPreferredNetwork(int i) {
        ((ConnectivityManager) iContext.getSystemService("connectivity")).setNetworkPreference(i);
    }

    public void openDataConnection(int i, int i2, int i3, int i4) {
        if (!bReceiving) {
            openDataConnectionCnf(1, -1, i2, i, -1, i3, null, null, 1000, 1000, 65536, 65536);
        } else if (this.iConnectionState == State.CONNECTED) {
            NetworkInfo activeNetworkInfo = getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                q.b(TAG, "openDataConnection(): No active network, but there's connectivity! Maybe LAN?");
                r6 = a.WLAN.ordinal();
                iConnectionHandler.a(i, r6, i3, i4);
                r5 = iConnectionHandler.c(r6);
                r8 = iConnectionHandler.a(r6, i3);
                r3 = iConnectionHandler.b(r6, i3);
                r10 = l.a(iContext).P();
                r11 = l.a(iContext).Q();
                if (r8 != 0) {
                    q.b(TAG, "ConnectivityManager.TYPE_LAN CID " + r5 + " Address " + (r3 != null ? r3.getHostAddress() : "null"));
                    openDataConnectionCnf(0, r5, r6, i, r8, i3, r10, r11, 1000, 1000, 65536, 65536);
                    return;
                }
                iConnectionHandler.b(r5);
                openDataConnectionCnf(1, -1, r6, i, -1, i3, r10, r11, 1000, 1000, 65536, 65536);
                return;
            }
            if ((k.d() > 0 ? 1 : null) == null && activeNetworkInfo.getType() == 0) {
                int w = l.a(iContext).w();
                int x = l.a(iContext).x();
                int u = l.a(iContext).u();
                int s = l.a(iContext).s();
                q.b(TAG, "openDataConnection(): ConnectivityManager.TYPE_MOBILE aBearer=" + a.values()[i2].name() + "(" + i2 + ")");
                if (i2 == a.ANY.ordinal() || i2 == a.GPRS.ordinal() || i2 == a.LTE.ordinal()) {
                    if (l.a(iContext).A()) {
                        r6 = a.LTE.ordinal();
                        u = l.a(iContext).v();
                    } else {
                        r6 = a.GPRS.ordinal();
                    }
                    iConnectionHandler.a(i, r6, i3, i4);
                    r5 = iConnectionHandler.c(r6);
                    r8 = iConnectionHandler.a(r6, i3);
                    r3 = iConnectionHandler.b(r6, i3);
                    q.b(TAG, "openDataConnection(): ConnectivityManager.TYPE_MOBILE (" + a.values()[r6].name() + "), CID=" + r5 + ", Address=" + (r3 != null ? r3.getHostAddress() : "null"));
                    openDataConnectionCnf(0, r5, r6, i, r8, i3, null, null, w, x, u, s);
                    return;
                }
                openDataConnectionCnf(1, -1, i2, i, -1, i3, null, null, w, x, u, s);
            } else if (i2 == a.ANY.ordinal() || i2 == a.WLAN.ordinal()) {
                r6 = a.WLAN.ordinal();
                iConnectionHandler.a(i, r6, i3, i4);
                r5 = iConnectionHandler.c(r6);
                r8 = iConnectionHandler.a(r6, i3);
                InetAddress b = iConnectionHandler.b(r6, i3);
                r10 = l.a(iContext).P();
                r11 = l.a(iContext).Q();
                q.b(TAG, "openDataConnection(): ConnectivityManager.TYPE_WIFI, CID=" + r5 + ", Address=" + (b != null ? b.getHostAddress() : "null"));
                if (b == null) {
                    openDataConnectionCnf(1, -1, r6, i, -1, i3, null, null, 1000, 1000, 65536, 65536);
                } else {
                    openDataConnectionCnf(0, r5, r6, i, r8, i3, r10, r11, 1000, 1000, 65536, 65536);
                }
            } else {
                openDataConnectionCnf(1, -1, i2, i, -1, i3, null, null, 1000, 1000, 65536, 65536);
            }
        } else if (this.iConnectionState == State.DISCONNECTED) {
            openDataConnectionCnf(1, -1, i2, i, -1, i3, null, null, 1000, 1000, 65536, 65536);
        } else {
            q.b(TAG, "openDataConnection(): No active network, but there's connectivity! Maybe LAN?");
            r6 = a.WLAN.ordinal();
            iConnectionHandler.a(i, r6, i3, i4);
            r5 = iConnectionHandler.c(r6);
            r8 = iConnectionHandler.a(r6, i3);
            r3 = iConnectionHandler.b(r6, i3);
            r10 = l.a(iContext).P();
            r11 = l.a(iContext).Q();
            if (r8 != 0) {
                q.b(TAG, "openDataConnection(): ConnectivityManager.TYPE_LAN CID " + r5 + " Address " + (r3 != null ? r3.getHostAddress() : "null"));
                openDataConnectionCnf(0, r5, r6, i, r8, i3, r10, r11, 1000, 1000, 65536, 65536);
                return;
            }
            iConnectionHandler.b(r5);
            openDataConnectionCnf(1, -1, r6, i, -1, i3, r10, r11, 1000, 1000, 65536, 65536);
        }
    }

    public void closeDataConnection(int i) {
    }

    public void requestProcessorWakelock() {
        FgVoIP.U().a(true, false, true);
    }

    public void getBearers() {
    }

    public void setDefaultBearer(int i, int i2) {
    }
}
