package com.mavenir.android.common;

import android.os.Build;
import java.io.File;

public class v {
    private static String a = v.class.getSimpleName();
    private static boolean b = false;

    public static boolean a() {
        long currentTimeMillis;
        long j = 0;
        if (b) {
            currentTimeMillis = System.currentTimeMillis();
        } else {
            currentTimeMillis = 0;
        }
        boolean b = b();
        if (b) {
            j = System.currentTimeMillis();
        }
        if (b) {
            q.a(a, "Method1() executed in " + (j - currentTimeMillis) + "ms - " + b);
        }
        if (!b) {
            if (b) {
                currentTimeMillis = System.currentTimeMillis();
            }
            b = c();
            if (b) {
                j = System.currentTimeMillis();
            }
            if (b) {
                q.a(a, "Method2() executed in " + (j - currentTimeMillis) + "ms - " + b);
            }
        }
        boolean z = b;
        if (!z) {
            if (b) {
                currentTimeMillis = System.currentTimeMillis();
            }
            z = d();
            long currentTimeMillis2 = System.currentTimeMillis();
            if (b) {
                q.a(a, "Method3() executed in " + (currentTimeMillis2 - currentTimeMillis) + "ms - " + z);
            }
        }
        return z;
    }

    public static boolean b() {
        String str = Build.TAGS;
        return str != null && str.contains("test-keys");
    }

    public static boolean c() {
        try {
            return new File("/system/app/Superuser.apk").exists();
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean d() {
        String[] split;
        int length;
        int i;
        String str;
        String str2 = System.getenv("PATH");
        if (b) {
            q.a(a, "path='" + str2 + "'");
        }
        if (str2 != null && str2.length() > 0) {
            split = str2.split(":");
            length = split.length;
            i = 0;
            while (i < length) {
                str = split[i];
                if (b) {
                    q.a(a, "- " + str + "/su");
                }
                if (!new File(str, "su").exists()) {
                    i++;
                } else if (!b) {
                    return true;
                } else {
                    q.a(a, "-> exists");
                    return true;
                }
            }
        }
        split = new String[]{"/sbin/su", "/system/bin/su", "/system/xbin/su", "/data/local/xbin/su", "/data/local/bin/su", "/system/sd/xbin/su", "/system/bin/failsafe/su", "/data/local/su"};
        length = split.length;
        i = 0;
        while (i < length) {
            str = split[i];
            if (b) {
                q.a(a, "- " + str);
            }
            if (!new File(str).exists()) {
                i++;
            } else if (!b) {
                return true;
            } else {
                q.a(a, "-> exists");
                return true;
            }
        }
        return false;
    }
}
