package org.spongycastle.crypto.digests;

import android.support.v4.app.NotificationCompat;
import org.spongycastle.crypto.ExtendedDigest;
import org.spongycastle.crypto.util.Pack;
import org.spongycastle.util.Memoable;

public abstract class LongDigest implements ExtendedDigest, Memoable {
    static final long[] i = new long[]{4794697086780616226L, 8158064640168781261L, -5349999486874862801L, -1606136188198331460L, 4131703408338449720L, 6480981068601479193L, -7908458776815382629L, -6116909921290321640L, -2880145864133508542L, 1334009975649890238L, 2608012711638119052L, 6128411473006802146L, 8268148722764581231L, -9160688886553864527L, -7215885187991268811L, -4495734319001033068L, -1973867731355612462L, -1171420211273849373L, 1135362057144423861L, 2597628984639134821L, 3308224258029322869L, 5365058923640841347L, 6679025012923562964L, 8573033837759648693L, -7476448914759557205L, -6327057829258317296L, -5763719355590565569L, -4658551843659510044L, -4116276920077217854L, -3051310485924567259L, 489312712824947311L, 1452737877330783856L, 2861767655752347644L, 3322285676063803686L, 5560940570517711597L, 5996557281743188959L, 7280758554555802590L, 8532644243296465576L, -9096487096722542874L, -7894198246740708037L, -6719396339535248540L, -6333637450476146687L, -4446306890439682159L, -4076793802049405392L, -3345356375505022440L, -2983346525034927856L, -860691631967231958L, 1182934255886127544L, 1847814050463011016L, 2177327727835720531L, 2830643537854262169L, 3796741975233480872L, 4115178125766777443L, 5681478168544905931L, 6601373596472566643L, 7507060721942968483L, 8399075790359081724L, 8693463985226723168L, -8878714635349349518L, -8302665154208450068L, -8016688836872298968L, -6606660893046293015L, -4685533653050689259L, -4147400797238176981L, -3880063495543823972L, -3348786107499101689L, -1523767162380948706L, -757361751448694408L, 500013540394364858L, 748580250866718886L, 1242879168328830382L, 1977374033974150939L, 2944078676154940804L, 3659926193048069267L, 4368137639120453308L, 4836135668995329356L, 5532061633213252278L, 6448918945643986474L, 6902733635092675308L, 7801388544844847127L};
    protected long a;
    protected long b;
    protected long c;
    protected long d;
    protected long e;
    protected long f;
    protected long g;
    protected long h;
    private byte[] j;
    private int k;
    private long l;
    private long m;
    private long[] n;
    private int o;

    protected LongDigest() {
        this.n = new long[80];
        this.j = new byte[8];
        this.k = 0;
        c();
    }

    protected LongDigest(LongDigest longDigest) {
        this.n = new long[80];
        this.j = new byte[longDigest.j.length];
        a(longDigest);
    }

    protected void a(LongDigest longDigest) {
        System.arraycopy(longDigest.j, 0, this.j, 0, longDigest.j.length);
        this.k = longDigest.k;
        this.l = longDigest.l;
        this.m = longDigest.m;
        this.a = longDigest.a;
        this.b = longDigest.b;
        this.c = longDigest.c;
        this.d = longDigest.d;
        this.e = longDigest.e;
        this.f = longDigest.f;
        this.g = longDigest.g;
        this.h = longDigest.h;
        System.arraycopy(longDigest.n, 0, this.n, 0, longDigest.n.length);
        this.o = longDigest.o;
    }

    public void a(byte b) {
        byte[] bArr = this.j;
        int i = this.k;
        this.k = i + 1;
        bArr[i] = b;
        if (this.k == this.j.length) {
            b(this.j, 0);
            this.k = 0;
        }
        this.l++;
    }

    public void a(byte[] bArr, int i, int i2) {
        while (this.k != 0 && i2 > 0) {
            a(bArr[i]);
            i++;
            i2--;
        }
        while (i2 > this.j.length) {
            b(bArr, i);
            i += this.j.length;
            i2 -= this.j.length;
            this.l += (long) this.j.length;
        }
        while (i2 > 0) {
            a(bArr[i]);
            i++;
            i2--;
        }
    }

    public void f() {
        h();
        long j = this.l << 3;
        long j2 = this.m;
        a(Byte.MIN_VALUE);
        while (this.k != 0) {
            a((byte) 0);
        }
        a(j, j2);
        g();
    }

    public void c() {
        int i = 0;
        this.l = 0;
        this.m = 0;
        this.k = 0;
        for (int i2 = 0; i2 < this.j.length; i2++) {
            this.j[i2] = (byte) 0;
        }
        this.o = 0;
        while (i != this.n.length) {
            this.n[i] = 0;
            i++;
        }
    }

