package org.spongycastle.jce.spec;

import org.spongycastle.math.ec.ECPoint;

public class ECPublicKeySpec extends ECKeySpec {
    private ECPoint a;

    public ECPublicKeySpec(ECPoint eCPoint, ECParameterSpec eCParameterSpec) {
        super(eCParameterSpec);
        if (eCPoint.a() != null) {
            this.a = eCPoint.k();
        } else {
            this.a = eCPoint;
        }
    }

    public ECPoint b() {
        return this.a;
    }
}
