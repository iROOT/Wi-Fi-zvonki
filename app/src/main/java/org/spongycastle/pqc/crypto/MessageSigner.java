package org.spongycastle.pqc.crypto;

import org.spongycastle.crypto.CipherParameters;

public interface MessageSigner {
    void a(boolean z, CipherParameters cipherParameters);

    boolean a(byte[] bArr, byte[] bArr2);

    byte[] a(byte[] bArr);
}
