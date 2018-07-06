package org.spongycastle.util;

public class StoreException extends RuntimeException {
    private Throwable a;

    public StoreException(String str, Throwable th) {
        super(str);
        this.a = th;
    }

    public Throwable getCause() {
        return this.a;
    }
}
