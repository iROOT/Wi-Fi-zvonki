package org.spongycastle.crypto.digests;

import java.lang.reflect.Array;
import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.ExtendedDigest;
import org.spongycastle.crypto.engines.GOST28147Engine;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.params.ParametersWithSBox;
import org.spongycastle.crypto.util.Pack;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.Memoable;

public class GOST3411Digest implements ExtendedDigest, Memoable {
    private static final byte[] s = new byte[]{(byte) 0, (byte) -1, (byte) 0, (byte) -1, (byte) 0, (byte) -1, (byte) 0, (byte) -1, (byte) -1, (byte) 0, (byte) -1, (byte) 0, (byte) -1, (byte) 0, (byte) -1, (byte) 0, (byte) 0, (byte) -1, (byte) -1, (byte) 0, (byte) -1, (byte) 0, (byte) 0, (byte) -1, (byte) -1, (byte) 0, (byte) 0, (byte) 0, (byte) -1, (byte) -1, (byte) 0, (byte) -1};
    byte[] a;
    short[] b;
    short[] c;
    byte[] d;
    byte[] e;
    byte[] f;
    byte[] g;
    private byte[] h;
    private byte[] i;
    private byte[] j;
    private byte[] k;
    private byte[][] l;
    private byte[] m;
    private int n;
    private long o;
    private BlockCipher p;
    private byte[] q;
    private byte[] r;

    public GOST3411Digest() {
        this.h = new byte[32];
        this.i = new byte[32];
        this.j = new byte[32];
        this.k = new byte[32];
        this.l = (byte[][]) Array.newInstance(Byte.TYPE, new int[]{4, 32});
        this.m = new byte[32];
        this.p = new GOST28147Engine();
        this.r = new byte[32];
        this.a = new byte[8];
        this.b = new short[16];
        this.c = new short[16];
        this.d = new byte[32];
        this.e = new byte[32];
        this.f = new byte[32];
        this.g = new byte[32];
        this.q = GOST28147Engine.a("D-A");
        this.p.a(true, new ParametersWithSBox(null, this.q));
        c();
    }

    public GOST3411Digest(byte[] bArr) {
        this.h = new byte[32];
        this.i = new byte[32];
        this.j = new byte[32];
        this.k = new byte[32];
        this.l = (byte[][]) Array.newInstance(Byte.TYPE, new int[]{4, 32});
        this.m = new byte[32];
        this.p = new GOST28147Engine();
        this.r = new byte[32];
        this.a = new byte[8];
        this.b = new short[16];
        this.c = new short[16];
        this.d = new byte[32];
        this.e = new byte[32];
        this.f = new byte[32];
        this.g = new byte[32];
        this.q = Arrays.b(bArr);
        this.p.a(true, new ParametersWithSBox(null, this.q));
        c();
    }

    public GOST3411Digest(GOST3411Digest gOST3411Digest) {
        this.h = new byte[32];
        this.i = new byte[32];
        this.j = new byte[32];
        this.k = new byte[32];
        this.l = (byte[][]) Array.newInstance(Byte.TYPE, new int[]{4, 32});
        this.m = new byte[32];
        this.p = new GOST28147Engine();
        this.r = new byte[32];
        this.a = new byte[8];
        this.b = new short[16];
        this.c = new short[16];
        this.d = new byte[32];
        this.e = new byte[32];
        this.f = new byte[32];
        this.g = new byte[32];
        a((Memoable) gOST3411Digest);
    }

    public String a() {
        return "GOST3411";
    }

    public int b() {
        return 32;
    }

    public void a(byte b) {
        byte[] bArr = this.m;
        int i = this.n;
        this.n = i + 1;
        bArr[i] = b;
        if (this.n == this.m.length) {
            d(this.m);
            b(this.m, 0);
            this.n = 0;
        }
        this.o++;
    }

    public void a(byte[] bArr, int i, int i2) {
        while (this.n != 0 && i2 > 0) {
            a(bArr[i]);
            i++;
            i2--;
        }
        while (i2 > this.m.length) {
            System.arraycopy(bArr, i, this.m, 0, this.m.length);
            d(this.m);
            b(this.m, 0);
            i += this.m.length;
            i2 -= this.m.length;
            this.o += (long) this.m.length;
        }
        while (i2 > 0) {
            a(bArr[i]);
            i++;
            i2--;
        }
    }

    private byte[] a(byte[] bArr) {
        for (int i = 0; i < 8; i++) {
            this.r[i * 4] = bArr[i];
            this.r[(i * 4) + 1] = bArr[i + 8];
            this.r[(i * 4) + 2] = bArr[i + 16];
            this.r[(i * 4) + 3] = bArr[i + 24];
        }
        return this.r;
    }

    private byte[] b(byte[] bArr) {
        for (int i = 0; i < 8; i++) {
            this.a[i] = (byte) (bArr[i] ^ bArr[i + 8]);
        }
        System.arraycopy(bArr, 8, bArr, 0, 24);
        System.arraycopy(this.a, 0, bArr, 24, 8);
        return bArr;
    }

    private void a(byte[] bArr, byte[] bArr2, int i, byte[] bArr3, int i2) {
        this.p.a(true, new KeyParameter(bArr));
        this.p.a(bArr3, i2, bArr2, i);
    }

