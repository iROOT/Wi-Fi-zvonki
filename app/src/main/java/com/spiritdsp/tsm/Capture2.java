package com.spiritdsp.tsm;

import android.annotation.TargetApi;
import android.graphics.ImageFormat;
import android.graphics.Point;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraDevice.StateCallback;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureRequest.Builder;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.media.Image;
import android.media.Image.Plane;
import android.media.ImageReader;
import android.media.ImageReader.OnImageAvailableListener;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Range;
import android.util.Rational;
import android.util.Size;
import android.view.Surface;
import android.view.SurfaceHolder;
import com.android.volley.toolbox.ImageRequest;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

@TargetApi(21)
class Capture2 extends CaptureBase {
    private static final int IMAGE_FORMAT = 35;
    private Handler mBackgroundHandler;
    private HandlerThread mBackgroundThread;
    private CameraDevice mCameraDevice;
    private String mCameraId;
    private Semaphore mCameraOpenCloseLock = new Semaphore(1);
    private byte[] mCaptureBuffer;
    private int mCurrentExpCompensation = 0;
    private Range<Integer> mExpCompensationRange = new Range(Integer.valueOf(0), Integer.valueOf(0));
    private float mExpCompensationStep = 0.0f;
    private String mFrontCameraId = null;
    private ImageReader mImageReader;
    private final OnImageAvailableListener mOnImageAvailableListener = new OnImageAvailableListener() {
        private byte[] RowData;

        public void onImageAvailable(ImageReader reader) {
            Image image = reader.acquireNextImage();
            int width = image.getWidth();
            int height = image.getHeight();
            int offset = 0;
            int bufSize = ((image.getWidth() * image.getHeight()) * ImageFormat.getBitsPerPixel(35)) / 8;
            if (Capture2.this.mCaptureBuffer == null || Capture2.this.mCaptureBuffer.length < bufSize) {
                Capture2.this.mCaptureBuffer = new byte[bufSize];
            }
            Plane[] planes = image.getPlanes();
            if (this.RowData == null || this.RowData.length < planes[0].getRowStride()) {
                this.RowData = new byte[planes[0].getRowStride()];
            }
            int i = 0;
            while (i < planes.length) {
                ByteBuffer buffer = planes[i].getBuffer();
                int rowStride = planes[i].getRowStride();
                int pixelStride = planes[i].getPixelStride();
                int w = i == 0 ? width : width / 2;
                int h = i == 0 ? height : height / 2;
                for (int row = 0; row < h; row++) {
                    int bytesPerPixel = ImageFormat.getBitsPerPixel(35) / 8;
                    if (pixelStride == bytesPerPixel) {
                        int length = w * bytesPerPixel;
                        buffer.get(Capture2.this.mCaptureBuffer, offset, length);
                        if (h - row != 1) {
                            buffer.position((buffer.position() + rowStride) - length);
                        }
                        offset += length;
                    } else {
                        if (h - row == 1) {
                            buffer.get(this.RowData, 0, (width - pixelStride) + 1);
                        } else {
                            buffer.get(this.RowData, 0, rowStride);
                        }
                        int col = 0;
                        int offset2 = offset;
                        while (col < w) {
                            offset = offset2 + 1;
                            Capture2.this.mCaptureBuffer[offset2] = this.RowData[col * pixelStride];
                            col++;
                            offset2 = offset;
                        }
                        offset = offset2;
                    }
                }
                i++;
            }
            image.close();
            Capture2.this.frameCaptured(Capture2.this.mcObject, Capture2.this.mCaptureBuffer, Capture2.this.mPreviewSize.getWidth(), Capture2.this.mPreviewSize.getHeight(), Capture2.this.mOutputOrientation, 0);
        }
    };
    private Builder mPreviewRequestBuilder;
    private CameraCaptureSession mPreviewSession;
    private Size mPreviewSize;
    private String mRearCameraId = null;
    private final StateCallback mStateCallback = new StateCallback() {
        public void onOpened(CameraDevice cameraDevice) {
            Capture2.this.mCameraOpenCloseLock.release();
            Capture2.this.mCameraDevice = cameraDevice;
            Capture2.this.createCameraSession();
        }

        public void onDisconnected(CameraDevice cameraDevice) {
            Capture2.this.mCameraOpenCloseLock.release();
            cameraDevice.close();
            Capture2.this.mCameraDevice = null;
        }

        public void onError(CameraDevice cameraDevice, int error) {
            Capture2.this.mCameraOpenCloseLock.release();
            cameraDevice.close();
            Capture2.this.mCameraDevice = null;
        }
    };

    Capture2() {
        CameraParams.haveFrontCamera = false;
        CameraParams.haveRearCamera = false;
        CameraParams.initDefaultNaturalOrientation();
    }

