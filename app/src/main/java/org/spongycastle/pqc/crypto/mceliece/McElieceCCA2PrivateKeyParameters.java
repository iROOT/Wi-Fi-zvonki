package org.spongycastle.pqc.crypto.mceliece;

import org.spongycastle.pqc.math.linearalgebra.GF2Matrix;
import org.spongycastle.pqc.math.linearalgebra.GF2mField;
import org.spongycastle.pqc.math.linearalgebra.Permutation;
import org.spongycastle.pqc.math.linearalgebra.PolynomialGF2mSmallM;

public class McElieceCCA2PrivateKeyParameters extends McElieceCCA2KeyParameters {
    private String b;
    private int c;
    private int d;
    private GF2mField e;
    private PolynomialGF2mSmallM f;
    private Permutation g;
    private GF2Matrix h;
    private PolynomialGF2mSmallM[] i;

    public McElieceCCA2PrivateKeyParameters(String str, int i, int i2, GF2mField gF2mField, PolynomialGF2mSmallM polynomialGF2mSmallM, Permutation permutation, GF2Matrix gF2Matrix, PolynomialGF2mSmallM[] polynomialGF2mSmallMArr, McElieceCCA2Parameters mcElieceCCA2Parameters) {
        super(true, mcElieceCCA2Parameters);
        this.b = str;
        this.c = i;
        this.d = i2;
        this.e = gF2mField;
        this.f = polynomialGF2mSmallM;
        this.g = permutation;
        this.h = gF2Matrix;
        this.i = polynomialGF2mSmallMArr;
    }

    public int c() {
        return this.c;
    }

    public int d() {
        return this.d;
    }

    public int e() {
        return this.f.a();
    }

    public GF2mField f() {
        return this.e;
    }

    public PolynomialGF2mSmallM g() {
        return this.f;
    }

    public Permutation h() {
        return this.g;
    }

    public GF2Matrix i() {
        return this.h;
    }

    public PolynomialGF2mSmallM[] j() {
        return this.i;
    }

    public String k() {
        return this.b;
    }
}
