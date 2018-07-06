package org.spongycastle.crypto.util;

public abstract class Pack {
    public static int a(byte[] bArr, int i) {
        int i2 = i + 1;
        i2++;
        return (((bArr[i] << 24) | ((bArr[i2] & 255) << 16)) | ((bArr[i2] & 255) << 8)) | (bArr[i2 + 1] & 255);
    }

    public static void a(byte[] bArr, int i, int[] iArr) {
        for (int i2 = 0; i2 < iArr.length; i2++) {
            iArr[i2] = a(bArr, i);
            i += 4;
        }
    }

    public static byte[] a(int i) {
        byte[] bArr = new byte[4];
        a(i, bArr, 0);
        return bArr;
    }

    public static void a(int i, byte[] bArr, int i2) {
        bArr[i2] = (byte) (i >>> 24);
        int i3 = i2 + 1;
        bArr[i3] = (byte) (i >>> 16);
        i3++;
        bArr[i3] = (byte) (i >>> 8);
        bArr[i3 + 1] = (byte) i;
    }

    public static byte[] a(int[] iArr) {
        byte[] bArr = new byte[(iArr.length * 4)];
        a(iArr, bArr, 0);
        return bArr;
    }

    public static void a(int[] iArr, byte[] bArr, int i) {
        for (int a : iArr) {
            a(a, bArr, i);
            i += 4;
        }
    }

    public static long b(byte[] bArr, int i) {
        return (((long) a(bArr, i + 4)) & 4294967295L) | ((((long) a(bArr, i)) & 4294967295L) << 32);
    }

    public static void a(long j, byte[] bArr, int i) {
        a((int) (j >>> 32), bArr, i);
        a((int) (4294967295L & j), bArr, i + 4);
    }

    public static int c(byte[] bArr, int i) {
        int i2 = i + 1;
        i2++;
        return (((bArr[i] & 255) | ((bArr[i2] & 255) << 8)) | ((bArr[i2] & 255) << 16)) | (bArr[i2 + 1] << 24);
    }

    public static void b(int i, byte[] bArr, int i2) {
        bArr[i2] = (byte) i;
        int i3 = i2 + 1;
        bArr[i3] = (byte) (i >>> 8);
        i3++;
        bArr[i3] = (byte) (i >>> 16);
        bArr[i3 + 1] = (byte) (i >>> 24);
    }

    public static void b(int[] iArr, byte[] bArr, int i) {
        for (int b : iArr) {
            b(b, bArr, i);
            i += 4;
        }
    }

    public static long d(byte[] bArr, int i) {
        return (((long) c(bArr, i)) & 4294967295L) | ((((long) c(bArr, i + 4)) & 4294967295L) << 32);
    }

    public static void b(long j, byte[] bArr, int i) {
        b((int) (4294967295L & j), bArr, i);
        b((int) (j >>> 32), bArr, i + 4);
    }
}
