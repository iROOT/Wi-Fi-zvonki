package com.spiritdsp.tsm;

import android.content.Context;
import android.view.Surface;
import android.widget.FrameLayout;
import java.lang.reflect.Method;

class c {
    private RemoteVideoView a = null;

    public c(Context context, int i, int i2, int i3, int i4, int i5) {
        Logging.LogDebugPrint(true, "VV:create local view %d %d,%d %dx%d", Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4), Integer.valueOf(i5));
        this.a = new RemoteVideoView(context, i);
        this.a.setId(102);
    }

    public void a() {
        Method method;
        try {
            method = Surface.class.getMethod("release", new Class[0]);
        } catch (Exception e) {
            method = null;
        }
        if (this.a != null) {
            this.a.setVisibility(8);
            if (method != null) {
                try {
                    method.invoke(this.a.getHolder().getSurface(), new Object[0]);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            this.a = null;
        }
    }

    public void a(FrameLayout frameLayout) {
        frameLayout.addView(this.a);
    }

    public void b(FrameLayout frameLayout) {
        frameLayout.removeView(this.a);
    }
}
