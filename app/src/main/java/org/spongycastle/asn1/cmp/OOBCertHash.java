package org.spongycastle.asn1.cmp;

import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.DERBitString;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERTaggedObject;
import org.spongycastle.asn1.crmf.CertId;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;

public class OOBCertHash extends ASN1Object {
    private AlgorithmIdentifier a;
    private CertId b;
    private DERBitString c;

    public ASN1Primitive a() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        a(aSN1EncodableVector, 0, this.a);
        a(aSN1EncodableVector, 1, this.b);
        aSN1EncodableVector.a(this.c);
        return new DERSequence(aSN1EncodableVector);
    }

    private void a(ASN1EncodableVector aSN1EncodableVector, int i, ASN1Encodable aSN1Encodable) {
        if (aSN1Encodable != null) {
            aSN1EncodableVector.a(new DERTaggedObject(true, i, aSN1Encodable));
        }
    }
}
