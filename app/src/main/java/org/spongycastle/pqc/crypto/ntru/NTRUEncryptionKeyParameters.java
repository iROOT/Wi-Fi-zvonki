package org.spongycastle.pqc.crypto.ntru;

import org.spongycastle.crypto.params.AsymmetricKeyParameter;

public class NTRUEncryptionKeyParameters extends AsymmetricKeyParameter {
    protected final NTRUEncryptionParameters b;

    public NTRUEncryptionKeyParameters(boolean z, NTRUEncryptionParameters nTRUEncryptionParameters) {
        super(z);
        this.b = nTRUEncryptionParameters;
    }

    public NTRUEncryptionParameters b() {
        return this.b;
    }
}
