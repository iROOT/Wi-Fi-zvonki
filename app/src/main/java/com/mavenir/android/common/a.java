package com.mavenir.android.common;

import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;

public class a implements ActivityLifecycleCallbacks {
    private Activity a = null;

    public Activity a() {
        return this.a;
    }

    public void onActivityCreated(Activity activity, Bundle bundle) {
        a("onActivityCreated()", activity);
    }

    public void onActivityStarted(Activity activity) {
        a("onActivityStarted()", activity);
    }

    public void onActivityResumed(Activity activity) {
        this.a = activity;
        a("onActivityResumed()", activity);
    }

    public void onActivityPaused(Activity activity) {
        if (activity == this.a) {
            this.a = null;
        }
        a("onActivityPaused()", activity);
    }

    public void onActivityStopped(Activity activity) {
        a("onActivityStopped()", activity);
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        a("onActivitySaveInstanceState()", activity);
    }

    public void onActivityDestroyed(Activity activity) {
        a("onActivityDestroyed()", activity);
    }

    private void a(String str, Activity activity) {
    }
}
