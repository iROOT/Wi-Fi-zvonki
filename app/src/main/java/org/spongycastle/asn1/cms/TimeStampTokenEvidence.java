package org.spongycastle.asn1.cms;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.DERSequence;

public class TimeStampTokenEvidence extends ASN1Object {
    private TimeStampAndCRL[] a;

    public ASN1Primitive a() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        for (int i = 0; i != this.a.length; i++) {
            aSN1EncodableVector.a(this.a[i]);
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
