package org.spongycastle.crypto.digests;

import org.spongycastle.crypto.util.Pack;
import org.spongycastle.util.Memoable;

public class SM3Digest extends GeneralDigest {
    private static final int[] f = new int[64];
    private int[] a;
    private int[] b;
    private int c;
    private int[] d;
    private int[] e;

    static {
        int i;
        int i2 = 16;
        for (i = 0; i < 16; i++) {
            f[i] = (2043430169 >>> (32 - i)) | (2043430169 << i);
        }
        while (i2 < 64) {
            i = i2 % 32;
            f[i2] = (2055708042 >>> (32 - i)) | (2055708042 << i);
            i2++;
        }
    }

    public SM3Digest() {
        this.a = new int[8];
        this.b = new int[16];
        this.d = new int[68];
        this.e = new int[64];
        c();
    }

    public SM3Digest(SM3Digest sM3Digest) {
        super(sM3Digest);
        this.a = new int[8];
        this.b = new int[16];
        this.d = new int[68];
        this.e = new int[64];
        a(sM3Digest);
    }

    private void a(SM3Digest sM3Digest) {
        System.arraycopy(sM3Digest.a, 0, this.a, 0, this.a.length);
        System.arraycopy(sM3Digest.b, 0, this.b, 0, this.b.length);
        this.c = sM3Digest.c;
    }

    public String a() {
        return "SM3";
    }

    public int b() {
        return 32;
    }

    public Memoable e() {
        return new SM3Digest(this);
    }

    public void a(Memoable memoable) {
        SM3Digest sM3Digest = (SM3Digest) memoable;
        super.a((GeneralDigest) sM3Digest);
        a(sM3Digest);
    }

    public void c() {
        super.c();
        this.a[0] = 1937774191;
        this.a[1] = 1226093241;
        this.a[2] = 388252375;
        this.a[3] = -628488704;
        this.a[4] = -1452330820;
        this.a[5] = 372324522;
        this.a[6] = -477237683;
        this.a[7] = -1325724082;
        this.c = 0;
    }

    public int a(byte[] bArr, int i) {
        f();
        Pack.a(this.a[0], bArr, i + 0);
        Pack.a(this.a[1], bArr, i + 4);
        Pack.a(this.a[2], bArr, i + 8);
        Pack.a(this.a[3], bArr, i + 12);
        Pack.a(this.a[4], bArr, i + 16);
        Pack.a(this.a[5], bArr, i + 20);
        Pack.a(this.a[6], bArr, i + 24);
        Pack.a(this.a[7], bArr, i + 28);
        c();
        return 32;
    }

    protected void b(byte[] bArr, int i) {
        int i2 = i + 1;
        i2++;
        this.b[this.c] = ((((bArr[i] & 255) << 24) | ((bArr[i2] & 255) << 16)) | ((bArr[i2] & 255) << 8)) | (bArr[i2 + 1] & 255);
        this.c++;
        if (this.c >= 16) {
            g();
        }
    }

    protected void a(long j) {
        if (this.c > 14) {
            this.b[this.c] = 0;
            this.c++;
            g();
        }
        while (this.c < 14) {
            this.b[this.c] = 0;
            this.c++;
        }
        int[] iArr = this.b;
        int i = this.c;
        this.c = i + 1;
        iArr[i] = (int) (j >>> 32);
        iArr = this.b;
        i = this.c;
        this.c = i + 1;
        iArr[i] = (int) j;
    }

    private int a(int i) {
        return (((i << 9) | (i >>> 23)) ^ i) ^ ((i << 17) | (i >>> 15));
    }

    private int b(int i) {
        return (((i << 15) | (i >>> 17)) ^ i) ^ ((i << 23) | (i >>> 9));
    }

    private int a(int i, int i2, int i3) {
        return (i ^ i2) ^ i3;
    }

    private int b(int i, int i2, int i3) {
        return ((i & i2) | (i & i3)) | (i2 & i3);
    }

    private int c(int i, int i2, int i3) {
        return (i ^ i2) ^ i3;
    }

    private int d(int i, int i2, int i3) {
        return (i & i2) | ((i ^ -1) & i3);
    }

    protected void g() {
        int i;
        int i2;
        int i3;
        for (i = 0; i < 16; i++) {
            this.d[i] = this.b[i];
        }
        for (i = 16; i < 68; i++) {
            i2 = this.d[i - 3];
            i2 = (i2 >>> 17) | (i2 << 15);
            i3 = this.d[i - 13];
            this.d[i] = (b(i2 ^ (this.d[i - 16] ^ this.d[i - 9])) ^ ((i3 >>> 25) | (i3 << 7))) ^ this.d[i - 6];
        }
        for (i = 0; i < 64; i++) {
            this.e[i] = this.d[i] ^ this.d[i + 4];
        }
        int i4 = this.a[0];
        int i5 = this.a[1];
        int i6 = this.a[2];
        int i7 = this.a[3];
        int i8 = this.a[4];
        int i9 = this.a[5];
        i3 = this.a[6];
        i2 = this.a[7];
        i = 0;
        while (i < 16) {
            int i10 = (i4 << 12) | (i4 >>> 20);
            int i11 = (i10 + i8) + f[i];
            i11 = (i11 >>> 25) | (i11 << 7);
            i10 = this.e[i] + ((i7 + a(i4, i5, i6)) + (i10 ^ i11));
            i11 = ((i2 + c(i8, i9, i3)) + i11) + this.d[i];
            i7 = (i5 >>> 23) | (i5 << 9);
            i2 = (i9 << 19) | (i9 >>> 13);
            i++;
            i5 = i4;
            i4 = i10;
            int i12 = i8;
            i8 = a(i11);
            i9 = i12;
            int i13 = i6;
            i6 = i7;
            i7 = i13;
            int i14 = i3;
            i3 = i2;
            i2 = i14;
        }
        i = 16;
        while (i < 64) {
            i10 = (i4 << 12) | (i4 >>> 20);
            i11 = (i10 + i8) + f[i];
            i11 = (i11 >>> 25) | (i11 << 7);
            i10 = this.e[i] + ((i7 + b(i4, i5, i6)) + (i10 ^ i11));
            i11 = ((i2 + d(i8, i9, i3)) + i11) + this.d[i];
            i7 = (i5 >>> 23) | (i5 << 9);
            i2 = (i9 << 19) | (i9 >>> 13);
            i++;
            i5 = i4;
            i4 = i10;
            i12 = i8;
            i8 = a(i11);
            i9 = i12;
            i13 = i6;
            i6 = i7;
            i7 = i13;
            i14 = i3;
            i3 = i2;
            i2 = i14;
        }
        int[] iArr = this.a;
        iArr[0] = i4 ^ iArr[0];
        iArr = this.a;
        iArr[1] = i5 ^ iArr[1];
        iArr = this.a;
        iArr[2] = i6 ^ iArr[2];
        iArr = this.a;
        iArr[3] = i7 ^ iArr[3];
        iArr = this.a;
        iArr[4] = i8 ^ iArr[4];
        iArr = this.a;
        iArr[5] = i9 ^ iArr[5];
        iArr = this.a;
        iArr[6] = i3 ^ iArr[6];
        iArr = this.a;
        iArr[7] = i2 ^ iArr[7];
        this.c = 0;
    }
}
