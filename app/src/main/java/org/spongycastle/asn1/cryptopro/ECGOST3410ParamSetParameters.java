package org.spongycastle.asn1.cryptopro;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.DERSequence;

public class ECGOST3410ParamSetParameters extends ASN1Object {
    ASN1Integer a;
    ASN1Integer b;
    ASN1Integer c;
    ASN1Integer d;
    ASN1Integer e;
    ASN1Integer f;

    public ASN1Primitive a() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.a(this.c);
        aSN1EncodableVector.a(this.d);
        aSN1EncodableVector.a(this.a);
        aSN1EncodableVector.a(this.b);
        aSN1EncodableVector.a(this.e);
        aSN1EncodableVector.a(this.f);
        return new DERSequence(aSN1EncodableVector);
    }
}
