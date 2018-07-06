package com.mavenir.android.messaging.utils;

import com.mavenir.android.messaging.c.c;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class b {
    private static b a = new b();
    private final HashSet<c> b = new HashSet(10);

    private b() {
    }

    public static b a() {
        if (a == null) {
            a = new b();
        }
        return a;
    }

    public void b() {
        this.b.clear();
    }

    public static c a(long j) {
        synchronized (a) {
            Iterator it = a.b.iterator();
            while (it.hasNext()) {
                c cVar = (c) it.next();
                if (cVar.c() == j) {
                    return cVar;
                }
            }
            return null;
        }
    }

    public static c a(com.mavenir.android.messaging.c.b bVar) {
        synchronized (a) {
            Iterator it = a.b.iterator();
            while (it.hasNext()) {
                c cVar = (c) it.next();
                String[] b = bVar.b();
                String[] b2 = cVar.g().b();
                if (b.length == b2.length) {
                    int i = 0;
                    int i2 = 0;
                    while (i < b.length && b[i].trim().equals(b2[i].trim())) {
                        i2++;
                        i++;
                    }
                    if (i2 == b2.length) {
                        return cVar;
                    }
                }
            }
            return null;
        }
    }

    public static void a(c cVar) {
        synchronized (a) {
            if (a.b.contains(cVar)) {
                throw new IllegalStateException("cache already contains " + cVar + " threadId: " + cVar.c());
            }
            a.b.add(cVar);
        }
    }

    public static boolean b(c cVar) {
        boolean z;
        synchronized (a) {
            if (a.b.contains(cVar)) {
                a.b.remove(cVar);
                a.b.add(cVar);
                z = true;
            } else {
                z = false;
            }
        }
        return z;
    }

    public static void c(c cVar) {
        synchronized (a) {
            a.b.remove(cVar);
            a.b.add(cVar);
        }
    }

    public static boolean b(long j) {
        synchronized (a) {
            Iterator it = a.b.iterator();
            while (it.hasNext()) {
                c cVar = (c) it.next();
                if (cVar.c() == j) {
                    cVar.b(true);
                    return true;
                }
            }
            return false;
        }
    }

    public static void c(long j) {
        synchronized (a) {
            Iterator it = a.b.iterator();
            while (it.hasNext()) {
                c cVar = (c) it.next();
                if (cVar.c() == j) {
                    a.b.remove(cVar);
                    return;
                }
            }
        }
    }

    public static void a(Set<Long> set) {
        synchronized (a) {
            Iterator it = a.b.iterator();
            while (it.hasNext()) {
                if (!set.contains(Long.valueOf(((c) it.next()).c()))) {
                    it.remove();
                }
            }
        }
    }
}
