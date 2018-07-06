package com.mavenir.android.messaging.a;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap.Config;
import android.net.Uri;
import android.provider.ContactsContract.Contacts;
import android.support.v4.widget.j;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.b.a.b.c;
import com.b.a.b.c.a;
import com.b.a.b.c.b;
import com.b.a.b.d;
import com.fgmicrotec.mobile.android.fgvoip.f.g;
import com.fgmicrotec.mobile.android.fgvoip.f.h;
import com.fgmicrotec.mobile.android.fgvoip.f.k;
import com.mavenir.android.common.t;
import com.mavenir.android.common.t.f;

public class e extends j {
    private c j = new a().a(Config.RGB_565).a(new b(100)).a();
    private String k = null;

    public e(Context context, Cursor cursor) {
        super(context, h.message_suggestion_list_item, cursor, false);
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
        com.mavenir.android.a.e eVar = new com.mavenir.android.a.e();
        eVar.d = (TextView) a.findViewById(g.avatar_initials);
        eVar.b = (ImageView) a.findViewById(g.avatar_image);
        eVar.e = (TextView) a.findViewById(g.list_item_title);
        eVar.f = (TextView) a.findViewById(g.list_item_number);
        a.setTag(eVar);
        return a;
    }

    public void a(View view, Context context, Cursor cursor) {
        int indexOf;
        com.mavenir.android.a.e eVar = (com.mavenir.android.a.e) view.getTag();
        Object string = cursor.getString(cursor.getColumnIndex("display_name"));
        CharSequence spannableString = new SpannableString(string);
        if (string != null) {
            if (!TextUtils.isEmpty(this.k)) {
                indexOf = string.toLowerCase().indexOf(this.k.toLowerCase());
                if (indexOf > -1) {
                    spannableString.setSpan(new ForegroundColorSpan(this.d.getResources().getColor(com.fgmicrotec.mobile.android.fgvoip.f.e.app_main)), indexOf, this.k.length() + indexOf, 17);
                }
            }
            eVar.e.setText(spannableString);
        } else {
            eVar.e.setText("Unknown");
        }
        String i = f.i(cursor.getString(cursor.getColumnIndex("data1")));
        spannableString = new SpannableString(i);
        if (i != null) {
            if (!TextUtils.isEmpty(this.k)) {
                int indexOf2 = i.indexOf(this.k);
                if (indexOf2 > -1) {
                    spannableString.setSpan(new ForegroundColorSpan(this.d.getResources().getColor(com.fgmicrotec.mobile.android.fgvoip.f.e.app_main)), indexOf2, this.k.length() + indexOf2, 17);
                }
                String d = f.d(this.k);
                indexOf = i.indexOf(d);
                if (indexOf > -1) {
                    spannableString.setSpan(new ForegroundColorSpan(this.d.getResources().getColor(com.fgmicrotec.mobile.android.fgvoip.f.e.app_main)), indexOf, d.length() + indexOf, 17);
                }
            }
            eVar.f.setText(spannableString);
        } else {
            eVar.f.setText("Unknown");
        }
        long j = -1;
        if (!cursor.isNull(cursor.getColumnIndex("photo_id"))) {
            j = cursor.getLong(cursor.getColumnIndex("photo_id"));
        }
        if (j != -1) {
            d.a().a(Uri.withAppendedPath(ContentUris.withAppendedId(Contacts.CONTENT_URI, cursor.getLong(cursor.getColumnIndex("contact_id"))), "photo").toString(), eVar.b, this.j);
        } else {
            d.a().a("", eVar.b, this.j);
        }
        if (j == -1) {
            CharSequence charSequence = "";
            for (String str : eVar.e.getText().toString().split(" ")) {
                if (str != null && str.length() > 0) {
                    charSequence = charSequence + str.substring(0, 1).toUpperCase();
                }
            }
            eVar.d.setText(charSequence);
        } else {
            eVar.d.setText(k.empty_string);
        }
        view.setContentDescription(this.d.getString(k.cd_message_recipient_item, new Object[]{eVar.e.getText().toString(), t.a.b(eVar.f.getText().toString())}));
    }

    public Cursor a(CharSequence charSequence) {
        if (b() == null) {
            return null;
        }
        this.k = String.valueOf(charSequence);
        return b().runQuery(charSequence);
    }

    public CharSequence c(Cursor cursor) {
        if (cursor == null) {
            return super.c(cursor);
        }
        String string = cursor.getString(cursor.getColumnIndex("display_name"));
        return string + " <" + f.i(cursor.getString(cursor.getColumnIndex("data1"))) + ">";
    }
}
