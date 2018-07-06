package org.spongycastle.pqc.crypto.mceliece;

import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.pqc.math.linearalgebra.PolynomialRingGF2;

public class McElieceParameters implements CipherParameters {
    private int a;
    private int b;
    private int c;
    private int d;

    public McElieceParameters() {
        this(11, 50);
    }

    public McElieceParameters(int i, int i2) {
        if (i < 1) {
            throw new IllegalArgumentException("m must be positive");
        } else if (i > 32) {
            throw new IllegalArgumentException("m is too large");
        } else {
            this.a = i;
            this.c = 1 << i;
            if (i2 < 0) {
                throw new IllegalArgumentException("t must be positive");
            } else if (i2 > this.c) {
                throw new IllegalArgumentException("t must be less than n = 2^m");
            } else {
                this.b = i2;
                this.d = PolynomialRingGF2.c(i);
            }
        }
    }

    public int b() {
        return this.a;
    }

    public int c() {
        return this.c;
    }

    public int d() {
        return this.b;
    }

    public int e() {
        return this.d;
    }
}
