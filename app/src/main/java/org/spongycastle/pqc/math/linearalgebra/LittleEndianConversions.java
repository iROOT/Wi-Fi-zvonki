package org.spongycastle.pqc.math.linearalgebra;

public final class LittleEndianConversions {
    private LittleEndianConversions() {
    }

    public static int a(byte[] bArr, int i) {
        int i2 = i + 1;
        int i3 = i2 + 1;
        return ((((bArr[i2] & 255) << 8) | (bArr[i] & 255)) | ((bArr[i3] & 255) << 16)) | ((bArr[i3 + 1] & 255) << 24);
    }

    public static int a(byte[] bArr, int i, int i2) {
        int i3 = 0;
        for (int i4 = i2 - 1; i4 >= 0; i4--) {
            i3 |= (bArr[i + i4] & 255) << (i4 * 8);
        }
        return i3;
    }

    public static byte[] a(int i) {
        return new byte[]{(byte) i, (byte) (i >>> 8), (byte) (i >>> 16), (byte) (i >>> 24)};
    }

    public static void a(int i, byte[] bArr, int i2) {
        int i3 = i2 + 1;
        bArr[i2] = (byte) i;
        int i4 = i3 + 1;
        bArr[i3] = (byte) (i >>> 8);
        i3 = i4 + 1;
        bArr[i4] = (byte) (i >>> 16);
        i4 = i3 + 1;
        bArr[i3] = (byte) (i >>> 24);
    }

    public static void a(int i, byte[] bArr, int i2, int i3) {
        for (int i4 = i3 - 1; i4 >= 0; i4--) {
            bArr[i2 + i4] = (byte) (i >>> (i4 * 8));
        }
    }

    public static byte[] a(int[] iArr, int i) {
        int i2 = 0;
        int length = iArr.length;
        byte[] bArr = new byte[i];
        int i3 = 0;
        while (i2 <= length - 2) {
            a(iArr[i2], bArr, i3);
            i2++;
            i3 += 4;
        }
        a(iArr[length - 1], bArr, i3, i - i3);
        return bArr;
    }

    public static int[] a(byte[] bArr) {
        int i = 0;
        int length = (bArr.length + 3) / 4;
        int length2 = bArr.length & 3;
        int[] iArr = new int[length];
        int i2 = 0;
        while (i <= length - 2) {
            iArr[i] = a(bArr, i2);
            i++;
            i2 += 4;
        }
        if (length2 != 0) {
            iArr[length - 1] = a(bArr, i2, length2);
        } else {
            iArr[length - 1] = a(bArr, i2);
        }
        return iArr;
    }
}
