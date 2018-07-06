package org.spongycastle.jcajce.provider.asymmetric.rsa;

import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.RSAKeyGenParameterSpec;
import net.hockeyapp.android.k;
import org.spongycastle.crypto.AsymmetricCipherKeyPair;
import org.spongycastle.crypto.generators.RSAKeyPairGenerator;
import org.spongycastle.crypto.params.RSAKeyGenerationParameters;
import org.spongycastle.crypto.params.RSAKeyParameters;
import org.spongycastle.crypto.params.RSAPrivateCrtKeyParameters;

public class KeyPairGeneratorSpi extends KeyPairGenerator {
    static final BigInteger a = BigInteger.valueOf(65537);
    RSAKeyGenerationParameters b = new RSAKeyGenerationParameters(a, new SecureRandom(), k.DIALOG_POSITIVE_BUTTON_ID, 12);
    RSAKeyPairGenerator c = new RSAKeyPairGenerator();

    public KeyPairGeneratorSpi() {
        super("RSA");
        this.c.a(this.b);
    }

    public void initialize(int i, SecureRandom secureRandom) {
        this.b = new RSAKeyGenerationParameters(a, secureRandom, i, 12);
        this.c.a(this.b);
    }

    public void initialize(AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) {
        if (algorithmParameterSpec instanceof RSAKeyGenParameterSpec) {
            RSAKeyGenParameterSpec rSAKeyGenParameterSpec = (RSAKeyGenParameterSpec) algorithmParameterSpec;
            this.b = new RSAKeyGenerationParameters(rSAKeyGenParameterSpec.getPublicExponent(), secureRandom, rSAKeyGenParameterSpec.getKeysize(), 12);
            this.c.a(this.b);
            return;
        }
        throw new InvalidAlgorithmParameterException("parameter object not a RSAKeyGenParameterSpec");
    }

    public KeyPair generateKeyPair() {
        AsymmetricCipherKeyPair a = this.c.a();
        return new KeyPair(new BCRSAPublicKey((RSAKeyParameters) a.a()), new BCRSAPrivateCrtKey((RSAPrivateCrtKeyParameters) a.b()));
    }
}
