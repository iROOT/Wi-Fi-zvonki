package org.spongycastle.crypto.params;

import java.math.BigInteger;

public class RSAKeyParameters extends AsymmetricKeyParameter {
    private BigInteger b;
    private BigInteger c;

    public RSAKeyParameters(boolean z, BigInteger bigInteger, BigInteger bigInteger2) {
        super(z);
        this.b = bigInteger;
        this.c = bigInteger2;
    }

    public BigInteger b() {
        return this.b;
    }

    public BigInteger c() {
        return this.c;
    }
}
