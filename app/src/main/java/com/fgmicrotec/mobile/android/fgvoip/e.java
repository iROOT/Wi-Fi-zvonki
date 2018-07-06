package com.fgmicrotec.mobile.android.fgvoip;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.AsyncQueryHandler;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build.VERSION;
import android.os.SystemClock;
import android.provider.CallLog.Calls;
import android.support.v4.app.NotificationCompat.Builder;
import android.widget.RemoteViews;
import com.fgmicrotec.mobile.android.fgvoip.f.f;
import com.fgmicrotec.mobile.android.fgvoip.f.g;
import com.fgmicrotec.mobile.android.fgvoip.f.h;
import com.fgmicrotec.mobile.android.fgvoipcommon.CallService;
import com.mavenir.android.common.l;
import com.mavenir.android.common.q;
import com.mavenir.android.common.t;
import com.mavenir.android.settings.c.k;

public class e {
    private static final String[] a = new String[]{"_id", "name", "number", "date", "duration", "type"};
    private static e b = null;
    private Context c;
    private NotificationManager d;
    private int e = 0;
    private int f;
    private String g;
    private int h = 0;
    private String i = "";
    private a j = null;

    private class a extends AsyncQueryHandler {
        final /* synthetic */ e a;

        private class a {
            public String a;
            public String b;
            public String c;
            public long d;
            final /* synthetic */ a e;

            private a(a aVar) {
                this.e = aVar;
            }
        }

        public a(e eVar, ContentResolver contentResolver) {
            this.a = eVar;
            super(contentResolver);
        }

        protected void onQueryComplete(int i, Object obj, Cursor cursor) {
            switch (i) {
                case -1:
                    if (cursor != null) {
                        if (cursor.getCount() > 0 && cursor.moveToFirst()) {
                            a a = a(cursor);
                            this.a.a(a.a, a.b, a.c, a.d);
                        }
                        cursor.close();
                        return;
                    }
                    return;
                default:
                    return;
            }
        }

        private final a a(Cursor cursor) {
            a aVar = new a();
            aVar.a = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            aVar.b = cursor.getString(cursor.getColumnIndexOrThrow("number"));
            aVar.c = cursor.getString(cursor.getColumnIndexOrThrow("type"));
            aVar.d = cursor.getLong(cursor.getColumnIndexOrThrow("date"));
            return aVar;
        }
    }

    e(Context context) {
        this.c = context;
        this.d = (NotificationManager) context.getSystemService("notification");
    }

    public static void a(Context context) {
        if (b == null) {
            b = new e(context);
            b.f();
            b.g();
            b.h();
            b.j();
        }
    }

    public static e a() {
        return b;
    }

    private void j() {
        this.j = new a(this, this.c.getContentResolver());
        this.j.startQuery(-1, null, Calls.CONTENT_URI, a, "type=3 AND new=1".toString(), null, "date DESC");
    }

    public Notification a(int i, int i2, int i3, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, PendingIntent pendingIntent, PendingIntent pendingIntent2, int i4) {
        if (i == 3) {
            if (i2 == this.h) {
                return null;
            }
            this.h = i2;
        }
        Builder largeIcon = new Builder(this.c).setColor(this.c.getResources().getColor(com.fgmicrotec.mobile.android.fgvoip.f.e.app_main)).setContentIntent(pendingIntent).setContentTitle(charSequence3).setContentText(charSequence2).setDeleteIntent(pendingIntent2).setTicker(charSequence).setSmallIcon(i2).setLargeIcon(b(i3));
        int i5 = (i != 3 || k.i()) ? 0 : -2;
        Notification build = largeIcon.setPriority(i5).build();
        build.flags |= i4;
        return build;
    }

    public void a(int i, Notification notification) {
        if (notification != null) {
            this.d.notify(i, notification);
        }
    }

    public void b(int i, int i2, int i3, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, PendingIntent pendingIntent, PendingIntent pendingIntent2, int i4) {
        a(i, a(i, i2, i3, charSequence, charSequence2, charSequence3, pendingIntent, pendingIntent2, i4));
    }

