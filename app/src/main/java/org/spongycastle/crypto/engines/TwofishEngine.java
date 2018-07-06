package org.spongycastle.crypto.engines;

import android.support.v4.app.NotificationCompat;
import net.hockeyapp.android.k;
import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.OutputLengthException;
import org.spongycastle.crypto.params.KeyParameter;

public final class TwofishEngine implements BlockCipher {
    private static final byte[][] a = new byte[][]{new byte[]{(byte) -87, (byte) 103, (byte) -77, (byte) -24, (byte) 4, (byte) -3, (byte) -93, (byte) 118, (byte) -102, (byte) -110, Byte.MIN_VALUE, (byte) 120, (byte) -28, (byte) -35, (byte) -47, (byte) 56, (byte) 13, (byte) -58, (byte) 53, (byte) -104, (byte) 24, (byte) -9, (byte) -20, (byte) 108, (byte) 67, (byte) 117, (byte) 55, (byte) 38, (byte) -6, (byte) 19, (byte) -108, (byte) 72, (byte) -14, (byte) -48, (byte) -117, (byte) 48, (byte) -124, (byte) 84, (byte) -33, (byte) 35, (byte) 25, (byte) 91, (byte) 61, (byte) 89, (byte) -13, (byte) -82, (byte) -94, (byte) -126, (byte) 99, (byte) 1, (byte) -125, (byte) 46, (byte) -39, (byte) 81, (byte) -101, (byte) 124, (byte) -90, (byte) -21, (byte) -91, (byte) -66, (byte) 22, (byte) 12, (byte) -29, (byte) 97, (byte) -64, (byte) -116, (byte) 58, (byte) -11, (byte) 115, (byte) 44, (byte) 37, (byte) 11, (byte) -69, (byte) 78, (byte) -119, (byte) 107, (byte) 83, (byte) 106, (byte) -76, (byte) -15, (byte) -31, (byte) -26, (byte) -67, (byte) 69, (byte) -30, (byte) -12, (byte) -74, (byte) 102, (byte) -52, (byte) -107, (byte) 3, (byte) 86, (byte) -44, (byte) 28, (byte) 30, (byte) -41, (byte) -5, (byte) -61, (byte) -114, (byte) -75, (byte) -23, (byte) -49, (byte) -65, (byte) -70, (byte) -22, (byte) 119, (byte) 57, (byte) -81, (byte) 51, (byte) -55, (byte) 98, (byte) 113, (byte) -127, (byte) 121, (byte) 9, (byte) -83, (byte) 36, (byte) -51, (byte) -7, (byte) -40, (byte) -27, (byte) -59, (byte) -71, (byte) 77, (byte) 68, (byte) 8, (byte) -122, (byte) -25, (byte) -95, (byte) 29, (byte) -86, (byte) -19, (byte) 6, (byte) 112, (byte) -78, (byte) -46, (byte) 65, (byte) 123, (byte) -96, (byte) 17, (byte) 49, (byte) -62, (byte) 39, (byte) -112, (byte) 32, (byte) -10, (byte) 96, (byte) -1, (byte) -106, (byte) 92, (byte) -79, (byte) -85, (byte) -98, (byte) -100, (byte) 82, (byte) 27, (byte) 95, (byte) -109, (byte) 10, (byte) -17, (byte) -111, (byte) -123, (byte) 73, (byte) -18, (byte) 45, (byte) 79, (byte) -113, (byte) 59, (byte) 71, (byte) -121, (byte) 109, (byte) 70, (byte) -42, (byte) 62, (byte) 105, (byte) 100, (byte) 42, (byte) -50, (byte) -53, (byte) 47, (byte) -4, (byte) -105, (byte) 5, (byte) 122, (byte) -84, Byte.MAX_VALUE, (byte) -43, (byte) 26, (byte) 75, (byte) 14, (byte) -89, (byte) 90, (byte) 40, (byte) 20, (byte) 63, (byte) 41, (byte) -120, (byte) 60, (byte) 76, (byte) 2, (byte) -72, (byte) -38, (byte) -80, (byte) 23, (byte) 85, (byte) 31, (byte) -118, (byte) 125, (byte) 87, (byte) -57, (byte) -115, (byte) 116, (byte) -73, (byte) -60, (byte) -97, (byte) 114, (byte) 126, (byte) 21, (byte) 34, (byte) 18, (byte) 88, (byte) 7, (byte) -103, (byte) 52, (byte) 110, (byte) 80, (byte) -34, (byte) 104, (byte) 101, (byte) -68, (byte) -37, (byte) -8, (byte) -56, (byte) -88, (byte) 43, (byte) 64, (byte) -36, (byte) -2, (byte) 50, (byte) -92, (byte) -54, (byte) 16, (byte) 33, (byte) -16, (byte) -45, (byte) 93, (byte) 15, (byte) 0, (byte) 111, (byte) -99, (byte) 54, (byte) 66, (byte) 74, (byte) 94, (byte) -63, (byte) -32}, new byte[]{(byte) 117, (byte) -13, (byte) -58, (byte) -12, (byte) -37, (byte) 123, (byte) -5, (byte) -56, (byte) 74, (byte) -45, (byte) -26, (byte) 107, (byte) 69, (byte) 125, (byte) -24, (byte) 75, (byte) -42, (byte) 50, (byte) -40, (byte) -3, (byte) 55, (byte) 113, (byte) -15, (byte) -31, (byte) 48, (byte) 15, (byte) -8, (byte) 27, (byte) -121, (byte) -6, (byte) 6, (byte) 63, (byte) 94, (byte) -70, (byte) -82, (byte) 91, (byte) -118, (byte) 0, (byte) -68, (byte) -99, (byte) 109, (byte) -63, (byte) -79, (byte) 14, Byte.MIN_VALUE, (byte) 93, (byte) -46, (byte) -43, (byte) -96, (byte) -124, (byte) 7, (byte) 20, (byte) -75, (byte) -112, (byte) 44, (byte) -93, (byte) -78, (byte) 115, (byte) 76, (byte) 84, (byte) -110, (byte) 116, (byte) 54, (byte) 81, (byte) 56, (byte) -80, (byte) -67, (byte) 90, (byte) -4, (byte) 96, (byte) 98, (byte) -106, (byte) 108, (byte) 66, (byte) -9, (byte) 16, (byte) 124, (byte) 40, (byte) 39, (byte) -116, (byte) 19, (byte) -107, (byte) -100, (byte) -57, (byte) 36, (byte) 70, (byte) 59, (byte) 112, (byte) -54, (byte) -29, (byte) -123, (byte) -53, (byte) 17, (byte) -48, (byte) -109, (byte) -72, (byte) -90, (byte) -125, (byte) 32, (byte) -1, (byte) -97, (byte) 119, (byte) -61, (byte) -52, (byte) 3, (byte) 111, (byte) 8, (byte) -65, (byte) 64, (byte) -25, (byte) 43, (byte) -30, (byte) 121, (byte) 12, (byte) -86, (byte) -126, (byte) 65, (byte) 58, (byte) -22, (byte) -71, (byte) -28, (byte) -102, (byte) -92, (byte) -105, (byte) 126, (byte) -38, (byte) 122, (byte) 23, (byte) 102, (byte) -108, (byte) -95, (byte) 29, (byte) 61, (byte) -16, (byte) -34, (byte) -77, (byte) 11, (byte) 114, (byte) -89, (byte) 28, (byte) -17, (byte) -47, (byte) 83, (byte) 62, (byte) -113, (byte) 51, (byte) 38, (byte) 95, (byte) -20, (byte) 118, (byte) 42, (byte) 73, (byte) -127, (byte) -120, (byte) -18, (byte) 33, (byte) -60, (byte) 26, (byte) -21, (byte) -39, (byte) -59, (byte) 57, (byte) -103, (byte) -51, (byte) -83, (byte) 49, (byte) -117, (byte) 1, (byte) 24, (byte) 35, (byte) -35, (byte) 31, (byte) 78, (byte) 45, (byte) -7, (byte) 72, (byte) 79, (byte) -14, (byte) 101, (byte) -114, (byte) 120, (byte) 92, (byte) 88, (byte) 25, (byte) -115, (byte) -27, (byte) -104, (byte) 87, (byte) 103, Byte.MAX_VALUE, (byte) 5, (byte) 100, (byte) -81, (byte) 99, (byte) -74, (byte) -2, (byte) -11, (byte) -73, (byte) 60, (byte) -91, (byte) -50, (byte) -23, (byte) 104, (byte) 68, (byte) -32, (byte) 77, (byte) 67, (byte) 105, (byte) 41, (byte) 46, (byte) -84, (byte) 21, (byte) 89, (byte) -88, (byte) 10, (byte) -98, (byte) 110, (byte) 71, (byte) -33, (byte) 52, (byte) 53, (byte) 106, (byte) -49, (byte) -36, (byte) 34, (byte) -55, (byte) -64, (byte) -101, (byte) -119, (byte) -44, (byte) -19, (byte) -85, (byte) 18, (byte) -94, (byte) 13, (byte) 82, (byte) -69, (byte) 2, (byte) 47, (byte) -87, (byte) -41, (byte) 97, (byte) 30, (byte) -76, (byte) 80, (byte) 4, (byte) -10, (byte) -62, (byte) 22, (byte) 37, (byte) -122, (byte) 86, (byte) 85, (byte) 9, (byte) -66, (byte) -111}};
    private boolean b = false;
    private int[] c = new int[256];
    private int[] d = new int[256];
    private int[] e = new int[256];
    private int[] f = new int[256];
    private int[] g;
    private int[] h;
    private int i = 0;
    private byte[] j = null;

