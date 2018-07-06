package com.fgmicrotec.mobile.android.fgvoip;

import android.support.v4.app.Fragment;
import com.mavenir.android.common.CallManager;

public abstract class d extends Fragment {
    protected CallManager a = null;

    public abstract void a();

    public abstract void a(boolean z);

    public abstract void b();

    public abstract void b(boolean z);

    public abstract void c();

    public abstract void d();

    public void a(CallManager callManager) {
        this.a = callManager;
    }
}