    public Notification b() {
        return c();
    }

    public Notification c() {
        int i = 1;
        int i2 = 0;
        PendingIntent activity = PendingIntent.getActivity(this.c, 1, new Intent("com.mavenir.action.LAUNCH_MAIN_TAB"), 134217728);
        boolean ar = FgVoIP.U().ar();
        boolean aq = FgVoIP.U().aq();
        boolean at = FgVoIP.U().at();
        boolean n = CallService.n();
        int i3 = CallService.o() == com.fgmicrotec.mobile.android.fgmag.DataConnectionManager.a.LTE ? 1 : 0;
        if (CallService.o() != com.fgmicrotec.mobile.android.fgmag.DataConnectionManager.a.WLAN) {
            i = 0;
        }
        if (at && ((i3 != 0 && ar) || (i != 0 && aq))) {
            return null;
        }
        String str = "";
        if (at && i3 != 0) {
            i = f.ic_stat_notify_lte;
            Object obj = (String) this.c.getText(f.k.notification_service_lte_ready);
        } else if (at && i != 0) {
            i = f.ic_stat_notify_wifi;
            obj = (String) this.c.getText(f.k.notification_service_wifi_ready);
        } else if (n) {
            i = f.ic_stat_notify_registering;
            obj = (String) this.c.getText(f.k.notification_service_registering);
        } else if (this.c.getString(f.k.app_name_short).equals("TinT") || at || !ar) {
            i = f.ic_stat_notify_disconnected;
            if (l.a(this.c).G()) {
                obj = (String) this.c.getText(f.k.notification_service_4g);
            } else if (l.a(this.c).F()) {
                obj = (String) this.c.getText(f.k.notification_service_3g);
            } else {
                obj = (String) this.c.getText(f.k.notification_service_no_data);
            }
        } else {
            i = f.ic_stat_notify_disconnected;
            obj = (String) this.c.getText(f.k.notification_service_wifi_available);
        }
        if (i == this.h && obj.equals(this.i)) {
            return null;
        }
        this.h = i;
        this.i = obj;
        Builder builder = new Builder(this.c);
        builder.setColor(this.c.getResources().getColor(com.fgmicrotec.mobile.android.fgvoip.f.e.app_main));
        builder.setContentIntent(activity);
        builder.setContentTitle(this.c.getText(f.k.app_name));
        builder.setContentText(obj);
        builder.setDeleteIntent(null);
        if (this.c.getString(f.k.app_name_short).equals("TinT")) {
            builder.setLargeIcon(BitmapFactory.decodeResource(this.c.getResources(), f.ic_launcher));
        }
        builder.setShowWhen(false);
        builder.setSmallIcon(i);
        builder.setTicker(obj);
        if (!k.i()) {
            i2 = -2;
        }
        builder.setPriority(i2);
        q.a("NotificationMgr", "createWiFiUpdateNotification(): registering: " + n + ", loggedIn: " + at + ", lteConnected: " + aq + ", wifiConnected: " + ar + ", message: " + obj + ", priority: " + i2);
        Notification build = builder.build();
        build.flags |= 32;
        return build;
    }

    public void a(boolean z, int i) {
        Notification c = c();
        this.c.sendBroadcast(new Intent("MainTabActions.LoginStatusChanged"));
        a(3, c);
    }

    public void a(boolean z, int i, boolean z2) {
        if (z2) {
            q.a("NotificationMgr", "notifyWiFiUpdate(): resetting icon");
            this.h = 0;
        }
        a(z, i);
    }

    public void d() {
        Intent intent = new Intent(this.c, CallService.class);
        intent.setAction("CallServiceActions.ActionNavigateToApplicationUpdatePage");
        PendingIntent service = PendingIntent.getService(this.c, 0, intent, 134217728);
        b(4, 17301625, 0, this.c.getText(f.k.notification_new_app_update_title), this.c.getText(f.k.notification_new_app_update_message), this.c.getText(f.k.notification_new_app_update_title), service, null, 16);
    }

