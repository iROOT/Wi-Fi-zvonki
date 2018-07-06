package com.mavenir.android.view;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.preference.EditTextPreference;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import com.fgmicrotec.mobile.android.fgvoip.f.e;
import com.fgmicrotec.mobile.android.fgvoip.f.g;
import com.fgmicrotec.mobile.android.fgvoip.f.h;
import com.fgmicrotec.mobile.android.fgvoip.f.k;

public class SupplementaryEditTextPreference extends EditTextPreference implements OnClickListener {
    private Activity a;
    private boolean b = false;
    private ImageButton c;
    private EditText d;

    public SupplementaryEditTextPreference(Activity activity) {
        super(activity);
        this.a = activity;
    }

    public void a(boolean z) {
        this.b = z;
    }

    protected View onCreateDialogView() {
        View inflate = LayoutInflater.from(getContext()).inflate(h.preference_suplementary_dialog, null);
        this.c = (ImageButton) inflate.findViewById(g.chooseNumberButton);
        this.c.setOnClickListener(this);
        return inflate;
    }

    protected void onAddEditTextToDialogView(View view, EditText editText) {
        ViewGroup viewGroup = (ViewGroup) view.findViewById(g.edittext_container);
        if (viewGroup != null) {
            this.d = editText;
            editText.setTextColor(this.a.getResources().getColor(e.black));
            viewGroup.addView(editText, -1, -2);
        }
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        if (i == -3) {
            i = -1;
            this.d.setText("");
        }
        super.onClick(dialogInterface, i);
    }

    public void onClick(View view) {
        if (view.getId() == g.chooseNumberButton) {
            this.a.startActivityForResult(new Intent("android.intent.action.PICK", Phone.CONTENT_URI), 1);
        }
    }

    protected void onPrepareDialogBuilder(Builder builder) {
        if (this.b) {
            builder.setNeutralButton(k.preference_call_additional_turn_off, this);
            builder.setPositiveButton(k.preference_call_additional_update, this);
        } else {
            builder.setNeutralButton(null, null);
            builder.setPositiveButton(k.preference_call_additional_turn_on, this);
        }
        super.onPrepareDialogBuilder(builder);
    }
}
