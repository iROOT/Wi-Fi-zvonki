package org.spongycastle.crypto.io;

import java.io.FilterInputStream;
import java.io.InputStream;
import org.spongycastle.crypto.Digest;

public class DigestInputStream extends FilterInputStream {
    protected Digest a;

    public DigestInputStream(InputStream inputStream, Digest digest) {
        super(inputStream);
        this.a = digest;
    }

    public int read() {
        int read = this.in.read();
        if (read >= 0) {
            this.a.a((byte) read);
        }
        return read;
    }

    public int read(byte[] bArr, int i, int i2) {
        int read = this.in.read(bArr, i, i2);
        if (read > 0) {
            this.a.a(bArr, i, read);
        }
        return read;
    }
}
