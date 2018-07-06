package org.spongycastle.crypto.io;

import java.io.OutputStream;
import org.spongycastle.crypto.Signer;

public class SignerOutputStream extends OutputStream {
    protected Signer a;

    public void write(int i) {
        this.a.a((byte) i);
    }

    public void write(byte[] bArr, int i, int i2) {
        this.a.a(bArr, i, i2);
    }
}
