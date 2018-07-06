package org.spongycastle.asn1.x509;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERObjectIdentifier;
import org.spongycastle.asn1.DERSequence;

public class AccessDescription extends ASN1Object {
    public static final ASN1ObjectIdentifier a = new ASN1ObjectIdentifier("1.3.6.1.5.5.7.48.2");
    public static final ASN1ObjectIdentifier b = new ASN1ObjectIdentifier("1.3.6.1.5.5.7.48.1");
    ASN1ObjectIdentifier c = null;
    GeneralName d = null;

    public static AccessDescription a(Object obj) {
        if (obj instanceof AccessDescription) {
            return (AccessDescription) obj;
        }
        if (obj != null) {
            return new AccessDescription(ASN1Sequence.a(obj));
        }
        return null;
    }

    private AccessDescription(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.f() != 2) {
            throw new IllegalArgumentException("wrong number of elements in sequence");
        }
        this.c = DERObjectIdentifier.a((Object) aSN1Sequence.a(0));
        this.d = GeneralName.a(aSN1Sequence.a(1));
    }

    public ASN1ObjectIdentifier d() {
        return this.c;
    }

    public GeneralName e() {
        return this.d;
    }

    public ASN1Primitive a() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.a(this.c);
        aSN1EncodableVector.a(this.d);
        return new DERSequence(aSN1EncodableVector);
    }

    public String toString() {
        return "AccessDescription: Oid(" + this.c.d() + ")";
    }
}
