package org.spongycastle.crypto.engines;

import java.util.Hashtable;
import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.OutputLengthException;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.params.ParametersWithSBox;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.Strings;

public class GOST28147Engine implements BlockCipher {
    private static byte[] d = new byte[]{(byte) 4, (byte) 10, (byte) 9, (byte) 2, (byte) 13, (byte) 8, (byte) 0, (byte) 14, (byte) 6, (byte) 11, (byte) 1, (byte) 12, (byte) 7, (byte) 15, (byte) 5, (byte) 3, (byte) 14, (byte) 11, (byte) 4, (byte) 12, (byte) 6, (byte) 13, (byte) 15, (byte) 10, (byte) 2, (byte) 3, (byte) 8, (byte) 1, (byte) 0, (byte) 7, (byte) 5, (byte) 9, (byte) 5, (byte) 8, (byte) 1, (byte) 13, (byte) 10, (byte) 3, (byte) 4, (byte) 2, (byte) 14, (byte) 15, (byte) 12, (byte) 7, (byte) 6, (byte) 0, (byte) 9, (byte) 11, (byte) 7, (byte) 13, (byte) 10, (byte) 1, (byte) 0, (byte) 8, (byte) 9, (byte) 15, (byte) 14, (byte) 4, (byte) 6, (byte) 12, (byte) 11, (byte) 2, (byte) 5, (byte) 3, (byte) 6, (byte) 12, (byte) 7, (byte) 1, (byte) 5, (byte) 15, (byte) 13, (byte) 8, (byte) 4, (byte) 10, (byte) 9, (byte) 14, (byte) 0, (byte) 3, (byte) 11, (byte) 2, (byte) 4, (byte) 11, (byte) 10, (byte) 0, (byte) 7, (byte) 2, (byte) 1, (byte) 13, (byte) 3, (byte) 6, (byte) 8, (byte) 5, (byte) 9, (byte) 12, (byte) 15, (byte) 14, (byte) 13, (byte) 11, (byte) 4, (byte) 1, (byte) 3, (byte) 15, (byte) 5, (byte) 9, (byte) 0, (byte) 10, (byte) 14, (byte) 7, (byte) 6, (byte) 8, (byte) 2, (byte) 12, (byte) 1, (byte) 15, (byte) 13, (byte) 0, (byte) 5, (byte) 7, (byte) 10, (byte) 4, (byte) 9, (byte) 2, (byte) 3, (byte) 14, (byte) 6, (byte) 11, (byte) 8, (byte) 12};
    private static byte[] e = new byte[]{(byte) 4, (byte) 2, (byte) 15, (byte) 5, (byte) 9, (byte) 1, (byte) 0, (byte) 8, (byte) 14, (byte) 3, (byte) 11, (byte) 12, (byte) 13, (byte) 7, (byte) 10, (byte) 6, (byte) 12, (byte) 9, (byte) 15, (byte) 14, (byte) 8, (byte) 1, (byte) 3, (byte) 10, (byte) 2, (byte) 7, (byte) 4, (byte) 13, (byte) 6, (byte) 0, (byte) 11, (byte) 5, (byte) 13, (byte) 8, (byte) 14, (byte) 12, (byte) 7, (byte) 3, (byte) 9, (byte) 10, (byte) 1, (byte) 5, (byte) 2, (byte) 4, (byte) 6, (byte) 15, (byte) 0, (byte) 11, (byte) 14, (byte) 9, (byte) 11, (byte) 2, (byte) 5, (byte) 15, (byte) 7, (byte) 1, (byte) 0, (byte) 13, (byte) 12, (byte) 6, (byte) 10, (byte) 4, (byte) 3, (byte) 8, (byte) 3, (byte) 14, (byte) 5, (byte) 9, (byte) 6, (byte) 8, (byte) 0, (byte) 13, (byte) 10, (byte) 11, (byte) 7, (byte) 12, (byte) 2, (byte) 1, (byte) 15, (byte) 4, (byte) 8, (byte) 15, (byte) 6, (byte) 11, (byte) 1, (byte) 9, (byte) 12, (byte) 5, (byte) 13, (byte) 3, (byte) 7, (byte) 10, (byte) 0, (byte) 14, (byte) 2, (byte) 4, (byte) 9, (byte) 11, (byte) 12, (byte) 0, (byte) 3, (byte) 6, (byte) 7, (byte) 5, (byte) 4, (byte) 8, (byte) 14, (byte) 15, (byte) 1, (byte) 10, (byte) 2, (byte) 13, (byte) 12, (byte) 6, (byte) 5, (byte) 2, (byte) 11, (byte) 0, (byte) 9, (byte) 13, (byte) 3, (byte) 14, (byte) 7, (byte) 10, (byte) 15, (byte) 4, (byte) 1, (byte) 8};
    private static byte[] f = new byte[]{(byte) 9, (byte) 6, (byte) 3, (byte) 2, (byte) 8, (byte) 11, (byte) 1, (byte) 7, (byte) 10, (byte) 4, (byte) 14, (byte) 15, (byte) 12, (byte) 0, (byte) 13, (byte) 5, (byte) 3, (byte) 7, (byte) 14, (byte) 9, (byte) 8, (byte) 10, (byte) 15, (byte) 0, (byte) 5, (byte) 2, (byte) 6, (byte) 12, (byte) 11, (byte) 4, (byte) 13, (byte) 1, (byte) 14, (byte) 4, (byte) 6, (byte) 2, (byte) 11, (byte) 3, (byte) 13, (byte) 8, (byte) 12, (byte) 15, (byte) 5, (byte) 10, (byte) 0, (byte) 7, (byte) 1, (byte) 9, (byte) 14, (byte) 7, (byte) 10, (byte) 12, (byte) 13, (byte) 1, (byte) 3, (byte) 9, (byte) 0, (byte) 2, (byte) 11, (byte) 4, (byte) 15, (byte) 8, (byte) 5, (byte) 6, (byte) 11, (byte) 5, (byte) 1, (byte) 9, (byte) 8, (byte) 13, (byte) 15, (byte) 0, (byte) 14, (byte) 4, (byte) 2, (byte) 3, (byte) 12, (byte) 7, (byte) 10, (byte) 6, (byte) 3, (byte) 10, (byte) 13, (byte) 12, (byte) 1, (byte) 2, (byte) 0, (byte) 11, (byte) 7, (byte) 5, (byte) 9, (byte) 4, (byte) 8, (byte) 15, (byte) 14, (byte) 6, (byte) 1, (byte) 13, (byte) 2, (byte) 9, (byte) 7, (byte) 10, (byte) 6, (byte) 0, (byte) 8, (byte) 12, (byte) 4, (byte) 5, (byte) 15, (byte) 3, (byte) 11, (byte) 14, (byte) 11, (byte) 10, (byte) 15, (byte) 5, (byte) 0, (byte) 12, (byte) 14, (byte) 8, (byte) 6, (byte) 2, (byte) 3, (byte) 9, (byte) 1, (byte) 7, (byte) 13, (byte) 4};
    private static byte[] g = new byte[]{(byte) 8, (byte) 4, (byte) 11, (byte) 1, (byte) 3, (byte) 5, (byte) 0, (byte) 9, (byte) 2, (byte) 14, (byte) 10, (byte) 12, (byte) 13, (byte) 6, (byte) 7, (byte) 15, (byte) 0, (byte) 1, (byte) 2, (byte) 10, (byte) 4, (byte) 13, (byte) 5, (byte) 12, (byte) 9, (byte) 7, (byte) 3, (byte) 15, (byte) 11, (byte) 8, (byte) 6, (byte) 14, (byte) 14, (byte) 12, (byte) 0, (byte) 10, (byte) 9, (byte) 2, (byte) 13, (byte) 11, (byte) 7, (byte) 5, (byte) 8, (byte) 15, (byte) 3, (byte) 6, (byte) 1, (byte) 4, (byte) 7, (byte) 5, (byte) 0, (byte) 13, (byte) 11, (byte) 6, (byte) 1, (byte) 2, (byte) 3, (byte) 10, (byte) 12, (byte) 15, (byte) 4, (byte) 14, (byte) 9, (byte) 8, (byte) 2, (byte) 7, (byte) 12, (byte) 15, (byte) 9, (byte) 5, (byte) 10, (byte) 11, (byte) 1, (byte) 4, (byte) 0, (byte) 13, (byte) 6, (byte) 8, (byte) 14, (byte) 3, (byte) 8, (byte) 3, (byte) 2, (byte) 6, (byte) 4, (byte) 13, (byte) 14, (byte) 11, (byte) 12, (byte) 1, (byte) 7, (byte) 15, (byte) 10, (byte) 0, (byte) 9, (byte) 5, (byte) 5, (byte) 2, (byte) 10, (byte) 11, (byte) 9, (byte) 1, (byte) 12, (byte) 3, (byte) 7, (byte) 4, (byte) 13, (byte) 0, (byte) 6, (byte) 15, (byte) 8, (byte) 14, (byte) 0, (byte) 4, (byte) 11, (byte) 14, (byte) 8, (byte) 3, (byte) 7, (byte) 1, (byte) 10, (byte) 2, (byte) 9, (byte) 6, (byte) 15, (byte) 13, (byte) 5, (byte) 12};
    private static byte[] h = new byte[]{(byte) 1, (byte) 11, (byte) 12, (byte) 2, (byte) 9, (byte) 13, (byte) 0, (byte) 15, (byte) 4, (byte) 5, (byte) 8, (byte) 14, (byte) 10, (byte) 7, (byte) 6, (byte) 3, (byte) 0, (byte) 1, (byte) 7, (byte) 13, (byte) 11, (byte) 4, (byte) 5, (byte) 2, (byte) 8, (byte) 14, (byte) 15, (byte) 12, (byte) 9, (byte) 10, (byte) 6, (byte) 3, (byte) 8, (byte) 2, (byte) 5, (byte) 0, (byte) 4, (byte) 9, (byte) 15, (byte) 10, (byte) 3, (byte) 7, (byte) 12, (byte) 13, (byte) 6, (byte) 14, (byte) 1, (byte) 11, (byte) 3, (byte) 6, (byte) 0, (byte) 1, (byte) 5, (byte) 13, (byte) 10, (byte) 8, (byte) 11, (byte) 2, (byte) 9, (byte) 7, (byte) 14, (byte) 15, (byte) 12, (byte) 4, (byte) 8, (byte) 13, (byte) 11, (byte) 0, (byte) 4, (byte) 5, (byte) 1, (byte) 2, (byte) 9, (byte) 3, (byte) 12, (byte) 14, (byte) 6, (byte) 15, (byte) 10, (byte) 7, (byte) 12, (byte) 9, (byte) 11, (byte) 1, (byte) 8, (byte) 14, (byte) 2, (byte) 4, (byte) 7, (byte) 3, (byte) 6, (byte) 5, (byte) 10, (byte) 0, (byte) 15, (byte) 13, (byte) 10, (byte) 9, (byte) 6, (byte) 8, (byte) 13, (byte) 14, (byte) 2, (byte) 0, (byte) 15, (byte) 3, (byte) 5, (byte) 11, (byte) 4, (byte) 1, (byte) 12, (byte) 7, (byte) 7, (byte) 4, (byte) 0, (byte) 5, (byte) 10, (byte) 2, (byte) 15, (byte) 14, (byte) 12, (byte) 6, (byte) 1, (byte) 11, (byte) 13, (byte) 9, (byte) 3, (byte) 8};
    private static byte[] i = new byte[]{(byte) 15, (byte) 12, (byte) 2, (byte) 10, (byte) 6, (byte) 4, (byte) 5, (byte) 0, (byte) 7, (byte) 9, (byte) 14, (byte) 13, (byte) 1, (byte) 11, (byte) 8, (byte) 3, (byte) 11, (byte) 6, (byte) 3, (byte) 4, (byte) 12, (byte) 15, (byte) 14, (byte) 2, (byte) 7, (byte) 13, (byte) 8, (byte) 0, (byte) 5, (byte) 10, (byte) 9, (byte) 1, (byte) 1, (byte) 12, (byte) 11, (byte) 0, (byte) 15, (byte) 14, (byte) 6, (byte) 5, (byte) 10, (byte) 13, (byte) 4, (byte) 8, (byte) 9, (byte) 3, (byte) 7, (byte) 2, (byte) 1, (byte) 5, (byte) 14, (byte) 12, (byte) 10, (byte) 7, (byte) 0, (byte) 13, (byte) 6, (byte) 2, (byte) 11, (byte) 4, (byte) 9, (byte) 3, (byte) 15, (byte) 8, (byte) 0, (byte) 12, (byte) 8, (byte) 9, (byte) 13, (byte) 2, (byte) 10, (byte) 11, (byte) 7, (byte) 3, (byte) 6, (byte) 5, (byte) 4, (byte) 14, (byte) 15, (byte) 1, (byte) 8, (byte) 0, (byte) 15, (byte) 3, (byte) 2, (byte) 5, (byte) 14, (byte) 11, (byte) 1, (byte) 10, (byte) 4, (byte) 7, (byte) 12, (byte) 9, (byte) 13, (byte) 6, (byte) 3, (byte) 0, (byte) 6, (byte) 15, (byte) 1, (byte) 14, (byte) 9, (byte) 2, (byte) 13, (byte) 8, (byte) 12, (byte) 4, (byte) 11, (byte) 10, (byte) 5, (byte) 7, (byte) 1, (byte) 10, (byte) 6, (byte) 8, (byte) 15, (byte) 11, (byte) 0, (byte) 4, (byte) 12, (byte) 3, (byte) 5, (byte) 9, (byte) 7, (byte) 13, (byte) 2, (byte) 14};
    private static byte[] j = new byte[]{(byte) 4, (byte) 10, (byte) 9, (byte) 2, (byte) 13, (byte) 8, (byte) 0, (byte) 14, (byte) 6, (byte) 11, (byte) 1, (byte) 12, (byte) 7, (byte) 15, (byte) 5, (byte) 3, (byte) 14, (byte) 11, (byte) 4, (byte) 12, (byte) 6, (byte) 13, (byte) 15, (byte) 10, (byte) 2, (byte) 3, (byte) 8, (byte) 1, (byte) 0, (byte) 7, (byte) 5, (byte) 9, (byte) 5, (byte) 8, (byte) 1, (byte) 13, (byte) 10, (byte) 3, (byte) 4, (byte) 2, (byte) 14, (byte) 15, (byte) 12, (byte) 7, (byte) 6, (byte) 0, (byte) 9, (byte) 11, (byte) 7, (byte) 13, (byte) 10, (byte) 1, (byte) 0, (byte) 8, (byte) 9, (byte) 15, (byte) 14, (byte) 4, (byte) 6, (byte) 12, (byte) 11, (byte) 2, (byte) 5, (byte) 3, (byte) 6, (byte) 12, (byte) 7, (byte) 1, (byte) 5, (byte) 15, (byte) 13, (byte) 8, (byte) 4, (byte) 10, (byte) 9, (byte) 14, (byte) 0, (byte) 3, (byte) 11, (byte) 2, (byte) 4, (byte) 11, (byte) 10, (byte) 0, (byte) 7, (byte) 2, (byte) 1, (byte) 13, (byte) 3, (byte) 6, (byte) 8, (byte) 5, (byte) 9, (byte) 12, (byte) 15, (byte) 14, (byte) 13, (byte) 11, (byte) 4, (byte) 1, (byte) 3, (byte) 15, (byte) 5, (byte) 9, (byte) 0, (byte) 10, (byte) 14, (byte) 7, (byte) 6, (byte) 8, (byte) 2, (byte) 12, (byte) 1, (byte) 15, (byte) 13, (byte) 0, (byte) 5, (byte) 7, (byte) 10, (byte) 4, (byte) 9, (byte) 2, (byte) 3, (byte) 14, (byte) 6, (byte) 11, (byte) 8, (byte) 12};
    private static byte[] k = new byte[]{(byte) 10, (byte) 4, (byte) 5, (byte) 6, (byte) 8, (byte) 1, (byte) 3, (byte) 7, (byte) 13, (byte) 12, (byte) 14, (byte) 0, (byte) 9, (byte) 2, (byte) 11, (byte) 15, (byte) 5, (byte) 15, (byte) 4, (byte) 0, (byte) 2, (byte) 13, (byte) 11, (byte) 9, (byte) 1, (byte) 7, (byte) 6, (byte) 3, (byte) 12, (byte) 14, (byte) 10, (byte) 8, (byte) 7, (byte) 15, (byte) 12, (byte) 14, (byte) 9, (byte) 4, (byte) 1, (byte) 0, (byte) 3, (byte) 11, (byte) 5, (byte) 2, (byte) 6, (byte) 10, (byte) 8, (byte) 13, (byte) 4, (byte) 10, (byte) 7, (byte) 12, (byte) 0, (byte) 15, (byte) 2, (byte) 8, (byte) 14, (byte) 1, (byte) 6, (byte) 5, (byte) 13, (byte) 11, (byte) 9, (byte) 3, (byte) 7, (byte) 6, (byte) 4, (byte) 11, (byte) 9, (byte) 12, (byte) 2, (byte) 10, (byte) 1, (byte) 8, (byte) 0, (byte) 14, (byte) 15, (byte) 13, (byte) 3, (byte) 5, (byte) 7, (byte) 6, (byte) 2, (byte) 4, (byte) 13, (byte) 9, (byte) 15, (byte) 0, (byte) 10, (byte) 1, (byte) 5, (byte) 11, (byte) 8, (byte) 14, (byte) 12, (byte) 3, (byte) 13, (byte) 14, (byte) 4, (byte) 1, (byte) 7, (byte) 0, (byte) 5, (byte) 10, (byte) 3, (byte) 12, (byte) 8, (byte) 15, (byte) 6, (byte) 2, (byte) 9, (byte) 11, (byte) 1, (byte) 3, (byte) 10, (byte) 9, (byte) 5, (byte) 11, (byte) 4, (byte) 15, (byte) 8, (byte) 6, (byte) 7, (byte) 14, (byte) 13, (byte) 0, (byte) 2, (byte) 12};
    private static Hashtable l = new Hashtable();
    private int[] a = null;
    private boolean b;
    private byte[] c = d;

