package net.hockeyapp.android;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings.Secure;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import java.io.File;
import java.security.MessageDigest;
import net.hockeyapp.android.e.d;

public class a {
    public static String ANDROID_VERSION = null;
    public static String APP_PACKAGE = null;
    public static String APP_VERSION = null;
    public static String APP_VERSION_NAME = null;
    public static final String BASE_URL = "https://sdk.hockeyapp.net/";
    public static String CRASH_IDENTIFIER = null;
    public static String FILES_PATH = null;
    public static String PHONE_MANUFACTURER = null;
    public static String PHONE_MODEL = null;
    public static final String SDK_NAME = "HockeySDK";
    public static final String SDK_VERSION = "3.6.0";
    public static final String TAG = "HockeyApp";
    public static final int UPDATE_PERMISSIONS_REQUEST = 1;

    public static void loadFromContext(Context context) {
        ANDROID_VERSION = VERSION.RELEASE;
        PHONE_MODEL = Build.MODEL;
        PHONE_MANUFACTURER = Build.MANUFACTURER;
        loadFilesPath(context);
        loadPackageData(context);
        loadCrashIdentifier(context);
    }

    public static File getHockeyAppStorageDir() {
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + "HockeyApp");
        file.mkdirs();
        return file;
    }

    private static void loadFilesPath(Context context) {
        if (context != null) {
            try {
                File filesDir = context.getFilesDir();
                if (filesDir != null) {
                    FILES_PATH = filesDir.getAbsolutePath();
                }
            } catch (Exception e) {
                Log.e("HockeyApp", "Exception thrown when accessing the files dir:");
                e.printStackTrace();
            }
        }
    }

    private static void loadPackageData(Context context) {
        if (context != null) {
            try {
                PackageManager packageManager = context.getPackageManager();
                PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
                APP_PACKAGE = packageInfo.packageName;
                APP_VERSION = "" + packageInfo.versionCode;
                APP_VERSION_NAME = packageInfo.versionName;
                int loadBuildNumber = loadBuildNumber(context, packageManager);
                if (loadBuildNumber != 0 && loadBuildNumber > packageInfo.versionCode) {
                    APP_VERSION = "" + loadBuildNumber;
                }
            } catch (Exception e) {
                Log.e("HockeyApp", "Exception thrown when accessing the package info:");
                e.printStackTrace();
            }
        }
    }

    private static int loadBuildNumber(Context context, PackageManager packageManager) {
        int i = 0;
        try {
            Bundle bundle = packageManager.getApplicationInfo(context.getPackageName(), NotificationCompat.FLAG_HIGH_PRIORITY).metaData;
            if (bundle != null) {
                i = bundle.getInt("buildNumber", 0);
            }
        } catch (Exception e) {
            Log.e("HockeyApp", "Exception thrown when accessing the application info:");
            e.printStackTrace();
        }
        return i;
    }

    private static void loadCrashIdentifier(Context context) {
        String string = Secure.getString(context.getContentResolver(), "android_id");
        if (APP_PACKAGE != null && string != null) {
            string = APP_PACKAGE + ":" + string + ":" + createSalt(context);
            try {
                MessageDigest instance = MessageDigest.getInstance("SHA-1");
                byte[] bytes = string.getBytes(d.DEFAULT_CHARSET);
                instance.update(bytes, 0, bytes.length);
                CRASH_IDENTIFIER = bytesToHex(instance.digest());
            } catch (Throwable th) {
            }
        }
    }

    private static String createSalt(Context context) {
        String str = "HA" + (Build.BOARD.length() % 10) + (Build.BRAND.length() % 10) + (Build.CPU_ABI.length() % 10) + (Build.PRODUCT.length() % 10);
        String str2 = "";
        if (VERSION.SDK_INT >= 9) {
            try {
                str2 = Build.class.getField("SERIAL").get(null).toString();
            } catch (Throwable th) {
            }
        }
        return str + ":" + str2;
    }

    private static String bytesToHex(byte[] bArr) {
        char[] toCharArray = "0123456789ABCDEF".toCharArray();
        char[] cArr = new char[(bArr.length * 2)];
        for (int i = 0; i < bArr.length; i++) {
            int i2 = bArr[i] & 255;
            cArr[i * 2] = toCharArray[i2 >>> 4];
            cArr[(i * 2) + 1] = toCharArray[i2 & 15];
        }
        return new String(cArr).replaceAll("(\\w{8})(\\w{4})(\\w{4})(\\w{4})(\\w{12})", "$1-$2-$3-$4-$5");
    }
}
