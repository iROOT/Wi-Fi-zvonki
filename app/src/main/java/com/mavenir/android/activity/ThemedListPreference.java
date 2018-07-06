package com.mavenir.android.activity;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.preference.ListPreference;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.fgmicrotec.mobile.android.fgvoip.f.g;
import com.fgmicrotec.mobile.android.fgvoip.f.h;

public class ThemedListPreference extends ListPreference implements OnItemClickListener {
    private int a;
    private CharSequence b;

    public ThemedListPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ThemedListPreference(Context context) {
        super(context);
    }

    protected View onCreateDialogView() {
        View inflate = View.inflate(getContext(), h.dialog_settings_preferance, null);
        this.b = getDialogTitle();
        if (this.b == null) {
            this.b = getTitle();
        }
        ((TextView) inflate.findViewById(g.dialog_title)).setText(this.b);
        ListView listView = (ListView) inflate.findViewById(16908298);
        listView.setAdapter(new ArrayAdapter(getContext(), h.custom_preferance_list_dialog, getEntries()));
        listView.setChoiceMode(1);
        listView.setItemChecked(findIndexOfValue(getValue()), true);
        listView.setOnItemClickListener(this);
        return inflate;
    }

    protected void onPrepareDialogBuilder(Builder builder) {
        if (getEntries() == null || getEntryValues() == null) {
            super.onPrepareDialogBuilder(builder);
            return;
        }
        this.a = findIndexOfValue(getValue());
        builder.setTitle(null);
        builder.setPositiveButton(null, null);
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        this.a = i;
        onClick(getDialog(), -1);
        getDialog().dismiss();
    }

    protected void onDialogClosed(boolean z) {
        super.onDialogClosed(z);
        if (z && this.a >= 0 && getEntryValues() != null) {
            String charSequence = getEntryValues()[this.a].toString();
            if (callChangeListener(charSequence)) {
                setValue(charSequence);
            }
        }
    }
}
