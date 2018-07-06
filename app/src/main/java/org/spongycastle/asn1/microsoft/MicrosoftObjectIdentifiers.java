package org.spongycastle.asn1.microsoft;

import org.spongycastle.asn1.ASN1ObjectIdentifier;

public interface MicrosoftObjectIdentifiers {
    public static final ASN1ObjectIdentifier a = new ASN1ObjectIdentifier("1.3.6.1.4.1.311");
    public static final ASN1ObjectIdentifier b = a.b("20.2");
    public static final ASN1ObjectIdentifier c = a.b("21.1");
    public static final ASN1ObjectIdentifier d = a.b("21.2");
    public static final ASN1ObjectIdentifier e = a.b("21.7");
    public static final ASN1ObjectIdentifier f = a.b("21.10");
}
