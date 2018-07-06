package org.spongycastle.pqc.crypto.ntru;

import org.spongycastle.crypto.Digest;

public class IndexGenerator {
    private byte[] a;
    private int b;
    private int c;
    private int d;
    private int e = 0;
    private int f = 0;
    private BitString g;
    private int h = 0;
    private boolean i;
    private Digest j;
    private int k;

    public static class BitString {
        byte[] a = new byte[4];
        int b;
        int c;

        void a(byte[] bArr) {
            for (int i = 0; i != bArr.length; i++) {
                a(bArr[i]);
            }
        }

        public void a(byte b) {
            if (this.b == this.a.length) {
                this.a = IndexGenerator.b(this.a, this.a.length * 2);
            }
            if (this.b == 0) {
                this.b = 1;
                this.a[0] = b;
                this.c = 8;
            } else if (this.c == 8) {
                byte[] bArr = this.a;
                int i = this.b;
                this.b = i + 1;
                bArr[i] = b;
            } else {
                int i2 = 8 - this.c;
                byte[] bArr2 = this.a;
                int i3 = this.b - 1;
                bArr2[i3] = (byte) (bArr2[i3] | ((b & 255) << this.c));
                bArr2 = this.a;
                i3 = this.b;
                this.b = i3 + 1;
                bArr2[i3] = (byte) ((b & 255) >> i2);
            }
        }

        public BitString a(int i) {
            int i2;
            BitString bitString = new BitString();
            bitString.b = (i + 7) / 8;
            bitString.a = new byte[bitString.b];
            for (i2 = 0; i2 < bitString.b; i2++) {
                bitString.a[i2] = this.a[i2];
            }
            bitString.c = i % 8;
            if (bitString.c == 0) {
                bitString.c = 8;
            } else {
                i2 = 32 - bitString.c;
                bitString.a[bitString.b - 1] = (byte) ((bitString.a[bitString.b - 1] << i2) >>> i2);
            }
            return bitString;
        }

        public int b(int i) {
            int i2 = (((this.b - 1) * 8) + this.c) - i;
            int i3 = i2 / 8;
            i2 %= 8;
            int i4 = (this.a[i3] & 255) >>> i2;
            int i5 = 8 - i2;
            for (i2 = i3 + 1; i2 < this.b; i2++) {
                i4 |= (this.a[i2] & 255) << i5;
                i5 += 8;
            }
            return i4;
        }
    }

    IndexGenerator(byte[] bArr, NTRUEncryptionParameters nTRUEncryptionParameters) {
        this.a = bArr;
        this.b = nTRUEncryptionParameters.a;
        this.c = nTRUEncryptionParameters.s;
        this.d = nTRUEncryptionParameters.t;
        this.j = nTRUEncryptionParameters.A;
        this.k = this.j.b();
        this.i = false;
    }

    int a() {
        int i;
        if (!this.i) {
            this.g = new BitString();
            byte[] bArr = new byte[this.j.b()];
            while (this.h < this.d) {
                a(this.g, bArr);
                this.h++;
            }
            this.e = (this.d * 8) * this.k;
            this.f = this.e;
            this.i = true;
        }
        do {
            this.e += this.c;
            BitString a = this.g.a(this.f);
            if (this.f < this.c) {
                i = this.c - this.f;
                int i2 = this.h + (((this.k + i) - 1) / this.k);
                byte[] bArr2 = new byte[this.j.b()];
                while (this.h < i2) {
                    a(a, bArr2);
                    this.h++;
                    if (i > this.k * 8) {
                        i -= this.k * 8;
                    }
                }
                this.f = (this.k * 8) - i;
                this.g = new BitString();
                this.g.a(bArr2);
            } else {
                this.f -= this.c;
            }
            i = a.b(this.c);
        } while (i >= (1 << this.c) - ((1 << this.c) % this.b));
        return i % this.b;
    }

    private void a(BitString bitString, byte[] bArr) {
        this.j.a(this.a, 0, this.a.length);
        a(this.j, this.h);
        this.j.a(bArr, 0);
        bitString.a(bArr);
    }

    private void a(Digest digest, int i) {
        digest.a((byte) (i >> 24));
        digest.a((byte) (i >> 16));
        digest.a((byte) (i >> 8));
        digest.a((byte) i);
    }

    private static byte[] b(byte[] bArr, int i) {
        Object obj = new byte[i];
        if (i >= bArr.length) {
            i = bArr.length;
        }
        System.arraycopy(bArr, 0, obj, 0, i);
        return obj;
    }
}
