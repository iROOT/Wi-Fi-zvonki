package org.spongycastle.i18n;

public class LocalizedException extends Exception {
    private Throwable a;

    public Throwable getCause() {
        return this.a;
    }
}
