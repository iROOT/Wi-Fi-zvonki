package org.spongycastle.crypto.generators;

import org.spongycastle.crypto.KeyGenerationParameters;
import org.spongycastle.crypto.params.DESParameters;
import org.spongycastle.crypto.params.DESedeParameters;

public class DESedeKeyGenerator extends DESKeyGenerator {
    public void a(KeyGenerationParameters keyGenerationParameters) {
        this.a = keyGenerationParameters.a();
        this.b = (keyGenerationParameters.b() + 7) / 8;
        if (this.b == 0 || this.b == 21) {
            this.b = 24;
        } else if (this.b == 14) {
            this.b = 16;
        } else if (this.b != 24 && this.b != 16) {
            throw new IllegalArgumentException("DESede key must be 192 or 128 bits long.");
        }
    }

    public byte[] a() {
        byte[] bArr = new byte[this.b];
        do {
            this.a.nextBytes(bArr);
            DESParameters.a(bArr);
        } while (DESedeParameters.a(bArr, 0, bArr.length));
        return bArr;
    }
}
