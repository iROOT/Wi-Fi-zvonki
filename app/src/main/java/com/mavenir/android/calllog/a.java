package com.mavenir.android.calllog;

import android.net.Uri;
import android.provider.CallLog.Calls;
import com.mavenir.android.common.q;

public class a {
    public static String a = "call_log";
    public static Uri b = Uri.parse("content://" + a);
    public static boolean c = false;

    public static class a extends Calls {
        public static Uri a = Uri.withAppendedPath(a.b, "calls");
        public static Uri b = Uri.withAppendedPath(a, "filter");
    }

    public static void a(String str) {
        c = true;
        a = str;
        b = Uri.parse("content://" + a);
        a.a = Uri.withAppendedPath(b, "calls");
        a.b = Uri.withAppendedPath(a.a, "filter");
        q.a("CallLog", "provider = " + str);
    }
}
