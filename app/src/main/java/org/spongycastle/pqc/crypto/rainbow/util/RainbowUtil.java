package org.spongycastle.pqc.crypto.rainbow.util;

import java.lang.reflect.Array;

public class RainbowUtil {
    public static int[] a(byte[] bArr) {
        int[] iArr = new int[bArr.length];
        for (int i = 0; i < bArr.length; i++) {
            iArr[i] = bArr[i] & 255;
        }
        return iArr;
    }

    public static short[] b(byte[] bArr) {
        short[] sArr = new short[bArr.length];
        for (int i = 0; i < bArr.length; i++) {
            sArr[i] = (short) (bArr[i] & 255);
        }
        return sArr;
    }

    public static short[][] a(byte[][] bArr) {
        short[][] sArr = (short[][]) Array.newInstance(Short.TYPE, new int[]{bArr.length, bArr[0].length});
        for (int i = 0; i < bArr.length; i++) {
            for (int i2 = 0; i2 < bArr[0].length; i2++) {
                sArr[i][i2] = (short) (bArr[i][i2] & 255);
            }
        }
        return sArr;
    }

    public static short[][][] a(byte[][][] bArr) {
        short[][][] sArr = (short[][][]) Array.newInstance(Short.TYPE, new int[]{bArr.length, bArr[0].length, bArr[0][0].length});
        for (int i = 0; i < bArr.length; i++) {
            for (int i2 = 0; i2 < bArr[0].length; i2++) {
                for (int i3 = 0; i3 < bArr[0][0].length; i3++) {
                    sArr[i][i2][i3] = (short) (bArr[i][i2][i3] & 255);
                }
            }
        }
        return sArr;
    }

    public static byte[] a(int[] iArr) {
        byte[] bArr = new byte[iArr.length];
        for (int i = 0; i < iArr.length; i++) {
            bArr[i] = (byte) iArr[i];
        }
        return bArr;
    }

    public static byte[] a(short[] sArr) {
        byte[] bArr = new byte[sArr.length];
        for (int i = 0; i < sArr.length; i++) {
            bArr[i] = (byte) sArr[i];
        }
        return bArr;
    }

    public static byte[][] a(short[][] sArr) {
        byte[][] bArr = (byte[][]) Array.newInstance(Byte.TYPE, new int[]{sArr.length, sArr[0].length});
        for (int i = 0; i < sArr.length; i++) {
            for (int i2 = 0; i2 < sArr[0].length; i2++) {
                bArr[i][i2] = (byte) sArr[i][i2];
            }
        }
        return bArr;
    }

    public static byte[][][] a(short[][][] sArr) {
        byte[][][] bArr = (byte[][][]) Array.newInstance(Byte.TYPE, new int[]{sArr.length, sArr[0].length, sArr[0][0].length});
        for (int i = 0; i < sArr.length; i++) {
            for (int i2 = 0; i2 < sArr[0].length; i2++) {
                for (int i3 = 0; i3 < sArr[0][0].length; i3++) {
                    bArr[i][i2][i3] = (byte) sArr[i][i2][i3];
                }
            }
        }
        return bArr;
    }

    public static boolean a(short[] sArr, short[] sArr2) {
        if (sArr.length != sArr2.length) {
            return false;
        }
        boolean z = true;
        for (int length = sArr.length - 1; length >= 0; length--) {
            int i;
            if (sArr[length] == sArr2[length]) {
                i = 1;
            } else {
                i = 0;
            }
            z &= i;
        }
        return z;
    }

    public static boolean a(short[][] sArr, short[][] sArr2) {
        if (sArr.length != sArr2.length) {
            return false;
        }
        boolean z = true;
        int length = sArr.length - 1;
        while (length >= 0) {
            boolean a = a(sArr[length], sArr2[length]) & z;
            length--;
            z = a;
        }
        return z;
    }

    public static boolean a(short[][][] sArr, short[][][] sArr2) {
        if (sArr.length != sArr2.length) {
            return false;
        }
        boolean z = true;
        int length = sArr.length - 1;
        while (length >= 0) {
            boolean a = a(sArr[length], sArr2[length]) & z;
            length--;
            z = a;
        }
        return z;
    }
}
