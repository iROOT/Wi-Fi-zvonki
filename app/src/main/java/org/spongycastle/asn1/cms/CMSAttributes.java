package org.spongycastle.asn1.cms;

import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.pkcs.PKCSObjectIdentifiers;

public interface CMSAttributes {
    public static final ASN1ObjectIdentifier a = PKCSObjectIdentifiers.X;
    public static final ASN1ObjectIdentifier b = PKCSObjectIdentifiers.Y;
    public static final ASN1ObjectIdentifier c = PKCSObjectIdentifiers.Z;
    public static final ASN1ObjectIdentifier d = PKCSObjectIdentifiers.aa;
    public static final ASN1ObjectIdentifier e = PKCSObjectIdentifiers.aK;
}
