package com.mavenir.android.common;

import android.util.Log;
import com.fgmicrotec.mobile.android.fgvoip.FgVoIP;
import com.mavenir.android.applog.a;
import com.mavenir.android.settings.c.h;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

public class q {
    private static Boolean a = null;
    private static boolean b = false;
    private static boolean c = false;

    public static boolean a() {
        if (!c) {
            return false;
        }
        if (a == null) {
            a = Boolean.valueOf(h.c());
        }
        return a.booleanValue();
    }

    public static void b() {
        a = null;
    }

    public static void a(boolean z) {
        c = z;
    }

    public static int a(String str, String str2) {
        if (!a()) {
            return -1;
        }
        a(3, str, str2);
        return Log.d(str, str2);
    }

    public static int a(String str, String str2, Throwable th) {
        if (!a()) {
            return -1;
        }
        a(3, str, str2, th);
        return Log.d(str, str2, th);
    }

    public static int b(String str, String str2) {
        if (!a()) {
            return -1;
        }
        a(4, str, str2);
        return Log.i(str, str2);
    }

    public static int c(String str, String str2) {
        if (!a()) {
            return -1;
        }
        a(5, str, str2);
        return Log.w(str, str2);
    }

    public static int b(String str, String str2, Throwable th) {
        if (!a()) {
            return -1;
        }
        a(5, str, str2, th);
        return Log.w(str, str2, th);
    }

    public static int d(String str, String str2) {
        if (!a()) {
            return -1;
        }
        a(6, str, str2);
        return Log.e(str, str2);
    }

    public static int c(String str, String str2, Throwable th) {
        if (!a()) {
            return -1;
        }
        a(6, str, str2, th);
        return Log.e(str, str2, th);
    }

    private static void a(int i, String str, String str2) {
        a(i, str, str2, null);
    }

    private static void a(int i, String str, String str2, Throwable th) {
        if (a.c()) {
            String a = a(i);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(" GUI Java.Log.");
            stringBuilder.append(a);
            stringBuilder.append(": ");
            stringBuilder.append(str + ": ");
            if (str2 != null) {
                stringBuilder.append(str2);
            }
            if (th != null) {
                stringBuilder.append(th.getLocalizedMessage());
            }
            stringBuilder.append("\n");
            if (th != null) {
                Writer stringWriter = new StringWriter();
                th.printStackTrace(new PrintWriter(stringWriter));
                stringBuilder.append(stringWriter.getBuffer());
            }
            try {
                a.a(FgVoIP.U()).a(stringBuilder.toString());
            } catch (Exception e) {
                if (a()) {
                    d("LOG", "writeLogToTraceFile(): " + e);
                }
            }
        }
    }

    private static String a(int i) {
        if (i == 3) {
            return "DEBUG";
        }
        if (i == 4) {
            return "INFO";
        }
        if (i == 5) {
            return "WARN";
        }
        if (i == 2) {
            return "VERBOSE";
        }
        if (i == 7) {
            return "ASSERT";
        }
        return "ERROR";
    }
}
