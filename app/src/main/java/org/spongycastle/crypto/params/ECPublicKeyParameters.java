package org.spongycastle.crypto.params;

import org.spongycastle.math.ec.ECPoint;

public class ECPublicKeyParameters extends ECKeyParameters {
    ECPoint c;

    public ECPublicKeyParameters(ECPoint eCPoint, ECDomainParameters eCDomainParameters) {
        super(false, eCDomainParameters);
        this.c = eCPoint.k();
    }

    public ECPoint c() {
        return this.c;
    }
}
