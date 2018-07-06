package org.spongycastle.crypto.params;

import java.math.BigInteger;

public class DSAPublicKeyParameters extends DSAKeyParameters {
    private BigInteger b;

    public DSAPublicKeyParameters(BigInteger bigInteger, DSAParameters dSAParameters) {
        super(false, dSAParameters);
        this.b = bigInteger;
    }

    public BigInteger c() {
        return this.b;
    }
}
