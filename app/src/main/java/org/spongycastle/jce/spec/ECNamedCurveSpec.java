package org.spongycastle.jce.spec;

import java.math.BigInteger;
import java.security.spec.ECFieldF2m;
import java.security.spec.ECFieldFp;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPoint;
import java.security.spec.EllipticCurve;
import org.spongycastle.math.ec.ECCurve;
import org.spongycastle.math.ec.ECCurve.F2m;
import org.spongycastle.math.ec.ECCurve.Fp;

public class ECNamedCurveSpec extends ECParameterSpec {
    private String a;

    private static EllipticCurve a(ECCurve eCCurve, byte[] bArr) {
        if (eCCurve instanceof Fp) {
            return new EllipticCurve(new ECFieldFp(((Fp) eCCurve).j()), eCCurve.f().a(), eCCurve.g().a(), bArr);
        }
        F2m f2m = (F2m) eCCurve;
        if (f2m.n()) {
            return new EllipticCurve(new ECFieldF2m(f2m.m(), new int[]{f2m.o()}), eCCurve.f().a(), eCCurve.g().a(), bArr);
        }
        return new EllipticCurve(new ECFieldF2m(f2m.m(), new int[]{f2m.q(), f2m.p(), f2m.o()}), eCCurve.f().a(), eCCurve.g().a(), bArr);
    }

    private static ECPoint a(org.spongycastle.math.ec.ECPoint eCPoint) {
        org.spongycastle.math.ec.ECPoint k = eCPoint.k();
        return new ECPoint(k.c().a(), k.d().a());
    }

    public ECNamedCurveSpec(String str, EllipticCurve ellipticCurve, ECPoint eCPoint, BigInteger bigInteger, BigInteger bigInteger2) {
        super(ellipticCurve, eCPoint, bigInteger, bigInteger2.intValue());
        this.a = str;
    }

    public ECNamedCurveSpec(String str, ECCurve eCCurve, org.spongycastle.math.ec.ECPoint eCPoint, BigInteger bigInteger, BigInteger bigInteger2, byte[] bArr) {
        super(a(eCCurve, bArr), a(eCPoint), bigInteger, bigInteger2.intValue());
        this.a = str;
    }

    public String a() {
        return this.a;
    }
}
