package org.spongycastle.jcajce.provider.asymmetric.rsa;

import java.io.ByteArrayOutputStream;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.InvalidParameterException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.MGF1ParameterSpec;
import javax.crypto.BadPaddingException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.OAEPParameterSpec;
import javax.crypto.spec.PSource.PSpecified;
import org.spongycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.spongycastle.crypto.AsymmetricBlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.InvalidCipherTextException;
import org.spongycastle.crypto.encodings.ISO9796d1Encoding;
import org.spongycastle.crypto.encodings.OAEPEncoding;
import org.spongycastle.crypto.encodings.PKCS1Encoding;
import org.spongycastle.crypto.engines.RSABlindedEngine;
import org.spongycastle.crypto.params.ParametersWithRandom;
import org.spongycastle.jcajce.provider.asymmetric.util.BaseCipherSpi;
import org.spongycastle.jcajce.provider.util.DigestFactory;
import org.spongycastle.util.Strings;

public class CipherSpi extends BaseCipherSpi {
    private AsymmetricBlockCipher c;
    private AlgorithmParameterSpec d;
    private AlgorithmParameters e;
    private boolean f = false;
    private boolean g = false;
    private ByteArrayOutputStream h = new ByteArrayOutputStream();

    public static class ISO9796d1Padding extends CipherSpi {
        public ISO9796d1Padding() {
            super(new ISO9796d1Encoding(new RSABlindedEngine()));
        }
    }

    public static class NoPadding extends CipherSpi {
        public NoPadding() {
            super(new RSABlindedEngine());
        }
    }

    public static class OAEPPadding extends CipherSpi {
        public OAEPPadding() {
            super(OAEPParameterSpec.DEFAULT);
        }
    }

    public static class PKCS1v1_5Padding extends CipherSpi {
        public PKCS1v1_5Padding() {
            super(new PKCS1Encoding(new RSABlindedEngine()));
        }
    }

    public static class PKCS1v1_5Padding_PrivateOnly extends CipherSpi {
        public PKCS1v1_5Padding_PrivateOnly() {
            super(false, true, new PKCS1Encoding(new RSABlindedEngine()));
        }
    }

    public static class PKCS1v1_5Padding_PublicOnly extends CipherSpi {
        public PKCS1v1_5Padding_PublicOnly() {
            super(true, false, new PKCS1Encoding(new RSABlindedEngine()));
        }
    }

    public CipherSpi(AsymmetricBlockCipher asymmetricBlockCipher) {
        this.c = asymmetricBlockCipher;
    }

