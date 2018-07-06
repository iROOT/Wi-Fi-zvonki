package org.spongycastle.crypto.params;

import java.security.SecureRandom;
import org.spongycastle.crypto.KeyGenerationParameters;

public class DHKeyGenerationParameters extends KeyGenerationParameters {
    private DHParameters a;

    public DHKeyGenerationParameters(SecureRandom secureRandom, DHParameters dHParameters) {
        super(secureRandom, a(dHParameters));
        this.a = dHParameters;
    }

    public DHParameters c() {
        return this.a;
    }

    static int a(DHParameters dHParameters) {
        return dHParameters.e() != 0 ? dHParameters.e() : dHParameters.a().bitLength();
    }
}
