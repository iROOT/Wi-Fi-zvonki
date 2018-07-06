package org.spongycastle.crypto.digests;

import org.spongycastle.util.Memoable;

public class RIPEMD160Digest extends GeneralDigest {
    private int a;
    private int b;
    private int c;
    private int d;
    private int e;
    private int[] f;
    private int g;

    public RIPEMD160Digest() {
        this.f = new int[16];
        c();
    }

    public RIPEMD160Digest(RIPEMD160Digest rIPEMD160Digest) {
        super(rIPEMD160Digest);
        this.f = new int[16];
        a(rIPEMD160Digest);
    }

    private void a(RIPEMD160Digest rIPEMD160Digest) {
        super.a((GeneralDigest) rIPEMD160Digest);
        this.a = rIPEMD160Digest.a;
        this.b = rIPEMD160Digest.b;
        this.c = rIPEMD160Digest.c;
        this.d = rIPEMD160Digest.d;
        this.e = rIPEMD160Digest.e;
        System.arraycopy(rIPEMD160Digest.f, 0, this.f, 0, rIPEMD160Digest.f.length);
        this.g = rIPEMD160Digest.g;
    }

    public String a() {
        return "RIPEMD160";
    }

    public int b() {
        return 20;
    }

    protected void b(byte[] bArr, int i) {
        int[] iArr = this.f;
        int i2 = this.g;
        this.g = i2 + 1;
        iArr[i2] = (((bArr[i] & 255) | ((bArr[i + 1] & 255) << 8)) | ((bArr[i + 2] & 255) << 16)) | ((bArr[i + 3] & 255) << 24);
        if (this.g == 16) {
            g();
        }
    }

