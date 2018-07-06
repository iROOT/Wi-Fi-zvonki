package org.spongycastle.crypto.engines;

import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.params.RC5Parameters;

public class RC564Engine implements BlockCipher {
    private int a = 12;
    private long[] b = null;
    private boolean c;

    public String a() {
        return "RC5-64";
    }

    public int b() {
        return 16;
    }

    public void a(boolean z, CipherParameters cipherParameters) {
        if (cipherParameters instanceof RC5Parameters) {
            RC5Parameters rC5Parameters = (RC5Parameters) cipherParameters;
            this.c = z;
            this.a = rC5Parameters.b();
            a(rC5Parameters.a());
            return;
        }
        throw new IllegalArgumentException("invalid parameter passed to RC564 init - " + cipherParameters.getClass().getName());
    }

    public int a(byte[] bArr, int i, byte[] bArr2, int i2) {
        return this.c ? b(bArr, i, bArr2, i2) : c(bArr, i, bArr2, i2);
    }

    public void c() {
    }

    private void a(byte[] bArr) {
        int i;
        int i2 = 0;
        long[] jArr = new long[((bArr.length + 7) / 8)];
        for (i = 0; i != bArr.length; i++) {
            int i3 = i / 8;
            jArr[i3] = jArr[i3] + (((long) (bArr[i] & 255)) << ((i % 8) * 8));
        }
        this.b = new long[((this.a + 1) * 2)];
        this.b[0] = -5196783011329398165L;
        for (i = 1; i < this.b.length; i++) {
            this.b[i] = this.b[i - 1] - 7046029254386353131L;
        }
        if (jArr.length > this.b.length) {
            i = jArr.length * 3;
        } else {
            i = this.b.length * 3;
        }
        long j = 0;
        long j2 = 0;
        int i4 = 0;
        int i5 = 0;
        while (i2 < i) {
            long[] jArr2 = this.b;
            j2 = a((j2 + this.b[i5]) + j, 3);
            jArr2[i5] = j2;
            j = a((jArr[i4] + j2) + j, j + j2);
            jArr[i4] = j;
            i5 = (i5 + 1) % this.b.length;
            i4 = (i4 + 1) % jArr.length;
            i2++;
        }
    }

    private int b(byte[] bArr, int i, byte[] bArr2, int i2) {
        int i3 = 1;
        long a = this.b[0] + a(bArr, i);
        long a2 = a(bArr, i + 8) + this.b[1];
        while (i3 <= this.a) {
            a = a(a ^ a2, a2) + this.b[i3 * 2];
            a2 = a(a2 ^ a, a) + this.b[(i3 * 2) + 1];
            i3++;
        }
        a(a, bArr2, i2);
        a(a2, bArr2, i2 + 8);
        return 16;
    }

    private int c(byte[] bArr, int i, byte[] bArr2, int i2) {
        long a = a(bArr, i);
        long a2 = a(bArr, i + 8);
        for (int i3 = this.a; i3 >= 1; i3--) {
            a2 = b(a2 - this.b[(i3 * 2) + 1], a) ^ a;
            a = b(a - this.b[i3 * 2], a2) ^ a2;
        }
        a(a - this.b[0], bArr2, i2);
        a(a2 - this.b[1], bArr2, i2 + 8);
        return 16;
    }

    private long a(long j, long j2) {
        return (j << ((int) (j2 & 63))) | (j >>> ((int) (64 - (63 & j2))));
    }

    private long b(long j, long j2) {
        return (j >>> ((int) (j2 & 63))) | (j << ((int) (64 - (63 & j2))));
    }

    private long a(byte[] bArr, int i) {
        long j = 0;
        for (int i2 = 7; i2 >= 0; i2--) {
            j = (j << 8) + ((long) (bArr[i2 + i] & 255));
        }
        return j;
    }

    private void a(long j, byte[] bArr, int i) {
        for (int i2 = 0; i2 < 8; i2++) {
            bArr[i2 + i] = (byte) ((int) j);
            j >>>= 8;
        }
    }
}
