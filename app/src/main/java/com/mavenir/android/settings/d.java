package com.mavenir.android.settings;

import android.content.Context;
import com.mavenir.android.common.q;

public class d {
    public static String a = "common_preferences";
    public static String b = "last_cf_update";

    public static boolean a(Context context, String str, boolean z) {
        try {
            z = context.getSharedPreferences(a, 0).getBoolean(str, z);
        } catch (ClassCastException e) {
            q.d("ClientSharedPreferences", "getPreference(): " + e);
        }
        return z;
    }

    public static long a(Context context, String str, long j) {
        try {
            j = context.getSharedPreferences(a, 0).getLong(str, j);
        } catch (ClassCastException e) {
            q.d("ClientSharedPreferences", "getPreference(): " + e);
        }
        return j;
    }

    public static String a(Context context, String str, String str2) {
        try {
            str2 = context.getSharedPreferences(a, 0).getString(str, str2);
        } catch (ClassCastException e) {
            q.d("ClientSharedPreferences", "getPreference(): " + e);
        }
        return str2;
    }

    public static boolean b(Context context, String str, boolean z) {
        boolean z2 = false;
        try {
            z2 = context.getSharedPreferences(a, z2).edit().putBoolean(str, z).commit();
        } catch (Exception e) {
            q.d("ClientSharedPreferences", "setPreference(): " + e);
        }
        return z2;
    }

    public static boolean b(Context context, String str, long j) {
        boolean z = false;
        try {
            z = context.getSharedPreferences(a, z).edit().putLong(str, j).commit();
        } catch (Exception e) {
            q.d("ClientSharedPreferences", "setPreference(): " + e);
        }
        return z;
    }
}
