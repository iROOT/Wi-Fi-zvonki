package org.spongycastle.crypto.params;

import java.math.BigInteger;
import java.util.Vector;

public class NaccacheSternPrivateKeyParameters extends NaccacheSternKeyParameters {
    private BigInteger c;
    private Vector d;

    public NaccacheSternPrivateKeyParameters(BigInteger bigInteger, BigInteger bigInteger2, int i, Vector vector, BigInteger bigInteger3) {
        super(true, bigInteger, bigInteger2, i);
        this.d = vector;
        this.c = bigInteger3;
    }

    public BigInteger e() {
        return this.c;
    }

    public Vector f() {
        return this.d;
    }
}
