package org.spongycastle.jcajce.provider.asymmetric.ecgost;

import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.ECGenParameterSpec;
import org.spongycastle.asn1.cryptopro.ECGOST3410NamedCurves;
import org.spongycastle.crypto.AsymmetricCipherKeyPair;
import org.spongycastle.crypto.generators.ECKeyPairGenerator;
import org.spongycastle.crypto.params.ECDomainParameters;
import org.spongycastle.crypto.params.ECKeyGenerationParameters;
import org.spongycastle.crypto.params.ECPrivateKeyParameters;
import org.spongycastle.crypto.params.ECPublicKeyParameters;
import org.spongycastle.jcajce.provider.asymmetric.util.EC5Util;
import org.spongycastle.jce.provider.BouncyCastleProvider;
import org.spongycastle.jce.spec.ECNamedCurveGenParameterSpec;
import org.spongycastle.jce.spec.ECNamedCurveSpec;
import org.spongycastle.jce.spec.ECParameterSpec;

public class KeyPairGeneratorSpi extends KeyPairGenerator {
    Object a = null;
    ECKeyPairGenerator b = new ECKeyPairGenerator();
    String c = "ECGOST3410";
    ECKeyGenerationParameters d;
    int e = 239;
    SecureRandom f = null;
    boolean g = false;

    public KeyPairGeneratorSpi() {
        super("ECGOST3410");
    }

    public void initialize(int i, SecureRandom secureRandom) {
        this.e = i;
        this.f = secureRandom;
        if (this.a != null) {
            try {
                initialize((ECGenParameterSpec) this.a, secureRandom);
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
            this.a = algorithmParameterSpec;
            this.d = new ECKeyGenerationParameters(new ECDomainParameters(eCParameterSpec.b(), eCParameterSpec.c(), eCParameterSpec.d()), secureRandom);
            this.b.a(this.d);
            this.g = true;
        } else if (algorithmParameterSpec instanceof java.security.spec.ECParameterSpec) {
            r0 = (java.security.spec.ECParameterSpec) algorithmParameterSpec;
            this.a = algorithmParameterSpec;
            r1 = EC5Util.a(r0.getCurve());
            this.d = new ECKeyGenerationParameters(new ECDomainParameters(r1, EC5Util.a(r1, r0.getGenerator(), false), r0.getOrder(), BigInteger.valueOf((long) r0.getCofactor())), secureRandom);
            this.b.a(this.d);
            this.g = true;
        } else if ((algorithmParameterSpec instanceof ECGenParameterSpec) || (algorithmParameterSpec instanceof ECNamedCurveGenParameterSpec)) {
            String name;
            if (algorithmParameterSpec instanceof ECGenParameterSpec) {
                name = ((ECGenParameterSpec) algorithmParameterSpec).getName();
            } else {
                name = ((ECNamedCurveGenParameterSpec) algorithmParameterSpec).a();
            }
            ECDomainParameters a = ECGOST3410NamedCurves.a(name);
            if (a == null) {
                throw new InvalidAlgorithmParameterException("unknown curve name: " + name);
            }
            this.a = new ECNamedCurveSpec(name, a.a(), a.b(), a.c(), a.d(), a.e());
            r0 = (java.security.spec.ECParameterSpec) this.a;
            r1 = EC5Util.a(r0.getCurve());
            this.d = new ECKeyGenerationParameters(new ECDomainParameters(r1, EC5Util.a(r1, r0.getGenerator(), false), r0.getOrder(), BigInteger.valueOf((long) r0.getCofactor())), secureRandom);
            this.b.a(this.d);
            this.g = true;
        } else if (algorithmParameterSpec == null && BouncyCastleProvider.a.a() != null) {
            eCParameterSpec = BouncyCastleProvider.a.a();
            this.a = algorithmParameterSpec;
            this.d = new ECKeyGenerationParameters(new ECDomainParameters(eCParameterSpec.b(), eCParameterSpec.c(), eCParameterSpec.d()), secureRandom);
            this.b.a(this.d);
            this.g = true;
        } else if (algorithmParameterSpec == null && BouncyCastleProvider.a.a() == null) {
            throw new InvalidAlgorithmParameterException("null parameter passed but no implicitCA set");
        } else {
            throw new InvalidAlgorithmParameterException("parameter object not a ECParameterSpec: " + algorithmParameterSpec.getClass().getName());
        }
    }

    public KeyPair generateKeyPair() {
        if (this.g) {
            AsymmetricCipherKeyPair a = this.b.a();
            ECPublicKeyParameters eCPublicKeyParameters = (ECPublicKeyParameters) a.a();
            ECPrivateKeyParameters eCPrivateKeyParameters = (ECPrivateKeyParameters) a.b();
            BCECGOST3410PublicKey bCECGOST3410PublicKey;
            if (this.a instanceof ECParameterSpec) {
                ECParameterSpec eCParameterSpec = (ECParameterSpec) this.a;
                bCECGOST3410PublicKey = new BCECGOST3410PublicKey(this.c, eCPublicKeyParameters, eCParameterSpec);
                return new KeyPair(bCECGOST3410PublicKey, new BCECGOST3410PrivateKey(this.c, eCPrivateKeyParameters, bCECGOST3410PublicKey, eCParameterSpec));
            } else if (this.a == null) {
                return new KeyPair(new BCECGOST3410PublicKey(this.c, eCPublicKeyParameters), new BCECGOST3410PrivateKey(this.c, eCPrivateKeyParameters));
            } else {
                java.security.spec.ECParameterSpec eCParameterSpec2 = (java.security.spec.ECParameterSpec) this.a;
                bCECGOST3410PublicKey = new BCECGOST3410PublicKey(this.c, eCPublicKeyParameters, eCParameterSpec2);
                return new KeyPair(bCECGOST3410PublicKey, new BCECGOST3410PrivateKey(this.c, eCPrivateKeyParameters, bCECGOST3410PublicKey, eCParameterSpec2));
            }
        }
        throw new IllegalStateException("EC Key Pair Generator not initialised");
    }
}
