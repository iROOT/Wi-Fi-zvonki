package org.spongycastle.crypto.params;

import java.math.BigInteger;

public class DHPrivateKeyParameters extends DHKeyParameters {
    private BigInteger b;

    public DHPrivateKeyParameters(BigInteger bigInteger, DHParameters dHParameters) {
        super(true, dHParameters);
        this.b = bigInteger;
    }

    public BigInteger c() {
        return this.b;
    }

    public int hashCode() {
        return this.b.hashCode() ^ super.hashCode();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof DHPrivateKeyParameters)) {
            return false;
        }
        boolean z = ((DHPrivateKeyParameters) obj).c().equals(this.b) && super.equals(obj);
        return z;
    }
}
