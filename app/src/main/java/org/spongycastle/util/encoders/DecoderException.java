package org.spongycastle.util.encoders;

public class DecoderException extends IllegalStateException {
    private Throwable a;

    DecoderException(String str, Throwable th) {
        super(str);
        this.a = th;
    }

    public Throwable getCause() {
        return this.a;
    }
}
