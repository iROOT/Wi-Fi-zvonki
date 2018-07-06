package org.spongycastle.crypto.params;

import org.spongycastle.crypto.DerivationParameters;

public class KDFParameters implements DerivationParameters {
    byte[] a;
    byte[] b;

    public KDFParameters(byte[] bArr, byte[] bArr2) {
        this.b = bArr;
        this.a = bArr2;
    }

    public byte[] a() {
        return this.b;
    }

    public byte[] b() {
        return this.a;
    }
}
