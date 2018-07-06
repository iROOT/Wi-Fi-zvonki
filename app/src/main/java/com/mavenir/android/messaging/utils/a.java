package com.mavenir.android.messaging.utils;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.fgmicrotec.mobile.android.fgvoip.f.g;
import com.fgmicrotec.mobile.android.fgvoip.f.h;
import com.fgmicrotec.mobile.android.fgvoip.f.k;
import com.mavenir.android.messaging.c.c;
import com.mavenir.android.messaging.provider.d;

public class a extends DialogFragment implements OnClickListener {
    private static c a;
    private static Context b;

    public static a a(Context context, c cVar) {
        b = context;
        a = cVar;
        return new a();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        getDialog().setTitle(k.quick_conversation_actions);
        View inflate = layoutInflater.inflate(h.message_conversation_actions_popup, viewGroup, false);
        a(inflate);
        return inflate;
    }

    public void onClick(View view) {
        if (view.getId() == g.quick_delete) {
            a();
            dismiss();
        }
    }

    private void a(View view) {
        view.findViewById(g.quick_delete).setOnClickListener(this);
    }

    private void a(boolean z) {
        d.a(b, a.c(), z);
        b.c(a.c());
    }

    private void a() {
        Builder builder = new Builder(b);
        builder.setTitle(k.delete);
        builder.setIcon(17301659);
        View linearLayout = new LinearLayout(b);
        linearLayout.setOrientation(1);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.setMargins(0, 0, 0, 0);
        View textView = new TextView(b);
        textView.setText(k.delete_thread_message);
        textView.setLayoutParams(layoutParams);
        textView.setTextSize(2, 20.0f);
        layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.setMargins(0, 0, 0, 0);
        final View checkBox = new CheckBox(b);
        checkBox.setChecked(false);
        checkBox.setText(k.delete_thread_locked);
        checkBox.setLayoutParams(layoutParams);
        linearLayout.addView(textView);
        linearLayout.addView(checkBox);
        builder.setView(linearLayout);
        builder.setPositiveButton(k.dialog_ok, new DialogInterface.OnClickListener(this) {
            final /* synthetic */ a b;

            public void onClick(DialogInterface dialogInterface, int i) {
                this.b.a(checkBox.isChecked());
            }
        });
        builder.setNegativeButton(k.dialog_cancel, null);
        builder.create().show();
    }
}
