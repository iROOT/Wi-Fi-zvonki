package org.spongycastle.crypto.digests;

import org.spongycastle.util.Memoable;

public class RIPEMD320Digest extends GeneralDigest {
    private int a;
    private int b;
    private int c;
    private int d;
    private int e;
    private int f;
    private int g;
    private int h;
    private int i;
    private int j;
    private int[] k;
    private int l;

    public RIPEMD320Digest() {
        this.k = new int[16];
        c();
    }

    public RIPEMD320Digest(RIPEMD320Digest rIPEMD320Digest) {
        super(rIPEMD320Digest);
        this.k = new int[16];
        a(rIPEMD320Digest);
    }

    private void a(RIPEMD320Digest rIPEMD320Digest) {
        super.a((GeneralDigest) rIPEMD320Digest);
        this.a = rIPEMD320Digest.a;
        this.b = rIPEMD320Digest.b;
        this.c = rIPEMD320Digest.c;
        this.d = rIPEMD320Digest.d;
        this.e = rIPEMD320Digest.e;
        this.f = rIPEMD320Digest.f;
        this.g = rIPEMD320Digest.g;
        this.h = rIPEMD320Digest.h;
        this.i = rIPEMD320Digest.i;
        this.j = rIPEMD320Digest.j;
        System.arraycopy(rIPEMD320Digest.k, 0, this.k, 0, rIPEMD320Digest.k.length);
        this.l = rIPEMD320Digest.l;
    }

    public String a() {
        return "RIPEMD320";
    }

    public int b() {
        return 40;
    }

    protected void b(byte[] bArr, int i) {
        int[] iArr = this.k;
        int i2 = this.l;
        this.l = i2 + 1;
        iArr[i2] = (((bArr[i] & 255) | ((bArr[i + 1] & 255) << 8)) | ((bArr[i + 2] & 255) << 16)) | ((bArr[i + 3] & 255) << 24);
        if (this.l == 16) {
            g();
        }
    }

    protected void a(long j) {
        if (this.l > 14) {
            g();
        }
        this.k[14] = (int) (-1 & j);
        this.k[15] = (int) (j >>> 32);
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
        a(this.i, bArr, i + 32);
        a(this.j, bArr, i + 36);
        c();
        return 40;
    }

