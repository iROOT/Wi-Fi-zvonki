package org.spongycastle.crypto.tls;

import java.io.OutputStream;

class TlsOutputStream extends OutputStream {
    private byte[] a;
    private TlsProtocol b;

    public void write(byte[] bArr, int i, int i2) {
        this.b.b(bArr, i, i2);
    }

    public void write(int i) {
        this.a[0] = (byte) i;
        write(this.a, 0, 1);
    }

    public void close() {
        this.b.i();
    }

    public void flush() {
        this.b.j();
    }
}
