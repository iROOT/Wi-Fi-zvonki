package org.spongycastle.pqc.math.linearalgebra;

import java.math.BigInteger;

public final class BigIntUtils {
    private BigIntUtils() {
    }

    public static byte[] a(BigInteger bigInteger) {
        Object toByteArray = bigInteger.toByteArray();
        if (toByteArray.length == 1 || (bigInteger.bitLength() & 7) != 0) {
            return toByteArray;
        }
        byte[] bArr = new byte[(bigInteger.bitLength() >> 3)];
        System.arraycopy(toByteArray, 1, bArr, 0, bArr.length);
        return bArr;
    }
}
