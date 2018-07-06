package org.spongycastle.pqc.asn1;

import org.spongycastle.asn1.ASN1ObjectIdentifier;

public interface PQCObjectIdentifiers {
    public static final ASN1ObjectIdentifier a = new ASN1ObjectIdentifier("1.3.6.1.4.1.8301.3.1.3.5.3.2");
    public static final ASN1ObjectIdentifier b = a.b("1");
    public static final ASN1ObjectIdentifier c = a.b("2");
    public static final ASN1ObjectIdentifier d = a.b("3");
    public static final ASN1ObjectIdentifier e = a.b("4");
    public static final ASN1ObjectIdentifier f = a.b("5");
    public static final ASN1ObjectIdentifier g = new ASN1ObjectIdentifier("1.3.6.1.4.1.8301.3.1.3.3");
    public static final ASN1ObjectIdentifier h = g.b("1");
    public static final ASN1ObjectIdentifier i = g.b("2");
    public static final ASN1ObjectIdentifier j = g.b("3");
    public static final ASN1ObjectIdentifier k = g.b("4");
    public static final ASN1ObjectIdentifier l = g.b("5");
    public static final ASN1ObjectIdentifier m = new ASN1ObjectIdentifier("1.3.6.1.4.1.8301.3.1.3.4.1");
    public static final ASN1ObjectIdentifier n = new ASN1ObjectIdentifier("1.3.6.1.4.1.8301.3.1.3.4.2");
}
