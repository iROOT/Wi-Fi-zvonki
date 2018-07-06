package org.spongycastle.pqc.jcajce.spec;

import java.security.spec.AlgorithmParameterSpec;

public class McElieceCCA2ParameterSpec implements AlgorithmParameterSpec {
    private String a;

    public McElieceCCA2ParameterSpec() {
        this("SHA256");
    }

    public McElieceCCA2ParameterSpec(String str) {
        this.a = str;
    }
}
