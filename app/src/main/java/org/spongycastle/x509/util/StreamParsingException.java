package org.spongycastle.x509.util;

public class StreamParsingException extends Exception {
    Throwable a;

    public StreamParsingException(String str, Throwable th) {
        super(str);
        this.a = th;
    }

    public Throwable getCause() {
        return this.a;
    }
}
