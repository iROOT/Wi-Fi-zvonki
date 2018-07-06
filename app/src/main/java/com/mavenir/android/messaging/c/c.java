package com.mavenir.android.messaging.c;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.text.TextUtils;
import com.fgmicrotec.mobile.android.fgvoip.FgVoIP;
import com.fgmicrotec.mobile.android.fgvoip.f.k;
import com.mavenir.android.common.q;
import com.mavenir.android.common.r;
import com.mavenir.android.common.t.d;
import com.mavenir.android.messaging.provider.a;
import com.mavenir.android.messaging.provider.g.g;
import com.mavenir.android.messaging.utils.b;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class c {
    private static Context a;
    private static boolean m = false;
    private static boolean n = false;
    private static Object o = new Object();
    private static boolean p = false;
    private long b;
    private String c;
    private b d;
    private long e;
    private int f;
    private String g;
    private boolean h;
    private boolean i;
    private boolean j;
    private boolean k;
    private boolean l;

    public c(Context context, Cursor cursor, boolean z) {
        a = context.getApplicationContext();
        a(context, this, cursor, z);
    }

    public c(Context context, long j, boolean z) {
        Context applicationContext;
        if (context != null) {
            applicationContext = context.getApplicationContext();
        } else {
            applicationContext = FgVoIP.U().getApplicationContext();
        }
        a = applicationContext;
        if (!a(this, j, z)) {
            this.d = new b();
            this.b = 0;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof c)) {
            return false;
        }
        return this.d.equals(((c) obj).d);
    }

    public int hashCode() {
        return this.d.hashCode();
    }

    private boolean a(c cVar, long j, boolean z) {
        Throwable th;
        Throwable th2;
        Cursor cursor = null;
        Cursor a;
        try {
            a = g.a(a.getContentResolver(), j);
            if (a != null) {
                try {
                    if (a.moveToFirst()) {
                        a(a, this, a, z);
                        if (j != this.b) {
                            q.d("Conversation", "loadFromConversationId: fillFromCursor returned different thread_id! threadId=" + j + ", mThreadId=" + this.b);
                        }
                        if (!(a == null || a.isClosed())) {
                            a.close();
                        }
                        return true;
                    }
                } catch (Exception e) {
                    cursor = a;
                    try {
                        q.d("Conversation", "loadFromConversationId: Can't find thread ID " + j);
                        cursor.close();
                        return true;
                    } catch (Throwable th3) {
                        th = th3;
                        a = cursor;
                        th2 = th;
                        a.close();
                        throw th2;
                    }
                } catch (Throwable th4) {
                    th2 = th4;
                    a.close();
                    throw th2;
                }
            }
            q.d("Conversation", "loadFromConversationId: Can't find thread ID " + j);
            if (a == null || a.isClosed()) {
                return false;
            }
            a.close();
            return false;
        } catch (Exception e2) {
            q.d("Conversation", "loadFromConversationId: Can't find thread ID " + j);
            if (!(cursor == null || cursor.isClosed())) {
                cursor.close();
            }
            return true;
        } catch (Throwable th32) {
            th = th32;
            a = null;
            th2 = th;
            if (!(a == null || a.isClosed())) {
                a.close();
            }
            throw th2;
        }
    }

    private static void a(Context context, c cVar, Cursor cursor, boolean z) {
        boolean z2 = true;
        synchronized (cVar) {
            boolean z3;
            cVar.b = cursor.getLong(0);
            cVar.e = cursor.getLong(1);
            cVar.f = cursor.getInt(2);
            String string = cursor.getString(4);
            if (TextUtils.isEmpty(string)) {
                string = context.getString(k.messages_no_subject);
            }
            cVar.g = string;
            cVar.c = cursor.getString(3);
            cVar.h = cursor.getInt(6) == 0;
            if (cursor.getInt(7) != 0) {
                z3 = true;
            } else {
                z3 = false;
            }
            cVar.j = z3;
            if (cursor.getInt(8) == 0) {
                z2 = false;
            }
            cVar.i = z2;
            cVar.k = a(a, cVar.b).v();
            cVar.l = false;
        }
        cVar.d = new b();
        if (!p) {
            b a = b.a(a, cVar.b, cVar.c, z);
            synchronized (cVar) {
                cVar.d = a;
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.mavenir.android.messaging.c.c a(android.content.Context r4, android.database.Cursor r5, boolean r6) {
        /*
        r2 = 0;
        r0 = r5.getLong(r2);
        p = r2;
        r2 = 0;
        r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r2 <= 0) goto L_0x0014;
    L_0x000d:
        r0 = com.mavenir.android.messaging.utils.b.a(r0);
        if (r0 == 0) goto L_0x0014;
    L_0x0013:
        return r0;
    L_0x0014:
        r0 = new com.mavenir.android.messaging.c.c;
        r0.<init>(r4, r5, r6);
        com.mavenir.android.messaging.utils.b.a(r0);	 Catch:{ IllegalStateException -> 0x001d }
        goto L_0x0013;
    L_0x001d:
        r1 = move-exception;
        r1 = "Conversation";
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "from(): Duplicate Conversation in Cache for: ";
        r2 = r2.append(r3);
        r2 = r2.append(r0);
        r2 = r2.toString();
        com.mavenir.android.common.q.d(r1, r2);
        r1 = com.mavenir.android.messaging.utils.b.b(r0);
        if (r1 != 0) goto L_0x0013;
    L_0x003c:
        r1 = "Conversation";
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "from(): Cache.replace() failed on ";
        r2 = r2.append(r3);
        r2 = r2.append(r0);
        r2 = r2.toString();
        com.mavenir.android.common.q.d(r1, r2);
        goto L_0x0013;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mavenir.android.messaging.c.c.a(android.content.Context, android.database.Cursor, boolean):com.mavenir.android.messaging.c.c");
    }

    public static c a(Context context, String[] strArr, boolean z) {
        c cVar = null;
        if (strArr != null) {
            cVar = b.a(b.a(context, strArr, z));
            if (cVar == null) {
                cVar = new c(context, -1, z);
                if (cVar.c() > 0) {
                    try {
                        b.a(cVar);
                    } catch (IllegalStateException e) {
                        q.d("Conversation", "get(): Duplicate Conversation in Cache for: " + cVar);
                        if (!b.b(cVar)) {
                            q.d("Conversation", "get(): Cache.replace() failed on " + cVar);
                        }
                    }
                }
            }
        }
        return cVar;
    }

    public static c a(Context context, Cursor cursor, boolean z, boolean z2) {
        long j = cursor.getLong(0);
        p = z2;
        if (j == 10 || j == 11) {
            q.a("Conversation", "10 or 11");
        }
        if (j > 0) {
            c a = b.a(j);
            if (a != null) {
                if (cursor.getLong(1) != a.h()) {
                    a.b(true);
                }
                String string = cursor.getString(4);
                if (TextUtils.isEmpty(string)) {
                    string = context.getString(k.messages_no_subject);
                }
                if (!string.equals(a.i())) {
                    a.b(true);
                }
                if (!a.n()) {
                    return a;
                }
            }
        }
        c cVar = new c(context, cursor, z);
        try {
            b.a(cVar);
            return cVar;
        } catch (IllegalStateException e) {
            q.d("Conversation", "from(): Duplicate Conversation in Cache for: " + cVar);
            if (b.b(cVar)) {
                return cVar;
            }
            q.d("Conversation", "from(): Cache.replace() failed on " + cVar);
            return cVar;
        }
    }

    public static void a(final Context context) {
        new Thread(new Runnable() {
            public void run() {
                c.c(context.getApplicationContext());
            }
        }, "Conversation.init()").start();
    }

    public static void a() {
        q.a("Conversation", "clearCache()");
        b.a().b();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void c(android.content.Context r8) {
        /*
        r3 = 0;
        r0 = "Conversation";
        r1 = "cacheAllThreads()";
        com.mavenir.android.common.q.a(r0, r1);
        r1 = com.mavenir.android.messaging.utils.b.a();
        monitor-enter(r1);
        r0 = m;	 Catch:{ all -> 0x00b3 }
        if (r0 == 0) goto L_0x001a;
    L_0x0011:
        r0 = "Conversation";
        r2 = "cacheAllThreads() already running - skipping";
        com.mavenir.android.common.q.a(r0, r2);	 Catch:{ all -> 0x00b3 }
        monitor-exit(r1);	 Catch:{ all -> 0x00b3 }
    L_0x0019:
        return;
    L_0x001a:
        r0 = 1;
        m = r0;	 Catch:{ all -> 0x00b3 }
        monitor-exit(r1);	 Catch:{ all -> 0x00b3 }
        r6 = new java.util.HashSet;
        r6.<init>();
        r0 = r8.getContentResolver();
        r1 = com.mavenir.android.messaging.provider.g.g.a;
        r2 = com.mavenir.android.messaging.provider.g.g.d;
        r4 = r3;
        r5 = r3;
        r1 = r0.query(r1, r2, r3, r4, r5);
        if (r1 == 0) goto L_0x00c6;
    L_0x0033:
        r0 = r1.moveToNext();	 Catch:{ all -> 0x009c }
        if (r0 == 0) goto L_0x00c6;
    L_0x0039:
        r0 = 0;
        r2 = r1.getLong(r0);	 Catch:{ all -> 0x009c }
        r0 = java.lang.Long.valueOf(r2);	 Catch:{ all -> 0x009c }
        r6.add(r0);	 Catch:{ all -> 0x009c }
        r4 = com.mavenir.android.messaging.utils.b.a();	 Catch:{ all -> 0x009c }
        monitor-enter(r4);	 Catch:{ all -> 0x009c }
        r0 = com.mavenir.android.messaging.utils.b.a(r2);	 Catch:{ all -> 0x00b6 }
        monitor-exit(r4);	 Catch:{ all -> 0x00b6 }
        if (r0 != 0) goto L_0x00b9;
    L_0x0051:
        r4 = new com.mavenir.android.messaging.c.c;	 Catch:{ all -> 0x009c }
        r0 = 1;
        r4.<init>(r8, r1, r0);	 Catch:{ all -> 0x009c }
        r5 = com.mavenir.android.messaging.utils.b.a();	 Catch:{ IllegalStateException -> 0x0064 }
        monitor-enter(r5);	 Catch:{ IllegalStateException -> 0x0064 }
        com.mavenir.android.messaging.utils.b.a(r4);	 Catch:{ all -> 0x0061 }
        monitor-exit(r5);	 Catch:{ all -> 0x0061 }
        goto L_0x0033;
    L_0x0061:
        r0 = move-exception;
        monitor-exit(r5);	 Catch:{ all -> 0x0061 }
        throw r0;	 Catch:{ IllegalStateException -> 0x0064 }
    L_0x0064:
        r0 = move-exception;
        r0 = "Conversation";
        r5 = new java.lang.StringBuilder;	 Catch:{ all -> 0x009c }
        r5.<init>();	 Catch:{ all -> 0x009c }
        r7 = "cacheAllThreads(): Conversation already  in Cache for threadId: ";
        r5 = r5.append(r7);	 Catch:{ all -> 0x009c }
        r2 = r5.append(r2);	 Catch:{ all -> 0x009c }
        r2 = r2.toString();	 Catch:{ all -> 0x009c }
        com.mavenir.android.common.q.d(r0, r2);	 Catch:{ all -> 0x009c }
        r0 = com.mavenir.android.messaging.utils.b.b(r4);	 Catch:{ all -> 0x009c }
        if (r0 != 0) goto L_0x0033;
    L_0x0083:
        r0 = "Conversation";
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x009c }
        r2.<init>();	 Catch:{ all -> 0x009c }
        r3 = "cacheAllThreads(): cache.replace() failed on ";
        r2 = r2.append(r3);	 Catch:{ all -> 0x009c }
        r2 = r2.append(r4);	 Catch:{ all -> 0x009c }
        r2 = r2.toString();	 Catch:{ all -> 0x009c }
        com.mavenir.android.common.q.d(r0, r2);	 Catch:{ all -> 0x009c }
        goto L_0x0033;
    L_0x009c:
        r0 = move-exception;
        if (r1 == 0) goto L_0x00a2;
    L_0x009f:
        r1.close();
    L_0x00a2:
        r1 = com.mavenir.android.messaging.utils.b.a();
        monitor-enter(r1);
        r2 = 0;
        m = r2;	 Catch:{ all -> 0x00e3 }
        monitor-exit(r1);	 Catch:{ all -> 0x00e3 }
        r1 = "Conversation";
        r2 = "cacheAllThreads() done";
        com.mavenir.android.common.q.a(r1, r2);
        throw r0;
    L_0x00b3:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x00b3 }
        throw r0;
    L_0x00b6:
        r0 = move-exception;
        monitor-exit(r4);	 Catch:{ all -> 0x00b6 }
        throw r0;	 Catch:{ all -> 0x009c }
    L_0x00b9:
        r2 = 1;
        a(r8, r0, r1, r2);	 Catch:{ all -> 0x009c }
        r2 = 1;
        r0.b(r2);	 Catch:{ all -> 0x009c }
        com.mavenir.android.messaging.utils.b.c(r0);	 Catch:{ all -> 0x009c }
        goto L_0x0033;
    L_0x00c6:
        if (r1 == 0) goto L_0x00cb;
    L_0x00c8:
        r1.close();
    L_0x00cb:
        r1 = com.mavenir.android.messaging.utils.b.a();
        monitor-enter(r1);
        r0 = 0;
        m = r0;	 Catch:{ all -> 0x00e0 }
        monitor-exit(r1);	 Catch:{ all -> 0x00e0 }
        r0 = "Conversation";
        r1 = "cacheAllThreads() done";
        com.mavenir.android.common.q.a(r0, r1);
        com.mavenir.android.messaging.utils.b.a(r6);
        goto L_0x0019;
    L_0x00e0:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x00e0 }
        throw r0;
    L_0x00e3:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x00e3 }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mavenir.android.messaging.c.c.c(android.content.Context):void");
    }

    public synchronized Uri b() {
        return ContentUris.withAppendedId(g.b, this.b);
    }

    public long c() {
        return this.b;
    }

    public synchronized long d() {
        q.a("Conversation", "ensureThreadId before: " + this.b);
        if (com.mavenir.android.messaging.provider.g.b) {
            a a = a.a(this.b);
            if (a.a <= 0) {
                a.a = a(a, this.d);
                this.b = a.a();
            }
        } else if (this.b <= 0) {
            this.b = a(a, this.d);
        }
        q.a("Conversation", "ensureThreadId after: " + this.b);
        return this.b;
    }

    public long e() {
        long j = this.b;
        if (com.mavenir.android.messaging.provider.g.b) {
            return a.a(this.b).a;
        }
        return j;
    }

    public boolean f() {
        if (com.mavenir.android.messaging.provider.g.b && a.a(this.b).a == -1) {
            return true;
        }
        return false;
    }

    public b g() {
        return this.d;
    }

    public void a(b bVar) {
        this.d = bVar;
        this.b = 0;
        q.a("Conversation", "Recipient set has changed!");
    }

    public long h() {
        return this.e;
    }

    public String i() {
        return this.g;
    }

    public boolean j() {
        return this.h;
    }

    public void a(boolean z) {
        this.h = z;
    }

    public boolean k() {
        return this.i;
    }

    public String l() {
        return d.g(this.e);
    }

    public boolean m() {
        return this.k;
    }

    public boolean n() {
        return this.l;
    }

    public void b(boolean z) {
        this.l = z;
    }

    private long a(Context context, b bVar) {
        long currentTimeMillis;
        Set hashSet = new HashSet();
        Iterator it = bVar.iterator();
        while (it.hasNext()) {
            a aVar = (a) it.next();
            a a = a.a(context, aVar.d(), false);
            if (a != null) {
                hashSet.add(a.d());
            } else {
                hashSet.add(aVar.d());
            }
        }
        synchronized (o) {
            q.a("Conversation", "Conversation getOrCreateThreadId for: " + bVar.a() + " sDeletingThreads: " + n);
            currentTimeMillis = System.currentTimeMillis();
            while (n) {
                try {
                    o.wait(30000);
                } catch (InterruptedException e) {
                }
                if (System.currentTimeMillis() - currentTimeMillis > 29000) {
                    q.d("Conversation", "getOrCreateThreadId timed out waiting for delete to complete");
                    n = false;
                    break;
                }
            }
            currentTimeMillis = -1;
            try {
                currentTimeMillis = g.a(context, hashSet);
            } catch (Exception e2) {
                q.c("Conversation", e2.getLocalizedMessage(), e2.getCause());
            }
            q.a("Conversation", "[Conversation] getOrCreateThreadId for " + hashSet + " returned " + currentTimeMillis);
        }
        return currentTimeMillis;
    }

    private static d a(Context context, long j) {
        Exception e;
        Throwable th;
        d dVar = new d(a);
        Cursor query;
        try {
            query = context.getContentResolver().query(g.a(j), d.a, null, null, null);
            if (query != null) {
                try {
                    if (query.moveToLast()) {
                        d dVar2 = new d(a, query);
                        if (query == null || query.isClosed()) {
                            return dVar2;
                        }
                        query.close();
                        return dVar2;
                    }
                } catch (Exception e2) {
                    e = e2;
                    try {
                        q.c("Conversation", e.getLocalizedMessage(), e.getCause());
                        query.close();
                        return dVar;
                    } catch (Throwable th2) {
                        th = th2;
                        query.close();
                        throw th;
                    }
                }
            }
            if (!(query == null || query.isClosed())) {
                query.close();
            }
        } catch (Exception e3) {
            e = e3;
            query = null;
            q.c("Conversation", e.getLocalizedMessage(), e.getCause());
            if (!(query == null || query.isClosed())) {
                query.close();
            }
            return dVar;
        } catch (Throwable th3) {
            th = th3;
            query = null;
            if (!(query == null || query.isClosed())) {
                query.close();
            }
            throw th;
        }
        return dVar;
    }

    public synchronized boolean o() {
        boolean z = false;
        synchronized (this) {
            if (this.b > 0) {
                if (com.mavenir.android.messaging.utils.c.a(a) != null) {
                    z = com.mavenir.android.messaging.utils.c.a(a).a(e());
                }
            }
        }
        return z;
    }

    public synchronized void c(boolean z) {
        if (this.b > 0) {
            com.mavenir.android.messaging.utils.c.a(a).a(e(), z);
        }
    }

    public void p() {
        final Uri a = g.a(this.b);
        new AsyncTask<Void, Void, Void>(this) {
            final /* synthetic */ c b;

            protected /* synthetic */ Object doInBackground(Object[] objArr) {
                return a((Void[]) objArr);
            }

            protected Void a(Void... voidArr) {
                q.a("Conversation", "markAsRead.doInBackground");
                if (a != null) {
                    int i;
                    Cursor query = c.a.getContentResolver().query(a, g.e, "(read=0 OR seen=0)", null, null);
                    if (query != null) {
                        try {
                            if (query.getCount() > 0) {
                                i = 1;
                            } else {
                                boolean i2 = false;
                            }
                            query.close();
                        } catch (Throwable th) {
                            query.close();
                        }
                    } else {
                        i2 = 1;
                    }
                    if (i2 != 0) {
                        q.a("Conversation", "markAsRead: update read/seen for thread uri: " + a);
                        ContentValues contentValues = new ContentValues(2);
                        contentValues.put("read", Integer.valueOf(1));
                        contentValues.put("seen", Integer.valueOf(1));
                        c.a.getContentResolver().update(a, contentValues, "(read=0 OR seen=0)", null);
                        b.b(this.b.b);
                    }
                    this.b.a(false);
                }
                if (FgVoIP.U().ai()) {
                    Intent intent = new Intent();
                    intent.setAction("InternalIntents.UpdateSmsNotification");
                    c.a.sendBroadcast(intent);
                } else {
                    r.a(c.a).a(false);
                }
                return null;
            }
        }.execute(new Void[0]);
    }
}
