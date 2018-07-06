package org.spongycastle.crypto.paddings;

import java.security.SecureRandom;
import org.spongycastle.crypto.InvalidCipherTextException;

public class X923Padding implements BlockCipherPadding {
    SecureRandom a = null;

    public void a(SecureRandom secureRandom) {
        this.a = secureRandom;
    }

    public int a(byte[] bArr, int i) {
        byte length = (byte) (bArr.length - i);
        while (i < bArr.length - 1) {
            if (this.a == null) {
                bArr[i] = (byte) 0;
            } else {
                bArr[i] = (byte) this.a.nextInt();
            }
            i++;
        }
        bArr[i] = length;
        return length;
    }

    public int a(byte[] bArr) {
        int i = bArr[bArr.length - 1] & 255;
        if (i <= bArr.length) {
            return i;
        }
        throw new InvalidCipherTextException("pad block corrupted");
    }
}
