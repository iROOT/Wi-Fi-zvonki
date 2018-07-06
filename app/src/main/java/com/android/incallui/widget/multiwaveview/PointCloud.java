package com.android.incallui.widget.multiwaveview;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.Log;
import java.util.ArrayList;

public class PointCloud {
    private static final int INNER_POINTS = 8;
    private static final float MAX_POINT_SIZE = 4.0f;
    private static final float MIN_POINT_SIZE = 2.0f;
    private static final float PI = 3.1415927f;
    private static final String TAG = "PointCloud";
    GlowManager glowManager = new GlowManager();
    private float mCenterX;
    private float mCenterY;
    private Drawable mDrawable;
    private float mOuterRadius;
    private Paint mPaint = new Paint();
    private ArrayList<Point> mPointCloud = new ArrayList();
    private float mScale = 1.0f;
    WaveManager waveManager = new WaveManager();

    public class GlowManager {
        private float alpha = 0.0f;
        private float radius = 0.0f;
        private float x;
        private float y;

        public void setX(float f) {
            this.x = f;
        }

        public float getX() {
            return this.x;
        }

        public void setY(float f) {
            this.y = f;
        }

        public float getY() {
            return this.y;
        }

        public void setAlpha(float f) {
            this.alpha = f;
        }

        public float getAlpha() {
            return this.alpha;
        }

        public void setRadius(float f) {
            this.radius = f;
        }

        public float getRadius() {
            return this.radius;
        }
    }

    class Point {
        float radius;
        float x;
        float y;

        public Point(float f, float f2, float f3) {
            this.x = f;
            this.y = f2;
            this.radius = f3;
        }
    }

    public class WaveManager {
        private float alpha = 0.0f;
        private float radius = 50.0f;
        private float width = 200.0f;

        public void setRadius(float f) {
            this.radius = f;
        }

        public float getRadius() {
            return this.radius;
        }

        public void setAlpha(float f) {
            this.alpha = f;
        }

        public float getAlpha() {
            return this.alpha;
        }
    }

    public PointCloud(Drawable drawable) {
        this.mPaint.setFilterBitmap(true);
        this.mPaint.setColor(Color.rgb(255, 255, 255));
        this.mPaint.setAntiAlias(true);
        this.mPaint.setDither(true);
        this.mDrawable = drawable;
        if (this.mDrawable != null) {
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        }
    }

    public void setCenter(float f, float f2) {
        this.mCenterX = f;
        this.mCenterY = f2;
    }

    public void makePointCloud(float f, float f2) {
        if (f == 0.0f) {
            Log.w(TAG, "Must specify an inner radius");
            return;
        }
        this.mOuterRadius = f2;
        this.mPointCloud.clear();
        float f3 = f2 - f;
        float f4 = (6.2831855f * f) / 8.0f;
        int round = Math.round(f3 / f4);
        float f5 = f3 / ((float) round);
        for (int i = 0; i <= round; i++) {
            int i2 = (int) ((6.2831855f * f) / f4);
            float f6 = 1.5707964f;
            float f7 = 6.2831855f / ((float) i2);
            for (int i3 = 0; i3 < i2; i3++) {
                float cos = ((float) Math.cos((double) f6)) * f;
                float sin = ((float) Math.sin((double) f6)) * f;
                f6 += f7;
                this.mPointCloud.add(new Point(cos, sin, f));
            }
            f += f5;
        }
    }

    public void setScale(float f) {
        this.mScale = f;
    }

    public float getScale() {
        return this.mScale;
    }

    private static float hypot(float f, float f2) {
        return (float) Math.sqrt((double) ((f * f) + (f2 * f2)));
    }

    private static float max(float f, float f2) {
        return f > f2 ? f : f2;
    }

    public int getAlphaForPoint(Point point) {
        float f = 0.0f;
        float hypot = hypot(this.glowManager.x - point.x, this.glowManager.y - point.y);
        if (hypot < this.glowManager.radius) {
            hypot = (float) Math.cos((double) ((hypot * 0.7853982f) / this.glowManager.radius));
            hypot = max(0.0f, (float) Math.pow((double) hypot, 10.0d)) * this.glowManager.alpha;
        } else {
            hypot = 0.0f;
        }
        float hypot2 = hypot(point.x, point.y) - this.waveManager.radius;
        if (hypot2 < this.waveManager.width * 0.5f && hypot2 < 0.0f) {
            hypot2 = (float) Math.cos((double) ((hypot2 * 0.7853982f) / this.waveManager.width));
            f = max(0.0f, (float) Math.pow((double) hypot2, 20.0d)) * this.waveManager.alpha;
        }
        return (int) (max(hypot, f) * 255.0f);
    }

    private float interp(float f, float f2, float f3) {
        return ((f2 - f) * f3) + f;
    }

    public void draw(Canvas canvas) {
        ArrayList arrayList = this.mPointCloud;
        canvas.save(1);
        canvas.scale(this.mScale, this.mScale, this.mCenterX, this.mCenterY);
        for (int i = 0; i < arrayList.size(); i++) {
            Point point = (Point) arrayList.get(i);
            float interp = interp(MAX_POINT_SIZE, MIN_POINT_SIZE, point.radius / this.mOuterRadius);
            float f = point.x + this.mCenterX;
            float f2 = point.y + this.mCenterY;
            int alphaForPoint = getAlphaForPoint(point);
            if (alphaForPoint != 0) {
                if (this.mDrawable != null) {
                    canvas.save(1);
                    float intrinsicWidth = ((float) this.mDrawable.getIntrinsicWidth()) * 0.5f;
                    float intrinsicHeight = ((float) this.mDrawable.getIntrinsicHeight()) * 0.5f;
                    interp /= MAX_POINT_SIZE;
                    canvas.scale(interp, interp, f, f2);
                    canvas.translate(f - intrinsicWidth, f2 - intrinsicHeight);
                    this.mDrawable.setAlpha(alphaForPoint);
                    this.mDrawable.draw(canvas);
                    canvas.restore();
                } else {
                    this.mPaint.setAlpha(alphaForPoint);
                    canvas.drawCircle(f, f2, interp, this.mPaint);
                }
            }
        }
        canvas.restore();
    }
}
