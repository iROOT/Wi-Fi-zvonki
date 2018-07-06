package org.spongycastle.crypto.paddings;

import java.security.SecureRandom;
import org.spongycastle.crypto.InvalidCipherTextException;

public class ISO10126d2Padding implements BlockCipherPadding {
    SecureRandom a;

    public void a(SecureRandom secureRandom) {
        if (secureRandom != null) {
            this.a = secureRandom;
        } else {
            this.a = new SecureRandom();
        }
    }

    public int a(byte[] bArr, int i) {
        byte length = (byte) (bArr.length - i);
        while (i < bArr.length - 1) {
            bArr[i] = (byte) this.a.nextInt();
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
