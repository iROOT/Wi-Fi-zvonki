package com.mavenir.android.fragments;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.DialogFragment;
import android.text.Html;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.util.SparseIntArray;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import com.fgmicrotec.mobile.android.fgmag.VoIP.d;
import com.fgmicrotec.mobile.android.fgvoip.FgVoIP;
import com.fgmicrotec.mobile.android.fgvoip.f.e;
import com.fgmicrotec.mobile.android.fgvoip.f.k;
import com.mavenir.android.activity.ExceptionDialogActivity;
import com.mavenir.android.common.q;
import com.mavenir.android.settings.c.j;

public class f extends DialogFragment {
    protected static int a;
    protected static a b;
    protected static int c;
    protected static String d;
    protected static boolean e = false;
    protected static boolean f = false;
    protected static Handler g;
    protected static SparseIntArray h = new SparseIntArray();
    protected static SparseIntArray i = new SparseIntArray();
    private static Dialog j;

    public enum a {
        GENERAL,
        SYSTEM,
        H3G_ACTIVATION,
        H3G_DEACTIVATION,
        VOIPC_PROXY,
        CUSTOM
    }

    public enum b {
        ENABLE_WIFI,
        DISABLE_WIFI,
        DISABLE_AIRPLANE_MODE,
        ENABLE_DATA,
        ENABLE_PERMISSION
    }

    static {
        h.put(d.FGVOIPCPROXY_POPUP_RESTARTING_SERVICE.ordinal(), k.exception_voipc_proxy_restarting);
        h.put(d.FGVOIPCPROXY_POPUP_ISP_BLOCKED_ACCESS.ordinal(), k.exception_voipc_proxy_blocked);
        h.put(d.FGVOIPCPROXY_POPUP_INVALID_SDP.ordinal(), k.exception_voipc_proxy_invalid_sdp);
        i.put(d.FGVOIPCPROXY_POPUP_INVALID_SDP.ordinal(), k.exception_url_help_5);
        h.put(d.FGVOIPCPROXY_POPUP_ISP_BLOCKED_ACCESS_IN_CALL.ordinal(), k.exception_voipc_proxy_blocked_access_in_call);
        h.put(d.FGVOIPCPROXY_POPUP_NO_SRTP_AGREEMENT.ordinal(), k.exception_voipc_proxy_no_srtp_agreement);
        h.put(d.FGVOIPCPROXY_POPUP_TLS_ERROR.ordinal(), k.exception_voipc_proxy_tls_error);
        h.put(d.FGVOIPCPROXY_POPUP_REGISTER_FAILED.ordinal(), k.exception_voipc_proxy_register_failed);
        h.put(d.FGVOIPCPROXY_POPUP_INVITE_SIP_ERROR.ordinal(), k.exception_voipc_proxy_sip_error);
        h.put(d.FGVOIPCPROXY_POPUP_INVITE_NOT_ACCEPTABLE.ordinal(), k.exception_voipc_proxy_invite_not_acceptable);
        i.put(d.FGVOIPCPROXY_POPUP_INVITE_NOT_ACCEPTABLE.ordinal(), k.exception_url_help_5);
        h.put(d.FGVOIPCPROXY_POPUP_WIFI_ISSUE.ordinal(), k.exception_voipc_proxy_wifi_issue);
        h.put(d.FGVOIPCPROXY_POPUP_LOCATION_ISSUE.ordinal(), k.exception_voipc_proxy_location_issue);
    }

    public static f a(a aVar, int i, int i2, String str, boolean z) {
        f fVar;
        try {
            fVar = (f) Class.forName("com.mavenir.android.vtow.c.a").newInstance();
        } catch (ClassNotFoundException e) {
            fVar = null;
        } catch (Throwable e2) {
            q.c("ExceptionDialogFragment", "newInstance()", e2);
            fVar = null;
        } catch (Throwable e22) {
            q.c("ExceptionDialogFragment", "newInstance()", e22);
            fVar = null;
        }
        if (fVar == null) {
            fVar = new f();
        }
        b = aVar;
        a = i;
        c = i2;
        d = str;
        e = z;
        g = new Handler();
        f = false;
        q.b("ExceptionDialogFragment", "newInstance(): type: " + aVar + "(" + aVar.ordinal() + "), ID: " + i + ", SIP code: " + i2 + ", msg: " + str);
        return fVar;
    }

