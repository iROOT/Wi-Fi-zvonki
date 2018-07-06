package org.spongycastle.crypto.digests;

import android.support.v4.app.NotificationCompat;
import com.mavenir.android.vtow.activation.ActivationAdapter;
import org.spongycastle.crypto.ExtendedDigest;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.Memoable;

public final class WhirlpoolDigest implements ExtendedDigest, Memoable {
    private static final int[] a = new int[]{24, 35, 198, 232, 135, 184, 1, 79, 54, 166, 210, 245, 121, 111, 145, 82, 96, 188, 155, 142, 163, 12, 123, 53, 29, 224, 215, 194, 46, 75, 254, 87, 21, 119, 55, 229, 159, 240, 74, 218, 88, ActivationAdapter.OP_CONFIGURATION_APP_UPDATE, 41, 10, 177, 160, 107, 133, 189, 93, 16, 244, ActivationAdapter.OP_LTE_PS_ACTIVATION, 62, 5, 103, 228, 39, 65, 139, 167, 125, 149, 216, 251, 238, 124, 102, 221, 23, 71, 158, ActivationAdapter.OP_CONFIGURATION_DAILY, 45, 191, 7, 173, 90, 131, 51, 99, 2, 170, 113, ActivationAdapter.OP_CONFIGURATION_INITIAL, 25, 73, 217, 242, 227, 91, 136, 154, 38, 50, 176, 233, 15, 213, NotificationCompat.FLAG_HIGH_PRIORITY, 190, 205, 52, 72, 255, 122, 144, 95, 32, 104, 26, 174, 180, 84, 147, 34, 100, 241, 115, 18, 64, 8, 195, 236, 219, 161, 141, 61, 151, 0, 207, 43, 118, 130, 214, 27, 181, 175, 106, 80, 69, 243, 48, 239, 63, 85, 162, 234, 101, 186, 47, 192, 222, 28, 253, 77, 146, 117, 6, 138, 178, 230, 14, 31, 98, 212, 168, 150, 249, 197, 37, 89, 132, 114, 57, 76, 94, 120, 56, 140, 209, 165, 226, 97, 179, 33, 156, 30, 67, 199, 252, 4, 81, 153, 109, 13, 250, 223, 126, 36, 59, 171, 206, 17, 143, 78, 183, 235, 60, 129, 148, 247, 185, 19, 44, 211, 231, 110, 196, 3, 86, 68, 127, 169, 42, 187, 193, 83, 220, 11, 157, 108, 49, 116, 246, 70, 172, 137, 20, 225, 22, 58, 105, 9, 112, 182, 208, 237, ActivationAdapter.OP_LTE_PS_DEACTIVATION, 66, 152, 164, 40, 92, 248, 134};
    private static final long[] b = new long[256];
    private static final long[] c = new long[256];
    private static final long[] d = new long[256];
    private static final long[] e = new long[256];
    private static final long[] f = new long[256];
    private static final long[] g = new long[256];
    private static final long[] h = new long[256];
    private static final long[] i = new long[256];
    private static final short[] s = new short[32];
    private final long[] j;
    private byte[] k;
    private int l;
    private short[] m;
    private long[] n;
    private long[] o;
    private long[] p;
    private long[] q;
    private long[] r;

    static {
        s[31] = (short) 8;
    }

    public WhirlpoolDigest() {
        this.j = new long[11];
        this.k = new byte[64];
        this.l = 0;
        this.m = new short[32];
        this.n = new long[8];
        this.o = new long[8];
        this.p = new long[8];
        this.q = new long[8];
        this.r = new long[8];
        for (int i = 0; i < 256; i++) {
            int i2 = a[i];
            int a = a(i2 << 1);
            int a2 = a(a << 1);
            int i3 = a2 ^ i2;
            int a3 = a(a2 << 1);
            int i4 = a3 ^ i2;
            b[i] = a(i2, i2, a2, i2, a3, i3, a, i4);
            c[i] = a(i4, i2, i2, a2, i2, a3, i3, a);
            d[i] = a(a, i4, i2, i2, a2, i2, a3, i3);
            e[i] = a(i3, a, i4, i2, i2, a2, i2, a3);
            f[i] = a(a3, i3, a, i4, i2, i2, a2, i2);
            g[i] = a(i2, a3, i3, a, i4, i2, i2, a2);
            h[i] = a(a2, i2, a3, i3, a, i4, i2, i2);
            i[i] = a(i2, a2, i2, a3, i3, a, i4, i2);
        }
        this.j[0] = 0;
        for (int i5 = 1; i5 <= 10; i5++) {
            i2 = (i5 - 1) * 8;
            this.j[i5] = (((((((b[i2] & -72057594037927936L) ^ (c[i2 + 1] & 71776119061217280L)) ^ (d[i2 + 2] & 280375465082880L)) ^ (e[i2 + 3] & 1095216660480L)) ^ (f[i2 + 4] & 4278190080L)) ^ (g[i2 + 5] & 16711680)) ^ (h[i2 + 6] & 65280)) ^ (i[i2 + 7] & 255);
        }
    }