    static {
        a("Default", d);
        a("E-TEST", e);
        a("E-A", f);
        a("E-B", g);
        a("E-C", h);
        a("E-D", i);
        a("D-TEST", j);
        a("D-A", k);
    }

    private static void a(String str, byte[] bArr) {
        l.put(Strings.b(str), bArr);
    }

    public void a(boolean z, CipherParameters cipherParameters) {
        if (cipherParameters instanceof ParametersWithSBox) {
            ParametersWithSBox parametersWithSBox = (ParametersWithSBox) cipherParameters;
            byte[] a = parametersWithSBox.a();
            if (a.length != d.length) {
                throw new IllegalArgumentException("invalid S-box passed to GOST28147 init");
            }
            this.c = Arrays.b(a);
            if (parametersWithSBox.b() != null) {
                this.a = a(z, ((KeyParameter) parametersWithSBox.b()).a());
            }
        } else if (cipherParameters instanceof KeyParameter) {
            this.a = a(z, ((KeyParameter) cipherParameters).a());
        } else if (cipherParameters != null) {
            throw new IllegalArgumentException("invalid parameter passed to GOST28147 init - " + cipherParameters.getClass().getName());
        }
    }

    public String a() {
        return "GOST28147";
    }

    public int b() {
        return 8;
    }

