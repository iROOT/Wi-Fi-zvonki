package com.mavenir.android.messaging.provider;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.telephony.PhoneNumberUtils;
import com.mavenir.android.common.q;
import com.mavenir.android.messaging.provider.g.d.b;
import com.mavenir.android.messaging.provider.g.f;
import com.mavenir.android.messaging.provider.g.f.a;
import com.mavenir.android.messaging.provider.g.f.c;
import com.mavenir.android.messaging.provider.g.g;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class d {
    private static final String[] a = new String[]{"_id", "thread_id", "address", "body", "status", "date"};

    public static void a(android.content.Context r7, long r8, int r10) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find block by offset: 0x005b in list [B:6:0x0052]
	at jadx.core.utils.BlockUtils.getBlockByOffset(BlockUtils.java:43)
	at jadx.core.dex.instructions.IfNode.initBlocks(IfNode.java:60)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.initBlocksInIfNodes(BlockFinish.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.visit(BlockFinish.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:60)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
        /*
        r4 = 0;
        r3 = 0;
        r0 = com.mavenir.android.messaging.provider.g.f.a;
        r1 = android.content.ContentUris.withAppendedId(r0, r8);
        r0 = 1;
        r2 = new java.lang.String[r0];
        r0 = "_id";
        r2[r4] = r0;
        r0 = r7.getContentResolver();
        r4 = r3;
        r5 = r3;
        r2 = r0.query(r1, r2, r3, r4, r5);
        r0 = r2.moveToFirst();	 Catch:{ all -> 0x0075 }
        if (r0 == 0) goto L_0x005c;	 Catch:{ all -> 0x0075 }
    L_0x001f:
        r0 = 0;	 Catch:{ all -> 0x0075 }
        r0 = r2.getInt(r0);	 Catch:{ all -> 0x0075 }
        r1 = com.mavenir.android.messaging.provider.g.f.d;	 Catch:{ all -> 0x0075 }
        r4 = (long) r0;	 Catch:{ all -> 0x0075 }
        r0 = android.content.ContentUris.withAppendedId(r1, r4);	 Catch:{ all -> 0x0075 }
        r1 = new android.content.ContentValues;	 Catch:{ all -> 0x0075 }
        r3 = 2;	 Catch:{ all -> 0x0075 }
        r1.<init>(r3);	 Catch:{ all -> 0x0075 }
        r3 = "status";	 Catch:{ all -> 0x0075 }
        r4 = java.lang.Integer.valueOf(r10);	 Catch:{ all -> 0x0075 }
        r1.put(r3, r4);	 Catch:{ all -> 0x0075 }
        r3 = "date_sent";	 Catch:{ all -> 0x0075 }
        r4 = java.lang.System.currentTimeMillis();	 Catch:{ all -> 0x0075 }
        r4 = java.lang.Long.valueOf(r4);	 Catch:{ all -> 0x0075 }
        r1.put(r3, r4);	 Catch:{ all -> 0x0075 }
        r3 = r7.getContentResolver();	 Catch:{ all -> 0x0075 }
        r4 = 0;	 Catch:{ all -> 0x0075 }
        r5 = 0;	 Catch:{ all -> 0x0075 }
        r3.update(r0, r1, r4, r5);	 Catch:{ all -> 0x0075 }
    L_0x0050:
        if (r2 == 0) goto L_0x005b;
    L_0x0052:
        r0 = r2.isClosed();
        if (r0 != 0) goto L_0x005b;
    L_0x0058:
        r2.close();
    L_0x005b:
        return;
    L_0x005c:
        r0 = "MessagesNativeInterface";	 Catch:{ all -> 0x0075 }
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0075 }
        r3.<init>();	 Catch:{ all -> 0x0075 }
        r4 = "updateMessageStatus(): Can't find message for status update: ";	 Catch:{ all -> 0x0075 }
        r3 = r3.append(r4);	 Catch:{ all -> 0x0075 }
        r1 = r3.append(r1);	 Catch:{ all -> 0x0075 }
        r1 = r1.toString();	 Catch:{ all -> 0x0075 }
        com.mavenir.android.common.q.d(r0, r1);	 Catch:{ all -> 0x0075 }
        goto L_0x0050;
    L_0x0075:
        r0 = move-exception;
        if (r2 == 0) goto L_0x0081;
    L_0x0078:
        r1 = r2.isClosed();
        if (r1 != 0) goto L_0x0081;
    L_0x007e:
        r2.close();
    L_0x0081:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mavenir.android.messaging.provider.d.a(android.content.Context, long, int):void");
    }

    public static boolean a(Context context) {
        try {
            f.a(context).close();
            f.a(context);
            boolean deleteDatabase = context.deleteDatabase("mmssms.db");
            if (deleteDatabase) {
                q.b("MessagesNativeInterface", "deleteDatabase(): database deleted");
                return deleteDatabase;
            }
            q.d("MessagesNativeInterface", "deleteDatabase(): failed to delete database");
            return deleteDatabase;
        } catch (Exception e) {
            q.d("MessagesNativeInterface", "deleteDatabase(): cannot delete database - " + e);
            return false;
        }
    }

    public static List<com.mavenir.android.messaging.c.d> b(Context context) {
        Exception e;
        Throwable th;
        List<com.mavenir.android.messaging.c.d> arrayList = new ArrayList();
        Cursor query;
        try {
            query = context.getContentResolver().query(c.a, f.e, "type=1 AND read=0", null, "date DESC");
            while (query.moveToNext()) {
                try {
                    arrayList.add(new com.mavenir.android.messaging.c.d(context, query, "sms"));
                } catch (Exception e2) {
                    e = e2;
                }
            }
            if (!(query == null || query.isClosed())) {
                query.close();
            }
        } catch (Exception e3) {
            e = e3;
            query = null;
            try {
                q.c("MessagesNativeInterface", "getUnreadSmsMessages(): " + e.getLocalizedMessage(), e.getCause());
                if (!(query == null || query.isClosed())) {
                    query.close();
                }
                return arrayList;
            } catch (Throwable th2) {
                th = th2;
                query.close();
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            query = null;
            if (!(query == null || query.isClosed())) {
                query.close();
            }
            throw th;
        }
        return arrayList;
    }

    public static com.mavenir.android.messaging.c.d a(Context context, long j) {
        return a(context, ContentUris.withAppendedId(f.a, j));
    }

    public static List<com.mavenir.android.messaging.c.d> a(Context context, boolean z, long j, long j2, String str) {
        Object e;
        Throwable th;
        List<com.mavenir.android.messaging.c.d> arrayList = new ArrayList();
        Cursor cursor = null;
        Cursor query;
        try {
            Uri uri = f.a;
            String str2 = "thread_id=? AND date=? AND body=?";
            String[] strArr = new String[]{String.valueOf(j), String.valueOf(j2), str};
            if (z) {
                uri = f.b;
                str2 = "thread_id=? AND body=? AND date BETWEEN ? AND ?";
                strArr = new String[]{String.valueOf(j), str, String.valueOf(j2), String.valueOf(10000 + j2)};
            }
            query = context.getContentResolver().query(uri, f.e, str2, strArr, null);
            if (query != null) {
                while (query.moveToNext()) {
                    try {
                        arrayList.add(new com.mavenir.android.messaging.c.d(context, query, "sms"));
                    } catch (Exception e2) {
                        e = e2;
                    }
                }
            }
            if (query != null) {
                query.close();
            }
        } catch (Exception e3) {
            e = e3;
            query = null;
            try {
                q.d("MessagesNativeInterface", "getMultiRecipientSms(): " + e);
                if (query != null) {
                    query.close();
                }
                return arrayList;
            } catch (Throwable th2) {
                th = th2;
                cursor = query;
                if (cursor != null) {
                    cursor.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
        return arrayList;
    }

    public static com.mavenir.android.messaging.c.d b(Context context, long j) {
        return a(context, Uri.parse("content://sms/" + j));
    }

    public static com.mavenir.android.messaging.c.d a(Context context, Uri uri) {
        Exception e;
        Throwable th;
        Cursor query;
        try {
            query = context.getContentResolver().query(uri, f.e, null, null, null);
            if (query != null) {
                try {
                    if (query.moveToFirst()) {
                        com.mavenir.android.messaging.c.d dVar = new com.mavenir.android.messaging.c.d(context, query, "sms");
                        if (query == null || query.isClosed()) {
                            return dVar;
                        }
                        query.close();
                        return dVar;
                    }
                } catch (Exception e2) {
                    e = e2;
                    try {
                        q.c("MessagesNativeInterface", "getSmsByUri(): " + e.getLocalizedMessage(), e.getCause());
                        if (!(query == null || query.isClosed())) {
                            query.close();
                        }
                        return null;
                    } catch (Throwable th2) {
                        th = th2;
                        query.close();
                        throw th;
                    }
                }
            }
            q.c("MessagesNativeInterface", "getSmsByUri(): no message found with URI: " + uri.toString());
            if (!(query == null || query.isClosed())) {
                query.close();
            }
        } catch (Exception e3) {
            e = e3;
            query = null;
            q.c("MessagesNativeInterface", "getSmsByUri(): " + e.getLocalizedMessage(), e.getCause());
            query.close();
            return null;
        } catch (Throwable th3) {
            th = th3;
            query = null;
            if (!(query == null || query.isClosed())) {
                query.close();
            }
            throw th;
        }
        return null;
    }

    public static com.mavenir.android.messaging.c.d c(Context context, long j) {
        return b(context, ContentUris.withAppendedId(com.mavenir.android.messaging.provider.g.d.a, j));
    }

    public static com.mavenir.android.messaging.c.d b(Context context, Uri uri) {
        Exception e;
        Throwable th;
        Cursor query;
        try {
            query = context.getContentResolver().query(uri, com.mavenir.android.messaging.provider.g.d.d, null, null, null);
            if (query != null) {
                try {
                    if (query.moveToFirst()) {
                        com.mavenir.android.messaging.c.d dVar = new com.mavenir.android.messaging.c.d(context, query, "mms");
                        if (query == null || query.isClosed()) {
                            return dVar;
                        }
                        query.close();
                        return dVar;
                    }
                } catch (Exception e2) {
                    e = e2;
                    try {
                        q.c("MessagesNativeInterface", "getMmsById(): " + e.getLocalizedMessage(), e.getCause());
                        query.close();
                        return null;
                    } catch (Throwable th2) {
                        th = th2;
                        if (!(query == null || query.isClosed())) {
                            query.close();
                        }
                        throw th;
                    }
                }
            }
            if (!(query == null || query.isClosed())) {
                query.close();
            }
        } catch (Exception e3) {
            e = e3;
            query = null;
            q.c("MessagesNativeInterface", "getMmsById(): " + e.getLocalizedMessage(), e.getCause());
            if (!(query == null || query.isClosed())) {
                query.close();
            }
            return null;
        } catch (Throwable th3) {
            th = th3;
            query = null;
            query.close();
            throw th;
        }
        return null;
    }

    public static void a(final Context context, com.mavenir.android.messaging.c.d dVar, boolean z) {
        Uri uri;
        if ("sms".equals(dVar.b())) {
            uri = f.a;
        } else {
            uri = com.mavenir.android.messaging.provider.g.d.a;
        }
        final Uri withAppendedId = ContentUris.withAppendedId(uri, dVar.c());
        final ContentValues contentValues = new ContentValues(1);
        contentValues.put("locked", Integer.valueOf(z ? 1 : 0));
        new Thread(new Runnable() {
            public void run() {
                try {
                    context.getContentResolver().update(withAppendedId, contentValues, null, null);
                } catch (Exception e) {
                    q.c("MessagesNativeInterface", "lockMessage(): " + e.getLocalizedMessage(), e.getCause());
                }
            }
        }, "MessagesFragment.lockMessage").start();
    }

    public static void a(final Context context, final long j, final boolean z) {
        new Thread(new Runnable() {
            public void run() {
                String str = null;
                try {
                    Uri withAppendedId = ContentUris.withAppendedId(g.b, j);
                    if (!z) {
                        str = "locked=0";
                    }
                    context.getContentResolver().delete(withAppendedId, str, null);
                } catch (Exception e) {
                    q.c("MessagesNativeInterface", "deleteConversation(): " + e.getLocalizedMessage(), e.getCause());
                }
            }
        }, "ConversationListFragment.deleteConversation").start();
    }

    public static void a(Context context, com.mavenir.android.messaging.c.d dVar) {
        try {
            int i = 0;
            for (com.mavenir.android.messaging.c.d a : a(context, false, dVar.d(), dVar.h(), dVar.g())) {
                i = context.getContentResolver().delete(a.a(), null, null) + i;
            }
            q.a("MessagesNativeInterface", "deleteMessage(): Deleted " + i + " messages");
        } catch (Exception e) {
            q.c("MessagesNativeInterface", "deleteMessage(): " + e.getLocalizedMessage(), e.getCause());
        }
    }

    public static int d(Context context, long j) {
        try {
            return context.getContentResolver().delete(ContentUris.withAppendedId(a.a, j), "type=3", null);
        } catch (Exception e) {
            q.c("MessagesNativeInterface", "deleteDraftSmsMessage(): " + e.getLocalizedMessage(), e.getCause());
            return 0;
        }
    }

    public static void e(Context context, long j) {
        try {
            context.getContentResolver().delete(com.mavenir.android.messaging.provider.g.d.a.a, "thread_id" + (j > 0 ? " = " + j : " IS NULL"), null);
        } catch (Exception e) {
            q.c("MessagesNativeInterface", "deleteDraftMmsMessage(): " + e.getLocalizedMessage(), e.getCause());
        }
    }

    public static String f(Context context, long j) {
        Exception e;
        Throwable th;
        Cursor query;
        try {
            query = context.getContentResolver().query(ContentUris.withAppendedId(a.a, j), f.e, "type=3", null, null);
            if (query != null) {
                try {
                    if (query.moveToFirst()) {
                        String string = query.getString(query.getColumnIndex("body"));
                        if (query == null || query.isClosed()) {
                            return string;
                        }
                        query.close();
                        return string;
                    }
                } catch (Exception e2) {
                    e = e2;
                    try {
                        q.c("MessagesNativeInterface", "getDraftSmsMessage(): " + e.getLocalizedMessage(), e.getCause());
                        if (!(query == null || query.isClosed())) {
                            query.close();
                        }
                        return null;
                    } catch (Throwable th2) {
                        th = th2;
                        query.close();
                        throw th;
                    }
                }
            }
            if (!(query == null || query.isClosed())) {
                query.close();
            }
        } catch (Exception e3) {
            e = e3;
            query = null;
            q.c("MessagesNativeInterface", "getDraftSmsMessage(): " + e.getLocalizedMessage(), e.getCause());
            query.close();
            return null;
        } catch (Throwable th3) {
            th = th3;
            query = null;
            if (!(query == null || query.isClosed())) {
                query.close();
            }
            throw th;
        }
        return null;
    }

    public static boolean a(Context context, long j, String str) {
        if (j <= 0) {
            return false;
        }
        try {
            ContentValues contentValues = new ContentValues(3);
            contentValues.put("thread_id", Long.valueOf(j));
            contentValues.put("body", str);
            contentValues.put("type", Integer.valueOf(3));
            context.getContentResolver().insert(f.a, contentValues);
            return true;
        } catch (Exception e) {
            q.c("MessagesNativeInterface", "updateDraftSmsMessage(): " + e.getLocalizedMessage(), e.getCause());
            return false;
        }
    }

    public static Uri b(Context context, com.mavenir.android.messaging.c.d dVar) {
        switch (dVar.m()) {
            case 1:
                return e(context, dVar);
            case 2:
                return f(context, dVar);
            case 4:
                return d(context, dVar);
            case 6:
                return c(context, dVar);
            default:
                return null;
        }
    }

    public static Uri c(Context context, com.mavenir.android.messaging.c.d dVar) {
        Uri uri = null;
        String[] split = dVar.f().split(":");
        if (split != null) {
            int length = split.length;
            int i = 0;
            while (i < length) {
                Uri a;
                try {
                    String extractNetworkPortion = PhoneNumberUtils.extractNetworkPortion(split[i]);
                    if (dVar.b().equals("sms")) {
                        a = f.a(context.getContentResolver(), f.c, extractNetworkPortion, dVar.g(), dVar.p(), Long.valueOf(dVar.h()), true, dVar.y(), dVar.d());
                        i++;
                        uri = a;
                    }
                } catch (Exception e) {
                    q.c("MessagesNativeInterface", "queueMessage(): " + e.getLocalizedMessage(), e.getCause());
                }
                a = uri;
                i++;
                uri = a;
            }
        }
        return uri;
    }

    private static Uri e(Context context, com.mavenir.android.messaging.c.d dVar) {
        Uri a;
        try {
            if (dVar.b().equals("sms")) {
                return c.a(context.getContentResolver(), dVar.f(), dVar.g(), dVar.p(), Long.valueOf(dVar.h()), dVar.l());
            }
            a = b.a(context.getContentResolver(), dVar.f(), dVar.g(), dVar.p(), Long.valueOf(dVar.h()), dVar.l(), dVar.d(), dVar.k(), dVar.j());
            try {
                dVar.d(a.getLastPathSegment().trim());
                return a;
            } catch (Exception e) {
                q.d("MessagesNativeInterface", "saveMessageToInbox(): Can't find message for status update: " + a);
                return a;
            }
        } catch (Exception e2) {
            a = null;
            q.d("MessagesNativeInterface", "saveMessageToInbox(): Can't find message for status update: " + a);
            return a;
        }
    }

    private static Uri f(Context context, com.mavenir.android.messaging.c.d dVar) {
        Uri a;
        try {
            if (dVar.b().equals("sms")) {
                return null;
            }
            a = com.mavenir.android.messaging.provider.g.d.d.a(context.getContentResolver(), dVar.f(), dVar.g(), dVar.p(), Long.valueOf(dVar.h()), dVar.l(), dVar.d(), dVar.k(), dVar.j());
            try {
                dVar.d(a.getLastPathSegment().trim());
                return a;
            } catch (Exception e) {
                q.d("MessagesNativeInterface", "saveMessageToInbox(): Can't find message for status update: " + a);
                return a;
            }
        } catch (Exception e2) {
            a = null;
            q.d("MessagesNativeInterface", "saveMessageToInbox(): Can't find message for status update: " + a);
            return a;
        }
    }

    public static Uri d(Context context, com.mavenir.android.messaging.c.d dVar) {
        Uri uri = null;
        String[] split = dVar.f().split(" ");
        Uri a;
        if (split.length > 1) {
            ArrayList arrayList = new ArrayList(split.length);
            int length = split.length;
            int i = 0;
            while (i < length) {
                String str = split[i];
                if (dVar.b().equals("sms")) {
                    a = com.mavenir.android.messaging.provider.g.f.d.a(context.getContentResolver(), str, dVar.g(), dVar.p(), Long.valueOf(dVar.h()), false, dVar.d());
                } else {
                    a = com.mavenir.android.messaging.provider.g.d.c.a(context.getContentResolver(), str, dVar.g(), dVar.p(), Long.valueOf(dVar.h()), false, dVar.d(), dVar.k(), dVar.j());
                    dVar.d(a.getLastPathSegment().trim());
                }
                arrayList.add(a);
                i++;
                uri = a;
            }
            Object obj = 1;
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                Object obj2;
                Uri uri2 = (Uri) it.next();
                ContentValues contentValues = new ContentValues();
                contentValues.put("group_id", Long.valueOf(ContentUris.parseId((Uri) arrayList.get(0))));
                if (obj != null) {
                    contentValues.put("group_type", Integer.valueOf(dVar.m()));
                    obj2 = null;
                } else {
                    obj2 = obj;
                }
                context.getContentResolver().update(uri2, contentValues, null, null);
                obj = obj2;
            }
            return uri;
        } else if (dVar.b().equals("sms")) {
            return com.mavenir.android.messaging.provider.g.f.d.a(context.getContentResolver(), dVar.f(), dVar.g(), dVar.p(), Long.valueOf(dVar.h()), false, dVar.d());
        } else {
            a = com.mavenir.android.messaging.provider.g.d.c.a(context.getContentResolver(), dVar.f(), dVar.g(), dVar.p(), Long.valueOf(dVar.h()), false, dVar.d(), dVar.k(), dVar.j());
            dVar.d(a.getLastPathSegment().trim());
            return a;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.mavenir.android.messaging.c.d a(android.content.Context r8, boolean r9) {
        /*
        r6 = 0;
        r1 = com.mavenir.android.messaging.provider.g.f.c;
        r0 = r8.getContentResolver();
        r2 = a;	 Catch:{ Exception -> 0x00ac, all -> 0x00db }
        r3 = 0;
        r4 = 0;
        r5 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00ac, all -> 0x00db }
        r5.<init>();	 Catch:{ Exception -> 0x00ac, all -> 0x00db }
        r7 = "date ";
        r7 = r5.append(r7);	 Catch:{ Exception -> 0x00ac, all -> 0x00db }
        if (r9 == 0) goto L_0x0099;
    L_0x0018:
        r5 = "ASC";
    L_0x001a:
        r5 = r7.append(r5);	 Catch:{ Exception -> 0x00ac, all -> 0x00db }
        r5 = r5.toString();	 Catch:{ Exception -> 0x00ac, all -> 0x00db }
        r2 = r0.query(r1, r2, r3, r4, r5);	 Catch:{ Exception -> 0x00ac, all -> 0x00db }
        if (r2 == 0) goto L_0x009f;
    L_0x0028:
        r0 = r2.moveToFirst();	 Catch:{ Exception -> 0x00ee, all -> 0x00e9 }
        if (r0 == 0) goto L_0x009f;
    L_0x002e:
        r0 = new com.mavenir.android.messaging.c.d;	 Catch:{ Exception -> 0x00ee, all -> 0x00e9 }
        r0.<init>(r8);	 Catch:{ Exception -> 0x00ee, all -> 0x00e9 }
        r1 = "_id";
        r1 = r2.getColumnIndex(r1);	 Catch:{ Exception -> 0x00f3, all -> 0x00e9 }
        r4 = r2.getLong(r1);	 Catch:{ Exception -> 0x00f3, all -> 0x00e9 }
        r0.c(r4);	 Catch:{ Exception -> 0x00f3, all -> 0x00e9 }
        r1 = "thread_id";
        r1 = r2.getColumnIndex(r1);	 Catch:{ Exception -> 0x00f3, all -> 0x00e9 }
        r4 = r2.getLong(r1);	 Catch:{ Exception -> 0x00f3, all -> 0x00e9 }
        r0.d(r4);	 Catch:{ Exception -> 0x00f3, all -> 0x00e9 }
        r1 = "address";
        r1 = r2.getColumnIndex(r1);	 Catch:{ Exception -> 0x00f3, all -> 0x00e9 }
        r1 = r2.getString(r1);	 Catch:{ Exception -> 0x00f3, all -> 0x00e9 }
        r0.b(r1);	 Catch:{ Exception -> 0x00f3, all -> 0x00e9 }
        r1 = "body";
        r1 = r2.getColumnIndex(r1);	 Catch:{ Exception -> 0x00f3, all -> 0x00e9 }
        r1 = r2.getString(r1);	 Catch:{ Exception -> 0x00f3, all -> 0x00e9 }
        r0.c(r1);	 Catch:{ Exception -> 0x00f3, all -> 0x00e9 }
        r1 = "status";
        r1 = r2.getColumnIndex(r1);	 Catch:{ Exception -> 0x00f3, all -> 0x00e9 }
        r1 = r2.getInt(r1);	 Catch:{ Exception -> 0x00f3, all -> 0x00e9 }
        r0.b(r1);	 Catch:{ Exception -> 0x00f3, all -> 0x00e9 }
        r1 = "date";
        r1 = r2.getColumnIndex(r1);	 Catch:{ Exception -> 0x00f3, all -> 0x00e9 }
        r4 = r2.getLong(r1);	 Catch:{ Exception -> 0x00f3, all -> 0x00e9 }
        r0.e(r4);	 Catch:{ Exception -> 0x00f3, all -> 0x00e9 }
        r1 = r0.o();	 Catch:{ Exception -> 0x00f3, all -> 0x00e9 }
        r3 = 64;
        if (r1 != r3) goto L_0x009d;
    L_0x0089:
        r1 = 1;
    L_0x008a:
        r0.c(r1);	 Catch:{ Exception -> 0x00f3, all -> 0x00e9 }
        if (r2 == 0) goto L_0x0098;
    L_0x008f:
        r1 = r2.isClosed();
        if (r1 != 0) goto L_0x0098;
    L_0x0095:
        r2.close();
    L_0x0098:
        return r0;
    L_0x0099:
        r5 = "DESC";
        goto L_0x001a;
    L_0x009d:
        r1 = 0;
        goto L_0x008a;
    L_0x009f:
        if (r2 == 0) goto L_0x00f6;
    L_0x00a1:
        r0 = r2.isClosed();
        if (r0 != 0) goto L_0x00f6;
    L_0x00a7:
        r2.close();
        r0 = r6;
        goto L_0x0098;
    L_0x00ac:
        r0 = move-exception;
        r1 = r0;
        r0 = r6;
    L_0x00af:
        r2 = "MessagesNativeInterface";
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00eb }
        r3.<init>();	 Catch:{ all -> 0x00eb }
        r4 = "getFirstQueuedMessage(): ";
        r3 = r3.append(r4);	 Catch:{ all -> 0x00eb }
        r4 = r1.getLocalizedMessage();	 Catch:{ all -> 0x00eb }
        r3 = r3.append(r4);	 Catch:{ all -> 0x00eb }
        r3 = r3.toString();	 Catch:{ all -> 0x00eb }
        r1 = r1.getCause();	 Catch:{ all -> 0x00eb }
        com.mavenir.android.common.q.c(r2, r3, r1);	 Catch:{ all -> 0x00eb }
        if (r6 == 0) goto L_0x0098;
    L_0x00d1:
        r1 = r6.isClosed();
        if (r1 != 0) goto L_0x0098;
    L_0x00d7:
        r6.close();
        goto L_0x0098;
    L_0x00db:
        r0 = move-exception;
        r2 = r6;
    L_0x00dd:
        if (r2 == 0) goto L_0x00e8;
    L_0x00df:
        r1 = r2.isClosed();
        if (r1 != 0) goto L_0x00e8;
    L_0x00e5:
        r2.close();
    L_0x00e8:
        throw r0;
    L_0x00e9:
        r0 = move-exception;
        goto L_0x00dd;
    L_0x00eb:
        r0 = move-exception;
        r2 = r6;
        goto L_0x00dd;
    L_0x00ee:
        r0 = move-exception;
        r1 = r0;
        r0 = r6;
        r6 = r2;
        goto L_0x00af;
    L_0x00f3:
        r1 = move-exception;
        r6 = r2;
        goto L_0x00af;
    L_0x00f6:
        r0 = r6;
        goto L_0x0098;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mavenir.android.messaging.provider.d.a(android.content.Context, boolean):com.mavenir.android.messaging.c.d");
    }

    public static List<com.mavenir.android.messaging.c.d> c(Context context) {
        Exception e;
        Throwable th;
        Uri uri = f.c;
        ContentResolver contentResolver = context.getContentResolver();
        List<com.mavenir.android.messaging.c.d> arrayList = new ArrayList(0);
        Cursor query;
        try {
            query = contentResolver.query(uri, a, null, null, "date ASC");
            if (query != null) {
                while (query.moveToNext()) {
                    try {
                        com.mavenir.android.messaging.c.d dVar = new com.mavenir.android.messaging.c.d(context);
                        dVar.c(query.getLong(query.getColumnIndex("_id")));
                        dVar.d(query.getLong(query.getColumnIndex("thread_id")));
                        dVar.b(query.getString(query.getColumnIndex("address")));
                        dVar.c(query.getString(query.getColumnIndex("body")));
                        dVar.b(query.getInt(query.getColumnIndex("status")));
                        dVar.e(query.getLong(query.getColumnIndex("date")));
                        arrayList.add(dVar);
                    } catch (Exception e2) {
                        e = e2;
                    }
                }
            }
            if (!(query == null || query.isClosed())) {
                query.close();
            }
        } catch (Exception e3) {
            e = e3;
            query = null;
            try {
                q.c("MessagesNativeInterface", "getAllQueuedMessages(): " + e.getLocalizedMessage(), e.getCause());
                if (!(query == null || query.isClosed())) {
                    query.close();
                }
                return arrayList;
            } catch (Throwable th2) {
                th = th2;
                if (!(query == null || query.isClosed())) {
                    query.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            query = null;
            query.close();
            throw th;
        }
        return arrayList;
    }

    public static List<com.mavenir.android.messaging.c.d> d(Context context) {
        Exception e;
        Throwable th;
        Uri uri = com.mavenir.android.messaging.provider.g.f.d.a;
        ContentResolver contentResolver = context.getContentResolver();
        List<com.mavenir.android.messaging.c.d> arrayList = new ArrayList(0);
        Cursor query;
        try {
            query = contentResolver.query(uri, a, null, null, "date ASC");
            if (query != null) {
                while (query.moveToNext()) {
                    try {
                        com.mavenir.android.messaging.c.d dVar = new com.mavenir.android.messaging.c.d(context);
                        dVar.c(query.getLong(query.getColumnIndex("_id")));
                        dVar.d(query.getLong(query.getColumnIndex("thread_id")));
                        dVar.b(query.getString(query.getColumnIndex("address")));
                        dVar.c(query.getString(query.getColumnIndex("body")));
                        dVar.b(query.getInt(query.getColumnIndex("status")));
                        dVar.e(query.getLong(query.getColumnIndex("date")));
                        arrayList.add(dVar);
                    } catch (Exception e2) {
                        e = e2;
                    }
                }
            }
            if (!(query == null || query.isClosed())) {
                query.close();
            }
        } catch (Exception e3) {
            e = e3;
            query = null;
            try {
                q.c("MessagesNativeInterface", "getAllOutboxMessages(): " + e.getLocalizedMessage(), e.getCause());
                if (!(query == null || query.isClosed())) {
                    query.close();
                }
                return arrayList;
            } catch (Throwable th2) {
                th = th2;
                if (!(query == null || query.isClosed())) {
                    query.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            query = null;
            query.close();
            throw th;
        }
        return arrayList;
    }

    public static void e(Context context) {
        for (com.mavenir.android.messaging.c.d dVar : d(context)) {
            if (f.a(context, dVar.a(), 5, 1)) {
                q.a("MessagesNativeInterface", "markOutboxAsFailed(): moved: " + dVar.a() + " to failed folder");
            } else {
                q.d("MessagesNativeInterface", "markOutboxAsFailed(): failed to move: " + dVar.a() + " to failed folder");
            }
        }
    }
}
