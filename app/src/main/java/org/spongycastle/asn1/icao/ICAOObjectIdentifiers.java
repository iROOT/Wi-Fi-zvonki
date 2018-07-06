package org.spongycastle.asn1.icao;

import org.spongycastle.asn1.ASN1ObjectIdentifier;

public interface ICAOObjectIdentifiers {
    public static final ASN1ObjectIdentifier a = new ASN1ObjectIdentifier("2.23.136");
    public static final ASN1ObjectIdentifier b = a.b("1");
    public static final ASN1ObjectIdentifier c = b.b("1");
    public static final ASN1ObjectIdentifier d = c.b("1");
    public static final ASN1ObjectIdentifier e = c.b("2");
    public static final ASN1ObjectIdentifier f = c.b("3");
    public static final ASN1ObjectIdentifier g = c.b("4");
    public static final ASN1ObjectIdentifier h = c.b("5");
    public static final ASN1ObjectIdentifier i = c.b("6");
    public static final ASN1ObjectIdentifier j = i.b("1");
}
