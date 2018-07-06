package org.spongycastle.crypto.params;

import java.math.BigInteger;

public class DSAPrivateKeyParameters extends DSAKeyParameters {
    private BigInteger b;

    public DSAPrivateKeyParameters(BigInteger bigInteger, DSAParameters dSAParameters) {
        super(true, dSAParameters);
        this.b = bigInteger;
    }

    public BigInteger c() {
        return this.b;
    }
}
