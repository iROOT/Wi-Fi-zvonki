package org.spongycastle.pqc.math.ntru.euclid;

import java.math.BigInteger;

public class BigIntEuclidean {
    public BigInteger a;
    public BigInteger b;
    public BigInteger c;

    private BigIntEuclidean() {
    }

    public static BigIntEuclidean a(BigInteger bigInteger, BigInteger bigInteger2) {
        BigInteger bigInteger3 = BigInteger.ZERO;
        BigInteger bigInteger4 = BigInteger.ONE;
        BigInteger bigInteger5 = BigInteger.ONE;
        BigInteger bigInteger6 = BigInteger.ZERO;
        while (!bigInteger2.equals(BigInteger.ZERO)) {
            BigInteger[] divideAndRemainder = bigInteger.divideAndRemainder(bigInteger2);
            BigInteger bigInteger7 = divideAndRemainder[0];
            BigInteger bigInteger8 = divideAndRemainder[1];
            bigInteger = bigInteger2;
            bigInteger2 = bigInteger8;
            BigInteger bigInteger9 = bigInteger3;
            bigInteger3 = bigInteger4.subtract(bigInteger7.multiply(bigInteger3));
            bigInteger4 = bigInteger9;
            BigInteger subtract = bigInteger6.subtract(bigInteger7.multiply(bigInteger5));
            bigInteger6 = bigInteger5;
            bigInteger5 = subtract;
        }
        BigIntEuclidean bigIntEuclidean = new BigIntEuclidean();
        bigIntEuclidean.a = bigInteger4;
        bigIntEuclidean.b = bigInteger6;
        bigIntEuclidean.c = bigInteger;
        return bigIntEuclidean;
    }
}
