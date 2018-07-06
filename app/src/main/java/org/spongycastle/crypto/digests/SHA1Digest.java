package org.spongycastle.crypto.digests;

import org.spongycastle.crypto.util.Pack;
import org.spongycastle.util.Memoable;

public class SHA1Digest extends GeneralDigest {
    private int a;
    private int b;
    private int c;
    private int d;
    private int e;
    private int[] f;
    private int g;

    public SHA1Digest() {
        this.f = new int[80];
        c();
    }

    public SHA1Digest(SHA1Digest sHA1Digest) {
        super(sHA1Digest);
        this.f = new int[80];
        a(sHA1Digest);
    }

    private void a(SHA1Digest sHA1Digest) {
        this.a = sHA1Digest.a;
        this.b = sHA1Digest.b;
        this.c = sHA1Digest.c;
        this.d = sHA1Digest.d;
        this.e = sHA1Digest.e;
        System.arraycopy(sHA1Digest.f, 0, this.f, 0, sHA1Digest.f.length);
        this.g = sHA1Digest.g;
    }

    public String a() {
        return "SHA-1";
    }

    public int b() {
        return 20;
    }

    protected void b(byte[] bArr, int i) {
        int i2 = i + 1;
        i2++;
        this.f[this.g] = (((bArr[i] << 24) | ((bArr[i2] & 255) << 16)) | ((bArr[i2] & 255) << 8)) | (bArr[i2 + 1] & 255);
        int i3 = this.g + 1;
        this.g = i3;
        if (i3 == 16) {
            g();
        }
    }

    protected void a(long j) {
        if (this.g > 14) {
            g();
        }
        this.f[14] = (int) (j >>> 32);
        this.f[15] = (int) (-1 & j);
    }

    public int a(byte[] bArr, int i) {
        f();
        Pack.a(this.a, bArr, i);
        Pack.a(this.b, bArr, i + 4);
        Pack.a(this.c, bArr, i + 8);
        Pack.a(this.d, bArr, i + 12);
        Pack.a(this.e, bArr, i + 16);
        c();
        return 20;
    }

    public void c() {
        super.c();
        this.a = 1732584193;
        this.b = -271733879;
        this.c = -1732584194;
        this.d = 271733878;
        this.e = -1009589776;
        this.g = 0;
        for (int i = 0; i != this.f.length; i++) {
            this.f[i] = 0;
        }
    }

    private int a(int i, int i2, int i3) {
        return (i & i2) | ((i ^ -1) & i3);
    }

    private int b(int i, int i2, int i3) {
        return (i ^ i2) ^ i3;
    }

    private int c(int i, int i2, int i3) {
        return ((i & i2) | (i & i3)) | (i2 & i3);
    }

