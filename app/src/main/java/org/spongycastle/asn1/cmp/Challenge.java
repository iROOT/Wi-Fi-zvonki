package org.spongycastle.asn1.cmp;

import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;

public class Challenge extends ASN1Object {
    private AlgorithmIdentifier a;
    private ASN1OctetString b;
    private ASN1OctetString c;

    public ASN1Primitive a() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        a(aSN1EncodableVector, this.a);
        aSN1EncodableVector.a(this.b);
        aSN1EncodableVector.a(this.c);
        return new DERSequence(aSN1EncodableVector);
    }

    private void a(ASN1EncodableVector aSN1EncodableVector, ASN1Encodable aSN1Encodable) {
        if (aSN1Encodable != null) {
            aSN1EncodableVector.a(aSN1Encodable);
        }
    }
}
