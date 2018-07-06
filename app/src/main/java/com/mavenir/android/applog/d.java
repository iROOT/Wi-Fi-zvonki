package com.mavenir.android.applog;

import android.content.Context;
import android.text.TextUtils;
import com.fgmicrotec.mobile.android.fgmag.DataConnectionManager.a;
import com.fgmicrotec.mobile.android.fgvoip.FgVoIP;
import com.mavenir.android.applog.AppLogAdapter.b;
import com.mavenir.android.applog.AppLogAdapter.c;
import com.mavenir.android.applog.AppLogAdapter.e;
import com.mavenir.android.common.l;
import com.mavenir.android.settings.c.k;
import com.mavenir.android.settings.c.p;

public class d {
    public static void a(Context context, String str, b bVar, com.mavenir.android.applog.AppLogAdapter.d dVar, c cVar, String str2) {
        String a = a(a(context, bVar, dVar, cVar, str2), str);
        a.a(context).a(true, a(), e.FGAPPLOG_HTTP_METHOD_POST.ordinal(), "*/*", "100-continue", "multipart/form-data;boundary=----------------------------3f338fae19be", a);
    }

    public static String a() {
        return ("https://rink.hockeyapp.net/api/2/apps/" + FgVoIP.U().ac()) + "/crashes/upload";
    }

    public static String a(String str, String str2) {
        String str3 = (((((((((((("------------------------------3f338fae19be" + "\r\n") + "Content-Disposition: form-data; name=\"log\"; filename=\"crash.log\"") + "\r\n") + "Content-Type: application/octet-stream") + "\r\n") + "\r\n") + str) + "\r\n") + "------------------------------3f338fae19be") + "\r\n") + "Content-Disposition: form-data; name=\"userID\"") + "\r\n") + "\r\n";
        if (!com.mavenir.android.settings.d.a(FgVoIP.U(), "db_encryption_problem", false)) {
            str3 = str3 + str2;
        }
        return ((((((((str3 + "\r\n") + "------------------------------3f338fae19be") + "\r\n") + "Content-Disposition: form-data; name=\"contact\"") + "\r\n") + "\r\n") + "\r\n") + "------------------------------3f338fae19be--") + "\r\n";
    }

    public static String a(Context context, b bVar, com.mavenir.android.applog.AppLogAdapter.d dVar, c cVar, String str) {
        Object obj;
        boolean a = com.mavenir.android.settings.d.a(FgVoIP.U(), "db_encryption_problem", false);
        String str2 = ((((((((("" + "Package: ") + context.getPackageName()) + "\n") + "App Version: ") + l.a(context).c()) + "\n") + "OS: ") + l.a(context).g()) + " ") + l.a(context).h();
        if (!a) {
            str2 = str2 + " (device ";
            if (!FgVoIP.U().ag()) {
                str2 = str2 + "not ";
            }
            str2 = str2 + "rooted)";
        }
        StringBuilder append = new StringBuilder().append((((((((str2 + "\n") + "Manufacturer: ") + l.a(context).i()) + "\n") + "Model: ") + l.a(context).j()) + "\n") + "Vers: ");
        if (a) {
            obj = "-3";
        } else {
            obj = Integer.valueOf(k.d());
        }
        str2 = ((((((append.append(obj).toString() + "\n") + "Date: ") + com.mavenir.android.common.t.d.a(System.currentTimeMillis(), "EEE MMM dd kk:mm:hh zzzz yyyy")) + "\n") + "\n") + "Event: ") + c.a(bVar, dVar, cVar);
        if (!str.isEmpty()) {
            str2 = ((str2 + "\n") + "Additional event info: ") + str;
        }
        a H = l.a(context).H();
        str2 = (str2 + "\n") + a(context, H);
        if (H != a.ANY) {
            str2 = ((str2 + "Bearer: ") + H.name()) + "\n";
        }
        if (a) {
            return str2;
        }
        if (bVar != b.FGAPPLOG_EVENT_GROUP_CONFIGURATION_ERROR && bVar != b.FGAPPLOG_EVENT_GROUP_PROVISIONING_ERROR && bVar != b.FGAPPLOG_EVENT_GROUP_APP_ACTIVATED && bVar != b.FGAPPLOG_EVENT_GROUP_LTE_800) {
            return str2;
        }
        return ((((((((((((str2 + "LTE-800 Features: ") + "Capability: ") + (com.mavenir.android.settings.c.l.b() ? "yes" : "no")) + ", ") + "Network Enabled: ") + (com.mavenir.android.settings.c.l.d() ? "yes" : "no")) + ", ") + "PS Feature User Activated: ") + (com.mavenir.android.settings.c.l.c() ? "yes" : "no")) + ", ") + "PS Capability Expires: ") + com.mavenir.android.common.t.d.a(com.mavenir.android.settings.c.l.e(), "EEE MMM dd kk:mm:hh zzzz yyyy")) + "\n";
    }

    public static String a(Context context, a aVar) {
        boolean a = com.mavenir.android.settings.d.a(FgVoIP.U(), "db_encryption_problem", false);
        String str = "" + "Public IP Address: ";
        if (!a) {
            str = str + l.a(context).n();
        }
        str = str + "\n";
        if (aVar == a.WLAN) {
            String Q = l.a(context).Q();
            String str2 = str + "WiFi MAC Address: ";
            str = "";
            if (!TextUtils.isEmpty(Q)) {
                str = Q.substring(0, Q.length() / 2);
            }
            str = (str2 + str) + "\n";
            str = ((str + "WiFi signal strength: ") + l.a(context).M()) + "\n";
        }
        int N = l.a(context).N();
        int O = l.a(context).O();
        if (N == 99) {
            if (O != 99) {
                N = O;
            } else {
                N = 99;
            }
        }
        str = (((str + "Cellular signal strength: ") + N) + "\n") + "TAC Digits: ";
        if (!a) {
            str = str + p.l();
        }
        str = (str + "\n") + "SVN Digits: ";
        if (!a) {
            str = str + p.m();
        }
        return str + "\n";
    }
}
