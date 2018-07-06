package org.spongycastle.crypto.params;

import org.spongycastle.crypto.CipherParameters;

public class ParametersWithSBox implements CipherParameters {
    private CipherParameters a;
    private byte[] b;

    public ParametersWithSBox(CipherParameters cipherParameters, byte[] bArr) {
        this.a = cipherParameters;
        this.b = bArr;
    }

    public byte[] a() {
        return this.b;
    }

    public CipherParameters b() {
        return this.a;
    }
}
