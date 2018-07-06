package org.spongycastle.math.ec;

import java.math.BigInteger;

public class WNafL2RMultiplier extends AbstractECMultiplier {
    protected ECPoint b(ECPoint eCPoint, BigInteger bigInteger) {
        ECPoint b;
        int max = Math.max(2, Math.min(16, a(bigInteger.bitLength())));
        WNafPreCompInfo a = WNafUtil.a(eCPoint, max, true);
        ECPoint[] a2 = a.a();
        ECPoint[] b2 = a.b();
        int[] a3 = WNafUtil.a(max, bigInteger);
        ECPoint e = eCPoint.a().e();
        int length = a3.length;
        if (length > 1) {
            int i = length - 1;
            length = a3[i];
            int i2 = length >> 16;
            length &= 65535;
            int abs = Math.abs(i2);
            ECPoint[] eCPointArr = i2 < 0 ? b2 : a2;
            if ((abs << 3) < (1 << max)) {
                byte b3 = LongArray.a[abs];
                int i3 = max - b3;
                e = eCPointArr[((1 << (max - 1)) - 1) >>> 1].b(eCPointArr[(((abs ^ (1 << (b3 - 1))) << i3) + 1) >>> 1]);
                length -= i3;
            } else {
                e = eCPointArr[abs >>> 1];
            }
            int i4 = i;
            b = e.b(length);
            length = i4;
        } else {
            b = e;
        }
        while (length > 0) {
            i2 = length - 1;
            length = a3[i2];
            max = length >> 16;
            b = b.d((max < 0 ? b2 : a2)[Math.abs(max) >>> 1]).b(length & 65535);
            length = i2;
        }
        return b;
    }

    protected int a(int i) {
        return WNafUtil.a(i);
    }
}
