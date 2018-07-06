package org.spongycastle.math.ec;

import java.math.BigInteger;
import java.util.Random;
import org.spongycastle.util.BigIntegers;

public abstract class ECCurve {
    protected ECFieldElement a;
    protected ECFieldElement b;
    protected int c = 0;
    protected ECMultiplier d = null;

    public class Config {
        protected int a;
        protected ECMultiplier b;
        final /* synthetic */ ECCurve c;

        Config(ECCurve eCCurve, int i, ECMultiplier eCMultiplier) {
            this.c = eCCurve;
            this.a = i;
            this.b = eCMultiplier;
        }

        public Config a(int i) {
            this.a = i;
            return this;
        }

        public ECCurve a() {
            if (this.c.a(this.a)) {
                ECCurve c = this.c.c();
                if (c == this.c) {
                    throw new IllegalStateException("implementation returned current curve");
                }
                c.c = this.a;
                c.d = this.b;
                return c;
            }
            throw new IllegalStateException("unsupported coordinate system");
        }
    }

    public static class F2m extends ECCurve {
        private int e;
        private int f;
        private int g;
        private int h;
        private BigInteger i;
        private BigInteger j;
        private org.spongycastle.math.ec.ECPoint.F2m k;
        private byte l;
        private BigInteger[] m;

        public F2m(int i, int i2, BigInteger bigInteger, BigInteger bigInteger2) {
            this(i, i2, 0, 0, bigInteger, bigInteger2, null, null);
        }

        public F2m(int i, int i2, BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4) {
            this(i, i2, 0, 0, bigInteger, bigInteger2, bigInteger3, bigInteger4);
        }

        public F2m(int i, int i2, int i3, int i4, BigInteger bigInteger, BigInteger bigInteger2) {
            this(i, i2, i3, i4, bigInteger, bigInteger2, null, null);
        }

        public F2m(int i, int i2, int i3, int i4, BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4) {
            this.l = (byte) 0;
            this.m = null;
            this.e = i;
            this.f = i2;
            this.g = i3;
            this.h = i4;
            this.i = bigInteger3;
            this.j = bigInteger4;
            if (i2 == 0) {
                throw new IllegalArgumentException("k1 must be > 0");
            }
            if (i3 == 0) {
                if (i4 != 0) {
                    throw new IllegalArgumentException("k3 must be 0 if k2 == 0");
                }
            } else if (i3 <= i2) {
                throw new IllegalArgumentException("k2 must be > k1");
            } else if (i4 <= i3) {
                throw new IllegalArgumentException("k3 must be > k2");
            }
            this.k = new org.spongycastle.math.ec.ECPoint.F2m(this, null, null);
            this.a = a(bigInteger);
            this.b = a(bigInteger2);
            this.c = 0;
        }

        protected F2m(int i, int i2, int i3, int i4, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, BigInteger bigInteger, BigInteger bigInteger2) {
            this.l = (byte) 0;
            this.m = null;
            this.e = i;
            this.f = i2;
            this.g = i3;
            this.h = i4;
            this.i = bigInteger;
            this.j = bigInteger2;
            this.k = new org.spongycastle.math.ec.ECPoint.F2m(this, null, null);
            this.a = eCFieldElement;
            this.b = eCFieldElement2;
            this.c = 0;
        }

        protected ECCurve c() {
            return new F2m(this.e, this.f, this.g, this.h, this.a, this.b, this.i, this.j);
        }

        public boolean a(int i) {
            switch (i) {
                case 0:
                case 1:
                case 6:
                    return true;
                default:
                    return false;
            }
        }

        protected ECMultiplier d() {
            if (j()) {
                return new WTauNafMultiplier();
            }
            return super.d();
        }

        public int a() {
            return this.e;
        }

        public ECFieldElement a(BigInteger bigInteger) {
            return new org.spongycastle.math.ec.ECFieldElement.F2m(this.e, this.f, this.g, this.h, bigInteger);
        }

        public ECPoint a(BigInteger bigInteger, BigInteger bigInteger2, boolean z) {
            ECFieldElement a = a(bigInteger);
            ECFieldElement a2 = a(bigInteger2);
            switch (h()) {
                case 5:
                case 6:
                    if (!a.i()) {
                        a2 = a2.d(a).a(a);
                        break;
                    }
                    break;
            }
            return a(a, a2, z);
        }

        protected ECPoint a(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, boolean z) {
            return new org.spongycastle.math.ec.ECPoint.F2m(this, eCFieldElement, eCFieldElement2, z);
        }

        public ECPoint e() {
            return this.k;
        }

        public boolean j() {
            return this.i != null && this.j != null && this.a.h() <= 1 && this.b.h() == 1;
        }

        synchronized byte k() {
            if (this.l == (byte) 0) {
                this.l = Tnaf.a(this);
            }
            return this.l;
        }

        synchronized BigInteger[] l() {
            if (this.m == null) {
                this.m = Tnaf.b(this);
            }
            return this.m;
        }

