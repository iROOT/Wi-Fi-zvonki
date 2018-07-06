package org.spongycastle.crypto.digests;

import org.spongycastle.util.Memoable;

public class RIPEMD256Digest extends GeneralDigest {
    private int a;
    private int b;
    private int c;
    private int d;
    private int e;
    private int f;
    private int g;
    private int h;
    private int[] i;
    private int j;

    public RIPEMD256Digest() {
        this.i = new int[16];
        c();
    }

    public RIPEMD256Digest(RIPEMD256Digest rIPEMD256Digest) {
        super(rIPEMD256Digest);
        this.i = new int[16];
        a(rIPEMD256Digest);
    }

    private void a(RIPEMD256Digest rIPEMD256Digest) {
        super.a((GeneralDigest) rIPEMD256Digest);
        this.a = rIPEMD256Digest.a;
        this.b = rIPEMD256Digest.b;
        this.c = rIPEMD256Digest.c;
        this.d = rIPEMD256Digest.d;
        this.e = rIPEMD256Digest.e;
        this.f = rIPEMD256Digest.f;
        this.g = rIPEMD256Digest.g;
        this.h = rIPEMD256Digest.h;
        System.arraycopy(rIPEMD256Digest.i, 0, this.i, 0, rIPEMD256Digest.i.length);
        this.j = rIPEMD256Digest.j;
    }

    public String a() {
        return "RIPEMD256";
    }

    public int b() {
        return 32;
    }

    protected void b(byte[] bArr, int i) {
        int[] iArr = this.i;
        int i2 = this.j;
        this.j = i2 + 1;
        iArr[i2] = (((bArr[i] & 255) | ((bArr[i + 1] & 255) << 8)) | ((bArr[i + 2] & 255) << 16)) | ((bArr[i + 3] & 255) << 24);
        if (this.j == 16) {
            g();
        }
    }

    protected void a(long j) {
        if (this.j > 14) {
            g();
        }
        this.i[14] = (int) (-1 & j);
        this.i[15] = (int) (j >>> 32);
    }

    private void a(int i, byte[] bArr, int i2) {
        bArr[i2] = (byte) i;
        bArr[i2 + 1] = (byte) (i >>> 8);
        bArr[i2 + 2] = (byte) (i >>> 16);
        bArr[i2 + 3] = (byte) (i >>> 24);
    }

    public int a(byte[] bArr, int i) {
        f();
        a(this.a, bArr, i);
        a(this.b, bArr, i + 4);
        a(this.c, bArr, i + 8);
        a(this.d, bArr, i + 12);
        a(this.e, bArr, i + 16);
        a(this.f, bArr, i + 20);
        a(this.g, bArr, i + 24);
        a(this.h, bArr, i + 28);
        c();
        return 32;
    }

    public void c() {
        super.c();
        this.a = 1732584193;
        this.b = -271733879;
        this.c = -1732584194;
        this.d = 271733878;
        this.e = 1985229328;
        this.f = -19088744;
        this.g = -1985229329;
        this.h = 19088743;
        this.j = 0;
        for (int i = 0; i != this.i.length; i++) {
            this.i[i] = 0;
        }
    }

    private int a(int i, int i2) {
        return (i << i2) | (i >>> (32 - i2));
    }

    private int a(int i, int i2, int i3) {
        return (i ^ i2) ^ i3;
    }

    private int b(int i, int i2, int i3) {
        return (i & i2) | ((i ^ -1) & i3);
    }

    private int c(int i, int i2, int i3) {
        return ((i2 ^ -1) | i) ^ i3;
    }

    private int d(int i, int i2, int i3) {
        return (i & i3) | ((i3 ^ -1) & i2);
    }

    private int a(int i, int i2, int i3, int i4, int i5, int i6) {
        return a((a(i2, i3, i4) + i) + i5, i6);
    }

    private int b(int i, int i2, int i3, int i4, int i5, int i6) {
        return a(((b(i2, i3, i4) + i) + i5) + 1518500249, i6);
    }

    private int c(int i, int i2, int i3, int i4, int i5, int i6) {
        return a(((c(i2, i3, i4) + i) + i5) + 1859775393, i6);
    }

    private int d(int i, int i2, int i3, int i4, int i5, int i6) {
        return a(((d(i2, i3, i4) + i) + i5) - 1894007588, i6);
    }

    private int e(int i, int i2, int i3, int i4, int i5, int i6) {
        return a((a(i2, i3, i4) + i) + i5, i6);
    }

    private int f(int i, int i2, int i3, int i4, int i5, int i6) {
        return a(((b(i2, i3, i4) + i) + i5) + 1836072691, i6);
    }

    private int g(int i, int i2, int i3, int i4, int i5, int i6) {
        return a(((c(i2, i3, i4) + i) + i5) + 1548603684, i6);
    }

    private int h(int i, int i2, int i3, int i4, int i5, int i6) {
        return a(((d(i2, i3, i4) + i) + i5) + 1352829926, i6);
    }

