package org.spongycastle.asn1.cmp;

import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.DERSequence;

public class ErrorMsgContent extends ASN1Object {
    private PKIStatusInfo a;
    private ASN1Integer b;
    private PKIFreeText c;

    public ASN1Primitive a() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.a(this.a);
        a(aSN1EncodableVector, this.b);
        a(aSN1EncodableVector, this.c);
        return new DERSequence(aSN1EncodableVector);
    }

    private void a(ASN1EncodableVector aSN1EncodableVector, ASN1Encodable aSN1Encodable) {
        if (aSN1Encodable != null) {
            aSN1EncodableVector.a(aSN1Encodable);
        }
    }
}
