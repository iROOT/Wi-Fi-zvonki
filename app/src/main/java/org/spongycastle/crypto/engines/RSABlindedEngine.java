package org.spongycastle.crypto.engines;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.spongycastle.crypto.AsymmetricBlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.params.ParametersWithRandom;
import org.spongycastle.crypto.params.RSAKeyParameters;
import org.spongycastle.crypto.params.RSAPrivateCrtKeyParameters;
import org.spongycastle.util.BigIntegers;

public class RSABlindedEngine implements AsymmetricBlockCipher {
    private static final BigInteger a = BigInteger.valueOf(1);
    private RSACoreEngine b = new RSACoreEngine();
    private RSAKeyParameters c;
    private SecureRandom d;

    public void a(boolean z, CipherParameters cipherParameters) {
        this.b.a(z, cipherParameters);
        if (cipherParameters instanceof ParametersWithRandom) {
            ParametersWithRandom parametersWithRandom = (ParametersWithRandom) cipherParameters;
            this.c = (RSAKeyParameters) parametersWithRandom.b();
            this.d = parametersWithRandom.a();
            return;
        }
        this.c = (RSAKeyParameters) cipherParameters;
        this.d = new SecureRandom();
    }

    public int a() {
        return this.b.a();
    }

    public int b() {
        return this.b.b();
    }

    public byte[] a(byte[] bArr, int i, int i2) {
        if (this.c == null) {
            throw new IllegalStateException("RSA engine not initialised");
        }
        BigInteger b;
        BigInteger a = this.b.a(bArr, i, i2);
        if (this.c instanceof RSAPrivateCrtKeyParameters) {
            RSAPrivateCrtKeyParameters rSAPrivateCrtKeyParameters = (RSAPrivateCrtKeyParameters) this.c;
            BigInteger d = rSAPrivateCrtKeyParameters.d();
            if (d != null) {
                b = rSAPrivateCrtKeyParameters.b();
                BigInteger a2 = BigIntegers.a(a, b.subtract(a), this.d);
                b = this.b.b(a2.modPow(d, b).multiply(a).mod(b)).multiply(a2.modInverse(b)).mod(b);
            } else {
                b = this.b.b(a);
            }
        } else {
            b = this.b.b(a);
        }
        return this.b.a(b);
    }
}
