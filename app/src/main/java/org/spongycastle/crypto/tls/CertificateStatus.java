package org.spongycastle.crypto.tls;

import java.io.InputStream;
import java.io.OutputStream;
import org.spongycastle.asn1.ocsp.OCSPResponse;

public class CertificateStatus {
    protected short a;
    protected Object b;

    public CertificateStatus(short s, Object obj) {
        if (a(s, obj)) {
            this.a = s;
            this.b = obj;
            return;
        }
        throw new IllegalArgumentException("'response' is not an instance of the correct type");
    }

    public void a(OutputStream outputStream) {
        TlsUtils.a(this.a, outputStream);
        switch (this.a) {
            case (short) 1:
                TlsUtils.c(((OCSPResponse) this.b).a("DER"), outputStream);
                return;
            default:
                throw new TlsFatalAlert((short) 80);
        }
    }

    public static CertificateStatus a(InputStream inputStream) {
        short a = TlsUtils.a(inputStream);
        switch (a) {
            case (short) 1:
                return new CertificateStatus(a, OCSPResponse.a(TlsUtils.c(TlsUtils.g(inputStream))));
            default:
                throw new TlsFatalAlert((short) 50);
        }
    }

    protected static boolean a(short s, Object obj) {
        switch (s) {
            case (short) 1:
                return obj instanceof OCSPResponse;
            default:
                throw new IllegalArgumentException("'statusType' is an unsupported value");
        }
    }
}
