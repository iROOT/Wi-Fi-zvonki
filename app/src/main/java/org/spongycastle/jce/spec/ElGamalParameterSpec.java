package org.spongycastle.jce.spec;

import java.math.BigInteger;
import java.security.spec.AlgorithmParameterSpec;

public class ElGamalParameterSpec implements AlgorithmParameterSpec {
    private BigInteger a;
    private BigInteger b;

    public ElGamalParameterSpec(BigInteger bigInteger, BigInteger bigInteger2) {
        this.a = bigInteger;
        this.b = bigInteger2;
    }

    public BigInteger a() {
        return this.a;
    }

    public BigInteger b() {
        return this.b;
    }
}
