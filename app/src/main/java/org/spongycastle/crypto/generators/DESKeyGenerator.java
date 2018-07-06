package org.spongycastle.crypto.generators;

import org.spongycastle.crypto.CipherKeyGenerator;
import org.spongycastle.crypto.KeyGenerationParameters;
import org.spongycastle.crypto.params.DESParameters;

public class DESKeyGenerator extends CipherKeyGenerator {
    public void a(KeyGenerationParameters keyGenerationParameters) {
        super.a(keyGenerationParameters);
        if (this.b == 0 || this.b == 7) {
            this.b = 8;
        } else if (this.b != 8) {
            throw new IllegalArgumentException("DES key must be 64 bits long.");
        }
    }

    public byte[] a() {
        byte[] bArr = new byte[8];
        do {
            this.a.nextBytes(bArr);
            DESParameters.a(bArr);
        } while (DESParameters.a(bArr, 0));
        return bArr;
    }
}
