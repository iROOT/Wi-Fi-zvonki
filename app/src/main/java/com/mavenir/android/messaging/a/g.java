package com.mavenir.android.messaging.a;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.mavenir.android.common.BubbleFrameLayout;

public class g {
    public View a;
    public View b;
    public View c;
    public TextView d;
    public ImageView e;
    public ImageView f;
    public TextView g;
    public BubbleFrameLayout h;
    public TextView i;

    public g(View view) {
        this.a = view.findViewById(com.fgmicrotec.mobile.android.fgvoip.f.g.message_box);
        this.b = view.findViewById(com.fgmicrotec.mobile.android.fgvoip.f.g.message_section_header);
        this.c = view.findViewById(com.fgmicrotec.mobile.android.fgvoip.f.g.message_body);
        this.d = (TextView) view.findViewById(com.fgmicrotec.mobile.android.fgvoip.f.g.message_header_date);
        this.e = (ImageView) view.findViewById(com.fgmicrotec.mobile.android.fgvoip.f.g.message_status);
        this.g = (TextView) view.findViewById(com.fgmicrotec.mobile.android.fgvoip.f.g.message_time);
        this.i = (TextView) view.findViewById(com.fgmicrotec.mobile.android.fgvoip.f.g.message_text);
        this.f = (ImageView) view.findViewById(com.fgmicrotec.mobile.android.fgvoip.f.g.message_lock_indicator);
        this.h = (BubbleFrameLayout) view.findViewById(com.fgmicrotec.mobile.android.fgvoip.f.g.message_content);
    }
}
