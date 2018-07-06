package org.spongycastle.crypto;

import java.math.BigInteger;

public interface DSA {
    void a(boolean z, CipherParameters cipherParameters);

    boolean a(byte[] bArr, BigInteger bigInteger, BigInteger bigInteger2);

    BigInteger[] a(byte[] bArr);
}