    protected void a(long j) {
        if (this.g > 14) {
            g();
        }
        this.f[14] = (int) (-1 & j);
        this.f[15] = (int) (j >>> 32);
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
        int a = a((a(i2, i3, i4) + i) + this.f[0], 11) + i5;
        int a2 = a(i3, 10);
        int a3 = a((a(a, i2, a2) + i5) + this.f[1], 14) + i4;
        int a4 = a(i2, 10);
        int a5 = a((a(a3, a, a4) + i4) + this.f[2], 15) + a2;
        a = a(a, 10);
        a2 = a((a2 + a(a5, a3, a)) + this.f[3], 12) + a4;
        a3 = a(a3, 10);
        a4 = a((a4 + a(a2, a5, a3)) + this.f[4], 5) + a;
        a5 = a(a5, 10);
        a = a((a + a(a4, a2, a5)) + this.f[5], 8) + a3;
        a2 = a(a2, 10);
        a3 = a((a3 + a(a, a4, a2)) + this.f[6], 7) + a5;
        a4 = a(a4, 10);
        a5 = a((a5 + a(a3, a, a4)) + this.f[7], 9) + a2;
        a = a(a, 10);
        a2 = a((a2 + a(a5, a3, a)) + this.f[8], 11) + a4;
        a3 = a(a3, 10);
        a4 = a((a4 + a(a2, a5, a3)) + this.f[9], 13) + a;
        a5 = a(a5, 10);
        a = a((a + a(a4, a2, a5)) + this.f[10], 14) + a3;
        a2 = a(a2, 10);
        a3 = a((a3 + a(a, a4, a2)) + this.f[11], 15) + a5;
        a4 = a(a4, 10);
        a5 = a((a5 + a(a3, a, a4)) + this.f[12], 6) + a2;
        a = a(a, 10);
        a2 = a((a2 + a(a5, a3, a)) + this.f[13], 7) + a4;
        a3 = a(a3, 10);
        a4 = a((a4 + a(a2, a5, a3)) + this.f[14], 9) + a;
        a5 = a(a5, 10);
        a = a((a + a(a4, a2, a5)) + this.f[15], 8) + a3;
        a2 = a(a2, 10);
        i = a(((i + e(i2, i3, i4)) + this.f[5]) + 1352829926, 8) + i5;
        i3 = a(i3, 10);
        i5 = a(((i5 + e(i, i2, i3)) + this.f[14]) + 1352829926, 9) + i4;
        i2 = a(i2, 10);
        i4 = a(((i4 + e(i5, i, i2)) + this.f[7]) + 1352829926, 9) + i3;
        i = a(i, 10);
        i3 = a(((i3 + e(i4, i5, i)) + this.f[0]) + 1352829926, 11) + i2;
        i5 = a(i5, 10);
        i2 = a(((i2 + e(i3, i4, i5)) + this.f[9]) + 1352829926, 13) + i;
        i4 = a(i4, 10);
        i = a(((i + e(i2, i3, i4)) + this.f[2]) + 1352829926, 15) + i5;
        i3 = a(i3, 10);
        i5 = a(((i5 + e(i, i2, i3)) + this.f[11]) + 1352829926, 15) + i4;
        i2 = a(i2, 10);
        i4 = a(((i4 + e(i5, i, i2)) + this.f[4]) + 1352829926, 5) + i3;
        i = a(i, 10);
        i3 = a(((i3 + e(i4, i5, i)) + this.f[13]) + 1352829926, 7) + i2;
        i5 = a(i5, 10);
        i2 = a(((i2 + e(i3, i4, i5)) + this.f[6]) + 1352829926, 7) + i;
        i4 = a(i4, 10);
        i = a(((i + e(i2, i3, i4)) + this.f[15]) + 1352829926, 8) + i5;
        i3 = a(i3, 10);
        i5 = a(((i5 + e(i, i2, i3)) + this.f[8]) + 1352829926, 11) + i4;
        i2 = a(i2, 10);
        i4 = a(((i4 + e(i5, i, i2)) + this.f[1]) + 1352829926, 14) + i3;
        i = a(i, 10);
        i3 = a(((i3 + e(i4, i5, i)) + this.f[10]) + 1352829926, 14) + i2;
        i5 = a(i5, 10);
        i2 = a(((i2 + e(i3, i4, i5)) + this.f[3]) + 1352829926, 12) + i;
        i4 = a(i4, 10);
        i = a(((i + e(i2, i3, i4)) + this.f[12]) + 1352829926, 6) + i5;
        i3 = a(i3, 10);
        a3 = a(((a3 + b(a, a4, a2)) + this.f[7]) + 1518500249, 7) + a5;
        a4 = a(a4, 10);
        a5 = a(((a5 + b(a3, a, a4)) + this.f[4]) + 1518500249, 6) + a2;
        a = a(a, 10);
        a2 = a(((a2 + b(a5, a3, a)) + this.f[13]) + 1518500249, 8) + a4;
        a3 = a(a3, 10);
        a4 = a(((a4 + b(a2, a5, a3)) + this.f[1]) + 1518500249, 13) + a;
        a5 = a(a5, 10);
        a = a(((a + b(a4, a2, a5)) + this.f[10]) + 1518500249, 11) + a3;
        a2 = a(a2, 10);
        a3 = a(((a3 + b(a, a4, a2)) + this.f[6]) + 1518500249, 9) + a5;
        a4 = a(a4, 10);
        a5 = a(((a5 + b(a3, a, a4)) + this.f[15]) + 1518500249, 7) + a2;
        a = a(a, 10);
        a2 = a(((a2 + b(a5, a3, a)) + this.f[3]) + 1518500249, 15) + a4;
        a3 = a(a3, 10);
        a4 = a(((a4 + b(a2, a5, a3)) + this.f[12]) + 1518500249, 7) + a;
        a5 = a(a5, 10);
        a = a(((a + b(a4, a2, a5)) + this.f[0]) + 1518500249, 12) + a3;
        a2 = a(a2, 10);
        a3 = a(((a3 + b(a, a4, a2)) + this.f[9]) + 1518500249, 15) + a5;
        a4 = a(a4, 10);
        a5 = a(((a5 + b(a3, a, a4)) + this.f[5]) + 1518500249, 9) + a2;
        a = a(a, 10);
        a2 = a(((a2 + b(a5, a3, a)) + this.f[2]) + 1518500249, 11) + a4;
        a3 = a(a3, 10);
        a4 = a(((a4 + b(a2, a5, a3)) + this.f[14]) + 1518500249, 7) + a;
        a5 = a(a5, 10);
        a = a(((a + b(a4, a2, a5)) + this.f[11]) + 1518500249, 13) + a3;
        a2 = a(a2, 10);
        a3 = a(((a3 + b(a, a4, a2)) + this.f[8]) + 1518500249, 12) + a5;
        a4 = a(a4, 10);
        i5 = a(((i5 + d(i, i2, i3)) + this.f[6]) + 1548603684, 9) + i4;
        i2 = a(i2, 10);
        i4 = a(((i4 + d(i5, i, i2)) + this.f[11]) + 1548603684, 13) + i3;
        i = a(i, 10);
        i3 = a(((i3 + d(i4, i5, i)) + this.f[3]) + 1548603684, 15) + i2;
        i5 = a(i5, 10);
        i2 = a(((i2 + d(i3, i4, i5)) + this.f[7]) + 1548603684, 7) + i;
        i4 = a(i4, 10);
        i = a(((i + d(i2, i3, i4)) + this.f[0]) + 1548603684, 12) + i5;
        i3 = a(i3, 10);
        i5 = a(((i5 + d(i, i2, i3)) + this.f[13]) + 1548603684, 8) + i4;
        i2 = a(i2, 10);
        i4 = a(((i4 + d(i5, i, i2)) + this.f[5]) + 1548603684, 9) + i3;
        i = a(i, 10);
        i3 = a(((i3 + d(i4, i5, i)) + this.f[10]) + 1548603684, 11) + i2;
        i5 = a(i5, 10);
        i2 = a(((i2 + d(i3, i4, i5)) + this.f[14]) + 1548603684, 7) + i;
        i4 = a(i4, 10);
        i = a(((i + d(i2, i3, i4)) + this.f[15]) + 1548603684, 7) + i5;
        i3 = a(i3, 10);
        i5 = a(((i5 + d(i, i2, i3)) + this.f[8]) + 1548603684, 12) + i4;
        i2 = a(i2, 10);
        i4 = a(((i4 + d(i5, i, i2)) + this.f[12]) + 1548603684, 7) + i3;
        i = a(i, 10);
        i3 = a(((i3 + d(i4, i5, i)) + this.f[4]) + 1548603684, 6) + i2;
        i5 = a(i5, 10);
        i2 = a(((i2 + d(i3, i4, i5)) + this.f[9]) + 1548603684, 15) + i;
        i4 = a(i4, 10);
        i = a(((i + d(i2, i3, i4)) + this.f[1]) + 1548603684, 13) + i5;
        i3 = a(i3, 10);
        i5 = a(((i5 + d(i, i2, i3)) + this.f[2]) + 1548603684, 11) + i4;
        i2 = a(i2, 10);
        a5 = a(((a5 + c(a3, a, a4)) + this.f[3]) + 1859775393, 11) + a2;
        a = a(a, 10);
        a2 = a(((a2 + c(a5, a3, a)) + this.f[10]) + 1859775393, 13) + a4;
        a3 = a(a3, 10);
        a4 = a(((a4 + c(a2, a5, a3)) + this.f[14]) + 1859775393, 6) + a;
        a5 = a(a5, 10);
        a = a(((a + c(a4, a2, a5)) + this.f[4]) + 1859775393, 7) + a3;
        a2 = a(a2, 10);
        a3 = a(((a3 + c(a, a4, a2)) + this.f[9]) + 1859775393, 14) + a5;
        a4 = a(a4, 10);
        a5 = a(((a5 + c(a3, a, a4)) + this.f[15]) + 1859775393, 9) + a2;
        a = a(a, 10);
        a2 = a(((a2 + c(a5, a3, a)) + this.f[8]) + 1859775393, 13) + a4;
        a3 = a(a3, 10);
        a4 = a(((a4 + c(a2, a5, a3)) + this.f[1]) + 1859775393, 15) + a;
        a5 = a(a5, 10);
        a = a(((a + c(a4, a2, a5)) + this.f[2]) + 1859775393, 14) + a3;
        a2 = a(a2, 10);
        a3 = a(((a3 + c(a, a4, a2)) + this.f[7]) + 1859775393, 8) + a5;
        a4 = a(a4, 10);
        a5 = a(((a5 + c(a3, a, a4)) + this.f[0]) + 1859775393, 13) + a2;
        a = a(a, 10);
        a2 = a(((a2 + c(a5, a3, a)) + this.f[6]) + 1859775393, 6) + a4;
        a3 = a(a3, 10);
        a4 = a(((a4 + c(a2, a5, a3)) + this.f[13]) + 1859775393, 5) + a;
        a5 = a(a5, 10);
        a = a(((a + c(a4, a2, a5)) + this.f[11]) + 1859775393, 12) + a3;
        a2 = a(a2, 10);
        a3 = a(((a3 + c(a, a4, a2)) + this.f[5]) + 1859775393, 7) + a5;
        a4 = a(a4, 10);
        a5 = a(((a5 + c(a3, a, a4)) + this.f[12]) + 1859775393, 5) + a2;
        a = a(a, 10);
        i4 = a(((i4 + c(i5, i, i2)) + this.f[15]) + 1836072691, 9) + i3;
        i = a(i, 10);
        i3 = a(((i3 + c(i4, i5, i)) + this.f[5]) + 1836072691, 7) + i2;
        i5 = a(i5, 10);
        i2 = a(((i2 + c(i3, i4, i5)) + this.f[1]) + 1836072691, 15) + i;
        i4 = a(i4, 10);
        i = a(((i + c(i2, i3, i4)) + this.f[3]) + 1836072691, 11) + i5;
        i3 = a(i3, 10);
        i5 = a(((i5 + c(i, i2, i3)) + this.f[7]) + 1836072691, 8) + i4;
        i2 = a(i2, 10);
        i4 = a(((i4 + c(i5, i, i2)) + this.f[14]) + 1836072691, 6) + i3;
        i = a(i, 10);
        i3 = a(((i3 + c(i4, i5, i)) + this.f[6]) + 1836072691, 6) + i2;
        i5 = a(i5, 10);
        i2 = a(((i2 + c(i3, i4, i5)) + this.f[9]) + 1836072691, 14) + i;
        i4 = a(i4, 10);
        i = a(((i + c(i2, i3, i4)) + this.f[11]) + 1836072691, 12) + i5;
        i3 = a(i3, 10);
        i5 = a(((i5 + c(i, i2, i3)) + this.f[8]) + 1836072691, 13) + i4;
        i2 = a(i2, 10);
        i4 = a(((i4 + c(i5, i, i2)) + this.f[12]) + 1836072691, 5) + i3;
        i = a(i, 10);
        i3 = a(((i3 + c(i4, i5, i)) + this.f[2]) + 1836072691, 14) + i2;
        i5 = a(i5, 10);
        i2 = a(((i2 + c(i3, i4, i5)) + this.f[10]) + 1836072691, 13) + i;
        i4 = a(i4, 10);
        i = a(((i + c(i2, i3, i4)) + this.f[0]) + 1836072691, 13) + i5;
        i3 = a(i3, 10);
        i5 = a(((i5 + c(i, i2, i3)) + this.f[4]) + 1836072691, 7) + i4;
        i2 = a(i2, 10);
        i4 = a(((i4 + c(i5, i, i2)) + this.f[13]) + 1836072691, 5) + i3;
        i = a(i, 10);
        a2 = a(((a2 + d(a5, a3, a)) + this.f[1]) - 1894007588, 11) + a4;
        a3 = a(a3, 10);
        a4 = a(((a4 + d(a2, a5, a3)) + this.f[9]) - 1894007588, 12) + a;
        a5 = a(a5, 10);
        a = a(((a + d(a4, a2, a5)) + this.f[11]) - 1894007588, 14) + a3;
        a2 = a(a2, 10);
        a3 = a(((a3 + d(a, a4, a2)) + this.f[10]) - 1894007588, 15) + a5;
        a4 = a(a4, 10);
        a5 = a(((a5 + d(a3, a, a4)) + this.f[0]) - 1894007588, 14) + a2;
        a = a(a, 10);
        a2 = a(((a2 + d(a5, a3, a)) + this.f[8]) - 1894007588, 15) + a4;
        a3 = a(a3, 10);
        a4 = a(((a4 + d(a2, a5, a3)) + this.f[12]) - 1894007588, 9) + a;
        a5 = a(a5, 10);
        a = a(((a + d(a4, a2, a5)) + this.f[4]) - 1894007588, 8) + a3;
        a2 = a(a2, 10);
        a3 = a(((a3 + d(a, a4, a2)) + this.f[13]) - 1894007588, 9) + a5;
        a4 = a(a4, 10);
        a5 = a(((a5 + d(a3, a, a4)) + this.f[3]) - 1894007588, 14) + a2;
        a = a(a, 10);
        a2 = a(((a2 + d(a5, a3, a)) + this.f[7]) - 1894007588, 5) + a4;
        a3 = a(a3, 10);
        a4 = a(((a4 + d(a2, a5, a3)) + this.f[15]) - 1894007588, 6) + a;
        a5 = a(a5, 10);
        a = a(((a + d(a4, a2, a5)) + this.f[14]) - 1894007588, 8) + a3;
        a2 = a(a2, 10);
        a3 = a(((a3 + d(a, a4, a2)) + this.f[5]) - 1894007588, 6) + a5;
        a4 = a(a4, 10);
        a5 = a(((a5 + d(a3, a, a4)) + this.f[6]) - 1894007588, 5) + a2;
        a = a(a, 10);
        a2 = a(((a2 + d(a5, a3, a)) + this.f[2]) - 1894007588, 12) + a4;
        a3 = a(a3, 10);
        i3 = a(((i3 + b(i4, i5, i)) + this.f[8]) + 2053994217, 15) + i2;
        i5 = a(i5, 10);
        i2 = a(((i2 + b(i3, i4, i5)) + this.f[6]) + 2053994217, 5) + i;
        i4 = a(i4, 10);
        i = a(((i + b(i2, i3, i4)) + this.f[4]) + 2053994217, 8) + i5;
        i3 = a(i3, 10);
        i5 = a(((i5 + b(i, i2, i3)) + this.f[1]) + 2053994217, 11) + i4;
        i2 = a(i2, 10);
        i4 = a(((i4 + b(i5, i, i2)) + this.f[3]) + 2053994217, 14) + i3;
        i = a(i, 10);
        i3 = a(((i3 + b(i4, i5, i)) + this.f[11]) + 2053994217, 14) + i2;
        i5 = a(i5, 10);
        i2 = a(((i2 + b(i3, i4, i5)) + this.f[15]) + 2053994217, 6) + i;
        i4 = a(i4, 10);
        i = a(((i + b(i2, i3, i4)) + this.f[0]) + 2053994217, 14) + i5;
        i3 = a(i3, 10);
        i5 = a(((i5 + b(i, i2, i3)) + this.f[5]) + 2053994217, 6) + i4;
        i2 = a(i2, 10);
        i4 = a(((i4 + b(i5, i, i2)) + this.f[12]) + 2053994217, 9) + i3;
        i = a(i, 10);
        i3 = a(((i3 + b(i4, i5, i)) + this.f[2]) + 2053994217, 12) + i2;
        i5 = a(i5, 10);
        i2 = a(((i2 + b(i3, i4, i5)) + this.f[13]) + 2053994217, 9) + i;
        i4 = a(i4, 10);
        i = a(((i + b(i2, i3, i4)) + this.f[9]) + 2053994217, 12) + i5;
        i3 = a(i3, 10);
        i5 = a(((i5 + b(i, i2, i3)) + this.f[7]) + 2053994217, 5) + i4;
        i2 = a(i2, 10);
        i4 = a(((i4 + b(i5, i, i2)) + this.f[10]) + 2053994217, 15) + i3;
        i = a(i, 10);
        i3 = a(((i3 + b(i4, i5, i)) + this.f[14]) + 2053994217, 8) + i2;
        i5 = a(i5, 10);
        a4 = a(((a4 + e(a2, a5, a3)) + this.f[4]) - 1454113458, 9) + a;
        a5 = a(a5, 10);
        a = a(((a + e(a4, a2, a5)) + this.f[0]) - 1454113458, 15) + a3;
        a2 = a(a2, 10);
        a3 = a(((a3 + e(a, a4, a2)) + this.f[5]) - 1454113458, 5) + a5;
        a4 = a(a4, 10);
        a5 = a(((a5 + e(a3, a, a4)) + this.f[9]) - 1454113458, 11) + a2;
        a = a(a, 10);
        a2 = a(((a2 + e(a5, a3, a)) + this.f[7]) - 1454113458, 6) + a4;
        a3 = a(a3, 10);
        a4 = a(((a4 + e(a2, a5, a3)) + this.f[12]) - 1454113458, 8) + a;
        a5 = a(a5, 10);
        a = a(((a + e(a4, a2, a5)) + this.f[2]) - 1454113458, 13) + a3;
        a2 = a(a2, 10);
        a3 = a(((a3 + e(a, a4, a2)) + this.f[10]) - 1454113458, 12) + a5;
        a4 = a(a4, 10);
        a5 = a(((a5 + e(a3, a, a4)) + this.f[14]) - 1454113458, 5) + a2;
        a = a(a, 10);
        a2 = a(((a2 + e(a5, a3, a)) + this.f[1]) - 1454113458, 12) + a4;
        a3 = a(a3, 10);
        a4 = a(((a4 + e(a2, a5, a3)) + this.f[3]) - 1454113458, 13) + a;
        a5 = a(a5, 10);
        a = a(((a + e(a4, a2, a5)) + this.f[8]) - 1454113458, 14) + a3;
        a2 = a(a2, 10);
        a3 = a(((a3 + e(a, a4, a2)) + this.f[11]) - 1454113458, 11) + a5;
        a4 = a(a4, 10);
        a5 = a(((a5 + e(a3, a, a4)) + this.f[6]) - 1454113458, 8) + a2;
        a = a(a, 10);
        a2 = a(((a2 + e(a5, a3, a)) + this.f[15]) - 1454113458, 5) + a4;
        a3 = a(a3, 10);
        a4 = a(((a4 + e(a2, a5, a3)) + this.f[13]) - 1454113458, 6) + a;
        a5 = a(a5, 10);
        i2 = a((i2 + a(i3, i4, i5)) + this.f[12], 8) + i;
        i4 = a(i4, 10);
        i = a((i + a(i2, i3, i4)) + this.f[15], 5) + i5;
        i3 = a(i3, 10);
        i5 = a((i5 + a(i, i2, i3)) + this.f[10], 12) + i4;
        i2 = a(i2, 10);
        i4 = a((i4 + a(i5, i, i2)) + this.f[4], 9) + i3;
        i = a(i, 10);
        i3 = a((i3 + a(i4, i5, i)) + this.f[1], 12) + i2;
        i5 = a(i5, 10);
        i2 = a((i2 + a(i3, i4, i5)) + this.f[5], 5) + i;
        i4 = a(i4, 10);
        i = a((i + a(i2, i3, i4)) + this.f[8], 14) + i5;
        i3 = a(i3, 10);
        i5 = a((i5 + a(i, i2, i3)) + this.f[7], 6) + i4;
        i2 = a(i2, 10);
        i4 = a((i4 + a(i5, i, i2)) + this.f[6], 8) + i3;
        i = a(i, 10);
        i3 = a((i3 + a(i4, i5, i)) + this.f[2], 13) + i2;
        i5 = a(i5, 10);
        i2 = a((i2 + a(i3, i4, i5)) + this.f[13], 6) + i;
        i4 = a(i4, 10);
        i = a((i + a(i2, i3, i4)) + this.f[14], 5) + i5;
        i3 = a(i3, 10);
        i5 = a((i5 + a(i, i2, i3)) + this.f[0], 15) + i4;
        i2 = a(i2, 10);
        i4 = a((i4 + a(i5, i, i2)) + this.f[3], 13) + i3;
        i = a(i, 10);
        i3 = a((i3 + a(i4, i5, i)) + this.f[9], 11) + i2;
        i5 = a(i5, 10);
        i2 = a((i2 + a(i3, i4, i5)) + this.f[11], 11) + i;
        i4 = a(i4, 10) + (a2 + this.b);
        this.b = i5 + (this.c + a5);
        this.c = i + (this.d + a3);
        this.d = (this.e + a) + i2;
        this.e = (this.a + a4) + i3;
        this.a = i4;
        this.g = 0;
        for (i = 0; i != this.f.length; i++) {
            this.f[i] = 0;
        }
    }

    public Memoable e() {
        return new RIPEMD160Digest(this);
    }

    public void a(Memoable memoable) {
        a((RIPEMD160Digest) memoable);
    }
}
