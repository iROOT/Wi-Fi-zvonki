package com.fgmicrotec.mobile.android.fgvoipcommon;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import com.mavenir.android.common.q;
import java.util.HashMap;

public class MessagesRepositoryProvider extends ContentProvider {
    private static HashMap<String, String> a = new HashMap();
    private static HashMap<String, String> b = new HashMap();
    private static final UriMatcher c = new UriMatcher(-1);
    private a d;

    private static class a extends SQLiteOpenHelper {
        a(Context context) {
            super(context, "messages_repository.db", null, 18);
        }

        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            sQLiteDatabase.execSQL("CREATE TABLE common (_id INTEGER PRIMARY KEY,message_reference INTEGER NOT NULL DEFAULT 0,message_reference_str TEXT NOT NULL DEFAULT '',conversation_id TEXT NOT NULL DEFAULT '',message_type INTEGER NOT NULL DEFAULT 0,message_status INTEGER NOT NULL DEFAULT 0,message_error INTEGER NOT NULL DEFAULT 0,message_body TEXT,message_time INTEGER NOT NULL DEFAULT 0,message_subject TEXT,correspondent_phone_number TEXT,correspondent_name TEXT,message_protocol INTEGER NOT NULL DEFAULT 0,accompanied_media TEXT,locked_message INTEGER NOT NULL DEFAULT 0,delivery_report INTEGER NOT NULL DEFAULT 0,delivery_reporter_ids TEXT NOT NULL DEFAULT '',unconfirmed_sending_success TEXT NOT NULL DEFAULT '',file_size INTEGER NOT NULL DEFAULT 0,file_name TEXT NOT NULL DEFAULT '',file_type INTEGER NOT NULL DEFAULT 0,file_hash TEXT NOT NULL DEFAULT '',display_report INTEGER NOT NULL DEFAULT 0,bytes_trasfered INTEGER NOT NULL DEFAULT 0,conversation_closed INTEGER NOT NULL DEFAULT 0,conversation_session_uri TEXT NOT NULL DEFAULT '',correspondent_status TEXT NOT NULL DEFAULT '',content_type INTEGER NOT NULL DEFAULT 0,content TEXT NOT NULL DEFAULT '',display_time INTEGER NOT NULL DEFAULT 0,message_uri TEXT NOT NULL DEFAULT '',mstore_uid INTEGER NOT NULL DEFAULT 0);");
            String str = "CREATE INDEX common_conversation_id ON common(conversation_id);";
            q.b("Voip", str);
            sQLiteDatabase.execSQL(str);
        }

        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            while (i < i2) {
                if (i == 1) {
                    String str = "ALTER TABLE common ADD COLUMN locked_message INTEGER NOT NULL DEFAULT 0;";
                    q.b("Voip", str);
                    sQLiteDatabase.execSQL(str);
                }
                if (i == 2) {
                    str = "ALTER TABLE common ADD COLUMN delivery_report INTEGER NOT NULL DEFAULT 0;";
                    q.b("Voip", str);
                    sQLiteDatabase.execSQL(str);
                    str = "ALTER TABLE common ADD COLUMN delivery_reporter_ids TEXT NOT NULL DEFAULT '';";
                    q.b("Voip", str);
                    sQLiteDatabase.execSQL(str);
                }
                if (i == 3) {
                    str = "ALTER TABLE common ADD COLUMN unconfirmed_sending_success TEXT NOT NULL DEFAULT '';";
                    q.b("Voip", str);
                    sQLiteDatabase.execSQL(str);
                }
                if (i == 4) {
                    str = "ALTER TABLE common ADD COLUMN file_size INTEGER NOT NULL DEFAULT 0;";
                    q.b("Voip", str);
                    sQLiteDatabase.execSQL(str);
                }
                if (i == 5) {
                    str = "ALTER TABLE common ADD COLUMN file_name TEXT NOT NULL DEFAULT '';";
                    q.b("Voip", str);
                    sQLiteDatabase.execSQL(str);
                    str = "ALTER TABLE common ADD COLUMN file_type INTEGER NOT NULL DEFAULT 0;";
                    q.b("Voip", str);
                    sQLiteDatabase.execSQL(str);
                }
                if (i == 6) {
                    str = "ALTER TABLE common ADD COLUMN display_report INTEGER NOT NULL DEFAULT 0;";
                    q.b("Voip", str);
                    sQLiteDatabase.execSQL(str);
                }
                if (i == 7) {
                    str = "ALTER TABLE common ADD COLUMN bytes_trasfered INTEGER NOT NULL DEFAULT 0;";
                    q.b("Voip", str);
                    sQLiteDatabase.execSQL(str);
                }
                if (i == 8) {
                    str = "CREATE INDEX IF NOT EXISTS common_conversation_id ON common(conversation_id);";
                    q.b("Voip", str);
                    sQLiteDatabase.execSQL(str);
                    str = "UPDATE common SET conversation_id=correspondent_phone_number;";
                    q.b("Voip", str);
                    sQLiteDatabase.execSQL(str);
                }
                if (i == 9) {
                    str = "ALTER TABLE common ADD COLUMN conversation_closed INTEGER NOT NULL DEFAULT 0;";
                    q.b("Voip", str);
                    sQLiteDatabase.execSQL(str);
                    str = "ALTER TABLE common ADD COLUMN conversation_session_uri TEXT NOT NULL DEFAULT '';";
                    q.b("Voip", str);
                    sQLiteDatabase.execSQL(str);
                    str = "ALTER TABLE common ADD COLUMN correspondent_status TEXT NOT NULL DEFAULT '';";
                    q.b("Voip", str);
                    sQLiteDatabase.execSQL(str);
                }
                if (i == 10) {
                    str = "ALTER TABLE common ADD COLUMN content_type INTEGER NOT NULL DEFAULT 0;";
                    q.b("Voip", str);
                    sQLiteDatabase.execSQL(str);
                    str = "ALTER TABLE common ADD COLUMN content TEXT NOT NULL DEFAULT '';";
                    q.b("Voip", str);
                    sQLiteDatabase.execSQL(str);
                }
                if (i == 11) {
                    str = "ALTER TABLE common ADD COLUMN message_reference_str TEXT NOT NULL DEFAULT '';";
                    q.b("Voip", str);
                    sQLiteDatabase.execSQL(str);
                }
                if (i == 12) {
                    str = "ALTER TABLE common ADD COLUMN display_time INTEGER NOT NULL DEFAULT 0;";
                    q.b("Voip", str);
                    sQLiteDatabase.execSQL(str);
                }
                if (i == 13) {
                    str = "ALTER TABLE common ADD COLUMN message_uri TEXT NOT NULL DEFAULT '';";
                    q.b("Voip", str);
                    sQLiteDatabase.execSQL(str);
                }
                if (i == 15) {
                    str = "ALTER TABLE common ADD COLUMN mstore_uid INTEGER NOT NULL DEFAULT 0;";
                    q.b("Voip", str);
                    sQLiteDatabase.execSQL(str);
                }
                if (i == 16) {
                    str = "ALTER TABLE common ADD COLUMN message_error INTEGER NOT NULL DEFAULT 0;";
                    q.b("Voip", str);
                    sQLiteDatabase.execSQL(str);
                }
                if (i == 17) {
                    str = "ALTER TABLE common ADD COLUMN file_hash TEXT NOT NULL DEFAULT '';";
                    q.b("Voip", str);
                    sQLiteDatabase.execSQL(str);
                }
                i++;
            }
        }
    }

    public boolean onCreate() {
        this.d = new a(getContext());
        return true;
    }

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        String str3;
        SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
        switch (c.match(uri)) {
            case 1:
                sQLiteQueryBuilder.setTables("common");
                sQLiteQueryBuilder.setProjectionMap(a);
                str3 = null;
                break;
            case 2:
                sQLiteQueryBuilder.setTables("common as message left join common as header on (message.conversation_id= header.conversation_id and header.message_type=5 )");
                sQLiteQueryBuilder.setProjectionMap(b);
                str3 = "message.conversation_id";
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        Cursor query = sQLiteQueryBuilder.query(this.d.getReadableDatabase(), strArr, str, strArr2, str3, null, str2);
        query.setNotificationUri(getContext().getContentResolver(), uri);
        return query;
    }

    public String getType(Uri uri) {
        switch (c.match(uri)) {
            case 1:
                return "vnd.android.cursor.dir/vnd.fgmicrotec.provider.messages.common";
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
    }

    public Uri insert(Uri uri, ContentValues contentValues) {
        if (c.match(uri) == 1) {
            ContentValues contentValues2;
            if (contentValues != null) {
                contentValues2 = new ContentValues(contentValues);
            } else {
                contentValues2 = new ContentValues();
            }
            if (!contentValues2.containsKey("message_reference")) {
                contentValues2.put("message_reference", Integer.valueOf(0));
            }
            if (!contentValues2.containsKey("message_reference_str")) {
                contentValues2.put("message_reference_str", "");
            }
            if (!contentValues2.containsKey("conversation_id")) {
                contentValues2.put("conversation_id", "");
            }
            if (!contentValues2.containsKey("message_type")) {
                contentValues2.put("message_type", Integer.valueOf(0));
            }
            if (!contentValues2.containsKey("message_status")) {
                contentValues2.put("message_status", Integer.valueOf(0));
            }
            if (!contentValues2.containsKey("message_error")) {
                contentValues2.put("message_error", Integer.valueOf(0));
            }
            if (!contentValues2.containsKey("message_body")) {
                contentValues2.put("message_body", "");
            }
            if (!contentValues2.containsKey("message_time")) {
                contentValues2.put("message_time", Integer.valueOf(0));
            }
            if (!contentValues2.containsKey("message_subject")) {
                contentValues2.put("message_subject", "");
            }
            if (!contentValues2.containsKey("correspondent_phone_number")) {
                contentValues2.put("correspondent_phone_number", "");
            }
            if (!contentValues2.containsKey("correspondent_name")) {
                contentValues2.put("correspondent_name", "");
            }
            if (!contentValues2.containsKey("message_protocol")) {
                contentValues2.put("message_protocol", Integer.valueOf(0));
            }
            if (!contentValues2.containsKey("accompanied_media")) {
                contentValues2.put("accompanied_media", "");
            }
            if (!contentValues2.containsKey("locked_message")) {
                contentValues2.put("locked_message", Integer.valueOf(0));
            }
            if (!contentValues2.containsKey("delivery_report")) {
                contentValues2.put("delivery_report", Integer.valueOf(0));
            }
            if (!contentValues2.containsKey("delivery_reporter_ids")) {
                contentValues2.put("delivery_reporter_ids", "");
            }
            if (!contentValues2.containsKey("unconfirmed_sending_success")) {
                contentValues2.put("unconfirmed_sending_success", "");
            }
            if (!contentValues2.containsKey("file_name")) {
                contentValues2.put("file_name", "");
            }
            if (!contentValues2.containsKey("file_size")) {
                contentValues2.put("file_size", Integer.valueOf(0));
            }
            if (!contentValues2.containsKey("file_type")) {
                contentValues2.put("file_type", Integer.valueOf(0));
            }
            if (!contentValues2.containsKey("file_hash")) {
                contentValues2.put("file_hash", "");
            }
            if (!contentValues2.containsKey("display_report")) {
                contentValues2.put("display_report", Integer.valueOf(0));
            }
            if (!contentValues2.containsKey("bytes_trasfered")) {
                contentValues2.put("bytes_trasfered", Integer.valueOf(0));
            }
            if (!contentValues2.containsKey("conversation_closed")) {
                contentValues2.put("conversation_closed", Integer.valueOf(0));
            }
            if (!contentValues2.containsKey("conversation_session_uri")) {
                contentValues2.put("conversation_session_uri", "");
            }
            if (!contentValues2.containsKey("correspondent_status")) {
                contentValues2.put("correspondent_status", "");
            }
            if (!contentValues2.containsKey("content_type")) {
                contentValues2.put("content_type", Integer.valueOf(0));
            }
            if (!contentValues2.containsKey("content")) {
                contentValues2.put("content", "");
            }
            if (!contentValues2.containsKey("display_time")) {
                contentValues2.put("display_time", Integer.valueOf(0));
            }
            if (!contentValues2.containsKey("message_uri")) {
                contentValues2.put("message_uri", "");
            }
            if (!contentValues2.containsKey("mstore_uid")) {
                contentValues2.put("mstore_uid", Integer.valueOf(0));
            }
            long insert = this.d.getWritableDatabase().insert("common", "conversation_id", contentValues2);
            if (insert > 0) {
                Uri withAppendedId = ContentUris.withAppendedId(com.fgmicrotec.mobile.android.fgvoipcommon.c.a.a, insert);
                getContext().getContentResolver().notifyChange(withAppendedId, null);
                return withAppendedId;
            }
            throw new SQLException("Failed to insert row into " + uri);
        }
        throw new IllegalArgumentException("Unknown or unsupported INSERT URI " + uri);
    }

    public int delete(Uri uri, String str, String[] strArr) {
        SQLiteDatabase writableDatabase = this.d.getWritableDatabase();
        switch (c.match(uri)) {
            case 1:
                int delete = writableDatabase.delete("common", str, strArr);
                getContext().getContentResolver().notifyChange(uri, null);
                return delete;
            default:
                throw new IllegalArgumentException("Unknown or unsupported delete URI " + uri);
        }
    }

    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        SQLiteDatabase writableDatabase = this.d.getWritableDatabase();
        switch (c.match(uri)) {
            case 1:
                int update = writableDatabase.update("common", contentValues, str, strArr);
                getContext().getContentResolver().notifyChange(uri, null);
                return update;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
    }

    static {
        c.addURI("com.fgmicrotec.provider.MessagesRepository", "common", 1);
        a.put("_id", "_id");
        a.put("message_reference", "message_reference");
        a.put("message_reference_str", "message_reference_str");
        a.put("conversation_id", "conversation_id");
        a.put("message_type", "message_type");
        a.put("message_status", "message_status");
        a.put("message_error", "message_error");
        a.put("message_body", "message_body");
        a.put("message_time", "message_time");
        a.put("message_subject", "message_subject");
        a.put("correspondent_phone_number", "correspondent_phone_number");
        a.put("correspondent_name", "correspondent_name");
        a.put("message_protocol", "message_protocol");
        a.put("accompanied_media", "accompanied_media");
        a.put("locked_message", "locked_message");
        a.put("delivery_report", "delivery_report");
        a.put("delivery_reporter_ids", "delivery_reporter_ids");
        a.put("unconfirmed_sending_success", "unconfirmed_sending_success");
        a.put("file_name", "file_name");
        a.put("file_size", "file_size");
        a.put("file_type", "file_type");
        a.put("file_hash", "file_hash");
        a.put("display_report", "display_report");
        a.put("bytes_trasfered", "bytes_trasfered");
        a.put("conversation_closed", "conversation_closed");
        a.put("conversation_session_uri", "conversation_session_uri");
        a.put("correspondent_status", "correspondent_status");
        a.put("content_type", "content_type");
        a.put("content", "content");
        a.put("display_time", "display_time");
        a.put("message_uri", "message_uri");
        a.put("mstore_uid", "mstore_uid");
        b.put("conversation_id", "message.conversation_id");
        b.put("message_count", "count(*) as message_count");
        b.put("message_min_id", "min(message._id) as message_min_id");
        b.put("message_max_id", "max(message._id) as message_max_id");
        b.put("conversation_subject", "header.message_subject as conversation_subject");
        b.put("conversation_correspondent_number", "header.correspondent_phone_number as conversation_correspondent_number");
        b.put("conversation_correspondent_name", "header.correspondent_name as conversation_correspondent_name");
        b.put("message.message_status", "message.message_status");
        c.addURI("com.fgmicrotec.provider.MessagesRepository", "conversationsview", 2);
    }
}
