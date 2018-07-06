package com.b.a.c;

import android.content.Context;
import android.os.Environment;
import java.io.File;
import java.io.IOException;

public final class e {
    public static File a(Context context) {
        return a(context, true);
    }

    public static File a(Context context, boolean z) {
        File file = null;
        Object externalStorageState;
        try {
            externalStorageState = Environment.getExternalStorageState();
        } catch (NullPointerException e) {
            externalStorageState = "";
        } catch (IncompatibleClassChangeError e2) {
            externalStorageState = "";
        }
        if (z && "mounted".equals(externalStorageState) && d(context)) {
            file = c(context);
        }
        if (file == null) {
            file = context.getCacheDir();
        }
        if (file != null) {
            return file;
        }
        c.c("Can't define system cache directory! '%s' will be used.", "/data/data/" + context.getPackageName() + "/cache/");
        return new File("/data/data/" + context.getPackageName() + "/cache/");
    }

    public static File b(Context context) {
        return a(context, "uil-images");
    }

    public static File a(Context context, String str) {
        File a = a(context);
        File file = new File(a, str);
        return (file.exists() || file.mkdir()) ? file : a;
    }

    private static File c(Context context) {
        File file = new File(new File(new File(new File(Environment.getExternalStorageDirectory(), "Android"), "data"), context.getPackageName()), "cache");
        if (file.exists()) {
            return file;
        }
        if (file.mkdirs()) {
            try {
                new File(file, ".nomedia").createNewFile();
                return file;
            } catch (IOException e) {
                c.b("Can't create \".nomedia\" file in application external cache directory", new Object[0]);
                return file;
            }
        }
        c.c("Unable to create external cache directory", new Object[0]);
        return null;
    }

    private static boolean d(Context context) {
        return context.checkCallingOrSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == 0;
    }
}
