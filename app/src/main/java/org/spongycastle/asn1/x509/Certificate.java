package org.spongycastle.asn1.x509;

import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERBitString;
import org.spongycastle.asn1.x500.X500Name;

public class Certificate extends ASN1Object {
    ASN1Sequence a;
    TBSCertificate b;
    AlgorithmIdentifier c;
    DERBitString d;

    public static Certificate a(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return a(ASN1Sequence.a(aSN1TaggedObject, z));
    }

    public static Certificate a(Object obj) {
        if (obj instanceof Certificate) {
            return (Certificate) obj;
        }
        if (obj != null) {
            return new Certificate(ASN1Sequence.a(obj));
        }
        return null;
    }

    private Certificate(ASN1Sequence aSN1Sequence) {
        this.a = aSN1Sequence;
        if (aSN1Sequence.f() == 3) {
            this.b = TBSCertificate.a(aSN1Sequence.a(0));
            this.c = AlgorithmIdentifier.a(aSN1Sequence.a(1));
            this.d = DERBitString.a(aSN1Sequence.a(2));
            return;
        }
        throw new IllegalArgumentException("sequence wrong size for a certificate");
    }

    public TBSCertificate d() {
        return this.b;
    }

    public int e() {
        return this.b.d();
    }

    public ASN1Integer f() {
        return this.b.e();
    }

    public X500Name g() {
        return this.b.g();
    }

    public Time h() {
        return this.b.h();
    }

    public Time i() {
        return this.b.i();
    }

    public X500Name j() {
        return this.b.j();
    }

    public SubjectPublicKeyInfo k() {
        return this.b.k();
    }

    public AlgorithmIdentifier l() {
        return this.c;
    }

    public DERBitString m() {
        return this.d;
    }

    public ASN1Primitive a() {
        return this.a;
    }
}
