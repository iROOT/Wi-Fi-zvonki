package com.spiritdsp.tsm;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;

public class NetworkActivity extends Activity {
    static final /* synthetic */ boolean a = (!NetworkActivity.class.desiredAssertionStatus());
    private BroadcastReceiver b;

    private void a() {
        ((TelephonyManager) super.getSystemService("phone")).listen(new PhoneStateListener(this) {
            final /* synthetic */ NetworkActivity a;

            {
                this.a = r1;
            }

            public void onSignalStrengthsChanged(SignalStrength signalStrength) {
                super.onSignalStrengthsChanged(signalStrength);
                Logging.LogNativePrint("gsm ss=" + signalStrength.getGsmSignalStrength(), new Object[0]);
            }
        }, 256);
    }

    private void b() {
        final WifiManager wifiManager = (WifiManager) super.getSystemService("wifi");
        if (!wifiManager.isWifiEnabled()) {
            return;
        }
        if (a || this.b == null) {
            this.b = new BroadcastReceiver(this) {
                final /* synthetic */ NetworkActivity b;

                public void onReceive(Context context, Intent intent) {
                    Logging.LogNativePrint("wi-fi rssi=" + wifiManager.getConnectionInfo().getRssi(), new Object[0]);
                }
            };
            super.registerReceiver(this.b, new IntentFilter("android.net.wifi.RSSI_CHANGED"));
            return;
        }
        throw new AssertionError();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        a();
        b();
    }
}