        protected ECPoint a(int i, BigInteger bigInteger) {
            ECFieldElement c;
            boolean z = false;
            ECFieldElement a = a(bigInteger);
            if (!a.i()) {
                ECFieldElement a2 = a(a.a(this.a).a(this.b.c(a.e().f())));
                if (a2 != null) {
                    boolean j = a2.j();
                    if (i == 1) {
                        z = true;
                    }
                    if (j != z) {
                        a2 = a2.c();
                    }
                    c = a.c(a2);
                    switch (h()) {
                        case 5:
                        case 6:
                            c = c.d(a).a(a);
                            break;
                    }
                }
                throw new IllegalArgumentException("Invalid point compression");
            }
            c = (org.spongycastle.math.ec.ECFieldElement.F2m) this.b;
            for (int i2 = 0; i2 < this.e - 1; i2++) {
                c = c.e();
            }
            return new org.spongycastle.math.ec.ECPoint.F2m(this, a, c, true);
        }

        private ECFieldElement a(ECFieldElement eCFieldElement) {
            if (eCFieldElement.i()) {
                return eCFieldElement;
            }
            ECFieldElement eCFieldElement2;
            ECFieldElement a = a(ECConstants.c);
            Random random = new Random();
            do {
                ECFieldElement a2 = a(new BigInteger(this.e, random));
                ECFieldElement eCFieldElement3 = eCFieldElement;
                eCFieldElement2 = a;
                for (int i = 1; i <= this.e - 1; i++) {
                    eCFieldElement3 = eCFieldElement3.e();
                    eCFieldElement2 = eCFieldElement2.e().a(eCFieldElement3.c(a2));
                    eCFieldElement3 = eCFieldElement3.a(eCFieldElement);
                }
                if (!eCFieldElement3.i()) {
                    return null;
                }
            } while (eCFieldElement2.e().a(eCFieldElement2).i());
            return eCFieldElement2;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof F2m)) {
                return false;
            }
            F2m f2m = (F2m) obj;
            if (this.e == f2m.e && this.f == f2m.f && this.g == f2m.g && this.h == f2m.h && this.a.equals(f2m.a) && this.b.equals(f2m.b)) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            return ((((this.a.hashCode() ^ this.b.hashCode()) ^ this.e) ^ this.f) ^ this.g) ^ this.h;
        }

        public int m() {
            return this.e;
        }

        public boolean n() {
            return this.g == 0 && this.h == 0;
        }

        public int o() {
            return this.f;
        }

        public int p() {
            return this.g;
        }

        public int q() {
            return this.h;
        }

        public BigInteger r() {
            return this.j;
        }
    }

    public static class Fp extends ECCurve {
        BigInteger e;
        BigInteger f;
        org.spongycastle.math.ec.ECPoint.Fp g = new org.spongycastle.math.ec.ECPoint.Fp(this, null, null);

        public Fp(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
            this.e = bigInteger;
            this.f = org.spongycastle.math.ec.ECFieldElement.Fp.a(bigInteger);
            this.a = a(bigInteger2);
            this.b = a(bigInteger3);
            this.c = 4;
        }

        protected Fp(BigInteger bigInteger, BigInteger bigInteger2, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
            this.e = bigInteger;
            this.f = bigInteger2;
            this.a = eCFieldElement;
            this.b = eCFieldElement2;
            this.c = 4;
        }

        protected ECCurve c() {
            return new Fp(this.e, this.f, this.a, this.b);
        }

        public boolean a(int i) {
            switch (i) {
                case 0:
                case 1:
                case 2:
                case 4:
                    return true;
                default:
                    return false;
            }
        }

        public BigInteger j() {
            return this.e;
        }

        public int a() {
            return this.e.bitLength();
        }

        public ECFieldElement a(BigInteger bigInteger) {
            return new org.spongycastle.math.ec.ECFieldElement.Fp(this.e, this.f, bigInteger);
        }

        protected ECPoint a(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, boolean z) {
            return new org.spongycastle.math.ec.ECPoint.Fp(this, eCFieldElement, eCFieldElement2, z);
        }

        public ECPoint b(ECPoint eCPoint) {
            if (!(this == eCPoint.a() || h() != 2 || eCPoint.l())) {
                switch (eCPoint.a().h()) {
                    case 2:
                    case 3:
                    case 4:
                        return new org.spongycastle.math.ec.ECPoint.Fp(this, a(eCPoint.c.a()), a(eCPoint.d.a()), new ECFieldElement[]{a(eCPoint.e[0].a())}, eCPoint.f);
                }
            }
            return super.b(eCPoint);
        }

        protected ECPoint a(int i, BigInteger bigInteger) {
            boolean z = false;
            ECFieldElement a = a(bigInteger);
            ECFieldElement g = a.c(a.e().a(this.a)).a(this.b).g();
            if (g == null) {
                throw new RuntimeException("Invalid point compression");
            }
            BigInteger a2 = g.a();
            boolean testBit = a2.testBit(0);
            if (i == 1) {
                z = true;
            }
            if (testBit != z) {
                g = a(this.e.subtract(a2));
            }
            return new org.spongycastle.math.ec.ECPoint.Fp(this, a, g, true);
        }

        public ECPoint e() {
            return this.g;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Fp)) {
                return false;
            }
            Fp fp = (Fp) obj;
            if (this.e.equals(fp.e) && this.a.equals(fp.a) && this.b.equals(fp.b)) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            return (this.a.hashCode() ^ this.b.hashCode()) ^ this.e.hashCode();
        }
    }

    public abstract int a();

    public abstract ECFieldElement a(BigInteger bigInteger);

    protected abstract ECPoint a(int i, BigInteger bigInteger);

    protected abstract ECPoint a(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, boolean z);

    protected abstract ECCurve c();

    public abstract ECPoint e();

    public Config b() {
        return new Config(this, this.c, this.d);
    }

    public ECPoint a(BigInteger bigInteger, BigInteger bigInteger2) {
        return a(bigInteger, bigInteger2, false);
    }

    public ECPoint a(BigInteger bigInteger, BigInteger bigInteger2, boolean z) {
        return a(a(bigInteger), a(bigInteger2), z);
    }

    protected ECMultiplier d() {
        return new WNafL2RMultiplier();
    }

    public boolean a(int i) {
        return i == 0;
    }

    public PreCompInfo a(ECPoint eCPoint) {
        c(eCPoint);
        return eCPoint.g;
    }

    public void a(ECPoint eCPoint, PreCompInfo preCompInfo) {
        c(eCPoint);
        eCPoint.g = preCompInfo;
    }

    public ECPoint b(ECPoint eCPoint) {
        if (this == eCPoint.a()) {
            return eCPoint;
        }
        if (eCPoint.l()) {
            return e();
        }
        ECPoint k = eCPoint.k();
        return a(k.e().a(), k.f().a(), k.f);
    }

    public void a(ECPoint[] eCPointArr) {
        int i = 0;
        b(eCPointArr);
        if (h() != 0) {
            int i2;
            ECFieldElement[] eCFieldElementArr = new ECFieldElement[eCPointArr.length];
            int[] iArr = new int[eCPointArr.length];
            int i3 = 0;
            for (i2 = 0; i2 < eCPointArr.length; i2++) {
                ECPoint eCPoint = eCPointArr[i2];
                if (!(eCPoint == null || eCPoint.j())) {
                    eCFieldElementArr[i3] = eCPoint.a(0);
                    int i4 = i3 + 1;
                    iArr[i3] = i2;
                    i3 = i4;
                }
            }
            if (i3 != 0) {
                ECAlgorithms.a(eCFieldElementArr, 0, i3);
                while (i < i3) {
                    i2 = iArr[i];
                    eCPointArr[i2] = eCPointArr[i2].a(eCFieldElementArr[i]);
                    i++;
                }
            }
        }
    }

    public ECFieldElement f() {
        return this.a;
    }

    public ECFieldElement g() {
        return this.b;
    }

    public int h() {
        return this.c;
    }

    public ECMultiplier i() {
        if (this.d == null) {
            this.d = d();
        }
        return this.d;
    }

    public ECPoint a(byte[] bArr) {
        int a = (a() + 7) / 8;
        switch (bArr[0]) {
            case (byte) 0:
                if (bArr.length == 1) {
                    return e();
                }
                throw new IllegalArgumentException("Incorrect length for infinity encoding");
            case (byte) 2:
            case (byte) 3:
                if (bArr.length == a + 1) {
                    return a(bArr[0] & 1, BigIntegers.a(bArr, 1, a));
                }
                throw new IllegalArgumentException("Incorrect length for compressed encoding");
            case (byte) 4:
            case (byte) 6:
            case (byte) 7:
                if (bArr.length == (a * 2) + 1) {
                    return a(BigIntegers.a(bArr, 1, a), BigIntegers.a(bArr, a + 1, a));
                }
                throw new IllegalArgumentException("Incorrect length for uncompressed/hybrid encoding");
            default:
                throw new IllegalArgumentException("Invalid point encoding 0x" + Integer.toString(bArr[0], 16));
        }
    }

    protected void c(ECPoint eCPoint) {
        if (eCPoint == null || this != eCPoint.a()) {
            throw new IllegalArgumentException("'point' must be non-null and on this curve");
        }
    }

    protected void b(ECPoint[] eCPointArr) {
        if (eCPointArr == null) {
            throw new IllegalArgumentException("'points' cannot be null");
        }
        int i = 0;
        while (i < eCPointArr.length) {
            ECPoint eCPoint = eCPointArr[i];
            if (eCPoint == null || this == eCPoint.a()) {
                i++;
            } else {
                throw new IllegalArgumentException("'points' entries must be null or on this curve");
            }
        }
    }
}
