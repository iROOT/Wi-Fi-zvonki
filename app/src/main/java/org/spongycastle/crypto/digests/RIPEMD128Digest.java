package org.spongycastle.crypto.digests;

import org.spongycastle.util.Memoable;

public class RIPEMD128Digest extends GeneralDigest {
    private int a;
    private int b;
    private int c;
    private int d;
    private int[] e;
    private int f;

    public RIPEMD128Digest() {
        this.e = new int[16];
        c();
    }

    public RIPEMD128Digest(RIPEMD128Digest rIPEMD128Digest) {
        super(rIPEMD128Digest);
        this.e = new int[16];
        a(rIPEMD128Digest);
    }

    private void a(RIPEMD128Digest rIPEMD128Digest) {
        super.a((GeneralDigest) rIPEMD128Digest);
        this.a = rIPEMD128Digest.a;
        this.b = rIPEMD128Digest.b;
        this.c = rIPEMD128Digest.c;
        this.d = rIPEMD128Digest.d;
        System.arraycopy(rIPEMD128Digest.e, 0, this.e, 0, rIPEMD128Digest.e.length);
        this.f = rIPEMD128Digest.f;
    }

    public String a() {
        return "RIPEMD128";
    }

    public int b() {
        return 16;
    }

    protected void b(byte[] bArr, int i) {
        int[] iArr = this.e;
        int i2 = this.f;
        this.f = i2 + 1;
        iArr[i2] = (((bArr[i] & 255) | ((bArr[i + 1] & 255) << 8)) | ((bArr[i + 2] & 255) << 16)) | ((bArr[i + 3] & 255) << 24);
        if (this.f == 16) {
            g();
        }
    }

    protected void a(long j) {
        if (this.f > 14) {
            g();
        }
        this.e[14] = (int) (-1 & j);
        this.e[15] = (int) (j >>> 32);
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
        c();
        return 16;
    }

