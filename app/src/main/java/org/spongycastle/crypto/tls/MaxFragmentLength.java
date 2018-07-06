package org.spongycastle.crypto.tls;

public class MaxFragmentLength {
    public static short a = (short) 1;
    public static short b = (short) 2;
    public static short c = (short) 3;
    public static short d = (short) 4;

    public static boolean a(short s) {
        return s >= a && s <= d;
    }
}
