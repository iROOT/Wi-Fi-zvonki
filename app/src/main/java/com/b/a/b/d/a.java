package com.b.a.b.d;

import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Build.VERSION;
import android.provider.ContactsContract.Contacts;
import android.provider.MediaStore.Video.Thumbnails;
import android.webkit.MimeTypeMap;
import com.b.a.c.b;
import com.mavenir.android.vtow.activation.ActivationAdapter;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class a implements b {
    protected final Context a;
    protected final int b;
    protected final int c;

    public a(Context context) {
        this(context, 5000, 20000);
    }

    public a(Context context, int i, int i2) {
        this.a = context.getApplicationContext();
        this.b = i;
        this.c = i2;
    }

    public InputStream a(String str, Object obj) {
        switch (com.b.a.b.d.b.a.a(str)) {
            case HTTP:
            case HTTPS:
                return c(str, obj);
            case FILE:
                return e(str, obj);
            case CONTENT:
                return a_(str, obj);
            case ASSETS:
                return f(str, obj);
            case DRAWABLE:
                return g(str, obj);
            default:
                return b(str, obj);
        }
    }

    protected InputStream c(String str, Object obj) {
        HttpURLConnection d = d(str, obj);
        int i = 0;
        while (d.getResponseCode() / 100 == 3 && i < 5) {
            d = d(d.getHeaderField("Location"), obj);
            i++;
        }
        try {
            Closeable inputStream = d.getInputStream();
            if (a(d)) {
                return new com.b.a.b.a.a(new BufferedInputStream(inputStream, 32768), d.getContentLength());
            }
            b.a(inputStream);
            throw new IOException("Image request failed with response code " + d.getResponseCode());
        } catch (IOException e) {
            b.a(d.getErrorStream());
            throw e;
        }
    }

    protected boolean a(HttpURLConnection httpURLConnection) {
        return httpURLConnection.getResponseCode() == ActivationAdapter.OP_CONFIGURATION_INITIAL;
    }

    protected HttpURLConnection d(String str, Object obj) {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(Uri.encode(str, "@#&=*+-_.,:!?()/~'%")).openConnection();
        httpURLConnection.setConnectTimeout(this.b);
        httpURLConnection.setReadTimeout(this.c);
        return httpURLConnection;
    }

    protected InputStream e(String str, Object obj) {
        String c = com.b.a.b.d.b.a.FILE.c(str);
        if (b(str)) {
            return a(c);
        }
        return new com.b.a.b.a.a(new BufferedInputStream(new FileInputStream(c), 32768), (int) new File(c).length());
    }

    @TargetApi(8)
    private InputStream a(String str) {
        if (VERSION.SDK_INT >= 8) {
            Bitmap createVideoThumbnail = ThumbnailUtils.createVideoThumbnail(str, 2);
            if (createVideoThumbnail != null) {
                OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                createVideoThumbnail.compress(CompressFormat.PNG, 0, byteArrayOutputStream);
                return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            }
        }
        return null;
    }

    protected InputStream a_(String str, Object obj) {
        ContentResolver contentResolver = this.a.getContentResolver();
        Uri parse = Uri.parse(str);
        if (b(parse)) {
            Bitmap thumbnail = Thumbnails.getThumbnail(contentResolver, Long.valueOf(parse.getLastPathSegment()).longValue(), 1, null);
            if (thumbnail != null) {
                OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                thumbnail.compress(CompressFormat.PNG, 0, byteArrayOutputStream);
                return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            }
        } else if (str.startsWith("content://com.android.contacts/")) {
            return a(parse);
        }
        return contentResolver.openInputStream(parse);
    }

    @TargetApi(14)
    protected InputStream a(Uri uri) {
        ContentResolver contentResolver = this.a.getContentResolver();
        if (VERSION.SDK_INT >= 14) {
            return Contacts.openContactPhotoInputStream(contentResolver, uri, true);
        }
        return Contacts.openContactPhotoInputStream(contentResolver, uri);
    }

    protected InputStream f(String str, Object obj) {
        return this.a.getAssets().open(com.b.a.b.d.b.a.ASSETS.c(str));
    }

    protected InputStream g(String str, Object obj) {
        return this.a.getResources().openRawResource(Integer.parseInt(com.b.a.b.d.b.a.DRAWABLE.c(str)));
    }

    protected InputStream b(String str, Object obj) {
        throw new UnsupportedOperationException(String.format("UIL doesn't support scheme(protocol) by default [%s]. You should implement this support yourself (BaseImageDownloader.getStreamFromOtherSource(...))", new Object[]{str}));
    }

    private boolean b(Uri uri) {
        String type = this.a.getContentResolver().getType(uri);
        return type != null && type.startsWith("video/");
    }

    private boolean b(String str) {
        String mimeTypeFromExtension = MimeTypeMap.getSingleton().getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(str));
        return mimeTypeFromExtension != null && mimeTypeFromExtension.startsWith("video/");
    }
}
