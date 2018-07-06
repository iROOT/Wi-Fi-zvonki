package org.spongycastle.crypto.digests;

import android.support.v4.app.NotificationCompat;
import com.mavenir.android.vtow.activation.ActivationAdapter;
import net.hockeyapp.android.k;
import org.spongycastle.crypto.ExtendedDigest;
import org.spongycastle.util.Arrays;

public class SHA3Digest implements ExtendedDigest {
    private static long[] d = e();
    private static int[] e = f();
    long[] a;
    long[] b;
    long[] c;
    private byte[] f;
    private byte[] g;
    private int h;
    private int i;
    private int j;
    private boolean k;
    private int l;
    private byte[] m;
    private byte[] n;

    private static long[] e() {
        long[] jArr = new long[24];
        byte[] bArr = new byte[]{(byte) 1};
        for (int i = 0; i < 24; i++) {
            jArr[i] = 0;
            for (int i2 = 0; i2 < 7; i2++) {
                int i3 = (1 << i2) - 1;
                if (a(bArr)) {
                    jArr[i] = jArr[i] ^ (1 << i3);
                }
            }
        }
        return jArr;
    }

    private static boolean a(byte[] bArr) {
        boolean z = (bArr[0] & 1) != 0;
        if ((bArr[0] & NotificationCompat.FLAG_HIGH_PRIORITY) != 0) {
            bArr[0] = (byte) ((bArr[0] << 1) ^ 113);
        } else {
            bArr[0] = (byte) (bArr[0] << 1);
        }
        return z;
    }

    private static int[] f() {
        int i = 0;
        int[] iArr = new int[25];
        iArr[0] = 0;
        int i2 = 1;
        int i3 = 0;
        while (i < 24) {
            iArr[(i2 % 5) + ((i3 % 5) * 5)] = (((i + 1) * (i + 2)) / 2) % 64;
            i3 = ((i3 * 3) + (i2 * 2)) % 5;
            i++;
            i2 = ((i2 * 0) + (i3 * 1)) % 5;
        }
        return iArr;
    }

    private void a(int i, int i2) {
        for (int i3 = i; i3 != i + i2; i3++) {
            this.g[i3] = (byte) 0;
        }
    }

    public SHA3Digest() {
        this.f = new byte[ActivationAdapter.OP_CONFIGURATION_INITIAL];
        this.g = new byte[192];
        this.a = new long[5];
        this.b = new long[25];
        this.c = new long[5];
        a(0);
    }

    public SHA3Digest(int i) {
        this.f = new byte[ActivationAdapter.OP_CONFIGURATION_INITIAL];
        this.g = new byte[192];
        this.a = new long[5];
        this.b = new long[25];
        this.c = new long[5];
        a(i);
    }

    public SHA3Digest(SHA3Digest sHA3Digest) {
        this.f = new byte[ActivationAdapter.OP_CONFIGURATION_INITIAL];
        this.g = new byte[192];
        this.a = new long[5];
        this.b = new long[25];
        this.c = new long[5];
        System.arraycopy(sHA3Digest.f, 0, this.f, 0, sHA3Digest.f.length);
        System.arraycopy(sHA3Digest.g, 0, this.g, 0, sHA3Digest.g.length);
        this.h = sHA3Digest.h;
        this.i = sHA3Digest.i;
        this.j = sHA3Digest.j;
        this.k = sHA3Digest.k;
        this.l = sHA3Digest.l;
        this.m = Arrays.b(sHA3Digest.m);
        this.n = Arrays.b(sHA3Digest.n);
    }

    public String a() {
        return "SHA3-" + this.j;
    }

    public int b() {
        return this.j / 8;
    }

    public void a(byte b) {
        this.n[0] = b;
        a(this.n, 0, 8);
    }

    public void a(byte[] bArr, int i, int i2) {
        a(bArr, i, ((long) i2) * 8);
    }

    public int a(byte[] bArr, int i) {
        c(bArr, i, (long) this.j);
        c();
        return b();
    }

    public void c() {
        a(this.j);
    }

    public int d() {
        return this.h / 8;
    }

