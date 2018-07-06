package org.spongycastle.pqc.jcajce.provider.rainbow;

import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import net.hockeyapp.android.k;
import org.spongycastle.crypto.AsymmetricCipherKeyPair;
import org.spongycastle.pqc.crypto.rainbow.RainbowKeyGenerationParameters;
import org.spongycastle.pqc.crypto.rainbow.RainbowKeyPairGenerator;
import org.spongycastle.pqc.crypto.rainbow.RainbowParameters;
import org.spongycastle.pqc.crypto.rainbow.RainbowPrivateKeyParameters;
import org.spongycastle.pqc.crypto.rainbow.RainbowPublicKeyParameters;
import org.spongycastle.pqc.jcajce.spec.RainbowParameterSpec;

public class RainbowKeyPairGeneratorSpi extends KeyPairGenerator {
    RainbowKeyGenerationParameters a;
    RainbowKeyPairGenerator b = new RainbowKeyPairGenerator();
    int c = k.FEEDBACK_FAILED_TITLE_ID;
    SecureRandom d = new SecureRandom();
    boolean e = false;

    public RainbowKeyPairGeneratorSpi() {
        super("Rainbow");
    }

    public void initialize(int i, SecureRandom secureRandom) {
        this.c = i;
        this.d = secureRandom;
    }

    public void initialize(AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) {
        if (algorithmParameterSpec instanceof RainbowParameterSpec) {
            this.a = new RainbowKeyGenerationParameters(secureRandom, new RainbowParameters(((RainbowParameterSpec) algorithmParameterSpec).a()));
            this.b.b(this.a);
            this.e = true;
            return;
        }
        throw new InvalidAlgorithmParameterException("parameter object not a RainbowParameterSpec");
    }

    public KeyPair generateKeyPair() {
        if (!this.e) {
            this.a = new RainbowKeyGenerationParameters(this.d, new RainbowParameters(new RainbowParameterSpec().a()));
            this.b.b(this.a);
            this.e = true;
        }
        AsymmetricCipherKeyPair a = this.b.a();
        return new KeyPair(new BCRainbowPublicKey((RainbowPublicKeyParameters) a.a()), new BCRainbowPrivateKey((RainbowPrivateKeyParameters) a.b()));
    }
}
