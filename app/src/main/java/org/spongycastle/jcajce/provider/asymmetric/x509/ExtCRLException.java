package org.spongycastle.jcajce.provider.asymmetric.x509;

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
