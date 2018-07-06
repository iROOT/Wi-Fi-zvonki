package org.spongycastle.jce.spec;

import java.security.spec.AlgorithmParameterSpec;

public class ECNamedCurveGenParameterSpec implements AlgorithmParameterSpec {
    private String a;

    public String a() {
        return this.a;
    }
}
