package org.spongycastle.asn1.x509;

import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERBitString;
import org.spongycastle.asn1.DERInteger;
import org.spongycastle.asn1.DERSequence;

public class AttributeCertificateInfo extends ASN1Object {
    private ASN1Integer a;
    private Holder b;
    private AttCertIssuer c;
    private AlgorithmIdentifier d;
    private ASN1Integer e;
    private AttCertValidityPeriod f;
    private ASN1Sequence g;
    private DERBitString h;
    private Extensions i;

    public static AttributeCertificateInfo a(Object obj) {
        if (obj instanceof AttributeCertificateInfo) {
            return (AttributeCertificateInfo) obj;
        }
        if (obj != null) {
            return new AttributeCertificateInfo(ASN1Sequence.a(obj));
        }
        return null;
    }

    private AttributeCertificateInfo(ASN1Sequence aSN1Sequence) {
        int i = 0;
        if (aSN1Sequence.f() < 6 || aSN1Sequence.f() > 9) {
            throw new IllegalArgumentException("Bad sequence size: " + aSN1Sequence.f());
        }
        if (aSN1Sequence.a(0) instanceof ASN1Integer) {
            this.a = DERInteger.a((Object) aSN1Sequence.a(0));
            i = 1;
        } else {
            this.a = new ASN1Integer(0);
        }
        this.b = Holder.a(aSN1Sequence.a(i));
        this.c = AttCertIssuer.a(aSN1Sequence.a(i + 1));
        this.d = AlgorithmIdentifier.a(aSN1Sequence.a(i + 2));
        this.e = DERInteger.a((Object) aSN1Sequence.a(i + 3));
        this.f = AttCertValidityPeriod.a(aSN1Sequence.a(i + 4));
        this.g = ASN1Sequence.a(aSN1Sequence.a(i + 5));
        for (i += 6; i < aSN1Sequence.f(); i++) {
            ASN1Encodable a = aSN1Sequence.a(i);
            if (a instanceof DERBitString) {
                this.h = DERBitString.a(aSN1Sequence.a(i));
            } else if ((a instanceof ASN1Sequence) || (a instanceof Extensions)) {
                this.i = Extensions.a(aSN1Sequence.a(i));
            }
        }
    }

    public Holder d() {
        return this.b;
    }

    public AttCertIssuer e() {
        return this.c;
    }

    public ASN1Integer f() {
        return this.e;
    }

    public AttCertValidityPeriod g() {
        return this.f;
    }

    public ASN1Sequence h() {
        return this.g;
    }

    public Extensions i() {
        return this.i;
    }

    public ASN1Primitive a() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        if (this.a.d().intValue() != 0) {
            aSN1EncodableVector.a(this.a);
        }
        aSN1EncodableVector.a(this.b);
        aSN1EncodableVector.a(this.c);
        aSN1EncodableVector.a(this.d);
        aSN1EncodableVector.a(this.e);
        aSN1EncodableVector.a(this.f);
        aSN1EncodableVector.a(this.g);
        if (this.h != null) {
            aSN1EncodableVector.a(this.h);
        }
        if (this.i != null) {
            aSN1EncodableVector.a(this.i);
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
