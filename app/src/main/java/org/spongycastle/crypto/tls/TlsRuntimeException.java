package org.spongycastle.crypto.tls;

public class TlsRuntimeException extends RuntimeException {
    Throwable a;

    public TlsRuntimeException(String str) {
        super(str);
    }

    public Throwable getCause() {
        return this.a;
    }
}
