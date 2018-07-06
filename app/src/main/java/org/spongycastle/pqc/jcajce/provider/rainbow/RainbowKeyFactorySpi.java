package org.spongycastle.pqc.jcajce.provider.rainbow;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactorySpi;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.pkcs.PrivateKeyInfo;
import org.spongycastle.asn1.x509.SubjectPublicKeyInfo;
import org.spongycastle.jcajce.provider.util.AsymmetricKeyInfoConverter;
import org.spongycastle.pqc.asn1.RainbowPrivateKey;
import org.spongycastle.pqc.asn1.RainbowPublicKey;
import org.spongycastle.pqc.jcajce.spec.RainbowPrivateKeySpec;
import org.spongycastle.pqc.jcajce.spec.RainbowPublicKeySpec;

public class RainbowKeyFactorySpi extends KeyFactorySpi implements AsymmetricKeyInfoConverter {
    public PrivateKey engineGeneratePrivate(KeySpec keySpec) {
        if (keySpec instanceof RainbowPrivateKeySpec) {
            return new BCRainbowPrivateKey((RainbowPrivateKeySpec) keySpec);
        }
        if (keySpec instanceof PKCS8EncodedKeySpec) {
            try {
                return a(PrivateKeyInfo.a(ASN1Primitive.a(((PKCS8EncodedKeySpec) keySpec).getEncoded())));
            } catch (Exception e) {
                throw new InvalidKeySpecException(e.toString());
            }
        }
        throw new InvalidKeySpecException("Unsupported key specification: " + keySpec.getClass() + ".");
    }

    public PublicKey engineGeneratePublic(KeySpec keySpec) {
        if (keySpec instanceof RainbowPublicKeySpec) {
            return new BCRainbowPublicKey((RainbowPublicKeySpec) keySpec);
        }
        if (keySpec instanceof X509EncodedKeySpec) {
            try {
                return a(SubjectPublicKeyInfo.a(((X509EncodedKeySpec) keySpec).getEncoded()));
            } catch (Exception e) {
                throw new InvalidKeySpecException(e.toString());
            }
        }
        throw new InvalidKeySpecException("Unknown key specification: " + keySpec + ".");
    }

    public final KeySpec engineGetKeySpec(Key key, Class cls) {
        if (key instanceof BCRainbowPrivateKey) {
            if (PKCS8EncodedKeySpec.class.isAssignableFrom(cls)) {
                return new PKCS8EncodedKeySpec(key.getEncoded());
            }
            if (RainbowPrivateKeySpec.class.isAssignableFrom(cls)) {
                BCRainbowPrivateKey bCRainbowPrivateKey = (BCRainbowPrivateKey) key;
                return new RainbowPrivateKeySpec(bCRainbowPrivateKey.a(), bCRainbowPrivateKey.b(), bCRainbowPrivateKey.d(), bCRainbowPrivateKey.c(), bCRainbowPrivateKey.f(), bCRainbowPrivateKey.e());
            }
        } else if (!(key instanceof BCRainbowPublicKey)) {
            throw new InvalidKeySpecException("Unsupported key type: " + key.getClass() + ".");
        } else if (X509EncodedKeySpec.class.isAssignableFrom(cls)) {
            return new X509EncodedKeySpec(key.getEncoded());
        } else {
            if (RainbowPublicKeySpec.class.isAssignableFrom(cls)) {
                BCRainbowPublicKey bCRainbowPublicKey = (BCRainbowPublicKey) key;
                return new RainbowPublicKeySpec(bCRainbowPublicKey.a(), bCRainbowPublicKey.b(), bCRainbowPublicKey.c(), bCRainbowPublicKey.d());
            }
        }
        throw new InvalidKeySpecException("Unknown key specification: " + cls + ".");
    }

    public final Key engineTranslateKey(Key key) {
        if ((key instanceof BCRainbowPrivateKey) || (key instanceof BCRainbowPublicKey)) {
            return key;
        }
        throw new InvalidKeyException("Unsupported key type");
    }

    public PrivateKey a(PrivateKeyInfo privateKeyInfo) {
        RainbowPrivateKey a = RainbowPrivateKey.a(privateKeyInfo.f());
        return new BCRainbowPrivateKey(a.d(), a.e(), a.g(), a.f(), a.i(), a.h());
    }

    public PublicKey a(SubjectPublicKeyInfo subjectPublicKeyInfo) {
        RainbowPublicKey a = RainbowPublicKey.a(subjectPublicKeyInfo.f());
        return new BCRainbowPublicKey(a.d(), a.e(), a.f(), a.g());
    }
}
