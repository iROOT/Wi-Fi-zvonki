package com.spiritdsp.tsm;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import com.fgmicrotec.mobile.android.fgmag.VoIP;
import com.spiritdsp.tsm.d.a;

class TSM_impl implements d {
    static final int SPIRIT_VIDEO_FORMAT_BGR24 = 5;
    static final int SPIRIT_VIDEO_FORMAT_GRAYSCALE = 10;
    static final int SPIRIT_VIDEO_FORMAT_RGB24 = 6;
    static final int SPIRIT_VIDEO_FORMAT_RGB32 = 7;
    static final int SPIRIT_VIDEO_FORMAT_RGB555 = 9;
    static final int SPIRIT_VIDEO_FORMAT_RGB565 = 8;
    static final int SPIRIT_VIDEO_FORMAT_UYVYi = 13;
    static final int SPIRIT_VIDEO_FORMAT_VY1UY2i = 15;
    static final int SPIRIT_VIDEO_FORMAT_VYUYi = 11;
    static final int SPIRIT_VIDEO_FORMAT_YUV410 = 3;
    static final int SPIRIT_VIDEO_FORMAT_YUV411 = 4;
    static final int SPIRIT_VIDEO_FORMAT_YUV420 = 0;
    static final int SPIRIT_VIDEO_FORMAT_YUV420i = 17;
    static final int SPIRIT_VIDEO_FORMAT_YUV422 = 1;
    static final int SPIRIT_VIDEO_FORMAT_YUV444 = 2;
    static final int SPIRIT_VIDEO_FORMAT_YUYVi = 12;
    static final int SPIRIT_VIDEO_FORMAT_YV12 = 14;
    static final int SPIRIT_VIDEO_FORMAT_YVU420i = 16;
    static final int mDeviceID = _get_device_id();
    static final boolean mIsAcerE380;
    static final boolean mIsAlcatel7045A;
    static final boolean mIsAlcatel7045Y;
    static final boolean mIsAlcatelPixi35;
    static final boolean mIsAndromax;
    static final boolean mIsAndromaxE2P;
    static final boolean mIsAndromaxE3;
    static final boolean mIsAndromaxQ;
    static final boolean mIsAndromaxR;
    static final boolean mIsAndromaxR2;
    static final boolean mIsGalaxyAdvance;
    static final boolean mIsGalaxyNote;
    static final boolean mIsGalaxyS2;
    static final boolean mIsGalaxyS2Plus;
    static final boolean mIsGalaxyS3;
    static final boolean mIsGalaxyS3mini;
    static final boolean mIsGalaxyS4;
    static final boolean mIsHSL671;
    static final boolean mIsHSL695;
    static final boolean mIsHTCD626;
    static final boolean mIsHTCD628;
    static final boolean mIsHTCOne;
    static final boolean mIsHTCOneA9;
    static final boolean mIsHTCOneM10;
    static final boolean mIsHTCOneM7;
    static final boolean mIsHTCOneM8;
    static final boolean mIsHTCOneM9;
    static final boolean mIsHTCOneS;
    static final boolean mIsHTCdesireSV;
    static final boolean mIsHTCdesireX;
    static final boolean mIsHonor5X;
    static final boolean mIsHuaweiAscend;
    static final boolean mIsInspire4G;
    static final boolean mIsKarbonnTAFone;
    static final boolean mIsKoreanGalaxyS2;
    static final boolean mIsLG_G2;
    static final boolean mIsLgOptimusG;
    static final boolean mIsMI_3W;
    static final boolean mIsMI_4;
    static final boolean mIsMilestone1;
    static final boolean mIsMilestone2;
    static final boolean mIsMotorolaAtrix;
    static final boolean mIsNexus5;
    static final boolean mIsNexus5X;
    static final boolean mIsNexus6;
    static final boolean mIsNexus6P;
    static final boolean mIsNexus7;
    static final boolean mIsQuantaAL7;
    static final boolean mIsSGS5;
    static final boolean mIsSGS6;
    static final boolean mIsSGS7;
    static final boolean mIsSGS8;
    static final boolean mIsSGS8P;
    static final boolean mIsSamsungGalaxyAce;
    static final boolean mIsSamsung_SHW_M250L;
    static final boolean mIsSonyEricssonXArc;
    static final boolean mIsSonyEricssonXMiro;
    static final boolean mIsSonyXperiaJ;
    static final boolean mIsSonyXperiaM2;
    static final boolean mIsSonyXperiaP;
    static final boolean mIsSonyXperiaS;
    static final boolean mIsSonyXperiaZ1;
    static final boolean mIsSonyXperiaZ3;
    static final boolean mIsSonyXperiaZ5;
    static final boolean mIsSonyXperiaZU;
    static final boolean mIsTele2Midi;
    static final boolean mIsTele2Mini;
    static final boolean mIsUnknownDevice = (mDeviceID == 0);
    static final boolean mIsZTE;
    private DisplayMetrics mDM = null;

