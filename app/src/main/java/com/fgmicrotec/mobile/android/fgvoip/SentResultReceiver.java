package com.fgmicrotec.mobile.android.fgvoip;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import com.fgmicrotec.mobile.android.fgvoip.f.k;

public class SentResultReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        switch (getResultCode()) {
            case -1:
                Toast.makeText(context, context.getString(k.activation_sms_success), 0).show();
                return;
            case 1:
                Toast.makeText(context, context.getString(k.activation_sms_failure_generic), 0).show();
                return;
            case 2:
                Toast.makeText(context, context.getString(k.activation_sms_failure_radio_off), 0).show();
                return;
            case 3:
                Toast.makeText(context, context.getString(k.activation_sms_failure_null_pdu), 0).show();
                return;
            case 4:
                Toast.makeText(context, context.getString(k.activation_sms_failure_no_service), 0).show();
                return;
            default:
                Toast.makeText(context, context.getString(k.activation_sms_failure_unknown), 0).show();
                return;
        }
    }
}
