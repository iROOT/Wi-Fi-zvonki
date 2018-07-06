package com.mavenir.android.applog;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.content.o;
import android.text.TextUtils;
import com.fgmicrotec.mobile.android.fgmag.VoIP;
import com.fgmicrotec.mobile.android.fgvoip.FgVoIP;
import com.fgmicrotec.mobile.android.fgvoipcommon.CallService;
import com.mavenir.android.applog.AppLogAdapter.c;
import com.mavenir.android.applog.AppLogAdapter.d;
import com.mavenir.android.applog.AppLogAdapter.e;
import com.mavenir.android.applog.AppLogAdapter.f;
import com.mavenir.android.common.l;
import com.mavenir.android.common.q;
import com.mavenir.android.common.t;
import com.mavenir.android.settings.c.k;
import com.mavenir.android.settings.c.p;
import com.mavenir.android.vtow.activation.ActivationAdapter;
import java.util.LinkedList;
import java.util.Queue;
import javax.net.ssl.HttpsURLConnection;

public class a implements b {
    private static AppLogAdapter a;
    private static Context b;
    private static a c;
    private static String d;
    private static boolean e = false;
    private static boolean f = false;
    private static boolean g = false;
    private static Queue<a> h;

    private class a {
        public com.mavenir.android.applog.AppLogAdapter.b a;
        public d b;
        public c c;
        public String d;
        public com.fgmicrotec.mobile.android.fgmag.DataConnectionManager.a e;
        final /* synthetic */ a f;

        private a(a aVar) {
            this.f = aVar;
        }

        /* synthetic */ a(a aVar, AnonymousClass1 anonymousClass1) {
            this(aVar);
        }
    }

