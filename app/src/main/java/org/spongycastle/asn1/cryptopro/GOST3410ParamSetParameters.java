package org.spongycastle.asn1.cryptopro;

import java.math.BigInteger;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.DERSequence;

public class GOST3410ParamSetParameters extends ASN1Object {
    int a;
    ASN1Integer b;
    ASN1Integer c;
    ASN1Integer d;

    public GOST3410ParamSetParameters(int i, BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
        this.a = i;
        this.b = new ASN1Integer(bigInteger);
        this.c = new ASN1Integer(bigInteger2);
        this.d = new ASN1Integer(bigInteger3);
    }

    public BigInteger d() {
        return this.b.e();
    }

    public BigInteger e() {
        return this.c.e();
    }

    public BigInteger f() {
        return this.d.e();
    }

    public ASN1Primitive a() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.a(new ASN1Integer((long) this.a));
        aSN1EncodableVector.a(this.b);
        aSN1EncodableVector.a(this.c);
        aSN1EncodableVector.a(this.d);
        return new DERSequence(aSN1EncodableVector);
    }
}
