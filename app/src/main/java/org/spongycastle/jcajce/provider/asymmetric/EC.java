package org.spongycastle.jcajce.provider.asymmetric;

import org.spongycastle.asn1.eac.EACObjectIdentifiers;
import org.spongycastle.asn1.teletrust.TeleTrusTObjectIdentifiers;
import org.spongycastle.asn1.x9.X9ObjectIdentifiers;
import org.spongycastle.jcajce.provider.asymmetric.ec.KeyFactorySpi.ECMQV;
import org.spongycastle.jcajce.provider.config.ConfigurableProvider;
import org.spongycastle.jcajce.provider.util.AsymmetricAlgorithmProvider;

public class EC {

    public static class Mappings extends AsymmetricAlgorithmProvider {
        public void a(ConfigurableProvider configurableProvider) {
            configurableProvider.a("KeyAgreement.ECDH", "org.spongycastle.jcajce.provider.asymmetric.ec.KeyAgreementSpi$DH");
            configurableProvider.a("KeyAgreement.ECDHC", "org.spongycastle.jcajce.provider.asymmetric.ec.KeyAgreementSpi$DHC");
            configurableProvider.a("KeyAgreement.ECMQV", "org.spongycastle.jcajce.provider.asymmetric.ec.KeyAgreementSpi$MQV");
            configurableProvider.a("KeyAgreement." + X9ObjectIdentifiers.X, "org.spongycastle.jcajce.provider.asymmetric.ec.KeyAgreementSpi$DHwithSHA1KDF");
            configurableProvider.a("KeyAgreement." + X9ObjectIdentifiers.Z, "org.spongycastle.jcajce.provider.asymmetric.ec.KeyAgreementSpi$MQVwithSHA1KDF");
            a(configurableProvider, X9ObjectIdentifiers.k, "EC", new org.spongycastle.jcajce.provider.asymmetric.ec.KeyFactorySpi.EC());
            a(configurableProvider, X9ObjectIdentifiers.X, "EC", new org.spongycastle.jcajce.provider.asymmetric.ec.KeyFactorySpi.EC());
            a(configurableProvider, X9ObjectIdentifiers.Z, "ECMQV", new ECMQV());
            a(configurableProvider, X9ObjectIdentifiers.k, "EC");
            a(configurableProvider, X9ObjectIdentifiers.X, "EC");
            a(configurableProvider, X9ObjectIdentifiers.Z, "EC");
            configurableProvider.a("KeyFactory.EC", "org.spongycastle.jcajce.provider.asymmetric.ec.KeyFactorySpi$EC");
            configurableProvider.a("KeyFactory.ECDSA", "org.spongycastle.jcajce.provider.asymmetric.ec.KeyFactorySpi$ECDSA");
            configurableProvider.a("KeyFactory.ECDH", "org.spongycastle.jcajce.provider.asymmetric.ec.KeyFactorySpi$ECDH");
            configurableProvider.a("KeyFactory.ECDHC", "org.spongycastle.jcajce.provider.asymmetric.ec.KeyFactorySpi$ECDHC");
            configurableProvider.a("KeyFactory.ECMQV", "org.spongycastle.jcajce.provider.asymmetric.ec.KeyFactorySpi$ECMQV");
            configurableProvider.a("KeyPairGenerator.EC", "org.spongycastle.jcajce.provider.asymmetric.ec.KeyPairGeneratorSpi$EC");
            configurableProvider.a("KeyPairGenerator.ECDSA", "org.spongycastle.jcajce.provider.asymmetric.ec.KeyPairGeneratorSpi$ECDSA");
            configurableProvider.a("KeyPairGenerator.ECDH", "org.spongycastle.jcajce.provider.asymmetric.ec.KeyPairGeneratorSpi$ECDH");
            configurableProvider.a("KeyPairGenerator.ECDHC", "org.spongycastle.jcajce.provider.asymmetric.ec.KeyPairGeneratorSpi$ECDHC");
            configurableProvider.a("KeyPairGenerator.ECIES", "org.spongycastle.jcajce.provider.asymmetric.ec.KeyPairGeneratorSpi$ECDH");
            configurableProvider.a("KeyPairGenerator.ECMQV", "org.spongycastle.jcajce.provider.asymmetric.ec.KeyPairGeneratorSpi$ECMQV");
            configurableProvider.a("Cipher.ECIES", "org.spongycastle.jcajce.provider.asymmetric.ec.IESCipher$ECIES");
            configurableProvider.a("Cipher.ECIESwithAES", "org.spongycastle.jcajce.provider.asymmetric.ec.IESCipher$ECIESwithAES");
            configurableProvider.a("Cipher.ECIESWITHAES", "org.spongycastle.jcajce.provider.asymmetric.ec.IESCipher$ECIESwithAES");
            configurableProvider.a("Cipher.ECIESwithDESEDE", "org.spongycastle.jcajce.provider.asymmetric.ec.IESCipher$ECIESwithDESede");
            configurableProvider.a("Cipher.ECIESWITHDESEDE", "org.spongycastle.jcajce.provider.asymmetric.ec.IESCipher$ECIESwithDESede");
            configurableProvider.a("Signature.ECDSA", "org.spongycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecDSA");
            configurableProvider.a("Signature.NONEwithECDSA", "org.spongycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecDSAnone");
            configurableProvider.a("Alg.Alias.Signature.SHA1withECDSA", "ECDSA");
            configurableProvider.a("Alg.Alias.Signature.ECDSAwithSHA1", "ECDSA");
            configurableProvider.a("Alg.Alias.Signature.SHA1WITHECDSA", "ECDSA");
            configurableProvider.a("Alg.Alias.Signature.ECDSAWITHSHA1", "ECDSA");
            configurableProvider.a("Alg.Alias.Signature.SHA1WithECDSA", "ECDSA");
            configurableProvider.a("Alg.Alias.Signature.ECDSAWithSHA1", "ECDSA");
            configurableProvider.a("Alg.Alias.Signature.1.2.840.10045.4.1", "ECDSA");
            configurableProvider.a("Alg.Alias.Signature." + TeleTrusTObjectIdentifiers.j, "ECDSA");
            configurableProvider.a("Signature.DETECDSA", "org.spongycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecDetDSA");
            configurableProvider.a("Signature.SHA1WITHDETECDSA", "org.spongycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecDetDSA");
            configurableProvider.a("Signature.SHA224WITHDETECDSA", "org.spongycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecDetDSA224");
            configurableProvider.a("Signature.SHA256WITHDETECDSA", "org.spongycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecDetDSA256");
            configurableProvider.a("Signature.SHA384WITHDETECDSA", "org.spongycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecDetDSA384");
            configurableProvider.a("Signature.SHA512WITHDETECDSA", "org.spongycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecDetDSA512");
            a(configurableProvider, "SHA224", "ECDSA", "org.spongycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecDSA224", X9ObjectIdentifiers.m);
            a(configurableProvider, "SHA256", "ECDSA", "org.spongycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecDSA256", X9ObjectIdentifiers.n);
            a(configurableProvider, "SHA384", "ECDSA", "org.spongycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecDSA384", X9ObjectIdentifiers.o);
            a(configurableProvider, "SHA512", "ECDSA", "org.spongycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecDSA512", X9ObjectIdentifiers.p);
            a(configurableProvider, "RIPEMD160", "ECDSA", "org.spongycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecDSARipeMD160", TeleTrusTObjectIdentifiers.k);
            configurableProvider.a("Signature.SHA1WITHECNR", "org.spongycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecNR");
            configurableProvider.a("Signature.SHA224WITHECNR", "org.spongycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecNR224");
            configurableProvider.a("Signature.SHA256WITHECNR", "org.spongycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecNR256");
            configurableProvider.a("Signature.SHA384WITHECNR", "org.spongycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecNR384");
            configurableProvider.a("Signature.SHA512WITHECNR", "org.spongycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecNR512");
            a(configurableProvider, "SHA1", "CVC-ECDSA", "org.spongycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecCVCDSA", EACObjectIdentifiers.s);
            a(configurableProvider, "SHA224", "CVC-ECDSA", "org.spongycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecCVCDSA224", EACObjectIdentifiers.t);
            a(configurableProvider, "SHA256", "CVC-ECDSA", "org.spongycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecCVCDSA256", EACObjectIdentifiers.u);
            a(configurableProvider, "SHA384", "CVC-ECDSA", "org.spongycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecCVCDSA384", EACObjectIdentifiers.v);
            a(configurableProvider, "SHA512", "CVC-ECDSA", "org.spongycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecCVCDSA512", EACObjectIdentifiers.w);
        }
    }
}
