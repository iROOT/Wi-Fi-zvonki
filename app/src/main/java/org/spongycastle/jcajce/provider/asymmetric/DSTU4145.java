package org.spongycastle.jcajce.provider.asymmetric;

import org.spongycastle.asn1.ua.UAObjectIdentifiers;
import org.spongycastle.jcajce.provider.asymmetric.dstu.KeyFactorySpi;
import org.spongycastle.jcajce.provider.config.ConfigurableProvider;
import org.spongycastle.jcajce.provider.util.AsymmetricAlgorithmProvider;

public class DSTU4145 {

    public static class Mappings extends AsymmetricAlgorithmProvider {
        public void a(ConfigurableProvider configurableProvider) {
            configurableProvider.a("KeyFactory.DSTU4145", "org.spongycastle.jcajce.provider.asymmetric.dstu.KeyFactorySpi");
            configurableProvider.a("Alg.Alias.KeyFactory.DSTU-4145-2002", "DSTU4145");
            configurableProvider.a("Alg.Alias.KeyFactory.DSTU4145-3410", "DSTU4145");
            a(configurableProvider, UAObjectIdentifiers.b, "DSTU4145", new KeyFactorySpi());
            a(configurableProvider, UAObjectIdentifiers.b, "DSTU4145");
            a(configurableProvider, UAObjectIdentifiers.c, "DSTU4145", new KeyFactorySpi());
            a(configurableProvider, UAObjectIdentifiers.c, "DSTU4145");
            configurableProvider.a("KeyPairGenerator.DSTU4145", "org.spongycastle.jcajce.provider.asymmetric.dstu.KeyPairGeneratorSpi");
            configurableProvider.a("Alg.Alias.KeyPairGenerator.DSTU-4145", "DSTU4145");
            configurableProvider.a("Alg.Alias.KeyPairGenerator.DSTU-4145-2002", "DSTU4145");
            configurableProvider.a("Signature.DSTU4145", "org.spongycastle.jcajce.provider.asymmetric.dstu.SignatureSpi");
            configurableProvider.a("Alg.Alias.Signature.DSTU-4145", "DSTU4145");
            configurableProvider.a("Alg.Alias.Signature.DSTU-4145-2002", "DSTU4145");
            a(configurableProvider, "GOST3411", "DSTU4145LE", "org.spongycastle.jcajce.provider.asymmetric.dstu.SignatureSpiLe", UAObjectIdentifiers.b);
            a(configurableProvider, "GOST3411", "DSTU4145", "org.spongycastle.jcajce.provider.asymmetric.dstu.SignatureSpi", UAObjectIdentifiers.c);
        }
    }
}
