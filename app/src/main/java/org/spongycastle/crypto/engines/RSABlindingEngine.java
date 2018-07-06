package org.spongycastle.crypto.engines;

import java.math.BigInteger;
import org.spongycastle.crypto.AsymmetricBlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.params.ParametersWithRandom;
import org.spongycastle.crypto.params.RSABlindingParameters;
import org.spongycastle.crypto.params.RSAKeyParameters;

public class RSABlindingEngine implements AsymmetricBlockCipher {
    private RSACoreEngine a = new RSACoreEngine();
    private RSAKeyParameters b;
    private BigInteger c;
    private boolean d;

    public void a(boolean z, CipherParameters cipherParameters) {
        RSABlindingParameters rSABlindingParameters;
        if (cipherParameters instanceof ParametersWithRandom) {
            rSABlindingParameters = (RSABlindingParameters) ((ParametersWithRandom) cipherParameters).b();
        } else {
            rSABlindingParameters = (RSABlindingParameters) cipherParameters;
        }
        this.a.a(z, rSABlindingParameters.a());
        this.d = z;
        this.b = rSABlindingParameters.a();
        this.c = rSABlindingParameters.b();
    }

    public int a() {
        return this.a.a();
    }

    public int b() {
        return this.a.b();
    }

    public byte[] a(byte[] bArr, int i, int i2) {
        BigInteger a = this.a.a(bArr, i, i2);
        if (this.d) {
            a = a(a);
        } else {
            a = b(a);
        }
        return this.a.a(a);
    }

    private BigInteger a(BigInteger bigInteger) {
        return bigInteger.multiply(this.c.modPow(this.b.c(), this.b.b())).mod(this.b.b());
    }

    private BigInteger b(BigInteger bigInteger) {
        BigInteger b = this.b.b();
        return bigInteger.multiply(this.c.modInverse(b)).mod(b);
    }
}
