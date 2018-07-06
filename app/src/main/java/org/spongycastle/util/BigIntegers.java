package org.spongycastle.util;

import java.math.BigInteger;
import java.security.SecureRandom;

public final class BigIntegers {
    private static final BigInteger a = BigInteger.valueOf(0);

    public static byte[] a(BigInteger bigInteger) {
        Object toByteArray = bigInteger.toByteArray();
        if (toByteArray[0] != (byte) 0) {
            return toByteArray;
        }
        Object obj = new byte[(toByteArray.length - 1)];
        System.arraycopy(toByteArray, 1, obj, 0, obj.length);
        return obj;
    }

    public static byte[] a(int i, BigInteger bigInteger) {
        int i2 = 0;
        Object toByteArray = bigInteger.toByteArray();
        if (toByteArray.length == i) {
            return toByteArray;
        }
        if (toByteArray[0] == (byte) 0) {
            i2 = 1;
        }
        int length = toByteArray.length - i2;
        if (length > i) {
            throw new IllegalArgumentException("standard length exceeded for value");
        }
        Object obj = new byte[i];
        System.arraycopy(toByteArray, i2, obj, obj.length - length, length);
        return obj;
    }

    public static BigInteger a(BigInteger bigInteger, BigInteger bigInteger2, SecureRandom secureRandom) {
        int compareTo = bigInteger.compareTo(bigInteger2);
        if (compareTo >= 0) {
            if (compareTo <= 0) {
                return bigInteger;
            }
            throw new IllegalArgumentException("'min' may not be greater than 'max'");
        } else if (bigInteger.bitLength() > bigInteger2.bitLength() / 2) {
            return a(a, bigInteger2.subtract(bigInteger), secureRandom).add(bigInteger);
        } else {
            for (int i = 0; i < 1000; i++) {
                BigInteger bigInteger3 = new BigInteger(bigInteger2.bitLength(), secureRandom);
                if (bigInteger3.compareTo(bigInteger) >= 0 && bigInteger3.compareTo(bigInteger2) <= 0) {
                    return bigInteger3;
                }
            }
            return new BigInteger(bigInteger2.subtract(bigInteger).bitLength() - 1, secureRandom).add(bigInteger);
        }
    }

    public static BigInteger a(byte[] bArr, int i, int i2) {
        if (!(i == 0 && i2 == bArr.length)) {
            Object obj = new byte[i2];
            System.arraycopy(bArr, i, obj, 0, i2);
            bArr = obj;
        }
        return new BigInteger(1, bArr);
    }
}
