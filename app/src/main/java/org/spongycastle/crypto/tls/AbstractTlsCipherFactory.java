package org.spongycastle.crypto.tls;

public class AbstractTlsCipherFactory implements TlsCipherFactory {
    public TlsCipher a(TlsContext tlsContext, int i, int i2) {
        throw new TlsFatalAlert((short) 80);
    }
}
