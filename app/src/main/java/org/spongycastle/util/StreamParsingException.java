package org.spongycastle.util;

public class StreamParsingException extends Exception {
    Throwable a;

    public Throwable getCause() {
        return this.a;
    }
}
