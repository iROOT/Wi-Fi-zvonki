package com.mavenir.android.common;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.net.Uri.Builder;
import android.provider.ContactsContract.Contacts;
import android.provider.ContactsContract.PhoneLookup;
import com.b.a.b.d.a;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class j extends a {
    public j(Context context) {
        super(context);
    }

    public static Uri a(String str) {
        return new Builder().scheme("contactSmallPhotoPhoneNumber").path(str).build();
    }

    protected InputStream a_(String str, Object obj) {
        ContentResolver contentResolver = this.a.getContentResolver();
        Uri parse = Uri.parse(str);
        if (str.startsWith("content://com.android.contacts/")) {
            return Contacts.openContactPhotoInputStream(contentResolver, parse);
        }
        return contentResolver.openInputStream(parse);
    }

    protected InputStream b(String str, Object obj) {
        Uri parse = Uri.parse(str);
        if ("contactSmallPhotoPhoneNumber".equals(parse.getScheme())) {
            return a(parse, obj);
        }
        if ("videoThumbnail".equals(parse.getScheme())) {
            return b(parse, obj);
        }
        return super.b(str, obj);
    }

    private InputStream a(Uri uri, Object obj) {
        Uri uri2;
        Uri withAppendedPath = Uri.withAppendedPath(PhoneLookup.CONTENT_FILTER_URI, Uri.encode(uri.getPath().substring(1)));
        Cursor query = this.a.getContentResolver().query(withAppendedPath, new String[]{"_id"}, null, null, null);
        if (query == null || !query.moveToFirst() || query.isNull(0)) {
            uri2 = null;
        } else {
            uri2 = ContentUris.withAppendedId(Contacts.CONTENT_URI, query.getLong(0));
        }
        query.close();
        if (uri2 == null) {
            return null;
        }
        return a_(uri2.toString(), obj);
    }

    private InputStream b(Uri uri, Object obj) {
        Bitmap createVideoThumbnail = ThumbnailUtils.createVideoThumbnail(uri.getPath(), 1);
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        createVideoThumbnail.compress(CompressFormat.PNG, 100, byteArrayOutputStream);
        createVideoThumbnail.recycle();
        return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
    }
}
