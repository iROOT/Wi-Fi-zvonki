package com.mavenir.android.a;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.j;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.fgmicrotec.mobile.android.fgvoip.FgVoIP;
import com.fgmicrotec.mobile.android.fgvoip.f.f;
import com.fgmicrotec.mobile.android.fgvoip.f.g;
import com.fgmicrotec.mobile.android.fgvoip.f.h;
import com.fgmicrotec.mobile.android.fgvoip.f.k;
import com.mavenir.android.common.q;
import com.mavenir.android.common.t.d;

public class a extends j {
    private OnClickListener j;

    public a(Context context, OnClickListener onClickListener) {
        super(context, h.call_details_list_item, null, false);
        this.d = context;
        this.j = onClickListener;
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

    public void a(View view, Context context, Cursor cursor) {
        Object string;
        b bVar = (b) view.getTag();
        long j = cursor.getLong(cursor.getColumnIndex("duration"));
        CharSequence d = d.d(j);
        CharSequence string2 = this.d.getString(k.cd_call_details_duration, new Object[]{com.mavenir.android.common.t.a.c(d)});
        int i = cursor.getInt(cursor.getColumnIndex("type"));
        if (i == 1) {
            bVar.a.setImageResource(f.ic_call_incoming);
            bVar.a.setContentDescription(this.d.getString(k.cd_call_log_icon_incoming));
            if (j == 0) {
                string2 = this.d.getString(k.call_details_canceled);
                d = string2;
            }
        } else if (i == 2) {
            bVar.a.setImageResource(f.ic_call_outgoing);
            bVar.a.setContentDescription(context.getString(k.cd_call_log_icon_outgoing));
            if (j == 0) {
                string2 = this.d.getString(k.call_details_canceled);
                d = string2;
            }
        } else if (i == 3) {
            bVar.a.setImageResource(f.ic_call_missed);
            bVar.a.setContentDescription(context.getString(k.cd_call_log_icon_missed));
            string2 = this.d.getString(k.call_details_missed);
            d = string2;
        } else {
            bVar.a.setImageResource(f.ic_call_outgoing);
            bVar.a.setContentDescription(context.getString(k.cd_call_log_icon_outgoing));
            if (j == 0) {
                string2 = this.d.getString(k.call_details_canceled);
                d = string2;
            }
        }
        bVar.c.setText(d);
        bVar.c.setContentDescription(string2);
        bVar.b.setText(d.c(cursor.getLong(cursor.getColumnIndex("date"))));
        bVar.b.setContentDescription(this.d.getString(k.cd_call_details_call_time, new Object[]{com.mavenir.android.common.t.a.c(string)}));
        if (FgVoIP.U().s()) {
            string = cursor.getString(cursor.getColumnIndex("voicemail_uri"));
            if (TextUtils.isEmpty(string)) {
                bVar.d.setVisibility(8);
                return;
            }
            if (string.contains("Whisper")) {
                bVar.d.setImageResource(f.ic_whisper_play);
            } else {
                bVar.d.setImageResource(f.ic_shout_play);
            }
            bVar.d.setVisibility(0);
            bVar.d.setTag(Integer.valueOf(cursor.getPosition()));
        }
    }

    public View a(Context context, Cursor cursor, ViewGroup viewGroup) {
        View a = super.a(context, cursor, viewGroup);
        b bVar = new b();
        bVar.a = (ImageView) a.findViewById(g.callTypeIcon);
        bVar.b = (TextView) a.findViewById(g.callDetailCallTime);
        bVar.c = (TextView) a.findViewById(g.callDetailDuration);
        if (FgVoIP.U().s()) {
            bVar.d = (ImageView) a.findViewById(g.play_button);
            bVar.d.setOnClickListener(this.j);
        }
        a.setTag(bVar);
        return a;
    }

    public String a(int i) {
        int position = this.c.getPosition();
        this.c.moveToPosition(i);
        String str = "";
        if (!(this.c.isBeforeFirst() || this.c.isAfterLast())) {
            try {
                str = this.c.getString(this.c.getColumnIndex("voicemail_uri"));
            } catch (Exception e) {
                q.d("CallDetailsAdapter", "getAudioFilePath(): " + e);
            }
        }
        this.c.moveToPosition(position);
        return str;
    }
}
