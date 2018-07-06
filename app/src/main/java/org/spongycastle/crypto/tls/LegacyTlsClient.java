package org.spongycastle.crypto.tls;

public class LegacyTlsClient extends DefaultTlsClient {
    protected CertificateVerifyer i;

    public TlsAuthentication g() {
        return new LegacyTlsAuthentication(this.i);
    }
}
