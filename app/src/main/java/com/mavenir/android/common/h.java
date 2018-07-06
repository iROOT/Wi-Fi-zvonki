package com.mavenir.android.common;

public class h {
    private String a;
    private String b;
    private int c;
    private String d;

    public String a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public int c() {
        return this.c;
    }

    public String d() {
        return this.d;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.mavenir.android.common.h a(java.lang.String r7) {
        /*
        r6 = 0;
        r0 = android.text.TextUtils.isEmpty(r7);
        if (r0 == 0) goto L_0x0009;
    L_0x0007:
        r0 = r6;
    L_0x0008:
        return r0;
    L_0x0009:
        r0 = android.provider.ContactsContract.PhoneLookup.CONTENT_FILTER_URI;
        r1 = android.net.Uri.encode(r7);
        r1 = android.net.Uri.withAppendedPath(r0, r1);
        r0 = com.fgmicrotec.mobile.android.fgvoip.FgVoIP.U();	 Catch:{ Exception -> 0x00ad, all -> 0x00a0 }
        r0 = r0.getContentResolver();	 Catch:{ Exception -> 0x00ad, all -> 0x00a0 }
        r2 = 4;
        r2 = new java.lang.String[r2];	 Catch:{ Exception -> 0x00ad, all -> 0x00a0 }
        r3 = 0;
        r4 = "number";
        r2[r3] = r4;	 Catch:{ Exception -> 0x00ad, all -> 0x00a0 }
        r3 = 1;
        r4 = "display_name";
        r2[r3] = r4;	 Catch:{ Exception -> 0x00ad, all -> 0x00a0 }
        r3 = 2;
        r4 = "type";
        r2[r3] = r4;	 Catch:{ Exception -> 0x00ad, all -> 0x00a0 }
        r3 = 3;
        r4 = "label";
        r2[r3] = r4;	 Catch:{ Exception -> 0x00ad, all -> 0x00a0 }
        r3 = 0;
        r4 = 0;
        r5 = 0;
        r2 = r0.query(r1, r2, r3, r4, r5);	 Catch:{ Exception -> 0x00ad, all -> 0x00a0 }
        r0 = new com.mavenir.android.common.h;	 Catch:{ Exception -> 0x00b1, all -> 0x00a8 }
        r0.<init>();	 Catch:{ Exception -> 0x00b1, all -> 0x00a8 }
        if (r2 == 0) goto L_0x007c;
    L_0x0040:
        r1 = r2.moveToFirst();	 Catch:{ Exception -> 0x007f, all -> 0x00a8 }
        if (r1 == 0) goto L_0x007c;
    L_0x0046:
        r1 = "display_name";
        r1 = r2.getColumnIndex(r1);	 Catch:{ Exception -> 0x007f, all -> 0x00a8 }
        r1 = r2.getString(r1);	 Catch:{ Exception -> 0x007f, all -> 0x00a8 }
        r0.a = r1;	 Catch:{ Exception -> 0x007f, all -> 0x00a8 }
        r1 = "number";
        r1 = r2.getColumnIndex(r1);	 Catch:{ Exception -> 0x007f, all -> 0x00a8 }
        r1 = r2.getString(r1);	 Catch:{ Exception -> 0x007f, all -> 0x00a8 }
        r0.b = r1;	 Catch:{ Exception -> 0x007f, all -> 0x00a8 }
        r1 = "type";
        r1 = r2.getColumnIndex(r1);	 Catch:{ Exception -> 0x007f, all -> 0x00a8 }
        r1 = r2.getInt(r1);	 Catch:{ Exception -> 0x007f, all -> 0x00a8 }
        r0.c = r1;	 Catch:{ Exception -> 0x007f, all -> 0x00a8 }
        r1 = "label";
        r1 = r2.getColumnIndex(r1);	 Catch:{ Exception -> 0x007f, all -> 0x00a8 }
        r1 = r2.getString(r1);	 Catch:{ Exception -> 0x007f, all -> 0x00a8 }
        r0.d = r1;	 Catch:{ Exception -> 0x007f, all -> 0x00a8 }
    L_0x0076:
        if (r2 == 0) goto L_0x0008;
    L_0x0078:
        r2.close();
        goto L_0x0008;
    L_0x007c:
        r0.b = r7;	 Catch:{ Exception -> 0x007f, all -> 0x00a8 }
        goto L_0x0076;
    L_0x007f:
        r1 = move-exception;
        r6 = r2;
    L_0x0081:
        r2 = "CallParticipantInfo";
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00aa }
        r3.<init>();	 Catch:{ all -> 0x00aa }
        r4 = "getCallerInfo(): unable to load contact info: ";
        r3 = r3.append(r4);	 Catch:{ all -> 0x00aa }
        r1 = r3.append(r1);	 Catch:{ all -> 0x00aa }
        r1 = r1.toString();	 Catch:{ all -> 0x00aa }
        com.mavenir.android.common.q.a(r2, r1);	 Catch:{ all -> 0x00aa }
        if (r6 == 0) goto L_0x0008;
    L_0x009b:
        r6.close();
        goto L_0x0008;
    L_0x00a0:
        r0 = move-exception;
        r2 = r6;
    L_0x00a2:
        if (r2 == 0) goto L_0x00a7;
    L_0x00a4:
        r2.close();
    L_0x00a7:
        throw r0;
    L_0x00a8:
        r0 = move-exception;
        goto L_0x00a2;
    L_0x00aa:
        r0 = move-exception;
        r2 = r6;
        goto L_0x00a2;
    L_0x00ad:
        r0 = move-exception;
        r1 = r0;
        r0 = r6;
        goto L_0x0081;
    L_0x00b1:
        r0 = move-exception;
        r1 = r0;
        r0 = r6;
        r6 = r2;
        goto L_0x0081;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mavenir.android.common.h.a(java.lang.String):com.mavenir.android.common.h");
    }
}
