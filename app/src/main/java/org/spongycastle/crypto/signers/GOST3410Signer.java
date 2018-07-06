package org.spongycastle.crypto.signers;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DSA;
import org.spongycastle.crypto.params.GOST3410KeyParameters;
import org.spongycastle.crypto.params.GOST3410Parameters;
import org.spongycastle.crypto.params.GOST3410PrivateKeyParameters;
import org.spongycastle.crypto.params.GOST3410PublicKeyParameters;
import org.spongycastle.crypto.params.ParametersWithRandom;

public class GOST3410Signer implements DSA {
    GOST3410KeyParameters a;
    SecureRandom b;

    public void a(boolean z, CipherParameters cipherParameters) {
        if (!z) {
            this.a = (GOST3410PublicKeyParameters) cipherParameters;
        } else if (cipherParameters instanceof ParametersWithRandom) {
            ParametersWithRandom parametersWithRandom = (ParametersWithRandom) cipherParameters;
            this.b = parametersWithRandom.a();
            this.a = (GOST3410PrivateKeyParameters) parametersWithRandom.b();
        } else {
            this.b = new SecureRandom();
            this.a = (GOST3410PrivateKeyParameters) cipherParameters;
        }
    }

    public BigInteger[] a(byte[] bArr) {
        BigInteger bigInteger;
        byte[] bArr2 = new byte[bArr.length];
        for (int i = 0; i != bArr2.length; i++) {
            bArr2[i] = bArr[(bArr2.length - 1) - i];
        }
        BigInteger bigInteger2 = new BigInteger(1, bArr2);
        GOST3410Parameters b = this.a.b();
        do {
            bigInteger = new BigInteger(b.b().bitLength(), this.b);
        } while (bigInteger.compareTo(b.b()) >= 0);
        bigInteger2 = bigInteger.multiply(bigInteger2).add(((GOST3410PrivateKeyParameters) this.a).c().multiply(b.c().modPow(bigInteger, b.a()).mod(b.b()))).mod(b.b());
        return new BigInteger[]{r4, bigInteger2};
    }

    public boolean a(byte[] bArr, BigInteger bigInteger, BigInteger bigInteger2) {
        byte[] bArr2 = new byte[bArr.length];
        for (int i = 0; i != bArr2.length; i++) {
            bArr2[i] = bArr[(bArr2.length - 1) - i];
        }
        BigInteger bigInteger3 = new BigInteger(1, bArr2);
        GOST3410Parameters b = this.a.b();
        BigInteger valueOf = BigInteger.valueOf(0);
        if (valueOf.compareTo(bigInteger) >= 0 || b.b().compareTo(bigInteger) <= 0 || valueOf.compareTo(bigInteger2) >= 0 || b.b().compareTo(bigInteger2) <= 0) {
            return false;
        }
        bigInteger3 = bigInteger3.modPow(b.b().subtract(new BigInteger("2")), b.b());
        return b.c().modPow(bigInteger2.multiply(bigInteger3).mod(b.b()), b.a()).multiply(((GOST3410PublicKeyParameters) this.a).c().modPow(b.b().subtract(bigInteger).multiply(bigInteger3).mod(b.b()), b.a())).mod(b.a()).mod(b.b()).equals(bigInteger);
    }
}
