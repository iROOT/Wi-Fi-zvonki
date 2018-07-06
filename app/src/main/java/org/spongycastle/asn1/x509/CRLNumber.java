package org.spongycastle.asn1.x509;

import java.math.BigInteger;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;

public class CRLNumber extends ASN1Object {
    private BigInteger a;

    public CRLNumber(BigInteger bigInteger) {
        this.a = bigInteger;
    }

    public BigInteger d() {
        return this.a;
    }

    public String toString() {
        return "CRLNumber: " + d();
    }

    public ASN1Primitive a() {
        return new ASN1Integer(this.a);
    }
}
