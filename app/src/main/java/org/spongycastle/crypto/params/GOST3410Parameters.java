package org.spongycastle.crypto.params;

import java.math.BigInteger;
import org.spongycastle.crypto.CipherParameters;

public class GOST3410Parameters implements CipherParameters {
    private BigInteger a;
    private BigInteger b;
    private BigInteger c;

    public GOST3410Parameters(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
        this.a = bigInteger;
        this.b = bigInteger2;
        this.c = bigInteger3;
    }

    public BigInteger a() {
        return this.a;
    }

    public BigInteger b() {
        return this.b;
    }

    public BigInteger c() {
        return this.c;
    }

    public int hashCode() {
        return (this.a.hashCode() ^ this.b.hashCode()) ^ this.c.hashCode();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof GOST3410Parameters)) {
            return false;
        }
        GOST3410Parameters gOST3410Parameters = (GOST3410Parameters) obj;
        if (gOST3410Parameters.a().equals(this.a) && gOST3410Parameters.b().equals(this.b) && gOST3410Parameters.c().equals(this.c)) {
            return true;
        }
        return false;
    }
}
