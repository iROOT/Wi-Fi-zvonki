package org.spongycastle.crypto.generators;

import org.spongycastle.crypto.CipherKeyGenerator;
import org.spongycastle.crypto.KeyGenerationParameters;

public class Poly1305KeyGenerator extends CipherKeyGenerator {
    public void a(KeyGenerationParameters keyGenerationParameters) {
        super.a(new KeyGenerationParameters(keyGenerationParameters.a(), 256));
    }

    public byte[] a() {
        byte[] a = super.a();
        a(a);
        return a;
    }

    public static void a(byte[] bArr) {
        if (bArr.length != 32) {
            throw new IllegalArgumentException("Poly1305 key must be 256 bits.");
        }
        bArr[19] = (byte) (bArr[19] & 15);
        bArr[23] = (byte) (bArr[23] & 15);
        bArr[27] = (byte) (bArr[27] & 15);
        bArr[31] = (byte) (bArr[31] & 15);
        bArr[20] = (byte) (bArr[20] & -4);
        bArr[24] = (byte) (bArr[24] & -4);
        bArr[28] = (byte) (bArr[28] & -4);
    }

    public static void b(byte[] bArr) {
        if (bArr.length != 32) {
            throw new IllegalArgumentException("Poly1305 key must be 256 bits.");
        }
        a(bArr[19], (byte) 15);
        a(bArr[23], (byte) 15);
        a(bArr[27], (byte) 15);
        a(bArr[31], (byte) 15);
        a(bArr[20], (byte) -4);
        a(bArr[24], (byte) -4);
        a(bArr[28], (byte) -4);
    }

    private static void a(byte b, byte b2) {
        if (((b2 ^ -1) & b) != 0) {
            throw new IllegalArgumentException("Invalid format for r portion of Poly1305 key.");
        }
    }
}
