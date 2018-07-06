package org.spongycastle.crypto.tls;

import org.spongycastle.util.Arrays;

class TlsSessionImpl implements TlsSession {
    final byte[] a;
    SessionParameters b;

    TlsSessionImpl(byte[] bArr, SessionParameters sessionParameters) {
        if (bArr == null) {
            throw new IllegalArgumentException("'sessionID' cannot be null");
        } else if (bArr.length < 1 || bArr.length > 32) {
            throw new IllegalArgumentException("'sessionID' must have length between 1 and 32 bytes, inclusive");
        } else {
            this.a = Arrays.b(bArr);
            this.b = sessionParameters;
        }
    }

    public synchronized byte[] a() {
        return this.a;
    }

    public synchronized void b() {
        if (this.b != null) {
            this.b.a();
            this.b = null;
        }
    }
}
