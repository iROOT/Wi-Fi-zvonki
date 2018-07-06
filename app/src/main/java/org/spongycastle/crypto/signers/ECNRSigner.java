package org.spongycastle.crypto.signers;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.spongycastle.crypto.AsymmetricCipherKeyPair;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DSA;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.generators.ECKeyPairGenerator;
import org.spongycastle.crypto.params.ECKeyGenerationParameters;
import org.spongycastle.crypto.params.ECKeyParameters;
import org.spongycastle.crypto.params.ECPrivateKeyParameters;
import org.spongycastle.crypto.params.ECPublicKeyParameters;
import org.spongycastle.crypto.params.ParametersWithRandom;
import org.spongycastle.math.ec.ECAlgorithms;
import org.spongycastle.math.ec.ECConstants;
import org.spongycastle.math.ec.ECPoint;

public class ECNRSigner implements DSA {
    private boolean a;
    private ECKeyParameters b;
    private SecureRandom c;

    public void a(boolean z, CipherParameters cipherParameters) {
        this.a = z;
        if (!z) {
            this.b = (ECPublicKeyParameters) cipherParameters;
        } else if (cipherParameters instanceof ParametersWithRandom) {
            ParametersWithRandom parametersWithRandom = (ParametersWithRandom) cipherParameters;
            this.c = parametersWithRandom.a();
            this.b = (ECPrivateKeyParameters) parametersWithRandom.b();
        } else {
            this.c = new SecureRandom();
            this.b = (ECPrivateKeyParameters) cipherParameters;
        }
    }

    public BigInteger[] a(byte[] bArr) {
        if (this.a) {
            BigInteger c = ((ECPrivateKeyParameters) this.b).b().c();
            int bitLength = c.bitLength();
            BigInteger bigInteger = new BigInteger(1, bArr);
            ECPrivateKeyParameters eCPrivateKeyParameters = (ECPrivateKeyParameters) this.b;
            if (bigInteger.bitLength() > bitLength) {
                throw new DataLengthException("input too large for ECNR key.");
            }
            BigInteger mod;
            AsymmetricCipherKeyPair a;
            do {
                ECKeyPairGenerator eCKeyPairGenerator = new ECKeyPairGenerator();
                eCKeyPairGenerator.a(new ECKeyGenerationParameters(eCPrivateKeyParameters.b(), this.c));
                a = eCKeyPairGenerator.a();
                mod = ((ECPublicKeyParameters) a.a()).c().k().c().a().add(bigInteger).mod(c);
            } while (mod.equals(ECConstants.c));
            BigInteger mod2 = ((ECPrivateKeyParameters) a.b()).c().subtract(mod.multiply(eCPrivateKeyParameters.c())).mod(c);
            return new BigInteger[]{mod, mod2};
        }
        throw new IllegalStateException("not initialised for signing");
    }

    public boolean a(byte[] bArr, BigInteger bigInteger, BigInteger bigInteger2) {
        if (this.a) {
            throw new IllegalStateException("not initialised for verifying");
        }
        ECPublicKeyParameters eCPublicKeyParameters = (ECPublicKeyParameters) this.b;
        BigInteger c = eCPublicKeyParameters.b().c();
        int bitLength = c.bitLength();
        BigInteger bigInteger3 = new BigInteger(1, bArr);
        if (bigInteger3.bitLength() > bitLength) {
            throw new DataLengthException("input too large for ECNR key.");
        } else if (bigInteger.compareTo(ECConstants.d) < 0 || bigInteger.compareTo(c) >= 0) {
            return false;
        } else {
            if (bigInteger2.compareTo(ECConstants.c) < 0 || bigInteger2.compareTo(c) >= 0) {
                return false;
            }
            ECPoint k = ECAlgorithms.a(eCPublicKeyParameters.b().b(), bigInteger2, eCPublicKeyParameters.c(), bigInteger).k();
            if (k.l()) {
                return false;
            }
            return bigInteger.subtract(k.c().a()).mod(c).equals(bigInteger3);
        }
    }
}
