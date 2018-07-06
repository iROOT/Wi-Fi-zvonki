package com.spiritdsp.tsm;

import android.support.v4.view.MotionEventCompat;

public class CameraParams {
    static final int FRONT_CAMERA = 1;
    static final int REAR_CAMERA = 0;
    static boolean haveFrontCamera;
    static boolean haveRearCamera = true;
    static Integer[] naturalOrientation = new Integer[]{null, null};
    static boolean[] needReleaseOnSurfaceChange = new boolean[]{false, false};
    static boolean needReleaseOnSurfaceLost;
    static boolean needToCheckForBlackFrames;

    static {
        haveFrontCamera = true;
        needReleaseOnSurfaceLost = false;
        needToCheckForBlackFrames = false;
        switch (TSM_impl.mDeviceID) {
            case 19:
                needReleaseOnSurfaceLost = true;
                return;
            case MotionEventCompat.AXIS_WHEEL /*21*/:
                needReleaseOnSurfaceLost = true;
                return;
            case MotionEventCompat.AXIS_GAS /*22*/:
            case MotionEventCompat.AXIS_BRAKE /*23*/:
                needReleaseOnSurfaceLost = true;
                return;
            case 408:
                naturalOrientation[1] = Integer.valueOf(0);
                return;
            case 409:
            case 410:
            case 413:
            case 414:
                needReleaseOnSurfaceChange[0] = true;
                needReleaseOnSurfaceChange[1] = true;
                return;
            case 420:
                needToCheckForBlackFrames = true;
                return;
            case 423:
                naturalOrientation[0] = Integer.valueOf(270);
                needReleaseOnSurfaceChange[0] = true;
                needReleaseOnSurfaceChange[1] = true;
                needReleaseOnSurfaceLost = true;
                return;
            case 425:
                needReleaseOnSurfaceLost = true;
                return;
            case 2202:
                needReleaseOnSurfaceChange[0] = true;
                needReleaseOnSurfaceChange[1] = true;
                return;
            case 2600:
                needReleaseOnSurfaceChange[0] = true;
                haveFrontCamera = false;
                return;
            case 2601:
                haveFrontCamera = false;
                return;
            case 2603:
                needReleaseOnSurfaceChange[0] = true;
                needReleaseOnSurfaceChange[1] = true;
                return;
            case 3100:
                naturalOrientation[1] = Integer.valueOf(270);
                return;
            default:
                return;
        }
    }

    static void initDefaultNaturalOrientation() {
        if (naturalOrientation[0] == null) {
            naturalOrientation[0] = Integer.valueOf(90);
        }
        if (naturalOrientation[1] == null) {
            naturalOrientation[1] = Integer.valueOf(90);
        }
    }

    public static boolean noCameras() {
        return (haveFrontCamera && haveRearCamera) ? false : true;
    }
}
