package org.spongycastle.crypto.generators;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.spongycastle.crypto.AsymmetricCipherKeyPair;
import org.spongycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.spongycastle.crypto.KeyGenerationParameters;
import org.spongycastle.crypto.params.ECDomainParameters;
import org.spongycastle.crypto.params.ECKeyGenerationParameters;
import org.spongycastle.crypto.params.ECPrivateKeyParameters;
import org.spongycastle.crypto.params.ECPublicKeyParameters;
import org.spongycastle.math.ec.ECConstants;

public class ECKeyPairGenerator implements AsymmetricCipherKeyPairGenerator, ECConstants {
    ECDomainParameters a;
    SecureRandom b;

    public void a(KeyGenerationParameters keyGenerationParameters) {
        ECKeyGenerationParameters eCKeyGenerationParameters = (ECKeyGenerationParameters) keyGenerationParameters;
        this.b = eCKeyGenerationParameters.a();
        this.a = eCKeyGenerationParameters.c();
        if (this.b == null) {
            this.b = new SecureRandom();
        }
    }

    public AsymmetricCipherKeyPair a() {
        BigInteger c = this.a.c();
        int bitLength = c.bitLength();
        while (true) {
            BigInteger bigInteger = new BigInteger(bitLength, this.b);
            if (!bigInteger.equals(c) && bigInteger.compareTo(c) < 0) {
                return new AsymmetricCipherKeyPair(new ECPublicKeyParameters(this.a.b().a(bigInteger), this.a), new ECPrivateKeyParameters(bigInteger, this.a));
            }
        }
    }
}
