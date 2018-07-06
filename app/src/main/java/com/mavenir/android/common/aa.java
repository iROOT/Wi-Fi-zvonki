package com.mavenir.android.common;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.fgmicrotec.mobile.android.fgvoip.FgVoIP;
import com.mavenir.android.fragments.f.b;
import com.mavenir.android.settings.c.k;
import com.mavenir.android.settings.c.y;
import java.util.ArrayList;
import java.util.List;

public class aa {
    private static aa a = null;
    private static Context b = null;
    private boolean c = true;
    private WifiManager d = null;
    private a e = null;

    private class a extends BroadcastReceiver {
        final /* synthetic */ aa a;

        private a(aa aaVar) {
            this.a = aaVar;
        }

        public void onReceive(Context context, Intent intent) {
            if (intent != null && intent.getAction().equals("android.net.wifi.SCAN_RESULTS")) {
                this.a.a(this.a.d.getScanResults());
                this.a.g();
            }
        }
    }

    private aa(Context context) {
        b = context;
        this.d = (WifiManager) b.getSystemService("wifi");
        this.e = new a();
    }

    public static aa a(Context context) {
        if (a == null) {
            a = new aa(context);
        }
        return a;
    }

    public boolean a() {
        if (!FgVoIP.U().J()) {
            return false;
        }
        List<WifiConfiguration> f = f();
        List b = y.b();
        int size = b.size();
        if (f == null || f.isEmpty()) {
            q.c("WifiWhitelist", "initializeWhitelist(): no configured networks returned");
            if (size == 0 && this.c) {
                FgVoIP.U().a(com.mavenir.android.fragments.f.a.SYSTEM, b.ENABLE_WIFI.ordinal());
                this.c = false;
            }
            return false;
        } else if (k.d() < 0) {
            return false;
        } else {
            if (size == 0) {
                y.a();
                for (WifiConfiguration wifiConfiguration : f) {
                    if (!TextUtils.isEmpty(wifiConfiguration.SSID)) {
                        y.a(wifiConfiguration.SSID.replace("\"", ""), TextUtils.isEmpty(wifiConfiguration.BSSID) ? "" : wifiConfiguration.BSSID, true);
                    }
                }
                return true;
            }
            for (int i = 0; i < size; i++) {
                String a = ((z) b.get(i)).a();
                if (!a((List) f, a, ((z) b.get(i)).b())) {
                    y.a(y.a(a));
                }
            }
            FgVoIP.U().a(b, "com.mavenir.android.action_backup_whitelist");
            return false;
        }
    }

    public boolean a(List<WifiConfiguration> list, String str, String str2) {
        for (WifiConfiguration wifiConfiguration : list) {
            if (TextUtils.isEmpty(wifiConfiguration.SSID)) {
                if (TextUtils.isEmpty(wifiConfiguration.BSSID)) {
                    q.d("WifiWhitelist", "isInConfiguredNetworks(): SSID: " + wifiConfiguration.SSID + ", BSSID: " + wifiConfiguration.BSSID);
                } else if (wifiConfiguration.BSSID.equals(str2)) {
                    return true;
                }
            } else if (wifiConfiguration.SSID.replace("\"", "").equals(str)) {
                return true;
            }
        }
        return false;
    }

    public void b() {
        q.a("WifiWhitelist", "clearWhitelist()");
        y.a();
        if (FgVoIP.U().at()) {
            q.a("WifiWhitelist", "clearWhitelist(): logging out due to whitelist clearing");
            FgVoIP.U().a("com.fgmicrotec.mobile.android.voip.LogoutFromServerReq");
        }
    }

    public void a(String str, String str2, boolean z) {
        if (str != null || str2 != null) {
            q.a("WifiWhitelist", "addWifiToWhitelist(): ssid: " + str + ", bssid: " + str2 + ", ignore: " + z);
            y.a(str.replace("\"", ""), str2, z);
        }
    }

    public void c() {
        String replace = l.a(b).P().replace("\"", "");
        String Q = l.a(b).Q();
        if (!TextUtils.isEmpty(replace) || !TextUtils.isEmpty(Q)) {
            q.a("WifiWhitelist", "updateWifiInWhitelist(): ssid: " + replace + ", bssid: " + Q);
            if (replace != null) {
                long a = y.a(replace);
                if (a > -1) {
                    y.a(a, replace, Q, y.d(a));
                }
            }
        }
    }

