package org.spongycastle.crypto.engines;

import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.OutputLengthException;
import org.spongycastle.crypto.params.KeyParameter;

public class XTEAEngine implements BlockCipher {
    private int[] a = new int[4];
    private int[] b = new int[32];
    private int[] c = new int[32];
    private boolean d = false;
    private boolean e;

    public String a() {
        return "XTEA";
    }

    public int b() {
        return 8;
    }

    public void a(boolean z, CipherParameters cipherParameters) {
        if (cipherParameters instanceof KeyParameter) {
            this.e = z;
            this.d = true;
            a(((KeyParameter) cipherParameters).a());
            return;
        }
        throw new IllegalArgumentException("invalid parameter passed to TEA init - " + cipherParameters.getClass().getName());
    }

    public int a(byte[] bArr, int i, byte[] bArr2, int i2) {
        if (!this.d) {
            throw new IllegalStateException(a() + " not initialised");
        } else if (i + 8 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        } else if (i2 + 8 <= bArr2.length) {
            return this.e ? b(bArr, i, bArr2, i2) : c(bArr, i, bArr2, i2);
        } else {
            throw new OutputLengthException("output buffer too short");
        }
    }

    public void c() {
    }

    private void a(byte[] bArr) {
        int i = 0;
        if (bArr.length != 16) {
            throw new IllegalArgumentException("Key size must be 128 bits.");
        }
        int i2 = 0;
        int i3 = 0;
        while (i3 < 4) {
            this.a[i3] = a(bArr, i2);
            i3++;
            i2 += 4;
        }
        for (i2 = 0; i2 < 32; i2++) {
            this.b[i2] = this.a[i & 3] + i;
            i -= 1640531527;
            this.c[i2] = this.a[(i >>> 11) & 3] + i;
        }
    }

    private int b(byte[] bArr, int i, byte[] bArr2, int i2) {
        int a = a(bArr, i);
        int a2 = a(bArr, i + 4);
        for (int i3 = 0; i3 < 32; i3++) {
            a += (((a2 << 4) ^ (a2 >>> 5)) + a2) ^ this.b[i3];
            a2 += (((a << 4) ^ (a >>> 5)) + a) ^ this.c[i3];
        }
        a(a, bArr2, i2);
        a(a2, bArr2, i2 + 4);
        return 8;
    }

    private int c(byte[] bArr, int i, byte[] bArr2, int i2) {
        int a = a(bArr, i);
        int a2 = a(bArr, i + 4);
        for (int i3 = 31; i3 >= 0; i3--) {
            a2 -= (((a << 4) ^ (a >>> 5)) + a) ^ this.c[i3];
            a -= (((a2 << 4) ^ (a2 >>> 5)) + a2) ^ this.b[i3];
        }
        a(a, bArr2, i2);
        a(a2, bArr2, i2 + 4);
        return 8;
    }

    private int a(byte[] bArr, int i) {
        int i2 = i + 1;
        int i3 = i2 + 1;
        return ((((bArr[i2] & 255) << 16) | (bArr[i] << 24)) | ((bArr[i3] & 255) << 8)) | (bArr[i3 + 1] & 255);
    }

    private void a(int i, byte[] bArr, int i2) {
        int i3 = i2 + 1;
        bArr[i2] = (byte) (i >>> 24);
        int i4 = i3 + 1;
        bArr[i3] = (byte) (i >>> 16);
        i3 = i4 + 1;
        bArr[i4] = (byte) (i >>> 8);
        bArr[i3] = (byte) i;
    }
}
