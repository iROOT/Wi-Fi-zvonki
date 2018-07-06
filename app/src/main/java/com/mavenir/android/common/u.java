package com.mavenir.android.common;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnDismissListener;
import android.database.Cursor;
import android.provider.ContactsContract.Data;
import android.telephony.PhoneNumberUtils;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import com.fgmicrotec.mobile.android.fgvoip.f.k;
import java.util.ArrayList;
import java.util.List;

public class u implements OnClickListener, OnDismissListener {
    private boolean a = false;
    private Context b;
    private AlertDialog c;
    private boolean d;
    private Cursor e;
    private ListAdapter f;
    private ArrayList<b> g;
    private String h = "";
    private a i;

    public interface a {
        void a(String str, boolean z);
    }

    private class b implements com.mavenir.android.common.i.a<b> {
        String a;
        long b;
        final /* synthetic */ u c;

        public b(u uVar, String str, long j) {
            this.c = uVar;
            this.a = str;
            this.b = j;
        }

        public boolean a(b bVar) {
            if (b(bVar)) {
                return true;
            }
            return false;
        }

        public boolean b(b bVar) {
            if (PhoneNumberUtils.compare(this.c.b, this.a, bVar.a)) {
                return true;
            }
            return false;
        }

        public String toString() {
            return this.a;
        }
    }

    private static class c extends ArrayAdapter<b> {
        public c(Context context, List<b> list) {
            super(context, 17367043, 16908308, list);
        }
    }

    public u(Context context, a aVar, Cursor cursor, boolean z) {
        this.b = context;
        this.d = z;
        this.e = cursor;
        this.i = aVar;
        this.g = a(cursor);
        i.a(this.g);
        this.f = new c(this.b, this.g);
        this.c = new Builder(this.b).setAdapter(this.f, this).setTitle(z ? k.sms_disambig_title : k.call_disambig_title).create();
    }

    public void a() {
        if (this.g.size() == 1) {
            onClick(this.c, 0);
        } else if (!((Activity) this.b).isFinishing() && !((Activity) this.b).isDestroyed()) {
            this.c.show();
        }
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        if (this.g.size() <= i || i < 0) {
            dialogInterface.dismiss();
            return;
        }
        b bVar = (b) this.g.get(i);
        long j = bVar.b;
        String str = bVar.a;
        if (this.a) {
            ContentValues contentValues = new ContentValues(1);
            contentValues.put("is_super_primary", Integer.valueOf(1));
            this.b.getContentResolver().update(ContentUris.withAppendedId(Data.CONTENT_URI, j), contentValues, null, null);
        }
        this.h = str;
        this.i.a(str, this.d);
    }

    public void onDismiss(DialogInterface dialogInterface) {
        this.e.close();
    }

    private ArrayList<b> a(Cursor cursor) {
        ArrayList<b> arrayList = new ArrayList();
        cursor.moveToPosition(-1);
        while (cursor.moveToNext()) {
            arrayList.add(new b(this, cursor.getString(cursor.getColumnIndex("data1")), cursor.getLong(cursor.getColumnIndex("_id"))));
        }
        return arrayList;
    }
}
