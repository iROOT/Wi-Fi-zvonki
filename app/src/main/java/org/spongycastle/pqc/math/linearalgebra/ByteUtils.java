package org.spongycastle.pqc.math.linearalgebra;

public final class ByteUtils {
    private static final char[] a = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    private ByteUtils() {
    }

    public static boolean a(byte[] bArr, byte[] bArr2) {
        boolean z = true;
        if (bArr == null) {
            if (bArr2 != null) {
                z = false;
            }
            return z;
        } else if (bArr2 == null || bArr.length != bArr2.length) {
            return false;
        } else {
            boolean z2 = true;
            for (int length = bArr.length - 1; length >= 0; length--) {
                int i;
                if (bArr[length] == bArr2[length]) {
                    i = 1;
                } else {
                    i = 0;
                }
                z2 &= i;
            }
            return z2;
        }
    }

    public static byte[] b(byte[] bArr, byte[] bArr2) {
        Object obj = new byte[(bArr.length + bArr2.length)];
        System.arraycopy(bArr, 0, obj, 0, bArr.length);
        System.arraycopy(bArr2, 0, obj, bArr.length, bArr2.length);
        return obj;
    }

    public static byte[][] a(byte[] bArr, int i) {
        if (i > bArr.length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        byte[][] bArr2 = new byte[][]{new byte[i], new byte[(bArr.length - i)]};
        System.arraycopy(bArr, 0, bArr2[0], 0, i);
        System.arraycopy(bArr, i, bArr2[1], 0, bArr.length - i);
        return bArr2;
    }

    public static byte[] a(byte[] bArr, int i, int i2) {
        Object obj = new byte[(i2 - i)];
        System.arraycopy(bArr, i, obj, 0, i2 - i);
        return obj;
    }
}
