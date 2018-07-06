package com.mavenir.android.messaging.c;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import com.mavenir.android.common.q;
import com.mavenir.android.messaging.provider.g;
import com.mavenir.android.messaging.provider.g.f;

public class d {
    public static final String[] a = new String[]{"transport_type", "_id", "thread_id", "address", "body", "date", "date_sent", "read", "type", "status", "locked", "error_code", "sub", "sub_cs", "date", "date_sent", "read", "m_type", "msg_box", "d_rpt", "rr", "err_type", "locked", "st"};
    private String A;
    private a B;
    private Context b;
    private Uri c;
    private String d;
    private long e;
    private long f;
    private int g;
    private String h;
    private String i;
    private long j;
    private long k;
    private boolean l;
    private int m;
    private int n;
    private boolean o;
    private int p;
    private String q;
    private int r;
    private int s;
    private int t;
    private int u;
    private boolean v;
    private boolean w = false;
    private int x = -1;
    private String y;
    private String z;

    public static class a {
        public int a;
        public int b;
        public int c;
        public int d;
        public int e;
        public int f;
        public int g;
        public int h;
        public int i;
        public int j;
        public int k;
        public int l;
        public int m;
        public int n;
        public int o;
        public int p;
        public int q;
        public int r;
        public int s;
        public int t;
        public int u;
        public int v;
        public int w;
        public int x;
        public int y;

        public a() {
            this.a = 0;
            this.b = 1;
            this.c = 2;
            this.d = 3;
            this.e = 4;
            this.f = 5;
            this.g = 6;
            this.h = 7;
            this.i = 8;
            this.j = 9;
            this.k = 10;
            this.l = 11;
            this.m = 12;
            this.n = 13;
            this.o = 14;
            this.p = 15;
            this.q = 16;
            this.r = 17;
            this.s = 18;
            this.t = 19;
            this.u = 20;
            this.v = 21;
            this.w = 22;
            this.x = 23;
            this.y = 24;
        }

        public a(Cursor cursor) {
            try {
                this.a = cursor.getColumnIndexOrThrow("transport_type");
            } catch (IllegalArgumentException e) {
            }
            try {
                this.b = cursor.getColumnIndexOrThrow("_id");
            } catch (IllegalArgumentException e2) {
            }
            try {
                this.c = cursor.getColumnIndexOrThrow("thread_id");
            } catch (IllegalArgumentException e3) {
            }
            try {
                this.d = cursor.getColumnIndexOrThrow("address");
            } catch (IllegalArgumentException e4) {
            }
            try {
                this.e = cursor.getColumnIndexOrThrow("body");
            } catch (IllegalArgumentException e5) {
            }
            try {
                this.f = cursor.getColumnIndexOrThrow("date");
            } catch (IllegalArgumentException e6) {
            }
            try {
                this.g = cursor.getColumnIndexOrThrow("date_sent");
            } catch (IllegalArgumentException e7) {
            }
            try {
                this.h = cursor.getColumnIndexOrThrow("read");
            } catch (IllegalArgumentException e8) {
            }
            try {
                this.i = cursor.getColumnIndexOrThrow("type");
            } catch (IllegalArgumentException e9) {
            }
            try {
                this.j = cursor.getColumnIndexOrThrow("status");
            } catch (IllegalArgumentException e10) {
            }
            try {
                this.k = cursor.getColumnIndexOrThrow("locked");
            } catch (IllegalArgumentException e11) {
            }
            try {
                this.l = cursor.getColumnIndexOrThrow("error_code");
            } catch (IllegalArgumentException e12) {
            }
            try {
                this.m = cursor.getColumnIndexOrThrow("sub");
            } catch (IllegalArgumentException e13) {
            }
            try {
                this.n = cursor.getColumnIndexOrThrow("sub_cs");
            } catch (IllegalArgumentException e14) {
            }
            try {
                this.o = cursor.getColumnIndexOrThrow("date");
            } catch (IllegalArgumentException e15) {
            }
            try {
                this.p = cursor.getColumnIndexOrThrow("date_sent");
            } catch (IllegalArgumentException e16) {
            }
            try {
                this.q = cursor.getColumnIndexOrThrow("read");
            } catch (IllegalArgumentException e17) {
            }
            try {
                this.r = cursor.getColumnIndexOrThrow("m_type");
            } catch (IllegalArgumentException e18) {
            }
            try {
                this.s = cursor.getColumnIndexOrThrow("msg_box");
            } catch (IllegalArgumentException e19) {
            }
            try {
                this.t = cursor.getColumnIndexOrThrow("d_rpt");
            } catch (IllegalArgumentException e20) {
            }
            try {
                this.u = cursor.getColumnIndexOrThrow("rr");
            } catch (IllegalArgumentException e21) {
            }
            try {
                this.v = cursor.getColumnIndexOrThrow("err_type");
            } catch (IllegalArgumentException e22) {
            }
            try {
                this.w = cursor.getColumnIndexOrThrow("locked");
            } catch (IllegalArgumentException e23) {
            }
            try {
                this.x = cursor.getColumnIndexOrThrow("st");
            } catch (IllegalArgumentException e24) {
            }
        }
    }

