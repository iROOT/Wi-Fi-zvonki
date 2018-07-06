package org.spongycastle.crypto;

import java.security.SecureRandom;

public class CipherKeyGenerator {
    protected SecureRandom a;
    protected int b;

    public void a(KeyGenerationParameters keyGenerationParameters) {
        this.a = keyGenerationParameters.a();
        this.b = (keyGenerationParameters.b() + 7) / 8;
    }

    public byte[] a() {
        byte[] bArr = new byte[this.b];
        this.a.nextBytes(bArr);
        return bArr;
    }
}
