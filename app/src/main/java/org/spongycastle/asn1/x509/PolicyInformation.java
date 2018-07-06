package org.spongycastle.asn1.x509;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERObjectIdentifier;
import org.spongycastle.asn1.DERSequence;

public class PolicyInformation extends ASN1Object {
    private ASN1ObjectIdentifier a;
    private ASN1Sequence b;

    private PolicyInformation(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.f() < 1 || aSN1Sequence.f() > 2) {
            throw new IllegalArgumentException("Bad sequence size: " + aSN1Sequence.f());
        }
        this.a = DERObjectIdentifier.a((Object) aSN1Sequence.a(0));
        if (aSN1Sequence.f() > 1) {
            this.b = ASN1Sequence.a(aSN1Sequence.a(1));
        }
    }

    public static PolicyInformation a(Object obj) {
        if (obj == null || (obj instanceof PolicyInformation)) {
            return (PolicyInformation) obj;
        }
        return new PolicyInformation(ASN1Sequence.a(obj));
    }

    public ASN1ObjectIdentifier d() {
        return this.a;
    }

    public ASN1Sequence e() {
        return this.b;
    }

    public ASN1Primitive a() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.a(this.a);
        if (this.b != null) {
            aSN1EncodableVector.a(this.b);
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
