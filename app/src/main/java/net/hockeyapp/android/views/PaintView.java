package net.hockeyapp.android.views;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;
import java.util.Iterator;
import java.util.Stack;

public class PaintView extends ImageView {
    private static final float TOUCH_TOLERANCE = 4.0f;
    private float mX;
    private float mY;
    private Paint paint = new Paint();
    private Path path = new Path();
    private Stack<Path> paths = new Stack();

    public static int determineOrientation(ContentResolver contentResolver, Uri uri) {
        Options options = new Options();
        options.inJustDecodeBounds = true;
        try {
            BitmapFactory.decodeStream(contentResolver.openInputStream(uri), null, options);
            if (((float) options.outWidth) / ((float) options.outHeight) > 1.0f) {
                return 0;
            }
            return 1;
        } catch (Throwable e) {
            Log.e("HockeyApp", "Unable to determine necessary screen orientation.", e);
            return 1;
        }
    }

    private static int calculateInSampleSize(Options options, int i, int i2) {
        int i3 = options.outHeight;
        int i4 = options.outWidth;
        int i5 = 1;
        if (i3 > i2 || i4 > i) {
            i3 /= 2;
            i4 /= 2;
            while (i3 / i5 > i2 && i4 / i5 > i) {
                i5 *= 2;
            }
        }
        return i5;
    }

    private static Bitmap decodeSampledBitmapFromResource(ContentResolver contentResolver, Uri uri, int i, int i2) {
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(contentResolver.openInputStream(uri), null, options);
        options.inSampleSize = calculateInSampleSize(options, i, i2);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeStream(contentResolver.openInputStream(uri), null, options);
    }

    public PaintView(Context context, Uri uri, int i, int i2) {
        super(context);
        this.paint.setAntiAlias(true);
        this.paint.setDither(true);
        this.paint.setColor(-65536);
        this.paint.setStyle(Style.STROKE);
        this.paint.setStrokeJoin(Join.ROUND);
        this.paint.setStrokeCap(Cap.ROUND);
        this.paint.setStrokeWidth(12.0f);
        new AsyncTask<Object, Void, Bitmap>() {
            protected void onPreExecute() {
                PaintView.this.setAdjustViewBounds(true);
            }

            protected Bitmap doInBackground(Object... objArr) {
                try {
                    return PaintView.decodeSampledBitmapFromResource(((Context) objArr[0]).getContentResolver(), (Uri) objArr[1], ((Integer) objArr[2]).intValue(), ((Integer) objArr[3]).intValue());
                } catch (Throwable e) {
                    Log.e("HockeyApp", "Could not load image into ImageView.", e);
                    return null;
                }
            }

            protected void onPostExecute(Bitmap bitmap) {
                if (bitmap != null) {
                    PaintView.this.setImageBitmap(bitmap);
                }
            }
        }.execute(new Object[]{context, uri, Integer.valueOf(i), Integer.valueOf(i2)});
    }

    public void clearImage() {
        this.paths.clear();
        invalidate();
    }

    public void undo() {
        if (!this.paths.empty()) {
            this.paths.pop();
            invalidate();
        }
    }

    public boolean isClear() {
        return this.paths.empty();
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Iterator it = this.paths.iterator();
        while (it.hasNext()) {
            canvas.drawPath((Path) it.next(), this.paint);
        }
        canvas.drawPath(this.path, this.paint);
    }

    private void touchStart(float f, float f2) {
        this.path.reset();
        this.path.moveTo(f, f2);
        this.mX = f;
        this.mY = f2;
    }

    private void touchMove(float f, float f2) {
        float abs = Math.abs(f - this.mX);
        float abs2 = Math.abs(f2 - this.mY);
        if (abs >= TOUCH_TOLERANCE || abs2 >= TOUCH_TOLERANCE) {
            this.path.quadTo(this.mX, this.mY, (this.mX + f) / 2.0f, (this.mY + f2) / 2.0f);
            this.mX = f;
            this.mY = f2;
        }
    }

    private void touchUp() {
        this.path.lineTo(this.mX, this.mY);
        this.paths.push(this.path);
        this.path = new Path();
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        switch (motionEvent.getAction()) {
            case 0:
                touchStart(x, y);
                invalidate();
                break;
            case 1:
                touchUp();
                invalidate();
                break;
            case 2:
                touchMove(x, y);
                invalidate();
                break;
        }
        return true;
    }
}
