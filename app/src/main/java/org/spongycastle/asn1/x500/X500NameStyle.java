package org.spongycastle.asn1.x500;

import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1ObjectIdentifier;

public interface X500NameStyle {
    int a(X500Name x500Name);

    ASN1Encodable a(ASN1ObjectIdentifier aSN1ObjectIdentifier, String str);

    ASN1ObjectIdentifier a(String str);

    boolean a(X500Name x500Name, X500Name x500Name2);

    String b(X500Name x500Name);

    RDN[] b(String str);
}
