package org.spongycastle.asn1.x509;

import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERBitString;
import org.spongycastle.asn1.DERInteger;
import org.spongycastle.asn1.DERTaggedObject;
import org.spongycastle.asn1.x500.X500Name;

public class TBSCertificate extends ASN1Object {
    ASN1Sequence a;
    ASN1Integer b;
    ASN1Integer c;
    AlgorithmIdentifier d;
    X500Name e;
    Time f;
    Time g;
    X500Name h;
    SubjectPublicKeyInfo i;
    DERBitString j;
    DERBitString k;
    Extensions l;

    public static TBSCertificate a(Object obj) {
        if (obj instanceof TBSCertificate) {
            return (TBSCertificate) obj;
        }
        if (obj != null) {
            return new TBSCertificate(ASN1Sequence.a(obj));
        }
        return null;
    }

    private TBSCertificate(ASN1Sequence aSN1Sequence) {
        int i;
        this.a = aSN1Sequence;
        if (aSN1Sequence.a(0) instanceof DERTaggedObject) {
            this.b = DERInteger.a((ASN1TaggedObject) aSN1Sequence.a(0), true);
            i = 0;
        } else {
            this.b = new ASN1Integer(0);
            i = -1;
        }
        this.c = DERInteger.a((Object) aSN1Sequence.a(i + 1));
        this.d = AlgorithmIdentifier.a(aSN1Sequence.a(i + 2));
        this.e = X500Name.a(aSN1Sequence.a(i + 3));
        ASN1Sequence aSN1Sequence2 = (ASN1Sequence) aSN1Sequence.a(i + 4);
        this.f = Time.a(aSN1Sequence2.a(0));
        this.g = Time.a(aSN1Sequence2.a(1));
        this.h = X500Name.a(aSN1Sequence.a(i + 5));
        this.i = SubjectPublicKeyInfo.a(aSN1Sequence.a(i + 6));
        for (int f = (aSN1Sequence.f() - (i + 6)) - 1; f > 0; f--) {
            ASN1TaggedObject aSN1TaggedObject = (DERTaggedObject) aSN1Sequence.a((i + 6) + f);
            switch (aSN1TaggedObject.d()) {
                case 1:
                    this.j = DERBitString.a(aSN1TaggedObject, false);
                    break;
                case 2:
                    this.k = DERBitString.a(aSN1TaggedObject, false);
                    break;
                case 3:
                    this.l = Extensions.a(ASN1Sequence.a(aSN1TaggedObject, true));
                    break;
                default:
                    break;
            }
        }
    }

    public int d() {
        return this.b.d().intValue() + 1;
    }

    public ASN1Integer e() {
        return this.c;
    }

    public AlgorithmIdentifier f() {
        return this.d;
    }

    public X500Name g() {
        return this.e;
    }

    public Time h() {
        return this.f;
    }

    public Time i() {
        return this.g;
    }

    public X500Name j() {
        return this.h;
    }

    public SubjectPublicKeyInfo k() {
        return this.i;
    }

    public DERBitString l() {
        return this.j;
    }

    public DERBitString m() {
        return this.k;
    }

    public Extensions n() {
        return this.l;
    }

    public ASN1Primitive a() {
        return this.a;
    }
}
