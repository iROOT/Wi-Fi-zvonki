package org.spongycastle.pqc.math.ntru.polynomial;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.spongycastle.pqc.math.ntru.util.Util;
import org.spongycastle.util.Arrays;

public class SparseTernaryPolynomial implements TernaryPolynomial {
    private int a;
    private int[] b = new int[this.a];
    private int[] c = new int[this.a];

    public SparseTernaryPolynomial(int[] iArr) {
        int i = 0;
        this.a = iArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i < this.a) {
            int i4 = iArr[i];
            switch (i4) {
                case -1:
                    i4 = i2 + 1;
                    this.c[i2] = i;
                    i2 = i4;
                    break;
                case 0:
                    break;
                case 1:
                    i4 = i3 + 1;
                    this.b[i3] = i;
                    i3 = i4;
                    break;
                default:
                    throw new IllegalArgumentException("Illegal value: " + i4 + ", must be one of {-1, 0, 1}");
            }
            i++;
        }
        this.b = Arrays.b(this.b, i3);
        this.c = Arrays.b(this.c, i2);
    }

    public static SparseTernaryPolynomial a(int i, int i2, int i3, SecureRandom secureRandom) {
        return new SparseTernaryPolynomial(Util.a(i, i2, i3, secureRandom));
    }

    public IntegerPolynomial a(IntegerPolynomial integerPolynomial) {
        int i = 0;
        int[] iArr = integerPolynomial.a;
        if (iArr.length != this.a) {
            throw new IllegalArgumentException("Number of coefficients must be the same");
        }
        int i2;
        int[] iArr2 = new int[this.a];
        for (i2 = 0; i2 != this.b.length; i2++) {
            int i3 = (this.a - 1) - this.b[i2];
            for (int i4 = this.a - 1; i4 >= 0; i4--) {
                iArr2[i4] = iArr2[i4] + iArr[i3];
                i3--;
                if (i3 < 0) {
                    i3 = this.a - 1;
                }
            }
        }
        while (i != this.c.length) {
            i2 = (this.a - 1) - this.c[i];
            for (i3 = this.a - 1; i3 >= 0; i3--) {
                iArr2[i3] = iArr2[i3] - iArr[i2];
                i2--;
                if (i2 < 0) {
                    i2 = this.a - 1;
                }
            }
            i++;
        }
        return new IntegerPolynomial(iArr2);
    }

    public IntegerPolynomial a(IntegerPolynomial integerPolynomial, int i) {
        IntegerPolynomial a = a(integerPolynomial);
        a.i(i);
        return a;
    }

    public BigIntPolynomial a(BigIntPolynomial bigIntPolynomial) {
        int i = 0;
        BigInteger[] bigIntegerArr = bigIntPolynomial.a;
        if (bigIntegerArr.length != this.a) {
            throw new IllegalArgumentException("Number of coefficients must be the same");
        }
        int i2;
        BigInteger[] bigIntegerArr2 = new BigInteger[this.a];
        for (i2 = 0; i2 < this.a; i2++) {
            bigIntegerArr2[i2] = BigInteger.ZERO;
        }
        for (i2 = 0; i2 != this.b.length; i2++) {
            int i3 = (this.a - 1) - this.b[i2];
            for (int i4 = this.a - 1; i4 >= 0; i4--) {
                bigIntegerArr2[i4] = bigIntegerArr2[i4].add(bigIntegerArr[i3]);
                i3--;
                if (i3 < 0) {
                    i3 = this.a - 1;
                }
            }
        }
        while (i != this.c.length) {
            i2 = (this.a - 1) - this.c[i];
            for (i3 = this.a - 1; i3 >= 0; i3--) {
                bigIntegerArr2[i3] = bigIntegerArr2[i3].subtract(bigIntegerArr[i2]);
                i2--;
                if (i2 < 0) {
                    i2 = this.a - 1;
                }
            }
            i++;
        }
        return new BigIntPolynomial(bigIntegerArr2);
    }

    public int[] a() {
        return this.b;
    }

    public int[] b() {
        return this.c;
    }

    public IntegerPolynomial m() {
        int i = 0;
        int[] iArr = new int[this.a];
        for (int i2 = 0; i2 != this.b.length; i2++) {
            iArr[this.b[i2]] = 1;
        }
        while (i != this.c.length) {
            iArr[this.c[i]] = -1;
            i++;
        }
        return new IntegerPolynomial(iArr);
    }

    public int c() {
        return this.a;
    }

    public int hashCode() {
        return ((((this.a + 31) * 31) + Arrays.a(this.c)) * 31) + Arrays.a(this.b);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        SparseTernaryPolynomial sparseTernaryPolynomial = (SparseTernaryPolynomial) obj;
        if (this.a != sparseTernaryPolynomial.a) {
            return false;
        }
        if (!Arrays.a(this.c, sparseTernaryPolynomial.c)) {
            return false;
        }
        if (Arrays.a(this.b, sparseTernaryPolynomial.b)) {
            return true;
        }
        return false;
    }
}
