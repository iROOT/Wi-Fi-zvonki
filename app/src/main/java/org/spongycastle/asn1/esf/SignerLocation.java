package org.spongycastle.asn1.esf;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERTaggedObject;
import org.spongycastle.asn1.DERUTF8String;

public class SignerLocation extends ASN1Object {
    private DERUTF8String a;
    private DERUTF8String b;
    private ASN1Sequence c;

    public ASN1Primitive a() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        if (this.a != null) {
            aSN1EncodableVector.a(new DERTaggedObject(true, 0, this.a));
        }
        if (this.b != null) {
            aSN1EncodableVector.a(new DERTaggedObject(true, 1, this.b));
        }
        if (this.c != null) {
            aSN1EncodableVector.a(new DERTaggedObject(true, 2, this.c));
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
