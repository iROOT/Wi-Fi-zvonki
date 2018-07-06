package org.spongycastle.asn1.ocsp;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1GeneralizedTime;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERGeneralizedTime;
import org.spongycastle.asn1.DERInteger;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERTaggedObject;
import org.spongycastle.asn1.x509.Extensions;

public class ResponseData extends ASN1Object {
    private static final ASN1Integer a = new ASN1Integer(0);
    private boolean b;
    private ASN1Integer c;
    private ResponderID d;
    private ASN1GeneralizedTime e;
    private ASN1Sequence f;
    private Extensions g;

    private ResponseData(ASN1Sequence aSN1Sequence) {
        int i;
        if (!(aSN1Sequence.a(0) instanceof ASN1TaggedObject)) {
            this.c = a;
            i = 0;
        } else if (((ASN1TaggedObject) aSN1Sequence.a(0)).d() == 0) {
            this.b = true;
            this.c = DERInteger.a((ASN1TaggedObject) aSN1Sequence.a(0), true);
            i = 1;
        } else {
            this.c = a;
            i = 0;
        }
        int i2 = i + 1;
        this.d = ResponderID.a(aSN1Sequence.a(i));
        i = i2 + 1;
        this.e = DERGeneralizedTime.a((Object) aSN1Sequence.a(i2));
        i2 = i + 1;
        this.f = (ASN1Sequence) aSN1Sequence.a(i);
        if (aSN1Sequence.f() > i2) {
            this.g = Extensions.a((ASN1TaggedObject) aSN1Sequence.a(i2), true);
        }
    }

    public static ResponseData a(Object obj) {
        if (obj instanceof ResponseData) {
            return (ResponseData) obj;
        }
        if (obj != null) {
            return new ResponseData(ASN1Sequence.a(obj));
        }
        return null;
    }

    public ASN1Sequence d() {
        return this.f;
    }

    public Extensions e() {
        return this.g;
    }

    public ASN1Primitive a() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        if (this.b || !this.c.equals(a)) {
            aSN1EncodableVector.a(new DERTaggedObject(true, 0, this.c));
        }
        aSN1EncodableVector.a(this.d);
        aSN1EncodableVector.a(this.e);
        aSN1EncodableVector.a(this.f);
        if (this.g != null) {
            aSN1EncodableVector.a(new DERTaggedObject(true, 1, this.g));
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
