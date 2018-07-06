package com.mavenir.android.b.a;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import com.mavenir.android.common.w;
import com.spiritdsp.tsm.d;
import com.spiritdsp.tsm.e;

public class a extends w {
    private static d b = null;
    private String a = "TAG";

    public void b(Activity activity) {
        if (b == null) {
            Log.e(this.a, "mTSM = TSM_factory.createTSM(this)");
            b = e.a(activity);
            b.setOption(com.spiritdsp.tsm.d.a.EnableAudioVolumeObservation, Boolean.valueOf(true));
        }
        super.b(activity);
    }

    public void b() {
        b = null;
    }

    public void a(Activity activity) {
        super.a(activity);
        if (b != null) {
            b.setContext(activity);
        } else {
            b(activity);
        }
    }

    public View c(Activity activity) {
        super.c(activity);
        if (b == null) {
            b(activity);
        }
        if (b == null) {
            return null;
        }
        return b.createVideoView(activity, new int[0]);
    }

    public boolean c() {
        return com.fgmicrotec.mobile.android.fgvoipcommon.e.a();
    }
}