    protected void g() {
        int i;
        int i2;
        for (i = 16; i < 80; i++) {
            i2 = ((this.f[i - 3] ^ this.f[i - 8]) ^ this.f[i - 14]) ^ this.f[i - 16];
            this.f[i] = (i2 >>> 31) | (i2 << 1);
        }
        int i3 = this.a;
        int i4 = this.b;
        int i5 = this.c;
        int i6 = this.d;
        int i7 = this.e;
        i2 = 0;
        for (i = 0; i < 4; i++) {
            int i8 = i2 + 1;
            i2 = ((this.f[i2] + (((i3 << 5) | (i3 >>> 27)) + a(i4, i5, i6))) + 1518500249) + i7;
            i4 = (i4 >>> 2) | (i4 << 30);
            int i9 = i8 + 1;
            i6 += ((((i2 << 5) | (i2 >>> 27)) + a(i3, i4, i5)) + this.f[i8]) + 1518500249;
            i3 = (i3 >>> 2) | (i3 << 30);
            i8 = i9 + 1;
            i5 += ((((i6 << 5) | (i6 >>> 27)) + a(i2, i3, i4)) + this.f[i9]) + 1518500249;
            i7 = (i2 << 30) | (i2 >>> 2);
            i9 = i8 + 1;
            i4 += ((((i5 << 5) | (i5 >>> 27)) + a(i6, i7, i3)) + this.f[i8]) + 1518500249;
            i6 = (i6 >>> 2) | (i6 << 30);
            int a = a(i5, i6, i7) + ((i4 << 5) | (i4 >>> 27));
            i2 = i9 + 1;
            i3 += (a + this.f[i9]) + 1518500249;
            i5 = (i5 >>> 2) | (i5 << 30);
        }
        for (i = 0; i < 4; i++) {
            i8 = i2 + 1;
            i2 = ((this.f[i2] + (((i3 << 5) | (i3 >>> 27)) + b(i4, i5, i6))) + 1859775393) + i7;
            i4 = (i4 >>> 2) | (i4 << 30);
            i9 = i8 + 1;
            i6 += ((((i2 << 5) | (i2 >>> 27)) + b(i3, i4, i5)) + this.f[i8]) + 1859775393;
            i3 = (i3 >>> 2) | (i3 << 30);
            i8 = i9 + 1;
            i5 += ((((i6 << 5) | (i6 >>> 27)) + b(i2, i3, i4)) + this.f[i9]) + 1859775393;
            i7 = (i2 << 30) | (i2 >>> 2);
            i9 = i8 + 1;
            i4 += ((((i5 << 5) | (i5 >>> 27)) + b(i6, i7, i3)) + this.f[i8]) + 1859775393;
            i6 = (i6 >>> 2) | (i6 << 30);
            a = b(i5, i6, i7) + ((i4 << 5) | (i4 >>> 27));
            i2 = i9 + 1;
            i3 += (a + this.f[i9]) + 1859775393;
            i5 = (i5 >>> 2) | (i5 << 30);
        }
        for (i = 0; i < 4; i++) {
            i8 = i2 + 1;
            i2 = ((this.f[i2] + (((i3 << 5) | (i3 >>> 27)) + c(i4, i5, i6))) - 1894007588) + i7;
            i4 = (i4 >>> 2) | (i4 << 30);
            i9 = i8 + 1;
            i6 += ((((i2 << 5) | (i2 >>> 27)) + c(i3, i4, i5)) + this.f[i8]) - 1894007588;
            i3 = (i3 >>> 2) | (i3 << 30);
            i8 = i9 + 1;
            i5 += ((((i6 << 5) | (i6 >>> 27)) + c(i2, i3, i4)) + this.f[i9]) - 1894007588;
            i7 = (i2 << 30) | (i2 >>> 2);
            i9 = i8 + 1;
            i4 += ((((i5 << 5) | (i5 >>> 27)) + c(i6, i7, i3)) + this.f[i8]) - 1894007588;
            i6 = (i6 >>> 2) | (i6 << 30);
            a = c(i5, i6, i7) + ((i4 << 5) | (i4 >>> 27));
            i2 = i9 + 1;
            i3 += (a + this.f[i9]) - 1894007588;
            i5 = (i5 >>> 2) | (i5 << 30);
        }
        for (i = 0; i <= 3; i++) {
            i8 = i2 + 1;
            i2 = ((this.f[i2] + (((i3 << 5) | (i3 >>> 27)) + b(i4, i5, i6))) - 899497514) + i7;
            i4 = (i4 >>> 2) | (i4 << 30);
            i9 = i8 + 1;
            i6 += ((((i2 << 5) | (i2 >>> 27)) + b(i3, i4, i5)) + this.f[i8]) - 899497514;
            i3 = (i3 >>> 2) | (i3 << 30);
            i8 = i9 + 1;
            i5 += ((((i6 << 5) | (i6 >>> 27)) + b(i2, i3, i4)) + this.f[i9]) - 899497514;
            i7 = (i2 << 30) | (i2 >>> 2);
            i9 = i8 + 1;
            i4 += ((((i5 << 5) | (i5 >>> 27)) + b(i6, i7, i3)) + this.f[i8]) - 899497514;
            i6 = (i6 >>> 2) | (i6 << 30);
            a = b(i5, i6, i7) + ((i4 << 5) | (i4 >>> 27));
            i2 = i9 + 1;
            i3 += (a + this.f[i9]) - 899497514;
            i5 = (i5 >>> 2) | (i5 << 30);
        }
        this.a += i3;
        this.b += i4;
        this.c += i5;
        this.d += i6;
        this.e += i7;
        this.g = 0;
        for (i = 0; i < 16; i++) {
            this.f[i] = 0;
        }
    }

    public Memoable e() {
        return new SHA1Digest(this);
    }

    public void a(Memoable memoable) {
        SHA1Digest sHA1Digest = (SHA1Digest) memoable;
        super.a((GeneralDigest) sHA1Digest);
        a(sHA1Digest);
    }
}
