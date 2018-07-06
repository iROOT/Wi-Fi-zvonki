package org.spongycastle.crypto.macs;

import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.Mac;
import org.spongycastle.crypto.generators.Poly1305KeyGenerator;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.params.ParametersWithIV;
import org.spongycastle.crypto.util.Pack;

public class Poly1305 implements Mac {
    private final BlockCipher a;
    private final byte[] b = new byte[1];
    private int c;
    private int d;
    private int e;
    private int f;
    private int g;
    private int h;
    private int i;
    private int j;
    private int k;
    private int l;
    private int m;
    private int n;
    private int o;
    private final byte[] p = new byte[16];
    private int q = 0;
    private int r;
    private int s;
    private int t;
    private int u;
    private int v;

    public Poly1305(BlockCipher blockCipher) {
        if (blockCipher.b() != 16) {
            throw new IllegalArgumentException("Poly1305 requires a 128 bit block cipher.");
        }
        this.a = blockCipher;
    }

    public void a(CipherParameters cipherParameters) {
        if ((cipherParameters instanceof ParametersWithIV) && (((ParametersWithIV) cipherParameters).b() instanceof KeyParameter)) {
            a(((KeyParameter) ((ParametersWithIV) cipherParameters).b()).a(), ((ParametersWithIV) cipherParameters).a());
            c();
            return;
        }
        throw new IllegalArgumentException("Poly1305 requires a key and and IV.");
    }

    private void a(byte[] bArr, byte[] bArr2) {
        if (bArr2.length != 16) {
            throw new IllegalArgumentException("Poly1305 requires a 128 bit IV.");
        }
        Poly1305KeyGenerator.b(bArr);
        int c = Pack.c(bArr, 16);
        int c2 = Pack.c(bArr, 20);
        int c3 = Pack.c(bArr, 24);
        int c4 = Pack.c(bArr, 28);
        this.c = 67108863 & c;
        this.d = ((c >>> 26) | (c2 << 6)) & 67108611;
        this.e = ((c2 >>> 20) | (c3 << 12)) & 67092735;
        this.f = ((c3 >>> 14) | (c4 << 18)) & 66076671;
        this.g = (c4 >>> 8) & 1048575;
        this.h = this.d * 5;
        this.i = this.e * 5;
        this.j = this.f * 5;
        this.k = this.g * 5;
        Object obj = new byte[16];
        System.arraycopy(bArr, 0, obj, 0, obj.length);
        this.a.a(true, new KeyParameter(obj));
        this.a.a(bArr2, 0, obj, 0);
        this.l = Pack.c(obj, 0);
        this.m = Pack.c(obj, 4);
        this.n = Pack.c(obj, 8);
        this.o = Pack.c(obj, 12);
    }

    public String a() {
        return "Poly1305-" + this.a.a();
    }

    public int b() {
        return 16;
    }

    public void a(byte b) {
        this.b[0] = b;
        a(this.b, 0, 1);
    }

    public void a(byte[] bArr, int i, int i2) {
        int i3 = 0;
        while (i2 > i3) {
            if (this.q == 16) {
                d();
                this.q = 0;
            }
            int min = Math.min(i2 - i3, 16 - this.q);
            System.arraycopy(bArr, i3 + i, this.p, this.q, min);
            i3 += min;
            this.q = min + this.q;
        }
    }

