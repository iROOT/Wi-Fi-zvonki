package org.spongycastle.crypto.engines;

import android.support.v4.app.NotificationCompat;
import java.lang.reflect.Array;
import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.OutputLengthException;
import org.spongycastle.crypto.params.KeyParameter;

public class AESLightEngine implements BlockCipher {
    private static final byte[] a = new byte[]{(byte) 99, (byte) 124, (byte) 119, (byte) 123, (byte) -14, (byte) 107, (byte) 111, (byte) -59, (byte) 48, (byte) 1, (byte) 103, (byte) 43, (byte) -2, (byte) -41, (byte) -85, (byte) 118, (byte) -54, (byte) -126, (byte) -55, (byte) 125, (byte) -6, (byte) 89, (byte) 71, (byte) -16, (byte) -83, (byte) -44, (byte) -94, (byte) -81, (byte) -100, (byte) -92, (byte) 114, (byte) -64, (byte) -73, (byte) -3, (byte) -109, (byte) 38, (byte) 54, (byte) 63, (byte) -9, (byte) -52, (byte) 52, (byte) -91, (byte) -27, (byte) -15, (byte) 113, (byte) -40, (byte) 49, (byte) 21, (byte) 4, (byte) -57, (byte) 35, (byte) -61, (byte) 24, (byte) -106, (byte) 5, (byte) -102, (byte) 7, (byte) 18, Byte.MIN_VALUE, (byte) -30, (byte) -21, (byte) 39, (byte) -78, (byte) 117, (byte) 9, (byte) -125, (byte) 44, (byte) 26, (byte) 27, (byte) 110, (byte) 90, (byte) -96, (byte) 82, (byte) 59, (byte) -42, (byte) -77, (byte) 41, (byte) -29, (byte) 47, (byte) -124, (byte) 83, (byte) -47, (byte) 0, (byte) -19, (byte) 32, (byte) -4, (byte) -79, (byte) 91, (byte) 106, (byte) -53, (byte) -66, (byte) 57, (byte) 74, (byte) 76, (byte) 88, (byte) -49, (byte) -48, (byte) -17, (byte) -86, (byte) -5, (byte) 67, (byte) 77, (byte) 51, (byte) -123, (byte) 69, (byte) -7, (byte) 2, Byte.MAX_VALUE, (byte) 80, (byte) 60, (byte) -97, (byte) -88, (byte) 81, (byte) -93, (byte) 64, (byte) -113, (byte) -110, (byte) -99, (byte) 56, (byte) -11, (byte) -68, (byte) -74, (byte) -38, (byte) 33, (byte) 16, (byte) -1, (byte) -13, (byte) -46, (byte) -51, (byte) 12, (byte) 19, (byte) -20, (byte) 95, (byte) -105, (byte) 68, (byte) 23, (byte) -60, (byte) -89, (byte) 126, (byte) 61, (byte) 100, (byte) 93, (byte) 25, (byte) 115, (byte) 96, (byte) -127, (byte) 79, (byte) -36, (byte) 34, (byte) 42, (byte) -112, (byte) -120, (byte) 70, (byte) -18, (byte) -72, (byte) 20, (byte) -34, (byte) 94, (byte) 11, (byte) -37, (byte) -32, (byte) 50, (byte) 58, (byte) 10, (byte) 73, (byte) 6, (byte) 36, (byte) 92, (byte) -62, (byte) -45, (byte) -84, (byte) 98, (byte) -111, (byte) -107, (byte) -28, (byte) 121, (byte) -25, (byte) -56, (byte) 55, (byte) 109, (byte) -115, (byte) -43, (byte) 78, (byte) -87, (byte) 108, (byte) 86, (byte) -12, (byte) -22, (byte) 101, (byte) 122, (byte) -82, (byte) 8, (byte) -70, (byte) 120, (byte) 37, (byte) 46, (byte) 28, (byte) -90, (byte) -76, (byte) -58, (byte) -24, (byte) -35, (byte) 116, (byte) 31, (byte) 75, (byte) -67, (byte) -117, (byte) -118, (byte) 112, (byte) 62, (byte) -75, (byte) 102, (byte) 72, (byte) 3, (byte) -10, (byte) 14, (byte) 97, (byte) 53, (byte) 87, (byte) -71, (byte) -122, (byte) -63, (byte) 29, (byte) -98, (byte) -31, (byte) -8, (byte) -104, (byte) 17, (byte) 105, (byte) -39, (byte) -114, (byte) -108, (byte) -101, (byte) 30, (byte) -121, (byte) -23, (byte) -50, (byte) 85, (byte) 40, (byte) -33, (byte) -116, (byte) -95, (byte) -119, (byte) 13, (byte) -65, (byte) -26, (byte) 66, (byte) 104, (byte) 65, (byte) -103, (byte) 45, (byte) 15, (byte) -80, (byte) 84, (byte) -69, (byte) 22};
    private static final byte[] b = new byte[]{(byte) 82, (byte) 9, (byte) 106, (byte) -43, (byte) 48, (byte) 54, (byte) -91, (byte) 56, (byte) -65, (byte) 64, (byte) -93, (byte) -98, (byte) -127, (byte) -13, (byte) -41, (byte) -5, (byte) 124, (byte) -29, (byte) 57, (byte) -126, (byte) -101, (byte) 47, (byte) -1, (byte) -121, (byte) 52, (byte) -114, (byte) 67, (byte) 68, (byte) -60, (byte) -34, (byte) -23, (byte) -53, (byte) 84, (byte) 123, (byte) -108, (byte) 50, (byte) -90, (byte) -62, (byte) 35, (byte) 61, (byte) -18, (byte) 76, (byte) -107, (byte) 11, (byte) 66, (byte) -6, (byte) -61, (byte) 78, (byte) 8, (byte) 46, (byte) -95, (byte) 102, (byte) 40, (byte) -39, (byte) 36, (byte) -78, (byte) 118, (byte) 91, (byte) -94, (byte) 73, (byte) 109, (byte) -117, (byte) -47, (byte) 37, (byte) 114, (byte) -8, (byte) -10, (byte) 100, (byte) -122, (byte) 104, (byte) -104, (byte) 22, (byte) -44, (byte) -92, (byte) 92, (byte) -52, (byte) 93, (byte) 101, (byte) -74, (byte) -110, (byte) 108, (byte) 112, (byte) 72, (byte) 80, (byte) -3, (byte) -19, (byte) -71, (byte) -38, (byte) 94, (byte) 21, (byte) 70, (byte) 87, (byte) -89, (byte) -115, (byte) -99, (byte) -124, (byte) -112, (byte) -40, (byte) -85, (byte) 0, (byte) -116, (byte) -68, (byte) -45, (byte) 10, (byte) -9, (byte) -28, (byte) 88, (byte) 5, (byte) -72, (byte) -77, (byte) 69, (byte) 6, (byte) -48, (byte) 44, (byte) 30, (byte) -113, (byte) -54, (byte) 63, (byte) 15, (byte) 2, (byte) -63, (byte) -81, (byte) -67, (byte) 3, (byte) 1, (byte) 19, (byte) -118, (byte) 107, (byte) 58, (byte) -111, (byte) 17, (byte) 65, (byte) 79, (byte) 103, (byte) -36, (byte) -22, (byte) -105, (byte) -14, (byte) -49, (byte) -50, (byte) -16, (byte) -76, (byte) -26, (byte) 115, (byte) -106, (byte) -84, (byte) 116, (byte) 34, (byte) -25, (byte) -83, (byte) 53, (byte) -123, (byte) -30, (byte) -7, (byte) 55, (byte) -24, (byte) 28, (byte) 117, (byte) -33, (byte) 110, (byte) 71, (byte) -15, (byte) 26, (byte) 113, (byte) 29, (byte) 41, (byte) -59, (byte) -119, (byte) 111, (byte) -73, (byte) 98, (byte) 14, (byte) -86, (byte) 24, (byte) -66, (byte) 27, (byte) -4, (byte) 86, (byte) 62, (byte) 75, (byte) -58, (byte) -46, (byte) 121, (byte) 32, (byte) -102, (byte) -37, (byte) -64, (byte) -2, (byte) 120, (byte) -51, (byte) 90, (byte) -12, (byte) 31, (byte) -35, (byte) -88, (byte) 51, (byte) -120, (byte) 7, (byte) -57, (byte) 49, (byte) -79, (byte) 18, (byte) 16, (byte) 89, (byte) 39, Byte.MIN_VALUE, (byte) -20, (byte) 95, (byte) 96, (byte) 81, Byte.MAX_VALUE, (byte) -87, (byte) 25, (byte) -75, (byte) 74, (byte) 13, (byte) 45, (byte) -27, (byte) 122, (byte) -97, (byte) -109, (byte) -55, (byte) -100, (byte) -17, (byte) -96, (byte) -32, (byte) 59, (byte) 77, (byte) -82, (byte) 42, (byte) -11, (byte) -80, (byte) -56, (byte) -21, (byte) -69, (byte) 60, (byte) -125, (byte) 83, (byte) -103, (byte) 97, (byte) 23, (byte) 43, (byte) 4, (byte) 126, (byte) -70, (byte) 119, (byte) -42, (byte) 38, (byte) -31, (byte) 105, (byte) 20, (byte) 99, (byte) 85, (byte) 33, (byte) 12, (byte) 125};
    private static final int[] c = new int[]{1, 2, 4, 8, 16, 32, 64, NotificationCompat.FLAG_HIGH_PRIORITY, 27, 54, 108, 216, 171, 77, 154, 47, 94, 188, 99, 198, 151, 53, 106, 212, 179, 125, 250, 239, 197, 145};
    private int d;
    private int[][] e = ((int[][]) null);
    private int f;
    private int g;
    private int h;
    private int i;
    private boolean j;

