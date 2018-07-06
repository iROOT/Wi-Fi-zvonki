package org.spongycastle.crypto.modes.gcm;

import org.spongycastle.crypto.util.Pack;
import org.spongycastle.util.Arrays;

abstract class GCMUtil {
    private static final int[] a = b();

    GCMUtil() {
    }

    private static int[] b() {
        int[] iArr = new int[256];
        for (int i = 0; i < 256; i++) {
            int i2 = 0;
            for (int i3 = 7; i3 >= 0; i3--) {
                if (((1 << i3) & i) != 0) {
                    i2 ^= -520093696 >>> (7 - i3);
                }
            }
            iArr[i] = i2;
        }
        return iArr;
    }

    static int[] a() {
        int[] iArr = new int[4];
        iArr[0] = Integer.MIN_VALUE;
        return iArr;
    }

    static void a(int[] iArr, byte[] bArr) {
        Pack.a(iArr, bArr, 0);
    }

    static int[] a(byte[] bArr) {
        int[] iArr = new int[4];
        Pack.a(bArr, 0, iArr);
        return iArr;
    }

    static void a(byte[] bArr, int[] iArr) {
        Pack.a(bArr, 0, iArr);
    }

    static void a(byte[] bArr, byte[] bArr2) {
        byte[] b = Arrays.b(bArr);
        byte[] bArr3 = new byte[16];
        for (int i = 0; i < 16; i++) {
            byte b2 = bArr2[i];
            for (int i2 = 7; i2 >= 0; i2--) {
                if (((1 << i2) & b2) != 0) {
                    b(bArr3, b);
                }
                if (b(b) != (byte) 0) {
                    b[0] = (byte) (b[0] ^ -31);
                }
            }
        }
        System.arraycopy(bArr3, 0, bArr, 0, 16);
    }

    static void a(int[] iArr, int[] iArr2) {
        int[] b = Arrays.b(iArr);
        Object obj = new int[4];
        for (int i = 0; i < 4; i++) {
            int i2 = iArr2[i];
            for (int i3 = 31; i3 >= 0; i3--) {
                if (((1 << i3) & i2) != 0) {
                    e(obj, b);
                }
                if (a(b) != 0) {
                    b[0] = b[0] ^ -520093696;
                }
            }
        }
        System.arraycopy(obj, 0, iArr, 0, 4);
    }

    static void b(int[] iArr, int[] iArr2) {
        if (d(iArr, iArr2) != 0) {
            iArr2[0] = iArr2[0] ^ -520093696;
        }
    }

    static void c(int[] iArr, int[] iArr2) {
        int a = a(iArr, 8, iArr2);
        iArr2[0] = a[a >>> 24] ^ iArr2[0];
    }

    static byte b(byte[] bArr) {
        int i = 0;
        int i2 = 0;
        do {
            int i3 = bArr[i2] & 255;
            int i4 = i2 + 1;
            bArr[i2] = (byte) (i | (i3 >>> 1));
            i = (i3 & 1) << 7;
            i2 = bArr[i4] & 255;
            i3 = i4 + 1;
            bArr[i4] = (byte) (i | (i2 >>> 1));
            i = (i2 & 1) << 7;
            i2 = bArr[i3] & 255;
            i4 = i3 + 1;
            bArr[i3] = (byte) (i | (i2 >>> 1));
            i = (i2 & 1) << 7;
            i3 = bArr[i4] & 255;
            i2 = i4 + 1;
            bArr[i4] = (byte) (i | (i3 >>> 1));
            i = (i3 & 1) << 7;
        } while (i2 < 16);
        return (byte) i;
    }

    static int a(int[] iArr) {
        int i = iArr[0];
        iArr[0] = i >>> 1;
        i <<= 31;
        int i2 = iArr[1];
        iArr[1] = i | (i2 >>> 1);
        i = i2 << 31;
        i2 = iArr[2];
        iArr[2] = i | (i2 >>> 1);
        i = i2 << 31;
        i2 = iArr[3];
        iArr[3] = i | (i2 >>> 1);
        return i2 << 31;
    }

    static int d(int[] iArr, int[] iArr2) {
        int i = iArr[0];
        iArr2[0] = i >>> 1;
        i <<= 31;
        int i2 = iArr[1];
        iArr2[1] = i | (i2 >>> 1);
        i = i2 << 31;
        i2 = iArr[2];
        iArr2[2] = i | (i2 >>> 1);
        i = i2 << 31;
        i2 = iArr[3];
        iArr2[3] = i | (i2 >>> 1);
        return i2 << 31;
    }

    static int a(int[] iArr, int i, int[] iArr2) {
        int i2 = iArr[0];
        int i3 = 32 - i;
        iArr2[0] = i2 >>> i;
        i2 <<= i3;
        int i4 = iArr[1];
        iArr2[1] = i2 | (i4 >>> i);
        i2 = i4 << i3;
        i4 = iArr[2];
        iArr2[2] = i2 | (i4 >>> i);
        i2 = i4 << i3;
        i4 = iArr[3];
        iArr2[3] = i2 | (i4 >>> i);
        return i4 << i3;
    }

    static void b(byte[] bArr, byte[] bArr2) {
        int i = 0;
        do {
            bArr[i] = (byte) (bArr[i] ^ bArr2[i]);
            i++;
            bArr[i] = (byte) (bArr[i] ^ bArr2[i]);
            i++;
            bArr[i] = (byte) (bArr[i] ^ bArr2[i]);
            i++;
            bArr[i] = (byte) (bArr[i] ^ bArr2[i]);
            i++;
        } while (i < 16);
    }

    static void e(int[] iArr, int[] iArr2) {
        iArr[0] = iArr[0] ^ iArr2[0];
        iArr[1] = iArr[1] ^ iArr2[1];
        iArr[2] = iArr[2] ^ iArr2[2];
        iArr[3] = iArr[3] ^ iArr2[3];
    }

    static void a(int[] iArr, int[] iArr2, int[] iArr3) {
        iArr3[0] = iArr[0] ^ iArr2[0];
        iArr3[1] = iArr[1] ^ iArr2[1];
        iArr3[2] = iArr[2] ^ iArr2[2];
        iArr3[3] = iArr[3] ^ iArr2[3];
    }
}
