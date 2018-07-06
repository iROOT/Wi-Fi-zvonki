package org.spongycastle.asn1.dvcs;

import org.spongycastle.asn1.ASN1ObjectIdentifier;

public interface DVCSObjectIdentifiers {
    public static final ASN1ObjectIdentifier a = new ASN1ObjectIdentifier("1.3.6.1.5.5.7");
    public static final ASN1ObjectIdentifier b = new ASN1ObjectIdentifier("1.2.840.113549.1.9.16");
    public static final ASN1ObjectIdentifier c = a.b("48.4");
    public static final ASN1ObjectIdentifier d = a.b("3.10");
    public static final ASN1ObjectIdentifier e = b.b("1.7");
    public static final ASN1ObjectIdentifier f = b.b("1.8");
    public static final ASN1ObjectIdentifier g = b.b("2.29");
}
