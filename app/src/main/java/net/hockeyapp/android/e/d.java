package net.hockeyapp.android.e;

import android.content.Context;
import android.net.Uri;
import android.os.Build.VERSION;
import android.text.TextUtils;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class d {
    public static final String DEFAULT_CHARSET = "UTF-8";
    private static final int DEFAULT_TIMEOUT = 120000;
    private final Map<String, String> mHeaders;
    private g mMultipartEntity;
    private String mRequestBody;
    private String mRequestMethod;
    private int mTimeout = DEFAULT_TIMEOUT;
    private final String mUrlString;

    public d(String str) {
        this.mUrlString = str;
        this.mHeaders = new HashMap();
    }

    public d setRequestMethod(String str) {
        this.mRequestMethod = str;
        return this;
    }

    public d setRequestBody(String str) {
        this.mRequestBody = str;
        return this;
    }

    public d writeFormFields(Map<String, String> map) {
        try {
            String formString = getFormString(map, DEFAULT_CHARSET);
            setHeader("Content-Type", "application/x-www-form-urlencoded");
            setRequestBody(formString);
            return this;
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public d writeMultipartData(Map<String, String> map, Context context, List<Uri> list) {
        try {
            this.mMultipartEntity = new g();
            this.mMultipartEntity.writeFirstBoundaryIfNeeds();
            for (String str : map.keySet()) {
                this.mMultipartEntity.addPart(str, (String) map.get(str));
            }
            for (int i = 0; i < list.size(); i++) {
                boolean z;
                Uri uri = (Uri) list.get(i);
                if (i == list.size() - 1) {
                    z = true;
                } else {
                    z = false;
                }
                InputStream openInputStream = context.getContentResolver().openInputStream(uri);
                this.mMultipartEntity.addPart("attachment" + i, uri.getLastPathSegment(), openInputStream, z);
            }
            this.mMultipartEntity.writeLastBoundaryIfNeeds();
            setHeader("Content-Type", "multipart/form-data; boundary=" + this.mMultipartEntity.getBoundary());
            return this;
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public d setTimeout(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("Timeout has to be positive.");
        }
        this.mTimeout = i;
        return this;
    }

    public d setHeader(String str, String str2) {
        this.mHeaders.put(str, str2);
        return this;
    }

    public d setBasicAuthorization(String str, String str2) {
        setHeader("Authorization", "Basic " + b.encodeToString((str + ":" + str2).getBytes(), 2));
        return this;
    }

    public HttpURLConnection build() {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(this.mUrlString).openConnection();
            httpURLConnection.setConnectTimeout(this.mTimeout);
            httpURLConnection.setReadTimeout(this.mTimeout);
            if (VERSION.SDK_INT <= 9) {
                httpURLConnection.setRequestProperty("Connection", "close");
            }
            if (!TextUtils.isEmpty(this.mRequestMethod)) {
                httpURLConnection.setRequestMethod(this.mRequestMethod);
                if (!TextUtils.isEmpty(this.mRequestBody) || this.mRequestMethod.equalsIgnoreCase("POST") || this.mRequestMethod.equalsIgnoreCase("PUT")) {
                    httpURLConnection.setDoOutput(true);
                }
            }
            for (String str : this.mHeaders.keySet()) {
                httpURLConnection.setRequestProperty(str, (String) this.mHeaders.get(str));
            }
            if (!TextUtils.isEmpty(this.mRequestBody)) {
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(httpURLConnection.getOutputStream(), DEFAULT_CHARSET));
                bufferedWriter.write(this.mRequestBody);
                bufferedWriter.flush();
                bufferedWriter.close();
            }
            if (this.mMultipartEntity != null) {
                httpURLConnection.setRequestProperty("Content-Length", String.valueOf(this.mMultipartEntity.getContentLength()));
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(httpURLConnection.getOutputStream());
                bufferedOutputStream.write(this.mMultipartEntity.getOutputStream().toByteArray());
                bufferedOutputStream.flush();
                bufferedOutputStream.close();
            }
            return httpURLConnection;
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    private static String getFormString(Map<String, String> map, String str) {
        Iterable arrayList = new ArrayList();
        for (String str2 : map.keySet()) {
            String str3 = (String) map.get(str2);
            String str22 = URLEncoder.encode(str22, str);
            arrayList.add(str22 + "=" + URLEncoder.encode(str3, str));
        }
        return TextUtils.join("&", arrayList);
    }
}
