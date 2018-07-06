package org.spongycastle.jcajce.provider.asymmetric.rsa;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPrivateCrtKeySpec;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.pkcs.PrivateKeyInfo;
import org.spongycastle.asn1.x509.SubjectPublicKeyInfo;
import org.spongycastle.jcajce.provider.asymmetric.util.BaseKeyFactorySpi;
import org.spongycastle.jcajce.provider.asymmetric.util.ExtendedInvalidKeySpecException;

public class KeyFactorySpi extends BaseKeyFactorySpi {
    protected KeySpec engineGetKeySpec(Key key, Class cls) {
        if (cls.isAssignableFrom(RSAPublicKeySpec.class) && (key instanceof RSAPublicKey)) {
            RSAPublicKey rSAPublicKey = (RSAPublicKey) key;
            return new RSAPublicKeySpec(rSAPublicKey.getModulus(), rSAPublicKey.getPublicExponent());
        } else if (cls.isAssignableFrom(RSAPrivateKeySpec.class) && (key instanceof RSAPrivateKey)) {
            RSAPrivateKey rSAPrivateKey = (RSAPrivateKey) key;
            return new RSAPrivateKeySpec(rSAPrivateKey.getModulus(), rSAPrivateKey.getPrivateExponent());
        } else if (!cls.isAssignableFrom(RSAPrivateCrtKeySpec.class) || !(key instanceof RSAPrivateCrtKey)) {
            return super.engineGetKeySpec(key, cls);
        } else {
            RSAPrivateCrtKey rSAPrivateCrtKey = (RSAPrivateCrtKey) key;
            return new RSAPrivateCrtKeySpec(rSAPrivateCrtKey.getModulus(), rSAPrivateCrtKey.getPublicExponent(), rSAPrivateCrtKey.getPrivateExponent(), rSAPrivateCrtKey.getPrimeP(), rSAPrivateCrtKey.getPrimeQ(), rSAPrivateCrtKey.getPrimeExponentP(), rSAPrivateCrtKey.getPrimeExponentQ(), rSAPrivateCrtKey.getCrtCoefficient());
        }
    }

    protected Key engineTranslateKey(Key key) {
        if (key instanceof RSAPublicKey) {
            return new BCRSAPublicKey((RSAPublicKey) key);
        }
        if (key instanceof RSAPrivateCrtKey) {
            return new BCRSAPrivateCrtKey((RSAPrivateCrtKey) key);
        }
        if (key instanceof RSAPrivateKey) {
            return new BCRSAPrivateKey((RSAPrivateKey) key);
        }
        throw new InvalidKeyException("key type unknown");
    }

    protected PrivateKey engineGeneratePrivate(KeySpec keySpec) {
        if (keySpec instanceof PKCS8EncodedKeySpec) {
            try {
                return a(PrivateKeyInfo.a(((PKCS8EncodedKeySpec) keySpec).getEncoded()));
            } catch (Throwable e) {
                Throwable th = e;
                try {
                    return new BCRSAPrivateCrtKey(org.spongycastle.asn1.pkcs.RSAPrivateKey.a(((PKCS8EncodedKeySpec) keySpec).getEncoded()));
                } catch (Exception e2) {
                    throw new ExtendedInvalidKeySpecException("unable to process key spec: " + th.toString(), th);
                }
            }
        } else if (keySpec instanceof RSAPrivateCrtKeySpec) {
            return new BCRSAPrivateCrtKey((RSAPrivateCrtKeySpec) keySpec);
        } else {
            if (keySpec instanceof RSAPrivateKeySpec) {
                return new BCRSAPrivateKey((RSAPrivateKeySpec) keySpec);
            }
            throw new InvalidKeySpecException("Unknown KeySpec type: " + keySpec.getClass().getName());
        }
    }

    protected PublicKey engineGeneratePublic(KeySpec keySpec) {
        if (keySpec instanceof RSAPublicKeySpec) {
            return new BCRSAPublicKey((RSAPublicKeySpec) keySpec);
        }
        return super.engineGeneratePublic(keySpec);
    }

    public PrivateKey a(PrivateKeyInfo privateKeyInfo) {
        ASN1ObjectIdentifier e = privateKeyInfo.d().e();
        if (RSAUtil.a(e)) {
            return new BCRSAPrivateCrtKey(privateKeyInfo);
        }
        throw new IOException("algorithm identifier " + e + " in key not recognised");
    }

    public PublicKey a(SubjectPublicKeyInfo subjectPublicKeyInfo) {
        ASN1ObjectIdentifier e = subjectPublicKeyInfo.d().e();
        if (RSAUtil.a(e)) {
            return new BCRSAPublicKey(subjectPublicKeyInfo);
        }
        throw new IOException("algorithm identifier " + e + " in key not recognised");
    }
}
