package org.spongycastle.jcajce.provider.asymmetric;

import org.spongycastle.jcajce.provider.config.ConfigurableProvider;
import org.spongycastle.jcajce.provider.util.AsymmetricAlgorithmProvider;

public class DH {

    public static class Mappings extends AsymmetricAlgorithmProvider {
        public void a(ConfigurableProvider configurableProvider) {
            configurableProvider.a("KeyPairGenerator.DH", "org.spongycastle.jcajce.provider.asymmetric.dh.KeyPairGeneratorSpi");
            configurableProvider.a("Alg.Alias.KeyPairGenerator.DIFFIEHELLMAN", "DH");
            configurableProvider.a("KeyAgreement.DH", "org.spongycastle.jcajce.provider.asymmetric.dh.KeyAgreementSpi");
            configurableProvider.a("Alg.Alias.KeyAgreement.DIFFIEHELLMAN", "DH");
            configurableProvider.a("KeyFactory.DH", "org.spongycastle.jcajce.provider.asymmetric.dh.KeyFactorySpi");
            configurableProvider.a("Alg.Alias.KeyFactory.DIFFIEHELLMAN", "DH");
            configurableProvider.a("AlgorithmParameters.DH", "org.spongycastle.jcajce.provider.asymmetric.dh.AlgorithmParametersSpi");
            configurableProvider.a("Alg.Alias.AlgorithmParameters.DIFFIEHELLMAN", "DH");
            configurableProvider.a("Alg.Alias.AlgorithmParameterGenerator.DIFFIEHELLMAN", "DH");
            configurableProvider.a("AlgorithmParameterGenerator.DH", "org.spongycastle.jcajce.provider.asymmetric.dh.AlgorithmParameterGeneratorSpi");
            configurableProvider.a("Cipher.DHIES", "org.spongycastle.jcajce.provider.asymmetric.dh.IESCipher$IES");
            configurableProvider.a("Cipher.DHIESwithAES", "org.spongycastle.jcajce.provider.asymmetric.dh.IESCipher$IESwithAES");
            configurableProvider.a("Cipher.DHIESWITHAES", "org.spongycastle.jcajce.provider.asymmetric.dh.IESCipher$IESwithAES");
            configurableProvider.a("Cipher.DHIESWITHDESEDE", "org.spongycastle.jcajce.provider.asymmetric.dh.IESCipher$IESwithDESede");
        }
    }
}
