package org.spongycastle.jce.spec;

import java.security.spec.KeySpec;

public class ElGamalKeySpec implements KeySpec {
    private ElGamalParameterSpec a;

    public ElGamalParameterSpec a() {
        return this.a;
    }
}
