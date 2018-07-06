package org.spongycastle.asn1.iana;

import org.spongycastle.asn1.ASN1ObjectIdentifier;

public interface IANAObjectIdentifiers {
    public static final ASN1ObjectIdentifier a = new ASN1ObjectIdentifier("1.3.6.1");
    public static final ASN1ObjectIdentifier b = a.b("1");
    public static final ASN1ObjectIdentifier c = a.b("2");
    public static final ASN1ObjectIdentifier d = a.b("3");
    public static final ASN1ObjectIdentifier e = a.b("4");
    public static final ASN1ObjectIdentifier f = a.b("5");
    public static final ASN1ObjectIdentifier g = a.b("6");
    public static final ASN1ObjectIdentifier h = a.b("7");
    public static final ASN1ObjectIdentifier i = f.b("5");
    public static final ASN1ObjectIdentifier j = f.b("6");
    public static final ASN1ObjectIdentifier k = i.b("6");
    public static final ASN1ObjectIdentifier l = i.b("8");
    public static final ASN1ObjectIdentifier m = l.b("1");
    public static final ASN1ObjectIdentifier n = m.b("1");
    public static final ASN1ObjectIdentifier o = m.b("2");
    public static final ASN1ObjectIdentifier p = m.b("3");
    public static final ASN1ObjectIdentifier q = m.b("4");
}
