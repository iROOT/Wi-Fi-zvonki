package org.spongycastle.jce.spec;

import java.math.BigInteger;
import java.security.spec.AlgorithmParameterSpec;
import org.spongycastle.math.ec.ECCurve;
import org.spongycastle.math.ec.ECPoint;

public class ECParameterSpec implements AlgorithmParameterSpec {
    private ECCurve a;
    private byte[] b;
    private ECPoint c;
    private BigInteger d;
    private BigInteger e;

    public ECParameterSpec(ECCurve eCCurve, ECPoint eCPoint, BigInteger bigInteger) {
        this.a = eCCurve;
        this.c = eCPoint.k();
        this.d = bigInteger;
        this.e = BigInteger.valueOf(1);
        this.b = null;
    }

    public ECParameterSpec(ECCurve eCCurve, ECPoint eCPoint, BigInteger bigInteger, BigInteger bigInteger2, byte[] bArr) {
        this.a = eCCurve;
        this.c = eCPoint.k();
        this.d = bigInteger;
        this.e = bigInteger2;
        this.b = bArr;
    }

    public ECCurve b() {
        return this.a;
    }

    public ECPoint c() {
        return this.c;
    }

    public BigInteger d() {
        return this.d;
    }

    public BigInteger e() {
        return this.e;
    }

    public byte[] f() {
        return this.b;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ECParameterSpec)) {
            return false;
        }
        ECParameterSpec eCParameterSpec = (ECParameterSpec) obj;
        if (b().equals(eCParameterSpec.b()) && c().a(eCParameterSpec.c())) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return b().hashCode() ^ c().hashCode();
    }
}
