package org.spongycastle.asn1.ocsp;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERTaggedObject;

public class OCSPResponse extends ASN1Object {
    OCSPResponseStatus a;
    ResponseBytes b;

    private OCSPResponse(ASN1Sequence aSN1Sequence) {
        this.a = OCSPResponseStatus.a(aSN1Sequence.a(0));
        if (aSN1Sequence.f() == 2) {
            this.b = ResponseBytes.a((ASN1TaggedObject) aSN1Sequence.a(1), true);
        }
    }

    public static OCSPResponse a(Object obj) {
        if (obj instanceof OCSPResponse) {
            return (OCSPResponse) obj;
        }
        if (obj != null) {
            return new OCSPResponse(ASN1Sequence.a(obj));
        }
        return null;
    }

    public OCSPResponseStatus d() {
        return this.a;
    }

    public ResponseBytes e() {
        return this.b;
    }

    public ASN1Primitive a() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.a(this.a);
        if (this.b != null) {
            aSN1EncodableVector.a(new DERTaggedObject(true, 0, this.b));
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
