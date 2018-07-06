package org.spongycastle.jcajce.provider.asymmetric.dh;

import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Hashtable;
import javax.crypto.spec.DHParameterSpec;
import net.hockeyapp.android.k;
import org.spongycastle.crypto.AsymmetricCipherKeyPair;
import org.spongycastle.crypto.generators.DHBasicKeyPairGenerator;
import org.spongycastle.crypto.generators.DHParametersGenerator;
import org.spongycastle.crypto.params.DHKeyGenerationParameters;
import org.spongycastle.crypto.params.DHParameters;
import org.spongycastle.crypto.params.DHPrivateKeyParameters;
import org.spongycastle.crypto.params.DHPublicKeyParameters;
import org.spongycastle.jce.provider.BouncyCastleProvider;
import org.spongycastle.util.Integers;

public class KeyPairGeneratorSpi extends KeyPairGenerator {
    private static Hashtable g = new Hashtable();
    private static Object h = new Object();
    DHKeyGenerationParameters a;
    DHBasicKeyPairGenerator b = new DHBasicKeyPairGenerator();
    int c = k.FEEDBACK_FAILED_TITLE_ID;
    int d = 20;
    SecureRandom e = new SecureRandom();
    boolean f = false;

    public KeyPairGeneratorSpi() {
        super("DH");
    }

    public void initialize(int i, SecureRandom secureRandom) {
        this.c = i;
        this.e = secureRandom;
    }

    public void initialize(AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) {
        if (algorithmParameterSpec instanceof DHParameterSpec) {
            DHParameterSpec dHParameterSpec = (DHParameterSpec) algorithmParameterSpec;
            this.a = new DHKeyGenerationParameters(secureRandom, new DHParameters(dHParameterSpec.getP(), dHParameterSpec.getG(), null, dHParameterSpec.getL()));
            this.b.a(this.a);
            this.f = true;
            return;
        }
        throw new InvalidAlgorithmParameterException("parameter object not a DHParameterSpec");
    }

    public KeyPair generateKeyPair() {
        if (!this.f) {
            Integer a = Integers.a(this.c);
            if (g.containsKey(a)) {
                this.a = (DHKeyGenerationParameters) g.get(a);
            } else {
                DHParameterSpec a2 = BouncyCastleProvider.a.a(this.c);
                if (a2 != null) {
                    this.a = new DHKeyGenerationParameters(this.e, new DHParameters(a2.getP(), a2.getG(), null, a2.getL()));
                } else {
                    synchronized (h) {
                        if (g.containsKey(a)) {
                            this.a = (DHKeyGenerationParameters) g.get(a);
                        } else {
                            DHParametersGenerator dHParametersGenerator = new DHParametersGenerator();
                            dHParametersGenerator.a(this.c, this.d, this.e);
                            this.a = new DHKeyGenerationParameters(this.e, dHParametersGenerator.a());
                            g.put(a, this.a);
                        }
                    }
                }
            }
            this.b.a(this.a);
            this.f = true;
        }
        AsymmetricCipherKeyPair a3 = this.b.a();
        return new KeyPair(new BCDHPublicKey((DHPublicKeyParameters) a3.a()), new BCDHPrivateKey((DHPrivateKeyParameters) a3.b()));
    }
}
