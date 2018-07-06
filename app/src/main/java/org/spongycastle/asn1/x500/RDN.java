package org.spongycastle.asn1.x500;

import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Set;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERSet;

public class RDN extends ASN1Object {
    private ASN1Set a;

    private RDN(ASN1Set aSN1Set) {
        this.a = aSN1Set;
    }

    public static RDN a(Object obj) {
        if (obj instanceof RDN) {
            return (RDN) obj;
        }
        if (obj != null) {
            return new RDN(ASN1Set.a(obj));
        }
        return null;
    }

    public RDN(ASN1ObjectIdentifier aSN1ObjectIdentifier, ASN1Encodable aSN1Encodable) {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.a((ASN1Encodable) aSN1ObjectIdentifier);
        aSN1EncodableVector.a(aSN1Encodable);
        this.a = new DERSet(new DERSequence(aSN1EncodableVector));
    }

    public RDN(AttributeTypeAndValue[] attributeTypeAndValueArr) {
        this.a = new DERSet((ASN1Encodable[]) attributeTypeAndValueArr);
    }

    public boolean d() {
        return this.a.e() > 1;
    }

    public AttributeTypeAndValue e() {
        if (this.a.e() == 0) {
            return null;
        }
        return AttributeTypeAndValue.a(this.a.a(0));
    }

    public AttributeTypeAndValue[] f() {
        AttributeTypeAndValue[] attributeTypeAndValueArr = new AttributeTypeAndValue[this.a.e()];
        for (int i = 0; i != attributeTypeAndValueArr.length; i++) {
            attributeTypeAndValueArr[i] = AttributeTypeAndValue.a(this.a.a(i));
        }
        return attributeTypeAndValueArr;
    }

    public ASN1Primitive a() {
        return this.a;
    }
}
