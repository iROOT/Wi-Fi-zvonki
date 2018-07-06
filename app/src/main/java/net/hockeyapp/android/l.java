package net.hockeyapp.android;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import net.hockeyapp.android.e.f;

public class l {
    private static final String START_TIME_KEY = "startTime";
    private static final String USAGE_TIME_KEY = "usageTime";

    public static void startUsage(Activity activity) {
        long currentTimeMillis = System.currentTimeMillis();
        if (activity != null) {
            Editor edit = getPreferences(activity).edit();
            edit.putLong(START_TIME_KEY + activity.hashCode(), currentTimeMillis);
            f.applyChanges(edit);
        }
    }

    public static void stopUsage(Activity activity) {
        long currentTimeMillis = System.currentTimeMillis();
        if (activity != null && checkVersion(activity)) {
            SharedPreferences preferences = getPreferences(activity);
            long j = preferences.getLong(START_TIME_KEY + activity.hashCode(), 0);
            long j2 = preferences.getLong(USAGE_TIME_KEY + a.APP_VERSION, 0);
            if (j > 0) {
                currentTimeMillis -= j;
                Editor edit = preferences.edit();
                edit.putLong(USAGE_TIME_KEY + a.APP_VERSION, currentTimeMillis + j2);
                f.applyChanges(edit);
            }
        }
    }

    public static long getUsageTime(Context context) {
        if (checkVersion(context)) {
            return getPreferences(context).getLong(USAGE_TIME_KEY + a.APP_VERSION, 0) / 1000;
        }
        return 0;
    }

    private static boolean checkVersion(Context context) {
        if (a.APP_VERSION == null) {
            a.loadFromContext(context);
            if (a.APP_VERSION == null) {
                return false;
            }
        }
        return true;
    }

    private static SharedPreferences getPreferences(Context context) {
        return context.getSharedPreferences("HockeyApp", 0);
    }
}
