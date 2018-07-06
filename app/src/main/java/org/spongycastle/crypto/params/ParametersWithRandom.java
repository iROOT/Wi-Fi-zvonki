package org.spongycastle.crypto.params;

import java.security.SecureRandom;
import org.spongycastle.crypto.CipherParameters;

public class ParametersWithRandom implements CipherParameters {
    private SecureRandom a;
    private CipherParameters b;

    public ParametersWithRandom(CipherParameters cipherParameters, SecureRandom secureRandom) {
        this.a = secureRandom;
        this.b = cipherParameters;
    }

    public SecureRandom a() {
        return this.a;
    }

    public CipherParameters b() {
        return this.b;
    }
}
