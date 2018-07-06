package org.spongycastle.jcajce.provider.asymmetric;

import org.spongycastle.jcajce.provider.config.ConfigurableProvider;
import org.spongycastle.jcajce.provider.util.AsymmetricAlgorithmProvider;

public class IES {

    public static class Mappings extends AsymmetricAlgorithmProvider {
        public void a(ConfigurableProvider configurableProvider) {
            configurableProvider.a("AlgorithmParameters.IES", "org.spongycastle.jcajce.provider.asymmetric.ies.AlgorithmParametersSpi");
            configurableProvider.a("Cipher.IES", "org.spongycastle.jcajce.provider.asymmetric.ies.CipherSpi$IES");
        }
    }
}