    private static native int _get_device_id();

    private native String _get_dll_version();

    private native boolean _is_dll_voice_only();

    static {
        boolean z;
        boolean z2 = true;
        if (mDeviceID == VoIP.REASON_CODE_GONE || mDeviceID == 413 || mDeviceID == VoIP.REASON_CODE_CONFLICT || mDeviceID == 414) {
            z = true;
        } else {
            z = false;
        }
        mIsGalaxyS2 = z;
        if (mDeviceID == VoIP.REASON_CODE_CONFLICT || mDeviceID == 413) {
            z = true;
        } else {
            z = false;
        }
        mIsKoreanGalaxyS2 = z;
        if (mDeviceID == 422) {
            z = true;
        } else {
            z = false;
        }
        mIsGalaxyS3 = z;
        if (mDeviceID == 421) {
            z = true;
        } else {
            z = false;
        }
        mIsGalaxyNote = z;
        if (mDeviceID == 425) {
            z = true;
        } else {
            z = false;
        }
        mIsGalaxyAdvance = z;
        if (mDeviceID == 426) {
            z = true;
        } else {
            z = false;
        }
        mIsSamsungGalaxyAce = z;
        if (mDeviceID == 2700) {
            z = true;
        } else {
            z = false;
        }
        mIsSonyEricssonXArc = z;
        if (mDeviceID == 2701) {
            z = true;
        } else {
            z = false;
        }
        mIsSonyXperiaS = z;
        if (mDeviceID == 2702) {
            z = true;
        } else {
            z = false;
        }
        mIsSonyEricssonXMiro = z;
        if (mDeviceID == 2601) {
            z = true;
        } else {
            z = false;
        }
        mIsMilestone1 = z;
        if (mDeviceID == 2600) {
            z = true;
        } else {
            z = false;
        }
        mIsMilestone2 = z;
        if (mDeviceID == 2603) {
            z = true;
        } else {
            z = false;
        }
        mIsMotorolaAtrix = z;
        if (mDeviceID == 18) {
            z = true;
        } else {
            z = false;
        }
        mIsInspire4G = z;
        if (mDeviceID == 23) {
            z = true;
        } else {
            z = false;
        }
        mIsHTCdesireX = z;
        if (mDeviceID == 22) {
            z = true;
        } else {
            z = false;
        }
        mIsHTCdesireSV = z;
        if (mDeviceID == 21) {
            z = true;
        } else {
            z = false;
        }
        mIsHTCOneS = z;
        if (mDeviceID == 413) {
            z = true;
        } else {
            z = false;
        }
        mIsSamsung_SHW_M250L = z;
        if (mDeviceID == 3200) {
            z = true;
        } else {
            z = false;
        }
        mIsKarbonnTAFone = z;
        if (mDeviceID == 3000) {
            z = true;
        } else {
            z = false;
        }
        mIsHuaweiAscend = z;
        if (mDeviceID == VoIP.REASON_CODE_SERVER_NOT_IMPLEMENTED) {
            z = true;
        } else {
            z = false;
        }
        mIsQuantaAL7 = z;
        if (mDeviceID == 2900) {
            z = true;
        } else {
            z = false;
        }
        mIsNexus7 = z;
        if (mDeviceID == 2204) {
            z = true;
        } else {
            z = false;
        }
        mIsLgOptimusG = z;
        if (mDeviceID == 430) {
            z = true;
        } else {
            z = false;
        }
        mIsGalaxyS4 = z;
        if (mDeviceID == 3100) {
            z = true;
        } else {
            z = false;
        }
        mIsZTE = z;
        if (mDeviceID == 429) {
            z = true;
        } else {
            z = false;
        }
        mIsGalaxyS2Plus = z;
        if (mDeviceID == 432) {
            z = true;
        } else {
            z = false;
        }
        mIsGalaxyS3mini = z;
        if (mDeviceID == 2207) {
            z = true;
        } else {
            z = false;
        }
        mIsLG_G2 = z;
        if (mDeviceID == 2703) {
            z = true;
        } else {
            z = false;
        }
        mIsSonyXperiaJ = z;
        if (mDeviceID == 2704) {
            z = true;
        } else {
            z = false;
        }
        mIsSonyXperiaP = z;
        if (mDeviceID == 2709) {
            z = true;
        } else {
            z = false;
        }
        mIsSonyXperiaM2 = z;
        if (mDeviceID == 2706) {
            z = true;
        } else {
            z = false;
        }
        mIsSonyXperiaZ3 = z;
        if (mDeviceID == 2707) {
            z = true;
        } else {
            z = false;
        }
        mIsSonyXperiaZ1 = z;
        if (mDeviceID == 3300) {
            z = true;
        } else {
            z = false;
        }
        mIsMI_3W = z;
        if (mDeviceID == 3301) {
            z = true;
        } else {
            z = false;
        }
        mIsMI_4 = z;
        if (mDeviceID == 2206) {
            z = true;
        } else {
            z = false;
        }
        mIsNexus5 = z;
        if (mDeviceID == 2208) {
            z = true;
        } else {
            z = false;
        }
        mIsNexus5X = z;
        if (mDeviceID == 2604) {
            z = true;
        } else {
            z = false;
        }
        mIsNexus6 = z;
        if (mDeviceID == 3002) {
            z = true;
        } else {
            z = false;
        }
        mIsNexus6P = z;
        if (mDeviceID == 3400) {
            z = true;
        } else {
            z = false;
        }
        mIsHSL695 = z;
        if (mDeviceID == 3401) {
            z = true;
        } else {
            z = false;
        }
        mIsHSL671 = z;
        if (mDeviceID == 3500 || mDeviceID == 3501 || mDeviceID == 3502 || mDeviceID == 3503 || mDeviceID == 3504 || mDeviceID == 3505 || mDeviceID == 3506) {
            z = true;
        } else {
            z = false;
        }
        mIsAndromax = z;
        if (mDeviceID == 3502) {
            z = true;
        } else {
            z = false;
        }
        mIsAndromaxQ = z;
        if (mDeviceID == 3503) {
            z = true;
        } else {
            z = false;
        }
        mIsAndromaxE3 = z;
        if (mDeviceID == 3504) {
            z = true;
        } else {
            z = false;
        }
        mIsAndromaxR = z;
        if (mDeviceID == 3505) {
            z = true;
        } else {
            z = false;
        }
        mIsAndromaxE2P = z;
        if (mDeviceID == 3506) {
            z = true;
        } else {
            z = false;
        }
        mIsAndromaxR2 = z;
        if (mDeviceID == 24) {
            z = true;
        } else {
            z = false;
        }
        mIsHTCOneA9 = z;
        if (mDeviceID == 25) {
            z = true;
        } else {
            z = false;
        }
        mIsHTCOneM7 = z;
        if (mDeviceID == 30) {
            z = true;
        } else {
            z = false;
        }
        mIsHTCOneM8 = z;
        if (mDeviceID == 26) {
            z = true;
        } else {
            z = false;
        }
        mIsHTCOneM9 = z;
        if (mDeviceID == 27) {
            z = true;
        } else {
            z = false;
        }
        mIsHTCOneM10 = z;
        if (mDeviceID == 28) {
            z = true;
        } else {
            z = false;
        }
        mIsHTCD628 = z;
        if (mDeviceID == 29) {
            z = true;
        } else {
            z = false;
        }
        mIsHTCD626 = z;
        if (mDeviceID == 433) {
            z = true;
        } else {
            z = false;
        }
        mIsSGS5 = z;
        if (mDeviceID == 434 || mDeviceID == 435) {
            z = true;
        } else {
            z = false;
        }
        mIsSGS6 = z;
        if (mDeviceID == 436 || mDeviceID == 437) {
            z = true;
        } else {
            z = false;
        }
        mIsSGS7 = z;
        if (mDeviceID == 438 || mDeviceID == 439) {
            z = true;
        } else {
            z = false;
        }
        mIsSGS8 = z;
        if (mDeviceID == 439) {
            z = true;
        } else {
            z = false;
        }
        mIsSGS8P = z;
        if (mDeviceID == 3001) {
            z = true;
        } else {
            z = false;
        }
        mIsHonor5X = z;
        if (mDeviceID == 1101) {
            z = true;
        } else {
            z = false;
        }
        mIsAcerE380 = z;
        if (mDeviceID == 3600) {
            z = true;
        } else {
            z = false;
        }
        mIsTele2Mini = z;
        if (mDeviceID == 3601) {
            z = true;
        } else {
            z = false;
        }
        mIsTele2Midi = z;
        if (mDeviceID == 2705) {
            z = true;
        } else {
            z = false;
        }
        mIsSonyXperiaZU = z;
        if (mDeviceID == 2708) {
            z = true;
        } else {
            z = false;
        }
        mIsSonyXperiaZ5 = z;
        if (mDeviceID == 3700) {
            z = true;
        } else {
            z = false;
        }
        mIsAlcatel7045A = z;
        if (mDeviceID == 3701) {
            z = true;
        } else {
            z = false;
        }
        mIsAlcatel7045Y = z;
        if (mDeviceID == 3702) {
            z = true;
        } else {
            z = false;
        }
        mIsAlcatelPixi35 = z;
        if (mDeviceID != 31) {
            z2 = false;
        }
        mIsHTCOne = z2;
    }

