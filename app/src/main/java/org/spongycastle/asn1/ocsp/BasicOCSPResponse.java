package org.spongycastle.asn1.ocsp;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERBitString;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERTaggedObject;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;

public class BasicOCSPResponse extends ASN1Object {
    private ResponseData a;
    private AlgorithmIdentifier b;
    private DERBitString c;
    private ASN1Sequence d;

    private BasicOCSPResponse(ASN1Sequence aSN1Sequence) {
        this.a = ResponseData.a(aSN1Sequence.a(0));
        this.b = AlgorithmIdentifier.a(aSN1Sequence.a(1));
        this.c = (DERBitString) aSN1Sequence.a(2);
        if (aSN1Sequence.f() > 3) {
            this.d = ASN1Sequence.a((ASN1TaggedObject) aSN1Sequence.a(3), true);
        }
    }

    public static BasicOCSPResponse a(Object obj) {
        if (obj instanceof BasicOCSPResponse) {
            return (BasicOCSPResponse) obj;
        }
        if (obj != null) {
            return new BasicOCSPResponse(ASN1Sequence.a(obj));
        }
        return null;
    }

    public ResponseData d() {
        return this.a;
    }

    public ASN1Primitive a() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.a(this.a);
        aSN1EncodableVector.a(this.b);
        aSN1EncodableVector.a(this.c);
        if (this.d != null) {
            aSN1EncodableVector.a(new DERTaggedObject(true, 0, this.d));
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
