package org.spongycastle.jcajce.provider.symmetric;

import org.spongycastle.jcajce.provider.config.ConfigurableProvider;
import org.spongycastle.jcajce.provider.util.AlgorithmProvider;

abstract class SymmetricAlgorithmProvider extends AlgorithmProvider {
    SymmetricAlgorithmProvider() {
    }

    protected void a(ConfigurableProvider configurableProvider, String str, String str2, String str3) {
        configurableProvider.a("Mac." + str + "-GMAC", str2);
        configurableProvider.a("Alg.Alias.Mac." + str + "GMAC", str + "-GMAC");
        configurableProvider.a("KeyGenerator." + str + "-GMAC", str3);
        configurableProvider.a("Alg.Alias.KeyGenerator." + str + "GMAC", str + "-GMAC");
    }

    protected void b(ConfigurableProvider configurableProvider, String str, String str2, String str3) {
        configurableProvider.a("Mac.POLY1305-" + str, str2);
        configurableProvider.a("Alg.Alias.Mac.POLY1305" + str, "POLY1305-" + str);
        configurableProvider.a("KeyGenerator.POLY1305-" + str, str3);
        configurableProvider.a("Alg.Alias.KeyGenerator.POLY1305" + str, "POLY1305-" + str);
    }
}
