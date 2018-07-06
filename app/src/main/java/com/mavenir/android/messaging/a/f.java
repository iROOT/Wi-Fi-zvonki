package com.mavenir.android.messaging.a;

import android.content.Context;
import android.database.Cursor;
import android.graphics.PorterDuff.Mode;
import android.support.v4.widget.b;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.fgmicrotec.mobile.android.fgvoip.FgVoIP;
import com.fgmicrotec.mobile.android.fgvoip.f.e;
import com.fgmicrotec.mobile.android.fgvoip.f.h;
import com.fgmicrotec.mobile.android.fgvoip.f.k;
import com.mavenir.android.common.BubbleDrawable;
import com.mavenir.android.common.n;
import com.mavenir.android.common.t;
import com.mavenir.android.messaging.c.d;
import com.mavenir.android.messaging.c.d.a;

public class f extends b {
    protected LayoutInflater j;
    private a k;
    private Context l;
    private String m = null;
    private boolean n = false;
    private d o = null;

    public f(Context context, Cursor cursor, String str) {
        super(context, cursor, false);
        this.j = (LayoutInflater) context.getSystemService("layout_inflater");
        this.k = new a();
        this.l = context;
        this.m = str;
    }

    public void a(boolean z) {
        this.n = z;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        Cursor cursor = this.c;
        if (cursor.moveToPosition(i)) {
            if (view == null) {
                view = a(this.l, cursor, viewGroup);
            }
            a(view, this.l, cursor);
            return view;
        }
        throw new IllegalStateException("couldn't move cursor to position " + i);
    }

    public void a(View view, Context context, Cursor cursor) {
        g gVar = (g) view.getTag();
        d dVar = new d(context, cursor);
        dVar.c(e(cursor));
        d(cursor);
        if (a(dVar, cursor)) {
            gVar.a.setVisibility(8);
        } else if (FgVoIP.U().ai() && b(dVar, cursor)) {
            gVar.a.setVisibility(8);
        } else if (FgVoIP.U().ai() && c(dVar, cursor)) {
            gVar.a.setVisibility(8);
        } else {
            gVar.a.setVisibility(0);
            a(gVar, dVar, cursor);
            a(gVar, dVar);
            b(gVar, dVar);
            c(gVar, dVar);
            d(gVar, dVar);
        }
    }

    private void d(Cursor cursor) {
        int position = cursor.getPosition();
        try {
            if (cursor.moveToPrevious()) {
                this.o = new d(this.l, cursor);
                this.o.c(e(cursor));
            } else {
                this.o = null;
            }
            cursor.moveToPosition(position);
        } catch (Throwable th) {
            cursor.moveToPosition(position);
        }
    }

    private void a(g gVar, d dVar, Cursor cursor) {
        CharSequence charSequence = DateFormat.format("dd/MM/yyyy", dVar.h()).toString();
        String str = null;
        if (this.o != null) {
            str = DateFormat.format("dd/MM/yyyy", this.o.h()).toString();
        }
        if (str == null || !str.equals(charSequence)) {
            gVar.b.setVisibility(0);
        } else {
            gVar.b.setVisibility(8);
        }
        if (t.d.e(dVar.h())) {
            charSequence = this.l.getString(k.call_log_date_today);
        } else if (t.d.f(dVar.h())) {
            charSequence = this.l.getString(k.call_log_date_yesterday);
        }
        gVar.d.setText(charSequence);
    }

    private void a(g gVar, d dVar) {
        CharSequence spannableString = new SpannableString("");
        if (dVar.g() != null) {
            spannableString = n.a(dVar.g(), this.l);
        }
        if (!(this.m == null || dVar.g() == null)) {
            int indexOf = dVar.g().indexOf(this.m);
            if (indexOf > -1) {
                spannableString.setSpan(new ForegroundColorSpan(this.l.getResources().getColor(e.h3g_yellow)), indexOf, this.m.length() + indexOf, 17);
            }
        }
        if (dVar.q()) {
            spannableString = new SpannableString(this.l.getString(k.message_mms_unsupported));
        } else if (TextUtils.isEmpty(dVar.g())) {
            spannableString = new SpannableString(this.l.getString(k.message_no_message));
        }
        BubbleDrawable bubbleDrawable;
        if (dVar.e()) {
            gVar.i.setText(spannableString);
            bubbleDrawable = (BubbleDrawable) gVar.h.getBackground().getCurrent();
            bubbleDrawable.setColorFilter(-16776961, Mode.SRC_ATOP);
            gVar.h.setBackgroundDrawable(bubbleDrawable);
        } else {
            gVar.i.setText(spannableString);
            bubbleDrawable = (BubbleDrawable) gVar.h.getBackground().getCurrent();
            bubbleDrawable.setColorFilter(-7829368, Mode.SRC_ATOP);
            gVar.h.setBackgroundDrawable(bubbleDrawable);
        }
        gVar.i.setBackgroundColor(0);
    }

    private void b(g gVar, d dVar) {
        Object charSequence = DateFormat.format("kk:mm", dVar.h()).toString();
        gVar.g.setText(charSequence);
        gVar.g.setContentDescription(t.a.c(charSequence));
    }

