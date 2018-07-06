package org.spongycastle.crypto.generators;

import java.math.BigInteger;
import org.spongycastle.crypto.AsymmetricCipherKeyPair;
import org.spongycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.spongycastle.crypto.KeyGenerationParameters;
import org.spongycastle.crypto.params.DHKeyGenerationParameters;
import org.spongycastle.crypto.params.DHParameters;
import org.spongycastle.crypto.params.DHPrivateKeyParameters;
import org.spongycastle.crypto.params.DHPublicKeyParameters;

public class DHKeyPairGenerator implements AsymmetricCipherKeyPairGenerator {
    private DHKeyGenerationParameters a;

    public void a(KeyGenerationParameters keyGenerationParameters) {
        this.a = (DHKeyGenerationParameters) keyGenerationParameters;
    }

    public AsymmetricCipherKeyPair a() {
        DHKeyGeneratorHelper dHKeyGeneratorHelper = DHKeyGeneratorHelper.a;
        DHParameters c = this.a.c();
        BigInteger a = dHKeyGeneratorHelper.a(c, this.a.a());
        return new AsymmetricCipherKeyPair(new DHPublicKeyParameters(dHKeyGeneratorHelper.a(c, a), c), new DHPrivateKeyParameters(a, c));
    }
}
