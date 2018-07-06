package com.mavenir.android.activity;

import android.content.Context;
import com.fgmicrotec.mobile.android.fgvoip.FgVoIP;
import com.mavenir.android.common.q;
import java.lang.reflect.InvocationTargetException;

public class a {
    public static String a() {
        String str;
        if (FgVoIP.U().ad() == com.fgmicrotec.mobile.android.fgvoip.FgVoIP.a.VToW) {
            str = "com.mavenir.android.messaging.activity.ConversationActivity";
        } else {
            str = "com.mavenir.android.messaging.orig.ConversationActivity";
        }
        q.a("ConversationActivity", "Found class: " + str);
        return str;
    }

    public static void a(Context context, String str, String str2, boolean z) {
        q.a("ConversationActivity", "newMessage() - reflection");
        try {
            Class.forName(a()).getMethod("newMessage", new Class[]{Context.class, String.class, String.class, Boolean.TYPE}).invoke(null, new Object[]{context, str, str2, Boolean.valueOf(z)});
        } catch (NoSuchMethodException e) {
            q.c("ConversationActivity", "NoSuchMethodException: " + e.getLocalizedMessage(), e.getCause());
        } catch (ClassNotFoundException e2) {
            q.c("ConversationActivity", "ClassNotFoundException: " + e2.getLocalizedMessage(), e2.getCause());
        } catch (IllegalArgumentException e3) {
            q.c("ConversationActivity", "IllegalArgumentException: " + e3.getLocalizedMessage(), e3.getCause());
        } catch (IllegalAccessException e4) {
            q.c("ConversationActivity", "IllegalAccessException: " + e4.getLocalizedMessage(), e4.getCause());
        } catch (InvocationTargetException e5) {
            q.c("ConversationActivity", "InvocationTargetException: " + e5.getLocalizedMessage(), e5.getCause());
        }
    }
}
