package com.mavenir.android.common;

import android.content.Context;

public class s {
    private static s a = null;

    public static s a() {
        if (a == null) {
            b();
        }
        return a;
    }

    private static synchronized void b() {
        synchronized (s.class) {
            if (a == null) {
                Class cls = null;
                if (cls == null) {
                    try {
                        cls = Class.forName("com.mavenir.android.rcs.common.RcsMingleOptions");
                    } catch (ClassNotFoundException e) {
                    }
                }
                if (cls == null) {
                    try {
                        cls = Class.forName("com.mavenir.android.vtow.b.a");
                    } catch (ClassNotFoundException e2) {
                    }
                }
                if (cls == null) {
                    cls = s.class;
                }
                try {
                    a = (s) cls.newInstance();
                } catch (InstantiationException e3) {
                    e3.printStackTrace();
                } catch (IllegalAccessException e4) {
                    e4.printStackTrace();
                }
            }
        }
    }

    public boolean a(Context context) {
        return false;
    }
}
