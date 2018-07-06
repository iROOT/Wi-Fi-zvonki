package org.spongycastle.asn1.x509;

import org.spongycastle.asn1.ASN1ObjectIdentifier;

public interface X509AttributeIdentifiers {
    public static final ASN1ObjectIdentifier a = new ASN1ObjectIdentifier("2.5.4.72");
    public static final ASN1ObjectIdentifier b = X509ObjectIdentifiers.n.b("4");
    public static final ASN1ObjectIdentifier c = X509ObjectIdentifiers.n.b("6");
    public static final ASN1ObjectIdentifier d = X509ObjectIdentifiers.n.b("10");
    public static final ASN1ObjectIdentifier e = X509ObjectIdentifiers.o.b("55");
    public static final ASN1ObjectIdentifier f = X509ObjectIdentifiers.m.b("10");
    public static final ASN1ObjectIdentifier g = f.b("1");
    public static final ASN1ObjectIdentifier h = f.b("2");
    public static final ASN1ObjectIdentifier i = f.b("3");
    public static final ASN1ObjectIdentifier j = f.b("4");
    public static final ASN1ObjectIdentifier k = f.b("6");
    public static final ASN1ObjectIdentifier l = new ASN1ObjectIdentifier("2.5.4.72");
    public static final ASN1ObjectIdentifier m = new ASN1ObjectIdentifier("2.5.1.5.55");
}
