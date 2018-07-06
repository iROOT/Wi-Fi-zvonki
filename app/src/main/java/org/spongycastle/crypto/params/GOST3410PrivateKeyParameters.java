package org.spongycastle.crypto.params;

import java.math.BigInteger;

public class GOST3410PrivateKeyParameters extends GOST3410KeyParameters {
    private BigInteger b;

    public GOST3410PrivateKeyParameters(BigInteger bigInteger, GOST3410Parameters gOST3410Parameters) {
        super(true, gOST3410Parameters);
        this.b = bigInteger;
    }

    public BigInteger c() {
        return this.b;
    }
}