    private void c(byte[] bArr) {
        a(bArr, this.b);
        this.c[15] = (short) (((((this.b[0] ^ this.b[1]) ^ this.b[2]) ^ this.b[3]) ^ this.b[12]) ^ this.b[15]);
        System.arraycopy(this.b, 1, this.c, 0, 15);
        a(this.c, bArr);
    }

    protected void b(byte[] bArr, int i) {
        int i2;
        System.arraycopy(bArr, i, this.j, 0, 32);
        System.arraycopy(this.h, 0, this.e, 0, 32);
        System.arraycopy(this.j, 0, this.f, 0, 32);
        for (i2 = 0; i2 < 32; i2++) {
            this.g[i2] = (byte) (this.e[i2] ^ this.f[i2]);
        }
        a(a(this.g), this.d, 0, this.h, 0);
        for (int i3 = 1; i3 < 4; i3++) {
            byte[] b = b(this.e);
            for (i2 = 0; i2 < 32; i2++) {
                this.e[i2] = (byte) (b[i2] ^ this.l[i3][i2]);
            }
            this.f = b(b(this.f));
            for (i2 = 0; i2 < 32; i2++) {
                this.g[i2] = (byte) (this.e[i2] ^ this.f[i2]);
            }
            a(a(this.g), this.d, i3 * 8, this.h, i3 * 8);
        }
        for (i2 = 0; i2 < 12; i2++) {
            c(this.d);
        }
        for (i2 = 0; i2 < 32; i2++) {
            this.d[i2] = (byte) (this.d[i2] ^ this.j[i2]);
        }
        c(this.d);
        for (i2 = 0; i2 < 32; i2++) {
            this.d[i2] = (byte) (this.h[i2] ^ this.d[i2]);
        }
        for (i2 = 0; i2 < 61; i2++) {
            c(this.d);
        }
        System.arraycopy(this.d, 0, this.h, 0, this.h.length);
    }

    private void f() {
        Pack.b(this.o * 8, this.i, 0);
        while (this.n != 0) {
            a((byte) 0);
        }
        b(this.i, 0);
        b(this.k, 0);
    }

    public int a(byte[] bArr, int i) {
        f();
        System.arraycopy(this.h, 0, bArr, i, this.h.length);
        c();
        return 32;
    }

    public void c() {
        int i;
        this.o = 0;
        this.n = 0;
        for (i = 0; i < this.h.length; i++) {
            this.h[i] = (byte) 0;
        }
        for (i = 0; i < this.i.length; i++) {
            this.i[i] = (byte) 0;
        }
        for (i = 0; i < this.j.length; i++) {
            this.j[i] = (byte) 0;
        }
        for (i = 0; i < this.l[1].length; i++) {
            this.l[1][i] = (byte) 0;
        }
        for (i = 0; i < this.l[3].length; i++) {
            this.l[3][i] = (byte) 0;
        }
        for (i = 0; i < this.k.length; i++) {
            this.k[i] = (byte) 0;
        }
        for (i = 0; i < this.m.length; i++) {
            this.m[i] = (byte) 0;
        }
        System.arraycopy(s, 0, this.l[2], 0, s.length);
    }

    private void d(byte[] bArr) {
        int i = 0;
        int i2 = 0;
        while (i != this.k.length) {
            i2 += (this.k[i] & 255) + (bArr[i] & 255);
            this.k[i] = (byte) i2;
            i2 >>>= 8;
            i++;
        }
    }

    private void a(byte[] bArr, short[] sArr) {
        for (int i = 0; i < bArr.length / 2; i++) {
            sArr[i] = (short) (((bArr[(i * 2) + 1] << 8) & 65280) | (bArr[i * 2] & 255));
        }
    }

    private void a(short[] sArr, byte[] bArr) {
        for (int i = 0; i < bArr.length / 2; i++) {
            bArr[(i * 2) + 1] = (byte) (sArr[i] >> 8);
            bArr[i * 2] = (byte) sArr[i];
        }
    }

    public int d() {
        return 32;
    }

    public Memoable e() {
        return new GOST3411Digest(this);
    }

    public void a(Memoable memoable) {
        GOST3411Digest gOST3411Digest = (GOST3411Digest) memoable;
        this.q = gOST3411Digest.q;
        this.p.a(true, new ParametersWithSBox(null, this.q));
        c();
        System.arraycopy(gOST3411Digest.h, 0, this.h, 0, gOST3411Digest.h.length);
        System.arraycopy(gOST3411Digest.i, 0, this.i, 0, gOST3411Digest.i.length);
        System.arraycopy(gOST3411Digest.j, 0, this.j, 0, gOST3411Digest.j.length);
        System.arraycopy(gOST3411Digest.k, 0, this.k, 0, gOST3411Digest.k.length);
        System.arraycopy(gOST3411Digest.l[1], 0, this.l[1], 0, gOST3411Digest.l[1].length);
        System.arraycopy(gOST3411Digest.l[2], 0, this.l[2], 0, gOST3411Digest.l[2].length);
        System.arraycopy(gOST3411Digest.l[3], 0, this.l[3], 0, gOST3411Digest.l[3].length);
        System.arraycopy(gOST3411Digest.m, 0, this.m, 0, gOST3411Digest.m.length);
        this.n = gOST3411Digest.n;
        this.o = gOST3411Digest.o;
    }
}
