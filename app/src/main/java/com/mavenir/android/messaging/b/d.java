package com.mavenir.android.messaging.b;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import com.fgmicrotec.mobile.android.fgvoip.f.k;
import com.mavenir.android.common.q;
import com.mavenir.android.messaging.c.a;
import java.util.List;

public class d extends DialogFragment {
    private long a;
    private String b;

    public static d a(com.mavenir.android.messaging.c.d dVar) {
        d dVar2 = new d();
        Bundle bundle = new Bundle();
        bundle.putLong("extra_unique_message_id", dVar.c());
        bundle.putLong("extra_message_thread_id", dVar.d());
        bundle.putString("extra_message_type", dVar.b());
        bundle.putLong("extra_message_date", dVar.h());
        bundle.putLong("extra_message_date", dVar.h());
        bundle.putString("extra_message_body", dVar.g());
        dVar2.setArguments(bundle);
        return dVar2;
    }

    public Dialog onCreateDialog(Bundle bundle) {
        this.a = getArguments().getLong("extra_unique_message_id", 0);
        this.b = getArguments().getString("extra_message_type");
        Builder builder = new Builder(getActivity());
        builder.setTitle(k.message_details_title);
        builder.setIcon(17301659);
        List a = a(bundle);
        if (a == null || a.size() <= 0) {
            Object obj = "Error in retrieving message details.";
            builder.setMessage(obj);
            q.d("MessageDetailsDialogFragment", obj);
        } else {
            builder.setMessage(a(a));
            builder.setOnCancelListener(new OnCancelListener(this) {
                final /* synthetic */ d a;

                {
                    this.a = r1;
                }

                public void onCancel(DialogInterface dialogInterface) {
                    dialogInterface.dismiss();
                }
            });
            builder.setPositiveButton(k.dialog_ok, new OnClickListener(this) {
                final /* synthetic */ d a;

                {
                    this.a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
        }
        return builder.create();
    }

    private List<com.mavenir.android.messaging.c.d> a(Bundle bundle) {
        String string = getArguments().getString("extra_message_type");
        long j = getArguments().getLong("extra_unique_message_id", 0);
        long j2 = getArguments().getLong("extra_message_thread_id", 0);
        long j3 = getArguments().getLong("extra_message_date", 0);
        String string2 = getArguments().getString("extra_message_body", "");
        if (!string.equals("sms")) {
            return null;
        }
        return com.mavenir.android.messaging.provider.d.a(getActivity(), j < 0, j2, j3, string2);
    }

    private String a(List<com.mavenir.android.messaging.c.d> list) {
        StringBuffer stringBuffer = new StringBuffer();
        b(stringBuffer);
        for (com.mavenir.android.messaging.c.d dVar : list) {
            a(dVar, stringBuffer);
            b(dVar, stringBuffer);
            c(dVar, stringBuffer);
            d(dVar, stringBuffer);
        }
        a(stringBuffer);
        return stringBuffer.toString();
    }

    private void a(StringBuffer stringBuffer) {
        if (this.a < 0) {
            stringBuffer.append(" ");
            stringBuffer.append(getResources().getString(k.pop_up_messages_cant_remove));
        }
    }

    private void b(StringBuffer stringBuffer) {
        stringBuffer.append(getResources().getString(k.message_details_type));
        stringBuffer.append(" ");
        stringBuffer.append(getResources().getString(k.preference_sms_protocol_sms));
    }

    private void a(com.mavenir.android.messaging.c.d dVar, StringBuffer stringBuffer) {
        stringBuffer.append("\n");
        if (dVar.t()) {
            stringBuffer.append(getResources().getString(k.message_details_to));
        } else {
            stringBuffer.append(getResources().getString(k.message_details_from));
        }
        stringBuffer.append(" ");
        String f = dVar.f();
        if (!TextUtils.isEmpty(f)) {
            a a = a.a(getActivity(), f, false);
            if (a.a() != -1) {
                stringBuffer.append(a.b());
                stringBuffer.append("(");
            }
            stringBuffer.append(f);
            if (a.a() != -1) {
                stringBuffer.append(")");
            }
        }
    }

    private void b(com.mavenir.android.messaging.c.d dVar, StringBuffer stringBuffer) {
        stringBuffer.append("\n");
        if (dVar.t()) {
            stringBuffer.append(getResources().getString(k.message_details_sent));
        } else {
            stringBuffer.append(getResources().getString(k.message_details_received));
        }
        stringBuffer.append(" ");
        stringBuffer.append(com.mavenir.android.common.t.d.a(dVar.h(), com.mavenir.android.common.t.d.a.dd_MMM_yyyy_kk_mm_ss));
    }

    private void c(com.mavenir.android.messaging.c.d dVar, StringBuffer stringBuffer) {
        if (dVar.t()) {
            stringBuffer.append("\n");
            stringBuffer.append(getResources().getString(k.message_details_status_label));
            stringBuffer.append(" ");
            if (dVar.m() == 5) {
                stringBuffer.append(getResources().getString(k.message_details_status_failed));
            } else if (dVar.m() == 2) {
                stringBuffer.append(getResources().getString(k.message_details_status_sent));
            } else if (dVar.m() == 4 || dVar.m() == 6) {
                stringBuffer.append(getResources().getString(k.message_details_status_sending));
            }
        }
    }

    private void d(com.mavenir.android.messaging.c.d dVar, StringBuffer stringBuffer) {
        if (dVar.t()) {
            stringBuffer.append("\n");
            stringBuffer.append(getResources().getString(k.message_details_delivery_label));
            stringBuffer.append(" ");
            if (dVar.o() == -1) {
                stringBuffer.append(getResources().getString(k.message_details_report_not_requested));
            } else if (dVar.o() == NotificationCompat.FLAG_HIGH_PRIORITY) {
                stringBuffer.append(getResources().getString(k.message_details_report_not_received));
            } else if (dVar.o() == 64) {
                stringBuffer.append(getResources().getString(k.message_details_report_pending));
            } else if (dVar.i() >= dVar.h()) {
                stringBuffer.append(com.mavenir.android.common.t.d.a(dVar.i(), com.mavenir.android.common.t.d.a.dd_MMM_yyyy_kk_mm_ss));
            } else {
                stringBuffer.append(getResources().getString(k.message_details_report_received));
            }
        }
    }
}
