package org.spongycastle.pqc.math.linearalgebra;

import java.lang.reflect.Array;
import java.security.SecureRandom;

public class GF2Matrix extends Matrix {
    private int[][] a;
    private int b;

    public GF2Matrix(int i, int[][] iArr) {
        int i2 = 0;
        if (iArr[0].length != ((i + 31) >> 5)) {
            throw new ArithmeticException("Int array does not match given number of columns.");
        }
        this.d = i;
        this.c = iArr.length;
        this.b = iArr[0].length;
        int i3 = i & 31;
        if (i3 == 0) {
            i3 = -1;
        } else {
            i3 = (1 << i3) - 1;
        }
        while (i2 < this.c) {
            int[] iArr2 = iArr[i2];
            int i4 = this.b - 1;
            iArr2[i4] = iArr2[i4] & i3;
            i2++;
        }
        this.a = iArr;
    }

    public GF2Matrix(int i, char c) {
        this(i, c, new SecureRandom());
    }

    public GF2Matrix(int i, char c, SecureRandom secureRandom) {
        if (i <= 0) {
            throw new ArithmeticException("Size of matrix is non-positive.");
        }
        switch (c) {
            case 'I':
                a(i);
                return;
            case 'L':
                b(i, secureRandom);
                return;
            case 'R':
                d(i, secureRandom);
                return;
            case 'U':
                c(i, secureRandom);
                return;
            case 'Z':
                a(i, i);
                return;
            default:
                throw new ArithmeticException("Unknown matrix type.");
        }
    }

    public GF2Matrix(GF2Matrix gF2Matrix) {
        this.d = gF2Matrix.h();
        this.c = gF2Matrix.g();
        this.b = gF2Matrix.b;
        this.a = new int[gF2Matrix.a.length][];
        for (int i = 0; i < this.a.length; i++) {
            this.a[i] = IntUtils.a(gF2Matrix.a[i]);
        }
    }

    private GF2Matrix(int i, int i2) {
        if (i2 <= 0 || i <= 0) {
            throw new ArithmeticException("size of matrix is non-positive");
        }
        a(i, i2);
    }

    private void a(int i, int i2) {
        this.c = i;
        this.d = i2;
        this.b = (i2 + 31) >>> 5;
        this.a = (int[][]) Array.newInstance(Integer.TYPE, new int[]{this.c, this.b});
        for (int i3 = 0; i3 < this.c; i3++) {
            for (int i4 = 0; i4 < this.b; i4++) {
                this.a[i3][i4] = 0;
            }
        }
    }

    private void a(int i) {
        int i2 = 0;
        this.c = i;
        this.d = i;
        this.b = (i + 31) >>> 5;
        this.a = (int[][]) Array.newInstance(Integer.TYPE, new int[]{this.c, this.b});
        for (int i3 = 0; i3 < this.c; i3++) {
            for (int i4 = 0; i4 < this.b; i4++) {
                this.a[i3][i4] = 0;
            }
        }
        while (i2 < this.c) {
            this.a[i2][i2 >>> 5] = 1 << (i2 & 31);
            i2++;
        }
    }

    private void b(int i, SecureRandom secureRandom) {
        this.c = i;
        this.d = i;
        this.b = (i + 31) >>> 5;
        this.a = (int[][]) Array.newInstance(Integer.TYPE, new int[]{this.c, this.b});
        for (int i2 = 0; i2 < this.c; i2++) {
            int i3 = i2 >>> 5;
            int i4 = i2 & 31;
            int i5 = 31 - i4;
            int i6 = 1 << i4;
            for (i4 = 0; i4 < i3; i4++) {
                this.a[i2][i4] = secureRandom.nextInt();
            }
            this.a[i2][i3] = (secureRandom.nextInt() >>> i5) | i6;
            for (i4 = i3 + 1; i4 < this.b; i4++) {
                this.a[i2][i4] = 0;
            }
        }
    }

    private void c(int i, SecureRandom secureRandom) {
        this.c = i;
        this.d = i;
        this.b = (i + 31) >>> 5;
        this.a = (int[][]) Array.newInstance(Integer.TYPE, new int[]{this.c, this.b});
        int i2 = i & 31;
        if (i2 == 0) {
            i2 = -1;
        } else {
            i2 = (1 << i2) - 1;
        }
        for (int i3 = 0; i3 < this.c; i3++) {
            int i4;
            int i5 = i3 >>> 5;
            int i6 = i3 & 31;
            int i7 = 1 << i6;
            for (i4 = 0; i4 < i5; i4++) {
                this.a[i3][i4] = 0;
            }
            this.a[i3][i5] = (secureRandom.nextInt() << i6) | i7;
            for (i4 = i5 + 1; i4 < this.b; i4++) {
                this.a[i3][i4] = secureRandom.nextInt();
            }
            int[] iArr = this.a[i3];
            i5 = this.b - 1;
            iArr[i5] = iArr[i5] & i2;
        }
    }

