package org.spongycastle.jcajce.provider.asymmetric.rsa;

import java.io.ByteArrayOutputStream;
import java.security.AlgorithmParameters;
import java.security.InvalidKeyException;
import java.security.InvalidParameterException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.SignatureException;
import java.security.SignatureSpi;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.MGF1ParameterSpec;
import java.security.spec.PSSParameterSpec;
import org.spongycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.spongycastle.crypto.AsymmetricBlockCipher;
import org.spongycastle.crypto.CryptoException;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.engines.RSABlindedEngine;
import org.spongycastle.crypto.params.ParametersWithRandom;
import org.spongycastle.crypto.signers.PSSSigner;
import org.spongycastle.jcajce.provider.util.DigestFactory;

public class PSSSignatureSpi extends SignatureSpi {
    private AlgorithmParameters a;
    private PSSParameterSpec b;
    private PSSParameterSpec c;
    private AsymmetricBlockCipher d;
    private Digest e;
    private Digest f;
    private int g;
    private byte h;
    private boolean i;
    private PSSSigner j;

    private class NullPssDigest implements Digest {
        final /* synthetic */ PSSSignatureSpi a;
        private ByteArrayOutputStream b = new ByteArrayOutputStream();
        private Digest c;
        private boolean d = true;

        public NullPssDigest(PSSSignatureSpi pSSSignatureSpi, Digest digest) {
            this.a = pSSSignatureSpi;
            this.c = digest;
        }

        public String a() {
            return "NULL";
        }

        public int b() {
            return this.c.b();
        }

        public void a(byte b) {
            this.b.write(b);
        }

        public void a(byte[] bArr, int i, int i2) {
            this.b.write(bArr, i, i2);
        }

        public int a(byte[] bArr, int i) {
            boolean z = false;
            Object toByteArray = this.b.toByteArray();
            if (this.d) {
                System.arraycopy(toByteArray, 0, bArr, i, toByteArray.length);
            } else {
                this.c.a(toByteArray, 0, toByteArray.length);
                this.c.a(bArr, i);
            }
            c();
            if (!this.d) {
                z = true;
            }
            this.d = z;
            return toByteArray.length;
        }

        public void c() {
            this.b.reset();
            this.c.c();
        }
    }

    public static class PSSwithRSA extends PSSSignatureSpi {
        public PSSwithRSA() {
            super(new RSABlindedEngine(), null);
        }
    }

    public static class SHA1withRSA extends PSSSignatureSpi {
        public SHA1withRSA() {
            super(new RSABlindedEngine(), PSSParameterSpec.DEFAULT);
        }
    }

    public static class SHA224withRSA extends PSSSignatureSpi {
        public SHA224withRSA() {
            super(new RSABlindedEngine(), new PSSParameterSpec("SHA-224", "MGF1", new MGF1ParameterSpec("SHA-224"), 28, 1));
        }
    }

    public static class SHA256withRSA extends PSSSignatureSpi {
        public SHA256withRSA() {
            super(new RSABlindedEngine(), new PSSParameterSpec("SHA-256", "MGF1", new MGF1ParameterSpec("SHA-256"), 32, 1));
        }
    }

    public static class SHA384withRSA extends PSSSignatureSpi {
        public SHA384withRSA() {
            super(new RSABlindedEngine(), new PSSParameterSpec("SHA-384", "MGF1", new MGF1ParameterSpec("SHA-384"), 48, 1));
        }
    }

    public static class SHA512withRSA extends PSSSignatureSpi {
        public SHA512withRSA() {
            super(new RSABlindedEngine(), new PSSParameterSpec("SHA-512", "MGF1", new MGF1ParameterSpec("SHA-512"), 64, 1));
        }
    }

    public static class nonePSS extends PSSSignatureSpi {
        public nonePSS() {
            super(new RSABlindedEngine(), null, true);
        }
    }

    private byte a(int i) {
        if (i == 1) {
            return (byte) -68;
        }
        throw new IllegalArgumentException("unknown trailer field");
    }

    private void a() {
        if (this.i) {
            this.e = new NullPssDigest(this, this.f);
        } else {
            this.e = this.f;
        }
    }

    protected PSSSignatureSpi(AsymmetricBlockCipher asymmetricBlockCipher, PSSParameterSpec pSSParameterSpec) {
        this(asymmetricBlockCipher, pSSParameterSpec, false);
    }

