package com.fgmicrotec.mobile.android.fgvoip;

import android.app.Activity;
import android.os.Bundle;
import com.mavenir.android.common.q;

public class ClearNotificationActivity extends Activity {
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        q.b("ClearNotificationActivity", "Open intent ClearNotificationActivity");
        e.a().h();
        finish();
    }
}
