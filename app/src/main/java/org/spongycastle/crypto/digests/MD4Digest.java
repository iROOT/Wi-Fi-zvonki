package org.spongycastle.crypto.digests;

import org.spongycastle.util.Memoable;

public class MD4Digest extends GeneralDigest {
    private int a;
    private int b;
    private int c;
    private int d;
    private int[] e;
    private int f;

    public MD4Digest() {
        this.e = new int[16];
        c();
    }

    public MD4Digest(MD4Digest mD4Digest) {
        super(mD4Digest);
        this.e = new int[16];
        a(mD4Digest);
    }

    private void a(MD4Digest mD4Digest) {
        super.a((GeneralDigest) mD4Digest);
        this.a = mD4Digest.a;
        this.b = mD4Digest.b;
        this.c = mD4Digest.c;
        this.d = mD4Digest.d;
        System.arraycopy(mD4Digest.e, 0, this.e, 0, mD4Digest.e.length);
        this.f = mD4Digest.f;
    }

    public String a() {
        return "MD4";
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
        return (i & i2) | ((i ^ -1) & i3);
    }

    private int b(int i, int i2, int i3) {
        return ((i & i2) | (i & i3)) | (i2 & i3);
    }

    private int c(int i, int i2, int i3) {
        return (i ^ i2) ^ i3;
    }

    protected void g() {
        int i = this.a;
        int i2 = this.b;
        int i3 = this.c;
        int i4 = this.d;
        i = a((i + a(i2, i3, i4)) + this.e[0], 3);
        i4 = a((i4 + a(i, i2, i3)) + this.e[1], 7);
        i3 = a((i3 + a(i4, i, i2)) + this.e[2], 11);
        i2 = a((i2 + a(i3, i4, i)) + this.e[3], 19);
        i = a((i + a(i2, i3, i4)) + this.e[4], 3);
        i4 = a((i4 + a(i, i2, i3)) + this.e[5], 7);
        i3 = a((i3 + a(i4, i, i2)) + this.e[6], 11);
        i2 = a((i2 + a(i3, i4, i)) + this.e[7], 19);
        i = a((i + a(i2, i3, i4)) + this.e[8], 3);
        i4 = a((i4 + a(i, i2, i3)) + this.e[9], 7);
        i3 = a((i3 + a(i4, i, i2)) + this.e[10], 11);
        i2 = a((i2 + a(i3, i4, i)) + this.e[11], 19);
        i = a((i + a(i2, i3, i4)) + this.e[12], 3);
        i4 = a((i4 + a(i, i2, i3)) + this.e[13], 7);
        i3 = a((i3 + a(i4, i, i2)) + this.e[14], 11);
        i2 = a((i2 + a(i3, i4, i)) + this.e[15], 19);
        i = a(((i + b(i2, i3, i4)) + this.e[0]) + 1518500249, 3);
        i4 = a(((i4 + b(i, i2, i3)) + this.e[4]) + 1518500249, 5);
        i3 = a(((i3 + b(i4, i, i2)) + this.e[8]) + 1518500249, 9);
        i2 = a(((i2 + b(i3, i4, i)) + this.e[12]) + 1518500249, 13);
        i = a(((i + b(i2, i3, i4)) + this.e[1]) + 1518500249, 3);
        i4 = a(((i4 + b(i, i2, i3)) + this.e[5]) + 1518500249, 5);
        i3 = a(((i3 + b(i4, i, i2)) + this.e[9]) + 1518500249, 9);
        i2 = a(((i2 + b(i3, i4, i)) + this.e[13]) + 1518500249, 13);
        i = a(((i + b(i2, i3, i4)) + this.e[2]) + 1518500249, 3);
        i4 = a(((i4 + b(i, i2, i3)) + this.e[6]) + 1518500249, 5);
        i3 = a(((i3 + b(i4, i, i2)) + this.e[10]) + 1518500249, 9);
        i2 = a(((i2 + b(i3, i4, i)) + this.e[14]) + 1518500249, 13);
        i = a(((i + b(i2, i3, i4)) + this.e[3]) + 1518500249, 3);
        i4 = a(((i4 + b(i, i2, i3)) + this.e[7]) + 1518500249, 5);
        i3 = a(((i3 + b(i4, i, i2)) + this.e[11]) + 1518500249, 9);
        i2 = a(((i2 + b(i3, i4, i)) + this.e[15]) + 1518500249, 13);
        i = a(((i + c(i2, i3, i4)) + this.e[0]) + 1859775393, 3);
        i4 = a(((i4 + c(i, i2, i3)) + this.e[8]) + 1859775393, 9);
        i3 = a(((i3 + c(i4, i, i2)) + this.e[4]) + 1859775393, 11);
        i2 = a(((i2 + c(i3, i4, i)) + this.e[12]) + 1859775393, 15);
        i = a(((i + c(i2, i3, i4)) + this.e[2]) + 1859775393, 3);
        i4 = a(((i4 + c(i, i2, i3)) + this.e[10]) + 1859775393, 9);
        i3 = a(((i3 + c(i4, i, i2)) + this.e[6]) + 1859775393, 11);
        i2 = a(((i2 + c(i3, i4, i)) + this.e[14]) + 1859775393, 15);
        i = a(((i + c(i2, i3, i4)) + this.e[1]) + 1859775393, 3);
        i4 = a(((i4 + c(i, i2, i3)) + this.e[9]) + 1859775393, 9);
        i3 = a(((i3 + c(i4, i, i2)) + this.e[5]) + 1859775393, 11);
        i2 = a(((i2 + c(i3, i4, i)) + this.e[13]) + 1859775393, 15);
        i = a(((i + c(i2, i3, i4)) + this.e[3]) + 1859775393, 3);
        i4 = a(((i4 + c(i, i2, i3)) + this.e[11]) + 1859775393, 9);
        i3 = a(((i3 + c(i4, i, i2)) + this.e[7]) + 1859775393, 11);
        i2 = a(((i2 + c(i3, i4, i)) + this.e[15]) + 1859775393, 15);
        this.a = i + this.a;
        this.b += i2;
        this.c += i3;
        this.d += i4;
        this.f = 0;
        for (i = 0; i != this.e.length; i++) {
            this.e[i] = 0;
        }
    }

    public Memoable e() {
        return new MD4Digest(this);
    }

    public void a(Memoable memoable) {
        a((MD4Digest) memoable);
    }
}