    public TSM_impl(Activity activity) {
        Logging.LogPrint("TSM:<init>", new Object[0]);
        log_build_info();
        if (!("1.0.2.952".equals("local") || "".equals("local"))) {
            String str;
            if (!_get_dll_version().equals("1.0.2.952") && !_is_dll_voice_only()) {
                str = "ERROR: Media Manager SO doesn't match jar! Expected version = 1.0.2.952";
                Logging.LogPrint("ERROR: Media Manager SO doesn't match jar! Expected version = 1.0.2.952", new Object[0]);
                Logging.LogNativePrintErr("ERROR: Media Manager SO doesn't match jar! Expected version = 1.0.2.952", new Object[0]);
                throw new Error("ERROR: Media Manager SO doesn't match jar! Expected version = 1.0.2.952");
            } else if (!_get_dll_version().equals("") && _is_dll_voice_only()) {
                str = "ERROR: Media Manager SO doesn't match jar! Expected version = ";
                Logging.LogPrint("ERROR: Media Manager SO doesn't match jar! Expected version = ", new Object[0]);
                Logging.LogNativePrintErr("ERROR: Media Manager SO doesn't match jar! Expected version = ", new Object[0]);
                throw new Error("ERROR: Media Manager SO doesn't match jar! Expected version = ");
            }
        }
        this.mDM = new DisplayMetrics();
        if (activity != null) {
            setContext(activity);
        }
    }