    public Dialog onCreateDialog(Bundle bundle) {
        Dialog dialog = null;
        if (b == null) {
            q.d("ExceptionDialogFragment", "onCreateDialog(): mExceptionType is null");
        } else {
            switch (b) {
                case GENERAL:
                    dialog = c();
                    break;
                case SYSTEM:
                    dialog = b();
                    break;
                case VOIPC_PROXY:
                    dialog = f();
                    if (a == d.FGVOIPCPROXY_POPUP_LOCATION_ISSUE.ordinal()) {
                        j = dialog;
                        break;
                    }
                    break;
                case CUSTOM:
                    dialog = d();
                    break;
            }
            if (dialog != null) {
                dialog.setCanceledOnTouchOutside(false);
            }
        }
        return dialog;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (f) {
            g.postDelayed(new Runnable(this) {
                final /* synthetic */ f a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.dismissAllowingStateLoss();
                }
            }, 5000);
        }
    }

    public void onDismiss(DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        f = false;
        Activity activity = getActivity();
        if (f && activity != null) {
            activity.sendBroadcast(new Intent("CallManager.ActionUnblockClosing"));
        }
        if (e && activity != null && (activity instanceof ExceptionDialogActivity)) {
            ((ExceptionDialogActivity) activity).a();
        }
    }

    protected TextView a(String str) {
        TextView textView = new TextView(getActivity());
        textView.setTextAppearance(getActivity(), 16973892);
        textView.setLinkTextColor(getResources().getColor(e.hyperlink));
        textView.setLinksClickable(true);
        textView.setLayoutParams(new LayoutParams(-1, -2));
        textView.setText(Html.fromHtml(str));
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        textView.setPadding(10, 10, 10, 10);
        return textView;
    }

    private Dialog b() {
        if (a == b.ENABLE_WIFI.ordinal()) {
            return a(true);
        }
        if (a == b.DISABLE_WIFI.ordinal()) {
            return a(false);
        }
        if (a == b.DISABLE_AIRPLANE_MODE.ordinal()) {
            return g();
        }
        if (a == b.ENABLE_DATA.ordinal()) {
            return h();
        }
        if (a == b.ENABLE_PERMISSION.ordinal()) {
            return i();
        }
        q.d("ExceptionDialogFragment", "displaySystemDialog(): invalid system exception ID: " + a);
        return null;
    }

    private Dialog c() {
        String e = e();
        if (TextUtils.isEmpty(e)) {
            e = getString(k.exception_generic);
        }
        return a(getString(k.dialog_error_title), e);
    }

    private Dialog d() {
        String str = d;
        if (TextUtils.isEmpty(str)) {
            str = getString(k.exception_generic);
        }
        return a(getString(k.dialog_error_title), str);
    }

    private String e() {
        CharSequence a = j.a(a);
        CharSequence b = j.b(a);
        if (TextUtils.isEmpty(a)) {
            int i = h.get(a);
            if (i == 0) {
                i = k.exception_generic;
            }
            String string = getString(i);
            if (TextUtils.isEmpty(b)) {
                i = i.get(a);
                if (i != 0) {
                    String string2 = getString(i);
                    Object obj = string;
                }
            }
            a = b;
            String obj2 = string;
        } else {
            CharSequence charSequence = b;
            b = a;
            a = charSequence;
        }
        StringBuilder stringBuilder = new StringBuilder();
        if (!TextUtils.isEmpty(obj2)) {
            stringBuilder.append(obj2);
            if (!TextUtils.isEmpty(string2)) {
                stringBuilder.append("<br/><br/>");
                stringBuilder.append("<a href='").append(string2).append("'>").append(string2).append("</a>");
            }
        }
        return stringBuilder.toString();
    }

    private Dialog f() {
        String e = e();
        f = false;
        if (a == d.FGVOIPCPROXY_POPUP_RESTARTING_SERVICE.ordinal() || a == d.FGVOIPCPROXY_POPUP_NO_SRTP_AGREEMENT.ordinal() || a == d.FGVOIPCPROXY_POPUP_INVITE_SIP_ERROR.ordinal()) {
            f = true;
        } else if (e != null && a == d.FGVOIPCPROXY_POPUP_REGISTER_FAILED.ordinal()) {
            e = e.replace("[x]", "[0004]");
        } else if (e != null && a == d.FGVOIPCPROXY_POPUP_INVALID_SDP.ordinal()) {
        }
        return a(getString(k.dialog_error_title), e);
    }

    protected Dialog a(String str, String str2) {
        q.a("ExceptionDialogFragment", "displayErrorMessageDialog(): " + str2);
        Builder builder = new Builder(getActivity());
        builder.setIcon(com.fgmicrotec.mobile.android.fgvoip.f.f.ic_launcher);
        builder.setView(a(str2));
        builder.setCancelable(false);
        if (!f) {
            builder.setTitle(str);
            builder.setPositiveButton(k.dialog_ok, new OnClickListener(this) {
                final /* synthetic */ f a;

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

    private Dialog g() {
        q.a("ExceptionDialogFragment", "displayAirplaneModeDialog()");
        Builder builder = new Builder(getActivity());
        builder.setTitle(k.activation_disable_airplane_mode_title);
        builder.setMessage(k.activation_disable_airplane_mode_message);
        builder.setCancelable(false);
        builder.setPositiveButton(k.dialog_ok, new OnClickListener(this) {
            final /* synthetic */ f a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                try {
                    this.a.startActivity(new Intent("android.settings.AIRPLANE_MODE_SETTINGS"));
                } catch (Exception e) {
                    q.d("ExceptionDialogFragment", "displayAirplaneModeDialog(): " + e);
                }
                dialogInterface.dismiss();
            }
        });
        builder.setNegativeButton(k.dialog_cancel, new OnClickListener(this) {
            final /* synthetic */ f a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        return builder.create();
    }

    private Dialog a(final boolean z) {
        q.a("ExceptionDialogFragment", "displayWifiDialog()");
        Builder builder = new Builder(getActivity());
        if (z) {
            builder.setTitle(k.activation_enable_wifi_title);
            builder.setMessage(k.configuration_enable_wifi_message);
        } else {
            builder.setTitle(k.activation_disable_wifi_title);
            builder.setMessage(k.activation_disable_wifi_message);
        }
        builder.setCancelable(false);
        builder.setPositiveButton(k.dialog_ok, new OnClickListener(this) {
            final /* synthetic */ f a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                this.a.startActivity(new Intent("android.settings.WIFI_SETTINGS"));
                dialogInterface.dismiss();
            }
        });
        builder.setNegativeButton(k.dialog_cancel, new OnClickListener(this) {
            final /* synthetic */ f b;

            public void onClick(DialogInterface dialogInterface, int i) {
                if (z) {
                    dialogInterface.dismiss();
                }
            }
        });
        return builder.create();
    }

    private Dialog h() {
        Builder builder = new Builder(getActivity());
        builder.setTitle(k.activation_enable_mobile_data_title);
        builder.setMessage(k.activation_enable_mobile_data_message);
        builder.setCancelable(false);
        builder.setPositiveButton(k.dialog_ok, new OnClickListener(this) {
            final /* synthetic */ f a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                try {
                    this.a.startActivity(new Intent("android.settings.DATA_ROAMING_SETTINGS"));
                } catch (Throwable e) {
                    q.a("ExceptionDialogFragment", "startActivity(): ACTION_DATA_ROAMING_SETTINGS", e);
                    try {
                        this.a.startActivity(new Intent("android.settings.WIRELESS_SETTINGS"));
                    } catch (Throwable e2) {
                        q.a("ExceptionDialogFragment", "startActivity(): ACTION_WIRELESS_SETTINGS", e2);
                        Toast.makeText(this.a.getActivity(), this.a.getActivity().getString(k.activation_enable_mobile_data_failed), 1).show();
                    }
                }
                dialogInterface.dismiss();
            }
        });
        builder.setNegativeButton(k.dialog_cancel, new OnClickListener(this) {
            final /* synthetic */ f a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        return builder.create();
    }

    private Dialog i() {
        Builder builder = new Builder(getActivity());
        builder.setTitle(k.exception_permission_title);
        if (TextUtils.isEmpty(d)) {
            builder.setMessage(k.exception_permission_message_generic);
        } else {
            builder.setMessage(d);
        }
        builder.setCancelable(false);
        builder.setPositiveButton(k.dialog_ok, new OnClickListener(this) {
            final /* synthetic */ f a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                if (f.c == -10) {
                    FgVoIP.U().a();
                    if (this.a.getActivity() != null) {
                        this.a.getActivity().finish();
                    }
                }
                dialogInterface.dismiss();
            }
        });
        return builder.create();
    }

    public static void a() {
        if (j != null) {
            j.dismiss();
        }
    }
}
