package org.spongycastle.math.ec;

import java.math.BigInteger;
import org.spongycastle.math.ec.ECPoint.F2m;

class Tnaf {
    public static final ZTauElement[] a = new ZTauElement[]{null, new ZTauElement(ECConstants.d, ECConstants.c), null, new ZTauElement(g, e), null, new ZTauElement(e, e), null, new ZTauElement(ECConstants.d, e), null};
    public static final byte[][] b;
    public static final ZTauElement[] c = new ZTauElement[]{null, new ZTauElement(ECConstants.d, ECConstants.c), null, new ZTauElement(g, ECConstants.d), null, new ZTauElement(e, ECConstants.d), null, new ZTauElement(ECConstants.d, ECConstants.d), null};
    public static final byte[][] d;
    private static final BigInteger e = ECConstants.d.negate();
    private static final BigInteger f = ECConstants.e.negate();
    private static final BigInteger g = ECConstants.f.negate();

    Tnaf() {
    }

    static {
        r0 = new byte[8][];
        r0[1] = new byte[]{(byte) 1};
        r0[2] = null;
        r0[3] = new byte[]{(byte) -1, (byte) 0, (byte) 1};
        r0[4] = null;
        r0[5] = new byte[]{(byte) 1, (byte) 0, (byte) 1};
        r0[6] = null;
        r0[7] = new byte[]{(byte) -1, (byte) 0, (byte) 0, (byte) 1};
        b = r0;
        r0 = new byte[8][];
        r0[1] = new byte[]{(byte) 1};
        r0[2] = null;
        r0[3] = new byte[]{(byte) -1, (byte) 0, (byte) 1};
        r0[4] = null;
        r0[5] = new byte[]{(byte) 1, (byte) 0, (byte) 1};
        r0[6] = null;
        r0[7] = new byte[]{(byte) -1, (byte) 0, (byte) 0, (byte) -1};
        d = r0;
    }

    public static BigInteger a(byte b, ZTauElement zTauElement) {
        BigInteger multiply = zTauElement.a.multiply(zTauElement.a);
        BigInteger multiply2 = zTauElement.a.multiply(zTauElement.b);
        BigInteger shiftLeft = zTauElement.b.multiply(zTauElement.b).shiftLeft(1);
        if (b == (byte) 1) {
            return multiply.add(multiply2).add(shiftLeft);
        }
        if (b == (byte) -1) {
            return multiply.subtract(multiply2).add(shiftLeft);
        }
        throw new IllegalArgumentException("mu must be 1 or -1");
    }

    public static ZTauElement a(SimpleBigDecimal simpleBigDecimal, SimpleBigDecimal simpleBigDecimal2, byte b) {
        int i = 0;
        int i2 = 1;
        if (simpleBigDecimal2.d() != simpleBigDecimal.d()) {
            throw new IllegalArgumentException("lambda0 and lambda1 do not have same scale");
        } else if (b == (byte) 1 || b == (byte) -1) {
            BigInteger c = simpleBigDecimal.c();
            BigInteger c2 = simpleBigDecimal2.c();
            SimpleBigDecimal a = simpleBigDecimal.a(c);
            SimpleBigDecimal a2 = simpleBigDecimal2.a(c2);
            SimpleBigDecimal a3 = a.a(a);
            if (b == (byte) 1) {
                a3 = a3.a(a2);
            } else {
                a3 = a3.b(a2);
            }
            SimpleBigDecimal a4 = a2.a(a2).a(a2);
            SimpleBigDecimal a5 = a4.a(a2);
            if (b == (byte) 1) {
                a2 = a.b(a4);
                a = a.a(a5);
            } else {
                a2 = a.a(a4);
                a = a.b(a5);
            }
            if (a3.b(ECConstants.d) >= 0) {
                if (a2.b(e) < 0) {
                    i2 = 0;
                    i = b;
                }
            } else if (a.b(ECConstants.e) >= 0) {
                i2 = 0;
                byte i3 = b;
            } else {
                i2 = 0;
            }
            if (a3.b(e) < 0) {
                if (a2.b(ECConstants.d) >= 0) {
                    i3 = (byte) (-b);
                } else {
                    i2 = -1;
                }
            } else if (a.b(f) < 0) {
                i3 = (byte) (-b);
            }
            return new ZTauElement(c.add(BigInteger.valueOf((long) i2)), c2.add(BigInteger.valueOf((long) i3)));
        } else {
            throw new IllegalArgumentException("mu must be 1 or -1");
        }
    }

