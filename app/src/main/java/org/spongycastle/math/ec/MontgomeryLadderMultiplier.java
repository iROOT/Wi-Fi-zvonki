package org.spongycastle.math.ec;

import java.math.BigInteger;

public class MontgomeryLadderMultiplier extends AbstractECMultiplier {
    protected ECPoint b(ECPoint eCPoint, BigInteger bigInteger) {
        ECPoint[] eCPointArr = new ECPoint[]{eCPoint.a().e(), eCPoint};
        int bitLength = bigInteger.bitLength();
        while (true) {
            int i = bitLength - 1;
            if (i < 0) {
                return eCPointArr[0];
            }
            if (bigInteger.testBit(i)) {
                bitLength = 1;
            } else {
                bitLength = 0;
            }
            int i2 = 1 - bitLength;
            eCPointArr[i2] = eCPointArr[i2].b(eCPointArr[bitLength]);
            eCPointArr[bitLength] = eCPointArr[bitLength].p();
            bitLength = i;
        }
    }
}
