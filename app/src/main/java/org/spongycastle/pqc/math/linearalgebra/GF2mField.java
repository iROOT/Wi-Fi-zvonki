package org.spongycastle.pqc.math.linearalgebra;

import java.security.SecureRandom;

public class GF2mField {
    private int a = 0;
    private int b;

    public GF2mField(int i, int i2) {
        if (i != PolynomialRingGF2.a(i2)) {
            throw new IllegalArgumentException(" Error: the degree is not correct");
        } else if (PolynomialRingGF2.b(i2)) {
            this.a = i;
            this.b = i2;
        } else {
            throw new IllegalArgumentException(" Error: given polynomial is reducible");
        }
    }

    public int a() {
        return this.a;
    }

    public byte[] b() {
        return LittleEndianConversions.a(this.b);
    }

    public int a(int i, int i2) {
        return i ^ i2;
    }

    public int b(int i, int i2) {
        return PolynomialRingGF2.a(i, i2, this.b);
    }

    public int c(int i, int i2) {
        if (i == 0) {
            return 0;
        }
        if (i == 1) {
            return 1;
        }
        int i3;
        if (i2 < 0) {
            i = a(i);
            i2 = -i2;
            i3 = 1;
        } else {
            i3 = 1;
        }
        while (i2 != 0) {
            if ((i2 & 1) == 1) {
                i3 = b(i3, i);
            }
            i = b(i, i);
            i2 >>>= 1;
        }
        return i3;
    }

    public int a(int i) {
        return c(i, (1 << this.a) - 2);
    }

    public int b(int i) {
        for (int i2 = 1; i2 < this.a; i2++) {
            i = b(i, i);
        }
        return i;
    }

    public int a(SecureRandom secureRandom) {
        return RandUtils.a(secureRandom, 1 << this.a);
    }

    public int b(SecureRandom secureRandom) {
        int i = 0;
        int a = RandUtils.a(secureRandom, 1 << this.a);
        while (a == 0 && i < 1048576) {
            a = RandUtils.a(secureRandom, 1 << this.a);
            i++;
        }
        if (i == 1048576) {
            return 1;
        }
        return a;
    }

    public boolean c(int i) {
        if (this.a == 31) {
            if (i >= 0) {
                return true;
            }
            return false;
        } else if (i < 0 || i >= (1 << this.a)) {
            return false;
        } else {
            return true;
        }
    }

    public String d(int i) {
        String str = "";
        for (int i2 = 0; i2 < this.a; i2++) {
            if ((((byte) i) & 1) == 0) {
                str = "0" + str;
            } else {
                str = "1" + str;
            }
            i >>>= 1;
        }
        return str;
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof GF2mField)) {
            return false;
        }
        GF2mField gF2mField = (GF2mField) obj;
        if (this.a == gF2mField.a && this.b == gF2mField.b) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this.b;
    }

    public String toString() {
        return "Finite Field GF(2^" + this.a + ") = " + "GF(2)[X]/<" + e(this.b) + "> ";
    }

    private static String e(int i) {
        String str = "";
        if (i == 0) {
            return "0";
        }
        if (((byte) (i & 1)) == (byte) 1) {
            str = "1";
        }
        int i2 = i >>> 1;
        int i3 = 1;
        while (i2 != 0) {
            if (((byte) (i2 & 1)) == (byte) 1) {
                str = str + "+x^" + i3;
            }
            i2 >>>= 1;
            i3++;
        }
        return str;
    }
}
