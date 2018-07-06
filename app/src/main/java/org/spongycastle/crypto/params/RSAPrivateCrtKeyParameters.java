package org.spongycastle.crypto.params;

import java.math.BigInteger;

public class RSAPrivateCrtKeyParameters extends RSAKeyParameters {
    private BigInteger b;
    private BigInteger c;
    private BigInteger d;
    private BigInteger e;
    private BigInteger f;
    private BigInteger g;

    public RSAPrivateCrtKeyParameters(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4, BigInteger bigInteger5, BigInteger bigInteger6, BigInteger bigInteger7, BigInteger bigInteger8) {
        super(true, bigInteger, bigInteger3);
        this.b = bigInteger2;
        this.c = bigInteger4;
        this.d = bigInteger5;
        this.e = bigInteger6;
        this.f = bigInteger7;
        this.g = bigInteger8;
    }

    public BigInteger d() {
        return this.b;
    }

    public BigInteger e() {
        return this.c;
    }

    public BigInteger f() {
        return this.d;
    }

    public BigInteger g() {
        return this.e;
    }

    public BigInteger h() {
        return this.f;
    }

    public BigInteger i() {
        return this.g;
    }
}