    private void d(int i, SecureRandom secureRandom) {
        this.c = i;
        this.d = i;
        this.b = (i + 31) >>> 5;
        this.a = (int[][]) Array.newInstance(Integer.TYPE, new int[]{this.c, this.b});
        GF2Matrix gF2Matrix = (GF2Matrix) new GF2Matrix(i, 'L', secureRandom).a(new GF2Matrix(i, 'U', secureRandom));
        int[] b = new Permutation(i, secureRandom).b();
        for (int i2 = 0; i2 < i; i2++) {
            System.arraycopy(gF2Matrix.a[i2], 0, this.a[b[i2]], 0, this.b);
        }
    }

    public static GF2Matrix[] a(int i, SecureRandom secureRandom) {
        int i2;
        GF2Matrix[] gF2MatrixArr = new GF2Matrix[2];
        int i3 = (i + 31) >> 5;
        GF2Matrix gF2Matrix = new GF2Matrix(i, 'L', secureRandom);
        Matrix gF2Matrix2 = new GF2Matrix(i, 'U', secureRandom);
        GF2Matrix gF2Matrix3 = (GF2Matrix) gF2Matrix.a(gF2Matrix2);
        Permutation permutation = new Permutation(i, secureRandom);
        int[] b = permutation.b();
        int[][] iArr = (int[][]) Array.newInstance(Integer.TYPE, new int[]{i, i3});
        for (i2 = 0; i2 < i; i2++) {
            System.arraycopy(gF2Matrix3.a[b[i2]], 0, iArr[i2], 0, i3);
        }
        gF2MatrixArr[0] = new GF2Matrix(i, iArr);
        GF2Matrix gF2Matrix4 = new GF2Matrix(i, 'I');
        for (i2 = 0; i2 < i; i2++) {
            int i4;
            int i5 = i2 >>> 5;
            int i6 = 1 << (i2 & 31);
            for (i4 = i2 + 1; i4 < i; i4++) {
                int i7;
                if ((gF2Matrix.a[i4][i5] & i6) != 0) {
                    for (i7 = 0; i7 <= i5; i7++) {
                        int[] iArr2 = gF2Matrix4.a[i4];
                        iArr2[i7] = iArr2[i7] ^ gF2Matrix4.a[i2][i7];
                    }
                }
            }
        }
        GF2Matrix gF2Matrix5 = new GF2Matrix(i, 'I');
        for (int i8 = i - 1; i8 >= 0; i8--) {
            i4 = i8 >>> 5;
            i6 = 1 << (i8 & 31);
            for (i2 = i8 - 1; i2 >= 0; i2--) {
                if ((gF2Matrix2.a[i2][i4] & i6) != 0) {
                    for (i7 = i4; i7 < i3; i7++) {
                        iArr2 = gF2Matrix5.a[i2];
                        iArr2[i7] = iArr2[i7] ^ gF2Matrix5.a[i8][i7];
                    }
                }
            }
        }
        gF2MatrixArr[1] = (GF2Matrix) gF2Matrix5.a(gF2Matrix4.a(permutation));
        return gF2MatrixArr;
    }

    public byte[] a() {
        byte[] bArr = new byte[((((this.d + 7) >>> 3) * this.c) + 8)];
        LittleEndianConversions.a(this.c, bArr, 0);
        LittleEndianConversions.a(this.d, bArr, 4);
        int i = this.d >>> 5;
        int i2 = this.d & 31;
        int i3 = 8;
        for (int i4 = 0; i4 < this.c; i4++) {
            int i5 = 0;
            while (i5 < i) {
                LittleEndianConversions.a(this.a[i4][i5], bArr, i3);
                i5++;
                i3 += 4;
            }
            i5 = 0;
            while (i5 < i2) {
                int i6 = i3 + 1;
                bArr[i3] = (byte) ((this.a[i4][i] >>> i5) & 255);
                i5 += 8;
                i3 = i6;
            }
        }
        return bArr;
    }