    public void a(String str, String str2, String str3, long j) {
        this.e = k();
        String f = t.f.f(str2);
        f = com.mavenir.android.common.k.a(f, f);
        CharSequence stringBuffer = new StringBuffer();
        Object stringBuffer2 = new StringBuffer();
        if (this.e == 1) {
            stringBuffer.append(this.c.getText(f.k.notification_missed_call_title));
            stringBuffer2.append(f);
        } else {
            stringBuffer.append(this.c.getText(f.k.notification_missed_calls_title));
            stringBuffer2.append(this.c.getString(f.k.notification_missed_calls_message, new Object[]{Integer.valueOf(this.e)}));
        }
        PendingIntent activity = PendingIntent.getActivity(FgVoIP.U(), 0, FgVoIP.aJ(), 134217728);
        PendingIntent activity2 = PendingIntent.getActivity(FgVoIP.U(), 0, FgVoIP.aK(), 134217728);
        if (this.e == 0) {
            h();
        } else {
            b(1, f.ic_call_missed, f.ic_launcher, stringBuffer, stringBuffer2, stringBuffer, activity, activity2, 16);
        }
    }

    public void a(int i) {
        this.d.cancel(i);
    }

    public void e() {
        this.h = 0;
        a(3);
    }

    public void f() {
        a(4);
    }

    public void g() {
        if (this.f == 0) {
            a(2);
            this.f = 0;
        } else {
            a(2);
            this.f = 0;
        }
    }

    public void h() {
        q.b("NotificationMgr", "Cleared missed calls");
        this.e = 0;
        a(1);
    }

    public void i() {
        e();
        f();
        g();
        h();
    }

    public void a(String str, String str2, int i, boolean z, int i2) {
        PendingIntent activity = PendingIntent.getActivity(FgVoIP.U(), 2, FgVoIP.U().a(str2, str, i, false, i2), 268435456);
        String f = t.f.f(str2);
        String a = com.mavenir.android.common.k.a(f, f);
        Bitmap decodeResource = BitmapFactory.decodeResource(this.c.getResources(), f.ic_launcher);
        PendingIntent broadcast = PendingIntent.getBroadcast(FgVoIP.U(), 2, FgVoIP.a(str2, a, i, 1), 1342177280);
        Builder addAction = new Builder(this.c).setColor(this.c.getResources().getColor(com.fgmicrotec.mobile.android.fgvoip.f.e.app_main)).setContentIntent(activity).setFullScreenIntent(activity, true).setContentTitle(this.c.getString(f.k.notification_incoming_call)).setContentText(a).setDefaults(0).setTicker(this.c.getString(f.k.notification_incoming_call)).setOngoing(true).setPriority(2).setSmallIcon(f.stat_sys_phone_call).setLargeIcon(decodeResource).setUsesChronometer(false).setWhen(System.currentTimeMillis()).addAction(f.ic_action_remove_gray, this.c.getString(f.k.notification_incoming_call_reject), broadcast).addAction(f.ic_action_call_gray, this.c.getString(f.k.notification_incoming_call_accept), PendingIntent.getActivity(FgVoIP.U(), 2, FgVoIP.U().a(str2, str, i, true, i2), 1342177280));
        this.d.cancel(2);
        this.d.notify(2, addAction.build());
    }

