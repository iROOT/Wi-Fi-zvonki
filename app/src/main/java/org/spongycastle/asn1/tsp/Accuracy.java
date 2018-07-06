package org.spongycastle.asn1.tsp;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERTaggedObject;

public class Accuracy extends ASN1Object {
    ASN1Integer a;
    ASN1Integer b;
    ASN1Integer c;

    protected Accuracy() {
    }

    public ASN1Primitive a() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        if (this.a != null) {
            aSN1EncodableVector.a(this.a);
        }
        if (this.b != null) {
            aSN1EncodableVector.a(new DERTaggedObject(false, 0, this.b));
        }
        if (this.c != null) {
            aSN1EncodableVector.a(new DERTaggedObject(false, 1, this.c));
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
