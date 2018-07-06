package org.spongycastle.math.ec;

import android.support.v4.app.NotificationCompat;
import java.math.BigInteger;
import java.util.Random;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.BigIntegers;

public abstract class ECFieldElement implements ECConstants {

    public static class F2m extends ECFieldElement {
        private int a;
        private int b;
        private int[] h;
        private LongArray i;

        public F2m(int i, int i2, int i3, int i4, BigInteger bigInteger) {
            if (i3 == 0 && i4 == 0) {
                this.a = 2;
                this.h = new int[]{i2};
            } else if (i3 >= i4) {
                throw new IllegalArgumentException("k2 must be smaller than k3");
            } else if (i3 <= 0) {
                throw new IllegalArgumentException("k2 must be larger than 0");
            } else {
                this.a = 3;
                this.h = new int[]{i2, i3, i4};
            }
            this.b = i;
            this.i = new LongArray(bigInteger);
        }

        private F2m(int i, int[] iArr, LongArray longArray) {
            this.b = i;
            this.a = iArr.length == 1 ? 2 : 3;
            this.h = iArr;
            this.i = longArray;
        }

        public int h() {
            return this.i.c();
        }

        public boolean i() {
            return this.i.a();
        }

        public boolean j() {
            return this.i.f();
        }

        public BigInteger a() {
            return this.i.d();
        }

        public int b() {
            return this.b;
        }

        public static void a(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
            if ((eCFieldElement instanceof F2m) && (eCFieldElement2 instanceof F2m)) {
                F2m f2m = (F2m) eCFieldElement;
                F2m f2m2 = (F2m) eCFieldElement2;
                if (f2m.a != f2m2.a) {
                    throw new IllegalArgumentException("One of the F2m field elements has incorrect representation");
                } else if (f2m.b != f2m2.b || !Arrays.a(f2m.h, f2m2.h)) {
                    throw new IllegalArgumentException("Field elements are not elements of the same field F2m");
                } else {
                    return;
                }
            }
            throw new IllegalArgumentException("Field elements are not both instances of ECFieldElement.F2m");
        }

        public ECFieldElement a(ECFieldElement eCFieldElement) {
            LongArray longArray = (LongArray) this.i.clone();
            longArray.a(((F2m) eCFieldElement).i, 0);
            return new F2m(this.b, this.h, longArray);
        }

        public ECFieldElement c() {
            return new F2m(this.b, this.h, this.i.e());
        }

        public ECFieldElement b(ECFieldElement eCFieldElement) {
            return a(eCFieldElement);
        }

        public ECFieldElement c(ECFieldElement eCFieldElement) {
            return new F2m(this.b, this.h, this.i.a(((F2m) eCFieldElement).i, this.b, this.h));
        }

        public ECFieldElement d(ECFieldElement eCFieldElement) {
            return c(eCFieldElement.f());
        }

        public ECFieldElement d() {
            return this;
        }

        public ECFieldElement e() {
            return new F2m(this.b, this.h, this.i.a(this.b, this.h));
        }

        public ECFieldElement f() {
            return new F2m(this.b, this.h, this.i.b(this.b, this.h));
        }

