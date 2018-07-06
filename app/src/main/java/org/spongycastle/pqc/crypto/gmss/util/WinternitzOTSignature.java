package org.spongycastle.pqc.crypto.gmss.util;

import java.lang.reflect.Array;
import org.spongycastle.crypto.Digest;

public class WinternitzOTSignature {
    private Digest a;
    private int b = this.a.b();
    private int c;
    private byte[][] d;
    private int e;
    private GMSSRandom f = new GMSSRandom(this.a);
    private int g;
    private int h;

    public WinternitzOTSignature(byte[] bArr, Digest digest, int i) {
        this.e = i;
        this.a = digest;
        this.g = (int) Math.ceil(((double) (this.b << 3)) / ((double) i));
        this.h = a((this.g << i) + 1);
        this.c = this.g + ((int) Math.ceil(((double) this.h) / ((double) i)));
        this.d = (byte[][]) Array.newInstance(Byte.TYPE, new int[]{this.c, this.b});
        Object obj = new byte[this.b];
        System.arraycopy(bArr, 0, obj, 0, obj.length);
        for (int i2 = 0; i2 < this.c; i2++) {
            this.d[i2] = this.f.a(obj);
        }
    }

    public byte[] a() {
        Object obj = new byte[(this.c * this.b)];
        byte[] bArr = new byte[this.b];
        int i = 1 << this.e;
        for (int i2 = 0; i2 < this.c; i2++) {
            this.a.a(this.d[i2], 0, this.d[i2].length);
            Object obj2 = new byte[this.a.b()];
            this.a.a(obj2, 0);
            for (int i3 = 2; i3 < i; i3++) {
                this.a.a(obj2, 0, obj2.length);
                obj2 = new byte[this.a.b()];
                this.a.a(obj2, 0);
            }
            System.arraycopy(obj2, 0, obj, this.b * i2, this.b);
        }
        this.a.a(obj, 0, obj.length);
        bArr = new byte[this.a.b()];
        this.a.a(bArr, 0);
        return bArr;
    }

