package org.spongycastle.crypto.engines;

import com.fgmicrotec.mobile.android.fgmag.VoIP;
import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.OutputLengthException;
import org.spongycastle.crypto.params.KeyParameter;

public class CamelliaLightEngine implements BlockCipher {
    private static final int[] g = new int[]{-1600231809, 1003262091, -1233459112, 1286239154, -957401297, -380665154, 1426019237, -237801700, 283453434, -563598051, -1336506174, -1276722691};
    private static final byte[] h = new byte[]{(byte) 112, (byte) -126, (byte) 44, (byte) -20, (byte) -77, (byte) 39, (byte) -64, (byte) -27, (byte) -28, (byte) -123, (byte) 87, (byte) 53, (byte) -22, (byte) 12, (byte) -82, (byte) 65, (byte) 35, (byte) -17, (byte) 107, (byte) -109, (byte) 69, (byte) 25, (byte) -91, (byte) 33, (byte) -19, (byte) 14, (byte) 79, (byte) 78, (byte) 29, (byte) 101, (byte) -110, (byte) -67, (byte) -122, (byte) -72, (byte) -81, (byte) -113, (byte) 124, (byte) -21, (byte) 31, (byte) -50, (byte) 62, (byte) 48, (byte) -36, (byte) 95, (byte) 94, (byte) -59, (byte) 11, (byte) 26, (byte) -90, (byte) -31, (byte) 57, (byte) -54, (byte) -43, (byte) 71, (byte) 93, (byte) 61, (byte) -39, (byte) 1, (byte) 90, (byte) -42, (byte) 81, (byte) 86, (byte) 108, (byte) 77, (byte) -117, (byte) 13, (byte) -102, (byte) 102, (byte) -5, (byte) -52, (byte) -80, (byte) 45, (byte) 116, (byte) 18, (byte) 43, (byte) 32, (byte) -16, (byte) -79, (byte) -124, (byte) -103, (byte) -33, (byte) 76, (byte) -53, (byte) -62, (byte) 52, (byte) 126, (byte) 118, (byte) 5, (byte) 109, (byte) -73, (byte) -87, (byte) 49, (byte) -47, (byte) 23, (byte) 4, (byte) -41, (byte) 20, (byte) 88, (byte) 58, (byte) 97, (byte) -34, (byte) 27, (byte) 17, (byte) 28, (byte) 50, (byte) 15, (byte) -100, (byte) 22, (byte) 83, (byte) 24, (byte) -14, (byte) 34, (byte) -2, (byte) 68, (byte) -49, (byte) -78, (byte) -61, (byte) -75, (byte) 122, (byte) -111, (byte) 36, (byte) 8, (byte) -24, (byte) -88, (byte) 96, (byte) -4, (byte) 105, (byte) 80, (byte) -86, (byte) -48, (byte) -96, (byte) 125, (byte) -95, (byte) -119, (byte) 98, (byte) -105, (byte) 84, (byte) 91, (byte) 30, (byte) -107, (byte) -32, (byte) -1, (byte) 100, (byte) -46, (byte) 16, (byte) -60, (byte) 0, (byte) 72, (byte) -93, (byte) -9, (byte) 117, (byte) -37, (byte) -118, (byte) 3, (byte) -26, (byte) -38, (byte) 9, (byte) 63, (byte) -35, (byte) -108, (byte) -121, (byte) 92, (byte) -125, (byte) 2, (byte) -51, (byte) 74, (byte) -112, (byte) 51, (byte) 115, (byte) 103, (byte) -10, (byte) -13, (byte) -99, Byte.MAX_VALUE, (byte) -65, (byte) -30, (byte) 82, (byte) -101, (byte) -40, (byte) 38, (byte) -56, (byte) 55, (byte) -58, (byte) 59, (byte) -127, (byte) -106, (byte) 111, (byte) 75, (byte) 19, (byte) -66, (byte) 99, (byte) 46, (byte) -23, (byte) 121, (byte) -89, (byte) -116, (byte) -97, (byte) 110, (byte) -68, (byte) -114, (byte) 41, (byte) -11, (byte) -7, (byte) -74, (byte) 47, (byte) -3, (byte) -76, (byte) 89, (byte) 120, (byte) -104, (byte) 6, (byte) 106, (byte) -25, (byte) 70, (byte) 113, (byte) -70, (byte) -44, (byte) 37, (byte) -85, (byte) 66, (byte) -120, (byte) -94, (byte) -115, (byte) -6, (byte) 114, (byte) 7, (byte) -71, (byte) 85, (byte) -8, (byte) -18, (byte) -84, (byte) 10, (byte) 54, (byte) 73, (byte) 42, (byte) 104, (byte) 60, (byte) 56, (byte) -15, (byte) -92, (byte) 64, (byte) 40, (byte) -45, (byte) 123, (byte) -69, (byte) -55, (byte) 67, (byte) -63, (byte) 21, (byte) -29, (byte) -83, (byte) -12, (byte) 119, (byte) -57, Byte.MIN_VALUE, (byte) -98};
    private boolean a;
    private boolean b;
    private int[] c = new int[96];
    private int[] d = new int[8];
    private int[] e = new int[12];
    private int[] f = new int[4];

