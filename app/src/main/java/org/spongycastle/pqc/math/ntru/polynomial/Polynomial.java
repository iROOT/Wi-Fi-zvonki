package org.spongycastle.pqc.math.ntru.polynomial;

public interface Polynomial {
    BigIntPolynomial a(BigIntPolynomial bigIntPolynomial);

    IntegerPolynomial a(IntegerPolynomial integerPolynomial);

    IntegerPolynomial a(IntegerPolynomial integerPolynomial, int i);

    IntegerPolynomial m();
}
