package org.spongycastle.asn1.pkcs;

import java.math.BigInteger;
import java.util.Enumeration;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERInteger;
import org.spongycastle.asn1.DERSequence;

public class DHParameter extends ASN1Object {
    ASN1Integer a;
    ASN1Integer b;
    ASN1Integer c;

    public DHParameter(BigInteger bigInteger, BigInteger bigInteger2, int i) {
        this.a = new ASN1Integer(bigInteger);
        this.b = new ASN1Integer(bigInteger2);
        if (i != 0) {
            this.c = new ASN1Integer((long) i);
        } else {
            this.c = null;
        }
    }

    public static DHParameter a(Object obj) {
        if (obj instanceof DHParameter) {
            return (DHParameter) obj;
        }
        if (obj != null) {
            return new DHParameter(ASN1Sequence.a(obj));
        }
        return null;
    }

    private DHParameter(ASN1Sequence aSN1Sequence) {
        Enumeration e = aSN1Sequence.e();
        this.a = DERInteger.a(e.nextElement());
        this.b = DERInteger.a(e.nextElement());
        if (e.hasMoreElements()) {
            this.c = (ASN1Integer) e.nextElement();
        } else {
            this.c = null;
        }
    }

    public BigInteger d() {
        return this.a.e();
    }

    public BigInteger e() {
        return this.b.e();
    }

    public BigInteger f() {
        if (this.c == null) {
            return null;
        }
        return this.c.e();
    }

    public ASN1Primitive a() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.a(this.a);
        aSN1EncodableVector.a(this.b);
        if (f() != null) {
            aSN1EncodableVector.a(this.c);
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
