package org.spongycastle.crypto.modes;

import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.CipherParameters;

public interface AEADBlockCipher {
    int a(int i);

    int a(byte[] bArr, int i);

    int a(byte[] bArr, int i, int i2, byte[] bArr2, int i3);

    BlockCipher a();

    void a(boolean z, CipherParameters cipherParameters);

    void a(byte[] bArr, int i, int i2);

    int b(int i);
}
