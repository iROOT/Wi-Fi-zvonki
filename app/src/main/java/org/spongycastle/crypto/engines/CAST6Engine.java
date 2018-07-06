package org.spongycastle.crypto.engines;

public final class CAST6Engine extends CAST5Engine {
    protected int[] k = new int[48];
    protected int[] l = new int[48];
    protected int[] m = new int[192];
    protected int[] n = new int[192];
    private int[] o = new int[8];

    public String a() {
        return "CAST6";
    }

    public void c() {
    }

    public int b() {
        return 16;
    }

    protected void a(byte[] bArr) {
        int i = 1518500249;
        int i2 = 19;
        int i3 = 0;
        while (i3 < 24) {
            int i4 = i;
            i = i2;
            for (i2 = 0; i2 < 8; i2++) {
                this.n[(i3 * 8) + i2] = i4;
                i4 += 1859775393;
                this.m[(i3 * 8) + i2] = i;
                i = (i + 17) & 31;
            }
            i3++;
            i2 = i;
            i = i4;
        }
        Object obj = new byte[64];
        System.arraycopy(bArr, 0, obj, 0, bArr.length);
        for (i2 = 0; i2 < 8; i2++) {
            this.o[i2] = a((byte[]) obj, i2 * 4);
        }
        for (i2 = 0; i2 < 12; i2++) {
            i = (i2 * 2) * 8;
            int[] iArr = this.o;
            iArr[6] = iArr[6] ^ a(this.o[7], this.n[i], this.m[i]);
            iArr = this.o;
            iArr[5] = iArr[5] ^ b(this.o[6], this.n[i + 1], this.m[i + 1]);
            iArr = this.o;
            iArr[4] = iArr[4] ^ c(this.o[5], this.n[i + 2], this.m[i + 2]);
            iArr = this.o;
            iArr[3] = iArr[3] ^ a(this.o[4], this.n[i + 3], this.m[i + 3]);
            iArr = this.o;
            iArr[2] = iArr[2] ^ b(this.o[3], this.n[i + 4], this.m[i + 4]);
            iArr = this.o;
            iArr[1] = iArr[1] ^ c(this.o[2], this.n[i + 5], this.m[i + 5]);
            iArr = this.o;
            iArr[0] = iArr[0] ^ a(this.o[1], this.n[i + 6], this.m[i + 6]);
            iArr = this.o;
            iArr[7] = b(this.o[0], this.n[i + 7], this.m[i + 7]) ^ iArr[7];
            i = ((i2 * 2) + 1) * 8;
            iArr = this.o;
            iArr[6] = iArr[6] ^ a(this.o[7], this.n[i], this.m[i]);
            iArr = this.o;
            iArr[5] = iArr[5] ^ b(this.o[6], this.n[i + 1], this.m[i + 1]);
            iArr = this.o;
            iArr[4] = iArr[4] ^ c(this.o[5], this.n[i + 2], this.m[i + 2]);
            iArr = this.o;
            iArr[3] = iArr[3] ^ a(this.o[4], this.n[i + 3], this.m[i + 3]);
            iArr = this.o;
            iArr[2] = iArr[2] ^ b(this.o[3], this.n[i + 4], this.m[i + 4]);
            iArr = this.o;
            iArr[1] = iArr[1] ^ c(this.o[2], this.n[i + 5], this.m[i + 5]);
            iArr = this.o;
            iArr[0] = iArr[0] ^ a(this.o[1], this.n[i + 6], this.m[i + 6]);
            iArr = this.o;
            iArr[7] = b(this.o[0], this.n[i + 7], this.m[i + 7]) ^ iArr[7];
            this.k[i2 * 4] = this.o[0] & 31;
            this.k[(i2 * 4) + 1] = this.o[2] & 31;
            this.k[(i2 * 4) + 2] = this.o[4] & 31;
            this.k[(i2 * 4) + 3] = this.o[6] & 31;
            this.l[i2 * 4] = this.o[7];
            this.l[(i2 * 4) + 1] = this.o[5];
            this.l[(i2 * 4) + 2] = this.o[3];
            this.l[(i2 * 4) + 3] = this.o[1];
        }
    }

    protected int b(byte[] bArr, int i, byte[] bArr2, int i2) {
        int[] iArr = new int[4];
        a(a(bArr, i), a(bArr, i + 4), a(bArr, i + 8), a(bArr, i + 12), iArr);
        a(iArr[0], bArr2, i2);
        a(iArr[1], bArr2, i2 + 4);
        a(iArr[2], bArr2, i2 + 8);
        a(iArr[3], bArr2, i2 + 12);
        return 16;
    }

    protected int c(byte[] bArr, int i, byte[] bArr2, int i2) {
        int[] iArr = new int[4];
        b(a(bArr, i), a(bArr, i + 4), a(bArr, i + 8), a(bArr, i + 12), iArr);
        a(iArr[0], bArr2, i2);
        a(iArr[1], bArr2, i2 + 4);
        a(iArr[2], bArr2, i2 + 8);
        a(iArr[3], bArr2, i2 + 12);
        return 16;
    }

    protected final void a(int i, int i2, int i3, int i4, int[] iArr) {
        int i5;
        int i6 = i4;
        int i7 = i3;
        int i8 = i2;
        int i9 = i;
        for (i5 = 0; i5 < 6; i5++) {
            int i10 = i5 * 4;
            i7 ^= a(i6, this.l[i10], this.k[i10]);
            i8 ^= b(i7, this.l[i10 + 1], this.k[i10 + 1]);
            i9 ^= c(i8, this.l[i10 + 2], this.k[i10 + 2]);
            i6 ^= a(i9, this.l[i10 + 3], this.k[i10 + 3]);
        }
        for (i5 = 6; i5 < 12; i5++) {
            i10 = i5 * 4;
            i6 ^= a(i9, this.l[i10 + 3], this.k[i10 + 3]);
            i9 ^= c(i8, this.l[i10 + 2], this.k[i10 + 2]);
            i8 ^= b(i7, this.l[i10 + 1], this.k[i10 + 1]);
            i7 ^= a(i6, this.l[i10], this.k[i10]);
        }
        iArr[0] = i9;
        iArr[1] = i8;
        iArr[2] = i7;
        iArr[3] = i6;
    }

    protected final void b(int i, int i2, int i3, int i4, int[] iArr) {
        int i5;
        int i6 = i4;
        int i7 = i3;
        int i8 = i2;
        int i9 = i;
        for (i5 = 0; i5 < 6; i5++) {
            int i10 = (11 - i5) * 4;
            i7 ^= a(i6, this.l[i10], this.k[i10]);
            i8 ^= b(i7, this.l[i10 + 1], this.k[i10 + 1]);
            i9 ^= c(i8, this.l[i10 + 2], this.k[i10 + 2]);
            i6 ^= a(i9, this.l[i10 + 3], this.k[i10 + 3]);
        }
        for (i5 = 6; i5 < 12; i5++) {
            i10 = (11 - i5) * 4;
            i6 ^= a(i9, this.l[i10 + 3], this.k[i10 + 3]);
            i9 ^= c(i8, this.l[i10 + 2], this.k[i10 + 2]);
            i8 ^= b(i7, this.l[i10 + 1], this.k[i10 + 1]);
            i7 ^= a(i6, this.l[i10], this.k[i10]);
        }
        iArr[0] = i9;
        iArr[1] = i8;
        iArr[2] = i7;
        iArr[3] = i6;
    }
}
