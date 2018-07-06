package com.mavenir.android.messaging.c;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Contacts;
import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;
import com.mavenir.android.common.q;
import com.mavenir.android.common.t.f;
import com.mavenir.android.messaging.provider.g.b;
import java.io.InputStream;

public class a {
    private long a;
    private long b;
    private String c;
    private long d;
    private Bitmap e;
    private String f;
    private int g;
    private String h;

    private static class a {
        private static a b = null;
        private static final String[] c = new String[]{"_id", "contact_id", "display_name", "photo_id", "data1", "data2", "data3"};
        private Context a;

        protected a(Context context) {
            this.a = context;
        }

        protected static a a(Context context) {
            if (b == null) {
                b = new a(context);
            }
            return b;
        }

        private String a(long j, long j2) {
            Exception e;
            Throwable th;
            String str = null;
            Cursor a;
            try {
                a = b.a(this.a.getContentResolver(), j, j2);
                if (a != null) {
                    try {
                        if (a.moveToFirst()) {
                            str = a.getString(a.getColumnIndex("address"));
                        }
                    } catch (Exception e2) {
                        e = e2;
                        try {
                            q.c("ContactLoader", e.getLocalizedMessage(), e.getCause());
                            a.close();
                            return str;
                        } catch (Throwable th2) {
                            th = th2;
                            if (!(a == null || a.isClosed())) {
                                a.close();
                            }
                            throw th;
                        }
                    }
                }
                if (!(a == null || a.isClosed())) {
                    a.close();
                }
            } catch (Exception e3) {
                e = e3;
                a = null;
                q.c("ContactLoader", e.getLocalizedMessage(), e.getCause());
                if (!(a == null || a.isClosed())) {
                    a.close();
                }
                return str;
            } catch (Throwable th3) {
                a = null;
                th = th3;
                a.close();
                throw th;
            }
            return str;
        }

        private a a(String str) {
            a aVar = new a(str);
            String d = f.d(str);
            CharSequence toCallerIDMinMatch = PhoneNumberUtils.toCallerIDMinMatch(d);
            if (!(TextUtils.isEmpty(d) || TextUtils.isEmpty(toCallerIDMinMatch))) {
                if (d.length() > 7 && d.startsWith("0")) {
                    d = d.replaceFirst("0", "");
                }
                String valueOf = String.valueOf(d.length());
                String[] strArr = new String[]{toCallerIDMinMatch, valueOf, d};
                Cursor query = this.a.getContentResolver().query(Phone.CONTENT_URI, c, " _ID IN  (SELECT DISTINCT lookup.data_id  FROM  (SELECT data_id, normalized_number, length(normalized_number) as len  FROM phone_lookup  WHERE min_match = ? ORDER BY len) AS lookup  WHERE  (substr(lookup.normalized_number, lookup.len - ? + 1) = ?))", strArr, null);
                if (query == null) {
                    q.d("ContactLoader", "queryContactInfoByNumber(" + str + ") returned NULL cursor!");
                    return aVar;
                }
                try {
                    if (query.moveToFirst()) {
                        a(aVar, query);
                    }
                    if (!(query == null || query.isClosed())) {
                        query.close();
                    }
                } catch (Throwable th) {
                    if (!(query == null || query.isClosed())) {
                        query.close();
                    }
                }
            }
            return aVar;
        }

        private void a(a aVar, Cursor cursor) {
            synchronized (aVar) {
                aVar.a = cursor.getLong(0);
                aVar.b = cursor.getLong(1);
                aVar.c = cursor.getString(2);
                aVar.d = cursor.getLong(3);
                aVar.f = cursor.getString(4);
                aVar.g = cursor.getInt(5);
                aVar.h = cursor.getString(6);
            }
        }

        private static Bitmap b(ContentResolver contentResolver, long j) {
            Bitmap bitmap = null;
            InputStream openContactPhotoInputStream = Contacts.openContactPhotoInputStream(contentResolver, ContentUris.withAppendedId(Contacts.CONTENT_URI, j));
            if (openContactPhotoInputStream != null) {
                try {
                    bitmap = BitmapFactory.decodeStream(openContactPhotoInputStream);
                } finally {
                    try {
                        openContactPhotoInputStream.close();
                    } catch (Throwable e) {
                        q.c("ContactLoader", "getPhoto()", e);
                    }
                }
            }
            return bitmap;
        }
    }

    public a() {
        this.a = -1;
        this.b = -1;
        this.d = -1;
    }

    public a(String str) {
        this.a = -1;
        this.b = -1;
        this.f = str;
        this.d = -1;
    }

    public static a a(Context context, long j, long j2, boolean z) {
        a a = a.a(context).a(a.a(context).a(j, j2));
        if (z && a.b > -1) {
            a.e = a.b(context.getContentResolver(), a.b);
        }
        return a;
    }

    public static a a(Context context, String str, boolean z) {
        a a = a.a(context).a(str);
        if (z && a.b > -1) {
            a.e = a.b(context.getContentResolver(), a.b);
        }
        return a;
    }

    public long a() {
        return this.b;
    }

    public String b() {
        return this.c;
    }

    public long c() {
        return this.d;
    }

    public String d() {
        return this.f;
    }

    public boolean e() {
        if (this.b > 0) {
            return true;
        }
        return false;
    }

    public String toString() {
        return (b() == null || b().length() <= 0) ? d() : b();
    }
}
