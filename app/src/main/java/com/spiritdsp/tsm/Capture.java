package com.spiritdsp.tsm;

import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.ErrorCallback;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.PreviewCallback;
import android.hardware.Camera.Size;
import android.os.Build.VERSION;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import com.android.volley.toolbox.ImageRequest;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

class Capture extends CaptureBase implements PreviewCallback, ErrorCallback {
    private int mActivityOrientationCode = 1;
    private Method mAddCallbackBuffer = null;
    private Camera mCamera = null;
    private int mCameraExposure = 0;
    private Boolean mCameraExposureChanged = Boolean.valueOf(false);
    private int mCameraFrontNum = 1;
    private int mFullPicSize = 0;
    protected SurfaceHolder mHolder = null;
    private Method mOpenInt = null;
    private int mOpenedHeight = 0;
    private int mOpenedWidth = 0;
    private int mPreviewOrientation = -1;
    private List<Size> mPreviewSizes = null;
    private Method mSetPreviewCallbackWithBuffer = null;
    private CaptureState mState = CaptureState.CREATED;
    protected SurfaceView mSurface = null;

    enum CaptureState {
        CREATED,
        READY_TO_CAPTURE,
        CAPTURING,
        WAITING_FOR_SURFACE
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
            this.mReleaseOnSurfaceLost = CameraParams.needReleaseOnSurfaceLost;
            int cameraCount = Camera.getNumberOfCameras();
            Logging.LogDebugPrint(isDebug, "C: cameraCount=" + cameraCount, new Object[0]);
            CameraInfo cameraInfo = new CameraInfo();
            CameraParams.haveFrontCamera = false;
            CameraParams.haveRearCamera = false;
            CameraParams.initDefaultNaturalOrientation();
            for (int camIdx = 0; camIdx < cameraCount; camIdx++) {
                Camera.getCameraInfo(camIdx, cameraInfo);
                if (cameraInfo.facing == 1) {
                    CameraParams.haveFrontCamera = true;
                    this.mCameraFrontNum = camIdx;
                }
                if (cameraInfo.facing == 0) {
                    CameraParams.haveRearCamera = true;
                    this.mCameraRearNum = camIdx;
                }
                Logging.LogDebugPrint(isDebug, "C: cam: facing=" + cameraInfo.facing + " orientatin=" + cameraInfo.orientation, new Object[0]);
            }
        } catch (Exception e2) {
            Logging.LogDebugPrint(isDebug, "C: warning: non-fatal exception", new Object[0]);
            e2.printStackTrace();
        }
    }

    public Capture() {
        getReflections();
        setCameraNum(1);
    }

    public void onActivitySet() {
        mActivity.runOnUiThread(new Runnable() {
            public void run() {
                Capture.this.setSurfaceInUi();
            }
        });
    }

    private void setSurfaceInUi() {
        if (this.mSurface != null) {
            ViewGroup vg = (ViewGroup) this.mSurface.getParent();
            if (vg != null) {
                vg.removeView(this.mSurface);
            }
        }
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
    }

    private void tryOpenFrontCamera_Motorola() {
        try {
            Method fcMethod = Class.forName("com.motorola.hardware.frontcamera.FrontCamera").getDeclaredMethod("getFrontCamera", (Class[]) null);
            if (fcMethod != null) {
                this.mCamera = (Camera) fcMethod.invoke(null, (Object[]) null);
            }
        } catch (Exception e) {
        }
    }

    private void tryOpenFrontCamera_HTC() {
        try {
            Class<?> cameraClass = Class.forName("android.hardware.HtcFrontFacingCamera");
            if (cameraClass != null) {
                Method methodCameraOpen = cameraClass.getMethod("getCamera", new Class[0]);
                if (methodCameraOpen != null) {
                    this.mCamera = (Camera) methodCameraOpen.invoke(null, new Object[0]);
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
        } catch (Throwable e7) {
            Logging.LogDebugPrint(isDebug, "C: error: Throwable: " + e7.getLocalizedMessage(), new Object[0]);
        }
    }

    private void trySetCamera_setCameraId(int cameraNum) {
        try {
            Method setCameraIdMethod = Camera.class.getMethod("setCameraId", new Class[]{Integer.TYPE});
            if (setCameraIdMethod != null) {
                setCameraIdMethod.invoke(null, new Object[]{Integer.valueOf(cameraNum)});
            }
        } catch (Throwable th) {
        }
    }

    private void trySetCamera_camera_id(int cameraNum) {
        try {
            Parameters cp = this.mCamera.getParameters();
            String id = cp.get("camera-id");
            if (id != null && id.length() > 0) {
                cp.set("camera-id", cameraNum + 1);
                this.mCamera.setParameters(cp);
            }
        } catch (Throwable e) {
            Logging.LogDebugPrint(isDebug, "C: failed to set front camera: " + e.getLocalizedMessage(), new Object[0]);
        }
    }

    private void trySetCamera_camera_sensor(int cameraNum) {
        try {
            Parameters cp = this.mCamera.getParameters();
            String id = cp.get("camera-sensor");
            if (id != null && id.length() > 0) {
                cp.set("camera-sensor", cameraNum);
                this.mCamera.setParameters(cp);
            }
        } catch (Throwable e) {
            Logging.LogDebugPrint(isDebug, "C: failed to set front camera: " + e.getLocalizedMessage(), new Object[0]);
        }
    }

    private int tryCamera_exposure_set(int ec) {
        try {
            Parameters cp = this.mCamera.getParameters();
            int minEC = cp.getMinExposureCompensation();
            int maxEC = cp.getMaxExposureCompensation();
            if (ec < minEC) {
                ec = minEC;
            }
            if (ec > maxEC) {
                ec = maxEC;
            }
            if (cp.getExposureCompensation() == ec) {
                return ec;
            }
            if (ec < minEC || ec > maxEC) {
                Logging.LogDebugPrint(isDebug, String.format("C: camera exposure compensation is out of range [%d;%d]", new Object[]{Integer.valueOf(minEC), Integer.valueOf(maxEC)}), new Object[0]);
                return ec;
            }
            cp.setExposureCompensation(ec);
            this.mCamera.setParameters(cp);
            return ec;
        } catch (Throwable e) {
            Logging.LogDebugPrint(isDebug, "C: failed to set camera exposure compensation: " + e.getLocalizedMessage(), new Object[0]);
        }
    }

    private float[] tryCamera_exposure_get() {
        float[] ec = new float[]{0.0f, 0.0f, 0.0f, 0.0f};
        try {
            Parameters cp = this.mCamera.getParameters();
            ec[0] = (float) cp.getExposureCompensation();
            ec[1] = (float) cp.getMinExposureCompensation();
            ec[2] = (float) cp.getMaxExposureCompensation();
            ec[3] = cp.getExposureCompensationStep();
        } catch (Throwable e) {
            Logging.LogDebugPrint(isDebug, "C: failed to get camera exposure compensation: " + e.getLocalizedMessage(), new Object[0]);
        }
        return ec;
    }

    private boolean open() {
        Logging.LogDebugPrint(isDebug, "C: open, API Level=%d", Integer.valueOf(VERSION.SDK_INT));
        CaptureBase.capAssert(this.mCamera == null);
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
                } catch (Throwable e5) {
                    Logging.LogDebugPrint(isDebug, "C: Throwable: " + e5.getLocalizedMessage(), new Object[0]);
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
            } catch (Exception e6) {
                Logging.LogDebugPrint(isDebug, "C: error: failed to set camera exposure: " + e6.toString(), new Object[0]);
            }
            try {
                if (this.mHolder != null) {
                    this.mCamera.setPreviewDisplay(this.mHolder);
                }
            } catch (IOException e7) {
                Logging.LogDebugPrint(isDebug, "C: error: setPreviewDisplay failed: " + e7.toString(), new Object[0]);
                e7.printStackTrace();
            }
            return true;
        } catch (Exception e62) {
            Logging.LogDebugPrint(isDebug, "C: error: open camera failed: " + e62.toString(), new Object[0]);
            e62.printStackTrace();
            return false;
        }
    }

    private void releaseCamera() {
        boolean z = false;
        Logging.LogDebugPrint(isDebug, "C: release", new Object[0]);
        if (this.mCamera != null) {
            z = true;
        }
        CaptureBase.capAssert(z);
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
        setState(CaptureState.CREATED);
        return 0;
    }

    public void setCameraNum(int cameraNum) {
        Logging.LogDebugPrint(isDebug, "C: setCameraNum(%d)", Integer.valueOf(cameraNum));
        if (cameraNum < 2 && !CameraParams.haveFrontCamera) {
            cameraNum = this.mCameraRearNum;
        }
        if (cameraNum < 2 && !CameraParams.haveRearCamera) {
            cameraNum = this.mCameraFrontNum;
        }
        if (cameraNum != this.mCameraNum) {
            this.mFramesSinceCameraChange = 0;
            this.mCameraNum = cameraNum;
            switch (this.mState) {
                case CAPTURING:
                    this.mCamera.stopPreview();
                    reopen();
                    if (!startPreview()) {
                        setState(CaptureState.READY_TO_CAPTURE);
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

    public void setCameraExposureCompensation(int ec) {
        Logging.LogDebugPrint(isDebug, "C: setCameraExposureCompensation(%d)", Integer.valueOf(ec));
        int _ec = tryCamera_exposure_set(ec);
        if (_ec != this.mCameraExposure) {
            this.mCameraExposure = _ec;
            this.mCameraExposureChanged = Boolean.valueOf(true);
        }
    }

    public int getCameraExposureCompensation() {
        if (this.mCamera != null) {
            int _ec = (int) tryCamera_exposure_get()[0];
            if (_ec != this.mCameraExposure) {
                this.mCameraExposure = _ec;
                this.mCameraExposureChanged = Boolean.valueOf(true);
            }
        }
        Logging.LogDebugPrint(isDebug, "C: getCameraExposureCompensation(%d)", Integer.valueOf(this.mCameraExposure));
        return this.mCameraExposure;
    }

    public int getCameraExposureCompensationMin() {
        int ecmin = 0;
        if (this.mCamera != null) {
            ecmin = (int) tryCamera_exposure_get()[1];
        }
        Logging.LogDebugPrint(isDebug, "C: getCameraExposureCompensationMin(%d)", Integer.valueOf(ecmin));
        return ecmin;
    }

    public int getCameraExposureCompensationMax() {
        int ecmax = 0;
        if (this.mCamera != null) {
            ecmax = (int) tryCamera_exposure_get()[2];
        }
        Logging.LogDebugPrint(isDebug, "C: getCameraExposureCompensationMax(%d)", Integer.valueOf(ecmax));
        return ecmax;
    }

    public float getCameraExposureCompensationStep() {
        float ecstep = 0.0f;
        if (this.mCamera != null) {
            ecstep = tryCamera_exposure_get()[3];
        }
        Logging.LogDebugPrint(isDebug, "C: getCameraExposureCompensationStep(%f)", Float.valueOf(ecstep));
        return ecstep;
    }

    public int start(int requestedWidth, int requestedHeight, int framerateNum, int framerateDenum) {
        boolean resize;
        if (this.mRequestedWidth == requestedWidth && this.mRequestedHeight == requestedHeight) {
            resize = false;
        } else {
            resize = true;
        }
        this.mRequestedWidth = requestedWidth;
        this.mRequestedHeight = requestedHeight;
        if (framerateDenum != 0) {
            this.mRequstedFPS = (framerateNum * ImageRequest.DEFAULT_IMAGE_TIMEOUT_MS) / framerateDenum;
        } else {
            this.mRequstedFPS = 15000;
        }
        Logging.LogDebugPrint(isDebug, "C: start %dx%d @%d", Integer.valueOf(this.mRequestedWidth), Integer.valueOf(this.mRequestedHeight), Integer.valueOf(this.mRequstedFPS));
        checkState();
        switch (this.mState) {
            case CAPTURING:
                if (!resize) {
                    return 0;
                }
                restart();
                return 0;
            case WAITING_FOR_SURFACE:
                if (this.mCamera == null || !resize) {
                    return 0;
                }
                reopen();
                return 0;
            case READY_TO_CAPTURE:
                if (!open()) {
                    return -5;
                }
                if (!startPreview()) {
                    return 0;
                }
                setState(CaptureState.CAPTURING);
                return 0;
            case CREATED:
                if (this.mHolder != null) {
                    if (!open()) {
                        return -5;
                    }
                    if (!startPreview()) {
                        return 0;
                    }
                    setState(CaptureState.CAPTURING);
                    return 0;
                } else if (!this.mReleaseOnSurfaceLost && !open()) {
                    return -5;
                } else {
                    setState(CaptureState.WAITING_FOR_SURFACE);
                    return 0;
                }
            default:
                Logging.LogDebugPrint(isDebug, "C: warning: wrong state (" + this.mState.toString() + ") in start()", new Object[0]);
                return 0;
        }
    }

    public void setUserOrient(int userAddPreviewOrient, int userAddOutputOrient) {
        Logging.LogDebugPrint(isDebug, "C: setUserOrient() %d %d", Integer.valueOf(userAddPreviewOrient), Integer.valueOf(userAddOutputOrient));
        if (this.mUserAddOutputOrientation != userAddOutputOrient || this.mUserAddPreviewOrientation != userAddPreviewOrient) {
            this.mUserAddOutputOrientation = userAddOutputOrient;
            this.mUserAddPreviewOrientation = userAddPreviewOrient;
            if (this.mCamera != null) {
                restart();
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void configureCamera() {
        /*
        r14 = this;
        r9 = r14.mCamera;
        if (r9 == 0) goto L_0x000d;
    L_0x0004:
        r9 = 1;
    L_0x0005:
        com.spiritdsp.tsm.CaptureBase.capAssert(r9);
        r9 = r14.mCamera;
        if (r9 != 0) goto L_0x000f;
    L_0x000c:
        return;
    L_0x000d:
        r9 = 0;
        goto L_0x0005;
    L_0x000f:
        r9 = r14.mCamera;	 Catch:{ Throwable -> 0x011c }
        r1 = r9.getParameters();	 Catch:{ Throwable -> 0x011c }
        r8 = r14.mRequestedWidth;	 Catch:{ Throwable -> 0x011c }
        r5 = r14.mRequestedHeight;	 Catch:{ Throwable -> 0x011c }
        r9 = r1.getSupportedPreviewSizes();	 Catch:{ Throwable -> 0x011c }
        r14.mPreviewSizes = r9;	 Catch:{ Throwable -> 0x011c }
        r9 = r14.mPreviewSizes;	 Catch:{ Throwable -> 0x011c }
        if (r9 == 0) goto L_0x0130;
    L_0x0023:
        r9 = 1;
    L_0x0024:
        com.spiritdsp.tsm.CaptureBase.capAssert(r9);	 Catch:{ Throwable -> 0x011c }
        r9 = r14.mPreviewSizes;	 Catch:{ Throwable -> 0x011c }
        r0 = r14.chooseCaptureSize(r9, r8, r5);	 Catch:{ Throwable -> 0x011c }
        r9 = 0;
        r9 = r0[r9];	 Catch:{ Throwable -> 0x011c }
        if (r9 == 0) goto L_0x0037;
    L_0x0032:
        r9 = 1;
        r9 = r0[r9];	 Catch:{ Throwable -> 0x011c }
        if (r9 != 0) goto L_0x0058;
    L_0x0037:
        r9 = isDebug;	 Catch:{ Throwable -> 0x011c }
        r10 = "C: %dx%d %d' is not supported";
        r11 = 3;
        r11 = new java.lang.Object[r11];	 Catch:{ Throwable -> 0x011c }
        r12 = 0;
        r13 = java.lang.Integer.valueOf(r8);	 Catch:{ Throwable -> 0x011c }
        r11[r12] = r13;	 Catch:{ Throwable -> 0x011c }
        r12 = 1;
        r13 = java.lang.Integer.valueOf(r5);	 Catch:{ Throwable -> 0x011c }
        r11[r12] = r13;	 Catch:{ Throwable -> 0x011c }
        r12 = 2;
        r13 = r14.mOutputOrientation;	 Catch:{ Throwable -> 0x011c }
        r13 = java.lang.Integer.valueOf(r13);	 Catch:{ Throwable -> 0x011c }
        r11[r12] = r13;	 Catch:{ Throwable -> 0x011c }
        com.spiritdsp.tsm.Logging.LogDebugPrint(r9, r10, r11);	 Catch:{ Throwable -> 0x011c }
    L_0x0058:
        r9 = 0;
        r9 = r0[r9];	 Catch:{ Throwable -> 0x011c }
        if (r9 == 0) goto L_0x0133;
    L_0x005d:
        r9 = 1;
    L_0x005e:
        com.spiritdsp.tsm.CaptureBase.capAssert(r9);	 Catch:{ Throwable -> 0x011c }
        r9 = 1;
        r9 = r0[r9];	 Catch:{ Throwable -> 0x011c }
        if (r9 == 0) goto L_0x0136;
    L_0x0066:
        r9 = 1;
    L_0x0067:
        com.spiritdsp.tsm.CaptureBase.capAssert(r9);	 Catch:{ Throwable -> 0x011c }
        r9 = isDebug;	 Catch:{ Throwable -> 0x011c }
        r10 = "C: set preview size %dx%d %d'";
        r11 = 3;
        r11 = new java.lang.Object[r11];	 Catch:{ Throwable -> 0x011c }
        r12 = 0;
        r13 = 0;
        r13 = r0[r13];	 Catch:{ Throwable -> 0x011c }
        r13 = java.lang.Integer.valueOf(r13);	 Catch:{ Throwable -> 0x011c }
        r11[r12] = r13;	 Catch:{ Throwable -> 0x011c }
        r12 = 1;
        r13 = 1;
        r13 = r0[r13];	 Catch:{ Throwable -> 0x011c }
        r13 = java.lang.Integer.valueOf(r13);	 Catch:{ Throwable -> 0x011c }
        r11[r12] = r13;	 Catch:{ Throwable -> 0x011c }
        r12 = 2;
        r13 = r14.mPreviewOrientation;	 Catch:{ Throwable -> 0x011c }
        r13 = java.lang.Integer.valueOf(r13);	 Catch:{ Throwable -> 0x011c }
        r11[r12] = r13;	 Catch:{ Throwable -> 0x011c }
        com.spiritdsp.tsm.Logging.LogDebugPrint(r9, r10, r11);	 Catch:{ Throwable -> 0x011c }
        r9 = 0;
        r9 = r0[r9];	 Catch:{ Throwable -> 0x011c }
        r10 = 1;
        r10 = r0[r10];	 Catch:{ Throwable -> 0x011c }
        r1.setPreviewSize(r9, r10);	 Catch:{ Throwable -> 0x011c }
        r9 = r14.mPreviewOrientation;	 Catch:{ Throwable -> 0x011c }
        r10 = 90;
        if (r9 == r10) goto L_0x00a6;
    L_0x00a0:
        r9 = r14.mPreviewOrientation;	 Catch:{ Throwable -> 0x011c }
        r10 = 270; // 0x10e float:3.78E-43 double:1.334E-321;
        if (r9 != r10) goto L_0x0139;
    L_0x00a6:
        r9 = r14.mcObject;	 Catch:{ Throwable -> 0x011c }
        r10 = 1;
        r10 = r0[r10];	 Catch:{ Throwable -> 0x011c }
        r11 = 0;
        r11 = r0[r11];	 Catch:{ Throwable -> 0x011c }
        r14.onPreviewSizeChanged(r9, r10, r11);	 Catch:{ Throwable -> 0x011c }
    L_0x00b1:
        r9 = android.hardware.Camera.Parameters.class;
        r10 = "getSupportedPreviewFpsRange";
        r11 = 0;
        r11 = new java.lang.Class[r11];	 Catch:{ Exception -> 0x0148 }
        r4 = r9.getMethod(r10, r11);	 Catch:{ Exception -> 0x0148 }
        r9 = 0;
        r9 = new java.lang.Object[r9];	 Catch:{ Exception -> 0x0148 }
        r9 = r4.invoke(r1, r9);	 Catch:{ Exception -> 0x0148 }
        r9 = (java.util.List) r9;	 Catch:{ Exception -> 0x0148 }
        r6 = r14.chooseCaptureRate(r9);	 Catch:{ Exception -> 0x0148 }
        if (r6 == 0) goto L_0x0146;
    L_0x00cb:
        r9 = 1;
    L_0x00cc:
        com.spiritdsp.tsm.CaptureBase.capAssert(r9);	 Catch:{ Exception -> 0x0148 }
        r9 = android.hardware.Camera.Parameters.class;
        r10 = "setPreviewFpsRange";
        r11 = 2;
        r11 = new java.lang.Class[r11];	 Catch:{ Exception -> 0x0148 }
        r12 = 0;
        r13 = java.lang.Integer.TYPE;	 Catch:{ Exception -> 0x0148 }
        r11[r12] = r13;	 Catch:{ Exception -> 0x0148 }
        r12 = 1;
        r13 = java.lang.Integer.TYPE;	 Catch:{ Exception -> 0x0148 }
        r11[r12] = r13;	 Catch:{ Exception -> 0x0148 }
        r7 = r9.getMethod(r10, r11);	 Catch:{ Exception -> 0x0148 }
        r9 = 2;
        r9 = new java.lang.Object[r9];	 Catch:{ Exception -> 0x0148 }
        r10 = 0;
        r11 = 0;
        r11 = r6[r11];	 Catch:{ Exception -> 0x0148 }
        r11 = java.lang.Integer.valueOf(r11);	 Catch:{ Exception -> 0x0148 }
        r9[r10] = r11;	 Catch:{ Exception -> 0x0148 }
        r10 = 1;
        r11 = 1;
        r11 = r6[r11];	 Catch:{ Exception -> 0x0148 }
        r11 = java.lang.Integer.valueOf(r11);	 Catch:{ Exception -> 0x0148 }
        r9[r10] = r11;	 Catch:{ Exception -> 0x0148 }
        r7.invoke(r1, r9);	 Catch:{ Exception -> 0x0148 }
    L_0x00fe:
        r9 = r14.mCamera;	 Catch:{ Throwable -> 0x011c }
        r9.setParameters(r1);	 Catch:{ Throwable -> 0x011c }
        r9 = "auto";
        r1.setAntibanding(r9);	 Catch:{ Exception -> 0x010f }
        r9 = r14.mCamera;	 Catch:{ Exception -> 0x010f }
        r9.setParameters(r1);	 Catch:{ Exception -> 0x010f }
        goto L_0x000c;
    L_0x010f:
        r3 = move-exception;
        r9 = isDebug;	 Catch:{ Throwable -> 0x011c }
        r10 = "C: warning: antibanding not supported";
        r11 = 0;
        r11 = new java.lang.Object[r11];	 Catch:{ Throwable -> 0x011c }
        com.spiritdsp.tsm.Logging.LogDebugPrint(r9, r10, r11);	 Catch:{ Throwable -> 0x011c }
        goto L_0x000c;
    L_0x011c:
        r2 = move-exception;
        r9 = isDebug;
        r10 = "C: error: failed to set camera parameters: %s";
        r11 = 1;
        r11 = new java.lang.Object[r11];
        r12 = 0;
        r13 = r2.getMessage();
        r11[r12] = r13;
        com.spiritdsp.tsm.Logging.LogDebugPrint(r9, r10, r11);
        goto L_0x000c;
    L_0x0130:
        r9 = 0;
        goto L_0x0024;
    L_0x0133:
        r9 = 0;
        goto L_0x005e;
    L_0x0136:
        r9 = 0;
        goto L_0x0067;
    L_0x0139:
        r9 = r14.mcObject;	 Catch:{ Throwable -> 0x011c }
        r10 = 0;
        r10 = r0[r10];	 Catch:{ Throwable -> 0x011c }
        r11 = 1;
        r11 = r0[r11];	 Catch:{ Throwable -> 0x011c }
        r14.onPreviewSizeChanged(r9, r10, r11);	 Catch:{ Throwable -> 0x011c }
        goto L_0x00b1;
    L_0x0146:
        r9 = 0;
        goto L_0x00cc;
    L_0x0148:
        r3 = move-exception;
        r9 = isDebug;	 Catch:{ Throwable -> 0x011c }
        r10 = "C: warning: setting rate range is not supported on the device";
        r11 = 0;
        r11 = new java.lang.Object[r11];	 Catch:{ Throwable -> 0x011c }
        com.spiritdsp.tsm.Logging.LogDebugPrint(r9, r10, r11);	 Catch:{ Throwable -> 0x011c }
        goto L_0x00fe;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.spiritdsp.tsm.Capture.configureCamera():void");
    }

    private void chooseOrientation() {
        boolean z;
        int naturalOrientation = 270;
        int camFacing = this.mCameraNum;
        try {
            CameraInfo cameraInfo = new CameraInfo();
            Camera.getCameraInfo(this.mCameraNum, cameraInfo);
            camFacing = cameraInfo.facing;
            naturalOrientation = cameraInfo.orientation;
        } catch (Exception e) {
            Logging.LogDebugPrint(isDebug, "C: error: problem with cameraInfo", new Object[0]);
            e.printStackTrace();
        }
        if (naturalOrientation >= 0) {
            z = true;
        } else {
            z = false;
        }
        CaptureBase.capAssert(z);
        int activityOrientationDegrees = 0;
        this.mActivityOrientationCode = mActivity.getWindowManager().getDefaultDisplay().getRotation();
        switch (this.mActivityOrientationCode) {
            case 0:
                activityOrientationDegrees = 0;
                break;
            case 1:
                activityOrientationDegrees = 90;
                break;
            case 2:
                activityOrientationDegrees = 180;
                break;
            case 3:
                activityOrientationDegrees = 270;
                break;
        }
        if (camFacing == 1) {
            this.mPreviewOrientation = ((360 - ((naturalOrientation + activityOrientationDegrees) % 360)) + this.mUserAddPreviewOrientation) % 360;
            this.mOutputOrientation = ((naturalOrientation + activityOrientationDegrees) + this.mUserAddPreviewOrientation) % 360;
        } else {
            this.mPreviewOrientation = (((naturalOrientation - activityOrientationDegrees) + this.mUserAddPreviewOrientation) + 360) % 360;
            this.mOutputOrientation = (((naturalOrientation - activityOrientationDegrees) + this.mUserAddPreviewOrientation) + 360) % 360;
        }
        try {
            this.mCamera.setDisplayOrientation(this.mPreviewOrientation);
        } catch (Exception e2) {
            Logging.LogDebugPrint(isDebug, "C: error: problem with mSetDisplayOrientation", new Object[0]);
            e2.printStackTrace();
        }
        Logging.LogDebugPrint(isDebug, "C: orientations: camera natural=%d facing=%d activity=%d preview=%d output=%d user=%d", Integer.valueOf(naturalOrientation), Integer.valueOf(camFacing), Integer.valueOf(activityOrientationDegrees), Integer.valueOf(this.mPreviewOrientation), Integer.valueOf(this.mOutputOrientation), Integer.valueOf(this.mUserAddPreviewOrientation));
    }

    public int[] getResolutionList() {
        Logging.LogDebugPrint(isDebug, "C: getResolutionList", new Object[0]);
        if (mActivity == null) {
            return null;
        }
        boolean needOpen;
        if (this.mPreviewSizes == null && this.mCamera == null) {
            needOpen = true;
        } else {
            needOpen = false;
        }
        boolean needClose = false;
        if (needOpen) {
            if (!open()) {
                return new int[0];
            }
            needClose = true;
        }
        if (this.mPreviewSizes == null) {
            chooseOrientation();
        }
        if (needClose) {
            releaseCamera();
        }
        if (this.mPreviewSizes == null) {
            return new int[0];
        }
        int[] res = new int[(this.mPreviewSizes.size() * 2)];
        for (int i = 0; i < res.length / 2; i++) {
            res[i * 2] = ((Size) this.mPreviewSizes.get(i)).width;
            res[(i * 2) + 1] = ((Size) this.mPreviewSizes.get(i)).height;
        }
        return res;
    }

    private int[] chooseCaptureSize(List<Size> sizes, int w, int h) {
        Logging.LogDebugPrint(isDebug, "C: camera size request (%dx%d)", Integer.valueOf(w), Integer.valueOf(h));
        Logging.LogDebugPrint(isDebug, "C: camera supports the following preview sizes:", new Object[0]);
        for (Size sz : sizes) {
            Logging.LogDebugPrint(isDebug, "C:  %dx%d %d'", Integer.valueOf(sz.width), Integer.valueOf(sz.height), Integer.valueOf(this.mOutputOrientation));
        }
        boolean z = 10000 > w || 10000 > h;
        CaptureBase.capAssert(z);
        int[] res = new int[]{0, 0};
        int minMark = 100000000;
        boolean hires_set = false;
        for (Size sz2 : sizes) {
            if (sz2.width <= 10000 && sz2.width > 176) {
                int mark = Math.abs(sz2.width - w) + Math.abs(sz2.height - h);
                if (sz2.width >= w && sz2.height >= h) {
                    if (mark < minMark || !hires_set) {
                        res[0] = sz2.width;
                        res[1] = sz2.height;
                        minMark = mark;
                    }
                    hires_set = true;
                } else if (!hires_set && mark < minMark) {
                    res[0] = sz2.width;
                    res[1] = sz2.height;
                    minMark = mark;
                }
            }
        }
        Logging.LogDebugPrint(isDebug, "C: result %dx%d", Integer.valueOf(res[0]), Integer.valueOf(res[1]));
        return res;
    }

    private int[] chooseCaptureRate(List<int[]> rates) {
        int[] res = new int[]{ImageRequest.DEFAULT_IMAGE_TIMEOUT_MS, ImageRequest.DEFAULT_IMAGE_TIMEOUT_MS};
        for (int[] i : rates) {
            Logging.LogDebugPrint(isDebug, "C: support camera rate: " + i[0] + ".." + i[1], new Object[0]);
            if (i[0] <= this.mRequstedFPS && i[1] >= this.mRequstedFPS) {
                res[0] = this.mRequstedFPS;
                res[1] = this.mRequstedFPS;
                break;
            } else if (res[0] == ImageRequest.DEFAULT_IMAGE_TIMEOUT_MS || (i[0] >= this.mRequstedFPS && i[0] < res[0])) {
                res[0] = i[0];
                res[1] = i[0];
            }
        }
        Logging.LogDebugPrint(isDebug, "C: rate range choosed: " + res[0] + ".." + res[1] + " (" + this.mRequstedFPS + " requested)", new Object[0]);
        return res;
    }

    private boolean startPreview() {
        CaptureBase.capAssert(this.mCamera != null);
        Logging.LogDebugPrint(isDebug, "C: startPreview", new Object[0]);
        if (this.mCamera == null) {
            Logging.LogDebugPrint(isDebug, "C: null camera object", new Object[0]);
            return false;
        }
        Parameters cp = null;
        try {
            cp = this.mCamera.getParameters();
        } catch (Exception e) {
            Logging.LogDebugPrint(isDebug, "C: catched exception in Camera.getParameters()", new Object[0]);
        }
        if (cp == null) {
            reopen();
        }
        try {
            cp = this.mCamera.getParameters();
        } catch (Exception e2) {
        }
        if (cp == null) {
            Logging.LogDebugPrint(isDebug, "C: camera in bad state in Camera.getParameters()", new Object[0]);
            return false;
        }
        chooseOrientation();
        configureCamera();
        this.mFramesSinceCameraChange = 0;
        try {
            boolean z;
            Size sz = this.mCamera.getParameters().getPreviewSize();
            this.mOpenedWidth = sz.width;
            this.mOpenedHeight = sz.height;
            if (this.mOpenedHeight != 0) {
                z = true;
            } else {
                z = false;
            }
            CaptureBase.capAssert(z);
            if (this.mOpenedWidth != 0) {
                z = true;
            } else {
                z = false;
            }
            CaptureBase.capAssert(z);
            this.mFullPicSize = ((this.mOpenedWidth * this.mOpenedHeight) * 3) / 2;
            Logging.LogDebugPrint(isDebug, "C: camera actually opened on (%d, %d)", Integer.valueOf(sz.width), Integer.valueOf(sz.height));
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
        CaptureBase.capAssert(this.mCamera != null);
        Logging.LogDebugPrint(isDebug, "C: restart", new Object[0]);
        if (this.mCamera == null) {
            Logging.LogDebugPrint(isDebug, "C: null camera object", new Object[0]);
            return;
        }
        this.mCamera.stopPreview();
        if (CameraParams.needReleaseOnSurfaceChange[this.mCameraNum]) {
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
        CaptureBase.capAssert(z);
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
                    byte[] buf = new byte[this.mFullPicSize];
                    this.mAddCallbackBuffer.invoke(this.mCamera, new Object[]{buf});
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

    public void onPreviewFrame(byte[] data, Camera camera) {
        if (this.mState == CaptureState.CAPTURING) {
            if (data == null) {
                Logging.LogDebugPrint(isDebug, "C: warning: null data in callback", new Object[0]);
                return;
            }
            if (data.length != this.mFullPicSize) {
                Logging.LogDebugPrint(isDebug, "C: error: frame size in callback wrong - %d instead of %d", Integer.valueOf(data.length), Integer.valueOf(this.mFullPicSize));
            } else {
                boolean res = true;
                if (CameraParams.needToCheckForBlackFrames) {
                    res = CheckBlackFrame(data);
                }
                if (res) {
                    frameCaptured(this.mcObject, data, this.mOpenedWidth, this.mOpenedHeight, this.mOutputOrientation, 0);
                }
            }
            try {
                if (this.mAddCallbackBuffer != null) {
                    this.mAddCallbackBuffer.invoke(camera, new Object[]{data});
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            int orient = 0;
            try {
                orient = mActivity.getWindowManager().getDefaultDisplay().getRotation();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            if (orient != this.mActivityOrientationCode) {
                Logging.LogDebugPrint(isDebug, "C: camera Ui orientation changed", new Object[0]);
                if (VERSION.SDK_INT >= 14) {
                    chooseOrientation();
                } else {
                    restart();
                }
            }
        }
    }

    private boolean CheckBlackFrame(byte[] data) {
        this.mFramesSinceCameraChange++;
        if (this.mFramesSinceCameraChange > 30 || data[0] != (byte) 0 || data[100] != (byte) 0 || data[data.length / 2] != (byte) 0) {
            return true;
        }
        Logging.LogDebugPrint(isDebug, "C: black frame found", new Object[0]);
        return false;
    }

    public void onError(int code, Camera cam) {
        Logging.LogDebugPrint(isDebug, "C: error: error callback from camera: " + code, new Object[0]);
        if (code == 100) {
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
                setState(CaptureState.READY_TO_CAPTURE);
                break;
            case WAITING_FOR_SURFACE:
                if (!this.mReleaseOnSurfaceLost) {
                    releaseCamera();
                }
                setState(CaptureState.CREATED);
                break;
            default:
                Logging.LogDebugPrint(isDebug, "C: warning: trying to stop from wrong state(" + this.mState.toString() + ")", new Object[0]);
                break;
        }
        return 0;
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Logging.LogDebugPrint(isDebug, "C: surfaceChanged() - (%dx%d)", Integer.valueOf(width), Integer.valueOf(height));
        checkState();
        this.mHolder = holder;
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
                setState(CaptureState.CAPTURING);
                return;
            default:
                Logging.LogDebugPrint(isDebug, "C: warning: wrong state (" + this.mState.toString() + ") in surfaceChanged()", new Object[0]);
                return;
        }
    }

    public void surfaceCreated(SurfaceHolder holder) {
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        Logging.LogDebugPrint(isDebug, "C: surfaceDestroyed()", new Object[0]);
        checkState();
        switch (this.mState) {
            case CAPTURING:
                try {
                    surfaceLost(this.mcObject);
                    this.mCamera.stopPreview();
                    this.mCamera.setPreviewDisplay(null);
                    setState(CaptureState.WAITING_FOR_SURFACE);
                    if (this.mReleaseOnSurfaceLost) {
                        releaseCamera();
                        break;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    break;
                }
                break;
            case READY_TO_CAPTURE:
                setState(CaptureState.CREATED);
                break;
            default:
                Logging.LogDebugPrint(isDebug, "C: warning: wrong state (" + this.mState.toString() + ") in surfaceDestroyed()", new Object[0]);
                break;
        }
        this.mSurface = null;
        this.mHolder = null;
    }

    private void setState(CaptureState newState) {
        Logging.LogDebugPrint(isDebug, "C: state changed from " + this.mState.toString() + " to " + newState.toString() + " in " + new Exception().getStackTrace()[1], new Object[0]);
        this.mState = newState;
    }

    private void checkState() {
        boolean z = true;
        if (ASSERTS_ENABLED) {
            switch (this.mState) {
                case CAPTURING:
                    if (this.mCamera == null) {
                        z = false;
                    }
                    CaptureBase.capAssert(z);
                    return;
                case WAITING_FOR_SURFACE:
                    if (this.mReleaseOnSurfaceLost) {
                        if (this.mCamera != null) {
                            z = false;
                        }
                        CaptureBase.capAssert(z);
                        return;
                    }
                    if (this.mCamera == null) {
                        z = false;
                    }
                    CaptureBase.capAssert(z);
                    return;
                case READY_TO_CAPTURE:
                case CREATED:
                    if (this.mCamera != null) {
                        z = false;
                    }
                    CaptureBase.capAssert(z);
                    return;
                default:
                    return;
            }
        }
    }
}
