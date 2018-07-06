package net.hockeyapp.android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import java.util.HashMap;
import java.util.Map;
import net.hockeyapp.android.d.g;
import net.hockeyapp.android.e.a;
import net.hockeyapp.android.e.f;

public class h {
    static final String LOGIN_EXIT_KEY = "net.hockeyapp.android.EXIT";
    public static final int LOGIN_MODE_ANONYMOUS = 0;
    public static final int LOGIN_MODE_EMAIL_ONLY = 1;
    public static final int LOGIN_MODE_EMAIL_PASSWORD = 2;
    public static final int LOGIN_MODE_VALIDATE = 3;
    private static String identifier = null;
    static i listener;
    static Class<?> mainActivity;
    private static int mode;
    private static String secret = null;
    private static String urlString = null;
    private static Handler validateHandler = null;

    public static void register(Context context, String str, String str2, int i, i iVar) {
        listener = iVar;
        register(context, str, str2, i, (Class) null);
    }

    public static void register(Context context, String str, String str2, int i, Class<?> cls) {
        register(context, str, str2, a.BASE_URL, i, cls);
    }

    public static void register(final Context context, String str, String str2, String str3, int i, Class<?> cls) {
        if (context != null) {
            identifier = net.hockeyapp.android.e.h.sanitizeAppIdentifier(str);
            secret = str2;
            urlString = str3;
            mode = i;
            mainActivity = cls;
            if (validateHandler == null) {
                validateHandler = new Handler() {
                    public void handleMessage(Message message) {
                        if (!message.getData().getBoolean("success")) {
                            h.startLoginActivity(context);
                        }
                    }
                };
            }
            a.loadFromContext(context);
        }
    }

    public static void verifyLogin(Activity activity, Intent intent) {
        boolean z = true;
        if (intent != null && intent.getBooleanExtra(LOGIN_EXIT_KEY, false)) {
            activity.finish();
        } else if (activity != null && mode != 0 && mode != 3) {
            SharedPreferences sharedPreferences = activity.getSharedPreferences("net.hockeyapp.android.login", 0);
            if (sharedPreferences.getInt("mode", -1) != mode) {
                f.applyChanges(sharedPreferences.edit().remove("auid").remove("iuid").putInt("mode", mode));
            }
            String string = sharedPreferences.getString("auid", null);
            String string2 = sharedPreferences.getString("iuid", null);
            boolean z2 = string == null && string2 == null;
            boolean z3;
            if (string == null && mode == 2) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (!(string2 == null && mode == 1)) {
                z = false;
            }
            if (z2 || z3 || z) {
                startLoginActivity(activity);
                return;
            }
            Map hashMap = new HashMap();
            if (string != null) {
                hashMap.put("type", "auid");
                hashMap.put("id", string);
            } else if (string2 != null) {
                hashMap.put("type", "iuid");
                hashMap.put("id", string2);
            }
            AsyncTask gVar = new g(activity, validateHandler, getURLString(3), 3, hashMap);
            gVar.setShowProgressDialog(false);
            a.execute(gVar);
        }
    }

    private static void startLoginActivity(Context context) {
        Intent intent = new Intent();
        intent.setFlags(1342177280);
        intent.setClass(context, LoginActivity.class);
        intent.putExtra("url", getURLString(mode));
        intent.putExtra("mode", mode);
        intent.putExtra("secret", secret);
        context.startActivity(intent);
    }

    private static String getURLString(int i) {
        String str = "";
        if (i == 2) {
            str = "authorize";
        } else if (i == 1) {
            str = "check";
        } else if (i == 3) {
            str = "validate";
        }
        return urlString + "api/3/apps/" + identifier + "/identity/" + str;
    }
}
