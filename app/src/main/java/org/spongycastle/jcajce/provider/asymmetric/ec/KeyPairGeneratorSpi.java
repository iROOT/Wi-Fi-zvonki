package org.spongycastle.jcajce.provider.asymmetric.ec;

import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.ECGenParameterSpec;
import java.util.Hashtable;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.x9.ECNamedCurveTable;
import org.spongycastle.asn1.x9.X9ECParameters;
import org.spongycastle.crypto.AsymmetricCipherKeyPair;
import org.spongycastle.crypto.generators.ECKeyPairGenerator;
import org.spongycastle.crypto.params.ECDomainParameters;
import org.spongycastle.crypto.params.ECKeyGenerationParameters;
import org.spongycastle.crypto.params.ECPrivateKeyParameters;
import org.spongycastle.crypto.params.ECPublicKeyParameters;
import org.spongycastle.jcajce.provider.asymmetric.util.EC5Util;
import org.spongycastle.jcajce.provider.config.ProviderConfiguration;
import org.spongycastle.jce.provider.BouncyCastleProvider;
import org.spongycastle.jce.spec.ECNamedCurveGenParameterSpec;
import org.spongycastle.jce.spec.ECNamedCurveSpec;
import org.spongycastle.jce.spec.ECParameterSpec;
import org.spongycastle.util.Integers;

public abstract class KeyPairGeneratorSpi extends KeyPairGenerator {

    public static class EC extends KeyPairGeneratorSpi {
        private static Hashtable j = new Hashtable();
        ECKeyGenerationParameters a;
        ECKeyPairGenerator b;
        Object c;
        int d;
        int e;
        SecureRandom f;
        boolean g;
        String h;
        ProviderConfiguration i;

        static {
            j.put(Integers.a(192), new ECGenParameterSpec("prime192v1"));
            j.put(Integers.a(239), new ECGenParameterSpec("prime239v1"));
            j.put(Integers.a(256), new ECGenParameterSpec("prime256v1"));
            j.put(Integers.a(224), new ECGenParameterSpec("P-224"));
            j.put(Integers.a(384), new ECGenParameterSpec("P-384"));
            j.put(Integers.a(521), new ECGenParameterSpec("P-521"));
        }

        public EC() {
            super("EC");
            this.b = new ECKeyPairGenerator();
            this.c = null;
            this.d = 239;
            this.e = 50;
            this.f = new SecureRandom();
            this.g = false;
            this.h = "EC";
            this.i = BouncyCastleProvider.a;
        }

        public EC(String str, ProviderConfiguration providerConfiguration) {
            super(str);
            this.b = new ECKeyPairGenerator();
            this.c = null;
            this.d = 239;
            this.e = 50;
            this.f = new SecureRandom();
            this.g = false;
            this.h = str;
            this.i = providerConfiguration;
        }

        public void initialize(int i, SecureRandom secureRandom) {
            this.d = i;
            this.f = secureRandom;
            AlgorithmParameterSpec algorithmParameterSpec = (ECGenParameterSpec) j.get(Integers.a(i));
            if (algorithmParameterSpec != null) {
                try {
                    initialize(algorithmParameterSpec, secureRandom);
                    return;
                } catch (InvalidAlgorithmParameterException e) {
                    throw new InvalidParameterException("key size not configurable.");
                }
            }
            throw new InvalidParameterException("unknown key size.");
        }

