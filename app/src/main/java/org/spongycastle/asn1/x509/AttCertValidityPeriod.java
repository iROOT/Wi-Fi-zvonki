package org.spongycastle.asn1.x509;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1GeneralizedTime;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERGeneralizedTime;
import org.spongycastle.asn1.DERSequence;

public class AttCertValidityPeriod extends ASN1Object {
    ASN1GeneralizedTime a;
    ASN1GeneralizedTime b;

    public static AttCertValidityPeriod a(Object obj) {
        if (obj instanceof AttCertValidityPeriod) {
            return (AttCertValidityPeriod) obj;
        }
        if (obj != null) {
            return new AttCertValidityPeriod(ASN1Sequence.a(obj));
        }
        return null;
    }

    private AttCertValidityPeriod(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.f() != 2) {
            throw new IllegalArgumentException("Bad sequence size: " + aSN1Sequence.f());
        }
        this.a = DERGeneralizedTime.a((Object) aSN1Sequence.a(0));
        this.b = DERGeneralizedTime.a((Object) aSN1Sequence.a(1));
    }

    public ASN1GeneralizedTime d() {
        return this.a;
    }

    public ASN1GeneralizedTime e() {
        return this.b;
    }

    public ASN1Primitive a() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.a(this.a);
        aSN1EncodableVector.a(this.b);
        return new DERSequence(aSN1EncodableVector);
    }
}
