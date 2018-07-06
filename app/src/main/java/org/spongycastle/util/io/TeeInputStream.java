package org.spongycastle.util.io;

import java.io.InputStream;
import java.io.OutputStream;

public class TeeInputStream extends InputStream {
    private final InputStream a;
    private final OutputStream b;

    public TeeInputStream(InputStream inputStream, OutputStream outputStream) {
        this.a = inputStream;
        this.b = outputStream;
    }

    public int read(byte[] bArr) {
        return read(bArr, 0, bArr.length);
    }

    public int read(byte[] bArr, int i, int i2) {
        int read = this.a.read(bArr, i, i2);
        if (read > 0) {
            this.b.write(bArr, i, read);
        }
        return read;
    }

    public int read() {
        int read = this.a.read();
        if (read >= 0) {
            this.b.write(read);
        }
        return read;
    }

    public void close() {
        this.a.close();
        this.b.close();
    }
}
