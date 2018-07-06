package org.spongycastle.jcajce.provider.asymmetric.ec;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECPrivateKeySpec;
import java.security.spec.ECPublicKeySpec;
import java.security.spec.KeySpec;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.pkcs.PrivateKeyInfo;
import org.spongycastle.asn1.x509.SubjectPublicKeyInfo;
import org.spongycastle.asn1.x9.X9ObjectIdentifiers;
import org.spongycastle.jcajce.provider.asymmetric.util.BaseKeyFactorySpi;
import org.spongycastle.jcajce.provider.asymmetric.util.EC5Util;
import org.spongycastle.jcajce.provider.config.ProviderConfiguration;
import org.spongycastle.jcajce.provider.util.AsymmetricKeyInfoConverter;
import org.spongycastle.jce.provider.BouncyCastleProvider;
import org.spongycastle.jce.spec.ECParameterSpec;

public class KeyFactorySpi extends BaseKeyFactorySpi implements AsymmetricKeyInfoConverter {
    String a;
    ProviderConfiguration b;

    public static class EC extends KeyFactorySpi {
        public EC() {
            super("EC", BouncyCastleProvider.a);
        }
    }

    public static class ECDH extends KeyFactorySpi {
        public ECDH() {
            super("ECDH", BouncyCastleProvider.a);
        }
    }

    public static class ECDHC extends KeyFactorySpi {
        public ECDHC() {
            super("ECDHC", BouncyCastleProvider.a);
        }
    }

    public static class ECDSA extends KeyFactorySpi {
        public ECDSA() {
            super("ECDSA", BouncyCastleProvider.a);
        }
    }

    public static class ECGOST3410 extends KeyFactorySpi {
        public ECGOST3410() {
            super("ECGOST3410", BouncyCastleProvider.a);
        }
    }

    public static class ECMQV extends KeyFactorySpi {
        public ECMQV() {
            super("ECMQV", BouncyCastleProvider.a);
        }
    }

    KeyFactorySpi(String str, ProviderConfiguration providerConfiguration) {
        this.a = str;
        this.b = providerConfiguration;
    }

    protected Key engineTranslateKey(Key key) {
        if (key instanceof ECPublicKey) {
            return new BCECPublicKey((ECPublicKey) key, this.b);
        }
        if (key instanceof ECPrivateKey) {
            return new BCECPrivateKey((ECPrivateKey) key, this.b);
        }
        throw new InvalidKeyException("key type unknown");
    }

    protected KeySpec engineGetKeySpec(Key key, Class cls) {
        ECPublicKey eCPublicKey;
        ECParameterSpec a;
        if (cls.isAssignableFrom(ECPublicKeySpec.class) && (key instanceof ECPublicKey)) {
            eCPublicKey = (ECPublicKey) key;
            if (eCPublicKey.getParams() != null) {
                return new ECPublicKeySpec(eCPublicKey.getW(), eCPublicKey.getParams());
            }
            a = BouncyCastleProvider.a.a();
            return new ECPublicKeySpec(eCPublicKey.getW(), EC5Util.a(EC5Util.a(a.b(), a.f()), a));
        } else if (cls.isAssignableFrom(ECPrivateKeySpec.class) && (key instanceof ECPrivateKey)) {
            r6 = (ECPrivateKey) key;
            if (r6.getParams() != null) {
                return new ECPrivateKeySpec(r6.getS(), r6.getParams());
            }
            a = BouncyCastleProvider.a.a();
            return new ECPrivateKeySpec(r6.getS(), EC5Util.a(EC5Util.a(a.b(), a.f()), a));
        } else if (cls.isAssignableFrom(org.spongycastle.jce.spec.ECPublicKeySpec.class) && (key instanceof ECPublicKey)) {
            eCPublicKey = (ECPublicKey) key;
            if (eCPublicKey.getParams() != null) {
                return new org.spongycastle.jce.spec.ECPublicKeySpec(EC5Util.a(eCPublicKey.getParams(), eCPublicKey.getW(), false), EC5Util.a(eCPublicKey.getParams(), false));
            }
            return new org.spongycastle.jce.spec.ECPublicKeySpec(EC5Util.a(eCPublicKey.getParams(), eCPublicKey.getW(), false), BouncyCastleProvider.a.a());
        } else if (!cls.isAssignableFrom(org.spongycastle.jce.spec.ECPrivateKeySpec.class) || !(key instanceof ECPrivateKey)) {
            return super.engineGetKeySpec(key, cls);
        } else {
            r6 = (ECPrivateKey) key;
            if (r6.getParams() != null) {
                return new org.spongycastle.jce.spec.ECPrivateKeySpec(r6.getS(), EC5Util.a(r6.getParams(), false));
            }
            return new org.spongycastle.jce.spec.ECPrivateKeySpec(r6.getS(), BouncyCastleProvider.a.a());
        }
    }

    protected PrivateKey engineGeneratePrivate(KeySpec keySpec) {
        if (keySpec instanceof org.spongycastle.jce.spec.ECPrivateKeySpec) {
            return new BCECPrivateKey(this.a, (org.spongycastle.jce.spec.ECPrivateKeySpec) keySpec, this.b);
        }
        if (keySpec instanceof ECPrivateKeySpec) {
            return new BCECPrivateKey(this.a, (ECPrivateKeySpec) keySpec, this.b);
        }
        return super.engineGeneratePrivate(keySpec);
    }

    protected PublicKey engineGeneratePublic(KeySpec keySpec) {
        if (keySpec instanceof org.spongycastle.jce.spec.ECPublicKeySpec) {
            return new BCECPublicKey(this.a, (org.spongycastle.jce.spec.ECPublicKeySpec) keySpec, this.b);
        }
        if (keySpec instanceof ECPublicKeySpec) {
            return new BCECPublicKey(this.a, (ECPublicKeySpec) keySpec, this.b);
        }
        return super.engineGeneratePublic(keySpec);
    }

    public PrivateKey a(PrivateKeyInfo privateKeyInfo) {
        ASN1ObjectIdentifier e = privateKeyInfo.d().e();
        if (e.equals(X9ObjectIdentifiers.k)) {
            return new BCECPrivateKey(this.a, privateKeyInfo, this.b);
        }
        throw new IOException("algorithm identifier " + e + " in key not recognised");
    }

    public PublicKey a(SubjectPublicKeyInfo subjectPublicKeyInfo) {
        ASN1ObjectIdentifier e = subjectPublicKeyInfo.d().e();
        if (e.equals(X9ObjectIdentifiers.k)) {
            return new BCECPublicKey(this.a, subjectPublicKeyInfo, this.b);
        }
        throw new IOException("algorithm identifier " + e + " in key not recognised");
    }
}
