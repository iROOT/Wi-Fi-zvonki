package org.spongycastle.pqc.crypto.mceliece;

import org.spongycastle.pqc.math.linearalgebra.GF2Matrix;

public class McEliecePublicKeyParameters extends McElieceKeyParameters {
    private String b;
    private int c;
    private int d;
    private GF2Matrix e;

    public McEliecePublicKeyParameters(String str, int i, int i2, GF2Matrix gF2Matrix, McElieceParameters mcElieceParameters) {
        super(false, mcElieceParameters);
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

    public String f() {
        return this.b;
    }

    public int g() {
        return this.e.g();
    }
}
