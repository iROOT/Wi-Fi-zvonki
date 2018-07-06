package org.spongycastle.asn1.cmp;

import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERTaggedObject;

public class KeyRecRepContent extends ASN1Object {
    private PKIStatusInfo a;
    private CMPCertificate b;
    private ASN1Sequence c;
    private ASN1Sequence d;

    public ASN1Primitive a() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.a(this.a);
        a(aSN1EncodableVector, 0, this.b);
        a(aSN1EncodableVector, 1, this.c);
        a(aSN1EncodableVector, 2, this.d);
        return new DERSequence(aSN1EncodableVector);
    }

    private void a(ASN1EncodableVector aSN1EncodableVector, int i, ASN1Encodable aSN1Encodable) {
        if (aSN1Encodable != null) {
            aSN1EncodableVector.a(new DERTaggedObject(true, i, aSN1Encodable));
        }
    }
}
