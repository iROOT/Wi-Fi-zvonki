package org.spongycastle.asn1.x509;

import java.util.Enumeration;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERTaggedObject;

public class NameConstraints extends ASN1Object {
    private GeneralSubtree[] a;
    private GeneralSubtree[] b;

    public static NameConstraints a(Object obj) {
        if (obj instanceof NameConstraints) {
            return (NameConstraints) obj;
        }
        if (obj != null) {
            return new NameConstraints(ASN1Sequence.a(obj));
        }
        return null;
    }

    private NameConstraints(ASN1Sequence aSN1Sequence) {
        Enumeration e = aSN1Sequence.e();
        while (e.hasMoreElements()) {
            ASN1TaggedObject a = ASN1TaggedObject.a(e.nextElement());
            switch (a.d()) {
                case 0:
                    this.a = a(ASN1Sequence.a(a, false));
                    break;
                case 1:
                    this.b = a(ASN1Sequence.a(a, false));
                    break;
                default:
                    break;
            }
        }
    }

    private GeneralSubtree[] a(ASN1Sequence aSN1Sequence) {
        GeneralSubtree[] generalSubtreeArr = new GeneralSubtree[aSN1Sequence.f()];
        for (int i = 0; i != generalSubtreeArr.length; i++) {
            generalSubtreeArr[i] = GeneralSubtree.a(aSN1Sequence.a(i));
        }
        return generalSubtreeArr;
    }

    public GeneralSubtree[] d() {
        return this.a;
    }

    public GeneralSubtree[] e() {
        return this.b;
    }

    public ASN1Primitive a() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        if (this.a != null) {
            aSN1EncodableVector.a(new DERTaggedObject(false, 0, new DERSequence(this.a)));
        }
        if (this.b != null) {
            aSN1EncodableVector.a(new DERTaggedObject(false, 1, new DERSequence(this.b)));
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
