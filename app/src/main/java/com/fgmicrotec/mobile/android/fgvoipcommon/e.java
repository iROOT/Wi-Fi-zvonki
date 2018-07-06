package com.fgmicrotec.mobile.android.fgvoipcommon;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.fgmicrotec.mobile.android.fgvoip.FgVoIP;

public class e {
    private static SharedPreferences a;
    private Context b;

    public e(Context context) {
        this.b = context;
        a = this.b.getSharedPreferences("SpiritTestConfigPrefs", 0);
    }

    public static boolean a() {
        return a.getBoolean("SPIRIT Test Config USe Audio", FgVoIP.U().B());
    }

    public static void a(boolean z) {
        Editor edit = a.edit();
        edit.putBoolean("SPIRIT Test Config USe Audio", z);
        edit.commit();
    }

    public static boolean b() {
        return a.getBoolean("SPIRIT Test Config Audio ARS", false);
    }

    public static void b(boolean z) {
        Editor edit = a.edit();
        edit.putBoolean("SPIRIT Test Config Audio ARS", z);
        edit.commit();
    }

    public static boolean c() {
        return a.getBoolean("SPIRIT Test Config Video ARS", false);
    }

    public static void c(boolean z) {
        Editor edit = a.edit();
        edit.putBoolean("SPIRIT Test Config Video ARS", z);
        edit.commit();
    }

    public static int d() {
        return a.getInt("SPIRIT Test Config Video Packetization", 0);
    }

    public static void a(int i) {
        Editor edit = a.edit();
        edit.putInt("SPIRIT Test Config Video Packetization", i);
        edit.commit();
    }

    public static boolean e() {
        return a.getBoolean("SPIRIT Test Config Logs", false);
    }

    public static void d(boolean z) {
        Editor edit = a.edit();
        edit.putBoolean("SPIRIT Test Config Logs", z);
        edit.commit();
    }
}
