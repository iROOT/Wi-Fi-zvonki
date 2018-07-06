package org.spongycastle.crypto.params;

import org.spongycastle.crypto.CipherParameters;

public class RC5Parameters implements CipherParameters {
    private byte[] a;
    private int b;

    public RC5Parameters(byte[] bArr, int i) {
        if (bArr.length > 255) {
            throw new IllegalArgumentException("RC5 key length can be no greater than 255");
        }
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
