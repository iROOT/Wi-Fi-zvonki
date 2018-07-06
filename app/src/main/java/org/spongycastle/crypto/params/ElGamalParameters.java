package org.spongycastle.crypto.params;

import java.math.BigInteger;
import org.spongycastle.crypto.CipherParameters;

public class ElGamalParameters implements CipherParameters {
    private BigInteger a;
    private BigInteger b;
    private int c;

    public ElGamalParameters(BigInteger bigInteger, BigInteger bigInteger2) {
        this(bigInteger, bigInteger2, 0);
    }

    public ElGamalParameters(BigInteger bigInteger, BigInteger bigInteger2, int i) {
        this.a = bigInteger2;
        this.b = bigInteger;
        this.c = i;
    }

    public BigInteger a() {
        return this.b;
    }

    public BigInteger b() {
        return this.a;
    }

    public int c() {
        return this.c;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ElGamalParameters)) {
            return false;
        }
        ElGamalParameters elGamalParameters = (ElGamalParameters) obj;
        if (elGamalParameters.a().equals(this.b) && elGamalParameters.b().equals(this.a) && elGamalParameters.c() == this.c) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (a().hashCode() ^ b().hashCode()) + this.c;
    }
}
