package org.spongycastle.pqc.math.linearalgebra;

import java.lang.reflect.Array;
import java.security.SecureRandom;

public final class GoppaCode {

    public static class MaMaPe {
        private GF2Matrix a;
        private GF2Matrix b;
        private Permutation c;

        public MaMaPe(GF2Matrix gF2Matrix, GF2Matrix gF2Matrix2, Permutation permutation) {
            this.a = gF2Matrix;
            this.b = gF2Matrix2;
            this.c = permutation;
        }

        public GF2Matrix a() {
            return this.b;
        }

        public Permutation b() {
            return this.c;
        }
    }

    public static class MatrixSet {
    }

    private GoppaCode() {
    }

    public static GF2Matrix a(GF2mField gF2mField, PolynomialGF2mSmallM polynomialGF2mSmallM) {
        int i;
        int i2;
        int i3;
        int a = gF2mField.a();
        int i4 = 1 << a;
        int a2 = polynomialGF2mSmallM.a();
        int[][] iArr = (int[][]) Array.newInstance(Integer.TYPE, new int[]{a2, i4});
        int[][] iArr2 = (int[][]) Array.newInstance(Integer.TYPE, new int[]{a2, i4});
        for (i = 0; i < i4; i++) {
            iArr2[0][i] = gF2mField.a(polynomialGF2mSmallM.b(i));
        }
        for (i2 = 1; i2 < a2; i2++) {
            for (i = 0; i < i4; i++) {
                iArr2[i2][i] = gF2mField.b(iArr2[i2 - 1][i], i);
            }
        }
        for (i3 = 0; i3 < a2; i3++) {
            for (i2 = 0; i2 < i4; i2++) {
                for (i = 0; i <= i3; i++) {
                    iArr[i3][i2] = gF2mField.a(iArr[i3][i2], gF2mField.b(iArr2[i][i2], polynomialGF2mSmallM.a((a2 + i) - i3)));
                }
            }
        }
        iArr2 = (int[][]) Array.newInstance(Integer.TYPE, new int[]{a2 * a, (i4 + 31) >>> 5});
        for (i3 = 0; i3 < i4; i3++) {
            int i5 = i3 >>> 5;
            int i6 = 1 << (i3 & 31);
            for (i2 = 0; i2 < a2; i2++) {
                int i7 = iArr[i2][i3];
                for (i = 0; i < a; i++) {
                    if (((i7 >>> i) & 1) != 0) {
                        int[] iArr3 = iArr2[(((i2 + 1) * a) - i) - 1];
                        iArr3[i5] = iArr3[i5] ^ i6;
                    }
                }
            }
        }
        return new GF2Matrix(i4, iArr2);
    }

    public static MaMaPe a(GF2Matrix gF2Matrix, SecureRandom secureRandom) {
        int h = gF2Matrix.h();
        GF2Matrix gF2Matrix2 = null;
        while (true) {
            GF2Matrix gF2Matrix3;
            Object obj;
            Permutation permutation = new Permutation(h, secureRandom);
            Matrix matrix = (GF2Matrix) gF2Matrix.a(permutation);
            GF2Matrix b = matrix.b();
            try {
                gF2Matrix3 = (GF2Matrix) b.f();
                obj = 1;
            } catch (ArithmeticException e) {
                gF2Matrix3 = gF2Matrix2;
                obj = null;
            }
            if (obj != null) {
                return new MaMaPe(b, ((GF2Matrix) gF2Matrix3.a(matrix)).d(), permutation);
            }
            gF2Matrix2 = gF2Matrix3;
        }
    }

    public static GF2Vector a(GF2Vector gF2Vector, GF2mField gF2mField, PolynomialGF2mSmallM polynomialGF2mSmallM, PolynomialGF2mSmallM[] polynomialGF2mSmallMArr) {
        int i = 0;
        int a = 1 << gF2mField.a();
        GF2Vector gF2Vector2 = new GF2Vector(a);
        if (!gF2Vector.d()) {
            PolynomialGF2mSmallM[] f = new PolynomialGF2mSmallM(gF2Vector.a(gF2mField)).e(polynomialGF2mSmallM).c(1).a(polynomialGF2mSmallMArr).f(polynomialGF2mSmallM);
            PolynomialGF2mSmallM a2 = f[0].c(f[0]).a(f[1].c(f[1]).f(1));
            a2 = a2.d(gF2mField.a(a2.b()));
            while (i < a) {
                if (a2.b(i) == 0) {
                    gF2Vector2.a(i);
                }
                i++;
            }
        }
        return gF2Vector2;
    }
}
