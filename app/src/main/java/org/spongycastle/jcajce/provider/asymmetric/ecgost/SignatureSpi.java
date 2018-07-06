package org.spongycastle.jcajce.provider.asymmetric.ecgost;

import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SignatureException;
import java.security.spec.AlgorithmParameterSpec;
import org.spongycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.spongycastle.asn1.x509.SubjectPublicKeyInfo;
import org.spongycastle.asn1.x509.X509ObjectIdentifiers;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DSA;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.digests.GOST3411Digest;
import org.spongycastle.crypto.params.ParametersWithRandom;
import org.spongycastle.crypto.signers.ECGOST3410Signer;
import org.spongycastle.jcajce.provider.asymmetric.util.ECUtil;
import org.spongycastle.jcajce.provider.asymmetric.util.GOST3410Util;
import org.spongycastle.jce.interfaces.ECKey;
import org.spongycastle.jce.interfaces.ECPublicKey;
import org.spongycastle.jce.interfaces.GOST3410Key;
import org.spongycastle.jce.provider.BouncyCastleProvider;

public class SignatureSpi extends java.security.SignatureSpi implements PKCSObjectIdentifiers, X509ObjectIdentifiers {
    private Digest bD = new GOST3411Digest();
    private DSA bE = new ECGOST3410Signer();

    protected void engineInitVerify(PublicKey publicKey) {
        CipherParameters a;
        if (publicKey instanceof ECPublicKey) {
            a = ECUtil.a(publicKey);
        } else if (publicKey instanceof GOST3410Key) {
            a = GOST3410Util.a(publicKey);
        } else {
            try {
                PublicKey a2 = BouncyCastleProvider.a(SubjectPublicKeyInfo.a(publicKey.getEncoded()));
                if (a2 instanceof ECPublicKey) {
                    a = ECUtil.a(a2);
                } else {
                    throw new InvalidKeyException("can't recognise key type in DSA based signer");
                }
            } catch (Exception e) {
                throw new InvalidKeyException("can't recognise key type in DSA based signer");
            }
        }
        this.bD.c();
        this.bE.a(false, a);
    }

    protected void engineInitSign(PrivateKey privateKey) {
        CipherParameters a;
        if (privateKey instanceof ECKey) {
            a = ECUtil.a(privateKey);
        } else {
            a = GOST3410Util.a(privateKey);
        }
        this.bD.c();
        if (this.appRandom != null) {
            this.bE.a(true, new ParametersWithRandom(a, this.appRandom));
        } else {
            this.bE.a(true, a);
        }
    }

    protected void engineUpdate(byte b) {
        this.bD.a(b);
    }

    protected void engineUpdate(byte[] bArr, int i, int i2) {
        this.bD.a(bArr, i, i2);
    }

    protected byte[] engineSign() {
        byte[] bArr = new byte[this.bD.b()];
        this.bD.a(bArr, 0);
        try {
            Object obj = new byte[64];
            BigInteger[] a = this.bE.a(bArr);
            Object toByteArray = a[0].toByteArray();
            Object toByteArray2 = a[1].toByteArray();
            if (toByteArray2[0] != (byte) 0) {
                System.arraycopy(toByteArray2, 0, obj, 32 - toByteArray2.length, toByteArray2.length);
            } else {
                System.arraycopy(toByteArray2, 1, obj, 32 - (toByteArray2.length - 1), toByteArray2.length - 1);
            }
            if (toByteArray[0] != (byte) 0) {
                System.arraycopy(toByteArray, 0, obj, 64 - toByteArray.length, toByteArray.length);
            } else {
                System.arraycopy(toByteArray, 1, obj, 64 - (toByteArray.length - 1), toByteArray.length - 1);
            }
            return obj;
        } catch (Exception e) {
            throw new SignatureException(e.toString());
        }
    }

    protected boolean engineVerify(byte[] bArr) {
        byte[] bArr2 = new byte[this.bD.b()];
        this.bD.a(bArr2, 0);
        try {
            Object obj = new byte[32];
            System.arraycopy(bArr, 0, new byte[32], 0, 32);
            System.arraycopy(bArr, 32, obj, 0, 32);
            BigInteger[] bigIntegerArr = new BigInteger[]{new BigInteger(1, obj), new BigInteger(1, r2)};
            return this.bE.a(bArr2, bigIntegerArr[0], bigIntegerArr[1]);
        } catch (Exception e) {
            throw new SignatureException("error decoding signature bytes.");
        }
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
