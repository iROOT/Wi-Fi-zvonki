package org.spongycastle.jce.provider;

import org.spongycastle.jce.exception.ExtException;

public class AnnotatedException extends Exception implements ExtException {
    private Throwable a;

    AnnotatedException(String str, Throwable th) {
        super(str);
        this.a = th;
    }

    AnnotatedException(String str) {
        this(str, null);
    }

    public Throwable getCause() {
        return this.a;
    }
}
