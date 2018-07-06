package org.spongycastle.pqc.math.ntru.euclid;

public class IntEuclidean {
    public int a;
    public int b;
    public int c;

    private IntEuclidean() {
    }

    public static IntEuclidean a(int i, int i2) {
        int i3 = 1;
        int i4 = 0;
        int i5 = 1;
        int i6 = 0;
        while (i2 != 0) {
            int i7 = i / i2;
            int i8 = i % i2;
            i = i2;
            i2 = i8;
            int i9 = i6;
            i6 = i5 - (i7 * i6);
            i5 = i9;
            int i10 = i4 - (i7 * i3);
            i4 = i3;
            i3 = i10;
        }
        IntEuclidean intEuclidean = new IntEuclidean();
        intEuclidean.a = i5;
        intEuclidean.b = i4;
        intEuclidean.c = i;
        return intEuclidean;
    }
}