    private static int a(int i, int i2) {
        return (i >>> i2) | (i << (-i2));
    }

    private static int a(int i) {
        return ((2139062143 & i) << 1) ^ (((-2139062144 & i) >>> 7) * 27);
    }

    private static int b(int i) {
        int a = a(i);
        return ((a ^ a(i ^ a, 8)) ^ a(i, 16)) ^ a(i, 24);
    }

    private static int c(int i) {
        int a = a(i);
        int a2 = a(a);
        int a3 = a(a2);
        int i2 = i ^ a3;
        return ((a(a ^ i2, 8) ^ (a3 ^ (a ^ a2))) ^ a(a2 ^ i2, 16)) ^ a(i2, 24);
    }

    private static int d(int i) {
        return (((a[i & 255] & 255) | ((a[(i >> 8) & 255] & 255) << 8)) | ((a[(i >> 16) & 255] & 255) << 16)) | (a[(i >> 24) & 255] << 24);
    }

    private int[][] a(byte[] bArr, boolean z) {
        int length = bArr.length / 4;
        if ((length == 4 || length == 6 || length == 8) && length * 4 == bArr.length) {
            this.d = length + 6;
            int[][] iArr = (int[][]) Array.newInstance(Integer.TYPE, new int[]{this.d + 1, 4});
            int i = 0;
            int i2 = 0;
            while (i < bArr.length) {
                iArr[i2 >> 2][i2 & 3] = (((bArr[i] & 255) | ((bArr[i + 1] & 255) << 8)) | ((bArr[i + 2] & 255) << 16)) | (bArr[i + 3] << 24);
                i += 4;
                i2++;
            }
            int i3 = (this.d + 1) << 2;
            i2 = length;
            while (i2 < i3) {
                i = iArr[(i2 - 1) >> 2][(i2 - 1) & 3];
                if (i2 % length == 0) {
                    i = d(a(i, 8)) ^ c[(i2 / length) - 1];
                } else if (length > 6 && i2 % length == 4) {
                    i = d(i);
                }
                iArr[i2 >> 2][i2 & 3] = i ^ iArr[(i2 - length) >> 2][(i2 - length) & 3];
                i2++;
            }
            if (!z) {
                for (i = 1; i < this.d; i++) {
                    for (i2 = 0; i2 < 4; i2++) {
                        iArr[i][i2] = c(iArr[i][i2]);
                    }
                }
            }
            return iArr;
        }
        throw new IllegalArgumentException("Key length not 128/192/256 bits.");
    }

