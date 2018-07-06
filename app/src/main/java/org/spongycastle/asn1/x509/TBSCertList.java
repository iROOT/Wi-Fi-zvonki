package org.spongycastle.asn1.x509;

import java.util.Enumeration;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERGeneralizedTime;
import org.spongycastle.asn1.DERInteger;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERTaggedObject;
import org.spongycastle.asn1.DERUTCTime;
import org.spongycastle.asn1.x500.X500Name;

public class TBSCertList extends ASN1Object {
    ASN1Integer a;
    AlgorithmIdentifier b;
    X500Name c;
    Time d;
    Time e;
    ASN1Sequence f;
    Extensions g;

    public static class CRLEntry extends ASN1Object {
        ASN1Sequence a;
        Extensions b;

        private CRLEntry(ASN1Sequence aSN1Sequence) {
            if (aSN1Sequence.f() < 2 || aSN1Sequence.f() > 3) {
                throw new IllegalArgumentException("Bad sequence size: " + aSN1Sequence.f());
            }
            this.a = aSN1Sequence;
        }

        public static CRLEntry a(Object obj) {
            if (obj instanceof CRLEntry) {
                return (CRLEntry) obj;
            }
            if (obj != null) {
                return new CRLEntry(ASN1Sequence.a(obj));
            }
            return null;
        }

        public ASN1Integer d() {
            return DERInteger.a((Object) this.a.a(0));
        }

        public Time e() {
            return Time.a(this.a.a(1));
        }

        public Extensions f() {
            if (this.b == null && this.a.f() == 3) {
                this.b = Extensions.a(this.a.a(2));
            }
            return this.b;
        }

        public ASN1Primitive a() {
            return this.a;
        }

        public boolean g() {
            return this.a.f() == 3;
        }
    }

    private class EmptyEnumeration implements Enumeration {
        final /* synthetic */ TBSCertList a;

        private EmptyEnumeration(TBSCertList tBSCertList) {
            this.a = tBSCertList;
        }

        public boolean hasMoreElements() {
            return false;
        }

        public Object nextElement() {
            return null;
        }
    }

    private class RevokedCertificatesEnumeration implements Enumeration {
        final /* synthetic */ TBSCertList a;
        private final Enumeration b;

        RevokedCertificatesEnumeration(TBSCertList tBSCertList, Enumeration enumeration) {
            this.a = tBSCertList;
            this.b = enumeration;
        }

        public boolean hasMoreElements() {
            return this.b.hasMoreElements();
        }

        public Object nextElement() {
            return CRLEntry.a(this.b.nextElement());
        }
    }

    public static TBSCertList a(Object obj) {
        if (obj instanceof TBSCertList) {
            return (TBSCertList) obj;
        }
        if (obj != null) {
            return new TBSCertList(ASN1Sequence.a(obj));
        }
        return null;
    }

    public TBSCertList(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.f() < 3 || aSN1Sequence.f() > 7) {
            throw new IllegalArgumentException("Bad sequence size: " + aSN1Sequence.f());
        }
        int i = 0;
        if (aSN1Sequence.a(0) instanceof ASN1Integer) {
            this.a = DERInteger.a((Object) aSN1Sequence.a(0));
            i = 1;
        } else {
            this.a = null;
        }
        int i2 = i + 1;
        this.b = AlgorithmIdentifier.a(aSN1Sequence.a(i));
        int i3 = i2 + 1;
        this.c = X500Name.a(aSN1Sequence.a(i2));
        i = i3 + 1;
        this.d = Time.a(aSN1Sequence.a(i3));
        if (i < aSN1Sequence.f() && ((aSN1Sequence.a(i) instanceof DERUTCTime) || (aSN1Sequence.a(i) instanceof DERGeneralizedTime) || (aSN1Sequence.a(i) instanceof Time))) {
            i2 = i + 1;
            this.e = Time.a(aSN1Sequence.a(i));
            i = i2;
        }
        if (i < aSN1Sequence.f() && !(aSN1Sequence.a(i) instanceof DERTaggedObject)) {
            i2 = i + 1;
            this.f = ASN1Sequence.a(aSN1Sequence.a(i));
            i = i2;
        }
        if (i < aSN1Sequence.f() && (aSN1Sequence.a(i) instanceof DERTaggedObject)) {
            this.g = Extensions.a(ASN1Sequence.a((ASN1TaggedObject) aSN1Sequence.a(i), true));
        }
    }

    public int d() {
        if (this.a == null) {
            return 1;
        }
        return this.a.d().intValue() + 1;
    }

    public AlgorithmIdentifier e() {
        return this.b;
    }

    public X500Name f() {
        return this.c;
    }

    public Time g() {
        return this.d;
    }

    public Time h() {
        return this.e;
    }

    public Enumeration i() {
        if (this.f == null) {
            return new EmptyEnumeration();
        }
        return new RevokedCertificatesEnumeration(this, this.f.e());
    }

    public Extensions j() {
        return this.g;
    }

    public ASN1Primitive a() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        if (this.a != null) {
            aSN1EncodableVector.a(this.a);
        }
        aSN1EncodableVector.a(this.b);
        aSN1EncodableVector.a(this.c);
        aSN1EncodableVector.a(this.d);
        if (this.e != null) {
            aSN1EncodableVector.a(this.e);
        }
        if (this.f != null) {
            aSN1EncodableVector.a(this.f);
        }
        if (this.g != null) {
            aSN1EncodableVector.a(new DERTaggedObject(0, this.g));
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
