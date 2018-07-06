package org.spongycastle.crypto.tls;

public interface TlsSignerCredentials extends TlsCredentials {
    byte[] a(byte[] bArr);

    SignatureAndHashAlgorithm b_();
}