    private static int a(int i, int i2) {
        return (i >>> i2) + (i << (32 - i2));
    }

    private static int b(int i, int i2) {
        return (i << i2) + (i >>> (32 - i2));
    }

    private static void a(int i, int[] iArr, int i2, int[] iArr2, int i3) {
        iArr2[i3 + 0] = (iArr[i2 + 0] << i) | (iArr[i2 + 1] >>> (32 - i));
        iArr2[i3 + 1] = (iArr[i2 + 1] << i) | (iArr[i2 + 2] >>> (32 - i));
        iArr2[i3 + 2] = (iArr[i2 + 2] << i) | (iArr[i2 + 3] >>> (32 - i));
        iArr2[i3 + 3] = (iArr[i2 + 3] << i) | (iArr[i2 + 0] >>> (32 - i));
        iArr[i2 + 0] = iArr2[i3 + 0];
        iArr[i2 + 1] = iArr2[i3 + 1];
        iArr[i2 + 2] = iArr2[i3 + 2];
        iArr[i2 + 3] = iArr2[i3 + 3];
    }

    private static void b(int i, int[] iArr, int i2, int[] iArr2, int i3) {
        iArr2[i3 + 2] = (iArr[i2 + 0] << i) | (iArr[i2 + 1] >>> (32 - i));
        iArr2[i3 + 3] = (iArr[i2 + 1] << i) | (iArr[i2 + 2] >>> (32 - i));
        iArr2[i3 + 0] = (iArr[i2 + 2] << i) | (iArr[i2 + 3] >>> (32 - i));
        iArr2[i3 + 1] = (iArr[i2 + 3] << i) | (iArr[i2 + 0] >>> (32 - i));
        iArr[i2 + 0] = iArr2[i3 + 2];
        iArr[i2 + 1] = iArr2[i3 + 3];
        iArr[i2 + 2] = iArr2[i3 + 0];
        iArr[i2 + 3] = iArr2[i3 + 1];
    }

    private static void c(int i, int[] iArr, int i2, int[] iArr2, int i3) {
        iArr2[i3 + 0] = (iArr[i2 + 1] << (i - 32)) | (iArr[i2 + 2] >>> (64 - i));
        iArr2[i3 + 1] = (iArr[i2 + 2] << (i - 32)) | (iArr[i2 + 3] >>> (64 - i));
        iArr2[i3 + 2] = (iArr[i2 + 3] << (i - 32)) | (iArr[i2 + 0] >>> (64 - i));
        iArr2[i3 + 3] = (iArr[i2 + 0] << (i - 32)) | (iArr[i2 + 1] >>> (64 - i));
        iArr[i2 + 0] = iArr2[i3 + 0];
        iArr[i2 + 1] = iArr2[i3 + 1];
        iArr[i2 + 2] = iArr2[i3 + 2];
        iArr[i2 + 3] = iArr2[i3 + 3];
    }

    private static void d(int i, int[] iArr, int i2, int[] iArr2, int i3) {
        iArr2[i3 + 2] = (iArr[i2 + 1] << (i - 32)) | (iArr[i2 + 2] >>> (64 - i));
        iArr2[i3 + 3] = (iArr[i2 + 2] << (i - 32)) | (iArr[i2 + 3] >>> (64 - i));
        iArr2[i3 + 0] = (iArr[i2 + 3] << (i - 32)) | (iArr[i2 + 0] >>> (64 - i));
        iArr2[i3 + 1] = (iArr[i2 + 0] << (i - 32)) | (iArr[i2 + 1] >>> (64 - i));
        iArr[i2 + 0] = iArr2[i3 + 2];
        iArr[i2 + 1] = iArr2[i3 + 3];
        iArr[i2 + 2] = iArr2[i3 + 0];
        iArr[i2 + 3] = iArr2[i3 + 1];
    }

