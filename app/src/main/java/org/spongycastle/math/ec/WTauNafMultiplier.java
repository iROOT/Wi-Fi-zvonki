package org.spongycastle.math.ec;

import java.math.BigInteger;
import org.spongycastle.math.ec.ECPoint.F2m;

public class WTauNafMultiplier extends AbstractECMultiplier {
    protected ECPoint b(ECPoint eCPoint, BigInteger bigInteger) {
        if (eCPoint instanceof F2m) {
            F2m f2m = (F2m) eCPoint;
            ECCurve.F2m f2m2 = (ECCurve.F2m) f2m.a();
            int m = f2m2.m();
            byte byteValue = f2m2.f().a().byteValue();
            byte k = f2m2.k();
            return a(f2m, Tnaf.a(bigInteger, m, byteValue, f2m2.l(), k, (byte) 10), f2m2.a((ECPoint) f2m), byteValue, k);
        }
        throw new IllegalArgumentException("Only ECPoint.F2m can be used in WTauNafMultiplier");
    }

    private F2m a(F2m f2m, ZTauElement zTauElement, PreCompInfo preCompInfo, byte b, byte b2) {
        ZTauElement[] zTauElementArr;
        if (b == (byte) 0) {
            zTauElementArr = Tnaf.a;
        } else {
            zTauElementArr = Tnaf.c;
        }
        return a(f2m, Tnaf.a(b2, zTauElement, (byte) 4, BigInteger.valueOf(16), Tnaf.a(b2, 4), zTauElementArr), preCompInfo);
    }

    private static F2m a(F2m f2m, byte[] bArr, PreCompInfo preCompInfo) {
        F2m[] a;
        ECCurve.F2m f2m2 = (ECCurve.F2m) f2m.a();
        byte byteValue = f2m2.f().a().byteValue();
        if (preCompInfo == null || !(preCompInfo instanceof WTauNafPreCompInfo)) {
            a = Tnaf.a(f2m, byteValue);
            f2m2.a((ECPoint) f2m, new WTauNafPreCompInfo(a));
        } else {
            a = ((WTauNafPreCompInfo) preCompInfo).a();
        }
        F2m f2m3 = (F2m) f2m.a().e();
        for (int length = bArr.length - 1; length >= 0; length--) {
            f2m3 = Tnaf.a(f2m3);
            if (bArr[length] != (byte) 0) {
                if (bArr[length] > (byte) 0) {
                    f2m3 = f2m3.a(a[bArr[length]]);
                } else {
                    f2m3 = f2m3.b(a[-bArr[length]]);
                }
            }
        }
        return f2m3;
    }
}
