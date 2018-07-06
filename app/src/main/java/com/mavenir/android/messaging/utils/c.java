package com.mavenir.android.messaging.utils;

import android.content.Context;
import com.mavenir.android.common.q;
import java.util.HashSet;
import java.util.Iterator;

public class c {
    static final String[] a = new String[]{"thread_id"};
    private static c b;
    private final Context c;
    private boolean d;
    private final Object e = new Object();
    private HashSet<Long> f = new HashSet(4);
    private final Object g = new Object();
    private final Object h = new Object();
    private final HashSet<a> i = new HashSet(1);

    public interface a {
        void a(long j, boolean z);
    }

    private c(Context context, boolean z) {
        this.c = context.getApplicationContext();
        if (z) {
            a();
        }
    }

    public static c a(Context context) {
        if (b == null) {
            b = new c(context, false);
        }
        return b;
    }

    public static void b(Context context) {
        b = new c(context, true);
    }

    public static void c(Context context) {
        a(context).f.clear();
    }

    public void a() {
        q.a("DraftCache", "refresh()");
        Thread thread = new Thread(new Runnable(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.b();
            }
        }, "DraftCache.refresh");
        thread.setPriority(1);
        thread.start();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void b() {
        /*
        r10 = this;
        r7 = 1;
        r3 = 0;
        r0 = "DraftCache";
        r1 = "rebuildCache()";
        com.mavenir.android.common.q.a(r0, r1);
        r6 = new java.util.HashSet;
        r6.<init>();
        r0 = r10.c;
        r0 = r0.getContentResolver();
        r1 = com.mavenir.android.messaging.provider.g.e.e;
        r2 = a;
        r4 = r3;
        r5 = r3;
        r1 = r0.query(r1, r2, r3, r4, r5);
        if (r1 == 0) goto L_0x0044;
    L_0x0020:
        r0 = r1.moveToFirst();	 Catch:{ all -> 0x003c }
        if (r0 == 0) goto L_0x0041;
    L_0x0026:
        r0 = r1.isAfterLast();	 Catch:{ all -> 0x003c }
        if (r0 != 0) goto L_0x0041;
    L_0x002c:
        r0 = 0;
        r2 = r1.getLong(r0);	 Catch:{ all -> 0x003c }
        r0 = java.lang.Long.valueOf(r2);	 Catch:{ all -> 0x003c }
        r6.add(r0);	 Catch:{ all -> 0x003c }
        r1.moveToNext();	 Catch:{ all -> 0x003c }
        goto L_0x0026;
    L_0x003c:
        r0 = move-exception;
        r1.close();
        throw r0;
    L_0x0041:
        r1.close();
    L_0x0044:
        r1 = r10.g;
        monitor-enter(r1);
        r0 = r10.f;	 Catch:{ all -> 0x009f }
        r10.f = r6;	 Catch:{ all -> 0x009f }
        r2 = r10.h;	 Catch:{ all -> 0x009f }
        monitor-enter(r2);	 Catch:{ all -> 0x009f }
        r3 = r10.i;	 Catch:{ all -> 0x009c }
        r3 = r3.size();	 Catch:{ all -> 0x009c }
        if (r3 >= r7) goto L_0x0059;
    L_0x0056:
        monitor-exit(r2);	 Catch:{ all -> 0x009c }
        monitor-exit(r1);	 Catch:{ all -> 0x009f }
    L_0x0058:
        return;
    L_0x0059:
        monitor-exit(r2);	 Catch:{ all -> 0x009c }
        r2 = new java.util.HashSet;	 Catch:{ all -> 0x009f }
        r2.<init>(r6);	 Catch:{ all -> 0x009f }
        r2.removeAll(r0);	 Catch:{ all -> 0x009f }
        r3 = new java.util.HashSet;	 Catch:{ all -> 0x009f }
        r3.<init>(r0);	 Catch:{ all -> 0x009f }
        r3.removeAll(r6);	 Catch:{ all -> 0x009f }
        monitor-exit(r1);	 Catch:{ all -> 0x009f }
        r4 = r10.h;
        monitor-enter(r4);
        r0 = r10.i;	 Catch:{ all -> 0x0099 }
        r5 = r0.iterator();	 Catch:{ all -> 0x0099 }
    L_0x0074:
        r0 = r5.hasNext();	 Catch:{ all -> 0x0099 }
        if (r0 == 0) goto L_0x00bb;
    L_0x007a:
        r0 = r5.next();	 Catch:{ all -> 0x0099 }
        r0 = (com.mavenir.android.messaging.utils.c.a) r0;	 Catch:{ all -> 0x0099 }
        r6 = r2.iterator();	 Catch:{ all -> 0x0099 }
    L_0x0084:
        r1 = r6.hasNext();	 Catch:{ all -> 0x0099 }
        if (r1 == 0) goto L_0x00a2;
    L_0x008a:
        r1 = r6.next();	 Catch:{ all -> 0x0099 }
        r1 = (java.lang.Long) r1;	 Catch:{ all -> 0x0099 }
        r8 = r1.longValue();	 Catch:{ all -> 0x0099 }
        r1 = 1;
        r0.a(r8, r1);	 Catch:{ all -> 0x0099 }
        goto L_0x0084;
    L_0x0099:
        r0 = move-exception;
        monitor-exit(r4);	 Catch:{ all -> 0x0099 }
        throw r0;
    L_0x009c:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x009c }
        throw r0;	 Catch:{ all -> 0x009f }
    L_0x009f:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x009f }
        throw r0;
    L_0x00a2:
        r6 = r3.iterator();	 Catch:{ all -> 0x0099 }
    L_0x00a6:
        r1 = r6.hasNext();	 Catch:{ all -> 0x0099 }
        if (r1 == 0) goto L_0x0074;
    L_0x00ac:
        r1 = r6.next();	 Catch:{ all -> 0x0099 }
        r1 = (java.lang.Long) r1;	 Catch:{ all -> 0x0099 }
        r8 = r1.longValue();	 Catch:{ all -> 0x0099 }
        r1 = 0;
        r0.a(r8, r1);	 Catch:{ all -> 0x0099 }
        goto L_0x00a6;
    L_0x00bb:
        monitor-exit(r4);	 Catch:{ all -> 0x0099 }
        goto L_0x0058;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mavenir.android.messaging.utils.c.b():void");
    }

    public boolean a(long j) {
        boolean contains;
        synchronized (this.g) {
            contains = this.f.contains(Long.valueOf(j));
        }
        return contains;
    }

    public void a(long j, boolean z) {
        if (j > 0) {
            boolean add;
            synchronized (this.g) {
                if (z) {
                    add = this.f.add(Long.valueOf(j));
                } else {
                    add = this.f.remove(Long.valueOf(j));
                }
            }
            if (add) {
                synchronized (this.h) {
                    Iterator it = this.i.iterator();
                    while (it.hasNext()) {
                        ((a) it.next()).a(j, z);
                    }
                }
            }
        }
    }

    public void a(boolean z) {
        synchronized (this.e) {
            this.d = z;
        }
    }
}
