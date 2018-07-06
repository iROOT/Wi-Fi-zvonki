package org.spongycastle.asn1.x9;

import java.math.BigInteger;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.math.ec.ECFieldElement;
import org.spongycastle.math.ec.ECFieldElement.F2m;
import org.spongycastle.math.ec.ECFieldElement.Fp;

public class X9FieldElement extends ASN1Object {
    private static X9IntegerConverter b = new X9IntegerConverter();
    protected ECFieldElement a;

    public X9FieldElement(ECFieldElement eCFieldElement) {
        this.a = eCFieldElement;
    }

    public X9FieldElement(BigInteger bigInteger, ASN1OctetString aSN1OctetString) {
        this(new Fp(bigInteger, new BigInteger(1, aSN1OctetString.e())));
    }

    public X9FieldElement(int i, int i2, int i3, int i4, ASN1OctetString aSN1OctetString) {
        this(new F2m(i, i2, i3, i4, new BigInteger(1, aSN1OctetString.e())));
    }

    public ECFieldElement d() {
        return this.a;
    }

    public ASN1Primitive a() {
        return new DEROctetString(b.a(this.a.a(), b.a(this.a)));
    }
}
