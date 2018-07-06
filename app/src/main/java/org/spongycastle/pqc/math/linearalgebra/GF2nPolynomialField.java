package org.spongycastle.pqc.math.linearalgebra;

public class GF2nPolynomialField extends GF2nField {
    private boolean c;
    private boolean d;
    private int e;
    private int[] f;

    protected void c() {
        if (!d() && !e()) {
            f();
        }
    }

    private boolean d() {
        boolean z = false;
        this.b = new GF2Polynomial(this.a + 1);
        this.b.c(0);
        this.b.c(this.a);
        int i = 0;
        for (int i2 = 1; i2 < this.a && !r0; i2++) {
            this.b.c(i2);
            z = this.b.h();
            i++;
            if (z) {
                this.c = true;
                this.e = i2;
                break;
            }
            this.b.d(i2);
            z = this.b.h();
        }
        return z;
    }

    private boolean e() {
        this.b = new GF2Polynomial(this.a + 1);
        this.b.c(0);
        this.b.c(this.a);
        boolean z = false;
        int i = 0;
        loop0:
        for (int i2 = 1; i2 <= this.a - 3 && !r0; i2++) {
            this.b.c(i2);
            for (int i3 = i2 + 1; i3 <= this.a - 2 && !z; i3++) {
                this.b.c(i3);
                for (int i4 = i3 + 1; i4 <= this.a - 1 && !z; i4++) {
                    int i5;
                    this.b.c(i4);
                    int i6 = (this.a & 1) != 0 ? 1 : 0;
                    if ((i2 & 1) != 0) {
                        i5 = 1;
                    } else {
                        i5 = 0;
                    }
                    i6 |= i5;
                    if ((i3 & 1) != 0) {
                        i5 = 1;
                    } else {
                        i5 = 0;
                    }
                    i6 |= i5;
                    if ((i4 & 1) != 0) {
                        i5 = 1;
                    } else {
                        i5 = 0;
                    }
                    if ((i5 | i6) != 0) {
                        z = this.b.h();
                        i++;
                        if (z) {
                            this.d = true;
                            this.f[0] = i2;
                            this.f[1] = i3;
                            this.f[2] = i4;
                            break loop0;
                        }
                    }
                    this.b.d(i4);
                }
                this.b.d(i3);
            }
            this.b.d(i2);
        }
        return z;
    }

    private boolean f() {
        this.b = new GF2Polynomial(this.a + 1);
        int i = 0;
        do {
            i++;
            this.b.e();
            this.b.c(this.a);
            this.b.c(0);
        } while (!this.b.h());
        return true;
    }
}
