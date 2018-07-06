package org.spongycastle.jcajce.provider.asymmetric.dsa;

import java.security.InvalidKeyException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.DSAPrivateKey;
import java.security.interfaces.DSAPublicKey;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.oiw.OIWObjectIdentifiers;
import org.spongycastle.asn1.x9.X9ObjectIdentifiers;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.crypto.params.DSAParameters;
import org.spongycastle.crypto.params.DSAPrivateKeyParameters;
import org.spongycastle.crypto.params.DSAPublicKeyParameters;

public class DSAUtil {
    public static final ASN1ObjectIdentifier[] a = new ASN1ObjectIdentifier[]{X9ObjectIdentifiers.U, OIWObjectIdentifiers.j};

    public static boolean a(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        for (int i = 0; i != a.length; i++) {
            if (aSN1ObjectIdentifier.equals(a[i])) {
                return true;
            }
        }
        return false;
    }

    public static AsymmetricKeyParameter a(PublicKey publicKey) {
        if (publicKey instanceof DSAPublicKey) {
            DSAPublicKey dSAPublicKey = (DSAPublicKey) publicKey;
            return new DSAPublicKeyParameters(dSAPublicKey.getY(), new DSAParameters(dSAPublicKey.getParams().getP(), dSAPublicKey.getParams().getQ(), dSAPublicKey.getParams().getG()));
        }
        throw new InvalidKeyException("can't identify DSA public key: " + publicKey.getClass().getName());
    }

    public static AsymmetricKeyParameter a(PrivateKey privateKey) {
        if (privateKey instanceof DSAPrivateKey) {
            DSAPrivateKey dSAPrivateKey = (DSAPrivateKey) privateKey;
            return new DSAPrivateKeyParameters(dSAPrivateKey.getX(), new DSAParameters(dSAPrivateKey.getParams().getP(), dSAPrivateKey.getParams().getQ(), dSAPrivateKey.getParams().getG()));
        }
        throw new InvalidKeyException("can't identify DSA private key.");
    }
}