    public void setContext(Activity activity) {
        Logging.LogPrint("TSM:setContext: %h", activity);
        if (activity == null) {
            String str = "ERROR: can't setup NULL context!";
            Logging.LogPrint("ERROR: can't setup NULL context!", new Object[0]);
            Logging.LogNativePrintErr("ERROR: can't setup NULL context!", new Object[0]);
            throw new Error("ERROR: can't setup NULL context!");
        }
        activity.getWindowManager().getDefaultDisplay().getMetrics(this.mDM);
        Capture.setActivity(activity);
        Audio.setContext(activity);
    }

    public void setOption(a aVar, Object obj) {
        switch (aVar) {
            case EnableAudioVolumeObservation:
                boolean booleanValue;
                if (obj instanceof Boolean) {
                    booleanValue = ((Boolean) obj).booleanValue();
                } else if (obj instanceof Integer) {
                    booleanValue = ((Integer) obj).intValue() != 0;
                } else {
                    throw new IllegalArgumentException("boolean is expected");
                }
                Audio.enableAudioVolumeObservation(booleanValue);
                return;
            default:
                return;
        }
    }

    public View createVideoView(Context context, int... iArr) {
        Logging.LogPrint("TSM:createVideoView", new Object[0]);
        if (context instanceof Activity) {
            Capture.setActivity((Activity) context);
        }
        return new VideoView(context, iArr);
    }

    private void log_build_info() {
    }
}
