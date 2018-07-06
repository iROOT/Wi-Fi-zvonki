package org.spongycastle.asn1.tsp;

import org.spongycastle.asn1.ASN1Boolean;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERTaggedObject;
import org.spongycastle.asn1.x509.Extensions;

public class TimeStampReq extends ASN1Object {
    ASN1Integer a;
    MessageImprint b;
    ASN1ObjectIdentifier c;
    ASN1Integer d;
    ASN1Boolean e;
    Extensions f;

    public ASN1Primitive a() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.a(this.a);
        aSN1EncodableVector.a(this.b);
        if (this.c != null) {
            aSN1EncodableVector.a(this.c);
        }
        if (this.d != null) {
            aSN1EncodableVector.a(this.d);
        }
        if (this.e != null && this.e.d()) {
            aSN1EncodableVector.a(this.e);
        }
        if (this.f != null) {
            aSN1EncodableVector.a(new DERTaggedObject(false, 0, this.f));
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
