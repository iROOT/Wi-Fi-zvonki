package org.spongycastle.crypto.signers;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DSA;
import org.spongycastle.crypto.params.ECKeyParameters;
import org.spongycastle.crypto.params.ECPrivateKeyParameters;
import org.spongycastle.crypto.params.ECPublicKeyParameters;
import org.spongycastle.crypto.params.ParametersWithRandom;
import org.spongycastle.math.ec.ECAlgorithms;
import org.spongycastle.math.ec.ECConstants;
import org.spongycastle.math.ec.ECPoint;

public class ECGOST3410Signer implements DSA {
    ECKeyParameters a;
    SecureRandom b;

    public void a(boolean z, CipherParameters cipherParameters) {
        if (!z) {
            this.a = (ECPublicKeyParameters) cipherParameters;
        } else if (cipherParameters instanceof ParametersWithRandom) {
            ParametersWithRandom parametersWithRandom = (ParametersWithRandom) cipherParameters;
            this.b = parametersWithRandom.a();
            this.a = (ECPrivateKeyParameters) parametersWithRandom.b();
        } else {
            this.b = new SecureRandom();
            this.a = (ECPrivateKeyParameters) cipherParameters;
        }
    }

    public BigInteger[] a(byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length];
        for (int i = 0; i != bArr2.length; i++) {
            bArr2[i] = bArr[(bArr2.length - 1) - i];
        }
        BigInteger bigInteger = new BigInteger(1, bArr2);
        BigInteger c = this.a.b().c();
        while (true) {
            BigInteger bigInteger2 = new BigInteger(c.bitLength(), this.b);
            if (!bigInteger2.equals(ECConstants.c)) {
                BigInteger mod = this.a.b().b().a(bigInteger2).k().c().a().mod(c);
                if (mod.equals(ECConstants.c)) {
                    continue;
                } else {
                    if (!bigInteger2.multiply(bigInteger).add(((ECPrivateKeyParameters) this.a).c().multiply(mod)).mod(c).equals(ECConstants.c)) {
                        return new BigInteger[]{mod, bigInteger2.multiply(bigInteger).add(((ECPrivateKeyParameters) this.a).c().multiply(mod)).mod(c)};
                    }
                }
            }
        }
    }

    public boolean a(byte[] bArr, BigInteger bigInteger, BigInteger bigInteger2) {
        byte[] bArr2 = new byte[bArr.length];
        for (int i = 0; i != bArr2.length; i++) {
            bArr2[i] = bArr[(bArr2.length - 1) - i];
        }
        BigInteger bigInteger3 = new BigInteger(1, bArr2);
        BigInteger c = this.a.b().c();
        if (bigInteger.compareTo(ECConstants.d) < 0 || bigInteger.compareTo(c) >= 0 || bigInteger2.compareTo(ECConstants.d) < 0 || bigInteger2.compareTo(c) >= 0) {
            return false;
        }
        bigInteger3 = bigInteger3.modInverse(c);
        ECPoint k = ECAlgorithms.a(this.a.b().b(), bigInteger2.multiply(bigInteger3).mod(c), ((ECPublicKeyParameters) this.a).c(), c.subtract(bigInteger).multiply(bigInteger3).mod(c)).k();
        if (k.l()) {
            return false;
        }
        return k.c().a().mod(c).equals(bigInteger);
    }
}
