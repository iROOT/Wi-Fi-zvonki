package org.spongycastle.crypto.params;

public class IESWithCipherParameters extends IESParameters {
    private int a;

    public IESWithCipherParameters(byte[] bArr, byte[] bArr2, int i, int i2) {
        super(bArr, bArr2, i);
        this.a = i2;
    }

    public int d() {
        return this.a;
    }
}
