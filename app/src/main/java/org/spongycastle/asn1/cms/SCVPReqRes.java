package org.spongycastle.asn1.cms;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERTaggedObject;

public class SCVPReqRes extends ASN1Object {
    private final ContentInfo a;
    private final ContentInfo b;

    public ASN1Primitive a() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        if (this.a != null) {
            aSN1EncodableVector.a(new DERTaggedObject(true, 0, this.a));
        }
        aSN1EncodableVector.a(this.b);
        return new DERSequence(aSN1EncodableVector);
    }
}
