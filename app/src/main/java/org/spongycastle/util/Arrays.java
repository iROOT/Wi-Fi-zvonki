package org.spongycastle.util;

import java.math.BigInteger;
import net.hockeyapp.android.k;

public final class Arrays {
    private Arrays() {
    }

    public static boolean a(char[] cArr, char[] cArr2) {
        if (cArr == cArr2) {
            return true;
        }
        if (cArr == null || cArr2 == null || cArr.length != cArr2.length) {
            return false;
        }
        for (int i = 0; i != cArr.length; i++) {
            if (cArr[i] != cArr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static boolean a(byte[] bArr, byte[] bArr2) {
        if (bArr == bArr2) {
            return true;
        }
        if (bArr == null || bArr2 == null || bArr.length != bArr2.length) {
            return false;
        }
        for (int i = 0; i != bArr.length; i++) {
            if (bArr[i] != bArr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static boolean b(byte[] bArr, byte[] bArr2) {
        if (bArr == bArr2) {
            return true;
        }
        if (bArr == null || bArr2 == null || bArr.length != bArr2.length) {
            return false;
        }
        int i = 0;
        for (int i2 = 0; i2 != bArr.length; i2++) {
            i |= bArr[i2] ^ bArr2[i2];
        }
        if (i == 0) {
            return true;
        }
        return false;
    }

    public static boolean a(int[] iArr, int[] iArr2) {
        if (iArr == iArr2) {
            return true;
        }
        if (iArr == null || iArr2 == null || iArr.length != iArr2.length) {
            return false;
        }
        for (int i = 0; i != iArr.length; i++) {
            if (iArr[i] != iArr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static boolean a(long[] jArr, long[] jArr2) {
        if (jArr == jArr2) {
            return true;
        }
        if (jArr == null || jArr2 == null || jArr.length != jArr2.length) {
            return false;
        }
        for (int i = 0; i != jArr.length; i++) {
            if (jArr[i] != jArr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static boolean a(Object[] objArr, Object[] objArr2) {
        if (objArr == objArr2) {
            return true;
        }
        if (objArr == null || objArr2 == null || objArr.length != objArr2.length) {
            return false;
        }
        for (int i = 0; i != objArr.length; i++) {
            Object obj = objArr[i];
            Object obj2 = objArr2[i];
            if (obj == null) {
                if (obj2 != null) {
                    return false;
                }
            } else if (!obj.equals(obj2)) {
                return false;
            }
        }
        return true;
    }

    public static boolean a(short[] sArr, short s) {
        for (short s2 : sArr) {
            if (s2 == s) {
                return true;
            }
        }
        return false;
    }

    public static boolean a(int[] iArr, int i) {
        for (int i2 : iArr) {
            if (i2 == i) {
                return true;
            }
        }
        return false;
    }

    public static void a(byte[] bArr, byte b) {
        for (int i = 0; i < bArr.length; i++) {
            bArr[i] = b;
        }
    }

    public static void a(long[] jArr, long j) {
        for (int i = 0; i < jArr.length; i++) {
            jArr[i] = j;
        }
    }

    public static void b(short[] sArr, short s) {
        for (int i = 0; i < sArr.length; i++) {
            sArr[i] = s;
        }
    }

    public static int a(byte[] bArr) {
        if (bArr == null) {
            return 0;
        }
        int length = bArr.length;
        int i = length + 1;
        while (true) {
            length--;
            if (length < 0) {
                return i;
            }
            i = (i * k.DOWNLOAD_FAILED_DIALOG_MESSAGE_ID) ^ bArr[length];
        }
    }

    public static int a(char[] cArr) {
        if (cArr == null) {
            return 0;
        }
        int length = cArr.length;
        int i = length + 1;
        while (true) {
            length--;
            if (length < 0) {
                return i;
            }
            i = (i * k.DOWNLOAD_FAILED_DIALOG_MESSAGE_ID) ^ cArr[length];
        }
    }

    public static int a(int[] iArr) {
        if (iArr == null) {
            return 0;
        }
        int length = iArr.length;
        int i = length + 1;
        while (true) {
            length--;
            if (length < 0) {
                return i;
            }
            i = (i * k.DOWNLOAD_FAILED_DIALOG_MESSAGE_ID) ^ iArr[length];
        }
    }

    public static int a(short[][][] sArr) {
        int i = 0;
        int i2 = 0;
        while (i != sArr.length) {
            i2 = (i2 * k.DOWNLOAD_FAILED_DIALOG_MESSAGE_ID) + a(sArr[i]);
            i++;
        }
        return i2;
    }

    public static int a(short[][] sArr) {
        int i = 0;
        int i2 = 0;
        while (i != sArr.length) {
            i2 = (i2 * k.DOWNLOAD_FAILED_DIALOG_MESSAGE_ID) + a(sArr[i]);
            i++;
        }
        return i2;
    }

    public static int a(short[] sArr) {
        if (sArr == null) {
            return 0;
        }
        int length = sArr.length;
        int i = length + 1;
        while (true) {
            length--;
            if (length < 0) {
                return i;
            }
            i = (i * k.DOWNLOAD_FAILED_DIALOG_MESSAGE_ID) ^ (sArr[length] & 255);
        }
    }

    public static int a(Object[] objArr) {
        if (objArr == null) {
            return 0;
        }
        int length = objArr.length;
        int i = length + 1;
        while (true) {
            length--;
            if (length < 0) {
                return i;
            }
            i = (i * k.DOWNLOAD_FAILED_DIALOG_MESSAGE_ID) ^ objArr[length].hashCode();
        }
    }

    public static byte[] b(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        byte[] bArr2 = new byte[bArr.length];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        return bArr2;
    }

    public static byte[] c(byte[] bArr, byte[] bArr2) {
        if (bArr == null) {
            return null;
        }
        if (bArr2 == null || bArr2.length != bArr.length) {
            return b(bArr);
        }
        System.arraycopy(bArr, 0, bArr2, 0, bArr2.length);
        return bArr2;
    }

    public static byte[][] a(byte[][] bArr) {
        if (bArr == null) {
            return (byte[][]) null;
        }
        byte[][] bArr2 = new byte[bArr.length][];
        for (int i = 0; i != bArr2.length; i++) {
            bArr2[i] = b(bArr[i]);
        }
        return bArr2;
    }

    public static byte[][][] a(byte[][][] bArr) {
        if (bArr == null) {
            return (byte[][][]) null;
        }
        byte[][][] bArr2 = new byte[bArr.length][][];
        for (int i = 0; i != bArr2.length; i++) {
            bArr2[i] = a(bArr[i]);
        }
        return bArr2;
    }

    public static int[] b(int[] iArr) {
        if (iArr == null) {
            return null;
        }
        int[] iArr2 = new int[iArr.length];
        System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
        return iArr2;
    }

    public static long[] a(long[] jArr) {
        if (jArr == null) {
            return null;
        }
        long[] jArr2 = new long[jArr.length];
        System.arraycopy(jArr, 0, jArr2, 0, jArr.length);
        return jArr2;
    }

    public static long[] b(long[] jArr, long[] jArr2) {
        if (jArr == null) {
            return null;
        }
        if (jArr2 == null || jArr2.length != jArr.length) {
            return a(jArr);
        }
        System.arraycopy(jArr, 0, jArr2, 0, jArr2.length);
        return jArr2;
    }

    public static short[] b(short[] sArr) {
        if (sArr == null) {
            return null;
        }
        short[] sArr2 = new short[sArr.length];
        System.arraycopy(sArr, 0, sArr2, 0, sArr.length);
        return sArr2;
    }

    public static BigInteger[] a(BigInteger[] bigIntegerArr) {
        if (bigIntegerArr == null) {
            return null;
        }
        BigInteger[] bigIntegerArr2 = new BigInteger[bigIntegerArr.length];
        System.arraycopy(bigIntegerArr, 0, bigIntegerArr2, 0, bigIntegerArr.length);
        return bigIntegerArr2;
    }

    public static byte[] a(byte[] bArr, int i) {
        Object obj = new byte[i];
        if (i < bArr.length) {
            System.arraycopy(bArr, 0, obj, 0, i);
        } else {
            System.arraycopy(bArr, 0, obj, 0, bArr.length);
        }
        return obj;
    }

    public static int[] b(int[] iArr, int i) {
        Object obj = new int[i];
        if (i < iArr.length) {
            System.arraycopy(iArr, 0, obj, 0, i);
        } else {
            System.arraycopy(iArr, 0, obj, 0, iArr.length);
        }
        return obj;
    }

    public static long[] a(long[] jArr, int i) {
        Object obj = new long[i];
        if (i < jArr.length) {
            System.arraycopy(jArr, 0, obj, 0, i);
        } else {
            System.arraycopy(jArr, 0, obj, 0, jArr.length);
        }
        return obj;
    }

    public static BigInteger[] a(BigInteger[] bigIntegerArr, int i) {
        Object obj = new BigInteger[i];
        if (i < bigIntegerArr.length) {
            System.arraycopy(bigIntegerArr, 0, obj, 0, i);
        } else {
            System.arraycopy(bigIntegerArr, 0, obj, 0, bigIntegerArr.length);
        }
        return obj;
    }

    public static byte[] a(byte[] bArr, int i, int i2) {
        int a = a(i, i2);
        Object obj = new byte[a];
        if (bArr.length - i < a) {
            System.arraycopy(bArr, i, obj, 0, bArr.length - i);
        } else {
            System.arraycopy(bArr, i, obj, 0, a);
        }
        return obj;
    }

    public static int[] a(int[] iArr, int i, int i2) {
        int a = a(i, i2);
        Object obj = new int[a];
        if (iArr.length - i < a) {
            System.arraycopy(iArr, i, obj, 0, iArr.length - i);
        } else {
            System.arraycopy(iArr, i, obj, 0, a);
        }
        return obj;
    }

    public static long[] a(long[] jArr, int i, int i2) {
        int a = a(i, i2);
        Object obj = new long[a];
        if (jArr.length - i < a) {
            System.arraycopy(jArr, i, obj, 0, jArr.length - i);
        } else {
            System.arraycopy(jArr, i, obj, 0, a);
        }
        return obj;
    }

    public static BigInteger[] a(BigInteger[] bigIntegerArr, int i, int i2) {
        int a = a(i, i2);
        Object obj = new BigInteger[a];
        if (bigIntegerArr.length - i < a) {
            System.arraycopy(bigIntegerArr, i, obj, 0, bigIntegerArr.length - i);
        } else {
            System.arraycopy(bigIntegerArr, i, obj, 0, a);
        }
        return obj;
    }

    private static int a(int i, int i2) {
        int i3 = i2 - i;
        if (i3 >= 0) {
            return i3;
        }
        StringBuffer stringBuffer = new StringBuffer(i);
        stringBuffer.append(" > ").append(i2);
        throw new IllegalArgumentException(stringBuffer.toString());
    }

    public static byte[] d(byte[] bArr, byte[] bArr2) {
        if (bArr != null && bArr2 != null) {
            Object obj = new byte[(bArr.length + bArr2.length)];
            System.arraycopy(bArr, 0, obj, 0, bArr.length);
            System.arraycopy(bArr2, 0, obj, bArr.length, bArr2.length);
            return obj;
        } else if (bArr2 != null) {
            return b(bArr2);
        } else {
            return b(bArr);
        }
    }

    public static byte[] a(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        if (bArr != null && bArr2 != null && bArr3 != null) {
            Object obj = new byte[((bArr.length + bArr2.length) + bArr3.length)];
            System.arraycopy(bArr, 0, obj, 0, bArr.length);
            System.arraycopy(bArr2, 0, obj, bArr.length, bArr2.length);
            System.arraycopy(bArr3, 0, obj, bArr.length + bArr2.length, bArr3.length);
            return obj;
        } else if (bArr2 == null) {
            return d(bArr, bArr3);
        } else {
            return d(bArr, bArr2);
        }
    }

    public static byte[] a(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) {
        if (bArr != null && bArr2 != null && bArr3 != null && bArr4 != null) {
            Object obj = new byte[(((bArr.length + bArr2.length) + bArr3.length) + bArr4.length)];
            System.arraycopy(bArr, 0, obj, 0, bArr.length);
            System.arraycopy(bArr2, 0, obj, bArr.length, bArr2.length);
            System.arraycopy(bArr3, 0, obj, bArr.length + bArr2.length, bArr3.length);
            System.arraycopy(bArr4, 0, obj, (bArr.length + bArr2.length) + bArr3.length, bArr4.length);
            return obj;
        } else if (bArr4 == null) {
            return a(bArr, bArr2, bArr3);
        } else {
            if (bArr3 == null) {
                return a(bArr, bArr2, bArr4);
            }
            if (bArr2 == null) {
                return a(bArr, bArr3, bArr4);
            }
            return a(bArr2, bArr3, bArr4);
        }
    }

    public static byte[] b(byte[] bArr, byte b) {
        if (bArr == null) {
            return new byte[]{b};
        }
        int length = bArr.length;
        byte[] bArr2 = new byte[(length + 1)];
        System.arraycopy(bArr, 0, bArr2, 1, length);
        bArr2[0] = b;
        return bArr2;
    }
}
