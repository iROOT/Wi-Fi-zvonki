package org.spongycastle.crypto.params;

import org.spongycastle.crypto.CipherParameters;

public class MQVPrivateParameters implements CipherParameters {
    private ECPrivateKeyParameters a;
    private ECPrivateKeyParameters b;
    private ECPublicKeyParameters c;

    public MQVPrivateParameters(ECPrivateKeyParameters eCPrivateKeyParameters, ECPrivateKeyParameters eCPrivateKeyParameters2, ECPublicKeyParameters eCPublicKeyParameters) {
        this.a = eCPrivateKeyParameters;
        this.b = eCPrivateKeyParameters2;
        this.c = eCPublicKeyParameters;
    }

    public ECPrivateKeyParameters a() {
        return this.a;
    }

    public ECPrivateKeyParameters b() {
        return this.b;
    }

    public ECPublicKeyParameters c() {
        return this.c;
    }
}
