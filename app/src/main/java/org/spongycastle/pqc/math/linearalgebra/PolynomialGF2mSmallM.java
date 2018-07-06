package org.spongycastle.pqc.math.linearalgebra;

import java.security.SecureRandom;

public class PolynomialGF2mSmallM {
    private GF2mField a;
    private int b;
    private int[] c;

    public PolynomialGF2mSmallM(GF2mField gF2mField, int i, char c, SecureRandom secureRandom) {
        this.a = gF2mField;
        switch (c) {
            case 'I':
                this.c = a(i, secureRandom);
                d();
                return;
            default:
                throw new IllegalArgumentException(" Error: type " + c + " is not defined for GF2smallmPolynomial");
        }
    }

    private int[] a(int i, SecureRandom secureRandom) {
        int i2 = 1;
        int[] iArr = new int[(i + 1)];
        iArr[i] = 1;
        iArr[0] = this.a.b(secureRandom);
        while (i2 < i) {
            iArr[i2] = this.a.a(secureRandom);
            i2++;
        }
        while (!b(iArr)) {
            i2 = RandUtils.a(secureRandom, i);
            if (i2 == 0) {
                iArr[0] = this.a.b(secureRandom);
            } else {
                iArr[i2] = this.a.a(secureRandom);
            }
        }
        return iArr;
    }

    public PolynomialGF2mSmallM(GF2mField gF2mField, int i) {
        this.a = gF2mField;
        this.b = i;
        this.c = new int[(i + 1)];
        this.c[i] = 1;
    }

    public PolynomialGF2mSmallM(GF2mField gF2mField, int[] iArr) {
        this.a = gF2mField;
        this.c = d(iArr);
        d();
    }

    public PolynomialGF2mSmallM(PolynomialGF2mSmallM polynomialGF2mSmallM) {
        this.a = polynomialGF2mSmallM.a;
        this.b = polynomialGF2mSmallM.b;
        this.c = IntUtils.a(polynomialGF2mSmallM.c);
    }

    public PolynomialGF2mSmallM(GF2mVector gF2mVector) {
        this(gF2mVector.a(), gF2mVector.b());
    }

    public int a() {
        int length = this.c.length - 1;
        if (this.c[length] == 0) {
            return -1;
        }
        return length;
    }

    public int b() {
        if (this.b == -1) {
            return 0;
        }
        return this.c[this.b];
    }

    private static int a(int[] iArr) {
        int c = c(iArr);
        if (c == -1) {
            return 0;
        }
        return iArr[c];
    }

    public int a(int i) {
        if (i < 0 || i > this.b) {
            return 0;
        }
        return this.c[i];
    }

    public byte[] c() {
        int i = 8;
        int i2 = 1;
        while (this.a.a() > i) {
            i2++;
            i += 8;
        }
        byte[] bArr = new byte[(i2 * this.c.length)];
        int i3 = 0;
        for (int i4 : this.c) {
            int i5 = 0;
            while (i5 < i) {
                int i6 = i3 + 1;
                bArr[i3] = (byte) (i4 >>> i5);
                i5 += 8;
                i3 = i6;
            }
        }
        return bArr;
    }

    public int b(int i) {
        int i2 = this.c[this.b];
        for (int i3 = this.b - 1; i3 >= 0; i3--) {
            i2 = this.a.b(i2, i) ^ this.c[i3];
        }
        return i2;
    }

    public PolynomialGF2mSmallM a(PolynomialGF2mSmallM polynomialGF2mSmallM) {
        return new PolynomialGF2mSmallM(this.a, a(this.c, polynomialGF2mSmallM.c));
    }

    public void b(PolynomialGF2mSmallM polynomialGF2mSmallM) {
        this.c = a(this.c, polynomialGF2mSmallM.c);
        d();
    }

    private int[] a(int[] iArr, int[] iArr2) {
        int[] iArr3;
        if (iArr.length < iArr2.length) {
            iArr3 = new int[iArr2.length];
            System.arraycopy(iArr2, 0, iArr3, 0, iArr2.length);
        } else {
            iArr3 = new int[iArr.length];
            System.arraycopy(iArr, 0, iArr3, 0, iArr.length);
            iArr = iArr2;
        }
        for (int length = iArr.length - 1; length >= 0; length--) {
            iArr3[length] = this.a.a(iArr3[length], iArr[length]);
        }
        return iArr3;
    }

    public PolynomialGF2mSmallM c(int i) {
        int[] iArr = new int[(i + 1)];
        iArr[i] = 1;
        return new PolynomialGF2mSmallM(this.a, a(this.c, iArr));
    }

    public PolynomialGF2mSmallM d(int i) {
        if (this.a.c(i)) {
            return new PolynomialGF2mSmallM(this.a, a(this.c, i));
        }
        throw new ArithmeticException("Not an element of the finite field this polynomial is defined over.");
    }

