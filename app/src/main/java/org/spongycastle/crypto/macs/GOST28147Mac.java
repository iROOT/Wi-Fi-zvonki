package org.spongycastle.crypto.macs;

import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.Mac;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.params.ParametersWithSBox;

public class GOST28147Mac implements Mac {
    private int a = 8;
    private int b = 4;
    private int c = 0;
    private byte[] d = new byte[this.a];
    private byte[] e = new byte[this.a];
    private boolean f = true;
    private int[] g = null;
    private byte[] h = new byte[]{(byte) 9, (byte) 6, (byte) 3, (byte) 2, (byte) 8, (byte) 11, (byte) 1, (byte) 7, (byte) 10, (byte) 4, (byte) 14, (byte) 15, (byte) 12, (byte) 0, (byte) 13, (byte) 5, (byte) 3, (byte) 7, (byte) 14, (byte) 9, (byte) 8, (byte) 10, (byte) 15, (byte) 0, (byte) 5, (byte) 2, (byte) 6, (byte) 12, (byte) 11, (byte) 4, (byte) 13, (byte) 1, (byte) 14, (byte) 4, (byte) 6, (byte) 2, (byte) 11, (byte) 3, (byte) 13, (byte) 8, (byte) 12, (byte) 15, (byte) 5, (byte) 10, (byte) 0, (byte) 7, (byte) 1, (byte) 9, (byte) 14, (byte) 7, (byte) 10, (byte) 12, (byte) 13, (byte) 1, (byte) 3, (byte) 9, (byte) 0, (byte) 2, (byte) 11, (byte) 4, (byte) 15, (byte) 8, (byte) 5, (byte) 6, (byte) 11, (byte) 5, (byte) 1, (byte) 9, (byte) 8, (byte) 13, (byte) 15, (byte) 0, (byte) 14, (byte) 4, (byte) 2, (byte) 3, (byte) 12, (byte) 7, (byte) 10, (byte) 6, (byte) 3, (byte) 10, (byte) 13, (byte) 12, (byte) 1, (byte) 2, (byte) 0, (byte) 11, (byte) 7, (byte) 5, (byte) 9, (byte) 4, (byte) 8, (byte) 15, (byte) 14, (byte) 6, (byte) 1, (byte) 13, (byte) 2, (byte) 9, (byte) 7, (byte) 10, (byte) 6, (byte) 0, (byte) 8, (byte) 12, (byte) 4, (byte) 5, (byte) 15, (byte) 3, (byte) 11, (byte) 14, (byte) 11, (byte) 10, (byte) 15, (byte) 5, (byte) 0, (byte) 12, (byte) 14, (byte) 8, (byte) 6, (byte) 2, (byte) 3, (byte) 9, (byte) 1, (byte) 7, (byte) 13, (byte) 4};

    private int[] a(byte[] bArr) {
        if (bArr.length != 32) {
            throw new IllegalArgumentException("Key length invalid. Key needs to be 32 byte - 256 bit!!!");
        }
        int[] iArr = new int[8];
        for (int i = 0; i != 8; i++) {
            iArr[i] = b(bArr, i * 4);
        }
        return iArr;
    }

    public void a(CipherParameters cipherParameters) {
        c();
        this.d = new byte[this.a];
        if (cipherParameters instanceof ParametersWithSBox) {
            ParametersWithSBox parametersWithSBox = (ParametersWithSBox) cipherParameters;
            System.arraycopy(parametersWithSBox.a(), 0, this.h, 0, parametersWithSBox.a().length);
            if (parametersWithSBox.b() != null) {
                this.g = a(((KeyParameter) parametersWithSBox.b()).a());
            }
        } else if (cipherParameters instanceof KeyParameter) {
            this.g = a(((KeyParameter) cipherParameters).a());
        } else {
            throw new IllegalArgumentException("invalid parameter passed to GOST28147 init - " + cipherParameters.getClass().getName());
        }
    }

    public String a() {
        return "GOST28147Mac";
    }

    public int b() {
        return this.b;
    }

