package org.spongycastle.jcajce.spec;

import javax.crypto.SecretKey;

public class RepeatedSecretKeySpec implements SecretKey {
    private String a;

    public String getAlgorithm() {
        return this.a;
    }

    public String getFormat() {
        return null;
    }

    public byte[] getEncoded() {
        return null;
    }
}
