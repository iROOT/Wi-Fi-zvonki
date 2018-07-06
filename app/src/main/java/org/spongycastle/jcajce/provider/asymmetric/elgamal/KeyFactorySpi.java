package org.spongycastle.jcajce.provider.asymmetric.elgamal;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.KeySpec;
import javax.crypto.interfaces.DHPrivateKey;
import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.DHPrivateKeySpec;
import javax.crypto.spec.DHPublicKeySpec;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.oiw.OIWObjectIdentifiers;
import org.spongycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.spongycastle.asn1.pkcs.PrivateKeyInfo;
import org.spongycastle.asn1.x509.SubjectPublicKeyInfo;
import org.spongycastle.asn1.x9.X9ObjectIdentifiers;
import org.spongycastle.jcajce.provider.asymmetric.util.BaseKeyFactorySpi;
import org.spongycastle.jce.interfaces.ElGamalPrivateKey;
import org.spongycastle.jce.interfaces.ElGamalPublicKey;
import org.spongycastle.jce.spec.ElGamalPrivateKeySpec;
import org.spongycastle.jce.spec.ElGamalPublicKeySpec;

public class KeyFactorySpi extends BaseKeyFactorySpi {
    protected PrivateKey engineGeneratePrivate(KeySpec keySpec) {
        if (keySpec instanceof ElGamalPrivateKeySpec) {
            return new BCElGamalPrivateKey((ElGamalPrivateKeySpec) keySpec);
        }
        if (keySpec instanceof DHPrivateKeySpec) {
            return new BCElGamalPrivateKey((DHPrivateKeySpec) keySpec);
        }
        return super.engineGeneratePrivate(keySpec);
    }

    protected PublicKey engineGeneratePublic(KeySpec keySpec) {
        if (keySpec instanceof ElGamalPublicKeySpec) {
            return new BCElGamalPublicKey((ElGamalPublicKeySpec) keySpec);
        }
        if (keySpec instanceof DHPublicKeySpec) {
            return new BCElGamalPublicKey((DHPublicKeySpec) keySpec);
        }
        return super.engineGeneratePublic(keySpec);
    }

    protected KeySpec engineGetKeySpec(Key key, Class cls) {
        if (cls.isAssignableFrom(DHPrivateKeySpec.class) && (key instanceof DHPrivateKey)) {
            DHPrivateKey dHPrivateKey = (DHPrivateKey) key;
            return new DHPrivateKeySpec(dHPrivateKey.getX(), dHPrivateKey.getParams().getP(), dHPrivateKey.getParams().getG());
        } else if (!cls.isAssignableFrom(DHPublicKeySpec.class) || !(key instanceof DHPublicKey)) {
            return super.engineGetKeySpec(key, cls);
        } else {
            DHPublicKey dHPublicKey = (DHPublicKey) key;
            return new DHPublicKeySpec(dHPublicKey.getY(), dHPublicKey.getParams().getP(), dHPublicKey.getParams().getG());
        }
    }

    protected Key engineTranslateKey(Key key) {
        if (key instanceof DHPublicKey) {
            return new BCElGamalPublicKey((DHPublicKey) key);
        }
        if (key instanceof DHPrivateKey) {
            return new BCElGamalPrivateKey((DHPrivateKey) key);
        }
        if (key instanceof ElGamalPublicKey) {
            return new BCElGamalPublicKey((ElGamalPublicKey) key);
        }
        if (key instanceof ElGamalPrivateKey) {
            return new BCElGamalPrivateKey((ElGamalPrivateKey) key);
        }
        throw new InvalidKeyException("key type unknown");
    }

    public PrivateKey a(PrivateKeyInfo privateKeyInfo) {
        ASN1ObjectIdentifier e = privateKeyInfo.d().e();
        if (e.equals(PKCSObjectIdentifiers.q)) {
            return new BCElGamalPrivateKey(privateKeyInfo);
        }
        if (e.equals(X9ObjectIdentifiers.ab)) {
            return new BCElGamalPrivateKey(privateKeyInfo);
        }
        if (e.equals(OIWObjectIdentifiers.l)) {
            return new BCElGamalPrivateKey(privateKeyInfo);
        }
        throw new IOException("algorithm identifier " + e + " in key not recognised");
    }

    public PublicKey a(SubjectPublicKeyInfo subjectPublicKeyInfo) {
        ASN1ObjectIdentifier e = subjectPublicKeyInfo.d().e();
        if (e.equals(PKCSObjectIdentifiers.q)) {
            return new BCElGamalPublicKey(subjectPublicKeyInfo);
        }
        if (e.equals(X9ObjectIdentifiers.ab)) {
            return new BCElGamalPublicKey(subjectPublicKeyInfo);
        }
        if (e.equals(OIWObjectIdentifiers.l)) {
            return new BCElGamalPublicKey(subjectPublicKeyInfo);
        }
        throw new IOException("algorithm identifier " + e + " in key not recognised");
    }
}
