package org.spongycastle.crypto.prng.drbg;

import org.spongycastle.math.ec.ECPoint;

public class DualECPoints {
    private final ECPoint a;
    private final ECPoint b;
    private final int c;
    private final int d;

    public DualECPoints(int i, ECPoint eCPoint, ECPoint eCPoint2, int i2) {
        if (eCPoint.a().equals(eCPoint2.a())) {
            this.c = i;
            this.a = eCPoint;
            this.b = eCPoint2;
            this.d = i2;
            return;
        }
        throw new IllegalArgumentException("points need to be on the same curve");
    }

    public int a() {
        return this.a.a().a();
    }

    public int b() {
        return ((this.a.a().a() - (a(this.d) + 13)) / 8) * 8;
    }

    public ECPoint c() {
        return this.a;
    }

    public ECPoint d() {
        return this.b;
    }

    public int e() {
        return this.c;
    }

    private static int a(int i) {
        int i2 = 0;
        while (true) {
            i >>= 1;
            if (i == 0) {
                return i2;
            }
            i2++;
        }
    }
}
