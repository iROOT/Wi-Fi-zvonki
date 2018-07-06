package org.spongycastle.asn1.ocsp;

import java.math.BigInteger;
import org.spongycastle.asn1.ASN1Enumerated;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.DEREnumerated;

public class OCSPResponseStatus extends ASN1Object {
    private ASN1Enumerated a;

    private OCSPResponseStatus(ASN1Enumerated aSN1Enumerated) {
        this.a = aSN1Enumerated;
    }

    public static OCSPResponseStatus a(Object obj) {
        if (obj instanceof OCSPResponseStatus) {
            return (OCSPResponseStatus) obj;
        }
        if (obj != null) {
            return new OCSPResponseStatus(DEREnumerated.a(obj));
        }
        return null;
    }

    public BigInteger d() {
        return this.a.d();
    }

    public ASN1Primitive a() {
        return this.a;
    }
}
