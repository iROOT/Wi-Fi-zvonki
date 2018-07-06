package org.spongycastle.crypto.params;

import org.spongycastle.crypto.CipherParameters;

public class AEADParameters implements CipherParameters {
    private byte[] a;
    private byte[] b;
    private KeyParameter c;
    private int d;

    public AEADParameters(KeyParameter keyParameter, int i, byte[] bArr) {
        this(keyParameter, i, bArr, null);
    }

    public AEADParameters(KeyParameter keyParameter, int i, byte[] bArr, byte[] bArr2) {
        this.c = keyParameter;
        this.b = bArr;
        this.d = i;
        this.a = bArr2;
    }

    public KeyParameter a() {
        return this.c;
    }

    public int b() {
        return this.d;
    }

    public byte[] c() {
        return this.a;
    }

    public byte[] d() {
        return this.b;
    }
}
