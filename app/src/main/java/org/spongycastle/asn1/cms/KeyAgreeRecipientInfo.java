package org.spongycastle.asn1.cms;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERTaggedObject;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;

public class KeyAgreeRecipientInfo extends ASN1Object {
    private ASN1Integer a;
    private OriginatorIdentifierOrKey b;
    private ASN1OctetString c;
    private AlgorithmIdentifier d;
    private ASN1Sequence e;

    public ASN1Primitive a() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.a(this.a);
        aSN1EncodableVector.a(new DERTaggedObject(true, 0, this.b));
        if (this.c != null) {
            aSN1EncodableVector.a(new DERTaggedObject(true, 1, this.c));
        }
        aSN1EncodableVector.a(this.d);
        aSN1EncodableVector.a(this.e);
        return new DERSequence(aSN1EncodableVector);
    }
}
