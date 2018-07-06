package com.mavenir.android.common;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.o;
import android.telephony.CellLocation;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.SignalStrength;
import android.telephony.gsm.GsmCellLocation;
import com.fgmicrotec.mobile.android.fgvoip.FgVoIP;
import com.fgmicrotec.mobile.android.fgvoip.e;
import com.mavenir.android.settings.c.l;
import java.lang.reflect.Method;

public class m extends PhoneStateListener {
    private static Context a = null;
    private static int b = 0;
    private static long c = 0;
    private static int d = 0;
    private static int e = 0;

    public m(Context context) {
        a = context;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onCallStateChanged(int r5, java.lang.String r6) {
        /*
        r4 = this;
        r0 = b;
        if (r0 == r5) goto L_0x0040;
    L_0x0004:
        r0 = "";
        if (r5 != 0) goto L_0x004c;
    L_0x0008:
        r0 = "CALL_STATE_IDLE";
    L_0x000a:
        r1 = "DeviceStateListener";
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "Call state changed: ";
        r2 = r2.append(r3);
        r0 = r2.append(r0);
        r2 = ", incoming number: ";
        r0 = r0.append(r2);
        r0 = r0.append(r6);
        r0 = r0.toString();
        com.mavenir.android.common.q.a(r1, r0);
        r0 = b;
        r1 = 2;
        if (r0 != r1) goto L_0x0040;
    L_0x0031:
        if (r5 != 0) goto L_0x0040;
    L_0x0033:
        r0 = java.lang.System.currentTimeMillis();
        r2 = c;
        r0 = r0 - r2;
        r2 = 5000; // 0x1388 float:7.006E-42 double:2.4703E-320;
        r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r0 > 0) goto L_0x0040;
    L_0x0040:
        b = r5;
        r0 = java.lang.System.currentTimeMillis();
        c = r0;
        super.onCallStateChanged(r5, r6);
        return;
    L_0x004c:
        r0 = 1;
        if (r5 != r0) goto L_0x0052;
    L_0x004f:
        r0 = "CALL_STATE_RINGING";
        goto L_0x000a;
    L_0x0052:
        r0 = "CALL_STATE_OFFHOOK";
        goto L_0x000a;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mavenir.android.common.m.onCallStateChanged(int, java.lang.String):void");
    }

    public void onServiceStateChanged(ServiceState serviceState) {
        Intent intent;
        q.a("DeviceStateListener", "onServiceStateChanged(): new state: " + serviceState);
        super.onServiceStateChanged(serviceState);
        l.a(a).a(serviceState.getState());
        l.a(a).b(serviceState.getRoaming());
        if (l.d()) {
            e.a().a(FgVoIP.U().at(), l.a(a).M());
            intent = new Intent();
            intent.setAction("com.mavenir.android.ActionConectivityChange");
            a.sendBroadcast(intent);
        }
        intent = new Intent();
        intent.setAction("com.mavenir.android.ActionConectivityChange");
        o.a(a).a(intent);
    }

    public void onSignalStrengthsChanged(SignalStrength signalStrength) {
        super.onSignalStrengthsChanged(signalStrength);
        int gsmSignalStrength = signalStrength.getGsmSignalStrength();
        int a = a(signalStrength);
        if (!(gsmSignalStrength == d && a == e)) {
            q.a("DeviceStateListener", "onSignalStrengthsChanged(): GSM: " + gsmSignalStrength + ", LTE: " + a);
        }
        l.a(a).c(gsmSignalStrength);
        l.a(a).d(a);
        d = gsmSignalStrength;
        e = a;
    }

    private int a(SignalStrength signalStrength) {
        try {
            for (Method method : SignalStrength.class.getMethods()) {
                if (method.getName().equals("getLteSignalStrength")) {
                    return ((Integer) method.invoke(signalStrength, new Object[0])).intValue();
                }
            }
        } catch (Exception e) {
        }
        return 99;
    }

    public void onCellLocationChanged(CellLocation cellLocation) {
        super.onCellLocationChanged(cellLocation);
        if (cellLocation == null) {
            q.a("DeviceStateListener", "onCellLocationChanged(): " + cellLocation);
        } else if (cellLocation instanceof GsmCellLocation) {
            GsmCellLocation gsmCellLocation = (GsmCellLocation) cellLocation;
            boolean at = FgVoIP.U().at();
            boolean a = CallManager.a();
            int lac = gsmCellLocation.getLac();
            int j = l.j();
            int k = l.k();
            q.a("DeviceStateListener", "onCellLocationChanged(): registered: " + at + ", in call: " + a + ", new LAC: " + lac + ", allowed TAC range: " + j + "-" + k);
            if (!a) {
                if (at && (lac < j || lac > k)) {
                    q.a("DeviceStateListener", "onCellLocationChanged(): TAC out of range, broadcasting...");
                    a.sendBroadcast(new Intent("com.mavenir.android.ActionConectivityChange"));
                } else if (!at && lac >= j && lac <= k) {
                    q.a("DeviceStateListener", "onCellLocationChanged(): TAC back in range, broadcasting...");
                    a.sendBroadcast(new Intent("com.mavenir.android.ActionConectivityChange"));
                }
            }
        } else {
            q.a("DeviceStateListener", "onCellLocationChanged(): " + cellLocation);
        }
    }
}
