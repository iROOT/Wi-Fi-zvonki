package com.mavenir.android.common;

import android.app.Activity;
import android.view.View;

public class w {
    private static w a = null;

    public static w a() {
        if (a == null) {
            d();
        }
        return a;
    }

    private static synchronized void d() {
        synchronized (w.class) {
            if (a == null) {
                Class cls;
                if (null == null) {
                    try {
                        cls = Class.forName("com.mavenir.android.b.a.a");
                    } catch (ClassNotFoundException e) {
                        q.d("SpiritWrapper", "Class.forName failed: " + e.getMessage());
                    }
                    if (cls == null) {
                        cls = w.class;
                    }
                    a = (w) cls.newInstance();
                }
                cls = null;
                if (cls == null) {
                    cls = w.class;
                }
                try {
                    a = (w) cls.newInstance();
                } catch (InstantiationException e2) {
                    e2.printStackTrace();
                } catch (IllegalAccessException e3) {
                    e3.printStackTrace();
                }
            }
        }
    }

    public void a(Activity activity) {
    }

    public void b(Activity activity) {
    }

    public void b() {
    }

    public View c(Activity activity) {
        return null;
    }

    public boolean c() {
        return false;
    }
}
