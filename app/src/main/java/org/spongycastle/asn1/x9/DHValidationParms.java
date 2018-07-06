package org.spongycastle.asn1.x9;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERBitString;
import org.spongycastle.asn1.DERInteger;
import org.spongycastle.asn1.DERSequence;

public class DHValidationParms extends ASN1Object {
    private DERBitString a;
    private ASN1Integer b;

    public static DHValidationParms a(Object obj) {
        if (obj == null || (obj instanceof DHDomainParameters)) {
            return (DHValidationParms) obj;
        }
        if (obj instanceof ASN1Sequence) {
            return new DHValidationParms((ASN1Sequence) obj);
        }
        throw new IllegalArgumentException("Invalid DHValidationParms: " + obj.getClass().getName());
    }

    private DHValidationParms(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.f() != 2) {
            throw new IllegalArgumentException("Bad sequence size: " + aSN1Sequence.f());
        }
        this.a = DERBitString.a(aSN1Sequence.a(0));
        this.b = DERInteger.a((Object) aSN1Sequence.a(1));
    }

    public DERBitString d() {
        return this.a;
    }

    public ASN1Integer e() {
        return this.b;
    }

    public ASN1Primitive a() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.a(this.a);
        aSN1EncodableVector.a(this.b);
        return new DERSequence(aSN1EncodableVector);
    }
}
