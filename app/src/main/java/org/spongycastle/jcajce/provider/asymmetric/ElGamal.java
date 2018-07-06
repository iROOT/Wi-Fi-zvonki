package org.spongycastle.jcajce.provider.asymmetric;

import org.spongycastle.asn1.oiw.OIWObjectIdentifiers;
import org.spongycastle.jcajce.provider.asymmetric.elgamal.KeyFactorySpi;
import org.spongycastle.jcajce.provider.config.ConfigurableProvider;
import org.spongycastle.jcajce.provider.util.AsymmetricAlgorithmProvider;

public class ElGamal {

    public static class Mappings extends AsymmetricAlgorithmProvider {
        public void a(ConfigurableProvider configurableProvider) {
            configurableProvider.a("AlgorithmParameterGenerator.ELGAMAL", "org.spongycastle.jcajce.provider.asymmetric.elgamal.AlgorithmParameterGeneratorSpi");
            configurableProvider.a("AlgorithmParameterGenerator.ElGamal", "org.spongycastle.jcajce.provider.asymmetric.elgamal.AlgorithmParameterGeneratorSpi");
            configurableProvider.a("AlgorithmParameters.ELGAMAL", "org.spongycastle.jcajce.provider.asymmetric.elgamal.AlgorithmParametersSpi");
            configurableProvider.a("AlgorithmParameters.ElGamal", "org.spongycastle.jcajce.provider.asymmetric.elgamal.AlgorithmParametersSpi");
            configurableProvider.a("Cipher.ELGAMAL", "org.spongycastle.jcajce.provider.asymmetric.elgamal.CipherSpi$NoPadding");
            configurableProvider.a("Cipher.ElGamal", "org.spongycastle.jcajce.provider.asymmetric.elgamal.CipherSpi$NoPadding");
            configurableProvider.a("Alg.Alias.Cipher.ELGAMAL/ECB/PKCS1PADDING", "ELGAMAL/PKCS1");
            configurableProvider.a("Alg.Alias.Cipher.ELGAMAL/NONE/PKCS1PADDING", "ELGAMAL/PKCS1");
            configurableProvider.a("Alg.Alias.Cipher.ELGAMAL/NONE/NOPADDING", "ELGAMAL");
            configurableProvider.a("Cipher.ELGAMAL/PKCS1", "org.spongycastle.jcajce.provider.asymmetric.elgamal.CipherSpi$PKCS1v1_5Padding");
            configurableProvider.a("KeyFactory.ELGAMAL", "org.spongycastle.jcajce.provider.asymmetric.elgamal.KeyFactorySpi");
            configurableProvider.a("KeyFactory.ElGamal", "org.spongycastle.jcajce.provider.asymmetric.elgamal.KeyFactorySpi");
            configurableProvider.a("KeyPairGenerator.ELGAMAL", "org.spongycastle.jcajce.provider.asymmetric.elgamal.KeyPairGeneratorSpi");
            configurableProvider.a("KeyPairGenerator.ElGamal", "org.spongycastle.jcajce.provider.asymmetric.elgamal.KeyPairGeneratorSpi");
            a(configurableProvider, OIWObjectIdentifiers.l, "ELGAMAL", new KeyFactorySpi());
            a(configurableProvider, OIWObjectIdentifiers.l, "ELGAMAL");
        }
    }
}
