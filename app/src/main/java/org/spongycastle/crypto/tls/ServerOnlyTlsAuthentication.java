package org.spongycastle.crypto.tls;

public abstract class ServerOnlyTlsAuthentication implements TlsAuthentication {
    public final TlsCredentials a(CertificateRequest certificateRequest) {
        return null;
    }
}
