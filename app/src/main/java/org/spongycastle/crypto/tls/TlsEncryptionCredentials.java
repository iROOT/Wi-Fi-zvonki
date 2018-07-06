package org.spongycastle.crypto.tls;

public interface TlsEncryptionCredentials extends TlsCredentials {
    byte[] a(byte[] bArr);
}
