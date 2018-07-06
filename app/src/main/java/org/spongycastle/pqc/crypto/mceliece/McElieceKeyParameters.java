package org.spongycastle.pqc.crypto.mceliece;

import org.spongycastle.crypto.params.AsymmetricKeyParameter;

public class McElieceKeyParameters extends AsymmetricKeyParameter {
    private McElieceParameters b;

    public McElieceKeyParameters(boolean z, McElieceParameters mcElieceParameters) {
        super(z);
        this.b = mcElieceParameters;
    }

    public McElieceParameters b() {
        return this.b;
    }
}
