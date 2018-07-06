package org.spongycastle.math.ec;

import java.math.BigInteger;

public class ReferenceMultiplier extends AbstractECMultiplier {
    protected ECPoint b(ECPoint eCPoint, BigInteger bigInteger) {
        ECPoint e = eCPoint.a().e();
        int bitLength = bigInteger.bitLength();
        if (bitLength > 0) {
            if (bigInteger.testBit(0)) {
                e = eCPoint;
            }
            for (int i = 1; i < bitLength; i++) {
                eCPoint = eCPoint.p();
                if (bigInteger.testBit(i)) {
                    e = e.b(eCPoint);
                }
            }
        }
        return e;
    }
}
