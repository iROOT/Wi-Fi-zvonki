package org.spongycastle.pqc.crypto.gmss.util;

import org.spongycastle.crypto.Digest;

public class GMSSRandom {
    private Digest a;

    public GMSSRandom(Digest digest) {
        this.a = digest;
    }

    public byte[] a(byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length];
        this.a.a(bArr, 0, bArr.length);
        bArr2 = new byte[this.a.b()];
        this.a.a(bArr2, 0);
        a(bArr, bArr2);
        b(bArr);
        return bArr2;
    }

    private void a(byte[] bArr, byte[] bArr2) {
        int i = 0;
        int i2 = 0;
        while (i < bArr.length) {
            i2 += (bArr[i] & 255) + (bArr2[i] & 255);
            bArr[i] = (byte) i2;
            i2 = (byte) (i2 >> 8);
            i++;
        }
    }

    private void b(byte[] bArr) {
        int i = 1;
        for (int i2 = 0; i2 < bArr.length; i2++) {
            i += bArr[i2] & 255;
            bArr[i2] = (byte) i;
            i = (byte) (i >> 8);
        }
    }
}
