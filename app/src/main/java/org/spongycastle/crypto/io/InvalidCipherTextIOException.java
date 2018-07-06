package org.spongycastle.crypto.io;

import java.io.IOException;

public class InvalidCipherTextIOException extends IOException {
    private final Throwable a;

    public InvalidCipherTextIOException(String str, Throwable th) {
        super(str);
        this.a = th;
    }

    public Throwable getCause() {
        return this.a;
    }
}
