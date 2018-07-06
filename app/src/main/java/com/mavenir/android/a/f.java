package com.mavenir.android.a;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap.Config;
import android.net.Uri;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Contacts;
import android.support.v4.widget.j;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AlphabetIndexer;
import android.widget.ImageView;
import android.widget.SectionIndexer;
import android.widget.TextView;
import com.b.a.b.c;
import com.b.a.b.c.a;
import com.b.a.b.c.b;
import com.b.a.b.d;
import com.fgmicrotec.mobile.android.fgvoip.FgVoIP;
import com.fgmicrotec.mobile.android.fgvoip.f.g;
import com.fgmicrotec.mobile.android.fgvoip.f.h;
import com.fgmicrotec.mobile.android.fgvoip.f.k;
import com.mavenir.android.common.p;
import com.mavenir.android.common.q;
import com.mavenir.android.common.t;
import com.mavenir.android.fragments.e;
import java.util.Locale;

public final class f extends j implements SectionIndexer {
    private Context j;
    private e k;
    private Cursor l;
    private SectionIndexer m;
    private String n;
    private boolean o;
    private int[] p;
    private OnClickListener q;
    private boolean r;
    private c s;
    private String t;

    public f(Context context, OnClickListener onClickListener, boolean z) {
        super(context, h.contact_list_item, null, false);
        this.k = null;
        this.o = true;
        this.t = null;
        this.k = null;
        this.j = context;
        this.q = onClickListener;
        this.r = z;
        this.n = this.j.getString(k.fast_scroll_alphabet);
        this.s = new a().a(Config.RGB_565).b(true).a(new b(100)).a();
    }

    public f(e eVar, OnClickListener onClickListener) {
        super(eVar.getActivity(), h.contact_list_item, null, false);
        this.k = null;
        this.o = true;
        this.t = null;
        this.k = eVar;
        this.j = eVar.getActivity();
        this.q = onClickListener;
        this.r = false;
        this.n = this.j.getString(k.fast_scroll_alphabet);
        this.s = new a().a(Config.RGB_565).c(true).a(true).a(new b(100)).a();
    }

    public void a(Cursor cursor) {
        super.a(cursor);
        this.l = cursor;
        d(cursor);
    }

    public Cursor b(Cursor cursor) {
        this.l = cursor;
        d(cursor);
        return super.b(cursor);
    }

    public Object[] getSections() {
        return this.m.getSections();
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        Cursor cursor = this.l;
        if (cursor.moveToPosition(i)) {
            if (view == null) {
                view = a(this.j, cursor, viewGroup);
            }
            a(view, this.j, cursor);
            a(view, i, this.o);
            return view;
        }
        throw new IllegalStateException("couldn't move cursor to position " + i);
    }

    public View a(Context context, Cursor cursor, ViewGroup viewGroup) {
        View a = super.a(context, cursor, viewGroup);
        e eVar = new e();
        eVar.a = (TextView) a.findViewById(g.header_text);
        eVar.b = (ImageView) a.findViewById(g.avatar_image);
        eVar.c = (ImageView) a.findViewById(g.social_presence_image);
        eVar.d = (TextView) a.findViewById(g.avatar_initials);
        eVar.e = (TextView) a.findViewById(g.list_item_title);
        eVar.f = (TextView) a.findViewById(g.list_item_number);
        eVar.g = (ImageView) a.findViewById(g.call_button);
        eVar.h = (ImageView) a.findViewById(g.sms_button);
        eVar.i = (ImageView) a.findViewById(g.availability_icon);
        eVar.j = (ImageView) a.findViewById(g.rcs_icon);
        if (eVar.f != null) {
            eVar.f.setVisibility(8);
        }
        if (eVar.g != null) {
            eVar.g.setOnClickListener(this.q);
        }
        if (eVar.h != null) {
            if (FgVoIP.U().ad() == FgVoIP.a.VOIP || !com.mavenir.android.settings.c.k.u()) {
                eVar.h.setVisibility(8);
            } else {
                eVar.h.setOnClickListener(this.q);
            }
        }
        a.setTag(eVar);
        return a;
    }

    public void a(View view, Context context, Cursor cursor) {
        e eVar = (e) view.getTag();
        String a = a(cursor, eVar);
        String b = b(cursor, eVar);
        c(cursor, eVar);
        int position = cursor.getPosition();
        eVar.g.setTag(Integer.valueOf(position));
        eVar.g.setContentDescription(this.j.getString(k.cd_contacts_call_button, new Object[]{a}));
        eVar.h.setTag(Integer.valueOf(position));
        eVar.h.setContentDescription(this.j.getString(k.cd_contacts_sms_button, new Object[]{a}));
        String str = "";
        view.setContentDescription(this.j.getString(k.cd_contacts_item, new Object[]{a + " " + b}));
        if (this.k != null) {
            this.k.a(view, cursor);
        }
    }

    public void a(String str) {
        this.t = str;
    }

