package com.mavenir.android.common;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

public class InterceptTouchFrameLayout extends FrameLayout {
    private a a = null;

    public interface a {
        void a(MotionEvent motionEvent);
    }

    public InterceptTouchFrameLayout(Context context) {
        super(context);
    }

    public InterceptTouchFrameLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public InterceptTouchFrameLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.a != null && motionEvent.getAction() == 0) {
            this.a.a(motionEvent);
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    public void setOnInterceptedTouchListener(a aVar) {
        this.a = aVar;
    }
}
