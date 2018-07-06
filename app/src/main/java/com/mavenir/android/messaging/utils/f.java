package com.mavenir.android.messaging.utils;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import com.mavenir.android.common.q;
import com.mavenir.android.messaging.provider.g.g;
import com.mavenir.android.settings.c.k;

public abstract class f {
    private static a a;

    public static class a extends f {
        private static final String[] a = new String[]{"thread_id", "msg_count"};
        private static final String[] b = new String[]{"_id", "thread_id", "address", "body", "date", "read", "type", "status"};

        protected int a(Context context) {
            return k.o();
        }

        protected void a(Context context, long j, int i) {
            Throwable th;
            Cursor cursor;
            q.a("MessageRecycler", "SMS: deleteMessagesForConversation()");
            ContentResolver contentResolver = context.getContentResolver();
            Uri withAppendedId = ContentUris.withAppendedId(g.b, j);
            try {
                Cursor query = contentResolver.query(withAppendedId, b, "locked=0", null, "date DESC");
                if (query == null) {
                    try {
                        q.d("MessageRecycler", "SMS: deleteMessagesForConversation(): no thread found");
                        if (query != null) {
                            query.close();
                            return;
                        }
                        return;
                    } catch (Throwable th2) {
                        th = th2;
                        cursor = query;
                        if (cursor != null) {
                            cursor.close();
                        }
                        throw th;
                    }
                }
                int count = query.getCount();
                int i2 = count - i;
                q.a("MessageRecycler", "SMS: deleteMessagesForConversation(): limit: " + i + " count: " + count + " numberToDelete: " + i2);
                if (i2 > 0) {
                    query.move(i);
                    q.a("MessageRecycler", "SMS: deleteMessagesForConversation(): deletedCount: " + ((long) contentResolver.delete(withAppendedId, "locked=0 AND date < " + query.getLong(query.getColumnIndex("date")), null)));
                    if (query != null) {
                        query.close();
                    }
                } else if (query != null) {
                    query.close();
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
    }

    protected abstract int a(Context context);

    protected abstract void a(Context context, long j, int i);

    public static a a() {
        if (a == null) {
            a = new a();
        }
        return a;
    }

    public void a(Context context, long j) {
        if (b(context)) {
            try {
                a(context, j, a(context));
            } catch (Exception e) {
                q.c("MessageRecycler", e.getLocalizedMessage(), e.getCause());
            }
        }
    }

    private boolean b(Context context) {
        return k.n();
    }
}
