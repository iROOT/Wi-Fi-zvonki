package org.spongycastle.math.ec;

import java.math.BigInteger;

public class ZSignedDigitL2RMultiplier extends AbstractECMultiplier {
    protected ECPoint b(ECPoint eCPoint, BigInteger bigInteger) {
        ECPoint k = eCPoint.k();
        ECPoint o = k.o();
        int bitLength = bigInteger.bitLength();
        int lowestSetBit = bigInteger.getLowestSetBit();
        ECPoint eCPoint2 = k;
        while (true) {
            int i = bitLength - 1;
            if (i <= lowestSetBit) {
                return eCPoint2.b(lowestSetBit);
            }
            eCPoint2 = eCPoint2.d(bigInteger.testBit(i) ? k : o);
            bitLength = i;
        }
    }
}
