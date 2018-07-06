package org.spongycastle.jce.spec;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.KeySpec;
import org.spongycastle.jce.interfaces.IESKey;

public class IEKeySpec implements KeySpec, IESKey {
    private PublicKey a;
    private PrivateKey b;

    public PublicKey a() {
        return this.a;
    }

    public PrivateKey b() {
        return this.b;
    }

    public String getAlgorithm() {
        return "IES";
    }

    public String getFormat() {
        return null;
    }

    public byte[] getEncoded() {
        return null;
    }
}
