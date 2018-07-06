package org.spongycastle.crypto.io;

import java.io.OutputStream;
import org.spongycastle.crypto.Mac;

public class MacOutputStream extends OutputStream {
    protected Mac a;

    public MacOutputStream(Mac mac) {
        this.a = mac;
    }

    public void write(int i) {
        this.a.a((byte) i);
    }

    public void write(byte[] bArr, int i, int i2) {
        this.a.a(bArr, i, i2);
    }
}
