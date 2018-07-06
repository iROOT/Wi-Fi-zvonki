package org.spongycastle.crypto.params;

import org.spongycastle.crypto.CipherParameters;

public class IESParameters implements CipherParameters {
    private byte[] a;
    private byte[] b;
    private int c;

    public IESParameters(byte[] bArr, byte[] bArr2, int i) {
        this.a = bArr;
        this.b = bArr2;
        this.c = i;
    }

    public byte[] a() {
        return this.a;
    }

    public byte[] b() {
        return this.b;
    }

    public int c() {
        return this.c;
    }
}
