package com.mavenir.android.common;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.fgmicrotec.mobile.android.fgvoip.FgVoIP;
import com.fgmicrotec.mobile.android.fgvoip.e;
import com.fgmicrotec.mobile.android.fgvoip.f.k;
import com.fgmicrotec.mobile.android.fgvoipcommon.CallService;
import com.mavenir.android.applog.a;
import com.mavenir.android.settings.c;

public class DeviceStateReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        if (intent == null) {
            return;
        }
        if (intent.getAction().equals("android.net.wifi.RSSI_CHANGED")) {
            if (intent.hasExtra("newRssi")) {
                int intExtra = intent.getIntExtra("newRssi", 0);
                if (!(CallService.p() || context.getResources().getString(k.app_name).equals("Three inTouch") || e.a() == null)) {
                    e.a().a(CallService.m(), intExtra);
                }
                if (FgVoIP.U().am()) {
                    Intent intent2 = new Intent(context, CallService.class);
                    intent2.setAction("com.fgmicrotec.mobile.android.voip.WifiSignalStrengthInd");
                    intent2.putExtra("extra_wifi_signal_strength", intExtra);
                    context.startService(intent2);
                }
            }
        } else if (intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")) {
            boolean booleanExtra = intent.getBooleanExtra("noConnectivity", false);
            if (FgVoIP.U().ai() && booleanExtra) {
                CallService.b(true);
            } else {
                CallService.b(false);
                if (e.a() != null) {
                    e.a().a(CallService.m(), l.a(context).M());
                }
            }
            FgVoIP.U().az();
            if (FgVoIP.U().ai() && c.k.d() > 0) {
                FgVoIP.U().d(!booleanExtra);
                boolean I = l.a(context).I();
                if (!booleanExtra) {
                    a.a(context).c(false);
                } else if (I) {
                    a.a(context).c(true);
                }
                Intent intent3 = new Intent();
                intent3.setAction("com.mavenir.android.ActionConectivityChange");
                context.sendBroadcast(intent3);
            }
        } else if (intent.getAction().equals("android.intent.action.TIMEZONE_CHANGED")) {
            q.a("DeviceStateReceiver", "Time zone changed: " + l.a(context).L());
            a.a(context).f();
        } else if (intent.getAction().equals("android.intent.action.SIM_STATE_CHANGED")) {
            q.a("DeviceStateReceiver", "onReceive(): SIM_STATE_CHANGED");
        }
    }
}
