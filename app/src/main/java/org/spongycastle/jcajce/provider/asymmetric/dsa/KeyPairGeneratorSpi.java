package org.spongycastle.jcajce.provider.asymmetric.dsa;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.DSAParameterSpec;
import net.hockeyapp.android.k;
import org.spongycastle.crypto.AsymmetricCipherKeyPair;
import org.spongycastle.crypto.generators.DSAKeyPairGenerator;
import org.spongycastle.crypto.generators.DSAParametersGenerator;
import org.spongycastle.crypto.params.DSAKeyGenerationParameters;
import org.spongycastle.crypto.params.DSAParameters;
import org.spongycastle.crypto.params.DSAPrivateKeyParameters;
import org.spongycastle.crypto.params.DSAPublicKeyParameters;

public class KeyPairGeneratorSpi extends KeyPairGenerator {
    DSAKeyGenerationParameters a;
    DSAKeyPairGenerator b = new DSAKeyPairGenerator();
    int c = k.FEEDBACK_FAILED_TITLE_ID;
    int d = 20;
    SecureRandom e = new SecureRandom();
    boolean f = false;

    public KeyPairGeneratorSpi() {
        super("DSA");
    }

    public void initialize(int i, SecureRandom secureRandom) {
        if (i < 512 || i > k.FEEDBACK_FAILED_TITLE_ID || i % 64 != 0) {
            throw new InvalidParameterException("strength must be from 512 - 1024 and a multiple of 64");
        }
        this.c = i;
        this.e = secureRandom;
    }

    public void initialize(AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) {
        if (algorithmParameterSpec instanceof DSAParameterSpec) {
            DSAParameterSpec dSAParameterSpec = (DSAParameterSpec) algorithmParameterSpec;
            this.a = new DSAKeyGenerationParameters(secureRandom, new DSAParameters(dSAParameterSpec.getP(), dSAParameterSpec.getQ(), dSAParameterSpec.getG()));
            this.b.a(this.a);
            this.f = true;
            return;
        }
        throw new InvalidAlgorithmParameterException("parameter object not a DSAParameterSpec");
    }

    public KeyPair generateKeyPair() {
        if (!this.f) {
            DSAParametersGenerator dSAParametersGenerator = new DSAParametersGenerator();
            dSAParametersGenerator.a(this.c, this.d, this.e);
            this.a = new DSAKeyGenerationParameters(this.e, dSAParametersGenerator.a());
            this.b.a(this.a);
            this.f = true;
        }
        AsymmetricCipherKeyPair a = this.b.a();
        return new KeyPair(new BCDSAPublicKey((DSAPublicKeyParameters) a.a()), new BCDSAPrivateKey((DSAPrivateKeyParameters) a.b()));
    }
}
