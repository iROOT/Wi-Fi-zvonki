package org.spongycastle.crypto.engines;

import java.math.BigInteger;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.params.ParametersWithRandom;
import org.spongycastle.crypto.params.RSAKeyParameters;
import org.spongycastle.crypto.params.RSAPrivateCrtKeyParameters;

class RSACoreEngine {
    private RSAKeyParameters a;
    private boolean b;

    RSACoreEngine() {
    }

    public void a(boolean z, CipherParameters cipherParameters) {
        if (cipherParameters instanceof ParametersWithRandom) {
            this.a = (RSAKeyParameters) ((ParametersWithRandom) cipherParameters).b();
        } else {
            this.a = (RSAKeyParameters) cipherParameters;
        }
        this.b = z;
    }

    public int a() {
        int bitLength = this.a.b().bitLength();
        if (this.b) {
            return ((bitLength + 7) / 8) - 1;
        }
        return (bitLength + 7) / 8;
    }

    public int b() {
        int bitLength = this.a.b().bitLength();
        if (this.b) {
            return (bitLength + 7) / 8;
        }
        return ((bitLength + 7) / 8) - 1;
    }

    public BigInteger a(byte[] bArr, int i, int i2) {
        if (i2 > a() + 1) {
            throw new DataLengthException("input too large for RSA cipher.");
        } else if (i2 != a() + 1 || this.b) {
            if (!(i == 0 && i2 == bArr.length)) {
                Object obj = new byte[i2];
                System.arraycopy(bArr, i, obj, 0, i2);
                bArr = obj;
            }
            BigInteger bigInteger = new BigInteger(1, bArr);
            if (bigInteger.compareTo(this.a.b()) < 0) {
                return bigInteger;
            }
            throw new DataLengthException("input too large for RSA cipher.");
        } else {
            throw new DataLengthException("input too large for RSA cipher.");
        }
    }

    public byte[] a(BigInteger bigInteger) {
        Object toByteArray = bigInteger.toByteArray();
        byte[] bArr;
        if (this.b) {
            if (toByteArray[0] == (byte) 0 && toByteArray.length > b()) {
                Object obj = new byte[(toByteArray.length - 1)];
                System.arraycopy(toByteArray, 1, obj, 0, obj.length);
                return obj;
            } else if (toByteArray.length < b()) {
                bArr = new byte[b()];
                System.arraycopy(toByteArray, 0, bArr, bArr.length - toByteArray.length, toByteArray.length);
                return bArr;
            }
        } else if (toByteArray[0] == (byte) 0) {
            bArr = new byte[(toByteArray.length - 1)];
            System.arraycopy(toByteArray, 1, bArr, 0, bArr.length);
            return bArr;
        }
        return toByteArray;
    }

    public BigInteger b(BigInteger bigInteger) {
        if (!(this.a instanceof RSAPrivateCrtKeyParameters)) {
            return bigInteger.modPow(this.a.c(), this.a.b());
        }
        RSAPrivateCrtKeyParameters rSAPrivateCrtKeyParameters = (RSAPrivateCrtKeyParameters) this.a;
        BigInteger e = rSAPrivateCrtKeyParameters.e();
        BigInteger f = rSAPrivateCrtKeyParameters.f();
        BigInteger g = rSAPrivateCrtKeyParameters.g();
        BigInteger h = rSAPrivateCrtKeyParameters.h();
        BigInteger i = rSAPrivateCrtKeyParameters.i();
        g = bigInteger.remainder(e).modPow(g, e);
        h = bigInteger.remainder(f).modPow(h, f);
        return g.subtract(h).multiply(i).mod(e).multiply(f).add(h);
    }
}
