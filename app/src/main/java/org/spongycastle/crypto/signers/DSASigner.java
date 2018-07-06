package org.spongycastle.crypto.signers;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DSA;
import org.spongycastle.crypto.params.DSAKeyParameters;
import org.spongycastle.crypto.params.DSAParameters;
import org.spongycastle.crypto.params.DSAPrivateKeyParameters;
import org.spongycastle.crypto.params.DSAPublicKeyParameters;
import org.spongycastle.crypto.params.ParametersWithRandom;

public class DSASigner implements DSA {
    private final DSAKCalculator a;
    private DSAKeyParameters b;
    private SecureRandom c;

    public DSASigner() {
        this.a = new RandomDSAKCalculator();
    }

    public DSASigner(DSAKCalculator dSAKCalculator) {
        this.a = dSAKCalculator;
    }

    public void a(boolean z, CipherParameters cipherParameters) {
        if (!z) {
            this.b = (DSAPublicKeyParameters) cipherParameters;
        } else if (cipherParameters instanceof ParametersWithRandom) {
            ParametersWithRandom parametersWithRandom = (ParametersWithRandom) cipherParameters;
            this.c = parametersWithRandom.a();
            this.b = (DSAPrivateKeyParameters) parametersWithRandom.b();
        } else {
            this.c = new SecureRandom();
            this.b = (DSAPrivateKeyParameters) cipherParameters;
        }
    }

    public BigInteger[] a(byte[] bArr) {
        DSAParameters b = this.b.b();
        BigInteger a = a(b.b(), bArr);
        if (this.a.a()) {
            this.a.a(b.b(), ((DSAPrivateKeyParameters) this.b).c(), bArr);
        } else {
            this.a.a(b.b(), this.c);
        }
        BigInteger b2 = this.a.b();
        b2 = b2.modInverse(b.b()).multiply(a.add(((DSAPrivateKeyParameters) this.b).c().multiply(b.c().modPow(b2, b.a()).mod(b.b())))).mod(b.b());
        return new BigInteger[]{r3, b2};
    }

    public boolean a(byte[] bArr, BigInteger bigInteger, BigInteger bigInteger2) {
        DSAParameters b = this.b.b();
        BigInteger a = a(b.b(), bArr);
        BigInteger valueOf = BigInteger.valueOf(0);
        if (valueOf.compareTo(bigInteger) >= 0 || b.b().compareTo(bigInteger) <= 0 || valueOf.compareTo(bigInteger2) >= 0 || b.b().compareTo(bigInteger2) <= 0) {
            return false;
        }
        BigInteger modInverse = bigInteger2.modInverse(b.b());
        return b.c().modPow(a.multiply(modInverse).mod(b.b()), b.a()).multiply(((DSAPublicKeyParameters) this.b).c().modPow(bigInteger.multiply(modInverse).mod(b.b()), b.a())).mod(b.a()).mod(b.b()).equals(bigInteger);
    }

    private BigInteger a(BigInteger bigInteger, byte[] bArr) {
        if (bigInteger.bitLength() >= bArr.length * 8) {
            return new BigInteger(1, bArr);
        }
        Object obj = new byte[(bigInteger.bitLength() / 8)];
        System.arraycopy(bArr, 0, obj, 0, obj.length);
        return new BigInteger(1, obj);
    }
}
