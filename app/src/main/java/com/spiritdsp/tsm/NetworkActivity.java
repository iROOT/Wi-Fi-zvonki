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
    static final /* synthetic */ boolean $assertionsDisabled = (!NetworkActivity.class.desiredAssertionStatus());
    private BroadcastReceiver broadcastReceiver;

    private void ListenTelephonyManager() {
        ((TelephonyManager) super.getSystemService("phone")).listen(new PhoneStateListener() {
            public void onSignalStrengthsChanged(SignalStrength signalStrength) {
                super.onSignalStrengthsChanged(signalStrength);
                Logging.LogNativePrint("gsm ss=" + signalStrength.getGsmSignalStrength(), new Object[0]);
            }
        }, 256);
    }

    private void ListenWifiManager() {
        final WifiManager wifi = (WifiManager) super.getApplicationContext().getSystemService("wifi");
        if (!wifi.isWifiEnabled()) {
            return;
        }
        if ($assertionsDisabled || this.broadcastReceiver == null) {
            this.broadcastReceiver = new BroadcastReceiver() {
                public void onReceive(Context context, Intent intent) {
                    Logging.LogNativePrint("wi-fi rssi=" + wifi.getConnectionInfo().getRssi(), new Object[0]);
                }
            };
            super.registerReceiver(this.broadcastReceiver, new IntentFilter("android.net.wifi.RSSI_CHANGED"));
            return;
        }
        throw new AssertionError();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListenTelephonyManager();
        ListenWifiManager();
    }

    public void onEndAsyncTask() {
        if (this.broadcastReceiver != null) {
            super.unregisterReceiver(this.broadcastReceiver);
            this.broadcastReceiver = null;
        }
    }
}
