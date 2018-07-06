package com.mavenir.android.vtow.c;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.SparseIntArray;
import com.fgmicrotec.mobile.android.fgmag.VoIP.d;
import com.fgmicrotec.mobile.android.fgvoip.FgVoIP;
import com.fgmicrotec.mobile.android.fgvoip.f.k;
import com.mavenir.android.common.q;
import com.mavenir.android.fragments.f;
import com.mavenir.android.settings.c;
import com.mavenir.android.settings.c.j;
import com.mavenir.android.vtow.activation.ActivationAdapter.b;

public class a extends f {
    protected static SparseIntArray j = new SparseIntArray();

    static {
        j.put(b.PROVISIONING_OK.ordinal(), k.activation_exception_ok);
        j.put(b.PROVISIONING_CONFIGURATION_UNAVAILABLE.ordinal(), k.activation_exception_unavailable);
        j.put(b.PROVISIONING_FAILED_NO_RECORD.ordinal(), k.activation_exception_no_record);
        j.put(b.PROVISIONING_FAILED_BLOCKED.ordinal(), k.activation_exception_blocked);
        j.put(b.PROVISIONING_BUSY.ordinal(), k.activation_exception_busy);
        j.put(b.PROVISIONING_HTTP_ERROR.ordinal(), k.activation_exception_http_error);
        j.put(b.PROVISIONING_NO_RESPONSE.ordinal(), k.activation_exception_no_response);
        j.put(b.PROVISIONING_PARSING_FAILED.ordinal(), k.activation_exception_parsing_failed);
        j.put(b.PROVISIONING_TLS_ERROR.ordinal(), k.activation_exception_tls_error);
        j.put(b.PROVISIONING_TLS_OCSP_ERROR_GROUP_1.ordinal(), k.activation_exception_ocsp_error);
        j.put(b.PROVISIONING_TLS_OCSP_ERROR_GROUP_2.ordinal(), k.activation_exception_ocsp_error);
        j.put(b.PROVISIONING_OCSP_NO_RESPONSE_REACHABLE.ordinal(), k.activation_exception_ocsp_no_resp_reach);
        j.put(b.PROVISIONING_OCSP_NO_RESPONSE_NOT_REACHABLE.ordinal(), k.activation_exception_ocsp_no_resp_not_reach);
        j.put(b.PROVISIONING_IMSI_MISMATCH.ordinal(), k.activation_exception_imsi_mismatch);
        j.put(b.PROVISIONING_INVALID_REQUEST.ordinal(), k.activation_exception_invalid_request);
    }

    private Dialog b() {
        String string;
        if (TextUtils.isEmpty(d)) {
            string = getString(j.get(a));
            String string2 = getResources().getString(k.exception_url_help_4);
            string = String.format(string, new Object[]{"<a href='" + string2 + "'>" + string2 + "</a>"});
            if (a == b.PROVISIONING_TLS_OCSP_ERROR_GROUP_1.ordinal()) {
                string = string.replace("[000x]", "[0005]");
            } else if (a == b.PROVISIONING_TLS_OCSP_ERROR_GROUP_2.ordinal()) {
                string = string.replace("[000x]", "[0006]");
            }
        } else {
            string = d;
        }
        q.a("ExceptionDialogFragment", "displayH3GActivationErrorDialog(): " + string);
        return a(getString(k.activation_title_error), string);
    }

    private Dialog c() {
        String string = getString(k.activation_lte_no_message);
        if (!TextUtils.isEmpty(d)) {
            string = d;
        }
        q.a("ExceptionDialogFragment", "displayH3GActivationDialog(): " + string);
        return a(getString(k.activation_lte_title), string);
    }

    private String d() {
        if (d != null) {
            return d;
        }
        String t;
        if (a == com.mavenir.android.vtow.activation.ActivationAdapter.a.FGVOIPCPROXY_DEACTIVATION_MUST_REPROVISION_CREDENTIALS.ordinal()) {
            t = c.q.t();
            if (TextUtils.isEmpty(t)) {
                return com.mavenir.android.settings.b.I;
            }
            return t;
        }
        t = j.a(d.FGVOIPCPROXY_POPUP_REGISTER_FAILED.ordinal());
        if (TextUtils.isEmpty(t)) {
            t = getString(h.get(d.FGVOIPCPROXY_POPUP_REGISTER_FAILED.ordinal()));
        }
        if (a == com.mavenir.android.vtow.activation.ActivationAdapter.a.FGVOIPCPROXY_DEACTIVATION_NO_USER_PROFILE.ordinal()) {
            return t.replace("[x]", "[0002]");
        }
        if (a == com.mavenir.android.vtow.activation.ActivationAdapter.a.FGVOIPCPROXY_DEACTIVATION_TLS.ordinal()) {
            return t.replace("[x]", "[0003]");
        }
        return t;
    }

    private Dialog e() {
        String d = d();
        Builder builder = new Builder(getActivity());
        builder.setIcon(com.fgmicrotec.mobile.android.fgvoip.f.f.ic_launcher);
        builder.setTitle(k.activation_deactivated_title);
        builder.setView(a(d));
        builder.setCancelable(false);
        builder.setPositiveButton(k.dialog_ok, new OnClickListener(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                this.a.f();
            }
        });
        builder.setOnCancelListener(new OnCancelListener(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onCancel(DialogInterface dialogInterface) {
                this.a.f();
            }
        });
        q.a("ExceptionDialogFragment", "displayDeactivationErrorDialog(): " + d);
        return builder.create();
    }

    public Dialog onCreateDialog(Bundle bundle) {
        Dialog dialog = null;
        if (b != null) {
            switch (b) {
                case H3G_ACTIVATION:
                    if (a != b.H3GPROVISIONING_LTE_RESPONSE.ordinal()) {
                        dialog = b();
                        break;
                    }
                    dialog = c();
                    break;
                case H3G_DEACTIVATION:
                    dialog = e();
                    break;
            }
            if (dialog != null) {
                dialog.setCanceledOnTouchOutside(false);
                return dialog;
            }
        }
        return super.onCreateDialog(bundle);
    }

    private void f() {
        if (c != -10) {
            Intent Y = FgVoIP.U().Y();
            Y.addFlags(268435456);
            Y.addFlags(32768);
            getActivity().startActivity(Y);
        }
    }
}
