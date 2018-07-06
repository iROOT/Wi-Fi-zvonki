package org.spongycastle.pqc.crypto.gmss.util;

import org.spongycastle.crypto.Digest;

public class WinternitzOTSVerify {
    private Digest a;
    private int b;

    public WinternitzOTSVerify(Digest digest, int i) {
        this.b = i;
        this.a = digest;
    }

    public int a() {
        int b = this.a.b();
        int i = ((b << 3) + (this.b - 1)) / this.b;
        return b * (i + (((a((i << this.b) + 1) + this.b) - 1) / this.b));
    }

    public byte[] a(byte[] bArr, byte[] bArr2) {
        int b = this.a.b();
        byte[] bArr3 = new byte[b];
        this.a.a(bArr, 0, bArr.length);
        byte[] bArr4 = new byte[this.a.b()];
        this.a.a(bArr4, 0);
        int i = ((b << 3) + (this.b - 1)) / this.b;
        int a = a((i << this.b) + 1);
        int i2 = ((((this.b + a) - 1) / this.b) + i) * b;
        if (i2 != bArr2.length) {
            return null;
        }
        Object obj = new byte[i2];
        int i3 = 0;
        int i4 = 0;
        int i5;
        int i6;
        Object obj2;
        int i7;
        int i8;
        int i9;
        Object obj3;
        int i10;
        if (8 % this.b == 0) {
            i5 = 8 / this.b;
            i6 = (1 << this.b) - 1;
            obj2 = new byte[b];
            for (i2 = 0; i2 < bArr4.length; i2++) {
                i7 = 0;
                while (i7 < i5) {
                    i8 = bArr4[i2] & i6;
                    i9 = i3 + i8;
                    System.arraycopy(bArr2, i4 * b, obj2, 0, b);
                    for (i3 = i8; i3 < i6; i3++) {
                        this.a.a(obj2, 0, obj2.length);
                        obj2 = new byte[this.a.b()];
                        this.a.a(obj2, 0);
                    }
                    System.arraycopy(obj2, 0, obj, i4 * b, b);
                    bArr4[i2] = (byte) (bArr4[i2] >>> this.b);
                    i7++;
                    i4++;
                    i3 = i9;
                }
            }
            i8 = (i << this.b) - i3;
            i3 = i4;
            i4 = 0;
            obj3 = obj2;
            while (i4 < a) {
                System.arraycopy(bArr2, i3 * b, obj3, 0, b);
                for (i10 = i8 & i6; i10 < i6; i10++) {
                    this.a.a(obj3, 0, obj3.length);
                    obj3 = new byte[this.a.b()];
                    this.a.a(obj3, 0);
                }
                System.arraycopy(obj3, 0, obj, i3 * b, b);
                i8 >>>= this.b;
                i3++;
                i4 = this.b + i4;
            }
        } else if (this.b < 8) {
            r18 = b / this.b;
            r19 = (1 << this.b) - 1;
            Object obj4 = new byte[b];
            i10 = 0;
            i6 = 0;
            i7 = 0;
            i9 = 0;
            while (i6 < r18) {
                r6 = 0;
                for (i2 = 0; i2 < this.b; i2++) {
                    r6 ^= (long) ((bArr4[i10] & 255) << (i2 << 3));
                    i10++;
                }
                i5 = i9;
                i9 = i7;
                Object obj5 = obj4;
                r8 = r6;
                obj3 = obj5;
                for (i3 = 0; i3 < 8; i3++) {
                    i4 = (int) (((long) r19) & r8);
                    i5 += i4;
                    System.arraycopy(bArr2, i9 * b, obj3, 0, b);
                    while (i4 < r19) {
                        this.a.a(obj3, 0, obj3.length);
                        obj3 = new byte[this.a.b()];
                        this.a.a(obj3, 0);
                        i4++;
                    }
                    System.arraycopy(obj3, 0, obj, i9 * b, b);
                    r8 >>>= this.b;
                    i9++;
                }
                i6++;
                obj4 = obj3;
                i7 = i9;
                i9 = i5;
            }
            i5 = b % this.b;
            r6 = 0;
            for (i2 = 0; i2 < i5; i2++) {
                r6 ^= (long) ((bArr4[i10] & 255) << (i2 << 3));
                i10++;
            }
            i5 <<= 3;
            obj2 = obj4;
            long j = r6;
            i3 = 0;
            i4 = i7;
            r8 = j;
            while (i3 < i5) {
                i2 = (int) (((long) r19) & r8);
                i9 += i2;
                System.arraycopy(bArr2, i4 * b, obj2, 0, b);
                while (i2 < r19) {
                    this.a.a(obj2, 0, obj2.length);
                    obj2 = new byte[this.a.b()];
                    this.a.a(obj2, 0);
                    i2++;
                }
                System.arraycopy(obj2, 0, obj, i4 * b, b);
                r8 >>>= this.b;
                i4++;
                i3 = this.b + i3;
            }
            i8 = (i << this.b) - i9;
            i3 = i4;
            i4 = 0;
            obj3 = obj2;
            while (i4 < a) {
                System.arraycopy(bArr2, i3 * b, obj3, 0, b);
                for (i10 = i8 & r19; i10 < r19; i10++) {
                    this.a.a(obj3, 0, obj3.length);
                    obj3 = new byte[this.a.b()];
                    this.a.a(obj3, 0);
                }
                System.arraycopy(obj3, 0, obj, i3 * b, b);
                i8 >>>= this.b;
                i3++;
                i4 = this.b + i4;
            }
        } else if (this.b < 57) {
            long j2;
            r18 = (b << 3) - this.b;
            r19 = (1 << this.b) - 1;
            i8 = 0;
            obj3 = new byte[b];
            i10 = 0;
            i4 = 0;
            while (i8 <= r18) {
                i7 = i8 % 8;
                i6 = i8 + this.b;
                j2 = 0;
                i8 = 0;
                for (i3 = i8 >>> 3; i3 < ((i6 + 7) >>> 3); i3++) {
                    j2 ^= (long) ((bArr4[i3] & 255) << (i8 << 3));
                    i8++;
                }
                r8 = (j2 >>> i7) & ((long) r19);
                i9 = (int) (((long) i4) + r8);
                System.arraycopy(bArr2, i10 * b, obj3, 0, b);
                for (r6 = r8; r6 < ((long) r19); r6++) {
                    this.a.a(obj3, 0, obj3.length);
                    obj3 = new byte[this.a.b()];
                    this.a.a(obj3, 0);
                }
                System.arraycopy(obj3, 0, obj, i10 * b, b);
                i10++;
                i8 = i6;
                i4 = i9;
            }
            i3 = i8 >>> 3;
            if (i3 < b) {
                i7 = i8 % 8;
                j2 = 0;
                i8 = 0;
                while (i3 < b) {
                    j2 ^= (long) ((bArr4[i3] & 255) << (i8 << 3));
                    i8++;
                    i3++;
                }
                r8 = (j2 >>> i7) & ((long) r19);
                i4 = (int) (((long) i4) + r8);
                System.arraycopy(bArr2, i10 * b, obj3, 0, b);
                while (r8 < ((long) r19)) {
                    this.a.a(obj3, 0, obj3.length);
                    obj3 = new byte[this.a.b()];
                    this.a.a(obj3, 0);
                    r8++;
                }
                System.arraycopy(obj3, 0, obj, i10 * b, b);
                i10++;
            }
            i8 = i10;
            i7 = (i << this.b) - i4;
            i3 = 0;
            Object obj6 = obj3;
            while (i3 < a) {
                System.arraycopy(bArr2, i8 * b, obj6, 0, b);
                for (long j3 = (long) (i7 & r19); j3 < ((long) r19); j3++) {
                    this.a.a(obj6, 0, obj6.length);
                    obj6 = new byte[this.a.b()];
                    this.a.a(obj6, 0);
                }
                System.arraycopy(obj6, 0, obj, i8 * b, b);
                i7 >>>= this.b;
                i3 = this.b + i3;
                i8++;
            }
        }
        bArr3 = new byte[b];
        this.a.a(obj, 0, obj.length);
        bArr3 = new byte[this.a.b()];
        this.a.a(bArr3, 0);
        return bArr3;
    }

    public int a(int i) {
        int i2 = 1;
        int i3 = 2;
        while (i3 < i) {
            i3 <<= 1;
            i2++;
        }
        return i2;
    }
}
