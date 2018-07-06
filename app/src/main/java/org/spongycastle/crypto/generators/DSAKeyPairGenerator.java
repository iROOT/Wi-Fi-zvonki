package org.spongycastle.crypto.generators;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.spongycastle.crypto.AsymmetricCipherKeyPair;
import org.spongycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.spongycastle.crypto.KeyGenerationParameters;
import org.spongycastle.crypto.params.DSAKeyGenerationParameters;
import org.spongycastle.crypto.params.DSAParameters;
import org.spongycastle.crypto.params.DSAPrivateKeyParameters;
import org.spongycastle.crypto.params.DSAPublicKeyParameters;
import org.spongycastle.util.BigIntegers;

public class DSAKeyPairGenerator implements AsymmetricCipherKeyPairGenerator {
    private static final BigInteger a = BigInteger.valueOf(1);
    private DSAKeyGenerationParameters b;

    public void a(KeyGenerationParameters keyGenerationParameters) {
        this.b = (DSAKeyGenerationParameters) keyGenerationParameters;
    }

    public AsymmetricCipherKeyPair a() {
        DSAParameters c = this.b.c();
        BigInteger a = a(c.b(), this.b.a());
        return new AsymmetricCipherKeyPair(new DSAPublicKeyParameters(a(c.a(), c.c(), a), c), new DSAPrivateKeyParameters(a, c));
    }

    private static BigInteger a(BigInteger bigInteger, SecureRandom secureRandom) {
        return BigIntegers.a(a, bigInteger.subtract(a), secureRandom);
    }

    private static BigInteger a(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
        return bigInteger2.modPow(bigInteger3, bigInteger);
    }
}