    private int a(byte[] bArr, int i) {
        int i2 = 0;
        int i3 = 0;
        while (i2 < 4) {
            i3 = (i3 << 8) + (bArr[i2 + i] & 255);
            i2++;
        }
        return i3;
    }

    private void a(int i, byte[] bArr, int i2) {
        for (int i3 = 0; i3 < 4; i3++) {
            bArr[(3 - i3) + i2] = (byte) i;
            i >>>= 8;
        }
    }

    private byte a(byte b, int i) {
        return (byte) ((b << i) | ((b & 255) >>> (8 - i)));
    }

    private int a(int i) {
        return a(h[i], 1) & 255;
    }

    private int b(int i) {
        return a(h[i], 7) & 255;
    }

    private int c(int i) {
        return h[a((byte) i, 1) & 255] & 255;
    }

    private void a(int[] iArr, int[] iArr2, int i) {
        int i2 = iArr[0] ^ iArr2[i + 0];
        i2 = ((h[(i2 >>> 24) & 255] & 255) << 24) | ((c(i2 & 255) | (b((i2 >>> 8) & 255) << 8)) | (a((i2 >>> 16) & 255) << 16));
        int i3 = iArr[1] ^ iArr2[i + 1];
        i3 = b((a((i3 >>> 24) & 255) << 24) | (((h[i3 & 255] & 255) | (c((i3 >>> 8) & 255) << 8)) | (b((i3 >>> 16) & 255) << 16)), 8);
        i2 ^= i3;
        i3 = b(i3, 8) ^ i2;
        i2 = a(i2, 8) ^ i3;
        iArr[2] = (b(i3, 16) ^ i2) ^ iArr[2];
        iArr[3] = b(i2, 8) ^ iArr[3];
        i2 = iArr[2] ^ iArr2[i + 2];
        i2 = ((h[(i2 >>> 24) & 255] & 255) << 24) | ((c(i2 & 255) | (b((i2 >>> 8) & 255) << 8)) | (a((i2 >>> 16) & 255) << 16));
        i3 = iArr[3] ^ iArr2[i + 3];
        i3 = b((a((i3 >>> 24) & 255) << 24) | (((h[i3 & 255] & 255) | (c((i3 >>> 8) & 255) << 8)) | (b((i3 >>> 16) & 255) << 16)), 8);
        i2 ^= i3;
        i3 = b(i3, 8) ^ i2;
        i2 = a(i2, 8) ^ i3;
        iArr[0] = (b(i3, 16) ^ i2) ^ iArr[0];
        iArr[1] = b(i2, 8) ^ iArr[1];
    }

    private void b(int[] iArr, int[] iArr2, int i) {
        iArr[1] = iArr[1] ^ b(iArr[0] & iArr2[i + 0], 1);
        iArr[0] = iArr[0] ^ (iArr2[i + 1] | iArr[1]);
        iArr[2] = iArr[2] ^ (iArr2[i + 3] | iArr[3]);
        iArr[3] = iArr[3] ^ b(iArr2[i + 2] & iArr[2], 1);
    }

