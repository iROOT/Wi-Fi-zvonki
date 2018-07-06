package com.mavenir.android.calllog.provider;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseUtils.InsertHelper;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import com.mavenir.android.calllog.a.a;
import java.util.HashSet;
import java.util.Set;

public class b implements a {
    private final String a;
    private final SQLiteDatabase b;
    private final InsertHelper c;
    private final Context d;
    private final Uri e;
    private final boolean f;

    public b(String str, SQLiteDatabase sQLiteDatabase, Context context) {
        this(str, sQLiteDatabase, null, context);
    }

    public b(String str, InsertHelper insertHelper, Context context) {
        this(str, null, insertHelper, context);
    }

    private b(String str, SQLiteDatabase sQLiteDatabase, InsertHelper insertHelper, Context context) {
        this.a = str;
        this.b = sQLiteDatabase;
        this.c = insertHelper;
        this.d = context;
        this.e = null;
        this.f = this.a.equals("calls");
    }

    public long a(ContentValues contentValues) {
        Set b = b(contentValues);
        long insert = this.c.insert(contentValues);
        if (insert > 0 && b.size() != 0) {
            a(ContentUris.withAppendedId(this.e, insert), b);
        }
        if (insert > 0 && this.f) {
            a();
        }
        return insert;
    }

    private void a() {
        this.d.getContentResolver().notifyChange(a.a, null, false);
    }

    private void a(Uri uri, Set<String> set) {
        if (this.f) {
            a(uri, (Set) set, "android.intent.action.NEW_VOICEMAIL", "android.intent.action.PROVIDER_CHANGED");
            return;
        }
        a(uri, (Set) set, "android.intent.action.PROVIDER_CHANGED");
    }

    public int a(String str, ContentValues contentValues, String str2, String[] strArr) {
        Set a = a(str2, strArr);
        a.addAll(b(contentValues));
        int update = this.b.update(str, contentValues, str2, strArr);
        if (update > 0 && a.size() != 0) {
            a(this.e, a, "android.intent.action.PROVIDER_CHANGED");
        }
        if (update > 0 && this.f) {
            a();
        }
        return update;
    }

    public int a(String str, String str2, String[] strArr) {
        Set a = a(str2, strArr);
        int delete = this.b.delete(str, str2, strArr);
        if (delete > 0 && a.size() != 0) {
            a(this.e, a, "android.intent.action.PROVIDER_CHANGED");
        }
        if (delete > 0 && this.f) {
            a();
        }
        return delete;
    }

    private Set<String> a(String str, String[] strArr) {
        return new HashSet();
    }

    private Set<String> b(ContentValues contentValues) {
        return new HashSet();
    }

    private void a(Uri uri, Set<String> set, String... strArr) {
    }
}
