package org.spongycastle.crypto.params;

import java.math.BigInteger;
import org.spongycastle.crypto.CipherParameters;

public class DSAParameters implements CipherParameters {
    private BigInteger a;
    private BigInteger b;
    private BigInteger c;
    private DSAValidationParameters d;

    public DSAParameters(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
        this.a = bigInteger3;
        this.c = bigInteger;
        this.b = bigInteger2;
    }

    public DSAParameters(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, DSAValidationParameters dSAValidationParameters) {
        this.a = bigInteger3;
        this.c = bigInteger;
        this.b = bigInteger2;
        this.d = dSAValidationParameters;
    }

    public BigInteger a() {
        return this.c;
    }

    public BigInteger b() {
        return this.b;
    }

    public BigInteger c() {
        return this.a;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof DSAParameters)) {
            return false;
        }
        DSAParameters dSAParameters = (DSAParameters) obj;
        if (dSAParameters.a().equals(this.c) && dSAParameters.b().equals(this.b) && dSAParameters.c().equals(this.a)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (a().hashCode() ^ b().hashCode()) ^ c().hashCode();
    }
}
