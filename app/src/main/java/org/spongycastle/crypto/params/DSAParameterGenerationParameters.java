package org.spongycastle.crypto.params;

import java.security.SecureRandom;

public class DSAParameterGenerationParameters {
    private final int a;
    private final int b;
    private final int c;
    private final int d;
    private final SecureRandom e;

    public DSAParameterGenerationParameters(int i, int i2, int i3, SecureRandom secureRandom) {
        this(i, i2, i3, secureRandom, -1);
    }

    public DSAParameterGenerationParameters(int i, int i2, int i3, SecureRandom secureRandom, int i4) {
        this.a = i;
        this.b = i2;
        this.d = i3;
        this.c = i4;
        this.e = secureRandom;
    }

    public int a() {
        return this.a;
    }

    public int b() {
        return this.b;
    }

    public int c() {
        return this.d;
    }

    public SecureRandom d() {
        return this.e;
    }

    public int e() {
        return this.c;
    }
}