    public TwofishEngine() {
        int[] iArr = new int[2];
        int[] iArr2 = new int[2];
        int[] iArr3 = new int[2];
        for (int i = 0; i < 256; i++) {
            int i2 = a[0][i] & 255;
            iArr[0] = i2;
            iArr2[0] = d(i2) & 255;
            iArr3[0] = e(i2) & 255;
            i2 = a[1][i] & 255;
            iArr[1] = i2;
            iArr2[1] = d(i2) & 255;
            iArr3[1] = e(i2) & 255;
            this.c[i] = ((iArr[1] | (iArr2[1] << 8)) | (iArr3[1] << 16)) | (iArr3[1] << 24);
            this.d[i] = ((iArr3[0] | (iArr3[0] << 8)) | (iArr2[0] << 16)) | (iArr[0] << 24);
            this.e[i] = ((iArr2[1] | (iArr3[1] << 8)) | (iArr[1] << 16)) | (iArr3[1] << 24);
            this.f[i] = ((iArr2[0] | (iArr[0] << 8)) | (iArr3[0] << 16)) | (iArr2[0] << 24);
        }
    }

    public void a(boolean z, CipherParameters cipherParameters) {
        if (cipherParameters instanceof KeyParameter) {
            this.b = z;
            this.j = ((KeyParameter) cipherParameters).a();
            this.i = this.j.length / 8;
            a(this.j);
            return;
        }
        throw new IllegalArgumentException("invalid parameter passed to Twofish init - " + cipherParameters.getClass().getName());
    }

