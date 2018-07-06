package org.spongycastle.ocsp;

public class OCSPException extends Exception {
    Exception a;

    public OCSPException(String str) {
        super(str);
    }

    public OCSPException(String str, Exception exception) {
        super(str);
        this.a = exception;
    }

    public Throwable getCause() {
        return this.a;
    }
}
