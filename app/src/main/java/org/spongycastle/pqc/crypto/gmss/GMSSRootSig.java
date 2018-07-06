package org.spongycastle.pqc.crypto.gmss;

import java.lang.reflect.Array;
import org.spongycastle.crypto.Digest;
import org.spongycastle.pqc.crypto.gmss.util.GMSSRandom;
import org.spongycastle.util.encoders.Hex;

public class GMSSRootSig {
    private Digest a;
    private int b = this.a.b();
    private int c;
    private byte[] d;
    private byte[] e;
    private byte[] f;
    private int g;
    private GMSSRandom h = new GMSSRandom(this.a);
    private int i;
    private int j;
    private int k;
    private int l;
    private int m;
    private int n;
    private long o;
    private long p;
    private int q;
    private int r;
    private int s;
    private byte[] t;

    public GMSSRootSig(Digest digest, int i, int i2) {
        this.a = digest;
        this.g = i;
        this.s = i2;
        this.j = (1 << i) - 1;
        this.i = (int) Math.ceil(((double) (this.b << 3)) / ((double) i));
    }

    public void a(byte[] bArr, byte[] bArr2) {
        this.e = new byte[this.b];
        this.a.a(bArr2, 0, bArr2.length);
        this.e = new byte[this.a.b()];
        this.a.a(this.e, 0);
        Object obj = new byte[this.b];
        System.arraycopy(this.e, 0, obj, 0, this.b);
        int i = 0;
        int a = a((this.i << this.g) + 1);
        int i2;
        int i3;
        int i4;
        int i5;
        if (8 % this.g == 0) {
            i2 = 8 / this.g;
            for (i3 = 0; i3 < this.b; i3++) {
                i4 = 0;
                while (i4 < i2) {
                    i5 = (obj[i3] & this.j) + i;
                    obj[i3] = (byte) (obj[i3] >>> this.g);
                    i4++;
                    i = i5;
                }
            }
            this.r = (this.i << this.g) - i;
            i4 = this.r;
            i3 = 0;
            while (i3 < a) {
                i += this.j & i4;
                i4 >>>= this.g;
                i3 += this.g;
            }
        } else if (this.g < 8) {
            int i6;
            r8 = this.b / this.g;
            i2 = 0;
            i = 0;
            for (i6 = 0; i6 < r8; i6++) {
                r2 = 0;
                for (i3 = 0; i3 < this.g; i3++) {
                    r2 ^= (long) ((obj[i] & 255) << (i3 << 3));
                    i++;
                }
                for (i3 = 0; i3 < 8; i3++) {
                    i2 += (int) (((long) this.j) & r2);
                    r2 >>>= this.g;
                }
            }
            i6 = this.b % this.g;
            r2 = 0;
            for (i3 = 0; i3 < i6; i3++) {
                r2 ^= (long) ((obj[i] & 255) << (i3 << 3));
                i++;
            }
            i3 = 0;
            i = i2;
            while (i3 < (i6 << 3)) {
                i += (int) (((long) this.j) & r2);
                r2 >>>= this.g;
                i3 += this.g;
            }
            this.r = (this.i << this.g) - i;
            i4 = this.r;
            i3 = 0;
            while (i3 < a) {
                i += this.j & i4;
                i4 >>>= this.g;
                i3 += this.g;
            }
        } else if (this.g < 57) {
            long j;
            i3 = 0;
            while (i3 <= (this.b << 3) - this.g) {
                r8 = i3 % 8;
                i3 += this.g;
                j = 0;
                i5 = 0;
                for (i4 = i3 >>> 3; i4 < ((i3 + 7) >>> 3); i4++) {
                    j ^= (long) ((obj[i4] & 255) << (i5 << 3));
                    i5++;
                }
                r2 = j >>> r8;
                i = (int) ((r2 & ((long) this.j)) + ((long) i));
            }
            i4 = i3 >>> 3;
            if (i4 < this.b) {
                i5 = i3 % 8;
                j = 0;
                int i7 = i4;
                i4 = 0;
                for (i3 = i7; i3 < this.b; i3++) {
                    j ^= (long) ((obj[i3] & 255) << (i4 << 3));
                    i4++;
                }
                i = (int) (((long) i) + ((j >>> i5) & ((long) this.j)));
            }
            this.r = (this.i << this.g) - i;
            i4 = this.r;
            i3 = 0;
            while (i3 < a) {
                i += this.j & i4;
                i4 >>>= this.g;
                i3 += this.g;
            }
        }
        this.c = this.i + ((int) Math.ceil(((double) a) / ((double) this.g)));
        this.q = (int) Math.ceil(((double) (this.c + i)) / ((double) (1 << this.s)));
        this.f = new byte[(this.c * this.b)];
        this.m = 0;
        this.l = 0;
        this.n = 0;
        this.o = 0;
        this.k = 0;
        this.d = new byte[this.b];
        this.t = new byte[this.b];
        System.arraycopy(bArr, 0, this.t, 0, this.b);
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

    public byte[][] a() {
        byte[][] bArr = (byte[][]) Array.newInstance(Byte.TYPE, new int[]{5, this.b});
        bArr[0] = this.d;
        bArr[1] = this.t;
        bArr[2] = this.e;
        bArr[3] = this.f;
        bArr[4] = c();
        return bArr;
    }

    public int[] b() {
        return new int[]{this.m, this.l, this.n, this.k, this.q, this.c, this.s, this.g, this.r};
    }

    public byte[] c() {
        return new byte[]{(byte) ((int) (this.o & 255)), (byte) ((int) ((this.o >> 8) & 255)), (byte) ((int) ((this.o >> 16) & 255)), (byte) ((int) ((this.o >> 24) & 255)), (byte) ((int) ((this.o >> 32) & 255)), (byte) ((int) ((this.o >> 40) & 255)), (byte) ((int) ((this.o >> 48) & 255)), (byte) ((int) ((this.o >> 56) & 255)), (byte) ((int) (this.p & 255)), (byte) ((int) ((this.p >> 8) & 255)), (byte) ((int) ((this.p >> 16) & 255)), (byte) ((int) ((this.p >> 24) & 255)), (byte) ((int) ((this.p >> 32) & 255)), (byte) ((int) ((this.p >> 40) & 255)), (byte) ((int) ((this.p >> 48) & 255)), (byte) ((int) ((this.p >> 56) & 255))};
    }

    public String toString() {
        String str = "" + this.p + "  ";
        int[] iArr = new int[9];
        int[] b = b();
        byte[][] bArr = (byte[][]) Array.newInstance(Byte.TYPE, new int[]{5, this.b});
        byte[][] a = a();
        String str2 = str;
        int i = 0;
        while (i < 9) {
            String str3 = str2 + b[i] + " ";
            i++;
            str2 = str3;
        }
        String str4 = str2;
        for (int i2 = 0; i2 < 5; i2++) {
            str4 = str4 + new String(Hex.a(a[i2])) + " ";
        }
        return str4;
    }
}
