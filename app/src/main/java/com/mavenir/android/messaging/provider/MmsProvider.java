package com.mavenir.android.messaging.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import android.util.Log;
import com.mavenir.android.messaging.provider.g.d;
import com.mavenir.android.messaging.provider.g.e;
import com.mavenir.android.messaging.provider.g.g;
import java.io.File;

public class MmsProvider extends ContentProvider {
    private static final UriMatcher a = new UriMatcher(-1);
    private SQLiteOpenHelper b;

    public boolean onCreate() {
        this.b = f.a(getContext());
        return true;
    }

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
        int match = a.match(uri);
        switch (match) {
            case 0:
                a(sQLiteQueryBuilder, 0);
                break;
            case 1:
                sQLiteQueryBuilder.setTables("pdu");
                sQLiteQueryBuilder.appendWhere("_id=" + ((String) uri.getPathSegments().get(0)));
                break;
            case 2:
                a(sQLiteQueryBuilder, 1);
                break;
            case 3:
            case 5:
            case 7:
            case 9:
                sQLiteQueryBuilder.setTables("pdu");
                sQLiteQueryBuilder.appendWhere("_id=" + ((String) uri.getPathSegments().get(1)));
                sQLiteQueryBuilder.appendWhere(" AND msg_box=" + a(match));
                break;
            case 4:
                a(sQLiteQueryBuilder, 2);
                break;
            case 6:
                a(sQLiteQueryBuilder, 3);
                break;
            case 8:
                a(sQLiteQueryBuilder, 4);
                break;
            case 10:
                sQLiteQueryBuilder.setTables("part");
                break;
            case 11:
                sQLiteQueryBuilder.setTables("part");
                sQLiteQueryBuilder.appendWhere("mid=" + ((String) uri.getPathSegments().get(0)));
                break;
            case 12:
                sQLiteQueryBuilder.setTables("part");
                sQLiteQueryBuilder.appendWhere("_id=" + ((String) uri.getPathSegments().get(1)));
                break;
            case 13:
                sQLiteQueryBuilder.setTables("addr");
                sQLiteQueryBuilder.appendWhere("msg_id=" + ((String) uri.getPathSegments().get(0)));
                break;
            case 14:
                sQLiteQueryBuilder.setTables("rate");
                break;
            case 15:
                sQLiteQueryBuilder.setTables("addr INNER JOIN (SELECT P1._id AS id1, P2._id AS id2, P3._id AS id3, ifnull(P2.st, 0) AS delivery_status, ifnull(P3.read_status, 0) AS read_status FROM pdu P1 INNER JOIN pdu P2 ON P1.m_id=P2.m_id AND P2.m_type=134 LEFT JOIN pdu P3 ON P1.m_id=P3.m_id AND P3.m_type=136 UNION SELECT P1._id AS id1, P2._id AS id2, P3._id AS id3, ifnull(P2.st, 0) AS delivery_status, ifnull(P3.read_status, 0) AS read_status FROM pdu P1 INNER JOIN pdu P3 ON P1.m_id=P3.m_id AND P3.m_type=136 LEFT JOIN pdu P2 ON P1.m_id=P2.m_id AND P2.m_type=134) T ON (msg_id=id2 AND type=151) OR (msg_id=id3 AND type=137)");
                sQLiteQueryBuilder.appendWhere("T.id1 = " + uri.getLastPathSegment());
                sQLiteQueryBuilder.setDistinct(true);
                break;
            case 16:
                sQLiteQueryBuilder.setTables("addr join pdu on pdu._id = addr.msg_id");
                sQLiteQueryBuilder.appendWhere("pdu._id = " + uri.getLastPathSegment());
                break;
            case 18:
                sQLiteQueryBuilder.setTables("drm");
                sQLiteQueryBuilder.appendWhere("_id=" + uri.getLastPathSegment());
                break;
            case 19:
                sQLiteQueryBuilder.setTables("pdu group by thread_id");
                break;
            default:
                Log.e("MmsProvider", "query: invalid request: " + uri);
                return null;
        }
        String str3 = TextUtils.isEmpty(str2) ? sQLiteQueryBuilder.getTables().equals("pdu") ? "date DESC" : sQLiteQueryBuilder.getTables().equals("part") ? "seq" : null : str2;
        Cursor query = sQLiteQueryBuilder.query(this.b.getReadableDatabase(), strArr, str, strArr2, null, null, str3);
        query.setNotificationUri(getContext().getContentResolver(), uri);
        return query;
    }

    private void a(SQLiteQueryBuilder sQLiteQueryBuilder, int i) {
        sQLiteQueryBuilder.setTables("pdu");
        if (i != 0) {
            sQLiteQueryBuilder.appendWhere("msg_box=" + i);
        }
    }

    public String getType(Uri uri) {
        switch (a.match(uri)) {
            case 0:
            case 2:
            case 4:
            case 6:
            case 8:
                return "vnd.android-dir/mms";
            case 1:
            case 3:
            case 5:
            case 7:
            case 9:
                return "vnd.android/mms";
            case 12:
                Cursor query = this.b.getReadableDatabase().query("part", new String[]{"ct"}, "_id = ?", new String[]{uri.getLastPathSegment()}, null, null, null);
                if (query != null) {
                    try {
                        if (query.getCount() == 1 && query.moveToFirst()) {
                            String string = query.getString(0);
                            return string;
                        }
                        Log.e("MmsProvider", "cursor.count() != 1: " + uri);
                        query.close();
                    } finally {
                        query.close();
                    }
                } else {
                    Log.e("MmsProvider", "cursor == null: " + uri);
                }
                return "*/*";
            default:
                return "*/*";
        }
    }

    public Uri insert(Uri uri, ContentValues contentValues) {
        int i;
        Uri parse;
        Object obj = 1;
        int match = a.match(uri);
        String str = "pdu";
        switch (match) {
            case 0:
                Integer asInteger = contentValues.getAsInteger("msg_box");
                if (asInteger == null) {
                    i = 1;
                    break;
                }
                i = asInteger.intValue();
                break;
            case 2:
                i = 1;
                break;
            case 4:
                i = 2;
                break;
            case 6:
                i = 3;
                break;
            case 8:
                i = 4;
                break;
            case 11:
                obj = null;
                str = "part";
                i = 0;
                break;
            case 13:
                obj = null;
                str = "addr";
                i = 0;
                break;
            case 14:
                obj = null;
                str = "rate";
                i = 0;
                break;
            case 17:
                obj = null;
                str = "drm";
                i = 0;
                break;
            default:
                Log.e("MmsProvider", "insert: invalid request: " + uri);
                return null;
        }
        SQLiteDatabase writableDatabase = this.b.getWritableDatabase();
        Uri uri2 = d.a;
        Object obj2;
        ContentValues contentValues2;
        long currentTimeMillis;
        String asString;
        long insert;
        if (str.equals("pdu")) {
            obj2 = !contentValues.containsKey("date") ? 1 : null;
            Object obj3 = !contentValues.containsKey("msg_box") ? 1 : null;
            a(contentValues);
            contentValues2 = new ContentValues(contentValues);
            currentTimeMillis = System.currentTimeMillis();
            if (obj2 != null) {
                contentValues2.put("date", Long.valueOf(currentTimeMillis / 1000));
            }
            if (!(obj3 == null || i == 0)) {
                contentValues2.put("msg_box", Integer.valueOf(i));
            }
            if (i != 1) {
                contentValues2.put("read", Integer.valueOf(1));
            }
            Long asLong = contentValues.getAsLong("thread_id");
            asString = contentValues.getAsString("address");
            if ((asLong == null || asLong.longValue() == 0) && !TextUtils.isEmpty(asString)) {
                contentValues2.put("thread_id", Long.valueOf(g.a(getContext(), asString)));
            }
            insert = writableDatabase.insert(str, null, contentValues2);
            if (insert <= 0) {
                Log.e("MmsProvider", "MmsProvider.insert: failed! " + contentValues2);
                return null;
            }
            parse = Uri.parse(uri2 + "/" + insert);
        } else if (str.equals("addr")) {
            ContentValues contentValues3 = new ContentValues(contentValues);
            contentValues3.put("msg_id", (String) uri.getPathSegments().get(0));
            insert = writableDatabase.insert(str, null, contentValues3);
            if (insert <= 0) {
                Log.e("MmsProvider", "Failed to insert address: " + contentValues3);
                return null;
            }
            parse = Uri.parse(uri2 + "/addr/" + insert);
        } else if (str.equals("part")) {
            contentValues2 = new ContentValues(contentValues);
            if (match == 11) {
                contentValues2.put("mid", (String) uri.getPathSegments().get(0));
            }
            String asString2 = contentValues.getAsString("ct");
            obj2 = null;
            Object obj4 = null;
            if ("text/plain".equals(asString2)) {
                obj2 = 1;
            } else if ("application/smil".equals(asString2)) {
                obj4 = 1;
            }
            if (obj2 == null && obj4 == null) {
                String str2;
                obj4 = contentValues.getAsString("cl");
                if (TextUtils.isEmpty(obj4)) {
                    str2 = "";
                } else {
                    str2 = "_" + new File(obj4).getName();
                }
                asString2 = getContext().getDir("parts", 0).getPath() + "/PART_" + System.currentTimeMillis() + str2;
                contentValues2.put("_data", asString2);
                File file = new File(asString2);
                if (!file.exists()) {
                    try {
                        if (!file.createNewFile()) {
                            throw new IllegalStateException("Unable to create new partFile: " + asString2);
                        }
                    } catch (Throwable e) {
                        Log.e("MmsProvider", "createNewFile", e);
                        throw new IllegalStateException("Unable to create new partFile: " + asString2);
                    }
                }
            }
            currentTimeMillis = writableDatabase.insert(str, null, contentValues2);
            if (currentTimeMillis <= 0) {
                Log.e("MmsProvider", "MmsProvider.insert: failed! " + contentValues2);
                return null;
            }
            parse = Uri.parse(uri2 + "/part/" + currentTimeMillis);
            if (obj2 != null) {
                ContentValues contentValues4 = new ContentValues();
                contentValues4.put("_id", Long.valueOf(2 + currentTimeMillis));
                contentValues4.put("index_text", contentValues.getAsString("text"));
                contentValues4.put("source_id", Long.valueOf(currentTimeMillis));
                contentValues4.put("table_to_use", Integer.valueOf(2));
                writableDatabase.insert("words", "index_text", contentValues4);
            }
        } else if (str.equals("rate")) {
            writableDatabase.delete(str, "sent_time<=" + (contentValues.getAsLong("sent_time").longValue() - 3600000), null);
            writableDatabase.insert(str, null, contentValues);
            parse = uri2;
        } else if (str.equals("drm")) {
            asString = getContext().getDir("parts", 0).getPath() + "/PART_" + System.currentTimeMillis();
            ContentValues contentValues5 = new ContentValues(1);
            contentValues5.put("_data", asString);
            File file2 = new File(asString);
            if (!file2.exists()) {
                try {
                    if (!file2.createNewFile()) {
                        throw new IllegalStateException("Unable to create new file: " + asString);
                    }
                } catch (Throwable e2) {
                    Log.e("MmsProvider", "createNewFile", e2);
                    throw new IllegalStateException("Unable to create new file: " + asString);
                }
            }
            long insert2 = writableDatabase.insert(str, null, contentValues5);
            if (insert2 <= 0) {
                Log.e("MmsProvider", "MmsProvider.insert: failed! " + contentValues5);
                return null;
            }
            parse = Uri.parse(uri2 + "/drm/" + insert2);
        } else {
            throw new AssertionError("Unknown table type: " + str);
        }
        if (obj == null) {
            return parse;
        }
        a();
        return parse;
    }

    private int a(int i) {
        switch (i) {
            case 2:
            case 3:
                return 1;
            case 4:
            case 5:
                return 2;
            case 6:
            case 7:
                return 3;
            case 8:
            case 9:
                return 4;
            default:
                throw new IllegalArgumentException("bad Arg: " + i);
        }
    }

    public int delete(Uri uri, String str, String[] strArr) {
        String str2;
        String str3;
        int i = 1;
        int i2 = 0;
        int match = a.match(uri);
        switch (match) {
            case 0:
            case 2:
            case 4:
            case 6:
            case 8:
                String str4 = "pdu";
                if (match == 0) {
                    str2 = null;
                    str3 = str4;
                    break;
                }
                str2 = "msg_box=" + a(match);
                str3 = str4;
                break;
            case 1:
            case 3:
            case 5:
            case 7:
            case 9:
                str3 = "pdu";
                str2 = "_id=" + uri.getLastPathSegment();
                break;
            case 10:
                str2 = null;
                str3 = "part";
                i = 0;
                break;
            case 11:
                str3 = "part";
                str2 = "mid=" + ((String) uri.getPathSegments().get(0));
                i = 0;
                break;
            case 12:
                str3 = "part";
                str2 = "_id=" + ((String) uri.getPathSegments().get(1));
                i = 0;
                break;
            case 13:
                str3 = "addr";
                str2 = "msg_id=" + ((String) uri.getPathSegments().get(0));
                i = 0;
                break;
            case 17:
                str2 = null;
                str3 = "drm";
                i = 0;
                break;
            default:
                Log.w("MmsProvider", "No match for URI '" + uri + "'");
                break;
        }
        str2 = a(str, str2);
        SQLiteDatabase writableDatabase = this.b.getWritableDatabase();
        if ("pdu".equals(str3)) {
            i2 = a(getContext(), writableDatabase, str2, strArr, uri);
        } else if ("part".equals(str3)) {
            i2 = a(writableDatabase, str2, strArr);
        } else if ("drm".equals(str3)) {
            i2 = b(writableDatabase, str2, strArr);
        } else {
            i2 = writableDatabase.delete(str3, str2, strArr);
        }
        if (i2 > 0 && i != 0) {
            a();
        }
        return i2;
    }

    static int a(Context context, SQLiteDatabase sQLiteDatabase, String str, String[] strArr, Uri uri) {
        SQLiteDatabase sQLiteDatabase2 = sQLiteDatabase;
        Cursor query = sQLiteDatabase2.query("pdu", new String[]{"_id"}, str, strArr, null, null, null);
        if (query == null) {
            return 0;
        }
        try {
            if (query.getCount() == 0) {
                return 0;
            }
            while (query.moveToNext()) {
                a(sQLiteDatabase, "mid = ?", new String[]{String.valueOf(query.getLong(0))});
            }
            query.close();
            int delete = sQLiteDatabase.delete("pdu", str, strArr);
            if (delete <= 0) {
                return delete;
            }
            Intent intent = new Intent("android.intent.action.CONTENT_CHANGED");
            intent.putExtra("deleted_contents", uri);
            context.sendBroadcast(intent);
            return delete;
        } finally {
            query.close();
        }
    }

    private static int a(SQLiteDatabase sQLiteDatabase, String str, String[] strArr) {
        return a(sQLiteDatabase, "part", str, strArr);
    }

    private static int b(SQLiteDatabase sQLiteDatabase, String str, String[] strArr) {
        return a(sQLiteDatabase, "drm", str, strArr);
    }

    private static int a(SQLiteDatabase sQLiteDatabase, String str, String str2, String[] strArr) {
        Cursor query = sQLiteDatabase.query(str, new String[]{"_data"}, str2, strArr, null, null, null);
        if (query == null) {
            return 0;
        }
        if (query.getCount() == 0) {
            query.close();
            return 0;
        }
        while (query.moveToNext()) {
            try {
                String string = query.getString(0);
                if (string != null) {
                    new File(string).delete();
                }
            } catch (Throwable th) {
                query.close();
            }
        }
        query.close();
        return sQLiteDatabase.delete(str, str2, strArr);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int update(android.net.Uri r9, android.content.ContentValues r10, java.lang.String r11, java.lang.String[] r12) {
        /*
        r8 = this;
        r1 = 0;
        r4 = 1;
        r5 = 0;
        r0 = a;
        r7 = r0.match(r9);
        switch(r7) {
            case 0: goto L_0x00f7;
            case 1: goto L_0x002b;
            case 2: goto L_0x00f7;
            case 3: goto L_0x002b;
            case 4: goto L_0x00f7;
            case 5: goto L_0x002b;
            case 6: goto L_0x00f7;
            case 7: goto L_0x002b;
            case 8: goto L_0x00f7;
            case 9: goto L_0x002b;
            case 10: goto L_0x000c;
            case 11: goto L_0x006e;
            case 12: goto L_0x006e;
            case 13: goto L_0x000c;
            case 14: goto L_0x000c;
            case 15: goto L_0x000c;
            case 16: goto L_0x000c;
            case 17: goto L_0x000c;
            case 18: goto L_0x000c;
            case 19: goto L_0x000c;
            case 20: goto L_0x0074;
            default: goto L_0x000c;
        };
    L_0x000c:
        r0 = "MmsProvider";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "Update operation for '";
        r1 = r1.append(r2);
        r1 = r1.append(r9);
        r2 = "' not implemented.";
        r1 = r1.append(r2);
        r1 = r1.toString();
        android.util.Log.w(r0, r1);
    L_0x002a:
        return r5;
    L_0x002b:
        r0 = r9.getLastPathSegment();
    L_0x002f:
        r2 = "pdu";
        r3 = r4;
    L_0x0032:
        r6 = "pdu";
        r6 = r2.equals(r6);
        if (r6 == 0) goto L_0x00a3;
    L_0x003a:
        r8.a(r10);
        r4 = new android.content.ContentValues;
        r4.<init>(r10);
        if (r0 == 0) goto L_0x00f4;
    L_0x0044:
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r5 = "_id=";
        r1 = r1.append(r5);
        r0 = r1.append(r0);
        r1 = r0.toString();
        r0 = r4;
    L_0x0058:
        r1 = a(r11, r1);
        r4 = r8.b;
        r4 = r4.getWritableDatabase();
        r5 = r4.update(r2, r0, r1, r12);
        if (r3 == 0) goto L_0x002a;
    L_0x0068:
        if (r5 <= 0) goto L_0x002a;
    L_0x006a:
        r8.a();
        goto L_0x002a;
    L_0x006e:
        r0 = "part";
        r2 = r0;
        r3 = r5;
        r0 = r1;
        goto L_0x0032;
    L_0x0074:
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = r8.getContext();
        r2 = "parts";
        r1 = r1.getDir(r2, r5);
        r1 = r1.getPath();
        r0 = r0.append(r1);
        r1 = 47;
        r1 = r0.append(r1);
        r0 = r9.getPathSegments();
        r0 = r0.get(r4);
        r0 = (java.lang.String) r0;
        r0 = r1.append(r0);
        r0.toString();
        goto L_0x002a;
    L_0x00a3:
        r0 = "part";
        r0 = r2.equals(r0);
        if (r0 == 0) goto L_0x002a;
    L_0x00ab:
        r6 = new android.content.ContentValues;
        r6.<init>(r10);
        switch(r7) {
            case 11: goto L_0x00b5;
            case 12: goto L_0x00d4;
            default: goto L_0x00b3;
        };
    L_0x00b3:
        r0 = r6;
        goto L_0x0058;
    L_0x00b5:
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = "mid=";
        r1 = r0.append(r1);
        r0 = r9.getPathSegments();
        r0 = r0.get(r5);
        r0 = (java.lang.String) r0;
        r0 = r1.append(r0);
        r1 = r0.toString();
        r0 = r6;
        goto L_0x0058;
    L_0x00d4:
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = "_id=";
        r1 = r0.append(r1);
        r0 = r9.getPathSegments();
        r0 = r0.get(r4);
        r0 = (java.lang.String) r0;
        r0 = r1.append(r0);
        r1 = r0.toString();
        r0 = r6;
        goto L_0x0058;
    L_0x00f4:
        r0 = r4;
        goto L_0x0058;
    L_0x00f7:
        r0 = r1;
        goto L_0x002f;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mavenir.android.messaging.provider.MmsProvider.update(android.net.Uri, android.content.ContentValues, java.lang.String, java.lang.String[]):int");
    }

    public ParcelFileDescriptor openFile(Uri uri, String str) {
        a.match(uri);
        if (Log.isLoggable("MmsProvider", 2)) {
            Log.d("MmsProvider", "openFile: uri=" + uri + ", mode=" + str);
        }
        return openFileHelper(uri, str);
    }

    private void a(ContentValues contentValues) {
        contentValues.remove("d_tm_tok");
        contentValues.remove("s_vis");
        contentValues.remove("r_chg");
        contentValues.remove("r_chg_dl_tok");
        contentValues.remove("r_chg_dl");
        contentValues.remove("r_chg_id");
        contentValues.remove("r_chg_sz");
        contentValues.remove("p_s_by");
        contentValues.remove("p_s_d");
        contentValues.remove("store");
        contentValues.remove("mm_st");
        contentValues.remove("mm_flg_tok");
        contentValues.remove("mm_flg");
        contentValues.remove("store_st");
        contentValues.remove("store_st_txt");
        contentValues.remove("stored");
        contentValues.remove("totals");
        contentValues.remove("mb_t");
        contentValues.remove("mb_t_tok");
        contentValues.remove("qt");
        contentValues.remove("mb_qt");
        contentValues.remove("mb_qt_tok");
        contentValues.remove("m_cnt");
        contentValues.remove("start");
        contentValues.remove("d_ind");
        contentValues.remove("e_des");
        contentValues.remove("limit");
        contentValues.remove("r_r_mod");
        contentValues.remove("r_r_mod_txt");
        contentValues.remove("st_txt");
        contentValues.remove("apl_id");
        contentValues.remove("r_apl_id");
        contentValues.remove("aux_apl_id");
        contentValues.remove("drm_c");
        contentValues.remove("adp_a");
        contentValues.remove("repl_id");
        contentValues.remove("cl_id");
        contentValues.remove("cl_st");
        contentValues.remove("_id");
    }

    private void a() {
        getContext().getContentResolver().notifyChange(e.a, null);
    }

    static {
        a.addURI("mms", null, 0);
        a.addURI("mms", "#", 1);
        a.addURI("mms", "inbox", 2);
        a.addURI("mms", "inbox/#", 3);
        a.addURI("mms", "sent", 4);
        a.addURI("mms", "sent/#", 5);
        a.addURI("mms", "drafts", 6);
        a.addURI("mms", "drafts/#", 7);
        a.addURI("mms", "outbox", 8);
        a.addURI("mms", "outbox/#", 9);
        a.addURI("mms", "part", 10);
        a.addURI("mms", "#/part", 11);
        a.addURI("mms", "part/#", 12);
        a.addURI("mms", "#/addr", 13);
        a.addURI("mms", "rate", 14);
        a.addURI("mms", "report-status/#", 15);
        a.addURI("mms", "report-request/#", 16);
        a.addURI("mms", "drm", 17);
        a.addURI("mms", "drm/#", 18);
        a.addURI("mms", "threads", 19);
        a.addURI("mms", "resetFilePerm/*", 20);
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
}
