package com.mavenir.android.messaging.provider;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.net.Uri.Builder;
import android.provider.BaseColumns;
import android.text.TextUtils;
import com.fgmicrotec.mobile.android.fgvoip.FgVoIP;
import com.mavenir.android.common.q;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class g {
    public static final String a = (b() ? "mms-sms" : "com.mavenir.provider.mingle.mms-sms");
    public static final boolean b = (!b());

    public interface a extends BaseColumns {
    }

    public interface c extends BaseColumns {
    }

    public static final class b implements c {
        public static final String[] a = new String[]{"_id", "address"};
        private static Uri b = Uri.parse(e.a + "/canonical-addresses");
        private static Uri c = Uri.parse(e.a + "/canonical-address");

        public static final Cursor a(ContentResolver contentResolver, long j, long j2) {
            return contentResolver.query(b.buildUpon().appendQueryParameter("threadId", Long.toString(j)).build(), a, "_id=?", new String[]{String.valueOf(j2)}, null);
        }
    }

    public static final class d implements a {
        public static final Uri a;
        public static final Uri b = Uri.withAppendedPath(a, "report-request");
        public static final Uri c = Uri.withAppendedPath(a, "report-status");
        public static final String[] d = new String[]{"_id", "thread_id", "sub", "sub_cs", "date", "date_sent", "read", "m_type", "msg_box", "d_rpt", "rr", "err_type", "locked", "st"};
        public static final Pattern e = Pattern.compile("\\s*(\"[^\"]*\"|[^<>\"]+)\\s*<([^<>]+)>\\s*");
        public static final Pattern f = Pattern.compile("\\s*\"([^\"]*)\"\\s*");

        public static final class a implements a {
            public static final Uri a = Uri.parse(d.a + "/drafts");
        }

        public static final class b implements a {
            public static final Uri a = Uri.parse(d.a + "/inbox");

            public static Uri a(ContentResolver contentResolver, String str, String str2, String str3, Long l, boolean z, long j, String str4, String str5) {
                return d.a(contentResolver, a, str, str2, str3, 1, l, z, false, j, str4, str5);
            }
        }

        public static final class c implements a {
            public static final Uri a = Uri.parse(d.a + "/outbox");

            public static Uri a(ContentResolver contentResolver, String str, String str2, String str3, Long l, boolean z, long j, String str4, String str5) {
                return d.a(contentResolver, a, str, str2, str3, 4, l, z, false, j, str4, str5);
            }
        }

        public static final class d implements a {
            public static final Uri a = Uri.parse(d.a + "/sent");

            public static Uri a(ContentResolver contentResolver, String str, String str2, String str3, Long l, boolean z, long j, String str4, String str5) {
                return d.a(contentResolver, a, str, str2, str3, 2, l, z, false, j, str4, str5);
            }
        }

        static {
            Uri parse;
            if (g.b()) {
                parse = Uri.parse("content://mms");
            } else {
                parse = Uri.parse("content://com.mavenir.provider.mingle.mms");
            }
            a = parse;
        }

        public static String a(String str) {
            Matcher matcher = e.matcher(str);
            if (matcher.matches()) {
                return matcher.group(2);
            }
            return str;
        }

        public static boolean b(String str) {
            if (TextUtils.isEmpty(str)) {
                return false;
            }
            return com.mavenir.android.common.t.g.a.matcher(a(str)).matches();
        }

        public static boolean c(String str) {
            if (TextUtils.isEmpty(str)) {
                return false;
            }
            return com.mavenir.android.common.t.g.b.matcher(str).matches();
        }

        public static Uri a(ContentResolver contentResolver, Uri uri, String str, String str2, String str3, int i, Long l, boolean z, boolean z2, long j, String str4, String str5) {
            Integer valueOf;
            ContentValues contentValues = new ContentValues();
            if (j != -1) {
                contentValues.put("thread_id", Long.valueOf(j));
            }
            if (l != null) {
                contentValues.put("date", l);
            }
            contentValues.put("msg_box", Integer.valueOf(i));
            String str6 = "read";
            if (z) {
                valueOf = Integer.valueOf(1);
            } else {
                valueOf = Integer.valueOf(0);
            }
            contentValues.put(str6, valueOf);
            contentValues.put("sub", str3);
            contentValues.put("ct_t", "application/vnd.wap.multipart.related");
            contentValues.put("m_type", Integer.valueOf(i));
            Uri insert = contentResolver.insert(uri, contentValues);
            str6 = insert.getLastPathSegment().trim();
            try {
                a(contentResolver, str6, str5, str2, str4);
            } catch (Exception e) {
                e.printStackTrace();
            }
            for (String a : str.split(" ")) {
                try {
                    a(contentResolver, str6, a);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            return insert;
        }

        private static Uri a(ContentResolver contentResolver, String str, String str2, String str3, String str4) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("mid", str);
            contentValues.put("ct", str2);
            contentValues.put("text", str3);
            contentValues.put("fn", str4);
            contentValues.put("cid", Long.valueOf(System.currentTimeMillis()));
            return contentResolver.insert(Uri.parse(a + "/" + str + "/part"), contentValues);
        }

        private static Uri a(ContentResolver contentResolver, String str, String str2) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("msg_id", str);
            contentValues.put("address", str2);
            return contentResolver.insert(Uri.parse(a + "/" + str + "/addr"), contentValues);
        }
    }

    public static final class e implements BaseColumns {
        public static final Uri a;
        public static final Uri b = Uri.parse(a + "/conversations");
        public static final Uri c = Uri.parse(a + "/messages/byphone");
        public static final Uri d = Uri.parse(a + "/undelivered");
        public static final Uri e = Uri.parse(a + "/draft");
        public static final Uri f = Uri.parse(a + "/locked");
        public static final Uri g = Uri.parse(a + "/search");

        static {
            Uri parse;
            if (g.b()) {
                parse = Uri.parse("content://mms-sms");
            } else {
                parse = Uri.parse("content://com.mavenir.provider.mingle.mms-sms");
            }
            a = parse;
        }
    }

    public static final class f implements BaseColumns {
        public static final Uri a;
        public static final Uri b = Uri.parse("content://sms");
        public static final Uri c = Uri.parse(a + "/queued");
        public static final Uri d = Uri.parse(a + "/status");
        public static final String[] e = new String[]{"_id", "thread_id", "address", "body", "date", "date_sent", "read", "type", "status", "locked", "error_code"};

        public static final class a implements BaseColumns {
            public static final Uri a = Uri.parse(f.a + "/conversations");
        }

        public static final class b implements BaseColumns {
            public static final Uri a = Uri.parse(f.a + "/draft");
        }

        public static final class c implements BaseColumns {
            public static final Uri a = Uri.parse(f.a + "/inbox");

            public static Uri a(ContentResolver contentResolver, String str, String str2, String str3, Long l, boolean z) {
                return f.a(contentResolver, a, str, str2, str3, l, z, false);
            }
        }

        public static final class d implements BaseColumns {
            public static final Uri a = Uri.parse(f.a + "/outbox");

            public static Uri a(ContentResolver contentResolver, String str, String str2, String str3, Long l, boolean z, long j) {
                return f.a(contentResolver, a, str, str2, str3, l, true, z, j);
            }
        }

        static {
            Uri parse;
            if (g.b()) {
                parse = Uri.parse("content://sms");
            } else {
                parse = Uri.parse("content://com.mavenir.provider.mingle.sms");
            }
            a = parse;
        }

        public static Uri a(ContentResolver contentResolver, Uri uri, String str, String str2, String str3, Long l, boolean z, boolean z2) {
            return a(contentResolver, uri, str, str2, str3, l, z, z2, -1);
        }

        public static Uri a(ContentResolver contentResolver, Uri uri, String str, String str2, String str3, Long l, boolean z, boolean z2, long j) {
            ContentValues contentValues = new ContentValues(7);
            contentValues.put("address", str);
            if (l != null) {
                contentValues.put("date", l);
            }
            contentValues.put("read", z ? Integer.valueOf(1) : Integer.valueOf(0));
            contentValues.put("subject", str3);
            contentValues.put("body", str2);
            if (z2) {
                contentValues.put("status", Integer.valueOf(64));
            }
            if (j != -1) {
                contentValues.put("thread_id", Long.valueOf(j));
            }
            return contentResolver.insert(uri, contentValues);
        }

        public static boolean a(Context context, Uri uri, int i, int i2) {
            boolean z = true;
            if (uri == null) {
                return false;
            }
            boolean z2;
            boolean z3;
            switch (i) {
                case 1:
                case 3:
                    z2 = false;
                    z3 = false;
                    break;
                case 2:
                case 4:
                    z2 = true;
                    z3 = false;
                    break;
                case 5:
                case 6:
                    z2 = false;
                    z3 = true;
                    break;
                default:
                    return false;
            }
            ContentValues contentValues = new ContentValues(3);
            contentValues.put("type", Integer.valueOf(i));
            if (z3) {
                contentValues.put("read", Integer.valueOf(0));
            } else if (z2) {
                contentValues.put("read", Integer.valueOf(1));
            }
            contentValues.put("error_code", Integer.valueOf(i2));
            if (1 != context.getContentResolver().update(uri, contentValues, null, null)) {
                z = false;
            }
            return z;
        }
    }

    public interface h extends BaseColumns {
    }

    public static final class g implements h {
        public static final Uri a = Uri.parse(e.a + "/conversations?simple=true");
        public static final Uri b = Uri.withAppendedPath(e.a, "conversations");
        public static final Uri c = Uri.withAppendedPath(b, "obsolete");
        public static final String[] d = new String[]{"_id", "date", "message_count", "recipient_ids", "snippet", "snippet_cs", "read", "error", "has_attachment"};
        public static final String[] e = new String[]{"_id", "read"};
        private static final String[] f = new String[]{"_id"};
        private static final Uri g = Uri.parse(e.a + "/threadID");

        public static Uri a(long j) {
            return ContentUris.withAppendedId(b, j);
        }

        public static long a(Context context, String str) {
            Set hashSet = new HashSet();
            hashSet.add(str);
            return a(context, hashSet);
        }

        public static long a(Context context, Set<String> set) {
            Builder buildUpon = g.buildUpon();
            for (String str : set) {
                String str2;
                if (d.b(str2)) {
                    str2 = d.a(str2);
                }
                buildUpon.appendQueryParameter("recipient", str2);
            }
            Uri build = buildUpon.build();
            Cursor query = context.getContentResolver().query(build, f, null, null, null);
            if (query != null) {
                try {
                    if (query.moveToFirst()) {
                        long j = query.getLong(0);
                        return j;
                    }
                    q.d("Telephony", "getOrCreateThreadId returned no rows!");
                    query.close();
                } finally {
                    query.close();
                }
            }
            q.d("Telephony", "getOrCreateThreadId() failed with uri " + build.toString());
            throw new IllegalArgumentException("getOrCreateThreadId(): unable to find or allocate a thread ID.");
        }

        public static Cursor a(ContentResolver contentResolver) {
            return contentResolver.query(a, d, null, null, "date DESC");
        }

        public static Cursor a(ContentResolver contentResolver, long j) {
            return contentResolver.query(a, d, "_id=?", new String[]{String.valueOf(j)}, "date DESC");
        }
    }

    private static boolean b() {
        if (FgVoIP.U() == null || !FgVoIP.U().af()) {
            return false;
        }
        return true;
    }
}
