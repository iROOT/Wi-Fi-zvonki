package org.spongycastle.pqc.crypto.mceliece;

import org.spongycastle.pqc.math.linearalgebra.GF2Matrix;

public class McElieceCCA2PublicKeyParameters extends McElieceCCA2KeyParameters {
    private String b;
    private int c;
    private int d;
    private GF2Matrix e;

    public McElieceCCA2PublicKeyParameters(String str, int i, int i2, GF2Matrix gF2Matrix, McElieceCCA2Parameters mcElieceCCA2Parameters) {
        super(false, mcElieceCCA2Parameters);
        this.b = str;
        this.c = i;
        this.d = i2;
        this.e = new GF2Matrix(gF2Matrix);
    }

    public int c() {
        return this.c;
    }

    public int d() {
        return this.d;
    }

    public GF2Matrix e() {
        return this.e;
    }

    public int f() {
        return this.e.g();
    }

    public String g() {
        return this.b;
    }
}
