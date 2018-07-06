package org.spongycastle.crypto.tls;

import java.io.InputStream;
import java.io.OutputStream;

public class DigitallySigned {
    protected SignatureAndHashAlgorithm a;
    protected byte[] b;

    public DigitallySigned(SignatureAndHashAlgorithm signatureAndHashAlgorithm, byte[] bArr) {
        if (bArr == null) {
            throw new IllegalArgumentException("'signature' cannot be null");
        }
        this.a = signatureAndHashAlgorithm;
        this.b = bArr;
    }

    public SignatureAndHashAlgorithm a() {
        return this.a;
    }

    public byte[] b() {
        return this.b;
    }

    public void a(OutputStream outputStream) {
        if (this.a != null) {
            this.a.a(outputStream);
        }
        TlsUtils.b(this.b, outputStream);
    }

    public static DigitallySigned a(TlsContext tlsContext, InputStream inputStream) {
        SignatureAndHashAlgorithm signatureAndHashAlgorithm = null;
        if (TlsUtils.c(tlsContext)) {
            signatureAndHashAlgorithm = SignatureAndHashAlgorithm.a(inputStream);
        }
        return new DigitallySigned(signatureAndHashAlgorithm, TlsUtils.f(inputStream));
    }
}
