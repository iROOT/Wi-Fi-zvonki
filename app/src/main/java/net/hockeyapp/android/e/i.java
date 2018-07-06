package net.hockeyapp.android.e;

import android.content.Context;
import android.content.SharedPreferences.Editor;

public class i {
    private static String VERSION_INFO_KEY = "versionInfo";

    public static void setVersionInfo(Context context, String str) {
        if (context != null) {
            Editor edit = context.getSharedPreferences("HockeyApp", 0).edit();
            edit.putString(VERSION_INFO_KEY, str);
            f.applyChanges(edit);
        }
    }

    public static String getVersionInfo(Context context) {
        if (context != null) {
            return context.getSharedPreferences("HockeyApp", 0).getString(VERSION_INFO_KEY, "[]");
        }
        return "[]";
    }
}
