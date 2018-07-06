package org.spongycastle.crypto.engines;

import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.OutputLengthException;
import org.spongycastle.crypto.params.KeyParameter;

public class TEAEngine implements BlockCipher {
    private int a;
    private int b;
    private int c;
    private int d;
    private boolean e = false;
    private boolean f;

    public String a() {
        return "TEA";
    }

    public int b() {
        return 8;
    }

    public void a(boolean z, CipherParameters cipherParameters) {
        if (cipherParameters instanceof KeyParameter) {
            this.f = z;
            this.e = true;
            a(((KeyParameter) cipherParameters).a());
            return;
        }
        throw new IllegalArgumentException("invalid parameter passed to TEA init - " + cipherParameters.getClass().getName());
    }

    public int a(byte[] bArr, int i, byte[] bArr2, int i2) {
        if (!this.e) {
            throw new IllegalStateException(a() + " not initialised");
        } else if (i + 8 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        } else if (i2 + 8 <= bArr2.length) {
            return this.f ? b(bArr, i, bArr2, i2) : c(bArr, i, bArr2, i2);
        } else {
            throw new OutputLengthException("output buffer too short");
        }
    }

    public void c() {
    }

    private void a(byte[] bArr) {
        if (bArr.length != 16) {
            throw new IllegalArgumentException("Key size must be 128 bits.");
        }
        this.a = a(bArr, 0);
        this.b = a(bArr, 4);
        this.c = a(bArr, 8);
        this.d = a(bArr, 12);
    }

    private int b(byte[] bArr, int i, byte[] bArr2, int i2) {
        int i3 = 0;
        int a = a(bArr, i);
        int a2 = a(bArr, i + 4);
        int i4 = 0;
        while (i3 != 32) {
            i4 -= 1640531527;
            a += (((a2 << 4) + this.a) ^ (a2 + i4)) ^ ((a2 >>> 5) + this.b);
            a2 += (((a << 4) + this.c) ^ (a + i4)) ^ ((a >>> 5) + this.d);
            i3++;
        }
        a(a, bArr2, i2);
        a(a2, bArr2, i2 + 4);
        return 8;
    }

    private int c(byte[] bArr, int i, byte[] bArr2, int i2) {
        int a = a(bArr, i);
        int a2 = a(bArr, i + 4);
        int i3 = -957401312;
        for (int i4 = 0; i4 != 32; i4++) {
            a2 -= (((a << 4) + this.c) ^ (a + i3)) ^ ((a >>> 5) + this.d);
            a -= (((a2 << 4) + this.a) ^ (a2 + i3)) ^ ((a2 >>> 5) + this.b);
            i3 += 1640531527;
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