    public static SimpleBigDecimal a(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, byte b, int i, int i2) {
        int i3 = ((i + 5) / 2) + i2;
        BigInteger multiply = bigInteger2.multiply(bigInteger.shiftRight(((i - i3) - 2) + b));
        BigInteger add = multiply.add(bigInteger3.multiply(multiply.shiftRight(i)));
        multiply = add.shiftRight(i3 - i2);
        if (add.testBit((i3 - i2) - 1)) {
            multiply = multiply.add(ECConstants.d);
        }
        return new SimpleBigDecimal(multiply, i2);
    }

    public static F2m a(F2m f2m) {
        return f2m.r();
    }

    public static byte a(ECCurve.F2m f2m) {
        if (!f2m.j()) {
            throw new IllegalArgumentException("No Koblitz curve (ABC), TNAF multiplication not possible");
        } else if (f2m.f().i()) {
            return (byte) -1;
        } else {
            return (byte) 1;
        }
    }

    public static BigInteger[] a(byte b, int i, boolean z) {
        if (b == (byte) 1 || b == (byte) -1) {
            BigInteger bigInteger;
            BigInteger valueOf;
            if (z) {
                bigInteger = ECConstants.e;
                valueOf = BigInteger.valueOf((long) b);
            } else {
                bigInteger = ECConstants.c;
                valueOf = ECConstants.d;
            }
            int i2 = 1;
            BigInteger bigInteger2 = bigInteger;
            bigInteger = valueOf;
            while (i2 < i) {
                if (b == (byte) 1) {
                    valueOf = bigInteger;
                } else {
                    valueOf = bigInteger.negate();
                }
                i2++;
                BigInteger subtract = valueOf.subtract(bigInteger2.shiftLeft(1));
                bigInteger2 = bigInteger;
                bigInteger = subtract;
            }
            return new BigInteger[]{bigInteger2, bigInteger};
        }
        throw new IllegalArgumentException("mu must be 1 or -1");
    }

    public static BigInteger a(byte b, int i) {
        if (i != 4) {
            BigInteger[] a = a(b, i, false);
            BigInteger bit = ECConstants.c.setBit(i);
            return ECConstants.e.multiply(a[0]).multiply(a[1].modInverse(bit)).mod(bit);
        } else if (b == (byte) 1) {
            return BigInteger.valueOf(6);
        } else {
            return BigInteger.valueOf(10);
        }
    }

    public static BigInteger[] b(ECCurve.F2m f2m) {
        if (f2m.j()) {
            BigInteger subtract;
            BigInteger subtract2;
            int m = f2m.m();
            int intValue = f2m.f().a().intValue();
            byte k = f2m.k();
            int intValue2 = f2m.r().intValue();
            BigInteger[] a = a(k, (m + 3) - intValue, false);
            if (k == (byte) 1) {
                subtract = ECConstants.d.subtract(a[1]);
                subtract2 = ECConstants.d.subtract(a[0]);
            } else if (k == (byte) -1) {
                subtract = ECConstants.d.add(a[1]);
                subtract2 = ECConstants.d.add(a[0]);
            } else {
                throw new IllegalArgumentException("mu must be 1 or -1");
            }
            BigInteger[] bigIntegerArr = new BigInteger[2];
            if (intValue2 == 2) {
                bigIntegerArr[0] = subtract.shiftRight(1);
                bigIntegerArr[1] = subtract2.shiftRight(1).negate();
            } else if (intValue2 == 4) {
                bigIntegerArr[0] = subtract.shiftRight(2);
                bigIntegerArr[1] = subtract2.shiftRight(2).negate();
            } else {
                throw new IllegalArgumentException("h (Cofactor) must be 2 or 4");
            }
            return bigIntegerArr;
        }
        throw new IllegalArgumentException("si is defined for Koblitz curves only");
    }

