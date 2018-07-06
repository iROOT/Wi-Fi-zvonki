package org.spongycastle.crypto.params;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.spongycastle.crypto.KeyGenerationParameters;

public class RSAKeyGenerationParameters extends KeyGenerationParameters {
    private BigInteger a;
    private int b;

    public RSAKeyGenerationParameters(BigInteger bigInteger, SecureRandom secureRandom, int i, int i2) {
        super(secureRandom, i);
        if (i < 12) {
            throw new IllegalArgumentException("key strength too small");
        } else if (bigInteger.testBit(0)) {
            this.a = bigInteger;
            this.b = i2;
        } else {
            throw new IllegalArgumentException("public exponent cannot be even");
        }
    }

    public BigInteger c() {
        return this.a;
    }

    public int d() {
        return this.b;
    }
}
