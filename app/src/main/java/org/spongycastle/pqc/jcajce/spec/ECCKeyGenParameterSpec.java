package org.spongycastle.pqc.jcajce.spec;

import java.security.InvalidParameterException;
import java.security.spec.AlgorithmParameterSpec;
import org.spongycastle.pqc.math.linearalgebra.PolynomialRingGF2;

public class ECCKeyGenParameterSpec implements AlgorithmParameterSpec {
    private int a;
    private int b;
    private int c;
    private int d;

    public ECCKeyGenParameterSpec() {
        this(11, 50);
    }

    public ECCKeyGenParameterSpec(int i, int i2) {
        if (i < 1) {
            throw new InvalidParameterException("m must be positive");
        } else if (i > 32) {
            throw new InvalidParameterException("m is too large");
        } else {
            this.a = i;
            this.c = 1 << i;
            if (i2 < 0) {
                throw new InvalidParameterException("t must be positive");
            } else if (i2 > this.c) {
                throw new InvalidParameterException("t must be less than n = 2^m");
            } else {
                this.b = i2;
                this.d = PolynomialRingGF2.c(i);
            }
        }
    }

    public int a() {
        return this.a;
    }

    public int b() {
        return this.b;
    }
}
