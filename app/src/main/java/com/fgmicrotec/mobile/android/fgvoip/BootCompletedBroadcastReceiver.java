package com.fgmicrotec.mobile.android.fgvoip;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BootCompletedBroadcastReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        if ("android.intent.action.BOOT_COMPLETED".equals(intent.getAction())) {
            Intent intent2 = new Intent();
            intent2.setAction("MainTabActions.ActionStartAfterReboot");
            context.sendBroadcast(intent2);
        }
    }
}
