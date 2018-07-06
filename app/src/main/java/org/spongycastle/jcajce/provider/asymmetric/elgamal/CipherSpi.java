package org.spongycastle.jcajce.provider.asymmetric.elgamal;

import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.InvalidParameterException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.MGF1ParameterSpec;
import javax.crypto.BadPaddingException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.interfaces.DHKey;
import javax.crypto.spec.OAEPParameterSpec;
import javax.crypto.spec.PSource.PSpecified;
import org.spongycastle.crypto.AsymmetricBlockCipher;
import org.spongycastle.crypto.BufferedAsymmetricBlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.InvalidCipherTextException;
import org.spongycastle.crypto.encodings.ISO9796d1Encoding;
import org.spongycastle.crypto.encodings.OAEPEncoding;
import org.spongycastle.crypto.encodings.PKCS1Encoding;
import org.spongycastle.crypto.engines.ElGamalEngine;
import org.spongycastle.crypto.params.ParametersWithRandom;
import org.spongycastle.jcajce.provider.asymmetric.util.BaseCipherSpi;
import org.spongycastle.jcajce.provider.util.DigestFactory;
import org.spongycastle.jce.interfaces.ElGamalKey;
import org.spongycastle.jce.interfaces.ElGamalPrivateKey;
import org.spongycastle.jce.interfaces.ElGamalPublicKey;
import org.spongycastle.util.Strings;

public class CipherSpi extends BaseCipherSpi {
    private BufferedAsymmetricBlockCipher c;
    private AlgorithmParameterSpec d;
    private AlgorithmParameters e;

    public static class NoPadding extends CipherSpi {
        public NoPadding() {
            super(new ElGamalEngine());
        }
    }

    public static class PKCS1v1_5Padding extends CipherSpi {
        public PKCS1v1_5Padding() {
            super(new PKCS1Encoding(new ElGamalEngine()));
        }
    }

    public CipherSpi(AsymmetricBlockCipher asymmetricBlockCipher) {
        this.c = new BufferedAsymmetricBlockCipher(asymmetricBlockCipher);
    }

    private void a(OAEPParameterSpec oAEPParameterSpec) {
        MGF1ParameterSpec mGF1ParameterSpec = (MGF1ParameterSpec) oAEPParameterSpec.getMGFParameters();
        Digest a = DigestFactory.a(mGF1ParameterSpec.getDigestAlgorithm());
        if (a == null) {
            throw new NoSuchPaddingException("no match on OAEP constructor for digest algorithm: " + mGF1ParameterSpec.getDigestAlgorithm());
        }
        this.c = new BufferedAsymmetricBlockCipher(new OAEPEncoding(new ElGamalEngine(), a, ((PSpecified) oAEPParameterSpec.getPSource()).getValue()));
        this.d = oAEPParameterSpec;
    }

    protected int engineGetBlockSize() {
        return this.c.a();
    }

    protected int engineGetKeySize(Key key) {
        if (key instanceof ElGamalKey) {
            return ((ElGamalKey) key).b().a().bitLength();
        }
        if (key instanceof DHKey) {
            return ((DHKey) key).getParams().getP().bitLength();
        }
        throw new IllegalArgumentException("not an ElGamal key!");
    }

