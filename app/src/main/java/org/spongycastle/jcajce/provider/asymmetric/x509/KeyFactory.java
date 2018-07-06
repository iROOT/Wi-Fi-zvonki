package org.spongycastle.jcajce.provider.asymmetric.x509;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactorySpi;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import org.spongycastle.asn1.pkcs.PrivateKeyInfo;
import org.spongycastle.asn1.x509.SubjectPublicKeyInfo;
import org.spongycastle.jce.provider.BouncyCastleProvider;

public class KeyFactory extends KeyFactorySpi {
    protected PrivateKey engineGeneratePrivate(KeySpec keySpec) {
        if (keySpec instanceof PKCS8EncodedKeySpec) {
            try {
                PrivateKeyInfo a = PrivateKeyInfo.a(((PKCS8EncodedKeySpec) keySpec).getEncoded());
                PrivateKey a2 = BouncyCastleProvider.a(a);
                if (a2 != null) {
                    return a2;
                }
                throw new InvalidKeySpecException("no factory found for OID: " + a.d().e());
            } catch (Exception e) {
                throw new InvalidKeySpecException(e.toString());
            }
        }
        throw new InvalidKeySpecException("Unknown KeySpec type: " + keySpec.getClass().getName());
    }

    protected PublicKey engineGeneratePublic(KeySpec keySpec) {
        if (keySpec instanceof X509EncodedKeySpec) {
            try {
                SubjectPublicKeyInfo a = SubjectPublicKeyInfo.a(((X509EncodedKeySpec) keySpec).getEncoded());
                PublicKey a2 = BouncyCastleProvider.a(a);
                if (a2 != null) {
                    return a2;
                }
                throw new InvalidKeySpecException("no factory found for OID: " + a.d().e());
            } catch (Exception e) {
                throw new InvalidKeySpecException(e.toString());
            }
        }
        throw new InvalidKeySpecException("Unknown KeySpec type: " + keySpec.getClass().getName());
    }

    protected KeySpec engineGetKeySpec(Key key, Class cls) {
        if (cls.isAssignableFrom(PKCS8EncodedKeySpec.class) && key.getFormat().equals("PKCS#8")) {
            return new PKCS8EncodedKeySpec(key.getEncoded());
        }
        if (cls.isAssignableFrom(X509EncodedKeySpec.class) && key.getFormat().equals("X.509")) {
            return new X509EncodedKeySpec(key.getEncoded());
        }
        throw new InvalidKeySpecException("not implemented yet " + key + " " + cls);
    }

    protected Key engineTranslateKey(Key key) {
        throw new InvalidKeyException("not implemented yet " + key);
    }
}
