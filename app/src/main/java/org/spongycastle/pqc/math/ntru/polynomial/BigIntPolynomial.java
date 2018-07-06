package org.spongycastle.pqc.math.ntru.polynomial;

import java.math.BigDecimal;
import java.math.BigInteger;
import org.spongycastle.util.Arrays;

public class BigIntPolynomial {
    private static final double b = Math.log10(2.0d);
    BigInteger[] a;

    BigIntPolynomial(int i) {
        this.a = new BigInteger[i];
        for (int i2 = 0; i2 < i; i2++) {
            this.a[i2] = Constants.a;
        }
    }

    BigIntPolynomial(BigInteger[] bigIntegerArr) {
        this.a = bigIntegerArr;
    }

    public BigIntPolynomial(IntegerPolynomial integerPolynomial) {
        this.a = new BigInteger[integerPolynomial.a.length];
        for (int i = 0; i < this.a.length; i++) {
            this.a[i] = BigInteger.valueOf((long) integerPolynomial.a[i]);
        }
    }

    public BigIntPolynomial a(BigIntPolynomial bigIntPolynomial) {
        int length = this.a.length;
        if (bigIntPolynomial.a.length != length) {
            throw new IllegalArgumentException("Number of coefficients must be the same");
        }
        BigIntPolynomial d = d(bigIntPolynomial);
        if (d.a.length > length) {
            for (int i = length; i < d.a.length; i++) {
                d.a[i - length] = d.a[i - length].add(d.a[i]);
            }
            d.a = Arrays.a(d.a, length);
        }
        return d;
    }

    private BigIntPolynomial d(BigIntPolynomial bigIntPolynomial) {
        int i = 0;
        BigInteger[] bigIntegerArr = this.a;
        BigInteger[] bigIntegerArr2 = bigIntPolynomial.a;
        int length = bigIntPolynomial.a.length;
        int i2;
        if (length <= 1) {
            bigIntegerArr2 = Arrays.a(this.a);
            for (i2 = 0; i2 < this.a.length; i2++) {
                bigIntegerArr2[i2] = bigIntegerArr2[i2].multiply(bigIntPolynomial.a[0]);
            }
            return new BigIntPolynomial(bigIntegerArr2);
        }
        int i3 = length / 2;
        BigIntPolynomial bigIntPolynomial2 = new BigIntPolynomial(Arrays.a(bigIntegerArr, i3));
        BigIntPolynomial bigIntPolynomial3 = new BigIntPolynomial(Arrays.a(bigIntegerArr, i3, length));
        BigIntPolynomial bigIntPolynomial4 = new BigIntPolynomial(Arrays.a(bigIntegerArr2, i3));
        BigIntPolynomial bigIntPolynomial5 = new BigIntPolynomial(Arrays.a(bigIntegerArr2, i3, length));
        BigIntPolynomial bigIntPolynomial6 = (BigIntPolynomial) bigIntPolynomial2.clone();
        bigIntPolynomial6.b(bigIntPolynomial3);
        BigIntPolynomial bigIntPolynomial7 = (BigIntPolynomial) bigIntPolynomial4.clone();
        bigIntPolynomial7.b(bigIntPolynomial5);
        bigIntPolynomial2 = bigIntPolynomial2.d(bigIntPolynomial4);
        bigIntPolynomial3 = bigIntPolynomial3.d(bigIntPolynomial5);
        bigIntPolynomial4 = bigIntPolynomial6.d(bigIntPolynomial7);
        bigIntPolynomial4.c(bigIntPolynomial2);
        bigIntPolynomial4.c(bigIntPolynomial3);
        bigIntPolynomial7 = new BigIntPolynomial((length * 2) - 1);
        for (i2 = 0; i2 < bigIntPolynomial2.a.length; i2++) {
            bigIntPolynomial7.a[i2] = bigIntPolynomial2.a[i2];
        }
        for (i2 = 0; i2 < bigIntPolynomial4.a.length; i2++) {
            bigIntPolynomial7.a[i3 + i2] = bigIntPolynomial7.a[i3 + i2].add(bigIntPolynomial4.a[i2]);
        }
        while (i < bigIntPolynomial3.a.length) {
            bigIntPolynomial7.a[(i3 * 2) + i] = bigIntPolynomial7.a[(i3 * 2) + i].add(bigIntPolynomial3.a[i]);
            i++;
        }
        return bigIntPolynomial7;
    }

    public void b(BigIntPolynomial bigIntPolynomial) {
        int length;
        if (bigIntPolynomial.a.length > this.a.length) {
            this.a = Arrays.a(this.a, bigIntPolynomial.a.length);
            for (length = this.a.length; length < this.a.length; length++) {
                this.a[length] = Constants.a;
            }
        }
        for (length = 0; length < bigIntPolynomial.a.length; length++) {
            this.a[length] = this.a[length].add(bigIntPolynomial.a[length]);
        }
    }

    public void c(BigIntPolynomial bigIntPolynomial) {
        int length;
        if (bigIntPolynomial.a.length > this.a.length) {
            this.a = Arrays.a(this.a, bigIntPolynomial.a.length);
            for (length = this.a.length; length < this.a.length; length++) {
                this.a[length] = Constants.a;
            }
        }
        for (length = 0; length < bigIntPolynomial.a.length; length++) {
            this.a[length] = this.a[length].subtract(bigIntPolynomial.a[length]);
        }
    }

    public void a(BigInteger bigInteger) {
        for (int i = 0; i < this.a.length; i++) {
            this.a[i] = this.a[i].multiply(bigInteger);
        }
    }

    public void b(BigInteger bigInteger) {
        BigInteger divide = bigInteger.add(Constants.b).divide(BigInteger.valueOf(2));
        for (int i = 0; i < this.a.length; i++) {
            this.a[i] = this.a[i].compareTo(Constants.a) > 0 ? this.a[i].add(divide) : this.a[i].add(divide.negate());
            this.a[i] = this.a[i].divide(bigInteger);
        }
    }

    public BigDecimalPolynomial a(BigDecimal bigDecimal, int i) {
        BigDecimal divide = Constants.c.divide(bigDecimal, ((((int) (((double) b().bitLength()) * b)) + 1) + i) + 1, 6);
        BigDecimalPolynomial bigDecimalPolynomial = new BigDecimalPolynomial(this.a.length);
        for (int i2 = 0; i2 < this.a.length; i2++) {
            bigDecimalPolynomial.a[i2] = new BigDecimal(this.a[i2]).multiply(divide).setScale(i, 6);
        }
        return bigDecimalPolynomial;
    }

    public int a() {
        return ((int) (((double) b().bitLength()) * b)) + 1;
    }

    private BigInteger b() {
        BigInteger abs = this.a[0].abs();
        for (int i = 1; i < this.a.length; i++) {
            BigInteger abs2 = this.a[i].abs();
            if (abs2.compareTo(abs) > 0) {
                abs = abs2;
            }
        }
        return abs;
    }

    public void c(BigInteger bigInteger) {
        for (int i = 0; i < this.a.length; i++) {
            this.a[i] = this.a[i].mod(bigInteger);
        }
    }

    public Object clone() {
        return new BigIntPolynomial((BigInteger[]) this.a.clone());
    }

    public int hashCode() {
        return Arrays.a(this.a) + 31;
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
        if (Arrays.a(this.a, ((BigIntPolynomial) obj).a)) {
            return true;
        }
        return false;
    }
}
