package org.spongycastle.asn1.cms;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Set;
import org.spongycastle.asn1.BERSequence;
import org.spongycastle.asn1.DERTaggedObject;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;

public class AuthenticatedData extends ASN1Object {
    private ASN1Integer a;
    private OriginatorInfo b;
    private ASN1Set c;
    private AlgorithmIdentifier d;
    private AlgorithmIdentifier e;
    private ContentInfo f;
    private ASN1Set g;
    private ASN1OctetString h;
    private ASN1Set i;

    public ASN1Primitive a() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.a(this.a);
        if (this.b != null) {
            aSN1EncodableVector.a(new DERTaggedObject(false, 0, this.b));
        }
        aSN1EncodableVector.a(this.c);
        aSN1EncodableVector.a(this.d);
        if (this.e != null) {
            aSN1EncodableVector.a(new DERTaggedObject(false, 1, this.e));
        }
        aSN1EncodableVector.a(this.f);
        if (this.g != null) {
            aSN1EncodableVector.a(new DERTaggedObject(false, 2, this.g));
        }
        aSN1EncodableVector.a(this.h);
        if (this.i != null) {
            aSN1EncodableVector.a(new DERTaggedObject(false, 3, this.i));
        }
        return new BERSequence(aSN1EncodableVector);
    }
}
