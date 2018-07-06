package org.spongycastle.asn1.esf;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.DERSequence;

public class CrlValidatedID extends ASN1Object {
    private OtherHash a;
    private CrlIdentifier b;

    public ASN1Primitive a() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.a(this.a.a());
        if (this.b != null) {
            aSN1EncodableVector.a(this.b.a());
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
