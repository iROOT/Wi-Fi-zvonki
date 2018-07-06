package org.spongycastle.asn1.oiw;

import java.math.BigInteger;
import java.util.Enumeration;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERSequence;

public class ElGamalParameter extends ASN1Object {
    ASN1Integer a;
    ASN1Integer b;

    public ElGamalParameter(BigInteger bigInteger, BigInteger bigInteger2) {
        this.a = new ASN1Integer(bigInteger);
        this.b = new ASN1Integer(bigInteger2);
    }

    public ElGamalParameter(ASN1Sequence aSN1Sequence) {
        Enumeration e = aSN1Sequence.e();
        this.a = (ASN1Integer) e.nextElement();
        this.b = (ASN1Integer) e.nextElement();
    }

    public BigInteger d() {
        return this.a.e();
    }

    public BigInteger e() {
        return this.b.e();
    }

    public ASN1Primitive a() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.a(this.a);
        aSN1EncodableVector.a(this.b);
        return new DERSequence(aSN1EncodableVector);
    }
}
