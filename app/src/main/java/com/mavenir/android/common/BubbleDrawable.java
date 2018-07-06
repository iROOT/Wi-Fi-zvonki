package com.mavenir.android.common;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.FillType;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import com.fgmicrotec.mobile.android.fgmag.VoIP;
import com.fgmicrotec.mobile.android.fgvoip.f.l;

public class BubbleDrawable extends Drawable {
    Paint a;
    float b = 0.0f;
    c c = c.NONE;
    float d = 10.0f;
    float e = 20.0f;
    b f = b.LEFT;
    a g = a.CENTER;
    int h = 0;
    Path i = new Path();
    RectF j = new RectF();

    public enum a {
        CENTER,
        START,
        END
    }

    public enum b {
        LEFT,
        RIGHT
    }

    public enum c {
        NONE,
        STANDARD
    }

    public BubbleDrawable(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, l.BubbleDrawableView);
        try {
            this.a = new Paint();
            this.a.setColor(obtainStyledAttributes.getColor(l.BubbleDrawableView_bubbleColor, 0));
            this.a.setAntiAlias(true);
            this.a.setStyle(Style.FILL);
            this.b = obtainStyledAttributes.getDimension(l.BubbleDrawableView_bubbleCornerRadius, 0.0f);
            this.c = c.values()[obtainStyledAttributes.getInteger(l.BubbleDrawableView_bubbleArrowType, 0)];
            this.d = obtainStyledAttributes.getDimension(l.BubbleDrawableView_bubbleArrowWidth, 0.0f);
            this.e = obtainStyledAttributes.getDimension(l.BubbleDrawableView_bubbleArrowHeight, 0.0f);
            try {
                int i = obtainStyledAttributes.getResources().getDisplayMetrics().densityDpi;
                if (i == 120) {
                    this.e = obtainStyledAttributes.getDimension(l.BubbleDrawableView_bubbleArrowHeightLdpi, this.e);
                } else if (i == 160) {
                    this.e = obtainStyledAttributes.getDimension(l.BubbleDrawableView_bubbleArrowHeightMdpi, this.e);
                } else if (i == 240) {
                    this.e = obtainStyledAttributes.getDimension(l.BubbleDrawableView_bubbleArrowHeightHdpi, this.e);
                } else if (i == 320) {
                    this.e = obtainStyledAttributes.getDimension(l.BubbleDrawableView_bubbleArrowHeightXhdpi, this.e);
                } else if (i == VoIP.REASON_CODE_CALLEE_TEMP_UNAVAILABLE) {
                    this.e = obtainStyledAttributes.getDimension(l.BubbleDrawableView_bubbleArrowHeightXxhdpi, this.e);
                }
            } catch (Throwable th) {
            }
            this.f = b.values()[obtainStyledAttributes.getInteger(l.BubbleDrawableView_bubbleArrowPosition, 0)];
            this.g = a.values()[obtainStyledAttributes.getInteger(l.BubbleDrawableView_bubbleArrowAlign, 0)];
            this.h = obtainStyledAttributes.getDimensionPixelSize(l.BubbleDrawableView_bubbleArrowOffset, 0);
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    public void draw(Canvas canvas) {
        if ((this.a.getColor() >>> 24) != 0) {
            this.j.set(getBounds());
            if (!(this.c == c.NONE || this.d == 0.0f || this.e == 0.0f)) {
                a(canvas);
            }
            b(canvas);
        }
    }

    private void a(Canvas canvas) {
        float floor;
        float floor2;
        float f;
        this.i.reset();
        this.i.setFillType(FillType.EVEN_ODD);
        if (this.f == b.LEFT) {
            floor = ((float) Math.floor((double) this.j.left)) + 0.5f;
            floor2 = ((float) Math.floor((double) (this.d + floor))) + 0.5f;
            this.j.left = (float) Math.floor((double) floor2);
        } else {
            floor2 = ((float) Math.floor((double) Math.round(this.j.right))) + 0.5f;
            floor = ((float) Math.floor((double) (floor2 - this.d))) + 0.5f;
            this.j.right = ((float) Math.floor((double) floor)) + 1.0f;
        }
        float f2 = this.e / 2.0f;
        switch (this.g) {
            case START:
                f = (this.j.top + f2) + ((float) this.h);
                break;
            case END:
                f = (this.j.bottom - f2) + ((float) this.h);
                break;
            default:
                f = (this.j.height() / 2.0f) + ((float) this.h);
                break;
        }
        float floor3 = ((float) Math.floor((double) f)) + 0.5f;
        f = ((float) Math.floor((double) (floor3 - f2))) + 0.5f;
        float floor4 = ((float) Math.floor((double) (f2 + floor3))) + 0.5f;
        if (this.f == b.LEFT) {
            this.i.moveTo(floor2, f);
            this.i.lineTo(floor2, floor4);
            this.i.lineTo(floor, floor3);
        } else {
            this.i.moveTo(floor, f);
            this.i.lineTo(floor, floor4);
            this.i.lineTo(floor2, floor3);
        }
        this.i.close();
        canvas.drawPath(this.i, this.a);
        if (this.b <= 0.0f) {
            return;
        }
        if (this.f == b.LEFT) {
            Canvas canvas2 = canvas;
            canvas2.drawRect(this.j.left, f, (this.b / 2.0f) + this.j.left, floor4, this.a);
            return;
        }
        canvas.drawRect(this.j.right - (this.b / 2.0f), f, this.j.right, floor4, this.a);
    }

    private void b(Canvas canvas) {
        if (this.b == 0.0f) {
            canvas.drawRect(this.j, this.a);
        } else {
            canvas.drawRoundRect(new RectF(this.j), this.b, this.b, this.a);
        }
    }

    public void setAlpha(int i) {
        if (this.a.getAlpha() != i) {
            this.a.setAlpha(i);
            invalidateSelf();
        }
    }

    public void setColorFilter(ColorFilter colorFilter) {
    }

    public void setColorFilter(int i, Mode mode) {
        if (this.a != null) {
            this.a.setColor(i);
        }
    }

    public int getOpacity() {
        switch (this.a.getAlpha()) {
            case 0:
                return -2;
            case 255:
                return -1;
            default:
                return -3;
        }
    }

    public int getIntrinsicWidth() {
        return super.getIntrinsicWidth();
    }

    public int getIntrinsicHeight() {
        return super.getIntrinsicWidth();
    }

    public boolean getPadding(Rect rect) {
        return false;
    }
}
