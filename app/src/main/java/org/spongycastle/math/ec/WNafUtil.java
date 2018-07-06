package org.spongycastle.math.ec;

import java.math.BigInteger;

public abstract class WNafUtil {
    private static int[] a = new int[]{13, 41, 121, 337, 897, 2305};

    public static int[] a(BigInteger bigInteger) {
        if ((bigInteger.bitLength() >>> 16) != 0) {
            throw new IllegalArgumentException("'k' must have bitlength < 2^16");
        }
        BigInteger add = bigInteger.shiftLeft(1).add(bigInteger);
        int bitLength = add.bitLength() - 1;
        int[] iArr = new int[((bitLength + 1) >> 1)];
        int i = 1;
        int i2 = 0;
        int i3 = 0;
        while (i <= bitLength) {
            int i4;
            boolean testBit = add.testBit(i);
            boolean testBit2 = bigInteger.testBit(i);
            if (testBit == testBit2) {
                i4 = i2 + 1;
            } else {
                if (testBit2) {
                    i4 = -1;
                } else {
                    i4 = 1;
                }
                int i5 = i3 + 1;
                iArr[i3] = (i4 << 16) | i2;
                i4 = 0;
                i3 = i5;
            }
            i++;
            i2 = i4;
        }
        if (iArr.length > i3) {
            return a(iArr, i3);
        }
        return iArr;
    }

    public static int[] a(int i, BigInteger bigInteger) {
        if (i == 2) {
            return a(bigInteger);
        }
        if (i < 2 || i > 16) {
            throw new IllegalArgumentException("'width' must be in the range [2, 16]");
        } else if ((bigInteger.bitLength() >>> 16) != 0) {
            throw new IllegalArgumentException("'k' must have bitlength < 2^16");
        } else {
            int[] iArr = new int[((bigInteger.bitLength() / i) + 1)];
            int i2 = 1 << i;
            int i3 = i2 - 1;
            int i4 = i2 >>> 1;
            int i5 = 0;
            int i6 = 0;
            boolean z = false;
            while (i5 <= bigInteger.bitLength()) {
                if (bigInteger.testBit(i5) == z) {
                    i5++;
                } else {
                    int i7;
                    bigInteger = bigInteger.shiftRight(i5);
                    int intValue = bigInteger.intValue() & i3;
                    if (z) {
                        intValue++;
                    }
                    z = (intValue & i4) != 0;
                    if (z) {
                        i7 = intValue - i2;
                    } else {
                        i7 = intValue;
                    }
                    if (i6 > 0) {
                        intValue = i5 - 1;
                    } else {
                        intValue = i5;
                    }
                    i5 = i6 + 1;
                    iArr[i6] = intValue | (i7 << 16);
                    i6 = i5;
                    i5 = i;
                }
            }
            return iArr.length > i6 ? a(iArr, i6) : iArr;
        }
    }

