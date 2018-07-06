package com.mavenir.android.messaging.utils;

import android.app.AlertDialog.Builder;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract.Contacts;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Toast;
import com.fgmicrotec.mobile.android.fgvoip.FgVoIP;
import com.fgmicrotec.mobile.android.fgvoip.f.g;
import com.fgmicrotec.mobile.android.fgvoip.f.h;
import com.fgmicrotec.mobile.android.fgvoip.f.k;
import com.mavenir.android.activity.ContactDetailsActivity;
import com.mavenir.android.messaging.activity.ConversationActivity;
import com.mavenir.android.messaging.b.c;
import com.mavenir.android.messaging.c.a;

public class d extends DialogFragment implements OnClickListener {
    private static com.mavenir.android.messaging.c.d a;
    private static Context b;

    public static d a(Context context, com.mavenir.android.messaging.c.d dVar) {
        b = context;
        a = dVar;
        return new d();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        getDialog().setTitle(k.quick_message_actions);
        View inflate = layoutInflater.inflate(h.message_actions_popup, viewGroup, false);
        a(inflate);
        return inflate;
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == g.quick_contact) {
            a();
            dismiss();
        } else if (id == g.quick_details) {
            f();
            dismiss();
        } else if (id == g.quick_forward) {
            c();
            dismiss();
        } else if (id == g.quick_resend) {
            d();
            dismiss();
        } else if (id == g.quick_copy) {
            e();
            dismiss();
        } else if (id == g.quick_delete) {
            a(a.n());
            dismiss();
        } else if (id == g.quick_lock) {
            b(true);
            dismiss();
        } else if (id == g.quick_unlock) {
            b(false);
            dismiss();
        } else if (id == g.quick_content_actions) {
            g();
            dismiss();
        }
    }

    private void a(View view) {
        View findViewById = view.findViewById(g.quick_delete);
        findViewById.setOnClickListener(this);
        View findViewById2 = view.findViewById(g.quick_forward);
        findViewById2.setOnClickListener(this);
        View findViewById3 = view.findViewById(g.quick_resend);
        findViewById3.setOnClickListener(this);
        View findViewById4 = view.findViewById(g.quick_copy);
        findViewById4.setOnClickListener(this);
        View findViewById5 = view.findViewById(g.quick_details);
        findViewById5.setOnClickListener(this);
        View findViewById6 = view.findViewById(g.quick_lock);
        findViewById6.setOnClickListener(this);
        View findViewById7 = view.findViewById(g.quick_unlock);
        findViewById7.setOnClickListener(this);
        View findViewById8 = view.findViewById(g.quick_content_actions);
        findViewById8.setOnClickListener(this);
        findViewById8.setVisibility(8);
        findViewById4.setVisibility(0);
        findViewById5.setVisibility(0);
        if (a.e()) {
            if (findViewById6 != null) {
                findViewById6.setVisibility(8);
            }
            if (findViewById7 != null) {
                findViewById7.setVisibility(8);
            }
            if (findViewById != null) {
                findViewById.setVisibility(8);
            }
        } else if (a.n()) {
            if (findViewById6 != null) {
                findViewById6.setVisibility(8);
            }
            if (findViewById7 != null) {
                findViewById7.setVisibility(0);
            }
            if (findViewById != null) {
                findViewById.setVisibility(8);
            }
        } else {
            if (findViewById6 != null) {
                findViewById6.setVisibility(0);
            }
            if (findViewById7 != null) {
                findViewById7.setVisibility(8);
            }
            if (findViewById != null) {
                findViewById.setVisibility(0);
            }
        }
        if (findViewById2 != null && a.v() && a.t()) {
            findViewById2.setVisibility(8);
            findViewById3.setVisibility(0);
            return;
        }
        findViewById2.setVisibility(0);
        findViewById3.setVisibility(8);
    }

    private void a() {
        a a = a.a(b, a.f(), true);
        if (a.a() > -1) {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setClass(b, ContactDetailsActivity.class);
            intent.setData(ContentUris.withAppendedId(Contacts.CONTENT_URI, a.a()));
            b.startActivity(intent);
            return;
        }
        FgVoIP.U().h(a.f());
    }

    private void b() {
        com.mavenir.android.messaging.provider.d.a(b, a);
        Intent intent = new Intent();
        intent.setAction("com.fgmicrotec.mobile.android.voip.ActionMessageDeleted");
        intent.putExtra("extra_unique_message_id", a.c());
        b.sendBroadcast(intent);
    }

    private void a(boolean z) {
        Builder builder = new Builder(b);
        builder.setCancelable(true);
        builder.setMessage(z ? k.delete_message_locked : k.delete_message);
        builder.setPositiveButton(k.delete, new DialogInterface.OnClickListener(this) {
            final /* synthetic */ d a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                this.a.b();
            }
        });
        builder.setNegativeButton(k.dialog_cancel, null);
        builder.show();
    }

    private void c() {
        ConversationActivity.a(b, a.c(), a.b());
    }

    private void d() {
        ConversationActivity.b(b, a.c(), a.b());
    }

    private void e() {
        ((ClipboardManager) b.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText(b.getString(k.app_name), a.g()));
        Toast.makeText(b, b.getString(k.message_clipboard), 0).show();
    }

    private void f() {
        com.mavenir.android.messaging.b.d.a(a).show(((ConversationActivity) b).getSupportFragmentManager(), "dialog");
    }

    private void b(boolean z) {
        com.mavenir.android.messaging.provider.d.a(b, a, z);
    }

    private void g() {
        c.a(a.c(), a.b()).show(((ConversationActivity) b).getSupportFragmentManager(), "dialog");
    }
}
