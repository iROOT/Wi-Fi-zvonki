package org.spongycastle.crypto;

import java.security.SecureRandom;

public class KeyGenerationParameters {
    private SecureRandom a;
    private int b;

    public KeyGenerationParameters(SecureRandom secureRandom, int i) {
        this.a = secureRandom;
        this.b = i;
    }

    public SecureRandom a() {
        return this.a;
    }

    public int b() {
        return this.b;
    }
}
