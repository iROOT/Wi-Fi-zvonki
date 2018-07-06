package org.spongycastle.crypto.modes;

import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;

public class OpenPGPCFBBlockCipher implements BlockCipher {
    private byte[] a = new byte[this.f];
    private byte[] b = new byte[this.f];
    private byte[] c = new byte[this.f];
    private BlockCipher d;
    private int e;
    private int f;
    private boolean g;

    public OpenPGPCFBBlockCipher(BlockCipher blockCipher) {
        this.d = blockCipher;
        this.f = blockCipher.b();
    }

    public String a() {
        return this.d.a() + "/OpenPGPCFB";
    }

    public int b() {
        return this.d.b();
    }

    public int a(byte[] bArr, int i, byte[] bArr2, int i2) {
        return this.g ? b(bArr, i, bArr2, i2) : c(bArr, i, bArr2, i2);
    }

    public void c() {
        this.e = 0;
        System.arraycopy(this.a, 0, this.b, 0, this.b.length);
        this.d.c();
    }

    public void a(boolean z, CipherParameters cipherParameters) {
        this.g = z;
        c();
        this.d.a(true, cipherParameters);
    }

    private byte a(byte b, int i) {
        return (byte) (this.c[i] ^ b);
    }

    private int b(byte[] bArr, int i, byte[] bArr2, int i2) {
        int i3 = 2;
        if (this.f + i > bArr.length) {
            throw new DataLengthException("input buffer too short");
        } else if (this.f + i2 > bArr2.length) {
            throw new DataLengthException("output buffer too short");
        } else {
            int i4;
            byte a;
            byte[] bArr3;
            int i5;
            if (this.e > this.f) {
                byte[] bArr4 = this.b;
                i4 = this.f - 2;
                a = a(bArr[i], this.f - 2);
                bArr2[i2] = a;
                bArr4[i4] = a;
                bArr4 = this.b;
                i4 = this.f - 1;
                int i6 = i2 + 1;
                byte a2 = a(bArr[i + 1], this.f - 1);
                bArr2[i6] = a2;
                bArr4[i4] = a2;
                this.d.a(this.b, 0, this.c, 0);
                while (i3 < this.f) {
                    bArr3 = this.b;
                    i5 = i3 - 2;
                    i4 = i2 + i3;
                    a = a(bArr[i + i3], i3 - 2);
                    bArr2[i4] = a;
                    bArr3[i5] = a;
                    i3++;
                }
            } else if (this.e == 0) {
                this.d.a(this.b, 0, this.c, 0);
                for (i3 = 0; i3 < this.f; i3++) {
                    bArr3 = this.b;
                    i5 = i2 + i3;
                    byte a3 = a(bArr[i + i3], i3);
                    bArr2[i5] = a3;
                    bArr3[i3] = a3;
                }
                this.e += this.f;
            } else if (this.e == this.f) {
                this.d.a(this.b, 0, this.c, 0);
                bArr2[i2] = a(bArr[i], 0);
                bArr2[i2 + 1] = a(bArr[i + 1], 1);
                System.arraycopy(this.b, 2, this.b, 0, this.f - 2);
                System.arraycopy(bArr2, i2, this.b, this.f - 2, 2);
                this.d.a(this.b, 0, this.c, 0);
                while (i3 < this.f) {
                    bArr3 = this.b;
                    i5 = i3 - 2;
                    i4 = i2 + i3;
                    a = a(bArr[i + i3], i3 - 2);
                    bArr2[i4] = a;
                    bArr3[i5] = a;
                    i3++;
                }
                this.e += this.f;
            }
            return this.f;
        }
    }

    private int c(byte[] bArr, int i, byte[] bArr2, int i2) {
        int i3 = 2;
        if (this.f + i > bArr.length) {
            throw new DataLengthException("input buffer too short");
        } else if (this.f + i2 > bArr2.length) {
            throw new DataLengthException("output buffer too short");
        } else {
            byte b;
            byte b2;
            if (this.e > this.f) {
                b = bArr[i];
                this.b[this.f - 2] = b;
                bArr2[i2] = a(b, this.f - 2);
                b = bArr[i + 1];
                this.b[this.f - 1] = b;
                bArr2[i2 + 1] = a(b, this.f - 1);
                this.d.a(this.b, 0, this.c, 0);
                while (i3 < this.f) {
                    b2 = bArr[i + i3];
                    this.b[i3 - 2] = b2;
                    bArr2[i2 + i3] = a(b2, i3 - 2);
                    i3++;
                }
            } else if (this.e == 0) {
                this.d.a(this.b, 0, this.c, 0);
                for (i3 = 0; i3 < this.f; i3++) {
                    this.b[i3] = bArr[i + i3];
                    bArr2[i3] = a(bArr[i + i3], i3);
                }
                this.e += this.f;
            } else if (this.e == this.f) {
                this.d.a(this.b, 0, this.c, 0);
                b = bArr[i];
                byte b3 = bArr[i + 1];
                bArr2[i2] = a(b, 0);
                bArr2[i2 + 1] = a(b3, 1);
                System.arraycopy(this.b, 2, this.b, 0, this.f - 2);
                this.b[this.f - 2] = b;
                this.b[this.f - 1] = b3;
                this.d.a(this.b, 0, this.c, 0);
                while (i3 < this.f) {
                    b2 = bArr[i + i3];
                    this.b[i3 - 2] = b2;
                    bArr2[i2 + i3] = a(b2, i3 - 2);
                    i3++;
                }
                this.e += this.f;
            }
            return this.f;
        }
    }
}
