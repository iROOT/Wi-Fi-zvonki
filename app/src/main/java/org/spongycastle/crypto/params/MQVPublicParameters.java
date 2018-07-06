package org.spongycastle.crypto.params;

import org.spongycastle.crypto.CipherParameters;

public class MQVPublicParameters implements CipherParameters {
    private ECPublicKeyParameters a;
    private ECPublicKeyParameters b;

    public MQVPublicParameters(ECPublicKeyParameters eCPublicKeyParameters, ECPublicKeyParameters eCPublicKeyParameters2) {
        this.a = eCPublicKeyParameters;
        this.b = eCPublicKeyParameters2;
    }

    public ECPublicKeyParameters a() {
        return this.a;
    }

    public ECPublicKeyParameters b() {
        return this.b;
    }
}
