package org.spongycastle.pqc.math.linearalgebra;

public class GF2mMatrix extends Matrix {
    protected GF2mField a;
    protected int[][] b;

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof GF2mMatrix)) {
            return false;
        }
        GF2mMatrix gF2mMatrix = (GF2mMatrix) obj;
        if (!this.a.equals(gF2mMatrix.a) || gF2mMatrix.c != this.d || gF2mMatrix.d != this.d) {
            return false;
        }
        for (int i = 0; i < this.c; i++) {
            for (int i2 = 0; i2 < this.d; i2++) {
                if (this.b[i][i2] != gF2mMatrix.b[i][i2]) {
                    return false;
                }
            }
        }
        return true;
    }

    public int hashCode() {
        int hashCode = (((this.a.hashCode() * 31) + this.c) * 31) + this.d;
        for (int i = 0; i < this.c; i++) {
            int i2 = 0;
            while (i2 < this.d) {
                int i3 = this.b[i][i2] + (hashCode * 31);
                i2++;
                hashCode = i3;
            }
        }
        return hashCode;
    }

    public String toString() {
        String str = this.c + " x " + this.d + " Matrix over " + this.a.toString() + ": \n";
        for (int i = 0; i < this.c; i++) {
            String str2 = str;
            for (int i2 = 0; i2 < this.d; i2++) {
                str2 = str2 + this.a.d(this.b[i][i2]) + " : ";
            }
            str = str2 + "\n";
        }
        return str;
    }
}
