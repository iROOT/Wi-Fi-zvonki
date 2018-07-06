package org.spongycastle.crypto.generators;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.spongycastle.util.BigIntegers;

class DHParametersHelper {
    private static final BigInteger a = BigInteger.valueOf(1);
    private static final BigInteger b = BigInteger.valueOf(2);

    DHParametersHelper() {
    }

    static BigInteger[] a(int i, int i2, SecureRandom secureRandom) {
        BigInteger bigInteger;
        int i3 = i - 1;
        while (true) {
            bigInteger = new BigInteger(i3, 2, secureRandom);
            if (!bigInteger.shiftLeft(1).add(a).isProbablePrime(i2) || (i2 > 2 && !bigInteger.isProbablePrime(i2))) {
            }
        }
        return new BigInteger[]{r2, bigInteger};
    }

    static BigInteger a(BigInteger bigInteger, BigInteger bigInteger2, SecureRandom secureRandom) {
        BigInteger modPow;
        BigInteger subtract = bigInteger.subtract(b);
        do {
            modPow = BigIntegers.a(b, subtract, secureRandom).modPow(b, bigInteger);
        } while (modPow.equals(a));
        return modPow;
    }
}
