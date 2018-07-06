package org.spongycastle.jce.spec;

import java.math.BigInteger;

public class ElGamalPrivateKeySpec extends ElGamalKeySpec {
    private BigInteger a;

    public BigInteger b() {
        return this.a;
    }
}
