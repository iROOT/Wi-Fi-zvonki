package org.spongycastle.crypto.prng;

import java.security.SecureRandom;

public class FixedSecureRandom extends SecureRandom {
    private byte[] a;
    private int b;
    private int c;

    public void nextBytes(byte[] bArr) {
        System.arraycopy(this.a, this.b, bArr, 0, bArr.length);
        this.b += bArr.length;
    }

    public byte[] generateSeed(int i) {
        byte[] bArr = new byte[i];
        nextBytes(bArr);
        return bArr;
    }

    public int nextInt() {
        int a = (0 | (a() << 24)) | (a() << 16);
        if (this.c == 2) {
            this.c--;
        } else {
            a |= a() << 8;
        }
        if (this.c != 1) {
            return a | a();
        }
        this.c--;
        return a;
    }

    public long nextLong() {
        return (((((((0 | (((long) a()) << 56)) | (((long) a()) << 48)) | (((long) a()) << 40)) | (((long) a()) << 32)) | (((long) a()) << 24)) | (((long) a()) << 16)) | (((long) a()) << 8)) | ((long) a());
    }

    private int a() {
        byte[] bArr = this.a;
        int i = this.b;
        this.b = i + 1;
        return bArr[i] & 255;
    }
}
