package org.spongycastle.pqc.math.ntru.polynomial;

import java.security.SecureRandom;

public class ProductFormPolynomial implements Polynomial {
    private SparseTernaryPolynomial a;
    private SparseTernaryPolynomial b;
    private SparseTernaryPolynomial c;

    public ProductFormPolynomial(SparseTernaryPolynomial sparseTernaryPolynomial, SparseTernaryPolynomial sparseTernaryPolynomial2, SparseTernaryPolynomial sparseTernaryPolynomial3) {
        this.a = sparseTernaryPolynomial;
        this.b = sparseTernaryPolynomial2;
        this.c = sparseTernaryPolynomial3;
    }

    public static ProductFormPolynomial a(int i, int i2, int i3, int i4, int i5, SecureRandom secureRandom) {
        return new ProductFormPolynomial(SparseTernaryPolynomial.a(i, i2, i2, secureRandom), SparseTernaryPolynomial.a(i, i3, i3, secureRandom), SparseTernaryPolynomial.a(i, i4, i5, secureRandom));
    }

    public IntegerPolynomial a(IntegerPolynomial integerPolynomial) {
        IntegerPolynomial a = this.b.a(this.a.a(integerPolynomial));
        a.b(this.c.a(integerPolynomial));
        return a;
    }

    public BigIntPolynomial a(BigIntPolynomial bigIntPolynomial) {
        BigIntPolynomial a = this.b.a(this.a.a(bigIntPolynomial));
        a.b(this.c.a(bigIntPolynomial));
        return a;
    }

    public IntegerPolynomial m() {
        IntegerPolynomial a = this.a.a(this.b.m());
        a.b(this.c.m());
        return a;
    }

    public IntegerPolynomial a(IntegerPolynomial integerPolynomial, int i) {
        IntegerPolynomial a = a(integerPolynomial);
        a.i(i);
        return a;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((this.b == null ? 0 : this.b.hashCode()) + (((this.a == null ? 0 : this.a.hashCode()) + 31) * 31)) * 31;
        if (this.c != null) {
            i = this.c.hashCode();
        }
        return hashCode + i;
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
        ProductFormPolynomial productFormPolynomial = (ProductFormPolynomial) obj;
        if (this.a == null) {
            if (productFormPolynomial.a != null) {
                return false;
            }
        } else if (!this.a.equals(productFormPolynomial.a)) {
            return false;
        }
        if (this.b == null) {
            if (productFormPolynomial.b != null) {
                return false;
            }
        } else if (!this.b.equals(productFormPolynomial.b)) {
            return false;
        }
        if (this.c == null) {
            if (productFormPolynomial.c != null) {
                return false;
            }
            return true;
        } else if (this.c.equals(productFormPolynomial.c)) {
            return true;
        } else {
            return false;
        }
    }
}
