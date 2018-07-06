package com.b.a.a.a.a;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import com.b.a.c.b;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public abstract class a implements com.b.a.a.a.a {
    public static final CompressFormat a = CompressFormat.PNG;
    protected final File b;
    protected final File c;
    protected final com.b.a.a.a.b.a d;
    protected int e = 32768;
    protected CompressFormat f = a;
    protected int g = 100;

    public a(File file, File file2, com.b.a.a.a.b.a aVar) {
        if (file == null) {
            throw new IllegalArgumentException("cacheDir argument must be not null");
        } else if (aVar == null) {
            throw new IllegalArgumentException("fileNameGenerator argument must be not null");
        } else {
            this.b = file;
            this.c = file2;
            this.d = aVar;
        }
    }

    public File a(String str) {
        return b(str);
    }

    public boolean a(String str, InputStream inputStream, com.b.a.c.b.a aVar) {
        Throwable th;
        File b = b(str);
        File file = new File(b.getAbsolutePath() + ".tmp");
        Closeable bufferedOutputStream;
        boolean a;
        try {
            bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file), this.e);
            a = b.a(inputStream, bufferedOutputStream, aVar, this.e);
            try {
                b.a(bufferedOutputStream);
                if (a && !file.renameTo(b)) {
                    a = false;
                }
                if (!a) {
                    file.delete();
                }
                return a;
            } catch (Throwable th2) {
                th = th2;
                a = false;
                if (!a) {
                    file.delete();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            a = false;
            if (a && !file.renameTo(b)) {
                a = false;
            }
            if (a) {
                file.delete();
            }
            throw th;
        }
    }

    public boolean a(String str, Bitmap bitmap) {
        File b = b(str);
        File file = new File(b.getAbsolutePath() + ".tmp");
        Closeable bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file), this.e);
        try {
            boolean compress = bitmap.compress(this.f, this.g, bufferedOutputStream);
            b.a(bufferedOutputStream);
            if (compress && !file.renameTo(b)) {
                compress = false;
            }
            if (!compress) {
                file.delete();
            }
            bitmap.recycle();
            return compress;
        } catch (Throwable th) {
            b.a(bufferedOutputStream);
            file.delete();
        }
    }

    protected File b(String str) {
        String a = this.d.a(str);
        File file = this.b;
        if (!(this.b.exists() || this.b.mkdirs() || this.c == null || (!this.c.exists() && !this.c.mkdirs()))) {
            file = this.c;
        }
        return new File(file, a);
    }
}
