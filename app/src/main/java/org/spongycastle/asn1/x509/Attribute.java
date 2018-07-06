package org.spongycastle.asn1.x509;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1Set;
import org.spongycastle.asn1.DERObjectIdentifier;
import org.spongycastle.asn1.DERSequence;

public class Attribute extends ASN1Object {
    private ASN1ObjectIdentifier a;
    private ASN1Set b;

    public static Attribute a(Object obj) {
        if (obj instanceof Attribute) {
            return (Attribute) obj;
        }
        if (obj != null) {
            return new Attribute(ASN1Sequence.a(obj));
        }
        return null;
    }

    private Attribute(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.f() != 2) {
            throw new IllegalArgumentException("Bad sequence size: " + aSN1Sequence.f());
        }
        this.a = DERObjectIdentifier.a((Object) aSN1Sequence.a(0));
        this.b = ASN1Set.a(aSN1Sequence.a(1));
    }

    public ASN1ObjectIdentifier d() {
        return new ASN1ObjectIdentifier(this.a.d());
    }

    public ASN1Primitive a() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.a(this.a);
        aSN1EncodableVector.a(this.b);
        return new DERSequence(aSN1EncodableVector);
    }
}
