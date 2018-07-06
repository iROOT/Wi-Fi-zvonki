package org.spongycastle.pqc.math.linearalgebra;

public class GF2nPolynomial {
    private GF2nElement[] a;
    private int b;

    public final int a() {
        for (int i = this.b - 1; i >= 0; i--) {
            if (!this.a[i].a()) {
                return i;
            }
        }
        return -1;
    }

    public final boolean equals(Object obj) {
        if (obj == null || !(obj instanceof GF2nPolynomial)) {
            return false;
        }
        GF2nPolynomial gF2nPolynomial = (GF2nPolynomial) obj;
        if (a() != gF2nPolynomial.a()) {
            return false;
        }
        for (int i = 0; i < this.b; i++) {
            if (!this.a[i].equals(gF2nPolynomial.a[i])) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        return a() + this.a.hashCode();
    }
}
