package org.spongycastle.crypto.params;

import org.spongycastle.crypto.CipherParameters;

public class RC2Parameters implements CipherParameters {
    private byte[] a;
    private int b;

    public RC2Parameters(byte[] bArr, int i) {
        this.a = new byte[bArr.length];
        this.b = i;
        System.arraycopy(bArr, 0, this.a, 0, bArr.length);
    }

    public byte[] a() {
        return this.a;
    }

    public int b() {
        return this.b;
    }
}
