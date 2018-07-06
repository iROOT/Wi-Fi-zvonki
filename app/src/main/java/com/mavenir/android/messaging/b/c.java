package com.mavenir.android.messaging.b;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import com.fgmicrotec.mobile.android.fgvoip.f.g;
import com.fgmicrotec.mobile.android.fgvoip.f.h;
import com.fgmicrotec.mobile.android.fgvoip.f.k;
import com.mavenir.android.common.q;
import com.mavenir.android.common.x;
import com.mavenir.android.messaging.activity.ConversationActivity;
import com.mavenir.android.messaging.c.d;
import java.util.Vector;

public class c extends DialogFragment implements OnClickListener {
    private long a;
    private String b;
    private Vector<String> c;
    private Vector<String> d;

    private class a extends ArrayAdapter<String> {
        final /* synthetic */ c a;

        a(c cVar) {
            this.a = cVar;
            super(cVar.getActivity(), h.message_content_actions_row);
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            View inflate = this.a.getActivity().getLayoutInflater().inflate(h.message_content_actions_row, viewGroup, false);
            TextView textView = (TextView) inflate.findViewById(g.text1);
            ImageView imageView = (ImageView) inflate.findViewById(g.icon1);
            if (i < this.a.d.size()) {
                textView.setText((CharSequence) this.a.d.get(i));
            } else if (i < this.a.d.size() * 2) {
                textView.setText((CharSequence) this.a.d.get(i - this.a.d.size()));
            } else {
                textView.setText((CharSequence) this.a.c.get(i - (this.a.d.size() * 2)));
            }
            return inflate;
        }
    }

    public static c a(long j, String str) {
        c cVar = new c();
        Bundle bundle = new Bundle();
        bundle.putLong("extra_unique_message_id", j);
        bundle.putString("extra_message_type", str);
        cVar.setArguments(bundle);
        return cVar;
    }

    public Dialog onCreateDialog(Bundle bundle) {
        d a;
        int i = 0;
        this.a = getArguments().getLong("extra_unique_message_id", 0);
        this.b = getArguments().getString("extra_message_type");
        Builder builder = new Builder(getActivity());
        builder.setTitle(k.message_content_actions_title);
        builder.setIcon(17301659);
        if (this.b.equals("sms")) {
            a = com.mavenir.android.messaging.provider.d.a(getActivity(), this.a);
        } else {
            a = com.mavenir.android.messaging.provider.d.c(getActivity(), this.a);
        }
        if (a != null) {
            int i2;
            ListAdapter aVar = new a(this);
            this.c = x.b(a.g());
            this.d = x.a(a.g());
            for (i2 = 0; i2 < this.d.size(); i2++) {
                aVar.add(this.d.get(i2));
            }
            for (i2 = 0; i2 < this.d.size(); i2++) {
                aVar.add(this.d.get(i2));
            }
            while (i < this.c.size()) {
                aVar.add(this.c.get(i));
                i++;
            }
            builder.setAdapter(aVar, this);
            builder.setOnCancelListener(new OnCancelListener(this) {
                final /* synthetic */ c a;

                {
                    this.a = r1;
                }

                public void onCancel(DialogInterface dialogInterface) {
                    dialogInterface.dismiss();
                }
            });
        } else {
            Object obj = "Error in retrieving message.";
            builder.setMessage(obj);
            q.d("MessageContentActionsFragment", obj);
        }
        return builder.create();
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        Intent intent;
        if (i < this.d.size()) {
            intent = new Intent("android.intent.action.DIAL");
            intent.setData(Uri.parse("tel:" + ((String) this.d.get(i)).replace(".", "")));
            startActivity(intent);
        } else if (i < this.d.size() * 2) {
            ConversationActivity.newMessage(getActivity(), ((String) this.d.get(i - this.d.size())).replace(".", ""), null, false);
        } else {
            String toLowerCase = ((String) this.c.get(i - (this.d.size() * 2))).toLowerCase();
            int indexOf = toLowerCase.indexOf("http://");
            int indexOf2 = toLowerCase.indexOf("https://");
            String str = "";
            if (indexOf == -1 && indexOf2 == -1) {
                toLowerCase = "http://" + toLowerCase;
            }
            intent = new Intent("android.intent.action.VIEW", Uri.parse(toLowerCase));
            intent.addFlags(268435456);
            startActivity(intent);
        }
        dismiss();
    }
}
