package com.mavenir.android.calllog.provider;

import android.annotation.SuppressLint;
import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.DatabaseUtils.InsertHelper;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.os.Build.VERSION;
import android.text.TextUtils;
import java.util.HashMap;
import java.util.List;

public class CallLogProvider extends ContentProvider {
    static final String[] a = new String[]{"number", "presentation", "type", "features", "date", "duration", "data_usage", "subscription_component_name", "subscription_id"};
    private static final String b = c.b("type", 4);
    private static final UriMatcher c = new UriMatcher(-1);
    private static final HashMap<String, String> d = new HashMap();
    private static final Integer h = new Integer(4);
    private a e;
    private InsertHelper f;
    private boolean g;

    protected static class a extends SQLiteOpenHelper {
        a(Context context) {
            super(context, "calllog.db", null, 3);
        }

        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            sQLiteDatabase.execSQL("CREATE TABLE calls (_id INTEGER PRIMARY KEY AUTOINCREMENT,number TEXT,presentation INTEGER NOT NULL DEFAULT 1,date INTEGER,duration INTEGER,data_usage INTEGER,type INTEGER,features INTEGER NOT NULL DEFAULT 0,subscription_component_name TEXT,subscription_id TEXT,new INTEGER,name TEXT,numbertype INTEGER,numberlabel TEXT,countryiso TEXT,voicemail_uri TEXT,is_read INTEGER,geocoded_location TEXT,lookup_uri TEXT,matched_number TEXT,normalized_number TEXT,photo_id INTEGER NOT NULL DEFAULT 0,formatted_number TEXT,has_content INTEGER,mime_type TEXT,source_data TEXT,source_package TEXT,transcription TEXT,custom_lineid TEXT,message_uri TEXT);");
        }

        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            while (i < i2) {
                if (i == 1) {
                    sQLiteDatabase.execSQL("ALTER TABLE calls ADD COLUMN custom_lineid TEXT;");
                }
                if (i == 2) {
                    sQLiteDatabase.execSQL("ALTER TABLE calls ADD COLUMN message_uri TEXT;");
                }
                i++;
            }
        }
    }

    static {
        com.mavenir.android.calllog.a.a("com.mavenir.provider.mingle.call_log");
        c.addURI(com.mavenir.android.calllog.a.a, "calls", 1);
        c.addURI(com.mavenir.android.calllog.a.a, "calls/#", 2);
        c.addURI(com.mavenir.android.calllog.a.a, "calls/filter/*", 3);
        d.put("_id", "_id");
        d.put("number", "number");
        d.put("presentation", "presentation");
        d.put("date", "date");
        d.put("duration", "duration");
        d.put("data_usage", "data_usage");
        d.put("type", "type");
        d.put("features", "features");
        d.put("subscription_component_name", "subscription_component_name");
        d.put("subscription_id", "subscription_id");
        d.put("new", "new");
        d.put("voicemail_uri", "voicemail_uri");
        d.put("transcription", "transcription");
        d.put("is_read", "is_read");
        d.put("name", "name");
        d.put("numbertype", "numbertype");
        d.put("numberlabel", "numberlabel");
        d.put("countryiso", "countryiso");
        d.put("geocoded_location", "geocoded_location");
        d.put("lookup_uri", "lookup_uri");
        d.put("matched_number", "matched_number");
        d.put("normalized_number", "normalized_number");
        d.put("photo_id", "photo_id");
        d.put("formatted_number", "formatted_number");
        d.put("custom_lineid", "custom_lineid");
        d.put("message_uri", "message_uri");
    }

    public boolean onCreate() {
        this.e = a(getContext());
        this.g = true;
        return true;
    }

    protected a a(Context context) {
        return new a(context);
    }

    @SuppressLint({"NewApi"})
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        String str3;
        SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
        sQLiteQueryBuilder.setTables("calls");
        sQLiteQueryBuilder.setProjectionMap(d);
        if (VERSION.SDK_INT >= 14) {
            sQLiteQueryBuilder.setStrict(true);
        }
        d dVar = new d(str);
        a(uri, dVar, true);
        switch (c.match(uri)) {
            case 1:
                break;
            case 2:
                dVar.a(c.a("_id", c(uri)));
                break;
            case 3:
                Object obj;
                List pathSegments = uri.getPathSegments();
                if (pathSegments.size() >= 2) {
                    obj = (String) pathSegments.get(2);
                } else {
                    obj = null;
                }
                if (!TextUtils.isEmpty(obj)) {
                    sQLiteQueryBuilder.appendWhere("PHONE_NUMBERS_EQUAL(number, ");
                    sQLiteQueryBuilder.appendWhereEscapeString(obj);
                    sQLiteQueryBuilder.appendWhere(this.g ? ", 1)" : ", 0)");
                    break;
                }
                sQLiteQueryBuilder.appendWhere("presentation!=1");
                break;
            default:
                throw new IllegalArgumentException("Unknown URL " + uri);
        }
        int a = a(uri, "limit", 0);
        int a2 = a(uri, "offset", 0);
        if (a > 0) {
            str3 = a2 + "," + a;
        } else {
            str3 = null;
        }
        Cursor query = sQLiteQueryBuilder.query(this.e.getReadableDatabase(), strArr, dVar.a(), strArr2, null, null, str2, str3);
        if (query != null) {
            query.setNotificationUri(getContext().getContentResolver(), com.mavenir.android.calllog.a.b);
        }
        return query;
    }

    private int a(Uri uri, String str, int i) {
        String queryParameter = uri.getQueryParameter(str);
        if (queryParameter != null) {
            try {
                i = Integer.parseInt(queryParameter);
            } catch (Throwable e) {
                throw new IllegalArgumentException("Integer required for " + str + " parameter but value '" + queryParameter + "' was found instead.", e);
            }
        }
        return i;
    }

    public String getType(Uri uri) {
        switch (c.match(uri)) {
            case 1:
                return "vnd.android.cursor.dir/calls";
            case 2:
                return "vnd.android.cursor.item/calls";
            case 3:
                return "vnd.android.cursor.dir/calls";
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
    }

    public Uri insert(Uri uri, ContentValues contentValues) {
        c.a(d, contentValues);
        if (a(contentValues)) {
            b(uri);
        }
        if (this.f == null) {
            this.f = new InsertHelper(this.e.getWritableDatabase(), "calls");
        }
        long a = a(this.f).a(new ContentValues(contentValues));
        if (a > 0) {
            return ContentUris.withAppendedId(uri, a);
        }
        return null;
    }

    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        c.a(d, contentValues);
        if (a(contentValues)) {
            b(uri);
        }
        d dVar = new d(str);
        a(uri, dVar, false);
        SQLiteDatabase writableDatabase = this.e.getWritableDatabase();
        switch (c.match(uri)) {
            case 1:
                break;
            case 2:
                dVar.a(c.a("_id", c(uri)));
                break;
            default:
                throw new UnsupportedOperationException("Cannot update URL: " + uri);
        }
        return a(writableDatabase).a("calls", contentValues, dVar.a(), strArr);
    }

    public int delete(Uri uri, String str, String[] strArr) {
        d dVar = new d(str);
        a(uri, dVar, false);
        SQLiteDatabase writableDatabase = this.e.getWritableDatabase();
        switch (c.match(uri)) {
            case 1:
                return a(writableDatabase).a("calls", dVar.a(), strArr);
            default:
                throw new UnsupportedOperationException("Cannot delete that URL: " + uri);
        }
    }

    protected Context a() {
        return getContext();
    }

    private a a(SQLiteDatabase sQLiteDatabase) {
        return new b("calls", sQLiteDatabase, a());
    }

    private a a(InsertHelper insertHelper) {
        return new b("calls", insertHelper, a());
    }

    private boolean a(ContentValues contentValues) {
        return h.equals(contentValues.getAsInteger("type"));
    }

    private void a(Uri uri, d dVar, boolean z) {
        if (!a(uri)) {
            dVar.a(b);
        } else if (!z) {
        }
    }

    private boolean a(Uri uri) {
        return false;
    }

    private void b(Uri uri) {
        if (!a(uri)) {
            throw new IllegalArgumentException(String.format("Uri %s cannot be used for voicemail record. Please set '%s=true' in the uri.", new Object[]{uri, "Calls.ALLOW_VOICEMAILS_PARAM_KEY"}));
        }
    }

    private long c(Uri uri) {
        try {
            return Long.parseLong((String) uri.getPathSegments().get(1));
        } catch (Throwable e) {
            throw new IllegalArgumentException("Invalid call id in uri: " + uri, e);
        }
    }
}