    public void a(boolean z, CipherParameters cipherParameters) {
        if (cipherParameters instanceof KeyParameter) {
            this.e = a(((KeyParameter) cipherParameters).a(), z);
            this.j = z;
            return;
        }
        throw new IllegalArgumentException("invalid parameter passed to AES init - " + cipherParameters.getClass().getName());
    }

    public String a() {
        return "AES";
    }

    public int b() {
        return 16;
    }

    public int a(byte[] bArr, int i, byte[] bArr2, int i2) {
        if (this.e == null) {
            throw new IllegalStateException("AES engine not initialised");
        } else if (i + 16 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        } else if (i2 + 16 > bArr2.length) {
            throw new OutputLengthException("output buffer too short");
        } else {
            if (this.j) {
                a(bArr, i);
                a(this.e);
                b(bArr2, i2);
            } else {
                a(bArr, i);
                b(this.e);
                b(bArr2, i2);
            }
            return 16;
        }
    }

    public void c() {
    }

    private void a(byte[] bArr, int i) {
        int i2 = i + 1;
        this.f = bArr[i] & 255;
        int i3 = i2 + 1;
        this.f = ((bArr[i2] & 255) << 8) | this.f;
        int i4 = i3 + 1;
        this.f |= (bArr[i3] & 255) << 16;
        i3 = i4 + 1;
        this.f |= bArr[i4] << 24;
        i2 = i3 + 1;
        this.g = bArr[i3] & 255;
        i3 = i2 + 1;
        this.g = ((bArr[i2] & 255) << 8) | this.g;
        i4 = i3 + 1;
        this.g |= (bArr[i3] & 255) << 16;
        i3 = i4 + 1;
        this.g |= bArr[i4] << 24;
        i2 = i3 + 1;
        this.h = bArr[i3] & 255;
        i3 = i2 + 1;
        this.h = ((bArr[i2] & 255) << 8) | this.h;
        i4 = i3 + 1;
        this.h |= (bArr[i3] & 255) << 16;
        i3 = i4 + 1;
        this.h |= bArr[i4] << 24;
        i2 = i3 + 1;
        this.i = bArr[i3] & 255;
        i3 = i2 + 1;
        this.i = ((bArr[i2] & 255) << 8) | this.i;
        i4 = i3 + 1;
        this.i |= (bArr[i3] & 255) << 16;
        i3 = i4 + 1;
        this.i |= bArr[i4] << 24;
    }

