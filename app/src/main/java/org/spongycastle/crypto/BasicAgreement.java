package org.spongycastle.crypto;

import java.math.BigInteger;

public interface BasicAgreement {
    int a();

    void a(CipherParameters cipherParameters);

    BigInteger b(CipherParameters cipherParameters);
}
