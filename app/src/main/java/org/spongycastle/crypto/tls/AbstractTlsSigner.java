package org.spongycastle.crypto.tls;

import org.spongycastle.crypto.params.AsymmetricKeyParameter;

public abstract class AbstractTlsSigner implements TlsSigner {
    protected TlsContext a;

    public void a(TlsContext tlsContext) {
        this.a = tlsContext;
    }

    public byte[] a(AsymmetricKeyParameter asymmetricKeyParameter, byte[] bArr) {
        return a(null, asymmetricKeyParameter, bArr);
    }
}
