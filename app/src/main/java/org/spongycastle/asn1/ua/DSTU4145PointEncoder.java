package org.spongycastle.asn1.ua;

import java.math.BigInteger;
import java.util.Random;
import org.spongycastle.math.ec.ECConstants;
import org.spongycastle.math.ec.ECCurve;
import org.spongycastle.math.ec.ECFieldElement;
import org.spongycastle.math.ec.ECFieldElement.F2m;
import org.spongycastle.math.ec.ECPoint;
import org.spongycastle.util.Arrays;

public abstract class DSTU4145PointEncoder {
    private static BigInteger a(ECFieldElement eCFieldElement) {
        ECFieldElement eCFieldElement2 = eCFieldElement;
        for (int i = 0; i < eCFieldElement.b() - 1; i++) {
            eCFieldElement2 = eCFieldElement2.e().a(eCFieldElement);
        }
        return eCFieldElement2.a();
    }

    private static ECFieldElement a(ECCurve eCCurve, ECFieldElement eCFieldElement) {
        if (eCFieldElement.i()) {
            return eCFieldElement;
        }
        ECFieldElement eCFieldElement2;
        ECFieldElement a = eCCurve.a(ECConstants.c);
        Random random = new Random();
        int b = eCFieldElement.b();
        do {
            ECFieldElement a2 = eCCurve.a(new BigInteger(b, random));
            int i = 1;
            ECFieldElement eCFieldElement3 = eCFieldElement;
            eCFieldElement2 = a;
            while (i <= b - 1) {
                eCFieldElement3 = eCFieldElement3.e();
                ECFieldElement a3 = eCFieldElement2.e().a(eCFieldElement3.c(a2));
                eCFieldElement3 = eCFieldElement3.a(eCFieldElement);
                i++;
                eCFieldElement2 = a3;
            }
            if (!eCFieldElement3.i()) {
                return null;
            }
        } while (eCFieldElement2.e().a(eCFieldElement2).i());
        return eCFieldElement2;
    }

    public static byte[] a(ECPoint eCPoint) {
        ECPoint k = eCPoint.k();
        ECFieldElement c = k.c();
        byte[] k2 = c.k();
        if (!c.i()) {
            int length;
            if (a(k.d().d(c)).equals(ECConstants.d)) {
                length = k2.length - 1;
                k2[length] = (byte) (k2[length] | 1);
            } else {
                length = k2.length - 1;
                k2[length] = (byte) (k2[length] & 254);
            }
        }
        return k2;
    }

    public static ECPoint a(ECCurve eCCurve, byte[] bArr) {
        ECFieldElement eCFieldElement;
        BigInteger valueOf = BigInteger.valueOf((long) (bArr[bArr.length - 1] & 1));
        if (!a(eCCurve.a(new BigInteger(1, bArr))).equals(eCCurve.f().a())) {
            bArr = Arrays.b(bArr);
            int length = bArr.length - 1;
            bArr[length] = (byte) (bArr[length] ^ 1);
        }
        ECFieldElement a = eCCurve.a(new BigInteger(1, bArr));
        if (a.i()) {
            eCFieldElement = (F2m) eCCurve.g();
            for (length = 0; length < eCCurve.a() - 1; length++) {
                eCFieldElement = eCFieldElement.e();
            }
        } else {
            ECFieldElement a2 = a(eCCurve, a.a(eCCurve.f()).a(eCCurve.g().c(a.e().f())));
            if (a2 == null) {
                throw new RuntimeException("Invalid point compression");
            }
            if (!a(a2).equals(valueOf)) {
                a2 = a2.c();
            }
            eCFieldElement = a.c(a2);
        }
        return new ECPoint.F2m(eCCurve, a, eCFieldElement);
    }
}
