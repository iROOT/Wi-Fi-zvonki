package org.spongycastle.jce.exception;

import java.security.cert.CertificateEncodingException;

public class ExtCertificateEncodingException extends CertificateEncodingException implements ExtException {
    private Throwable a;

    public Throwable getCause() {
        return this.a;
    }
}
