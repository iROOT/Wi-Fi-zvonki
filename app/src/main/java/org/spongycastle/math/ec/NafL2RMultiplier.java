package org.spongycastle.math.ec;

import java.math.BigInteger;

public class NafL2RMultiplier extends AbstractECMultiplier {
    protected ECPoint b(ECPoint eCPoint, BigInteger bigInteger) {
        int[] a = WNafUtil.a(bigInteger);
        ECPoint k = eCPoint.k();
        ECPoint o = k.o();
        ECPoint e = eCPoint.a().e();
        int length = a.length;
        ECPoint eCPoint2 = e;
        while (true) {
            int i = length - 1;
            if (i < 0) {
                return eCPoint2;
            }
            ECPoint eCPoint3;
            length = a[i];
            int i2 = 65535 & length;
            if ((length >> 16) < 0) {
                eCPoint3 = o;
            } else {
                eCPoint3 = k;
            }
            eCPoint2 = eCPoint2.d(eCPoint3).b(i2);
            length = i;
        }
    }
}
