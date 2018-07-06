package org.spongycastle.crypto.params;

import java.math.BigInteger;
import org.spongycastle.crypto.CipherParameters;

public class DHParameters implements CipherParameters {
    private BigInteger a;
    private BigInteger b;
    private BigInteger c;
    private BigInteger d;
    private int e;
    private int f;
    private DHValidationParameters g;

    private static int a(int i) {
        if (i == 0) {
            return 160;
        }
        if (i >= 160) {
            i = 160;
        }
        return i;
    }

    public DHParameters(BigInteger bigInteger, BigInteger bigInteger2) {
        this(bigInteger, bigInteger2, null, 0);
    }

    public DHParameters(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
        this(bigInteger, bigInteger2, bigInteger3, 0);
    }

    public DHParameters(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, int i) {
        this(bigInteger, bigInteger2, bigInteger3, a(i), i, null, null);
    }

    public DHParameters(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4, DHValidationParameters dHValidationParameters) {
        this(bigInteger, bigInteger2, bigInteger3, 160, 0, bigInteger4, dHValidationParameters);
    }

    public DHParameters(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, int i, int i2, BigInteger bigInteger4, DHValidationParameters dHValidationParameters) {
        if (i2 != 0) {
            if (BigInteger.valueOf(2 ^ ((long) (i2 - 1))).compareTo(bigInteger) == 1) {
                throw new IllegalArgumentException("when l value specified, it must satisfy 2^(l-1) <= p");
            } else if (i2 < i) {
                throw new IllegalArgumentException("when l value specified, it may not be less than m value");
            }
        }
        this.a = bigInteger2;
        this.b = bigInteger;
        this.c = bigInteger3;
        this.e = i;
        this.f = i2;
        this.d = bigInteger4;
        this.g = dHValidationParameters;
    }

    public BigInteger a() {
        return this.b;
    }

    public BigInteger b() {
        return this.a;
    }

    public BigInteger c() {
        return this.c;
    }

    public int d() {
        return this.e;
    }

    public int e() {
        return this.f;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof DHParameters)) {
            return false;
        }
        DHParameters dHParameters = (DHParameters) obj;
        if (c() != null) {
            if (!c().equals(dHParameters.c())) {
                return false;
            }
        } else if (dHParameters.c() != null) {
            return false;
        }
        if (dHParameters.a().equals(this.b) && dHParameters.b().equals(this.a)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (c() != null ? c().hashCode() : 0) ^ (b().hashCode() ^ a().hashCode());
    }
}
