package org.spongycastle.crypto.agreement.srp;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.spongycastle.crypto.CryptoException;
import org.spongycastle.crypto.Digest;
import org.spongycastle.util.BigIntegers;

public class SRP6Util {
    private static BigInteger a = BigInteger.valueOf(0);
    private static BigInteger b = BigInteger.valueOf(1);

    public static BigInteger a(Digest digest, BigInteger bigInteger, BigInteger bigInteger2) {
        return b(digest, bigInteger, bigInteger, bigInteger2);
    }

    public static BigInteger a(Digest digest, BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
        return b(digest, bigInteger, bigInteger2, bigInteger3);
    }

    public static BigInteger a(Digest digest, BigInteger bigInteger, byte[] bArr, byte[] bArr2, byte[] bArr3) {
        byte[] bArr4 = new byte[digest.b()];
        digest.a(bArr2, 0, bArr2.length);
        digest.a((byte) 58);
        digest.a(bArr3, 0, bArr3.length);
        digest.a(bArr4, 0);
        digest.a(bArr, 0, bArr.length);
        digest.a(bArr4, 0, bArr4.length);
        digest.a(bArr4, 0);
        return new BigInteger(1, bArr4);
    }

    public static BigInteger a(Digest digest, BigInteger bigInteger, BigInteger bigInteger2, SecureRandom secureRandom) {
        return BigIntegers.a(b.shiftLeft(Math.min(256, bigInteger.bitLength() / 2) - 1), bigInteger.subtract(b), secureRandom);
    }

    public static BigInteger a(BigInteger bigInteger, BigInteger bigInteger2) {
        BigInteger mod = bigInteger2.mod(bigInteger);
        if (!mod.equals(a)) {
            return mod;
        }
        throw new CryptoException("Invalid public value: 0");
    }

    private static BigInteger b(Digest digest, BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
        int bitLength = (bigInteger.bitLength() + 7) / 8;
        byte[] a = a(bigInteger2, bitLength);
        byte[] a2 = a(bigInteger3, bitLength);
        digest.a(a, 0, a.length);
        digest.a(a2, 0, a2.length);
        a2 = new byte[digest.b()];
        digest.a(a2, 0);
        return new BigInteger(1, a2);
    }

    private static byte[] a(BigInteger bigInteger, int i) {
        Object a = BigIntegers.a(bigInteger);
        if (a.length >= i) {
            return a;
        }
        Object obj = new byte[i];
        System.arraycopy(a, 0, obj, i - a.length, a.length);
        return obj;
    }
}