    public String a() {
        return "Twofish";
    }

    public int a(byte[] bArr, int i, byte[] bArr2, int i2) {
        if (this.j == null) {
            throw new IllegalStateException("Twofish not initialised");
        } else if (i + 16 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        } else if (i2 + 16 > bArr2.length) {
            throw new OutputLengthException("output buffer too short");
        } else {
            if (this.b) {
                b(bArr, i, bArr2, i2);
            } else {
                c(bArr, i, bArr2, i2);
            }
            return 16;
        }
    }

    public void c() {
        if (this.j != null) {
            a(this.j);
        }
    }

    public int b() {
        return 16;
    }

    private void a(byte[] bArr) {
        int[] iArr = new int[4];
        int[] iArr2 = new int[4];
        int[] iArr3 = new int[4];
        this.g = new int[40];
        if (this.i < 1) {
            throw new IllegalArgumentException("Key size less than 64 bits");
        } else if (this.i > 4) {
            throw new IllegalArgumentException("Key size larger than 256 bits");
        } else {
            int i;
            int i2;
            int a;
            for (i = 0; i < this.i; i++) {
                i2 = i * 8;
                iArr[i] = a(bArr, i2);
                iArr2[i] = a(bArr, i2 + 4);
                iArr3[(this.i - 1) - i] = a(iArr[i], iArr2[i]);
            }
            for (i = 0; i < 20; i++) {
                i2 = 33686018 * i;
                a = a(i2, iArr);
                i2 = a(i2 + 16843009, iArr2);
                i2 = (i2 >>> 24) | (i2 << 8);
                a += i2;
                this.g[i * 2] = a;
                i2 += a;
                this.g[(i * 2) + 1] = (i2 >>> 23) | (i2 << 9);
            }
            a = iArr3[0];
            int i3 = iArr3[1];
            int i4 = iArr3[2];
            int i5 = iArr3[3];
            this.h = new int[k.FEEDBACK_FAILED_TITLE_ID];
            for (int i6 = 0; i6 < 256; i6++) {
                int g;
                int h;
                switch (this.i & 3) {
                    case 0:
                        i2 = (a[1][i6] & 255) ^ f(i5);
                        g = (a[0][i6] & 255) ^ g(i5);
                        h = h(i5) ^ (a[0][i6] & 255);
                        i = (a[1][i6] & 255) ^ i(i5);
                        break;
                    case 1:
                        this.h[i6 * 2] = this.c[(a[0][i6] & 255) ^ f(a)];
                        this.h[(i6 * 2) + 1] = this.d[(a[0][i6] & 255) ^ g(a)];
                        this.h[(i6 * 2) + 512] = this.e[(a[1][i6] & 255) ^ h(a)];
                        this.h[(i6 * 2) + k.UPDATE_DIALOG_TITLE_ID] = this.f[(a[1][i6] & 255) ^ i(a)];
                        continue;
                    case 2:
                        i = i6;
                        h = i6;
                        g = i6;
                        i2 = i6;
                        break;
                    case 3:
                        i = i6;
                        h = i6;
                        g = i6;
                        i2 = i6;
                        break;
                    default:
                        break;
                }
                i2 = (a[1][i2] & 255) ^ f(i4);
                g = (a[1][g] & 255) ^ g(i4);
                h = (a[0][h] & 255) ^ h(i4);
                i = (a[0][i] & 255) ^ i(i4);
                this.h[i6 * 2] = this.c[(a[0][(a[0][i2] & 255) ^ f(i3)] & 255) ^ f(a)];
                this.h[(i6 * 2) + 1] = this.d[(a[0][(a[1][g] & 255) ^ g(i3)] & 255) ^ g(a)];
                this.h[(i6 * 2) + 512] = this.e[(a[1][(a[0][h] & 255) ^ h(i3)] & 255) ^ h(a)];
                this.h[(i6 * 2) + k.UPDATE_DIALOG_TITLE_ID] = this.f[(a[1][(a[1][i] & 255) ^ i(i3)] & 255) ^ i(a)];
            }
        }
    }

