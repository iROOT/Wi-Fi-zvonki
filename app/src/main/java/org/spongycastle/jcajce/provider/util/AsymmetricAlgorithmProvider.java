package org.spongycastle.jcajce.provider.util;

import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.jcajce.provider.config.ConfigurableProvider;

public abstract class AsymmetricAlgorithmProvider extends AlgorithmProvider {
    protected void a(ConfigurableProvider configurableProvider, String str, String str2, String str3, ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        String str4 = str + "WITH" + str2;
        String str5 = str + "with" + str2;
        String str6 = str + "With" + str2;
        String str7 = str + "/" + str2;
        configurableProvider.a("Signature." + str4, str3);
        configurableProvider.a("Alg.Alias.Signature." + str5, str4);
        configurableProvider.a("Alg.Alias.Signature." + str6, str4);
        configurableProvider.a("Alg.Alias.Signature." + str7, str4);
        configurableProvider.a("Alg.Alias.Signature." + aSN1ObjectIdentifier, str4);
        configurableProvider.a("Alg.Alias.Signature.OID." + aSN1ObjectIdentifier, str4);
    }

    protected void a(ConfigurableProvider configurableProvider, ASN1ObjectIdentifier aSN1ObjectIdentifier, String str, AsymmetricKeyInfoConverter asymmetricKeyInfoConverter) {
        configurableProvider.a("Alg.Alias.KeyFactory." + aSN1ObjectIdentifier, str);
        configurableProvider.a("Alg.Alias.KeyPairGenerator." + aSN1ObjectIdentifier, str);
        configurableProvider.a(aSN1ObjectIdentifier, asymmetricKeyInfoConverter);
    }

    protected void a(ConfigurableProvider configurableProvider, ASN1ObjectIdentifier aSN1ObjectIdentifier, String str) {
        configurableProvider.a("Alg.Alias.AlgorithmParameterGenerator." + aSN1ObjectIdentifier, str);
        configurableProvider.a("Alg.Alias.AlgorithmParameters." + aSN1ObjectIdentifier, str);
    }
}
