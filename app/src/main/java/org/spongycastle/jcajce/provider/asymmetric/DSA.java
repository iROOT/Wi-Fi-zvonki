package org.spongycastle.jcajce.provider.asymmetric;

import org.spongycastle.asn1.nist.NISTObjectIdentifiers;
import org.spongycastle.jcajce.provider.asymmetric.dsa.DSAUtil;
import org.spongycastle.jcajce.provider.asymmetric.dsa.KeyFactorySpi;
import org.spongycastle.jcajce.provider.config.ConfigurableProvider;
import org.spongycastle.jcajce.provider.util.AsymmetricAlgorithmProvider;
import org.spongycastle.jcajce.provider.util.AsymmetricKeyInfoConverter;

public class DSA {

    public static class Mappings extends AsymmetricAlgorithmProvider {
        public void a(ConfigurableProvider configurableProvider) {
            configurableProvider.a("AlgorithmParameters.DSA", "org.spongycastle.jcajce.provider.asymmetric.dsa.AlgorithmParametersSpi");
            configurableProvider.a("AlgorithmParameterGenerator.DSA", "org.spongycastle.jcajce.provider.asymmetric.dsa.AlgorithmParameterGeneratorSpi");
            configurableProvider.a("KeyPairGenerator.DSA", "org.spongycastle.jcajce.provider.asymmetric.dsa.KeyPairGeneratorSpi");
            configurableProvider.a("KeyFactory.DSA", "org.spongycastle.jcajce.provider.asymmetric.dsa.KeyFactorySpi");
            configurableProvider.a("Signature.DSA", "org.spongycastle.jcajce.provider.asymmetric.dsa.DSASigner$stdDSA");
            configurableProvider.a("Signature.NONEWITHDSA", "org.spongycastle.jcajce.provider.asymmetric.dsa.DSASigner$noneDSA");
            configurableProvider.a("Alg.Alias.Signature.RAWDSA", "NONEWITHDSA");
            configurableProvider.a("Signature.DETDSA", "org.spongycastle.jcajce.provider.asymmetric.dsa.DSASigner$detDSA");
            configurableProvider.a("Signature.SHA1WITHDETDSA", "org.spongycastle.jcajce.provider.asymmetric.dsa.DSASigner$detDSA");
            configurableProvider.a("Signature.SHA224WITHDETDSA", "org.spongycastle.jcajce.provider.asymmetric.dsa.DSASigner$detDSA224");
            configurableProvider.a("Signature.SHA256WITHDETDSA", "org.spongycastle.jcajce.provider.asymmetric.dsa.DSASigner$detDSA256");
            configurableProvider.a("Signature.SHA384WITHDETDSA", "org.spongycastle.jcajce.provider.asymmetric.dsa.DSASigner$detDSA384");
            configurableProvider.a("Signature.SHA512WITHDETDSA", "org.spongycastle.jcajce.provider.asymmetric.dsa.DSASigner$detDSA512");
            a(configurableProvider, "SHA224", "DSA", "org.spongycastle.jcajce.provider.asymmetric.dsa.DSASigner$dsa224", NISTObjectIdentifiers.F);
            a(configurableProvider, "SHA256", "DSA", "org.spongycastle.jcajce.provider.asymmetric.dsa.DSASigner$dsa256", NISTObjectIdentifiers.G);
            a(configurableProvider, "SHA384", "DSA", "org.spongycastle.jcajce.provider.asymmetric.dsa.DSASigner$dsa384", NISTObjectIdentifiers.H);
            a(configurableProvider, "SHA512", "DSA", "org.spongycastle.jcajce.provider.asymmetric.dsa.DSASigner$dsa512", NISTObjectIdentifiers.I);
            configurableProvider.a("Alg.Alias.Signature.SHA/DSA", "DSA");
            configurableProvider.a("Alg.Alias.Signature.SHA1withDSA", "DSA");
            configurableProvider.a("Alg.Alias.Signature.SHA1WITHDSA", "DSA");
            configurableProvider.a("Alg.Alias.Signature.1.3.14.3.2.26with1.2.840.10040.4.1", "DSA");
            configurableProvider.a("Alg.Alias.Signature.1.3.14.3.2.26with1.2.840.10040.4.3", "DSA");
            configurableProvider.a("Alg.Alias.Signature.DSAwithSHA1", "DSA");
            configurableProvider.a("Alg.Alias.Signature.DSAWITHSHA1", "DSA");
            configurableProvider.a("Alg.Alias.Signature.SHA1WithDSA", "DSA");
            configurableProvider.a("Alg.Alias.Signature.DSAWithSHA1", "DSA");
            configurableProvider.a("Alg.Alias.Signature.1.2.840.10040.4.3", "DSA");
            AsymmetricKeyInfoConverter keyFactorySpi = new KeyFactorySpi();
            for (int i = 0; i != DSAUtil.a.length; i++) {
                configurableProvider.a("Alg.Alias.Signature." + DSAUtil.a[i], "DSA");
                a(configurableProvider, DSAUtil.a[i], "DSA", keyFactorySpi);
                a(configurableProvider, DSAUtil.a[i], "DSA");
            }
        }
    }
}