    private void b(byte[] bArr, int i, byte[] bArr2, int i2) {
        int i3 = 0;
        int a = a(bArr, i) ^ this.g[0];
        int a2 = a(bArr, i + 4) ^ this.g[1];
        int a3 = a(bArr, i + 8) ^ this.g[2];
        int a4 = this.g[3] ^ a(bArr, i + 12);
        int i4 = 8;
        while (i3 < 16) {
            int j = j(a);
            int k = k(a2);
            int i5 = i4 + 1;
            i4 = (this.g[i4] + (j + k)) ^ a3;
            a3 = (i4 >>> 1) | (i4 << 31);
            k = i5 + 1;
            a4 = (((k * 2) + j) + this.g[i5]) ^ ((a4 << 1) | (a4 >>> 31));
            i4 = j(a3);
            j = k(a4);
            i5 = k + 1;
            a ^= this.g[k] + (i4 + j);
            a = (a << 31) | (a >>> 1);
            j = (j * 2) + i4;
            i4 = i5 + 1;
            a2 = ((a2 >>> 31) | (a2 << 1)) ^ (j + this.g[i5]);
            i3 += 2;
        }
        a(this.g[4] ^ a3, bArr2, i2);
        a(this.g[5] ^ a4, bArr2, i2 + 4);
        a(this.g[6] ^ a, bArr2, i2 + 8);
        a(this.g[7] ^ a2, bArr2, i2 + 12);
    }

    private void c(byte[] bArr, int i, byte[] bArr2, int i2) {
        int a = a(bArr, i) ^ this.g[4];
        int a2 = a(bArr, i + 4) ^ this.g[5];
        int a3 = a(bArr, i + 8) ^ this.g[6];
        int a4 = a(bArr, i + 12) ^ this.g[7];
        int i3 = 39;
        for (int i4 = 0; i4 < 16; i4 += 2) {
            int j = j(a);
            int k = k(a2);
            int i5 = i3 - 1;
            i3 = (this.g[i3] + ((k * 2) + j)) ^ a4;
            a4 = (a3 << 1) | (a3 >>> 31);
            a3 = j + k;
            k = i5 - 1;
            a3 = (a3 + this.g[i5]) ^ a4;
            a4 = (i3 >>> 1) | (i3 << 31);
            i3 = j(a3);
            j = k(a4);
            i5 = k - 1;
            a2 ^= this.g[k] + ((j * 2) + i3);
            j += i3;
            i3 = i5 - 1;
            a = ((a >>> 31) | (a << 1)) ^ (j + this.g[i5]);
            a2 = (a2 << 31) | (a2 >>> 1);
        }
        a(this.g[0] ^ a3, bArr2, i2);
        a(this.g[1] ^ a4, bArr2, i2 + 4);
        a(this.g[2] ^ a, bArr2, i2 + 8);
        a(this.g[3] ^ a2, bArr2, i2 + 12);
    }

