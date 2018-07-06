package com.mavenir.android.messaging.provider;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.MatrixCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.provider.Contacts.Phones;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.text.TextUtils;
import android.util.Log;
import com.fgmicrotec.mobile.android.fgmag.VoIP;
import com.mavenir.android.messaging.provider.g.e;
import com.mavenir.android.messaging.provider.g.g;
import java.util.ArrayList;
import java.util.HashMap;

public class SmsProvider extends ContentProvider {
    private static final Uri a = Uri.parse("content://com.mavenir.provider.mingle.sms");
    private static final Uri b = Uri.parse("content://com.mavenir.provider.mingle.sms/icc");
    private static final Integer c = Integer.valueOf(1);
    private static final String[] d = new String[]{"person"};
    private static final String[] e = new String[]{"service_center_address", "address", "message_class", "body", "date", "status", "index_on_icc", "is_status_report", "transport_type", "type", "locked", "error_code", "_id"};
    private static final HashMap<String, String> g = new HashMap();
    private static final String[] h = new String[]{"_id"};
    private static final UriMatcher i = new UriMatcher(-1);
    private SQLiteOpenHelper f;

    static {
        i.addURI("com.mavenir.provider.mingle.sms", null, 0);
        i.addURI("com.mavenir.provider.mingle.sms", "#", 1);
        i.addURI("com.mavenir.provider.mingle.sms", "inbox", 2);
        i.addURI("com.mavenir.provider.mingle.sms", "inbox/#", 3);
        i.addURI("com.mavenir.provider.mingle.sms", "sent", 4);
        i.addURI("com.mavenir.provider.mingle.sms", "sent/#", 5);
        i.addURI("com.mavenir.provider.mingle.sms", "draft", 6);
        i.addURI("com.mavenir.provider.mingle.sms", "draft/#", 7);
        i.addURI("com.mavenir.provider.mingle.sms", "outbox", 8);
        i.addURI("com.mavenir.provider.mingle.sms", "outbox/#", 9);
        i.addURI("com.mavenir.provider.mingle.sms", "undelivered", 27);
        i.addURI("com.mavenir.provider.mingle.sms", "failed", 24);
        i.addURI("com.mavenir.provider.mingle.sms", "failed/#", 25);
        i.addURI("com.mavenir.provider.mingle.sms", "queued", 26);
        i.addURI("com.mavenir.provider.mingle.sms", "conversations", 10);
        i.addURI("com.mavenir.provider.mingle.sms", "conversations/*", 11);
        i.addURI("com.mavenir.provider.mingle.sms", "raw", 15);
        i.addURI("com.mavenir.provider.mingle.sms", "attachments", 16);
        i.addURI("com.mavenir.provider.mingle.sms", "attachments/#", 17);
        i.addURI("com.mavenir.provider.mingle.sms", "threadID", 18);
        i.addURI("com.mavenir.provider.mingle.sms", "threadID/*", 19);
        i.addURI("com.mavenir.provider.mingle.sms", "status/#", 20);
        i.addURI("com.mavenir.provider.mingle.sms", "sr_pending", 21);
        i.addURI("com.mavenir.provider.mingle.sms", "icc", 22);
        i.addURI("com.mavenir.provider.mingle.sms", "icc/#", 23);
        i.addURI("com.mavenir.provider.mingle.sms", "sim", 22);
        i.addURI("com.mavenir.provider.mingle.sms", "sim/#", 23);
        g.put("snippet", "sms.body AS snippet");
        g.put("thread_id", "sms.thread_id AS thread_id");
        g.put("msg_count", "groups.msg_count AS msg_count");
        g.put("delta", null);
    }