        public ECFieldElement g() {
            throw new RuntimeException("Not implemented");
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof F2m)) {
                return false;
            }
            F2m f2m = (F2m) obj;
            if (this.b == f2m.b && this.a == f2m.a && Arrays.a(this.h, f2m.h) && this.i.equals(f2m.i)) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            return (this.i.hashCode() ^ this.b) ^ Arrays.a(this.h);
        }
    }

    public static class Fp extends ECFieldElement {
        BigInteger a;
        BigInteger b;
        BigInteger h;

        static BigInteger a(BigInteger bigInteger) {
            int bitLength = bigInteger.bitLength();
            if (bitLength <= NotificationCompat.FLAG_HIGH_PRIORITY || bigInteger.shiftRight(bitLength - 64).longValue() != -1) {
                return null;
            }
            return d.shiftLeft(bitLength).subtract(bigInteger);
        }

        public Fp(BigInteger bigInteger, BigInteger bigInteger2) {
            this(bigInteger, a(bigInteger), bigInteger2);
        }

        Fp(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
            if (bigInteger3 == null || bigInteger3.signum() < 0 || bigInteger3.compareTo(bigInteger) >= 0) {
                throw new IllegalArgumentException("x value invalid in Fp field element");
            }
            this.a = bigInteger;
            this.b = bigInteger2;
            this.h = bigInteger3;
        }

        public BigInteger a() {
            return this.h;
        }

        public int b() {
            return this.a.bitLength();
        }

        public ECFieldElement a(ECFieldElement eCFieldElement) {
            return new Fp(this.a, this.b, a(this.h, eCFieldElement.a()));
        }

        public ECFieldElement c() {
            BigInteger add = this.h.add(ECConstants.d);
            if (add.compareTo(this.a) == 0) {
                add = ECConstants.c;
            }
            return new Fp(this.a, this.b, add);
        }

        public ECFieldElement b(ECFieldElement eCFieldElement) {
            BigInteger subtract = this.h.subtract(eCFieldElement.a());
            if (subtract.signum() < 0) {
                subtract = subtract.add(this.a);
            }
            return new Fp(this.a, this.b, subtract);
        }

        public ECFieldElement c(ECFieldElement eCFieldElement) {
            return new Fp(this.a, this.b, b(this.h, eCFieldElement.a()));
        }

        public ECFieldElement d(ECFieldElement eCFieldElement) {
            return new Fp(this.a, b(this.h, eCFieldElement.a().modInverse(this.a)));
        }

        public ECFieldElement d() {
            BigInteger bigInteger;
            if (this.h.signum() == 0) {
                bigInteger = this.h;
            } else if (d.equals(this.b)) {
                bigInteger = this.a.xor(this.h);
            } else {
                bigInteger = this.a.subtract(this.h);
            }
            return new Fp(this.a, this.b, bigInteger);
        }

        public ECFieldElement e() {
            return new Fp(this.a, this.b, b(this.h, this.h));
        }

        public ECFieldElement f() {
            return new Fp(this.a, this.b, this.h.modInverse(this.a));
        }

        public ECFieldElement g() {
            if (!this.a.testBit(0)) {
                throw new RuntimeException("not done yet");
            } else if (this.a.testBit(1)) {
                ECFieldElement fp = new Fp(this.a, this.b, this.h.modPow(this.a.shiftRight(2).add(ECConstants.d), this.a));
                if (!fp.e().equals(this)) {
                    fp = null;
                }
                return fp;
            } else {
                BigInteger subtract = this.a.subtract(ECConstants.d);
                BigInteger shiftRight = subtract.shiftRight(1);
                if (!this.h.modPow(shiftRight, this.a).equals(ECConstants.d)) {
                    return null;
                }
                BigInteger bigInteger;
                BigInteger add = subtract.shiftRight(2).shiftLeft(1).add(ECConstants.d);
                BigInteger bigInteger2 = this.h;
                BigInteger b = b(b(bigInteger2));
                Random random = new Random();
                while (true) {
                    bigInteger = new BigInteger(this.a.bitLength(), random);
                    if (bigInteger.compareTo(this.a) < 0 && bigInteger.multiply(bigInteger).subtract(b).modPow(shiftRight, this.a).equals(subtract)) {
                        BigInteger[] a = a(bigInteger, bigInteger2, add);
                        BigInteger bigInteger3 = a[0];
                        bigInteger = a[1];
                        if (b(bigInteger, bigInteger).equals(b)) {
                            break;
                        } else if (!(bigInteger3.equals(ECConstants.d) || bigInteger3.equals(subtract))) {
                            return null;
                        }
                    }
                }
                if (bigInteger.testBit(0)) {
                    bigInteger = bigInteger.add(this.a);
                }
                return new Fp(this.a, this.b, bigInteger.shiftRight(1));
            }
        }

        private BigInteger[] a(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
            int bitLength = bigInteger3.bitLength();
            int lowestSetBit = bigInteger3.getLowestSetBit();
            BigInteger bigInteger4 = ECConstants.d;
            BigInteger bigInteger5 = ECConstants.e;
            BigInteger bigInteger6 = ECConstants.d;
            int i = bitLength - 1;
            BigInteger bigInteger7 = bigInteger;
            BigInteger bigInteger8 = bigInteger5;
            BigInteger bigInteger9 = ECConstants.d;
            BigInteger bigInteger10 = bigInteger6;
            while (i >= lowestSetBit + 1) {
                bigInteger10 = b(bigInteger10, bigInteger9);
                if (bigInteger3.testBit(i)) {
                    bigInteger9 = b(bigInteger10, bigInteger2);
                    bigInteger4 = b(bigInteger4, bigInteger7);
                    bigInteger5 = c(bigInteger7.multiply(bigInteger8).subtract(bigInteger.multiply(bigInteger10)));
                    bigInteger6 = c(bigInteger7.multiply(bigInteger7).subtract(bigInteger9.shiftLeft(1)));
                } else {
                    bigInteger5 = c(bigInteger4.multiply(bigInteger8).subtract(bigInteger10));
                    bigInteger9 = c(bigInteger7.multiply(bigInteger8).subtract(bigInteger.multiply(bigInteger10)));
                    bigInteger4 = bigInteger5;
                    bigInteger5 = c(bigInteger8.multiply(bigInteger8).subtract(bigInteger10.shiftLeft(1)));
                    bigInteger6 = bigInteger9;
                    bigInteger9 = bigInteger10;
                }
                i--;
                bigInteger7 = bigInteger6;
                bigInteger8 = bigInteger5;
            }
            bigInteger9 = b(bigInteger10, bigInteger9);
            bigInteger5 = b(bigInteger9, bigInteger2);
            bigInteger6 = c(bigInteger4.multiply(bigInteger8).subtract(bigInteger9));
            bigInteger10 = c(bigInteger7.multiply(bigInteger8).subtract(bigInteger.multiply(bigInteger9)));
            bigInteger9 = b(bigInteger9, bigInteger5);
            bigInteger5 = bigInteger6;
            bigInteger6 = bigInteger10;
            bigInteger10 = bigInteger9;
            for (bitLength = 1; bitLength <= lowestSetBit; bitLength++) {
                bigInteger5 = b(bigInteger5, bigInteger6);
                bigInteger6 = c(bigInteger6.multiply(bigInteger6).subtract(bigInteger10.shiftLeft(1)));
                bigInteger10 = b(bigInteger10, bigInteger10);
            }
            return new BigInteger[]{bigInteger5, bigInteger6};
        }

        protected BigInteger a(BigInteger bigInteger, BigInteger bigInteger2) {
            BigInteger add = bigInteger.add(bigInteger2);
            if (add.compareTo(this.a) >= 0) {
                return add.subtract(this.a);
            }
            return add;
        }

        protected BigInteger b(BigInteger bigInteger) {
            BigInteger shiftLeft = bigInteger.shiftLeft(1);
            if (shiftLeft.compareTo(this.a) >= 0) {
                return shiftLeft.subtract(this.a);
            }
            return shiftLeft;
        }

        protected BigInteger b(BigInteger bigInteger, BigInteger bigInteger2) {
            return c(bigInteger.multiply(bigInteger2));
        }

        protected BigInteger c(BigInteger bigInteger) {
            if (this.b == null) {
                return bigInteger.mod(this.a);
            }
            int bitLength = this.a.bitLength();
            while (bigInteger.bitLength() > bitLength + 1) {
                BigInteger shiftRight = bigInteger.shiftRight(bitLength);
                BigInteger subtract = bigInteger.subtract(shiftRight.shiftLeft(bitLength));
                if (!this.b.equals(d)) {
                    shiftRight = shiftRight.multiply(this.b);
                }
                bigInteger = shiftRight.add(subtract);
            }
            while (bigInteger.compareTo(this.a) >= 0) {
                bigInteger = bigInteger.subtract(this.a);
            }
            return bigInteger;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Fp)) {
                return false;
            }
            Fp fp = (Fp) obj;
            if (this.a.equals(fp.a) && this.h.equals(fp.h)) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            return this.a.hashCode() ^ this.h.hashCode();
        }
    }

    public abstract BigInteger a();

    public abstract ECFieldElement a(ECFieldElement eCFieldElement);

    public abstract int b();

    public abstract ECFieldElement b(ECFieldElement eCFieldElement);

    public abstract ECFieldElement c();

    public abstract ECFieldElement c(ECFieldElement eCFieldElement);

    public abstract ECFieldElement d();

    public abstract ECFieldElement d(ECFieldElement eCFieldElement);

    public abstract ECFieldElement e();

    public abstract ECFieldElement f();

    public abstract ECFieldElement g();

    public int h() {
        return a().bitLength();
    }

    public boolean i() {
        return a().signum() == 0;
    }

    public boolean j() {
        return a().testBit(0);
    }

    public String toString() {
        return a().toString(16);
    }

    public byte[] k() {
        return BigIntegers.a((b() + 7) / 8, a());
    }
}
