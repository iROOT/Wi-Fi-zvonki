package org.spongycastle.crypto.prng;

import org.spongycastle.crypto.Digest;

public class DigestRandomGenerator implements RandomGenerator {
    private static long a = 10;
    private long b;
    private long c = 1;
    private Digest d;
    private byte[] e;
    private byte[] f;

    public DigestRandomGenerator(Digest digest) {
        this.d = digest;
        this.f = new byte[digest.b()];
        this.e = new byte[digest.b()];
        this.b = 1;
    }

    public void a(byte[] bArr) {
        synchronized (this) {
            c(bArr);
            c(this.f);
            d(this.f);
        }
    }

    public void b(byte[] bArr) {
        a(bArr, 0, bArr.length);
    }

    public void a(byte[] bArr, int i, int i2) {
        synchronized (this) {
            b();
            int i3 = i + i2;
            int i4 = 0;
            while (i != i3) {
                if (i4 == this.e.length) {
                    b();
                    i4 = 0;
                }
                int i5 = i4 + 1;
                bArr[i] = this.e[i4];
                i++;
                i4 = i5;
            }
        }
    }

    private void a() {
        c(this.f);
        long j = this.c;
        this.c = 1 + j;
        a(j);
        d(this.f);
    }

    private void b() {
        long j = this.b;
        this.b = 1 + j;
        a(j);
        c(this.e);
        c(this.f);
        d(this.e);
        if (this.b % a == 0) {
            a();
        }
    }

    private void a(long j) {
        for (int i = 0; i != 8; i++) {
            this.d.a((byte) ((int) j));
            j >>>= 8;
        }
    }

    private void c(byte[] bArr) {
        this.d.a(bArr, 0, bArr.length);
    }

    private void d(byte[] bArr) {
        this.d.a(bArr, 0);
    }
}
