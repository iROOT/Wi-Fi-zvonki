package com.mavenir.android.messaging.service;

import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Binder;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.IBinder;
import android.os.SystemClock;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.fgmicrotec.mobile.android.fgvoip.FgVoIP;
import com.mavenir.android.applog.AppLogAdapter;
import com.mavenir.android.common.l;
import com.mavenir.android.common.q;
import com.mavenir.android.common.t;
import com.mavenir.android.messaging.activity.ConversationActivity;
import com.mavenir.android.messaging.c.d;
import com.mavenir.android.messaging.provider.e;
import com.mavenir.android.messaging.provider.g;
import com.mavenir.android.messaging.provider.g.f;
import com.mavenir.android.settings.c.k;
import com.mavenir.android.settings.c.p;
import com.mavenir.android.settings.c.w;
import com.mavenir.android.vtow.activity.MainTabActivity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MessagingService extends Service implements a {
    public static long a = -1;
    private static boolean b = false;
    private static boolean c = false;
    private static MessagingServiceBroadcastReceiver h = null;
    private static boolean q = false;
    private Handler d = null;
    private MessagingAdapter e = null;
    private e f = null;
    private com.mavenir.android.messaging.provider.b g = null;
    private PhoneStateListener i = null;
    private final IBinder j = new a(this);
    private boolean k = false;
    private int l = 0;
    private int m = 0;
    private HashMap<Integer, Long> n = new HashMap();
    private List<String> o = null;
    private List<String> p = null;
    private Runnable r = new Runnable(this) {
        final /* synthetic */ MessagingService a;

        {
            this.a = r1;
        }

        public void run() {
            this.a.e = new MessagingAdapter(this.a);
            this.a.e.init();
            this.a.f();
        }
    };
    private Runnable s = new Runnable(this) {
        final /* synthetic */ MessagingService a;

        {
            this.a = r1;
        }

        public void run() {
            this.a.d();
        }
    };

    public static class MessagingServiceBroadcastReceiver extends BroadcastReceiver {
        public void onReceive(Context context, Intent intent) {
            Intent intent2 = new Intent(context, MessagingService.class);
            intent2.setAction(intent.getAction());
            intent2.putExtras(intent);
            context.startService(intent2);
        }
    }

    public class a extends Binder {
        final /* synthetic */ MessagingService a;

        public a(MessagingService messagingService) {
            this.a = messagingService;
        }
    }

    public static class b {
        private static HashMap<Long, Integer> a;
        private static HashMap<Long, Integer> b;

        public static void a(final Context context) {
            new Thread(new Runnable() {
                public void run() {
                    b.a = new HashMap();
                    b.b = new HashMap();
                    for (d c : com.mavenir.android.messaging.provider.d.c(context)) {
                        b.a(c.c(), 0, 0);
                    }
                }
            }).start();
        }

        public static void a() {
            if (a != null) {
                a.clear();
            }
            if (b != null) {
                b.clear();
            }
        }

        public static void a(long j, int i, int i2) {
            if (a != null) {
                a.put(Long.valueOf(j), Integer.valueOf(i));
                b.put(Long.valueOf(j), Integer.valueOf(i2));
                return;
            }
            q.d("MessagingService", "addToQueue(): queue not initialized");
        }

        public static void a(long j) {
            if (a != null) {
                a.remove(Long.valueOf(j));
                b.remove(Long.valueOf(j));
                return;
            }
            q.d("MessagingService", "removeFromQueue(): queue not initialized");
        }

        public static int b(long j) {
            if (a == null) {
                q.d("MessagingService", "getTimesSent(): queue not initialized");
                return 0;
            } else if (a.get(Long.valueOf(j)) == null) {
                return 0;
            } else {
                return ((Integer) a.get(Long.valueOf(j))).intValue();
            }
        }

        public static int c(long j) {
            if (b == null) {
                q.d("MessagingService", "getLastSendOption(): queue not initialized");
                return 0;
            } else if (b.get(Long.valueOf(j)) == null) {
                return 0;
            } else {
                return ((Integer) b.get(Long.valueOf(j))).intValue();
            }
        }

        public static void d(long j) {
            if (a != null) {
                int intValue = a.get(Long.valueOf(j)) == null ? 0 : ((Integer) a.get(Long.valueOf(j))).intValue();
                q.a("MessagingService", "updateTimesSent(): msgId: " + j + ", timesSent: " + (intValue + 1));
                a.put(Long.valueOf(j), Integer.valueOf(intValue + 1));
                return;
            }
            q.d("MessagingService", "updateTimesSent(): queue not initialized");
        }

        public static void a(long j, int i) {
            if (b != null) {
                q.a("MessagingService", "updateSendOption(): msgId: " + j + ", sendOption: " + i);
                b.put(Long.valueOf(j), Integer.valueOf(i));
                return;
            }
            q.d("MessagingService", "updateLastSendOption(): queue not initialized");
        }
    }

    private class c extends PhoneStateListener {
        final /* synthetic */ MessagingService a;

        private c(MessagingService messagingService) {
            this.a = messagingService;
        }

        /* synthetic */ c(MessagingService messagingService, AnonymousClass1 anonymousClass1) {
            this(messagingService);
        }

        public void onServiceStateChanged(ServiceState serviceState) {
            q.a("MessagingService", "Service state changed: " + serviceState.getState());
            if (serviceState.getState() == 0 || serviceState.getState() == 2) {
                this.a.a(1000);
            }
        }
    }

    public void onCreate() {
        int i = 0;
        super.onCreate();
        q.b("MessagingService", "MessagingService onCreate()");
        this.d = new Handler();
        this.p = new ArrayList();
        this.o = new ArrayList();
        h = new MessagingServiceBroadcastReceiver();
        this.f = new e(this, this.d);
        this.g = new com.mavenir.android.messaging.provider.b(this, this.d);
        getContentResolver().registerContentObserver(g.e.a, true, this.f);
        getContentResolver().registerContentObserver(com.mavenir.android.messaging.provider.g.f.b.a, true, this.g);
        String[] strArr = new String[]{"IntentActions.StartServiceReq", "IntentActions.StopServiceReq", "com.fgmicrotec.mobile.android.voip.SendMessageReq", "com.fgmicrotec.mobile.android.voip.SendMessageSipSmsReq", "com.fgmicrotec.mobile.android.voip.SendMessageLegacySmsReq", "InternalIntents.UpdateSmsNotification", "InternalIntents.ActionUpdateSmsSettings", "ActionSendSMSOverWiFi", "MessagingService.ACTION_SEND_SUCCESS", "MessagingService.ACTION_SEND_FAILED", "MessagingService.ACTION_REFRESH_CACHE", "com.fgmicrotec.mobile.android.voip.SimulateReceiveSmsReq", "com.fgmicrotec.mobile.android.voip.LoginToServerCnf"};
        while (i < strArr.length) {
            registerReceiver(h, new IntentFilter(strArr[i]));
            i++;
        }
        if (FgVoIP.U().am()) {
            this.d.post(this.r);
            return;
        }
        FgVoIP.U().a("IntentActions.ActionNone");
        this.d.postDelayed(this.r, 2000);
        q.b("MessagingService", "MessagingService: CallService not running, start it!");
    }

    public void onDestroy() {
        super.onDestroy();
        Intent intent = new Intent();
        intent.setAction("com.fgmicrotec.mobile.android.voip.MessagingServiceReleasedInd");
        sendBroadcast(intent);
        b = false;
        h();
        unregisterReceiver(h);
        getContentResolver().unregisterContentObserver(this.f);
        getContentResolver().unregisterContentObserver(this.g);
        c = false;
        a = -1;
        com.mavenir.android.messaging.utils.e.a((Context) this).a();
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        super.onStartCommand(intent, i, i2);
        if (intent != null) {
            String action = intent.getAction();
            q.a("MessagingService", "onStartCommand() " + (action != null ? " : " + action : ""));
            if (action != null) {
                if (action.equals("IntentActions.StartServiceReq") && !b) {
                    a();
                    b();
                    com.mavenir.android.messaging.provider.d.e(this);
                    this.d.postDelayed(new Runnable(this) {
                        final /* synthetic */ MessagingService a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            this.a.d();
                            this.a.g();
                        }
                    }, 2000);
                    b = true;
                }
                if (action.equals("IntentActions.StopServiceReq")) {
                    c();
                    e();
                } else if (action.equals("com.fgmicrotec.mobile.android.voip.SendMessageReq")) {
                    a(0);
                } else if (action.equals("InternalIntents.UpdateSmsNotification")) {
                    this.d.postDelayed(new Runnable(this) {
                        final /* synthetic */ MessagingService a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            com.mavenir.android.messaging.utils.e.a(this.a).a(false);
                        }
                    }, 1000);
                } else if (!action.equals("InternalIntents.CanceledSmsNotification")) {
                    if (action.equals("InternalIntents.CanceledFailedSmsNotification")) {
                        com.mavenir.android.messaging.utils.e.a((Context) this).c(true);
                    } else if (action.equals("InternalIntents.NavigateToMessagesDialog")) {
                        r0 = intent.getIntExtra("InternalIntents.NavigateToMessagesUnopenedNumExtra", 0);
                        long longExtra = intent.getLongExtra("InternalIntents.NavigateToConversation", -1);
                        int intExtra = intent.getIntExtra("InternalIntents.NavigateToMessagesSameAuthorExtra", 0);
                        Intent intent2;
                        if (r0 > 1 && intExtra == 0) {
                            intent2 = new Intent(this, MainTabActivity.class);
                            intent2.putExtra("MainTabExtras.ExtraSelectedTabIndex", 3);
                            intent2.addFlags(268435456);
                            startActivity(intent2);
                        } else if (longExtra > -1) {
                            intent2 = new Intent(this, ConversationActivity.class);
                            intent2.addFlags(268435456);
                            intent2.putExtra("extra_conversationId", longExtra);
                            startActivity(intent2);
                        }
                    } else if (action.equals("InternalIntents.NavigateToMessageDeliveryReport")) {
                        String str = "";
                        if (intent.hasExtra("InternalIntents.NavigateToConversation")) {
                            str = intent.getStringExtra("InternalIntents.NavigateToConversation");
                            if (str.length() > 0) {
                                Intent intent3 = new Intent(this, ConversationActivity.class);
                                intent3.addFlags(268435456);
                                intent3.putExtra("extra_conversationId", str);
                                startActivity(intent3);
                            }
                        }
                    } else if (action.equals("ActionSendSMSOverWiFi")) {
                        r0 = intent.getIntExtra("EXTRA_ERROR_CODE", 0);
                        r1 = intent.getBooleanExtra("EXTRA_ERROR_TYPE", false);
                        r2 = Uri.parse(intent.getStringExtra("EXTRA_URI"));
                        if (r0 == -1) {
                            q.a("MessagingService", "onStartCommand(): ACTION_SEND_SMS_OVER_WIFI, moving SMS " + r2 + " to sent folder");
                            f.a(this, r2, 2, r0);
                        } else {
                            d a = com.mavenir.android.messaging.provider.d.a((Context) this, ContentUris.parseId(r2));
                            if (a == null || b.c(a.c()) != 2) {
                                a(0);
                            } else if (b.b(a.c()) > 1) {
                                a(r2, r0, r1);
                            } else {
                                a(a);
                            }
                        }
                    } else if (action.equals("MessagingService.ACTION_SEND_SUCCESS")) {
                        r0 = intent.getIntExtra("EXTRA_ERROR_CODE", 0);
                        r1 = intent.getBooleanExtra("EXTRA_ERROR_TYPE", false);
                        r2 = Uri.parse(intent.getStringExtra("EXTRA_URI"));
                        long parseId = ContentUris.parseId(r2);
                        if (g.b && r1 && VERSION.SDK_INT >= 19) {
                            getContentResolver().delete(r2, null, null);
                        } else {
                            q.a("MessagingService", "onStartCommand(): ACTION_SEND_SUCCESS, moving SMS to sent folder");
                            f.a(this, r2, 2, r0);
                        }
                        b.a(parseId);
                        this.k = false;
                        a(0);
                    } else if (action.equals("MessagingService.ACTION_SEND_FAILED")) {
                        a(intent);
                    } else if (action.equals("InternalIntents.ActionUpdateSmsSettings")) {
                        a();
                    } else if (action.equals("MessagingService.ACTION_REFRESH_CACHE")) {
                        b();
                    } else if (action.equals("com.fgmicrotec.mobile.android.voip.SimulateReceiveSmsReq")) {
                        a(intent.getStringExtra("extra_message_string"), intent.getStringExtra("extra_message_sender_uri_string"));
                    } else if (action.equals("com.fgmicrotec.mobile.android.voip.LoginToServerCnf") && FgVoIP.U().at()) {
                        a(0);
                    }
                }
            }
        }
        return 1;
    }

    private void b() {
        com.mavenir.android.messaging.utils.c.b(this);
        com.mavenir.android.messaging.c.c.a((Context) this);
        b.a((Context) this);
    }

    private void c() {
        com.mavenir.android.messaging.utils.c.c(this);
        com.mavenir.android.messaging.c.c.a();
        b.a();
    }

    private void a(Intent intent) {
        int intExtra = intent.getIntExtra("EXTRA_ERROR_CODE", 0);
        boolean booleanExtra = intent.getBooleanExtra("EXTRA_ERROR_TYPE", false);
        String stringExtra = intent.getStringExtra("EXTRA_URI");
        Uri parse = stringExtra != null ? Uri.parse(stringExtra) : null;
        if (com.mavenir.android.messaging.provider.d.a((Context) this, ContentUris.parseId(parse)) != null) {
            a(parse, intExtra, booleanExtra);
        }
        b.a(ContentUris.parseId(parse));
        a(0);
    }

    private void d() {
        d a = com.mavenir.android.messaging.provider.d.a((Context) this, true);
        if (a != null) {
            this.k = true;
            q.a("MessagingService", "sendFirstQueuedMessage(): To: " + a.f());
            q.a("MessagingService", "sendFirstQueuedMessage(): moving SMS " + a.a() + " to outbox folder");
            boolean a2 = f.a(this, a.a(), 4, 0);
            if (q) {
                com.mavenir.android.messaging.utils.f.a().a(this, a.d());
            }
            if (!a2) {
                a(a.a(), 1, true);
                return;
            } else if (FgVoIP.U().e(a.f())) {
                a(a, true);
                return;
            } else if (FgVoIP.U().at()) {
                a(a);
                return;
            } else if (l.a((Context) this).F()) {
                a(a, false);
                return;
            } else {
                q.a("MessagingService", "sendFirstQueuedMessage(): moving SMS " + a.a() + " to queued folder");
                f.a(this, a.a(), 6, 4);
                this.k = false;
                return;
            }
        }
        q.a("MessagingService", "sendFirstQueuedMessage(): No queued messages");
    }

    private void a(long j) {
        this.d.removeCallbacks(this.s);
        this.d.postDelayed(this.s, j);
    }

    private void a(d dVar, boolean z) {
        q.b("MessagingService", "Sending SMS over Cellular network");
        SmsManager smsManager = SmsManager.getDefault();
        if (dVar == null) {
            q.d("MessagingService", "sendMessageOverCS(): message is not initialized");
            return;
        }
        String f = dVar.f();
        String f2 = dVar.f();
        String g = dVar.g();
        com.mavenir.android.messaging.c.a a = com.mavenir.android.messaging.c.a.a(this, dVar.f(), false);
        if (a != null) {
            f = a.d();
            f2 = f;
            f = a.b();
        } else {
            String str = f2;
            f2 = f;
            f = str;
        }
        Intent intent = new Intent("com.mavenir.android.ActionSmsSendingResult");
        intent.putExtra("EXTRA_PHONE_NUMBER", f2);
        intent.putExtra("EXTRA_MESSAGE", g);
        intent.putExtra("EXTRA_DISPLAY_NAME", f);
        intent.putExtra("EXTRA_URI", dVar.a().toString());
        PendingIntent broadcast = PendingIntent.getBroadcast(this, 0, intent, 1073741824);
        intent = new Intent("com.mavenir.android.ActionSmsDeliveryResult");
        intent.putExtra("EXTRA_PHONE_NUMBER", f2);
        intent.putExtra("EXTRA_MESSAGE", g);
        intent.putExtra("EXTRA_DISPLAY_NAME", f);
        intent.putExtra("EXTRA_URI", dVar.a().toString());
        PendingIntent broadcast2 = PendingIntent.getBroadcast(this, 0, intent, 1073741824);
        ArrayList divideMessage = smsManager.divideMessage(g);
        int size = divideMessage.size();
        ArrayList arrayList = new ArrayList(size);
        ArrayList arrayList2 = new ArrayList(size);
        boolean y = dVar.y();
        for (int i = 0; i < size; i++) {
            if (i == size - 1) {
                if (y || FgVoIP.U().e(f2)) {
                    arrayList.add(broadcast2);
                }
                arrayList2.add(broadcast);
            } else {
                arrayList.add(null);
                arrayList2.add(null);
            }
        }
        if (size != 0) {
            try {
                b.d(dVar.c());
                b.a(dVar.c(), 2);
                smsManager.sendMultipartTextMessage(f2, null, divideMessage, arrayList2, arrayList);
                q.b("MessagingService", "sendMessageOverCS(): sending sms '" + dVar.g() + "' to " + f2);
                return;
            } catch (Exception e) {
                q.d("MessagingService", "sendMessageOverCS(): sendMultipartTextMessage(): " + e.getLocalizedMessage());
                a(dVar.a(), 1, true);
                return;
            }
        }
        q.d("MessagingService", "Failed to send sms '" + dVar + "' to " + f2);
    }

    private void a(d dVar) {
        q.b("MessagingService", "Sending SMS over WiFi network");
        if (dVar == null || TextUtils.isEmpty(dVar.g())) {
            q.d("MessagingService", "sendMessageOverWiFi(): message is not initialized");
        } else if (TextUtils.isEmpty(dVar.f())) {
            q.d("MessagingService", "sendMessageOverWiFi(): recipient is not initialized");
        } else if (w.c() == 1) {
            c(dVar);
        } else {
            b(dVar);
        }
    }

    private void b(d dVar) {
        this.p.clear();
        this.l = dVar.c() > 0 ? (int) dVar.c() : this.m;
        String str = "text/plain;charset=utf-8";
        int length = dVar.g().length();
        String b = com.mavenir.android.settings.c.c.b();
        String[] strArr = new String[1];
        String[] strArr2 = new String[1];
        try {
            com.mavenir.android.messaging.c.a a = com.mavenir.android.messaging.c.a.a(this, dVar.f(), false);
            if (a != null) {
                strArr[0] = t.f.a(a.d(), b);
                if (a.b() == null || a.b().length() <= 0) {
                    strArr2[0] = t.f.d(dVar.f());
                } else {
                    strArr2[0] = a.b();
                }
            } else {
                strArr[0] = t.f.a(dVar.f(), b);
                strArr2[0] = t.f.d(dVar.f());
            }
            b.d(dVar.c());
            b.a(dVar.c(), 1);
            this.p.add(strArr[0]);
            this.e.sipMessageSendReq(this.l, length, dVar.g(), str, 1, strArr, strArr2);
        } catch (Exception e) {
            q.d("MessagingService", "sendSipMessage(): " + e);
        }
    }

    private void c(d dVar) {
        this.o.clear();
        this.m = dVar.c() > 0 ? (int) dVar.c() : this.m;
        int length = dVar.g().length();
        String[] strArr = new String[]{dVar.f()};
        b.d(dVar.c());
        b.a(dVar.c(), 1);
        boolean y = dVar.y();
        boolean[] zArr = new boolean[strArr.length];
        String b = w.b();
        String[] strArr2 = new String[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            zArr[i] = y;
            strArr2[i] = b;
        }
        this.e.sipSmsSendReq((int) dVar.c(), length, dVar.g(), strArr.length, zArr, strArr, strArr2);
    }

    private void a(Uri uri, int i, boolean z) {
        q.d("MessagingService", "failedToSendMessage(): uri: " + uri + " error: " + i);
        q.a("MessagingService", "failedToSendMessage(): moving SMS " + uri + " to failed folder");
        f.a(this, uri, 5, i);
        com.mavenir.android.messaging.utils.e.a((Context) this).c(true);
        b.a(ContentUris.parseId(uri));
        this.k = false;
        if (z) {
            String str;
            if (i == 4) {
                str = "Native SMS - no service";
            } else if (i == 3) {
                str = "Native SMS - null PDU";
            } else if (i == 2) {
                str = "Native SMS - radio off";
            } else {
                str = "Native SMS - generic error";
            }
            com.mavenir.android.applog.a.a((Context) this).a(com.mavenir.android.applog.AppLogAdapter.b.FGAPPLOG_EVENT_GROUP_MO_SMS_ERROR, AppLogAdapter.d.FGAPPLOG_EVENT_TYPE_SUBMIT_ERROR, com.mavenir.android.applog.AppLogAdapter.c.FGAPPLOG_EVENT_REASON_NONE, str);
        }
    }

    private void e() {
        c = true;
        a = SystemClock.elapsedRealtime();
        if (FgVoIP.U().am() && this.e != null) {
            this.e.exit();
        }
        stopSelf();
    }

    public void a() {
        this.d.postDelayed(new Runnable(this) {
            final /* synthetic */ MessagingService a;

            {
                this.a = r1;
            }

            public void run() {
                if (this.a.e == null) {
                    this.a.a();
                    return;
                }
                String e = p.e();
                String b = w.b();
                int d = w.d();
                int e2 = w.e();
                int f = w.f();
                int g = w.g();
                q.a("MessagingService", "setSmsSettingsReq(): displayName: " + e + ", smsc: " + b + ", refNum: " + d + ", validity: " + e2 + ", encNatNum: " + f + ", encIntlNum: " + g);
                this.a.e.setSmsSettingsReq(e, b, d, true, e2, f, g);
            }
        }, 1000);
    }

    public void a(final int i, int i2) {
        if (this.l == i2) {
            final Uri withAppendedId = ContentUris.withAppendedId(f.d.a, (long) i2);
            q.a("MessagingService", "sipMessageSendCnf(): uri: " + withAppendedId + " error: " + i);
            if (i == 0) {
                this.d.post(new Runnable(this) {
                    final /* synthetic */ MessagingService c;

                    public void run() {
                        Intent intent = new Intent();
                        intent.setAction("MessagingService.ACTION_SEND_SUCCESS");
                        intent.putExtra("EXTRA_ERROR_CODE", i);
                        intent.putExtra("EXTRA_URI", withAppendedId.toString());
                        this.c.sendBroadcast(intent);
                    }
                });
            } else {
                this.d.post(new Runnable(this) {
                    final /* synthetic */ MessagingService c;

                    public void run() {
                        Intent intent = new Intent();
                        intent.setAction("MessagingService.ACTION_SEND_FAILED");
                        intent.putExtra("EXTRA_ERROR_CODE", i);
                        intent.putExtra("EXTRA_URI", withAppendedId.toString());
                        this.c.sendBroadcast(intent);
                    }
                });
            }
            Intent intent = new Intent();
            intent.setAction("com.fgmicrotec.mobile.android.voip.SendMessageSipSmsCnf");
            intent.putExtra("EXTRA_ERROR_CODE", i);
            intent.putExtra("extra_unique_message_id", this.l);
            sendBroadcast(intent);
        }
    }

    public void a(final int i, String str, String str2) {
        q.a("MessagingService", "sipMessageSendResp(): uri: " + str + ", error: " + i);
        final Uri withAppendedId = ContentUris.withAppendedId(f.d.a, (long) this.l);
        if (i != 0) {
            this.d.post(new Runnable(this) {
                final /* synthetic */ MessagingService c;

                public void run() {
                    Intent intent = new Intent();
                    intent.setAction("MessagingService.ACTION_SEND_FAILED");
                    intent.putExtra("EXTRA_ERROR_CODE", i);
                    intent.putExtra("EXTRA_URI", withAppendedId.toString());
                    this.c.sendBroadcast(intent);
                }
            });
        } else if (this.p.contains(str)) {
            this.p.remove(str);
            d a = com.mavenir.android.messaging.provider.d.a((Context) this, withAppendedId);
            if (a != null && a.o() == 64) {
                com.mavenir.android.messaging.provider.d.a((Context) this, (long) this.l, 0);
            }
            if (this.p.size() == 0) {
                Intent intent = new Intent();
                intent.setAction("com.fgmicrotec.mobile.android.voip.SendMessageSipSmsResp");
                intent.putExtra("EXTRA_ERROR_CODE", i);
                intent.putExtra("extra_unique_message_id", this.l);
                sendBroadcast(intent);
            }
        }
    }

    public void a(int i, int i2, int i3, int[] iArr, String[] strArr, int[] iArr2, int[] iArr3) {
        Uri withAppendedId = ContentUris.withAppendedId(f.d.a, (long) i);
        q.a("MessagingService", "sipSmsSendCnf(): uri: " + withAppendedId + " error: " + i2);
        if (i2 == 0) {
            this.n.put(Integer.valueOf(iArr3[0]), Long.valueOf((long) i));
            w.b(iArr3[0]);
        } else {
            a(withAppendedId, i2, false);
        }
        Intent intent = new Intent();
        intent.setAction("com.fgmicrotec.mobile.android.voip.SendMessageLegacySmsCnf");
        intent.putExtra("EXTRA_ERROR_CODE", i2);
        intent.putExtra("extra_unique_message_id", i);
        sendBroadcast(intent);
    }

    public void a(int i, int i2, String str, String str2, String str3, String str4) {
        q.a("MessagingService", "sipMessageReceivedInd(): " + str + ", " + str3);
        String f = t.f.f(str3);
        long j = (long) i;
        d dVar = new d(this);
        dVar.a("sms");
        dVar.d(j);
        dVar.a(1);
        dVar.a(false);
        dVar.b(false);
        dVar.c(str);
        dVar.b(f);
        dVar.e(System.currentTimeMillis());
        d a = com.mavenir.android.messaging.provider.d.a((Context) this, com.mavenir.android.messaging.provider.d.b((Context) this, dVar));
        com.mavenir.android.messaging.utils.b.b(a.d());
        if (q) {
            com.mavenir.android.messaging.utils.f.a().a(this, a.d());
        }
        f();
    }

    public void a(int i, final int i2, final int i3, String str) {
        final Long l = (Long) this.n.get(Integer.valueOf(i3));
        if (i == 0) {
            if (l != null) {
                q.a("MessagingService", "sipSmsStatusInd(" + i2 + "): statusType: SMS submit: msgId: " + l + ", nRef: " + i3 + ", strAddress: " + str);
                this.d.post(new Runnable(this) {
                    final /* synthetic */ MessagingService d;

                    public void run() {
                        Uri withAppendedId = ContentUris.withAppendedId(f.a, l.longValue());
                        Intent intent = new Intent();
                        if (i2 == 0) {
                            intent.setAction("MessagingService.ACTION_SEND_SUCCESS");
                        } else {
                            intent.setAction("MessagingService.ACTION_SEND_FAILED");
                        }
                        intent.putExtra("EXTRA_ERROR_CODE", i2);
                        intent.putExtra("EXTRA_URI", withAppendedId.toString());
                        this.d.sendBroadcast(intent);
                        if (!k.l()) {
                            this.d.n.remove(Integer.valueOf(i3));
                        }
                    }
                });
                return;
            }
            q.d("MessagingService", "sipSmsStatusInd(" + i2 + "): statusType: SMS submit, message ID: " + l + " not found. Ref: " + i3);
        } else if (l != null) {
            q.a("MessagingService", "sipSmsStatusInd(" + i2 + "): statusType: SMS delivery report, msgId: " + l + ", nRef: " + i3 + ", strAddress" + str);
            if (i2 == 0) {
                com.mavenir.android.messaging.provider.d.a((Context) this, l.longValue(), 0);
                b(l.longValue());
            } else {
                Uri withAppendedId = ContentUris.withAppendedId(f.a, l.longValue());
                Intent intent = new Intent();
                intent.setAction("MessagingService.ACTION_SEND_FAILED");
                intent.putExtra("EXTRA_ERROR_CODE", i2);
                intent.putExtra("EXTRA_URI", withAppendedId.toString());
                sendBroadcast(intent);
            }
            this.n.remove(Integer.valueOf(i3));
        } else {
            q.d("MessagingService", "sipSmsStatusInd(" + i2 + "): statusType: SMS delivery report, message ID: " + l + " not found. Ref: " + i3);
        }
    }

    public void a(int i, int i2, String str, String str2) {
        q.a("MessagingService", "sipSmsReceivedInd(): nIndicationType: " + i + ", nLength: " + i2 + ", pMsg: " + str + ", address: " + str2 + ", date: " + t.d.a(System.currentTimeMillis()));
        d dVar = new d(this);
        dVar.a("sms");
        dVar.d(-1);
        dVar.a(1);
        dVar.a(false);
        dVar.b(false);
        dVar.c(str);
        dVar.b(str2);
        dVar.e(System.currentTimeMillis());
        d a = com.mavenir.android.messaging.provider.d.a((Context) this, com.mavenir.android.messaging.provider.d.b((Context) this, dVar));
        if (a == null) {
            q.d("MessagingService", "sipSmsReceivedInd(): cannot retrieve message from DB");
            return;
        }
        com.mavenir.android.messaging.utils.b.b(a.d());
        if (q) {
            com.mavenir.android.messaging.utils.f.a().a(this, a.d());
        }
        f();
    }

    public IBinder onBind(Intent intent) {
        return this.j;
    }

    private void f() {
        if (k.p()) {
            Intent intent = new Intent();
            intent.setAction("com.mavenir.action.UPDATE_MAIN_TAB");
            sendBroadcast(intent);
            this.d.postDelayed(new Runnable(this) {
                final /* synthetic */ MessagingService a;

                {
                    this.a = r1;
                }

                public void run() {
                    com.mavenir.android.messaging.utils.e.a(this.a).b(true);
                }
            }, 100);
        }
    }

    private void b(long j) {
        com.mavenir.android.messaging.utils.e.a((Context) this).a(j);
    }

    private void g() {
        if (this.i == null) {
            q.a("MessagingService", "Starting phone service state listener");
            this.i = new c();
            ((TelephonyManager) getSystemService("phone")).listen(this.i, 1);
        }
    }

    private void h() {
        if (this.i != null) {
            q.a("MessagingService", "Stopping phone service state listener");
            ((TelephonyManager) getSystemService("phone")).listen(this.i, 0);
            this.i = null;
        }
    }

    private void a(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            q.d("MessagingService", "simulateSendSipSms(): Empty message or recipient");
        } else {
            a(1, str.length(), str, str2);
        }
    }
}