    public void onActivitySet() {
        CameraManager manager = (CameraManager) mActivity.getSystemService("camera");
        try {
            for (String cameraId : manager.getCameraIdList()) {
                Integer facing = (Integer) manager.getCameraCharacteristics(cameraId).get(CameraCharacteristics.LENS_FACING);
                if (facing != null) {
                    if (facing.intValue() == 0) {
                        this.mFrontCameraId = cameraId;
                        CameraParams.haveFrontCamera = true;
                    } else if (facing.intValue() == 1) {
                        this.mRearCameraId = cameraId;
                        CameraParams.haveRearCamera = true;
                    }
                }
            }
            if (CameraParams.noCameras()) {
                Logging.LogDebugPrint(isDebug, "C: Neither front nor rear camera has found", new Object[0]);
            }
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
        setCameraNum(1);
    }

    public void setCameraNum(int cameraNum) {
        Logging.LogDebugPrint(isDebug, "C: setCameraNum(%d)", Integer.valueOf(cameraNum));
        if (CameraParams.noCameras()) {
            Logging.LogDebugPrint(isDebug, "C: No cameras found, ignoring", new Object[0]);
            return;
        }
        if (cameraNum == 1 && !CameraParams.haveFrontCamera) {
            cameraNum = 0;
        } else if (cameraNum == 0 && !CameraParams.haveRearCamera) {
            cameraNum = 1;
        }
        if (cameraNum != this.mCameraNum) {
            this.mFramesSinceCameraChange = 0;
            this.mCameraNum = cameraNum;
        }
    }

    public int start(int requestedWidth, int requestedHeight, int framerateNum, int framerateDenum) {
        if (CameraParams.noCameras()) {
            return -5;
        }
        startBackgroundThread();
        this.mRequestedWidth = requestedWidth;
        this.mRequestedHeight = requestedHeight;
        if (framerateDenum != 0) {
            this.mRequstedFPS = (framerateNum * ImageRequest.DEFAULT_IMAGE_TIMEOUT_MS) / framerateDenum;
        } else {
            this.mRequstedFPS = 15000;
        }
        int res = setUpCameraOutputs(requestedWidth, requestedHeight);
        if (res != 0) {
            Logging.LogDebugPrint(isDebug, "C: setUpCameraOutputs fail", new Object[0]);
            return res;
        }
        res = openCamera();
        if (res != 0) {
            Logging.LogDebugPrint(isDebug, "C: openCamera fail", new Object[0]);
            return res;
        }
        Logging.LogDebugPrint(isDebug, "C: start %dx%d @%d", Integer.valueOf(this.mRequestedWidth), Integer.valueOf(this.mRequestedHeight), Integer.valueOf(this.mRequstedFPS));
        return 0;
    }

    public void setUserOrient(int userAddPreviewOrient, int userAddOutputOrient) {
        Logging.LogDebugPrint(isDebug, "C: setUserOrient() %d %d", Integer.valueOf(userAddPreviewOrient), Integer.valueOf(userAddOutputOrient));
        if (this.mUserAddOutputOrientation != userAddOutputOrient || this.mUserAddPreviewOrientation != userAddPreviewOrient) {
            this.mUserAddOutputOrientation = userAddOutputOrient;
            this.mUserAddPreviewOrientation = userAddPreviewOrient;
        }
    }

    public int stop() {
        closePreviewSession();
        closeCamera();
        return 0;
    }

    public int close() {
        stopBackgroundThread();
        return 0;
    }

    public int[] getResolutionList() {
        return new int[0];
    }

    public void setCameraExposureCompensation(int ec) {
        Logging.LogDebugPrint(isDebug, "C: setCameraExposureCompensation(%d)", Integer.valueOf(ec));
        this.mCurrentExpCompensation = ec;
        if (this.mPreviewRequestBuilder != null) {
            this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AE_EXPOSURE_COMPENSATION, Integer.valueOf(ec));
        }
    }

    public int getCameraExposureCompensation() {
        Logging.LogDebugPrint(isDebug, "C: getCameraExposureCompensation(%d)", Integer.valueOf(this.mCurrentExpCompensation));
        return this.mCurrentExpCompensation;
    }

    public int getCameraExposureCompensationMin() {
        Logging.LogDebugPrint(isDebug, "C: getCameraExposureCompensationMin(%d)", Integer.valueOf(((Integer) this.mExpCompensationRange.getLower()).intValue()));
        return ((Integer) this.mExpCompensationRange.getLower()).intValue();
    }

    public int getCameraExposureCompensationMax() {
        Logging.LogDebugPrint(isDebug, "C: getCameraExposureCompensationMax(%d)", Integer.valueOf(((Integer) this.mExpCompensationRange.getUpper()).intValue()));
        return ((Integer) this.mExpCompensationRange.getUpper()).intValue();
    }

