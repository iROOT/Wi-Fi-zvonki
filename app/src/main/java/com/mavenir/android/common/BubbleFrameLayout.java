package com.mavenir.android.common;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

public class BubbleFrameLayout extends FrameLayout {
    public BubbleFrameLayout(Context context) {
        super(context);
    }

    public BubbleFrameLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BubbleFrameLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setBackgroundDrawable(new BubbleDrawable(context, attributeSet));
    }
}
