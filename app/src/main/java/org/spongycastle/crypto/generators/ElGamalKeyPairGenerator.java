package org.spongycastle.crypto.generators;

import java.math.BigInteger;
import org.spongycastle.crypto.AsymmetricCipherKeyPair;
import org.spongycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.spongycastle.crypto.KeyGenerationParameters;
import org.spongycastle.crypto.params.DHParameters;
import org.spongycastle.crypto.params.ElGamalKeyGenerationParameters;
import org.spongycastle.crypto.params.ElGamalParameters;
import org.spongycastle.crypto.params.ElGamalPrivateKeyParameters;
import org.spongycastle.crypto.params.ElGamalPublicKeyParameters;

public class ElGamalKeyPairGenerator implements AsymmetricCipherKeyPairGenerator {
    private ElGamalKeyGenerationParameters a;

    public void a(KeyGenerationParameters keyGenerationParameters) {
        this.a = (ElGamalKeyGenerationParameters) keyGenerationParameters;
    }

    public AsymmetricCipherKeyPair a() {
        DHKeyGeneratorHelper dHKeyGeneratorHelper = DHKeyGeneratorHelper.a;
        ElGamalParameters c = this.a.c();
        DHParameters dHParameters = new DHParameters(c.a(), c.b(), null, c.c());
        BigInteger a = dHKeyGeneratorHelper.a(dHParameters, this.a.a());
        return new AsymmetricCipherKeyPair(new ElGamalPublicKeyParameters(dHKeyGeneratorHelper.a(dHParameters, a), c), new ElGamalPrivateKeyParameters(a, c));
    }
}
