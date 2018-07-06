package org.spongycastle.crypto.generators;

import java.math.BigInteger;
import java.util.Random;
import org.spongycastle.crypto.AsymmetricCipherKeyPair;
import org.spongycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.spongycastle.crypto.KeyGenerationParameters;
import org.spongycastle.crypto.params.GOST3410KeyGenerationParameters;
import org.spongycastle.crypto.params.GOST3410Parameters;
import org.spongycastle.crypto.params.GOST3410PrivateKeyParameters;
import org.spongycastle.crypto.params.GOST3410PublicKeyParameters;

public class GOST3410KeyPairGenerator implements AsymmetricCipherKeyPairGenerator {
    private static final BigInteger a = BigInteger.valueOf(0);
    private GOST3410KeyGenerationParameters b;

    public void a(KeyGenerationParameters keyGenerationParameters) {
        this.b = (GOST3410KeyGenerationParameters) keyGenerationParameters;
    }

    public AsymmetricCipherKeyPair a() {
        GOST3410Parameters c = this.b.c();
        Random a = this.b.a();
        BigInteger b = c.b();
        BigInteger a2 = c.a();
        BigInteger c2 = c.c();
        while (true) {
            BigInteger bigInteger = new BigInteger(256, a);
            if (!bigInteger.equals(a) && bigInteger.compareTo(b) < 0) {
                return new AsymmetricCipherKeyPair(new GOST3410PublicKeyParameters(c2.modPow(bigInteger, a2), c), new GOST3410PrivateKeyParameters(bigInteger, c));
            }
        }
    }
}