    public boolean onCreate() {
        this.f = f.a(getContext());
        return true;
    }

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        String[] strArr3;
        String str3;
        SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
        switch (i.match(uri)) {
            case 0:
                a(sQLiteQueryBuilder, 0);
                strArr3 = strArr;
                break;
            case 1:
                sQLiteQueryBuilder.setTables("sms");
                sQLiteQueryBuilder.appendWhere("(_id = " + ((String) uri.getPathSegments().get(0)) + ")");
                strArr3 = strArr;
                break;
            case 2:
                a(sQLiteQueryBuilder, 1);
                strArr3 = strArr;
                break;
            case 3:
            case 5:
            case 7:
            case 9:
            case VoIP.ERR_NO_SRTP_AGREEMENT /*25*/:
                sQLiteQueryBuilder.setTables("sms");
                sQLiteQueryBuilder.appendWhere("(_id = " + ((String) uri.getPathSegments().get(1)) + ")");
                strArr3 = strArr;
                break;
            case 4:
                a(sQLiteQueryBuilder, 2);
                strArr3 = strArr;
                break;
            case 6:
                a(sQLiteQueryBuilder, 3);
                strArr3 = strArr;
                break;
            case 8:
                a(sQLiteQueryBuilder, 4);
                strArr3 = strArr;
                break;
            case 10:
                sQLiteQueryBuilder.setTables("sms, (SELECT thread_id AS group_thread_id, MAX(date)AS group_date,COUNT(*) AS msg_count FROM sms GROUP BY thread_id) AS groups");
                sQLiteQueryBuilder.appendWhere("sms.thread_id = groups.group_thread_id AND sms.date =groups.group_date");
                sQLiteQueryBuilder.setProjectionMap(g);
                strArr3 = strArr;
                break;
            case 11:
                try {
                    int parseInt = Integer.parseInt((String) uri.getPathSegments().get(1));
                    if (Log.isLoggable("SmsProvider", 2)) {
                        Log.d("SmsProvider", "query conversations: threadID=" + parseInt);
                    }
                    sQLiteQueryBuilder.setTables("sms");
                    sQLiteQueryBuilder.appendWhere("thread_id = " + parseInt);
                    strArr3 = strArr;
                    break;
                } catch (Exception e) {
                    Log.e("SmsProvider", "Bad conversation thread id: " + ((String) uri.getPathSegments().get(1)));
                    return null;
                }
            case 15:
                sQLiteQueryBuilder.setTables("raw");
                strArr3 = strArr;
                break;
            case 16:
                sQLiteQueryBuilder.setTables("attachments");
                strArr3 = strArr;
                break;
            case 17:
                sQLiteQueryBuilder.setTables("attachments");
                sQLiteQueryBuilder.appendWhere("(sms_id = " + ((String) uri.getPathSegments().get(1)) + ")");
                strArr3 = strArr;
                break;
            case 19:
                sQLiteQueryBuilder.setTables("canonical_addresses");
                if (strArr != null) {
                    strArr3 = strArr;
                    break;
                }
                strArr3 = h;
                break;
            case VoIP.ERR_SIP_TIMED_OUT /*20*/:
                sQLiteQueryBuilder.setTables("sms");
                sQLiteQueryBuilder.appendWhere("(_id = " + ((String) uri.getPathSegments().get(1)) + ")");
                strArr3 = strArr;
                break;
            case VoIP.ERR_SESSION_NOT_FOUND /*21*/:
                sQLiteQueryBuilder.setTables("sr_pending");
                strArr3 = strArr;
                break;
            case VoIP.ERR_TRANSPORT_ERROR /*22*/:
                return a();
            case VoIP.ERR_SOCKET_CONNECTION_BROKE_NET_DOWN /*23*/:
                return a((String) uri.getPathSegments().get(1));
            case VoIP.ERR_SDP_NEGOTIATION_FAILED /*24*/:
                a(sQLiteQueryBuilder, 5);
                strArr3 = strArr;
                break;
            case VoIP.ERR_SOCKET_CONNECTION_TLS_ERROR /*26*/:
                a(sQLiteQueryBuilder, 6);
                strArr3 = strArr;
                break;
            case VoIP.ERR_SIP_BYE_TIMEOUT /*27*/:
                a(sQLiteQueryBuilder);
                strArr3 = strArr;
                break;
            default:
                Log.e("SmsProvider", "Invalid request: " + uri);
                return null;
        }
        if (!TextUtils.isEmpty(str2)) {
            str3 = str2;
        } else if (sQLiteQueryBuilder.getTables().equals("sms")) {
            str3 = "date DESC";
        } else {
            str3 = null;
        }
        Cursor query = sQLiteQueryBuilder.query(this.f.getReadableDatabase(), strArr3, str, strArr2, null, null, str3);
        query.setNotificationUri(getContext().getContentResolver(), a);
        return query;
    }

    private Object[] a(SmsMessage smsMessage, int i) {
        return new Object[]{smsMessage.getServiceCenterAddress(), smsMessage.getDisplayOriginatingAddress(), String.valueOf(smsMessage.getMessageClass()), smsMessage.getDisplayMessageBody(), Long.valueOf(smsMessage.getTimestampMillis()), Integer.valueOf(-1), Integer.valueOf(smsMessage.getIndexOnIcc()), Boolean.valueOf(smsMessage.isStatusReportMessage()), "sms", Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(i)};
    }

    private Cursor a(String str) {
        try {
            int parseInt = Integer.parseInt(str);
            SmsManager.getDefault();
            SmsMessage smsMessage = (SmsMessage) new ArrayList().get(parseInt);
            if (smsMessage == null) {
                throw new IllegalArgumentException("Message not retrieved. ID: " + str);
            }
            Cursor matrixCursor = new MatrixCursor(e, 1);
            matrixCursor.addRow(a(smsMessage, 0));
            return a(matrixCursor);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Bad SMS ICC ID: " + str);
        }
    }

    private Cursor a() {
        SmsManager.getDefault();
        ArrayList arrayList = new ArrayList();
        int size = arrayList.size();
        Cursor matrixCursor = new MatrixCursor(e, size);
        for (int i = 0; i < size; i++) {
            SmsMessage smsMessage = (SmsMessage) arrayList.get(i);
            if (smsMessage != null) {
                matrixCursor.addRow(a(smsMessage, i));
            }
        }
        return a(matrixCursor);
    }

    private Cursor a(Cursor cursor) {
        cursor.setNotificationUri(getContext().getContentResolver(), b);
        return cursor;
    }

    private void a(SQLiteQueryBuilder sQLiteQueryBuilder, int i) {
        sQLiteQueryBuilder.setTables("sms");
        if (i != 0) {
            sQLiteQueryBuilder.appendWhere("type=" + i);
        }
    }

    private void a(SQLiteQueryBuilder sQLiteQueryBuilder) {
        sQLiteQueryBuilder.setTables("sms");
        sQLiteQueryBuilder.appendWhere("(type=4 OR type=5 OR type=6)");
    }

    public String getType(Uri uri) {
        switch (uri.getPathSegments().size()) {
            case 0:
                return "vnd.android.cursor.dir/sms";
            case 1:
                try {
                    Integer.parseInt((String) uri.getPathSegments().get(0));
                    return "vnd.android.cursor.item/sms";
                } catch (NumberFormatException e) {
                    return "vnd.android.cursor.dir/sms";
                }
            case 2:
                if (((String) uri.getPathSegments().get(0)).equals("conversations")) {
                    return "vnd.android.cursor.item/sms-chat";
                }
                return "vnd.android.cursor.item/sms";
            default:
                return null;
        }
    }

    public Uri insert(Uri uri, ContentValues contentValues) {
        String str;
        int i;
        Throwable e;
        long insert;
        ContentValues contentValues2;
        Uri parse;
        String str2 = "sms";
        switch (i.match(uri)) {
            case 0:
                Integer asInteger = contentValues.getAsInteger("type");
                if (asInteger == null) {
                    str = str2;
                    i = 1;
                    break;
                }
                str = str2;
                i = asInteger.intValue();
                break;
            case 2:
                str = str2;
                i = 1;
                break;
            case 4:
                str = str2;
                i = 2;
                break;
            case 6:
                str = str2;
                i = 3;
                break;
            case 8:
                str = str2;
                i = 4;
                break;
            case 15:
                str = "raw";
                i = 0;
                break;
            case 16:
                str = "attachments";
                i = 0;
                break;
            case 18:
                str = "canonical_addresses";
                i = 0;
                break;
            case VoIP.ERR_SESSION_NOT_FOUND /*21*/:
                str = "sr_pending";
                i = 0;
                break;
            case VoIP.ERR_SDP_NEGOTIATION_FAILED /*24*/:
                str = str2;
                i = 5;
                break;
            case VoIP.ERR_SOCKET_CONNECTION_TLS_ERROR /*26*/:
                str = str2;
                i = 6;
                break;
            default:
                Log.e("SmsProvider", "Invalid request: " + uri);
                return null;
        }
        SQLiteDatabase writableDatabase = this.f.getWritableDatabase();
        if (str.equals("sms")) {
            Object obj;
            Object obj2 = null;
            if (contentValues == null) {
                contentValues = new ContentValues(1);
                obj = 1;
                obj2 = 1;
            } else {
                ContentValues contentValues3 = new ContentValues(contentValues);
                if (!contentValues.containsKey("date")) {
                    obj2 = 1;
                }
                Object obj3;
                if (contentValues.containsKey("type")) {
                    contentValues = contentValues3;
                    obj3 = obj2;
                    obj2 = null;
                    obj = obj3;
                } else {
                    contentValues = contentValues3;
                    obj3 = obj2;
                    int i2 = 1;
                    obj = obj3;
                }
            }
            if (obj != null) {
                contentValues.put("date", new Long(System.currentTimeMillis()));
            }
            if (!(obj2 == null || i == 0)) {
                contentValues.put("type", Integer.valueOf(i));
            }
            Long asLong = contentValues.getAsLong("thread_id");
            String asString = contentValues.getAsString("address");
            if ((asLong == null || asLong.longValue() == 0) && !TextUtils.isEmpty(asString)) {
                contentValues.put("thread_id", Long.valueOf(g.a(getContext(), asString)));
            }
            if (contentValues.getAsInteger("type").intValue() == 3) {
                writableDatabase.delete("sms", "thread_id=? AND type=?", new String[]{contentValues.getAsString("thread_id"), Integer.toString(3)});
            }
            if (i != 1) {
                contentValues.put("read", c);
            } else if (contentValues.getAsLong("person") == null && !TextUtils.isEmpty(asString)) {
                Uri withAppendedPath = Uri.withAppendedPath(Phones.CONTENT_FILTER_URL, Uri.encode(asString));
                Cursor query;
                try {
                    query = getContext().getContentResolver().query(withAppendedPath, d, null, null, null);
                    try {
                        if (query.moveToFirst()) {
                            contentValues.put("person", Long.valueOf(query.getLong(0)));
                        }
                        if (query != null) {
                            query.close();
                        }
                    } catch (Exception e2) {
                        e = e2;
                        try {
                            Log.e("SmsProvider", "insert: query contact uri " + withAppendedPath + " caught ", e);
                            if (query != null) {
                                query.close();
                            }
                            insert = writableDatabase.insert(str, "body", contentValues);
                            if (str == "sms") {
                                contentValues2 = new ContentValues();
                                contentValues2.put("_id", Long.valueOf(insert));
                                contentValues2.put("index_text", contentValues.getAsString("body"));
                                contentValues2.put("source_id", Long.valueOf(insert));
                                contentValues2.put("table_to_use", Integer.valueOf(1));
                                writableDatabase.insert("words", "index_text", contentValues2);
                            }
                            if (insert > 0) {
                                if (str == "sms") {
                                    parse = Uri.parse("content://com.mavenir.provider.mingle.sms/" + insert);
                                } else {
                                    parse = Uri.parse("content://com.mavenir.provider.mingle.sms/" + str + "/" + insert);
                                }
                                if (Log.isLoggable("SmsProvider", 2)) {
                                    Log.d("SmsProvider", "insert " + parse + " succeeded");
                                }
                                a(parse);
                                return parse;
                            }
                            Log.e("SmsProvider", "insert: failed! " + contentValues.toString());
                            return null;
                        } catch (Throwable th) {
                            e = th;
                            if (query != null) {
                                query.close();
                            }
                            throw e;
                        }
                    }
                } catch (Exception e3) {
                    e = e3;
                    query = null;
                    Log.e("SmsProvider", "insert: query contact uri " + withAppendedPath + " caught ", e);
                    if (query != null) {
                        query.close();
                    }
                    insert = writableDatabase.insert(str, "body", contentValues);
                    if (str == "sms") {
                        contentValues2 = new ContentValues();
                        contentValues2.put("_id", Long.valueOf(insert));
                        contentValues2.put("index_text", contentValues.getAsString("body"));
                        contentValues2.put("source_id", Long.valueOf(insert));
                        contentValues2.put("table_to_use", Integer.valueOf(1));
                        writableDatabase.insert("words", "index_text", contentValues2);
                    }
                    if (insert > 0) {
                        Log.e("SmsProvider", "insert: failed! " + contentValues.toString());
                        return null;
                    }
                    if (str == "sms") {
                        parse = Uri.parse("content://com.mavenir.provider.mingle.sms/" + str + "/" + insert);
                    } else {
                        parse = Uri.parse("content://com.mavenir.provider.mingle.sms/" + insert);
                    }
                    if (Log.isLoggable("SmsProvider", 2)) {
                        Log.d("SmsProvider", "insert " + parse + " succeeded");
                    }
                    a(parse);
                    return parse;
                } catch (Throwable th2) {
                    e = th2;
                    query = null;
                    if (query != null) {
                        query.close();
                    }
                    throw e;
                }
            }
        } else if (contentValues == null) {
            contentValues = new ContentValues(1);
        }
        insert = writableDatabase.insert(str, "body", contentValues);
        if (str == "sms") {
            contentValues2 = new ContentValues();
            contentValues2.put("_id", Long.valueOf(insert));
            contentValues2.put("index_text", contentValues.getAsString("body"));
            contentValues2.put("source_id", Long.valueOf(insert));
            contentValues2.put("table_to_use", Integer.valueOf(1));
            writableDatabase.insert("words", "index_text", contentValues2);
        }
        if (insert > 0) {
            if (str == "sms") {
                parse = Uri.parse("content://com.mavenir.provider.mingle.sms/" + insert);
            } else {
                parse = Uri.parse("content://com.mavenir.provider.mingle.sms/" + str + "/" + insert);
            }
            if (Log.isLoggable("SmsProvider", 2)) {
                Log.d("SmsProvider", "insert " + parse + " succeeded");
            }
            a(parse);
            return parse;
        }
        Log.e("SmsProvider", "insert: failed! " + contentValues.toString());
        return null;
    }

    public int delete(Uri uri, String str, String[] strArr) {
        int match = i.match(uri);
        SQLiteDatabase writableDatabase = this.f.getWritableDatabase();
        switch (match) {
            case 0:
                match = writableDatabase.delete("sms", str, strArr);
                if (match != 0) {
                    f.a(writableDatabase, str, strArr);
                    break;
                }
                break;
            case 1:
                try {
                    match = f.a(writableDatabase, Integer.parseInt((String) uri.getPathSegments().get(0)));
                    break;
                } catch (Exception e) {
                    throw new IllegalArgumentException("Bad message id: " + ((String) uri.getPathSegments().get(0)));
                }
            case 11:
                try {
                    int parseInt = Integer.parseInt((String) uri.getPathSegments().get(1));
                    match = writableDatabase.delete("sms", DatabaseUtils.concatenateWhere("thread_id=" + parseInt, str), strArr);
                    f.a(writableDatabase, (long) parseInt);
                    break;
                } catch (Exception e2) {
                    throw new IllegalArgumentException("Bad conversation thread id: " + ((String) uri.getPathSegments().get(1)));
                }
            case 15:
                match = writableDatabase.delete("raw", str, strArr);
                break;
            case VoIP.ERR_SESSION_NOT_FOUND /*21*/:
                match = writableDatabase.delete("sr_pending", str, strArr);
                break;
            case VoIP.ERR_SOCKET_CONNECTION_BROKE_NET_DOWN /*23*/:
                return b((String) uri.getPathSegments().get(1));
            default:
                throw new IllegalArgumentException("Unknown URL");
        }
        if (match <= 0) {
            return match;
        }
        a(uri);
        return match;
    }

    private int b(String str) {
        return 0;
    }

    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        String str2;
        String str3;
        int update;
        Object e;
        String str4 = "sms";
        SQLiteDatabase writableDatabase = this.f.getWritableDatabase();
        switch (i.match(uri)) {
            case 0:
            case 2:
            case 4:
            case 6:
            case 8:
            case 10:
            case VoIP.ERR_SDP_NEGOTIATION_FAILED /*24*/:
            case VoIP.ERR_SOCKET_CONNECTION_TLS_ERROR /*26*/:
                str2 = null;
                str3 = str4;
                break;
            case 1:
                str2 = "_id=" + ((String) uri.getPathSegments().get(0));
                str3 = str4;
                break;
            case 3:
            case 5:
            case 7:
            case 9:
            case VoIP.ERR_NO_SRTP_AGREEMENT /*25*/:
                str2 = "_id=" + ((String) uri.getPathSegments().get(1));
                str3 = str4;
                break;
            case 11:
                str2 = (String) uri.getPathSegments().get(1);
                try {
                    Integer.parseInt(str2);
                    str2 = "thread_id=" + str2;
                    str3 = str4;
                    break;
                } catch (Exception e2) {
                    Log.e("SmsProvider", "Bad conversation thread id: " + str2);
                    str2 = null;
                    str3 = str4;
                    break;
                }
            case 15:
                str3 = "raw";
                str2 = null;
                break;
            case VoIP.ERR_SIP_TIMED_OUT /*20*/:
                str2 = "_id=" + ((String) uri.getPathSegments().get(1));
                str3 = str4;
                break;
            case VoIP.ERR_SESSION_NOT_FOUND /*21*/:
                str3 = "sr_pending";
                str2 = null;
                break;
            default:
                throw new UnsupportedOperationException("URI " + uri + " not supported");
        }
        try {
            update = writableDatabase.update(str3, contentValues, DatabaseUtils.concatenateWhere(str, str2), strArr);
            if (update > 0) {
                try {
                    if (Log.isLoggable("SmsProvider", 2)) {
                        Log.d("SmsProvider", "update " + uri + " succeeded");
                    }
                    a(uri);
                } catch (Exception e3) {
                    e = e3;
                    Log.e("SmsProvider", "update(): " + e);
                    return update;
                }
            }
        } catch (Exception e4) {
            e = e4;
            update = 0;
            Log.e("SmsProvider", "update(): " + e);
            return update;
        }
        return update;
    }

    private void a(Uri uri) {
        ContentResolver contentResolver = getContext().getContentResolver();
        contentResolver.notifyChange(uri, null);
        contentResolver.notifyChange(e.a, null);
        contentResolver.notifyChange(Uri.parse("content://com.mavenir.provider.mingle.mms-sms/conversations/"), null);
    }
}
