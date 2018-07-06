package com.mavenir.android.messaging.a;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.fgmicrotec.mobile.android.fgvoip.f.b;
import com.fgmicrotec.mobile.android.fgvoip.f.g;
import com.fgmicrotec.mobile.android.fgvoip.f.h;

public class d extends BaseAdapter {
    private Context a;
    private TypedArray b = this.a.getResources().obtainTypedArray(b.emo_image_ids);
    private TypedArray c = this.a.getResources().obtainTypedArray(b.emo_names);
    private TypedArray d = this.a.getResources().obtainTypedArray(b.emo_shortcuts);

    public d(Context context) {
        this.a = context;
    }

    public int getCount() {
        return this.b.length();
    }

    public Object getItem(int i) {
        return Integer.valueOf(i);
    }

    public long getItemId(int i) {
        return (long) this.b.getResourceId(i, -1);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = ((LayoutInflater) this.a.getSystemService("layout_inflater")).inflate(h.emoticon_list_item, viewGroup, false);
        }
        c cVar = new c();
        cVar.a = (ImageView) view.findViewById(g.emoticonIconTmageView);
        cVar.b = (TextView) view.findViewById(g.emoticonNameTextView);
        cVar.c = (TextView) view.findViewById(g.emoticonShortcutTextView);
        if (cVar.a != null) {
            cVar.a.setImageResource(this.b.getResourceId(i, -1));
        }
        if (cVar.b != null) {
            cVar.b.setText(this.c.getResourceId(i, -1));
        }
        if (cVar.c != null) {
            cVar.c.setText(this.d.getText(i));
        }
        return view;
    }
}
