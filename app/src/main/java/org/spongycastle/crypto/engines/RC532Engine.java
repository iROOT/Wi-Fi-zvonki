package org.spongycastle.crypto.engines;

import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.params.RC5Parameters;

public class RC532Engine implements BlockCipher {
    private int a = 12;
    private int[] b = null;
    private boolean c;

    public String a() {
        return "RC5-32";
    }

    public int b() {
        return 8;
    }

    public void a(boolean z, CipherParameters cipherParameters) {
        if (cipherParameters instanceof RC5Parameters) {
            RC5Parameters rC5Parameters = (RC5Parameters) cipherParameters;
            this.a = rC5Parameters.b();
            a(rC5Parameters.a());
        } else if (cipherParameters instanceof KeyParameter) {
            a(((KeyParameter) cipherParameters).a());
        } else {
            throw new IllegalArgumentException("invalid parameter passed to RC532 init - " + cipherParameters.getClass().getName());
        }
        this.c = z;
    }

    public int a(byte[] bArr, int i, byte[] bArr2, int i2) {
        return this.c ? b(bArr, i, bArr2, i2) : c(bArr, i, bArr2, i2);
    }

    public void c() {
    }

    private void a(byte[] bArr) {
        int i;
        int i2;
        int i3 = 0;
        int[] iArr = new int[((bArr.length + 3) / 4)];
        for (i = 0; i != bArr.length; i++) {
            i2 = i / 4;
            iArr[i2] = iArr[i2] + ((bArr[i] & 255) << ((i % 4) * 8));
        }
        this.b = new int[((this.a + 1) * 2)];
        this.b[0] = -1209970333;
        for (i = 1; i < this.b.length; i++) {
            this.b[i] = this.b[i - 1] - 1640531527;
        }
        if (iArr.length > this.b.length) {
            i = iArr.length * 3;
        } else {
            i = this.b.length * 3;
        }
        i2 = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        while (i3 < i) {
            int[] iArr2 = this.b;
            i6 = a((i6 + this.b[i4]) + i5, 3);
            iArr2[i4] = i6;
            i5 = a((iArr[i2] + i6) + i5, i5 + i6);
            iArr[i2] = i5;
            i4 = (i4 + 1) % this.b.length;
            i2 = (i2 + 1) % iArr.length;
            i3++;
        }
    }

    private int b(byte[] bArr, int i, byte[] bArr2, int i2) {
        int i3 = 1;
        int a = this.b[0] + a(bArr, i);
        int a2 = a(bArr, i + 4) + this.b[1];
        while (i3 <= this.a) {
            a = a(a ^ a2, a2) + this.b[i3 * 2];
            a2 = a(a2 ^ a, a) + this.b[(i3 * 2) + 1];
            i3++;
        }
        a(a, bArr2, i2);
        a(a2, bArr2, i2 + 4);
        return 8;
    }

    private int c(byte[] bArr, int i, byte[] bArr2, int i2) {
        int a = a(bArr, i);
        int a2 = a(bArr, i + 4);
        for (int i3 = this.a; i3 >= 1; i3--) {
            a2 = b(a2 - this.b[(i3 * 2) + 1], a) ^ a;
            a = b(a - this.b[i3 * 2], a2) ^ a2;
        }
        a(a - this.b[0], bArr2, i2);
        a(a2 - this.b[1], bArr2, i2 + 4);
        return 8;
    }

    private int a(int i, int i2) {
        return (i << (i2 & 31)) | (i >>> (32 - (i2 & 31)));
    }

    private int b(int i, int i2) {
        return (i >>> (i2 & 31)) | (i << (32 - (i2 & 31)));
    }

    private int a(byte[] bArr, int i) {
        return (((bArr[i] & 255) | ((bArr[i + 1] & 255) << 8)) | ((bArr[i + 2] & 255) << 16)) | ((bArr[i + 3] & 255) << 24);
    }

    private void a(int i, byte[] bArr, int i2) {
        bArr[i2] = (byte) i;
        bArr[i2 + 1] = (byte) (i >> 8);
        bArr[i2 + 2] = (byte) (i >> 16);
        bArr[i2 + 3] = (byte) (i >> 24);
    }
}