    public CipherSpi(OAEPParameterSpec oAEPParameterSpec) {
        try {
            a(oAEPParameterSpec);
        } catch (NoSuchPaddingException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public CipherSpi(boolean z, boolean z2, AsymmetricBlockCipher asymmetricBlockCipher) {
        this.f = z;
        this.g = z2;
        this.c = asymmetricBlockCipher;
    }

    private void a(OAEPParameterSpec oAEPParameterSpec) {
        MGF1ParameterSpec mGF1ParameterSpec = (MGF1ParameterSpec) oAEPParameterSpec.getMGFParameters();
        Digest a = DigestFactory.a(mGF1ParameterSpec.getDigestAlgorithm());
        if (a == null) {
            throw new NoSuchPaddingException("no match on OAEP constructor for digest algorithm: " + mGF1ParameterSpec.getDigestAlgorithm());
        }
        this.c = new OAEPEncoding(new RSABlindedEngine(), a, ((PSpecified) oAEPParameterSpec.getPSource()).getValue());
        this.d = oAEPParameterSpec;
    }

    protected int engineGetBlockSize() {
        try {
            return this.c.a();
        } catch (NullPointerException e) {
            throw new IllegalStateException("RSA Cipher not initialised");
        }
    }

    protected int engineGetKeySize(Key key) {
        if (key instanceof RSAPrivateKey) {
            return ((RSAPrivateKey) key).getModulus().bitLength();
        }
        if (key instanceof RSAPublicKey) {
            return ((RSAPublicKey) key).getModulus().bitLength();
        }
        throw new IllegalArgumentException("not an RSA key!");
    }

    protected int engineGetOutputSize(int i) {
        try {
            return this.c.b();
        } catch (NullPointerException e) {
            throw new IllegalStateException("RSA Cipher not initialised");
        }
    }

    protected AlgorithmParameters engineGetParameters() {
        if (this.e == null && this.d != null) {
            try {
                this.e = AlgorithmParameters.getInstance("OAEP", "SC");
                this.e.init(this.d);
            } catch (Exception e) {
                throw new RuntimeException(e.toString());
            }
        }
        return this.e;
    }

    protected void engineSetMode(String str) {
        String b = Strings.b(str);
        if (!b.equals("NONE") && !b.equals("ECB")) {
            if (b.equals("1")) {
                this.g = true;
                this.f = false;
            } else if (b.equals("2")) {
                this.g = false;
                this.f = true;
            } else {
                throw new NoSuchAlgorithmException("can't support mode " + str);
            }
        }
    }

    protected void engineSetPadding(String str) {
        String b = Strings.b(str);
        if (b.equals("NOPADDING")) {
            this.c = new RSABlindedEngine();
        } else if (b.equals("PKCS1PADDING")) {
            this.c = new PKCS1Encoding(new RSABlindedEngine());
        } else if (b.equals("ISO9796-1PADDING")) {
            this.c = new ISO9796d1Encoding(new RSABlindedEngine());
        } else if (b.equals("OAEPWITHMD5ANDMGF1PADDING")) {
            a(new OAEPParameterSpec("MD5", "MGF1", new MGF1ParameterSpec("MD5"), PSpecified.DEFAULT));
        } else if (b.equals("OAEPPADDING")) {
            a(OAEPParameterSpec.DEFAULT);
        } else if (b.equals("OAEPWITHSHA1ANDMGF1PADDING") || b.equals("OAEPWITHSHA-1ANDMGF1PADDING")) {
            a(OAEPParameterSpec.DEFAULT);
        } else if (b.equals("OAEPWITHSHA224ANDMGF1PADDING") || b.equals("OAEPWITHSHA-224ANDMGF1PADDING")) {
            a(new OAEPParameterSpec("SHA-224", "MGF1", new MGF1ParameterSpec("SHA-224"), PSpecified.DEFAULT));
        } else if (b.equals("OAEPWITHSHA256ANDMGF1PADDING") || b.equals("OAEPWITHSHA-256ANDMGF1PADDING")) {
            a(new OAEPParameterSpec("SHA-256", "MGF1", MGF1ParameterSpec.SHA256, PSpecified.DEFAULT));
        } else if (b.equals("OAEPWITHSHA384ANDMGF1PADDING") || b.equals("OAEPWITHSHA-384ANDMGF1PADDING")) {
            a(new OAEPParameterSpec("SHA-384", "MGF1", MGF1ParameterSpec.SHA384, PSpecified.DEFAULT));
        } else if (b.equals("OAEPWITHSHA512ANDMGF1PADDING") || b.equals("OAEPWITHSHA-512ANDMGF1PADDING")) {
            a(new OAEPParameterSpec("SHA-512", "MGF1", MGF1ParameterSpec.SHA512, PSpecified.DEFAULT));
        } else {
            throw new NoSuchPaddingException(str + " unavailable with RSA.");
        }
    }

    protected void engineInit(int i, Key key, AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) {
        if (algorithmParameterSpec == null || (algorithmParameterSpec instanceof OAEPParameterSpec)) {
            CipherParameters a;
            CipherParameters cipherParameters;
            if (key instanceof RSAPublicKey) {
                if (this.g && i == 1) {
                    throw new InvalidKeyException("mode 1 requires RSAPrivateKey");
                }
                a = RSAUtil.a((RSAPublicKey) key);
            } else if (!(key instanceof RSAPrivateKey)) {
                throw new InvalidKeyException("unknown key type passed to RSA");
            } else if (this.f && i == 1) {
                throw new InvalidKeyException("mode 2 requires RSAPublicKey");
            } else {
                a = RSAUtil.a((RSAPrivateKey) key);
            }
            if (algorithmParameterSpec != null) {
                OAEPParameterSpec oAEPParameterSpec = (OAEPParameterSpec) algorithmParameterSpec;
                this.d = algorithmParameterSpec;
                if (!oAEPParameterSpec.getMGFAlgorithm().equalsIgnoreCase("MGF1") && !oAEPParameterSpec.getMGFAlgorithm().equals(PKCSObjectIdentifiers.l_.d())) {
                    throw new InvalidAlgorithmParameterException("unknown mask generation function specified");
                } else if (oAEPParameterSpec.getMGFParameters() instanceof MGF1ParameterSpec) {
                    Digest a2 = DigestFactory.a(oAEPParameterSpec.getDigestAlgorithm());
                    if (a2 == null) {
                        throw new InvalidAlgorithmParameterException("no match on digest algorithm: " + oAEPParameterSpec.getDigestAlgorithm());
                    }
                    MGF1ParameterSpec mGF1ParameterSpec = (MGF1ParameterSpec) oAEPParameterSpec.getMGFParameters();
                    Digest a3 = DigestFactory.a(mGF1ParameterSpec.getDigestAlgorithm());
                    if (a3 == null) {
                        throw new InvalidAlgorithmParameterException("no match on MGF digest algorithm: " + mGF1ParameterSpec.getDigestAlgorithm());
                    }
                    this.c = new OAEPEncoding(new RSABlindedEngine(), a2, a3, ((PSpecified) oAEPParameterSpec.getPSource()).getValue());
                } else {
                    throw new InvalidAlgorithmParameterException("unkown MGF parameters");
                }
            }
            if (this.c instanceof RSABlindedEngine) {
                cipherParameters = a;
            } else if (secureRandom != null) {
                cipherParameters = new ParametersWithRandom(a, secureRandom);
            } else {
                cipherParameters = new ParametersWithRandom(a, new SecureRandom());
            }
            this.h.reset();
            switch (i) {
                case 1:
                case 3:
                    this.c.a(true, cipherParameters);
                    return;
                case 2:
                case 4:
                    this.c.a(false, cipherParameters);
                    return;
                default:
                    throw new InvalidParameterException("unknown opmode " + i + " passed to RSA");
            }
        }
        throw new IllegalArgumentException("unknown parameter type.");
    }

    protected void engineInit(int i, Key key, AlgorithmParameters algorithmParameters, SecureRandom secureRandom) {
        AlgorithmParameterSpec algorithmParameterSpec = null;
        if (algorithmParameters != null) {
            try {
                algorithmParameterSpec = algorithmParameters.getParameterSpec(OAEPParameterSpec.class);
            } catch (Throwable e) {
                throw new InvalidAlgorithmParameterException("cannot recognise parameters: " + e.toString(), e);
            }
        }
        this.e = algorithmParameters;
        engineInit(i, key, algorithmParameterSpec, secureRandom);
    }

    protected void engineInit(int i, Key key, SecureRandom secureRandom) {
        try {
            engineInit(i, key, (AlgorithmParameterSpec) null, secureRandom);
        } catch (Throwable e) {
            throw new InvalidKeyException("Eeeek! " + e.toString(), e);
        }
    }

    protected byte[] engineUpdate(byte[] bArr, int i, int i2) {
        this.h.write(bArr, i, i2);
        if (this.c instanceof RSABlindedEngine) {
            if (this.h.size() > this.c.a() + 1) {
                throw new ArrayIndexOutOfBoundsException("too much data for RSA block");
            }
        } else if (this.h.size() > this.c.a()) {
            throw new ArrayIndexOutOfBoundsException("too much data for RSA block");
        }
        return null;
    }

    protected int engineUpdate(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        this.h.write(bArr, i, i2);
        if (this.c instanceof RSABlindedEngine) {
            if (this.h.size() > this.c.a() + 1) {
                throw new ArrayIndexOutOfBoundsException("too much data for RSA block");
            }
        } else if (this.h.size() > this.c.a()) {
            throw new ArrayIndexOutOfBoundsException("too much data for RSA block");
        }
        return 0;
    }

    protected byte[] engineDoFinal(byte[] bArr, int i, int i2) {
        if (bArr != null) {
            this.h.write(bArr, i, i2);
        }
        if (this.c instanceof RSABlindedEngine) {
            if (this.h.size() > this.c.a() + 1) {
                throw new ArrayIndexOutOfBoundsException("too much data for RSA block");
            }
        } else if (this.h.size() > this.c.a()) {
            throw new ArrayIndexOutOfBoundsException("too much data for RSA block");
        }
        try {
            byte[] toByteArray = this.h.toByteArray();
            this.h.reset();
            return this.c.a(toByteArray, 0, toByteArray.length);
        } catch (InvalidCipherTextException e) {
            throw new BadPaddingException(e.getMessage());
        }
    }

    protected int engineDoFinal(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        if (bArr != null) {
            this.h.write(bArr, i, i2);
        }
        if (this.c instanceof RSABlindedEngine) {
            if (this.h.size() > this.c.a() + 1) {
                throw new ArrayIndexOutOfBoundsException("too much data for RSA block");
            }
        } else if (this.h.size() > this.c.a()) {
            throw new ArrayIndexOutOfBoundsException("too much data for RSA block");
        }
        try {
            byte[] toByteArray = this.h.toByteArray();
            toByteArray = this.c.a(toByteArray, 0, toByteArray.length);
            this.h.reset();
            for (int i4 = 0; i4 != toByteArray.length; i4++) {
                bArr2[i3 + i4] = toByteArray[i4];
            }
            return toByteArray.length;
        } catch (InvalidCipherTextException e) {
            throw new BadPaddingException(e.getMessage());
        } catch (Throwable th) {
            this.h.reset();
        }
    }
}