    private void a(int i) {
        switch (i) {
            case 0:
            case 288:
                b(k.FEEDBACK_FAILED_TITLE_ID, 576);
                return;
            case 224:
                b(1152, 448);
                return;
            case 256:
                b(1088, 512);
                return;
            case 384:
                b(832, k.EXPIRY_INFO_TITLE_ID);
                return;
            case 512:
                b(576, k.FEEDBACK_FAILED_TITLE_ID);
                return;
            default:
                throw new IllegalArgumentException("bitLength must be one of 224, 256, 384, or 512.");
        }
    }

    private void a(byte[] bArr, int i, long j) {
        if (j % 8 == 0) {
            b(bArr, i, j);
            return;
        }
        b(bArr, i, j - (j % 8));
        b(new byte[]{(byte) (bArr[((int) (j / 8)) + i] >> ((int) (8 - (j % 8))))}, i, j % 8);
    }

    private void b(int i, int i2) {
        if (i + i2 != 1600) {
            throw new IllegalStateException("rate + capacity != 1600");
        } else if (i <= 0 || i >= 1600 || i % 64 != 0) {
            throw new IllegalStateException("invalid rate value");
        } else {
            this.h = i;
            this.j = 0;
            Arrays.a(this.f, (byte) 0);
            Arrays.a(this.g, (byte) 0);
            this.i = 0;
            this.k = false;
            this.l = 0;
            this.j = i2 / 2;
            this.m = new byte[(i / 8)];
            this.n = new byte[1];
        }
    }

    private void g() {
        b(this.f, this.g, this.h / 8);
        this.i = 0;
    }

    private void b(byte[] bArr, int i, long j) {
        if (this.i % 8 != 0) {
            throw new IllegalStateException("attempt to absorb with odd length queue.");
        } else if (this.k) {
            throw new IllegalStateException("attempt to absorb while squeezing.");
        } else {
            long j2 = 0;
            while (j2 < j) {
                long j3;
                if (this.i != 0 || j < ((long) this.h) || j2 > j - ((long) this.h)) {
                    int i2 = (int) (j - j2);
                    if (this.i + i2 > this.h) {
                        i2 = this.h - this.i;
                    }
                    int i3 = i2 % 8;
                    i2 -= i3;
                    System.arraycopy(bArr, ((int) (j2 / 8)) + i, this.g, this.i / 8, i2 / 8);
                    this.i += i2;
                    j3 = ((long) i2) + j2;
                    if (this.i == this.h) {
                        g();
                    }
                    if (i3 > 0) {
                        this.g[this.i / 8] = (byte) (((1 << i3) - 1) & bArr[((int) (j3 / 8)) + i]);
                        this.i += i3;
                        j3 += (long) i3;
                    }
                    j2 = j3;
                } else {
                    long j4 = (j - j2) / ((long) this.h);
                    for (j3 = 0; j3 < j4; j3++) {
                        System.arraycopy(bArr, (int) ((((long) i) + (j2 / 8)) + (((long) this.m.length) * j3)), this.m, 0, this.m.length);
                        b(this.f, this.m, this.m.length);
                    }
                    j2 = (((long) this.h) * j4) + j2;
                }
            }
        }
    }

    private void h() {
        byte[] bArr;
        int i;
        if (this.i + 1 == this.h) {
            bArr = this.g;
            i = this.i / 8;
            bArr[i] = (byte) (bArr[i] | (1 << (this.i % 8)));
            g();
            a(0, this.h / 8);
        } else {
            a((this.i + 7) / 8, (this.h / 8) - ((this.i + 7) / 8));
            bArr = this.g;
            i = this.i / 8;
            bArr[i] = (byte) (bArr[i] | (1 << (this.i % 8)));
        }
        bArr = this.g;
        i = (this.h - 1) / 8;
        bArr[i] = (byte) (bArr[i] | (1 << ((this.h - 1) % 8)));
        g();
        if (this.h == k.FEEDBACK_FAILED_TITLE_ID) {
            a(this.f, this.g);
            this.l = k.FEEDBACK_FAILED_TITLE_ID;
        } else {
            c(this.f, this.g, this.h / 64);
            this.l = this.h;
        }
        this.k = true;
    }

