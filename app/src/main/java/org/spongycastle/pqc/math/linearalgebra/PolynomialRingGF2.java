package org.spongycastle.pqc.math.linearalgebra;

public final class PolynomialRingGF2 {
    private PolynomialRingGF2() {
    }

    public static int a(int i, int i2, int i3) {
        int i4 = 0;
        int a = a(i, i3);
        int a2 = a(i2, i3);
        if (a2 != 0) {
            int a3 = 1 << a(i3);
            int i5 = a;
            a = a2;
            while (i5 != 0) {
                if (((byte) (i5 & 1)) == (byte) 1) {
                    a2 = i4 ^ a;
                } else {
                    a2 = i4;
                }
                i5 >>>= 1;
                i4 = a << 1;
                if (i4 >= a3) {
                    i4 ^= i3;
                }
                a = i4;
                i4 = a2;
            }
        }
        return i4;
    }

    public static int a(int i) {
        int i2 = -1;
        while (i != 0) {
            i2++;
            i >>>= 1;
        }
        return i2;
    }

    public static int a(int i, int i2) {
        if (i2 == 0) {
            System.err.println("Error: to be divided by 0");
            return 0;
        }
        while (a(i) >= a(i2)) {
            i ^= i2 << (a(i) - a(i2));
        }
        return i;
    }

    public static int b(int i, int i2) {
        while (i2 != 0) {
            int a = a(i, i2);
            i = i2;
            i2 = a;
        }
        return i;
    }

    public static boolean b(int i) {
        if (i == 0) {
            return false;
        }
        int a = a(i) >>> 1;
        int i2 = 2;
        for (int i3 = 0; i3 < a; i3++) {
            i2 = a(i2, i2, i);
            if (b(i2 ^ 2, i) != 1) {
                return false;
            }
        }
        return true;
    }

    public static int c(int i) {
        if (i < 0) {
            System.err.println("The Degree is negative");
            return 0;
        } else if (i > 31) {
            System.err.println("The Degree is more then 31");
            return 0;
        } else if (i == 0) {
            return 1;
        } else {
            int i2 = 1 << (i + 1);
            for (int i3 = (1 << i) + 1; i3 < i2; i3 += 2) {
                if (b(i3)) {
                    return i3;
                }
            }
            return 0;
        }
    }
}