    public GF2Matrix b() {
        if (this.d <= this.c) {
            throw new ArithmeticException("empty submatrix");
        }
        int i = (this.c + 31) >> 5;
        int[][] iArr = (int[][]) Array.newInstance(Integer.TYPE, new int[]{this.c, i});
        int i2 = (1 << (this.c & 31)) - 1;
        if (i2 == 0) {
            i2 = -1;
        }
        for (int i3 = this.c - 1; i3 >= 0; i3--) {
            System.arraycopy(this.a[i3], 0, iArr[i3], 0, i);
            int[] iArr2 = iArr[i3];
            int i4 = i - 1;
            iArr2[i4] = iArr2[i4] & i2;
        }
        return new GF2Matrix(this.c, iArr);
    }

    public GF2Matrix c() {
        GF2Matrix gF2Matrix = new GF2Matrix(this.c, this.d + this.c);
        int i = this.d + (this.c - 1);
        int i2 = this.c - 1;
        while (i2 >= 0) {
            System.arraycopy(this.a[i2], 0, gF2Matrix.a[i2], 0, this.b);
            int[] iArr = gF2Matrix.a[i2];
            int i3 = i >> 5;
            iArr[i3] = iArr[i3] | (1 << (i & 31));
            i2--;
            i--;
        }
        return gF2Matrix;
    }

    public GF2Matrix d() {
        if (this.d <= this.c) {
            throw new ArithmeticException("empty submatrix");
        }
        int i = this.c >> 5;
        int i2 = this.c & 31;
        GF2Matrix gF2Matrix = new GF2Matrix(this.c, this.d - this.c);
        for (int i3 = this.c - 1; i3 >= 0; i3--) {
            if (i2 != 0) {
                int i4 = 0;
                int i5 = i;
                while (i4 < gF2Matrix.b - 1) {
                    int i6 = i5 + 1;
                    gF2Matrix.a[i3][i4] = (this.a[i3][i5] >>> i2) | (this.a[i3][i6] << (32 - i2));
                    i4++;
                    i5 = i6;
                }
                int i7 = i5 + 1;
                gF2Matrix.a[i3][gF2Matrix.b - 1] = this.a[i3][i5] >>> i2;
                if (i7 < this.b) {
                    int[] iArr = gF2Matrix.a[i3];
                    i5 = gF2Matrix.b - 1;
                    iArr[i5] = iArr[i5] | (this.a[i3][i7] << (32 - i2));
                }
            } else {
                System.arraycopy(this.a[i3], i, gF2Matrix.a[i3], 0, gF2Matrix.b);
            }
        }
        return gF2Matrix;
    }

    public Matrix e() {
        int[][] iArr = (int[][]) Array.newInstance(Integer.TYPE, new int[]{this.d, (this.c + 31) >>> 5});
        for (int i = 0; i < this.c; i++) {
            for (int i2 = 0; i2 < this.d; i2++) {
                int i3 = i2 & 31;
                int i4 = (this.a[i][i2 >>> 5] >>> i3) & 1;
                i3 = i >>> 5;
                int i5 = i & 31;
                if (i4 == 1) {
                    int[] iArr2 = iArr[i2];
                    iArr2[i3] = (1 << i5) | iArr2[i3];
                }
            }
        }
        return new GF2Matrix(this.c, iArr);
    }

    public Matrix f() {
        if (this.c != this.d) {
            throw new ArithmeticException("Matrix is not invertible.");
        }
        int i;
        int[][] iArr = (int[][]) Array.newInstance(Integer.TYPE, new int[]{this.c, this.b});
        for (int i2 = this.c - 1; i2 >= 0; i2--) {
            iArr[i2] = IntUtils.a(this.a[i2]);
        }
        int[][] iArr2 = (int[][]) Array.newInstance(Integer.TYPE, new int[]{this.c, this.b});
        for (i = this.c - 1; i >= 0; i--) {
            iArr2[i][i >> 5] = 1 << (i & 31);
        }
        for (i = 0; i < this.c; i++) {
            int i3;
            int i4 = i >> 5;
            int i5 = 1 << (i & 31);
            if ((iArr[i][i4] & i5) == 0) {
                i3 = i + 1;
                int i6 = 0;
                while (i3 < this.c) {
                    if ((iArr[i3][i4] & i5) != 0) {
                        a(iArr, i, i3);
                        a(iArr2, i, i3);
                        i3 = this.c;
                        i6 = 1;
                    }
                    i3++;
                }
                if (i6 == 0) {
                    throw new ArithmeticException("Matrix is not invertible.");
                }
            }
            i3 = this.c - 1;
            while (i3 >= 0) {
                if (!(i3 == i || (iArr[i3][i4] & i5) == 0)) {
                    a(iArr[i], iArr[i3], i4);
                    a(iArr2[i], iArr2[i3], 0);
                }
                i3--;
            }
        }
        return new GF2Matrix(this.d, iArr2);
    }

