package org.spongycastle.crypto.agreement.jpake;

import java.math.BigInteger;

public class JPAKEPrimeOrderGroup {
    private final BigInteger a;
    private final BigInteger b;
    private final BigInteger c;

    JPAKEPrimeOrderGroup(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, boolean z) {
        JPAKEUtil.a(bigInteger, "p");
        JPAKEUtil.a(bigInteger2, "q");
        JPAKEUtil.a(bigInteger3, "g");
        if (!z) {
            if (!bigInteger.subtract(JPAKEUtil.b).mod(bigInteger2).equals(JPAKEUtil.a)) {
                throw new IllegalArgumentException("p-1 must be evenly divisible by q");
            } else if (bigInteger3.compareTo(BigInteger.valueOf(2)) == -1 || bigInteger3.compareTo(bigInteger.subtract(JPAKEUtil.b)) == 1) {
                throw new IllegalArgumentException("g must be in [2, p-1]");
            } else if (!bigInteger3.modPow(bigInteger2, bigInteger).equals(JPAKEUtil.b)) {
                throw new IllegalArgumentException("g^q mod p must equal 1");
            } else if (!bigInteger.isProbablePrime(20)) {
                throw new IllegalArgumentException("p must be prime");
            } else if (!bigInteger2.isProbablePrime(20)) {
                throw new IllegalArgumentException("q must be prime");
            }
        }
        this.a = bigInteger;
        this.b = bigInteger2;
        this.c = bigInteger3;
    }
}
