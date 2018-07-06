package org.spongycastle.jcajce.provider.symmetric.util;

import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactorySpi;
import javax.crypto.spec.SecretKeySpec;
import org.spongycastle.asn1.ASN1ObjectIdentifier;

public class BaseSecretKeyFactory extends SecretKeyFactorySpi implements PBE {
    protected String a;
    protected ASN1ObjectIdentifier b;

    protected BaseSecretKeyFactory(String str, ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        this.a = str;
        this.b = aSN1ObjectIdentifier;
    }

    protected SecretKey engineGenerateSecret(KeySpec keySpec) {
        if (keySpec instanceof SecretKeySpec) {
            return (SecretKey) keySpec;
        }
        throw new InvalidKeySpecException("Invalid KeySpec");
    }

    protected KeySpec engineGetKeySpec(SecretKey secretKey, Class cls) {
        if (cls == null) {
            throw new InvalidKeySpecException("keySpec parameter is null");
        } else if (secretKey == null) {
            throw new InvalidKeySpecException("key parameter is null");
        } else if (SecretKeySpec.class.isAssignableFrom(cls)) {
            return new SecretKeySpec(secretKey.getEncoded(), this.a);
        } else {
            try {
                return (KeySpec) cls.getConstructor(new Class[]{byte[].class}).newInstance(new Object[]{secretKey.getEncoded()});
            } catch (Exception e) {
                throw new InvalidKeySpecException(e.toString());
            }
        }
    }

    protected SecretKey engineTranslateKey(SecretKey secretKey) {
        if (secretKey == null) {
            throw new InvalidKeyException("key parameter is null");
        } else if (secretKey.getAlgorithm().equalsIgnoreCase(this.a)) {
            return new SecretKeySpec(secretKey.getEncoded(), this.a);
        } else {
            throw new InvalidKeyException("Key not of type " + this.a + ".");
        }
    }
}
