package com.mavenir.android.messaging.provider;

import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;
import com.mavenir.android.common.q;
import com.mavenir.android.messaging.utils.c;

public class b extends ContentObserver {
    private static Context a;

    public b(Context context, Handler handler) {
        super(handler);
        a = context;
    }

    public void onChange(boolean z) {
        super.onChange(z);
        q.a("DraftsObserver", "DraftsObserver: onChange()");
        c.a(a).a();
    }
}
