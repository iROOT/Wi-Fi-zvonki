package org.spongycastle.jce.exception;

import java.security.cert.CertPathBuilderException;

public class ExtCertPathBuilderException extends CertPathBuilderException implements ExtException {
    private Throwable a;

    public ExtCertPathBuilderException(String str, Throwable th) {
        super(str);
        this.a = th;
    }

    public Throwable getCause() {
        return this.a;
    }
}
