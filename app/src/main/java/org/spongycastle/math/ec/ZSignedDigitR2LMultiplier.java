package org.spongycastle.math.ec;

import java.math.BigInteger;

public class ZSignedDigitR2LMultiplier extends AbstractECMultiplier {
    protected ECPoint b(ECPoint eCPoint, BigInteger bigInteger) {
        ECPoint e = eCPoint.a().e();
        int bitLength = bigInteger.bitLength();
        int lowestSetBit = bigInteger.getLowestSetBit();
        ECPoint b = eCPoint.b(lowestSetBit);
        ECPoint eCPoint2 = e;
        while (true) {
            int i = lowestSetBit + 1;
            if (i >= bitLength) {
                return eCPoint2.b(b);
            }
            ECPoint b2 = eCPoint2.b(bigInteger.testBit(i) ? b : b.o());
            b = b.p();
            eCPoint2 = b2;
            lowestSetBit = i;
        }
    }
}
