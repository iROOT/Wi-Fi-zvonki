package org.spongycastle.crypto.digests;

import org.spongycastle.crypto.ExtendedDigest;

public class ShortenedDigest implements ExtendedDigest {
    private ExtendedDigest a;
    private int b;

    public String a() {
        return this.a.a() + "(" + (this.b * 8) + ")";
    }

    public int b() {
        return this.b;
    }

    public void a(byte b) {
        this.a.a(b);
    }

    public void a(byte[] bArr, int i, int i2) {
        this.a.a(bArr, i, i2);
    }

    public int a(byte[] bArr, int i) {
        Object obj = new byte[this.a.b()];
        this.a.a(obj, 0);
        System.arraycopy(obj, 0, bArr, i, this.b);
        return this.b;
    }

    public void c() {
        this.a.c();
    }

    public int d() {
        return this.a.d();
    }
}
