package org.spongycastle.asn1.x509;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Enumerated;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERBitString;
import org.spongycastle.asn1.DEREnumerated;
import org.spongycastle.asn1.DERObjectIdentifier;
import org.spongycastle.asn1.DERSequence;

public class ObjectDigestInfo extends ASN1Object {
    ASN1Enumerated a;
    ASN1ObjectIdentifier b;
    AlgorithmIdentifier c;
    DERBitString d;

    public static ObjectDigestInfo a(Object obj) {
        if (obj instanceof ObjectDigestInfo) {
            return (ObjectDigestInfo) obj;
        }
        if (obj != null) {
            return new ObjectDigestInfo(ASN1Sequence.a(obj));
        }
        return null;
    }

    public static ObjectDigestInfo a(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return a(ASN1Sequence.a(aSN1TaggedObject, z));
    }

    private ObjectDigestInfo(ASN1Sequence aSN1Sequence) {
        int i = 1;
        if (aSN1Sequence.f() > 4 || aSN1Sequence.f() < 3) {
            throw new IllegalArgumentException("Bad sequence size: " + aSN1Sequence.f());
        }
        this.a = DEREnumerated.a((Object) aSN1Sequence.a(0));
        if (aSN1Sequence.f() == 4) {
            this.b = DERObjectIdentifier.a((Object) aSN1Sequence.a(1));
        } else {
            i = 0;
        }
        this.c = AlgorithmIdentifier.a(aSN1Sequence.a(i + 1));
        this.d = DERBitString.a(aSN1Sequence.a(i + 2));
    }

    public ASN1Enumerated d() {
        return this.a;
    }

    public AlgorithmIdentifier e() {
        return this.c;
    }

    public DERBitString f() {
        return this.d;
    }

    public ASN1Primitive a() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.a(this.a);
        if (this.b != null) {
            aSN1EncodableVector.a(this.b);
        }
        aSN1EncodableVector.a(this.c);
        aSN1EncodableVector.a(this.d);
        return new DERSequence(aSN1EncodableVector);
    }
}
