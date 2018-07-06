package org.spongycastle.crypto.agreement.srp;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.spongycastle.crypto.Digest;

public class SRP6Client {
    protected BigInteger a;
    protected BigInteger b;
    protected BigInteger c;
    protected BigInteger d;
    protected BigInteger e;
    protected BigInteger f;
    protected BigInteger g;
    protected BigInteger h;
    protected Digest i;
    protected SecureRandom j;

    public void a(BigInteger bigInteger, BigInteger bigInteger2, Digest digest, SecureRandom secureRandom) {
        this.a = bigInteger;
        this.b = bigInteger2;
        this.i = digest;
        this.j = secureRandom;
    }

    public BigInteger a(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        this.f = SRP6Util.a(this.i, this.a, bArr, bArr2, bArr3);
        this.c = a();
        this.d = this.b.modPow(this.c, this.a);
        return this.d;
    }

    public BigInteger a(BigInteger bigInteger) {
        this.e = SRP6Util.a(this.a, bigInteger);
        this.g = SRP6Util.a(this.i, this.a, this.d, this.e);
        this.h = b();
        return this.h;
    }

    protected BigInteger a() {
        return SRP6Util.a(this.i, this.a, this.b, this.j);
    }

    private BigInteger b() {
        BigInteger a = SRP6Util.a(this.i, this.a, this.b);
        return this.e.subtract(this.b.modPow(this.f, this.a).multiply(a).mod(this.a)).mod(this.a).modPow(this.g.multiply(this.f).add(this.c), this.a);
    }
}
