package org.spongycastle.util.io;

import java.io.OutputStream;

public class TeeOutputStream extends OutputStream {
    private OutputStream a;
    private OutputStream b;

    public TeeOutputStream(OutputStream outputStream, OutputStream outputStream2) {
        this.a = outputStream;
        this.b = outputStream2;
    }

    public void write(byte[] bArr) {
        this.a.write(bArr);
        this.b.write(bArr);
    }

    public void write(byte[] bArr, int i, int i2) {
        this.a.write(bArr, i, i2);
        this.b.write(bArr, i, i2);
    }

    public void write(int i) {
        this.a.write(i);
        this.b.write(i);
    }

    public void flush() {
        this.a.flush();
        this.b.flush();
    }

    public void close() {
        this.a.close();
        this.b.close();
    }
}
