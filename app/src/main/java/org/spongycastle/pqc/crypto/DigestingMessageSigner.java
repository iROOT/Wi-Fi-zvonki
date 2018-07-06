package org.spongycastle.pqc.crypto;

import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.Signer;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.crypto.params.ParametersWithRandom;

public class DigestingMessageSigner implements Signer {
    private final Digest a;
    private final MessageSigner b;
    private boolean c;

    public void a(boolean z, CipherParameters cipherParameters) {
        this.c = z;
        AsymmetricKeyParameter asymmetricKeyParameter;
        if (cipherParameters instanceof ParametersWithRandom) {
            asymmetricKeyParameter = (AsymmetricKeyParameter) ((ParametersWithRandom) cipherParameters).b();
        } else {
            asymmetricKeyParameter = (AsymmetricKeyParameter) cipherParameters;
        }
        if (z && !asymmetricKeyParameter.a()) {
            throw new IllegalArgumentException("Signing Requires Private Key.");
        } else if (z || !asymmetricKeyParameter.a()) {
            b();
            this.b.a(z, cipherParameters);
        } else {
            throw new IllegalArgumentException("Verification Requires Public Key.");
        }
    }

    public byte[] a() {
        if (this.c) {
            byte[] bArr = new byte[this.a.b()];
            this.a.a(bArr, 0);
            return this.b.a(bArr);
        }
        throw new IllegalStateException("RainbowDigestSigner not initialised for signature generation.");
    }

    public boolean b(byte[] bArr) {
        if (this.c) {
            throw new IllegalStateException("RainbowDigestSigner not initialised for verification");
        }
        byte[] bArr2 = new byte[this.a.b()];
        this.a.a(bArr2, 0);
        return this.b.a(bArr2, bArr);
    }

    public void a(byte b) {
        this.a.a(b);
    }

    public void a(byte[] bArr, int i, int i2) {
        this.a.a(bArr, i, i2);
    }

    public void b() {
        this.a.c();
    }

    public boolean a(byte[] bArr) {
        return b(bArr);
    }
}
