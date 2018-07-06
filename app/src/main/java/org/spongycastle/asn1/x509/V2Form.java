package org.spongycastle.asn1.x509;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERTaggedObject;

public class V2Form extends ASN1Object {
    GeneralNames a;
    IssuerSerial b;
    ObjectDigestInfo c;

    public static V2Form a(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return a(ASN1Sequence.a(aSN1TaggedObject, z));
    }

    public static V2Form a(Object obj) {
        if (obj instanceof V2Form) {
            return (V2Form) obj;
        }
        if (obj != null) {
            return new V2Form(ASN1Sequence.a(obj));
        }
        return null;
    }

    public V2Form(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.f() > 3) {
            throw new IllegalArgumentException("Bad sequence size: " + aSN1Sequence.f());
        }
        int i;
        if (aSN1Sequence.a(0) instanceof ASN1TaggedObject) {
            i = 0;
        } else {
            this.a = GeneralNames.a(aSN1Sequence.a(0));
            i = 1;
        }
        while (i != aSN1Sequence.f()) {
            ASN1TaggedObject a = ASN1TaggedObject.a(aSN1Sequence.a(i));
            if (a.d() == 0) {
                this.b = IssuerSerial.a(a, false);
            } else if (a.d() == 1) {
                this.c = ObjectDigestInfo.a(a, false);
            } else {
                throw new IllegalArgumentException("Bad tag number: " + a.d());
            }
            i++;
        }
    }

    public GeneralNames d() {
        return this.a;
    }

    public IssuerSerial e() {
        return this.b;
    }

    public ASN1Primitive a() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        if (this.a != null) {
            aSN1EncodableVector.a(this.a);
        }
        if (this.b != null) {
            aSN1EncodableVector.a(new DERTaggedObject(false, 0, this.b));
        }
        if (this.c != null) {
            aSN1EncodableVector.a(new DERTaggedObject(false, 1, this.c));
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
