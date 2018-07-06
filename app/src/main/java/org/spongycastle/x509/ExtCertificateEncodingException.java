package org.spongycastle.x509;

import java.security.cert.CertificateEncodingException;

class ExtCertificateEncodingException extends CertificateEncodingException {
    Throwable a;

    public Throwable getCause() {
        return this.a;
    }
}
