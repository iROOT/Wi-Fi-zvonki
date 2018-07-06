package com.mavenir.android.messaging.provider;

import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.os.Handler;
import com.fgmicrotec.mobile.android.fgvoip.FgVoIP;
import com.mavenir.android.common.q;

public class e extends ContentObserver {
    private Context a;

    public e(Context context, Handler handler) {
        super(handler);
        this.a = context;
    }

    public void onChange(boolean z) {
        super.onChange(z);
        q.a("MessagesObserver", "onChange(): There was a change in SMS database");
        Intent intent = new Intent();
        intent.setAction("InternalIntents.UpdateSmsNotification");
        this.a.sendBroadcast(intent);
        FgVoIP.U().a(this.a, "com.mavenir.android.action_backup_sms");
    }
}
