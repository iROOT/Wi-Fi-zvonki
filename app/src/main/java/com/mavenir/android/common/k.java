package com.mavenir.android.common;

import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract.PhoneLookup;
import android.provider.ContactsContract.RawContacts;
import android.provider.Settings.System;
import android.text.TextUtils;
import com.fgmicrotec.mobile.android.fgvoip.FgVoIP;

public class k {
    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @android.annotation.SuppressLint({"NewApi"})
    public static android.graphics.Bitmap c(java.lang.String r6) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find block by offset: 0x000a in list [B:57:0x009c]
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
        r1 = 0;
        if (r6 == 0) goto L_0x0009;
    L_0x0003:
        r0 = r6.length();
        if (r0 != 0) goto L_0x000b;
    L_0x0009:
        r0 = r1;
    L_0x000a:
        return r0;
    L_0x000b:
        r2 = a(r6);	 Catch:{ Exception -> 0x0077 }
        r4 = -1;
        r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r0 == 0) goto L_0x00c8;
    L_0x0015:
        r0 = com.fgmicrotec.mobile.android.fgvoip.FgVoIP.U();	 Catch:{ OutOfMemoryError -> 0x0055, all -> 0x006f }
        r0 = r0.getContentResolver();	 Catch:{ OutOfMemoryError -> 0x0055, all -> 0x006f }
        r4 = android.provider.ContactsContract.Contacts.CONTENT_URI;	 Catch:{ OutOfMemoryError -> 0x0055, all -> 0x006f }
        r2 = android.content.ContentUris.withAppendedId(r4, r2);	 Catch:{ OutOfMemoryError -> 0x0055, all -> 0x006f }
        r3 = android.os.Build.VERSION.SDK_INT;	 Catch:{ OutOfMemoryError -> 0x0055, all -> 0x006f }
        r4 = 14;	 Catch:{ OutOfMemoryError -> 0x0055, all -> 0x006f }
        if (r3 < r4) goto L_0x0046;	 Catch:{ OutOfMemoryError -> 0x0055, all -> 0x006f }
    L_0x0029:
        r3 = 1;	 Catch:{ OutOfMemoryError -> 0x0055, all -> 0x006f }
        r2 = android.provider.ContactsContract.Contacts.openContactPhotoInputStream(r0, r2, r3);	 Catch:{ OutOfMemoryError -> 0x0055, all -> 0x006f }
    L_0x002e:
        if (r2 == 0) goto L_0x00c5;
    L_0x0030:
        r3 = android.graphics.BitmapFactory.decodeStream(r2);	 Catch:{ OutOfMemoryError -> 0x00c3 }
    L_0x0034:
        if (r2 == 0) goto L_0x0039;
    L_0x0036:
        r2.close();	 Catch:{ IOException -> 0x004b }
    L_0x0039:
        r0 = r3;
    L_0x003a:
        if (r1 == 0) goto L_0x000a;
    L_0x003c:
        r2 = r1.isClosed();
        if (r2 != 0) goto L_0x000a;
    L_0x0042:
        r1.close();
        goto L_0x000a;
    L_0x0046:
        r2 = android.provider.ContactsContract.Contacts.openContactPhotoInputStream(r0, r2);	 Catch:{ OutOfMemoryError -> 0x0055, all -> 0x006f }
        goto L_0x002e;
    L_0x004b:
        r0 = move-exception;
        r2 = "ContactLookup";	 Catch:{ Exception -> 0x00bd }
        r4 = "getContactBitmapFromNumber(): ";	 Catch:{ Exception -> 0x00bd }
        com.mavenir.android.common.q.c(r2, r4, r0);	 Catch:{ Exception -> 0x00bd }
        r0 = r3;
        goto L_0x003a;
    L_0x0055:
        r0 = move-exception;
        r2 = r1;
    L_0x0057:
        r3 = "ContactLookup";	 Catch:{ all -> 0x00c1 }
        r4 = "getContactBitmapFromNumber(): ";	 Catch:{ all -> 0x00c1 }
        com.mavenir.android.common.q.c(r3, r4, r0);	 Catch:{ all -> 0x00c1 }
        if (r2 == 0) goto L_0x0063;
    L_0x0060:
        r2.close();	 Catch:{ IOException -> 0x0065 }
    L_0x0063:
        r0 = r1;
        goto L_0x003a;
    L_0x0065:
        r0 = move-exception;
        r2 = "ContactLookup";	 Catch:{ Exception -> 0x0077 }
        r3 = "getContactBitmapFromNumber(): ";	 Catch:{ Exception -> 0x0077 }
        com.mavenir.android.common.q.c(r2, r3, r0);	 Catch:{ Exception -> 0x0077 }
        r0 = r1;
        goto L_0x003a;
    L_0x006f:
        r0 = move-exception;
        r2 = r1;
    L_0x0071:
        if (r2 == 0) goto L_0x0076;
    L_0x0073:
        r2.close();	 Catch:{ IOException -> 0x00a7 }
    L_0x0076:
        throw r0;	 Catch:{ Exception -> 0x0077 }
    L_0x0077:
        r0 = move-exception;
        r2 = r0;
        r0 = r1;
    L_0x007a:
        r3 = "ContactLookup";	 Catch:{ all -> 0x00b0 }
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00b0 }
        r4.<init>();	 Catch:{ all -> 0x00b0 }
        r5 = "getContactBitmapFromNumber() : ";	 Catch:{ all -> 0x00b0 }
        r4 = r4.append(r5);	 Catch:{ all -> 0x00b0 }
        r5 = r2.getLocalizedMessage();	 Catch:{ all -> 0x00b0 }
        r4 = r4.append(r5);	 Catch:{ all -> 0x00b0 }
        r4 = r4.toString();	 Catch:{ all -> 0x00b0 }
        r2 = r2.getCause();	 Catch:{ all -> 0x00b0 }
        com.mavenir.android.common.q.c(r3, r4, r2);	 Catch:{ all -> 0x00b0 }
        if (r1 == 0) goto L_0x000a;
    L_0x009c:
        r2 = r1.isClosed();
        if (r2 != 0) goto L_0x000a;
    L_0x00a2:
        r1.close();
        goto L_0x000a;
    L_0x00a7:
        r2 = move-exception;
        r3 = "ContactLookup";	 Catch:{ Exception -> 0x0077 }
        r4 = "getContactBitmapFromNumber(): ";	 Catch:{ Exception -> 0x0077 }
        com.mavenir.android.common.q.c(r3, r4, r2);	 Catch:{ Exception -> 0x0077 }
        goto L_0x0076;
    L_0x00b0:
        r0 = move-exception;
        if (r1 == 0) goto L_0x00bc;
    L_0x00b3:
        r2 = r1.isClosed();
        if (r2 != 0) goto L_0x00bc;
    L_0x00b9:
        r1.close();
    L_0x00bc:
        throw r0;
    L_0x00bd:
        r0 = move-exception;
        r2 = r0;
        r0 = r3;
        goto L_0x007a;
    L_0x00c1:
        r0 = move-exception;
        goto L_0x0071;
    L_0x00c3:
        r0 = move-exception;
        goto L_0x0057;
    L_0x00c5:
        r3 = r1;
        goto L_0x0034;
    L_0x00c8:
        r0 = r1;
        goto L_0x003a;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mavenir.android.common.k.c(java.lang.String):android.graphics.Bitmap");
    }

    public static String a(Uri uri) {
        Object e;
        Throwable th;
        if (uri == null) {
            return "";
        }
        String str = "";
        Cursor query;
        try {
            String string;
            query = FgVoIP.U().getContentResolver().query(uri, new String[]{"data1"}, null, null, null);
            if (query != null) {
                try {
                    if (query.moveToFirst()) {
                        string = query.getString(query.getColumnIndex("data1"));
                        if (query == null && !query.isClosed()) {
                            query.close();
                            return string;
                        }
                    }
                } catch (Exception e2) {
                    e = e2;
                    try {
                        q.d("ContactLookup", "getContactNumberFromUri(): " + e);
                        if (query != null) {
                        }
                        return str;
                    } catch (Throwable th2) {
                        th = th2;
                        query.close();
                        throw th;
                    }
                }
            }
            string = str;
            return query == null ? string : string;
        } catch (Exception e3) {
            e = e3;
            query = null;
            q.d("ContactLookup", "getContactNumberFromUri(): " + e);
            if (query != null || query.isClosed()) {
                return str;
            }
            query.close();
            return str;
        } catch (Throwable th3) {
            th = th3;
            query = null;
            if (!(query == null || query.isClosed())) {
                query.close();
            }
            throw th;
        }
    }

    public static long a(String str) {
        Object e;
        Cursor cursor;
        Throwable th;
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        long j;
        Uri withAppendedPath = Uri.withAppendedPath(PhoneLookup.CONTENT_FILTER_URI, Uri.encode(str));
        try {
            Cursor query = FgVoIP.U().getContentResolver().query(withAppendedPath, new String[]{"_id"}, null, null, null);
            if (query != null) {
                try {
                    if (query.moveToFirst()) {
                        j = query.getLong(query.getColumnIndex("_id"));
                        if (!(query == null || query.isClosed())) {
                            query.close();
                        }
                        return j;
                    }
                } catch (Exception e2) {
                    e = e2;
                    cursor = query;
                    try {
                        q.d("ContactLookup", "getContactIDFromNumber(): " + e);
                        if (cursor != null) {
                        }
                        j = -1;
                        return j;
                    } catch (Throwable th2) {
                        th = th2;
                        cursor.close();
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    cursor = query;
                    cursor.close();
                    throw th;
                }
            }
            j = -1;
            query.close();
        } catch (Exception e3) {
            e = e3;
            cursor = null;
            q.d("ContactLookup", "getContactIDFromNumber(): " + e);
            if (cursor != null || cursor.isClosed()) {
                j = -1;
            } else {
                cursor.close();
                j = -1;
            }
            return j;
        } catch (Throwable th4) {
            th = th4;
            cursor = null;
            if (!(cursor == null || cursor.isClosed())) {
                cursor.close();
            }
            throw th;
        }
        return j;
    }

    public static long b(String str) {
        long j;
        Object e;
        Throwable th;
        Cursor cursor = null;
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        Cursor query;
        try {
            if (a(str) != -1) {
                query = FgVoIP.U().getContentResolver().query(RawContacts.CONTENT_URI, new String[]{"_id"}, "contact_id=?", new String[]{String.valueOf(r10)}, null);
                if (query != null) {
                    try {
                        if (query.moveToFirst()) {
                            cursor = query;
                            j = query.getLong(query.getColumnIndex("_id"));
                        }
                    } catch (Exception e2) {
                        e = e2;
                        try {
                            q.d("ContactLookup", "getRawContactIDFromNumber(): " + e);
                            if (query != null) {
                            }
                            j = -1;
                            return j;
                        } catch (Throwable th2) {
                            th = th2;
                            query.close();
                            throw th;
                        }
                    }
                }
                cursor = query;
                j = -1;
            } else {
                j = -1;
            }
            if (!(cursor == null || cursor.isClosed())) {
                cursor.close();
            }
        } catch (Exception e3) {
            e = e3;
            query = null;
            q.d("ContactLookup", "getRawContactIDFromNumber(): " + e);
            if (query != null || query.isClosed()) {
                j = -1;
            } else {
                query.close();
                j = -1;
            }
            return j;
        } catch (Throwable th3) {
            th = th3;
            query = null;
            if (!(query == null || query.isClosed())) {
                query.close();
            }
            throw th;
        }
        return j;
    }

    public static String a(String str, String str2) {
        Object e;
        Throwable th;
        if (TextUtils.isEmpty(str) || str.equals(FgVoIP.U().getString(com.fgmicrotec.mobile.android.fgvoip.f.k.call_log_blocked_number)) || str.equals("-1") || str.equals("-2")) {
            return FgVoIP.U().getString(com.fgmicrotec.mobile.android.fgvoip.f.k.call_log_blocked_number);
        }
        if (FgVoIP.U().e(str)) {
            return FgVoIP.U().getString(com.fgmicrotec.mobile.android.fgvoip.f.k.call_screen_emergency_call);
        }
        Cursor query;
        try {
            query = FgVoIP.U().getContentResolver().query(Uri.withAppendedPath(PhoneLookup.CONTENT_FILTER_URI, Uri.encode(str)), new String[]{"number", "display_name"}, null, null, null);
            if (query != null) {
                try {
                    if (query.moveToFirst()) {
                        str2 = query.getString(query.getColumnIndex("display_name"));
                    }
                } catch (Exception e2) {
                    e = e2;
                    try {
                        q.d("ContactLookup", "getContactNameFromNumber(): " + e);
                        if (query == null && !query.isClosed()) {
                            query.close();
                            return str2;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        query.close();
                        throw th;
                    }
                }
            }
            if (query == null || query.isClosed()) {
                return str2;
            }
            query.close();
            return str2;
        } catch (Exception e3) {
            e = e3;
            query = null;
            q.d("ContactLookup", "getContactNameFromNumber(): " + e);
            return query == null ? str2 : str2;
        } catch (Throwable th3) {
            th = th3;
            query = null;
            if (!(query == null || query.isClosed())) {
                query.close();
            }
            throw th;
        }
    }

    public static Uri d(String str) {
        Object e;
        Throwable th;
        Uri uri = System.DEFAULT_RINGTONE_URI;
        if (TextUtils.isEmpty(str)) {
            return uri;
        }
        Uri parse;
        Uri withAppendedPath = Uri.withAppendedPath(PhoneLookup.CONTENT_FILTER_URI, Uri.encode(str));
        Cursor query;
        try {
            query = FgVoIP.U().getContentResolver().query(withAppendedPath, new String[]{"custom_ringtone"}, null, null, null);
            if (query != null) {
                try {
                    if (query.moveToFirst()) {
                        int columnIndex = query.getColumnIndex("custom_ringtone");
                        if (!query.isNull(columnIndex)) {
                            parse = Uri.parse(query.getString(columnIndex));
                            if (!(query == null || query.isClosed())) {
                                query.close();
                            }
                            return parse;
                        }
                    }
                } catch (Exception e2) {
                    e = e2;
                    try {
                        q.d("ContactLookup", "getContactCustomRingtoneFromNumber(): " + e);
                        if (query != null) {
                        }
                        parse = uri;
                        return parse;
                    } catch (Throwable th2) {
                        th = th2;
                        query.close();
                        throw th;
                    }
                }
            }
            parse = uri;
            query.close();
        } catch (Exception e3) {
            e = e3;
            query = null;
            q.d("ContactLookup", "getContactCustomRingtoneFromNumber(): " + e);
            if (query != null || query.isClosed()) {
                parse = uri;
            } else {
                query.close();
                parse = uri;
            }
            return parse;
        } catch (Throwable th3) {
            th = th3;
            query = null;
            if (!(query == null || query.isClosed())) {
                query.close();
            }
            throw th;
        }
        return parse;
    }
}
