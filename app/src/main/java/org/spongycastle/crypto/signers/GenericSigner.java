package org.spongycastle.crypto.signers;

import org.spongycastle.crypto.AsymmetricBlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.Signer;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.crypto.params.ParametersWithRandom;
import org.spongycastle.util.Arrays;

public class GenericSigner implements Signer {
    private final AsymmetricBlockCipher a;
    private final Digest b;
    private boolean c;

    public GenericSigner(AsymmetricBlockCipher asymmetricBlockCipher, Digest digest) {
        this.a = asymmetricBlockCipher;
        this.b = digest;
    }

    public void a(boolean z, CipherParameters cipherParameters) {
        this.c = z;
        AsymmetricKeyParameter asymmetricKeyParameter;
        if (cipherParameters instanceof ParametersWithRandom) {
            asymmetricKeyParameter = (AsymmetricKeyParameter) ((ParametersWithRandom) cipherParameters).b();
        } else {
            asymmetricKeyParameter = (AsymmetricKeyParameter) cipherParameters;
        }
        if (z && !asymmetricKeyParameter.a()) {
            throw new IllegalArgumentException("signing requires private key");
        } else if (z || !asymmetricKeyParameter.a()) {
            b();
            this.a.a(z, cipherParameters);
        } else {
            throw new IllegalArgumentException("verification requires public key");
        }
    }

    public void a(byte b) {
        this.b.a(b);
    }

    public void a(byte[] bArr, int i, int i2) {
        this.b.a(bArr, i, i2);
    }

    public byte[] a() {
        if (this.c) {
            byte[] bArr = new byte[this.b.b()];
            this.b.a(bArr, 0);
            return this.a.a(bArr, 0, bArr.length);
        }
        throw new IllegalStateException("GenericSigner not initialised for signature generation.");
    }

    public boolean a(byte[] bArr) {
        boolean z = false;
        if (this.c) {
            throw new IllegalStateException("GenericSigner not initialised for verification");
        }
        byte[] bArr2 = new byte[this.b.b()];
        this.b.a(bArr2, z);
        try {
            z = Arrays.b(this.a.a(bArr, 0, bArr.length), bArr2);
        } catch (Exception e) {
        }
        return z;
    }

    public void b() {
        this.b.c();
    }
}
