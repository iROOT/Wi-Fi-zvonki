package com.mavenir.android.messaging.a;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.ContactsContract.Contacts;
import android.support.v4.widget.j;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.b.a.b.c;
import com.b.a.b.c.b;
import com.fgmicrotec.mobile.android.fgvoip.f.e;
import com.fgmicrotec.mobile.android.fgvoip.f.f;
import com.fgmicrotec.mobile.android.fgvoip.f.g;
import com.fgmicrotec.mobile.android.fgvoip.f.h;
import com.fgmicrotec.mobile.android.fgvoip.f.k;
import com.mavenir.android.common.q;
import com.mavenir.android.common.t;
import com.mavenir.android.common.t.d;

public class a extends j implements OnScrollListener {
    private Cursor j;
    private String k = null;
    private c l = new com.b.a.b.c.a().a(new b(100)).a();

    private class a extends AsyncTask<Object, Long, String> {
        final /* synthetic */ a a;
        private TextView b;
        private long c;

        private a(a aVar) {
            this.a = aVar;
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return a(objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            a((String) obj);
        }

        protected /* synthetic */ void onProgressUpdate(Object[] objArr) {
            a((Long[]) objArr);
        }

        protected String a(Object... objArr) {
            this.b = (TextView) objArr[0];
            this.c = ((Long) objArr[1]).longValue();
            publishProgress(new Long[]{(Long) objArr[1]});
            return this.a.a(this.c);
        }

        protected void a(Long... lArr) {
            if (this.b != null) {
                this.b.setText(k.messages_searching_hint);
            }
        }

        protected void a(String str) {
            if (this.b != null && this.b.getTag().toString().equals("" + this.c)) {
                this.b.setText(str);
            }
        }
    }

    public a(Context context, ListView listView) {
        super(context, h.message_conversation_listview_item, null, false);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        Cursor cursor = this.j;
        if (cursor.moveToPosition(i)) {
            if (view == null) {
                view = a(this.d, cursor, viewGroup);
            }
            a(view, this.d, cursor);
            return view;
        }
        throw new IllegalStateException("couldn't move cursor to position " + i);
    }

    public void a(View view, Context context, Cursor cursor) {
        b bVar = (b) view.getTag();
        com.mavenir.android.messaging.c.c a = com.mavenir.android.messaging.c.c.a(context, cursor, false, false);
        String a2 = a(bVar, a);
        String b = b(bVar, a);
        String c = c(bVar, a);
        f(bVar, a);
        String d = d(bVar, a);
        e(bVar, a);
        g(bVar, a);
        view.setContentDescription(this.d.getString(k.cd_conversation_item, new Object[]{a2, c, b, d}));
    }

    private String a(b bVar, com.mavenir.android.messaging.c.c cVar) {
        bVar.e.setText(cVar.g().a(";"));
        return bVar.e.getText().toString();
    }

    private String b(b bVar, com.mavenir.android.messaging.c.c cVar) {
        bVar.i.setText(cVar.l());
        return d.a(cVar.h(), com.mavenir.android.common.t.d.a.dd_MM_yyyy_slash) + "," + com.mavenir.android.common.t.a.c(d.a(cVar.h(), com.mavenir.android.common.t.d.a.kk_mm));
    }

    private String c(b bVar, com.mavenir.android.messaging.c.c cVar) {
        if (this.k == null || this.k.length() <= 0) {
            bVar.f.setText(cVar.i());
        } else {
            bVar.f.setTag("" + cVar.c());
            new a().execute(new Object[]{bVar.f, new Long(r0)});
        }
        return cVar.i();
    }

    private String d(b bVar, com.mavenir.android.messaging.c.c cVar) {
        String str = "";
        if (cVar.o()) {
            bVar.h.setImageResource(f.ic_message_draft);
            return this.d.getString(k.messages_draft);
        } else if (cVar.m()) {
            bVar.h.setImageResource(f.ic_message_failure);
            return this.d.getString(k.message_details_status_failed);
        } else {
            bVar.h.setImageResource(f.ic_message_success);
            return this.d.getString(k.message_details_status_sent);
        }
    }

