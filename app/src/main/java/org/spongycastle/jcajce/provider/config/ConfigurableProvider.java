package org.spongycastle.jcajce.provider.config;

import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.jcajce.provider.util.AsymmetricKeyInfoConverter;

public interface ConfigurableProvider {
    void a(String str, String str2);

    void a(ASN1ObjectIdentifier aSN1ObjectIdentifier, AsymmetricKeyInfoConverter asymmetricKeyInfoConverter);

    boolean b(String str, String str2);
}
