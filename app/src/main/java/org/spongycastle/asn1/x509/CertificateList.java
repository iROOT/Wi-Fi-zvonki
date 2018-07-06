package org.spongycastle.asn1.x509;

import java.util.Enumeration;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERBitString;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.x500.X500Name;

public class CertificateList extends ASN1Object {
    TBSCertList a;
    AlgorithmIdentifier b;
    DERBitString c;
    boolean d = false;
    int e;

    public static CertificateList a(Object obj) {
        if (obj instanceof CertificateList) {
            return (CertificateList) obj;
        }
        if (obj != null) {
            return new CertificateList(ASN1Sequence.a(obj));
        }
        return null;
    }

    public CertificateList(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.f() == 3) {
            this.a = TBSCertList.a(aSN1Sequence.a(0));
            this.b = AlgorithmIdentifier.a(aSN1Sequence.a(1));
            this.c = DERBitString.a(aSN1Sequence.a(2));
            return;
        }
        throw new IllegalArgumentException("sequence wrong size for CertificateList");
    }

    public TBSCertList d() {
        return this.a;
    }

    public Enumeration e() {
        return this.a.i();
    }

    public AlgorithmIdentifier f() {
        return this.b;
    }

    public DERBitString g() {
        return this.c;
    }

    public int h() {
        return this.a.d();
    }

    public X500Name i() {
        return this.a.f();
    }

    public Time j() {
        return this.a.g();
    }

    public Time k() {
        return this.a.h();
    }

    public ASN1Primitive a() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.a(this.a);
        aSN1EncodableVector.a(this.b);
        aSN1EncodableVector.a(this.c);
        return new DERSequence(aSN1EncodableVector);
    }

    public int hashCode() {
        if (!this.d) {
            this.e = super.hashCode();
            this.d = true;
        }
        return this.e;
    }
}
