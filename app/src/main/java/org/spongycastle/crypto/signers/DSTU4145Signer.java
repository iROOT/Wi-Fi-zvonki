package org.spongycastle.crypto.signers;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DSA;
import org.spongycastle.crypto.params.ECDomainParameters;
import org.spongycastle.crypto.params.ECKeyParameters;
import org.spongycastle.crypto.params.ECPrivateKeyParameters;
import org.spongycastle.crypto.params.ECPublicKeyParameters;
import org.spongycastle.crypto.params.ParametersWithRandom;
import org.spongycastle.math.ec.ECAlgorithms;
import org.spongycastle.math.ec.ECCurve;
import org.spongycastle.math.ec.ECFieldElement;
import org.spongycastle.math.ec.ECPoint;
import org.spongycastle.util.Arrays;

public class DSTU4145Signer implements DSA {
    private static final BigInteger a = BigInteger.valueOf(1);
    private ECKeyParameters b;
    private SecureRandom c;

    public void a(boolean z, CipherParameters cipherParameters) {
        if (z) {
            CipherParameters b;
            if (cipherParameters instanceof ParametersWithRandom) {
                ParametersWithRandom parametersWithRandom = (ParametersWithRandom) cipherParameters;
                this.c = parametersWithRandom.a();
                b = parametersWithRandom.b();
            } else {
                this.c = new SecureRandom();
                b = cipherParameters;
            }
            this.b = (ECPrivateKeyParameters) b;
            return;
        }
        this.b = (ECPublicKeyParameters) cipherParameters;
    }

    public BigInteger[] a(byte[] bArr) {
        ECFieldElement a;
        ECDomainParameters b = this.b.b();
        ECCurve a2 = b.a();
        ECFieldElement a3 = a(a2, bArr);
        if (a3.i()) {
            a = a2.a(a);
        } else {
            a = a3;
        }
        BigInteger c = b.c();
        while (true) {
            BigInteger a4 = a(c, this.c);
            a3 = b.b().a(a4).k().c();
            if (!a3.i()) {
                BigInteger a5 = a(c, a.c(a3));
                if (a5.signum() != 0) {
                    if (a5.multiply(((ECPrivateKeyParameters) this.b).c()).add(a4).mod(c).signum() != 0) {
                        return new BigInteger[]{a5, a5.multiply(((ECPrivateKeyParameters) this.b).c()).add(a4).mod(c)};
                    }
                } else {
                    continue;
                }
            }
        }
    }

    public boolean a(byte[] bArr, BigInteger bigInteger, BigInteger bigInteger2) {
        if (bigInteger.signum() <= 0 || bigInteger2.signum() <= 0) {
            return false;
        }
        ECDomainParameters b = this.b.b();
        BigInteger c = b.c();
        if (bigInteger.compareTo(c) >= 0 || bigInteger2.compareTo(c) >= 0) {
            return false;
        }
        ECFieldElement a;
        ECCurve a2 = b.a();
        ECFieldElement a3 = a(a2, bArr);
        if (a3.i()) {
            a = a2.a(a);
        } else {
            a = a3;
        }
        ECPoint k = ECAlgorithms.a(b.b(), bigInteger2, ((ECPublicKeyParameters) this.b).c(), bigInteger).k();
        if (k.l()) {
            return false;
        }
        return a(c, a.c(k.c())).compareTo(bigInteger) == 0;
    }

    private static BigInteger a(BigInteger bigInteger, SecureRandom secureRandom) {
        return new BigInteger(bigInteger.bitLength() - 1, secureRandom);
    }

    private static void b(byte[] bArr) {
        for (int i = 0; i < bArr.length / 2; i++) {
            byte b = bArr[i];
            bArr[i] = bArr[(bArr.length - 1) - i];
            bArr[(bArr.length - 1) - i] = b;
        }
    }

    private static ECFieldElement a(ECCurve eCCurve, byte[] bArr) {
        byte[] b = Arrays.b(bArr);
        b(b);
        BigInteger bigInteger = new BigInteger(1, b);
        while (bigInteger.bitLength() > eCCurve.a()) {
            bigInteger = bigInteger.clearBit(bigInteger.bitLength() - 1);
        }
        return eCCurve.a(bigInteger);
    }

    private static BigInteger a(BigInteger bigInteger, ECFieldElement eCFieldElement) {
        BigInteger a = eCFieldElement.a();
        while (a.bitLength() >= bigInteger.bitLength()) {
            a = a.clearBit(a.bitLength() - 1);
        }
        return a;
    }
}
