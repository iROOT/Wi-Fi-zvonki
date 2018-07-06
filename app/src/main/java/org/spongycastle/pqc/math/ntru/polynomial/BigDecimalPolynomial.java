package org.spongycastle.pqc.math.ntru.polynomial;

import java.math.BigDecimal;

public class BigDecimalPolynomial {
    private static final BigDecimal b = new BigDecimal("0");
    private static final BigDecimal c = new BigDecimal("0.5");
    BigDecimal[] a;

    BigDecimalPolynomial(int i) {
        this.a = new BigDecimal[i];
        for (int i2 = 0; i2 < i; i2++) {
            this.a[i2] = b;
        }
    }

    BigDecimalPolynomial(BigDecimal[] bigDecimalArr) {
        this.a = bigDecimalArr;
    }

    public BigDecimalPolynomial(BigIntPolynomial bigIntPolynomial) {
        int length = bigIntPolynomial.a.length;
        this.a = new BigDecimal[length];
        for (int i = 0; i < length; i++) {
            this.a[i] = new BigDecimal(bigIntPolynomial.a[i]);
        }
    }

    public void a() {
        for (int i = 0; i < this.a.length; i++) {
            this.a[i] = this.a[i].multiply(c);
        }
    }

    public BigDecimalPolynomial a(BigIntPolynomial bigIntPolynomial) {
        return a(new BigDecimalPolynomial(bigIntPolynomial));
    }

    public BigDecimalPolynomial a(BigDecimalPolynomial bigDecimalPolynomial) {
        int length = this.a.length;
        if (bigDecimalPolynomial.a.length != length) {
            throw new IllegalArgumentException("Number of coefficients must be the same");
        }
        BigDecimalPolynomial d = d(bigDecimalPolynomial);
        if (d.a.length > length) {
            for (int i = length; i < d.a.length; i++) {
                d.a[i - length] = d.a[i - length].add(d.a[i]);
            }
            d.a = a(d.a, length);
        }
        return d;
    }

    private BigDecimalPolynomial d(BigDecimalPolynomial bigDecimalPolynomial) {
        int i = 0;
        BigDecimal[] bigDecimalArr = this.a;
        BigDecimal[] bigDecimalArr2 = bigDecimalPolynomial.a;
        int length = bigDecimalPolynomial.a.length;
        if (length <= 1) {
            bigDecimalArr = (BigDecimal[]) this.a.clone();
            for (int i2 = 0; i2 < this.a.length; i2++) {
                bigDecimalArr[i2] = bigDecimalArr[i2].multiply(bigDecimalPolynomial.a[0]);
            }
            return new BigDecimalPolynomial(bigDecimalArr);
        }
        int i3;
        int i4 = length / 2;
        BigDecimalPolynomial bigDecimalPolynomial2 = new BigDecimalPolynomial(a(bigDecimalArr, i4));
        BigDecimalPolynomial bigDecimalPolynomial3 = new BigDecimalPolynomial(a(bigDecimalArr, i4, length));
        BigDecimalPolynomial bigDecimalPolynomial4 = new BigDecimalPolynomial(a(bigDecimalArr2, i4));
        BigDecimalPolynomial bigDecimalPolynomial5 = new BigDecimalPolynomial(a(bigDecimalArr2, i4, length));
        BigDecimalPolynomial bigDecimalPolynomial6 = (BigDecimalPolynomial) bigDecimalPolynomial2.clone();
        bigDecimalPolynomial6.b(bigDecimalPolynomial3);
        BigDecimalPolynomial bigDecimalPolynomial7 = (BigDecimalPolynomial) bigDecimalPolynomial4.clone();
        bigDecimalPolynomial7.b(bigDecimalPolynomial5);
        bigDecimalPolynomial2 = bigDecimalPolynomial2.d(bigDecimalPolynomial4);
        bigDecimalPolynomial3 = bigDecimalPolynomial3.d(bigDecimalPolynomial5);
        bigDecimalPolynomial4 = bigDecimalPolynomial6.d(bigDecimalPolynomial7);
        bigDecimalPolynomial4.c(bigDecimalPolynomial2);
        bigDecimalPolynomial4.c(bigDecimalPolynomial3);
        bigDecimalPolynomial7 = new BigDecimalPolynomial((length * 2) - 1);
        for (i3 = 0; i3 < bigDecimalPolynomial2.a.length; i3++) {
            bigDecimalPolynomial7.a[i3] = bigDecimalPolynomial2.a[i3];
        }
        for (i3 = 0; i3 < bigDecimalPolynomial4.a.length; i3++) {
            bigDecimalPolynomial7.a[i4 + i3] = bigDecimalPolynomial7.a[i4 + i3].add(bigDecimalPolynomial4.a[i3]);
        }
        while (i < bigDecimalPolynomial3.a.length) {
            bigDecimalPolynomial7.a[(i4 * 2) + i] = bigDecimalPolynomial7.a[(i4 * 2) + i].add(bigDecimalPolynomial3.a[i]);
            i++;
        }
        return bigDecimalPolynomial7;
    }

    public void b(BigDecimalPolynomial bigDecimalPolynomial) {
        int length;
        if (bigDecimalPolynomial.a.length > this.a.length) {
            this.a = a(this.a, bigDecimalPolynomial.a.length);
            for (length = this.a.length; length < this.a.length; length++) {
                this.a[length] = b;
            }
        }
        for (length = 0; length < bigDecimalPolynomial.a.length; length++) {
            this.a[length] = this.a[length].add(bigDecimalPolynomial.a[length]);
        }
    }

    void c(BigDecimalPolynomial bigDecimalPolynomial) {
        int length;
        if (bigDecimalPolynomial.a.length > this.a.length) {
            this.a = a(this.a, bigDecimalPolynomial.a.length);
            for (length = this.a.length; length < this.a.length; length++) {
                this.a[length] = b;
            }
        }
        for (length = 0; length < bigDecimalPolynomial.a.length; length++) {
            this.a[length] = this.a[length].subtract(bigDecimalPolynomial.a[length]);
        }
    }

    public BigIntPolynomial b() {
        int length = this.a.length;
        BigIntPolynomial bigIntPolynomial = new BigIntPolynomial(length);
        for (int i = 0; i < length; i++) {
            bigIntPolynomial.a[i] = this.a[i].setScale(0, 6).toBigInteger();
        }
        return bigIntPolynomial;
    }

    public Object clone() {
        return new BigDecimalPolynomial((BigDecimal[]) this.a.clone());
    }

    private BigDecimal[] a(BigDecimal[] bigDecimalArr, int i) {
        Object obj = new BigDecimal[i];
        if (bigDecimalArr.length < i) {
            i = bigDecimalArr.length;
        }
        System.arraycopy(bigDecimalArr, 0, obj, 0, i);
        return obj;
    }

    private BigDecimal[] a(BigDecimal[] bigDecimalArr, int i, int i2) {
        int i3 = i2 - i;
        Object obj = new BigDecimal[(i2 - i)];
        if (bigDecimalArr.length - i < i3) {
            i3 = bigDecimalArr.length - i;
        }
        System.arraycopy(bigDecimalArr, i, obj, 0, i3);
        return obj;
    }
}
