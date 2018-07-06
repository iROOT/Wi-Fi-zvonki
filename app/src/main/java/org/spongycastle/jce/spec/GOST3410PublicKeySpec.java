package org.spongycastle.jce.spec;

import java.math.BigInteger;
import java.security.spec.KeySpec;

public class GOST3410PublicKeySpec implements KeySpec {
    private BigInteger a;
    private BigInteger b;
    private BigInteger c;
    private BigInteger d;

    public GOST3410PublicKeySpec(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4) {
        this.a = bigInteger;
        this.b = bigInteger2;
        this.c = bigInteger3;
        this.d = bigInteger4;
    }

    public BigInteger a() {
        return this.a;
    }

    public BigInteger b() {
        return this.b;
    }

    public BigInteger c() {
        return this.c;
    }

    public BigInteger d() {
        return this.d;
    }
}
