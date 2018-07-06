package org.spongycastle.asn1.bc;

import org.spongycastle.asn1.ASN1ObjectIdentifier;

public interface BCObjectIdentifiers {
    public static final ASN1ObjectIdentifier a = new ASN1ObjectIdentifier("1.3.6.1.4.1.22554");
    public static final ASN1ObjectIdentifier b = a.b("1");
    public static final ASN1ObjectIdentifier c = b.b("1");
    public static final ASN1ObjectIdentifier d = b.b("2.1");
    public static final ASN1ObjectIdentifier e = b.b("2.2");
    public static final ASN1ObjectIdentifier f = b.b("2.3");
    public static final ASN1ObjectIdentifier g = b.b("2.4");
    public static final ASN1ObjectIdentifier h = c.b("1");
    public static final ASN1ObjectIdentifier i = c.b("2");
    public static final ASN1ObjectIdentifier j = d.b("1");
    public static final ASN1ObjectIdentifier k = d.b("2");
    public static final ASN1ObjectIdentifier l = i.b("1.2");
    public static final ASN1ObjectIdentifier m = i.b("1.22");
    public static final ASN1ObjectIdentifier n = i.b("1.42");
    public static final ASN1ObjectIdentifier o = k.b("1.2");
    public static final ASN1ObjectIdentifier p = k.b("1.22");
    public static final ASN1ObjectIdentifier q = k.b("1.42");
}