    public float getCameraExposureCompensationStep() {
        Logging.LogDebugPrint(isDebug, "C: getCameraExposureCompensationStep(%f)", Float.valueOf(this.mExpCompensationStep));
        return this.mExpCompensationStep;
    }

    private int openCamera() {
        if (CameraParams.noCameras()) {
            return -5;
        }
        closeCamera();
        CameraManager manager = (CameraManager) mActivity.getSystemService("camera");
        try {
            if (this.mCameraOpenCloseLock.tryAcquire(2500, TimeUnit.MILLISECONDS)) {
                manager.openCamera(this.mCameraNum == 1 ? this.mFrontCameraId : this.mRearCameraId, this.mStateCallback, null);
                return 0;
            }
            throw new RuntimeException("Time out waiting to lock camera opening.");
        } catch (CameraAccessException e) {
            e.printStackTrace();
            return -5;
        } catch (SecurityException e2) {
            Logging.LogDebugPrint(isDebug, "C: The App has no permission", new Object[0]);
            return -5;
        } catch (InterruptedException e3) {
            throw new RuntimeException("Interrupted while trying to lock camera opening.", e3);
        }
    }

    private void closeCamera() {
        try {
            this.mCameraOpenCloseLock.acquire();
            if (this.mPreviewSession != null) {
                this.mPreviewSession.close();
                this.mPreviewSession = null;
            }
            if (this.mCameraDevice != null) {
                this.mCameraDevice.close();
                this.mCameraDevice = null;
            }
            this.mCameraOpenCloseLock.release();
        } catch (InterruptedException e) {
            throw new RuntimeException("Interrupted while trying to lock camera closing.", e);
        } catch (Throwable th) {
            this.mCameraOpenCloseLock.release();
        }
    }

