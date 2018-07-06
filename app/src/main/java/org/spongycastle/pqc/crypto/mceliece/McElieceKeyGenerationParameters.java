package org.spongycastle.pqc.crypto.mceliece;

import java.security.SecureRandom;
import org.spongycastle.crypto.KeyGenerationParameters;

public class McElieceKeyGenerationParameters extends KeyGenerationParameters {
    private McElieceParameters a;

    public McElieceKeyGenerationParameters(SecureRandom secureRandom, McElieceParameters mcElieceParameters) {
        super(secureRandom, 256);
        this.a = mcElieceParameters;
    }

    public McElieceParameters c() {
        return this.a;
    }
}
