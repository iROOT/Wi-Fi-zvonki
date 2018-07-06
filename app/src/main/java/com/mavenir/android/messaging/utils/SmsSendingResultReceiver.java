package com.mavenir.android.messaging.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.mavenir.android.common.q;

public class SmsSendingResultReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        boolean z;
        int intExtra = intent.getIntExtra("errorCode", -1);
        String str = "";
        if (intExtra > -1) {
            str = " (" + intExtra + ")";
        }
        switch (getResultCode()) {
            case -1:
                q.a("SmsSendingResultReceiver", "Sms sending state: OK");
                z = true;
                break;
            case 1:
                q.a("SmsSendingResultReceiver", "Sms sending state: RESULT_ERROR_GENERIC_FAILURE" + str);
                z = false;
                break;
            case 2:
                q.a("SmsSendingResultReceiver", "Sms sending state: RESULT_ERROR_RADIO_OFF" + str);
                z = false;
                break;
            case 3:
                q.a("SmsSendingResultReceiver", "Sms sending state: RESULT_ERROR_NULL_PDU" + str);
                z = false;
                break;
            case 4:
                q.a("SmsSendingResultReceiver", "Sms sending state: RESULT_ERROR_NO_SERVICE" + str);
                z = false;
                break;
            default:
                q.a("SmsSendingResultReceiver", "Sms sending state: UNKNOWN");
                z = false;
                break;
        }
        Intent intent2 = new Intent();
        if (z) {
            intent2.setAction("MessagingService.ACTION_SEND_SUCCESS");
        } else {
            intent2.setAction("MessagingService.ACTION_SEND_FAILED");
        }
        intent2.putExtra("EXTRA_ERROR_CODE", getResultCode());
        intent2.putExtra("EXTRA_ERROR_TYPE", true);
        intent2.putExtra("EXTRA_URI", intent.getStringExtra("EXTRA_URI"));
        context.sendBroadcast(intent2);
    }
}
