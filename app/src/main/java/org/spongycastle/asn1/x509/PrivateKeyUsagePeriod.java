package org.spongycastle.asn1.x509;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.DERGeneralizedTime;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERTaggedObject;

public class PrivateKeyUsagePeriod extends ASN1Object {
    private DERGeneralizedTime a;
    private DERGeneralizedTime b;

    public ASN1Primitive a() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        if (this.a != null) {
            aSN1EncodableVector.a(new DERTaggedObject(false, 0, this.a));
        }
        if (this.b != null) {
            aSN1EncodableVector.a(new DERTaggedObject(false, 1, this.b));
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
