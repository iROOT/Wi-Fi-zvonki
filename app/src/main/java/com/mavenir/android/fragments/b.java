package com.mavenir.android.fragments;

import android.support.v4.app.Fragment;
import com.mavenir.android.common.CallManager;

public abstract class b extends Fragment {
    protected CallManager a = null;

    public abstract void a();

    public abstract void a(int i);

    public abstract void a(boolean z, boolean z2, boolean z3);

    public abstract void b();

    public abstract void b(boolean z, boolean z2, boolean z3);

    public abstract void c();

    public void a(CallManager callManager) {
        this.a = callManager;
    }
}
