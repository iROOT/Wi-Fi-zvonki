package org.spongycastle.crypto.digests;

import org.spongycastle.crypto.ExtendedDigest;
import org.spongycastle.util.Memoable;

public abstract class GeneralDigest implements ExtendedDigest, Memoable {
    private byte[] a;
    private int b;
    private long c;

    protected abstract void a(long j);

    protected abstract void b(byte[] bArr, int i);

    protected abstract void g();

    protected GeneralDigest() {
        this.a = new byte[4];
        this.b = 0;
    }

    protected GeneralDigest(GeneralDigest generalDigest) {
        this.a = new byte[generalDigest.a.length];
        a(generalDigest);
    }

    protected void a(GeneralDigest generalDigest) {
        System.arraycopy(generalDigest.a, 0, this.a, 0, generalDigest.a.length);
        this.b = generalDigest.b;
        this.c = generalDigest.c;
    }

    public void a(byte b) {
        byte[] bArr = this.a;
        int i = this.b;
        this.b = i + 1;
        bArr[i] = b;
        if (this.b == this.a.length) {
            b(this.a, 0);
            this.b = 0;
        }
        this.c++;
    }

    public void a(byte[] bArr, int i, int i2) {
        while (this.b != 0 && i2 > 0) {
            a(bArr[i]);
            i++;
            i2--;
        }
        while (i2 > this.a.length) {
            b(bArr, i);
            i += this.a.length;
            i2 -= this.a.length;
            this.c += (long) this.a.length;
        }
        while (i2 > 0) {
            a(bArr[i]);
            i++;
            i2--;
        }
    }

    public void f() {
        long j = this.c << 3;
        a(Byte.MIN_VALUE);
        while (this.b != 0) {
            a((byte) 0);
        }
        a(j);
        g();
    }

    public void c() {
        this.c = 0;
        this.b = 0;
        for (int i = 0; i < this.a.length; i++) {
            this.a[i] = (byte) 0;
        }
    }

    public int d() {
        return 64;
    }
}
