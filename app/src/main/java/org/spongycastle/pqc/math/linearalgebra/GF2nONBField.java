package org.spongycastle.pqc.math.linearalgebra;

public class GF2nONBField extends GF2nField {
    private int c;
    private int d;
    private int e;

    int d() {
        return this.c;
    }

    int e() {
        return this.d;
    }

    protected void c() {
        int i = 1;
        if (this.e == 1) {
            this.b = new GF2Polynomial(this.a + 1, "ALL");
        } else if (this.e == 2) {
            GF2Polynomial gF2Polynomial = new GF2Polynomial(this.a + 1, "ONE");
            GF2Polynomial gF2Polynomial2 = new GF2Polynomial(this.a + 1, "X");
            gF2Polynomial2.a(gF2Polynomial);
            GF2Polynomial gF2Polynomial3 = gF2Polynomial;
            while (i < this.a) {
                gF2Polynomial = gF2Polynomial2.k();
                gF2Polynomial.a(gF2Polynomial3);
                i++;
                gF2Polynomial3 = gF2Polynomial2;
                gF2Polynomial2 = gF2Polynomial;
            }
            this.b = gF2Polynomial2;
        }
    }
}
