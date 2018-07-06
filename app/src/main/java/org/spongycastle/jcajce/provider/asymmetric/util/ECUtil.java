package org.spongycastle.jcajce.provider.asymmetric.util;

import java.security.InvalidKeyException;
import java.security.PrivateKey;
import java.security.PublicKey;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.cryptopro.ECGOST3410NamedCurves;
import org.spongycastle.asn1.nist.NISTNamedCurves;
import org.spongycastle.asn1.pkcs.PrivateKeyInfo;
import org.spongycastle.asn1.sec.SECNamedCurves;
import org.spongycastle.asn1.teletrust.TeleTrusTNamedCurves;
import org.spongycastle.asn1.x509.SubjectPublicKeyInfo;
import org.spongycastle.asn1.x9.X962NamedCurves;
import org.spongycastle.asn1.x9.X9ECParameters;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.crypto.params.ECDomainParameters;
import org.spongycastle.crypto.params.ECPrivateKeyParameters;
import org.spongycastle.crypto.params.ECPublicKeyParameters;
import org.spongycastle.jcajce.provider.asymmetric.ec.BCECPublicKey;
import org.spongycastle.jce.interfaces.ECPrivateKey;
import org.spongycastle.jce.interfaces.ECPublicKey;
import org.spongycastle.jce.provider.BouncyCastleProvider;
import org.spongycastle.jce.spec.ECParameterSpec;

public class ECUtil {
    static int[] a(int[] iArr) {
        int[] iArr2 = new int[3];
        if (iArr.length == 1) {
            iArr2[0] = iArr[0];
        } else if (iArr.length != 3) {
            throw new IllegalArgumentException("Only Trinomials and pentanomials supported");
        } else if (iArr[0] < iArr[1] && iArr[0] < iArr[2]) {
            iArr2[0] = iArr[0];
            if (iArr[1] < iArr[2]) {
                iArr2[1] = iArr[1];
                iArr2[2] = iArr[2];
            } else {
                iArr2[1] = iArr[2];
                iArr2[2] = iArr[1];
            }
        } else if (iArr[1] < iArr[2]) {
            iArr2[0] = iArr[1];
            if (iArr[0] < iArr[2]) {
                iArr2[1] = iArr[0];
                iArr2[2] = iArr[2];
            } else {
                iArr2[1] = iArr[2];
                iArr2[2] = iArr[0];
            }
        } else {
            iArr2[0] = iArr[2];
            if (iArr[0] < iArr[1]) {
                iArr2[1] = iArr[0];
                iArr2[2] = iArr[1];
            } else {
                iArr2[1] = iArr[1];
                iArr2[2] = iArr[0];
            }
        }
        return iArr2;
    }

    public static AsymmetricKeyParameter a(PublicKey publicKey) {
        ECParameterSpec b;
        if (publicKey instanceof ECPublicKey) {
            ECPublicKey eCPublicKey = (ECPublicKey) publicKey;
            b = eCPublicKey.b();
            if (b != null) {
                return new ECPublicKeyParameters(eCPublicKey.c(), new ECDomainParameters(b.b(), b.c(), b.d(), b.e(), b.f()));
            }
            b = BouncyCastleProvider.a.a();
            return new ECPublicKeyParameters(((BCECPublicKey) eCPublicKey).a(), new ECDomainParameters(b.b(), b.c(), b.d(), b.e(), b.f()));
        } else if (publicKey instanceof java.security.interfaces.ECPublicKey) {
            java.security.interfaces.ECPublicKey eCPublicKey2 = (java.security.interfaces.ECPublicKey) publicKey;
            b = EC5Util.a(eCPublicKey2.getParams(), false);
            return new ECPublicKeyParameters(EC5Util.a(eCPublicKey2.getParams(), eCPublicKey2.getW(), false), new ECDomainParameters(b.b(), b.c(), b.d(), b.e(), b.f()));
        } else {
            try {
                Object encoded = publicKey.getEncoded();
                if (encoded == null) {
                    throw new InvalidKeyException("no encoding for EC public key");
                }
                PublicKey a = BouncyCastleProvider.a(SubjectPublicKeyInfo.a(encoded));
                if (a instanceof java.security.interfaces.ECPublicKey) {
                    return a(a);
                }
                throw new InvalidKeyException("cannot identify EC public key.");
            } catch (Exception e) {
                throw new InvalidKeyException("cannot identify EC public key: " + e.toString());
            }
        }
    }

    public static AsymmetricKeyParameter a(PrivateKey privateKey) {
        ECParameterSpec a;
        if (privateKey instanceof ECPrivateKey) {
            ECPrivateKey eCPrivateKey = (ECPrivateKey) privateKey;
            ECParameterSpec b = eCPrivateKey.b();
            if (b == null) {
                a = BouncyCastleProvider.a.a();
            } else {
                a = b;
            }
            return new ECPrivateKeyParameters(eCPrivateKey.d(), new ECDomainParameters(a.b(), a.c(), a.d(), a.e(), a.f()));
        } else if (privateKey instanceof java.security.interfaces.ECPrivateKey) {
            java.security.interfaces.ECPrivateKey eCPrivateKey2 = (java.security.interfaces.ECPrivateKey) privateKey;
            a = EC5Util.a(eCPrivateKey2.getParams(), false);
            return new ECPrivateKeyParameters(eCPrivateKey2.getS(), new ECDomainParameters(a.b(), a.c(), a.d(), a.e(), a.f()));
        } else {
            try {
                Object encoded = privateKey.getEncoded();
                if (encoded == null) {
                    throw new InvalidKeyException("no encoding for EC private key");
                }
                PrivateKey a2 = BouncyCastleProvider.a(PrivateKeyInfo.a(encoded));
                if (a2 instanceof java.security.interfaces.ECPrivateKey) {
                    return a(a2);
                }
                throw new InvalidKeyException("can't identify EC private key.");
            } catch (Exception e) {
                throw new InvalidKeyException("cannot identify EC private key: " + e.toString());
            }
        }
    }

    public static ASN1ObjectIdentifier a(String str) {
        ASN1ObjectIdentifier b = X962NamedCurves.b(str);
        if (b != null) {
            return b;
        }
        b = SECNamedCurves.b(str);
        if (b == null) {
            b = NISTNamedCurves.b(str);
        }
        if (b == null) {
            b = TeleTrusTNamedCurves.b(str);
        }
        if (b == null) {
            return ECGOST3410NamedCurves.b(str);
        }
        return b;
    }

    public static X9ECParameters a(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        X9ECParameters a = X962NamedCurves.a(aSN1ObjectIdentifier);
        if (a != null) {
            return a;
        }
        a = SECNamedCurves.a(aSN1ObjectIdentifier);
        if (a == null) {
            a = NISTNamedCurves.a(aSN1ObjectIdentifier);
        }
        if (a == null) {
            return TeleTrusTNamedCurves.a(aSN1ObjectIdentifier);
        }
        return a;
    }

    public static String b(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        String b = X962NamedCurves.b(aSN1ObjectIdentifier);
        if (b != null) {
            return b;
        }
        b = SECNamedCurves.b(aSN1ObjectIdentifier);
        if (b == null) {
            b = NISTNamedCurves.b(aSN1ObjectIdentifier);
        }
        if (b == null) {
            b = TeleTrusTNamedCurves.b(aSN1ObjectIdentifier);
        }
        if (b == null) {
            return ECGOST3410NamedCurves.b(aSN1ObjectIdentifier);
        }
        return b;
    }
}
