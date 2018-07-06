package org.spongycastle.math.ec;

import java.math.BigInteger;

public class NafR2LMultiplier extends AbstractECMultiplier {
    protected ECPoint b(ECPoint eCPoint, BigInteger bigInteger) {
        int i = 0;
        int[] a = WNafUtil.a(bigInteger);
        ECPoint e = eCPoint.a().e();
        int i2 = 0;
        while (i < a.length) {
            ECPoint o;
            int i3 = a[i];
            int i4 = i3 >> 16;
            eCPoint = eCPoint.b(i2 + (i3 & 65535));
            if (i4 < 0) {
                o = eCPoint.o();
            } else {
                o = eCPoint;
            }
            e = e.b(o);
            i2 = 1;
            i++;
        }
        return e;
    }
}
