package org.spongycastle.pqc.math.linearalgebra;

public final class IntUtils {
    private IntUtils() {
    }

    public static boolean a(int[] iArr, int[] iArr2) {
        if (iArr.length != iArr2.length) {
            return false;
        }
        boolean z = true;
        for (int length = iArr.length - 1; length >= 0; length--) {
            int i;
            if (iArr[length] == iArr2[length]) {
                i = 1;
            } else {
                i = 0;
            }
            z &= i;
        }
        return z;
    }

    public static int[] a(int[] iArr) {
        Object obj = new int[iArr.length];
        System.arraycopy(iArr, 0, obj, 0, iArr.length);
        return obj;
    }
}