    public class b extends AsyncTask<Void, Void, Integer> {
        final /* synthetic */ a a;
        private boolean b;
        private String c;
        private e d;
        private String e;
        private String f;
        private String g;
        private String h;

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return a((Void[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            a((Integer) obj);
        }

        public b(a aVar, boolean z, String str, e eVar, String str2, String str3, String str4, String str5) {
            this.a = aVar;
            this.b = z;
            this.c = str;
            this.d = eVar;
            this.e = str2;
            this.f = str3;
            this.g = str4;
            this.h = str5;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        protected java.lang.Integer a(java.lang.Void... r10) {
            /*
            r9 = this;
            r4 = 0;
            r3 = -1;
            r1 = "";
            r0 = new java.net.URL;	 Catch:{ Exception -> 0x00cd, all -> 0x0111 }
            r2 = r9.c;	 Catch:{ Exception -> 0x00cd, all -> 0x0111 }
            r0.<init>(r2);	 Catch:{ Exception -> 0x00cd, all -> 0x0111 }
            r0 = r0.openConnection();	 Catch:{ Exception -> 0x00cd, all -> 0x0111 }
            r0 = (javax.net.ssl.HttpsURLConnection) r0;	 Catch:{ Exception -> 0x00cd, all -> 0x0111 }
            r2 = r9.d;	 Catch:{ Exception -> 0x0147, all -> 0x0138 }
            r5 = com.mavenir.android.applog.AppLogAdapter.e.FGAPPLOG_HTTP_METHOD_POST;	 Catch:{ Exception -> 0x0147, all -> 0x0138 }
            if (r2 != r5) goto L_0x0020;
        L_0x0017:
            r2 = "POST";
            r0.setRequestMethod(r2);	 Catch:{ Exception -> 0x0147, all -> 0x0138 }
            r2 = 1;
            r0.setDoOutput(r2);	 Catch:{ Exception -> 0x0147, all -> 0x0138 }
        L_0x0020:
            r2 = r9.e;	 Catch:{ Exception -> 0x0147, all -> 0x0138 }
            r2 = android.text.TextUtils.isEmpty(r2);	 Catch:{ Exception -> 0x0147, all -> 0x0138 }
            if (r2 != 0) goto L_0x002f;
        L_0x0028:
            r2 = "Accept";
            r5 = r9.e;	 Catch:{ Exception -> 0x0147, all -> 0x0138 }
            r0.setRequestProperty(r2, r5);	 Catch:{ Exception -> 0x0147, all -> 0x0138 }
        L_0x002f:
            r2 = r9.f;	 Catch:{ Exception -> 0x0147, all -> 0x0138 }
            r2 = android.text.TextUtils.isEmpty(r2);	 Catch:{ Exception -> 0x0147, all -> 0x0138 }
            if (r2 != 0) goto L_0x003e;
        L_0x0037:
            r2 = "Expect";
            r5 = r9.f;	 Catch:{ Exception -> 0x0147, all -> 0x0138 }
            r0.setRequestProperty(r2, r5);	 Catch:{ Exception -> 0x0147, all -> 0x0138 }
        L_0x003e:
            r2 = r9.g;	 Catch:{ Exception -> 0x0147, all -> 0x0138 }
            r2 = android.text.TextUtils.isEmpty(r2);	 Catch:{ Exception -> 0x0147, all -> 0x0138 }
            if (r2 != 0) goto L_0x004d;
        L_0x0046:
            r2 = "Content-Type";
            r5 = r9.g;	 Catch:{ Exception -> 0x0147, all -> 0x0138 }
            r0.setRequestProperty(r2, r5);	 Catch:{ Exception -> 0x0147, all -> 0x0138 }
        L_0x004d:
            r2 = r9.h;	 Catch:{ Exception -> 0x0147, all -> 0x0138 }
            r2 = r2.getBytes();	 Catch:{ Exception -> 0x0147, all -> 0x0138 }
            r2 = r2.length;	 Catch:{ Exception -> 0x0147, all -> 0x0138 }
            r5 = "Content-Length";
            r6 = java.lang.String.valueOf(r2);	 Catch:{ Exception -> 0x0147, all -> 0x0138 }
            r0.setRequestProperty(r5, r6);	 Catch:{ Exception -> 0x0147, all -> 0x0138 }
            r0.setFixedLengthStreamingMode(r2);	 Catch:{ Exception -> 0x0147, all -> 0x0138 }
            r5 = new java.io.BufferedOutputStream;	 Catch:{ Exception -> 0x0147, all -> 0x0138 }
            r2 = r0.getOutputStream();	 Catch:{ Exception -> 0x0147, all -> 0x0138 }
            r5.<init>(r2);	 Catch:{ Exception -> 0x0147, all -> 0x0138 }
            r2 = r9.h;	 Catch:{ Exception -> 0x014d, all -> 0x013d }
            r2 = r2.getBytes();	 Catch:{ Exception -> 0x014d, all -> 0x013d }
            r5.write(r2);	 Catch:{ Exception -> 0x014d, all -> 0x013d }
            r5.flush();	 Catch:{ Exception -> 0x014d, all -> 0x013d }
            r9.a(r0);	 Catch:{ Exception -> 0x014d, all -> 0x013d }
            r2 = r0.getResponseCode();	 Catch:{ Exception -> 0x014d, all -> 0x013d }
            r1 = r0.getResponseMessage();	 Catch:{ Exception -> 0x0152, all -> 0x013d }
            if (r5 == 0) goto L_0x0085;
        L_0x0082:
            r5.close();	 Catch:{ IOException -> 0x00b3 }
        L_0x0085:
            if (r0 == 0) goto L_0x015e;
        L_0x0087:
            r0.disconnect();
            r0 = r1;
            r1 = r2;
        L_0x008c:
            r2 = "AppLogHandler";
            r3 = new java.lang.StringBuilder;
            r3.<init>();
            r4 = "HttpRequest: doInBackground: HTTP response code: ";
            r3 = r3.append(r4);
            r3 = r3.append(r1);
            r4 = ", HTTP response message: ";
            r3 = r3.append(r4);
            r0 = r3.append(r0);
            r0 = r0.toString();
            com.mavenir.android.common.q.a(r2, r0);
            r0 = java.lang.Integer.valueOf(r1);
            return r0;
        L_0x00b3:
            r3 = move-exception;
            r4 = "AppLogHandler";
            r5 = new java.lang.StringBuilder;
            r5.<init>();
            r6 = "sendHttpRequestReq(): cannot close output stream ";
            r5 = r5.append(r6);
            r3 = r5.append(r3);
            r3 = r3.toString();
            com.mavenir.android.common.q.d(r4, r3);
            goto L_0x0085;
        L_0x00cd:
            r0 = move-exception;
            r2 = r0;
            r0 = r3;
            r3 = r4;
        L_0x00d1:
            r5 = "AppLogHandler";
            r6 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0143 }
            r6.<init>();	 Catch:{ all -> 0x0143 }
            r7 = "sendHttpRequestReq(): ";
            r6 = r6.append(r7);	 Catch:{ all -> 0x0143 }
            r2 = r6.append(r2);	 Catch:{ all -> 0x0143 }
            r2 = r2.toString();	 Catch:{ all -> 0x0143 }
            com.mavenir.android.common.q.d(r5, r2);	 Catch:{ all -> 0x0143 }
            if (r3 == 0) goto L_0x00ee;
        L_0x00eb:
            r3.close();	 Catch:{ IOException -> 0x00f7 }
        L_0x00ee:
            if (r4 == 0) goto L_0x0159;
        L_0x00f0:
            r4.disconnect();
            r8 = r1;
            r1 = r0;
            r0 = r8;
            goto L_0x008c;
        L_0x00f7:
            r2 = move-exception;
            r3 = "AppLogHandler";
            r5 = new java.lang.StringBuilder;
            r5.<init>();
            r6 = "sendHttpRequestReq(): cannot close output stream ";
            r5 = r5.append(r6);
            r2 = r5.append(r2);
            r2 = r2.toString();
            com.mavenir.android.common.q.d(r3, r2);
            goto L_0x00ee;
        L_0x0111:
            r0 = move-exception;
            r1 = r4;
        L_0x0113:
            if (r4 == 0) goto L_0x0118;
        L_0x0115:
            r4.close();	 Catch:{ IOException -> 0x011e }
        L_0x0118:
            if (r1 == 0) goto L_0x011d;
        L_0x011a:
            r1.disconnect();
        L_0x011d:
            throw r0;
        L_0x011e:
            r2 = move-exception;
            r3 = "AppLogHandler";
            r4 = new java.lang.StringBuilder;
            r4.<init>();
            r5 = "sendHttpRequestReq(): cannot close output stream ";
            r4 = r4.append(r5);
            r2 = r4.append(r2);
            r2 = r2.toString();
            com.mavenir.android.common.q.d(r3, r2);
            goto L_0x0118;
        L_0x0138:
            r1 = move-exception;
            r8 = r1;
            r1 = r0;
            r0 = r8;
            goto L_0x0113;
        L_0x013d:
            r1 = move-exception;
            r4 = r5;
            r8 = r1;
            r1 = r0;
            r0 = r8;
            goto L_0x0113;
        L_0x0143:
            r0 = move-exception;
            r1 = r4;
            r4 = r3;
            goto L_0x0113;
        L_0x0147:
            r2 = move-exception;
            r8 = r3;
            r3 = r4;
            r4 = r0;
            r0 = r8;
            goto L_0x00d1;
        L_0x014d:
            r2 = move-exception;
            r4 = r0;
            r0 = r3;
            r3 = r5;
            goto L_0x00d1;
        L_0x0152:
            r3 = move-exception;
            r4 = r0;
            r0 = r2;
            r2 = r3;
            r3 = r5;
            goto L_0x00d1;
        L_0x0159:
            r8 = r1;
            r1 = r0;
            r0 = r8;
            goto L_0x008c;
        L_0x015e:
            r0 = r1;
            r1 = r2;
            goto L_0x008c;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.mavenir.android.applog.a.b.a(java.lang.Void[]):java.lang.Integer");
        }

        protected void a(Integer num) {
            f fVar;
            super.onPostExecute(num);
            if (num.intValue() == ActivationAdapter.OP_CONFIGURATION_INITIAL || num.intValue() == ActivationAdapter.OP_CONFIGURATION_APP_UPDATE) {
                fVar = f.FGAPPLOG_HTTP_STATUS_OK;
            } else if (num.intValue() == VoIP.REASON_CODE_BAD_REQUEST) {
                fVar = f.FGAPPLOG_HTTP_STATUS_INVALID_REQUEST;
            } else if (num.intValue() == VoIP.REASON_CODE_REQUEST_TIMEOUT || num.intValue() == VoIP.REASON_CODE_SERVER_TIMEOUT) {
                fVar = f.FGAPPLOG_HTTP_STATUS_TIMEOUT;
            } else {
                fVar = f.FGAPPLOG_HTTP_STATUS_GENERAL_ERROR;
            }
            this.a.a(this.b, num.intValue(), fVar);
        }

        private void a(HttpsURLConnection httpsURLConnection) {
            if (this.b) {
                q.a("AppLogHandler", "sendHttpRequestReq(): Method: " + httpsURLConnection.getRequestMethod() + "\nAccept: " + httpsURLConnection.getRequestProperty("Accept") + "\nExpect: " + httpsURLConnection.getRequestProperty("Expect") + "\nContent-Type: " + httpsURLConnection.getRequestProperty("Content-Type") + "\nContent-Length: " + httpsURLConnection.getRequestProperty("Content-Length") + "\nContent-Body: " + this.h);
            }
        }
    }

    private a(Context context) {
        b = context;
        a = new AppLogAdapter(this);
        h = new LinkedList();
        e = false;
    }

    public static a a(Context context) {
        if (c == null) {
            c = new a(context);
        }
        return c;
    }

    public void a() {
        if (!e) {
            q.a("AppLogHandler", "Initializing app logging adapter...");
            a.init();
            e = true;
        }
    }

    public void b() {
        q.a("AppLogHandler", "destroy() - Destroying app logging adapter...");
        a.exit();
    }

    public static boolean c() {
        return e;
    }

    public static boolean d() {
        return g;
    }

    public void e() {
        String k = l.a(b).k();
        String c = l.a(b).c();
        String g = l.a(b).g();
        String h = l.a(b).h();
        String i = l.a(b).i();
        String j = l.a(b).j();
        int v = l.a(b).v();
        String j2 = j();
        if (FgVoIP.U().I()) {
            j2 = p.g().split("@")[0];
        }
        String e = k.e();
        String l = p.l();
        String m = p.m();
        String valueOf = String.valueOf(k.d());
        q.a("AppLogHandler", "appLogConfigureReq(): , appVer: " + c + ", OS: " + g + ", OSver: " + h + ", manufacturer: " + i + ", model:" + j + ", msisdn: " + j2 + ", imsi: " + e + ", IMEI: " + k + ", TAC: " + l + ", TAC-LTE: " + v + ", SVN: " + m + ", CMS-CC vers: " + valueOf);
        a.appLogConfigureReq(j2, e, c, g, h, i, j, k, l, m, valueOf);
    }

    public void a(int i) {
        q.a("AppLogHandler", "appLogConfigureCnf(): errCode: " + i);
        f = true;
        o();
    }

    private void o() {
        try {
            new Thread(new Runnable(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void run() {
                    while (!a.h.isEmpty()) {
                        a aVar = (a) a.h.poll();
                        this.a.a(aVar.a, aVar.b, aVar.c, aVar.d, aVar.e);
                    }
                }
            }).start();
        } catch (Exception e) {
            q.d("AppLogHandler", "sendQueuedEvents(): " + e);
        }
    }

    public void f() {
        int L = l.a(b).L();
        q.a("AppLogHandler", "timeZoneInfoSetupReq(): GMT offset: " + L + " min");
        a.timeZoneInfoSetupReq(L);
    }

    public void b(int i) {
        q.a("AppLogHandler", "timeZoneInfoSetupCnf(): errCode: " + i);
    }

    public void g() {
        boolean C = l.a(b).C();
        String Q = l.a(b).Q();
        String P = l.a(b).P();
        q.a("AppLogHandler", "wifiAccessInfoSetupReq(): connected: " + C + ", MAC: " + Q + ", SSID: " + P);
        a.wifiAccessInfoSetupReq(C, Q, P);
    }

    public void c(int i) {
        q.a("AppLogHandler", "wifiAccessInfoSetupCnf(): errCode: " + i);
    }

    public void d(int i) {
        q.a("AppLogHandler", "ipConnectionInfoSetupCnf(): errCode: " + i);
    }

    public void h() {
        q.a("AppLogHandler", "loggingServerInfoSetupReq()");
        a.loggingServerInfoSetupReq(FgVoIP.U().ac(), FgVoIP.U().getPackageName());
    }

    public void e(int i) {
        q.a("AppLogHandler", "loggingServerInfoSetupCnf(): errCode: " + i);
    }

    public void a(com.mavenir.android.applog.AppLogAdapter.b bVar, d dVar, c cVar, String str) {
        a(bVar, dVar, cVar, str, l.a(b).H());
    }

    public void a(com.mavenir.android.applog.AppLogAdapter.b bVar, d dVar, c cVar, String str, com.fgmicrotec.mobile.android.fgmag.DataConnectionManager.a aVar) {
        if (f) {
            if (bVar != com.mavenir.android.applog.AppLogAdapter.b.FGAPPLOG_EVENT_GROUP_NATIVE_CRASH) {
                q.a("AppLogHandler", "eventLoggingReq(): group: " + bVar.toString() + ", type: " + dVar.toString() + ", reason: " + cVar.toString() + ", other info: " + str + ", bearer: " + aVar.name());
            }
            a.eventLoggingReq(bVar.ordinal(), dVar.ordinal(), cVar.ordinal(), str, aVar.ordinal());
            g = true;
            return;
        }
        q.a("AppLogHandler", "eventLoggingReq(): AppLog not configured, queing event...");
        a aVar2 = new a();
        aVar2.a = bVar;
        aVar2.b = dVar;
        aVar2.c = cVar;
        aVar2.d = str;
        aVar2.e = aVar;
        h.add(aVar2);
    }

    public void f(int i) {
        g = false;
        q.a("AppLogHandler", "eventLoggingCnf(): errCode: " + i);
    }

    public void a(boolean z) {
        q.a("AppLogHandler", "additionalInfoNeededInd(): fillIPInfo: " + z);
        b(z);
    }

    public void b(final boolean z) {
        q.a("AppLogHandler", "additionalInfoNeededRes(): fillIPInfo: " + z);
        new Thread(new Runnable(this) {
            final /* synthetic */ a b;

            public void run() {
                int ordinal = com.mavenir.android.applog.AppLogAdapter.a.FGAPPLOG_OK.ordinal();
                int M = l.a(a.b).M();
                int N = l.a(a.b).N();
                int O = l.a(a.b).O();
                if (N == 99) {
                    if (O != 99) {
                        N = O;
                    } else {
                        N = 99;
                    }
                }
                String j = a.j();
                if (FgVoIP.U().I()) {
                    j = p.g().split("@")[0];
                }
                boolean ag = FgVoIP.U().ag();
                String Q = l.a(a.b).Q();
                int t = l.a(a.b).t();
                int v = l.a(a.b).v();
                int w = l.a(a.b).w();
                int x = l.a(a.b).x();
                boolean b = com.mavenir.android.settings.c.l.b();
                boolean d = com.mavenir.android.settings.c.l.d();
                boolean c = com.mavenir.android.settings.c.l.c();
                String a = t.d.a(com.mavenir.android.settings.c.l.e(), "EEE MMM dd kk:mm:hh zzzz yyyy");
                String str = "";
                String str2 = "";
                if (z) {
                    q.a("AppLogHandler", "additionalInfoNeededRes(): start IP search");
                    str = l.a(a.b).m();
                    str2 = l.a(a.b).n();
                    q.a("AppLogHandler", "additionalInfoNeededRes(): end IP search: localIP: " + str + ", publicIP: " + str2 + ", bssid: " + Q);
                }
                a.a.additionalInfoNeededRes(ordinal, M, N, j, str, str2, ag, Q, t, v, w, x, O, b, d, c, a);
            }
        }).start();
    }

    public void g(int i) {
        q.a("AppLogHandler", "setEventLoggingPeriodCnf(): errCode: " + i);
    }

    public void i() {
        q.a("AppLogHandler", "getEventListReq()");
        a.getEventListReq();
    }

    public void a(int i, String[] strArr) {
        q.a("AppLogHandler", "getEventListCnf(): size: " + i);
        Intent intent = new Intent();
        intent.setAction(AppLogAdapter.ACTION_EVENT_LIST_CNF);
        intent.putExtra(AppLogAdapter.EXTRA_EVENT_LIST_SIZE, i);
        intent.putExtra(AppLogAdapter.EXTRA_EVENT_LIST, strArr);
        o.a(b).a(intent);
    }

    public void c(boolean z) {
        q.a("AppLogHandler", "updateRoamingStatusReq(): Roaming enabled: " + z);
        a.updateRoamingStatusReq(z);
    }

    public static String j() {
        String i = p.i();
        if (TextUtils.isEmpty(i)) {
            return p.k();
        }
        return i;
    }

    public void a(String str) {
        if (e) {
            a.writeJavaLog(str);
        }
    }

    public void k() {
        Boolean w = k.w();
        boolean ag = FgVoIP.U().ag();
        if (w == null || w.booleanValue() != ag) {
            a.reportDeviceRootedReq(ag);
        }
    }

    public void b(com.mavenir.android.applog.AppLogAdapter.b bVar, d dVar, c cVar, String str) {
        if (TextUtils.isEmpty(d)) {
            d = p.i();
        }
        d.a(b, d, bVar, dVar, cVar, str);
    }

    public void a(boolean z, String str, int i, String str2, String str3, String str4, String str5) {
        q.a("AppLogHandler", "sendHttpRequestReq(): method: " + e.values()[i].name());
        new b(this, z, str, e.values()[i], str2, str3, str4, str5).execute(new Void[0]);
    }

    public void a(String str, int i, String str2, String str3, String str4, String str5) {
        a(false, str, i, str2, str3, str4, str5);
    }

    public void a(boolean z, int i, f fVar) {
        q.a("AppLogHandler", "sendHttpRequestCnf(): HTTP responseCode: " + i + ", HTTP status: " + fVar);
        if (!CallService.l() && !z) {
            a.sendHttpRequestCnf(i, fVar.ordinal());
        }
    }
}