    public Vector a(Vector vector) {
        int i = 1;
        if (!(vector instanceof GF2Vector)) {
            throw new ArithmeticException("vector is not defined over GF(2)");
        } else if (vector.a != this.c) {
            throw new ArithmeticException("length mismatch");
        } else {
            int i2;
            int[] b = ((GF2Vector) vector).b();
            int[] iArr = new int[this.b];
            int i3 = this.c >> 5;
            int i4 = 1 << (this.c & 31);
            int i5 = 0;
            int i6 = 0;
            while (i5 < i3) {
                i2 = i6;
                i6 = 1;
                do {
                    if ((b[i5] & i6) != 0) {
                        for (int i7 = 0; i7 < this.b; i7++) {
                            iArr[i7] = iArr[i7] ^ this.a[i2][i7];
                        }
                    }
                    i2++;
                    i6 <<= 1;
                } while (i6 != 0);
                i5++;
                i6 = i2;
            }
            i2 = i6;
            while (i != i4) {
                if ((b[i3] & i) != 0) {
                    for (i6 = 0; i6 < this.b; i6++) {
                        iArr[i6] = iArr[i6] ^ this.a[i2][i6];
                    }
                }
                i <<= 1;
                i2++;
            }
            return new GF2Vector(iArr, this.d);
        }
    }

    public Vector b(Vector vector) {
        if (!(vector instanceof GF2Vector)) {
            throw new ArithmeticException("vector is not defined over GF(2)");
        } else if (vector.a != this.c) {
            throw new ArithmeticException("length mismatch");
        } else {
            int i;
            int i2;
            int[] b = ((GF2Vector) vector).b();
            int[] iArr = new int[(((this.c + this.d) + 31) >>> 5)];
            int i3 = this.c >>> 5;
            int i4 = 0;
            int i5 = 0;
            while (i4 < i3) {
                i = i5;
                i5 = 1;
                do {
                    if ((b[i4] & i5) != 0) {
                        for (i2 = 0; i2 < this.b; i2++) {
                            iArr[i2] = iArr[i2] ^ this.a[i][i2];
                        }
                        i2 = (this.d + i) >>> 5;
                        iArr[i2] = (1 << ((this.d + i) & 31)) | iArr[i2];
                    }
                    i++;
                    i5 <<= 1;
                } while (i5 != 0);
                i4++;
                i5 = i;
            }
            i4 = 1 << (this.c & 31);
            i2 = i5;
            for (i = 1; i != i4; i <<= 1) {
                if ((b[i3] & i) != 0) {
                    for (i5 = 0; i5 < this.b; i5++) {
                        iArr[i5] = iArr[i5] ^ this.a[i2][i5];
                    }
                    i5 = (this.d + i2) >>> 5;
                    iArr[i5] = (1 << ((this.d + i2) & 31)) | iArr[i5];
                }
                i2++;
            }
            return new GF2Vector(iArr, this.c + this.d);
        }
    }

    public Matrix a(Matrix matrix) {
        if (!(matrix instanceof GF2Matrix)) {
            throw new ArithmeticException("matrix is not defined over GF(2)");
        } else if (matrix.c != this.d) {
            throw new ArithmeticException("length mismatch");
        } else {
            int i;
            GF2Matrix gF2Matrix = (GF2Matrix) matrix;
            Matrix gF2Matrix2 = new GF2Matrix(this.c, matrix.d);
            int i2 = this.d & 31;
            if (i2 == 0) {
                i = this.b;
            } else {
                i = this.b - 1;
            }
            for (int i3 = 0; i3 < this.c; i3++) {
                int i4;
                int i5;
                int i6 = 0;
                int i7 = 0;
                while (i7 < i) {
                    int i8 = this.a[i3][i7];
                    i4 = i6;
                    for (i6 = 0; i6 < 32; i6++) {
                        if (((1 << i6) & i8) != 0) {
                            for (i5 = 0; i5 < gF2Matrix.b; i5++) {
                                int[] iArr = gF2Matrix2.a[i3];
                                iArr[i5] = iArr[i5] ^ gF2Matrix.a[i4][i5];
                            }
                        }
                        i4++;
                    }
                    i7++;
                    i6 = i4;
                }
                i7 = this.a[i3][this.b - 1];
                i4 = i6;
                for (i6 = 0; i6 < i2; i6++) {
                    if (((1 << i6) & i7) != 0) {
                        for (i5 = 0; i5 < gF2Matrix.b; i5++) {
                            int[] iArr2 = gF2Matrix2.a[i3];
                            iArr2[i5] = iArr2[i5] ^ gF2Matrix.a[i4][i5];
                        }
                    }
                    i4++;
                }
            }
            return gF2Matrix2;
        }
    }

