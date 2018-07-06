package org.spongycastle.pqc.math.linearalgebra;

public abstract class GF2nField {
    protected int a;
    protected GF2Polynomial b;

    protected abstract void c();

    public final int a() {
        return this.a;
    }

    public final GF2Polynomial b() {
        if (this.b == null) {
            c();
        }
        return new GF2Polynomial(this.b);
    }

    public final boolean equals(Object obj) {
        if (obj == null || !(obj instanceof GF2nField)) {
            return false;
        }
        GF2nField gF2nField = (GF2nField) obj;
        if (gF2nField.a != this.a || !this.b.equals(gF2nField.b)) {
            return false;
        }
        if ((this instanceof GF2nPolynomialField) && !(gF2nField instanceof GF2nPolynomialField)) {
            return false;
        }
        if (!(this instanceof GF2nONBField) || (gF2nField instanceof GF2nONBField)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this.a + this.b.hashCode();
    }
}
