package org.spongycastle.jcajce.provider.asymmetric.rsa;

import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.spongycastle.asn1.x509.X509ObjectIdentifiers;
import org.spongycastle.crypto.params.RSAKeyParameters;
import org.spongycastle.crypto.params.RSAPrivateCrtKeyParameters;

public class RSAUtil {
    public static final ASN1ObjectIdentifier[] a = new ASN1ObjectIdentifier[]{PKCSObjectIdentifiers.h_, X509ObjectIdentifiers.l, PKCSObjectIdentifiers.h, PKCSObjectIdentifiers.k};

    public static boolean a(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        for (int i = 0; i != a.length; i++) {
            if (aSN1ObjectIdentifier.equals(a[i])) {
                return true;
            }
        }
        return false;
    }

    static RSAKeyParameters a(RSAPublicKey rSAPublicKey) {
        return new RSAKeyParameters(false, rSAPublicKey.getModulus(), rSAPublicKey.getPublicExponent());
    }

    static RSAKeyParameters a(RSAPrivateKey rSAPrivateKey) {
        if (!(rSAPrivateKey instanceof RSAPrivateCrtKey)) {
            return new RSAKeyParameters(true, rSAPrivateKey.getModulus(), rSAPrivateKey.getPrivateExponent());
        }
        RSAPrivateCrtKey rSAPrivateCrtKey = (RSAPrivateCrtKey) rSAPrivateKey;
        return new RSAPrivateCrtKeyParameters(rSAPrivateCrtKey.getModulus(), rSAPrivateCrtKey.getPublicExponent(), rSAPrivateCrtKey.getPrivateExponent(), rSAPrivateCrtKey.getPrimeP(), rSAPrivateCrtKey.getPrimeQ(), rSAPrivateCrtKey.getPrimeExponentP(), rSAPrivateCrtKey.getPrimeExponentQ(), rSAPrivateCrtKey.getCrtCoefficient());
    }
}
