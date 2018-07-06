package net.hockeyapp.android.views;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import java.util.ArrayList;

public class AttachmentListView extends ViewGroup {
    static final /* synthetic */ boolean $assertionsDisabled = (!AttachmentListView.class.desiredAssertionStatus());
    private int line_height;

    public AttachmentListView(Context context) {
        super(context);
    }

    public ArrayList<Uri> getAttachments() {
        ArrayList<Uri> arrayList = new ArrayList();
        for (int i = 0; i < getChildCount(); i++) {
            arrayList.add(((AttachmentView) getChildAt(i)).getAttachmentUri());
        }
        return arrayList;
    }

    protected void onMeasure(int i, int i2) {
        int i3 = 0;
        if ($assertionsDisabled || MeasureSpec.getMode(i) != 0) {
            int size = MeasureSpec.getSize(i);
            int childCount = getChildCount();
            int paddingLeft = getPaddingLeft();
            int paddingTop = getPaddingTop();
            int i4 = 0;
            int i5 = 0;
            while (i4 < childCount) {
                int paddingLeft2;
                View childAt = getChildAt(i4);
                AttachmentView attachmentView = (AttachmentView) childAt;
                int effectiveMaxHeight = attachmentView.getEffectiveMaxHeight() + attachmentView.getPaddingTop();
                if (childAt.getVisibility() != 8) {
                    LayoutParams layoutParams = childAt.getLayoutParams();
                    childAt.measure(MeasureSpec.makeMeasureSpec(size, Integer.MIN_VALUE), MeasureSpec.makeMeasureSpec(effectiveMaxHeight, Integer.MIN_VALUE));
                    int measuredWidth = childAt.getMeasuredWidth();
                    i5 = Math.max(i5, childAt.getMeasuredHeight() + layoutParams.height);
                    if (paddingLeft + measuredWidth > size) {
                        paddingLeft2 = getPaddingLeft();
                        i3 = paddingTop + i5;
                    } else {
                        i3 = paddingTop;
                        paddingLeft2 = paddingLeft;
                    }
                    paddingLeft = paddingLeft2 + (layoutParams.width + measuredWidth);
                    paddingLeft2 = i5;
                } else {
                    i3 = paddingTop;
                    paddingLeft2 = i5;
                }
                i4++;
                i5 = paddingLeft2;
                paddingTop = i3;
                i3 = effectiveMaxHeight;
            }
            this.line_height = i5;
            if (MeasureSpec.getMode(i2) == 0) {
                i3 = (paddingTop + i5) + getPaddingBottom();
            } else if (MeasureSpec.getMode(i2) == Integer.MIN_VALUE && (paddingTop + i5) + getPaddingBottom() < r0) {
                i3 = (paddingTop + i5) + getPaddingBottom();
            }
            setMeasuredDimension(size, i3);
            return;
        }
        throw new AssertionError();
    }

    protected LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(1, 1);
    }

    protected boolean checkLayoutParams(LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int childCount = getChildCount();
        int i5 = i3 - i;
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        for (int i6 = 0; i6 < childCount; i6++) {
            View childAt = getChildAt(i6);
            if (childAt.getVisibility() != 8) {
                childAt.invalidate();
                int measuredWidth = childAt.getMeasuredWidth();
                int measuredHeight = childAt.getMeasuredHeight();
                LayoutParams layoutParams = childAt.getLayoutParams();
                if (paddingLeft + measuredWidth > i5) {
                    paddingLeft = getPaddingLeft();
                    paddingTop += this.line_height;
                }
                childAt.layout(paddingLeft, paddingTop, paddingLeft + measuredWidth, measuredHeight + paddingTop);
                paddingLeft += ((AttachmentView) childAt).getGap() + (measuredWidth + layoutParams.width);
            }
        }
    }
}
