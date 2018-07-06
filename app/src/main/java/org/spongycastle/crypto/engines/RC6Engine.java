package org.spongycastle.crypto.engines;

import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.OutputLengthException;
import org.spongycastle.crypto.params.KeyParameter;

public class RC6Engine implements BlockCipher {
    private int[] a = null;
    private boolean b;

    public String a() {
        return "RC6";
    }

    public int b() {
        return 16;
    }

    public void a(boolean z, CipherParameters cipherParameters) {
        if (cipherParameters instanceof KeyParameter) {
            KeyParameter keyParameter = (KeyParameter) cipherParameters;
            this.b = z;
            a(keyParameter.a());
            return;
        }
        throw new IllegalArgumentException("invalid parameter passed to RC6 init - " + cipherParameters.getClass().getName());
    }

    public int a(byte[] bArr, int i, byte[] bArr2, int i2) {
        int b = b();
        if (this.a == null) {
            throw new IllegalStateException("RC6 engine not initialised");
        } else if (i + b > bArr.length) {
            throw new DataLengthException("input buffer too short");
        } else if (b + i2 <= bArr2.length) {
            return this.b ? b(bArr, i, bArr2, i2) : c(bArr, i, bArr2, i2);
        } else {
            throw new OutputLengthException("output buffer too short");
        }
    }

    public void c() {
    }

    private void a(byte[] bArr) {
        int[] iArr;
        int length;
        int i = 0;
        if ((bArr.length + 3) / 4 == 0) {
            iArr = new int[(((bArr.length + 4) - 1) / 4)];
        } else {
            iArr = new int[(((bArr.length + 4) - 1) / 4)];
        }
        for (length = bArr.length - 1; length >= 0; length--) {
            iArr[length / 4] = (iArr[length / 4] << 8) + (bArr[length] & 255);
        }
        this.a = new int[44];
        this.a[0] = -1209970333;
        for (length = 1; length < this.a.length; length++) {
            this.a[length] = this.a[length - 1] - 1640531527;
        }
        if (iArr.length > this.a.length) {
            length = iArr.length * 3;
        } else {
            length = this.a.length * 3;
        }
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        while (i < length) {
            int[] iArr2 = this.a;
            i5 = a((i5 + this.a[i3]) + i4, 3);
            iArr2[i3] = i5;
            i4 = a((iArr[i2] + i5) + i4, i4 + i5);
            iArr[i2] = i4;
            i3 = (i3 + 1) % this.a.length;
            i2 = (i2 + 1) % iArr.length;
            i++;
        }
    }

    private int b(byte[] bArr, int i, byte[] bArr2, int i2) {
        int i3 = 1;
        int a = a(bArr, i);
        int a2 = a(bArr, i + 4);
        int a3 = a(bArr, i + 8);
        int i4 = this.a[0] + a2;
        a2 = this.a[1] + a(bArr, i + 12);
        int i5 = a3;
        a3 = a;
        a = i4;
        i4 = i5;
        while (i3 <= 20) {
            int a4 = a(((a * 2) + 1) * a, 5);
            int a5 = a(((a2 * 2) + 1) * a2, 5);
            a3 = a(a3 ^ a4, a5) + this.a[i3 * 2];
            i3++;
            i5 = a3;
            a3 = a;
            a = a(i4 ^ a5, a4) + this.a[(i3 * 2) + 1];
            i4 = a2;
            a2 = i5;
        }
        i3 = this.a[42] + a3;
        a3 = this.a[43] + i4;
        a(i3, bArr2, i2);
        a(a, bArr2, i2 + 4);
        a(a3, bArr2, i2 + 8);
        a(a2, bArr2, i2 + 12);
        return 16;
    }

    private int c(byte[] bArr, int i, byte[] bArr2, int i2) {
        int a = a(bArr, i);
        int a2 = a(bArr, i + 4);
        int a3 = a(bArr, i + 8);
        int a4 = a(bArr, i + 12);
        a3 -= this.a[43];
        int i3 = a - this.a[42];
        a = 20;
        while (a >= 1) {
            int a5 = a(((i3 * 2) + 1) * i3, 5);
            int a6 = a(((a3 * 2) + 1) * a3, 5);
            a2 = b(a2 - this.a[(a * 2) + 1], a5) ^ a6;
            a--;
            int i4 = a3;
            a3 = a2;
            a2 = i3;
            i3 = b(a4 - this.a[a * 2], a6) ^ a5;
            a4 = i4;
        }
        a = a4 - this.a[1];
        a4 = a2 - this.a[0];
        a(i3, bArr2, i2);
        a(a4, bArr2, i2 + 4);
        a(a3, bArr2, i2 + 8);
        a(a, bArr2, i2 + 12);
        return 16;
    }

    private int a(int i, int i2) {
        return (i << i2) | (i >>> (-i2));
    }

    private int b(int i, int i2) {
        return (i >>> i2) | (i << (-i2));
    }

    private int a(byte[] bArr, int i) {
        int i2 = 0;
        for (int i3 = 3; i3 >= 0; i3--) {
            i2 = (i2 << 8) + (bArr[i3 + i] & 255);
        }
        return i2;
    }

    private void a(int i, byte[] bArr, int i2) {
        for (int i3 = 0; i3 < 4; i3++) {
            bArr[i3 + i2] = (byte) i;
            i >>>= 8;
        }
    }
}
