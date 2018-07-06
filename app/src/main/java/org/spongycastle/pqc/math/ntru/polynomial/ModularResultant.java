package org.spongycastle.pqc.math.ntru.polynomial;

import java.math.BigInteger;
import org.spongycastle.pqc.math.ntru.euclid.BigIntEuclidean;

public class ModularResultant extends Resultant {
    BigInteger a;

    ModularResultant(BigIntPolynomial bigIntPolynomial, BigInteger bigInteger, BigInteger bigInteger2) {
        super(bigIntPolynomial, bigInteger);
        this.a = bigInteger2;
    }

    static ModularResultant a(ModularResultant modularResultant, ModularResultant modularResultant2) {
        BigInteger bigInteger = modularResultant.a;
        BigInteger bigInteger2 = modularResultant2.a;
        BigInteger multiply = bigInteger.multiply(bigInteger2);
        BigIntEuclidean a = BigIntEuclidean.a(bigInteger2, bigInteger);
        BigIntPolynomial bigIntPolynomial = (BigIntPolynomial) modularResultant.b.clone();
        bigIntPolynomial.a(a.a.multiply(bigInteger2));
        BigIntPolynomial bigIntPolynomial2 = (BigIntPolynomial) modularResultant2.b.clone();
        bigIntPolynomial2.a(a.b.multiply(bigInteger));
        bigIntPolynomial.b(bigIntPolynomial2);
        bigIntPolynomial.c(multiply);
        return new ModularResultant(bigIntPolynomial, null, multiply);
    }
}