    private void b(byte[] bArr, int i) {
        int i2 = i + 1;
        bArr[i] = (byte) this.f;
        int i3 = i2 + 1;
        bArr[i2] = (byte) (this.f >> 8);
        i2 = i3 + 1;
        bArr[i3] = (byte) (this.f >> 16);
        i3 = i2 + 1;
        bArr[i2] = (byte) (this.f >> 24);
        i2 = i3 + 1;
        bArr[i3] = (byte) this.g;
        i3 = i2 + 1;
        bArr[i2] = (byte) (this.g >> 8);
        i2 = i3 + 1;
        bArr[i3] = (byte) (this.g >> 16);
        i3 = i2 + 1;
        bArr[i2] = (byte) (this.g >> 24);
        i2 = i3 + 1;
        bArr[i3] = (byte) this.h;
        i3 = i2 + 1;
        bArr[i2] = (byte) (this.h >> 8);
        i2 = i3 + 1;
        bArr[i3] = (byte) (this.h >> 16);
        i3 = i2 + 1;
        bArr[i2] = (byte) (this.h >> 24);
        i2 = i3 + 1;
        bArr[i3] = (byte) this.i;
        i3 = i2 + 1;
        bArr[i2] = (byte) (this.i >> 8);
        i2 = i3 + 1;
        bArr[i3] = (byte) (this.i >> 16);
        i3 = i2 + 1;
        bArr[i2] = (byte) (this.i >> 24);
    }

