package com.mavenir.android.a;

import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.wifi.WifiConfiguration;
import android.support.v4.widget.j;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import com.fgmicrotec.mobile.android.fgvoip.f.g;
import com.fgmicrotec.mobile.android.fgvoip.f.h;
import com.mavenir.android.common.aa;
import com.mavenir.android.common.l;
import com.mavenir.android.settings.c;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class i extends j implements OnClickListener {
    public static String[] j = new String[]{"_id", "ssid", "bssid", "ignore"};
    private MatrixCursor k;
    private boolean l = false;

    public i(Context context) {
        super(context, h.whitelist_list_item, null, false);
        this.d = context;
    }

    public void a(View view, Context context, Cursor cursor) {
        boolean z = false;
        k kVar = (k) view.getTag();
        CharSequence a = a(cursor, false);
        kVar.b.setText(a);
        if (l.a(this.d).P().replace("\"", "").equals(a)) {
            kVar.a.setVisibility(0);
        } else {
            kVar.a.setVisibility(4);
        }
        Boolean valueOf = Boolean.valueOf(f(cursor));
        CheckBox checkBox = kVar.c;
        if (!valueOf.booleanValue()) {
            z = true;
        }
        checkBox.setChecked(z);
        kVar.c.setTag(Integer.valueOf(cursor.getPosition()));
    }

    public View a(Context context, Cursor cursor, ViewGroup viewGroup) {
        View a = super.a(context, cursor, viewGroup);
        k kVar = new k();
        kVar.a = (ImageView) a.findViewById(g.connectedIcon);
        kVar.b = (TextView) a.findViewById(g.ssidTextView);
        kVar.c = (CheckBox) a.findViewById(g.allowedCheckBox);
        if (kVar.c != null) {
            kVar.c.setOnClickListener(this);
        }
        a.setTag(kVar);
        return a;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        Cursor cursor = this.k;
        if (cursor.moveToPosition(i)) {
            if (view == null) {
                view = a(this.d, cursor, viewGroup);
            }
            a(view, this.d, cursor);
            return view;
        }
        throw new IllegalStateException("couldn't move cursor to position " + i);
    }

    public void onClick(View view) {
        if (view instanceof CheckBox) {
            CheckBox checkBox = (CheckBox) view;
            this.k.moveToPosition(((Integer) checkBox.getTag()).intValue());
            if (!this.k.isBeforeFirst() && !this.k.isAfterLast()) {
                aa.a(this.d).a(this.k.getLong(this.k.getColumnIndex("_id")), !checkBox.isChecked());
                this.l = true;
            }
        }
    }

    public Cursor b(Cursor cursor) {
        this.k = d(cursor);
        return super.b(this.k);
    }

    private MatrixCursor d(Cursor cursor) {
        MatrixCursor matrixCursor = new MatrixCursor(new String[]{"_id", "ssid", "bssid", "ignore"});
        if (cursor != null) {
            cursor.moveToPosition(-1);
            int a = a(matrixCursor, cursor);
            List<WifiConfiguration> f = aa.a(this.d).f();
            Map hashMap = new HashMap();
            if (f != null) {
                for (WifiConfiguration wifiConfiguration : f) {
                    hashMap.put(wifiConfiguration.SSID != null ? wifiConfiguration.SSID.replace("\"", "") : "", wifiConfiguration);
                }
            }
            while (cursor.moveToNext()) {
                if (cursor.getPosition() != a) {
                    long e = e(cursor);
                    String a2 = a(cursor, true);
                    String b = b(cursor, true);
                    String f2 = f(cursor);
                    if (!l.a(this.d).C() || hashMap.containsKey(a2)) {
                        matrixCursor.newRow().add(Long.valueOf(e)).add(a2).add(b).add(f2);
                    } else {
                        aa.a(this.d).a(e);
                    }
                }
            }
            matrixCursor.moveToPosition(-1);
            cursor.moveToPosition(-1);
        }
        return matrixCursor;
    }

    private long e(Cursor cursor) {
        return cursor.getLong(cursor.getColumnIndex("_id"));
    }

    private String a(Cursor cursor, boolean z) {
        String string = cursor.getString(cursor.getColumnIndex("ssid"));
        return z ? c.g.a(true, "ssid", string) : string;
    }

    private String b(Cursor cursor, boolean z) {
        String string = cursor.getString(cursor.getColumnIndex("bssid"));
        return z ? c.g.a(true, "bssid", string) : string;
    }

    private String f(Cursor cursor) {
        return cursor.getString(cursor.getColumnIndex("ignore"));
    }

    private int a(MatrixCursor matrixCursor, Cursor cursor) {
        String replace = l.a(this.d).P().replace("\"", "");
        String Q = l.a(this.d).Q();
        if (cursor == null) {
            return -1;
        }
        int position;
        cursor.moveToPosition(-1);
        while (cursor.moveToNext()) {
            String a = a(cursor, true);
            String b = b(cursor, true);
            if ((a != null && a.equals(replace)) || (b != null && b.equals(Q))) {
                matrixCursor.newRow().add(Long.valueOf(e(cursor))).add(a(cursor, true)).add(b(cursor, true)).add(f(cursor));
                position = cursor.getPosition();
                break;
            }
        }
        position = -1;
        cursor.moveToPosition(-1);
        return position;
    }

    public long a(int i) {
        int position = a().getPosition();
        a().moveToPosition(i);
        long j = this.k.getLong(a().getColumnIndex("_id"));
        a().moveToPosition(position);
        return j;
    }

    public boolean d() {
        return this.l;
    }

    public void a(boolean z) {
        this.l = z;
    }
}
