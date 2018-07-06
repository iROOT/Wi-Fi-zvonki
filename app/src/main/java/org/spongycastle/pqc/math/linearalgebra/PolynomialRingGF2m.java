package org.spongycastle.pqc.math.linearalgebra;

public class PolynomialRingGF2m {
    protected PolynomialGF2mSmallM[] a;
    protected PolynomialGF2mSmallM[] b;
    private GF2mField c;
    private PolynomialGF2mSmallM d;

    public PolynomialRingGF2m(GF2mField gF2mField, PolynomialGF2mSmallM polynomialGF2mSmallM) {
        this.c = gF2mField;
        this.d = polynomialGF2mSmallM;
        b();
        c();
    }

    public PolynomialGF2mSmallM[] a() {
        return this.b;
    }

    private void b() {
        int i;
        int a = this.d.a();
        this.a = new PolynomialGF2mSmallM[a];
        for (i = 0; i < (a >> 1); i++) {
            int[] iArr = new int[((i << 1) + 1)];
            iArr[i << 1] = 1;
            this.a[i] = new PolynomialGF2mSmallM(this.c, iArr);
        }
        for (i = a >> 1; i < a; i++) {
            iArr = new int[((i << 1) + 1)];
            iArr[i << 1] = 1;
            this.a[i] = new PolynomialGF2mSmallM(this.c, iArr).d(this.d);
        }
    }

    private void c() {
        int i;
        int a = this.d.a();
        PolynomialGF2mSmallM[] polynomialGF2mSmallMArr = new PolynomialGF2mSmallM[a];
        for (i = a - 1; i >= 0; i--) {
            polynomialGF2mSmallMArr[i] = new PolynomialGF2mSmallM(this.a[i]);
        }
        this.b = new PolynomialGF2mSmallM[a];
        for (i = a - 1; i >= 0; i--) {
            this.b[i] = new PolynomialGF2mSmallM(this.c, i);
        }
        for (int i2 = 0; i2 < a; i2++) {
            if (polynomialGF2mSmallMArr[i2].a(i2) == 0) {
                i = i2 + 1;
                Object obj = null;
                while (i < a) {
                    if (polynomialGF2mSmallMArr[i].a(i2) != 0) {
                        obj = 1;
                        a(polynomialGF2mSmallMArr, i2, i);
                        a(this.b, i2, i);
                        i = a;
                    }
                    i++;
                }
                if (obj == null) {
                    throw new ArithmeticException("Squaring matrix is not invertible.");
                }
            }
            i = this.c.a(polynomialGF2mSmallMArr[i2].a(i2));
            polynomialGF2mSmallMArr[i2].e(i);
            this.b[i2].e(i);
            for (i = 0; i < a; i++) {
                if (i != i2) {
                    int a2 = polynomialGF2mSmallMArr[i].a(i2);
                    if (a2 != 0) {
                        PolynomialGF2mSmallM d = polynomialGF2mSmallMArr[i2].d(a2);
                        PolynomialGF2mSmallM d2 = this.b[i2].d(a2);
                        polynomialGF2mSmallMArr[i].b(d);
                        this.b[i].b(d2);
                    }
                }
            }
        }
    }

    private static void a(PolynomialGF2mSmallM[] polynomialGF2mSmallMArr, int i, int i2) {
        PolynomialGF2mSmallM polynomialGF2mSmallM = polynomialGF2mSmallMArr[i];
        polynomialGF2mSmallMArr[i] = polynomialGF2mSmallMArr[i2];
        polynomialGF2mSmallMArr[i2] = polynomialGF2mSmallM;
    }
}