    private int a(int i, int i2) {
        int i3 = i2 + i;
        i3 = (this.h[((i3 >> 28) & 15) + 112] << 28) + (((((((this.h[((i3 >> 0) & 15) + 0] << 0) + (this.h[((i3 >> 4) & 15) + 16] << 4)) + (this.h[((i3 >> 8) & 15) + 32] << 8)) + (this.h[((i3 >> 12) & 15) + 48] << 12)) + (this.h[((i3 >> 16) & 15) + 64] << 16)) + (this.h[((i3 >> 20) & 15) + 80] << 20)) + (this.h[((i3 >> 24) & 15) + 96] << 24));
        return (i3 >>> 21) | (i3 << 11);
    }

    private void a(int[] iArr, byte[] bArr, int i, byte[] bArr2, int i2) {
        int b = b(bArr, i);
        int b2 = b(bArr, i + 4);
        int i3 = 0;
        while (i3 < 2) {
            int i4 = b2;
            b2 = 0;
            while (b2 < 8) {
                b2++;
                int i5 = b;
                b = i4 ^ a(b, iArr[b2]);
                i4 = i5;
            }
            i3++;
            b2 = i4;
        }
        a(b, bArr2, i2);
        a(b2, bArr2, i2 + 4);
    }

    private int b(byte[] bArr, int i) {
        return ((((bArr[i + 3] << 24) & -16777216) + ((bArr[i + 2] << 16) & 16711680)) + ((bArr[i + 1] << 8) & 65280)) + (bArr[i] & 255);
    }

    private void a(int i, byte[] bArr, int i2) {
        bArr[i2 + 3] = (byte) (i >>> 24);
        bArr[i2 + 2] = (byte) (i >>> 16);
        bArr[i2 + 1] = (byte) (i >>> 8);
        bArr[i2] = (byte) i;
    }

    private byte[] a(byte[] bArr, int i, byte[] bArr2) {
        int i2 = 0;
        Object obj = new byte[(bArr.length - i)];
        System.arraycopy(bArr, i, obj, 0, bArr2.length);
        while (i2 != bArr2.length) {
            obj[i2] = (byte) (obj[i2] ^ bArr2[i2]);
            i2++;
        }
        return obj;
    }

    public void a(byte b) {
        if (this.c == this.d.length) {
            byte[] bArr = new byte[this.d.length];
            System.arraycopy(this.d, 0, bArr, 0, this.e.length);
            if (this.f) {
                this.f = false;
            } else {
                bArr = a(this.d, 0, this.e);
            }
            a(this.g, bArr, 0, this.e, 0);
            this.c = 0;
        }
        byte[] bArr2 = this.d;
        int i = this.c;
        this.c = i + 1;
        bArr2[i] = b;
    }

    public void a(byte[] bArr, int i, int i2) {
        if (i2 < 0) {
            throw new IllegalArgumentException("Can't have a negative input length!");
        }
        int i3;
        int i4 = this.a - this.c;
        if (i2 > i4) {
            System.arraycopy(bArr, i, this.d, this.c, i4);
            byte[] bArr2 = new byte[this.d.length];
            System.arraycopy(this.d, 0, bArr2, 0, this.e.length);
            if (this.f) {
                this.f = false;
            } else {
                bArr2 = a(this.d, 0, this.e);
            }
            a(this.g, bArr2, 0, this.e, 0);
            this.c = 0;
            int i5 = i + i4;
            i4 = i2 - i4;
            i3 = i5;
            while (i4 > this.a) {
                a(this.g, a(bArr, i3, this.e), 0, this.e, 0);
                i4 -= this.a;
                i3 = this.a + i3;
            }
        } else {
            i4 = i2;
            i3 = i;
        }
        System.arraycopy(bArr, i3, this.d, this.c, i4);
        this.c += i4;
    }

    public int a(byte[] bArr, int i) {
        while (this.c < this.a) {
            this.d[this.c] = (byte) 0;
            this.c++;
        }
        byte[] bArr2 = new byte[this.d.length];
        System.arraycopy(this.d, 0, bArr2, 0, this.e.length);
        if (this.f) {
            this.f = false;
        } else {
            bArr2 = a(this.d, 0, this.e);
        }
        a(this.g, bArr2, 0, this.e, 0);
        System.arraycopy(this.e, (this.e.length / 2) - this.b, bArr, i, this.b);
        c();
        return this.b;
    }

    public void c() {
        for (int i = 0; i < this.d.length; i++) {
            this.d[i] = (byte) 0;
        }
        this.c = 0;
        this.f = true;
    }
}
