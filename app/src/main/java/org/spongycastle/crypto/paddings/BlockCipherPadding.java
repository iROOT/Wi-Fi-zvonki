package org.spongycastle.crypto.paddings;

import java.security.SecureRandom;

public interface BlockCipherPadding {
    int a(byte[] bArr);

    int a(byte[] bArr, int i);

    void a(SecureRandom secureRandom);
}
