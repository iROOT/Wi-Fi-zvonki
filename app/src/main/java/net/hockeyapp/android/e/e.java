package net.hockeyapp.android.e;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.net.Uri;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class e {
    public static final int ORIENTATION_LANDSCAPE = 1;
    public static final int ORIENTATION_PORTRAIT = 0;

    public static int determineOrientation(File file) {
        Throwable th;
        InputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(file);
            try {
                int determineOrientation = determineOrientation(fileInputStream);
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                return determineOrientation;
            } catch (Throwable th2) {
                th = th2;
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            fileInputStream = null;
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            throw th;
        }
    }

    public static int determineOrientation(Context context, Uri uri) {
        InputStream inputStream = null;
        try {
            inputStream = context.getContentResolver().openInputStream(uri);
            int determineOrientation = determineOrientation(inputStream);
            return determineOrientation;
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

    public static int determineOrientation(InputStream inputStream) {
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(inputStream, null, options);
        if (options.outWidth == -1 || options.outHeight == -1) {
            return 0;
        }
        if (((float) options.outWidth) / ((float) options.outHeight) <= 1.0f) {
            return 0;
        }
        return 1;
    }

    public static Bitmap decodeSampledBitmap(File file, int i, int i2) {
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(file.getAbsolutePath(), options);
        options.inSampleSize = calculateInSampleSize(options, i, i2);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(file.getAbsolutePath(), options);
    }

    public static Bitmap decodeSampledBitmap(Context context, Uri uri, int i, int i2) {
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(context.getContentResolver().openInputStream(uri), null, options);
        options.inSampleSize = calculateInSampleSize(options, i, i2);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeStream(context.getContentResolver().openInputStream(uri), null, options);
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
}
