package org.spongycastle.crypto.digests;

import org.spongycastle.util.Memoable;
import org.spongycastle.util.MemoableResetException;

public class SHA512tDigest extends LongDigest {
    private final int j;
    private long k;
    private long l;
    private long m;
    private long n;
    private long o;
    private long p;
    private long q;
    private long r;

    public SHA512tDigest(int i) {
        if (i >= 512) {
            throw new IllegalArgumentException("bitLength cannot be >= 512");
        } else if (i % 8 != 0) {
            throw new IllegalArgumentException("bitLength needs to be a multiple of 8");
        } else if (i == 384) {
            throw new IllegalArgumentException("bitLength cannot be 384 use SHA384 instead");
        } else {
            this.j = i / 8;
            a(this.j * 8);
            c();
        }
    }

    public SHA512tDigest(SHA512tDigest sHA512tDigest) {
        super(sHA512tDigest);
        this.j = sHA512tDigest.j;
        a((Memoable) sHA512tDigest);
    }

    public String a() {
        return "SHA-512/" + Integer.toString(this.j * 8);
    }

    public int b() {
        return this.j;
    }

    public int a(byte[] bArr, int i) {
        f();
        a(this.a, bArr, i, this.j);
        a(this.b, bArr, i + 8, this.j - 8);
        a(this.c, bArr, i + 16, this.j - 16);
        a(this.d, bArr, i + 24, this.j - 24);
        a(this.e, bArr, i + 32, this.j - 32);
        a(this.f, bArr, i + 40, this.j - 40);
        a(this.g, bArr, i + 48, this.j - 48);
        a(this.h, bArr, i + 56, this.j - 56);
        c();
        return this.j;
    }

    public void c() {
        super.c();
        this.a = this.k;
        this.b = this.l;
        this.c = this.m;
        this.d = this.n;
        this.e = this.o;
        this.f = this.p;
        this.g = this.q;
        this.h = this.r;
    }

    private void a(int i) {
        this.a = -3482333909917012819L;
        this.b = 2216346199247487646L;
        this.c = -7364697282686394994L;
        this.d = 65953792586715988L;
        this.e = -816286391624063116L;
        this.f = 4512832404995164602L;
        this.g = -5033199132376557362L;
        this.h = -124578254951840548L;
        a((byte) 83);
        a((byte) 72);
        a((byte) 65);
        a((byte) 45);
        a((byte) 53);
        a((byte) 49);
        a((byte) 50);
        a((byte) 47);
        if (i > 100) {
            a((byte) ((i / 100) + 48));
            int i2 = i % 100;
            a((byte) ((i2 / 10) + 48));
            a((byte) ((i2 % 10) + 48));
        } else if (i > 10) {
            a((byte) ((i / 10) + 48));
            a((byte) ((i % 10) + 48));
        } else {
            a((byte) (i + 48));
        }
        f();
        this.k = this.a;
        this.l = this.b;
        this.m = this.c;
        this.n = this.d;
        this.o = this.e;
        this.p = this.f;
        this.q = this.g;
        this.r = this.h;
    }

    private static void a(long j, byte[] bArr, int i, int i2) {
        if (i2 > 0) {
            a((int) (j >>> 32), bArr, i, i2);
            if (i2 > 4) {
                a((int) (4294967295L & j), bArr, i + 4, i2 - 4);
            }
        }
    }

    private static void a(int i, byte[] bArr, int i2, int i3) {
        int min = Math.min(4, i3);
        while (true) {
            min--;
            if (min >= 0) {
                bArr[i2 + min] = (byte) (i >>> ((3 - min) * 8));
            } else {
                return;
            }
        }
    }

    public Memoable e() {
        return new SHA512tDigest(this);
    }

    public void a(Memoable memoable) {
        LongDigest longDigest = (SHA512tDigest) memoable;
        if (this.j != longDigest.j) {
            throw new MemoableResetException("digestLength inappropriate in other");
        }
        super.a(longDigest);
        this.k = longDigest.k;
        this.l = longDigest.l;
        this.m = longDigest.m;
        this.n = longDigest.n;
        this.o = longDigest.o;
        this.p = longDigest.p;
        this.q = longDigest.q;
        this.r = longDigest.r;
    }
}
