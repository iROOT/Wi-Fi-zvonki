package org.spongycastle.pqc.math.ntru.util;

public class ArrayEncoder {
    private static final int[] a = new int[]{0, 0, 0, 1, 1, 1, -1, -1};
    private static final int[] b = new int[]{0, 1, -1, 0, 1, -1, 0, 1};
    private static final int[] c = new int[]{1, 1, 1, 0, 0, 0, 1, 0, 1};
    private static final int[] d = new int[]{1, 1, 1, 1, 0, 0, 0, 1, 0};
    private static final int[] e = new int[]{1, 0, 1, 0, 0, 1, 1, 1, 0};

    public static byte[] a(int[] iArr, int i) {
        int numberOfLeadingZeros = 31 - Integer.numberOfLeadingZeros(i);
        byte[] bArr = new byte[(((iArr.length * numberOfLeadingZeros) + 7) / 8)];
        int i2 = 0;
        int i3 = 0;
        for (int i4 : iArr) {
            for (int i5 = 0; i5 < numberOfLeadingZeros; i5++) {
                bArr[i2] = (byte) ((((i4 >> i5) & 1) << i3) | bArr[i2]);
                if (i3 == 7) {
                    i2++;
                    i3 = 0;
                } else {
                    i3++;
                }
            }
        }
        return bArr;
    }

    public static int[] a(byte[] bArr, int i, int i2) {
        int i3 = 0;
        int[] iArr = new int[i];
        int numberOfLeadingZeros = 31 - Integer.numberOfLeadingZeros(i2);
        int i4 = i * numberOfLeadingZeros;
        int i5 = 0;
        while (i5 < i4) {
            if (i5 > 0 && i5 % numberOfLeadingZeros == 0) {
                i3++;
            }
            iArr[i3] = (b(bArr, i5) << (i5 % numberOfLeadingZeros)) + iArr[i3];
            i5++;
        }
        return iArr;
    }

    public static int[] a(byte[] bArr, int i) {
        int i2 = 0;
        int[] iArr = new int[i];
        int i3 = 0;
        while (i2 < bArr.length * 8) {
            int i4 = i2 + 1;
            int b = b(bArr, i2);
            int i5 = i4 + 1;
            i2 = i5 + 1;
            i4 = ((b(bArr, i4) * 2) + (b * 4)) + b(bArr, i5);
            b = i3 + 1;
            iArr[i3] = a[i4];
            i3 = b + 1;
            iArr[b] = b[i4];
            if (i3 > i - 2) {
                break;
            }
        }
        return iArr;
    }

    public static byte[] a(int[] iArr) {
        byte[] bArr = new byte[(((((iArr.length * 3) + 1) / 2) + 7) / 8)];
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (i < (iArr.length / 2) * 2) {
            int i4 = i + 1;
            int i5 = iArr[i] + 1;
            i = i4 + 1;
            i4 = iArr[i4] + 1;
            if (i5 == 0 && i4 == 0) {
                throw new IllegalStateException("Illegal encoding!");
            }
            i4 += i5 * 3;
            int[] iArr2 = new int[]{c[i4], d[i4], e[i4]};
            for (i4 = 0; i4 < 3; i4++) {
                bArr[i2] = (byte) (bArr[i2] | (iArr2[i4] << i3));
                if (i3 == 7) {
                    i2++;
                    i3 = 0;
                } else {
                    i3++;
                }
            }
        }
        return bArr;
    }

    private static int b(byte[] bArr, int i) {
        return ((bArr[i / 8] & 255) >> (i % 8)) & 1;
    }
}
