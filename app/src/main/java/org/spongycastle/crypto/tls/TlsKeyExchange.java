package org.spongycastle.crypto.tls;

import java.io.InputStream;
import java.io.OutputStream;

public interface TlsKeyExchange {
    void a(InputStream inputStream);

    void a(OutputStream outputStream);

    void a(Certificate certificate);

    void a(CertificateRequest certificateRequest);

    void a(TlsContext tlsContext);

    void a(TlsCredentials tlsCredentials);

    void b(InputStream inputStream);

    void b(Certificate certificate);

    void b(TlsCredentials tlsCredentials);

    byte[] b();

    void c();

    void d();

    void e();

    byte[] f();
}
