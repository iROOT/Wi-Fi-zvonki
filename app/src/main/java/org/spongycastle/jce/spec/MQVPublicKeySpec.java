package org.spongycastle.jce.spec;

import java.security.PublicKey;
import java.security.spec.KeySpec;
import org.spongycastle.jce.interfaces.MQVPublicKey;

public class MQVPublicKeySpec implements KeySpec, MQVPublicKey {
    private PublicKey a;
    private PublicKey b;

    public PublicKey a() {
        return this.a;
    }

    public PublicKey b() {
        return this.b;
    }

    public String getAlgorithm() {
        return "ECMQV";
    }

    public String getFormat() {
        return null;
    }

    public byte[] getEncoded() {
        return null;
    }
}
