package com.spiritdsp.tsm;

import android.app.Activity;
import android.os.Build.VERSION;
import android.view.SurfaceHolder.Callback;
import java.util.concurrent.CountDownLatch;

public abstract class CaptureBase implements Callback {
    protected static boolean ASSERTS_ENABLED = true;
    private static final boolean DisableCam2 = true;
    protected static final int SPIRIT_LOG_LEVEL_DEBUG = 8;
    protected static final int SPIRIT_RESULT_BAD_PARAM = -2;
    protected static final int SPIRIT_RESULT_FAIL = -5;
    protected static final int SPIRIT_RESULT_OK = 0;
    protected static boolean isDebug = true;
    protected static Activity mActivity = null;
    private static CaptureBase theCapture = null;
    protected int mCameraNum = -1;
    protected int mCameraRearNum = 0;
    protected int mFramesSinceCameraChange = 0;
    protected int mOutputOrientation = -1;
    protected boolean mReleaseOnSurfaceLost;
    protected int mRequestedHeight = 0;
    protected int mRequestedWidth = 0;
    protected int mRequstedFPS = 15000;
    protected int mUserAddOutputOrientation = 0;
    protected int mUserAddPreviewOrientation = 0;
    protected int mcObject = 0;

    public static native int callbackFromUi(int i, int i2, int i3);

    public abstract int close();

    native void frameCaptured(int i, byte[] bArr, int i2, int i3, int i4, int i5);

    public abstract int getCameraExposureCompensation();

    public abstract int getCameraExposureCompensationMax();

    public abstract int getCameraExposureCompensationMin();

    public abstract float getCameraExposureCompensationStep();

    public abstract int[] getResolutionList();

    public abstract void onActivitySet();

    native void onPreviewSizeChanged(int i, int i2, int i3);

    public abstract void setCameraExposureCompensation(int i);

    public abstract void setCameraNum(int i);

    public abstract void setUserOrient(int i, int i2);

    public abstract int start(int i, int i2, int i3, int i4);

    public abstract int stop();

    native void surfaceLost(int i);

    public static synchronized CaptureBase create() {
        CaptureBase captureBase;
        synchronized (CaptureBase.class) {
            if (theCapture == null) {
                if (VERSION.SDK_INT >= 21) {
                    theCapture = new Capture();
                } else {
                    theCapture = new Capture();
                }
            }
            captureBase = theCapture;
        }
        return captureBase;
    }

    public static int getImplVersion() {
        if (VERSION.SDK_INT >= 21) {
        }
        return 1;
    }

    static void setActivity(Activity activity) {
        if (activity != mActivity) {
            mActivity = activity;
            create().onActivitySet();
            Logging.LogDebugPrint(isDebug, "C: setActivity(" + activity.toString() + ")", new Object[0]);
        }
    }

    public int setInitialParams(int cObject, int releaseMode) {
        boolean z;
        this.mcObject = cObject;
        if (CameraParams.needReleaseOnSurfaceLost || releaseMode != 0) {
            z = true;
        } else {
            z = false;
        }
        this.mReleaseOnSurfaceLost = z;
        return 0;
    }

    static int runOnUiThread(int objPtr, int key, int paramPtr) {
        final int[] res = new int[]{0};
        final CountDownLatch latch = new CountDownLatch(1);
        final int i = objPtr;
        final int i2 = key;
        final int i3 = paramPtr;
        Runnable r = new Runnable() {
            public void run() {
                res[0] = CaptureBase.callbackFromUi(i, i2, i3);
                latch.countDown();
            }
        };
        if (mActivity != null) {
            mActivity.runOnUiThread(r);
        } else {
            r.run();
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return res[0];
    }

    protected static void capAssert(boolean condition, String msg) {
        if (ASSERTS_ENABLED && !condition) {
            Logging.LogDebugPrint(true, "C: assert : " + msg, new Object[0]);
            new Exception().printStackTrace();
        }
    }

    protected static void capAssert(boolean condition) {
        if (ASSERTS_ENABLED && !condition) {
            Logging.LogDebugPrint(true, "C: assert", new Object[0]);
            new Exception().printStackTrace();
        }
    }

    public void setLogLevel(int lvl) {
        if ((lvl & 8) != 0) {
            isDebug = true;
            ASSERTS_ENABLED = true;
        } else {
            isDebug = false;
            ASSERTS_ENABLED = false;
        }
        String str = "C: debug=%s";
        Object[] objArr = new Object[1];
        objArr[0] = lvl != 0 ? "on" : "off";
        Logging.LogPrint(str, objArr);
    }
}
