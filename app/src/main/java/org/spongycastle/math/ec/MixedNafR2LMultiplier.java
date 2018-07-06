package org.spongycastle.math.ec;

import java.math.BigInteger;

public class MixedNafR2LMultiplier extends AbstractECMultiplier {
    protected int a;
    protected int b;

    public MixedNafR2LMultiplier() {
        this(2, 4);
    }

    public MixedNafR2LMultiplier(int i, int i2) {
        this.a = i;
        this.b = i2;
    }

    protected ECPoint b(ECPoint eCPoint, BigInteger bigInteger) {
        int i = 0;
        ECCurve a = eCPoint.a();
        ECCurve a2 = a(a, this.a);
        ECCurve a3 = a(a, this.b);
        int[] a4 = WNafUtil.a(bigInteger);
        ECPoint e = a2.e();
        ECPoint b = a3.b(eCPoint);
        int i2 = 0;
        while (i < a4.length) {
            int i3 = a4[i];
            int i4 = i3 >> 16;
            b = b.b(i2 + (i3 & 65535));
            ECPoint b2 = a2.b(b);
            if (i4 < 0) {
                b2 = b2.o();
            }
            e = e.b(b2);
            i2 = 1;
            i++;
        }
        return a.b(e);
    }

    protected ECCurve a(ECCurve eCCurve, int i) {
        if (eCCurve.h() == i) {
            return eCCurve;
        }
        if (eCCurve.a(i)) {
            return eCCurve.b().a(i).a();
        }
        throw new IllegalArgumentException("Coordinate system " + i + " not supported by this curve");
    }
}