    private void c(byte[] bArr, int i, long j) {
        if (!this.k) {
            h();
        }
        if (j % 8 != 0) {
            throw new IllegalStateException("outputLength not a multiple of 8");
        }
        long j2 = 0;
        while (j2 < j) {
            if (this.l == 0) {
                b(this.f);
                if (this.h == k.FEEDBACK_FAILED_TITLE_ID) {
                    a(this.f, this.g);
                    this.l = k.FEEDBACK_FAILED_TITLE_ID;
                } else {
                    c(this.f, this.g, this.h / 64);
                    this.l = this.h;
                }
            }
            int i2 = this.l;
            if (((long) i2) > j - j2) {
                i2 = (int) (j - j2);
            }
            System.arraycopy(this.g, (this.h - this.l) / 8, bArr, ((int) (j2 / 8)) + i, i2 / 8);
            this.l -= i2;
            j2 = ((long) i2) + j2;
        }
    }

    private void a(long[] jArr, byte[] bArr) {
        for (int i = 0; i < 25; i++) {
            jArr[i] = 0;
            int i2 = i * 8;
            for (int i3 = 0; i3 < 8; i3++) {
                jArr[i] = jArr[i] | ((((long) bArr[i2 + i3]) & 255) << (i3 * 8));
            }
        }
    }

    private void a(byte[] bArr, long[] jArr) {
        for (int i = 0; i < 25; i++) {
            int i2 = i * 8;
            for (int i3 = 0; i3 < 8; i3++) {
                bArr[i2 + i3] = (byte) ((int) ((jArr[i] >>> (i3 * 8)) & 255));
            }
        }
    }

    private void b(byte[] bArr) {
        long[] jArr = new long[(bArr.length / 8)];
        a(jArr, bArr);
        a(jArr);
        a(bArr, jArr);
    }

    private void a(byte[] bArr, byte[] bArr2, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            bArr[i2] = (byte) (bArr[i2] ^ bArr2[i2]);
        }
        b(bArr);
    }

    private void a(long[] jArr) {
        for (int i = 0; i < 24; i++) {
            b(jArr);
            c(jArr);
            d(jArr);
            e(jArr);
            a(jArr, i);
        }
    }

    private void b(long[] jArr) {
        int i;
        for (i = 0; i < 5; i++) {
            int i2;
            this.a[i] = 0;
            for (i2 = 0; i2 < 5; i2++) {
                long[] jArr2 = this.a;
                jArr2[i] = jArr2[i] ^ jArr[(i2 * 5) + i];
            }
        }
        for (i = 0; i < 5; i++) {
            long j = ((this.a[(i + 1) % 5] << 1) ^ (this.a[(i + 1) % 5] >>> 63)) ^ this.a[(i + 4) % 5];
            for (i2 = 0; i2 < 5; i2++) {
                int i3 = (i2 * 5) + i;
                jArr[i3] = jArr[i3] ^ j;
            }
        }
    }

    private void c(long[] jArr) {
        for (int i = 0; i < 5; i++) {
            for (int i2 = 0; i2 < 5; i2++) {
                int i3 = i + (i2 * 5);
                jArr[i3] = e[i3] != 0 ? (jArr[i3] << e[i3]) ^ (jArr[i3] >>> (64 - e[i3])) : jArr[i3];
            }
        }
    }

    private void d(long[] jArr) {
        System.arraycopy(jArr, 0, this.b, 0, this.b.length);
        for (int i = 0; i < 5; i++) {
            for (int i2 = 0; i2 < 5; i2++) {
                jArr[((((i * 2) + (i2 * 3)) % 5) * 5) + i2] = this.b[(i2 * 5) + i];
            }
        }
    }

    private void e(long[] jArr) {
        for (int i = 0; i < 5; i++) {
            int i2;
            for (i2 = 0; i2 < 5; i2++) {
                this.c[i2] = jArr[(i * 5) + i2] ^ ((jArr[((i2 + 1) % 5) + (i * 5)] ^ -1) & jArr[((i2 + 2) % 5) + (i * 5)]);
            }
            for (i2 = 0; i2 < 5; i2++) {
                jArr[(i * 5) + i2] = this.c[i2];
            }
        }
    }

    private void a(long[] jArr, int i) {
        jArr[0] = jArr[0] ^ d[i];
    }

    private void b(byte[] bArr, byte[] bArr2, int i) {
        a(bArr, bArr2, i);
    }

    private void a(byte[] bArr, byte[] bArr2) {
        System.arraycopy(bArr, 0, bArr2, 0, NotificationCompat.FLAG_HIGH_PRIORITY);
    }

    private void c(byte[] bArr, byte[] bArr2, int i) {
        System.arraycopy(bArr, 0, bArr2, 0, i * 8);
    }
}
