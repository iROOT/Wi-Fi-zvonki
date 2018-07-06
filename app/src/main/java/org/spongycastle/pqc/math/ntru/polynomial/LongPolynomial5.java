package org.spongycastle.pqc.math.ntru.polynomial;

import java.lang.reflect.Array;
import org.spongycastle.util.Arrays;

public class LongPolynomial5 {
    private long[] a;
    private int b;

    public LongPolynomial5(IntegerPolynomial integerPolynomial) {
        this.b = integerPolynomial.a.length;
        this.a = new long[((this.b + 4) / 5)];
        long j = 0;
        int i = 0;
        for (int i2 = 0; i2 < this.b; i2++) {
            long[] jArr = this.a;
            jArr[i] = jArr[i] | (((long) integerPolynomial.a[i2]) << j);
            j += 12;
            if (j >= 60) {
                i++;
                j = 0;
            }
        }
    }

    private LongPolynomial5(long[] jArr, int i) {
        this.a = jArr;
        this.b = i;
    }

    public LongPolynomial5 a(TernaryPolynomial ternaryPolynomial) {
        int i;
        int i2;
        long[][] jArr = (long[][]) Array.newInstance(Long.TYPE, new int[]{5, (this.a.length + ((ternaryPolynomial.c() + 4) / 5)) - 1});
        int[] a = ternaryPolynomial.a();
        for (i = 0; i != a.length; i++) {
            int i3 = a[i];
            int i4 = i3 / 5;
            i2 = i3 - (i4 * 5);
            for (long j : this.a) {
                long j2;
                jArr[i2][i4] = (jArr[i2][i4] + j2) & 576319980446939135L;
                i4++;
            }
        }
        a = ternaryPolynomial.b();
        for (i = 0; i != a.length; i++) {
            i3 = a[i];
            i4 = i3 / 5;
            i2 = i3 - (i4 * 5);
            for (long j22 : this.a) {
                jArr[i2][i4] = ((576601524159907840L + jArr[i2][i4]) - j22) & 576319980446939135L;
                i4++;
            }
        }
        long[] a2 = Arrays.a(jArr[0], jArr[0].length + 1);
        for (i3 = 1; i3 <= 4; i3++) {
            i4 = i3 * 12;
            i2 = 60 - i4;
            long j3 = (1 << i2) - 1;
            int length = jArr[i3].length;
            for (i = 0; i < length; i++) {
                j22 = jArr[i3][i] >> i2;
                a2[i] = (((jArr[i3][i] & j3) << i4) + a2[i]) & 576319980446939135L;
                int i5 = i + 1;
                a2[i5] = (j22 + a2[i5]) & 576319980446939135L;
            }
        }
        i2 = (this.b % 5) * 12;
        for (int length2 = this.a.length - 1; length2 < a2.length; length2++) {
            long j4;
            if (length2 == this.a.length - 1) {
                j4 = this.b == 5 ? 0 : a2[length2] >> i2;
                i = 0;
            } else {
                j4 = a2[length2];
                i = (length2 * 5) - this.b;
            }
            int i6 = i / 5;
            i -= i6 * 5;
            long j5 = j4 << (i * 12);
            j4 >>= (5 - i) * 12;
            a2[i6] = (j5 + a2[i6]) & 576319980446939135L;
            i = i6 + 1;
            if (i < this.a.length) {
                a2[i] = (j4 + a2[i]) & 576319980446939135L;
            }
        }
        return new LongPolynomial5(a2, this.b);
    }

    public IntegerPolynomial a() {
        int[] iArr = new int[this.b];
        long j = 0;
        int i = 0;
        for (int i2 = 0; i2 < this.b; i2++) {
            iArr[i2] = (int) ((this.a[i] >> j) & 2047);
            j += 12;
            if (j >= 60) {
                i++;
                j = 0;
            }
        }
        return new IntegerPolynomial(iArr);
    }
}