        public void initialize(AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) {
            ECParameterSpec eCParameterSpec;
            if (algorithmParameterSpec instanceof ECParameterSpec) {
                eCParameterSpec = (ECParameterSpec) algorithmParameterSpec;
                this.c = algorithmParameterSpec;
                this.a = new ECKeyGenerationParameters(new ECDomainParameters(eCParameterSpec.b(), eCParameterSpec.c(), eCParameterSpec.d()), secureRandom);
                this.b.a(this.a);
                this.g = true;
            } else if (algorithmParameterSpec instanceof java.security.spec.ECParameterSpec) {
                r0 = (java.security.spec.ECParameterSpec) algorithmParameterSpec;
                this.c = algorithmParameterSpec;
                r1 = EC5Util.a(r0.getCurve());
                this.a = new ECKeyGenerationParameters(new ECDomainParameters(r1, EC5Util.a(r1, r0.getGenerator(), false), r0.getOrder(), BigInteger.valueOf((long) r0.getCofactor())), secureRandom);
                this.b.a(this.a);
                this.g = true;
            } else if ((algorithmParameterSpec instanceof ECGenParameterSpec) || (algorithmParameterSpec instanceof ECNamedCurveGenParameterSpec)) {
                String name;
                X9ECParameters x9ECParameters;
                if (algorithmParameterSpec instanceof ECGenParameterSpec) {
                    name = ((ECGenParameterSpec) algorithmParameterSpec).getName();
                } else {
                    name = ((ECNamedCurveGenParameterSpec) algorithmParameterSpec).a();
                }
                X9ECParameters a = ECNamedCurveTable.a(name);
                if (a == null) {
                    try {
                        a = ECNamedCurveTable.a(new ASN1ObjectIdentifier(name));
                        if (a == null) {
                            throw new InvalidAlgorithmParameterException("unknown curve OID: " + name);
                        }
                        x9ECParameters = a;
                    } catch (IllegalArgumentException e) {
                        throw new InvalidAlgorithmParameterException("unknown curve name: " + name);
                    }
                }
                x9ECParameters = a;
                this.c = new ECNamedCurveSpec(name, x9ECParameters.d(), x9ECParameters.e(), x9ECParameters.f(), x9ECParameters.g(), null);
                r0 = (java.security.spec.ECParameterSpec) this.c;
                r1 = EC5Util.a(r0.getCurve());
                this.a = new ECKeyGenerationParameters(new ECDomainParameters(r1, EC5Util.a(r1, r0.getGenerator(), false), r0.getOrder(), BigInteger.valueOf((long) r0.getCofactor())), secureRandom);
                this.b.a(this.a);
                this.g = true;
            } else if (algorithmParameterSpec == null && this.i.a() != null) {
                eCParameterSpec = this.i.a();
                this.c = algorithmParameterSpec;
                this.a = new ECKeyGenerationParameters(new ECDomainParameters(eCParameterSpec.b(), eCParameterSpec.c(), eCParameterSpec.d()), secureRandom);
                this.b.a(this.a);
                this.g = true;
            } else if (algorithmParameterSpec == null && this.i.a() == null) {
                throw new InvalidAlgorithmParameterException("null parameter passed but no implicitCA set");
            } else {
                throw new InvalidAlgorithmParameterException("parameter object not a ECParameterSpec");
            }
        }

        public KeyPair generateKeyPair() {
            if (!this.g) {
                initialize(this.d, new SecureRandom());
            }
            AsymmetricCipherKeyPair a = this.b.a();
            ECPublicKeyParameters eCPublicKeyParameters = (ECPublicKeyParameters) a.a();
            ECPrivateKeyParameters eCPrivateKeyParameters = (ECPrivateKeyParameters) a.b();
            BCECPublicKey bCECPublicKey;
            if (this.c instanceof ECParameterSpec) {
                ECParameterSpec eCParameterSpec = (ECParameterSpec) this.c;
                bCECPublicKey = new BCECPublicKey(this.h, eCPublicKeyParameters, eCParameterSpec, this.i);
                return new KeyPair(bCECPublicKey, new BCECPrivateKey(this.h, eCPrivateKeyParameters, bCECPublicKey, eCParameterSpec, this.i));
            } else if (this.c == null) {
                return new KeyPair(new BCECPublicKey(this.h, eCPublicKeyParameters, this.i), new BCECPrivateKey(this.h, eCPrivateKeyParameters, this.i));
            } else {
                java.security.spec.ECParameterSpec eCParameterSpec2 = (java.security.spec.ECParameterSpec) this.c;
                bCECPublicKey = new BCECPublicKey(this.h, eCPublicKeyParameters, eCParameterSpec2, this.i);
                return new KeyPair(bCECPublicKey, new BCECPrivateKey(this.h, eCPrivateKeyParameters, bCECPublicKey, eCParameterSpec2, this.i));
            }
        }
    }

    public static class ECDH extends EC {
        public ECDH() {
            super("ECDH", BouncyCastleProvider.a);
        }
    }

    public static class ECDHC extends EC {
        public ECDHC() {
            super("ECDHC", BouncyCastleProvider.a);
        }
    }

    public static class ECDSA extends EC {
        public ECDSA() {
            super("ECDSA", BouncyCastleProvider.a);
        }
    }

    public static class ECMQV extends EC {
        public ECMQV() {
            super("ECMQV", BouncyCastleProvider.a);
        }
    }

    public KeyPairGeneratorSpi(String str) {
        super(str);
    }
}
