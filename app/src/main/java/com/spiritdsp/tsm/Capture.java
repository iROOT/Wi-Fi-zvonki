package com.spiritdsp.tsm;

import android.app.Activity;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.ErrorCallback;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.PreviewCallback;
import android.hardware.Camera.Size;
import android.os.Build.VERSION;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.ViewGroup.LayoutParams;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.CountDownLatch;

class Capture implements ErrorCallback, PreviewCallback, Callback {
    private static boolean ASSERTS_ENABLED = true;
    private static final int SPIRIT_LOG_LEVEL_DEBUG = 8;
    private static final int SPIRIT_RESULT_BAD_PARAM = -2;
    private static final int SPIRIT_RESULT_FAIL = -5;
    private static final int SPIRIT_RESULT_OK = 0;
    private static boolean isDebug = true;
    private static Activity mActivity = null;
    private static Capture theCapture = null;
    private int mActivityOrientationCode = 1;
    private Method mAddCallbackBuffer = null;
    private Camera mCamera = null;
    private int mCameraCount = 0;
    private int mCameraExposure = 0;
    private Boolean mCameraExposureChanged = Boolean.valueOf(false);
    private int mCameraFrontNum = 1;
    private int mCameraNum = -1;
    private int mCameraRearNum = 0;
    private int mFramesSinceCameraChange = 0;
    private int mFullPicSize = 0;
    private SurfaceHolder mHolder = null;
    private Method mOpenInt = null;
    private int mOpenedHeight = 0;
    private int mOpenedWidth = 0;
    private int mOutputOrientation = -1;
    private int mPreviewOrientation = -1;
    private List<Size> mPreviewSizes = null;
    private boolean mReleaseOnSurfaceLost;
    private int mRequestedHeight = 0;
    private int mRequestedWidth = 0;
    private int mRequstedFPS = 15000;
    private Method mSetPreviewCallbackWithBuffer = null;
    private a mState = a.CREATED;
    private SurfaceView mSurface = null;
    private int mUserAddOutputOrientation = 0;
    private int mUserAddPreviewOrientation = 0;
    private int mcObject = 0;

    enum a {
        CREATED,
        READY_TO_CAPTURE,
        CAPTURING,
        WAITING_FOR_SURFACE
    }

    static native int callbackFromUi(int i, int i2, int i3);

    private native void frameCaptured(int i, byte[] bArr, int i2, int i3, int i4, int i5);

    private native void onPreviewSizeChanged(int i, int i2, int i3);

    private native void surfaceLost(int i);

    static void setActivity(Activity activity) {
        if (mActivity != activity) {
            mActivity = activity;
            Logging.LogDebugPrint(isDebug, "C: setActivity(" + activity.toString() + ")", new Object[0]);
            create().setSurface();
        }
    }

