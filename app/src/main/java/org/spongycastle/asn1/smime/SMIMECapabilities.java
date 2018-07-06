package org.spongycastle.asn1.smime;

import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.pkcs.PKCSObjectIdentifiers;

public class SMIMECapabilities extends ASN1Object {
    public static final ASN1ObjectIdentifier a = PKCSObjectIdentifiers.aq;
    public static final ASN1ObjectIdentifier b = PKCSObjectIdentifiers.ar;
    public static final ASN1ObjectIdentifier c = PKCSObjectIdentifiers.as;
    public static final ASN1ObjectIdentifier d = new ASN1ObjectIdentifier("1.3.14.3.2.7");
    public static final ASN1ObjectIdentifier e = PKCSObjectIdentifiers.B;
    public static final ASN1ObjectIdentifier f = PKCSObjectIdentifiers.C;
    private ASN1Sequence g;

    public ASN1Primitive a() {
        return this.g;
    }
}
