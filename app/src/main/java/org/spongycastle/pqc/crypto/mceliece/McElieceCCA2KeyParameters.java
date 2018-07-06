package org.spongycastle.pqc.crypto.mceliece;

import org.spongycastle.crypto.params.AsymmetricKeyParameter;

public class McElieceCCA2KeyParameters extends AsymmetricKeyParameter {
    private McElieceCCA2Parameters b;

    public McElieceCCA2KeyParameters(boolean z, McElieceCCA2Parameters mcElieceCCA2Parameters) {
        super(z);
        this.b = mcElieceCCA2Parameters;
    }

    public McElieceCCA2Parameters b() {
        return this.b;
    }
}