    static int runOnUiThread(int i, int i2, int i3) {
        final int[] iArr = new int[]{0};
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final int i4 = i;
        final int i5 = i2;
        final int i6 = i3;
        Runnable anonymousClass1 = new Runnable() {
            public void run() {
                iArr[0] = Capture.callbackFromUi(i4, i5, i6);
                countDownLatch.countDown();
            }
        };
        if (mActivity != null) {
            mActivity.runOnUiThread(anonymousClass1);
        } else {
            anonymousClass1.run();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return iArr[0];
    }

    static synchronized Capture create() {
        Capture capture;
        synchronized (Capture.class) {
            if (theCapture == null) {
                theCapture = new Capture();
            }
            capAssert(theCapture != null);
            capture = theCapture;
        }
        return capture;
    }

    private void getReflections() {
        try {
            this.mSetPreviewCallbackWithBuffer = Camera.class.getMethod("setPreviewCallbackWithBuffer", new Class[]{PreviewCallback.class});
            this.mAddCallbackBuffer = Camera.class.getMethod("addCallbackBuffer", new Class[]{byte[].class});
        } catch (Exception e) {
            Logging.LogDebugPrint(isDebug, "C: warning: non-fatal exception", new Object[0]);
            e.printStackTrace();
        }
        try {
            this.mOpenInt = Camera.class.getMethod("open", new Class[]{Integer.TYPE});
            this.mReleaseOnSurfaceLost = b.e;
            this.mCameraCount = Camera.getNumberOfCameras();
            Logging.LogDebugPrint(isDebug, "C: cameraCount=" + this.mCameraCount, new Object[0]);
            CameraInfo cameraInfo = new CameraInfo();
            b.a = false;
            b.b = false;
            b.a();
            for (int i = 0; i < this.mCameraCount; i++) {
                Camera.getCameraInfo(i, cameraInfo);
                if (cameraInfo.facing == 1) {
                    b.a = true;
                    this.mCameraFrontNum = i;
                }
                if (cameraInfo.facing == 0) {
                    b.b = true;
                    this.mCameraRearNum = i;
                }
                Logging.LogDebugPrint(isDebug, "C: cam: facing=" + cameraInfo.facing + " orientatin=" + cameraInfo.orientation, new Object[0]);
            }
        } catch (Exception e2) {
            Logging.LogDebugPrint(isDebug, "C: warning: non-fatal exception", new Object[0]);
            e2.printStackTrace();
        }
    }

    private Capture() {
        getReflections();
        setCameraNum(1);
    }

    private void tryOpenFrontCamera_Motorola() {
        try {
            Method declaredMethod = Class.forName("com.motorola.hardware.frontcamera.FrontCamera").getDeclaredMethod("getFrontCamera", (Class[]) null);
            if (declaredMethod != null) {
                this.mCamera = (Camera) declaredMethod.invoke((Object[]) null, (Object[]) null);
            }
        } catch (Exception e) {
        }
    }

    private void tryOpenFrontCamera_HTC() {
        try {
            Class cls = Class.forName("android.hardware.HtcFrontFacingCamera");
            if (cls != null) {
                Method method = cls.getMethod("getCamera", new Class[0]);
                if (method != null) {
                    this.mCamera = (Camera) method.invoke(null, new Object[0]);
                }
            }
        } catch (ClassNotFoundException e) {
            Logging.LogDebugPrint(isDebug, "C: error: ClassNotFoundException: " + e.getLocalizedMessage(), new Object[0]);
        } catch (NoSuchMethodException e2) {
            Logging.LogDebugPrint(isDebug, "C: error: NoSuchMethodException: " + e2.getLocalizedMessage(), new Object[0]);
        } catch (IllegalAccessException e3) {
            Logging.LogDebugPrint(isDebug, "C: error: IllegalAccessException: " + e3.getLocalizedMessage(), new Object[0]);
        } catch (InvocationTargetException e4) {
            Logging.LogDebugPrint(isDebug, "C: error: InvocationTargetException: " + e4.getLocalizedMessage(), new Object[0]);
        } catch (SecurityException e5) {
            Logging.LogDebugPrint(isDebug, "C: error: SecurityException: " + e5.getLocalizedMessage(), new Object[0]);
        } catch (RuntimeException e6) {
            Logging.LogDebugPrint(isDebug, "C: error: RuntimeException " + e6.getLocalizedMessage(), new Object[0]);
        } catch (Throwable th) {
            Logging.LogDebugPrint(isDebug, "C: error: Throwable: " + th.getLocalizedMessage(), new Object[0]);
        }
    }

    private void trySetCamera_setCameraId(int i) {
        try {
            Method method = Camera.class.getMethod("setCameraId", new Class[]{Integer.TYPE});
            if (method != null) {
                method.invoke(null, new Object[]{Integer.valueOf(i)});
            }
        } catch (Throwable th) {
        }
    }

    private void trySetCamera_camera_id(int i) {
        try {
            Parameters parameters = this.mCamera.getParameters();
            String str = parameters.get("camera-id");
            if (str != null && str.length() > 0) {
                parameters.set("camera-id", i + 1);
                this.mCamera.setParameters(parameters);
            }
        } catch (Throwable th) {
            Logging.LogDebugPrint(isDebug, "C: failed to set front camera: " + th.getLocalizedMessage(), new Object[0]);
        }
    }

    private void trySetCamera_camera_sensor(int i) {
        try {
            Parameters parameters = this.mCamera.getParameters();
            String str = parameters.get("camera-sensor");
            if (str != null && str.length() > 0) {
                parameters.set("camera-sensor", i);
                this.mCamera.setParameters(parameters);
            }
        } catch (Throwable th) {
            Logging.LogDebugPrint(isDebug, "C: failed to set front camera: " + th.getLocalizedMessage(), new Object[0]);
        }
    }

    private int tryCamera_exposure_set(int i) {
        int i2;
        Throwable th;
        try {
            Parameters parameters = this.mCamera.getParameters();
            int minExposureCompensation = parameters.getMinExposureCompensation();
            int maxExposureCompensation = parameters.getMaxExposureCompensation();
            if (i < minExposureCompensation) {
                i2 = minExposureCompensation;
            } else {
                i2 = i;
            }
            if (i2 > maxExposureCompensation) {
                i2 = maxExposureCompensation;
            }
            try {
                if (parameters.getExposureCompensation() != i2) {
                    if (i2 < minExposureCompensation || i2 > maxExposureCompensation) {
                        Logging.LogDebugPrint(isDebug, String.format("C: camera exposure compensation is out of range [%d;%d]", new Object[]{Integer.valueOf(minExposureCompensation), Integer.valueOf(maxExposureCompensation)}), new Object[0]);
                    } else {
                        parameters.setExposureCompensation(i2);
                        this.mCamera.setParameters(parameters);
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                Logging.LogDebugPrint(isDebug, "C: failed to set camera exposure compensation: " + th.getLocalizedMessage(), new Object[0]);
                return i2;
            }
        } catch (Throwable th3) {
            th = th3;
            i2 = i;
            Logging.LogDebugPrint(isDebug, "C: failed to set camera exposure compensation: " + th.getLocalizedMessage(), new Object[0]);
            return i2;
        }
        return i2;
    }

    private float[] tryCamera_exposure_get() {
        float[] fArr = new float[]{0.0f, 0.0f, 0.0f, 0.0f};
        try {
            Parameters parameters = this.mCamera.getParameters();
            fArr[0] = (float) parameters.getExposureCompensation();
            fArr[1] = (float) parameters.getMinExposureCompensation();
            fArr[2] = (float) parameters.getMaxExposureCompensation();
            fArr[3] = parameters.getExposureCompensationStep();
        } catch (Throwable th) {
            Logging.LogDebugPrint(isDebug, "C: failed to get camera exposure compensation: " + th.getLocalizedMessage(), new Object[0]);
        }
        return fArr;
    }

    public int setInitialParams(int i, int i2) {
        this.mcObject = i;
        if (b.e) {
            this.mReleaseOnSurfaceLost = true;
        } else if (i2 != 0) {
            this.mReleaseOnSurfaceLost = true;
        } else {
            this.mReleaseOnSurfaceLost = false;
        }
        return 0;
    }

    public boolean open() {
        Logging.LogDebugPrint(isDebug, "C: open, API Level=%d", Integer.valueOf(VERSION.SDK_INT));
        capAssert(this.mCamera == null);
        try {
            if (this.mCamera != null) {
                releaseCamera();
            }
            if (VERSION.SDK_INT >= 9) {
                try {
                    this.mCamera = (Camera) this.mOpenInt.invoke(null, new Object[]{Integer.valueOf(this.mCameraNum)});
                } catch (IllegalAccessException e) {
                    Logging.LogDebugPrint(isDebug, "C: IllegalAccessException: " + e.getLocalizedMessage(), new Object[0]);
                } catch (InvocationTargetException e2) {
                    Logging.LogDebugPrint(isDebug, "C: InvocationTargetException: " + e2.getLocalizedMessage(), new Object[0]);
                } catch (SecurityException e3) {
                    Logging.LogDebugPrint(isDebug, "C: SecurityException: " + e3.getLocalizedMessage(), new Object[0]);
                } catch (RuntimeException e4) {
                    Logging.LogDebugPrint(isDebug, "C: RuntimeException " + e4.getLocalizedMessage(), new Object[0]);
                } catch (Throwable th) {
                    Logging.LogDebugPrint(isDebug, "C: Throwable: " + th.getLocalizedMessage(), new Object[0]);
                }
            }
            if (this.mCameraNum == 1) {
                if (this.mCamera == null) {
                    tryOpenFrontCamera_Motorola();
                }
                if (this.mCamera == null) {
                    tryOpenFrontCamera_HTC();
                }
            }
            if (this.mCamera == null) {
                trySetCamera_setCameraId(this.mCameraNum);
                this.mCamera = Camera.open();
                if (this.mCamera == null) {
                    return false;
                }
                trySetCamera_camera_id(this.mCameraNum);
                trySetCamera_camera_sensor(this.mCameraNum);
            }
            try {
                if (this.mCameraExposureChanged.booleanValue()) {
                    this.mCameraExposure = tryCamera_exposure_set(this.mCameraExposure);
                }
            } catch (Exception e5) {
                Logging.LogDebugPrint(isDebug, "C: error: failed to set camera exposure: " + e5.toString(), new Object[0]);
            }
            try {
                if (this.mHolder != null) {
                    this.mCamera.setPreviewDisplay(this.mHolder);
                }
            } catch (IOException e6) {
                Logging.LogDebugPrint(isDebug, "C: error: setPreviewDisplay failed: " + e6.toString(), new Object[0]);
                e6.printStackTrace();
            }
            return true;
        } catch (Exception e52) {
            Logging.LogDebugPrint(isDebug, "C: error: open camera failed: " + e52.toString(), new Object[0]);
            e52.printStackTrace();
            return false;
        }
    }

    private void releaseCamera() {
        boolean z = false;
        Logging.LogDebugPrint(isDebug, "C: release", new Object[0]);
        if (this.mCamera != null) {
            z = true;
        }
        capAssert(z);
        if (this.mCamera != null) {
            this.mCamera.setPreviewCallback(null);
            this.mCamera.setErrorCallback(null);
            this.mCamera.release();
            this.mCamera = null;
        }
    }

    public int close() {
        Logging.LogDebugPrint(isDebug, "C: close", new Object[0]);
        checkState();
        switch (this.mState) {
            case CAPTURING:
            case WAITING_FOR_SURFACE:
                if (this.mCamera != null) {
                    this.mCamera.stopPreview();
                }
                releaseCamera();
                break;
            case READY_TO_CAPTURE:
                if (this.mCamera != null) {
                    releaseCamera();
                    break;
                }
                break;
        }
        setState(a.CREATED);
        return 0;
    }

    public void setCameraNum(int i) {
        int i2;
        Logging.LogDebugPrint(isDebug, "C: setCameraNum(%d)", Integer.valueOf(i));
        if (i >= 2 || b.a) {
            i2 = i;
        } else {
            i2 = this.mCameraRearNum;
        }
        if (i2 < 2 && !b.b) {
            i2 = this.mCameraFrontNum;
        }
        if (i2 != this.mCameraNum) {
            this.mFramesSinceCameraChange = 0;
            this.mCameraNum = i2;
            switch (this.mState) {
                case CAPTURING:
                    this.mCamera.stopPreview();
                    reopen();
                    if (!startPreview()) {
                        setState(a.READY_TO_CAPTURE);
                        return;
                    }
                    return;
                case WAITING_FOR_SURFACE:
                    reopen();
                    return;
                case READY_TO_CAPTURE:
                    reopen();
                    return;
                default:
                    return;
            }
        }
    }

    public void setCameraExposureCompensation(int i) {
        Logging.LogDebugPrint(isDebug, "C: setCameraExposureCompensation(%d)", Integer.valueOf(i));
        int tryCamera_exposure_set = tryCamera_exposure_set(i);
        if (tryCamera_exposure_set != this.mCameraExposure) {
            this.mCameraExposure = tryCamera_exposure_set;
            this.mCameraExposureChanged = Boolean.valueOf(true);
        }
    }

    public int getCameraExposureCompensation() {
        if (this.mCamera != null) {
            int i = (int) tryCamera_exposure_get()[0];
            if (i != this.mCameraExposure) {
                this.mCameraExposure = i;
                this.mCameraExposureChanged = Boolean.valueOf(true);
            }
        }
        Logging.LogDebugPrint(isDebug, "C: getCameraExposureCompensation(%d)", Integer.valueOf(this.mCameraExposure));
        return this.mCameraExposure;
    }

    public int getCameraExposureCompensationMin() {
        int i;
        if (this.mCamera != null) {
            i = (int) tryCamera_exposure_get()[1];
        } else {
            i = 0;
        }
        Logging.LogDebugPrint(isDebug, "C: getCameraExposureCompensationMin(%d)", Integer.valueOf(i));
        return i;
    }

    public int getCameraExposureCompensationMax() {
        int i;
        if (this.mCamera != null) {
            i = (int) tryCamera_exposure_get()[2];
        } else {
            i = 0;
        }
        Logging.LogDebugPrint(isDebug, "C: getCameraExposureCompensationMax(%d)", Integer.valueOf(i));
        return i;
    }

    public float getCameraExposureCompensationStep() {
        float f = 0.0f;
        if (this.mCamera != null) {
            f = tryCamera_exposure_get()[3];
        }
        Logging.LogDebugPrint(isDebug, "C: getCameraExposureCompensationStep(%f)", Float.valueOf(f));
        return f;
    }

    public int start(int i, int i2, int i3, int i4) {
        int i5 = (this.mRequestedWidth == i && this.mRequestedHeight == i2) ? 0 : 1;
        this.mRequestedWidth = i;
        this.mRequestedHeight = i2;
        if (i4 != 0) {
            this.mRequstedFPS = (i3 * 1000) / i4;
        } else {
            this.mRequstedFPS = 15000;
        }
        Logging.LogDebugPrint(isDebug, "C: start %dx%d @%d", Integer.valueOf(this.mRequestedWidth), Integer.valueOf(this.mRequestedHeight), Integer.valueOf(this.mRequstedFPS));
        checkState();
        switch (this.mState) {
            case CAPTURING:
                if (i5 == 0) {
                    return 0;
                }
                restart();
                return 0;
            case WAITING_FOR_SURFACE:
                if (this.mCamera == null || i5 == 0) {
                    return 0;
                }
                reopen();
                return 0;
            case READY_TO_CAPTURE:
                if (!open()) {
                    return SPIRIT_RESULT_FAIL;
                }
                if (!startPreview()) {
                    return 0;
                }
                setState(a.CAPTURING);
                return 0;
            case CREATED:
                if (this.mHolder != null) {
                    if (!open()) {
                        return SPIRIT_RESULT_FAIL;
                    }
                    if (!startPreview()) {
                        return 0;
                    }
                    setState(a.CAPTURING);
                    return 0;
                } else if (!this.mReleaseOnSurfaceLost && !open()) {
                    return SPIRIT_RESULT_FAIL;
                } else {
                    setState(a.WAITING_FOR_SURFACE);
                    return 0;
                }
            default:
                Logging.LogDebugPrint(isDebug, "C: warning: wrong state (" + this.mState.toString() + ") in start()", new Object[0]);
                return 0;
        }
    }

    public void setUserOrient(int i, int i2) {
        Logging.LogDebugPrint(isDebug, "C: setUserOrient() %d %d", Integer.valueOf(i), Integer.valueOf(i2));
        if (this.mUserAddOutputOrientation != i2 || this.mUserAddPreviewOrientation != i) {
            this.mUserAddOutputOrientation = i2;
            this.mUserAddPreviewOrientation = i;
            if (this.mCamera != null) {
                restart();
            }
        }
    }

    public void setLogLevel(int i) {
        if ((i & 8) != 0) {
            isDebug = true;
            ASSERTS_ENABLED = true;
        } else {
            isDebug = false;
            ASSERTS_ENABLED = false;
        }
        String str = "C: debug=%s";
        Object[] objArr = new Object[1];
        objArr[0] = i != 0 ? "on" : "off";
        Logging.LogPrint(str, objArr);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void configureCamera() {
        /*
        r10 = this;
        r1 = 1;
        r2 = 0;
        r0 = r10.mCamera;
        if (r0 == 0) goto L_0x000f;
    L_0x0006:
        r0 = r1;
    L_0x0007:
        capAssert(r0);
        r0 = r10.mCamera;
        if (r0 != 0) goto L_0x0011;
    L_0x000e:
        return;
    L_0x000f:
        r0 = r2;
        goto L_0x0007;
    L_0x0011:
        r0 = r10.mCamera;	 Catch:{ Throwable -> 0x011e }
        r3 = r0.getParameters();	 Catch:{ Throwable -> 0x011e }
        r4 = r10.mRequestedWidth;	 Catch:{ Throwable -> 0x011e }
        r5 = r10.mRequestedHeight;	 Catch:{ Throwable -> 0x011e }
        r0 = r3.getSupportedPreviewSizes();	 Catch:{ Throwable -> 0x011e }
        r10.mPreviewSizes = r0;	 Catch:{ Throwable -> 0x011e }
        r0 = r10.mPreviewSizes;	 Catch:{ Throwable -> 0x011e }
        if (r0 == 0) goto L_0x0130;
    L_0x0025:
        r0 = r1;
    L_0x0026:
        capAssert(r0);	 Catch:{ Throwable -> 0x011e }
        r0 = r10.mPreviewSizes;	 Catch:{ Throwable -> 0x011e }
        r6 = r10.chooseCaptureSize(r0, r4, r5);	 Catch:{ Throwable -> 0x011e }
        r0 = 0;
        r0 = r6[r0];	 Catch:{ Throwable -> 0x011e }
        if (r0 == 0) goto L_0x0039;
    L_0x0034:
        r0 = 1;
        r0 = r6[r0];	 Catch:{ Throwable -> 0x011e }
        if (r0 != 0) goto L_0x005a;
    L_0x0039:
        r0 = isDebug;	 Catch:{ Throwable -> 0x011e }
        r7 = "C: %dx%d %d' is not supported";
        r8 = 3;
        r8 = new java.lang.Object[r8];	 Catch:{ Throwable -> 0x011e }
        r9 = 0;
        r4 = java.lang.Integer.valueOf(r4);	 Catch:{ Throwable -> 0x011e }
        r8[r9] = r4;	 Catch:{ Throwable -> 0x011e }
        r4 = 1;
        r5 = java.lang.Integer.valueOf(r5);	 Catch:{ Throwable -> 0x011e }
        r8[r4] = r5;	 Catch:{ Throwable -> 0x011e }
        r4 = 2;
        r5 = r10.mOutputOrientation;	 Catch:{ Throwable -> 0x011e }
        r5 = java.lang.Integer.valueOf(r5);	 Catch:{ Throwable -> 0x011e }
        r8[r4] = r5;	 Catch:{ Throwable -> 0x011e }
        com.spiritdsp.tsm.Logging.LogDebugPrint(r0, r7, r8);	 Catch:{ Throwable -> 0x011e }
    L_0x005a:
        r0 = 0;
        r0 = r6[r0];	 Catch:{ Throwable -> 0x011e }
        if (r0 == 0) goto L_0x0133;
    L_0x005f:
        r0 = r1;
    L_0x0060:
        capAssert(r0);	 Catch:{ Throwable -> 0x011e }
        r0 = 1;
        r0 = r6[r0];	 Catch:{ Throwable -> 0x011e }
        if (r0 == 0) goto L_0x0136;
    L_0x0068:
        r0 = r1;
    L_0x0069:
        capAssert(r0);	 Catch:{ Throwable -> 0x011e }
        r0 = isDebug;	 Catch:{ Throwable -> 0x011e }
        r4 = "C: set preview size %dx%d %d'";
        r5 = 3;
        r5 = new java.lang.Object[r5];	 Catch:{ Throwable -> 0x011e }
        r7 = 0;
        r8 = 0;
        r8 = r6[r8];	 Catch:{ Throwable -> 0x011e }
        r8 = java.lang.Integer.valueOf(r8);	 Catch:{ Throwable -> 0x011e }
        r5[r7] = r8;	 Catch:{ Throwable -> 0x011e }
        r7 = 1;
        r8 = 1;
        r8 = r6[r8];	 Catch:{ Throwable -> 0x011e }
        r8 = java.lang.Integer.valueOf(r8);	 Catch:{ Throwable -> 0x011e }
        r5[r7] = r8;	 Catch:{ Throwable -> 0x011e }
        r7 = 2;
        r8 = r10.mPreviewOrientation;	 Catch:{ Throwable -> 0x011e }
        r8 = java.lang.Integer.valueOf(r8);	 Catch:{ Throwable -> 0x011e }
        r5[r7] = r8;	 Catch:{ Throwable -> 0x011e }
        com.spiritdsp.tsm.Logging.LogDebugPrint(r0, r4, r5);	 Catch:{ Throwable -> 0x011e }
        r0 = 0;
        r0 = r6[r0];	 Catch:{ Throwable -> 0x011e }
        r4 = 1;
        r4 = r6[r4];	 Catch:{ Throwable -> 0x011e }
        r3.setPreviewSize(r0, r4);	 Catch:{ Throwable -> 0x011e }
        r0 = r10.mPreviewOrientation;	 Catch:{ Throwable -> 0x011e }
        r4 = 90;
        if (r0 == r4) goto L_0x00a8;
    L_0x00a2:
        r0 = r10.mPreviewOrientation;	 Catch:{ Throwable -> 0x011e }
        r4 = 270; // 0x10e float:3.78E-43 double:1.334E-321;
        if (r0 != r4) goto L_0x0139;
    L_0x00a8:
        r0 = r10.mcObject;	 Catch:{ Throwable -> 0x011e }
        r4 = 1;
        r4 = r6[r4];	 Catch:{ Throwable -> 0x011e }
        r5 = 0;
        r5 = r6[r5];	 Catch:{ Throwable -> 0x011e }
        r10.onPreviewSizeChanged(r0, r4, r5);	 Catch:{ Throwable -> 0x011e }
    L_0x00b3:
        r0 = android.hardware.Camera.Parameters.class;
        r4 = "getSupportedPreviewFpsRange";
        r5 = 0;
        r5 = new java.lang.Class[r5];	 Catch:{ Exception -> 0x0148 }
        r0 = r0.getMethod(r4, r5);	 Catch:{ Exception -> 0x0148 }
        r4 = 0;
        r4 = new java.lang.Object[r4];	 Catch:{ Exception -> 0x0148 }
        r0 = r0.invoke(r3, r4);	 Catch:{ Exception -> 0x0148 }
        r0 = (java.util.List) r0;	 Catch:{ Exception -> 0x0148 }
        r4 = r10.chooseCaptureRate(r0);	 Catch:{ Exception -> 0x0148 }
        if (r4 == 0) goto L_0x0146;
    L_0x00cd:
        r0 = r1;
    L_0x00ce:
        capAssert(r0);	 Catch:{ Exception -> 0x0148 }
        r0 = android.hardware.Camera.Parameters.class;
        r5 = "setPreviewFpsRange";
        r6 = 2;
        r6 = new java.lang.Class[r6];	 Catch:{ Exception -> 0x0148 }
        r7 = 0;
        r8 = java.lang.Integer.TYPE;	 Catch:{ Exception -> 0x0148 }
        r6[r7] = r8;	 Catch:{ Exception -> 0x0148 }
        r7 = 1;
        r8 = java.lang.Integer.TYPE;	 Catch:{ Exception -> 0x0148 }
        r6[r7] = r8;	 Catch:{ Exception -> 0x0148 }
        r0 = r0.getMethod(r5, r6);	 Catch:{ Exception -> 0x0148 }
        r5 = 2;
        r5 = new java.lang.Object[r5];	 Catch:{ Exception -> 0x0148 }
        r6 = 0;
        r7 = 0;
        r7 = r4[r7];	 Catch:{ Exception -> 0x0148 }
        r7 = java.lang.Integer.valueOf(r7);	 Catch:{ Exception -> 0x0148 }
        r5[r6] = r7;	 Catch:{ Exception -> 0x0148 }
        r6 = 1;
        r7 = 1;
        r4 = r4[r7];	 Catch:{ Exception -> 0x0148 }
        r4 = java.lang.Integer.valueOf(r4);	 Catch:{ Exception -> 0x0148 }
        r5[r6] = r4;	 Catch:{ Exception -> 0x0148 }
        r0.invoke(r3, r5);	 Catch:{ Exception -> 0x0148 }
    L_0x0100:
        r0 = r10.mCamera;	 Catch:{ Throwable -> 0x011e }
        r0.setParameters(r3);	 Catch:{ Throwable -> 0x011e }
        r0 = "auto";
        r3.setAntibanding(r0);	 Catch:{ Exception -> 0x0111 }
        r0 = r10.mCamera;	 Catch:{ Exception -> 0x0111 }
        r0.setParameters(r3);	 Catch:{ Exception -> 0x0111 }
        goto L_0x000e;
    L_0x0111:
        r0 = move-exception;
        r0 = isDebug;	 Catch:{ Throwable -> 0x011e }
        r3 = "C: warning: antibanding not supported";
        r4 = 0;
        r4 = new java.lang.Object[r4];	 Catch:{ Throwable -> 0x011e }
        com.spiritdsp.tsm.Logging.LogDebugPrint(r0, r3, r4);	 Catch:{ Throwable -> 0x011e }
        goto L_0x000e;
    L_0x011e:
        r0 = move-exception;
        r3 = isDebug;
        r4 = "C: error: failed to set camera parameters: %s";
        r1 = new java.lang.Object[r1];
        r0 = r0.getMessage();
        r1[r2] = r0;
        com.spiritdsp.tsm.Logging.LogDebugPrint(r3, r4, r1);
        goto L_0x000e;
    L_0x0130:
        r0 = r2;
        goto L_0x0026;
    L_0x0133:
        r0 = r2;
        goto L_0x0060;
    L_0x0136:
        r0 = r2;
        goto L_0x0069;
    L_0x0139:
        r0 = r10.mcObject;	 Catch:{ Throwable -> 0x011e }
        r4 = 0;
        r4 = r6[r4];	 Catch:{ Throwable -> 0x011e }
        r5 = 1;
        r5 = r6[r5];	 Catch:{ Throwable -> 0x011e }
        r10.onPreviewSizeChanged(r0, r4, r5);	 Catch:{ Throwable -> 0x011e }
        goto L_0x00b3;
    L_0x0146:
        r0 = r2;
        goto L_0x00ce;
    L_0x0148:
        r0 = move-exception;
        r0 = isDebug;	 Catch:{ Throwable -> 0x011e }
        r4 = "C: warning: setting rate range is not supported on the device";
        r5 = 0;
        r5 = new java.lang.Object[r5];	 Catch:{ Throwable -> 0x011e }
        com.spiritdsp.tsm.Logging.LogDebugPrint(r0, r4, r5);	 Catch:{ Throwable -> 0x011e }
        goto L_0x0100;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.spiritdsp.tsm.Capture.configureCamera():void");
    }

    private void chooseOrientation() {
        int i;
        int i2;
        boolean z;
        int i3 = this.mCameraNum;
        try {
            CameraInfo cameraInfo = new CameraInfo();
            Camera.getCameraInfo(this.mCameraNum, cameraInfo);
            i3 = cameraInfo.facing;
            i = i3;
            i2 = cameraInfo.orientation;
        } catch (Exception e) {
            Logging.LogDebugPrint(isDebug, "C: error: problem with cameraInfo", new Object[0]);
            e.printStackTrace();
            i = i3;
            i2 = 270;
        }
        if (i2 >= 0) {
            z = true;
        } else {
            z = false;
        }
        capAssert(z);
        this.mActivityOrientationCode = mActivity.getWindowManager().getDefaultDisplay().getRotation();
        switch (this.mActivityOrientationCode) {
            case 0:
                i3 = 0;
                break;
            case 1:
                i3 = 90;
                break;
            case 2:
                i3 = 180;
                break;
            case 3:
                i3 = 270;
                break;
            default:
                i3 = 0;
                break;
        }
        if (i == 1) {
            this.mPreviewOrientation = ((360 - ((i2 + i3) % 360)) + this.mUserAddPreviewOrientation) % 360;
            this.mOutputOrientation = ((i2 + i3) + this.mUserAddPreviewOrientation) % 360;
        } else {
            this.mPreviewOrientation = (((i2 - i3) + this.mUserAddPreviewOrientation) + 360) % 360;
            this.mOutputOrientation = (((i2 - i3) + this.mUserAddPreviewOrientation) + 360) % 360;
        }
        try {
            this.mCamera.setDisplayOrientation(this.mPreviewOrientation);
        } catch (Exception e2) {
            Logging.LogDebugPrint(isDebug, "C: error: problem with mSetDisplayOrientation", new Object[0]);
            e2.printStackTrace();
        }
        Logging.LogDebugPrint(isDebug, "C: orientations: camera natural=%d facing=%d activity=%d preview=%d output=%d user=%d", Integer.valueOf(i2), Integer.valueOf(i), Integer.valueOf(i3), Integer.valueOf(this.mPreviewOrientation), Integer.valueOf(this.mOutputOrientation), Integer.valueOf(this.mUserAddPreviewOrientation));
    }

    public int[] getResolutionList() {
        int i = 1;
        int i2 = 0;
        Logging.LogDebugPrint(isDebug, "C: getResolutionList", new Object[0]);
        if (mActivity == null) {
            return null;
        }
        int i3 = (this.mPreviewSizes == null && this.mCamera == null) ? 1 : 0;
        if (i3 == 0) {
            i = 0;
        } else if (!open()) {
            return new int[0];
        }
        if (this.mPreviewSizes == null) {
            chooseOrientation();
        }
        if (i != 0) {
            releaseCamera();
        }
        if (this.mPreviewSizes == null) {
            return new int[0];
        }
        int[] iArr = new int[(this.mPreviewSizes.size() * 2)];
        while (i2 < iArr.length / 2) {
            iArr[i2 * 2] = ((Size) this.mPreviewSizes.get(i2)).width;
            iArr[(i2 * 2) + 1] = ((Size) this.mPreviewSizes.get(i2)).height;
            i2++;
        }
        return iArr;
    }

    private int[] chooseCaptureSize(List<Size> list, int i, int i2) {
        Logging.LogDebugPrint(isDebug, "C: camera size request (%dx%d)", Integer.valueOf(i), Integer.valueOf(i2));
        Logging.LogDebugPrint(isDebug, "C: camera supports the following preview sizes:", new Object[0]);
        for (Size size : list) {
            Logging.LogDebugPrint(isDebug, "C:  %dx%d %d'", Integer.valueOf(size.width), Integer.valueOf(size.height), Integer.valueOf(this.mOutputOrientation));
        }
        boolean z = 10000 > i || 10000 > i2;
        capAssert(z);
        int[] iArr = new int[]{0, 0};
        int i3 = 0;
        int i4 = 100000000;
        for (Size size2 : list) {
            if (size2.width <= 10000 && size2.width > 176) {
                int i5;
                int abs = Math.abs(size2.width - i) + Math.abs(size2.height - i2);
                if (size2.width >= i && size2.height >= i2) {
                    if (abs < i4 || i3 == 0) {
                        iArr[0] = size2.width;
                        iArr[1] = size2.height;
                        i4 = abs;
                    }
                    i5 = 1;
                    i3 = i4;
                } else if (i3 != 0 || abs >= i4) {
                    i5 = i3;
                    i3 = i4;
                } else {
                    iArr[0] = size2.width;
                    iArr[1] = size2.height;
                    i5 = i3;
                    i3 = abs;
                }
                i4 = i3;
                i3 = i5;
            }
        }
        Logging.LogDebugPrint(isDebug, "C: result %dx%d", Integer.valueOf(iArr[0]), Integer.valueOf(iArr[1]));
        return iArr;
    }

    private int[] chooseCaptureRate(List<int[]> list) {
        int[] iArr = new int[]{1000, 1000};
        for (int[] iArr2 : list) {
            Logging.LogDebugPrint(isDebug, "C: support camera rate: " + iArr2[0] + ".." + iArr2[1], new Object[0]);
            if (iArr2[0] <= this.mRequstedFPS && iArr2[1] >= this.mRequstedFPS) {
                iArr[0] = this.mRequstedFPS;
                iArr[1] = this.mRequstedFPS;
                break;
            } else if (iArr[0] == 1000 || (iArr2[0] >= this.mRequstedFPS && iArr2[0] < iArr[0])) {
                iArr[0] = iArr2[0];
                iArr[1] = iArr2[0];
            }
        }
        Logging.LogDebugPrint(isDebug, "C: rate range choosed: " + iArr[0] + ".." + iArr[1] + " (" + this.mRequstedFPS + " requested)", new Object[0]);
        return iArr;
    }

    private boolean startPreview() {
        capAssert(this.mCamera != null);
        Logging.LogDebugPrint(isDebug, "C: startPreview", new Object[0]);
        if (this.mCamera == null) {
            Logging.LogDebugPrint(isDebug, "C: null camera object", new Object[0]);
            return false;
        }
        Parameters parameters = null;
        try {
            parameters = this.mCamera.getParameters();
        } catch (Exception e) {
            Logging.LogDebugPrint(isDebug, "C: catched exception in Camera.getParameters()", new Object[0]);
        }
        if (parameters == null) {
            reopen();
        }
        try {
            parameters = this.mCamera.getParameters();
        } catch (Exception e2) {
        }
        if (parameters == null) {
            Logging.LogDebugPrint(isDebug, "C: camera in bad state in Camera.getParameters()", new Object[0]);
            return false;
        }
        chooseOrientation();
        configureCamera();
        this.mFramesSinceCameraChange = 0;
        try {
            boolean z;
            Size previewSize = this.mCamera.getParameters().getPreviewSize();
            this.mOpenedWidth = previewSize.width;
            this.mOpenedHeight = previewSize.height;
            if (this.mOpenedHeight != 0) {
                z = true;
            } else {
                z = false;
            }
            capAssert(z);
            if (this.mOpenedWidth != 0) {
                z = true;
            } else {
                z = false;
            }
            capAssert(z);
            this.mFullPicSize = ((this.mOpenedWidth * this.mOpenedHeight) * 3) / 2;
            Logging.LogDebugPrint(isDebug, "C: camera actually opened on (%d, %d)", Integer.valueOf(previewSize.width), Integer.valueOf(previewSize.height));
            setPreviewCallback();
            try {
                this.mCamera.startPreview();
                return true;
            } catch (Exception e3) {
                Logging.LogDebugPrint(isDebug, "C: catched exception in Camera.startPreview()", new Object[0]);
                e3.printStackTrace();
                return false;
            }
        } catch (Exception e4) {
            Logging.LogDebugPrint(isDebug, "C: camera in bad state in Camera.getParameters()", new Object[0]);
            return false;
        }
    }

    private void restart() {
        capAssert(this.mCamera != null);
        Logging.LogDebugPrint(isDebug, "C: restart", new Object[0]);
        if (this.mCamera == null) {
            Logging.LogDebugPrint(isDebug, "C: null camera object", new Object[0]);
            return;
        }
        this.mCamera.stopPreview();
        if (b.d[this.mCameraNum]) {
            reopen();
        }
        startPreview();
    }

    private void reopen() {
        boolean z;
        if (this.mCamera != null) {
            z = true;
        } else {
            z = false;
        }
        capAssert(z);
        Logging.LogDebugPrint(isDebug, "C: reopen", new Object[0]);
        releaseCamera();
        open();
    }

    private void setPreviewCallback() {
        if (this.mSetPreviewCallbackWithBuffer == null || this.mAddCallbackBuffer == null) {
            this.mCamera.setPreviewCallback(this);
        } else {
            try {
                this.mSetPreviewCallbackWithBuffer.invoke(this.mCamera, new Object[]{null});
                for (int i = 0; i < 10; i++) {
                    byte[] bArr = new byte[this.mFullPicSize];
                    this.mAddCallbackBuffer.invoke(this.mCamera, new Object[]{bArr});
                }
                this.mSetPreviewCallbackWithBuffer.invoke(this.mCamera, new Object[]{this});
            } catch (OutOfMemoryError e) {
                Logging.LogDebugPrint(isDebug, "C: error: not enough memory for camera Q - try to use old (< 8) API", new Object[0]);
                this.mAddCallbackBuffer = null;
                this.mSetPreviewCallbackWithBuffer = null;
                this.mCamera.setPreviewCallback(null);
                this.mCamera.setPreviewCallback(this);
            } catch (Exception e2) {
                Logging.LogDebugPrint(isDebug, "C: error: exception in fast camera buffers", new Object[0]);
                e2.printStackTrace();
            }
        }
        this.mCamera.setErrorCallback(this);
    }

    public void onPreviewFrame(byte[] bArr, Camera camera) {
        int i = 1;
        if (this.mState == a.CAPTURING) {
            if (bArr == null) {
                Logging.LogDebugPrint(isDebug, "C: warning: null data in callback", new Object[0]);
                return;
            }
            if (bArr.length != this.mFullPicSize) {
                Logging.LogDebugPrint(isDebug, "C: error: frame size in callback wrong - %d instead of %d", Integer.valueOf(bArr.length), Integer.valueOf(this.mFullPicSize));
            } else {
                if (b.f) {
                    i = CheckBlackFrame(bArr);
                }
                if (i != 0) {
                    frameCaptured(this.mcObject, bArr, this.mOpenedWidth, this.mOpenedHeight, this.mOutputOrientation, 0);
                }
            }
            try {
                if (this.mAddCallbackBuffer != null) {
                    this.mAddCallbackBuffer.invoke(camera, new Object[]{bArr});
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                i = mActivity.getWindowManager().getDefaultDisplay().getRotation();
            } catch (Exception e2) {
                e2.printStackTrace();
                i = 0;
            }
            if (i != this.mActivityOrientationCode) {
                Logging.LogDebugPrint(isDebug, "C: camera Ui orientation changed", new Object[0]);
                if (VERSION.SDK_INT >= 14) {
                    chooseOrientation();
                } else {
                    restart();
                }
            }
        }
    }

    private boolean CheckBlackFrame(byte[] bArr) {
        this.mFramesSinceCameraChange++;
        if (this.mFramesSinceCameraChange > 30 || bArr[0] != (byte) 0 || bArr[100] != (byte) 0 || bArr[bArr.length / 2] != (byte) 0) {
            return true;
        }
        Logging.LogDebugPrint(isDebug, "C: black frame found", new Object[0]);
        return false;
    }

    public void onError(int i, Camera camera) {
        Logging.LogDebugPrint(isDebug, "C: error: error callback from camera: " + i, new Object[0]);
        if (i == 100) {
            reopen();
        }
    }

    public int stop() {
        Logging.LogDebugPrint(isDebug, "C: stop()", new Object[0]);
        checkState();
        switch (this.mState) {
            case CAPTURING:
                if (this.mCamera != null) {
                    this.mCamera.stopPreview();
                    try {
                        this.mCamera.setPreviewDisplay(null);
                    } catch (IOException e) {
                        Logging.LogDebugPrint(isDebug, "C: error: setPreviewDisplay failed", new Object[0]);
                    }
                    releaseCamera();
                }
                setState(a.READY_TO_CAPTURE);
                break;
            case WAITING_FOR_SURFACE:
                if (!this.mReleaseOnSurfaceLost) {
                    releaseCamera();
                }
                setState(a.CREATED);
                break;
            default:
                Logging.LogDebugPrint(isDebug, "C: warning: trying to stop from wrong state(" + this.mState.toString() + ")", new Object[0]);
                break;
        }
        return 0;
    }

    public int setSurface() {
        final int[] iArr = new int[]{0};
        mActivity.runOnUiThread(new Runnable(this) {
            final /* synthetic */ Capture b;

            public void run() {
                iArr[0] = this.b.setSurfaceInUi();
            }
        });
        return iArr[0];
    }

    private int setSurfaceInUi() {
        this.mSurface = new SurfaceView(mActivity.getBaseContext());
        mActivity.addContentView(this.mSurface, new LayoutParams(1, 1));
        this.mSurface.setVisibility(0);
        this.mSurface.setBackgroundColor(0);
        this.mSurface.setDrawingCacheEnabled(false);
        this.mSurface.setZOrderMediaOverlay(true);
        this.mSurface.setZOrderOnTop(true);
        this.mHolder = this.mSurface.getHolder();
        this.mHolder.addCallback(this);
        this.mHolder.setFixedSize(0, 0);
        if (11 > VERSION.SDK_INT) {
            this.mHolder.setType(3);
        }
        return 0;
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        Logging.LogDebugPrint(isDebug, "C: surfaceChanged() - (%dx%d)", Integer.valueOf(i2), Integer.valueOf(i3));
        checkState();
        this.mHolder = surfaceHolder;
        switch (this.mState) {
            case CAPTURING:
                Logging.LogDebugPrint(isDebug, "C: restarting camera preview", new Object[0]);
                try {
                    this.mCamera.setPreviewDisplay(this.mHolder);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                restart();
                return;
            case WAITING_FOR_SURFACE:
                if (this.mReleaseOnSurfaceLost) {
                    open();
                }
                try {
                    this.mCamera.setPreviewDisplay(this.mHolder);
                    startPreview();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
                setState(a.CAPTURING);
                return;
            default:
                Logging.LogDebugPrint(isDebug, "C: warning: wrong state (" + this.mState.toString() + ") in surfaceChanged()", new Object[0]);
                return;
        }
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder) {
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        Logging.LogDebugPrint(isDebug, "C: surfaceDestroyed()", new Object[0]);
        checkState();
        switch (this.mState) {
            case CAPTURING:
                try {
                    surfaceLost(this.mcObject);
                    this.mCamera.stopPreview();
                    this.mCamera.setPreviewDisplay(null);
                    setState(a.WAITING_FOR_SURFACE);
                    if (this.mReleaseOnSurfaceLost) {
                        releaseCamera();
                        return;
                    }
                    return;
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
            case READY_TO_CAPTURE:
                setState(a.CREATED);
                return;
            default:
                Logging.LogDebugPrint(isDebug, "C: warning: wrong state (" + this.mState.toString() + ") in surfaceDestroyed()", new Object[0]);
                return;
        }
    }

    private void setState(a aVar) {
        Logging.LogDebugPrint(isDebug, "C: state changed from " + this.mState.toString() + " to " + aVar.toString() + " in " + new Exception().getStackTrace()[1], new Object[0]);
        this.mState = aVar;
    }

    private void checkState() {
        boolean z = true;
        if (ASSERTS_ENABLED) {
            switch (this.mState) {
                case CAPTURING:
                    if (this.mCamera == null) {
                        z = false;
                    }
                    capAssert(z);
                    return;
                case WAITING_FOR_SURFACE:
                    if (this.mReleaseOnSurfaceLost) {
                        if (this.mCamera != null) {
                            z = false;
                        }
                        capAssert(z);
                        return;
                    }
                    if (this.mCamera == null) {
                        z = false;
                    }
                    capAssert(z);
                    return;
                case READY_TO_CAPTURE:
                case CREATED:
                    if (this.mCamera != null) {
                        z = false;
                    }
                    capAssert(z);
                    return;
                default:
                    return;
            }
        }
    }

    private static void capAssert(boolean z, String str) {
        if (ASSERTS_ENABLED && !z) {
            Logging.LogDebugPrint(true, "C: assert : " + str, new Object[0]);
            new Exception().printStackTrace();
        }
    }

    private static void capAssert(boolean z) {
        if (ASSERTS_ENABLED && !z) {
            Logging.LogDebugPrint(true, "C: assert", new Object[0]);
            new Exception().printStackTrace();
        }
    }
}
