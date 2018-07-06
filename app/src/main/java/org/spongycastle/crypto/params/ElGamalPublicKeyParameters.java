package org.spongycastle.crypto.params;

import java.math.BigInteger;

public class ElGamalPublicKeyParameters extends ElGamalKeyParameters {
    private BigInteger b;

    public ElGamalPublicKeyParameters(BigInteger bigInteger, ElGamalParameters elGamalParameters) {
        super(false, elGamalParameters);
        this.b = bigInteger;
    }

    public BigInteger c() {
        return this.b;
    }

    public int hashCode() {
        return this.b.hashCode() ^ super.hashCode();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ElGamalPublicKeyParameters)) {
            return false;
        }
        boolean z = ((ElGamalPublicKeyParameters) obj).c().equals(this.b) && super.equals(obj);
        return z;
    }
}