    protected void g() {
        int i = this.a;
        int i2 = this.b;
        int i3 = this.c;
        int i4 = this.d;
        int i5 = this.e;
        int i6 = this.f;
        int i7 = this.g;
        int i8 = this.h;
        int a = a(i, i2, i3, i4, this.i[0], 11);
        int a2 = a(i4, a, i2, i3, this.i[1], 14);
        int a3 = a(i3, a2, a, i2, this.i[2], 15);
        int a4 = a(i2, a3, a2, a, this.i[3], 12);
        int a5 = a(a, a4, a3, a2, this.i[4], 5);
        int a6 = a(a2, a5, a4, a3, this.i[5], 8);
        i2 = a(a3, a6, a5, a4, this.i[6], 7);
        int a7 = a(a4, i2, a6, a5, this.i[7], 9);
        int a8 = a(a5, a7, i2, a6, this.i[8], 11);
        a4 = a(a6, a8, a7, i2, this.i[9], 13);
        i2 = a(i2, a4, a8, a7, this.i[10], 14);
        a3 = a(a7, i2, a4, a8, this.i[11], 15);
        a8 = a(a8, a3, i2, a4, this.i[12], 6);
        i3 = a(a4, a8, a3, i2, this.i[13], 7);
        a2 = a(i2, i3, a8, a3, this.i[14], 9);
        a3 = a(a3, a2, i3, a8, this.i[15], 8);
        int h = h(i5, i6, i7, i8, this.i[5], 8);
        int h2 = h(i8, h, i6, i7, this.i[14], 9);
        i5 = h(i7, h2, h, i6, this.i[7], 9);
        int h3 = h(i6, i5, h2, h, this.i[0], 11);
        int h4 = h(h, h3, i5, h2, this.i[9], 13);
        i8 = h(h2, h4, h3, i5, this.i[2], 15);
        int h5 = h(i5, i8, h4, h3, this.i[11], 15);
        i6 = h(h3, h5, i8, h4, this.i[4], 5);
        i7 = h(h4, i6, h5, i8, this.i[13], 7);
        h2 = h(i8, i7, i6, h5, this.i[6], 7);
        i5 = h(h5, h2, i7, i6, this.i[15], 8);
        int h6 = h(i6, i5, h2, i7, this.i[8], 11);
        h4 = h(i7, h6, i5, h2, this.i[1], 14);
        i8 = h(h2, h4, h6, i5, this.i[10], 14);
        int h7 = h(i5, i8, h4, h6, this.i[3], 12);
        h6 = h(h6, h7, i8, h4, this.i[12], 6);
        i4 = b(h4, a3, a2, i3, this.i[7], 7);
        h = b(i3, i4, a3, a2, this.i[4], 6);
        int b = b(a2, h, i4, a3, this.i[13], 8);
        a3 = b(a3, b, h, i4, this.i[1], 13);
        h3 = b(i4, a3, b, h, this.i[10], 11);
        int b2 = b(h, h3, a3, b, this.i[6], 9);
        a2 = b(b, b2, h3, a3, this.i[15], 7);
        h5 = b(a3, a2, b2, h3, this.i[3], 15);
        int b3 = b(h3, h5, a2, b2, this.i[12], 7);
        b2 = b(b2, b3, h5, a2, this.i[0], 12);
        b = b(a2, b2, b3, h5, this.i[9], 15);
        i2 = b(h5, b, b2, b3, this.i[5], 9);
        a3 = b(b3, i2, b, b2, this.i[2], 11);
        b2 = b(b2, a3, i2, b, this.i[14], 7);
        i3 = b(b, b2, a3, i2, this.i[11], 13);
        int b4 = b(i2, i3, b2, a3, this.i[8], 12);
        i7 = g(a8, h6, h7, i8, this.i[6], 9);
        a6 = g(i8, i7, h6, h7, this.i[11], 13);
        a8 = g(h7, a6, i7, h6, this.i[3], 15);
        h6 = g(h6, a8, a6, i7, this.i[7], 7);
        a5 = g(i7, h6, a8, a6, this.i[0], 12);
        a7 = g(a6, a5, h6, a8, this.i[13], 8);
        h3 = g(a8, a7, a5, h6, this.i[5], 9);
        a4 = g(h6, h3, a7, a5, this.i[10], 11);
        int g = g(a5, a4, h3, a7, this.i[14], 7);
        h5 = g(a7, g, a4, h3, this.i[15], 7);
        a8 = g(h3, h5, g, a4, this.i[8], 12);
        h = g(a4, a8, h5, g, this.i[12], 7);
        h2 = g(g, h, a8, h5, this.i[4], 6);
        a7 = g(h5, h2, h, a8, this.i[9], 15);
        h3 = g(a8, a7, h2, h, this.i[1], 13);
        a2 = g(h, h3, a7, h2, this.i[2], 11);
        b3 = c(a3, a2, i3, b2, this.i[3], 11);
        i4 = c(b2, b3, a2, i3, this.i[10], 13);
        a = c(i3, i4, b3, a2, this.i[14], 6);
        int c = c(a2, a, i4, b3, this.i[4], 7);
        a3 = c(b3, c, a, i4, this.i[9], 14);
        b2 = c(i4, a3, c, a, this.i[15], 9);
        int c2 = c(a, b2, a3, c, this.i[8], 13);
        a2 = c(c, c2, b2, a3, this.i[1], 15);
        b3 = c(a3, a2, c2, b2, this.i[2], 14);
        int c3 = c(b2, b3, a2, c2, this.i[7], 8);
        a = c(c2, c3, b3, a2, this.i[0], 13);
        c = c(a2, a, c3, b3, this.i[6], 6);
        int c4 = c(b3, c, a, c3, this.i[13], 5);
        b2 = c(c3, c4, c, a, this.i[11], 12);
        c2 = c(a, b2, c4, c, this.i[5], 7);
        int c5 = c(c, c2, b2, c4, this.i[12], 5);
        g = f(h2, b4, h3, a7, this.i[15], 9);
        h5 = f(a7, g, b4, h3, this.i[5], 7);
        a8 = f(h3, h5, g, b4, this.i[1], 15);
        h = f(b4, a8, h5, g, this.i[3], 11);
        h2 = f(g, h, a8, h5, this.i[7], 8);
        a7 = f(h5, h2, h, a8, this.i[14], 6);
        h3 = f(a8, a7, h2, h, this.i[6], 6);
        h4 = f(h, h3, a7, h2, this.i[9], 14);
        g = f(h2, h4, h3, a7, this.i[11], 12);
        h5 = f(a7, g, h4, h3, this.i[8], 13);
        i6 = f(h3, h5, g, h4, this.i[12], 5);
        h = f(h4, i6, h5, g, this.i[2], 14);
        h2 = f(g, h, i6, h5, this.i[10], 13);
        i5 = f(h5, h2, h, i6, this.i[0], 13);
        h3 = f(i6, i5, h2, h, this.i[4], 7);
        h4 = f(h, h3, i5, h2, this.i[13], 5);
        i2 = d(c4, c5, h3, b2, this.i[1], 11);
        a3 = d(b2, i2, c5, h3, this.i[9], 12);
        int d = d(h3, a3, i2, c5, this.i[11], 14);
        i3 = d(c5, d, a3, i2, this.i[10], 15);
        a2 = d(i2, i3, d, a3, this.i[0], 14);
        int d2 = d(a3, a2, i3, d, this.i[8], 15);
        i4 = d(d, d2, a2, i3, this.i[12], 9);
        a = d(i3, i4, d2, a2, this.i[4], 8);
        int d3 = d(a2, a, i4, d2, this.i[13], 9);
        a3 = d(d2, d3, a, i4, this.i[3], 14);
        a6 = d(i4, a3, d3, a, this.i[7], 5);
        int d4 = d(a, a6, a3, d3, this.i[15], 6);
        a2 = d(d3, d4, a6, a3, this.i[14], 8);
        a5 = d(a3, a2, d4, a6, this.i[5], 6);
        int d5 = d(a6, a5, a2, d4, this.i[6], 5);
        b4 = d(d4, d5, a5, a2, this.i[2], 12);
        i8 = e(h2, h4, c2, i5, this.i[8], 15);
        c3 = e(i5, i8, h4, c2, this.i[6], 5);
        i6 = e(c2, c3, i8, h4, this.i[4], 8);
        i7 = e(h4, i6, c3, i8, this.i[1], 11);
        c4 = e(i8, i7, i6, c3, this.i[3], 14);
        i5 = e(c3, c4, i7, i6, this.i[11], 14);
        h6 = e(i6, i5, c4, i7, this.i[15], 6);
        c5 = e(i7, h6, i5, c4, this.i[0], 14);
        i8 = e(c4, c5, h6, i5, this.i[5], 6);
        h7 = e(i5, i8, c5, h6, this.i[12], 9);
        i6 = e(h6, h7, i8, c5, this.i[2], 12);
        i7 = e(c5, i6, h7, i8, this.i[13], 9);
        int e = e(i8, i7, i6, h7, this.i[9], 12);
        i5 = e(h7, e, i7, i6, this.i[7], 5);
        h6 = e(i6, i5, e, i7, this.i[10], 15);
        i = e(i7, h6, i5, e, this.i[14], 8);
        this.a += a2;
        this.b = b4 + this.b;
        this.c += d5;
        this.d += i5;
        this.e += e;
        this.f += i;
        this.g += h6;
        this.h += a5;
        this.j = 0;
        for (b4 = 0; b4 != this.i.length; b4++) {
            this.i[b4] = 0;
        }
    }

    public Memoable e() {
        return new RIPEMD256Digest(this);
    }

    public void a(Memoable memoable) {
        a((RIPEMD256Digest) memoable);
    }
}
