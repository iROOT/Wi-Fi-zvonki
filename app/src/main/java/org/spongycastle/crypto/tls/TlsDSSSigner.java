package org.spongycastle.crypto.tls;

import org.spongycastle.crypto.DSA;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.crypto.params.DSAPublicKeyParameters;
import org.spongycastle.crypto.signers.DSASigner;

public class TlsDSSSigner extends TlsDSASigner {
    public boolean a(AsymmetricKeyParameter asymmetricKeyParameter) {
        return asymmetricKeyParameter instanceof DSAPublicKeyParameters;
    }

    protected DSA b() {
        return new DSASigner();
    }

    protected short a() {
        return (short) 2;
    }
}
