package org.spongycastle.crypto;

import org.spongycastle.util.Strings;

public abstract class PBEParametersGenerator {
    protected byte[] a;
    protected byte[] b;
    protected int c;

    public abstract CipherParameters a(int i);

    public abstract CipherParameters a(int i, int i2);

    public abstract CipherParameters b(int i);

    protected PBEParametersGenerator() {
    }

    public void a(byte[] bArr, byte[] bArr2, int i) {
        this.a = bArr;
        this.b = bArr2;
        this.c = i;
    }

    public static byte[] a(char[] cArr) {
        int i = 0;
        if (cArr == null) {
            return new byte[0];
        }
        byte[] bArr = new byte[cArr.length];
        while (i != bArr.length) {
            bArr[i] = (byte) cArr[i];
            i++;
        }
        return bArr;
    }

    public static byte[] b(char[] cArr) {
        if (cArr != null) {
            return Strings.a(cArr);
        }
        return new byte[0];
    }

    public static byte[] c(char[] cArr) {
        int i = 0;
        if (cArr == null || cArr.length <= 0) {
            return new byte[0];
        }
        byte[] bArr = new byte[((cArr.length + 1) * 2)];
        while (i != cArr.length) {
            bArr[i * 2] = (byte) (cArr[i] >>> 8);
            bArr[(i * 2) + 1] = (byte) cArr[i];
            i++;
        }
        return bArr;
    }
}
