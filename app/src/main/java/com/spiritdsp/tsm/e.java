package com.spiritdsp.tsm;

import android.app.Activity;

public class e {
    private static d a = null;

    public static d a(Activity activity) {
        if (a != null) {
            return a;
        }
        a = new TSM_impl(activity);
        return a;
    }
}