    public static ZTauElement a(BigInteger bigInteger, int i, byte b, BigInteger[] bigIntegerArr, byte b2, byte b3) {
        BigInteger add;
        if (b2 == (byte) 1) {
            add = bigIntegerArr[0].add(bigIntegerArr[1]);
        } else {
            add = bigIntegerArr[0].subtract(bigIntegerArr[1]);
        }
        BigInteger bigInteger2 = a(b2, i, true)[1];
        ZTauElement a = a(a(bigInteger, bigIntegerArr[0], bigInteger2, b, i, (int) b3), a(bigInteger, bigIntegerArr[1], bigInteger2, b, i, (int) b3), b2);
        return new ZTauElement(bigInteger.subtract(add.multiply(a.a)).subtract(BigInteger.valueOf(2).multiply(bigIntegerArr[1]).multiply(a.b)), bigIntegerArr[1].multiply(a.a).subtract(bigIntegerArr[0].multiply(a.b)));
    }

    public static F2m a(F2m f2m, byte[] bArr) {
        F2m f2m2 = (F2m) ((ECCurve.F2m) f2m.a()).e();
        for (int length = bArr.length - 1; length >= 0; length--) {
            f2m2 = a(f2m2);
            if (bArr[length] == (byte) 1) {
                f2m2 = f2m2.a(f2m);
            } else if (bArr[length] == (byte) -1) {
                f2m2 = f2m2.b(f2m);
            }
        }
        return f2m2;
    }

    public static byte[] a(byte b, ZTauElement zTauElement, byte b2, BigInteger bigInteger, BigInteger bigInteger2, ZTauElement[] zTauElementArr) {
        if (b == (byte) 1 || b == (byte) -1) {
            int bitLength = a(b, zTauElement).bitLength();
            byte[] bArr = new byte[(bitLength > 30 ? (bitLength + 4) + b2 : b2 + 34)];
            BigInteger shiftRight = bigInteger.shiftRight(1);
            BigInteger bigInteger3 = zTauElement.a;
            BigInteger bigInteger4 = zTauElement.b;
            bitLength = 0;
            while (true) {
                if (bigInteger3.equals(ECConstants.c) && bigInteger4.equals(ECConstants.c)) {
                    return bArr;
                }
                BigInteger mod;
                if (bigInteger3.testBit(0)) {
                    byte intValue;
                    int i;
                    mod = bigInteger3.add(bigInteger4.multiply(bigInteger2)).mod(bigInteger);
                    if (mod.compareTo(shiftRight) >= 0) {
                        intValue = (byte) mod.subtract(bigInteger).intValue();
                    } else {
                        intValue = (byte) mod.intValue();
                    }
                    bArr[bitLength] = intValue;
                    if (intValue < (byte) 0) {
                        i = (byte) (-intValue);
                        intValue = (byte) 0;
                    } else {
                        byte i2 = intValue;
                        intValue = (byte) 1;
                    }
                    if (intValue != (byte) 0) {
                        bigInteger3 = bigInteger3.subtract(zTauElementArr[i2].a);
                        bigInteger4 = bigInteger4.subtract(zTauElementArr[i2].b);
                    } else {
                        bigInteger3 = bigInteger3.add(zTauElementArr[i2].a);
                        bigInteger4 = bigInteger4.add(zTauElementArr[i2].b);
                    }
                    mod = bigInteger3;
                } else {
                    bArr[bitLength] = (byte) 0;
                    mod = bigInteger3;
                }
                if (b == (byte) 1) {
                    bigInteger3 = bigInteger4.add(mod.shiftRight(1));
                } else {
                    bigInteger3 = bigInteger4.subtract(mod.shiftRight(1));
                }
                bigInteger4 = mod.shiftRight(1).negate();
                bitLength++;
            }
        } else {
            throw new IllegalArgumentException("mu must be 1 or -1");
        }
    }

    public static F2m[] a(F2m f2m, byte b) {
        byte[][] bArr;
        ECPoint[] eCPointArr = new F2m[16];
        eCPointArr[1] = f2m;
        if (b == (byte) 0) {
            bArr = b;
        } else {
            bArr = d;
        }
        int length = bArr.length;
        for (int i = 3; i < length; i += 2) {
            eCPointArr[i] = a(f2m, bArr[i]);
        }
        f2m.a().a(eCPointArr);
        return eCPointArr;
    }
}
