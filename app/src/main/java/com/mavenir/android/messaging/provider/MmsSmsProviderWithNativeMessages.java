package com.mavenir.android.messaging.provider;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.MatrixCursor.RowBuilder;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;
import com.mavenir.android.common.q;
import com.mavenir.android.common.t.f;
import com.mavenir.android.messaging.provider.g.e;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class MmsSmsProviderWithNativeMessages extends MmsSmsProvider {
    private a b;
    private Map<Long, String> c = null;
    private Map<Long, String> d = null;

    private class a extends ContentObserver {
        final /* synthetic */ MmsSmsProviderWithNativeMessages a;
        private long b;

        public a(MmsSmsProviderWithNativeMessages mmsSmsProviderWithNativeMessages) {
            this.a = mmsSmsProviderWithNativeMessages;
            super(new Handler(Looper.getMainLooper()));
            this.b = 0;
            this.b = System.currentTimeMillis();
        }

        public boolean deliverSelfNotifications() {
            return true;
        }

        public void onChange(boolean z) {
            onChange(z, null);
        }

        public void onChange(boolean z, Uri uri) {
            System.currentTimeMillis();
            this.a.getContext().getContentResolver().notifyChange(Uri.parse("vtow://conversations"), null);
            this.b = System.currentTimeMillis();
        }
    }

    private class b {
        final /* synthetic */ MmsSmsProviderWithNativeMessages a;
        private Cursor b;
        private Cursor c;
        private int d = -1;
        private int e = -1;
        private int f = -1;
        private int g = -1;
        private MatrixCursor h;
        private Map<Integer, AtomicLong> i = null;
        private Map<Integer, StringBuilder> j = null;
        private Map<Integer, String[]> k = null;

        public b(MmsSmsProviderWithNativeMessages mmsSmsProviderWithNativeMessages, Cursor cursor, Cursor cursor2) {
            this.a = mmsSmsProviderWithNativeMessages;
            this.b = cursor;
            this.c = cursor2;
        }

        public Cursor a() {
            try {
                b();
                this.i = null;
                this.j = null;
                this.k = null;
                if (this.b != null) {
                    this.b.close();
                }
                if (this.c != null) {
                    this.c.close();
                }
                this.b = null;
                this.c = null;
                return this.h;
            } catch (Throwable th) {
                this.i = null;
                this.j = null;
                this.k = null;
                if (this.b != null) {
                    this.b.close();
                }
                if (this.c != null) {
                    this.c.close();
                }
                this.b = null;
                this.c = null;
            }
        }

        private void b() {
            String[] c = c();
            this.i = new HashMap();
            this.j = new HashMap();
            this.k = new HashMap();
            this.h = new MatrixCursor(c);
            this.e = this.b.getColumnIndex("date");
            this.d = this.b.getColumnIndex("_id");
            this.f = this.b.getColumnIndex("recipient_ids");
            this.g = this.b.getColumnIndex("message_count");
            this.c.moveToPosition(-1);
            this.b.moveToPosition(-1);
            boolean moveToNext = this.c.moveToNext();
            boolean moveToNext2 = this.b.moveToNext();
            while (true) {
                if (!moveToNext && !moveToNext2) {
                    this.h.moveToPosition(-1);
                    return;
                } else if (moveToNext && moveToNext2) {
                    if (this.c.getLong(this.e) > this.b.getLong(this.e)) {
                        a(this.c, -1, this.c.getLong(this.d));
                        moveToNext = this.c.moveToNext();
                    } else {
                        a(this.b, this.b.getLong(this.d), -1);
                        moveToNext2 = this.b.moveToNext();
                    }
                } else if (moveToNext) {
                    a(this.c, -1, this.c.getLong(this.d));
                    moveToNext = this.c.moveToNext();
                } else {
                    a(this.b, this.b.getLong(this.d), -1);
                    moveToNext2 = this.b.moveToNext();
                }
            }
        }

        private String[] c() {
            return this.b.getColumnNames();
        }

        private void a(Cursor cursor, long j, long j2) {
            if (cursor.getInt(this.g) > 0 || this.a.d(j) || this.a.b(j2)) {
                String[] a = a(cursor, j == -1);
                if (!a(cursor, j, j2, a)) {
                    a(cursor, new a(j, j2).a(), a);
                }
            }
        }

        private void a(Cursor cursor, long j, String[] strArr) {
            RowBuilder newRow = this.h.newRow();
            for (int i = 0; i < this.h.getColumnCount(); i++) {
                if (i == this.d) {
                    AtomicLong atomicLong = new AtomicLong(j);
                    newRow.add(atomicLong);
                    this.i.put(Integer.valueOf(this.h.getCount() - 1), atomicLong);
                } else if (i == this.f) {
                    StringBuilder stringBuilder = new StringBuilder(cursor.getString(i));
                    newRow.add(stringBuilder);
                    this.j.put(Integer.valueOf(this.h.getCount() - 1), stringBuilder);
                } else if (cursor.getType(i) == 1) {
                    newRow.add(Long.valueOf(cursor.getLong(i)));
                } else if (cursor.getType(i) == 2) {
                    newRow.add(Double.valueOf(cursor.getDouble(i)));
                } else if (cursor.getType(i) == 3) {
                    newRow.add(cursor.getString(i));
                } else if (cursor.getType(i) == 4) {
                    newRow.add(cursor.getBlob(i));
                } else {
                    newRow.add(null);
                }
            }
            this.k.put(Integer.valueOf(this.h.getCount() - 1), strArr);
        }

        private boolean a(Cursor cursor, long j, long j2, String[] strArr) {
            if (strArr == null) {
                return false;
            }
            this.h.moveToPosition(-1);
            while (this.h.moveToNext()) {
                if (a((String[]) this.k.get(Integer.valueOf(this.h.getPosition())), strArr)) {
                    AtomicLong atomicLong = (AtomicLong) this.i.get(Integer.valueOf(this.h.getPosition()));
                    if (atomicLong != null) {
                        a a = a.a(atomicLong.get());
                        if (a.a > -1) {
                            if (j2 > -1 && a.b == -1) {
                                a.b = j2;
                                atomicLong.set(a.a());
                                return true;
                            }
                        } else if (a.b > -1 && j > -1 && a.a == -1) {
                            a.a = j;
                            atomicLong.set(a.a());
                            StringBuilder stringBuilder = (StringBuilder) this.j.get(Integer.valueOf(this.h.getPosition()));
                            if (stringBuilder != null) {
                                stringBuilder.setLength(0);
                                stringBuilder.append(cursor.getString(this.f));
                            }
                            return true;
                        }
                    }
                    continue;
                }
            }
            return false;
        }

        private String[] a(Cursor cursor, boolean z) {
            Object string = cursor.getString(this.f);
            if (TextUtils.isEmpty(string)) {
                return null;
            }
            String[] split = string.split(" ");
            if (split == null || split.length == 0) {
                return null;
            }
            String[] strArr = new String[split.length];
            for (int i = 0; i < split.length; i++) {
                strArr[i] = z ? this.a.a(split[i]) : this.a.b(split[i]);
            }
            return strArr;
        }

        private boolean a(String[] strArr, String[] strArr2) {
            if (strArr == null || strArr2 == null || strArr.length != strArr2.length) {
                return false;
            }
            for (int i = 0; i < strArr.length; i++) {
                boolean z;
                for (int i2 = 0; i2 < strArr2.length; i2++) {
                    boolean h = f.h(strArr[i]);
                    boolean h2 = f.h(strArr2[i2]);
                    if (h && h2) {
                        if (PhoneNumberUtils.compare(strArr[i], strArr2[i2])) {
                            z = true;
                            break;
                        }
                    } else if (TextUtils.equals(strArr[i], strArr2[i2])) {
                        z = true;
                        break;
                    }
                }
                z = false;
                if (!z) {
                    return false;
                }
            }
            return true;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(com.mavenir.android.messaging.provider.a r11) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find block by offset: 0x000f in list [B:38:0x0086]
	at jadx.core.utils.BlockUtils.getBlockByOffset(BlockUtils.java:43)
	at jadx.core.dex.instructions.IfNode.initBlocks(IfNode.java:60)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.initBlocksInIfNodes(BlockFinish.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.visit(BlockFinish.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
        /*
        r10 = this;
        r3 = 0;
        r8 = -1;
        r0 = r11.a;
        r0 = (r0 > r8 ? 1 : (r0 == r8 ? 0 : -1));
        if (r0 != 0) goto L_0x0010;
    L_0x0009:
        r0 = r11.b;
        r0 = (r0 > r8 ? 1 : (r0 == r8 ? 0 : -1));
        if (r0 != 0) goto L_0x0010;
    L_0x000f:
        return;
    L_0x0010:
        r0 = r11.a;
        r0 = (r0 > r8 ? 1 : (r0 == r8 ? 0 : -1));
        if (r0 <= 0) goto L_0x001c;
    L_0x0016:
        r0 = r11.b;
        r0 = (r0 > r8 ? 1 : (r0 == r8 ? 0 : -1));
        if (r0 > 0) goto L_0x000f;
    L_0x001c:
        r1 = com.mavenir.android.messaging.provider.g.g.a;
        r2 = com.mavenir.android.messaging.provider.g.g.d;
        r5 = "date DESC";
        r0 = r10;
        r4 = r3;
        r1 = r0.query(r1, r2, r3, r4, r5);
        if (r1 == 0) goto L_0x0030;
    L_0x002a:
        r0 = r1.getCount();	 Catch:{ all -> 0x0090 }
        if (r0 != 0) goto L_0x0036;
    L_0x0030:
        if (r1 == 0) goto L_0x000f;
    L_0x0032:
        r1.close();
        goto L_0x000f;
    L_0x0036:
        r0 = "_id";	 Catch:{ all -> 0x0090 }
        r0 = r1.getColumnIndex(r0);	 Catch:{ all -> 0x0090 }
        r2 = -1;	 Catch:{ all -> 0x0090 }
        r1.move(r2);	 Catch:{ all -> 0x0090 }
    L_0x0040:
        r2 = r1.moveToNext();	 Catch:{ all -> 0x0090 }
        if (r2 == 0) goto L_0x008a;	 Catch:{ all -> 0x0090 }
    L_0x0046:
        r2 = r1.getLong(r0);	 Catch:{ all -> 0x0090 }
        r2 = com.mavenir.android.messaging.provider.a.a(r2);	 Catch:{ all -> 0x0090 }
        r4 = r11.a;	 Catch:{ all -> 0x0090 }
        r3 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1));	 Catch:{ all -> 0x0090 }
        if (r3 != 0) goto L_0x006c;	 Catch:{ all -> 0x0090 }
    L_0x0054:
        r4 = r2.a;	 Catch:{ all -> 0x0090 }
        r3 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1));	 Catch:{ all -> 0x0090 }
        if (r3 <= 0) goto L_0x006c;	 Catch:{ all -> 0x0090 }
    L_0x005a:
        r4 = r11.b;	 Catch:{ all -> 0x0090 }
        r6 = r2.b;	 Catch:{ all -> 0x0090 }
        r3 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));	 Catch:{ all -> 0x0090 }
        if (r3 != 0) goto L_0x006c;	 Catch:{ all -> 0x0090 }
    L_0x0062:
        r2 = r2.a;	 Catch:{ all -> 0x0090 }
        r11.a = r2;	 Catch:{ all -> 0x0090 }
        if (r1 == 0) goto L_0x000f;
    L_0x0068:
        r1.close();
        goto L_0x000f;
    L_0x006c:
        r4 = r11.b;	 Catch:{ all -> 0x0090 }
        r3 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1));	 Catch:{ all -> 0x0090 }
        if (r3 != 0) goto L_0x0040;	 Catch:{ all -> 0x0090 }
    L_0x0072:
        r4 = r2.b;	 Catch:{ all -> 0x0090 }
        r3 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1));	 Catch:{ all -> 0x0090 }
        if (r3 <= 0) goto L_0x0040;	 Catch:{ all -> 0x0090 }
    L_0x0078:
        r4 = r11.a;	 Catch:{ all -> 0x0090 }
        r6 = r2.a;	 Catch:{ all -> 0x0090 }
        r3 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));	 Catch:{ all -> 0x0090 }
        if (r3 != 0) goto L_0x0040;	 Catch:{ all -> 0x0090 }
    L_0x0080:
        r2 = r2.b;	 Catch:{ all -> 0x0090 }
        r11.b = r2;	 Catch:{ all -> 0x0090 }
        if (r1 == 0) goto L_0x000f;
    L_0x0086:
        r1.close();
        goto L_0x000f;
    L_0x008a:
        if (r1 == 0) goto L_0x000f;
    L_0x008c:
        r1.close();
        goto L_0x000f;
    L_0x0090:
        r0 = move-exception;
        if (r1 == 0) goto L_0x0096;
    L_0x0093:
        r1.close();
    L_0x0096:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mavenir.android.messaging.provider.MmsSmsProviderWithNativeMessages.a(com.mavenir.android.messaging.provider.a):void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected java.lang.String a(long r10) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find block by offset: 0x0009 in list [B:15:0x0060]
	at jadx.core.utils.BlockUtils.getBlockByOffset(BlockUtils.java:43)
	at jadx.core.dex.instructions.IfNode.initBlocks(IfNode.java:60)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.initBlocksInIfNodes(BlockFinish.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.visit(BlockFinish.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
        /*
        r9 = this;
        r4 = 1;
        r7 = 0;
        r5 = 0;
        r0 = 0;
        r0 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1));
        if (r0 >= 0) goto L_0x000a;
    L_0x0009:
        return r5;
    L_0x000a:
        r0 = r9.c;
        if (r0 != 0) goto L_0x0011;
    L_0x000e:
        r9.b();
    L_0x0011:
        r0 = r9.c;
        r1 = java.lang.Long.valueOf(r10);
        r0 = r0.get(r1);
        r0 = (java.lang.String) r0;
        if (r0 == 0) goto L_0x0021;
    L_0x001f:
        r5 = r0;
        goto L_0x0009;
    L_0x0021:
        r0 = r9.getContext();
        r0 = r0.getContentResolver();
        r1 = "content://mms-sms/canonical-addresses";
        r1 = android.net.Uri.parse(r1);
        r2 = new java.lang.String[r4];
        r3 = "address";
        r2[r7] = r3;
        r3 = "_id=?";
        r4 = new java.lang.String[r4];
        r6 = java.lang.String.valueOf(r10);
        r4[r7] = r6;
        r1 = r0.query(r1, r2, r3, r4, r5);
        if (r1 == 0) goto L_0x0064;
    L_0x0045:
        r0 = r1.moveToFirst();	 Catch:{ all -> 0x006a }
        if (r0 == 0) goto L_0x0064;	 Catch:{ all -> 0x006a }
    L_0x004b:
        r0 = "address";	 Catch:{ all -> 0x006a }
        r0 = r1.getColumnIndex(r0);	 Catch:{ all -> 0x006a }
        r5 = r1.getString(r0);	 Catch:{ all -> 0x006a }
        r0 = r9.c;	 Catch:{ all -> 0x006a }
        r2 = java.lang.Long.valueOf(r10);	 Catch:{ all -> 0x006a }
        r0.put(r2, r5);	 Catch:{ all -> 0x006a }
        if (r1 == 0) goto L_0x0009;
    L_0x0060:
        r1.close();
        goto L_0x0009;
    L_0x0064:
        if (r1 == 0) goto L_0x0009;
    L_0x0066:
        r1.close();
        goto L_0x0009;
    L_0x006a:
        r0 = move-exception;
        if (r1 == 0) goto L_0x0070;
    L_0x006d:
        r1.close();
    L_0x0070:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mavenir.android.messaging.provider.MmsSmsProviderWithNativeMessages.a(long):java.lang.String");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected java.lang.String c(long r8) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find block by offset: 0x0009 in list [B:15:0x0059]
	at jadx.core.utils.BlockUtils.getBlockByOffset(BlockUtils.java:43)
	at jadx.core.dex.instructions.IfNode.initBlocks(IfNode.java:60)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.initBlocksInIfNodes(BlockFinish.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.visit(BlockFinish.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
        /*
        r7 = this;
        r4 = 1;
        r6 = 0;
        r5 = 0;
        r0 = 0;
        r0 = (r8 > r0 ? 1 : (r8 == r0 ? 0 : -1));
        if (r0 >= 0) goto L_0x000a;
    L_0x0009:
        return r5;
    L_0x000a:
        r0 = r7.d;
        if (r0 != 0) goto L_0x0011;
    L_0x000e:
        r7.c();
    L_0x0011:
        r0 = r7.d;
        r1 = java.lang.Long.valueOf(r8);
        r0 = r0.get(r1);
        r0 = (java.lang.String) r0;
        if (r0 == 0) goto L_0x0021;
    L_0x001f:
        r5 = r0;
        goto L_0x0009;
    L_0x0021:
        r0 = "content://com.mavenir.provider.mingle.mms-sms/canonical-addresses?threadId=0";
        r1 = android.net.Uri.parse(r0);
        r2 = new java.lang.String[r4];
        r0 = "address";
        r2[r6] = r0;
        r3 = "_id=?";
        r4 = new java.lang.String[r4];
        r0 = java.lang.String.valueOf(r8);
        r4[r6] = r0;
        r0 = r7;
        r1 = super.query(r1, r2, r3, r4, r5);
        if (r1 == 0) goto L_0x005d;
    L_0x003e:
        r0 = r1.moveToFirst();	 Catch:{ all -> 0x0063 }
        if (r0 == 0) goto L_0x005d;	 Catch:{ all -> 0x0063 }
    L_0x0044:
        r0 = "address";	 Catch:{ all -> 0x0063 }
        r0 = r1.getColumnIndex(r0);	 Catch:{ all -> 0x0063 }
        r5 = r1.getString(r0);	 Catch:{ all -> 0x0063 }
        r0 = r7.d;	 Catch:{ all -> 0x0063 }
        r2 = java.lang.Long.valueOf(r8);	 Catch:{ all -> 0x0063 }
        r0.put(r2, r5);	 Catch:{ all -> 0x0063 }
        if (r1 == 0) goto L_0x0009;
    L_0x0059:
        r1.close();
        goto L_0x0009;
    L_0x005d:
        if (r1 == 0) goto L_0x0009;
    L_0x005f:
        r1.close();
        goto L_0x0009;
    L_0x0063:
        r0 = move-exception;
        if (r1 == 0) goto L_0x0069;
    L_0x0066:
        r1.close();
    L_0x0069:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mavenir.android.messaging.provider.MmsSmsProviderWithNativeMessages.c(long):java.lang.String");
    }

    public boolean onCreate() {
        a();
        return super.onCreate();
    }

    private void a() {
        this.b = new a(this);
        ContentResolver contentResolver = getContext().getContentResolver();
        contentResolver.registerContentObserver(e.b, false, this.b);
        contentResolver.registerContentObserver(a(e.b, "mms-sms"), false, this.b);
    }

    private Uri a(Uri uri, String str) {
        return uri.buildUpon().authority(str).build();
    }

    private Uri b(Uri uri, String str) {
        return uri.buildUpon().authority(str).clearQuery().build();
    }

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        ContentResolver contentResolver;
        if (strArr2 == null || strArr2.length > 0) {
            contentResolver = getContext().getContentResolver();
        } else {
            contentResolver = getContext().getContentResolver();
        }
        switch (a.match(uri)) {
            case 0:
                System.currentTimeMillis();
                String queryParameter = uri.getQueryParameter("simple");
                if (queryParameter != null && queryParameter.equals("true")) {
                    String[] strArr3;
                    if (!"_id=?".equals(str) || strArr2 == null || strArr2.length <= 0) {
                        strArr3 = strArr2;
                    } else {
                        a a = a.a(strArr2[0]);
                        strArr3 = new String[]{Long.toString(a.a)};
                        strArr2 = new String[]{Long.toString(a.b)};
                    }
                    Cursor a2 = new b(this, super.query(uri, strArr, str, strArr3, str2), contentResolver.query(a(uri, "mms-sms"), strArr, str, strArr2, str2)).a();
                    a2.setNotificationUri(contentResolver, Uri.parse("vtow://conversations"));
                    return a2;
                }
                break;
            case 1:
                Cursor query;
                Cursor query2;
                a a3 = a.a((String) uri.getPathSegments().get(1));
                System.currentTimeMillis();
                a(a3);
                if (a3.a > -1) {
                    query = super.query(uri.buildUpon().path("conversations/" + a3.a).build(), strArr, str, strArr2, str2);
                } else {
                    query = null;
                }
                if (a3.b > -1) {
                    query2 = contentResolver.query(uri.buildUpon().authority("mms-sms").path("/conversations/" + a3.b).build(), strArr, str, strArr2, str2);
                } else {
                    query2 = null;
                }
                if (query2 == null) {
                    return query;
                }
                return new c(query2, query, "date");
            case 13:
                a a4 = a.a(uri.getQueryParameter("threadId"));
                long j = -1;
                if ("_id=?".equals(str) && strArr2 != null && strArr2.length > 0) {
                    j = Long.parseLong(strArr2[0]);
                }
                if (a4.a == -1) {
                    if (j > -1) {
                        return a(strArr, j, a(j));
                    }
                    return contentResolver.query(b(uri, "mms-sms"), strArr, str, strArr2, str2);
                } else if (j > -1) {
                    return a(strArr, j, c(j));
                } else {
                    return super.query(uri, strArr, str, strArr2, str2);
                }
        }
        return super.query(uri, strArr, str, strArr2, str2);
    }

    public int delete(Uri uri, String str, String[] strArr) {
        a a;
        if (strArr == null || strArr.length > 0) {
            a = a.a((String) uri.getPathSegments().get(1));
        } else {
            a = a.a((String) uri.getPathSegments().get(1));
        }
        if (a.a > -1) {
            return super.delete(uri.buildUpon().path("conversations/" + a.a).build(), str, strArr);
        }
        return 0;
    }

    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        if (strArr == null || strArr.length > 0) {
        }
        switch (a.match(uri)) {
            case 1:
                a a = a.a((String) uri.getPathSegments().get(1));
                if (a.a > -1) {
                    uri = uri.buildUpon().path("conversations/" + a.a).build();
                    break;
                }
                return 0;
        }
        return super.update(uri, contentValues, str, strArr);
    }

    private synchronized void b() {
        if (this.c == null) {
            this.c = new HashMap();
            Cursor query = getContext().getContentResolver().query(Uri.parse("content://mms-sms/canonical-addresses"), com.mavenir.android.messaging.provider.g.b.a, null, null, null);
            if (query != null) {
                try {
                    this.c = new HashMap(query.getCount());
                    int columnIndex = query.getColumnIndex("_id");
                    int columnIndex2 = query.getColumnIndex("address");
                    while (query.moveToNext()) {
                        this.c.put(Long.valueOf(query.getLong(columnIndex)), query.getString(columnIndex2));
                    }
                } catch (Throwable th) {
                    if (query != null) {
                        query.close();
                    }
                }
            }
            if (query != null) {
                query.close();
            }
        }
    }

    protected String a(String str) {
        return a(Long.parseLong(str));
    }

    protected boolean b(long j) {
        Exception e;
        Throwable th;
        if (j < 0) {
            return false;
        }
        Cursor query;
        try {
            query = getContext().getContentResolver().query(ContentUris.withAppendedId(Uri.parse("content://mms-sms/conversations"), j), g.f.e, "type=3", null, null);
            if (query != null) {
                try {
                    if (query.moveToFirst()) {
                        if (query == null || query.isClosed()) {
                            return true;
                        }
                        query.close();
                        return true;
                    }
                } catch (Exception e2) {
                    e = e2;
                    try {
                        q.c("MmsSmsProviderWithNativeMessages", "conversationHasDraftNative(): " + e.getLocalizedMessage(), e.getCause());
                        query.close();
                        return false;
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
            q.c("MmsSmsProviderWithNativeMessages", "conversationHasDraftNative(): " + e.getLocalizedMessage(), e.getCause());
            if (!(query == null || query.isClosed())) {
                query.close();
            }
            return false;
        } catch (Throwable th3) {
            th = th3;
            query = null;
            query.close();
            throw th;
        }
        return false;
    }

    private synchronized void c() {
        if (this.d == null) {
            this.d = new HashMap();
            Cursor query = super.query(Uri.parse("content://com.mavenir.provider.mingle.mms-sms/canonical-addresses?threadId=0"), com.mavenir.android.messaging.provider.g.b.a, null, null, null);
            if (query != null) {
                try {
                    this.d = new HashMap(query.getCount());
                    int columnIndex = query.getColumnIndex("_id");
                    int columnIndex2 = query.getColumnIndex("address");
                    while (query.moveToNext()) {
                        this.d.put(Long.valueOf(query.getLong(columnIndex)), query.getString(columnIndex2));
                    }
                } catch (Throwable th) {
                    if (query != null) {
                        query.close();
                    }
                }
            }
            if (query != null) {
                query.close();
            }
        }
    }

    protected String b(String str) {
        return c(Long.parseLong(str));
    }

    protected boolean d(long j) {
        Exception e;
        Throwable th;
        if (j < 0) {
            return false;
        }
        Cursor query;
        try {
            query = getContext().getContentResolver().query(ContentUris.withAppendedId(Uri.parse("content://com.mavenir.provider.mingle.sms/conversations"), j), g.f.e, "type=3", null, null);
            if (query != null) {
                try {
                    if (query.moveToFirst()) {
                        if (query == null || query.isClosed()) {
                            return true;
                        }
                        query.close();
                        return true;
                    }
                } catch (Exception e2) {
                    e = e2;
                    try {
                        q.c("MmsSmsProviderWithNativeMessages", "conversationHasDraftCustom(): " + e.getLocalizedMessage(), e.getCause());
                        query.close();
                        return false;
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
            q.c("MmsSmsProviderWithNativeMessages", "conversationHasDraftCustom(): " + e.getLocalizedMessage(), e.getCause());
            if (!(query == null || query.isClosed())) {
                query.close();
            }
            return false;
        } catch (Throwable th3) {
            th = th3;
            query = null;
            query.close();
            throw th;
        }
        return false;
    }

    private Cursor a(String[] strArr, long j, String str) {
        Cursor matrixCursor = new MatrixCursor(strArr, 1);
        matrixCursor.addRow(new Object[]{new Long(j), str});
        matrixCursor.moveToPosition(-1);
        return matrixCursor;
    }
}
