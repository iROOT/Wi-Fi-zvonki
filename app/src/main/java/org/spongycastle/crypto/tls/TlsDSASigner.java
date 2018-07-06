package org.spongycastle.crypto.tls;

import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DSA;
import org.spongycastle.crypto.Signer;
import org.spongycastle.crypto.digests.NullDigest;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.crypto.params.ParametersWithRandom;
import org.spongycastle.crypto.signers.DSADigestSigner;

public abstract class TlsDSASigner extends AbstractTlsSigner {
    protected abstract short a();

    protected abstract DSA b();

    public byte[] a(SignatureAndHashAlgorithm signatureAndHashAlgorithm, AsymmetricKeyParameter asymmetricKeyParameter, byte[] bArr) {
        Signer a = a(signatureAndHashAlgorithm, true, true, new ParametersWithRandom(asymmetricKeyParameter, this.a.a()));
        if (signatureAndHashAlgorithm == null) {
            a.a(bArr, 16, 20);
        } else {
            a.a(bArr, 0, bArr.length);
        }
        return a.a();
    }

    public boolean a(SignatureAndHashAlgorithm signatureAndHashAlgorithm, byte[] bArr, AsymmetricKeyParameter asymmetricKeyParameter, byte[] bArr2) {
        Signer a = a(signatureAndHashAlgorithm, true, false, (CipherParameters) asymmetricKeyParameter);
        if (signatureAndHashAlgorithm == null) {
            a.a(bArr2, 16, 20);
        } else {
            a.a(bArr2, 0, bArr2.length);
        }
        return a.a(bArr);
    }

    public Signer a(SignatureAndHashAlgorithm signatureAndHashAlgorithm, AsymmetricKeyParameter asymmetricKeyParameter) {
        return a(signatureAndHashAlgorithm, false, false, (CipherParameters) asymmetricKeyParameter);
    }

    protected Signer a(SignatureAndHashAlgorithm signatureAndHashAlgorithm, boolean z, boolean z2, CipherParameters cipherParameters) {
        if ((signatureAndHashAlgorithm != null) != TlsUtils.c(this.a)) {
            throw new IllegalStateException();
        } else if (signatureAndHashAlgorithm == null || (signatureAndHashAlgorithm.a() == (short) 2 && signatureAndHashAlgorithm.b() == a())) {
            Signer dSADigestSigner = new DSADigestSigner(b(), z ? new NullDigest() : TlsUtils.b((short) 2));
            dSADigestSigner.a(z2, cipherParameters);
            return dSADigestSigner;
        } else {
            throw new IllegalStateException();
        }
    }
}
