package org.spongycastle.asn1.x9;

import java.math.BigInteger;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERBitString;
import org.spongycastle.asn1.DERInteger;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.math.ec.ECCurve;
import org.spongycastle.math.ec.ECCurve.F2m;
import org.spongycastle.math.ec.ECCurve.Fp;

public class X9Curve extends ASN1Object implements X9ObjectIdentifiers {
    private ECCurve al;
    private byte[] am;
    private ASN1ObjectIdentifier an = null;

    public X9Curve(ECCurve eCCurve, byte[] bArr) {
        this.al = eCCurve;
        this.am = bArr;
        f();
    }

    public X9Curve(X9FieldID x9FieldID, ASN1Sequence aSN1Sequence) {
        this.an = x9FieldID.d();
        if (this.an.equals(c)) {
            BigInteger d = ((ASN1Integer) x9FieldID.e()).d();
            this.al = new Fp(d, new X9FieldElement(d, (ASN1OctetString) aSN1Sequence.a(0)).d().a(), new X9FieldElement(d, (ASN1OctetString) aSN1Sequence.a(1)).d().a());
        } else if (this.an.equals(d)) {
            int intValue;
            int i;
            int i2;
            ASN1Sequence a = ASN1Sequence.a(x9FieldID.e());
            int intValue2 = ((ASN1Integer) a.a(0)).d().intValue();
            ASN1ObjectIdentifier aSN1ObjectIdentifier = (ASN1ObjectIdentifier) a.a(1);
            if (aSN1ObjectIdentifier.equals(f)) {
                intValue = DERInteger.a((Object) a.a(2)).d().intValue();
                i = 0;
                i2 = 0;
            } else if (aSN1ObjectIdentifier.equals(g)) {
                ASN1Sequence a2 = ASN1Sequence.a(a.a(2));
                intValue = DERInteger.a((Object) a2.a(0)).d().intValue();
                i2 = DERInteger.a((Object) a2.a(1)).d().intValue();
                i = DERInteger.a((Object) a2.a(2)).d().intValue();
            } else {
                throw new IllegalArgumentException("This type of EC basis is not implemented");
            }
            X9FieldElement x9FieldElement = new X9FieldElement(intValue2, intValue, i2, i, (ASN1OctetString) aSN1Sequence.a(0));
            X9FieldElement x9FieldElement2 = new X9FieldElement(intValue2, intValue, i2, i, (ASN1OctetString) aSN1Sequence.a(1));
            this.al = new F2m(intValue2, intValue, i2, i, x9FieldElement.d().a(), x9FieldElement2.d().a());
        } else {
            throw new IllegalArgumentException("This type of ECCurve is not implemented");
        }
        if (aSN1Sequence.f() == 3) {
            this.am = ((DERBitString) aSN1Sequence.a(2)).d();
        }
    }

    private void f() {
        if (this.al instanceof Fp) {
            this.an = c;
        } else if (this.al instanceof F2m) {
            this.an = d;
        } else {
            throw new IllegalArgumentException("This type of ECCurve is not implemented");
        }
    }

    public ECCurve d() {
        return this.al;
    }

    public byte[] e() {
        return this.am;
    }

    public ASN1Primitive a() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        if (this.an.equals(c)) {
            aSN1EncodableVector.a(new X9FieldElement(this.al.f()).a());
            aSN1EncodableVector.a(new X9FieldElement(this.al.g()).a());
        } else if (this.an.equals(d)) {
            aSN1EncodableVector.a(new X9FieldElement(this.al.f()).a());
            aSN1EncodableVector.a(new X9FieldElement(this.al.g()).a());
        }
        if (this.am != null) {
            aSN1EncodableVector.a(new DERBitString(this.am));
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
