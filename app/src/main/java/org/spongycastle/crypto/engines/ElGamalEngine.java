package org.spongycastle.crypto.engines;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.spongycastle.crypto.AsymmetricBlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.params.ElGamalKeyParameters;
import org.spongycastle.crypto.params.ElGamalPrivateKeyParameters;
import org.spongycastle.crypto.params.ElGamalPublicKeyParameters;
import org.spongycastle.crypto.params.ParametersWithRandom;
import org.spongycastle.util.BigIntegers;

public class ElGamalEngine implements AsymmetricBlockCipher {
    private static final BigInteger e = BigInteger.valueOf(0);
    private static final BigInteger f = BigInteger.valueOf(1);
    private static final BigInteger g = BigInteger.valueOf(2);
    private ElGamalKeyParameters a;
    private SecureRandom b;
    private boolean c;
    private int d;

    public void a(boolean z, CipherParameters cipherParameters) {
        if (cipherParameters instanceof ParametersWithRandom) {
            ParametersWithRandom parametersWithRandom = (ParametersWithRandom) cipherParameters;
            this.a = (ElGamalKeyParameters) parametersWithRandom.b();
            this.b = parametersWithRandom.a();
        } else {
            this.a = (ElGamalKeyParameters) cipherParameters;
            this.b = new SecureRandom();
        }
        this.c = z;
        this.d = this.a.b().a().bitLength();
        if (z) {
            if (!(this.a instanceof ElGamalPublicKeyParameters)) {
                throw new IllegalArgumentException("ElGamalPublicKeyParameters are required for encryption.");
            }
        } else if (!(this.a instanceof ElGamalPrivateKeyParameters)) {
            throw new IllegalArgumentException("ElGamalPrivateKeyParameters are required for decryption.");
        }
    }

    public int a() {
        if (this.c) {
            return (this.d - 1) / 8;
        }
        return ((this.d + 7) / 8) * 2;
    }

    public int b() {
        if (this.c) {
            return ((this.d + 7) / 8) * 2;
        }
        return (this.d - 1) / 8;
    }

    public byte[] a(byte[] bArr, int i, int i2) {
        if (this.a == null) {
            throw new IllegalStateException("ElGamal engine not initialised");
        }
        if (i2 > (this.c ? ((this.d - 1) + 7) / 8 : a())) {
            throw new DataLengthException("input too large for ElGamal cipher.\n");
        }
        BigInteger a = this.a.b().a();
        if (this.a instanceof ElGamalPrivateKeyParameters) {
            Object obj = new byte[(i2 / 2)];
            Object obj2 = new byte[(i2 / 2)];
            System.arraycopy(bArr, i, obj, 0, obj.length);
            System.arraycopy(bArr, obj.length + i, obj2, 0, obj2.length);
            return BigIntegers.a(new BigInteger(1, obj).modPow(a.subtract(f).subtract(((ElGamalPrivateKeyParameters) this.a).c()), a).multiply(new BigInteger(1, obj2)).mod(a));
        }
        if (!(i == 0 && i2 == bArr.length)) {
            obj = new byte[i2];
            System.arraycopy(bArr, i, obj, 0, i2);
            bArr = obj;
        }
        BigInteger bigInteger = new BigInteger(1, bArr);
        if (bigInteger.bitLength() >= a.bitLength()) {
            throw new DataLengthException("input too large for ElGamal cipher.\n");
        }
        ElGamalPublicKeyParameters elGamalPublicKeyParameters = (ElGamalPublicKeyParameters) this.a;
        int bitLength = a.bitLength();
        BigInteger bigInteger2 = new BigInteger(bitLength, this.b);
        while (true) {
            if (!bigInteger2.equals(e) && bigInteger2.compareTo(a.subtract(g)) <= 0) {
                break;
            }
            bigInteger2 = new BigInteger(bitLength, this.b);
        }
        BigInteger modPow = this.a.b().b().modPow(bigInteger2, a);
        BigInteger mod = bigInteger.multiply(elGamalPublicKeyParameters.c().modPow(bigInteger2, a)).mod(a);
        obj2 = modPow.toByteArray();
        Object toByteArray = mod.toByteArray();
        byte[] bArr2 = new byte[b()];
        if (obj2.length > bArr2.length / 2) {
            System.arraycopy(obj2, 1, bArr2, (bArr2.length / 2) - (obj2.length - 1), obj2.length - 1);
        } else {
            System.arraycopy(obj2, 0, bArr2, (bArr2.length / 2) - obj2.length, obj2.length);
        }
        if (toByteArray.length > bArr2.length / 2) {
            System.arraycopy(toByteArray, 1, bArr2, bArr2.length - (toByteArray.length - 1), toByteArray.length - 1);
            return bArr2;
        }
        System.arraycopy(toByteArray, 0, bArr2, bArr2.length - toByteArray.length, toByteArray.length);
        return bArr2;
    }
}
