package org.spongycastle.pqc.crypto.gmss;

import org.spongycastle.crypto.Digest;
import org.spongycastle.pqc.crypto.gmss.util.GMSSRandom;
import org.spongycastle.util.encoders.Hex;

public class GMSSLeaf {
    byte[] a;
    private Digest b;
    private int c = this.b.b();
    private int d;
    private GMSSRandom e = new GMSSRandom(this.b);
    private byte[] f;
    private byte[] g;
    private int h;
    private int i;
    private int j;
    private int k;
    private int l;
    private byte[] m;

    GMSSLeaf(Digest digest, int i, int i2) {
        this.k = i;
        this.b = digest;
        int ceil = (int) Math.ceil(((double) (this.c << 3)) / ((double) i));
        this.d = ceil + ((int) Math.ceil(((double) a((ceil << i) + 1)) / ((double) i)));
        this.j = 1 << i;
        this.l = (int) Math.ceil(((double) (((((1 << i) - 1) * this.d) + 1) + this.d)) / ((double) i2));
        this.m = new byte[this.c];
        this.f = new byte[this.c];
        this.a = new byte[this.c];
        this.g = new byte[(this.c * this.d)];
    }

    public GMSSLeaf(Digest digest, int i, int i2, byte[] bArr) {
        this.k = i;
        this.b = digest;
        int ceil = (int) Math.ceil(((double) (this.c << 3)) / ((double) i));
        this.d = ceil + ((int) Math.ceil(((double) a((ceil << i) + 1)) / ((double) i)));
        this.j = 1 << i;
        this.l = (int) Math.ceil(((double) (((((1 << i) - 1) * this.d) + 1) + this.d)) / ((double) i2));
        this.m = new byte[this.c];
        this.f = new byte[this.c];
        this.a = new byte[this.c];
        this.g = new byte[(this.c * this.d)];
        a(bArr);
    }

    void a(byte[] bArr) {
        this.h = 0;
        this.i = 0;
        Object obj = new byte[this.c];
        System.arraycopy(bArr, 0, obj, 0, this.m.length);
        this.m = this.e.a(obj);
    }

    private int a(int i) {
        int i2 = 1;
        int i3 = 2;
        while (i3 < i) {
            i3 <<= 1;
            i2++;
        }
        return i2;
    }

    public byte[][] a() {
        byte[][] bArr = new byte[][]{new byte[this.c], new byte[this.c], new byte[(this.c * this.d)], new byte[this.c]};
        bArr[0] = this.a;
        bArr[1] = this.m;
        bArr[2] = this.g;
        bArr[3] = this.f;
        return bArr;
    }

    public int[] b() {
        return new int[]{this.h, this.i, this.l, this.k};
    }

    public String toString() {
        int i;
        String str = "";
        for (i = 0; i < 4; i++) {
            str = str + b()[i] + " ";
        }
        String str2 = str + " " + this.c + " " + this.d + " " + this.j + " ";
        byte[][] a = a();
        String str3 = str2;
        for (i = 0; i < 4; i++) {
            if (a[i] != null) {
                str3 = str3 + new String(Hex.a(a[i])) + " ";
            } else {
                str3 = str3 + "null ";
            }
        }
        return str3;
    }
}