    private void a(int[][] iArr) {
        int b;
        int b2;
        int b3;
        int i;
        this.f ^= iArr[0][0];
        this.g ^= iArr[0][1];
        this.h ^= iArr[0][2];
        this.i ^= iArr[0][3];
        int i2 = 1;
        while (i2 < this.d - 1) {
            b = b((((a[this.f & 255] & 255) ^ ((a[(this.g >> 8) & 255] & 255) << 8)) ^ ((a[(this.h >> 16) & 255] & 255) << 16)) ^ (a[(this.i >> 24) & 255] << 24)) ^ iArr[i2][0];
            b2 = b((((a[this.g & 255] & 255) ^ ((a[(this.h >> 8) & 255] & 255) << 8)) ^ ((a[(this.i >> 16) & 255] & 255) << 16)) ^ (a[(this.f >> 24) & 255] << 24)) ^ iArr[i2][1];
            b3 = b((((a[this.h & 255] & 255) ^ ((a[(this.i >> 8) & 255] & 255) << 8)) ^ ((a[(this.f >> 16) & 255] & 255) << 16)) ^ (a[(this.g >> 24) & 255] << 24)) ^ iArr[i2][2];
            i = i2 + 1;
            i2 = iArr[i2][3] ^ b((((a[this.i & 255] & 255) ^ ((a[(this.f >> 8) & 255] & 255) << 8)) ^ ((a[(this.g >> 16) & 255] & 255) << 16)) ^ (a[(this.h >> 24) & 255] << 24));
            this.f = b((((a[b & 255] & 255) ^ ((a[(b2 >> 8) & 255] & 255) << 8)) ^ ((a[(b3 >> 16) & 255] & 255) << 16)) ^ (a[(i2 >> 24) & 255] << 24)) ^ iArr[i][0];
            this.g = b((((a[b2 & 255] & 255) ^ ((a[(b3 >> 8) & 255] & 255) << 8)) ^ ((a[(i2 >> 16) & 255] & 255) << 16)) ^ (a[(b >> 24) & 255] << 24)) ^ iArr[i][1];
            this.h = b((((a[b3 & 255] & 255) ^ ((a[(i2 >> 8) & 255] & 255) << 8)) ^ ((a[(b >> 16) & 255] & 255) << 16)) ^ (a[(b2 >> 24) & 255] << 24)) ^ iArr[i][2];
            b = b((((a[i2 & 255] & 255) ^ ((a[(b >> 8) & 255] & 255) << 8)) ^ ((a[(b2 >> 16) & 255] & 255) << 16)) ^ (a[(b3 >> 24) & 255] << 24));
            i2 = i + 1;
            this.i = b ^ iArr[i][3];
        }
        b = b((((a[this.f & 255] & 255) ^ ((a[(this.g >> 8) & 255] & 255) << 8)) ^ ((a[(this.h >> 16) & 255] & 255) << 16)) ^ (a[(this.i >> 24) & 255] << 24)) ^ iArr[i2][0];
        b2 = b((((a[this.g & 255] & 255) ^ ((a[(this.h >> 8) & 255] & 255) << 8)) ^ ((a[(this.i >> 16) & 255] & 255) << 16)) ^ (a[(this.f >> 24) & 255] << 24)) ^ iArr[i2][1];
        b3 = b((((a[this.h & 255] & 255) ^ ((a[(this.i >> 8) & 255] & 255) << 8)) ^ ((a[(this.f >> 16) & 255] & 255) << 16)) ^ (a[(this.g >> 24) & 255] << 24)) ^ iArr[i2][2];
        i = i2 + 1;
        i2 = iArr[i2][3] ^ b((((a[this.i & 255] & 255) ^ ((a[(this.f >> 8) & 255] & 255) << 8)) ^ ((a[(this.g >> 16) & 255] & 255) << 16)) ^ (a[(this.h >> 24) & 255] << 24));
        this.f = ((((a[b & 255] & 255) ^ ((a[(b2 >> 8) & 255] & 255) << 8)) ^ ((a[(b3 >> 16) & 255] & 255) << 16)) ^ (a[(i2 >> 24) & 255] << 24)) ^ iArr[i][0];
        this.g = iArr[i][1] ^ ((((a[b2 & 255] & 255) ^ ((a[(b3 >> 8) & 255] & 255) << 8)) ^ ((a[(i2 >> 16) & 255] & 255) << 16)) ^ (a[(b >> 24) & 255] << 24));
        this.h = ((((a[b3 & 255] & 255) ^ ((a[(i2 >> 8) & 255] & 255) << 8)) ^ ((a[(b >> 16) & 255] & 255) << 16)) ^ (a[(b2 >> 24) & 255] << 24)) ^ iArr[i][2];
        this.i = ((((a[i2 & 255] & 255) ^ ((a[(b >> 8) & 255] & 255) << 8)) ^ ((a[(b2 >> 16) & 255] & 255) << 16)) ^ (a[(b3 >> 24) & 255] << 24)) ^ iArr[i][3];
    }

