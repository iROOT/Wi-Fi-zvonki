package com.fgmicrotec.mobile.android.fgvoipcommon;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.fgmicrotec.mobile.android.fgmag.VoIP;
import com.mavenir.android.vtow.activation.ActivationAdapter;

public class d {
    private static SharedPreferences a;
    private Context b;

    public d(Context context) {
        this.b = context;
        a = this.b.getSharedPreferences("RokeTestConfigPrefs", 0);
    }

    public static boolean a() {
        return a.getBoolean("ROKE Test Config Enabled", false);
    }

    public static void a(boolean z) {
        Editor edit = a.edit();
        edit.putBoolean("ROKE Test Config Enabled", z);
        edit.commit();
    }

    public static int b() {
        return a.getInt("ROKE Test Config WiFi RSSI Threshold", -80);
    }

    public static void a(int i) {
        Editor edit = a.edit();
        edit.putInt("ROKE Test Config WiFi RSSI Threshold", i);
        edit.commit();
    }

    public static int c() {
        return a.getInt("ROKE Test Config RoundTrip Delay Threshold", VoIP.REASON_CODE_BUSY_EVERYWHERE);
    }

    public static void b(int i) {
        Editor edit = a.edit();
        edit.putInt("ROKE Test Config RoundTrip Delay Threshold", i);
        edit.commit();
    }

    public static int d() {
        return a.getInt("ROKE Test Config Jitter Threshold", ActivationAdapter.REASON_NEW_CONFIG_DATA);
    }

    public static void c(int i) {
        Editor edit = a.edit();
        edit.putInt("ROKE Test Config Jitter Threshold", i);
        edit.commit();
    }

    public static int e() {
        return a.getInt("ROKE Test Config Fractional Loss Threshold", 10);
    }

    public static void d(int i) {
        Editor edit = a.edit();
        edit.putInt("ROKE Test Config Fractional Loss Threshold", i);
        edit.commit();
    }

    public static int f() {
        return a.getInt("ROKE Test Config Cumulative Loss Threshold", 1000);
    }

    public static void e(int i) {
        Editor edit = a.edit();
        edit.putInt("ROKE Test Config Cumulative Loss Threshold", i);
        edit.commit();
    }
}
