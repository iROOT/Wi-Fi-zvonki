package org.spongycastle.crypto.digests;

import org.spongycastle.crypto.util.Pack;
import org.spongycastle.util.Memoable;

public class SHA224Digest extends GeneralDigest {
    static final int[] a = new int[]{1116352408, 1899447441, -1245643825, -373957723, 961987163, 1508970993, -1841331548, -1424204075, -670586216, 310598401, 607225278, 1426881987, 1925078388, -2132889090, -1680079193, -1046744716, -459576895, -272742522, 264347078, 604807628, 770255983, 1249150122, 1555081692, 1996064986, -1740746414, -1473132947, -1341970488, -1084653625, -958395405, -710438585, 113926993, 338241895, 666307205, 773529912, 1294757372, 1396182291, 1695183700, 1986661051, -2117940946, -1838011259, -1564481375, -1474664885, -1035236496, -949202525, -778901479, -694614492, -200395387, 275423344, 430227734, 506948616, 659060556, 883997877, 958139571, 1322822218, 1537002063, 1747873779, 1955562222, 2024104815, -2067236844, -1933114872, -1866530822, -1538233109, -1090935817, -965641998};
    private int b;
    private int c;
    private int d;
    private int e;
    private int f;
    private int g;
    private int h;
    private int i;
    private int[] j;
    private int k;

    public SHA224Digest() {
        this.j = new int[64];
        c();
    }

    public SHA224Digest(SHA224Digest sHA224Digest) {
        super(sHA224Digest);
        this.j = new int[64];
        a(sHA224Digest);
    }

    private void a(SHA224Digest sHA224Digest) {
        super.a((GeneralDigest) sHA224Digest);
        this.b = sHA224Digest.b;
        this.c = sHA224Digest.c;
        this.d = sHA224Digest.d;
        this.e = sHA224Digest.e;
        this.f = sHA224Digest.f;
        this.g = sHA224Digest.g;
        this.h = sHA224Digest.h;
        this.i = sHA224Digest.i;
        System.arraycopy(sHA224Digest.j, 0, this.j, 0, sHA224Digest.j.length);
        this.k = sHA224Digest.k;
    }

    public String a() {
        return "SHA-224";
    }

    public int b() {
        return 28;
    }

    protected void b(byte[] bArr, int i) {
        int i2 = i + 1;
        i2++;
        this.j[this.k] = (((bArr[i] << 24) | ((bArr[i2] & 255) << 16)) | ((bArr[i2] & 255) << 8)) | (bArr[i2 + 1] & 255);
        int i3 = this.k + 1;
        this.k = i3;
        if (i3 == 16) {
            g();
        }
    }

    protected void a(long j) {
        if (this.k > 14) {
            g();
        }
        this.j[14] = (int) (j >>> 32);
        this.j[15] = (int) (-1 & j);
    }

    public int a(byte[] bArr, int i) {
        f();
        Pack.a(this.b, bArr, i);
        Pack.a(this.c, bArr, i + 4);
        Pack.a(this.d, bArr, i + 8);
        Pack.a(this.e, bArr, i + 12);
        Pack.a(this.f, bArr, i + 16);
        Pack.a(this.g, bArr, i + 20);
        Pack.a(this.h, bArr, i + 24);
        c();
        return 28;
    }

    public void c() {
        super.c();
        this.b = -1056596264;
        this.c = 914150663;
        this.d = 812702999;
        this.e = -150054599;
        this.f = -4191439;
        this.g = 1750603025;
        this.h = 1694076839;
        this.i = -1090891868;
        this.k = 0;
        for (int i = 0; i != this.j.length; i++) {
            this.j[i] = 0;
        }
    }

    protected void g() {
        int i;
        for (i = 16; i <= 63; i++) {
            this.j[i] = ((d(this.j[i - 2]) + this.j[i - 7]) + c(this.j[i - 15])) + this.j[i - 16];
        }
        int i2 = this.b;
        int i3 = this.c;
        int i4 = this.d;
        int i5 = this.e;
        int i6 = this.f;
        int i7 = this.g;
        int i8 = this.h;
        int i9 = i3;
        int i10 = i2;
        i3 = i5;
        i2 = i4;
        i5 = i7;
        i4 = i6;
        i7 = this.i;
        i6 = i8;
        i8 = 0;
        for (i = 0; i < 8; i++) {
            i7 += ((b(i4) + a(i4, i5, i6)) + a[i8]) + this.j[i8];
            i3 += i7;
            i7 += a(i10) + b(i10, i9, i2);
            i8++;
            i6 += ((b(i3) + a(i3, i4, i5)) + a[i8]) + this.j[i8];
            i2 += i6;
            i6 += a(i7) + b(i7, i10, i9);
            i8++;
            i5 += ((b(i2) + a(i2, i3, i4)) + a[i8]) + this.j[i8];
            i9 += i5;
            i5 += a(i6) + b(i6, i7, i10);
            i8++;
            i4 += ((b(i9) + a(i9, i2, i3)) + a[i8]) + this.j[i8];
            i10 += i4;
            i4 += a(i5) + b(i5, i6, i7);
            i8++;
            i3 += ((b(i10) + a(i10, i9, i2)) + a[i8]) + this.j[i8];
            i7 += i3;
            i3 += a(i4) + b(i4, i5, i6);
            i8++;
            i2 += ((b(i7) + a(i7, i10, i9)) + a[i8]) + this.j[i8];
            i6 += i2;
            i2 += a(i3) + b(i3, i4, i5);
            i8++;
            i9 += ((b(i6) + a(i6, i7, i10)) + a[i8]) + this.j[i8];
            i5 += i9;
            i9 += a(i2) + b(i2, i3, i4);
            i8++;
            i10 += ((b(i5) + a(i5, i6, i7)) + a[i8]) + this.j[i8];
            i4 += i10;
            i10 += a(i9) + b(i9, i2, i3);
            i8++;
        }
        this.b += i10;
        this.c += i9;
        this.d += i2;
        this.e += i3;
        this.f += i4;
        this.g += i5;
        this.h += i6;
        this.i += i7;
        this.k = 0;
        for (i = 0; i < 16; i++) {
            this.j[i] = 0;
        }
    }

    private int a(int i, int i2, int i3) {
        return (i & i2) ^ ((i ^ -1) & i3);
    }

    private int b(int i, int i2, int i3) {
        return ((i & i2) ^ (i & i3)) ^ (i2 & i3);
    }

    private int a(int i) {
        return (((i >>> 2) | (i << 30)) ^ ((i >>> 13) | (i << 19))) ^ ((i >>> 22) | (i << 10));
    }

    private int b(int i) {
        return (((i >>> 6) | (i << 26)) ^ ((i >>> 11) | (i << 21))) ^ ((i >>> 25) | (i << 7));
    }

    private int c(int i) {
        return (((i >>> 7) | (i << 25)) ^ ((i >>> 18) | (i << 14))) ^ (i >>> 3);
    }

    private int d(int i) {
        return (((i >>> 17) | (i << 15)) ^ ((i >>> 19) | (i << 13))) ^ (i >>> 10);
    }

    public Memoable e() {
        return new SHA224Digest(this);
    }

    public void a(Memoable memoable) {
        a((SHA224Digest) memoable);
    }
}