    public void c() {
        super.c();
        this.a = 1732584193;
        this.b = -271733879;
        this.c = -1732584194;
        this.d = 271733878;
        this.f = 0;
        for (int i = 0; i != this.e.length; i++) {
            this.e[i] = 0;
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
        int a = a(i, i2, i3, i4, this.e[0], 11);
        int a2 = a(i4, a, i2, i3, this.e[1], 14);
        int a3 = a(i3, a2, a, i2, this.e[2], 15);
        int a4 = a(i2, a3, a2, a, this.e[3], 12);
        int a5 = a(a, a4, a3, a2, this.e[4], 5);
        int a6 = a(a2, a5, a4, a3, this.e[5], 8);
        int a7 = a(a3, a6, a5, a4, this.e[6], 7);
        int a8 = a(a4, a7, a6, a5, this.e[7], 9);
        int a9 = a(a5, a8, a7, a6, this.e[8], 11);
        a2 = a(a6, a9, a8, a7, this.e[9], 13);
        a3 = a(a7, a2, a9, a8, this.e[10], 14);
        int a10 = a(a8, a3, a2, a9, this.e[11], 15);
        a5 = a(a9, a10, a3, a2, this.e[12], 6);
        a6 = a(a2, a5, a10, a3, this.e[13], 7);
        int a11 = a(a3, a6, a5, a10, this.e[14], 9);
        a8 = a(a10, a11, a6, a5, this.e[15], 8);
        a9 = b(a5, a8, a11, a6, this.e[7], 7);
        int b = b(a6, a9, a8, a11, this.e[4], 6);
        a3 = b(a11, b, a9, a8, this.e[13], 8);
        a10 = b(a8, a3, b, a9, this.e[1], 13);
        int b2 = b(a9, a10, a3, b, this.e[10], 11);
        a6 = b(b, b2, a10, a3, this.e[6], 9);
        a11 = b(a3, a6, b2, a10, this.e[15], 7);
        int b3 = b(a10, a11, a6, b2, this.e[3], 15);
        a9 = b(b2, b3, a11, a6, this.e[12], 7);
        b = b(a6, a9, b3, a11, this.e[0], 12);
        int b4 = b(a11, b, a9, b3, this.e[9], 15);
        a10 = b(b3, b4, b, a9, this.e[5], 9);
        b2 = b(a9, a10, b4, b, this.e[2], 11);
        int b5 = b(b, b2, a10, b4, this.e[14], 7);
        a11 = b(b4, b5, b2, a10, this.e[11], 13);
        b3 = b(a10, a11, b5, b2, this.e[8], 12);
        int c = c(b2, b3, a11, b5, this.e[3], 11);
        b = c(b5, c, b3, a11, this.e[10], 13);
        b4 = c(a11, b, c, b3, this.e[14], 6);
        int c2 = c(b3, b4, b, c, this.e[4], 7);
        b2 = c(c, c2, b4, b, this.e[9], 14);
        b5 = c(b, b2, c2, b4, this.e[15], 9);
        int c3 = c(b4, b5, b2, c2, this.e[8], 13);
        b3 = c(c2, c3, b5, b2, this.e[1], 15);
        c = c(b2, b3, c3, b5, this.e[2], 14);
        int c4 = c(b5, c, b3, c3, this.e[7], 8);
        b4 = c(c3, c4, c, b3, this.e[0], 13);
        c2 = c(b3, b4, c4, c, this.e[6], 6);
        int c5 = c(c, c2, b4, c4, this.e[13], 5);
        b5 = c(c4, c5, c2, b4, this.e[11], 12);
        c3 = c(b4, b5, c5, c2, this.e[5], 7);
        int c6 = c(c2, c3, b5, c5, this.e[12], 5);
        c = d(c5, c6, c3, b5, this.e[1], 11);
        c4 = d(b5, c, c6, c3, this.e[9], 12);
        int d = d(c3, c4, c, c6, this.e[11], 14);
        c2 = d(c6, d, c4, c, this.e[10], 15);
        c5 = d(c, c2, d, c4, this.e[0], 14);
        int d2 = d(c4, c5, c2, d, this.e[8], 15);
        c3 = d(d, d2, c5, c2, this.e[12], 9);
        c6 = d(c2, c3, d2, c5, this.e[4], 8);
        int d3 = d(c5, c6, c3, d2, this.e[13], 9);
        c4 = d(d2, d3, c6, c3, this.e[3], 14);
        d = d(c3, c4, d3, c6, this.e[7], 5);
        int d4 = d(c6, d, c4, d3, this.e[15], 6);
        c5 = d(d3, d4, d, c4, this.e[14], 8);
        d2 = d(c4, c5, d4, d, this.e[5], 6);
        int d5 = d(d, d2, c5, d4, this.e[6], 5);
        c6 = d(d4, d5, d2, c5, this.e[2], 12);
        a = h(i, i2, i3, i4, this.e[5], 8);
        int h = h(i4, a, i2, i3, this.e[14], 9);
        int h2 = h(i3, h, a, i2, this.e[7], 9);
        a2 = h(i2, h2, h, a, this.e[0], 11);
        a7 = h(a, a2, h2, h, this.e[9], 13);
        a4 = h(h, a7, a2, h2, this.e[2], 15);
        i2 = h(h2, a4, a7, a2, this.e[11], 15);
        a8 = h(a2, i2, a4, a7, this.e[4], 5);
        a5 = h(a7, a8, i2, a4, this.e[13], 7);
        a2 = h(a4, a5, a8, i2, this.e[6], 7);
        i2 = h(i2, a2, a5, a8, this.e[15], 8);
        h2 = h(a8, i2, a2, a5, this.e[8], 11);
        a5 = h(a5, h2, i2, a2, this.e[1], 14);
        i3 = h(a2, a5, h2, i2, this.e[10], 14);
        h = h(i2, i3, a5, h2, this.e[3], 12);
        a8 = h(h2, h, i3, a5, this.e[12], 6);
        i4 = g(a5, a8, h, i3, this.e[6], 9);
        a = g(i3, i4, a8, h, this.e[11], 13);
        a3 = g(h, a, i4, a8, this.e[3], 15);
        h2 = g(a8, a3, a, i4, this.e[7], 7);
        a4 = g(i4, h2, a3, a, this.e[0], 12);
        a6 = g(a, a4, h2, a3, this.e[13], 8);
        h = g(a3, a6, a4, h2, this.e[5], 9);
        a7 = g(h2, h, a6, a4, this.e[10], 11);
        a9 = g(a4, a7, h, a6, this.e[14], 7);
        a = g(a6, a9, a7, h, this.e[15], 7);
        a2 = g(h, a, a9, a7, this.e[8], 12);
        a10 = g(a7, a2, a, a9, this.e[12], 7);
        a4 = g(a9, a10, a2, a, this.e[4], 6);
        a5 = g(a, a4, a10, a2, this.e[9], 15);
        a11 = g(a2, a5, a4, a10, this.e[1], 13);
        a7 = g(a10, a11, a5, a4, this.e[2], 11);
        a8 = f(a4, a7, a11, a5, this.e[15], 9);
        b = f(a5, a8, a7, a11, this.e[5], 7);
        a2 = f(a11, b, a8, a7, this.e[1], 15);
        a3 = f(a7, a2, b, a8, this.e[3], 11);
        b2 = f(a8, a3, a2, b, this.e[7], 8);
        a5 = f(b, b2, a3, a2, this.e[14], 6);
        a6 = f(a2, a5, b2, a3, this.e[6], 6);
        b3 = f(a3, a6, a5, b2, this.e[9], 14);
        a8 = f(b2, b3, a6, a5, this.e[11], 12);
        a9 = f(a5, a8, b3, a6, this.e[8], 13);
        b4 = f(a6, a9, a8, b3, this.e[12], 5);
        a3 = f(b3, b4, a9, a8, this.e[2], 14);
        a10 = f(a8, a3, b4, a9, this.e[10], 13);
        b5 = f(a9, a10, a3, b4, this.e[0], 13);
        a6 = f(b4, b5, a10, a3, this.e[4], 7);
        a11 = f(a3, a6, b5, a10, this.e[13], 5);
        a8 = e(a10, a11, a6, b5, this.e[8], 15);
        a9 = e(b5, a8, a11, a6, this.e[6], 5);
        b = e(a6, a9, a8, a11, this.e[4], 8);
        a3 = e(a11, b, a9, a8, this.e[1], 11);
        a10 = e(a8, a3, b, a9, this.e[3], 14);
        b2 = e(a9, a10, a3, b, this.e[11], 14);
        a6 = e(b, b2, a10, a3, this.e[15], 6);
        a11 = e(a3, a6, b2, a10, this.e[0], 14);
        b3 = e(a10, a11, a6, b2, this.e[5], 6);
        a9 = e(b2, b3, a11, a6, this.e[12], 9);
        b = e(a6, a9, b3, a11, this.e[2], 12);
        b4 = e(a11, b, a9, b3, this.e[13], 9);
        a10 = e(b3, b4, b, a9, this.e[9], 12);
        b2 = e(a9, a10, b4, b, this.e[7], 5);
        b5 = e(b, b2, a10, b4, this.e[10], 15);
        int e = e(b4, b5, b2, a10, this.e[14], 8);
        i = (this.b + d5) + b2;
        this.b = (this.c + d2) + a10;
        this.c = e + (this.d + c5);
        this.d = (this.a + c6) + b5;
        this.a = i;
        this.f = 0;
        for (e = 0; e != this.e.length; e++) {
            this.e[e] = 0;
        }
    }

    public Memoable e() {
        return new RIPEMD128Digest(this);
    }

    public void a(Memoable memoable) {
        a((RIPEMD128Digest) memoable);
    }
}
