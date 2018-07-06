package org.spongycastle.math.ec;

import java.math.BigInteger;
import org.spongycastle.math.ec.ECCurve.F2m;

public class ECAlgorithms {
    public static ECPoint a(ECPoint eCPoint, BigInteger bigInteger, ECPoint eCPoint2, BigInteger bigInteger2) {
        ECCurve a = eCPoint.a();
        ECPoint a2 = a(a, eCPoint2);
        if ((a instanceof F2m) && ((F2m) a).j()) {
            return eCPoint.a(bigInteger).b(a2.a(bigInteger2));
        }
        return b(eCPoint, bigInteger, a2, bigInteger2);
    }

    public static ECPoint a(ECCurve eCCurve, ECPoint eCPoint) {
        if (eCCurve.equals(eCPoint.a())) {
            return eCCurve.b(eCPoint);
        }
        throw new IllegalArgumentException("Point must be on the same curve");
    }

    static void a(ECFieldElement[] eCFieldElementArr, int i, int i2) {
        int i3 = 0;
        ECFieldElement[] eCFieldElementArr2 = new ECFieldElement[i2];
        eCFieldElementArr2[0] = eCFieldElementArr[i];
        while (true) {
            i3++;
            if (i3 >= i2) {
                break;
            }
            eCFieldElementArr2[i3] = eCFieldElementArr2[i3 - 1].c(eCFieldElementArr[i + i3]);
        }
        int i4 = i3 - 1;
        ECFieldElement f = eCFieldElementArr2[i4].f();
        while (i4 > 0) {
            int i5 = i4 - 1;
            i4 += i;
            ECFieldElement eCFieldElement = eCFieldElementArr[i4];
            eCFieldElementArr[i4] = eCFieldElementArr2[i5].c(f);
            f = f.c(eCFieldElement);
            i4 = i5;
        }
        eCFieldElementArr[i] = f;
    }

    static ECPoint b(ECPoint eCPoint, BigInteger bigInteger, ECPoint eCPoint2, BigInteger bigInteger2) {
        ECCurve a = eCPoint.a();
        ECPoint e = a.e();
        ECPoint b = eCPoint.b(eCPoint2);
        ECPoint c = eCPoint.c(eCPoint2);
        a.a(new ECPoint[]{eCPoint2, c, eCPoint, b});
        ECPoint[] eCPointArr = new ECPoint[]{r4[3].o(), r4[2].o(), r4[1].o(), r4[0].o(), e, r4[0], r4[1], r4[2], r4[3]};
        byte[] a2 = WNafUtil.a(bigInteger, bigInteger2);
        int length = a2.length;
        while (true) {
            length--;
            if (length < 0) {
                return e;
            }
            byte b2 = a2[length];
            e = e.d(eCPointArr[((b2 << 28) >> 28) + (((b2 >> 4) * 3) + 4)]);
        }
    }
}
