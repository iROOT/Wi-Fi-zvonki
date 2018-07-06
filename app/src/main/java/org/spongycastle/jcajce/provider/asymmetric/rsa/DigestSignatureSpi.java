package org.spongycastle.jcajce.provider.asymmetric.rsa;

import java.security.AlgorithmParameters;
import java.security.InvalidKeyException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SignatureException;
import java.security.SignatureSpi;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.AlgorithmParameterSpec;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.DERNull;
import org.spongycastle.asn1.nist.NISTObjectIdentifiers;
import org.spongycastle.asn1.oiw.OIWObjectIdentifiers;
import org.spongycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.spongycastle.asn1.teletrust.TeleTrusTObjectIdentifiers;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.asn1.x509.DigestInfo;
import org.spongycastle.crypto.AsymmetricBlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.digests.MD2Digest;
import org.spongycastle.crypto.digests.MD4Digest;
import org.spongycastle.crypto.digests.MD5Digest;
import org.spongycastle.crypto.digests.NullDigest;
import org.spongycastle.crypto.digests.RIPEMD128Digest;
import org.spongycastle.crypto.digests.RIPEMD160Digest;
import org.spongycastle.crypto.digests.RIPEMD256Digest;
import org.spongycastle.crypto.digests.SHA1Digest;
import org.spongycastle.crypto.digests.SHA224Digest;
import org.spongycastle.crypto.digests.SHA256Digest;
import org.spongycastle.crypto.digests.SHA384Digest;
import org.spongycastle.crypto.digests.SHA512Digest;
import org.spongycastle.crypto.encodings.PKCS1Encoding;
import org.spongycastle.crypto.engines.RSABlindedEngine;

public class DigestSignatureSpi extends SignatureSpi {
    private Digest a;
    private AsymmetricBlockCipher b;
    private AlgorithmIdentifier c;

    public static class MD2 extends DigestSignatureSpi {
        public MD2() {
            super(PKCSObjectIdentifiers.F, new MD2Digest(), new PKCS1Encoding(new RSABlindedEngine()));
        }
    }

    public static class MD4 extends DigestSignatureSpi {
        public MD4() {
            super(PKCSObjectIdentifiers.G, new MD4Digest(), new PKCS1Encoding(new RSABlindedEngine()));
        }
    }

    public static class MD5 extends DigestSignatureSpi {
        public MD5() {
            super(PKCSObjectIdentifiers.H, new MD5Digest(), new PKCS1Encoding(new RSABlindedEngine()));
        }
    }

    public static class RIPEMD128 extends DigestSignatureSpi {
        public RIPEMD128() {
            super(TeleTrusTObjectIdentifiers.c, new RIPEMD128Digest(), new PKCS1Encoding(new RSABlindedEngine()));
        }
    }

    public static class RIPEMD160 extends DigestSignatureSpi {
        public RIPEMD160() {
            super(TeleTrusTObjectIdentifiers.b, new RIPEMD160Digest(), new PKCS1Encoding(new RSABlindedEngine()));
        }
    }

    public static class RIPEMD256 extends DigestSignatureSpi {
        public RIPEMD256() {
            super(TeleTrusTObjectIdentifiers.d, new RIPEMD256Digest(), new PKCS1Encoding(new RSABlindedEngine()));
        }
    }

    public static class SHA1 extends DigestSignatureSpi {
        public SHA1() {
            super(OIWObjectIdentifiers.i, new SHA1Digest(), new PKCS1Encoding(new RSABlindedEngine()));
        }
    }

    public static class SHA224 extends DigestSignatureSpi {
        public SHA224() {
            super(NISTObjectIdentifiers.f, new SHA224Digest(), new PKCS1Encoding(new RSABlindedEngine()));
        }
    }

    public static class SHA256 extends DigestSignatureSpi {
        public SHA256() {
            super(NISTObjectIdentifiers.c, new SHA256Digest(), new PKCS1Encoding(new RSABlindedEngine()));
        }
    }

    public static class SHA384 extends DigestSignatureSpi {
        public SHA384() {
            super(NISTObjectIdentifiers.d, new SHA384Digest(), new PKCS1Encoding(new RSABlindedEngine()));
        }
    }

