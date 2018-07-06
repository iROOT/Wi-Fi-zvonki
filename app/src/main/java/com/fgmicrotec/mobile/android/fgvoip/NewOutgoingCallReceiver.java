package com.fgmicrotec.mobile.android.fgvoip;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneNumberUtils;
import com.fgmicrotec.mobile.android.fgvoip.FgVoIP.a;
import com.mavenir.android.common.l;
import com.mavenir.android.common.q;

public class NewOutgoingCallReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        String resultData = getResultData();
        if (resultData == null) {
            resultData = intent.getStringExtra("android.intent.extra.PHONE_NUMBER");
        }
        if (FgVoIP.U().W()) {
            q.b("NewOutgoingCallReceiver", "internal CS call - routing to CS");
            FgVoIP.U().X();
            return;
        }
        boolean e;
        q.b("NewOutgoingCallReceiver", "onReceive(): calling number: " + resultData);
        boolean z = false;
        if (FgVoIP.U().ad() == a.VToW) {
            e = FgVoIP.U().e(resultData);
            z = FgVoIP.U().f(resultData);
        } else {
            e = PhoneNumberUtils.isEmergencyNumber(resultData);
        }
        if ((FgVoIP.U().x() || !e) && !z) {
            z = l.a(context).q();
            q.b("NewOutgoingCallReceiver", "onReceive(): hasdualsim:   " + z);
            if (resultData != null && resultData.length() > 0 && FgVoIP.U() != null && !z) {
                q.b("NewOutgoingCallReceiver", "onReceive(): time: " + (System.currentTimeMillis() - FgVoIP.V()));
                if (System.currentTimeMillis() - FgVoIP.V() <= 3000) {
                    q.d("NewOutgoingCallReceiver", "onReceive(): closing due to unexpected click-to-dial start!");
                    FgVoIP.U().g();
                    return;
                } else if (FgVoIP.U().i(resultData)) {
                    setResultData(null);
                    return;
                } else {
                    return;
                }
            }
            return;
        }
        String str = "NewOutgoingCallReceiver";
        StringBuilder append = new StringBuilder().append("onReceive(): ");
        if (e) {
            resultData = "Emergency ";
        } else {
            resultData = "Native";
        }
        q.b(str, append.append(resultData).append(" number, routing to CS").toString());
    }
}
