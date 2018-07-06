package com.fgmicrotec.mobile.android.fgvoip;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class ButtonGridLayout extends ViewGroup {
    private final int a = 3;
    private final int b = 4;
    private int c;
    private int d;
    private int e;
    private int f = 5;
    private int g;
    private int h = 5;
    private int i;
    private int j;
    private int k;

    public ButtonGridLayout(Context context) {
        super(context);
    }

    public ButtonGridLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ButtonGridLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setChildrenBackgroundResource(int i) {
        for (int i2 = 0; i2 < 12; i2++) {
            getChildAt(i2).setBackgroundResource(i);
        }
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5 = 0;
        int i6 = ((i4 - i2) - this.k) + this.g;
        int i7 = 0;
        while (i5 < 4) {
            int i8 = i7;
            i7 = this.e;
            for (int i9 = 0; i9 < 3; i9++) {
                getChildAt(i8).layout(i7, i6, this.c + i7, this.d + i6);
                i7 += this.i;
                i8++;
            }
            i5++;
            i6 += this.j;
            i7 = i8;
        }
    }

    protected void onMeasure(int i, int i2) {
        View childAt = getChildAt(0);
        childAt.measure(0, 0);
        for (int i3 = 1; i3 < getChildCount(); i3++) {
            getChildAt(i3).measure(0, 0);
        }
        this.c = childAt.getMeasuredWidth();
        this.d = childAt.getMeasuredHeight();
        this.i = (this.c + this.e) + this.f;
        this.j = (this.d + this.g) + this.h;
        this.k = this.j * 4;
        setMeasuredDimension(resolveSize(this.i * 3, i), resolveSize(this.k, i2));
    }
}