    private void e(b bVar, com.mavenir.android.messaging.c.c cVar) {
        if (cVar.j()) {
            bVar.e.setTypeface(null, 1);
            bVar.f.setTypeface(null, 1);
            bVar.i.setTypeface(null, 1);
            bVar.i.setTextColor(this.d.getResources().getColor(e.h3g_green));
            return;
        }
        bVar.e.setTypeface(null, 0);
        bVar.f.setTypeface(null, 0);
        bVar.i.setTypeface(null, 0);
        bVar.i.setTextColor(this.d.getResources().getColor(e.gray));
    }

    private void f(b bVar, com.mavenir.android.messaging.c.c cVar) {
        bVar.g.setVisibility(cVar.k() ? 0 : 8);
    }

    private void g(b bVar, com.mavenir.android.messaging.c.c cVar) {
        if (cVar.g().size() > 1) {
            bVar.a.setImageResource(f.avatar_group);
            bVar.b.setText(k.empty_string);
        } else if (cVar.g().size() != 0) {
            com.mavenir.android.messaging.c.a aVar = (com.mavenir.android.messaging.c.a) cVar.g().get(0);
            if (aVar.c() <= 0) {
                com.b.a.b.d.a().a("", bVar.a, this.l);
                bVar.b.setText(t.c.a(bVar.e.getText()));
                return;
            }
            Uri withAppendedId = ContentUris.withAppendedId(Contacts.CONTENT_URI, aVar.a());
            Uri.withAppendedPath(withAppendedId, "photo");
            com.b.a.b.d.a().a(withAppendedId.toString(), bVar.a, this.l);
            bVar.b.setText(k.empty_string);
        }
    }

    public View a(Context context, Cursor cursor, ViewGroup viewGroup) {
        View a = super.a(context, cursor, viewGroup);
        b bVar = new b();
        bVar.a = (ImageView) a.findViewById(g.avatar_image);
        bVar.b = (TextView) a.findViewById(g.avatar_initials);
        bVar.c = (TextView) a.findViewById(g.group_name);
        bVar.d = (TextView) a.findViewById(g.group_topic);
        bVar.e = (TextView) a.findViewById(g.latest_chat_author);
        bVar.f = (TextView) a.findViewById(g.latest_chat_message);
        bVar.g = (ImageView) a.findViewById(g.message_attachment);
        bVar.h = (ImageView) a.findViewById(g.message_status);
        bVar.i = (TextView) a.findViewById(g.last_message_time);
        a.setTag(bVar);
        return a;
    }

    public Cursor b(Cursor cursor) {
        this.j = cursor;
        return super.b(cursor);
    }

    public void a(Cursor cursor, String str) {
        this.j = cursor;
        b(cursor);
        this.k = str;
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
    }

    private String a(long j) {
        String str;
        Exception e;
        Throwable th;
        Cursor query;
        try {
            Uri withAppendedId = ContentUris.withAppendedId(com.mavenir.android.messaging.provider.g.g.b, j);
            query = this.d.getContentResolver().query(withAppendedId, new String[]{"date", "body"}, "body LIKE '%" + this.k + "%'", null, "date DESC");
            if (query != null) {
                try {
                    if (query.getCount() > 1) {
                        str = query.getCount() + " " + this.d.getResources().getString(k.search_messages_match);
                        if (!(query == null || query.isClosed())) {
                            query.close();
                        }
                    } else if (query.moveToFirst()) {
                        str = query.getString(query.getColumnIndex("body"));
                        if (!(query == null || query.isClosed())) {
                            query.close();
                        }
                    }
                } catch (Exception e2) {
                    e = e2;
                    try {
                        q.c("ConversationListAdapter", e.getLocalizedMessage(), e.getCause());
                        str = "Error: " + this.d.getResources().getString(k.status_no_matches);
                        query.close();
                        return str;
                    } catch (Throwable th2) {
                        th = th2;
                        if (!(query == null || query.isClosed())) {
                            query.close();
                        }
                        throw th;
                    }
                }
                return str;
            }
            str = this.d.getResources().getString(k.status_no_matches);
            if (!(query == null || query.isClosed())) {
                query.close();
            }
        } catch (Exception e3) {
            e = e3;
            query = null;
            q.c("ConversationListAdapter", e.getLocalizedMessage(), e.getCause());
            str = "Error: " + this.d.getResources().getString(k.status_no_matches);
            if (!(query == null || query.isClosed())) {
                query.close();
            }
            return str;
        } catch (Throwable th3) {
            th = th3;
            query = null;
            query.close();
            throw th;
        }
        return str;
    }
}
