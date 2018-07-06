package org.spongycastle.crypto.tls;

import java.io.InputStream;
import java.io.OutputStream;

public class SignatureAndHashAlgorithm {
    protected short a;
    protected short b;

    public SignatureAndHashAlgorithm(short s, short s2) {
        if (!TlsUtils.a(s)) {
            throw new IllegalArgumentException("'hash' should be a uint8");
        } else if (!TlsUtils.a(s2)) {
            throw new IllegalArgumentException("'signature' should be a uint8");
        } else if (s2 == (short) 0) {
            throw new IllegalArgumentException("'signature' MUST NOT be \"anonymous\"");
        } else {
            this.a = s;
            this.b = s2;
        }
    }

    public short a() {
        return this.a;
    }

    public short b() {
        return this.b;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof SignatureAndHashAlgorithm)) {
            return false;
        }
        SignatureAndHashAlgorithm signatureAndHashAlgorithm = (SignatureAndHashAlgorithm) obj;
        if (signatureAndHashAlgorithm.a() == a() && signatureAndHashAlgorithm.b() == b()) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (a() << 16) | b();
    }

    public void a(OutputStream outputStream) {
        TlsUtils.a(this.a, outputStream);
        TlsUtils.a(this.b, outputStream);
    }

    public static SignatureAndHashAlgorithm a(InputStream inputStream) {
        return new SignatureAndHashAlgorithm(TlsUtils.a(inputStream), TlsUtils.a(inputStream));
    }
}
