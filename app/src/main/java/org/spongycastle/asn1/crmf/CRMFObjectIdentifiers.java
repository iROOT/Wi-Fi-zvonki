package org.spongycastle.asn1.crmf;

import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.pkcs.PKCSObjectIdentifiers;

public interface CRMFObjectIdentifiers {
    public static final ASN1ObjectIdentifier a = new ASN1ObjectIdentifier("1.3.6.1.5.5.7");
    public static final ASN1ObjectIdentifier b = a.b("5");
    public static final ASN1ObjectIdentifier c = b.b("1");
    public static final ASN1ObjectIdentifier d = c.b("1");
    public static final ASN1ObjectIdentifier e = c.b("2");
    public static final ASN1ObjectIdentifier f = c.b("3");
    public static final ASN1ObjectIdentifier g = c.b("4");
    public static final ASN1ObjectIdentifier h = PKCSObjectIdentifiers.at.b("21");
}
