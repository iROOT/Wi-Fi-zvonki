package org.spongycastle.crypto.params;

import java.math.BigInteger;

public class ElGamalPrivateKeyParameters extends ElGamalKeyParameters {
    private BigInteger b;

    public ElGamalPrivateKeyParameters(BigInteger bigInteger, ElGamalParameters elGamalParameters) {
        super(true, elGamalParameters);
        this.b = bigInteger;
    }

    public BigInteger c() {
        return this.b;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ElGamalPrivateKeyParameters)) {
            return false;
        }
        if (((ElGamalPrivateKeyParameters) obj).c().equals(this.b)) {
            return super.equals(obj);
        }
        return false;
    }

    public int hashCode() {
        return c().hashCode();
    }
}
