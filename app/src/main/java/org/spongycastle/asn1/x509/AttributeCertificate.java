package org.spongycastle.asn1.x509;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERBitString;
import org.spongycastle.asn1.DERSequence;

public class AttributeCertificate extends ASN1Object {
    AttributeCertificateInfo a;
    AlgorithmIdentifier b;
    DERBitString c;

    public static AttributeCertificate a(Object obj) {
        if (obj instanceof AttributeCertificate) {
            return (AttributeCertificate) obj;
        }
        if (obj != null) {
            return new AttributeCertificate(ASN1Sequence.a(obj));
        }
        return null;
    }

    public AttributeCertificate(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.f() != 3) {
            throw new IllegalArgumentException("Bad sequence size: " + aSN1Sequence.f());
        }
        this.a = AttributeCertificateInfo.a(aSN1Sequence.a(0));
        this.b = AlgorithmIdentifier.a(aSN1Sequence.a(1));
        this.c = DERBitString.a(aSN1Sequence.a(2));
    }

    public AttributeCertificateInfo d() {
        return this.a;
    }

    public ASN1Primitive a() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.a(this.a);
        aSN1EncodableVector.a(this.b);
        aSN1EncodableVector.a(this.c);
        return new DERSequence(aSN1EncodableVector);
    }
}