    public int a(byte[] bArr, int i, byte[] bArr2, int i2) {
        if (this.a == null) {
            throw new IllegalStateException("GOST28147 engine not initialised");
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

    private int[] a(boolean z, byte[] bArr) {
        this.b = z;
        if (bArr.length != 32) {
            throw new IllegalArgumentException("Key length invalid. Key needs to be 32 byte - 256 bit!!!");
        }
        int[] iArr = new int[8];
        for (int i = 0; i != 8; i++) {
            iArr[i] = a(bArr, i * 4);
        }
        return iArr;
    }

    private int a(int i, int i2) {
        int i3 = i2 + i;
        i3 = (this.c[((i3 >> 28) & 15) + 112] << 28) + (((((((this.c[((i3 >> 0) & 15) + 0] << 0) + (this.c[((i3 >> 4) & 15) + 16] << 4)) + (this.c[((i3 >> 8) & 15) + 32] << 8)) + (this.c[((i3 >> 12) & 15) + 48] << 12)) + (this.c[((i3 >> 16) & 15) + 64] << 16)) + (this.c[((i3 >> 20) & 15) + 80] << 20)) + (this.c[((i3 >> 24) & 15) + 96] << 24));
        return (i3 >>> 21) | (i3 << 11);
    }

    private void a(int[] iArr, byte[] bArr, int i, byte[] bArr2, int i2) {
        int i3 = 7;
        int a = a(bArr, i);
        int a2 = a(bArr, i + 4);
        int i4;
        int i5;
        int i6;
        if (this.b) {
            i4 = 0;
            while (i4 < 3) {
                i5 = a;
                a = a2;
                a2 = 0;
                while (a2 < 8) {
                    a2++;
                    i6 = i5;
                    i5 = a ^ a(i5, iArr[a2]);
                    a = i6;
                }
                i4++;
                a2 = a;
                a = i5;
            }
            while (i3 > 0) {
                i3--;
                i6 = a;
                a = a2 ^ a(a, iArr[i3]);
                a2 = i6;
            }
        } else {
            i5 = 0;
            while (i5 < 8) {
                i4 = a(a, iArr[i5]) ^ a2;
                i5++;
                a2 = a;
                a = i4;
            }
            i4 = 0;
            while (i4 < 3) {
                i5 = a;
                a = a2;
                a2 = 7;
                while (a2 >= 0 && (i4 != 2 || a2 != 0)) {
                    a2--;
                    i6 = i5;
                    i5 = a ^ a(i5, iArr[a2]);
                    a = i6;
                }
                i4++;
                a2 = a;
                a = i5;
            }
        }
        a2 ^= a(a, iArr[0]);
        a(a, bArr2, i2);
        a(a2, bArr2, i2 + 4);
    }

    private int a(byte[] bArr, int i) {
        return ((((bArr[i + 3] << 24) & -16777216) + ((bArr[i + 2] << 16) & 16711680)) + ((bArr[i + 1] << 8) & 65280)) + (bArr[i] & 255);
    }

    private void a(int i, byte[] bArr, int i2) {
        bArr[i2 + 3] = (byte) (i >>> 24);
        bArr[i2 + 2] = (byte) (i >>> 16);
        bArr[i2 + 1] = (byte) (i >>> 8);
        bArr[i2] = (byte) i;
    }

    public static byte[] a(String str) {
        byte[] bArr = (byte[]) l.get(Strings.b(str));
        if (bArr != null) {
            return Arrays.b(bArr);
        }
        throw new IllegalArgumentException("Unknown S-Box - possible types: \"Default\", \"E-Test\", \"E-A\", \"E-B\", \"E-C\", \"E-D\", \"D-Test\", \"D-A\".");
    }
}
