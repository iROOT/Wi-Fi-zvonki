package org.spongycastle.asn1.isismtt;

import org.spongycastle.asn1.ASN1ObjectIdentifier;

public interface ISISMTTObjectIdentifiers {
    public static final ASN1ObjectIdentifier a = new ASN1ObjectIdentifier("1.3.36.8");
    public static final ASN1ObjectIdentifier b = a.b("1");
    public static final ASN1ObjectIdentifier c = b.b("1");
    public static final ASN1ObjectIdentifier d = a.b("3");
    public static final ASN1ObjectIdentifier e = d.b("1");
    public static final ASN1ObjectIdentifier f = d.b("2");
    public static final ASN1ObjectIdentifier g = d.b("3");
    public static final ASN1ObjectIdentifier h = d.b("4");
    public static final ASN1ObjectIdentifier i = d.b("5");
    public static final ASN1ObjectIdentifier j = d.b("6");
    public static final ASN1ObjectIdentifier k = d.b("7");
    public static final ASN1ObjectIdentifier l = d.b("8");
    public static final ASN1ObjectIdentifier m = d.b("9");
    public static final ASN1ObjectIdentifier n = d.b("10");
    public static final ASN1ObjectIdentifier o = d.b("11");
    public static final ASN1ObjectIdentifier p = d.b("12");
    public static final ASN1ObjectIdentifier q = d.b("13");
    public static final ASN1ObjectIdentifier r = d.b("14");
    public static final ASN1ObjectIdentifier s = d.b("15");
    public static final ASN1ObjectIdentifier t = new ASN1ObjectIdentifier("0.2.262.1.10.12.0");
}
