package com.mavenir.android.messaging.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.support.v4.app.NotificationCompat.Builder;
import android.support.v4.app.TaskStackBuilder;
import com.fgmicrotec.mobile.android.fgvoip.FgVoIP;
import com.fgmicrotec.mobile.android.fgvoip.f;
import com.mavenir.android.common.q;
import com.mavenir.android.messaging.activity.ConversationActivity;
import com.mavenir.android.messaging.c.a;
import com.mavenir.android.messaging.c.d;
import com.mavenir.android.messaging.service.MessagingService;
import com.mavenir.android.settings.b;
import com.mavenir.android.settings.c.k;
import com.mavenir.android.vtow.activity.MainTabActivity;
import java.util.List;

public class e {
    private static e c = null;
    private static Context d;
    PowerManager a = null;
    Builder b = null;
    private NotificationManager e;
    private WakeLock f = null;
    private int g = Integer.MAX_VALUE;
    private boolean h = false;
    private int i;
    private int j;
    private int k = 0;

    private e(Context context) {
        d = context.getApplicationContext();
        this.e = (NotificationManager) context.getSystemService("notification");
        this.a = (PowerManager) context.getSystemService("power");
        this.f = this.a.newWakeLock(805306394, "MessageNotification");
    }

    public static e a(Context context) {
        if (c == null) {
            c = new e(context);
        }
        return c;
    }

    public void a(boolean z) {
        if (FgVoIP.U().ax()) {
            b(z);
            c(z);
            Intent intent = new Intent();
            intent.setAction("com.mavenir.action.UPDATE_MAIN_TAB");
            d.sendBroadcast(intent);
            return;
        }
        a();
    }

    public void b(boolean z) {
        if (!FgVoIP.U().ax()) {
            a();
        } else if (k.p()) {
            Object obj;
            Object obj2;
            e();
            List<d> b = com.mavenir.android.messaging.provider.d.b(d);
            int size = b != null ? b.size() : 0;
            if (this.g >= size) {
                obj = null;
            } else {
                obj = 1;
            }
            Object obj3;
            if (PendingIntent.getActivity(FgVoIP.U(), 2, FgVoIP.U().aI(), 536870912) != null) {
                obj2 = null;
                obj3 = 1;
            } else {
                int obj22 = 1;
                obj3 = obj;
            }
            if (size == 0) {
                this.e.cancel(2001);
                this.g = 0;
                return;
            }
            this.j = 2001;
            if (!this.h || size > this.g) {
                d dVar;
                Intent intent;
                int i;
                this.h = false;
                if (b == null || b.size() <= 0) {
                    dVar = null;
                } else {
                    dVar = (d) b.get(0);
                }
                a a = a.a(d, dVar.f(), false);
                StringBuffer stringBuffer = new StringBuffer();
                Object stringBuffer2 = new StringBuffer();
                Object obj4 = 1;
                long d = ((d) b.get(0)).d();
                for (d d2 : b) {
                    if (d != d2.d()) {
                        obj4 = null;
                        break;
                    }
                }
                if (obj4 != null) {
                    String b2 = a.b();
                    if (b2 == null || b2.length() == 0) {
                        b2 = a.d();
                    }
                    stringBuffer.append(b2);
                    if (size == 1) {
                        stringBuffer2.append(dVar.g());
                    } else {
                        stringBuffer2.append(size + " " + d.getText(f.k.notification_received_another_sip_sms_message));
                    }
                } else {
                    stringBuffer.append(size);
                    stringBuffer.append(" ");
                    stringBuffer.append(d.getText(f.k.notification_received_another_sip_sms_message));
                    stringBuffer2.append(d.getString(f.k.notification_received_another_details));
                }
                Bitmap decodeResource = BitmapFactory.decodeResource(d.getResources(), f.f.ic_launcher);
                TaskStackBuilder create = TaskStackBuilder.create(d);
                if (obj4 != null) {
                    intent = new Intent(d, ConversationActivity.class);
                    intent.putExtra("extra_conversationId", dVar.d());
                    intent.setAction("ACTION_VIEW_MESSAGES");
                    intent.addFlags(268435456);
                    create.addParentStack(ConversationActivity.class);
                } else {
                    intent = new Intent(d, MainTabActivity.class);
                    intent.putExtra("MainTabExtras.ExtraSelectedTabIndex", 2);
                    intent.addFlags(268435456);
                }
                create.addNextIntent(intent);
                PendingIntent pendingIntent = create.getPendingIntent(0, 268435456);
                intent = new Intent(d, MessagingService.class);
                intent.setAction("InternalIntents.CanceledSmsNotification");
                PendingIntent service = PendingIntent.getService(d, 0, intent, 134217728);
                Uri uri = null;
                CharSequence charSequence = null;
                if (size <= this.g || obj3 == null) {
                    i = 4;
                } else {
                    int i2;
                    Uri uri2;
                    String stringBuffer3 = stringBuffer.toString();
                    String q = k.q();
                    int i3;
                    if (q.equals(b.z)) {
                        if (obj22 != null) {
                            i3 = -1;
                        } else {
                            i3 = 5;
                        }
                        i2 = i3;
                        uri2 = null;
                    } else {
                        i3 = obj22 != null ? 6 : 4;
                        if (q.length() > 0) {
                            i2 = i3;
                            uri2 = Uri.parse(q);
                        } else {
                            i2 = i3;
                            uri2 = null;
                        }
                    }
                    String str = stringBuffer3;
                    i = i2;
                    uri = uri2;
                    charSequence = str;
                }
                this.e.notify(2001, new Builder(d).setColor(d.getResources().getColor(com.fgmicrotec.mobile.android.fgvoip.f.e.app_main)).setContentText(stringBuffer2).setContentTitle(stringBuffer).setContentIntent(pendingIntent).setDeleteIntent(service).setLargeIcon(decodeResource).setSmallIcon(f.f.ic_action_message).setTicker(charSequence).setWhen(dVar.h()).setDefaults(i).setSound(uri).build());
                this.g = size;
                return;
            }
            this.g = size;
        } else {
            this.g = 0;
        }
    }

