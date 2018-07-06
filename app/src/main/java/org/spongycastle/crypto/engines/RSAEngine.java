package org.spongycastle.crypto.engines;

import org.spongycastle.crypto.AsymmetricBlockCipher;
import org.spongycastle.crypto.CipherParameters;

public class RSAEngine implements AsymmetricBlockCipher {
    private RSACoreEngine a;

    public void a(boolean z, CipherParameters cipherParameters) {
        if (this.a == null) {
            this.a = new RSACoreEngine();
        }
        this.a.a(z, cipherParameters);
    }

    public int a() {
        return this.a.a();
    }

    public int b() {
        return this.a.b();
    }

    public byte[] a(byte[] bArr, int i, int i2) {
        if (this.a != null) {
            return this.a.a(this.a.b(this.a.a(bArr, i, i2)));
        }
        throw new IllegalStateException("RSA engine not initialised");
    }
}