    private Size[] GetOutputSizes() {
        CameraManager manager = (CameraManager) mActivity.getSystemService("camera");
        try {
            for (String cameraId : manager.getCameraIdList()) {
                CameraCharacteristics characteristics = manager.getCameraCharacteristics(cameraId);
                Integer facing = (Integer) characteristics.get(CameraCharacteristics.LENS_FACING);
                if (facing != null && ((this.mCameraNum != 1 || facing.intValue() == 0) && (this.mCameraNum != 0 || facing.intValue() == 1))) {
                    StreamConfigurationMap map = (StreamConfigurationMap) characteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
                    if (map != null) {
                        return map.getOutputSizes(SurfaceHolder.class);
                    }
                }
            }
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    private int setUpCameraOutputs(int width, int height) {
        CameraManager manager = (CameraManager) mActivity.getSystemService("camera");
        try {
            for (String cameraId : manager.getCameraIdList()) {
                CameraCharacteristics characteristics = manager.getCameraCharacteristics(cameraId);
                Integer facing = (Integer) characteristics.get(CameraCharacteristics.LENS_FACING);
                if (facing != null && ((this.mCameraNum != 1 || facing.intValue() == 0) && (this.mCameraNum != 0 || facing.intValue() == 1))) {
                    StreamConfigurationMap map = (StreamConfigurationMap) characteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
                    if (map != null) {
                        this.mOutputOrientation = ((Integer) characteristics.get(CameraCharacteristics.SENSOR_ORIENTATION)).intValue();
                        mActivity.getWindowManager().getDefaultDisplay().getSize(new Point());
                        Size newPreviewSize = chooseCaptureSize(map.getOutputSizes(SurfaceHolder.class), width, height);
                        boolean sizeChanged = this.mPreviewSize == null || !this.mPreviewSize.equals(newPreviewSize);
                        this.mPreviewSize = newPreviewSize;
                        if (this.mImageReader == null || sizeChanged) {
                            this.mImageReader = ImageReader.newInstance(this.mPreviewSize.getWidth(), this.mPreviewSize.getHeight(), 35, 2);
                            this.mImageReader.setOnImageAvailableListener(this.mOnImageAvailableListener, this.mBackgroundHandler);
                        }
                        int orientation = mActivity.getResources().getConfiguration().orientation;
                        Logging.LogDebugPrint(isDebug, "C: set preview size %dx%d'", Integer.valueOf(this.mPreviewSize.getWidth()), Integer.valueOf(this.mPreviewSize.getHeight()));
                        if (orientation == 2) {
                            onPreviewSizeChanged(this.mcObject, this.mPreviewSize.getWidth(), this.mPreviewSize.getHeight());
                        } else {
                            onPreviewSizeChanged(this.mcObject, this.mPreviewSize.getHeight(), this.mPreviewSize.getWidth());
                        }
                        this.mCameraId = cameraId;
                        this.mExpCompensationRange = (Range) characteristics.get(CameraCharacteristics.CONTROL_AE_COMPENSATION_RANGE);
                        if (this.mExpCompensationRange == null) {
                            this.mExpCompensationRange = new Range(Integer.valueOf(0), Integer.valueOf(0));
                        }
                        Rational step = (Rational) characteristics.get(CameraCharacteristics.CONTROL_AE_COMPENSATION_STEP);
                        if (step != null) {
                            this.mExpCompensationStep = step.floatValue();
                        } else {
                            this.mExpCompensationStep = 0.0f;
                        }
                        return 0;
                    }
                }
            }
        } catch (CameraAccessException e) {
            e.printStackTrace();
        } catch (NullPointerException e2) {
            e2.printStackTrace();
        }
        return -5;
    }

    private Size chooseCaptureSize(Size[] sizes, int w, int h) {
        Logging.LogDebugPrint(isDebug, "C: camera size request (%dx%d)", Integer.valueOf(w), Integer.valueOf(h));
        Logging.LogDebugPrint(isDebug, "C: camera supports the following preview sizes:", new Object[0]);
        for (Size sz : sizes) {
            Logging.LogDebugPrint(isDebug, "C:  %dx%d %d'", Integer.valueOf(sz.getWidth()), Integer.valueOf(sz.getHeight()), Integer.valueOf(this.mUserAddOutputOrientation));
        }
        boolean z = 10000 > w || 10000 > h;
        CaptureBase.capAssert(z);
        Size res = null;
        int minMark = 100000000;
        boolean hires_set = false;
        for (Size sz2 : sizes) {
            if (sz2.getWidth() <= 10000 && sz2.getWidth() > 176) {
                int mark = Math.abs(sz2.getWidth() - w) + Math.abs(sz2.getHeight() - h);
                if (sz2.getWidth() >= w && sz2.getHeight() >= h) {
                    if (mark < minMark || !hires_set) {
                        res = new Size(sz2.getWidth(), sz2.getHeight());
                        minMark = mark;
                    }
                    hires_set = true;
                } else if (!hires_set && mark < minMark) {
                    res = new Size(sz2.getWidth(), sz2.getHeight());
                    minMark = mark;
                }
            }
        }
        if (res == null) {
            res = new Size(16, 16);
        }
        Logging.LogDebugPrint(isDebug, "C: result %dx%d", Integer.valueOf(res.getWidth()), Integer.valueOf(res.getHeight()));
        return res;
    }

    private void createCameraSession() {
        closePreviewSession();
        try {
            Surface captureSurface = this.mImageReader.getSurface();
            this.mPreviewRequestBuilder = this.mCameraDevice.createCaptureRequest(3);
            this.mPreviewRequestBuilder.addTarget(captureSurface);
            this.mCameraDevice.createCaptureSession(Arrays.asList(new Surface[]{captureSurface}), new CameraCaptureSession.StateCallback() {
                public void onConfigured(CameraCaptureSession cameraCaptureSession) {
                    if (Capture2.this.mCameraDevice != null) {
                        Capture2.this.mPreviewSession = cameraCaptureSession;
                        Capture2.this.updatePreview();
                    }
                }

                public void onConfigureFailed(CameraCaptureSession cameraCaptureSession) {
                    Logging.LogDebugPrint(CaptureBase.isDebug, "C: createCaptureSession fail", new Object[0]);
                }
            }, this.mBackgroundHandler);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    private void closePreviewSession() {
        if (this.mPreviewSession != null) {
            this.mPreviewSession.close();
            this.mPreviewSession = null;
        }
    }

    private void updatePreview() {
        if (this.mCameraDevice != null) {
            try {
                setUpCaptureRequestBuilder(this.mPreviewRequestBuilder);
                this.mPreviewSession.setRepeatingRequest(this.mPreviewRequestBuilder.build(), null, this.mBackgroundHandler);
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
        }
    }

    private void setUpCaptureRequestBuilder(Builder builder) {
        builder.set(CaptureRequest.CONTROL_MODE, Integer.valueOf(1));
        builder.set(CaptureRequest.CONTROL_AE_EXPOSURE_COMPENSATION, Integer.valueOf(this.mCurrentExpCompensation));
    }

    public void surfaceCreated(SurfaceHolder holder) {
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
    }

    private void startBackgroundThread() {
        this.mBackgroundThread = new HandlerThread("CameraBackground");
        this.mBackgroundThread.start();
        this.mBackgroundHandler = new Handler(this.mBackgroundThread.getLooper());
    }

    private void stopBackgroundThread() {
        if (this.mBackgroundThread != null) {
            this.mBackgroundThread.quitSafely();
            try {
                this.mBackgroundThread.join();
                this.mBackgroundThread = null;
                this.mBackgroundHandler = null;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
