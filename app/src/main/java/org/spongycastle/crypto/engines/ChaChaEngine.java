package org.spongycastle.crypto.engines;

import org.spongycastle.crypto.util.Pack;

public class ChaChaEngine extends Salsa20Engine {
    public String a() {
        return "ChaCha" + this.c;
    }

    protected void c() {
        int[] iArr = this.d;
        int i = iArr[12] + 1;
        iArr[12] = i;
        if (i == 0) {
            iArr = this.d;
            iArr[13] = iArr[13] + 1;
        }
    }

    protected void d() {
        int[] iArr = this.d;
        this.d[13] = 0;
        iArr[12] = 0;
    }

    protected void a(byte[] bArr, byte[] bArr2) {
        int i = 16;
        if (bArr.length == 16 || bArr.length == 32) {
            byte[] bArr3;
            this.d[4] = Pack.c(bArr, 0);
            this.d[5] = Pack.c(bArr, 4);
            this.d[6] = Pack.c(bArr, 8);
            this.d[7] = Pack.c(bArr, 12);
            if (bArr.length == 32) {
                bArr3 = a;
            } else {
                bArr3 = b;
                i = 0;
            }
            this.d[8] = Pack.c(bArr, i);
            this.d[9] = Pack.c(bArr, i + 4);
            this.d[10] = Pack.c(bArr, i + 8);
            this.d[11] = Pack.c(bArr, i + 12);
            this.d[0] = Pack.c(bArr3, 0);
            this.d[1] = Pack.c(bArr3, 4);
            this.d[2] = Pack.c(bArr3, 8);
            this.d[3] = Pack.c(bArr3, 12);
            int[] iArr = this.d;
            this.d[13] = 0;
            iArr[12] = 0;
            this.d[14] = Pack.c(bArr2, 0);
            this.d[15] = Pack.c(bArr2, 4);
            return;
        }
        throw new IllegalArgumentException(a() + " requires 128 bit or 256 bit key");
    }

    protected void a(byte[] bArr) {
        a(this.c, this.d, this.e);
        Pack.b(this.e, bArr, 0);
    }

    public static void a(int i, int[] iArr, int[] iArr2) {
        if (iArr.length != 16) {
            throw new IllegalArgumentException();
        } else if (iArr2.length != 16) {
            throw new IllegalArgumentException();
        } else if (i % 2 != 0) {
            throw new IllegalArgumentException("Number of rounds must be even");
        } else {
            int i2 = iArr[0];
            int i3 = iArr[1];
            int i4 = iArr[2];
            int i5 = iArr[3];
            int i6 = iArr[4];
            int i7 = iArr[5];
            int i8 = iArr[6];
            int i9 = iArr[7];
            int i10 = iArr[8];
            int i11 = iArr[9];
            int i12 = iArr[10];
            int i13 = iArr[11];
            int i14 = iArr[12];
            int i15 = iArr[13];
            int i16 = iArr[14];
            int i17 = iArr[15];
            while (i > 0) {
                i2 += i6;
                i14 = Salsa20Engine.a(i14 ^ i2, 16);
                i10 += i14;
                i6 = Salsa20Engine.a(i6 ^ i10, 12);
                i2 += i6;
                i14 = Salsa20Engine.a(i14 ^ i2, 8);
                i10 += i14;
                i6 = Salsa20Engine.a(i6 ^ i10, 7);
                i3 += i7;
                i15 = Salsa20Engine.a(i15 ^ i3, 16);
                i11 += i15;
                i7 = Salsa20Engine.a(i7 ^ i11, 12);
                i3 += i7;
                i15 = Salsa20Engine.a(i15 ^ i3, 8);
                i11 += i15;
                i7 = Salsa20Engine.a(i7 ^ i11, 7);
                i4 += i8;
                i16 = Salsa20Engine.a(i16 ^ i4, 16);
                i12 += i16;
                i8 = Salsa20Engine.a(i8 ^ i12, 12);
                i4 += i8;
                i16 = Salsa20Engine.a(i16 ^ i4, 8);
                i12 += i16;
                i8 = Salsa20Engine.a(i8 ^ i12, 7);
                i5 += i9;
                i17 = Salsa20Engine.a(i17 ^ i5, 16);
                i13 += i17;
                i9 = Salsa20Engine.a(i9 ^ i13, 12);
                i5 += i9;
                i17 = Salsa20Engine.a(i17 ^ i5, 8);
                i13 += i17;
                i9 = Salsa20Engine.a(i9 ^ i13, 7);
                i2 += i7;
                i17 = Salsa20Engine.a(i17 ^ i2, 16);
                i12 += i17;
                i7 = Salsa20Engine.a(i7 ^ i12, 12);
                i2 += i7;
                i17 = Salsa20Engine.a(i17 ^ i2, 8);
                i12 += i17;
                i7 = Salsa20Engine.a(i7 ^ i12, 7);
                i3 += i8;
                i14 = Salsa20Engine.a(i14 ^ i3, 16);
                i13 += i14;
                i8 = Salsa20Engine.a(i8 ^ i13, 12);
                i3 += i8;
                i14 = Salsa20Engine.a(i14 ^ i3, 8);
                i13 += i14;
                i8 = Salsa20Engine.a(i8 ^ i13, 7);
                i4 += i9;
                i15 = Salsa20Engine.a(i15 ^ i4, 16);
                i10 += i15;
                i9 = Salsa20Engine.a(i9 ^ i10, 12);
                i4 += i9;
                i15 = Salsa20Engine.a(i15 ^ i4, 8);
                i10 += i15;
                i9 = Salsa20Engine.a(i9 ^ i10, 7);
                i5 += i6;
                i16 = Salsa20Engine.a(i16 ^ i5, 16);
                i11 += i16;
                i6 = Salsa20Engine.a(i6 ^ i11, 12);
                i5 += i6;
                i16 = Salsa20Engine.a(i16 ^ i5, 8);
                i11 += i16;
                i6 = Salsa20Engine.a(i6 ^ i11, 7);
                i -= 2;
            }
            iArr2[0] = i2 + iArr[0];
            iArr2[1] = i3 + iArr[1];
            iArr2[2] = i4 + iArr[2];
            iArr2[3] = i5 + iArr[3];
            iArr2[4] = i6 + iArr[4];
            iArr2[5] = i7 + iArr[5];
            iArr2[6] = i8 + iArr[6];
            iArr2[7] = i9 + iArr[7];
            iArr2[8] = i10 + iArr[8];
            iArr2[9] = i11 + iArr[9];
            iArr2[10] = i12 + iArr[10];
            iArr2[11] = i13 + iArr[11];
            iArr2[12] = i14 + iArr[12];
            iArr2[13] = i15 + iArr[13];
            iArr2[14] = i16 + iArr[14];
            iArr2[15] = i17 + iArr[15];
        }
    }
}
