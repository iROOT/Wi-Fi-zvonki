package com.mavenir.android.messaging.provider;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.MatrixCursor.RowBuilder;
import android.net.Uri;

public class MessagesProvider extends ContentProvider {
    private static final UriMatcher a = new UriMatcher(-1);

    static {
        a.addURI("com.hutchison3g.threeintouch.mms-sms", "conversations", 0);
    }

    public boolean onCreate() {
        return false;
    }

    public String getType(Uri uri) {
        return "vnd.android-dir/mms-sms";
    }

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        ContentResolver contentResolver = getContext().getContentResolver();
        switch (a.match(uri)) {
            case 0:
                return a(contentResolver.query(Uri.parse("content://mms-sms/conversations?simple=true"), strArr, str, strArr2, str2), contentResolver.query(Uri.parse("content://com.mavenir.provider.mingle.mms-sms/conversations?simple=true"), strArr, str, strArr2, str2));
            default:
                return null;
        }
    }

    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        return 0;
    }

    public int delete(Uri uri, String str, String[] strArr) {
        return 0;
    }

    private MatrixCursor a(Cursor cursor, Cursor cursor2) {
        boolean z = true;
        MatrixCursor matrixCursor = new MatrixCursor(b(cursor2));
        cursor2.getColumnIndex("date");
        boolean z2 = true;
        while (true) {
            if (!z2 && !z) {
                return matrixCursor;
            }
            z2 = a(cursor);
            z = a(cursor2);
            if (!z2 || !z) {
                if (z2) {
                    a(matrixCursor, cursor);
                } else if (z) {
                    a(matrixCursor, cursor2);
                }
            }
        }
    }

    private void a(MatrixCursor matrixCursor, Cursor cursor) {
        RowBuilder newRow = matrixCursor.newRow();
        newRow.add(Long.valueOf(0));
        int columnIndex = cursor.getColumnIndex("_id");
        newRow.add(Long.valueOf(cursor.getLong(columnIndex)));
        for (int i = 0; i < cursor.getColumnCount(); i++) {
            if (i != columnIndex) {
                if (cursor.getType(i) == 1) {
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
        }
    }

    private boolean a(Cursor cursor) {
        if (cursor == null || !cursor.moveToNext()) {
            return false;
        }
        return true;
    }

    private String[] b(Cursor cursor) {
        String[] strArr = new String[(cursor.getColumnCount() + 1)];
        strArr[0] = "_id";
        strArr[1] = "_originalId";
        int i = 2;
        String[] columnNames = cursor.getColumnNames();
        int length = columnNames.length;
        int i2 = 0;
        while (i2 < length) {
            int i3;
            String str = columnNames[i2];
            if (str.toLowerCase().equals("_id")) {
                i3 = i;
            } else {
                i3 = i + 1;
                strArr[i] = str;
            }
            i2++;
            i = i3;
        }
        return strArr;
    }
}
