package org.spongycastle.crypto;

public class CryptoException extends Exception {
    private Throwable a;

    public CryptoException(String str) {
        super(str);
    }

    public CryptoException(String str, Throwable th) {
        super(str);
        this.a = th;
    }

    public Throwable getCause() {
        return this.a;
    }
}