    public String a(String str, String str2, long j, boolean z, boolean z2, boolean z3, boolean z4) {
        Notification build;
        a(z, z2, z3, z4);
        Bitmap b = b(f.ic_launcher);
        String a = com.mavenir.android.common.k.a(str2, str2);
        String a2 = a(z, z2, z3);
        Builder when = new Builder(this.c).setColor(this.c.getResources().getColor(com.fgmicrotec.mobile.android.fgvoip.f.e.app_main)).setContentIntent(PendingIntent.getActivity(FgVoIP.U(), 2, FgVoIP.U().aI(), 268435456)).setContentTitle(a2).setContentText(a).setDefaults(0).setLargeIcon(b).setTicker(b(z, z2, z3)).setOngoing(true).setSmallIcon(this.f).setUsesChronometer(j > 0).setWhen(System.currentTimeMillis());
        if (VERSION.SDK_INT < 16) {
            RemoteViews a3 = a(j, a2, a);
            when.setContent(a3);
            build = when.build();
            build.contentView = a3;
        } else {
            build = when.build();
        }
        this.d.cancel(2);
        this.d.notify(2, build);
        return a;
    }

    private RemoteViews a(long j, String str, String str2) {
        RemoteViews remoteViews = new RemoteViews(this.c.getPackageName(), h.ongoing_call_notification);
        remoteViews.setImageViewResource(g.icon, this.f);
        remoteViews.setTextViewText(g.text1, str);
        remoteViews.setTextViewText(g.text2, str2);
        if (j > 0) {
            remoteViews.setChronometer(g.text1, SystemClock.elapsedRealtime() - (System.currentTimeMillis() - j), str, true);
        }
        return remoteViews;
    }

    private String a(boolean z, boolean z2, boolean z3) {
        if (z) {
            return this.c.getString(f.k.notification_incoming_call);
        }
        if (z3 && !z2) {
            return this.c.getString(f.k.notification_on_hold);
        }
        if (VERSION.SDK_INT < 16) {
            return this.c.getString(f.k.notification_ongoing_call_format);
        }
        return this.c.getString(f.k.notification_ongoing_call);
    }

    private String b(boolean z, boolean z2, boolean z3) {
        if (z) {
            return this.c.getString(f.k.notification_incoming_call);
        }
        if (!z3 || z2) {
            return this.c.getString(f.k.ongoing);
        }
        return this.c.getString(f.k.notification_on_hold);
    }

    private void a(boolean z, boolean z2, boolean z3, boolean z4) {
        int i;
        if (z) {
            i = f.stat_sys_phone_call_ringing;
            this.g = this.c.getString(f.k.cd_notification_ringing);
        } else if (!z2 && z3) {
            i = f.stat_sys_phone_call_on_hold;
            this.g = this.c.getString(f.k.cd_notification_hold);
        } else if (z4) {
            i = f.stat_sys_phone_call_bluetooth;
            this.g = this.c.getString(f.k.cd_notification_bluetooth);
        } else {
            i = f.stat_sys_phone_call;
            this.g = this.c.getString(f.k.cd_notification_ongoing);
        }
        this.f = i;
    }

    private Bitmap b(int i) {
        if (i > 0) {
            return BitmapFactory.decodeResource(this.c.getResources(), i);
        }
        return null;
    }

    private int k() {
        Exception e;
        Throwable th;
        Cursor query;
        try {
            int count;
            query = this.c.getContentResolver().query(Calls.CONTENT_URI, null, "type=? AND new=?", new String[]{"3", "1"}, null);
            if (query != null) {
                try {
                    count = query.getCount();
                } catch (Exception e2) {
                    e = e2;
                    try {
                        q.d("NotificationMgr", "getMissedCallsCount(): " + e.getLocalizedMessage());
                        if (query != null) {
                        }
                        return 0;
                    } catch (Throwable th2) {
                        th = th2;
                        query.close();
                        throw th;
                    }
                }
            }
            count = 0;
            if (query == null || query.isClosed()) {
                return count;
            }
            query.close();
            return count;
        } catch (Exception e3) {
            e = e3;
            query = null;
            q.d("NotificationMgr", "getMissedCallsCount(): " + e.getLocalizedMessage());
            if (query != null || query.isClosed()) {
                return 0;
            }
            query.close();
            return 0;
        } catch (Throwable th3) {
            th = th3;
            query = null;
            if (!(query == null || query.isClosed())) {
                query.close();
            }
            throw th;
        }
    }
}
