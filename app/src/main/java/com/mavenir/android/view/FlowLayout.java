package com.mavenir.android.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;

public class FlowLayout extends ViewGroup {
    static final /* synthetic */ boolean a = (!FlowLayout.class.desiredAssertionStatus());
    private int b;

    public static class a extends LayoutParams {
        public final int a;
        public final int b;

        public a(int i, int i2) {
            super(0, 0);
            this.a = i;
            this.b = i2;
        }
    }

    public int getLineHeight() {
        return this.b;
    }

    public FlowLayout(Context context) {
        super(context);
    }

    public FlowLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onMeasure(int i, int i2) {
        if (a || MeasureSpec.getMode(i) != 0) {
            int makeMeasureSpec;
            int i3;
            int size = (MeasureSpec.getSize(i) - getPaddingLeft()) - getPaddingRight();
            int size2 = (MeasureSpec.getSize(i2) - getPaddingTop()) - getPaddingBottom();
            int childCount = getChildCount();
            int i4 = 0;
            int paddingLeft = getPaddingLeft();
            int paddingTop = getPaddingTop();
            if (MeasureSpec.getMode(i2) == Integer.MIN_VALUE) {
                makeMeasureSpec = MeasureSpec.makeMeasureSpec(size2, Integer.MIN_VALUE);
            } else {
                makeMeasureSpec = MeasureSpec.makeMeasureSpec(0, 0);
            }
            int i5 = 0;
            while (i5 < childCount) {
                View childAt = getChildAt(i5);
                if (childAt.getVisibility() != 8) {
                    a aVar = (a) childAt.getLayoutParams();
                    childAt.measure(MeasureSpec.makeMeasureSpec(size, Integer.MIN_VALUE), makeMeasureSpec);
                    int measuredWidth = childAt.getMeasuredWidth();
                    i4 = Math.max(i4, childAt.getMeasuredHeight() + aVar.b);
                    if (paddingLeft + measuredWidth > size) {
                        paddingLeft = getPaddingLeft();
                        paddingTop += i4;
                    }
                    paddingLeft += aVar.a + measuredWidth;
                    i3 = i4;
                } else {
                    i3 = i4;
                }
                i5++;
                i4 = i3;
            }
            this.b = i4;
            i3 = MeasureSpec.getMode(i2) == 0 ? paddingTop + i4 : (MeasureSpec.getMode(i2) != Integer.MIN_VALUE || paddingTop + i4 >= size2) ? size2 : paddingTop + i4;
            setMeasuredDimension(size, i3);
            return;
        }
        throw new AssertionError();
    }

    protected LayoutParams generateDefaultLayoutParams() {
        return new a(2, 2);
    }

    protected boolean checkLayoutParams(LayoutParams layoutParams) {
        if (layoutParams instanceof a) {
            return true;
        }
        return false;
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int childCount = getChildCount();
        int i5 = i3 - i;
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        for (int i6 = 0; i6 < childCount; i6++) {
            View childAt = getChildAt(i6);
            if (childAt.getVisibility() != 8) {
                int measuredWidth = childAt.getMeasuredWidth();
                int measuredHeight = childAt.getMeasuredHeight();
                a aVar = (a) childAt.getLayoutParams();
                if (paddingLeft + measuredWidth > i5) {
                    paddingLeft = getPaddingLeft();
                    paddingTop += this.b;
                }
                childAt.layout(paddingLeft, paddingTop, paddingLeft + measuredWidth, measuredHeight + paddingTop);
                paddingLeft += aVar.a + measuredWidth;
            }
        }
    }
}
