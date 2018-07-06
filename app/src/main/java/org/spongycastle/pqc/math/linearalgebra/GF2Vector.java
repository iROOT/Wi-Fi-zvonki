package org.spongycastle.pqc.math.linearalgebra;

import java.security.SecureRandom;

public class GF2Vector extends Vector {
    private int[] b;

    public GF2Vector(int i) {
        if (i < 0) {
            throw new ArithmeticException("Negative length.");
        }
        this.a = i;
        this.b = new int[((i + 31) >> 5)];
    }

    public GF2Vector(int i, SecureRandom secureRandom) {
        int i2;
        this.a = i;
        int i3 = (i + 31) >> 5;
        this.b = new int[i3];
        for (i2 = i3 - 1; i2 >= 0; i2--) {
            this.b[i2] = secureRandom.nextInt();
        }
        i2 = i & 31;
        if (i2 != 0) {
            int[] iArr = this.b;
            i3--;
            iArr[i3] = ((1 << i2) - 1) & iArr[i3];
        }
    }

    public GF2Vector(int i, int i2, SecureRandom secureRandom) {
        int i3 = 0;
        if (i2 > i) {
            throw new ArithmeticException("The hamming weight is greater than the length of vector.");
        }
        int i4;
        this.a = i;
        this.b = new int[((i + 31) >> 5)];
        int[] iArr = new int[i];
        for (i4 = 0; i4 < i; i4++) {
            iArr[i4] = i4;
        }
        while (i3 < i2) {
            i4 = RandUtils.a(secureRandom, i);
            a(iArr[i4]);
            i--;
            iArr[i4] = iArr[i];
            i3++;
        }
    }

    public GF2Vector(int i, int[] iArr) {
        if (i < 0) {
            throw new ArithmeticException("negative length");
        }
        this.a = i;
        int i2 = (i + 31) >> 5;
        if (iArr.length != i2) {
            throw new ArithmeticException("length mismatch");
        }
        this.b = IntUtils.a(iArr);
        int i3 = i & 31;
        if (i3 != 0) {
            int[] iArr2 = this.b;
            i2--;
            iArr2[i2] = ((1 << i3) - 1) & iArr2[i2];
        }
    }

    public GF2Vector(GF2Vector gF2Vector) {
        this.a = gF2Vector.a;
        this.b = IntUtils.a(gF2Vector.b);
    }

    protected GF2Vector(int[] iArr, int i) {
        this.b = iArr;
        this.a = i;
    }

    public static GF2Vector a(int i, byte[] bArr) {
        if (i < 0) {
            throw new ArithmeticException("negative length");
        }
        if (bArr.length <= ((i + 7) >> 3)) {
            return new GF2Vector(i, LittleEndianConversions.a(bArr));
        }
        throw new ArithmeticException("length mismatch");
    }

    public byte[] a() {
        return LittleEndianConversions.a(this.b, (this.a + 7) >> 3);
    }

    public int[] b() {
        return this.b;
    }

    public int c() {
        int i = 0;
        for (int i2 : this.b) {
            for (int i3 = 0; i3 < 32; i3++) {
                if ((i2 & 1) != 0) {
                    i++;
                }
                int i22 >>>= 1;
            }
        }
        return i;
    }

    public boolean d() {
        for (int length = this.b.length - 1; length >= 0; length--) {
            if (this.b[length] != 0) {
                return false;
            }
        }
        return true;
    }

    public void a(int i) {
        if (i >= this.a) {
            throw new IndexOutOfBoundsException();
        }
        int[] iArr = this.b;
        int i2 = i >> 5;
        iArr[i2] = iArr[i2] | (1 << (i & 31));
    }

    public Vector a(Vector vector) {
        if (vector instanceof GF2Vector) {
            if (this.a != ((GF2Vector) vector).a) {
                throw new ArithmeticException("length mismatch");
            }
            int[] a = IntUtils.a(((GF2Vector) vector).b);
            for (int length = a.length - 1; length >= 0; length--) {
                a[length] = a[length] ^ this.b[length];
            }
            return new GF2Vector(this.a, a);
        }
        throw new ArithmeticException("vector is not defined over GF(2)");
    }

    public Vector a(Permutation permutation) {
        int[] b = permutation.b();
        if (this.a != b.length) {
            throw new ArithmeticException("length mismatch");
        }
        Vector gF2Vector = new GF2Vector(this.a);
        for (int i = 0; i < b.length; i++) {
            if ((this.b[b[i] >> 5] & (1 << (b[i] & 31))) != 0) {
                int[] iArr = gF2Vector.b;
                int i2 = i >> 5;
                iArr[i2] = iArr[i2] | (1 << (i & 31));
            }
        }
        return gF2Vector;
    }

    public GF2Vector b(int i) {
        int i2 = 0;
        if (i > this.a) {
            throw new ArithmeticException("invalid length");
        } else if (i == this.a) {
            return new GF2Vector(this);
        } else {
            GF2Vector gF2Vector = new GF2Vector(i);
            int i3 = (this.a - i) >> 5;
            int i4 = (this.a - i) & 31;
            int i5 = (i + 31) >> 5;
            if (i4 != 0) {
                while (i2 < i5 - 1) {
                    int i6 = i3 + 1;
                    gF2Vector.b[i2] = (this.b[i3] >>> i4) | (this.b[i6] << (32 - i4));
                    i2++;
                    i3 = i6;
                }
                int i7 = i3 + 1;
                gF2Vector.b[i5 - 1] = this.b[i3] >>> i4;
                if (i7 >= this.b.length) {
                    return gF2Vector;
                }
                int[] iArr = gF2Vector.b;
                i3 = i5 - 1;
                iArr[i3] = iArr[i3] | (this.b[i7] << (32 - i4));
                return gF2Vector;
            }
            System.arraycopy(this.b, i3, gF2Vector.b, 0, i5);
            return gF2Vector;
        }
    }

    public GF2mVector a(GF2mField gF2mField) {
        int a = gF2mField.a();
        if (this.a % a != 0) {
            throw new ArithmeticException("conversion is impossible");
        }
        a = this.a / a;
        int[] iArr = new int[a];
        int i = 0;
        for (int i2 = a - 1; i2 >= 0; i2--) {
            for (a = gF2mField.a() - 1; a >= 0; a--) {
                if (((this.b[i >>> 5] >>> (i & 31)) & 1) == 1) {
                    iArr[i2] = iArr[i2] ^ (1 << a);
                }
                i++;
            }
        }
        return new GF2mVector(gF2mField, iArr);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof GF2Vector)) {
            return false;
        }
        GF2Vector gF2Vector = (GF2Vector) obj;
        if (this.a == gF2Vector.a && IntUtils.a(this.b, gF2Vector.b)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (this.a * 31) + this.b.hashCode();
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        int i = 0;
        while (i < this.a) {
            if (i != 0 && (i & 31) == 0) {
                stringBuffer.append(' ');
            }
            if ((this.b[i >> 5] & (1 << (i & 31))) == 0) {
                stringBuffer.append('0');
            } else {
                stringBuffer.append('1');
            }
            i++;
        }
        return stringBuffer.toString();
    }
}
