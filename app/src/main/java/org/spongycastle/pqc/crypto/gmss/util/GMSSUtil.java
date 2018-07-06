package org.spongycastle.pqc.crypto.gmss.util;

public class GMSSUtil {
    public byte[] a(int i) {
        return new byte[]{(byte) (i & 255), (byte) ((i >> 8) & 255), (byte) ((i >> 16) & 255), (byte) ((i >> 24) & 255)};
    }

    public int a(byte[] bArr, int i) {
        int i2 = i + 1;
        int i3 = i2 + 1;
        return ((((bArr[i2] & 255) << 8) | (bArr[i] & 255)) | ((bArr[i3] & 255) << 16)) | ((bArr[i3 + 1] & 255) << 24);
    }

    public byte[] a(byte[][] bArr) {
        Object obj = new byte[(bArr.length * bArr[0].length)];
        int i = 0;
        for (int i2 = 0; i2 < bArr.length; i2++) {
            System.arraycopy(bArr[i2], 0, obj, i, bArr[i2].length);
            i += bArr[i2].length;
        }
        return obj;
    }
}
