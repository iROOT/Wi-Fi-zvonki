package org.spongycastle.util.encoders;

public class EncoderException extends IllegalStateException {
    private Throwable a;

    EncoderException(String str, Throwable th) {
        super(str);
        this.a = th;
    }

    public Throwable getCause() {
        return this.a;
    }
}
