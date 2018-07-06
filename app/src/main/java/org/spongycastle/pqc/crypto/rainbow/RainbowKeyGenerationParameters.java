package org.spongycastle.pqc.crypto.rainbow;

import java.security.SecureRandom;
import org.spongycastle.crypto.KeyGenerationParameters;

public class RainbowKeyGenerationParameters extends KeyGenerationParameters {
    private RainbowParameters a;

    public RainbowKeyGenerationParameters(SecureRandom secureRandom, RainbowParameters rainbowParameters) {
        super(secureRandom, rainbowParameters.b()[rainbowParameters.b().length - 1] - rainbowParameters.b()[0]);
        this.a = rainbowParameters;
    }

    public RainbowParameters c() {
        return this.a;
    }
}