    public static class SHA512 extends DigestSignatureSpi {
        public SHA512() {
            super(NISTObjectIdentifiers.e, new SHA512Digest(), new PKCS1Encoding(new RSABlindedEngine()));
        }
    }

    public static class noneRSA extends DigestSignatureSpi {
        public noneRSA() {
            super(new NullDigest(), new PKCS1Encoding(new RSABlindedEngine()));
        }
    }

    protected DigestSignatureSpi(Digest digest, AsymmetricBlockCipher asymmetricBlockCipher) {
        this.a = digest;
        this.b = asymmetricBlockCipher;
        this.c = null;
    }

    protected DigestSignatureSpi(ASN1ObjectIdentifier aSN1ObjectIdentifier, Digest digest, AsymmetricBlockCipher asymmetricBlockCipher) {
        this.a = digest;
        this.b = asymmetricBlockCipher;
        this.c = new AlgorithmIdentifier(aSN1ObjectIdentifier, DERNull.a);
    }

    protected void engineInitVerify(PublicKey publicKey) {
        if (publicKey instanceof RSAPublicKey) {
            CipherParameters a = RSAUtil.a((RSAPublicKey) publicKey);
            this.a.c();
            this.b.a(false, a);
            return;
        }
        throw new InvalidKeyException("Supplied key (" + a((Object) publicKey) + ") is not a RSAPublicKey instance");
    }

    protected void engineInitSign(PrivateKey privateKey) {
        if (privateKey instanceof RSAPrivateKey) {
            CipherParameters a = RSAUtil.a((RSAPrivateKey) privateKey);
            this.a.c();
            this.b.a(true, a);
            return;
        }
        throw new InvalidKeyException("Supplied key (" + a((Object) privateKey) + ") is not a RSAPrivateKey instance");
    }

    private String a(Object obj) {
        if (obj == null) {
            return null;
        }
        return obj.getClass().getName();
    }

    protected void engineUpdate(byte b) {
        this.a.a(b);
    }

    protected void engineUpdate(byte[] bArr, int i, int i2) {
        this.a.a(bArr, i, i2);
    }

    protected byte[] engineSign() {
        byte[] bArr = new byte[this.a.b()];
        this.a.a(bArr, 0);
        try {
            bArr = a(bArr);
            return this.b.a(bArr, 0, bArr.length);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new SignatureException("key too small for signature type");
        } catch (Exception e2) {
            throw new SignatureException(e2.toString());
        }
    }

    protected boolean engineVerify(byte[] bArr) {
        byte[] bArr2 = new byte[this.a.b()];
        this.a.a(bArr2, 0);
        try {
            byte[] a = this.b.a(bArr, 0, bArr.length);
            byte[] a2 = a(bArr2);
            int i;
            if (a.length == a2.length) {
                for (i = 0; i < a.length; i++) {
                    if (a[i] != a2[i]) {
                        return false;
                    }
                }
            } else if (a.length != a2.length - 2) {
                return false;
            } else {
                int length = (a.length - bArr2.length) - 2;
                int length2 = (a2.length - bArr2.length) - 2;
                a2[1] = (byte) (a2[1] - 2);
                a2[3] = (byte) (a2[3] - 2);
                for (i = 0; i < bArr2.length; i++) {
                    if (a[length + i] != a2[length2 + i]) {
                        return false;
                    }
                }
                for (i = 0; i < length; i++) {
                    if (a[i] != a2[i]) {
                        return false;
                    }
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    protected void engineSetParameter(AlgorithmParameterSpec algorithmParameterSpec) {
        throw new UnsupportedOperationException("engineSetParameter unsupported");
    }

    protected void engineSetParameter(String str, Object obj) {
        throw new UnsupportedOperationException("engineSetParameter unsupported");
    }

    protected Object engineGetParameter(String str) {
        return null;
    }

    protected AlgorithmParameters engineGetParameters() {
        return null;
    }

    private byte[] a(byte[] bArr) {
        return this.c == null ? bArr : new DigestInfo(this.c, bArr).a("DER");
    }
}
