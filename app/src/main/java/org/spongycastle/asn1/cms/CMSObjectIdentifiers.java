package org.spongycastle.asn1.cms;

import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.pkcs.PKCSObjectIdentifiers;

public interface CMSObjectIdentifiers {
    public static final ASN1ObjectIdentifier a = PKCSObjectIdentifiers.O;
    public static final ASN1ObjectIdentifier b = PKCSObjectIdentifiers.P;
    public static final ASN1ObjectIdentifier c = PKCSObjectIdentifiers.Q;
    public static final ASN1ObjectIdentifier d = PKCSObjectIdentifiers.R;
    public static final ASN1ObjectIdentifier e = PKCSObjectIdentifiers.S;
    public static final ASN1ObjectIdentifier f = PKCSObjectIdentifiers.T;
    public static final ASN1ObjectIdentifier g = PKCSObjectIdentifiers.au;
    public static final ASN1ObjectIdentifier h = PKCSObjectIdentifiers.aw;
    public static final ASN1ObjectIdentifier i = PKCSObjectIdentifiers.ax;
    public static final ASN1ObjectIdentifier j = PKCSObjectIdentifiers.ay;
    public static final ASN1ObjectIdentifier k = new ASN1ObjectIdentifier("1.3.6.1.5.5.7.16");
    public static final ASN1ObjectIdentifier l = k.b("2");
    public static final ASN1ObjectIdentifier m = k.b("4");
}
