package org.spongycastle.jcajce.provider.asymmetric;

import org.spongycastle.asn1.cryptopro.CryptoProObjectIdentifiers;
import org.spongycastle.jcajce.provider.asymmetric.ecgost.KeyFactorySpi;
import org.spongycastle.jcajce.provider.config.ConfigurableProvider;
import org.spongycastle.jcajce.provider.util.AsymmetricAlgorithmProvider;

public class ECGOST {

    public static class Mappings extends AsymmetricAlgorithmProvider {
        public void a(ConfigurableProvider configurableProvider) {
            configurableProvider.a("KeyFactory.ECGOST3410", "org.spongycastle.jcajce.provider.asymmetric.ecgost.KeyFactorySpi");
            configurableProvider.a("Alg.Alias.KeyFactory.GOST-3410-2001", "ECGOST3410");
            configurableProvider.a("Alg.Alias.KeyFactory.ECGOST-3410", "ECGOST3410");
            a(configurableProvider, CryptoProObjectIdentifiers.j, "ECGOST3410", new KeyFactorySpi());
            a(configurableProvider, CryptoProObjectIdentifiers.j, "ECGOST3410");
            configurableProvider.a("KeyPairGenerator.ECGOST3410", "org.spongycastle.jcajce.provider.asymmetric.ecgost.KeyPairGeneratorSpi");
            configurableProvider.a("Alg.Alias.KeyPairGenerator.ECGOST-3410", "ECGOST3410");
            configurableProvider.a("Alg.Alias.KeyPairGenerator.GOST-3410-2001", "ECGOST3410");
            configurableProvider.a("Signature.ECGOST3410", "org.spongycastle.jcajce.provider.asymmetric.ecgost.SignatureSpi");
            configurableProvider.a("Alg.Alias.Signature.ECGOST-3410", "ECGOST3410");
            configurableProvider.a("Alg.Alias.Signature.GOST-3410-2001", "ECGOST3410");
            a(configurableProvider, "GOST3411", "ECGOST3410", "org.spongycastle.jcajce.provider.asymmetric.ecgost.SignatureSpi", CryptoProObjectIdentifiers.l);
        }
    }
}
