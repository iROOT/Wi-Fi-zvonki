package org.spongycastle.crypto.modes.gcm;

public interface GCMExponentiator {
    void a(long j, byte[] bArr);

    void a(byte[] bArr);
}
