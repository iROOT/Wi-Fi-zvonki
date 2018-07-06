package org.spongycastle.jcajce.io;

import java.io.OutputStream;
import javax.crypto.Mac;

public class MacOutputStream extends OutputStream {
    protected Mac a;

    public void write(int i) {
        this.a.update((byte) i);
    }

    public void write(byte[] bArr, int i, int i2) {
        this.a.update(bArr, i, i2);
    }
}
