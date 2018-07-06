package com.spiritdsp.tsm;

import android.content.Context;
import android.view.Surface;
import android.widget.FrameLayout;
import com.android.volley.BuildConfig;
import java.lang.reflect.Method;

/* compiled from: SurfaceFactory */
class RenderWrapper {
    private RemoteVideoView mSurface = null;

    public RenderWrapper(Context context, int type, int x, int y, int w, int h) {
        Logging.LogDebugPrint(true, "VV:create local view %d %d,%d %dx%d", Integer.valueOf(type), Integer.valueOf(x), Integer.valueOf(y), Integer.valueOf(w), Integer.valueOf(h));
        this.mSurface = new RemoteVideoView(context, type);
        this.mSurface.setId(102);
    }

    public void Release() {
        Method mSurfaceRelease = null;
        try {
            mSurfaceRelease = Surface.class.getMethod(BuildConfig.BUILD_TYPE, new Class[0]);
        } catch (Exception e) {
        }
        if (this.mSurface != null) {
            this.mSurface.setVisibility(8);
            if (mSurfaceRelease != null) {
                try {
                    mSurfaceRelease.invoke(this.mSurface.getHolder().getSurface(), new Object[0]);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            this.mSurface = null;
        }
    }

    public void Show(boolean show) {
        if (show) {
            this.mSurface.setVisibility(0);
            this.mSurface.onResume();
            return;
        }
        this.mSurface.setVisibility(4);
        this.mSurface.onPause();
    }

    public void AddToView(FrameLayout view) {
        view.addView(this.mSurface);
    }

    public void RemoveFromView(FrameLayout view) {
        view.removeView(this.mSurface);
    }
}
