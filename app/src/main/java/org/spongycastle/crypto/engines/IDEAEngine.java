package org.spongycastle.crypto.engines;

import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.OutputLengthException;
import org.spongycastle.crypto.params.KeyParameter;

public class IDEAEngine implements BlockCipher {
    private int[] a = null;

    public void a(boolean z, CipherParameters cipherParameters) {
        if (cipherParameters instanceof KeyParameter) {
            this.a = a(z, ((KeyParameter) cipherParameters).a());
            return;
        }
        throw new IllegalArgumentException("invalid parameter passed to IDEA init - " + cipherParameters.getClass().getName());
    }

    public String a() {
        return "IDEA";
    }

    public int b() {
        return 8;
    }

    public int a(byte[] bArr, int i, byte[] bArr2, int i2) {
        if (this.a == null) {
            throw new IllegalStateException("IDEA engine not initialised");
        } else if (i + 8 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        } else if (i2 + 8 > bArr2.length) {
            throw new OutputLengthException("output buffer too short");
        } else {
            a(this.a, bArr, i, bArr2, i2);
            return 8;
        }
    }

    public void c() {
    }

    private int a(byte[] bArr, int i) {
        return ((bArr[i] << 8) & 65280) + (bArr[i + 1] & 255);
    }

    private void a(int i, byte[] bArr, int i2) {
        bArr[i2] = (byte) (i >>> 8);
        bArr[i2 + 1] = (byte) i;
    }

    private int a(int i, int i2) {
        int i3;
        if (i == 0) {
            i3 = 65537 - i2;
        } else if (i2 == 0) {
            i3 = 65537 - i;
        } else {
            i3 = i * i2;
            int i4 = i3 & 65535;
            i3 >>>= 16;
            i3 = (i4 < i3 ? 1 : 0) + (i4 - i3);
        }
        return i3 & 65535;
    }

    private void a(int[] iArr, byte[] bArr, int i, byte[] bArr2, int i2) {
        int i3 = 0;
        int a = a(bArr, i);
        int a2 = a(bArr, i + 2);
        int i4 = a;
        a = a2;
        a2 = a(bArr, i + 4);
        int a3 = a(bArr, i + 6);
        int i5 = 0;
        while (i3 < 8) {
            int i6 = i5 + 1;
            i4 = a(i4, iArr[i5]);
            i5 = i6 + 1;
            i6 = (a + iArr[i6]) & 65535;
            a = i5 + 1;
            a2 = (iArr[i5] + a2) & 65535;
            i5 = a + 1;
            a3 = a(a3, iArr[a]);
            int i7 = i6 ^ a3;
            int i8 = i5 + 1;
            a = a(a2 ^ i4, iArr[i5]);
            i5 = i8 + 1;
            i7 = a((i7 + a) & 65535, iArr[i8]);
            i8 = (a + i7) & 65535;
            i4 ^= i7;
            a3 ^= i8;
            a = i7 ^ a2;
            a2 = i8 ^ i6;
            i3++;
        }
        i3 = i5 + 1;
        a(a(i4, iArr[i5]), bArr2, i2);
        i5 = i3 + 1;
        a(iArr[i3] + a2, bArr2, i2 + 2);
        i3 = i5 + 1;
        a(iArr[i5] + a, bArr2, i2 + 4);
        a(a(a3, iArr[i3]), bArr2, i2 + 6);
    }

    private int[] a(byte[] bArr) {
        int i;
        int[] iArr = new int[52];
        if (bArr.length < 16) {
            Object obj = new byte[16];
            System.arraycopy(bArr, 0, obj, obj.length - bArr.length, bArr.length);
            bArr = obj;
        }
        for (i = 0; i < 8; i++) {
            iArr[i] = a(bArr, i * 2);
        }
        for (i = 8; i < 52; i++) {
            if ((i & 7) < 6) {
                iArr[i] = (((iArr[i - 7] & 127) << 9) | (iArr[i - 6] >> 7)) & 65535;
            } else if ((i & 7) == 6) {
                iArr[i] = (((iArr[i - 7] & 127) << 9) | (iArr[i - 14] >> 7)) & 65535;
            } else {
                iArr[i] = (((iArr[i - 15] & 127) << 9) | (iArr[i - 14] >> 7)) & 65535;
            }
        }
        return iArr;
    }

    private int b(int i) {
        if (i < 2) {
            return i;
        }
        int i2 = 65537 / i;
        int i3 = 65537 % i;
        int i4 = i;
        i = 1;
        while (i3 != 1) {
            int i5 = i4 / i3;
            i4 %= i3;
            i = ((i5 * i2) + i) & 65535;
            if (i4 == 1) {
                return i;
            }
            i5 = i3 / i4;
            i3 %= i4;
            i2 = (i2 + (i5 * i)) & 65535;
        }
        return (1 - i2) & 65535;
    }

    int a(int i) {
        return (0 - i) & 65535;
    }

    private int[] a(int[] iArr) {
        int i;
        int i2 = 1;
        int[] iArr2 = new int[52];
        int b = b(iArr[0]);
        int a = a(iArr[1]);
        int a2 = a(iArr[2]);
        int i3 = 4;
        iArr2[51] = b(iArr[3]);
        iArr2[50] = a2;
        iArr2[49] = a;
        a = 48;
        iArr2[48] = b;
        while (i2 < 8) {
            b = i3 + 1;
            i3 = iArr[i3];
            i = b + 1;
            a--;
            iArr2[a] = iArr[b];
            a--;
            iArr2[a] = i3;
            i3 = i + 1;
            b = b(iArr[i]);
            i = i3 + 1;
            a2 = a(iArr[i3]);
            int i4 = i + 1;
            i = a(iArr[i]);
            i3 = i4 + 1;
            a--;
            iArr2[a] = b(iArr[i4]);
            a--;
            iArr2[a] = a2;
            a--;
            iArr2[a] = i;
            a--;
            iArr2[a] = b;
            i2++;
        }
        i2 = i3 + 1;
        i3 = iArr[i3];
        b = i2 + 1;
        a--;
        iArr2[a] = iArr[i2];
        i2 = a - 1;
        iArr2[i2] = i3;
        i3 = b + 1;
        a = b(iArr[b]);
        b = i3 + 1;
        i3 = a(iArr[i3]);
        i = b + 1;
        b = a(iArr[b]);
        i2--;
        iArr2[i2] = b(iArr[i]);
        i2--;
        iArr2[i2] = b;
        i2--;
        iArr2[i2] = i3;
        iArr2[i2 - 1] = a;
        return iArr2;
    }

    private int[] a(boolean z, byte[] bArr) {
        if (z) {
            return a(bArr);
        }
        return a(a(bArr));
    }
}
