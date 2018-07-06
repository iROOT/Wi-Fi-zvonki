package org.spongycastle.crypto.params;

import java.security.SecureRandom;
import org.spongycastle.crypto.KeyGenerationParameters;

public class ECKeyGenerationParameters extends KeyGenerationParameters {
    private ECDomainParameters a;

    public ECKeyGenerationParameters(ECDomainParameters eCDomainParameters, SecureRandom secureRandom) {
        super(secureRandom, eCDomainParameters.c().bitLength());
        this.a = eCDomainParameters;
    }

    public ECDomainParameters c() {
        return this.a;
    }
}
