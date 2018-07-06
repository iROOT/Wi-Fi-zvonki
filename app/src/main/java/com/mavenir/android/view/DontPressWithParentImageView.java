package com.mavenir.android.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

public class DontPressWithParentImageView extends ImageView {
    public DontPressWithParentImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setPressed(boolean z) {
        if (!z || !((View) getParent()).isPressed()) {
            super.setPressed(z);
        }
    }
}
