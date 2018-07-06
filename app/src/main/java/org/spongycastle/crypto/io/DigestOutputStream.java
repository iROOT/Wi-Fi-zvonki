package org.spongycastle.crypto.io;

import java.io.OutputStream;
import org.spongycastle.crypto.Digest;

public class DigestOutputStream extends OutputStream {
    protected Digest a;

    public DigestOutputStream(Digest digest) {
        this.a = digest;
    }

    public void write(int i) {
        this.a.a((byte) i);
    }

    public void write(byte[] bArr, int i, int i2) {
        this.a.a(bArr, i, i2);
    }

    public byte[] a() {
        byte[] bArr = new byte[this.a.b()];
        this.a.a(bArr, 0);
        return bArr;
    }
}
