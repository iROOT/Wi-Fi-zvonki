package com.android.incallui.widget.multiwaveview;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;

public class TargetDrawable {
    private static final boolean DEBUG = false;
    public static final int[] STATE_ACTIVE = new int[]{16842910, 16842914};
    public static final int[] STATE_FOCUSED = new int[]{16842910, -16842914, 16842908};
    public static final int[] STATE_INACTIVE = new int[]{16842910, -16842914};
    private static final String TAG = "TargetDrawable";
    private float mAlpha = 1.0f;
    private Drawable mDrawable;
    private boolean mEnabled = true;
    private int mNumDrawables = 1;
    private float mPositionX = 0.0f;
    private float mPositionY = 0.0f;
    private final int mResourceId;
    private float mScaleX = 1.0f;
    private float mScaleY = 1.0f;
    private float mTranslationX = 0.0f;
    private float mTranslationY = 0.0f;

    public TargetDrawable(Resources resources, int i, int i2) {
        this.mResourceId = i;
        setDrawable(resources, i);
        this.mNumDrawables = i2;
    }

    public void setDrawable(Resources resources, int i) {
        Drawable drawable = null;
        Drawable drawable2 = i == 0 ? null : resources.getDrawable(i);
        if (drawable2 != null) {
            drawable = drawable2.mutate();
        }
        this.mDrawable = drawable;
        resizeDrawables();
        setState(STATE_INACTIVE);
    }

    public TargetDrawable(TargetDrawable targetDrawable) {
        this.mResourceId = targetDrawable.mResourceId;
        this.mDrawable = targetDrawable.mDrawable != null ? targetDrawable.mDrawable.mutate() : null;
        resizeDrawables();
        setState(STATE_INACTIVE);
    }

    public void setState(int[] iArr) {
        if (this.mDrawable instanceof StateListDrawable) {
            ((StateListDrawable) this.mDrawable).setState(iArr);
        }
    }

    public boolean isActive() {
        if (!(this.mDrawable instanceof StateListDrawable)) {
            return false;
        }
        int[] state = ((StateListDrawable) this.mDrawable).getState();
        for (int i : state) {
            if (i == 16842908) {
                return true;
            }
        }
        return false;
    }

    public boolean isEnabled() {
        return this.mDrawable != null && this.mEnabled;
    }

    private void resizeDrawables() {
        if (this.mDrawable instanceof StateListDrawable) {
            int i;
            StateListDrawable stateListDrawable = (StateListDrawable) this.mDrawable;
            int i2 = 0;
            int i3 = 0;
            for (i = 0; i < this.mNumDrawables; i++) {
                stateListDrawable.selectDrawable(i);
                Drawable current = stateListDrawable.getCurrent();
                i3 = Math.max(i3, current.getIntrinsicWidth());
                i2 = Math.max(i2, current.getIntrinsicHeight());
            }
            stateListDrawable.setBounds(0, 0, i3, i2);
            for (i = 0; i < this.mNumDrawables; i++) {
                stateListDrawable.selectDrawable(i);
                stateListDrawable.getCurrent().setBounds(0, 0, i3, i2);
            }
        } else if (this.mDrawable != null) {
            this.mDrawable.setBounds(0, 0, this.mDrawable.getIntrinsicWidth(), this.mDrawable.getIntrinsicHeight());
        }
    }

    public void setX(float f) {
        this.mTranslationX = f;
    }

    public void setY(float f) {
        this.mTranslationY = f;
    }

    public void setScaleX(float f) {
        this.mScaleX = f;
    }

    public void setScaleY(float f) {
        this.mScaleY = f;
    }

    public void setAlpha(float f) {
        this.mAlpha = f;
    }

    public float getX() {
        return this.mTranslationX;
    }

    public float getY() {
        return this.mTranslationY;
    }

    public float getScaleX() {
        return this.mScaleX;
    }

    public float getScaleY() {
        return this.mScaleY;
    }

    public float getAlpha() {
        return this.mAlpha;
    }

    public void setPositionX(float f) {
        this.mPositionX = f;
    }

    public void setPositionY(float f) {
        this.mPositionY = f;
    }

    public float getPositionX() {
        return this.mPositionX;
    }

    public float getPositionY() {
        return this.mPositionY;
    }

    public int getWidth() {
        return this.mDrawable != null ? this.mDrawable.getIntrinsicWidth() : 0;
    }

    public int getHeight() {
        return this.mDrawable != null ? this.mDrawable.getIntrinsicHeight() : 0;
    }

    public void draw(Canvas canvas) {
        if (this.mDrawable != null && this.mEnabled) {
            canvas.save(1);
            canvas.scale(this.mScaleX, this.mScaleY, this.mPositionX, this.mPositionY);
            canvas.translate(this.mTranslationX + this.mPositionX, this.mTranslationY + this.mPositionY);
            canvas.translate(((float) getWidth()) * -0.5f, ((float) getHeight()) * -0.5f);
            this.mDrawable.setAlpha(Math.round(this.mAlpha * 255.0f));
            this.mDrawable.draw(canvas);
            canvas.restore();
        }
    }

    public void setEnabled(boolean z) {
        this.mEnabled = z;
    }

    public int getResourceId() {
        return this.mResourceId;
    }
}
