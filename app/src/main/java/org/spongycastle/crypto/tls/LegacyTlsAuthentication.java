package org.spongycastle.crypto.tls;

public class LegacyTlsAuthentication extends ServerOnlyTlsAuthentication {
    protected CertificateVerifyer a;

    public LegacyTlsAuthentication(CertificateVerifyer certificateVerifyer) {
        this.a = certificateVerifyer;
    }

    public void a(Certificate certificate) {
        if (!this.a.a(certificate.a())) {
            throw new TlsFatalAlert((short) 90);
        }
    }
}
