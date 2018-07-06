package com.mavenir.android.a;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.provider.CallLog.Calls;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.PhoneLookup;
import android.support.v4.widget.j;
import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.b.a.b.d;
import com.fgmicrotec.mobile.android.fgvoip.FgVoIP;
import com.fgmicrotec.mobile.android.fgvoip.f.g;
import com.fgmicrotec.mobile.android.fgvoip.f.h;
import com.fgmicrotec.mobile.android.fgvoip.f.k;
import com.mavenir.android.common.q;
import com.mavenir.android.common.t;
import com.mavenir.android.common.t.f;
import java.util.HashMap;
import java.util.LinkedList;

public class c extends j {
    public static String[] j = new String[]{"_id", "type", "name", "number", "numbertype", "numberlabel", "date", "log_count", "voicemail_uri", "grouped_call_id"};
    public static String[] k = new String[]{"_id", "display_name", "type", "label", "number"};
    private Cursor l;
    private OnClickListener m;
    private com.b.a.b.c n;
    private HashMap<String, b> o;
    private boolean p = false;
    private Thread q;
    private LinkedList<a> r;
    private Handler s = new Handler(this) {
        final /* synthetic */ c a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    this.a.notifyDataSetChanged();
                    return;
                default:
                    return;
            }
        }
    };

    public class a {
        public int a;
        public String b;
        public String c;
        public int d;
        public String e;
        public long f;
        public int g;
        public int h;
        public String i;
        final /* synthetic */ c j;

        public a(c cVar) {
            this.j = cVar;
        }

        public void a(Cursor cursor) {
            this.a = cursor.getPosition();
            this.b = cursor.getString(cursor.getColumnIndex("name"));
            this.c = cursor.getString(cursor.getColumnIndex("number"));
            this.d = cursor.getInt(cursor.getColumnIndex("numbertype"));
            this.e = cursor.getString(cursor.getColumnIndex("numberlabel"));
            this.f = cursor.getLong(cursor.getColumnIndex("date"));
            this.g = cursor.getInt(cursor.getColumnIndex("type"));
            this.h = cursor.getInt(cursor.getColumnIndex("log_count"));
            this.i = cursor.getString(cursor.getColumnIndex("voicemail_uri"));
        }
    }

    public static class b {
        public static b f = new b();
        public long a;
        public String b;
        public String c;
        public int d;
        public String e;

        public void a(Cursor cursor) {
            this.a = cursor.getLong(cursor.getColumnIndex("_id"));
            this.b = cursor.getString(cursor.getColumnIndex("display_name"));
            this.c = cursor.getString(cursor.getColumnIndex("number"));
            this.d = cursor.getInt(cursor.getColumnIndex("type"));
            this.e = cursor.getString(cursor.getColumnIndex("label"));
        }
    }

    public c(Context context, OnClickListener onClickListener) {
        super(context, h.call_log_list_item, null, false);
        this.d = context;
        this.l = null;
        this.m = onClickListener;
        this.o = new HashMap();
        this.r = new LinkedList();
        this.n = new com.b.a.b.c.a().a(Config.RGB_565).b(true).c(false).a(new com.b.a.b.c.b(100)).a();
        d();
    }

    public void a(View view, Context context, Cursor cursor) {
        CharSequence string;
        d dVar = (d) view.getTag();
        a aVar = new a(this);
        aVar.a(cursor);
        e(aVar, dVar);
        String d = d(aVar, dVar);
        String a = a(aVar, dVar);
        b(aVar, dVar);
        String charSequence = dVar.a.getText().toString();
        String c = c(aVar, dVar);
        f(aVar, dVar);
        dVar.j.setTag(Integer.valueOf(aVar.a));
        dVar.j.setContentDescription(this.d.getString(k.cd_call_log_call_button, new Object[]{a}));
        a(aVar);
        String str = "";
        if (aVar.g == 2) {
            string = this.d.getString(k.cd_call_log_item_outgoing, new Object[]{d, a, charSequence + " " + c});
        } else {
            string = this.d.getString(k.cd_call_log_item_incoming, new Object[]{d, a, charSequence + " " + c});
        }
        view.setContentDescription(string);
    }

    private void a(a aVar) {
        b bVar = (b) this.o.get(aVar.c);
        if (bVar == null) {
            this.o.put(aVar.c, b.f);
            synchronized (this.r) {
                this.r.add(aVar);
                this.r.notifyAll();
            }
        } else if (bVar == b.f) {
        } else {
            if (!TextUtils.equals(bVar.b, aVar.b) || bVar.d != aVar.d || !TextUtils.equals(bVar.e, aVar.e)) {
                synchronized (this.r) {
                    this.r.add(aVar);
                    this.r.notifyAll();
                }
            }
        }
    }

    private String a(a aVar, d dVar) {
        CharSequence charSequence = Phone.getTypeLabel(this.d.getResources(), aVar.d, aVar.e).toString();
        if (aVar.b != null && aVar.b.length() > 0) {
            dVar.e.setText(aVar.b);
            dVar.f.setText(charSequence);
        } else if (FgVoIP.U().e(aVar.c)) {
            dVar.f.setText(aVar.c);
            dVar.e.setText(k.call_log_emergency_number);
        } else if (f.j(aVar.c)) {
            dVar.e.setText(k.call_log_blocked_number);
            dVar.f.setText(k.empty_string);
        } else {
            dVar.e.setText(aVar.c);
            dVar.f.setText(k.call_log_unsaved);
            return com.mavenir.android.common.t.a.b(aVar.c);
        }
        return dVar.e.getText().toString();
    }

    private void b(a aVar, d dVar) {
        dVar.c.setText(com.mavenir.android.common.t.c.a(dVar.e.getText()));
        dVar.b.setImageBitmap(null);
        if (TextUtils.isEmpty(aVar.c) || TextUtils.isEmpty(aVar.b)) {
            d.a().a(dVar.b);
            dVar.b.setImageResource(com.fgmicrotec.mobile.android.fgvoip.f.f.avatar_unsaved);
            dVar.c.setText("");
            return;
        }
        Uri a = com.mavenir.android.common.j.a(aVar.c);
        final TextView textView = dVar.c;
        d.a().a(a.toString(), dVar.b, this.n, new com.b.a.b.f.a(this) {
            final /* synthetic */ c b;

            public void a(String str, View view) {
            }

            public void a(String str, View view, com.b.a.b.a.b bVar) {
            }

            public void a(String str, View view, Bitmap bitmap) {
                textView.setText("");
            }

            public void b(String str, View view) {
            }
        });
    }

    private String c(a aVar, d dVar) {
        Object c = t.d.c(aVar.f);
        dVar.h.setText(c);
        return com.mavenir.android.common.t.a.c(c);
    }

    private String d(a aVar, d dVar) {
        String str = "";
        if (aVar.g == 1) {
            dVar.d.setImageResource(com.fgmicrotec.mobile.android.fgvoip.f.f.ic_call_incoming);
            return this.d.getResources().getQuantityString(com.fgmicrotec.mobile.android.fgvoip.f.j.cd_incoming_call, aVar.h, new Object[]{Integer.valueOf(aVar.h)});
        } else if (aVar.g == 2) {
            dVar.d.setImageResource(com.fgmicrotec.mobile.android.fgvoip.f.f.ic_call_outgoing);
            return this.d.getResources().getQuantityString(com.fgmicrotec.mobile.android.fgvoip.f.j.cd_outgoing_call, aVar.h, new Object[]{Integer.valueOf(aVar.h)});
        } else {
            dVar.d.setImageResource(com.fgmicrotec.mobile.android.fgvoip.f.f.ic_call_missed);
            return this.d.getResources().getQuantityString(com.fgmicrotec.mobile.android.fgvoip.f.j.cd_missed_call, aVar.h, new Object[]{Integer.valueOf(aVar.h)});
        }
    }

    private void e(a aVar, d dVar) {
        if (aVar.h == 1) {
            dVar.g.setText("");
        } else {
            dVar.g.setText("(" + aVar.h + ")");
        }
    }

    private void f(a aVar, d dVar) {
        if (!FgVoIP.U().s()) {
            return;
        }
        if (TextUtils.isEmpty(aVar.i)) {
            dVar.i.setVisibility(8);
            return;
        }
        if (aVar.i.contains("Whisper")) {
            dVar.i.setImageResource(com.fgmicrotec.mobile.android.fgvoip.f.f.ic_whisper_play);
        } else {
            dVar.i.setImageResource(com.fgmicrotec.mobile.android.fgvoip.f.f.ic_shout_play);
        }
        dVar.i.setVisibility(0);
        dVar.i.setTag(Integer.valueOf(aVar.a));
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        Cursor cursor = this.l;
        if (cursor.moveToPosition(i)) {
            if (view == null) {
                view = a(this.d, cursor, viewGroup);
            }
            a(view, this.d, cursor);
            a(view, i, cursor, true);
            return view;
        }
        throw new IllegalStateException("couldn't move cursor to position " + i);
    }

    public Cursor b(Cursor cursor) {
        this.l = d(cursor);
        return super.b(this.l);
    }

    private Cursor d(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        MatrixCursor matrixCursor = new MatrixCursor(j);
        if (cursor != null && cursor.getCount() > 0) {
            String[] strArr = new String[10];
            cursor.moveToPosition(-1);
            String str = null;
            String str2 = "";
            String str3 = "";
            int i = 1;
            while (cursor.moveToNext()) {
                String[] strArr2;
                int i2;
                String string = cursor.getString(cursor.getColumnIndex("number"));
                String b = t.d.b(cursor.getLong(cursor.getColumnIndex("date")));
                boolean compare = PhoneNumberUtils.compare(str, string);
                boolean equals = str2.equals(b);
                if (str != null && compare && equals) {
                    int i3 = i + 1;
                    str3 = str3 + "," + String.valueOf(cursor.getLong(cursor.getColumnIndex("_id")));
                    strArr[9] = str3;
                    strArr2 = strArr;
                    int i4 = i3;
                    b = str;
                    i2 = i4;
                } else {
                    str3 = "";
                    str3 = String.valueOf(cursor.getLong(cursor.getColumnIndex("_id")));
                    if (cursor.getPosition() > 0) {
                        strArr[7] = String.valueOf(i);
                        matrixCursor.addRow(strArr);
                    }
                    String str4 = b;
                    b = string;
                    strArr2 = new String[]{String.valueOf(cursor.getLong(cursor.getColumnIndex("_id"))), String.valueOf(cursor.getInt(cursor.getColumnIndex("type"))), cursor.getString(cursor.getColumnIndex("name")), string, String.valueOf(cursor.getInt(cursor.getColumnIndex("numbertype"))), cursor.getString(cursor.getColumnIndex("numberlabel")), String.valueOf(r8), String.valueOf(1), cursor.getString(cursor.getColumnIndex("voicemail_uri")), String.valueOf(cursor.getLong(cursor.getColumnIndex("_id")))};
                    i2 = 1;
                    str2 = str4;
                }
                i = i2;
                strArr = strArr2;
                str = b;
            }
            strArr[7] = String.valueOf(i);
            matrixCursor.addRow(strArr);
            cursor.moveToPosition(-1);
        }
        q.a("CallLogAdapter", "buildNewCursor(): data count " + matrixCursor.getCount());
        return matrixCursor;
    }

    public View a(Context context, Cursor cursor, ViewGroup viewGroup) {
        View a = super.a(context, cursor, viewGroup);
        d dVar = new d();
        dVar.a = (TextView) a.findViewById(g.header_text);
        dVar.b = (ImageView) a.findViewById(g.avatar_image);
        dVar.c = (TextView) a.findViewById(g.avatar_initials);
        dVar.d = (ImageView) a.findViewById(g.call_type);
        dVar.e = (TextView) a.findViewById(g.list_item_title);
        dVar.f = (TextView) a.findViewById(g.list_item_number);
        dVar.g = (TextView) a.findViewById(g.entry_count);
        dVar.h = (TextView) a.findViewById(g.call_time);
        dVar.i = (ImageView) a.findViewById(g.play_button);
        dVar.j = (ImageView) a.findViewById(g.call_button);
        if (dVar.i != null) {
            dVar.i.setOnClickListener(this.m);
        }
        if (dVar.j != null) {
            dVar.j.setOnClickListener(this.m);
        }
        a.setTag(dVar);
        return a;
    }

    public void a(Cursor cursor) {
        super.a(cursor);
        this.l = cursor;
    }

    private void a(View view, int i, Cursor cursor, boolean z) {
        String str;
        d dVar = (d) view.getTag();
        int columnIndex = cursor.getColumnIndex("date");
        long j = cursor.getLong(columnIndex);
        CharSequence charSequence = DateFormat.format("dd/MM/yyyy", j).toString();
        int position = cursor.getPosition();
        boolean moveToPrevious = cursor.moveToPrevious();
        if (cursor.getPosition() == -1) {
            str = null;
        } else {
            str = DateFormat.format("dd/MM/yyyy", cursor.getLong(columnIndex)).toString();
        }
        if (str == null || !str.equals(charSequence)) {
            dVar.a.setVisibility(0);
        } else {
            dVar.a.setVisibility(8);
        }
        if (t.d.e(j)) {
            charSequence = this.d.getString(k.call_log_date_today);
        } else if (t.d.f(j)) {
            charSequence = this.d.getString(k.call_log_date_yesterday);
        }
        dVar.a.setText(charSequence);
        if (moveToPrevious) {
            cursor.moveToPosition(position);
        }
    }

    public String a(int i) {
        int position = this.l.getPosition();
        this.l.moveToPosition(i);
        String str = "";
        if (!(this.l.isBeforeFirst() || this.l.isAfterLast())) {
            try {
                str = this.l.getString(this.l.getColumnIndex("grouped_call_id"));
            } catch (Exception e) {
                q.d("CallLogAdapter", "getLogEntryID(): " + e);
            }
        }
        this.l.moveToPosition(position);
        return str;
    }

    public String b(int i) {
        int position = this.l.getPosition();
        this.l.moveToPosition(i);
        String str = "";
        if (!(this.l.isBeforeFirst() || this.l.isAfterLast())) {
            try {
                str = this.l.getString(this.l.getColumnIndex("number"));
            } catch (Exception e) {
                q.d("CallLogAdapter", "getContactNumber(): " + e);
            }
        }
        this.l.moveToPosition(position);
        return str;
    }

    public String c(int i) {
        int position = this.l.getPosition();
        this.l.moveToPosition(i);
        String str = "";
        if (!(this.l.isBeforeFirst() || this.l.isAfterLast())) {
            try {
                str = this.l.getString(this.l.getColumnIndex("voicemail_uri"));
            } catch (Exception e) {
                q.d("CallLogAdapter", "getAudioFilePath(): " + e);
            }
        }
        this.l.moveToPosition(position);
        return str;
    }

    public void d() {
        this.p = false;
        this.q = new Thread(new Runnable(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void run() {
                q.a("CallLogAdapter", "startSyncProcessing(): starting thread");
                while (!this.a.p) {
                    a aVar = null;
                    synchronized (this.a.r) {
                        if (this.a.r.isEmpty()) {
                            try {
                                this.a.r.wait(1000);
                            } catch (InterruptedException e) {
                            }
                        } else {
                            aVar = (a) this.a.r.removeFirst();
                        }
                    }
                    if (aVar != null) {
                        this.a.b(aVar);
                    }
                }
            }
        });
        this.q.setPriority(1);
        this.q.start();
    }

    public void e() {
        this.p = true;
        if (this.q != null) {
            this.q.interrupt();
            q.a("CallLogAdapter", "stopSyncProcessing(): stopping thread");
        }
    }

    public void f() {
        if (this.o != null) {
            synchronized (this.o) {
                q.a("CallLogAdapter", "clearCache()");
                this.o.clear();
            }
        }
    }

    private void b(a aVar) {
        Cursor query;
        Object e;
        Cursor cursor = null;
        try {
            b bVar = (b) this.o.get(aVar.c);
            if (bVar == null || bVar == b.f) {
                query = this.d.getContentResolver().query(Uri.withAppendedPath(PhoneLookup.CONTENT_FILTER_URI, Uri.encode(aVar.c)), k, null, null, null);
                if (query != null) {
                    if (query.moveToFirst()) {
                        bVar = new b();
                        bVar.a(query);
                        this.o.put(bVar.c, bVar);
                        synchronized (this.r) {
                            if (this.r.isEmpty()) {
                                this.s.sendEmptyMessage(1);
                            }
                        }
                    }
                }
            } else {
                q.a("CallLogAdapter", "queryContact(): number already processed");
                synchronized (this.r) {
                    if (this.r.isEmpty()) {
                        this.s.sendEmptyMessage(1);
                    }
                }
                query = null;
            }
            if (bVar != null) {
                try {
                    a(aVar, bVar);
                } catch (Exception e2) {
                    e = e2;
                }
            }
            if (query != null && !query.isClosed()) {
                query.close();
                return;
            }
            return;
        } catch (Exception e3) {
            e = e3;
            query = null;
        } catch (Throwable th) {
            Throwable th2 = th;
            if (!(cursor == null || cursor.isClosed())) {
                cursor.close();
            }
            throw th2;
        }
        try {
            q.d("CallLogAdapter", "queryContact(): " + e);
            if (query != null && !query.isClosed()) {
                query.close();
            }
        } catch (Throwable th3) {
            th2 = th3;
            cursor = query;
            cursor.close();
            throw th2;
        }
    }

    private void a(a aVar, b bVar) {
        if (!TextUtils.equals(aVar.b, bVar.b) || !TextUtils.equals(aVar.e, bVar.e) || aVar.d != bVar.d) {
            ContentValues contentValues = new ContentValues(3);
            contentValues.put("name", bVar.b);
            contentValues.put("numbertype", Integer.valueOf(bVar.d));
            contentValues.put("numberlabel", bVar.e);
            try {
                this.d.getContentResolver().update(Calls.CONTENT_URI, contentValues, "number='" + aVar.c + "'", null);
            } catch (Throwable e) {
                q.b("CallLogAdapter", "updateCallLog(): ", e);
            }
        }
    }
}
