package org.spongycastle.jce.exception;

import java.io.IOException;

public class ExtIOException extends IOException implements ExtException {
    private Throwable a;

    public Throwable getCause() {
        return this.a;
    }
}