    private void d() {
        if (this.q < 16) {
            this.p[this.q] = (byte) 1;
            for (int i = this.q + 1; i < 16; i++) {
                this.p[i] = (byte) 0;
            }
        }
        long c = 4294967295L & ((long) Pack.c(this.p, 0));
        long c2 = 4294967295L & ((long) Pack.c(this.p, 4));
        long c3 = 4294967295L & ((long) Pack.c(this.p, 8));
        long c4 = 4294967295L & ((long) Pack.c(this.p, 12));
        this.r = (int) (((long) this.r) + (67108863 & c));
        this.s = (int) ((((c | (c2 << 32)) >>> 26) & 67108863) + ((long) this.s));
        this.t = (int) (((long) this.t) + (((c2 | (c3 << 32)) >>> 20) & 67108863));
        this.u = (int) (((long) this.u) + ((((c4 << 32) | c3) >>> 14) & 67108863));
        this.v = (int) (((long) this.v) + (c4 >>> 8));
        if (this.q == 16) {
            this.v += 16777216;
        }
        c = (((a(this.r, this.c) + a(this.s, this.k)) + a(this.t, this.j)) + a(this.u, this.i)) + a(this.v, this.h);
        c2 = (((a(this.r, this.d) + a(this.s, this.c)) + a(this.t, this.k)) + a(this.u, this.j)) + a(this.v, this.i);
        c3 = (((a(this.r, this.e) + a(this.s, this.d)) + a(this.t, this.c)) + a(this.u, this.k)) + a(this.v, this.j);
        c4 = (((a(this.r, this.f) + a(this.s, this.e)) + a(this.t, this.d)) + a(this.u, this.c)) + a(this.v, this.k);
        long a = (((a(this.r, this.g) + a(this.s, this.f)) + a(this.t, this.e)) + a(this.u, this.d)) + a(this.v, this.c);
        this.r = ((int) c) & 67108863;
        c = (c >>> 26) + c2;
        this.s = ((int) c) & 67108863;
        c = ((c >>> 26) & -1) + c3;
        this.t = ((int) c) & 67108863;
        c = ((c >>> 26) & -1) + c4;
        this.u = ((int) c) & 67108863;
        c = (c >>> 26) + a;
        this.v = ((int) c) & 67108863;
        this.r = (int) (((c >>> 26) * 5) + ((long) this.r));
    }

    public int a(byte[] bArr, int i) {
        if (i + 16 > bArr.length) {
            throw new DataLengthException("Output buffer is too short.");
        }
        if (this.q > 0) {
            d();
        }
        int i2 = this.r >>> 26;
        this.r &= 67108863;
        this.s = i2 + this.s;
        i2 = this.s >>> 26;
        this.s &= 67108863;
        this.t = i2 + this.t;
        i2 = this.t >>> 26;
        this.t &= 67108863;
        this.u = i2 + this.u;
        i2 = this.u >>> 26;
        this.u &= 67108863;
        this.v = i2 + this.v;
        i2 = this.v >>> 26;
        this.v &= 67108863;
        this.r = (i2 * 5) + this.r;
        i2 = this.r + 5;
        int i3 = i2 >>> 26;
        i3 += this.s;
        int i4 = i3 >>> 26;
        i3 &= 67108863;
        i4 += this.t;
        int i5 = i4 >>> 26;
        i4 &= 67108863;
        i5 += this.u;
        int i6 = i5 >>> 26;
        i5 &= 67108863;
        i6 = (i6 + this.v) - 67108864;
        int i7 = (i6 >>> 31) - 1;
        int i8 = i7 ^ -1;
        this.r = ((i2 & 67108863) & i7) | (this.r & i8);
        this.s = (this.s & i8) | (i3 & i7);
        this.t = (this.t & i8) | (i4 & i7);
        this.u = (this.u & i8) | (i5 & i7);
        this.v = (this.v & i8) | (i6 & i7);
        long j = (((long) (this.r | (this.s << 26))) & 4294967295L) + (((long) this.l) & 4294967295L);
        long j2 = (((long) ((this.s >>> 6) | (this.t << 20))) & 4294967295L) + (((long) this.m) & 4294967295L);
        long j3 = (((long) ((this.t >>> 12) | (this.u << 14))) & 4294967295L) + (((long) this.n) & 4294967295L);
        long j4 = (((long) ((this.u >>> 18) | (this.v << 8))) & 4294967295L) + (((long) this.o) & 4294967295L);
        Pack.b((int) j, bArr, i);
        j = (j >>> 32) + j2;
        Pack.b((int) j, bArr, i + 4);
        j = (j >>> 32) + j3;
        Pack.b((int) j, bArr, i + 8);
        Pack.b((int) ((j >>> 32) + j4), bArr, i + 12);
        c();
        return 16;
    }

    public void c() {
        this.q = 0;
        this.v = 0;
        this.u = 0;
        this.t = 0;
        this.s = 0;
        this.r = 0;
    }

    private static final long a(int i, int i2) {
        return ((long) i) * ((long) i2);
    }
}
