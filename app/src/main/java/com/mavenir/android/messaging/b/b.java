package com.mavenir.android.messaging.b;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import com.fgmicrotec.mobile.android.fgvoip.f.h;
import com.fgmicrotec.mobile.android.fgvoip.f.k;
import com.mavenir.android.common.o;
import com.mavenir.android.messaging.a.d;

public class b extends DialogFragment implements OnItemClickListener {
    private o a;

    public b(o oVar) {
        this.a = oVar;
    }

    public Dialog onCreateDialog(Bundle bundle) {
        Builder builder = new Builder(getActivity());
        builder.setTitle(k.emoticon_title);
        builder.setCancelable(true);
        View inflate = ((LayoutInflater) getActivity().getSystemService("layout_inflater")).inflate(h.emoticon_listview, null);
        ListView listView = (ListView) inflate.findViewById(16908298);
        listView.setAdapter(new d(getActivity()));
        listView.setOnItemClickListener(this);
        builder.setView(inflate);
        return builder.create();
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        this.a.a(i);
        dismiss();
    }
}
