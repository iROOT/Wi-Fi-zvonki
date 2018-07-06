package com.spiritdsp.tsm;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class TextView {
    private Bitmap m_Bitmap = null;
    private int m_TextureHeight = 0;
    private int m_TextureWidth = 0;
    private ByteBuffer m_bitmapBuffer = null;

    public static TextView externalCreateTextView() {
        return new TextView();
    }

    public Object SetText(String[] lines, DisplayMetrics mDisplayMetrics) {
        if (lines == null || mDisplayMetrics == null) {
            return null;
        }
        int TextureHeight = 4;
        int TextureWidth = 0;
        int LineSpace = (int) (1.0f * mDisplayMetrics.density);
        Rect bounds = new Rect();
        Paint textPaint = new Paint();
        textPaint.setStyle(Style.FILL);
        textPaint.setFlags(1);
        textPaint.setAntiAlias(true);
        textPaint.setShadowLayer(1.0f, 1.0f, 1.0f, -12303292);
        textPaint.setTextSize(20.0f);
        textPaint.setColor(-1);
        for (String line : lines) {
            int len = line.length();
            if (len == 0) {
                textPaint.getTextBounds("0", 0, 1, bounds);
            } else {
                textPaint.getTextBounds(line, 0, len, bounds);
            }
            if (TextureWidth < bounds.width()) {
                TextureWidth = bounds.width();
            }
            TextureHeight += bounds.height() + LineSpace;
        }
        TextureWidth = ((TextureWidth + 7) / 8) * 8;
        if (TextureHeight > this.m_TextureHeight || TextureWidth > this.m_TextureWidth || this.m_Bitmap == null) {
            this.m_TextureWidth = TextureWidth;
            this.m_TextureHeight = TextureHeight;
            if (this.m_Bitmap != null) {
                this.m_Bitmap.recycle();
                this.m_Bitmap = null;
            }
            this.m_Bitmap = Bitmap.createBitmap(TextureWidth, TextureHeight, Config.ARGB_8888);
            if (this.m_bitmapBuffer != null) {
                this.m_bitmapBuffer = null;
            }
            this.m_bitmapBuffer = ByteBuffer.allocateDirect((TextureWidth * TextureHeight) * 4);
            this.m_bitmapBuffer.order(ByteOrder.nativeOrder());
        }
        if (this.m_Bitmap == null) {
            Logging.LogDebugPrint(true, "R: warning: text bitmap is null %d %d %d %d", Integer.valueOf(TextureHeight), Integer.valueOf(this.m_TextureHeight), Integer.valueOf(TextureWidth), Integer.valueOf(this.m_TextureWidth));
            return null;
        }
        this.m_Bitmap.eraseColor(0);
        this.m_Bitmap.setDensity(mDisplayMetrics.densityDpi);
        Canvas canvas = new Canvas(this.m_Bitmap);
        int offset = 0;
        for (String line2 : lines) {
            textPaint.getTextBounds(line2, 0, line2.length(), bounds);
            canvas.drawText(line2, 0.0f, (float) (bounds.height() + offset), textPaint);
            offset += bounds.height() + LineSpace;
        }
        this.m_bitmapBuffer.clear();
        this.m_Bitmap.copyPixelsToBuffer(this.m_bitmapBuffer);
        this.m_bitmapBuffer.position(0);
        return this.m_bitmapBuffer;
    }

    public int externalGetWigth() {
        return this.m_TextureWidth;
    }

    public int externalGetHeight() {
        return this.m_TextureHeight;
    }
}
