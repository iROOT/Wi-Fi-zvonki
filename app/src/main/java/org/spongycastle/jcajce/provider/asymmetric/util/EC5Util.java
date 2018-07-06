package org.spongycastle.jcajce.provider.asymmetric.util;

import java.math.BigInteger;
import java.security.spec.ECField;
import java.security.spec.ECFieldF2m;
import java.security.spec.ECFieldFp;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPoint;
import java.security.spec.EllipticCurve;
import org.spongycastle.jce.spec.ECNamedCurveParameterSpec;
import org.spongycastle.jce.spec.ECNamedCurveSpec;
import org.spongycastle.math.ec.ECCurve;
import org.spongycastle.math.ec.ECCurve.F2m;
import org.spongycastle.math.ec.ECCurve.Fp;

public class EC5Util {
    public static EllipticCurve a(ECCurve eCCurve, byte[] bArr) {
        if (eCCurve instanceof Fp) {
            return new EllipticCurve(new ECFieldFp(((Fp) eCCurve).j()), eCCurve.f().a(), eCCurve.g().a(), null);
        }
        F2m f2m = (F2m) eCCurve;
        if (f2m.n()) {
            return new EllipticCurve(new ECFieldF2m(f2m.m(), new int[]{f2m.o()}), eCCurve.f().a(), eCCurve.g().a(), null);
        }
        return new EllipticCurve(new ECFieldF2m(f2m.m(), new int[]{f2m.q(), f2m.p(), f2m.o()}), eCCurve.f().a(), eCCurve.g().a(), null);
    }

    public static ECCurve a(EllipticCurve ellipticCurve) {
        ECField field = ellipticCurve.getField();
        BigInteger a = ellipticCurve.getA();
        BigInteger b = ellipticCurve.getB();
        if (field instanceof ECFieldFp) {
            return new Fp(((ECFieldFp) field).getP(), a, b);
        }
        ECFieldF2m eCFieldF2m = (ECFieldF2m) field;
        int m = eCFieldF2m.getM();
        int[] a2 = ECUtil.a(eCFieldF2m.getMidTermsOfReductionPolynomial());
        return new F2m(m, a2[0], a2[1], a2[2], a, b);
    }

    public static ECParameterSpec a(EllipticCurve ellipticCurve, org.spongycastle.jce.spec.ECParameterSpec eCParameterSpec) {
        if (!(eCParameterSpec instanceof ECNamedCurveParameterSpec)) {
            return new ECParameterSpec(ellipticCurve, new ECPoint(eCParameterSpec.c().c().a(), eCParameterSpec.c().d().a()), eCParameterSpec.d(), eCParameterSpec.e().intValue());
        }
        return new ECNamedCurveSpec(((ECNamedCurveParameterSpec) eCParameterSpec).a(), ellipticCurve, new ECPoint(eCParameterSpec.c().c().a(), eCParameterSpec.c().d().a()), eCParameterSpec.d(), eCParameterSpec.e());
    }

    public static org.spongycastle.jce.spec.ECParameterSpec a(ECParameterSpec eCParameterSpec, boolean z) {
        ECCurve a = a(eCParameterSpec.getCurve());
        return new org.spongycastle.jce.spec.ECParameterSpec(a, a(a, eCParameterSpec.getGenerator(), z), eCParameterSpec.getOrder(), BigInteger.valueOf((long) eCParameterSpec.getCofactor()), eCParameterSpec.getCurve().getSeed());
    }

    public static org.spongycastle.math.ec.ECPoint a(ECParameterSpec eCParameterSpec, ECPoint eCPoint, boolean z) {
        return a(a(eCParameterSpec.getCurve()), eCPoint, z);
    }

    public static org.spongycastle.math.ec.ECPoint a(ECCurve eCCurve, ECPoint eCPoint, boolean z) {
        return eCCurve.a(eCPoint.getAffineX(), eCPoint.getAffineY(), z);
    }
}
