package com.spiritdsp.tsm;

public class Logging {
    private static int contextPtr = 0;

    static native void LogNativePrint(int i, String str);

    static void LogPrint(String fmt, Object... args) {
        System.out.println(String.format(fmt, args));
    }

    static void LogDebugPrint(boolean isDebug, String fmt, Object... args) {
        if (isDebug) {
            LogPrint(fmt, args);
        }
    }

    static void LogNativePrint(String fmt, Object... args) {
        LogFullPrint(contextPtr, String.format(fmt, args));
    }

    static void LogNativePrintErr(String fmt, Object... args) {
        LogFullPrint(contextPtr, "Error!" + String.format(fmt, args));
    }

    static void LogFullPrint(int context, String str) {
        System.out.println(str);
    }
}
