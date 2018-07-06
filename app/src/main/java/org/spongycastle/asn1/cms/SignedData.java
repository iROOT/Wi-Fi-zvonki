package org.spongycastle.asn1.cms;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Set;
import org.spongycastle.asn1.BERSequence;
import org.spongycastle.asn1.BERTaggedObject;
import org.spongycastle.asn1.DERTaggedObject;

public class SignedData extends ASN1Object {
    private static final ASN1Integer a = new ASN1Integer(1);
    private static final ASN1Integer b = new ASN1Integer(3);
    private static final ASN1Integer c = new ASN1Integer(4);
    private static final ASN1Integer d = new ASN1Integer(5);
    private ASN1Integer e;
    private ASN1Set f;
    private ContentInfo g;
    private ASN1Set h;
    private ASN1Set i;
    private ASN1Set j;
    private boolean k;
    private boolean l;

    public ASN1Primitive a() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.a(this.e);
        aSN1EncodableVector.a(this.f);
        aSN1EncodableVector.a(this.g);
        if (this.h != null) {
            if (this.k) {
                aSN1EncodableVector.a(new BERTaggedObject(false, 0, this.h));
            } else {
                aSN1EncodableVector.a(new DERTaggedObject(false, 0, this.h));
            }
        }
        if (this.i != null) {
            if (this.l) {
                aSN1EncodableVector.a(new BERTaggedObject(false, 1, this.i));
            } else {
                aSN1EncodableVector.a(new DERTaggedObject(false, 1, this.i));
            }
        }
        aSN1EncodableVector.a(this.j);
        return new BERSequence(aSN1EncodableVector);
    }
}