    private void a(boolean z, byte[] bArr) {
        int i;
        int[] iArr = new int[8];
        int[] iArr2 = new int[4];
        int[] iArr3 = new int[4];
        int[] iArr4 = new int[4];
        switch (bArr.length) {
            case 16:
                this.b = true;
                iArr[0] = a(bArr, 0);
                iArr[1] = a(bArr, 4);
                iArr[2] = a(bArr, 8);
                iArr[3] = a(bArr, 12);
                iArr[7] = 0;
                iArr[6] = 0;
                iArr[5] = 0;
                iArr[4] = 0;
                break;
            case VoIP.ERR_SDP_NEGOTIATION_FAILED /*24*/:
                iArr[0] = a(bArr, 0);
                iArr[1] = a(bArr, 4);
                iArr[2] = a(bArr, 8);
                iArr[3] = a(bArr, 12);
                iArr[4] = a(bArr, 16);
                iArr[5] = a(bArr, 20);
                iArr[6] = iArr[4] ^ -1;
                iArr[7] = iArr[5] ^ -1;
                this.b = false;
                break;
            case 32:
                iArr[0] = a(bArr, 0);
                iArr[1] = a(bArr, 4);
                iArr[2] = a(bArr, 8);
                iArr[3] = a(bArr, 12);
                iArr[4] = a(bArr, 16);
                iArr[5] = a(bArr, 20);
                iArr[6] = a(bArr, 24);
                iArr[7] = a(bArr, 28);
                this.b = false;
                break;
            default:
                throw new IllegalArgumentException("key sizes are only 16/24/32 bytes.");
        }
        for (i = 0; i < 4; i++) {
            iArr2[i] = iArr[i] ^ iArr[i + 4];
        }
        a(iArr2, g, 0);
        for (i = 0; i < 4; i++) {
            iArr2[i] = iArr2[i] ^ iArr[i];
        }
        a(iArr2, g, 4);
        if (!this.b) {
            for (i = 0; i < 4; i++) {
                iArr3[i] = iArr2[i] ^ iArr[i + 4];
            }
            a(iArr3, g, 8);
            if (z) {
                this.d[0] = iArr[0];
                this.d[1] = iArr[1];
                this.d[2] = iArr[2];
                this.d[3] = iArr[3];
                c(45, iArr, 0, this.c, 16);
                a(15, iArr, 0, this.e, 4);
                a(17, iArr, 0, this.c, 32);
                c(34, iArr, 0, this.c, 44);
                a(15, iArr, 4, this.c, 4);
                a(15, iArr, 4, this.e, 0);
                a(30, iArr, 4, this.c, 24);
                c(34, iArr, 4, this.c, 36);
                a(15, iArr2, 0, this.c, 8);
                a(30, iArr2, 0, this.c, 20);
                this.e[8] = iArr2[1];
                this.e[9] = iArr2[2];
                this.e[10] = iArr2[3];
                this.e[11] = iArr2[0];
                c(49, iArr2, 0, this.c, 40);
                this.c[0] = iArr3[0];
                this.c[1] = iArr3[1];
                this.c[2] = iArr3[2];
                this.c[3] = iArr3[3];
                a(30, iArr3, 0, this.c, 12);
                a(30, iArr3, 0, this.c, 28);
                c(51, iArr3, 0, this.d, 4);
                return;
            }
            this.d[4] = iArr[0];
            this.d[5] = iArr[1];
            this.d[6] = iArr[2];
            this.d[7] = iArr[3];
            d(45, iArr, 0, this.c, 28);
            b(15, iArr, 0, this.e, 4);
            b(17, iArr, 0, this.c, 12);
            d(34, iArr, 0, this.c, 0);
            b(15, iArr, 4, this.c, 40);
            b(15, iArr, 4, this.e, 8);
            b(30, iArr, 4, this.c, 20);
            d(34, iArr, 4, this.c, 8);
            b(15, iArr2, 0, this.c, 36);
            b(30, iArr2, 0, this.c, 24);
            this.e[2] = iArr2[1];
            this.e[3] = iArr2[2];
            this.e[0] = iArr2[3];
            this.e[1] = iArr2[0];
            d(49, iArr2, 0, this.c, 4);
            this.c[46] = iArr3[0];
            this.c[47] = iArr3[1];
            this.c[44] = iArr3[2];
            this.c[45] = iArr3[3];
            b(30, iArr3, 0, this.c, 32);
            b(30, iArr3, 0, this.c, 16);
            c(51, iArr3, 0, this.d, 0);
        } else if (z) {
            this.d[0] = iArr[0];
            this.d[1] = iArr[1];
            this.d[2] = iArr[2];
            this.d[3] = iArr[3];
            a(15, iArr, 0, this.c, 4);
            a(30, iArr, 0, this.c, 12);
            a(15, iArr, 0, iArr4, 0);
            this.c[18] = iArr4[2];
            this.c[19] = iArr4[3];
            a(17, iArr, 0, this.e, 4);
            a(17, iArr, 0, this.c, 24);
            a(17, iArr, 0, this.c, 32);
            this.c[0] = iArr2[0];
            this.c[1] = iArr2[1];
            this.c[2] = iArr2[2];
            this.c[3] = iArr2[3];
            a(15, iArr2, 0, this.c, 8);
            a(15, iArr2, 0, this.e, 0);
            a(15, iArr2, 0, iArr4, 0);
            this.c[16] = iArr4[0];
            this.c[17] = iArr4[1];
            a(15, iArr2, 0, this.c, 20);
            c(34, iArr2, 0, this.c, 28);
            a(17, iArr2, 0, this.d, 4);
        } else {
            this.d[4] = iArr[0];
            this.d[5] = iArr[1];
            this.d[6] = iArr[2];
            this.d[7] = iArr[3];
            b(15, iArr, 0, this.c, 28);
            b(30, iArr, 0, this.c, 20);
            b(15, iArr, 0, iArr4, 0);
            this.c[16] = iArr4[0];
            this.c[17] = iArr4[1];
            b(17, iArr, 0, this.e, 0);
            b(17, iArr, 0, this.c, 8);
            b(17, iArr, 0, this.c, 0);
            this.c[34] = iArr2[0];
            this.c[35] = iArr2[1];
            this.c[32] = iArr2[2];
            this.c[33] = iArr2[3];
            b(15, iArr2, 0, this.c, 24);
            b(15, iArr2, 0, this.e, 4);
            b(15, iArr2, 0, iArr4, 0);
            this.c[18] = iArr4[2];
            this.c[19] = iArr4[3];
            b(15, iArr2, 0, this.c, 12);
            d(34, iArr2, 0, this.c, 4);
            a(17, iArr2, 0, this.d, 0);
        }
    }

