package org.spongycastle.asn1.crmf;

import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.DERBitString;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERTaggedObject;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;

public class EncryptedValue extends ASN1Object {
    private AlgorithmIdentifier a;
    private AlgorithmIdentifier b;
    private DERBitString c;
    private AlgorithmIdentifier d;
    private ASN1OctetString e;
    private DERBitString f;

    public ASN1Primitive a() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        a(aSN1EncodableVector, 0, this.a);
        a(aSN1EncodableVector, 1, this.b);
        a(aSN1EncodableVector, 2, this.c);
        a(aSN1EncodableVector, 3, this.d);
        a(aSN1EncodableVector, 4, this.e);
        aSN1EncodableVector.a(this.f);
        return new DERSequence(aSN1EncodableVector);
    }

    private void a(ASN1EncodableVector aSN1EncodableVector, int i, ASN1Encodable aSN1Encodable) {
        if (aSN1Encodable != null) {
            aSN1EncodableVector.a(new DERTaggedObject(false, i, aSN1Encodable));
        }
    }
}
