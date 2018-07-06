package org.spongycastle.asn1.x9;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERTaggedObject;

public class OtherInfo extends ASN1Object {
    private KeySpecificInfo a;
    private ASN1OctetString b;
    private ASN1OctetString c;

    public ASN1Primitive a() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.a(this.a);
        if (this.b != null) {
            aSN1EncodableVector.a(new DERTaggedObject(0, this.b));
        }
        aSN1EncodableVector.a(new DERTaggedObject(2, this.c));
        return new DERSequence(aSN1EncodableVector);
    }
}
