package com.fgmicrotec.mobile.android.fgmag;

public class FgSDKLoader {
    public native void fgExit();

    public native String fgGetAppBuildDateStr();

    public native String fgGetAppVerStr();

    public native void fgInit(boolean z, boolean z2, String str, String str2, String str3);

    public native void fgSetTraceLevel(int i, int i2);
}
