package com.spiritdsp.tsm;

import android.app.Activity;

public class TSM_factory {
    private static TSM mTSM = null;

    public static TSM createTSM(Activity act) throws NullPointerException {
        if (mTSM != null) {
            return mTSM;
        }
        mTSM = new TSM_impl(act);
        return mTSM;
    }
}
