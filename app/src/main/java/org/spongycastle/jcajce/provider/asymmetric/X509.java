package org.spongycastle.jcajce.provider.asymmetric;

import org.spongycastle.jcajce.provider.config.ConfigurableProvider;
import org.spongycastle.jcajce.provider.util.AsymmetricAlgorithmProvider;

public class X509 {

    public static class Mappings extends AsymmetricAlgorithmProvider {
        public void a(ConfigurableProvider configurableProvider) {
            configurableProvider.a("KeyFactory.X.509", "org.spongycastle.jcajce.provider.asymmetric.x509.KeyFactory");
            configurableProvider.a("Alg.Alias.KeyFactory.X509", "X.509");
            configurableProvider.a("CertificateFactory.X.509", "org.spongycastle.jcajce.provider.asymmetric.x509.CertificateFactory");
            configurableProvider.a("Alg.Alias.CertificateFactory.X509", "X.509");
        }
    }
}
