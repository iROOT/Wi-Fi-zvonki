package org.spongycastle.pqc.math.ntru.polynomial;

public interface TernaryPolynomial extends Polynomial {
    int[] a();

    int[] b();

    int c();
}
