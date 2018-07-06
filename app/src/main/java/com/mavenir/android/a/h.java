package com.mavenir.android.a;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.j;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.fgmicrotec.mobile.android.fgvoip.f.e;
import com.fgmicrotec.mobile.android.fgvoip.f.g;
import com.mavenir.android.common.t.d;
import com.mavenir.android.settings.c.q;

public class h extends j {
    public static String[] j = new String[]{"_id", "wifi_signal_strength", "jitter", "round_trip_delay", "cumulative_loss", "fraction_loss", "timestamp"};
    private boolean k = false;
    private int l;
    private int m;
    private int n;
    private int o;
    private int p;

    public h(Context context) {
        super(context, com.fgmicrotec.mobile.android.fgvoip.f.h.qos_test_ui_list_item, null, false);
        this.d = context;
        this.l = q.d();
        this.m = q.j();
        this.n = q.i();
        this.o = q.k();
        this.p = q.l();
    }

    public void a(View view, Context context, Cursor cursor) {
        j jVar = (j) view.getTag();
        jVar.a.setText(a(String.valueOf(cursor.getString(cursor.getColumnIndex("wifi_signal_strength"))), this.l, true));
        jVar.c.setText(a(String.valueOf(cursor.getString(cursor.getColumnIndex("round_trip_delay"))), this.m, false));
        jVar.b.setText(a(String.valueOf(cursor.getString(cursor.getColumnIndex("jitter"))), this.n, false));
        jVar.d.setText(a(String.valueOf(cursor.getString(cursor.getColumnIndex("cumulative_loss"))), this.o, false));
        jVar.e.setText(a(String.valueOf(cursor.getString(cursor.getColumnIndex("fraction_loss"))), this.p, false));
        String string = cursor.getString(cursor.getColumnIndex("timestamp"));
        jVar.f.setText(string + " (" + d.d(Long.valueOf(string).longValue()) + ")");
        a(string, jVar);
    }

    private void a(String str, j jVar) {
        if (str.equals("0")) {
            jVar.a.setVisibility(this.k ? 8 : 0);
            jVar.c.setVisibility(0);
            jVar.b.setVisibility(0);
            jVar.d.setVisibility(0);
            jVar.e.setVisibility(0);
            jVar.f.setVisibility(8);
            return;
        }
        jVar.a.setVisibility(8);
        jVar.c.setVisibility(8);
        jVar.b.setVisibility(8);
        jVar.d.setVisibility(8);
        jVar.e.setVisibility(8);
        jVar.f.setVisibility(0);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        Cursor cursor = this.c;
        if (cursor.moveToPosition(i)) {
            if (view == null) {
                view = a(this.d, cursor, viewGroup);
            }
            a(view, this.d, cursor);
            return view;
        }
        throw new IllegalStateException("couldn't move cursor to position " + i);
    }

    public View a(Context context, Cursor cursor, ViewGroup viewGroup) {
        View a = super.a(context, cursor, viewGroup);
        j jVar = new j();
        jVar.a = (TextView) a.findViewById(g.wifiTextView);
        jVar.b = (TextView) a.findViewById(g.jitterTextView);
        jVar.c = (TextView) a.findViewById(g.rtdTextView);
        jVar.d = (TextView) a.findViewById(g.clTextView);
        jVar.e = (TextView) a.findViewById(g.flTextView);
        jVar.f = (TextView) a.findViewById(g.timestampTextView);
        a.setTag(jVar);
        return a;
    }

    private SpannableStringBuilder a(String str, int i, boolean z) {
        Object foregroundColorSpan;
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(str);
        if ((!z || Integer.valueOf(str).intValue() <= i) && (z || Integer.valueOf(str).intValue() >= i)) {
            foregroundColorSpan = new ForegroundColorSpan(this.d.getResources().getColor(e.qos_text_warning));
        } else {
            foregroundColorSpan = new ForegroundColorSpan(this.d.getResources().getColor(e.qos_text_normal));
        }
        spannableStringBuilder.setSpan(foregroundColorSpan, 0, str.length(), 18);
        return spannableStringBuilder;
    }

    public boolean d(Cursor cursor) {
        if (cursor != null) {
            int position = cursor.getPosition();
            if (cursor.moveToFirst() && cursor.getString(cursor.getColumnIndex("wifi_signal_strength")).equals("-99999")) {
                this.k = true;
                return true;
            }
            cursor.moveToPosition(position);
        }
        return false;
    }
}
