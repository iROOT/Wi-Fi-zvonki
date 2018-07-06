package org.spongycastle.crypto.params;

import java.security.SecureRandom;
import org.spongycastle.crypto.KeyGenerationParameters;

public class DSAKeyGenerationParameters extends KeyGenerationParameters {
    private DSAParameters a;

    public DSAKeyGenerationParameters(SecureRandom secureRandom, DSAParameters dSAParameters) {
        super(secureRandom, dSAParameters.a().bitLength() - 1);
        this.a = dSAParameters;
    }

    public DSAParameters c() {
        return this.a;
    }
}
