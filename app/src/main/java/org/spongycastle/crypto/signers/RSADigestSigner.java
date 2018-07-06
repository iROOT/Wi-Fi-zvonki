package org.spongycastle.crypto.signers;

import java.util.Hashtable;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.DERNull;
import org.spongycastle.asn1.nist.NISTObjectIdentifiers;
import org.spongycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.spongycastle.asn1.teletrust.TeleTrusTObjectIdentifiers;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.asn1.x509.DigestInfo;
import org.spongycastle.asn1.x509.X509ObjectIdentifiers;
import org.spongycastle.crypto.AsymmetricBlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.CryptoException;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.Signer;
import org.spongycastle.crypto.encodings.PKCS1Encoding;
import org.spongycastle.crypto.engines.RSABlindedEngine;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.crypto.params.ParametersWithRandom;
import org.spongycastle.util.Arrays;

public class RSADigestSigner implements Signer {
    private static final Hashtable e = new Hashtable();
    private final AsymmetricBlockCipher a = new PKCS1Encoding(new RSABlindedEngine());
    private final AlgorithmIdentifier b;
    private final Digest c;
    private boolean d;

    static {
        e.put("RIPEMD128", TeleTrusTObjectIdentifiers.c);
        e.put("RIPEMD160", TeleTrusTObjectIdentifiers.b);
        e.put("RIPEMD256", TeleTrusTObjectIdentifiers.d);
        e.put("SHA-1", X509ObjectIdentifiers.i);
        e.put("SHA-224", NISTObjectIdentifiers.f);
        e.put("SHA-256", NISTObjectIdentifiers.c);
        e.put("SHA-384", NISTObjectIdentifiers.d);
        e.put("SHA-512", NISTObjectIdentifiers.e);
        e.put("MD2", PKCSObjectIdentifiers.F);
        e.put("MD4", PKCSObjectIdentifiers.G);
        e.put("MD5", PKCSObjectIdentifiers.H);
    }

    public RSADigestSigner(Digest digest, ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        this.c = digest;
        this.b = new AlgorithmIdentifier(aSN1ObjectIdentifier, DERNull.a);
    }

    public void a(boolean z, CipherParameters cipherParameters) {
        this.d = z;
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
        this.c.a(b);
    }

    public void a(byte[] bArr, int i, int i2) {
        this.c.a(bArr, i, i2);
    }

    public byte[] a() {
        if (this.d) {
            byte[] bArr = new byte[this.c.b()];
            this.c.a(bArr, 0);
            try {
                bArr = b(bArr);
                return this.a.a(bArr, 0, bArr.length);
            } catch (Throwable e) {
                throw new CryptoException("unable to encode signature: " + e.getMessage(), e);
            }
        }
        throw new IllegalStateException("RSADigestSigner not initialised for signature generation.");
    }

    public boolean a(byte[] bArr) {
        if (this.d) {
            throw new IllegalStateException("RSADigestSigner not initialised for verification");
        }
        byte[] bArr2 = new byte[this.c.b()];
        this.c.a(bArr2, 0);
        try {
            byte[] a = this.a.a(bArr, 0, bArr.length);
            byte[] b = b(bArr2);
            if (a.length == b.length) {
                return Arrays.b(a, b);
            }
            if (a.length != b.length - 2) {
                return false;
            }
            int i;
            int length = (a.length - bArr2.length) - 2;
            int length2 = (b.length - bArr2.length) - 2;
            b[1] = (byte) (b[1] - 2);
            b[3] = (byte) (b[3] - 2);
            int i2 = 0;
            for (i = 0; i < bArr2.length; i++) {
                i2 |= a[length + i] ^ b[length2 + i];
            }
            for (i = 0; i < length; i++) {
                i2 |= a[i] ^ b[i];
            }
            if (i2 == 0) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public void b() {
        this.c.c();
    }

    private byte[] b(byte[] bArr) {
        return new DigestInfo(this.b, bArr).a("DER");
    }
}