    private void c(g gVar, d dVar) {
        if (dVar.s() && gVar.e != null) {
            gVar.e.setVisibility(0);
            if (dVar.u()) {
                gVar.e.setVisibility(4);
            } else if (dVar.v()) {
                gVar.e.setImageResource(com.fgmicrotec.mobile.android.fgvoip.f.f.ic_message_failure);
                gVar.e.setContentDescription(this.l.getString(k.cd_message_status_failed));
            } else if (dVar.o() == 0) {
                gVar.e.setImageResource(com.fgmicrotec.mobile.android.fgvoip.f.f.ic_message_delivered);
                gVar.e.setContentDescription(this.l.getString(k.cd_message_status_delivered));
            } else {
                gVar.e.setImageResource(com.fgmicrotec.mobile.android.fgvoip.f.f.ic_message_success);
                gVar.e.setContentDescription(this.l.getString(k.cd_message_status_sent));
            }
        }
    }

    private void d(g gVar, d dVar) {
        if (dVar.n()) {
            gVar.f.setVisibility(0);
        } else {
            gVar.f.setVisibility(8);
        }
    }

    public Cursor b(Cursor cursor) {
        this.c = cursor;
        return super.b(cursor);
    }

    public void a(Cursor cursor) {
        this.c = cursor;
        super.a(cursor);
    }

    public int getViewTypeCount() {
        return 4;
    }

    public int getItemViewType(int i) {
        return e((Cursor) getItem(i));
    }

    private int e(Cursor cursor) {
        int i;
        if ("sms".equals(cursor.getString(this.k.a))) {
            i = cursor.getInt(this.k.i);
            if (i == 1 || i == 0) {
                return 0;
            }
            return 1;
        }
        i = cursor.getInt(this.k.s);
        return (i == 1 || i == 0) ? 2 : 3;
    }

    public View a(Context context, Cursor cursor, ViewGroup viewGroup) {
        int e = e(cursor);
        LayoutInflater layoutInflater = this.j;
        int i = (e == 0 || e == 2) ? h.message_received_layout : h.message_sent_layout;
        View inflate = layoutInflater.inflate(i, viewGroup, false);
        if (e == 2 || e == 3) {
            inflate.setTag(new g(inflate));
        } else {
            inflate.setTag(new g(inflate));
        }
        return inflate;
    }

    private boolean a(d dVar, Cursor cursor) {
        if (!this.n) {
            return false;
        }
        if (this.o != null && a(this.o, dVar)) {
            return true;
        }
        if (dVar.v()) {
            return false;
        }
        int position = cursor.getPosition();
        d dVar2 = dVar;
        while (cursor.moveToNext()) {
            d dVar3 = new d(this.l, cursor);
            dVar3.c(e(cursor));
            if (!a(dVar2, dVar3)) {
                break;
            }
            try {
                if (dVar3.v()) {
                    dVar.w();
                    break;
                }
                dVar2 = dVar3;
            } catch (Throwable th) {
                cursor.moveToPosition(position);
            }
        }
        cursor.moveToPosition(position);
        return false;
    }

    private boolean a(d dVar, d dVar2) {
        if (dVar.z() != -1 && dVar.z() == dVar2.z() && Math.abs(dVar.h() - dVar2.h()) < 10000) {
            String g = dVar.g();
            String g2 = dVar2.g();
            if ((g == null && g2 == null) || (g != null && g.equals(g2))) {
                return true;
            }
        }
        return false;
    }

    private boolean b(d dVar, Cursor cursor) {
        boolean z = false;
        if (!this.n) {
            int position = cursor.getPosition();
            d dVar2;
            do {
                if (!cursor.moveToNext()) {
                    break;
                }
                dVar2 = new d(this.l, cursor);
                dVar2.c(e(cursor));
                if (Math.abs(dVar.h() - dVar2.h()) > 3600000) {
                    break;
                }
                try {
                } catch (Throwable th) {
                    cursor.moveToPosition(position);
                }
            } while (!b(dVar, dVar2));
            z = true;
            cursor.moveToPosition(position);
        }
        return z;
    }

    private boolean b(d dVar, d dVar2) {
        if (dVar.z() == 0 && dVar.z() == dVar2.z() && Math.signum((float) dVar.c()) != Math.signum((float) dVar2.c())) {
            String g = dVar.g();
            String g2 = dVar2.g();
            if ((g == null && g2 == null) || (g != null && g.equals(g2))) {
                return true;
            }
        }
        return false;
    }

    private boolean c(d dVar, Cursor cursor) {
        boolean z = false;
        if (!this.n) {
            int position = cursor.getPosition();
            try {
                if (cursor.moveToNext()) {
                    d dVar2 = new d(this.l, cursor);
                    dVar2.c(e(cursor));
                    if (Math.abs(dVar.h() - dVar2.h()) < 60000 && c(dVar, dVar2)) {
                        z = true;
                    }
                }
                cursor.moveToPosition(position);
            } catch (Throwable th) {
                cursor.moveToPosition(position);
            }
        }
        return z;
    }

    private boolean c(d dVar, d dVar2) {
        if (dVar.z() == 1 && dVar.z() == dVar2.z() && Math.signum((float) dVar.c()) != Math.signum((float) dVar2.c())) {
            String g = dVar.g();
            String g2 = dVar2.g();
            if (g == null && g2 == null) {
                return true;
            }
            if (g != null && g.equals(g2)) {
                return true;
            }
        }
        return false;
    }
}
