package org.spongycastle.pqc.crypto.gmss;

import java.security.SecureRandom;
import org.spongycastle.crypto.KeyGenerationParameters;

public class GMSSKeyGenerationParameters extends KeyGenerationParameters {
    private GMSSParameters a;

    public GMSSKeyGenerationParameters(SecureRandom secureRandom, GMSSParameters gMSSParameters) {
        super(secureRandom, 1);
        this.a = gMSSParameters;
    }

    public GMSSParameters c() {
        return this.a;
    }
}