    public void e(int i) {
        if (this.a.c(i)) {
            this.c = a(this.c, i);
            d();
            return;
        }
        throw new ArithmeticException("Not an element of the finite field this polynomial is defined over.");
    }

    private int[] a(int[] iArr, int i) {
        int c = c(iArr);
        if (c == -1 || i == 0) {
            return new int[1];
        }
        if (i == 1) {
            return IntUtils.a(iArr);
        }
        int[] iArr2 = new int[(c + 1)];
        while (c >= 0) {
            iArr2[c] = this.a.b(iArr[c], i);
            c--;
        }
        return iArr2;
    }

    public PolynomialGF2mSmallM f(int i) {
        return new PolynomialGF2mSmallM(this.a, b(this.c, i));
    }

    private static int[] b(int[] iArr, int i) {
        int c = c(iArr);
        if (c == -1) {
            return new int[1];
        }
        int[] iArr2 = new int[((c + i) + 1)];
        System.arraycopy(iArr, 0, iArr2, i, c + 1);
        return iArr2;
    }

    private int[][] b(int[] iArr, int[] iArr2) {
        int c = c(iArr2);
        int c2 = c(iArr) + 1;
        if (c == -1) {
            throw new ArithmeticException("Division by zero.");
        }
        int[][] iArr3 = new int[][]{new int[1], new int[c2]};
        c2 = this.a.a(a(iArr2));
        iArr3[0][0] = 0;
        System.arraycopy(iArr, 0, iArr3[1], 0, iArr3[1].length);
        while (c <= c(iArr3[1])) {
            int[] iArr4 = new int[]{this.a.b(a(iArr3[1]), c2)};
            int c3 = c(iArr3[1]) - c;
            int[] b = b(a(iArr2, iArr4[0]), c3);
            iArr3[0] = a(b(iArr4, c3), iArr3[0]);
            iArr3[1] = a(b, iArr3[1]);
        }
        return iArr3;
    }

    private int[] c(int[] iArr, int[] iArr2) {
        if (c(iArr) == -1) {
            return iArr2;
        }
        while (c(iArr2) != -1) {
            Object e = e(iArr, iArr2);
            iArr = new int[iArr2.length];
            System.arraycopy(iArr2, 0, iArr, 0, iArr.length);
            iArr2 = new int[e.length];
            System.arraycopy(e, 0, iArr2, 0, iArr2.length);
        }
        return a(iArr, this.a.a(a(iArr)));
    }

    public PolynomialGF2mSmallM c(PolynomialGF2mSmallM polynomialGF2mSmallM) {
        return new PolynomialGF2mSmallM(this.a, d(this.c, polynomialGF2mSmallM.c));
    }

    private int[] d(int[] iArr, int[] iArr2) {
        if (c(iArr) >= c(iArr2)) {
            int[] iArr3 = iArr2;
            iArr2 = iArr;
            iArr = iArr3;
        }
        int[] d = d(iArr2);
        Object d2 = d(iArr);
        if (d2.length == 1) {
            return a(d, d2[0]);
        }
        int length = d.length;
        int length2 = d2.length;
        int[] iArr4 = new int[((length + length2) - 1)];
        if (length2 != length) {
            Object obj = new int[length2];
            Object obj2 = new int[(length - length2)];
            System.arraycopy(d, 0, obj, 0, obj.length);
            System.arraycopy(d, length2, obj2, 0, obj2.length);
            return a(d(obj, d2), b(d(obj2, d2), length2));
        }
        length2 = (length + 1) >>> 1;
        length -= length2;
        iArr4 = new int[length2];
        int[] iArr5 = new int[length2];
        int[] iArr6 = new int[length];
        int[] iArr7 = new int[length];
        System.arraycopy(d, 0, iArr4, 0, iArr4.length);
        System.arraycopy(d, length2, iArr6, 0, iArr6.length);
        System.arraycopy(d2, 0, iArr5, 0, iArr5.length);
        System.arraycopy(d2, length2, iArr7, 0, iArr7.length);
        d = a(iArr4, iArr6);
        int[] a = a(iArr5, iArr7);
        iArr4 = d(iArr4, iArr5);
        d = d(d, a);
        a = d(iArr6, iArr7);
        return a(b(a(a(a(d, iArr4), a), b(a, length2)), length2), iArr4);
    }

    private boolean b(int[] iArr) {
        if (iArr[0] == 0) {
            return false;
        }
        int c = c(iArr) >> 1;
        int[] iArr2 = new int[]{0, 1};
        int[] iArr3 = new int[]{0, 1};
        int a = this.a.a();
        for (int i = 0; i < c; i++) {
            for (int i2 = a - 1; i2 >= 0; i2--) {
                iArr2 = a(iArr2, iArr2, iArr);
            }
            iArr2 = d(iArr2);
            if (c(c(a(iArr2, iArr3), iArr)) != 0) {
                return false;
            }
        }
        return true;
    }

    public PolynomialGF2mSmallM d(PolynomialGF2mSmallM polynomialGF2mSmallM) {
        return new PolynomialGF2mSmallM(this.a, e(this.c, polynomialGF2mSmallM.c));
    }

