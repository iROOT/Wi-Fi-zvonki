package com.mavenir.android.messaging.provider;

import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class f extends SQLiteOpenHelper {
    private static f a = null;
    private static boolean b = false;
    private static boolean c = false;
    private final Context d;
    private a e;

    private class a extends BroadcastReceiver {
        final /* synthetic */ f a;

        public a(f fVar) {
            this.a = fVar;
        }

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.d("MmsSmsDatabaseHelper", "[LowStorageMonitor] onReceive intent " + action);
            if ("android.intent.action.DEVICE_STORAGE_OK".equals(action)) {
                f.b = false;
            }
        }
    }

    private f(Context context) {
        super(context, "mmssms.db", null, 55);
        this.d = context;
    }

    static synchronized f a(Context context) {
        f fVar;
        synchronized (f.class) {
            if (a == null) {
                a = new f(context);
            }
            fVar = a;
        }
        return fVar;
    }

    private static void a(SQLiteDatabase sQLiteDatabase) {
        Cursor query = sQLiteDatabase.query("threads", new String[]{"recipient_ids"}, null, null, null, null, null);
        if (query != null) {
            try {
                if (query.getCount() == 0) {
                    sQLiteDatabase.delete("canonical_addresses", null, null);
                } else {
                    HashSet hashSet = new HashSet();
                    while (query.moveToNext()) {
                        for (String parseInt : query.getString(0).split(" ")) {
                            try {
                                hashSet.add(Integer.valueOf(Integer.parseInt(parseInt)));
                            } catch (Exception e) {
                            }
                        }
                    }
                    StringBuilder stringBuilder = new StringBuilder();
                    Iterator it = hashSet.iterator();
                    while (it.hasNext()) {
                        stringBuilder.append("_id != " + it.next());
                        if (it.hasNext()) {
                            stringBuilder.append(" AND ");
                        }
                    }
                    if (stringBuilder.length() > 0) {
                        sQLiteDatabase.delete("canonical_addresses", stringBuilder.toString(), null);
                    }
                }
                query.close();
            } catch (Throwable th) {
                query.close();
            }
        }
    }

    public static void a(SQLiteDatabase sQLiteDatabase, long j) {
        int i = 0;
        if (j < 0) {
            a(sQLiteDatabase, null, null);
            return;
        }
        if (sQLiteDatabase.delete("threads", "_id = ? AND _id NOT IN (SELECT thread_id FROM sms  UNION SELECT thread_id FROM pdu)", new String[]{String.valueOf(j)}) > 0) {
            a(sQLiteDatabase);
            return;
        }
        sQLiteDatabase.execSQL(" UPDATE threads SET message_count =  (SELECT COUNT(sms._id) FROM sms LEFT JOIN threads  ON threads._id = thread_id WHERE thread_id = " + j + " AND sms." + "type" + " != 3) +  (SELECT COUNT(pdu._id) FROM pdu LEFT JOIN threads  ON threads._id = " + "thread_id" + " WHERE " + "thread_id" + " = " + j + " AND (m_type=132 OR m_type=130 OR m_type=128) AND " + "msg_box" + " != 3)  WHERE threads._id = " + j + ";");
        sQLiteDatabase.execSQL(" UPDATE threads SET date = (SELECT date FROM (SELECT date * 1000 AS date, thread_id FROM pdu UNION SELECT date, thread_id FROM sms) WHERE thread_id = " + j + " ORDER BY date DESC LIMIT 1), snippet = (SELECT snippet FROM (SELECT date * 1000 AS date, sub AS snippet, thread_id FROM pdu UNION SELECT date, body AS snippet, thread_id FROM sms) WHERE thread_id = " + j + " ORDER BY date DESC LIMIT 1), snippet_cs = (SELECT snippet_cs FROM (SELECT date * 1000 AS date, sub_cs AS snippet_cs, thread_id FROM pdu UNION SELECT date, 0 AS snippet_cs, thread_id FROM sms) WHERE thread_id = " + j + " ORDER BY date DESC LIMIT 1) WHERE threads._id = " + j + ";");
        Cursor rawQuery = sQLiteDatabase.rawQuery("SELECT thread_id FROM sms WHERE type=5 AND thread_id = " + j + " LIMIT 1", null);
        if (rawQuery != null) {
            try {
                i = rawQuery.getCount();
            } finally {
                rawQuery.close();
            }
        }
        rawQuery = sQLiteDatabase.rawQuery("SELECT error FROM threads WHERE _id = " + j, null);
        if (rawQuery != null) {
            try {
                if (rawQuery.moveToNext() && rawQuery.getInt(0) != i) {
                    sQLiteDatabase.execSQL("UPDATE threads SET error=" + i + " WHERE _id = " + j);
                }
                rawQuery.close();
            } catch (Throwable th) {
                rawQuery.close();
            }
        }
    }

    public static void a(SQLiteDatabase sQLiteDatabase, String str, String[] strArr) {
        String str2;
        if (str == null) {
            str2 = "";
        } else {
            str2 = "WHERE (" + str + ")";
        }
        Cursor rawQuery = sQLiteDatabase.rawQuery("SELECT _id FROM threads WHERE _id IN (SELECT DISTINCT thread_id FROM sms " + str2 + ")", strArr);
        if (rawQuery != null) {
            while (rawQuery.moveToNext()) {
                try {
                    a(sQLiteDatabase, (long) rawQuery.getInt(0));
                } finally {
                    rawQuery.close();
                }
            }
        }
        sQLiteDatabase.delete("threads", "_id NOT IN (SELECT DISTINCT thread_id FROM sms where thread_id NOT NULL UNION SELECT DISTINCT thread_id FROM pdu where thread_id NOT NULL)", null);
        a(sQLiteDatabase);
    }

    public static int a(SQLiteDatabase sQLiteDatabase, int i) {
        int i2;
        String[] strArr = new String[]{"thread_id"};
        String str = "_id=" + i;
        Cursor query = sQLiteDatabase.query("sms", strArr, str, null, null, null, null);
        if (query != null) {
            if (query.moveToFirst()) {
                i2 = query.getInt(0);
            } else {
                i2 = -1;
            }
            query.close();
        } else {
            i2 = -1;
        }
        int delete = sQLiteDatabase.delete("sms", "_id=" + i, null);
        if (i2 > 0) {
            a(sQLiteDatabase, (long) i2);
        }
        return delete;
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        f(sQLiteDatabase);
        h(sQLiteDatabase);
        i(sQLiteDatabase);
        j(sQLiteDatabase);
        g(sQLiteDatabase);
        c(sQLiteDatabase);
        d(sQLiteDatabase);
    }

    private void b(SQLiteDatabase sQLiteDatabase) {
        ContentValues contentValues;
        String str = "words";
        SQLiteDatabase sQLiteDatabase2 = sQLiteDatabase;
        Cursor query = sQLiteDatabase2.query("sms", new String[]{"_id", "body"}, null, null, null, null, null);
        if (query != null) {
            try {
                query.moveToPosition(-1);
                contentValues = new ContentValues();
                while (query.moveToNext()) {
                    contentValues.clear();
                    long j = query.getLong(0);
                    String string = query.getString(1);
                    contentValues.put("_id", Long.valueOf(j));
                    contentValues.put("index_text", string);
                    contentValues.put("source_id", Long.valueOf(j));
                    contentValues.put("table_to_use", Integer.valueOf(1));
                    sQLiteDatabase.insert("words", "index_text", contentValues);
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
        SQLiteDatabase sQLiteDatabase3 = sQLiteDatabase;
        query = sQLiteDatabase3.query("part", new String[]{"_id", "text"}, "ct = 'text/plain'", null, null, null, null);
        if (query != null) {
            try {
                query.moveToPosition(-1);
                contentValues = new ContentValues();
                while (query.moveToNext()) {
                    contentValues.clear();
                    long j2 = query.getLong(0);
                    String string2 = query.getString(1);
                    contentValues.put("_id", Long.valueOf(j2));
                    contentValues.put("index_text", string2);
                    contentValues.put("source_id", Long.valueOf(j2));
                    contentValues.put("table_to_use", Integer.valueOf(1));
                    sQLiteDatabase.insert("words", "index_text", contentValues);
                }
            } catch (Throwable th2) {
                if (query != null) {
                    query.close();
                }
            }
        }
        if (query != null) {
            query.close();
        }
    }

    private void c(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("CREATE VIRTUAL TABLE words USING FTS3 (_id INTEGER PRIMARY KEY, index_text TEXT, source_id INTEGER, table_to_use INTEGER);");
            sQLiteDatabase.execSQL("CREATE TRIGGER sms_words_update AFTER UPDATE ON sms BEGIN UPDATE words  SET index_text = NEW.body WHERE (source_id=NEW._id AND table_to_use=1);  END;");
            sQLiteDatabase.execSQL("CREATE TRIGGER sms_words_delete AFTER DELETE ON sms BEGIN DELETE FROM  words WHERE source_id = OLD._id AND table_to_use = 1; END;");
            sQLiteDatabase.execSQL("CREATE TRIGGER mms_words_update AFTER UPDATE ON part BEGIN UPDATE words  SET index_text = NEW.text WHERE (source_id=NEW._id AND table_to_use=2);  END;");
            sQLiteDatabase.execSQL("CREATE TRIGGER mms_words_delete AFTER DELETE ON part BEGIN DELETE FROM  words WHERE source_id = OLD._id AND table_to_use = 2; END;");
            b(sQLiteDatabase);
        } catch (Exception e) {
            Log.e("MmsSmsDatabaseHelper", "got exception creating words table: " + e.toString());
        }
    }

    private void d(SQLiteDatabase sQLiteDatabase) {
        e(sQLiteDatabase);
    }

    private void e(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("CREATE INDEX IF NOT EXISTS typeThreadIdIndex ON sms (type, thread_id);");
        } catch (Exception e) {
            Log.e("MmsSmsDatabaseHelper", "got exception creating indices: " + e.toString());
        }
    }

    private void f(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE pdu (_id INTEGER PRIMARY KEY,thread_id INTEGER,date INTEGER,date_sent INTEGER DEFAULT 0,msg_box INTEGER,read INTEGER DEFAULT 0,m_id TEXT,sub TEXT,sub_cs INTEGER,ct_t TEXT,ct_l TEXT,exp INTEGER,m_cls TEXT,m_type INTEGER,v INTEGER,m_size INTEGER,pri INTEGER,rr INTEGER,rpt_a INTEGER,resp_st INTEGER,st INTEGER,tr_id TEXT,retr_st INTEGER,retr_txt TEXT,retr_txt_cs INTEGER,read_status INTEGER,ct_cls INTEGER,resp_txt TEXT,d_tm INTEGER,d_rpt INTEGER,locked INTEGER DEFAULT 0,seen INTEGER DEFAULT 0);");
        sQLiteDatabase.execSQL("CREATE TABLE addr (_id INTEGER PRIMARY KEY,msg_id INTEGER,contact_id INTEGER,address TEXT,type INTEGER,charset INTEGER);");
        sQLiteDatabase.execSQL("CREATE TABLE part (_id INTEGER PRIMARY KEY,mid INTEGER,seq INTEGER DEFAULT 0,ct TEXT,name TEXT,chset INTEGER,cd TEXT,fn TEXT,cid TEXT,cl TEXT,ctt_s INTEGER,ctt_t TEXT,_data TEXT,text TEXT);");
        sQLiteDatabase.execSQL("CREATE TABLE rate (sent_time INTEGER);");
        sQLiteDatabase.execSQL("CREATE TABLE drm (_id INTEGER PRIMARY KEY,_data TEXT);");
    }

    private void g(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TRIGGER part_cleanup DELETE ON pdu BEGIN  DELETE FROM part WHERE mid=old._id;END;");
        sQLiteDatabase.execSQL("CREATE TRIGGER addr_cleanup DELETE ON pdu BEGIN  DELETE FROM addr WHERE msg_id=old._id;END;");
        sQLiteDatabase.execSQL("CREATE TRIGGER update_threads_on_insert_part  AFTER INSERT ON part  WHEN new.ct != 'text/plain' AND new.ct != 'application/smil'  BEGIN  UPDATE threads SET has_attachment=1 WHERE _id IN  (SELECT pdu.thread_id FROM part JOIN pdu ON pdu._id=part.mid  WHERE part._id=new._id LIMIT 1);  END");
        sQLiteDatabase.execSQL("CREATE TRIGGER update_threads_on_update_part  AFTER UPDATE of mid ON part  WHEN new.ct != 'text/plain' AND new.ct != 'application/smil'  BEGIN  UPDATE threads SET has_attachment=1 WHERE _id IN  (SELECT pdu.thread_id FROM part JOIN pdu ON pdu._id=part.mid  WHERE part._id=new._id LIMIT 1);  END");
        sQLiteDatabase.execSQL("CREATE TRIGGER update_threads_on_delete_part  AFTER DELETE ON part  WHEN old.ct != 'text/plain' AND old.ct != 'application/smil'  BEGIN  UPDATE threads SET has_attachment =  CASE  (SELECT COUNT(*) FROM part JOIN pdu  WHERE pdu.thread_id = threads._id  AND part.ct != 'text/plain' AND part.ct != 'application/smil'  AND part.mid = pdu._id) WHEN 0 THEN 0  ELSE 1  END;  END");
        sQLiteDatabase.execSQL("CREATE TRIGGER update_threads_on_update_pdu  AFTER UPDATE of thread_id ON pdu  BEGIN  UPDATE threads SET has_attachment=1 WHERE _id IN  (SELECT pdu.thread_id FROM part JOIN pdu  WHERE part.ct != 'text/plain' AND part.ct != 'application/smil'  AND part.mid = pdu._id); END");
    }

    private void h(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE sms (_id INTEGER PRIMARY KEY,thread_id INTEGER,address TEXT,person INTEGER,date INTEGER,date_sent INTEGER DEFAULT 0,protocol INTEGER,read INTEGER DEFAULT 0,status INTEGER DEFAULT -1,type INTEGER,reply_path_present INTEGER,subject TEXT,body TEXT,service_center TEXT,locked INTEGER DEFAULT 0,error_code INTEGER DEFAULT 0,seen INTEGER DEFAULT 0);");
        sQLiteDatabase.execSQL("CREATE TABLE raw (_id INTEGER PRIMARY KEY,date INTEGER,reference_number INTEGER,count INTEGER,sequence INTEGER,destination_port INTEGER,address TEXT,pdu TEXT);");
        sQLiteDatabase.execSQL("CREATE TABLE attachments (sms_id INTEGER,content_url TEXT,offset INTEGER);");
        sQLiteDatabase.execSQL("CREATE TABLE sr_pending (reference_number INTEGER,action TEXT,data TEXT);");
    }

    private void i(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE canonical_addresses (_id INTEGER PRIMARY KEY AUTOINCREMENT,address TEXT);");
        sQLiteDatabase.execSQL("CREATE TABLE threads (_id INTEGER PRIMARY KEY AUTOINCREMENT,date INTEGER DEFAULT 0,message_count INTEGER DEFAULT 0,recipient_ids TEXT,snippet TEXT,snippet_cs INTEGER DEFAULT 0,read INTEGER DEFAULT 1,type INTEGER DEFAULT 0,error INTEGER DEFAULT 0,has_attachment INTEGER DEFAULT 0);");
        sQLiteDatabase.execSQL("CREATE TABLE pending_msgs (_id INTEGER PRIMARY KEY,proto_type INTEGER,msg_id INTEGER,msg_type INTEGER,err_type INTEGER,err_code INTEGER,retry_index INTEGER NOT NULL DEFAULT 0,due_time INTEGER,last_try INTEGER);");
    }

    private void j(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TRIGGER sms_update_thread_on_insert AFTER INSERT ON sms BEGIN UPDATE threads SET date = (strftime('%s','now') * 1000),  snippet = new.body,  snippet_cs = 0 WHERE threads._id = new.thread_id;  UPDATE threads SET message_count =  (SELECT COUNT(sms._id) FROM sms LEFT JOIN threads  ON threads._id = thread_id WHERE thread_id = new.thread_id AND sms.type != 3) +  (SELECT COUNT(pdu._id) FROM pdu LEFT JOIN threads  ON threads._id = thread_id WHERE thread_id = new.thread_id AND (m_type=132 OR m_type=130 OR m_type=128) AND msg_box != 3)  WHERE threads._id = new.thread_id;  UPDATE threads SET read =  CASE (SELECT COUNT(*) FROM sms WHERE read = 0 AND thread_id = threads._id) WHEN 0 THEN 1 ELSE 0 END WHERE threads._id = new.thread_id; END;");
        sQLiteDatabase.execSQL("CREATE TRIGGER sms_update_thread_date_subject_on_update AFTER UPDATE OF date, body, type ON sms BEGIN UPDATE threads SET date = (strftime('%s','now') * 1000),  snippet = new.body,  snippet_cs = 0 WHERE threads._id = new.thread_id;  UPDATE threads SET message_count =  (SELECT COUNT(sms._id) FROM sms LEFT JOIN threads  ON threads._id = thread_id WHERE thread_id = new.thread_id AND sms.type != 3) +  (SELECT COUNT(pdu._id) FROM pdu LEFT JOIN threads  ON threads._id = thread_id WHERE thread_id = new.thread_id AND (m_type=132 OR m_type=130 OR m_type=128) AND msg_box != 3)  WHERE threads._id = new.thread_id;  UPDATE threads SET read =  CASE (SELECT COUNT(*) FROM sms WHERE read = 0 AND thread_id = threads._id) WHEN 0 THEN 1 ELSE 0 END WHERE threads._id = new.thread_id; END;");
        sQLiteDatabase.execSQL("CREATE TRIGGER sms_update_thread_read_on_update AFTER UPDATE OF read ON sms BEGIN  UPDATE threads SET read =  CASE (SELECT COUNT(*) FROM sms WHERE read = 0 AND thread_id = threads._id) WHEN 0 THEN 1 ELSE 0 END WHERE threads._id = new.thread_id; END;");
        sQLiteDatabase.execSQL("CREATE TRIGGER pdu_update_thread_on_delete AFTER DELETE ON pdu BEGIN  UPDATE threads SET  date = (strftime('%s','now') * 1000) WHERE threads._id = old.thread_id;  UPDATE threads SET message_count =  (SELECT COUNT(sms._id) FROM sms LEFT JOIN threads  ON threads._id = thread_id WHERE thread_id = old.thread_id AND sms.type != 3) +  (SELECT COUNT(pdu._id) FROM pdu LEFT JOIN threads  ON threads._id = thread_id WHERE thread_id = old.thread_id AND (m_type=132 OR m_type=130 OR m_type=128) AND msg_box != 3)  WHERE threads._id = old.thread_id;  UPDATE threads SET snippet =  (SELECT snippet FROM (SELECT date * 1000 AS date, sub AS snippet, thread_id FROM pdu UNION SELECT date, body AS snippet, thread_id FROM sms) WHERE thread_id = OLD.thread_id ORDER BY date DESC LIMIT 1)  WHERE threads._id = OLD.thread_id;  UPDATE threads SET snippet_cs =  (SELECT snippet_cs FROM (SELECT date * 1000 AS date, sub_cs AS snippet_cs, thread_id FROM pdu UNION SELECT date, 0 AS snippet_cs, thread_id FROM sms) WHERE thread_id = OLD.thread_id ORDER BY date DESC LIMIT 1)  WHERE threads._id = OLD.thread_id; END;");
        sQLiteDatabase.execSQL("CREATE TRIGGER update_threads_error_on_update_mms  AFTER UPDATE OF err_type ON pending_msgs  WHEN (OLD.err_type < 10 AND NEW.err_type >= 10) OR (OLD.err_type >= 10 AND NEW.err_type < 10) BEGIN UPDATE threads SET error =  CASE WHEN NEW.err_type >= 10 THEN error + 1 ELSE error - 1 END  WHERE _id = (SELECT DISTINCT thread_id FROM pdu WHERE _id = NEW.msg_id); END;");
        sQLiteDatabase.execSQL("CREATE TRIGGER update_threads_error_on_delete_mms  BEFORE DELETE ON pdu WHEN OLD._id IN (SELECT DISTINCT msg_id FROM pending_msgs WHERE err_type >= 10) BEGIN  UPDATE threads SET error = error - 1 WHERE _id = OLD.thread_id; END;");
        sQLiteDatabase.execSQL("CREATE TRIGGER update_threads_error_on_move_mms  BEFORE UPDATE OF msg_box ON pdu  WHEN (OLD.msg_box = 4 AND NEW.msg_box != 4)  AND (OLD._id IN (SELECT DISTINCT msg_id FROM pending_msgs WHERE err_type >= 10)) BEGIN  UPDATE threads SET error = error - 1 WHERE _id = OLD.thread_id; END;");
        sQLiteDatabase.execSQL("CREATE TRIGGER update_threads_error_on_update_sms  AFTER UPDATE OF type ON sms WHEN (OLD.type != 5 AND NEW.type = 5) OR (OLD.type = 5 AND NEW.type != 5) BEGIN  UPDATE threads SET error =  CASE WHEN NEW.type = 5 THEN error + 1 ELSE error - 1 END  WHERE _id = NEW.thread_id; END;");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onUpgrade(android.database.sqlite.SQLiteDatabase r4, int r5, int r6) {
        /*
        r3 = this;
        r0 = "MmsSmsDatabaseHelper";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "Upgrading database from version ";
        r1 = r1.append(r2);
        r1 = r1.append(r5);
        r2 = " to ";
        r1 = r1.append(r2);
        r1 = r1.append(r6);
        r2 = ".";
        r1 = r1.append(r2);
        r1 = r1.toString();
        android.util.Log.w(r0, r1);
        switch(r5) {
            case 40: goto L_0x0039;
            case 41: goto L_0x0049;
            case 42: goto L_0x0059;
            case 43: goto L_0x0069;
            case 44: goto L_0x0079;
            case 45: goto L_0x0089;
            case 46: goto L_0x0099;
            case 47: goto L_0x00a9;
            case 48: goto L_0x00b9;
            case 49: goto L_0x00c9;
            case 50: goto L_0x00d9;
            case 51: goto L_0x00e9;
            case 52: goto L_0x00ed;
            case 53: goto L_0x00fd;
            case 54: goto L_0x010d;
            default: goto L_0x002b;
        };
    L_0x002b:
        r0 = "MmsSmsDatabaseHelper";
        r1 = "Destroying all old data.";
        android.util.Log.e(r0, r1);
        r3.k(r4);
        r3.onCreate(r4);
    L_0x0038:
        return;
    L_0x0039:
        r0 = 40;
        if (r6 <= r0) goto L_0x0038;
    L_0x003d:
        r4.beginTransaction();
        r3.l(r4);	 Catch:{ Throwable -> 0x011f }
        r4.setTransactionSuccessful();	 Catch:{ Throwable -> 0x011f }
        r4.endTransaction();
    L_0x0049:
        r0 = 41;
        if (r6 <= r0) goto L_0x0038;
    L_0x004d:
        r4.beginTransaction();
        r3.m(r4);	 Catch:{ Throwable -> 0x0133 }
        r4.setTransactionSuccessful();	 Catch:{ Throwable -> 0x0133 }
        r4.endTransaction();
    L_0x0059:
        r0 = 42;
        if (r6 <= r0) goto L_0x0038;
    L_0x005d:
        r4.beginTransaction();
        r3.n(r4);	 Catch:{ Throwable -> 0x0147 }
        r4.setTransactionSuccessful();	 Catch:{ Throwable -> 0x0147 }
        r4.endTransaction();
    L_0x0069:
        r0 = 43;
        if (r6 <= r0) goto L_0x0038;
    L_0x006d:
        r4.beginTransaction();
        r3.o(r4);	 Catch:{ Throwable -> 0x015b }
        r4.setTransactionSuccessful();	 Catch:{ Throwable -> 0x015b }
        r4.endTransaction();
    L_0x0079:
        r0 = 44;
        if (r6 <= r0) goto L_0x0038;
    L_0x007d:
        r4.beginTransaction();
        r3.p(r4);	 Catch:{ Throwable -> 0x016f }
        r4.setTransactionSuccessful();	 Catch:{ Throwable -> 0x016f }
        r4.endTransaction();
    L_0x0089:
        r0 = 45;
        if (r6 <= r0) goto L_0x0038;
    L_0x008d:
        r4.beginTransaction();
        r3.q(r4);	 Catch:{ Throwable -> 0x0183 }
        r4.setTransactionSuccessful();	 Catch:{ Throwable -> 0x0183 }
        r4.endTransaction();
    L_0x0099:
        r0 = 46;
        if (r6 <= r0) goto L_0x0038;
    L_0x009d:
        r4.beginTransaction();
        r3.r(r4);	 Catch:{ Throwable -> 0x0197 }
        r4.setTransactionSuccessful();	 Catch:{ Throwable -> 0x0197 }
        r4.endTransaction();
    L_0x00a9:
        r0 = 47;
        if (r6 <= r0) goto L_0x0038;
    L_0x00ad:
        r4.beginTransaction();
        r3.s(r4);	 Catch:{ Throwable -> 0x01ab }
        r4.setTransactionSuccessful();	 Catch:{ Throwable -> 0x01ab }
        r4.endTransaction();
    L_0x00b9:
        r0 = 48;
        if (r6 <= r0) goto L_0x0038;
    L_0x00bd:
        r4.beginTransaction();
        r3.c(r4);	 Catch:{ Throwable -> 0x01bf }
        r4.setTransactionSuccessful();	 Catch:{ Throwable -> 0x01bf }
        r4.endTransaction();
    L_0x00c9:
        r0 = 49;
        if (r6 <= r0) goto L_0x0038;
    L_0x00cd:
        r4.beginTransaction();
        r3.e(r4);	 Catch:{ Throwable -> 0x01d3 }
        r4.setTransactionSuccessful();	 Catch:{ Throwable -> 0x01d3 }
        r4.endTransaction();
    L_0x00d9:
        r0 = 50;
        if (r6 <= r0) goto L_0x0038;
    L_0x00dd:
        r4.beginTransaction();
        r3.t(r4);	 Catch:{ Throwable -> 0x01e7 }
        r4.setTransactionSuccessful();	 Catch:{ Throwable -> 0x01e7 }
        r4.endTransaction();
    L_0x00e9:
        r0 = 51;
        if (r6 <= r0) goto L_0x0038;
    L_0x00ed:
        r0 = 52;
        if (r6 <= r0) goto L_0x0038;
    L_0x00f1:
        r4.beginTransaction();
        r3.u(r4);	 Catch:{ Throwable -> 0x01fb }
        r4.setTransactionSuccessful();	 Catch:{ Throwable -> 0x01fb }
        r4.endTransaction();
    L_0x00fd:
        r0 = 53;
        if (r6 <= r0) goto L_0x0038;
    L_0x0101:
        r4.beginTransaction();
        r3.v(r4);	 Catch:{ Throwable -> 0x020f }
        r4.setTransactionSuccessful();	 Catch:{ Throwable -> 0x020f }
        r4.endTransaction();
    L_0x010d:
        r0 = 54;
        if (r6 <= r0) goto L_0x0038;
    L_0x0111:
        r4.beginTransaction();
        r3.w(r4);	 Catch:{ Throwable -> 0x0223 }
        r4.setTransactionSuccessful();	 Catch:{ Throwable -> 0x0223 }
        r4.endTransaction();
        goto L_0x0038;
    L_0x011f:
        r0 = move-exception;
        r1 = "MmsSmsDatabaseHelper";
        r2 = r0.getMessage();	 Catch:{ all -> 0x012e }
        android.util.Log.e(r1, r2, r0);	 Catch:{ all -> 0x012e }
        r4.endTransaction();
        goto L_0x002b;
    L_0x012e:
        r0 = move-exception;
        r4.endTransaction();
        throw r0;
    L_0x0133:
        r0 = move-exception;
        r1 = "MmsSmsDatabaseHelper";
        r2 = r0.getMessage();	 Catch:{ all -> 0x0142 }
        android.util.Log.e(r1, r2, r0);	 Catch:{ all -> 0x0142 }
        r4.endTransaction();
        goto L_0x002b;
    L_0x0142:
        r0 = move-exception;
        r4.endTransaction();
        throw r0;
    L_0x0147:
        r0 = move-exception;
        r1 = "MmsSmsDatabaseHelper";
        r2 = r0.getMessage();	 Catch:{ all -> 0x0156 }
        android.util.Log.e(r1, r2, r0);	 Catch:{ all -> 0x0156 }
        r4.endTransaction();
        goto L_0x002b;
    L_0x0156:
        r0 = move-exception;
        r4.endTransaction();
        throw r0;
    L_0x015b:
        r0 = move-exception;
        r1 = "MmsSmsDatabaseHelper";
        r2 = r0.getMessage();	 Catch:{ all -> 0x016a }
        android.util.Log.e(r1, r2, r0);	 Catch:{ all -> 0x016a }
        r4.endTransaction();
        goto L_0x002b;
    L_0x016a:
        r0 = move-exception;
        r4.endTransaction();
        throw r0;
    L_0x016f:
        r0 = move-exception;
        r1 = "MmsSmsDatabaseHelper";
        r2 = r0.getMessage();	 Catch:{ all -> 0x017e }
        android.util.Log.e(r1, r2, r0);	 Catch:{ all -> 0x017e }
        r4.endTransaction();
        goto L_0x002b;
    L_0x017e:
        r0 = move-exception;
        r4.endTransaction();
        throw r0;
    L_0x0183:
        r0 = move-exception;
        r1 = "MmsSmsDatabaseHelper";
        r2 = r0.getMessage();	 Catch:{ all -> 0x0192 }
        android.util.Log.e(r1, r2, r0);	 Catch:{ all -> 0x0192 }
        r4.endTransaction();
        goto L_0x002b;
    L_0x0192:
        r0 = move-exception;
        r4.endTransaction();
        throw r0;
    L_0x0197:
        r0 = move-exception;
        r1 = "MmsSmsDatabaseHelper";
        r2 = r0.getMessage();	 Catch:{ all -> 0x01a6 }
        android.util.Log.e(r1, r2, r0);	 Catch:{ all -> 0x01a6 }
        r4.endTransaction();
        goto L_0x002b;
    L_0x01a6:
        r0 = move-exception;
        r4.endTransaction();
        throw r0;
    L_0x01ab:
        r0 = move-exception;
        r1 = "MmsSmsDatabaseHelper";
        r2 = r0.getMessage();	 Catch:{ all -> 0x01ba }
        android.util.Log.e(r1, r2, r0);	 Catch:{ all -> 0x01ba }
        r4.endTransaction();
        goto L_0x002b;
    L_0x01ba:
        r0 = move-exception;
        r4.endTransaction();
        throw r0;
    L_0x01bf:
        r0 = move-exception;
        r1 = "MmsSmsDatabaseHelper";
        r2 = r0.getMessage();	 Catch:{ all -> 0x01ce }
        android.util.Log.e(r1, r2, r0);	 Catch:{ all -> 0x01ce }
        r4.endTransaction();
        goto L_0x002b;
    L_0x01ce:
        r0 = move-exception;
        r4.endTransaction();
        throw r0;
    L_0x01d3:
        r0 = move-exception;
        r1 = "MmsSmsDatabaseHelper";
        r2 = r0.getMessage();	 Catch:{ all -> 0x01e2 }
        android.util.Log.e(r1, r2, r0);	 Catch:{ all -> 0x01e2 }
        r4.endTransaction();
        goto L_0x002b;
    L_0x01e2:
        r0 = move-exception;
        r4.endTransaction();
        throw r0;
    L_0x01e7:
        r0 = move-exception;
        r1 = "MmsSmsDatabaseHelper";
        r2 = r0.getMessage();	 Catch:{ all -> 0x01f6 }
        android.util.Log.e(r1, r2, r0);	 Catch:{ all -> 0x01f6 }
        r4.endTransaction();
        goto L_0x002b;
    L_0x01f6:
        r0 = move-exception;
        r4.endTransaction();
        throw r0;
    L_0x01fb:
        r0 = move-exception;
        r1 = "MmsSmsDatabaseHelper";
        r2 = r0.getMessage();	 Catch:{ all -> 0x020a }
        android.util.Log.e(r1, r2, r0);	 Catch:{ all -> 0x020a }
        r4.endTransaction();
        goto L_0x002b;
    L_0x020a:
        r0 = move-exception;
        r4.endTransaction();
        throw r0;
    L_0x020f:
        r0 = move-exception;
        r1 = "MmsSmsDatabaseHelper";
        r2 = r0.getMessage();	 Catch:{ all -> 0x021e }
        android.util.Log.e(r1, r2, r0);	 Catch:{ all -> 0x021e }
        r4.endTransaction();
        goto L_0x002b;
    L_0x021e:
        r0 = move-exception;
        r4.endTransaction();
        throw r0;
    L_0x0223:
        r0 = move-exception;
        r1 = "MmsSmsDatabaseHelper";
        r2 = r0.getMessage();	 Catch:{ all -> 0x0232 }
        android.util.Log.e(r1, r2, r0);	 Catch:{ all -> 0x0232 }
        r4.endTransaction();
        goto L_0x002b;
    L_0x0232:
        r0 = move-exception;
        r4.endTransaction();
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mavenir.android.messaging.provider.f.onUpgrade(android.database.sqlite.SQLiteDatabase, int, int):void");
    }

    private void k(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS canonical_addresses");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS threads");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS pending_msgs");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS sms");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS raw");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS attachments");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS thread_ids");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS sr_pending");
    }

    private void l(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("DROP TRIGGER IF EXISTS update_threads_error_on_move_mms");
        sQLiteDatabase.execSQL("CREATE TRIGGER update_threads_error_on_move_mms  BEFORE UPDATE OF msg_box ON pdu  WHEN (OLD.msg_box = 4 AND NEW.msg_box != 4)  AND (OLD._id IN (SELECT DISTINCT msg_id FROM pending_msgs WHERE err_type >= 10)) BEGIN  UPDATE threads SET error = error - 1 WHERE _id = OLD.thread_id; END;");
    }

    private void m(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("DROP TRIGGER IF EXISTS sms_update_thread_on_delete");
        sQLiteDatabase.execSQL("DROP TRIGGER IF EXISTS delete_obsolete_threads_sms");
        sQLiteDatabase.execSQL("DROP TRIGGER IF EXISTS update_threads_error_on_delete_sms");
    }

    private void n(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("ALTER TABLE threads ADD COLUMN has_attachment INTEGER DEFAULT 0");
        z(sQLiteDatabase);
        sQLiteDatabase.execSQL("CREATE TRIGGER update_threads_on_insert_part  AFTER INSERT ON part  WHEN new.ct != 'text/plain' AND new.ct != 'application/smil'  BEGIN  UPDATE threads SET has_attachment=1 WHERE _id IN  (SELECT pdu.thread_id FROM part JOIN pdu ON pdu._id=part.mid  WHERE part._id=new._id LIMIT 1);  END");
        sQLiteDatabase.execSQL("CREATE TRIGGER update_threads_on_delete_part  AFTER DELETE ON part  WHEN old.ct != 'text/plain' AND old.ct != 'application/smil'  BEGIN  UPDATE threads SET has_attachment =  CASE  (SELECT COUNT(*) FROM part JOIN pdu  WHERE pdu.thread_id = threads._id  AND part.ct != 'text/plain' AND part.ct != 'application/smil'  AND part.mid = pdu._id) WHEN 0 THEN 0  ELSE 1  END;  END");
    }

    private void o(SQLiteDatabase sQLiteDatabase) {
        z(sQLiteDatabase);
        sQLiteDatabase.execSQL("CREATE TRIGGER update_threads_on_update_part  AFTER UPDATE of mid ON part  WHEN new.ct != 'text/plain' AND new.ct != 'application/smil'  BEGIN  UPDATE threads SET has_attachment=1 WHERE _id IN  (SELECT pdu.thread_id FROM part JOIN pdu ON pdu._id=part.mid  WHERE part._id=new._id LIMIT 1);  END");
    }

    private void p(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("ALTER TABLE sms ADD COLUMN locked INTEGER DEFAULT 0");
        sQLiteDatabase.execSQL("ALTER TABLE pdu ADD COLUMN locked INTEGER DEFAULT 0");
    }

    private void q(SQLiteDatabase sQLiteDatabase) {
        String string;
        Iterator it;
        sQLiteDatabase.execSQL("ALTER TABLE part ADD COLUMN text TEXT");
        SQLiteDatabase sQLiteDatabase2 = sQLiteDatabase;
        Cursor query = sQLiteDatabase2.query("part", new String[]{"_id", "_data", "text"}, "ct = 'text/plain' OR ct == 'application/smil'", null, null, null, null);
        ArrayList arrayList = new ArrayList();
        sQLiteDatabase.beginTransaction();
        if (query != null) {
            int columnIndex = query.getColumnIndex("_data");
            while (query.moveToNext()) {
                string = query.getString(columnIndex);
                if (string != null) {
                    try {
                        InputStream fileInputStream = new FileInputStream(string);
                        fileInputStream.read(new byte[fileInputStream.available()]);
                        fileInputStream.close();
                        arrayList.add(string);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (Throwable th) {
                        Throwable th2 = th;
                        sQLiteDatabase.endTransaction();
                        it = arrayList.iterator();
                        while (it.hasNext()) {
                            string = (String) it.next();
                            try {
                                new File(string).delete();
                            } catch (Throwable e2) {
                                Log.e("MmsSmsDatabaseHelper", "unable to clean up old mms file for " + string, e2);
                            }
                        }
                        if (query != null) {
                            query.close();
                        }
                    }
                }
            }
        }
        sQLiteDatabase.setTransactionSuccessful();
        sQLiteDatabase.endTransaction();
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            string = (String) it2.next();
            try {
                new File(string).delete();
            } catch (Throwable th22) {
                Log.e("MmsSmsDatabaseHelper", "unable to clean up old mms file for " + string, th22);
            }
        }
        if (query != null) {
            query.close();
        }
    }

    private void r(SQLiteDatabase sQLiteDatabase) {
        z(sQLiteDatabase);
        sQLiteDatabase.execSQL("CREATE TRIGGER update_threads_on_update_pdu  AFTER UPDATE of thread_id ON pdu  BEGIN  UPDATE threads SET has_attachment=1 WHERE _id IN  (SELECT pdu.thread_id FROM part JOIN pdu  WHERE part.ct != 'text/plain' AND part.ct != 'application/smil'  AND part.mid = pdu._id); END");
    }

    private void s(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("ALTER TABLE sms ADD COLUMN error_code INTEGER DEFAULT 0");
    }

    private void t(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("ALTER TABLE sms add COLUMN seen INTEGER DEFAULT 0");
        sQLiteDatabase.execSQL("ALTER TABLE pdu add COLUMN seen INTEGER DEFAULT 0");
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("seen", Integer.valueOf(1));
            Log.d("MmsSmsDatabaseHelper", "[MmsSmsDb] upgradeDatabaseToVersion51: updated " + sQLiteDatabase.update("sms", contentValues, "read=1", null) + " rows in sms table to have READ=1");
            Log.d("MmsSmsDatabaseHelper", "[MmsSmsDb] upgradeDatabaseToVersion51: updated " + sQLiteDatabase.update("pdu", contentValues, "read=1", null) + " rows in pdu table to have READ=1");
        } catch (Throwable e) {
            Log.e("MmsSmsDatabaseHelper", "[MmsSmsDb] upgradeDatabaseToVersion51 caught ", e);
        }
    }

    private void u(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("DROP TRIGGER IF EXISTS pdu_update_thread_read_on_update");
    }

    private void v(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("ALTER TABLE sms ADD COLUMN date_sent INTEGER DEFAULT 0");
        sQLiteDatabase.execSQL("ALTER TABLE pdu ADD COLUMN date_sent INTEGER DEFAULT 0");
    }

    private void w(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("DROP TRIGGER IF EXISTS delete_obsolete_threads_pdu");
        sQLiteDatabase.execSQL("DROP TRIGGER IF EXISTS delete_obsolete_threads_when_update_pdu");
    }

    public synchronized SQLiteDatabase getWritableDatabase() {
        SQLiteDatabase writableDatabase;
        Object obj;
        IntentFilter intentFilter;
        String str = true;
        synchronized (this) {
            writableDatabase = super.getWritableDatabase();
            if (!b) {
                b = true;
                boolean a = a(writableDatabase, "threads");
                boolean a2 = a(writableDatabase, "canonical_addresses");
                Log.d("MmsSmsDatabaseHelper", "[getWritableDatabase] hasAutoIncrementThreads: " + a + " hasAutoIncrementAddresses: " + a2);
                if (a) {
                    int obj2 = 1;
                } else {
                    writableDatabase.beginTransaction();
                    try {
                        x(writableDatabase);
                        writableDatabase.setTransactionSuccessful();
                        writableDatabase.endTransaction();
                        obj2 = 1;
                    } catch (Throwable th) {
                        writableDatabase.endTransaction();
                    }
                }
                String str2;
                if (a2) {
                    str2 = 1;
                } else {
                    writableDatabase.beginTransaction();
                    try {
                        y(writableDatabase);
                        writableDatabase.setTransactionSuccessful();
                        str2 = str;
                    } catch (Throwable th2) {
                        str = "MmsSmsDatabaseHelper";
                        Log.e(str, "Failed to add autoIncrement to canonical_addresses: " + th2.getMessage(), th2);
                        str2 = null;
                        if (obj2 != null) {
                        }
                        if (c) {
                            c = false;
                        }
                        if (this.e == null) {
                            Log.d("MmsSmsDatabaseHelper", "[getWritableDatabase] turning on storage monitor");
                            this.e = new a(this);
                            intentFilter = new IntentFilter();
                            intentFilter.addAction("android.intent.action.DEVICE_STORAGE_LOW");
                            intentFilter.addAction("android.intent.action.DEVICE_STORAGE_OK");
                            this.d.registerReceiver(this.e, intentFilter);
                        }
                        return writableDatabase;
                    } finally {
                        writableDatabase.endTransaction();
                    }
                }
                if (obj2 != null || str2 == null) {
                    if (c) {
                        c = false;
                    }
                    if (this.e == null) {
                        Log.d("MmsSmsDatabaseHelper", "[getWritableDatabase] turning on storage monitor");
                        this.e = new a(this);
                        intentFilter = new IntentFilter();
                        intentFilter.addAction("android.intent.action.DEVICE_STORAGE_LOW");
                        intentFilter.addAction("android.intent.action.DEVICE_STORAGE_OK");
                        this.d.registerReceiver(this.e, intentFilter);
                    }
                } else if (this.e != null) {
                    Log.d("MmsSmsDatabaseHelper", "Unregistering mLowStorageMonitor - we've upgraded");
                    this.d.unregisterReceiver(this.e);
                    this.e = null;
                }
            }
        }
        return writableDatabase;
    }

    private boolean a(SQLiteDatabase sQLiteDatabase, String str) {
        boolean z = false;
        Cursor rawQuery = sQLiteDatabase.rawQuery("SELECT sql FROM sqlite_master WHERE type='table' AND name='" + str + "'", null);
        if (rawQuery != null) {
            try {
                if (rawQuery.moveToFirst()) {
                    String string = rawQuery.getString(0);
                    if (string != null) {
                        z = string.contains("AUTOINCREMENT");
                    }
                    Log.d("MmsSmsDatabaseHelper", "[MmsSmsDb] tableName: " + str + " hasAutoIncrement: " + string + " result: " + z);
                }
                rawQuery.close();
            } catch (Throwable th) {
                rawQuery.close();
            }
        }
        return z;
    }

    private void x(SQLiteDatabase sQLiteDatabase) {
        if (a(sQLiteDatabase, "threads")) {
            Log.d("MmsSmsDatabaseHelper", "[MmsSmsDb] upgradeThreadsTableToAutoIncrement: already upgraded");
            return;
        }
        Log.d("MmsSmsDatabaseHelper", "[MmsSmsDb] upgradeThreadsTableToAutoIncrement: upgrading");
        sQLiteDatabase.execSQL("CREATE TABLE threads_temp (_id INTEGER PRIMARY KEY AUTOINCREMENT,date INTEGER DEFAULT 0,message_count INTEGER DEFAULT 0,recipient_ids TEXT,snippet TEXT,snippet_cs INTEGER DEFAULT 0,read INTEGER DEFAULT 1,type INTEGER DEFAULT 0,error INTEGER DEFAULT 0,has_attachment INTEGER DEFAULT 0);");
        sQLiteDatabase.execSQL("INSERT INTO threads_temp SELECT * from threads;");
        sQLiteDatabase.execSQL("DROP TABLE threads;");
        sQLiteDatabase.execSQL("ALTER TABLE threads_temp RENAME TO threads;");
    }

    private void y(SQLiteDatabase sQLiteDatabase) {
        if (a(sQLiteDatabase, "canonical_addresses")) {
            Log.d("MmsSmsDatabaseHelper", "[MmsSmsDb] upgradeAddressTableToAutoIncrement: already upgraded");
            return;
        }
        Log.d("MmsSmsDatabaseHelper", "[MmsSmsDb] upgradeAddressTableToAutoIncrement: upgrading");
        sQLiteDatabase.execSQL("CREATE TABLE canonical_addresses_temp (_id INTEGER PRIMARY KEY AUTOINCREMENT,address TEXT);");
        sQLiteDatabase.execSQL("INSERT INTO canonical_addresses_temp SELECT * from canonical_addresses;");
        sQLiteDatabase.execSQL("DROP TABLE canonical_addresses;");
        sQLiteDatabase.execSQL("ALTER TABLE canonical_addresses_temp RENAME TO canonical_addresses;");
    }

    private void z(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("UPDATE threads SET has_attachment=1 WHERE _id IN  (SELECT DISTINCT pdu.thread_id FROM part  JOIN pdu ON pdu._id=part.mid  WHERE part.ct != 'text/plain' AND part.ct != 'application/smil')");
    }
}