    private int a(int i, int[] iArr) {
        int f = f(i);
        int g = g(i);
        int h = h(i);
        int i2 = i(i);
        int i3 = iArr[0];
        int i4 = iArr[1];
        int i5 = iArr[2];
        int i6 = iArr[3];
        switch (this.i & 3) {
            case 0:
                f = (a[1][f] & 255) ^ f(i6);
                g = (a[0][g] & 255) ^ g(i6);
                h = (a[0][h] & 255) ^ h(i6);
                i2 = (a[1][i2] & 255) ^ i(i6);
                break;
            case 1:
                return this.f[(a[1][i2] & 255) ^ i(i3)] ^ (this.e[(a[1][h] & 255) ^ h(i3)] ^ (this.d[(a[0][g] & 255) ^ g(i3)] ^ this.c[(a[0][f] & 255) ^ f(i3)]));
            case 2:
                break;
            case 3:
                break;
            default:
                return 0;
        }
        f = (a[1][f] & 255) ^ f(i5);
        g = (a[1][g] & 255) ^ g(i5);
        h = (a[0][h] & 255) ^ h(i5);
        i2 = (a[0][i2] & 255) ^ i(i5);
        return this.f[(a[1][(a[1][i2] & 255) ^ i(i4)] & 255) ^ i(i3)] ^ (this.e[(a[1][(a[0][h] & 255) ^ h(i4)] & 255) ^ h(i3)] ^ (this.d[(a[0][(a[1][g] & 255) ^ g(i4)] & 255) ^ g(i3)] ^ this.c[(a[0][(a[0][f] & 255) ^ f(i4)] & 255) ^ f(i3)]));
    }

    private int a(int i, int i2) {
        int i3;
        int i4 = 0;
        for (i3 = 0; i3 < 4; i3++) {
            i2 = a(i2);
        }
        i3 = i2 ^ i;
        while (i4 < 4) {
            i3 = a(i3);
            i4++;
        }
        return i3;
    }

    private int a(int i) {
        int i2;
        int i3 = 0;
        int i4 = (i >>> 24) & 255;
        int i5 = i4 << 1;
        if ((i4 & NotificationCompat.FLAG_HIGH_PRIORITY) != 0) {
            i2 = 333;
        } else {
            i2 = 0;
        }
        i2 = (i2 ^ i5) & 255;
        i5 = i4 >>> 1;
        if ((i4 & 1) != 0) {
            i3 = 166;
        }
        i3 = (i3 ^ i5) ^ i2;
        return ((i3 << 8) ^ ((i2 << 16) ^ ((i << 8) ^ (i3 << 24)))) ^ i4;
    }

    private int b(int i) {
        return ((i & 1) != 0 ? 180 : 0) ^ (i >> 1);
    }

    private int c(int i) {
        int i2 = 0;
        int i3 = ((i & 2) != 0 ? 180 : 0) ^ (i >> 2);
        if ((i & 1) != 0) {
            i2 = 90;
        }
        return i2 ^ i3;
    }

    private int d(int i) {
        return c(i) ^ i;
    }

    private int e(int i) {
        return (b(i) ^ i) ^ c(i);
    }

    private int f(int i) {
        return i & 255;
    }

    private int g(int i) {
        return (i >>> 8) & 255;
    }

    private int h(int i) {
        return (i >>> 16) & 255;
    }

    private int i(int i) {
        return (i >>> 24) & 255;
    }

    private int j(int i) {
        return ((this.h[((i & 255) * 2) + 0] ^ this.h[(((i >>> 8) & 255) * 2) + 1]) ^ this.h[(((i >>> 16) & 255) * 2) + 512]) ^ this.h[(((i >>> 24) & 255) * 2) + k.UPDATE_DIALOG_TITLE_ID];
    }

    private int k(int i) {
        return ((this.h[(((i >>> 24) & 255) * 2) + 0] ^ this.h[((i & 255) * 2) + 1]) ^ this.h[(((i >>> 8) & 255) * 2) + 512]) ^ this.h[(((i >>> 16) & 255) * 2) + k.UPDATE_DIALOG_TITLE_ID];
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
