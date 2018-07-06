package com.mavenir.android.a;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.j;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckedTextView;
import com.fgmicrotec.mobile.android.fgvoip.f.k;
import com.mavenir.android.settings.c;

public class g extends j {
    public g(Context context, Cursor cursor) {
        super(context, 17367055, cursor, 0);
    }

    public void a(View view, Context context, Cursor cursor) {
        CheckedTextView checkedTextView = (CheckedTextView) view.findViewById(16908308);
        CharSequence a = com.mavenir.android.settings.c.g.a(true, "profile_name", cursor.getString(cursor.getColumnIndex("profile_name")));
        if (TextUtils.isEmpty(a)) {
            a = context.getString(k.preference_account_profile_name_undefined);
        }
        checkedTextView.setText(a);
    }

    public long a(int i) {
        int position = a().getPosition();
        a().moveToPosition(i);
        long j = this.c.getLong(a().getColumnIndex("_id"));
        a().moveToPosition(position);
        return j;
    }

    public String b(int i) {
        Cursor a = a();
        if (a == null) {
            return "";
        }
        int position = a.getPosition();
        a.moveToPosition(i);
        String string = this.c.getString(a.getColumnIndex("profile_name"));
        a().moveToPosition(position);
        return com.mavenir.android.settings.c.g.a(true, "profile_name", string);
    }

    public int d() {
        int i = -1;
        long c = c.k.c();
        Cursor a = a();
        if (a != null) {
            int position = a.getPosition();
            a.moveToPosition(-1);
            while (a.moveToNext()) {
                if (c == this.c.getLong(a.getColumnIndex("_id"))) {
                    i = a.getPosition();
                    break;
                }
            }
            a().moveToPosition(position);
        }
        return i;
    }
}