    public void c() {
        super.c();
        this.a = 1732584193;
        this.b = -271733879;
        this.c = -1732584194;
        this.d = 271733878;
        this.e = -1009589776;
        this.f = 1985229328;
        this.g = -19088744;
        this.h = -1985229329;
        this.i = 19088743;
        this.j = 1009589775;
        this.l = 0;
        for (int i = 0; i != this.k.length; i++) {
            this.k[i] = 0;
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

    private int e(int i, int i2, int i3) {
        return ((i3 ^ -1) | i2) ^ i;
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
        int i9 = this.i;
        int i10 = this.j;
        i = a((i + a(i2, i3, i4)) + this.k[0], 11) + i5;
        i3 = a(i3, 10);
        i5 = a((i5 + a(i, i2, i3)) + this.k[1], 14) + i4;
        i2 = a(i2, 10);
        i4 = a((i4 + a(i5, i, i2)) + this.k[2], 15) + i3;
        i = a(i, 10);
        i3 = a((i3 + a(i4, i5, i)) + this.k[3], 12) + i2;
        i5 = a(i5, 10);
        i2 = a((i2 + a(i3, i4, i5)) + this.k[4], 5) + i;
        i4 = a(i4, 10);
        i = a((i + a(i2, i3, i4)) + this.k[5], 8) + i5;
        i3 = a(i3, 10);
        i5 = a((i5 + a(i, i2, i3)) + this.k[6], 7) + i4;
        i2 = a(i2, 10);
        i4 = a((i4 + a(i5, i, i2)) + this.k[7], 9) + i3;
        i = a(i, 10);
        i3 = a((i3 + a(i4, i5, i)) + this.k[8], 11) + i2;
        i5 = a(i5, 10);
        i2 = a((i2 + a(i3, i4, i5)) + this.k[9], 13) + i;
        i4 = a(i4, 10);
        i = a((i + a(i2, i3, i4)) + this.k[10], 14) + i5;
        i3 = a(i3, 10);
        i5 = a((i5 + a(i, i2, i3)) + this.k[11], 15) + i4;
        i2 = a(i2, 10);
        i4 = a((i4 + a(i5, i, i2)) + this.k[12], 6) + i3;
        i = a(i, 10);
        i3 = a((i3 + a(i4, i5, i)) + this.k[13], 7) + i2;
        i5 = a(i5, 10);
        i2 = a((i2 + a(i3, i4, i5)) + this.k[14], 9) + i;
        i4 = a(i4, 10);
        i = a((i + a(i2, i3, i4)) + this.k[15], 8) + i5;
        i3 = a(i3, 10);
        i6 = a(((i6 + e(i7, i8, i9)) + this.k[5]) + 1352829926, 8) + i10;
        i8 = a(i8, 10);
        i10 = a(((i10 + e(i6, i7, i8)) + this.k[14]) + 1352829926, 9) + i9;
        i7 = a(i7, 10);
        i9 = a(((i9 + e(i10, i6, i7)) + this.k[7]) + 1352829926, 9) + i8;
        i6 = a(i6, 10);
        i8 = a(((i8 + e(i9, i10, i6)) + this.k[0]) + 1352829926, 11) + i7;
        i10 = a(i10, 10);
        i7 = a(((i7 + e(i8, i9, i10)) + this.k[9]) + 1352829926, 13) + i6;
        i9 = a(i9, 10);
        i6 = a(((i6 + e(i7, i8, i9)) + this.k[2]) + 1352829926, 15) + i10;
        i8 = a(i8, 10);
        i10 = a(((i10 + e(i6, i7, i8)) + this.k[11]) + 1352829926, 15) + i9;
        i7 = a(i7, 10);
        i9 = a(((i9 + e(i10, i6, i7)) + this.k[4]) + 1352829926, 5) + i8;
        i6 = a(i6, 10);
        i8 = a(((i8 + e(i9, i10, i6)) + this.k[13]) + 1352829926, 7) + i7;
        i10 = a(i10, 10);
        i7 = a(((i7 + e(i8, i9, i10)) + this.k[6]) + 1352829926, 7) + i6;
        i9 = a(i9, 10);
        i6 = a(((i6 + e(i7, i8, i9)) + this.k[15]) + 1352829926, 8) + i10;
        i8 = a(i8, 10);
        i10 = a(((i10 + e(i6, i7, i8)) + this.k[8]) + 1352829926, 11) + i9;
        i7 = a(i7, 10);
        i9 = a(((i9 + e(i10, i6, i7)) + this.k[1]) + 1352829926, 14) + i8;
        i6 = a(i6, 10);
        i8 = a(((i8 + e(i9, i10, i6)) + this.k[10]) + 1352829926, 14) + i7;
        i10 = a(i10, 10);
        i7 = a(((i7 + e(i8, i9, i10)) + this.k[3]) + 1352829926, 12) + i6;
        i9 = a(i9, 10);
        i6 = a(((i6 + e(i7, i8, i9)) + this.k[12]) + 1352829926, 6) + i10;
        i8 = a(i8, 10);
        i5 = a(((i5 + b(i6, i2, i3)) + this.k[7]) + 1518500249, 7) + i4;
        i2 = a(i2, 10);
        i4 = a(((i4 + b(i5, i6, i2)) + this.k[4]) + 1518500249, 6) + i3;
        i6 = a(i6, 10);
        i3 = a(((i3 + b(i4, i5, i6)) + this.k[13]) + 1518500249, 8) + i2;
        i5 = a(i5, 10);
        i2 = a(((i2 + b(i3, i4, i5)) + this.k[1]) + 1518500249, 13) + i6;
        i4 = a(i4, 10);
        i6 = a(((i6 + b(i2, i3, i4)) + this.k[10]) + 1518500249, 11) + i5;
        i3 = a(i3, 10);
        i5 = a(((i5 + b(i6, i2, i3)) + this.k[6]) + 1518500249, 9) + i4;
        i2 = a(i2, 10);
        i4 = a(((i4 + b(i5, i6, i2)) + this.k[15]) + 1518500249, 7) + i3;
        i6 = a(i6, 10);
        i3 = a(((i3 + b(i4, i5, i6)) + this.k[3]) + 1518500249, 15) + i2;
        i5 = a(i5, 10);
        i2 = a(((i2 + b(i3, i4, i5)) + this.k[12]) + 1518500249, 7) + i6;
        i4 = a(i4, 10);
        i6 = a(((i6 + b(i2, i3, i4)) + this.k[0]) + 1518500249, 12) + i5;
        i3 = a(i3, 10);
        i5 = a(((i5 + b(i6, i2, i3)) + this.k[9]) + 1518500249, 15) + i4;
        i2 = a(i2, 10);
        i4 = a(((i4 + b(i5, i6, i2)) + this.k[5]) + 1518500249, 9) + i3;
        i6 = a(i6, 10);
        i3 = a(((i3 + b(i4, i5, i6)) + this.k[2]) + 1518500249, 11) + i2;
        i5 = a(i5, 10);
        i2 = a(((i2 + b(i3, i4, i5)) + this.k[14]) + 1518500249, 7) + i6;
        i4 = a(i4, 10);
        i6 = a(((i6 + b(i2, i3, i4)) + this.k[11]) + 1518500249, 13) + i5;
        i3 = a(i3, 10);
        i5 = a(((i5 + b(i6, i2, i3)) + this.k[8]) + 1518500249, 12) + i4;
        i2 = a(i2, 10);
        i10 = a(((i10 + d(i, i7, i8)) + this.k[6]) + 1548603684, 9) + i9;
        i7 = a(i7, 10);
        i9 = a(((i9 + d(i10, i, i7)) + this.k[11]) + 1548603684, 13) + i8;
        i = a(i, 10);
        i8 = a(((i8 + d(i9, i10, i)) + this.k[3]) + 1548603684, 15) + i7;
        i10 = a(i10, 10);
        i7 = a(((i7 + d(i8, i9, i10)) + this.k[7]) + 1548603684, 7) + i;
        i9 = a(i9, 10);
        i = a(((i + d(i7, i8, i9)) + this.k[0]) + 1548603684, 12) + i10;
        i8 = a(i8, 10);
        i10 = a(((i10 + d(i, i7, i8)) + this.k[13]) + 1548603684, 8) + i9;
        i7 = a(i7, 10);
        i9 = a(((i9 + d(i10, i, i7)) + this.k[5]) + 1548603684, 9) + i8;
        i = a(i, 10);
        i8 = a(((i8 + d(i9, i10, i)) + this.k[10]) + 1548603684, 11) + i7;
        i10 = a(i10, 10);
        i7 = a(((i7 + d(i8, i9, i10)) + this.k[14]) + 1548603684, 7) + i;
        i9 = a(i9, 10);
        i = a(((i + d(i7, i8, i9)) + this.k[15]) + 1548603684, 7) + i10;
        i8 = a(i8, 10);
        i10 = a(((i10 + d(i, i7, i8)) + this.k[8]) + 1548603684, 12) + i9;
        i7 = a(i7, 10);
        i9 = a(((i9 + d(i10, i, i7)) + this.k[12]) + 1548603684, 7) + i8;
        i = a(i, 10);
        i8 = a(((i8 + d(i9, i10, i)) + this.k[4]) + 1548603684, 6) + i7;
        i10 = a(i10, 10);
        i7 = a(((i7 + d(i8, i9, i10)) + this.k[9]) + 1548603684, 15) + i;
        i9 = a(i9, 10);
        i = a(((i + d(i7, i8, i9)) + this.k[1]) + 1548603684, 13) + i10;
        i8 = a(i8, 10);
        i10 = a(((i10 + d(i, i7, i8)) + this.k[2]) + 1548603684, 11) + i9;
        i7 = a(i7, 10);
        i4 = a(((i4 + c(i5, i6, i7)) + this.k[3]) + 1859775393, 11) + i3;
        i6 = a(i6, 10);
        i3 = a(((i3 + c(i4, i5, i6)) + this.k[10]) + 1859775393, 13) + i7;
        i5 = a(i5, 10);
        i7 = a(((i7 + c(i3, i4, i5)) + this.k[14]) + 1859775393, 6) + i6;
        i4 = a(i4, 10);
        i6 = a(((i6 + c(i7, i3, i4)) + this.k[4]) + 1859775393, 7) + i5;
        i3 = a(i3, 10);
        i5 = a(((i5 + c(i6, i7, i3)) + this.k[9]) + 1859775393, 14) + i4;
        i7 = a(i7, 10);
        i4 = a(((i4 + c(i5, i6, i7)) + this.k[15]) + 1859775393, 9) + i3;
        i6 = a(i6, 10);
        i3 = a(((i3 + c(i4, i5, i6)) + this.k[8]) + 1859775393, 13) + i7;
        i5 = a(i5, 10);
        i7 = a(((i7 + c(i3, i4, i5)) + this.k[1]) + 1859775393, 15) + i6;
        i4 = a(i4, 10);
        i6 = a(((i6 + c(i7, i3, i4)) + this.k[2]) + 1859775393, 14) + i5;
        i3 = a(i3, 10);
        i5 = a(((i5 + c(i6, i7, i3)) + this.k[7]) + 1859775393, 8) + i4;
        i7 = a(i7, 10);
        i4 = a(((i4 + c(i5, i6, i7)) + this.k[0]) + 1859775393, 13) + i3;
        i6 = a(i6, 10);
        i3 = a(((i3 + c(i4, i5, i6)) + this.k[6]) + 1859775393, 6) + i7;
        i5 = a(i5, 10);
        i7 = a(((i7 + c(i3, i4, i5)) + this.k[13]) + 1859775393, 5) + i6;
        i4 = a(i4, 10);
        i6 = a(((i6 + c(i7, i3, i4)) + this.k[11]) + 1859775393, 12) + i5;
        i3 = a(i3, 10);
        i5 = a(((i5 + c(i6, i7, i3)) + this.k[5]) + 1859775393, 7) + i4;
        i7 = a(i7, 10);
        i4 = a(((i4 + c(i5, i6, i7)) + this.k[12]) + 1859775393, 5) + i3;
        i6 = a(i6, 10);
        i9 = a(((i9 + c(i10, i, i2)) + this.k[15]) + 1836072691, 9) + i8;
        i = a(i, 10);
        i8 = a(((i8 + c(i9, i10, i)) + this.k[5]) + 1836072691, 7) + i2;
        i10 = a(i10, 10);
        i2 = a(((i2 + c(i8, i9, i10)) + this.k[1]) + 1836072691, 15) + i;
        i9 = a(i9, 10);
        i = a(((i + c(i2, i8, i9)) + this.k[3]) + 1836072691, 11) + i10;
        i8 = a(i8, 10);
        i10 = a(((i10 + c(i, i2, i8)) + this.k[7]) + 1836072691, 8) + i9;
        i2 = a(i2, 10);
        i9 = a(((i9 + c(i10, i, i2)) + this.k[14]) + 1836072691, 6) + i8;
        i = a(i, 10);
        i8 = a(((i8 + c(i9, i10, i)) + this.k[6]) + 1836072691, 6) + i2;
        i10 = a(i10, 10);
        i2 = a(((i2 + c(i8, i9, i10)) + this.k[9]) + 1836072691, 14) + i;
        i9 = a(i9, 10);
        i = a(((i + c(i2, i8, i9)) + this.k[11]) + 1836072691, 12) + i10;
        i8 = a(i8, 10);
        i10 = a(((i10 + c(i, i2, i8)) + this.k[8]) + 1836072691, 13) + i9;
        i2 = a(i2, 10);
        i9 = a(((i9 + c(i10, i, i2)) + this.k[12]) + 1836072691, 5) + i8;
        i = a(i, 10);
        i8 = a(((i8 + c(i9, i10, i)) + this.k[2]) + 1836072691, 14) + i2;
        i10 = a(i10, 10);
        i2 = a(((i2 + c(i8, i9, i10)) + this.k[10]) + 1836072691, 13) + i;
        i9 = a(i9, 10);
        i = a(((i + c(i2, i8, i9)) + this.k[0]) + 1836072691, 13) + i10;
        i8 = a(i8, 10);
        i10 = a(((i10 + c(i, i2, i8)) + this.k[4]) + 1836072691, 7) + i9;
        i2 = a(i2, 10);
        i9 = a(((i9 + c(i10, i, i2)) + this.k[13]) + 1836072691, 5) + i8;
        i = a(i, 10);
        i8 = a(((i8 + d(i4, i5, i6)) + this.k[1]) - 1894007588, 11) + i7;
        i5 = a(i5, 10);
        i7 = a(((i7 + d(i8, i4, i5)) + this.k[9]) - 1894007588, 12) + i6;
        i4 = a(i4, 10);
        i6 = a(((i6 + d(i7, i8, i4)) + this.k[11]) - 1894007588, 14) + i5;
        i8 = a(i8, 10);
        i5 = a(((i5 + d(i6, i7, i8)) + this.k[10]) - 1894007588, 15) + i4;
        i7 = a(i7, 10);
        i4 = a(((i4 + d(i5, i6, i7)) + this.k[0]) - 1894007588, 14) + i8;
        i6 = a(i6, 10);
        i8 = a(((i8 + d(i4, i5, i6)) + this.k[8]) - 1894007588, 15) + i7;
        i5 = a(i5, 10);
        i7 = a(((i7 + d(i8, i4, i5)) + this.k[12]) - 1894007588, 9) + i6;
        i4 = a(i4, 10);
        i6 = a(((i6 + d(i7, i8, i4)) + this.k[4]) - 1894007588, 8) + i5;
        i8 = a(i8, 10);
        i5 = a(((i5 + d(i6, i7, i8)) + this.k[13]) - 1894007588, 9) + i4;
        i7 = a(i7, 10);
        i4 = a(((i4 + d(i5, i6, i7)) + this.k[3]) - 1894007588, 14) + i8;
        i6 = a(i6, 10);
        i8 = a(((i8 + d(i4, i5, i6)) + this.k[7]) - 1894007588, 5) + i7;
        i5 = a(i5, 10);
        i7 = a(((i7 + d(i8, i4, i5)) + this.k[15]) - 1894007588, 6) + i6;
        i4 = a(i4, 10);
        i6 = a(((i6 + d(i7, i8, i4)) + this.k[14]) - 1894007588, 8) + i5;
        i8 = a(i8, 10);
        i5 = a(((i5 + d(i6, i7, i8)) + this.k[5]) - 1894007588, 6) + i4;
        i7 = a(i7, 10);
        i4 = a(((i4 + d(i5, i6, i7)) + this.k[6]) - 1894007588, 5) + i8;
        i6 = a(i6, 10);
        i8 = a(((i8 + d(i4, i5, i6)) + this.k[2]) - 1894007588, 12) + i7;
        i5 = a(i5, 10);
        i3 = a(((i3 + b(i9, i10, i)) + this.k[8]) + 2053994217, 15) + i2;
        i10 = a(i10, 10);
        i2 = a(((i2 + b(i3, i9, i10)) + this.k[6]) + 2053994217, 5) + i;
        i9 = a(i9, 10);
        i = a(((i + b(i2, i3, i9)) + this.k[4]) + 2053994217, 8) + i10;
        i3 = a(i3, 10);
        i10 = a(((i10 + b(i, i2, i3)) + this.k[1]) + 2053994217, 11) + i9;
        i2 = a(i2, 10);
        i9 = a(((i9 + b(i10, i, i2)) + this.k[3]) + 2053994217, 14) + i3;
        i = a(i, 10);
        i3 = a(((i3 + b(i9, i10, i)) + this.k[11]) + 2053994217, 14) + i2;
        i10 = a(i10, 10);
        i2 = a(((i2 + b(i3, i9, i10)) + this.k[15]) + 2053994217, 6) + i;
        i9 = a(i9, 10);
        i = a(((i + b(i2, i3, i9)) + this.k[0]) + 2053994217, 14) + i10;
        i3 = a(i3, 10);
        i10 = a(((i10 + b(i, i2, i3)) + this.k[5]) + 2053994217, 6) + i9;
        i2 = a(i2, 10);
        i9 = a(((i9 + b(i10, i, i2)) + this.k[12]) + 2053994217, 9) + i3;
        i = a(i, 10);
        i3 = a(((i3 + b(i9, i10, i)) + this.k[2]) + 2053994217, 12) + i2;
        i10 = a(i10, 10);
        i2 = a(((i2 + b(i3, i9, i10)) + this.k[13]) + 2053994217, 9) + i;
        i9 = a(i9, 10);
        i = a(((i + b(i2, i3, i9)) + this.k[9]) + 2053994217, 12) + i10;
        i3 = a(i3, 10);
        i10 = a(((i10 + b(i, i2, i3)) + this.k[7]) + 2053994217, 5) + i9;
        i2 = a(i2, 10);
        i9 = a(((i9 + b(i10, i, i2)) + this.k[10]) + 2053994217, 15) + i3;
        i = a(i, 10);
        i3 = a(((i3 + b(i9, i10, i)) + this.k[14]) + 2053994217, 8) + i2;
        i10 = a(i10, 10);
        i7 = a(((i7 + e(i8, i9, i5)) + this.k[4]) - 1454113458, 9) + i6;
        i9 = a(i9, 10);
        i6 = a(((i6 + e(i7, i8, i9)) + this.k[0]) - 1454113458, 15) + i5;
        i8 = a(i8, 10);
        i5 = a(((i5 + e(i6, i7, i8)) + this.k[5]) - 1454113458, 5) + i9;
        i7 = a(i7, 10);
        i9 = a(((i9 + e(i5, i6, i7)) + this.k[9]) - 1454113458, 11) + i8;
        i6 = a(i6, 10);
        i8 = a(((i8 + e(i9, i5, i6)) + this.k[7]) - 1454113458, 6) + i7;
        i5 = a(i5, 10);
        i7 = a(((i7 + e(i8, i9, i5)) + this.k[12]) - 1454113458, 8) + i6;
        i9 = a(i9, 10);
        i6 = a(((i6 + e(i7, i8, i9)) + this.k[2]) - 1454113458, 13) + i5;
        i8 = a(i8, 10);
        i5 = a(((i5 + e(i6, i7, i8)) + this.k[10]) - 1454113458, 12) + i9;
        i7 = a(i7, 10);
        i9 = a(((i9 + e(i5, i6, i7)) + this.k[14]) - 1454113458, 5) + i8;
        i6 = a(i6, 10);
        i8 = a(((i8 + e(i9, i5, i6)) + this.k[1]) - 1454113458, 12) + i7;
        i5 = a(i5, 10);
        i7 = a(((i7 + e(i8, i9, i5)) + this.k[3]) - 1454113458, 13) + i6;
        i9 = a(i9, 10);
        i6 = a(((i6 + e(i7, i8, i9)) + this.k[8]) - 1454113458, 14) + i5;
        i8 = a(i8, 10);
        i5 = a(((i5 + e(i6, i7, i8)) + this.k[11]) - 1454113458, 11) + i9;
        i7 = a(i7, 10);
        i9 = a(((i9 + e(i5, i6, i7)) + this.k[6]) - 1454113458, 8) + i8;
        i6 = a(i6, 10);
        i8 = a(((i8 + e(i9, i5, i6)) + this.k[15]) - 1454113458, 5) + i7;
        i5 = a(i5, 10);
        i7 = a(((i7 + e(i8, i9, i5)) + this.k[13]) - 1454113458, 6) + i6;
        i9 = a(i9, 10);
        i2 = a((i2 + a(i3, i4, i10)) + this.k[12], 8) + i;
        i4 = a(i4, 10);
        i = a((i + a(i2, i3, i4)) + this.k[15], 5) + i10;
        i3 = a(i3, 10);
        i10 = a((i10 + a(i, i2, i3)) + this.k[10], 12) + i4;
        i2 = a(i2, 10);
        i4 = a((i4 + a(i10, i, i2)) + this.k[4], 9) + i3;
        i = a(i, 10);
        i3 = a((i3 + a(i4, i10, i)) + this.k[1], 12) + i2;
        i10 = a(i10, 10);
        i2 = a((i2 + a(i3, i4, i10)) + this.k[5], 5) + i;
        i4 = a(i4, 10);
        i = a((i + a(i2, i3, i4)) + this.k[8], 14) + i10;
        i3 = a(i3, 10);
        i10 = a((i10 + a(i, i2, i3)) + this.k[7], 6) + i4;
        i2 = a(i2, 10);
        i4 = a((i4 + a(i10, i, i2)) + this.k[6], 8) + i3;
        i = a(i, 10);
        i3 = a((i3 + a(i4, i10, i)) + this.k[2], 13) + i2;
        i10 = a(i10, 10);
        i2 = a((i2 + a(i3, i4, i10)) + this.k[13], 6) + i;
        i4 = a(i4, 10);
        i = a((i + a(i2, i3, i4)) + this.k[14], 5) + i10;
        i3 = a(i3, 10);
        i10 = a((i10 + a(i, i2, i3)) + this.k[0], 15) + i4;
        i2 = a(i2, 10);
        i4 = a((i4 + a(i10, i, i2)) + this.k[3], 13) + i3;
        i = a(i, 10);
        i3 = a((i3 + a(i4, i10, i)) + this.k[9], 11) + i2;
        i10 = a(i10, 10);
        i2 = a((i2 + a(i3, i4, i10)) + this.k[11], 11) + i;
        i4 = a(i4, 10);
        this.a = i6 + this.a;
        this.b += i7;
        this.c += i8;
        this.d += i9;
        this.e += i10;
        this.f = i + this.f;
        this.g += i2;
        this.h += i3;
        this.i += i4;
        this.j += i5;
        this.l = 0;
        for (i = 0; i != this.k.length; i++) {
            this.k[i] = 0;
        }
    }

    public Memoable e() {
        return new RIPEMD320Digest(this);
    }

    public void a(Memoable memoable) {
        a((RIPEMD320Digest) memoable);
    }
}
