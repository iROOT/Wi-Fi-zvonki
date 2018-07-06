package org.spongycastle.asn1.x509;

import java.math.BigInteger;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERInteger;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERTaggedObject;

public class GeneralSubtree extends ASN1Object {
    private static final BigInteger a = BigInteger.valueOf(0);
    private GeneralName b;
    private ASN1Integer c;
    private ASN1Integer d;

    private GeneralSubtree(ASN1Sequence aSN1Sequence) {
        this.b = GeneralName.a(aSN1Sequence.a(0));
        ASN1TaggedObject a;
        switch (aSN1Sequence.f()) {
            case 1:
                return;
            case 2:
                a = ASN1TaggedObject.a(aSN1Sequence.a(1));
                switch (a.d()) {
                    case 0:
                        this.c = DERInteger.a(a, false);
                        return;
                    case 1:
                        this.d = DERInteger.a(a, false);
                        return;
                    default:
                        throw new IllegalArgumentException("Bad tag number: " + a.d());
                }
            case 3:
                a = ASN1TaggedObject.a(aSN1Sequence.a(1));
                if (a.d() != 0) {
                    throw new IllegalArgumentException("Bad tag number for 'minimum': " + a.d());
                }
                this.c = DERInteger.a(a, false);
                a = ASN1TaggedObject.a(aSN1Sequence.a(2));
                if (a.d() != 1) {
                    throw new IllegalArgumentException("Bad tag number for 'maximum': " + a.d());
                }
                this.d = DERInteger.a(a, false);
                return;
            default:
                throw new IllegalArgumentException("Bad sequence size: " + aSN1Sequence.f());
        }
    }

    public static GeneralSubtree a(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof GeneralSubtree) {
            return (GeneralSubtree) obj;
        }
        return new GeneralSubtree(ASN1Sequence.a(obj));
    }

    public GeneralName d() {
        return this.b;
    }

    public ASN1Primitive a() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.a(this.b);
        if (!(this.c == null || this.c.d().equals(a))) {
            aSN1EncodableVector.a(new DERTaggedObject(false, 0, this.c));
        }
        if (this.d != null) {
            aSN1EncodableVector.a(new DERTaggedObject(false, 1, this.d));
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
