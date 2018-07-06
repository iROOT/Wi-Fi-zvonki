package org.spongycastle.pqc.crypto.mceliece;

import java.math.BigInteger;
import org.spongycastle.pqc.math.linearalgebra.BigIntUtils;
import org.spongycastle.pqc.math.linearalgebra.GF2Vector;
import org.spongycastle.pqc.math.linearalgebra.IntegerFunctions;

final class Conversions {
    private static final BigInteger a = BigInteger.valueOf(0);
    private static final BigInteger b = BigInteger.valueOf(1);

    private Conversions() {
    }

    public static GF2Vector a(int i, int i2, byte[] bArr) {
        if (i < i2) {
            throw new IllegalArgumentException("n < t");
        }
        BigInteger a = IntegerFunctions.a(i, i2);
        BigInteger bigInteger = new BigInteger(1, bArr);
        if (bigInteger.compareTo(a) >= 0) {
            throw new IllegalArgumentException("Encoded number too large.");
        }
        GF2Vector gF2Vector = new GF2Vector(i);
        int i3 = i;
        int i4 = i2;
        for (int i5 = 0; i5 < i; i5++) {
            a = a.multiply(BigInteger.valueOf((long) (i3 - i4))).divide(BigInteger.valueOf((long) i3));
            i3--;
            if (a.compareTo(bigInteger) <= 0) {
                gF2Vector.a(i5);
                bigInteger = bigInteger.subtract(a);
                i4--;
                if (i3 == i4) {
                    a = b;
                } else {
                    a = a.multiply(BigInteger.valueOf((long) (i4 + 1))).divide(BigInteger.valueOf((long) (i3 - i4)));
                }
            }
        }
        return gF2Vector;
    }

    public static byte[] a(int i, int i2, GF2Vector gF2Vector) {
        if (gF2Vector.e() == i && gF2Vector.c() == i2) {
            int[] b = gF2Vector.b();
            BigInteger a = IntegerFunctions.a(i, i2);
            BigInteger bigInteger = a;
            int i3 = i;
            int i4 = i2;
            for (int i5 = 0; i5 < i; i5++) {
                a = a.multiply(BigInteger.valueOf((long) (i3 - i4))).divide(BigInteger.valueOf((long) i3));
                i3--;
                if ((b[i5 >> 5] & (1 << (i5 & 31))) != 0) {
                    bigInteger = bigInteger.add(a);
                    i4--;
                    if (i3 == i4) {
                        a = b;
                    } else {
                        a = a.multiply(BigInteger.valueOf((long) (i4 + 1))).divide(BigInteger.valueOf((long) (i3 - i4)));
                    }
                }
            }
            return BigIntUtils.a(bigInteger);
        }
        throw new IllegalArgumentException("vector has wrong length or hamming weight");
    }
}
