package org.spongycastle.crypto.paddings;

import java.security.SecureRandom;

public class ZeroBytePadding implements BlockCipherPadding {
    public void a(SecureRandom secureRandom) {
    }

    public int a(byte[] bArr, int i) {
        int length = bArr.length - i;
        while (i < bArr.length) {
            bArr[i] = (byte) 0;
            i++;
        }
        return length;
    }

    public int a(byte[] bArr) {
        int length = bArr.length;
        while (length > 0 && bArr[length - 1] == (byte) 0) {
            length--;
        }
        return bArr.length - length;
    }
}