    public void a(long j) {
        q.a("WifiWhitelist", "removeFromWhiteList(): ssid: " + y.b(j) + ", bssid: " + y.c(j));
        y.a(j);
        if (!d() && FgVoIP.U().at()) {
            q.a("WifiWhitelist", "removeFromWhiteList(): logging out due to hotspot removal from whitelist");
            FgVoIP.U().a("com.fgmicrotec.mobile.android.voip.LogoutFromServerReq");
        }
    }

    public void a(long j, boolean z) {
        q.a("WifiWhitelist", "updateIgnoredState(): ssid: " + y.b(j) + ", bssid: " + y.c(j) + ", ignore: " + z);
        y.a(j, z);
        if (e() && FgVoIP.U().at()) {
            q.a("WifiWhitelist", "updateIgnoredState(): logging out due to hotspot removal from whitelist");
            FgVoIP.U().a("com.fgmicrotec.mobile.android.voip.LogoutFromServerReq");
        } else if (d() && !FgVoIP.U().at() && !FgVoIP.U().au()) {
            q.a("WifiWhitelist", "updateIgnoredState(): loggin in due to hotspot adding from whitelist");
            FgVoIP.U().a("com.fgmicrotec.mobile.android.voip.LoginToServerReq");
        }
    }

    public boolean a(String str, String str2) {
        if (!FgVoIP.U().J()) {
            return true;
        }
        if (TextUtils.isEmpty(str) && TextUtils.isEmpty(str2)) {
            q.a("WifiWhitelist", "isWifiInWhitelist(): false. SSID and BSSID empty");
            return false;
        }
        boolean b = y.b(str == null ? "" : str.replace("\"", ""), str2);
        q.a("WifiWhitelist", "isWifiInWhitelist(): " + b + "[ ssid: " + str + ", bssid: " + str2 + " ]");
        return b;
    }

    public boolean b(String str, String str2) {
        if (!FgVoIP.U().J()) {
            return false;
        }
        if (TextUtils.isEmpty(str) && TextUtils.isEmpty(str2)) {
            q.a("WifiWhitelist", "isWifiInIgnorelist(): false. SSID and BSSID empty");
            return false;
        }
        boolean a = y.a(str == null ? "" : str.replace("\"", ""), str2);
        q.a("WifiWhitelist", "isWifiInIgnorelist(): " + a + "[ ssid: " + str + ", bssid: " + str2 + " ]");
        return a;
    }

    public boolean d() {
        if (!FgVoIP.U().J()) {
            return true;
        }
        String P = l.a(b).P();
        String Q = l.a(b).Q();
        if (TextUtils.isEmpty(P) && TextUtils.isEmpty(Q)) {
            return false;
        }
        return a(P, Q);
    }

    public boolean e() {
        if (!FgVoIP.U().J()) {
            return false;
        }
        Object P = l.a(b).P();
        Object Q = l.a(b).Q();
        if (TextUtils.isEmpty(P) && TextUtils.isEmpty(Q)) {
            return false;
        }
        return b(P, Q);
    }

    public List<WifiConfiguration> f() {
        ArrayList arrayList = new ArrayList();
        return this.d.getConfiguredNetworks();
    }

    private void g() {
        b.unregisterReceiver(this.e);
    }

    @TargetApi(17)
    private void a(List<ScanResult> list) {
        q.a("WifiWhitelist", "-------------------WIFI SCAN RESULTS-------------------");
        StringBuilder stringBuilder = new StringBuilder(64);
        for (ScanResult scanResult : list) {
            stringBuilder.setLength(0);
            if (VERSION.SDK_INT >= 17) {
                stringBuilder.append(", timestamp: ").append(scanResult.timestamp);
            }
            q.a("WifiWhitelist", "SSID: " + scanResult.SSID + ", BSSID: " + scanResult.BSSID + ", level: " + scanResult.level + ", frequency: " + scanResult.frequency + ", capabilities: " + scanResult.capabilities + stringBuilder);
        }
        q.a("WifiWhitelist", "-----.--------------END SCAN RESULTS-------------------");
    }
}
