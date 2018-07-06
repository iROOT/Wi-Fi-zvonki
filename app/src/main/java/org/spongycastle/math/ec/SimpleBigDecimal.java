package org.spongycastle.math.ec;

import java.math.BigInteger;

class SimpleBigDecimal {
    private final BigInteger a;
    private final int b;

    public SimpleBigDecimal(BigInteger bigInteger, int i) {
        if (i < 0) {
            throw new IllegalArgumentException("scale may not be negative");
        }
        this.a = bigInteger;
        this.b = i;
    }

    private SimpleBigDecimal(SimpleBigDecimal simpleBigDecimal) {
        this.a = simpleBigDecimal.a;
        this.b = simpleBigDecimal.b;
    }

    private void c(SimpleBigDecimal simpleBigDecimal) {
        if (this.b != simpleBigDecimal.b) {
            throw new IllegalArgumentException("Only SimpleBigDecimal of same scale allowed in arithmetic operations");
        }
    }

    public SimpleBigDecimal a(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("scale may not be negative");
        } else if (i == this.b) {
            return new SimpleBigDecimal(this);
        } else {
            return new SimpleBigDecimal(this.a.shiftLeft(i - this.b), i);
        }
    }

    public SimpleBigDecimal a(SimpleBigDecimal simpleBigDecimal) {
        c(simpleBigDecimal);
        return new SimpleBigDecimal(this.a.add(simpleBigDecimal.a), this.b);
    }

    public SimpleBigDecimal a() {
        return new SimpleBigDecimal(this.a.negate(), this.b);
    }

    public SimpleBigDecimal b(SimpleBigDecimal simpleBigDecimal) {
        return a(simpleBigDecimal.a());
    }

    public SimpleBigDecimal a(BigInteger bigInteger) {
        return new SimpleBigDecimal(this.a.subtract(bigInteger.shiftLeft(this.b)), this.b);
    }

    public int b(BigInteger bigInteger) {
        return this.a.compareTo(bigInteger.shiftLeft(this.b));
    }

    public BigInteger b() {
        return this.a.shiftRight(this.b);
    }

    public BigInteger c() {
        return a(new SimpleBigDecimal(ECConstants.d, 1).a(this.b)).b();
    }

    public int d() {
        return this.b;
    }

    public String toString() {
        if (this.b == 0) {
            return this.a.toString();
        }
        int i;
        BigInteger b = b();
        BigInteger subtract = this.a.subtract(b.shiftLeft(this.b));
        if (this.a.signum() == -1) {
            subtract = ECConstants.d.shiftLeft(this.b).subtract(subtract);
        }
        if (b.signum() == -1 && !subtract.equals(ECConstants.c)) {
            b = b.add(ECConstants.d);
        }
        String bigInteger = b.toString();
        char[] cArr = new char[this.b];
        String bigInteger2 = subtract.toString(2);
        int length = bigInteger2.length();
        int i2 = this.b - length;
        for (i = 0; i < i2; i++) {
            cArr[i] = '0';
        }
        for (i = 0; i < length; i++) {
            cArr[i2 + i] = bigInteger2.charAt(i);
        }
        String str = new String(cArr);
        StringBuffer stringBuffer = new StringBuffer(bigInteger);
        stringBuffer.append(".");
        stringBuffer.append(str);
        return stringBuffer.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SimpleBigDecimal)) {
            return false;
        }
        SimpleBigDecimal simpleBigDecimal = (SimpleBigDecimal) obj;
        if (this.a.equals(simpleBigDecimal.a) && this.b == simpleBigDecimal.b) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this.a.hashCode() ^ this.b;
    }
}
