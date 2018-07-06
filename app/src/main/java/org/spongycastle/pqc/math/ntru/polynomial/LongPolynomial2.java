package org.spongycastle.pqc.math.ntru.polynomial;

import net.hockeyapp.android.k;
import org.spongycastle.util.Arrays;

public class LongPolynomial2 {
    private long[] a;
    private int b;

    public LongPolynomial2(IntegerPolynomial integerPolynomial) {
        int i = 0;
        this.b = integerPolynomial.a.length;
        this.a = new long[((this.b + 1) / 2)];
        int i2 = 0;
        while (i < this.b) {
            long j;
            int i3 = i + 1;
            int i4 = integerPolynomial.a[i];
            while (i4 < 0) {
                i4 += k.DIALOG_POSITIVE_BUTTON_ID;
            }
            if (i3 < this.b) {
                i = i3 + 1;
                j = (long) integerPolynomial.a[i3];
            } else {
                i = i3;
                j = 0;
            }
            while (j < 0) {
                j += 2048;
            }
            this.a[i2] = (j << 24) + ((long) i4);
            i2++;
        }
    }

    private LongPolynomial2(long[] jArr) {
        this.a = jArr;
    }

    private LongPolynomial2(int i) {
        this.a = new long[i];
    }

    public LongPolynomial2 a(LongPolynomial2 longPolynomial2) {
        int length = this.a.length;
        if (longPolynomial2.a.length == length && this.b == longPolynomial2.b) {
            LongPolynomial2 b = b(longPolynomial2);
            if (b.a.length > length) {
                int i;
                if (this.b % 2 == 0) {
                    for (i = length; i < b.a.length; i++) {
                        b.a[i - length] = (b.a[i - length] + b.a[i]) & 34342963199L;
                    }
                    b.a = Arrays.a(b.a, length);
                } else {
                    for (i = length; i < b.a.length; i++) {
                        b.a[i - length] = b.a[i - length] + (b.a[i - 1] >> 24);
                        b.a[i - length] = b.a[i - length] + ((b.a[i] & 2047) << 24);
                        long[] jArr = b.a;
                        int i2 = i - length;
                        jArr[i2] = jArr[i2] & 34342963199L;
                    }
                    b.a = Arrays.a(b.a, length);
                    long[] jArr2 = b.a;
                    length = b.a.length - 1;
                    jArr2[length] = jArr2[length] & 2047;
                }
            }
            LongPolynomial2 longPolynomial22 = new LongPolynomial2(b.a);
            longPolynomial22.b = this.b;
            return longPolynomial22;
        }
        throw new IllegalArgumentException("Number of coefficients must be the same");
    }

    public IntegerPolynomial a() {
        int i = 0;
        int[] iArr = new int[this.b];
        int i2 = 0;
        while (i < this.a.length) {
            int i3 = i2 + 1;
            iArr[i2] = (int) (this.a[i] & 2047);
            if (i3 < this.b) {
                i2 = i3 + 1;
                iArr[i3] = (int) ((this.a[i] >> 24) & 2047);
            } else {
                i2 = i3;
            }
            i++;
        }
        return new IntegerPolynomial(iArr);
    }

    private LongPolynomial2 b(LongPolynomial2 longPolynomial2) {
        long[] jArr = this.a;
        long[] jArr2 = longPolynomial2.a;
        int length = longPolynomial2.a.length;
        LongPolynomial2 longPolynomial22;
        int i;
        int max;
        if (length <= 32) {
            int i2 = length * 2;
            longPolynomial22 = new LongPolynomial2(new long[i2]);
            for (i = 0; i < i2; i++) {
                for (max = Math.max(0, (i - length) + 1); max <= Math.min(i, length - 1); max++) {
                    long j = jArr[i - max] * jArr2[max];
                    long j2 = (34342961152L + (2047 & j)) & j;
                    j = (j >>> 48) & 2047;
                    longPolynomial22.a[i] = (j2 + longPolynomial22.a[i]) & 34342963199L;
                    longPolynomial22.a[i + 1] = (j + longPolynomial22.a[i + 1]) & 34342963199L;
                }
            }
            return longPolynomial22;
        }
        i = length / 2;
        LongPolynomial2 longPolynomial23 = new LongPolynomial2(Arrays.a(jArr, i));
        LongPolynomial2 longPolynomial24 = new LongPolynomial2(Arrays.a(jArr, i, length));
        LongPolynomial2 longPolynomial25 = new LongPolynomial2(Arrays.a(jArr2, i));
        LongPolynomial2 longPolynomial26 = new LongPolynomial2(Arrays.a(jArr2, i, length));
        LongPolynomial2 longPolynomial27 = (LongPolynomial2) longPolynomial23.clone();
        longPolynomial27.c(longPolynomial24);
        longPolynomial22 = (LongPolynomial2) longPolynomial25.clone();
        longPolynomial22.c(longPolynomial26);
        longPolynomial25 = longPolynomial23.b(longPolynomial25);
        LongPolynomial2 b = longPolynomial24.b(longPolynomial26);
        longPolynomial23 = longPolynomial27.b(longPolynomial22);
        longPolynomial23.d(longPolynomial25);
        longPolynomial23.d(b);
        longPolynomial22 = new LongPolynomial2(length * 2);
        for (max = 0; max < longPolynomial25.a.length; max++) {
            longPolynomial22.a[max] = longPolynomial25.a[max] & 34342963199L;
        }
        for (max = 0; max < longPolynomial23.a.length; max++) {
            longPolynomial22.a[i + max] = (longPolynomial22.a[i + max] + longPolynomial23.a[max]) & 34342963199L;
        }
        for (max = 0; max < b.a.length; max++) {
            longPolynomial22.a[(i * 2) + max] = (longPolynomial22.a[(i * 2) + max] + b.a[max]) & 34342963199L;
        }
        return longPolynomial22;
    }

    private void c(LongPolynomial2 longPolynomial2) {
        if (longPolynomial2.a.length > this.a.length) {
            this.a = Arrays.a(this.a, longPolynomial2.a.length);
        }
        for (int i = 0; i < longPolynomial2.a.length; i++) {
            this.a[i] = (this.a[i] + longPolynomial2.a[i]) & 34342963199L;
        }
    }

    private void d(LongPolynomial2 longPolynomial2) {
        if (longPolynomial2.a.length > this.a.length) {
            this.a = Arrays.a(this.a, longPolynomial2.a.length);
        }
        for (int i = 0; i < longPolynomial2.a.length; i++) {
            this.a[i] = ((140737496743936L + this.a[i]) - longPolynomial2.a[i]) & 34342963199L;
        }
    }

    public void a(LongPolynomial2 longPolynomial2, int i) {
        long j = ((long) i) + (((long) i) << 24);
        for (int i2 = 0; i2 < longPolynomial2.a.length; i2++) {
            this.a[i2] = ((140737496743936L + this.a[i2]) - longPolynomial2.a[i2]) & j;
        }
    }

    public void a(int i) {
        long j = ((long) i) + (((long) i) << 24);
        for (int i2 = 0; i2 < this.a.length; i2++) {
            this.a[i2] = (this.a[i2] << 1) & j;
        }
    }

    public Object clone() {
        LongPolynomial2 longPolynomial2 = new LongPolynomial2((long[]) this.a.clone());
        longPolynomial2.b = this.b;
        return longPolynomial2;
    }

    public boolean equals(Object obj) {
        if (obj instanceof LongPolynomial2) {
            return Arrays.a(this.a, ((LongPolynomial2) obj).a);
        }
        return false;
    }
}
