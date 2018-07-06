package net.hockeyapp.android;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask.Status;
import android.text.TextUtils;
import java.lang.ref.WeakReference;
import java.util.Date;
import net.hockeyapp.android.d.b;
import net.hockeyapp.android.d.c;
import net.hockeyapp.android.e.a;
import net.hockeyapp.android.e.h;

public class o {
    private static p lastListener = null;
    private static b updateTask = null;

    public static void register(Activity activity, String str) {
        register(activity, str, true);
    }

    public static void register(Activity activity, String str, boolean z) {
        register(activity, str, null, z);
    }

    public static void register(Activity activity, String str, p pVar) {
        register(activity, a.BASE_URL, str, pVar, true);
    }

    public static void register(Activity activity, String str, p pVar, boolean z) {
        register(activity, a.BASE_URL, str, pVar, z);
    }

    public static void register(Activity activity, String str, String str2, p pVar) {
        register(activity, str, str2, pVar, true);
    }

    public static void register(Activity activity, String str, String str2, p pVar, boolean z) {
        String sanitizeAppIdentifier = h.sanitizeAppIdentifier(str2);
        lastListener = pVar;
        WeakReference weakReference = new WeakReference(activity);
        if ((!h.fragmentsSupported().booleanValue() || !dialogShown(weakReference)) && !checkExpiryDate(weakReference, pVar)) {
            if ((pVar != null && pVar.canUpdateInMarket()) || !installedFromMarket(weakReference)) {
                startUpdateTask(weakReference, str, sanitizeAppIdentifier, pVar, z);
            }
        }
    }

    public static void registerForBackground(Context context, String str, p pVar) {
        registerForBackground(context, a.BASE_URL, str, pVar);
    }

    public static void registerForBackground(Context context, String str, String str2, p pVar) {
        String sanitizeAppIdentifier = h.sanitizeAppIdentifier(str2);
        lastListener = pVar;
        WeakReference weakReference = new WeakReference(context);
        if (!checkExpiryDateForBackground(pVar)) {
            if ((pVar != null && pVar.canUpdateInMarket()) || !installedFromMarket(weakReference)) {
                startUpdateTaskForBackground(weakReference, str, sanitizeAppIdentifier, pVar);
            }
        }
    }

    public static void unregister() {
        if (updateTask != null) {
            updateTask.cancel(true);
            updateTask.detach();
            updateTask = null;
        }
        lastListener = null;
    }

    private static boolean checkExpiryDate(WeakReference<Activity> weakReference, p pVar) {
        boolean z = false;
        boolean checkExpiryDateForBackground = checkExpiryDateForBackground(pVar);
        if (checkExpiryDateForBackground) {
            z = pVar.onBuildExpired();
        }
        if (checkExpiryDateForBackground && z) {
            startExpiryInfoIntent(weakReference);
        }
        return checkExpiryDateForBackground;
    }

    private static boolean checkExpiryDateForBackground(p pVar) {
        if (pVar == null) {
            return false;
        }
        Date expiryDate = pVar.getExpiryDate();
        if (expiryDate == null || new Date().compareTo(expiryDate) <= 0) {
            return false;
        }
        return true;
    }

    private static boolean installedFromMarket(WeakReference<? extends Context> weakReference) {
        Context context = (Context) weakReference.get();
        if (context == null) {
            return false;
        }
        try {
            return !TextUtils.isEmpty(context.getPackageManager().getInstallerPackageName(context.getPackageName()));
        } catch (Throwable th) {
            return false;
        }
    }

    private static void startExpiryInfoIntent(WeakReference<Activity> weakReference) {
        if (weakReference != null) {
            Activity activity = (Activity) weakReference.get();
            if (activity != null) {
                activity.finish();
                Intent intent = new Intent(activity, ExpiryInfoActivity.class);
                intent.addFlags(335544320);
                activity.startActivity(intent);
            }
        }
    }

    private static void startUpdateTask(WeakReference<Activity> weakReference, String str, String str2, p pVar, boolean z) {
        if (updateTask == null || updateTask.getStatus() == Status.FINISHED) {
            updateTask = new c(weakReference, str, str2, pVar, z);
            a.execute(updateTask);
            return;
        }
        updateTask.attach(weakReference);
    }

    private static void startUpdateTaskForBackground(WeakReference<Context> weakReference, String str, String str2, p pVar) {
        if (updateTask == null || updateTask.getStatus() == Status.FINISHED) {
            updateTask = new b(weakReference, str, str2, pVar);
            a.execute(updateTask);
            return;
        }
        updateTask.attach(weakReference);
    }

    @TargetApi(11)
    private static boolean dialogShown(WeakReference<Activity> weakReference) {
        if (weakReference != null) {
            Activity activity = (Activity) weakReference.get();
            if (activity != null) {
                if (activity.getFragmentManager().findFragmentByTag("hockey_update_dialog") != null) {
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    public static p getLastListener() {
        return lastListener;
    }
}
