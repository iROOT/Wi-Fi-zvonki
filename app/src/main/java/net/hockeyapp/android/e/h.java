package net.hockeyapp.android.e;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Notification;
import android.app.Notification.Builder;
import android.app.PendingIntent;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.Patterns;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class h {
    public static final int APP_IDENTIFIER_LENGTH = 32;
    public static final String APP_IDENTIFIER_PATTERN = "[0-9a-f]+";
    public static final String LOG_IDENTIFIER = "HockeyApp";
    public static final String PREFS_FEEDBACK_TOKEN = "net.hockeyapp.android.prefs_feedback_token";
    public static final String PREFS_KEY_FEEDBACK_TOKEN = "net.hockeyapp.android.prefs_key_feedback_token";
    public static final String PREFS_KEY_NAME_EMAIL_SUBJECT = "net.hockeyapp.android.prefs_key_name_email";
    public static final String PREFS_NAME_EMAIL_SUBJECT = "net.hockeyapp.android.prefs_name_email";
    private static final Pattern appIdentifierPattern = Pattern.compile(APP_IDENTIFIER_PATTERN, 2);

    public static String encodeParam(String str) {
        try {
            return URLEncoder.encode(str, d.DEFAULT_CHARSET);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }

    @TargetApi(8)
    public static final boolean isValidEmail(String str) {
        if (VERSION.SDK_INT >= 8) {
            if (TextUtils.isEmpty(str) || !Patterns.EMAIL_ADDRESS.matcher(str).matches()) {
                return false;
            }
            return true;
        } else if (TextUtils.isEmpty(str)) {
            return false;
        } else {
            return true;
        }
    }

    @SuppressLint({"NewApi"})
    public static Boolean fragmentsSupported() {
        try {
            boolean z;
            if (VERSION.SDK_INT < 11 || !classExists("android.app.Fragment")) {
                z = false;
            } else {
                z = true;
            }
            return Boolean.valueOf(z);
        } catch (NoClassDefFoundError e) {
            return Boolean.valueOf(false);
        }
    }

    public static Boolean runsOnTablet(WeakReference<Activity> weakReference) {
        if (weakReference != null) {
            Activity activity = (Activity) weakReference.get();
            if (activity != null) {
                Configuration configuration = activity.getResources().getConfiguration();
                boolean z = (configuration.screenLayout & 15) == 3 || (configuration.screenLayout & 15) == 4;
                return Boolean.valueOf(z);
            }
        }
        return Boolean.valueOf(false);
    }

    public static String sanitizeAppIdentifier(String str) {
        if (str == null) {
            throw new IllegalArgumentException("App ID must not be null.");
        }
        String trim = str.trim();
        Matcher matcher = appIdentifierPattern.matcher(trim);
        if (trim.length() != 32) {
            throw new IllegalArgumentException("App ID length must be 32 characters.");
        } else if (matcher.matches()) {
            return trim;
        } else {
            throw new IllegalArgumentException("App ID must match regex pattern /[0-9a-f]+/i");
        }
    }

    public static String getFormString(Map<String, String> map) {
        Iterable arrayList = new ArrayList();
        for (String str : map.keySet()) {
            String str2 = (String) map.get(str);
            String str3 = URLEncoder.encode(str3, d.DEFAULT_CHARSET);
            arrayList.add(str3 + "=" + URLEncoder.encode(str2, d.DEFAULT_CHARSET));
        }
        return TextUtils.join("&", arrayList);
    }

    public static boolean classExists(String str) {
        try {
            return Class.forName(str) != null;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static boolean isNotificationBuilderSupported() {
        return VERSION.SDK_INT >= 11 && classExists("android.app.Notification.Builder");
    }

    public static Notification createNotification(Context context, PendingIntent pendingIntent, String str, String str2, int i) {
        if (isNotificationBuilderSupported()) {
            return buildNotificationWithBuilder(context, pendingIntent, str, str2, i);
        }
        return buildNotificationPreHoneycomb(context, pendingIntent, str, str2, i);
    }

    private static Notification buildNotificationPreHoneycomb(Context context, PendingIntent pendingIntent, String str, String str2, int i) {
        Notification notification = new Notification(i, "", System.currentTimeMillis());
        try {
            notification.getClass().getMethod("setLatestEventInfo", new Class[]{Context.class, CharSequence.class, CharSequence.class, PendingIntent.class}).invoke(notification, new Object[]{context, str, str2, pendingIntent});
        } catch (Exception e) {
        }
        return notification;
    }

    @TargetApi(11)
    private static Notification buildNotificationWithBuilder(Context context, PendingIntent pendingIntent, String str, String str2, int i) {
        Builder smallIcon = new Builder(context).setContentTitle(str).setContentText(str2).setContentIntent(pendingIntent).setSmallIcon(i);
        if (VERSION.SDK_INT < 16) {
            return smallIcon.getNotification();
        }
        return smallIcon.build();
    }
}
