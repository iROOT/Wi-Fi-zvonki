package org.spongycastle.util.test;

import java.io.FilterOutputStream;

public class UncloseableOutputStream extends FilterOutputStream {
    public void close() {
        throw new RuntimeException("close() called on UncloseableOutputStream");
    }

    public void write(byte[] bArr, int i, int i2) {
        this.out.write(bArr, i, i2);
    }
}
