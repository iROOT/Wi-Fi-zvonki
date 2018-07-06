package org.spongycastle.asn1.smime;

import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.nist.NISTObjectIdentifiers;
import org.spongycastle.asn1.pkcs.PKCSObjectIdentifiers;

public class SMIMECapability extends ASN1Object {
    public static final ASN1ObjectIdentifier a = PKCSObjectIdentifiers.aq;
    public static final ASN1ObjectIdentifier b = PKCSObjectIdentifiers.ar;
    public static final ASN1ObjectIdentifier c = PKCSObjectIdentifiers.as;
    public static final ASN1ObjectIdentifier d = new ASN1ObjectIdentifier("1.3.14.3.2.7");
    public static final ASN1ObjectIdentifier e = PKCSObjectIdentifiers.B;
    public static final ASN1ObjectIdentifier f = PKCSObjectIdentifiers.C;
    public static final ASN1ObjectIdentifier g = NISTObjectIdentifiers.k;
    public static final ASN1ObjectIdentifier h = NISTObjectIdentifiers.r;
    public static final ASN1ObjectIdentifier i = NISTObjectIdentifiers.y;
    private ASN1ObjectIdentifier j;
    private ASN1Encodable k;

    public ASN1Primitive a() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.a(this.j);
        if (this.k != null) {
            aSN1EncodableVector.a(this.k);
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
