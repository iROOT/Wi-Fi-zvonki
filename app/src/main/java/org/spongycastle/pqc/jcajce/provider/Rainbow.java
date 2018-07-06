package org.spongycastle.pqc.jcajce.provider;

import org.spongycastle.jcajce.provider.config.ConfigurableProvider;
import org.spongycastle.jcajce.provider.util.AsymmetricAlgorithmProvider;
import org.spongycastle.pqc.asn1.PQCObjectIdentifiers;
import org.spongycastle.pqc.jcajce.provider.rainbow.RainbowKeyFactorySpi;

public class Rainbow {

    public static class Mappings extends AsymmetricAlgorithmProvider {
        public void a(ConfigurableProvider configurableProvider) {
            configurableProvider.a("KeyFactory.Rainbow", "org.spongycastle.pqc.jcajce.provider.rainbow.RainbowKeyFactorySpi");
            configurableProvider.a("KeyPairGenerator.Rainbow", "org.spongycastle.pqc.jcajce.provider.rainbow.RainbowKeyPairGeneratorSpi");
            a(configurableProvider, "SHA224", "Rainbow", "org.spongycastle.pqc.jcajce.provider.rainbow.SignatureSpi$withSha224", PQCObjectIdentifiers.c);
            a(configurableProvider, "SHA256", "Rainbow", "org.spongycastle.pqc.jcajce.provider.rainbow.SignatureSpi$withSha256", PQCObjectIdentifiers.d);
            a(configurableProvider, "SHA384", "Rainbow", "org.spongycastle.pqc.jcajce.provider.rainbow.SignatureSpi$withSha384", PQCObjectIdentifiers.e);
            a(configurableProvider, "SHA512", "Rainbow", "org.spongycastle.pqc.jcajce.provider.rainbow.SignatureSpi$withSha512", PQCObjectIdentifiers.f);
            a(configurableProvider, PQCObjectIdentifiers.a, "Rainbow", new RainbowKeyFactorySpi());
            a(configurableProvider, PQCObjectIdentifiers.a, "Rainbow");
        }
    }
}
