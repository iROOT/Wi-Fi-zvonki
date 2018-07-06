package org.spongycastle.pqc.crypto.mceliece;

import org.spongycastle.pqc.math.linearalgebra.GF2Matrix;
import org.spongycastle.pqc.math.linearalgebra.GF2mField;
import org.spongycastle.pqc.math.linearalgebra.Permutation;
import org.spongycastle.pqc.math.linearalgebra.PolynomialGF2mSmallM;

public class McEliecePrivateKeyParameters extends McElieceKeyParameters {
    private String b;
    private int c;
    private int d;
    private GF2mField e;
    private PolynomialGF2mSmallM f;
    private GF2Matrix g;
    private Permutation h;
    private Permutation i;
    private GF2Matrix j;
    private PolynomialGF2mSmallM[] k;

    public McEliecePrivateKeyParameters(String str, int i, int i2, GF2mField gF2mField, PolynomialGF2mSmallM polynomialGF2mSmallM, GF2Matrix gF2Matrix, Permutation permutation, Permutation permutation2, GF2Matrix gF2Matrix2, PolynomialGF2mSmallM[] polynomialGF2mSmallMArr, McElieceParameters mcElieceParameters) {
        super(true, mcElieceParameters);
        this.b = str;
        this.d = i2;
        this.c = i;
        this.e = gF2mField;
        this.f = polynomialGF2mSmallM;
        this.g = gF2Matrix;
        this.h = permutation;
        this.i = permutation2;
        this.j = gF2Matrix2;
        this.k = polynomialGF2mSmallMArr;
    }

    public int c() {
        return this.c;
    }

    public int d() {
        return this.d;
    }

    public GF2mField e() {
        return this.e;
    }

    public PolynomialGF2mSmallM f() {
        return this.f;
    }

    public GF2Matrix g() {
        return this.g;
    }

    public Permutation h() {
        return this.h;
    }

    public Permutation i() {
        return this.i;
    }

    public GF2Matrix j() {
        return this.j;
    }

    public PolynomialGF2mSmallM[] k() {
        return this.k;
    }

    public String l() {
        return this.b;
    }
}
