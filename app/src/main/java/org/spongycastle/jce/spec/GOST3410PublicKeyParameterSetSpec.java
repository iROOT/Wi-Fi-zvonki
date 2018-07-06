package org.spongycastle.jce.spec;

import java.math.BigInteger;

public class GOST3410PublicKeyParameterSetSpec {
    private BigInteger a;
    private BigInteger b;
    private BigInteger c;

    public GOST3410PublicKeyParameterSetSpec(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
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

    public boolean equals(Object obj) {
        if (!(obj instanceof GOST3410PublicKeyParameterSetSpec)) {
            return false;
        }
        GOST3410PublicKeyParameterSetSpec gOST3410PublicKeyParameterSetSpec = (GOST3410PublicKeyParameterSetSpec) obj;
        if (this.c.equals(gOST3410PublicKeyParameterSetSpec.c) && this.a.equals(gOST3410PublicKeyParameterSetSpec.a) && this.b.equals(gOST3410PublicKeyParameterSetSpec.b)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (this.c.hashCode() ^ this.a.hashCode()) ^ this.b.hashCode();
    }
}
