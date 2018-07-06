package com.b.a.b.c;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Matrix.ScaleToFit;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.Drawable;
import com.b.a.b.a.f;

public class b implements a {
    protected final int a;
    protected final int b;

    public static class a extends Drawable {
        protected final float a;
        protected final int b;
        protected final RectF c = new RectF();
        protected final RectF d;
        protected final BitmapShader e;
        protected final Paint f;

        public a(Bitmap bitmap, int i, int i2) {
            this.a = (float) i;
            this.b = i2;
            this.e = new BitmapShader(bitmap, TileMode.CLAMP, TileMode.CLAMP);
            this.d = new RectF((float) i2, (float) i2, (float) (bitmap.getWidth() - i2), (float) (bitmap.getHeight() - i2));
            this.f = new Paint();
            this.f.setAntiAlias(true);
            this.f.setShader(this.e);
            this.f.setFilterBitmap(true);
            this.f.setDither(true);
        }

        protected void onBoundsChange(Rect rect) {
            super.onBoundsChange(rect);
            this.c.set((float) this.b, (float) this.b, (float) (rect.width() - this.b), (float) (rect.height() - this.b));
            Matrix matrix = new Matrix();
            matrix.setRectToRect(this.d, this.c, ScaleToFit.FILL);
            this.e.setLocalMatrix(matrix);
        }

        public void draw(Canvas canvas) {
            canvas.drawRoundRect(this.c, this.a, this.a, this.f);
        }

        public int getOpacity() {
            return -3;
        }

        public void setAlpha(int i) {
            this.f.setAlpha(i);
        }

        public void setColorFilter(ColorFilter colorFilter) {
            this.f.setColorFilter(colorFilter);
        }
    }

    public b(int i) {
        this(i, 0);
    }

    public b(int i, int i2) {
        this.a = i;
        this.b = i2;
    }

    public void a(Bitmap bitmap, com.b.a.b.e.a aVar, f fVar) {
        if (aVar instanceof com.b.a.b.e.b) {
            aVar.a(new a(bitmap, this.a, this.b));
            return;
        }
        throw new IllegalArgumentException("ImageAware should wrap ImageView. ImageViewAware is expected.");
    }
}
