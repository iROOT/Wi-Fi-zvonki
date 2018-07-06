package org.spongycastle.crypto.paddings;

import java.security.SecureRandom;
import org.spongycastle.crypto.InvalidCipherTextException;

public class PKCS7Padding implements BlockCipherPadding {
    public void a(SecureRandom secureRandom) {
    }

    public int a(byte[] bArr, int i) {
        byte length = (byte) (bArr.length - i);
        while (i < bArr.length) {
            bArr[i] = length;
            i++;
        }
        return length;
    }

    public int a(byte[] bArr) {
        byte b = bArr[bArr.length - 1] & 255;
        if (b > bArr.length || b == (byte) 0) {
            throw new InvalidCipherTextException("pad block corrupted");
        }
        for (byte b2 = (byte) 1; b2 <= b; b2++) {
            if (bArr[bArr.length - b2] != b) {
                throw new InvalidCipherTextException("pad block corrupted");
            }
        }
        return b;
    }
}
