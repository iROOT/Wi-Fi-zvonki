package com.mavenir.android.common;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

public class b implements AnimationListener {
    private View a;
    private boolean b;

    public b(View view, boolean z) {
        this.a = view;
        this.b = z;
    }

    public void onAnimationEnd(Animation animation) {
        if (!this.b) {
            this.a.setVisibility(4);
        }
    }

    public void onAnimationRepeat(Animation animation) {
    }

    public void onAnimationStart(Animation animation) {
        if (this.b) {
            this.a.setVisibility(0);
        }
    }
}