    public Matrix a(Permutation permutation) {
        int[] b = permutation.b();
        if (b.length != this.d) {
            throw new ArithmeticException("length mismatch");
        }
        Matrix gF2Matrix = new GF2Matrix(this.c, this.d);
        for (int i = this.d - 1; i >= 0; i--) {
            int i2 = i >>> 5;
            int i3 = i & 31;
            int i4 = b[i] >>> 5;
            int i5 = b[i] & 31;
            for (int i6 = this.c - 1; i6 >= 0; i6--) {
                int[] iArr = gF2Matrix.a[i6];
                iArr[i2] = iArr[i2] | (((this.a[i6][i4] >>> i5) & 1) << i3);
            }
        }
        return gF2Matrix;
    }

    public Vector c(Vector vector) {
        if (!(vector instanceof GF2Vector)) {
            throw new ArithmeticException("vector is not defined over GF(2)");
        } else if (vector.a != this.d) {
            throw new ArithmeticException("length mismatch");
        } else {
            int[] b = ((GF2Vector) vector).b();
            int[] iArr = new int[((this.c + 31) >>> 5)];
            for (int i = 0; i < this.c; i++) {
                int i2;
                int i3 = 0;
                for (i2 = 0; i2 < this.b; i2++) {
                    i3 ^= this.a[i][i2] & b[i2];
                }
                int i4 = 0;
                for (i2 = 0; i2 < 32; i2++) {
                    i4 ^= (i3 >>> i2) & 1;
                }
                if (i4 == 1) {
                    i2 = i >>> 5;
                    iArr[i2] = iArr[i2] | (1 << (i & 31));
                }
            }
            return new GF2Vector(iArr, this.c);
        }
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof GF2Matrix)) {
            return false;
        }
        GF2Matrix gF2Matrix = (GF2Matrix) obj;
        if (this.c != gF2Matrix.c || this.d != gF2Matrix.d || this.b != gF2Matrix.b) {
            return false;
        }
        for (int i = 0; i < this.c; i++) {
            if (!IntUtils.a(this.a[i], gF2Matrix.a[i])) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        int i = this.b + (((this.c * 31) + this.d) * 31);
        for (int i2 = 0; i2 < this.c; i2++) {
            i = (i * 31) + this.a[i2].hashCode();
        }
        return i;
    }

    public String toString() {
        int i;
        int i2 = this.d & 31;
        if (i2 == 0) {
            i = this.b;
        } else {
            i = this.b - 1;
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i3 = 0; i3 < this.c; i3++) {
            int i4;
            stringBuffer.append(i3 + ": ");
            for (i4 = 0; i4 < i; i4++) {
                int i5;
                int i6 = this.a[i3][i4];
                for (i5 = 0; i5 < 32; i5++) {
                    if (((i6 >>> i5) & 1) == 0) {
                        stringBuffer.append('0');
                    } else {
                        stringBuffer.append('1');
                    }
                }
                stringBuffer.append(' ');
            }
            i4 = this.a[i3][this.b - 1];
            for (i5 = 0; i5 < i2; i5++) {
                if (((i4 >>> i5) & 1) == 0) {
                    stringBuffer.append('0');
                } else {
                    stringBuffer.append('1');
                }
            }
            stringBuffer.append('\n');
        }
        return stringBuffer.toString();
    }

    private static void a(int[][] iArr, int i, int i2) {
        int[] iArr2 = iArr[i];
        iArr[i] = iArr[i2];
        iArr[i2] = iArr2;
    }

    private static void a(int[] iArr, int[] iArr2, int i) {
        for (int length = iArr2.length - 1; length >= i; length--) {
            iArr2[length] = iArr[length] ^ iArr2[length];
        }
    }
}
