package com.mavenir.android.messaging.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.mavenir.android.common.q;

public class SmsDeliveryResultReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        q.a("SmsSendingResultReceiver", "Sms delivery result: " + getResultCode());
        switch (getResultCode()) {
            case -1:
                q.a("SmsSendingResultReceiver", "Sms delivery result: delivered");
                return;
            default:
                q.a("SmsSendingResultReceiver", "Sms delivery result: not delivered");
                return;
        }
    }
}