    private String a(Cursor cursor, e eVar) {
        int columnIndex;
        if (com.mavenir.android.settings.c.k.y() == 0) {
            columnIndex = cursor.getColumnIndex("display_name");
        } else {
            columnIndex = cursor.getColumnIndex("display_name_alt");
        }
        CharSequence string = cursor.getString(columnIndex);
        if (string != null) {
            eVar.e.setText(string);
        } else {
            eVar.e.setText("Unknown");
        }
        return eVar.e.getText().toString();
    }

    private String b(Cursor cursor, e eVar) {
        if (this.r) {
            String string = cursor.getString(cursor.getColumnIndex("data1"));
            if (string != null) {
                eVar.f.setVisibility(0);
                Object i = com.mavenir.android.common.t.f.i(string);
                CharSequence spannableString = new SpannableString(i);
                if (this.t != null) {
                    string = this.t;
                    if (this.t.startsWith("00")) {
                        string = this.t.substring(2);
                    } else if (this.t.startsWith("0")) {
                        string = this.t.substring(1);
                    }
                    int indexOf = i.indexOf(string);
                    if (indexOf > -1) {
                        spannableString.setSpan(new ForegroundColorSpan(this.j.getResources().getColor(com.fgmicrotec.mobile.android.fgvoip.f.e.app_main)), indexOf, string.length() + indexOf, 17);
                    }
                }
                eVar.f.setText(spannableString);
            } else {
                eVar.f.setVisibility(8);
            }
        }
        return "";
    }

    private void c(Cursor cursor, e eVar) {
        long j;
        if (cursor.isNull(cursor.getColumnIndex("photo_id"))) {
            j = -1;
        } else {
            j = cursor.getLong(cursor.getColumnIndex("photo_id"));
        }
        eVar.b.setImageBitmap(null);
        if (j != -1) {
            Uri withAppendedId;
            if (this.r) {
                withAppendedId = ContentUris.withAppendedId(Phone.CONTENT_URI, cursor.getLong(cursor.getColumnIndex("contact_id")));
            } else {
                withAppendedId = ContentUris.withAppendedId(Contacts.CONTENT_URI, cursor.getLong(cursor.getColumnIndex("_id")));
            }
            d.a().a(withAppendedId.toString(), eVar.b, this.s);
        } else {
            d.a().a(eVar.b);
        }
        if (j == -1) {
            eVar.d.setText(t.c.a(eVar.e.getText()));
            return;
        }
        eVar.d.setText(k.empty_string);
    }

    private void a(View view, int i, boolean z) {
        e eVar = (e) view.getTag();
        if (z) {
            int sectionForPosition = getSectionForPosition(i);
            if (getPositionForSection(sectionForPosition) == i) {
                CharSequence trim = this.m.getSections()[sectionForPosition].toString().trim();
                if (TextUtils.isEmpty(trim)) {
                    eVar.a.setVisibility(8);
                    return;
                }
                eVar.a.setText(trim);
                eVar.a.setVisibility(0);
                return;
            }
            eVar.a.setVisibility(8);
            return;
        }
        eVar.a.setVisibility(8);
    }

    public int getPositionForSection(int i) {
        if (i < 0 || i >= this.p.length) {
            return -1;
        }
        if (this.m == null) {
            Cursor cursor = this.l;
            if (cursor == null) {
                return 0;
            }
            this.m = e(cursor);
        }
        int i2 = this.p[i];
        if (i2 != -1) {
            return i2;
        }
        int[] iArr = this.p;
        i2 = this.m.getPositionForSection(i);
        iArr[i] = i2;
        return i2;
    }

    public int getSectionForPosition(int i) {
        int i2 = 0;
        int length = this.p.length;
        while (i2 != length) {
            int i3 = ((length - i2) / 4) + i2;
            if (getPositionForSection(i3) <= i) {
                i3++;
            } else {
                length = i3;
                i3 = i2;
            }
            i2 = i3;
        }
        return i2 - 1;
    }

    private void d(Cursor cursor) {
        if (cursor != null) {
            try {
                if (this.m == null) {
                    this.m = e(cursor);
                } else if (Locale.getDefault().equals(Locale.JAPAN)) {
                    if (this.m instanceof p) {
                        ((p) this.m).a(cursor);
                    } else {
                        this.m = e(cursor);
                    }
                } else if (this.m instanceof AlphabetIndexer) {
                    ((AlphabetIndexer) this.m).setCursor(cursor);
                } else {
                    this.m = e(cursor);
                }
                int length = this.m.getSections().length;
                if (this.p == null || this.p.length != length) {
                    this.p = new int[length];
                }
                for (int i = 0; i < length; i++) {
                    this.p[i] = -1;
                }
            } catch (Exception e) {
                q.c("ContactsListAdapter", e.getLocalizedMessage(), e.getCause());
            }
        }
    }

    private SectionIndexer e(Cursor cursor) {
        int columnIndex;
        if (com.mavenir.android.settings.c.k.z() == 0) {
            columnIndex = cursor.getColumnIndex("display_name");
        } else {
            columnIndex = cursor.getColumnIndex("display_name_alt");
        }
        if (Locale.getDefault().getLanguage().equals(Locale.JAPAN.getLanguage())) {
            return new p(cursor, columnIndex);
        }
        return new AlphabetIndexer(cursor, columnIndex, this.n);
    }
}
