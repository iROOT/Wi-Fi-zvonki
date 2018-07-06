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

public class ECDSASigner implements DSA, ECConstants {
    private final DSAKCalculator a;
    private ECKeyParameters b;
    private SecureRandom h;

    public ECDSASigner() {
        this.a = new RandomDSAKCalculator();
    }

    public ECDSASigner(DSAKCalculator dSAKCalculator) {
        this.a = dSAKCalculator;
    }

    public void a(boolean z, CipherParameters cipherParameters) {
        if (!z) {
            this.b = (ECPublicKeyParameters) cipherParameters;
        } else if (cipherParameters instanceof ParametersWithRandom) {
            ParametersWithRandom parametersWithRandom = (ParametersWithRandom) cipherParameters;
            this.h = parametersWithRandom.a();
            this.b = (ECPrivateKeyParameters) parametersWithRandom.b();
        } else {
            this.h = new SecureRandom();
            this.b = (ECPrivateKeyParameters) cipherParameters;
        }
    }

    public BigInteger[] a(byte[] bArr) {
        BigInteger c = this.b.b().c();
        BigInteger a = a(c, bArr);
        if (this.a.a()) {
            this.a.a(c, ((ECPrivateKeyParameters) this.b).c(), bArr);
        } else {
            this.a.a(c, this.h);
        }
        while (true) {
            BigInteger b = this.a.b();
            BigInteger mod = this.b.b().b().a(b).k().c().a().mod(c);
            if (!mod.equals(c)) {
                if (!b.modInverse(c).multiply(a.add(((ECPrivateKeyParameters) this.b).c().multiply(mod))).mod(c).equals(c)) {
                    return new BigInteger[]{mod, b.modInverse(c).multiply(a.add(((ECPrivateKeyParameters) this.b).c().multiply(mod))).mod(c)};
                }
            }
        }
    }

    public boolean a(byte[] bArr, BigInteger bigInteger, BigInteger bigInteger2) {
        BigInteger c = this.b.b().c();
        BigInteger a = a(c, bArr);
        if (bigInteger.compareTo(d) < 0 || bigInteger.compareTo(c) >= 0) {
            return false;
        }
        if (bigInteger2.compareTo(d) < 0 || bigInteger2.compareTo(c) >= 0) {
            return false;
        }
        BigInteger modInverse = bigInteger2.modInverse(c);
        ECPoint k = ECAlgorithms.a(this.b.b().b(), a.multiply(modInverse).mod(c), ((ECPublicKeyParameters) this.b).c(), bigInteger.multiply(modInverse).mod(c)).k();
        if (k.l()) {
            return false;
        }
        return k.c().a().mod(c).equals(bigInteger);
    }

    private BigInteger a(BigInteger bigInteger, byte[] bArr) {
        int bitLength = bigInteger.bitLength();
        int length = bArr.length * 8;
        BigInteger bigInteger2 = new BigInteger(1, bArr);
        if (bitLength < length) {
            return bigInteger2.shiftRight(length - bitLength);
        }
        return bigInteger2;
    }
}
