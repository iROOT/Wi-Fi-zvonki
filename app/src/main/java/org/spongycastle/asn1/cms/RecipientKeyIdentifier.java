package org.spongycastle.asn1.cms;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.DERGeneralizedTime;
import org.spongycastle.asn1.DERSequence;

public class RecipientKeyIdentifier extends ASN1Object {
    private ASN1OctetString a;
    private DERGeneralizedTime b;
    private OtherKeyAttribute c;

    public ASN1Primitive a() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.a(this.a);
        if (this.b != null) {
            aSN1EncodableVector.a(this.b);
        }
        if (this.c != null) {
            aSN1EncodableVector.a(this.c);
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
