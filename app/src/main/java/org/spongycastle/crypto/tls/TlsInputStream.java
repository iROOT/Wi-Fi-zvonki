package org.spongycastle.crypto.tls;

import java.io.InputStream;

class TlsInputStream extends InputStream {
    private byte[] a;
    private TlsProtocol b;

    public int read(byte[] bArr, int i, int i2) {
        return this.b.a(bArr, i, i2);
    }

    public int read() {
        if (read(this.a) < 0) {
            return -1;
        }
        return this.a[0] & 255;
    }

    public void close() {
        this.b.i();
    }
}
