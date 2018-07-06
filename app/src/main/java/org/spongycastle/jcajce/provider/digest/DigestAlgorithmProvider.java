package org.spongycastle.jcajce.provider.digest;

import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.jcajce.provider.config.ConfigurableProvider;
import org.spongycastle.jcajce.provider.util.AlgorithmProvider;

abstract class DigestAlgorithmProvider extends AlgorithmProvider {
    DigestAlgorithmProvider() {
    }

    protected void a(ConfigurableProvider configurableProvider, String str, String str2, String str3) {
        String str4 = "HMAC" + str;
        configurableProvider.a("Mac." + str4, str2);
        configurableProvider.a("Alg.Alias.Mac.HMAC-" + str, str4);
        configurableProvider.a("Alg.Alias.Mac.HMAC/" + str, str4);
        configurableProvider.a("KeyGenerator." + str4, str3);
        configurableProvider.a("Alg.Alias.KeyGenerator.HMAC-" + str, str4);
        configurableProvider.a("Alg.Alias.KeyGenerator.HMAC/" + str, str4);
    }

    protected void a(ConfigurableProvider configurableProvider, String str, ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        String str2 = "HMAC" + str;
        configurableProvider.a("Alg.Alias.Mac." + aSN1ObjectIdentifier, str2);
        configurableProvider.a("Alg.Alias.KeyGenerator." + aSN1ObjectIdentifier, str2);
    }
}
