package org.spongycastle.asn1.x509;

import java.math.BigInteger;
import java.util.Enumeration;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERInteger;
import org.spongycastle.asn1.DERSequence;

public class DSAParameter extends ASN1Object {
    ASN1Integer a;
    ASN1Integer b;
    ASN1Integer c;

    public static DSAParameter a(Object obj) {
        if (obj instanceof DSAParameter) {
            return (DSAParameter) obj;
        }
        if (obj != null) {
            return new DSAParameter(ASN1Sequence.a(obj));
        }
        return null;
    }

    public DSAParameter(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
        this.a = new ASN1Integer(bigInteger);
        this.b = new ASN1Integer(bigInteger2);
        this.c = new ASN1Integer(bigInteger3);
    }

    private DSAParameter(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.f() != 3) {
            throw new IllegalArgumentException("Bad sequence size: " + aSN1Sequence.f());
        }
        Enumeration e = aSN1Sequence.e();
        this.a = DERInteger.a(e.nextElement());
        this.b = DERInteger.a(e.nextElement());
        this.c = DERInteger.a(e.nextElement());
    }

    public BigInteger d() {
        return this.a.e();
    }

    public BigInteger e() {
        return this.b.e();
    }

    public BigInteger f() {
        return this.c.e();
    }

    public ASN1Primitive a() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.a(this.a);
        aSN1EncodableVector.a(this.b);
        aSN1EncodableVector.a(this.c);
        return new DERSequence(aSN1EncodableVector);
    }
}