    private int[] e(int[] iArr, int[] iArr2) {
        int c = c(iArr2);
        if (c == -1) {
            throw new ArithmeticException("Division by zero");
        }
        int[] iArr3 = new int[iArr.length];
        int a = this.a.a(a(iArr2));
        System.arraycopy(iArr, 0, iArr3, 0, iArr3.length);
        while (c <= c(iArr3)) {
            iArr3 = a(a(b(iArr2, c(iArr3) - c), this.a.b(a(iArr3), a)), iArr3);
        }
        return iArr3;
    }

    private int[] a(int[] iArr, int[] iArr2, int[] iArr3) {
        return e(d(iArr, iArr2), iArr3);
    }

    public PolynomialGF2mSmallM a(PolynomialGF2mSmallM[] polynomialGF2mSmallMArr) {
        int i = 0;
        int length = polynomialGF2mSmallMArr.length;
        int[] iArr = new int[length];
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = 0;
            while (i3 < length) {
                if (i2 < polynomialGF2mSmallMArr[i3].c.length && i3 < this.c.length) {
                    iArr[i2] = this.a.a(iArr[i2], this.a.b(polynomialGF2mSmallMArr[i3].c[i2], this.c[i3]));
                }
                i3++;
            }
        }
        while (i < length) {
            iArr[i] = this.a.b(iArr[i]);
            i++;
        }
        return new PolynomialGF2mSmallM(this.a, iArr);
    }

    private int[] b(int[] iArr, int[] iArr2, int[] iArr3) {
        int[] d = d(iArr3);
        int[] e = e(iArr2, iArr3);
        int[] iArr4 = new int[]{0};
        int[] e2 = e(iArr, iArr3);
        while (c(e) != -1) {
            int[][] b = b(d, e);
            d = d(e);
            e = d(b[1]);
            int[] a = a(iArr4, a(b[0], e2, iArr3));
            iArr4 = d(e2);
            e2 = d(a);
        }
        return a(iArr4, this.a.a(a(d)));
    }

    public PolynomialGF2mSmallM e(PolynomialGF2mSmallM polynomialGF2mSmallM) {
        return new PolynomialGF2mSmallM(this.a, b(new int[]{1}, this.c, polynomialGF2mSmallM.c));
    }

    public PolynomialGF2mSmallM[] f(PolynomialGF2mSmallM polynomialGF2mSmallM) {
        int i = polynomialGF2mSmallM.b >> 1;
        int[] d = d(polynomialGF2mSmallM.c);
        int[] e = e(this.c, polynomialGF2mSmallM.c);
        int[] iArr = new int[]{0};
        int[] iArr2 = new int[]{1};
        while (c(e) > i) {
            int[][] b = b(d, e);
            d = b[1];
            int[] a = a(iArr, a(b[0], iArr2, polynomialGF2mSmallM.c));
            iArr = iArr2;
            iArr2 = a;
            int[] iArr3 = d;
            d = e;
            e = iArr3;
        }
        return new PolynomialGF2mSmallM[]{new PolynomialGF2mSmallM(this.a, e), new PolynomialGF2mSmallM(this.a, iArr2)};
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof PolynomialGF2mSmallM)) {
            return false;
        }
        PolynomialGF2mSmallM polynomialGF2mSmallM = (PolynomialGF2mSmallM) obj;
        if (this.a.equals(polynomialGF2mSmallM.a) && this.b == polynomialGF2mSmallM.b && f(this.c, polynomialGF2mSmallM.c)) {
            return true;
        }
        return false;
    }

    private static boolean f(int[] iArr, int[] iArr2) {
        int c = c(iArr);
        if (c != c(iArr2)) {
            return false;
        }
        for (int i = 0; i <= c; i++) {
            if (iArr[i] != iArr2[i]) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        int hashCode = this.a.hashCode();
        for (int i : this.c) {
            hashCode = (hashCode * 31) + i;
        }
        return hashCode;
    }

    public String toString() {
        String str = " Polynomial over " + this.a.toString() + ": \n";
        for (int i = 0; i < this.c.length; i++) {
            str = str + this.a.d(this.c[i]) + "Y^" + i + "+";
        }
        return str + ";";
    }

    private void d() {
        this.b = this.c.length - 1;
        while (this.b >= 0 && this.c[this.b] == 0) {
            this.b--;
        }
    }

    private static int c(int[] iArr) {
        int length = iArr.length - 1;
        while (length >= 0 && iArr[length] == 0) {
            length--;
        }
        return length;
    }

    private static int[] d(int[] iArr) {
        int c = c(iArr);
        if (c == -1) {
            return new int[1];
        }
        if (iArr.length == c + 1) {
            return IntUtils.a(iArr);
        }
        int[] iArr2 = new int[(c + 1)];
        System.arraycopy(iArr, 0, iArr2, 0, c + 1);
        return iArr2;
    }
}
