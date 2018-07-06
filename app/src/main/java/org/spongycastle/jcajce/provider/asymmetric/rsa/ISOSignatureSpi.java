package org.spongycastle.jcajce.provider.asymmetric.rsa;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SignatureException;
import java.security.SignatureSpi;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.AlgorithmParameterSpec;
import org.spongycastle.crypto.AsymmetricBlockCipher;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.digests.MD5Digest;
import org.spongycastle.crypto.digests.RIPEMD160Digest;
import org.spongycastle.crypto.digests.SHA1Digest;
import org.spongycastle.crypto.engines.RSABlindedEngine;
import org.spongycastle.crypto.signers.ISO9796d2Signer;

public class ISOSignatureSpi extends SignatureSpi {
    private ISO9796d2Signer a;

    public static class MD5WithRSAEncryption extends ISOSignatureSpi {
        public MD5WithRSAEncryption() {
            super(new MD5Digest(), new RSABlindedEngine());
        }
    }

    public static class RIPEMD160WithRSAEncryption extends ISOSignatureSpi {
        public RIPEMD160WithRSAEncryption() {
            super(new RIPEMD160Digest(), new RSABlindedEngine());
        }
    }

    public static class SHA1WithRSAEncryption extends ISOSignatureSpi {
        public SHA1WithRSAEncryption() {
            super(new SHA1Digest(), new RSABlindedEngine());
        }
    }

    protected ISOSignatureSpi(Digest digest, AsymmetricBlockCipher asymmetricBlockCipher) {
        this.a = new ISO9796d2Signer(asymmetricBlockCipher, digest, true);
    }

    protected void engineInitVerify(PublicKey publicKey) {
        this.a.a(false, RSAUtil.a((RSAPublicKey) publicKey));
    }

    protected void engineInitSign(PrivateKey privateKey) {
        this.a.a(true, RSAUtil.a((RSAPrivateKey) privateKey));
    }

    protected void engineUpdate(byte b) {
        this.a.a(b);
    }

    protected void engineUpdate(byte[] bArr, int i, int i2) {
        this.a.a(bArr, i, i2);
    }

    protected byte[] engineSign() {
        try {
            return this.a.a();
        } catch (Exception e) {
            throw new SignatureException(e.toString());
        }
    }

    protected boolean engineVerify(byte[] bArr) {
        return this.a.a(bArr);
    }

    protected void engineSetParameter(AlgorithmParameterSpec algorithmParameterSpec) {
        throw new UnsupportedOperationException("engineSetParameter unsupported");
    }

    protected void engineSetParameter(String str, Object obj) {
        throw new UnsupportedOperationException("engineSetParameter unsupported");
    }

    protected Object engineGetParameter(String str) {
        throw new UnsupportedOperationException("engineSetParameter unsupported");
    }
}
