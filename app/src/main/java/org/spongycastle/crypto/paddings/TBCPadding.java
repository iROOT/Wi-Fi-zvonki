package org.spongycastle.crypto.paddings;

import java.security.SecureRandom;

public class TBCPadding implements BlockCipherPadding {
    public void a(SecureRandom secureRandom) {
    }

    public int a(byte[] bArr, int i) {
        byte b;
        int i2 = 255;
        int length = bArr.length - i;
        if (i > 0) {
            if ((bArr[i - 1] & 1) != 0) {
                i2 = 0;
            }
            b = (byte) i2;
        } else {
            if ((bArr[bArr.length - 1] & 1) != 0) {
                i2 = 0;
            }
            b = (byte) i2;
        }
        while (i < bArr.length) {
            bArr[i] = b;
            i++;
        }
        return length;
    }

    public int a(byte[] bArr) {
        byte b = bArr[bArr.length - 1];
        int length = bArr.length - 1;
        while (length > 0 && bArr[length - 1] == b) {
            length--;
        }
        return bArr.length - length;
    }
}
