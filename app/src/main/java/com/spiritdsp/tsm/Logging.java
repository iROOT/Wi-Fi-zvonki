package com.spiritdsp.tsm;

public class Logging {
    private static int contextPtr = 0;

    static native void LogNativePrint(int i, String str);

    static void LogPrint(String str, Object... objArr) {
        System.out.println(String.format(str, objArr));
    }

    static void LogDebugPrint(boolean z, String str, Object... objArr) {
        if (z) {
            LogPrint(str, objArr);
        }
    }

    static void LogNativePrint(String str, Object... objArr) {
        LogFullPrint(contextPtr, String.format(str, objArr));
    }

    static void LogNativePrintErr(String str, Object... objArr) {
        LogFullPrint(contextPtr, "Error!" + String.format(str, objArr));
    }

    static void LogFullPrint(int i, String str) {
        System.out.println(str);
    }
}
