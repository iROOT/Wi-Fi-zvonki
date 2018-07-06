package org.spongycastle.crypto.params;

import java.math.BigInteger;
import org.spongycastle.crypto.CipherParameters;

public class RSABlindingParameters implements CipherParameters {
    private RSAKeyParameters a;
    private BigInteger b;

    public RSAKeyParameters a() {
        return this.a;
    }

    public BigInteger b() {
        return this.b;
    }
}