    public byte[] a(byte[] bArr) {
        Object obj = new byte[(this.c * this.b)];
        byte[] bArr2 = new byte[this.b];
        int i = 0;
        int i2 = 0;
        this.a.a(bArr, 0, bArr.length);
        byte[] bArr3 = new byte[this.a.b()];
        this.a.a(bArr3, 0);
        int i3;
        int i4;
        Object obj2;
        int i5;
        int i6;
        int i7;
        int i8;
        if (8 % this.e == 0) {
            i3 = 8 / this.e;
            i4 = (1 << this.e) - 1;
            obj2 = new byte[this.b];
            for (i5 = 0; i5 < bArr3.length; i5++) {
                i6 = 0;
                while (i6 < i3) {
                    i7 = bArr3[i5] & i4;
                    i8 = i2 + i7;
                    System.arraycopy(this.d[i], 0, obj2, 0, this.b);
                    for (i2 = i7; i2 > 0; i2--) {
                        this.a.a(obj2, 0, obj2.length);
                        obj2 = new byte[this.a.b()];
                        this.a.a(obj2, 0);
                    }
                    System.arraycopy(obj2, 0, obj, this.b * i, this.b);
                    bArr3[i5] = (byte) (bArr3[i5] >>> this.e);
                    i++;
                    i6++;
                    i2 = i8;
                }
            }
            i2 = (this.g << this.e) - i2;
            i5 = 0;
            while (i5 < this.h) {
                System.arraycopy(this.d[i], 0, obj2, 0, this.b);
                for (i7 = i2 & i4; i7 > 0; i7--) {
                    this.a.a(obj2, 0, obj2.length);
                    obj2 = new byte[this.a.b()];
                    this.a.a(obj2, 0);
                }
                System.arraycopy(obj2, 0, obj, this.b * i, this.b);
                i2 >>>= this.e;
                i++;
                i5 += this.e;
            }
        } else if (this.e < 8) {
            r15 = this.b / this.e;
            r16 = (1 << this.e) - 1;
            r8 = new byte[this.b];
            r5 = 0;
            i4 = 0;
            i6 = 0;
            i8 = 0;
            while (i4 < r15) {
                r6 = 0;
                for (i5 = 0; i5 < this.e; i5++) {
                    r6 ^= (long) ((bArr3[r5] & 255) << (i5 << 3));
                    r5++;
                }
                i3 = i8;
                i8 = i6;
                Object obj3 = r8;
                r8 = r6;
                r4 = obj3;
                for (i = 0; i < 8; i++) {
                    i2 = (int) (((long) r16) & r8);
                    i8 += i2;
                    System.arraycopy(this.d[i3], 0, r4, 0, this.b);
                    while (i2 > 0) {
                        this.a.a(r4, 0, r4.length);
                        r4 = new byte[this.a.b()];
                        this.a.a(r4, 0);
                        i2--;
                    }
                    System.arraycopy(r4, 0, obj, this.b * i3, this.b);
                    r8 >>>= this.e;
                    i3++;
                }
                i4++;
                r8 = r4;
                i6 = i8;
                i8 = i3;
            }
            i3 = this.b % this.e;
            r6 = 0;
            for (i5 = 0; i5 < i3; i5++) {
                r6 ^= (long) ((bArr3[r5] & 255) << (i5 << 3));
                r5++;
            }
            i3 <<= 3;
            obj2 = r8;
            long j = r6;
            i2 = 0;
            i = i8;
            i8 = i6;
            r8 = j;
            while (i2 < i3) {
                i5 = (int) (((long) r16) & r8);
                i8 += i5;
                System.arraycopy(this.d[i], 0, obj2, 0, this.b);
                while (i5 > 0) {
                    this.a.a(obj2, 0, obj2.length);
                    obj2 = new byte[this.a.b()];
                    this.a.a(obj2, 0);
                    i5--;
                }
                System.arraycopy(obj2, 0, obj, this.b * i, this.b);
                r8 >>>= this.e;
                i++;
                i2 = this.e + i2;
            }
            i2 = (this.g << this.e) - i8;
            i5 = 0;
            while (i5 < this.h) {
                System.arraycopy(this.d[i], 0, obj2, 0, this.b);
                for (i7 = i2 & r16; i7 > 0; i7--) {
                    this.a.a(obj2, 0, obj2.length);
                    obj2 = new byte[this.a.b()];
                    this.a.a(obj2, 0);
                }
                System.arraycopy(obj2, 0, obj, this.b * i, this.b);
                i2 >>>= this.e;
                i++;
                i5 += this.e;
            }
        } else if (this.e < 57) {
            i4 = (this.b << 3) - this.e;
            r15 = (1 << this.e) - 1;
            r8 = new byte[this.b];
            r5 = 0;
            i6 = 0;
            i8 = 0;
            while (r5 <= i4) {
                r16 = r5 % 8;
                i3 = r5 + this.e;
                r6 = 0;
                r5 = 0;
                for (i5 = r5 >>> 3; i5 < ((i3 + 7) >>> 3); i5++) {
                    r6 ^= (long) ((bArr3[i5] & 255) << (r5 << 3));
                    r5++;
                }
                long j2 = (r6 >>> r16) & ((long) r15);
                i6 = (int) (((long) i6) + j2);
                System.arraycopy(this.d[i8], 0, r8, 0, this.b);
                while (j2 > 0) {
                    this.a.a(r8, 0, r8.length);
                    r8 = new byte[this.a.b()];
                    this.a.a(r8, 0);
                    j2--;
                }
                System.arraycopy(r8, 0, obj, this.b * i8, this.b);
                i8++;
                r5 = i3;
            }
            i5 = r5 >>> 3;
            if (i5 < this.b) {
                i3 = r5 % 8;
                r6 = 0;
                r5 = 0;
                while (i5 < this.b) {
                    r6 ^= (long) ((bArr3[i5] & 255) << (r5 << 3));
                    r5++;
                    i5++;
                }
                r6 = ((long) r15) & (r6 >>> i3);
                r5 = (int) (((long) i6) + r6);
                System.arraycopy(this.d[i8], 0, r8, 0, this.b);
                r4 = r8;
                while (r6 > 0) {
                    this.a.a(r4, 0, r4.length);
                    r4 = new byte[this.a.b()];
                    this.a.a(r4, 0);
                    r6--;
                }
                System.arraycopy(r4, 0, obj, this.b * i8, this.b);
                i2 = i8 + 1;
            } else {
                r4 = r8;
                r5 = i6;
                i2 = i8;
            }
            i = (this.g << this.e) - r5;
            obj2 = r4;
            i5 = 0;
            int i9 = i;
            i = i2;
            i2 = i9;
            while (i5 < this.h) {
                System.arraycopy(this.d[i], 0, obj2, 0, this.b);
                for (r8 = (long) (i2 & r15); r8 > 0; r8--) {
                    this.a.a(obj2, 0, obj2.length);
                    obj2 = new byte[this.a.b()];
                    this.a.a(obj2, 0);
                }
                System.arraycopy(obj2, 0, obj, this.b * i, this.b);
                i2 >>>= this.e;
                i++;
                i5 += this.e;
            }
        }
        return obj;
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
