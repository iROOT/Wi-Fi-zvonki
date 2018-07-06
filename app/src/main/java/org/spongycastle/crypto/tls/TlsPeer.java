package org.spongycastle.crypto.tls;

public interface TlsPeer {
    void a(short s, short s2);

    void a(short s, short s2, String str, Exception exception);

    void a(boolean z);

    TlsCompression c();

    TlsCipher e();
}
