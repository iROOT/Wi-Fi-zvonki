package org.spongycastle.asn1.cms;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Set;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERTaggedObject;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;

public class SignerInfo extends ASN1Object {
    private ASN1Integer a;
    private SignerIdentifier b;
    private AlgorithmIdentifier c;
    private ASN1Set d;
    private AlgorithmIdentifier e;
    private ASN1OctetString f;
    private ASN1Set g;

    public ASN1Primitive a() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.a(this.a);
        aSN1EncodableVector.a(this.b);
        aSN1EncodableVector.a(this.c);
        if (this.d != null) {
            aSN1EncodableVector.a(new DERTaggedObject(false, 0, this.d));
        }
        aSN1EncodableVector.a(this.e);
        aSN1EncodableVector.a(this.f);
        if (this.g != null) {
            aSN1EncodableVector.a(new DERTaggedObject(false, 1, this.g));
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