    protected int engineGetOutputSize(int i) {
        return this.c.b();
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
            throw new NoSuchAlgorithmException("can't support mode " + str);
        }
    }

    protected void engineSetPadding(String str) {
        String b = Strings.b(str);
        if (b.equals("NOPADDING")) {
            this.c = new BufferedAsymmetricBlockCipher(new ElGamalEngine());
        } else if (b.equals("PKCS1PADDING")) {
            this.c = new BufferedAsymmetricBlockCipher(new PKCS1Encoding(new ElGamalEngine()));
        } else if (b.equals("ISO9796-1PADDING")) {
            this.c = new BufferedAsymmetricBlockCipher(new ISO9796d1Encoding(new ElGamalEngine()));
        } else if (b.equals("OAEPPADDING")) {
            a(OAEPParameterSpec.DEFAULT);
        } else if (b.equals("OAEPWITHMD5ANDMGF1PADDING")) {
            a(new OAEPParameterSpec("MD5", "MGF1", new MGF1ParameterSpec("MD5"), PSpecified.DEFAULT));
        } else if (b.equals("OAEPWITHSHA1ANDMGF1PADDING")) {
            a(OAEPParameterSpec.DEFAULT);
        } else if (b.equals("OAEPWITHSHA224ANDMGF1PADDING")) {
            a(new OAEPParameterSpec("SHA-224", "MGF1", new MGF1ParameterSpec("SHA-224"), PSpecified.DEFAULT));
        } else if (b.equals("OAEPWITHSHA256ANDMGF1PADDING")) {
            a(new OAEPParameterSpec("SHA-256", "MGF1", MGF1ParameterSpec.SHA256, PSpecified.DEFAULT));
        } else if (b.equals("OAEPWITHSHA384ANDMGF1PADDING")) {
            a(new OAEPParameterSpec("SHA-384", "MGF1", MGF1ParameterSpec.SHA384, PSpecified.DEFAULT));
        } else if (b.equals("OAEPWITHSHA512ANDMGF1PADDING")) {
            a(new OAEPParameterSpec("SHA-512", "MGF1", MGF1ParameterSpec.SHA512, PSpecified.DEFAULT));
        } else {
            throw new NoSuchPaddingException(str + " unavailable with ElGamal.");
        }
    }

    protected void engineInit(int i, Key key, AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) {
        if (algorithmParameterSpec == null) {
            CipherParameters a;
            CipherParameters parametersWithRandom;
            if (key instanceof ElGamalPublicKey) {
                a = ElGamalUtil.a((PublicKey) key);
            } else if (key instanceof ElGamalPrivateKey) {
                a = ElGamalUtil.a((PrivateKey) key);
            } else {
                throw new InvalidKeyException("unknown key type passed to ElGamal");
            }
            if (secureRandom != null) {
                parametersWithRandom = new ParametersWithRandom(a, secureRandom);
            } else {
                parametersWithRandom = a;
            }
            switch (i) {
                case 1:
                case 3:
                    this.c.a(true, parametersWithRandom);
                    return;
                case 2:
                case 4:
                    this.c.a(false, parametersWithRandom);
                    return;
                default:
                    throw new InvalidParameterException("unknown opmode " + i + " passed to ElGamal");
            }
        }
        throw new IllegalArgumentException("unknown parameter type.");
    }

    protected void engineInit(int i, Key key, AlgorithmParameters algorithmParameters, SecureRandom secureRandom) {
        throw new InvalidAlgorithmParameterException("can't handle parameters in ElGamal");
    }

    protected void engineInit(int i, Key key, SecureRandom secureRandom) {
        engineInit(i, key, (AlgorithmParameterSpec) null, secureRandom);
    }

    protected byte[] engineUpdate(byte[] bArr, int i, int i2) {
        this.c.a(bArr, i, i2);
        return null;
    }

    protected int engineUpdate(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        this.c.a(bArr, i, i2);
        return 0;
    }

    protected byte[] engineDoFinal(byte[] bArr, int i, int i2) {
        this.c.a(bArr, i, i2);
        try {
            return this.c.c();
        } catch (InvalidCipherTextException e) {
            throw new BadPaddingException(e.getMessage());
        }
    }

    protected int engineDoFinal(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        this.c.a(bArr, i, i2);
        try {
            byte[] c = this.c.c();
            for (int i4 = 0; i4 != c.length; i4++) {
                bArr2[i3 + i4] = c[i4];
            }
            return c.length;
        } catch (InvalidCipherTextException e) {
            throw new BadPaddingException(e.getMessage());
        }
    }
}
