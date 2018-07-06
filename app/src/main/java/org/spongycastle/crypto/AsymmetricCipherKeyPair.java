package org.spongycastle.crypto;

import org.spongycastle.crypto.params.AsymmetricKeyParameter;

public class AsymmetricCipherKeyPair {
    private AsymmetricKeyParameter a;
    private AsymmetricKeyParameter b;

    public AsymmetricCipherKeyPair(AsymmetricKeyParameter asymmetricKeyParameter, AsymmetricKeyParameter asymmetricKeyParameter2) {
        this.a = asymmetricKeyParameter;
        this.b = asymmetricKeyParameter2;
    }

    public AsymmetricKeyParameter a() {
        return this.a;
    }

    public AsymmetricKeyParameter b() {
        return this.b;
    }
}
