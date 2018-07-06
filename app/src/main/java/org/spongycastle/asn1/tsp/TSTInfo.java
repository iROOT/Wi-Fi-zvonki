package org.spongycastle.asn1.tsp;

import org.spongycastle.asn1.ASN1Boolean;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1GeneralizedTime;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERTaggedObject;
import org.spongycastle.asn1.x509.Extensions;
import org.spongycastle.asn1.x509.GeneralName;

public class TSTInfo extends ASN1Object {
    private ASN1Integer a;
    private ASN1ObjectIdentifier b;
    private MessageImprint c;
    private ASN1Integer d;
    private ASN1GeneralizedTime e;
    private Accuracy f;
    private ASN1Boolean g;
    private ASN1Integer h;
    private GeneralName i;
    private Extensions j;

    public ASN1Primitive a() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.a(this.a);
        aSN1EncodableVector.a(this.b);
        aSN1EncodableVector.a(this.c);
        aSN1EncodableVector.a(this.d);
        aSN1EncodableVector.a(this.e);
        if (this.f != null) {
            aSN1EncodableVector.a(this.f);
        }
        if (this.g != null && this.g.d()) {
            aSN1EncodableVector.a(this.g);
        }
        if (this.h != null) {
            aSN1EncodableVector.a(this.h);
        }
        if (this.i != null) {
            aSN1EncodableVector.a(new DERTaggedObject(true, 0, this.i));
        }
        if (this.j != null) {
            aSN1EncodableVector.a(new DERTaggedObject(false, 1, this.j));
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
