package org.spongycastle.crypto.params;

import org.spongycastle.crypto.CipherParameters;

public class AsymmetricKeyParameter implements CipherParameters {
    boolean a;

    public AsymmetricKeyParameter(boolean z) {
        this.a = z;
    }

    public boolean a() {
        return this.a;
    }
}
