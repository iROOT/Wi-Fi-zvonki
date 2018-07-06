package org.spongycastle.pqc.math.ntru.polynomial;

import java.math.BigInteger;

public class Resultant {
    public BigIntPolynomial b;
    public BigInteger c;

    Resultant(BigIntPolynomial bigIntPolynomial, BigInteger bigInteger) {
        this.b = bigIntPolynomial;
        this.c = bigInteger;
    }
}