    private long a(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        return (((((((((long) i) << 56) ^ (((long) i2) << 48)) ^ (((long) i3) << 40)) ^ (((long) i4) << 32)) ^ (((long) i5) << 24)) ^ (((long) i6) << 16)) ^ (((long) i7) << 8)) ^ ((long) i8);
    }

    private int a(int i) {
        if (((long) i) >= 256) {
            return i ^ 285;
        }
        return i;
    }

    public WhirlpoolDigest(WhirlpoolDigest whirlpoolDigest) {
        this.j = new long[11];
        this.k = new byte[64];
        this.l = 0;
        this.m = new short[32];
        this.n = new long[8];
        this.o = new long[8];
        this.p = new long[8];
        this.q = new long[8];
        this.r = new long[8];
        a((Memoable) whirlpoolDigest);
    }

    public String a() {
        return "Whirlpool";
    }

    public int b() {
        return 64;
    }

    public int a(byte[] bArr, int i) {
        h();
        for (int i2 = 0; i2 < 8; i2++) {
            a(this.n[i2], bArr, (i2 * 8) + i);
        }
        c();
        return b();
    }

    public void c() {
        this.l = 0;
        Arrays.b(this.m, (short) 0);
        Arrays.a(this.k, (byte) 0);
        Arrays.a(this.n, 0);
        Arrays.a(this.o, 0);
        Arrays.a(this.p, 0);
        Arrays.a(this.q, 0);
        Arrays.a(this.r, 0);
    }

    private void b(byte[] bArr, int i) {
        for (int i2 = 0; i2 < this.r.length; i2++) {
            this.q[i2] = c(this.k, i2 * 8);
        }
        f();
        this.l = 0;
        Arrays.a(this.k, (byte) 0);
    }

    private long c(byte[] bArr, int i) {
        return ((((((((((long) bArr[i + 0]) & 255) << 56) | ((((long) bArr[i + 1]) & 255) << 48)) | ((((long) bArr[i + 2]) & 255) << 40)) | ((((long) bArr[i + 3]) & 255) << 32)) | ((((long) bArr[i + 4]) & 255) << 24)) | ((((long) bArr[i + 5]) & 255) << 16)) | ((((long) bArr[i + 6]) & 255) << 8)) | (((long) bArr[i + 7]) & 255);
    }

    private void a(long j, byte[] bArr, int i) {
        for (int i2 = 0; i2 < 8; i2++) {
            bArr[i + i2] = (byte) ((int) ((j >> (56 - (i2 * 8))) & 255));
        }
    }

