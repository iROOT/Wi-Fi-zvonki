package com.mavenir.android.common;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class SmsDeliveryReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        q.b("SmsDeliveryReceiver", "Sms delivery result: " + getResultCode());
        switch (getResultCode()) {
            case -1:
                q.b("SmsDeliveryReceiver", "Sms delivery result: OK");
                return;
            case 0:
                q.b("SmsDeliveryReceiver", "Sms delivery result: Failed");
                return;
            default:
                return;
        }
    }
}
