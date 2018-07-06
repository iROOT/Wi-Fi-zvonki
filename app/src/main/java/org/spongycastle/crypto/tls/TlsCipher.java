package org.spongycastle.crypto.tls;

public interface TlsCipher {
    byte[] a(long j, short s, byte[] bArr, int i, int i2);

    byte[] b(long j, short s, byte[] bArr, int i, int i2);
}