    protected void f() {
        int i;
        int i2 = 0;
        for (i = 0; i < 8; i++) {
            long[] jArr = this.r;
            long j = this.q[i];
            long[] jArr2 = this.o;
            long j2 = this.n[i];
            jArr2[i] = j2;
            jArr[i] = j ^ j2;
        }
        for (int i3 = 1; i3 <= 10; i3++) {
            for (i = 0; i < 8; i++) {
                this.p[i] = 0;
                jArr2 = this.p;
                jArr2[i] = jArr2[i] ^ b[((int) (this.o[(i + 0) & 7] >>> 56)) & 255];
                jArr2 = this.p;
                jArr2[i] = jArr2[i] ^ c[((int) (this.o[(i - 1) & 7] >>> 48)) & 255];
                jArr2 = this.p;
                jArr2[i] = jArr2[i] ^ d[((int) (this.o[(i - 2) & 7] >>> 40)) & 255];
                jArr2 = this.p;
                jArr2[i] = jArr2[i] ^ e[((int) (this.o[(i - 3) & 7] >>> 32)) & 255];
                jArr2 = this.p;
                jArr2[i] = jArr2[i] ^ f[((int) (this.o[(i - 4) & 7] >>> 24)) & 255];
                jArr2 = this.p;
                jArr2[i] = jArr2[i] ^ g[((int) (this.o[(i - 5) & 7] >>> 16)) & 255];
                jArr2 = this.p;
                jArr2[i] = jArr2[i] ^ h[((int) (this.o[(i - 6) & 7] >>> 8)) & 255];
                jArr2 = this.p;
                jArr2[i] = jArr2[i] ^ i[((int) this.o[(i - 7) & 7]) & 255];
            }
            System.arraycopy(this.p, 0, this.o, 0, this.o.length);
            long[] jArr3 = this.o;
            jArr3[0] = jArr3[0] ^ this.j[i3];
            for (i = 0; i < 8; i++) {
                this.p[i] = this.o[i];
                jArr2 = this.p;
                jArr2[i] = jArr2[i] ^ b[((int) (this.r[(i + 0) & 7] >>> 56)) & 255];
                jArr2 = this.p;
                jArr2[i] = jArr2[i] ^ c[((int) (this.r[(i - 1) & 7] >>> 48)) & 255];
                jArr2 = this.p;
                jArr2[i] = jArr2[i] ^ d[((int) (this.r[(i - 2) & 7] >>> 40)) & 255];
                jArr2 = this.p;
                jArr2[i] = jArr2[i] ^ e[((int) (this.r[(i - 3) & 7] >>> 32)) & 255];
                jArr2 = this.p;
                jArr2[i] = jArr2[i] ^ f[((int) (this.r[(i - 4) & 7] >>> 24)) & 255];
                jArr2 = this.p;
                jArr2[i] = jArr2[i] ^ g[((int) (this.r[(i - 5) & 7] >>> 16)) & 255];
                jArr2 = this.p;
                jArr2[i] = jArr2[i] ^ h[((int) (this.r[(i - 6) & 7] >>> 8)) & 255];
                jArr2 = this.p;
                jArr2[i] = jArr2[i] ^ i[((int) this.r[(i - 7) & 7]) & 255];
            }
            System.arraycopy(this.p, 0, this.r, 0, this.r.length);
        }
        while (i2 < 8) {
            jArr3 = this.n;
            jArr3[i2] = jArr3[i2] ^ (this.r[i2] ^ this.q[i2]);
            i2++;
        }
    }

    public void a(byte b) {
        this.k[this.l] = b;
        this.l++;
        if (this.l == this.k.length) {
            b(this.k, 0);
        }
        g();
    }

    private void g() {
        int i = 0;
        for (int length = this.m.length - 1; length >= 0; length--) {
            int i2 = ((this.m[length] & 255) + s[length]) + i;
            i = i2 >>> 8;
            this.m[length] = (short) (i2 & 255);
        }
    }

    public void a(byte[] bArr, int i, int i2) {
        while (i2 > 0) {
            a(bArr[i]);
            i++;
            i2--;
        }
    }

    private void h() {
        Object i = i();
        byte[] bArr = this.k;
        int i2 = this.l;
        this.l = i2 + 1;
        bArr[i2] = (byte) (bArr[i2] | NotificationCompat.FLAG_HIGH_PRIORITY);
        if (this.l == this.k.length) {
            b(this.k, 0);
        }
        if (this.l > 32) {
            while (this.l != 0) {
                a((byte) 0);
            }
        }
        while (this.l <= 32) {
            a((byte) 0);
        }
        System.arraycopy(i, 0, this.k, 32, i.length);
        b(this.k, 0);
    }

    private byte[] i() {
        byte[] bArr = new byte[32];
        for (int i = 0; i < bArr.length; i++) {
            bArr[i] = (byte) (this.m[i] & 255);
        }
        return bArr;
    }

    public int d() {
        return 64;
    }

    public Memoable e() {
        return new WhirlpoolDigest(this);
    }

    public void a(Memoable memoable) {
        WhirlpoolDigest whirlpoolDigest = (WhirlpoolDigest) memoable;
        System.arraycopy(whirlpoolDigest.j, 0, this.j, 0, this.j.length);
        System.arraycopy(whirlpoolDigest.k, 0, this.k, 0, this.k.length);
        this.l = whirlpoolDigest.l;
        System.arraycopy(whirlpoolDigest.m, 0, this.m, 0, this.m.length);
        System.arraycopy(whirlpoolDigest.n, 0, this.n, 0, this.n.length);
        System.arraycopy(whirlpoolDigest.o, 0, this.o, 0, this.o.length);
        System.arraycopy(whirlpoolDigest.p, 0, this.p, 0, this.p.length);
        System.arraycopy(whirlpoolDigest.q, 0, this.q, 0, this.q.length);
        System.arraycopy(whirlpoolDigest.r, 0, this.r, 0, this.r.length);
    }
}
