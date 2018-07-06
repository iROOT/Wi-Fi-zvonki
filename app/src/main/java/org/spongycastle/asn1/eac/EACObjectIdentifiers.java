package org.spongycastle.asn1.eac;

import org.spongycastle.asn1.ASN1ObjectIdentifier;

public interface EACObjectIdentifiers {
    public static final ASN1ObjectIdentifier a = new ASN1ObjectIdentifier("0.4.0.127.0.7");
    public static final ASN1ObjectIdentifier b = a.b("2.2.1");
    public static final ASN1ObjectIdentifier c = b.b("1");
    public static final ASN1ObjectIdentifier d = b.b("2");
    public static final ASN1ObjectIdentifier e = a.b("2.2.3");
    public static final ASN1ObjectIdentifier f = e.b("1");
    public static final ASN1ObjectIdentifier g = f.b("1");
    public static final ASN1ObjectIdentifier h = e.b("2");
    public static final ASN1ObjectIdentifier i = h.b("1");
    public static final ASN1ObjectIdentifier j = a.b("2.2.2");
    public static final ASN1ObjectIdentifier k = j.b("1");
    public static final ASN1ObjectIdentifier l = k.b("1");
    public static final ASN1ObjectIdentifier m = k.b("2");
    public static final ASN1ObjectIdentifier n = k.b("3");
    public static final ASN1ObjectIdentifier o = k.b("4");
    public static final ASN1ObjectIdentifier p = k.b("5");
    public static final ASN1ObjectIdentifier q = k.b("6");
    public static final ASN1ObjectIdentifier r = j.b("2");
    public static final ASN1ObjectIdentifier s = r.b("1");
    public static final ASN1ObjectIdentifier t = r.b("2");
    public static final ASN1ObjectIdentifier u = r.b("3");
    public static final ASN1ObjectIdentifier v = r.b("4");
    public static final ASN1ObjectIdentifier w = r.b("5");
    public static final ASN1ObjectIdentifier x = a.b("3.1.2.1");
}
