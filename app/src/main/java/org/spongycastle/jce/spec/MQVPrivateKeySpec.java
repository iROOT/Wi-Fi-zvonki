package org.spongycastle.jce.spec;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.KeySpec;
import org.spongycastle.jce.interfaces.MQVPrivateKey;

public class MQVPrivateKeySpec implements KeySpec, MQVPrivateKey {
    private PrivateKey a;
    private PrivateKey b;
    private PublicKey c;

    public PrivateKey a() {
        return this.a;
    }

    public PrivateKey b() {
        return this.b;
    }

    public PublicKey c() {
        return this.c;
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
