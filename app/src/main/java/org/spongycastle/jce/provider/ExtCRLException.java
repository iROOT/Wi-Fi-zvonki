package org.spongycastle.jce.provider;

import java.security.cert.CRLException;

class ExtCRLException extends CRLException {
    Throwable a;

    ExtCRLException(String str, Throwable th) {
        super(str);
        this.a = th;
    }

    public Throwable getCause() {
        return this.a;
    }
}
