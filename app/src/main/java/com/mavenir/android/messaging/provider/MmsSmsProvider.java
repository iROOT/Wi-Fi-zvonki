package com.mavenir.android.messaging.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import com.mavenir.android.messaging.provider.g.d;
import com.mavenir.android.messaging.provider.g.e;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MmsSmsProvider extends ContentProvider {
    protected static final UriMatcher a = new UriMatcher(-1);
    private static final String[] b = new String[]{"_id", "date", "date_sent", "read", "thread_id", "locked"};
    private static final String[] c = new String[]{"ct_cls", "ct_l", "ct_t", "d_rpt", "exp", "m_cls", "m_id", "m_size", "m_type", "msg_box", "pri", "read_status", "resp_st", "resp_txt", "retr_st", "retr_txt_cs", "rpt_a", "rr", "st", "sub", "sub_cs", "tr_id", "v"};
    private static final String[] d = new String[]{"address", "body", "person", "reply_path_present", "service_center", "status", "subject", "type", "error_code"};
    private static final String[] e = new String[]{"_id", "date", "recipient_ids", "message_count"};
    private static final String[] f = new String[]{"address"};
    private static final String[] g = new String[]{"_id", "address"};
    private static final String[] h = new String[((b.length + c.length) + d.length)];
    private static final Set<String> i = new HashSet();
    private static final Set<String> j = new HashSet();
    private static final String[] k = new String[]{"_id"};
    private static final String[] l = new String[0];
    private static final String[] m = new String[1];
    private SQLiteOpenHelper n;
    private boolean o;

    static {
        a.addURI("com.mavenir.provider.mingle.mms-sms", "conversations", 0);
        a.addURI("com.mavenir.provider.mingle.mms-sms", "complete-conversations", 7);
        a.addURI("com.mavenir.provider.mingle.mms-sms", "conversations/#", 1);
        a.addURI("com.mavenir.provider.mingle.mms-sms", "conversations/#/recipients", 2);
        a.addURI("com.mavenir.provider.mingle.mms-sms", "conversations/#/subject", 9);
        a.addURI("com.mavenir.provider.mingle.mms-sms", "conversations/obsolete", 11);
        a.addURI("com.mavenir.provider.mingle.mms-sms", "messages/byphone/*", 3);
        a.addURI("com.mavenir.provider.mingle.mms-sms", "threadID", 4);
        a.addURI("com.mavenir.provider.mingle.mms-sms", "canonical-address/#", 5);
        a.addURI("com.mavenir.provider.mingle.mms-sms", "canonical-addresses", 13);
        a.addURI("com.mavenir.provider.mingle.mms-sms", "search", 14);
        a.addURI("com.mavenir.provider.mingle.mms-sms", "searchSuggest", 15);
        a.addURI("com.mavenir.provider.mingle.mms-sms", "pending", 6);
        a.addURI("com.mavenir.provider.mingle.mms-sms", "undelivered", 8);
        a.addURI("com.mavenir.provider.mingle.mms-sms", "notifications", 10);
        a.addURI("com.mavenir.provider.mingle.mms-sms", "draft", 12);
        a.addURI("com.mavenir.provider.mingle.mms-sms", "locked", 16);
        a.addURI("com.mavenir.provider.mingle.mms-sms", "locked/#", 17);
        a.addURI("com.mavenir.provider.mingle.mms-sms", "messageIdToThread", 18);
        b();
    }

    public boolean onCreate() {
        this.n = f.a(getContext());
        this.o = true;
        return true;
    }

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        SQLiteDatabase readableDatabase = this.n.getReadableDatabase();
        Cursor cursor = null;
        String str3;
        String str4;
        switch (a.match(uri)) {
            case 0:
                String queryParameter = uri.getQueryParameter("simple");
                if (queryParameter != null && queryParameter.equals("true")) {
                    Object queryParameter2 = uri.getQueryParameter("thread_type");
                    if (!TextUtils.isEmpty(queryParameter2)) {
                        str = a(str, "type=" + queryParameter2);
                    }
                    cursor = a(strArr, str, strArr2, str2);
                    break;
                }
                cursor = b(strArr, str, str2);
                break;
                break;
            case 1:
                cursor = a((String) uri.getPathSegments().get(1), strArr, str, str2);
                break;
            case 2:
                cursor = a((String) uri.getPathSegments().get(1), strArr, str, strArr2, str2);
                break;
            case 3:
                cursor = b((String) uri.getPathSegments().get(2), strArr, str, str2);
                break;
            case 4:
                cursor = b(uri.getQueryParameters("recipient"));
                break;
            case 5:
                str3 = "_id=" + ((String) uri.getPathSegments().get(1));
                if (!TextUtils.isEmpty(str)) {
                    str3 = str3 + " AND " + str;
                }
                cursor = readableDatabase.query("canonical_addresses", f, str3, strArr2, null, null, str2);
                break;
            case 6:
                String str5;
                Object queryParameter3 = uri.getQueryParameter("protocol");
                Object queryParameter4 = uri.getQueryParameter("message");
                int i = TextUtils.isEmpty(queryParameter3) ? -1 : queryParameter3.equals("sms") ? 0 : 1;
                str4 = i != -1 ? "proto_type=" + i : " 0=0 ";
                if (!TextUtils.isEmpty(queryParameter4)) {
                    str4 = str4 + " AND msg_id=" + queryParameter4;
                }
                str3 = TextUtils.isEmpty(str) ? str4 : "(" + str4 + ") AND " + str;
                if (TextUtils.isEmpty(str2)) {
                    str5 = "due_time";
                } else {
                    str5 = str2;
                }
                cursor = readableDatabase.query("pending_msgs", null, str3, strArr2, null, null, str5);
                break;
            case 7:
                cursor = d(strArr, str, str2);
                break;
            case 8:
                cursor = b(strArr, str, strArr2, str2);
                break;
            case 9:
                cursor = a((String) uri.getPathSegments().get(1), strArr, str, strArr2, str2);
                break;
            case 12:
                cursor = a(strArr, str, str2);
                break;
            case 13:
                cursor = readableDatabase.query("canonical_addresses", g, str, strArr2, null, null, str2);
                break;
            case 14:
                if (str2 == null && str == null && strArr2 == null && strArr == null) {
                    str4 = uri.getQueryParameter("pattern") + "*";
                    try {
                        cursor = readableDatabase.rawQuery("SELECT sms._id AS _id,thread_id,address,body,date,date_sent,index_text,words._id FROM sms,words WHERE (index_text MATCH ? AND sms._id=words.source_id AND words.table_to_use=1) UNION SELECT pdu._id,thread_id,addr.address,part.text AS body,pdu.date,pdu.date_sent,index_text,words._id FROM pdu,part,addr,words WHERE ((part.mid=pdu._id) AND (addr.msg_id=pdu._id) AND (part.ct='text/plain') AND (index_text MATCH ?) AND (part._id = words.source_id) AND (words.table_to_use=2)) GROUP BY thread_id ORDER BY thread_id ASC, date DESC", new String[]{str4, str4});
                        break;
                    } catch (Exception e) {
                        Log.e("MmsSmsProvider", "got exception: " + e.toString());
                        break;
                    }
                }
                throw new IllegalArgumentException("do not specify sortOrder, selection, selectionArgs, or projectionwith this query");
            case 15:
                m[0] = uri.getQueryParameter("pattern") + '*';
                if (str2 == null && str == null && strArr2 == null && strArr == null) {
                    cursor = readableDatabase.rawQuery("SELECT snippet(words, '', ' ', '', 1, 1) as snippet FROM words WHERE index_text MATCH ? ORDER BY snippet LIMIT 50;", m);
                    break;
                }
                throw new IllegalArgumentException("do not specify sortOrder, selection, selectionArgs, or projectionwith this query");
                break;
            case 16:
                cursor = c(strArr, str, str2);
                break;
            case 17:
                try {
                    cursor = c(strArr, "thread_id=" + Long.toString(Long.parseLong(uri.getLastPathSegment())), str2);
                    break;
                } catch (NumberFormatException e2) {
                    Log.e("MmsSmsProvider", "Thread ID must be a long.");
                    break;
                }
            case 18:
                try {
                    Cursor query;
                    long parseLong = Long.parseLong(uri.getQueryParameter("row_id"));
                    switch (Integer.parseInt(uri.getQueryParameter("table_to_use"))) {
                        case 1:
                            query = readableDatabase.query("sms", new String[]{"thread_id"}, "_id=?", new String[]{String.valueOf(parseLong)}, null, null, null);
                            break;
                        case 2:
                            query = readableDatabase.rawQuery("SELECT thread_id FROM pdu,part WHERE ((part.mid=pdu._id) AND (part._id=?))", new String[]{String.valueOf(parseLong)});
                            break;
                        default:
                            query = cursor;
                            break;
                    }
                    cursor = query;
                    break;
                } catch (NumberFormatException e3) {
                    break;
                }
            default:
                throw new IllegalStateException("Unrecognized URI:" + uri);
        }
        if (cursor != null) {
            cursor.setNotificationUri(getContext().getContentResolver(), e.a);
        }
        return cursor;
    }

    private long a(String str) {
        String[] strArr;
        Throwable th;
        Cursor cursor;
        boolean b = d.b(str);
        boolean c = d.c(str);
        if (b) {
            str = str.toLowerCase();
        }
        String str2 = "address=?";
        if (c) {
            int i;
            StringBuilder append = new StringBuilder().append(str2).append(" OR PHONE_NUMBERS_EQUAL(address, ?, ");
            if (this.o) {
                i = 1;
            } else {
                i = 0;
            }
            str2 = append.append(i).append(")").toString();
            strArr = new String[]{str, str};
        } else {
            strArr = new String[]{str};
        }
        try {
            Cursor query = this.n.getReadableDatabase().query("canonical_addresses", k, str2, strArr, null, null, null);
            try {
                long insert;
                if (query.getCount() == 0) {
                    ContentValues contentValues = new ContentValues(1);
                    contentValues.put("address", str);
                    insert = this.n.getWritableDatabase().insert("canonical_addresses", "address", contentValues);
                    Log.d("MmsSmsProvider", "getSingleAddressId: insert new canonical_address for xxxxxx, _id=" + insert);
                    if (query != null) {
                        query.close();
                    }
                } else {
                    if (query.moveToFirst()) {
                        insert = query.getLong(query.getColumnIndexOrThrow("_id"));
                    } else {
                        insert = -1;
                    }
                    if (query != null) {
                        query.close();
                    }
                }
                return insert;
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
            cursor = null;
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    private Set<Long> a(List<String> list) {
        Set<Long> hashSet = new HashSet(list.size());
        for (String str : list) {
            if (!str.equals("insert-address-token")) {
                long a = a(str);
                if (a != -1) {
                    hashSet.add(Long.valueOf(a));
                } else {
                    Log.e("MmsSmsProvider", "getAddressIds: address ID not found for " + str);
                }
            }
        }
        return hashSet;
    }

    private long[] a(Set<Long> set) {
        int size = set.size();
        long[] jArr = new long[size];
        int i = 0;
        for (Long longValue : set) {
            int i2 = i + 1;
            jArr[i] = longValue.longValue();
            i = i2;
        }
        if (size > 1) {
            Arrays.sort(jArr);
        }
        return jArr;
    }

    private String a(long[] jArr) {
        int length = jArr.length;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            if (i != 0) {
                stringBuilder.append(' ');
            }
            stringBuilder.append(jArr[i]);
        }
        return stringBuilder.toString();
    }

    private void a(String str, int i) {
        ContentValues contentValues = new ContentValues(4);
        long currentTimeMillis = System.currentTimeMillis();
        contentValues.put("date", Long.valueOf(currentTimeMillis - (currentTimeMillis % 1000)));
        contentValues.put("recipient_ids", str);
        if (i > 1) {
            contentValues.put("type", Integer.valueOf(1));
        }
        contentValues.put("message_count", Integer.valueOf(0));
        Log.d("MmsSmsProvider", "insertThread: created new thread_id " + this.n.getWritableDatabase().insert("threads", null, contentValues) + " for recipientIds xxxxxxx");
        getContext().getContentResolver().notifyChange(e.a, null);
    }

    private synchronized Cursor b(List<String> list) {
        Cursor cursor;
        Set<Long> a = a((List) list);
        String str = "";
        if (a.size() == 0) {
            Log.e("MmsSmsProvider", "getThreadId: NO receipients specified -- NOT creating thread", new Exception());
            cursor = null;
        } else {
            String str2;
            if (a.size() == 1) {
                for (Long longValue : a) {
                    str = Long.toString(longValue.longValue());
                }
                str2 = str;
            } else {
                str2 = a(a((Set) a));
            }
            if (Log.isLoggable("MmsSmsProvider", 2)) {
                Log.d("MmsSmsProvider", "getThreadId: recipientIds (selectionArgs) =xxxxxxx");
            }
            String[] strArr = new String[]{str2};
            cursor = this.n.getReadableDatabase().rawQuery("SELECT _id FROM threads WHERE recipient_ids=?", strArr);
            if (cursor.getCount() == 0) {
                cursor.close();
                Log.d("MmsSmsProvider", "getThreadId: create new thread_id for recipients xxxxxxxx");
                a(str2, list.size());
                cursor = this.n.getReadableDatabase().rawQuery("SELECT _id FROM threads WHERE recipient_ids=?", strArr);
            }
            if (cursor.getCount() > 1) {
                Log.w("MmsSmsProvider", "getThreadId: why is cursorCount=" + cursor.getCount());
            }
        }
        return cursor;
    }

    private static String a(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return str2;
        }
        if (TextUtils.isEmpty(str2)) {
            return str;
        }
        return str + " AND " + str2;
    }

    private static String[] a(String[] strArr) {
        return strArr == null ? h : strArr;
    }

    private static String[] b(String[] strArr) {
        return strArr == null ? e : strArr;
    }

    private static String b(String str) {
        return str == null ? "normalized_date ASC" : str;
    }

    private Cursor a(String[] strArr, String str, String[] strArr2, String str2) {
        return this.n.getReadableDatabase().query("threads", strArr, str, strArr2, null, null, " date DESC");
    }

    private Cursor a(String[] strArr, String str, String str2) {
        String[] strArr2 = new String[]{"_id", "thread_id"};
        SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
        SQLiteQueryBuilder sQLiteQueryBuilder2 = new SQLiteQueryBuilder();
        sQLiteQueryBuilder.setTables("pdu");
        sQLiteQueryBuilder2.setTables("sms");
        String buildUnionSubQuery = sQLiteQueryBuilder.buildUnionSubQuery("transport_type", strArr2, i, 1, "mms", a(str, "msg_box=3"), null, null);
        sQLiteQueryBuilder = sQLiteQueryBuilder2;
        String buildUnionSubQuery2 = sQLiteQueryBuilder.buildUnionSubQuery("transport_type", strArr2, j, 1, "sms", a(str, "type=3"), null, null);
        SQLiteQueryBuilder sQLiteQueryBuilder3 = new SQLiteQueryBuilder();
        sQLiteQueryBuilder3.setDistinct(true);
        String buildUnionQuery = sQLiteQueryBuilder3.buildUnionQuery(new String[]{buildUnionSubQuery, buildUnionSubQuery2}, null, null);
        sQLiteQueryBuilder = new SQLiteQueryBuilder();
        sQLiteQueryBuilder.setTables("(" + buildUnionQuery + ")");
        return this.n.getReadableDatabase().rawQuery(sQLiteQueryBuilder.buildQuery(strArr, null, null, null, str2, null), l);
    }

    private Cursor b(String[] strArr, String str, String str2) {
        SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
        SQLiteQueryBuilder sQLiteQueryBuilder2 = new SQLiteQueryBuilder();
        sQLiteQueryBuilder.setTables("pdu");
        sQLiteQueryBuilder2.setTables("sms");
        String[] a = a(strArr);
        String[] a2 = a(h, 1000);
        String[] a3 = a(h, 1);
        String buildUnionSubQuery = sQLiteQueryBuilder.buildUnionSubQuery("transport_type", a2, i, 1, "mms", a(str, "(msg_box != 3)"), "thread_id", "date = MAX(date)");
        String buildUnionSubQuery2 = sQLiteQueryBuilder2.buildUnionSubQuery("transport_type", a3, j, 1, "sms", a(str, "(type != 3)"), "thread_id", "date = MAX(date)");
        SQLiteQueryBuilder sQLiteQueryBuilder3 = new SQLiteQueryBuilder();
        sQLiteQueryBuilder3.setDistinct(true);
        String buildUnionQuery = sQLiteQueryBuilder3.buildUnionQuery(new String[]{buildUnionSubQuery, buildUnionSubQuery2}, null, null);
        sQLiteQueryBuilder = new SQLiteQueryBuilder();
        sQLiteQueryBuilder.setTables("(" + buildUnionQuery + ")");
        return this.n.getReadableDatabase().rawQuery(sQLiteQueryBuilder.buildQuery(a, null, "tid", "normalized_date = MAX(normalized_date)", str2, null), l);
    }

    private Cursor c(String[] strArr, String str, String str2) {
        SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
        SQLiteQueryBuilder sQLiteQueryBuilder2 = new SQLiteQueryBuilder();
        sQLiteQueryBuilder.setTables("pdu");
        sQLiteQueryBuilder2.setTables("sms");
        String[] strArr2 = new String[]{"_id"};
        String buildUnionSubQuery = sQLiteQueryBuilder.buildUnionSubQuery("transport_type", strArr2, null, 1, "mms", str, "_id", "locked=1");
        String buildUnionSubQuery2 = sQLiteQueryBuilder2.buildUnionSubQuery("transport_type", strArr2, null, 1, "sms", str, "_id", "locked=1");
        SQLiteQueryBuilder sQLiteQueryBuilder3 = new SQLiteQueryBuilder();
        sQLiteQueryBuilder3.setDistinct(true);
        return this.n.getReadableDatabase().rawQuery(sQLiteQueryBuilder3.buildUnionQuery(new String[]{buildUnionSubQuery, buildUnionSubQuery2}, null, "1"), l);
    }

    private Cursor d(String[] strArr, String str, String str2) {
        return this.n.getReadableDatabase().rawQuery(e(strArr, str, str2), l);
    }

    private String[] a(String[] strArr, int i) {
        int i2 = 0;
        int length = strArr.length;
        String[] strArr2 = new String[(length + 2)];
        strArr2[0] = "thread_id AS tid";
        strArr2[1] = "date * " + i + " AS normalized_date";
        while (i2 < length) {
            strArr2[i2 + 2] = strArr[i2];
            i2++;
        }
        return strArr2;
    }

    private Cursor a(String str, String[] strArr, String str2, String str3) {
        try {
            Long.parseLong(str);
            return this.n.getReadableDatabase().rawQuery(e(strArr, a(str2, "thread_id = " + str), str3), l);
        } catch (NumberFormatException e) {
            Log.e("MmsSmsProvider", "Thread ID must be a Long.");
            return null;
        }
    }

    private Cursor b(String str, String[] strArr, String str2, String str3) {
        String sqlEscapeString = DatabaseUtils.sqlEscapeString(str);
        String a = a(str2, "pdu._id = matching_addresses.address_id");
        String a2 = a(str2, "(address=" + sqlEscapeString + " OR PHONE_NUMBERS_EQUAL(address, " + sqlEscapeString + (this.o ? ", 1))" : ", 0))"));
        SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
        SQLiteQueryBuilder sQLiteQueryBuilder2 = new SQLiteQueryBuilder();
        sQLiteQueryBuilder.setDistinct(true);
        sQLiteQueryBuilder2.setDistinct(true);
        sQLiteQueryBuilder.setTables("pdu, (SELECT _id AS address_id FROM addr WHERE (address=" + sqlEscapeString + " OR PHONE_NUMBERS_EQUAL(addr.address, " + sqlEscapeString + (this.o ? ", 1))) " : ", 0))) ") + "AS matching_addresses");
        sQLiteQueryBuilder2.setTables("sms");
        String[] a3 = a(strArr);
        String buildUnionSubQuery = sQLiteQueryBuilder.buildUnionSubQuery("transport_type", a3, i, 0, "mms", a, null, null);
        String buildUnionSubQuery2 = sQLiteQueryBuilder2.buildUnionSubQuery("transport_type", a3, j, 0, "sms", a2, null, null);
        SQLiteQueryBuilder sQLiteQueryBuilder3 = new SQLiteQueryBuilder();
        sQLiteQueryBuilder3.setDistinct(true);
        return this.n.getReadableDatabase().rawQuery(sQLiteQueryBuilder3.buildUnionQuery(new String[]{buildUnionSubQuery, buildUnionSubQuery2}, str3, null), l);
    }

    private Cursor a(String str, String[] strArr, String str2, String[] strArr2, String str3) {
        try {
            Long.parseLong(str);
            String a = a(str2, "_id=" + str);
            SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
            String[] b = b(strArr);
            sQLiteQueryBuilder.setDistinct(true);
            sQLiteQueryBuilder.setTables("threads");
            return sQLiteQueryBuilder.query(this.n.getReadableDatabase(), b, a, strArr2, str3, null, null);
        } catch (NumberFormatException e) {
            Log.e("MmsSmsProvider", "Thread ID must be a Long.");
            return null;
        }
    }

    private static String a() {
        return "pdu LEFT JOIN pending_msgs ON pdu._id = pending_msgs.msg_id";
    }

    private static String[] c(String[] strArr) {
        String[] strArr2 = new String[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            if (strArr[i].equals("_id")) {
                strArr2[i] = "pdu._id";
            } else {
                strArr2[i] = strArr[i];
            }
        }
        return strArr2;
    }

    private Cursor b(String[] strArr, String str, String[] strArr2, String str2) {
        String[] c = c(strArr);
        SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
        SQLiteQueryBuilder sQLiteQueryBuilder2 = new SQLiteQueryBuilder();
        sQLiteQueryBuilder.setTables(a());
        sQLiteQueryBuilder2.setTables("sms");
        String a = a(str, "msg_box = 4");
        String a2 = a(str, "(type = 4 OR type = 5 OR type = 6)");
        String[] a3 = a(strArr);
        String[] a4 = a(a(c), 1000);
        String[] a5 = a(a3, 1);
        Set hashSet = new HashSet(i);
        hashSet.add("pdu._id");
        hashSet.add("err_type");
        String buildUnionSubQuery = sQLiteQueryBuilder.buildUnionSubQuery("transport_type", a4, hashSet, 1, "mms", a, null, null);
        String buildUnionSubQuery2 = sQLiteQueryBuilder2.buildUnionSubQuery("transport_type", a5, j, 1, "sms", a2, null, null);
        SQLiteQueryBuilder sQLiteQueryBuilder3 = new SQLiteQueryBuilder();
        sQLiteQueryBuilder3.setDistinct(true);
        String buildUnionQuery = sQLiteQueryBuilder3.buildUnionQuery(new String[]{buildUnionSubQuery2, buildUnionSubQuery}, null, null);
        sQLiteQueryBuilder = new SQLiteQueryBuilder();
        sQLiteQueryBuilder.setTables("(" + buildUnionQuery + ")");
        return this.n.getReadableDatabase().rawQuery(sQLiteQueryBuilder.buildQuery(a3, null, null, null, str2, null), l);
    }

    private static String[] b(String[] strArr, int i) {
        int length = strArr.length;
        Object obj = new String[(length + 1)];
        obj[0] = "date * " + i + " AS normalized_date";
        System.arraycopy(strArr, 0, obj, 1, length);
        return obj;
    }

    private static String e(String[] strArr, String str, String str2) {
        String[] c = c(strArr);
        SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
        SQLiteQueryBuilder sQLiteQueryBuilder2 = new SQLiteQueryBuilder();
        sQLiteQueryBuilder.setDistinct(true);
        sQLiteQueryBuilder2.setDistinct(true);
        sQLiteQueryBuilder.setTables(a());
        sQLiteQueryBuilder2.setTables("sms");
        String[] a = a(strArr);
        String[] b = b(a(c), 1000);
        String[] b2 = b(a, 1);
        Set hashSet = new HashSet(i);
        hashSet.add("pdu._id");
        hashSet.add("err_type");
        String buildUnionSubQuery = sQLiteQueryBuilder.buildUnionSubQuery("transport_type", b, hashSet, 0, "mms", a(a(str, "msg_box != 3"), "(msg_box != 3)"), null, null);
        sQLiteQueryBuilder = sQLiteQueryBuilder2;
        b = b2;
        String buildUnionSubQuery2 = sQLiteQueryBuilder.buildUnionSubQuery("transport_type", b, j, 0, "sms", a(str, "(type != 3)"), null, null);
        SQLiteQueryBuilder sQLiteQueryBuilder3 = new SQLiteQueryBuilder();
        sQLiteQueryBuilder3.setDistinct(true);
        String buildUnionQuery = sQLiteQueryBuilder3.buildUnionQuery(new String[]{buildUnionSubQuery2, buildUnionSubQuery}, b(str2), null);
        sQLiteQueryBuilder = new SQLiteQueryBuilder();
        sQLiteQueryBuilder.setTables("(" + buildUnionQuery + ")");
        return sQLiteQueryBuilder.buildQuery(a, null, null, null, str2, null);
    }

    public String getType(Uri uri) {
        return "vnd.android-dir/mms-sms";
    }

    public int delete(Uri uri, String str, String[] strArr) {
        SQLiteDatabase writableDatabase = this.n.getWritableDatabase();
        Context context = getContext();
        int i = 0;
        switch (a.match(uri)) {
            case 0:
                i = MmsProvider.a(context, writableDatabase, str, strArr, uri) + writableDatabase.delete("sms", str, strArr);
                f.a(writableDatabase, null, null);
                break;
            case 1:
                try {
                    long parseLong = Long.parseLong(uri.getLastPathSegment());
                    i = a(uri, str, strArr);
                    f.a(writableDatabase, parseLong);
                    break;
                } catch (NumberFormatException e) {
                    Log.e("MmsSmsProvider", "Thread ID must be a long.");
                    break;
                }
            case 11:
                i = writableDatabase.delete("threads", "_id NOT IN (SELECT DISTINCT thread_id FROM sms where thread_id NOT NULL UNION SELECT DISTINCT thread_id FROM pdu where thread_id NOT NULL)", null);
                break;
            default:
                throw new UnsupportedOperationException("MmsSmsProvider does not support deletes, inserts, or updates for this URI." + uri);
        }
        if (i > 0) {
            context.getContentResolver().notifyChange(e.a, null);
        }
        return i;
    }

    private int a(Uri uri, String str, String[] strArr) {
        String lastPathSegment = uri.getLastPathSegment();
        SQLiteDatabase writableDatabase = this.n.getWritableDatabase();
        lastPathSegment = a(str, "thread_id = " + lastPathSegment);
        return writableDatabase.delete("sms", lastPathSegment, strArr) + MmsProvider.a(getContext(), writableDatabase, lastPathSegment, strArr, uri);
    }

    public Uri insert(Uri uri, ContentValues contentValues) {
        if (a.match(uri) == 6) {
            return Uri.parse(uri + "/" + this.n.getWritableDatabase().insert("pending_msgs", null, contentValues));
        }
        throw new UnsupportedOperationException("MmsSmsProvider does not support deletes, inserts, or updates for this URI." + uri);
    }

    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        int a;
        SQLiteDatabase writableDatabase = this.n.getWritableDatabase();
        switch (a.match(uri)) {
            case 1:
                a = a((String) uri.getPathSegments().get(1), contentValues, str, strArr);
                break;
            case 5:
                String str2 = "_id=" + ((String) uri.getPathSegments().get(1));
                if (!TextUtils.isEmpty(str)) {
                    str2 = str2 + " AND " + str;
                }
                a = writableDatabase.update("canonical_addresses", contentValues, str2, null);
                break;
            case 6:
                a = writableDatabase.update("pending_msgs", contentValues, str, null);
                break;
            default:
                throw new UnsupportedOperationException("MmsSmsProvider does not support deletes, inserts, or updates for this URI." + uri);
        }
        if (a > 0) {
            getContext().getContentResolver().notifyChange(e.a, null);
        }
        return a;
    }

    private int a(String str, ContentValues contentValues, String str2, String[] strArr) {
        try {
            Long.parseLong(str);
            SQLiteDatabase writableDatabase = this.n.getWritableDatabase();
            String a = a(str2, "thread_id=" + str);
            return writableDatabase.update("sms", contentValues, a, strArr) + writableDatabase.update("pdu", contentValues, a, strArr);
        } catch (NumberFormatException e) {
            Log.e("MmsSmsProvider", "Thread ID must be a Long.");
            return 0;
        }
    }

    private static void b() {
        int i;
        int length = b.length;
        int length2 = c.length;
        int length3 = d.length;
        Set<String> hashSet = new HashSet();
        for (i = 0; i < length; i++) {
            i.add(b[i]);
            j.add(b[i]);
            hashSet.add(b[i]);
        }
        for (i = 0; i < length2; i++) {
            i.add(c[i]);
            hashSet.add(c[i]);
        }
        for (i = 0; i < length3; i++) {
            j.add(d[i]);
            hashSet.add(d[i]);
        }
        i = 0;
        for (String str : hashSet) {
            length = i + 1;
            h[i] = str;
            i = length;
        }
    }
}