    private int b(byte[] bArr, int i, byte[] bArr2, int i2) {
        for (int i3 = 0; i3 < 4; i3++) {
            this.f[i3] = a(bArr, (i3 * 4) + i);
            int[] iArr = this.f;
            iArr[i3] = iArr[i3] ^ this.d[i3];
        }
        a(this.f, this.c, 0);
        a(this.f, this.c, 4);
        a(this.f, this.c, 8);
        b(this.f, this.e, 0);
        a(this.f, this.c, 12);
        a(this.f, this.c, 16);
        a(this.f, this.c, 20);
        b(this.f, this.e, 4);
        a(this.f, this.c, 24);
        a(this.f, this.c, 28);
        a(this.f, this.c, 32);
        int[] iArr2 = this.f;
        iArr2[2] = iArr2[2] ^ this.d[4];
        iArr2 = this.f;
        iArr2[3] = iArr2[3] ^ this.d[5];
        iArr2 = this.f;
        iArr2[0] = iArr2[0] ^ this.d[6];
        iArr2 = this.f;
        iArr2[1] = iArr2[1] ^ this.d[7];
        a(this.f[2], bArr2, i2);
        a(this.f[3], bArr2, i2 + 4);
        a(this.f[0], bArr2, i2 + 8);
        a(this.f[1], bArr2, i2 + 12);
        return 16;
    }

    private int c(byte[] bArr, int i, byte[] bArr2, int i2) {
        for (int i3 = 0; i3 < 4; i3++) {
            this.f[i3] = a(bArr, (i3 * 4) + i);
            int[] iArr = this.f;
            iArr[i3] = iArr[i3] ^ this.d[i3];
        }
        a(this.f, this.c, 0);
        a(this.f, this.c, 4);
        a(this.f, this.c, 8);
        b(this.f, this.e, 0);
        a(this.f, this.c, 12);
        a(this.f, this.c, 16);
        a(this.f, this.c, 20);
        b(this.f, this.e, 4);
        a(this.f, this.c, 24);
        a(this.f, this.c, 28);
        a(this.f, this.c, 32);
        b(this.f, this.e, 8);
        a(this.f, this.c, 36);
        a(this.f, this.c, 40);
        a(this.f, this.c, 44);
        int[] iArr2 = this.f;
        iArr2[2] = iArr2[2] ^ this.d[4];
        iArr2 = this.f;
        iArr2[3] = iArr2[3] ^ this.d[5];
        iArr2 = this.f;
        iArr2[0] = iArr2[0] ^ this.d[6];
        iArr2 = this.f;
        iArr2[1] = iArr2[1] ^ this.d[7];
        a(this.f[2], bArr2, i2);
        a(this.f[3], bArr2, i2 + 4);
        a(this.f[0], bArr2, i2 + 8);
        a(this.f[1], bArr2, i2 + 12);
        return 16;
    }

    public String a() {
        return "Camellia";
    }

    public int b() {
        return 16;
    }

    public void a(boolean z, CipherParameters cipherParameters) {
        if (cipherParameters instanceof KeyParameter) {
            a(z, ((KeyParameter) cipherParameters).a());
            this.a = true;
            return;
        }
        throw new IllegalArgumentException("only simple KeyParameter expected.");
    }

    public int a(byte[] bArr, int i, byte[] bArr2, int i2) {
        if (!this.a) {
            throw new IllegalStateException("Camellia is not initialized");
        } else if (i + 16 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        } else if (i2 + 16 > bArr2.length) {
            throw new OutputLengthException("output buffer too short");
        } else if (this.b) {
            return b(bArr, i, bArr2, i2);
        } else {
            return c(bArr, i, bArr2, i2);
        }
    }

    public void c() {
    }
}