    public void c(boolean z) {
    }

    public void a(long j) {
        if (!FgVoIP.U().S()) {
            return;
        }
        if (FgVoIP.U().ax()) {
            this.i = 1001;
            d a = com.mavenir.android.messaging.provider.d.a(d, j);
            if (a != null) {
                int i;
                CharSequence f = a.f();
                a a2 = a.a(d, a.f(), false);
                if (!(a2 == null || a2.b() == null)) {
                    f = a2.b();
                }
                Intent intent = new Intent(d, MessagingService.class);
                intent.setAction("InternalIntents.NavigateToMessageDeliveryReport");
                Builder when = new Builder(d).setColor(d.getResources().getColor(com.fgmicrotec.mobile.android.fgvoip.f.e.app_main)).setContentIntent(PendingIntent.getService(d, 0, intent, 268435456)).setContentTitle(d.getString(f.k.notification_received_delivery_report)).setContentText(f).setTicker(d.getString(f.k.notification_received_delivery_report)).setSmallIcon(f.f.ic_action_message).setPriority(0).setWhen(System.currentTimeMillis());
                if (FgVoIP.U().R()) {
                    Uri uri;
                    String q = k.q();
                    if (q.equals(b.z)) {
                        i = -1;
                        uri = null;
                    } else if (q.length() > 0) {
                        uri = Uri.parse(q);
                        i = 6;
                    } else {
                        uri = null;
                        i = 6;
                    }
                    when.setSound(uri);
                } else {
                    i = 4;
                }
                when.setDefaults(i);
                Notification build = when.build();
                build.flags |= 16;
                this.e.notify(this.i, build);
                return;
            }
            return;
        }
        a();
    }

    private void b() {
        if (this.k > 0) {
            this.e.cancel(this.k);
            this.k = 0;
        }
    }

    private void c() {
        this.g = 0;
        if (this.j > 0) {
            this.e.cancel(this.j);
            this.j = 0;
        }
    }

    private void d() {
        if (this.i > 0) {
            this.e.cancel(1001);
            this.i = 0;
        }
    }

    public void a() {
        b();
        c();
        d();
        f();
    }

    private void e() {
        if (this.f != null && this.a != null && !this.a.isScreenOn()) {
            q.a("MessageNotification", "Aquired wake lock for screen");
            this.f.acquire(5000);
        }
    }

    private void f() {
        if (this.f != null && this.f.isHeld()) {
            q.a("MessageNotification", "Released wake lock");
            this.f.release();
        }
    }
}
