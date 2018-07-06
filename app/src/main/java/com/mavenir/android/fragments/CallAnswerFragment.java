package com.mavenir.android.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.incallui.GlowPadWrapper;
import com.android.incallui.GlowPadWrapper.a;
import com.fgmicrotec.mobile.android.fgvoip.f.h;
import com.mavenir.android.common.CallManager;
import com.mavenir.android.common.q;

public class CallAnswerFragment extends Fragment implements a {
    private CallManager a;
    private GlowPadWrapper b;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        super.onCreateView(layoutInflater, viewGroup, bundle);
        this.b = (GlowPadWrapper) layoutInflater.inflate(h.call_screen_answer_fragment, viewGroup, false);
        this.b.setAnswerListener(this);
        return this.b;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
    }

    public void onDestroyView() {
        q.a("CallAnswerFragment", "onDestroyView()");
        if (this.b != null) {
            this.b.b();
            this.b = null;
        }
        super.onDestroyView();
    }

    public void a(CallManager callManager) {
        this.a = callManager;
    }

    public void a() {
        this.a.P();
    }

    public void b() {
        this.a.Q();
    }

    public void c() {
        this.a.R();
    }

    public void d() {
        this.a.S();
    }

    public void a(boolean z) {
        getView().setVisibility(z ? 0 : 8);
        q.a("CallAnswerFragment", "Show answer UI: " + z);
        if (z) {
            this.b.a();
        } else {
            this.b.b();
        }
    }

    public void e() {
        this.b.c();
    }
}
