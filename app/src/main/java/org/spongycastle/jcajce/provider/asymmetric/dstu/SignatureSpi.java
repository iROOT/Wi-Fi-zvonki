package org.spongycastle.jcajce.provider.asymmetric.dstu;

import android.support.v4.app.NotificationCompat;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SignatureException;
import java.security.spec.AlgorithmParameterSpec;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.spongycastle.asn1.x509.SubjectPublicKeyInfo;
import org.spongycastle.asn1.x509.X509ObjectIdentifiers;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DSA;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.digests.GOST3411Digest;
import org.spongycastle.crypto.params.ParametersWithRandom;
import org.spongycastle.crypto.signers.DSTU4145Signer;
import org.spongycastle.jcajce.provider.asymmetric.util.ECUtil;
import org.spongycastle.jce.interfaces.ECKey;
import org.spongycastle.jce.interfaces.ECPublicKey;
import org.spongycastle.jce.provider.BouncyCastleProvider;

public class SignatureSpi extends java.security.SignatureSpi implements PKCSObjectIdentifiers, X509ObjectIdentifiers {
    private static byte[] bF = new byte[]{(byte) 10, (byte) 9, (byte) 13, (byte) 6, (byte) 14, (byte) 11, (byte) 4, (byte) 5, (byte) 15, (byte) 1, (byte) 3, (byte) 12, (byte) 7, (byte) 0, (byte) 8, (byte) 2, (byte) 8, (byte) 0, (byte) 12, (byte) 4, (byte) 9, (byte) 6, (byte) 7, (byte) 11, (byte) 2, (byte) 3, (byte) 1, (byte) 15, (byte) 5, (byte) 14, (byte) 10, (byte) 13, (byte) 15, (byte) 6, (byte) 5, (byte) 8, (byte) 14, (byte) 11, (byte) 10, (byte) 4, (byte) 12, (byte) 0, (byte) 3, (byte) 7, (byte) 2, (byte) 9, (byte) 1, (byte) 13, (byte) 3, (byte) 8, (byte) 13, (byte) 9, (byte) 6, (byte) 11, (byte) 15, (byte) 0, (byte) 2, (byte) 5, (byte) 12, (byte) 10, (byte) 4, (byte) 14, (byte) 1, (byte) 7, (byte) 15, (byte) 8, (byte) 14, (byte) 9, (byte) 7, (byte) 2, (byte) 0, (byte) 13, (byte) 12, (byte) 6, (byte) 1, (byte) 5, (byte) 11, (byte) 4, (byte) 3, (byte) 10, (byte) 2, (byte) 8, (byte) 9, (byte) 7, (byte) 5, (byte) 15, (byte) 0, (byte) 11, (byte) 12, (byte) 1, (byte) 13, (byte) 14, (byte) 10, (byte) 3, (byte) 6, (byte) 4, (byte) 3, (byte) 8, (byte) 11, (byte) 5, (byte) 6, (byte) 4, (byte) 14, (byte) 10, (byte) 2, (byte) 12, (byte) 1, (byte) 7, (byte) 9, (byte) 15, (byte) 13, (byte) 0, (byte) 1, (byte) 2, (byte) 3, (byte) 14, (byte) 6, (byte) 13, (byte) 11, (byte) 8, (byte) 15, (byte) 10, (byte) 12, (byte) 5, (byte) 7, (byte) 9, (byte) 0, (byte) 4};
    private Digest bD;
    private DSA bE = new DSTU4145Signer();

    protected void engineInitVerify(PublicKey publicKey) {
        CipherParameters a;
        PublicKey publicKey2;
        if (publicKey instanceof ECPublicKey) {
            a = ECUtil.a(publicKey);
            publicKey2 = publicKey;
        } else {
            try {
                publicKey = BouncyCastleProvider.a(SubjectPublicKeyInfo.a(publicKey.getEncoded()));
                if (publicKey instanceof ECPublicKey) {
                    Object a2 = ECUtil.a(publicKey);
                    publicKey2 = publicKey;
                } else {
                    throw new InvalidKeyException("can't recognise key type in DSA based signer");
                }
            } catch (Exception e) {
                throw new InvalidKeyException("can't recognise key type in DSA based signer");
            }
        }
        this.bD = new GOST3411Digest(a(((BCDSTU4145PublicKey) publicKey2).a()));
        this.bE.a(false, a2);
    }

    byte[] a(byte[] bArr) {
        byte[] bArr2 = new byte[NotificationCompat.FLAG_HIGH_PRIORITY];
        for (int i = 0; i < bArr.length; i++) {
            bArr2[i * 2] = (byte) ((bArr[i] >> 4) & 15);
            bArr2[(i * 2) + 1] = (byte) (bArr[i] & 15);
        }
        return bArr2;
    }

    protected void engineInitSign(PrivateKey privateKey) {
        CipherParameters cipherParameters = null;
        if (privateKey instanceof ECKey) {
            cipherParameters = ECUtil.a(privateKey);
        }
        this.bD = new GOST3411Digest(bF);
        if (this.appRandom != null) {
            this.bE.a(true, new ParametersWithRandom(cipherParameters, this.appRandom));
        } else {
            this.bE.a(true, cipherParameters);
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
            BigInteger[] a = this.bE.a(bArr);
            Object toByteArray = a[0].toByteArray();
            Object toByteArray2 = a[1].toByteArray();
            Object obj = new byte[(toByteArray.length > toByteArray2.length ? toByteArray.length * 2 : toByteArray2.length * 2)];
            System.arraycopy(toByteArray2, 0, obj, (obj.length / 2) - toByteArray2.length, toByteArray2.length);
            System.arraycopy(toByteArray, 0, obj, obj.length - toByteArray.length, toByteArray.length);
            return new DEROctetString(obj).b();
        } catch (Exception e) {
            throw new SignatureException(e.toString());
        }
    }

    protected boolean engineVerify(byte[] bArr) {
        byte[] bArr2 = new byte[this.bD.b()];
        this.bD.a(bArr2, 0);
        try {
            Object e = ((ASN1OctetString) ASN1Primitive.a(bArr)).e();
            Object obj = new byte[(e.length / 2)];
            System.arraycopy(e, 0, new byte[(e.length / 2)], 0, e.length / 2);
            System.arraycopy(e, e.length / 2, obj, 0, e.length / 2);
            BigInteger[] bigIntegerArr = new BigInteger[]{new BigInteger(1, obj), new BigInteger(1, r3)};
            return this.bE.a(bArr2, bigIntegerArr[0], bigIntegerArr[1]);
        } catch (Exception e2) {
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
