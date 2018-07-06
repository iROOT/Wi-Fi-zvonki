package org.spongycastle.crypto.prng;

public class ThreadedSeedGenerator {

    private class SeedGenerator implements Runnable {
        private volatile int a;
        private volatile boolean b;

        public void run() {
            while (!this.b) {
                this.a++;
            }
        }
    }
}
