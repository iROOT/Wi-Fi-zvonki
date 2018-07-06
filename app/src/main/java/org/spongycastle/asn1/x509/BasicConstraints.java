package org.spongycastle.asn1.x509;

import java.math.BigInteger;
import org.spongycastle.asn1.ASN1Boolean;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERBoolean;
import org.spongycastle.asn1.DERInteger;
import org.spongycastle.asn1.DERSequence;

public class BasicConstraints extends ASN1Object {
    ASN1Boolean a = DERBoolean.a(false);
    ASN1Integer b = null;

    public static BasicConstraints a(Object obj) {
        if (obj instanceof BasicConstraints) {
            return (BasicConstraints) obj;
        }
        if (obj instanceof X509Extension) {
            return a(X509Extension.a((X509Extension) obj));
        }
        if (obj != null) {
            return new BasicConstraints(ASN1Sequence.a(obj));
        }
        return null;
    }

    private BasicConstraints(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.f() == 0) {
            this.a = null;
            this.b = null;
            return;
        }
        if (aSN1Sequence.a(0) instanceof DERBoolean) {
            this.a = DERBoolean.a(aSN1Sequence.a(0));
        } else {
            this.a = null;
            this.b = DERInteger.a((Object) aSN1Sequence.a(0));
        }
        if (aSN1Sequence.f() <= 1) {
            return;
        }
        if (this.a != null) {
            this.b = DERInteger.a((Object) aSN1Sequence.a(1));
            return;
        }
        throw new IllegalArgumentException("wrong sequence in constructor");
    }

    public boolean d() {
        return this.a != null && this.a.d();
    }

    public BigInteger e() {
        if (this.b != null) {
            return this.b.d();
        }
        return null;
    }

    public ASN1Primitive a() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        if (this.a != null) {
            aSN1EncodableVector.a(this.a);
        }
        if (this.b != null) {
            aSN1EncodableVector.a(this.b);
        }
        return new DERSequence(aSN1EncodableVector);
    }

    public String toString() {
        if (this.b != null) {
            return "BasicConstraints: isCa(" + d() + "), pathLenConstraint = " + this.b.d();
        }
        if (this.a == null) {
            return "BasicConstraints: isCa(false)";
        }
        return "BasicConstraints: isCa(" + d() + ")";
    }
}
