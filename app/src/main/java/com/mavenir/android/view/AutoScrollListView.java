package com.mavenir.android.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

public class AutoScrollListView extends ListView {
    public AutoScrollListView(Context context) {
        super(context);
    }

    public AutoScrollListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public AutoScrollListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (i2 < i4) {
            setSelection(getCount());
        }
    }
}