    public d(Context context) {
        this.b = context;
        this.B = new a();
    }

    public d(Context context, Cursor cursor) {
        this.b = context;
        this.B = new a();
        a(context, this, cursor, null);
    }

    public d(Context context, Cursor cursor, String str) {
        this.b = context;
        this.B = new a(cursor);
        a(context, this, cursor, str);
    }

    public void a(Context context, d dVar, Cursor cursor, String str) {
        boolean z = true;
        synchronized (dVar) {
            if (str == null) {
                dVar.d = cursor.getString(this.B.a);
            } else {
                dVar.d = str;
            }
            if (dVar.b().equals("sms")) {
                dVar.v = false;
                dVar.e = cursor.getLong(this.B.b);
                dVar.f = cursor.getLong(this.B.c);
                dVar.h = cursor.getString(this.B.d);
                dVar.i = cursor.getString(this.B.e);
                dVar.j = cursor.getLong(this.B.f);
                dVar.k = cursor.getLong(this.B.g);
                dVar.l = cursor.getInt(this.B.h) != 0;
                dVar.m = cursor.getInt(this.B.i);
                dVar.n = cursor.getInt(this.B.j);
                if (cursor.getInt(this.B.k) == 0) {
                    z = false;
                }
                dVar.o = z;
                dVar.c = ContentUris.withAppendedId(f.a, this.e);
            } else if (dVar.b().equals("mms")) {
                dVar.e = cursor.getLong(this.B.b);
                dVar.g = cursor.getInt(this.B.s);
                dVar.r = cursor.getInt(this.B.r);
                dVar.t = cursor.getInt(this.B.v);
                dVar.q = cursor.getString(this.B.m);
                dVar.j = cursor.getLong(this.B.o) * 1000;
                dVar.k = cursor.getLong(this.B.p);
                dVar.l = cursor.getInt(this.B.q) != 0;
                if (cursor.getInt(this.B.w) == 0) {
                    z = false;
                }
                dVar.o = z;
                dVar.v = false;
                dVar.h = f(dVar.e);
                dVar.A = b(dVar.e);
                dVar.i = a(dVar.e);
                dVar.s = 0;
                dVar.u = cursor.getInt(this.B.x);
                dVar.c = ContentUris.withAppendedId(com.mavenir.android.messaging.provider.g.d.a, this.e);
            } else {
                q.d("Message", "Unknown type of message: " + dVar.b());
            }
        }
    }

