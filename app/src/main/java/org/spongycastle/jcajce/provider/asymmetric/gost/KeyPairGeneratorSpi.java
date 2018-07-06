package org.spongycastle.jcajce.provider.asymmetric.gost;

import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import net.hockeyapp.android.k;
import org.spongycastle.asn1.cryptopro.CryptoProObjectIdentifiers;
import org.spongycastle.crypto.AsymmetricCipherKeyPair;
import org.spongycastle.crypto.generators.GOST3410KeyPairGenerator;
import org.spongycastle.crypto.params.GOST3410KeyGenerationParameters;
import org.spongycastle.crypto.params.GOST3410Parameters;
import org.spongycastle.crypto.params.GOST3410PrivateKeyParameters;
import org.spongycastle.crypto.params.GOST3410PublicKeyParameters;
import org.spongycastle.jce.spec.GOST3410ParameterSpec;
import org.spongycastle.jce.spec.GOST3410PublicKeyParameterSetSpec;

public class KeyPairGeneratorSpi extends KeyPairGenerator {
    GOST3410KeyGenerationParameters a;
    GOST3410KeyPairGenerator b = new GOST3410KeyPairGenerator();
    GOST3410ParameterSpec c;
    int d = k.FEEDBACK_FAILED_TITLE_ID;
    SecureRandom e = null;
    boolean f = false;

    public KeyPairGeneratorSpi() {
        super("GOST3410");
    }

    public void initialize(int i, SecureRandom secureRandom) {
        this.d = i;
        this.e = secureRandom;
    }

    private void a(GOST3410ParameterSpec gOST3410ParameterSpec, SecureRandom secureRandom) {
        GOST3410PublicKeyParameterSetSpec d = gOST3410ParameterSpec.d();
        this.a = new GOST3410KeyGenerationParameters(secureRandom, new GOST3410Parameters(d.a(), d.b(), d.c()));
        this.b.a(this.a);
        this.f = true;
        this.c = gOST3410ParameterSpec;
    }

    public void initialize(AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) {
        if (algorithmParameterSpec instanceof GOST3410ParameterSpec) {
            a((GOST3410ParameterSpec) algorithmParameterSpec, secureRandom);
            return;
        }
        throw new InvalidAlgorithmParameterException("parameter object not a GOST3410ParameterSpec");
    }

    public KeyPair generateKeyPair() {
        if (!this.f) {
            a(new GOST3410ParameterSpec(CryptoProObjectIdentifiers.n.d()), new SecureRandom());
        }
        AsymmetricCipherKeyPair a = this.b.a();
        return new KeyPair(new BCGOST3410PublicKey((GOST3410PublicKeyParameters) a.a(), this.c), new BCGOST3410PrivateKey((GOST3410PrivateKeyParameters) a.b(), this.c));
    }
}
