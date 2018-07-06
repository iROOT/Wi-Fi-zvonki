package org.spongycastle.asn1.x509;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERTaggedObject;

public class Holder extends ASN1Object {
    IssuerSerial a;
    GeneralNames b;
    ObjectDigestInfo c;
    private int d = 1;

    public static Holder a(Object obj) {
        if (obj instanceof Holder) {
            return (Holder) obj;
        }
        if (obj instanceof ASN1TaggedObject) {
            return new Holder(ASN1TaggedObject.a(obj));
        }
        if (obj != null) {
            return new Holder(ASN1Sequence.a(obj));
        }
        return null;
    }

    private Holder(ASN1TaggedObject aSN1TaggedObject) {
        switch (aSN1TaggedObject.d()) {
            case 0:
                this.a = IssuerSerial.a(aSN1TaggedObject, true);
                break;
            case 1:
                this.b = GeneralNames.a(aSN1TaggedObject, true);
                break;
            default:
                throw new IllegalArgumentException("unknown tag in Holder");
        }
        this.d = 0;
    }

    private Holder(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.f() > 3) {
            throw new IllegalArgumentException("Bad sequence size: " + aSN1Sequence.f());
        }
        for (int i = 0; i != aSN1Sequence.f(); i++) {
            ASN1TaggedObject a = ASN1TaggedObject.a(aSN1Sequence.a(i));
            switch (a.d()) {
                case 0:
                    this.a = IssuerSerial.a(a, false);
                    break;
                case 1:
                    this.b = GeneralNames.a(a, false);
                    break;
                case 2:
                    this.c = ObjectDigestInfo.a(a, false);
                    break;
                default:
                    throw new IllegalArgumentException("unknown tag in Holder");
            }
        }
        this.d = 1;
    }

    public IssuerSerial d() {
        return this.a;
    }

    public GeneralNames e() {
        return this.b;
    }

    public ObjectDigestInfo f() {
        return this.c;
    }

    public ASN1Primitive a() {
        if (this.d == 1) {
            ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
            if (this.a != null) {
                aSN1EncodableVector.a(new DERTaggedObject(false, 0, this.a));
            }
            if (this.b != null) {
                aSN1EncodableVector.a(new DERTaggedObject(false, 1, this.b));
            }
            if (this.c != null) {
                aSN1EncodableVector.a(new DERTaggedObject(false, 2, this.c));
            }
            return new DERSequence(aSN1EncodableVector);
        } else if (this.b != null) {
            return new DERTaggedObject(true, 1, this.b);
        } else {
            return new DERTaggedObject(true, 0, this.a);
        }
    }
}
