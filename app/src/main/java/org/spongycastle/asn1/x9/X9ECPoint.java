package org.spongycastle.asn1.x9;

import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.math.ec.ECCurve;
import org.spongycastle.math.ec.ECPoint;

public class X9ECPoint extends ASN1Object {
    ECPoint a;

    public X9ECPoint(ECPoint eCPoint) {
        this.a = eCPoint.k();
    }

    public X9ECPoint(ECCurve eCCurve, ASN1OctetString aSN1OctetString) {
        this.a = eCCurve.a(aSN1OctetString.e());
    }

    public ECPoint d() {
        return this.a;
    }

    public ASN1Primitive a() {
        return new DEROctetString(this.a.m());
    }
}
