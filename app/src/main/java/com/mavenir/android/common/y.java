package com.mavenir.android.common;

import android.content.Context;
import android.content.Intent;
import com.fgmicrotec.mobile.android.fgvoip.FgVoIP;

public class y {
    private static y a = null;

    public static y a() {
        if (a == null) {
            f();
        }
        return a;
    }

    private static synchronized void f() {
        synchronized (y.class) {
            if (a == null) {
                Class cls;
                if (null == null) {
                    try {
                        if (FgVoIP.U().aj()) {
                            cls = Class.forName("com.mavenir.android.rcs.common.RcsVpnWrapper");
                        } else {
                            cls = y.class;
                        }
                    } catch (ClassNotFoundException e) {
                        q.d("VpnWrapper", "createInstance(): Class.forName failed: " + e.getMessage());
                    }
                    a = (y) cls.newInstance();
                }
                cls = null;
                try {
                    a = (y) cls.newInstance();
                } catch (InstantiationException e2) {
                    e2.printStackTrace();
                } catch (IllegalAccessException e3) {
                    e3.printStackTrace();
                }
            }
        }
    }

    public void b() {
    }

    public void c() {
    }

    public boolean d() {
        return false;
    }

    public boolean e() {
        return false;
    }

    public Intent a(Context context) {
        return null;
    }
}
