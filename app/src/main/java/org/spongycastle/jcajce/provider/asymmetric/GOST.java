package org.spongycastle.jcajce.provider.asymmetric;

import org.spongycastle.asn1.cryptopro.CryptoProObjectIdentifiers;
import org.spongycastle.jcajce.provider.asymmetric.gost.KeyFactorySpi;
import org.spongycastle.jcajce.provider.config.ConfigurableProvider;
import org.spongycastle.jcajce.provider.util.AsymmetricAlgorithmProvider;

public class GOST {

    public static class Mappings extends AsymmetricAlgorithmProvider {
        public void a(ConfigurableProvider configurableProvider) {
            configurableProvider.a("KeyPairGenerator.GOST3410", "org.spongycastle.jcajce.provider.asymmetric.gost.KeyPairGeneratorSpi");
            configurableProvider.a("Alg.Alias.KeyPairGenerator.GOST-3410", "GOST3410");
            configurableProvider.a("Alg.Alias.KeyPairGenerator.GOST-3410-94", "GOST3410");
            configurableProvider.a("KeyFactory.GOST3410", "org.spongycastle.jcajce.provider.asymmetric.gost.KeyFactorySpi");
            configurableProvider.a("Alg.Alias.KeyFactory.GOST-3410", "GOST3410");
            configurableProvider.a("Alg.Alias.KeyFactory.GOST-3410-94", "GOST3410");
            configurableProvider.a("AlgorithmParameters.GOST3410", "org.spongycastle.jcajce.provider.asymmetric.gost.AlgorithmParametersSpi");
            configurableProvider.a("AlgorithmParameterGenerator.GOST3410", "org.spongycastle.jcajce.provider.asymmetric.gost.AlgorithmParameterGeneratorSpi");
            a(configurableProvider, CryptoProObjectIdentifiers.i, "GOST3410", new KeyFactorySpi());
            a(configurableProvider, CryptoProObjectIdentifiers.i, "GOST3410");
            configurableProvider.a("Signature.GOST3410", "org.spongycastle.jcajce.provider.asymmetric.gost.SignatureSpi");
            configurableProvider.a("Alg.Alias.Signature.GOST-3410", "GOST3410");
            configurableProvider.a("Alg.Alias.Signature.GOST-3410-94", "GOST3410");
            configurableProvider.a("Alg.Alias.Signature.GOST3411withGOST3410", "GOST3410");
            configurableProvider.a("Alg.Alias.Signature.GOST3411WITHGOST3410", "GOST3410");
            configurableProvider.a("Alg.Alias.Signature.GOST3411WithGOST3410", "GOST3410");
            configurableProvider.a("Alg.Alias.Signature." + CryptoProObjectIdentifiers.k, "GOST3410");
            configurableProvider.a("Alg.Alias.AlgorithmParameterGenerator.GOST-3410", "GOST3410");
            configurableProvider.a("Alg.Alias.AlgorithmParameters.GOST-3410", "GOST3410");
        }
    }
}
