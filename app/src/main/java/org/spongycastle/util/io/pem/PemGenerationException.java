package org.spongycastle.util.io.pem;

import java.io.IOException;

public class PemGenerationException extends IOException {
    private Throwable a;

    public Throwable getCause() {
        return this.a;
    }
}