    public static byte[] a(BigInteger bigInteger, BigInteger bigInteger2) {
        int i = 0;
        byte[] bArr = new byte[(Math.max(bigInteger.bitLength(), bigInteger2.bitLength()) + 1)];
        int i2 = 0;
        int i3 = 0;
        while (true) {
            if (bigInteger.signum() <= 0 && bigInteger2.signum() <= 0 && i2 <= 0 && i <= 0) {
                break;
            }
            int i4;
            int intValue = (bigInteger.intValue() + i2) & 7;
            int intValue2 = (bigInteger2.intValue() + i) & 7;
            int i5 = intValue & 1;
            if (i5 != 0) {
                i5 -= intValue & 2;
                if (intValue + i5 == 4 && (intValue2 & 3) == 2) {
                    i4 = -i5;
                    i5 = intValue2 & 1;
                    if (i5 != 0) {
                        i5 -= intValue2 & 2;
                        if (intValue2 + i5 == 4 && (intValue & 3) == 2) {
                            intValue = -i5;
                            if ((i2 << 1) == i4 + 1) {
                                i2 = 1 - i2;
                            }
                            if ((i << 1) != intValue + 1) {
                                i = 1 - i;
                            }
                            bigInteger = bigInteger.shiftRight(1);
                            bigInteger2 = bigInteger2.shiftRight(1);
                            i5 = i3 + 1;
                            bArr[i3] = (byte) ((intValue & 15) | (i4 << 4));
                            i3 = i5;
                        }
                    }
                    intValue = i5;
                    if ((i2 << 1) == i4 + 1) {
                        i2 = 1 - i2;
                    }
                    if ((i << 1) != intValue + 1) {
                        i = 1 - i;
                    }
                    bigInteger = bigInteger.shiftRight(1);
                    bigInteger2 = bigInteger2.shiftRight(1);
                    i5 = i3 + 1;
                    bArr[i3] = (byte) ((intValue & 15) | (i4 << 4));
                    i3 = i5;
                }
            }
            i4 = i5;
            i5 = intValue2 & 1;
            if (i5 != 0) {
                i5 -= intValue2 & 2;
                intValue = -i5;
                if ((i2 << 1) == i4 + 1) {
                    i2 = 1 - i2;
                }
                if ((i << 1) != intValue + 1) {
                    i = 1 - i;
                }
                bigInteger = bigInteger.shiftRight(1);
                bigInteger2 = bigInteger2.shiftRight(1);
                i5 = i3 + 1;
                bArr[i3] = (byte) ((intValue & 15) | (i4 << 4));
                i3 = i5;
            }
            intValue = i5;
            if ((i2 << 1) == i4 + 1) {
                i2 = 1 - i2;
            }
            if ((i << 1) != intValue + 1) {
                i = 1 - i;
            }
            bigInteger = bigInteger.shiftRight(1);
            bigInteger2 = bigInteger2.shiftRight(1);
            i5 = i3 + 1;
            bArr[i3] = (byte) ((intValue & 15) | (i4 << 4));
            i3 = i5;
        }
        if (bArr.length > i3) {
            return a(bArr, i3);
        }
        return bArr;
    }

    public static WNafPreCompInfo a(PreCompInfo preCompInfo) {
        if (preCompInfo == null || !(preCompInfo instanceof WNafPreCompInfo)) {
            return new WNafPreCompInfo();
        }
        return (WNafPreCompInfo) preCompInfo;
    }

    public static int a(int i) {
        return a(i, a);
    }

    public static int a(int i, int[] iArr) {
        int i2 = 0;
        while (i2 < iArr.length && i >= iArr[i2]) {
            i2++;
        }
        return i2 + 2;
    }

    public static WNafPreCompInfo a(ECPoint eCPoint, int i, boolean z) {
        ECCurve a = eCPoint.a();
        PreCompInfo a2 = a(a.a(eCPoint));
        ECPoint[] a3 = a2.a();
        if (a3 == null) {
            a3 = new ECPoint[]{eCPoint};
        }
        int length = a3.length;
        int max = 1 << Math.max(0, i - 2);
        if (length < max) {
            ECPoint c = a2.c();
            if (c == null) {
                c = a3[0].p().k();
                a2.a(c);
            }
            a3 = a(a3, max);
            while (length < max) {
                a3[length] = c.b(a3[length - 1]);
                length++;
            }
            a.a(a3);
        }
        a2.a(a3);
        if (z) {
            ECPoint[] eCPointArr;
            int i2;
            ECPoint[] b = a2.b();
            if (b == null) {
                eCPointArr = new ECPoint[max];
                i2 = 0;
            } else {
                i2 = b.length;
                if (i2 < max) {
                    eCPointArr = a(b, max);
                } else {
                    eCPointArr = b;
                }
            }
            while (i2 < max) {
                eCPointArr[i2] = a3[i2].o();
                i2++;
            }
            a2.b(eCPointArr);
        }
        a.a(eCPoint, a2);
        return a2;
    }

    private static byte[] a(byte[] bArr, int i) {
        Object obj = new byte[i];
        System.arraycopy(bArr, 0, obj, 0, obj.length);
        return obj;
    }

    private static int[] a(int[] iArr, int i) {
        Object obj = new int[i];
        System.arraycopy(iArr, 0, obj, 0, obj.length);
        return obj;
    }

    private static ECPoint[] a(ECPoint[] eCPointArr, int i) {
        Object obj = new ECPoint[i];
        System.arraycopy(eCPointArr, 0, obj, 0, eCPointArr.length);
        return obj;
    }
}
