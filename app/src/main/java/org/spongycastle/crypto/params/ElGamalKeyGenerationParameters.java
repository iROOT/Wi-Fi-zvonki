package org.spongycastle.crypto.params;

import java.security.SecureRandom;
import org.spongycastle.crypto.KeyGenerationParameters;

public class ElGamalKeyGenerationParameters extends KeyGenerationParameters {
    private ElGamalParameters a;

    public ElGamalKeyGenerationParameters(SecureRandom secureRandom, ElGamalParameters elGamalParameters) {
        super(secureRandom, a(elGamalParameters));
        this.a = elGamalParameters;
    }

    public ElGamalParameters c() {
        return this.a;
    }

    static int a(ElGamalParameters elGamalParameters) {
        return elGamalParameters.c() != 0 ? elGamalParameters.c() : elGamalParameters.a().bitLength();
    }
}
