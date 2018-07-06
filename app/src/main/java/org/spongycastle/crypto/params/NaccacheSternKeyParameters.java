package org.spongycastle.crypto.params;

import java.math.BigInteger;

public class NaccacheSternKeyParameters extends AsymmetricKeyParameter {
    int b;
    private BigInteger c;
    private BigInteger d;

    public NaccacheSternKeyParameters(boolean z, BigInteger bigInteger, BigInteger bigInteger2, int i) {
        super(z);
        this.c = bigInteger;
        this.d = bigInteger2;
        this.b = i;
    }

    public BigInteger b() {
        return this.c;
    }

    public int c() {
        return this.b;
    }

    public BigInteger d() {
        return this.d;
    }
}
