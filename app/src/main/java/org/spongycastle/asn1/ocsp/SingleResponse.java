package org.spongycastle.asn1.ocsp;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1GeneralizedTime;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERGeneralizedTime;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERTaggedObject;
import org.spongycastle.asn1.x509.Extensions;

public class SingleResponse extends ASN1Object {
    private CertID a;
    private CertStatus b;
    private ASN1GeneralizedTime c;
    private ASN1GeneralizedTime d;
    private Extensions e;

    private SingleResponse(ASN1Sequence aSN1Sequence) {
        this.a = CertID.a(aSN1Sequence.a(0));
        this.b = CertStatus.a(aSN1Sequence.a(1));
        this.c = DERGeneralizedTime.a((Object) aSN1Sequence.a(2));
        if (aSN1Sequence.f() > 4) {
            this.d = DERGeneralizedTime.a((ASN1TaggedObject) aSN1Sequence.a(3), true);
            this.e = Extensions.a((ASN1TaggedObject) aSN1Sequence.a(4), true);
        } else if (aSN1Sequence.f() > 3) {
            ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject) aSN1Sequence.a(3);
            if (aSN1TaggedObject.d() == 0) {
                this.d = DERGeneralizedTime.a(aSN1TaggedObject, true);
            } else {
                this.e = Extensions.a(aSN1TaggedObject, true);
            }
        }
    }

    public static SingleResponse a(Object obj) {
        if (obj instanceof SingleResponse) {
            return (SingleResponse) obj;
        }
        if (obj != null) {
            return new SingleResponse(ASN1Sequence.a(obj));
        }
        return null;
    }

    public CertStatus d() {
        return this.b;
    }

    public Extensions e() {
        return this.e;
    }

    public ASN1Primitive a() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.a(this.a);
        aSN1EncodableVector.a(this.b);
        aSN1EncodableVector.a(this.c);
        if (this.d != null) {
            aSN1EncodableVector.a(new DERTaggedObject(true, 0, this.d));
        }
        if (this.e != null) {
            aSN1EncodableVector.a(new DERTaggedObject(true, 1, this.e));
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