    protected PSSSignatureSpi(AsymmetricBlockCipher asymmetricBlockCipher, PSSParameterSpec pSSParameterSpec, boolean z) {
        this.d = asymmetricBlockCipher;
        this.c = pSSParameterSpec;
        if (pSSParameterSpec == null) {
            this.b = PSSParameterSpec.DEFAULT;
        } else {
            this.b = pSSParameterSpec;
        }
        this.f = DigestFactory.a(this.b.getDigestAlgorithm());
        this.g = this.b.getSaltLength();
        this.h = a(this.b.getTrailerField());
        this.i = z;
        a();
    }

    protected void engineInitVerify(PublicKey publicKey) {
        if (publicKey instanceof RSAPublicKey) {
            this.j = new PSSSigner(this.d, this.e, this.f, this.g, this.h);
            this.j.a(false, RSAUtil.a((RSAPublicKey) publicKey));
            return;
        }
        throw new InvalidKeyException("Supplied key is not a RSAPublicKey instance");
    }

    protected void engineInitSign(PrivateKey privateKey, SecureRandom secureRandom) {
        if (privateKey instanceof RSAPrivateKey) {
            this.j = new PSSSigner(this.d, this.e, this.f, this.g, this.h);
            this.j.a(true, new ParametersWithRandom(RSAUtil.a((RSAPrivateKey) privateKey), secureRandom));
            return;
        }
        throw new InvalidKeyException("Supplied key is not a RSAPrivateKey instance");
    }

    protected void engineInitSign(PrivateKey privateKey) {
        if (privateKey instanceof RSAPrivateKey) {
            this.j = new PSSSigner(this.d, this.e, this.f, this.g, this.h);
            this.j.a(true, RSAUtil.a((RSAPrivateKey) privateKey));
            return;
        }
        throw new InvalidKeyException("Supplied key is not a RSAPrivateKey instance");
    }

    protected void engineUpdate(byte b) {
        this.j.a(b);
    }

    protected void engineUpdate(byte[] bArr, int i, int i2) {
        this.j.a(bArr, i, i2);
    }

    protected byte[] engineSign() {
        try {
            return this.j.a();
        } catch (CryptoException e) {
            throw new SignatureException(e.getMessage());
        }
    }

    protected boolean engineVerify(byte[] bArr) {
        return this.j.a(bArr);
    }

    protected void engineSetParameter(AlgorithmParameterSpec algorithmParameterSpec) {
        if (algorithmParameterSpec instanceof PSSParameterSpec) {
            PSSParameterSpec pSSParameterSpec = (PSSParameterSpec) algorithmParameterSpec;
            if (this.c != null && !DigestFactory.a(this.c.getDigestAlgorithm(), pSSParameterSpec.getDigestAlgorithm())) {
                throw new InvalidParameterException("parameter must be using " + this.c.getDigestAlgorithm());
            } else if (!pSSParameterSpec.getMGFAlgorithm().equalsIgnoreCase("MGF1") && !pSSParameterSpec.getMGFAlgorithm().equals(PKCSObjectIdentifiers.l_.d())) {
                throw new InvalidParameterException("unknown mask generation function specified");
            } else if (pSSParameterSpec.getMGFParameters() instanceof MGF1ParameterSpec) {
                MGF1ParameterSpec mGF1ParameterSpec = (MGF1ParameterSpec) pSSParameterSpec.getMGFParameters();
                if (DigestFactory.a(mGF1ParameterSpec.getDigestAlgorithm(), pSSParameterSpec.getDigestAlgorithm())) {
                    Digest a = DigestFactory.a(mGF1ParameterSpec.getDigestAlgorithm());
                    if (a == null) {
                        throw new InvalidParameterException("no match on MGF digest algorithm: " + mGF1ParameterSpec.getDigestAlgorithm());
                    }
                    this.a = null;
                    this.b = pSSParameterSpec;
                    this.f = a;
                    this.g = this.b.getSaltLength();
                    this.h = a(this.b.getTrailerField());
                    a();
                    return;
                }
                throw new InvalidParameterException("digest algorithm for MGF should be the same as for PSS parameters.");
            } else {
                throw new InvalidParameterException("unkown MGF parameters");
            }
        }
        throw new InvalidParameterException("Only PSSParameterSpec supported");
    }

    protected AlgorithmParameters engineGetParameters() {
        if (this.a == null && this.b != null) {
            try {
                this.a = AlgorithmParameters.getInstance("PSS", "SC");
                this.a.init(this.b);
            } catch (Exception e) {
                throw new RuntimeException(e.toString());
            }
        }
        return this.a;
    }

    protected void engineSetParameter(String str, Object obj) {
        throw new UnsupportedOperationException("engineSetParameter unsupported");
    }

    protected Object engineGetParameter(String str) {
        throw new UnsupportedOperationException("engineGetParameter unsupported");
    }
}
