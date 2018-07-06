package org.spongycastle.asn1.misc;

import org.spongycastle.asn1.ASN1ObjectIdentifier;

public interface MiscObjectIdentifiers {
    public static final ASN1ObjectIdentifier a = new ASN1ObjectIdentifier("2.16.840.1.113730.1");
    public static final ASN1ObjectIdentifier b = a.b("1");
    public static final ASN1ObjectIdentifier c = a.b("2");
    public static final ASN1ObjectIdentifier d = a.b("3");
    public static final ASN1ObjectIdentifier e = a.b("4");
    public static final ASN1ObjectIdentifier f = a.b("7");
    public static final ASN1ObjectIdentifier g = a.b("8");
    public static final ASN1ObjectIdentifier h = a.b("12");
    public static final ASN1ObjectIdentifier i = a.b("13");
    public static final ASN1ObjectIdentifier j = new ASN1ObjectIdentifier("2.16.840.1.113733.1");
    public static final ASN1ObjectIdentifier k = j.b("6.3");
    public static final ASN1ObjectIdentifier l = j.b("6.15");
    public static final ASN1ObjectIdentifier m = new ASN1ObjectIdentifier("2.16.840.1.113719");
    public static final ASN1ObjectIdentifier n = m.b("1.9.4.1");
    public static final ASN1ObjectIdentifier o = new ASN1ObjectIdentifier("1.2.840.113533.7");
    public static final ASN1ObjectIdentifier p = o.b("65.0");
}
