package org.spongycastle.crypto.params;

import java.math.BigInteger;

public class DHPublicKeyParameters extends DHKeyParameters {
    private BigInteger b;

    public DHPublicKeyParameters(BigInteger bigInteger, DHParameters dHParameters) {
        super(false, dHParameters);
        this.b = bigInteger;
    }

    public BigInteger c() {
        return this.b;
    }

    public int hashCode() {
        return this.b.hashCode() ^ super.hashCode();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof DHPublicKeyParameters)) {
            return false;
        }
        boolean z = ((DHPublicKeyParameters) obj).c().equals(this.b) && super.equals(obj);
        return z;
    }
}
