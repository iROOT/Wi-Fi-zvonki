package org.spongycastle.asn1.ocsp;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERTaggedObject;
import org.spongycastle.asn1.x509.Extensions;
import org.spongycastle.asn1.x509.GeneralName;
import org.spongycastle.asn1.x509.X509Extensions;

public class TBSRequest extends ASN1Object {
    private static final ASN1Integer f = new ASN1Integer(0);
    ASN1Integer a = f;
    GeneralName b;
    ASN1Sequence c;
    Extensions d;
    boolean e;

    public TBSRequest(GeneralName generalName, ASN1Sequence aSN1Sequence, X509Extensions x509Extensions) {
        this.b = generalName;
        this.c = aSN1Sequence;
        this.d = Extensions.a((Object) x509Extensions);
    }

    public Extensions d() {
        return this.d;
    }

    public ASN1Primitive a() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        if (!this.a.equals(f) || this.e) {
            aSN1EncodableVector.a(new DERTaggedObject(true, 0, this.a));
        }
        if (this.b != null) {
            aSN1EncodableVector.a(new DERTaggedObject(true, 1, this.b));
        }
        aSN1EncodableVector.a(this.c);
        if (this.d != null) {
            aSN1EncodableVector.a(new DERTaggedObject(true, 2, this.d));
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