    public String a(long j) {
        Cursor cursor;
        Throwable th;
        Cursor cursor2 = null;
        Cursor query;
        try {
            String string;
            query = this.b.getContentResolver().query(Uri.parse("content://mms/part"), null, "mid=" + j, null, null);
            if (query != null) {
                try {
                    if (query.moveToFirst()) {
                        string = query.getString(query.getColumnIndex("mid"));
                        if ("text/plain".equals(query.getString(query.getColumnIndex("ct")))) {
                            if (query.getString(query.getColumnIndex("_data")) != null) {
                                string = e(string);
                            } else {
                                string = query.getString(query.getColumnIndex("text"));
                            }
                            if (query == null && !query.isClosed()) {
                                query.close();
                                return string;
                            }
                        }
                    }
                } catch (Exception e) {
                    cursor = query;
                    if (cursor != null) {
                    }
                    return cursor2;
                } catch (Throwable th2) {
                    th = th2;
                    query.close();
                    throw th;
                }
            }
            Object obj = cursor2;
            return query == null ? string : string;
        } catch (Exception e2) {
            cursor = cursor2;
            if (cursor != null || cursor.isClosed()) {
                return cursor2;
            }
            cursor.close();
            return cursor2;
        } catch (Throwable th3) {
            th = th3;
            query = cursor2;
            if (!(query == null || query.isClosed())) {
                query.close();
            }
            throw th;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String e(java.lang.String r6) {
        /*
        r5 = this;
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = "content://mms/part/";
        r0 = r0.append(r1);
        r0 = r0.append(r6);
        r0 = r0.toString();
        r1 = android.net.Uri.parse(r0);
        r0 = 0;
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = r5.b;	 Catch:{ IOException -> 0x004d, all -> 0x0056 }
        r3 = r3.getContentResolver();	 Catch:{ IOException -> 0x004d, all -> 0x0056 }
        r0 = r3.openInputStream(r1);	 Catch:{ IOException -> 0x004d, all -> 0x0056 }
        if (r0 == 0) goto L_0x0043;
    L_0x0029:
        r1 = new java.io.InputStreamReader;	 Catch:{ IOException -> 0x004d, all -> 0x0064 }
        r3 = "UTF-8";
        r1.<init>(r0, r3);	 Catch:{ IOException -> 0x004d, all -> 0x0064 }
        r3 = new java.io.BufferedReader;	 Catch:{ IOException -> 0x004d, all -> 0x0064 }
        r3.<init>(r1);	 Catch:{ IOException -> 0x004d, all -> 0x0064 }
        r1 = r3.readLine();	 Catch:{ IOException -> 0x004d, all -> 0x0064 }
    L_0x0039:
        if (r1 == 0) goto L_0x0043;
    L_0x003b:
        r2.append(r1);	 Catch:{ IOException -> 0x004d, all -> 0x0064 }
        r1 = r3.readLine();	 Catch:{ IOException -> 0x004d, all -> 0x0064 }
        goto L_0x0039;
    L_0x0043:
        if (r0 == 0) goto L_0x0048;
    L_0x0045:
        r0.close();	 Catch:{ IOException -> 0x0060 }
    L_0x0048:
        r0 = r2.toString();
        return r0;
    L_0x004d:
        r1 = move-exception;
        if (r0 == 0) goto L_0x0048;
    L_0x0050:
        r0.close();	 Catch:{ IOException -> 0x0054 }
        goto L_0x0048;
    L_0x0054:
        r0 = move-exception;
        goto L_0x0048;
    L_0x0056:
        r1 = move-exception;
        r4 = r1;
        r1 = r0;
        r0 = r4;
    L_0x005a:
        if (r1 == 0) goto L_0x005f;
    L_0x005c:
        r1.close();	 Catch:{ IOException -> 0x0062 }
    L_0x005f:
        throw r0;
    L_0x0060:
        r0 = move-exception;
        goto L_0x0048;
    L_0x0062:
        r1 = move-exception;
        goto L_0x005f;
    L_0x0064:
        r1 = move-exception;
        r4 = r1;
        r1 = r0;
        r0 = r4;
        goto L_0x005a;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mavenir.android.messaging.c.d.e(java.lang.String):java.lang.String");
    }

    public String b(long j) {
        Cursor cursor;
        Throwable th;
        Cursor query;
        try {
            String string;
            query = this.b.getContentResolver().query(Uri.parse("content://mms/part"), null, "mid=" + j, null, null);
            if (query != null) {
                try {
                    if (query.moveToFirst()) {
                        string = query.getString(query.getColumnIndex("fn"));
                        if (query == null && !query.isClosed()) {
                            query.close();
                            return string;
                        }
                    }
                } catch (Exception e) {
                    cursor = query;
                    if (cursor != null) {
                    }
                    return null;
                } catch (Throwable th2) {
                    th = th2;
                    query.close();
                    throw th;
                }
            }
            string = null;
            return query == null ? string : string;
        } catch (Exception e2) {
            cursor = null;
            if (cursor != null || cursor.isClosed()) {
                return null;
            }
            cursor.close();
            return null;
        } catch (Throwable th3) {
            th = th3;
            query = null;
            if (!(query == null || query.isClosed())) {
                query.close();
            }
            throw th;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String f(long r12) {
        /*
        r11 = this;
        r8 = 1;
        r9 = 0;
        r7 = 0;
        r3 = new java.lang.String;
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = "msg_id=";
        r0 = r0.append(r1);
        r0 = r0.append(r12);
        r0 = r0.toString();
        r3.<init>(r0);
        r0 = "content://mms/{0}/addr";
        r1 = new java.lang.Object[r8];
        r2 = java.lang.Long.valueOf(r12);
        r1[r9] = r2;
        r0 = java.text.MessageFormat.format(r0, r1);
        r1 = android.net.Uri.parse(r0);
        r6 = "";
        r0 = r11.b;	 Catch:{ Exception -> 0x00a6, all -> 0x00b5 }
        r0 = r0.getContentResolver();	 Catch:{ Exception -> 0x00a6, all -> 0x00b5 }
        r2 = 0;
        r4 = 0;
        r5 = 0;
        r1 = r0.query(r1, r2, r3, r4, r5);	 Catch:{ Exception -> 0x00a6, all -> 0x00b5 }
        if (r1 == 0) goto L_0x009f;
    L_0x003e:
        r0 = r8;
    L_0x003f:
        r2 = r1.moveToFirst();	 Catch:{ Exception -> 0x00c5, all -> 0x00c3 }
        r0 = r0 & r2;
        if (r0 == 0) goto L_0x00ca;
    L_0x0046:
        r0 = r6;
    L_0x0047:
        r2 = "address";
        r2 = r1.getColumnIndex(r2);	 Catch:{ Exception -> 0x00c8, all -> 0x00c3 }
        r2 = r1.getString(r2);	 Catch:{ Exception -> 0x00c8, all -> 0x00c3 }
        if (r2 == 0) goto L_0x0082;
    L_0x0053:
        r3 = "-";
        r4 = "";
        r3 = r2.replace(r3, r4);	 Catch:{ NumberFormatException -> 0x00a1 }
        java.lang.Long.parseLong(r3);	 Catch:{ NumberFormatException -> 0x00a1 }
        r3 = new java.lang.StringBuilder;	 Catch:{ NumberFormatException -> 0x00a1 }
        r3.<init>();	 Catch:{ NumberFormatException -> 0x00a1 }
        r3 = r3.append(r0);	 Catch:{ NumberFormatException -> 0x00a1 }
        r3 = r3.append(r2);	 Catch:{ NumberFormatException -> 0x00a1 }
        r0 = r3.toString();	 Catch:{ NumberFormatException -> 0x00a1 }
        r3 = new java.lang.StringBuilder;	 Catch:{ NumberFormatException -> 0x00a1 }
        r3.<init>();	 Catch:{ NumberFormatException -> 0x00a1 }
        r3 = r3.append(r0);	 Catch:{ NumberFormatException -> 0x00a1 }
        r4 = ";";
        r3 = r3.append(r4);	 Catch:{ NumberFormatException -> 0x00a1 }
        r0 = r3.toString();	 Catch:{ NumberFormatException -> 0x00a1 }
    L_0x0082:
        r2 = r1.moveToNext();	 Catch:{ Exception -> 0x00c8, all -> 0x00c3 }
        if (r2 != 0) goto L_0x0047;
    L_0x0088:
        r2 = 0;
        r3 = ";";
        r3 = r0.lastIndexOf(r3);	 Catch:{ Exception -> 0x00c8, all -> 0x00c3 }
        r0 = r0.substring(r2, r3);	 Catch:{ Exception -> 0x00c8, all -> 0x00c3 }
    L_0x0093:
        if (r1 == 0) goto L_0x009e;
    L_0x0095:
        r2 = r1.isClosed();
        if (r2 != 0) goto L_0x009e;
    L_0x009b:
        r1.close();
    L_0x009e:
        return r0;
    L_0x009f:
        r0 = r9;
        goto L_0x003f;
    L_0x00a1:
        r3 = move-exception;
        if (r0 != 0) goto L_0x0082;
    L_0x00a4:
        r0 = r2;
        goto L_0x0082;
    L_0x00a6:
        r0 = move-exception;
        r0 = r6;
        r1 = r7;
    L_0x00a9:
        if (r1 == 0) goto L_0x009e;
    L_0x00ab:
        r2 = r1.isClosed();
        if (r2 != 0) goto L_0x009e;
    L_0x00b1:
        r1.close();
        goto L_0x009e;
    L_0x00b5:
        r0 = move-exception;
        r1 = r7;
    L_0x00b7:
        if (r1 == 0) goto L_0x00c2;
    L_0x00b9:
        r2 = r1.isClosed();
        if (r2 != 0) goto L_0x00c2;
    L_0x00bf:
        r1.close();
    L_0x00c2:
        throw r0;
    L_0x00c3:
        r0 = move-exception;
        goto L_0x00b7;
    L_0x00c5:
        r0 = move-exception;
        r0 = r6;
        goto L_0x00a9;
    L_0x00c8:
        r2 = move-exception;
        goto L_0x00a9;
    L_0x00ca:
        r0 = r6;
        goto L_0x0093;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mavenir.android.messaging.c.d.f(long):java.lang.String");
    }

    public Uri a() {
        if (this.c == null && this.e > 0) {
            this.c = ContentUris.withAppendedId(f.a, this.e);
        }
        return this.c;
    }

    public String b() {
        return this.d;
    }

    public void a(String str) {
        this.d = str;
    }

    public long c() {
        return this.e;
    }

    public void c(long j) {
        this.e = j;
    }

    public long d() {
        return this.f;
    }

    public void d(long j) {
        this.f = j;
    }

    public boolean e() {
        return g.b && this.e < 0;
    }

    public String f() {
        return this.h;
    }

    public void b(String str) {
        this.h = str;
    }

    public String g() {
        return this.i;
    }

    public void c(String str) {
        this.i = str;
    }

    public long h() {
        return this.j;
    }

    public long i() {
        return this.k;
    }

    public void e(long j) {
        this.j = j;
    }

    public void d(String str) {
        this.y = str;
    }

    public String j() {
        return this.z;
    }

    public String k() {
        return this.A;
    }

    public boolean l() {
        return this.l;
    }

    public void a(boolean z) {
        this.l = z;
    }

    public int m() {
        return this.m;
    }

    public void a(int i) {
        this.m = i;
    }

    public boolean n() {
        return this.o;
    }

    public void b(boolean z) {
        this.o = z;
    }

    public int o() {
        return this.n;
    }

    public void b(int i) {
        this.n = i;
    }

    public String p() {
        return this.q;
    }

    public boolean q() {
        return this.d != null && this.d.equals("mms");
    }

    public boolean r() {
        return this.d != null && this.d.equals("sms");
    }

    public boolean s() {
        boolean z;
        if (q() && (this.m == 1 || this.m == 0)) {
            z = true;
        } else {
            z = false;
        }
        boolean z2;
        if (r() && (this.m == 1 || this.m == 0)) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z || z2) {
            return false;
        }
        return true;
    }

    public boolean t() {
        boolean z;
        boolean z2 = q() && (this.m == 4 || this.m == 2);
        if (r() && (this.m == 5 || this.m == 4 || this.m == 6 || this.m == 2)) {
            z = true;
        } else {
            z = false;
        }
        return z2 || z;
    }

    public boolean u() {
        return (v() || x() || !t()) ? false : true;
    }

    public boolean v() {
        boolean z;
        if (!q() || this.p < 10) {
            z = false;
        } else {
            z = true;
        }
        boolean z2;
        if (r() && this.m == 5) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z || z2) {
            return true;
        }
        return false;
    }

    public void w() {
        if (r()) {
            this.m = 5;
        } else if (q()) {
            this.p = 10;
        }
    }

    public boolean x() {
        boolean z;
        if (q() && this.m == 2) {
            z = true;
        } else {
            z = false;
        }
        boolean z2;
        if (r() && this.m == 2) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z || z2) {
            return true;
        }
        return false;
    }

    public void c(boolean z) {
        this.w = z;
    }

    public boolean y() {
        return this.w;
    }

    public int z() {
        return this.x;
    }

    public void c(int i) {
        this.x = i;
    }
}
