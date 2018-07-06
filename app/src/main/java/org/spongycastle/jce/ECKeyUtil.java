package org.spongycastle.jce;

public class ECKeyUtil {

    private static class UnexpectedException extends RuntimeException {
        private Throwable a;

        public Throwable getCause() {
            return this.a;
        }
    }
}
