package com.mavenir.android.common;

import android.content.Context;
import com.fgmicrotec.mobile.android.fgvoip.FgVoIP;

public class r {
    private static r a = null;
    private static Class<?> b = null;
    private static Object c = null;
    private static Context d;

    private r(Context context) {
        d = context;
        if (b == null) {
            try {
                if (FgVoIP.U().aj()) {
                    b = Class.forName("com.mavenir.android.rcs.common.RcsMessageNotificationWrapper");
                } else {
                    b = Class.forName("com.mavenir.android.messaging.utils.e");
                }
                if (b != null) {
                    c = b.getMethod("getInstance", new Class[]{Context.class}).invoke(null, new Object[]{d});
                }
            } catch (Exception e) {
                q.d("MessageNotificationWrapper", "createInstance(): Class.forName failed: " + e.getMessage());
            }
        }
    }

    public static r a(Context context) {
        if (a == null) {
            a = new r(context);
        }
        return a;
    }

    public void a(boolean z) {
        if (b != null) {
            try {
                b.getDeclaredMethod("updateNotifications", new Class[]{Boolean.TYPE}).invoke(c, new Object[]{Boolean.valueOf(z)});
                return;
            } catch (Exception e) {
                q.d("MessageNotificationWrapper", "updateNotifications(): " + e);
                return;
            }
        }
        q.d("MessageNotificationWrapper", "updateNotifications(): class definition is null");
    }
}
