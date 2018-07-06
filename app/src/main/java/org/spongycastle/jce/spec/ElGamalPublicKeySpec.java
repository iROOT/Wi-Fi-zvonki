package org.spongycastle.jce.spec;

import java.math.BigInteger;

public class ElGamalPublicKeySpec extends ElGamalKeySpec {
    private BigInteger a;

    public BigInteger b() {
        return this.a;
    }
}