    private void b(int[][] iArr) {
        int c;
        int c2;
        int c3;
        this.f ^= iArr[this.d][0];
        this.g ^= iArr[this.d][1];
        this.h ^= iArr[this.d][2];
        this.i ^= iArr[this.d][3];
        int i = this.d - 1;
        while (i > 1) {
            c = c((((b[this.f & 255] & 255) ^ ((b[(this.i >> 8) & 255] & 255) << 8)) ^ ((b[(this.h >> 16) & 255] & 255) << 16)) ^ (b[(this.g >> 24) & 255] << 24)) ^ iArr[i][0];
            c2 = c((((b[this.g & 255] & 255) ^ ((b[(this.f >> 8) & 255] & 255) << 8)) ^ ((b[(this.i >> 16) & 255] & 255) << 16)) ^ (b[(this.h >> 24) & 255] << 24)) ^ iArr[i][1];
            c3 = c((((b[this.h & 255] & 255) ^ ((b[(this.g >> 8) & 255] & 255) << 8)) ^ ((b[(this.f >> 16) & 255] & 255) << 16)) ^ (b[(this.i >> 24) & 255] << 24)) ^ iArr[i][2];
            int i2 = i - 1;
            i = iArr[i][3] ^ c((((b[this.i & 255] & 255) ^ ((b[(this.h >> 8) & 255] & 255) << 8)) ^ ((b[(this.g >> 16) & 255] & 255) << 16)) ^ (b[(this.f >> 24) & 255] << 24));
            this.f = c((((b[c & 255] & 255) ^ ((b[(i >> 8) & 255] & 255) << 8)) ^ ((b[(c3 >> 16) & 255] & 255) << 16)) ^ (b[(c2 >> 24) & 255] << 24)) ^ iArr[i2][0];
            this.g = c((((b[c2 & 255] & 255) ^ ((b[(c >> 8) & 255] & 255) << 8)) ^ ((b[(i >> 16) & 255] & 255) << 16)) ^ (b[(c3 >> 24) & 255] << 24)) ^ iArr[i2][1];
            this.h = c((((b[c3 & 255] & 255) ^ ((b[(c2 >> 8) & 255] & 255) << 8)) ^ ((b[(c >> 16) & 255] & 255) << 16)) ^ (b[(i >> 24) & 255] << 24)) ^ iArr[i2][2];
            c = c((((b[i & 255] & 255) ^ ((b[(c3 >> 8) & 255] & 255) << 8)) ^ ((b[(c2 >> 16) & 255] & 255) << 16)) ^ (b[(c >> 24) & 255] << 24));
            i = i2 - 1;
            this.i = c ^ iArr[i2][3];
        }
        c = c((((b[this.f & 255] & 255) ^ ((b[(this.i >> 8) & 255] & 255) << 8)) ^ ((b[(this.h >> 16) & 255] & 255) << 16)) ^ (b[(this.g >> 24) & 255] << 24)) ^ iArr[i][0];
        c2 = c((((b[this.g & 255] & 255) ^ ((b[(this.f >> 8) & 255] & 255) << 8)) ^ ((b[(this.i >> 16) & 255] & 255) << 16)) ^ (b[(this.h >> 24) & 255] << 24)) ^ iArr[i][1];
        c3 = c((((b[this.h & 255] & 255) ^ ((b[(this.g >> 8) & 255] & 255) << 8)) ^ ((b[(this.f >> 16) & 255] & 255) << 16)) ^ (b[(this.i >> 24) & 255] << 24)) ^ iArr[i][2];
        i = iArr[i][3] ^ c((((b[this.i & 255] & 255) ^ ((b[(this.h >> 8) & 255] & 255) << 8)) ^ ((b[(this.g >> 16) & 255] & 255) << 16)) ^ (b[(this.f >> 24) & 255] << 24));
        this.f = ((((b[c & 255] & 255) ^ ((b[(i >> 8) & 255] & 255) << 8)) ^ ((b[(c3 >> 16) & 255] & 255) << 16)) ^ (b[(c2 >> 24) & 255] << 24)) ^ iArr[0][0];
        this.g = ((((b[c2 & 255] & 255) ^ ((b[(c >> 8) & 255] & 255) << 8)) ^ ((b[(i >> 16) & 255] & 255) << 16)) ^ (b[(c3 >> 24) & 255] << 24)) ^ iArr[0][1];
        this.h = ((((b[c3 & 255] & 255) ^ ((b[(c2 >> 8) & 255] & 255) << 8)) ^ ((b[(c >> 16) & 255] & 255) << 16)) ^ (b[(i >> 24) & 255] << 24)) ^ iArr[0][2];
        this.i = ((((b[i & 255] & 255) ^ ((b[(c3 >> 8) & 255] & 255) << 8)) ^ ((b[(c2 >> 16) & 255] & 255) << 16)) ^ (b[(c >> 24) & 255] << 24)) ^ iArr[0][3];
    }
}