    public int d() {
        return NotificationCompat.FLAG_HIGH_PRIORITY;
    }

    protected void b(byte[] bArr, int i) {
        this.n[this.o] = Pack.b(bArr, i);
        int i2 = this.o + 1;
        this.o = i2;
        if (i2 == 16) {
            g();
        }
    }

    private void h() {
        if (this.l > 2305843009213693951L) {
            this.m += this.l >>> 61;
            this.l &= 2305843009213693951L;
        }
    }

    protected void a(long j, long j2) {
        if (this.o > 14) {
            g();
        }
        this.n[14] = j2;
        this.n[15] = j;
    }

    protected void g() {
        int i;
        h();
        for (i = 16; i <= 79; i++) {
            this.n[i] = ((d(this.n[i - 2]) + this.n[i - 7]) + c(this.n[i - 15])) + this.n[i - 16];
        }
        long j = this.a;
        long j2 = this.b;
        long j3 = this.c;
        long j4 = this.d;
        long j5 = this.e;
        long j6 = this.f;
        long j7 = this.g;
        i = 0;
        long j8 = this.h;
        long j9 = j;
        int i2 = 0;
        while (i < 10) {
            int i3 = i2 + 1;
            j8 += this.n[i2] + ((b(j5) + a(j5, j6, j7)) + i[i2]);
            j4 += j8;
            j = (b(j9, j2, j3) + a(j9)) + j8;
            int i4 = i3 + 1;
            long a = j7 + (((a(j4, j5, j6) + b(j4)) + i[i3]) + this.n[i3]);
            j8 = j3 + a;
            j7 = (b(j, j9, j2) + a(j)) + a;
            int i5 = i4 + 1;
            long a2 = j6 + (((a(j8, j4, j5) + b(j8)) + i[i4]) + this.n[i4]);
            j3 = j2 + a2;
            j6 = (b(j7, j, j9) + a(j7)) + a2;
            int i6 = i5 + 1;
            a2 = j5 + (((a(j3, j8, j4) + b(j3)) + i[i5]) + this.n[i5]);
            j2 = j9 + a2;
            j5 = (b(j6, j7, j) + a(j6)) + a2;
            i5 = i6 + 1;
            j4 += ((a(j2, j3, j8) + b(j2)) + i[i6]) + this.n[i6];
            j9 = j + j4;
            j4 += a(j5) + b(j5, j6, j7);
            i3 = i5 + 1;
            long a3 = j8 + (((a(j9, j2, j3) + b(j9)) + i[i5]) + this.n[i5]);
            j = j7 + a3;
            j8 = a3 + (a(j4) + b(j4, j5, j6));
            i4 = i3 + 1;
            long a4 = j3 + (((a(j, j9, j2) + b(j)) + i[i3]) + this.n[i3]);
            j7 = j6 + a4;
            j3 = a4 + (a(j8) + b(j8, j4, j5));
            int i7 = i4 + 1;
            j6 = (((a(j7, j, j9) + b(j7)) + i[i4]) + this.n[i4]) + j2;
            j5 += j6;
            j6 += b(j3, j8, j4) + a(j3);
            i++;
            j2 = j3;
            j3 = j8;
            j8 = j9;
            j9 = j6;
            j6 = j7;
            j7 = j;
            i2 = i7;
        }
        this.a += j9;
        this.b += j2;
        this.c += j3;
        this.d += j4;
        this.e += j5;
        this.f += j6;
        this.g += j7;
        this.h += j8;
        this.o = 0;
        for (i = 0; i < 16; i++) {
            this.n[i] = 0;
        }
    }

    private long a(long j, long j2, long j3) {
        return (j & j2) ^ ((-1 ^ j) & j3);
    }

    private long b(long j, long j2, long j3) {
        return ((j & j2) ^ (j & j3)) ^ (j2 & j3);
    }

    private long a(long j) {
        return (((j << 36) | (j >>> 28)) ^ ((j << 30) | (j >>> 34))) ^ ((j << 25) | (j >>> 39));
    }

    private long b(long j) {
        return (((j << 50) | (j >>> 14)) ^ ((j << 46) | (j >>> 18))) ^ ((j << 23) | (j >>> 41));
    }

    private long c(long j) {
        return (((j << 63) | (j >>> 1)) ^ ((j << 56) | (j >>> 8))) ^ (j >>> 7);
    }

    private long d(long j) {
        return (((j << 45) | (j >>> 19)) ^ ((j << 3) | (j >>> 61))) ^ (j >>> 6);
    }
}
