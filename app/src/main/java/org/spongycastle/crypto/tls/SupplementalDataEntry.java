package org.spongycastle.crypto.tls;

public class SupplementalDataEntry {
    protected int a;
    protected byte[] b;

    public SupplementalDataEntry(int i, byte[] bArr) {
        this.a = i;
        this.b = bArr;
    }

    public int a() {
        return this.a;
    }

    public byte[] b() {
        return this.b;
    }
}
