package net.hockeyapp.android.d;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Locale;
import net.hockeyapp.android.a;
import net.hockeyapp.android.e.d;
import net.hockeyapp.android.e.i;
import net.hockeyapp.android.e.j;
import net.hockeyapp.android.k;
import net.hockeyapp.android.l;
import net.hockeyapp.android.p;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class b extends AsyncTask<Void, String, JSONArray> {
    protected static final String APK = "apk";
    protected static final String INTENT_EXTRA_JSON = "json";
    protected static final String INTENT_EXTRA_URL = "url";
    private static final int MAX_NUMBER_OF_VERSIONS = 25;
    protected String appIdentifier;
    private Context context;
    protected p listener;
    protected Boolean mandatory;
    protected String urlString;
    private long usageTime;

    public b(WeakReference<? extends Context> weakReference, String str) {
        this(weakReference, str, null);
    }

    public b(WeakReference<? extends Context> weakReference, String str, String str2) {
        this(weakReference, str, str2, null);
    }

    public b(WeakReference<? extends Context> weakReference, String str, String str2, p pVar) {
        Context context = null;
        this.urlString = null;
        this.appIdentifier = null;
        this.context = null;
        this.mandatory = Boolean.valueOf(false);
        this.usageTime = 0;
        this.appIdentifier = str2;
        this.urlString = str;
        this.listener = pVar;
        if (weakReference != null) {
            context = (Context) weakReference.get();
        }
        if (context != null) {
            this.context = context.getApplicationContext();
            this.usageTime = l.getUsageTime(context);
            a.loadFromContext(context);
        }
    }

    public void attach(WeakReference<? extends Context> weakReference) {
        Context context = null;
        if (weakReference != null) {
            context = (Context) weakReference.get();
        }
        if (context != null) {
            this.context = context.getApplicationContext();
            a.loadFromContext(context);
        }
    }

    public void detach() {
        this.context = null;
    }

    protected int getVersionCode() {
        return Integer.parseInt(a.APP_VERSION);
    }

    protected JSONArray doInBackground(Void... voidArr) {
        try {
            int versionCode = getVersionCode();
            JSONArray jSONArray = new JSONArray(i.getVersionInfo(this.context));
            if (getCachingEnabled() && findNewVersion(jSONArray, versionCode)) {
                return jSONArray;
            }
            URLConnection createConnection = createConnection(new URL(getURLString(INTENT_EXTRA_JSON)));
            createConnection.connect();
            InputStream bufferedInputStream = new BufferedInputStream(createConnection.getInputStream());
            String convertStreamToString = convertStreamToString(bufferedInputStream);
            bufferedInputStream.close();
            JSONArray jSONArray2 = new JSONArray(convertStreamToString);
            if (findNewVersion(jSONArray2, versionCode)) {
                return limitResponseSize(jSONArray2);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected URLConnection createConnection(URL url) {
        URLConnection openConnection = url.openConnection();
        openConnection.addRequestProperty("User-Agent", "HockeySDK/Android");
        if (VERSION.SDK_INT <= 9) {
            openConnection.setRequestProperty("connection", "close");
        }
        return openConnection;
    }

    private boolean findNewVersion(JSONArray jSONArray, int i) {
        int i2 = 0;
        boolean z = false;
        while (i2 < jSONArray.length()) {
            try {
                Object obj;
                JSONObject jSONObject = jSONArray.getJSONObject(i2);
                if (jSONObject.getInt("version") > i) {
                    obj = 1;
                } else {
                    obj = null;
                }
                Object obj2;
                if (jSONObject.getInt("version") == i && j.isNewerThanLastUpdateTime(this.context, jSONObject.getLong("timestamp"))) {
                    obj2 = 1;
                } else {
                    obj2 = null;
                }
                Object obj3;
                if (j.compareVersionStrings(jSONObject.getString("minimum_os_version"), j.mapGoogleVersion(VERSION.RELEASE)) <= 0) {
                    obj3 = 1;
                } else {
                    obj3 = null;
                }
                if (!((obj == null && obj2 == null) || obj3 == null)) {
                    if (jSONObject.has("mandatory")) {
                        this.mandatory = Boolean.valueOf(this.mandatory.booleanValue() | jSONObject.getBoolean("mandatory"));
                    }
                    z = true;
                }
                i2++;
            } catch (JSONException e) {
                return false;
            }
        }
        return z;
    }

    private JSONArray limitResponseSize(JSONArray jSONArray) {
        JSONArray jSONArray2 = new JSONArray();
        for (int i = 0; i < Math.min(jSONArray.length(), 25); i++) {
            try {
                jSONArray2.put(jSONArray.get(i));
            } catch (JSONException e) {
            }
        }
        return jSONArray2;
    }

    protected void onPostExecute(JSONArray jSONArray) {
        if (jSONArray != null) {
            if (this.listener != null) {
                this.listener.onUpdateAvailable(jSONArray, getURLString(APK));
            }
        } else if (this.listener != null) {
            this.listener.onNoUpdateAvailable();
        }
    }

    protected void cleanUp() {
        this.urlString = null;
        this.appIdentifier = null;
    }

    protected String getURLString(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.urlString);
        stringBuilder.append("api/2/apps/");
        stringBuilder.append(this.appIdentifier != null ? this.appIdentifier : this.context.getPackageName());
        stringBuilder.append("?format=" + str);
        if (Secure.getString(this.context.getContentResolver(), "android_id") != null) {
            stringBuilder.append("&udid=" + encodeParam(Secure.getString(this.context.getContentResolver(), "android_id")));
        }
        stringBuilder.append("&os=Android");
        stringBuilder.append("&os_version=" + encodeParam(a.ANDROID_VERSION));
        stringBuilder.append("&device=" + encodeParam(a.PHONE_MODEL));
        stringBuilder.append("&oem=" + encodeParam(a.PHONE_MANUFACTURER));
        stringBuilder.append("&app_version=" + encodeParam(a.APP_VERSION));
        stringBuilder.append("&sdk=" + encodeParam(a.SDK_NAME));
        stringBuilder.append("&sdk_version=" + encodeParam(a.SDK_VERSION));
        stringBuilder.append("&lang=" + encodeParam(Locale.getDefault().getLanguage()));
        stringBuilder.append("&usage_time=" + this.usageTime);
        return stringBuilder.toString();
    }

    private String encodeParam(String str) {
        try {
            return URLEncoder.encode(str, d.DEFAULT_CHARSET);
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

    protected boolean getCachingEnabled() {
        return true;
    }

    private static String convertStreamToString(InputStream inputStream) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream), k.FEEDBACK_FAILED_TITLE_ID);
        StringBuilder stringBuilder = new StringBuilder();
        while (true) {
            try {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    stringBuilder.append(readLine + "\n");
                } else {
                    try {
                        break;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } catch (IOException e2) {
                e2.printStackTrace();
                try {
                    inputStream.close();
                } catch (IOException e22) {
                    e22.printStackTrace();
                }
            } catch (Throwable th) {
                try {
                    inputStream.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
                throw th;
            }
        }
        inputStream.close();
        return stringBuilder.toString();
    }
}
